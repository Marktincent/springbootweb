package com.uaes.esw.service.ldap;

import com.sun.jndi.ldap.ctl.SortControl;
import com.sun.jndi.ldap.ctl.VirtualListViewControl;
import com.uaes.esw.entity.obpmdir.Participant;
import com.uaes.esw.entity.participant.AdAccount;
import com.uaes.esw.entity.participant.AdOU;
import com.uaes.esw.mapper.obpmdir.ParticipantMapper;
import com.uaes.esw.service.SynchronizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * @author zhenghuan.wang
 */
@Service(value = "synchronizationService")
public class SynchronizationServiceImpl implements SynchronizationService {
    @Value("${ldap.name}")
    private String name;
    @Value("${ldap.password}")
    private String password;
    @Value("${ldap.url}")
    private String url;
    @Value("${ldap.baseDomain}")
    private String baseDomain;

    @Autowired
    private ParticipantMapper participantMapper;

    public static Hashtable<String, String> HashEnv = new Hashtable<String, String>();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void synAdAccountToUcs() throws Exception {
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        HashEnv.put(Context.SECURITY_PRINCIPAL, name);
        HashEnv.put(Context.SECURITY_CREDENTIALS, password);
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        HashEnv.put(Context.PROVIDER_URL, url);
        HashEnv.put("java.naming.ldap.version", "3");
        executeADInfo();
    }

    public void executeADInfo() throws Exception {
        LdapContext ctx = new InitialLdapContext(HashEnv, null);
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] adbaseDN = baseDomain.split(";");
        List<AdOU> list = null;
        for (int i = 0; i < adbaseDN.length; i++) {
            String[] returnedAttr = {"url", "displayname", "name", "userPrincipalName", "physicalDeliveryOfficeName",
                    "departmentNumber", "telephoneNumber", "homePhone", "mobile", "department", "sAMAccountName",
                    "mail", "Sn", "Givename", "Pager", "FacsimileTelephoneNumber", "ipPhone", "distinguishedName"};
            // 这个属性集合要改动userPrincipalName表示登录名sAMAccountName显示名
            searchCtls.setReturningAttributes(returnedAttr);
            int totalResults = 0;
            list = new ArrayList<AdOU>();
            NamingEnumeration answer = null;
            try {
                answer = ctx.search(adbaseDN[i], "(&(objectclass=user))", searchCtls);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("获取 {} 的用户失败,无法验证此OU链接，请检查是否正确！", adbaseDN[i]);
                if (answer == null) {
                    continue;
                }
            }
            while (answer.hasMoreElements()) {
                // 定义获取组织机构的方法
                AdOU ou = new AdOU();
                // 同时创建一个用户
                AdAccount ad = new AdAccount();
                SearchResult sr = (SearchResult) answer.next();
                // 添加到OU对象中
                ou.setOu(sr.getName());
                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    try {
                        for (NamingEnumeration ne = attrs.getAll(); ne.hasMore(); ) {
                            // 定义CN对象来获取用户对象属性
                            Attribute attr = (Attribute) ne.next();
                            // 读取属性值
                            Enumeration values = attr.getAll();
                            if (values != null) {
                                while (values.hasMoreElements()) {
                                    sideAdd(ad, attr.getID().toString(), values.nextElement().toString());
                                }
                            }
                        }
                    } catch (NamingException e) {
                        logger.error("获取 {} 的用户失败！", adbaseDN[i]);
                        e.printStackTrace();
                        continue;
                    }
                }
                // 将用户保存在组织对象中去
                ou.setAccount(ad);
                totalResults++;
                // 保存到存储库数据中
                list.add(ou);
            }
            synchronizeUser(list, adbaseDN[i], totalResults);
        }
        ctx.close();
    }

    /**
     * 判断存储类型
     */
    public void sideAdd(AdAccount cn, String title, String value) {
        // 这里我们保存姓名和
        if (title.toLowerCase().equals("name")) {
            cn.setName(value);
        } else if (title.toLowerCase().equals("samaccountname")) {
            // 保存登录
            cn.setSAMAccountName(value);
        } else if (title.toLowerCase().equals("mail")) {
            // 保存电子邮件
            cn.setMail(value);
        } else if (title.toLowerCase().equals("sn")) {
            // 保存姓
            cn.setSn(value);
        } else if (title.toLowerCase().equals("givename")) {
            // 保存名
            cn.setGivenname(value);
        } else if (title.toLowerCase().equals("getfacsimiletelephonenumber")) {
            // 保存传真
            cn.setFacsimileTelephoneNumber(value);
        } else if (title.toLowerCase().equals("displayname")) {
            // 保存显示名称
            cn.setDisplayname(value);
        } else if (title.toLowerCase().equals("ipphone")) {
            // 主管
            cn.setIpPhone(value);
        } else if (title.toLowerCase().equals("pager")) {
            cn.setPager(value);
        } else if (title.toLowerCase().equals("distinguishedname")) {
            cn.setDistinguishedName(value);
        } else if (title.toLowerCase().equals("telephonenumber")) {
            cn.setTelephoneNumber(value);
        } else if (title.toLowerCase().equals("msExchUserCulture")) {
            cn.setMsExchUserCulture("msexchuserculture");
        }
    }

    public void synchronizeUser(List<AdOU> list, String adbaseDN, int totalResults) {
        int existCount = 0;
        int addCount = 0;
        StringBuffer addUserid = new StringBuffer();
        for (AdOU a : list) {
            AdAccount u = a.getAccount();
            String useridAD = u.getSAMAccountName();
            //System.out.println(useridAD);
            Participant p = new Participant();
            p.setFuegoId(useridAD);
            List<Participant> pList = participantMapper.selectIsExist(p);

            if (pList != null && pList.size() > 0) {
                existCount++;
                if (existCount == 1) {
                    addSynchronizeUser(useridAD, u, p);
                }
            } else {
                addCount++;
                addUserid.append(useridAD + ",");
            }
        }
//        logger.info("获取[AD]:[{}]", new Object[]{adbaseDN});
//        logger.info("--------:[用户个数]=[{}]", new Object[]{totalResults});
//        logger.info("--------:[判断ucs存在个数]=[{}]", new Object[]{existCount});
//        logger.info("--------:[ucs新同步个数]=[{}]", new Object[]{addCount});
//        logger.info("--------:[新增者]=[{}]", new Object[]{addUserid.toString()});
    }

    public void addSynchronizeUser(String useridAD, AdAccount u, Participant p) {
        try {
            String fuegoId = useridAD;
            Integer fuegoPermissions = 0;
            String displayName = u.getDisplayname();
            String fuegoDisplayname = displayName;
            if (displayName.indexOf("(") != -1) {
                fuegoDisplayname = displayName.substring(displayName.indexOf("(") + 1,
                        displayName.indexOf(")"));
            }
            String fuegoFirstName = fuegoDisplayname.substring(1);
            String fuegoLastName = fuegoDisplayname.substring(0, 1);
            String distinguishedName = u.getDistinguishedName();
            String fuegoOu = getUcsFuegoOu(distinguishedName);
            String fuegoStatus = "A";
            String fuegoTelephone = u.getTelephoneNumber() == null ? "" : u.getTelephoneNumber();
            String fuegoMail = u.getMail();
            byte[] fuegoPhoto = null;
            String fuegoRid = fuegoId.toLowerCase(); //小写
            String fuegoCreator = "system";
            Timestamp fuegoCrtime = new java.sql.Timestamp((new java.util.Date()).getTime());
            String fuegoModifier = "system";
            Timestamp fuegoMotime = fuegoCrtime;
            String fuegoFax = u.getFacsimileTelephoneNumber();
            String fuegoLocale = u.getMsExchUserCulture();
            String fuegoManager = "";
            String fuegoTimezone = "zh_CN";
            if (fuegoTelephone.length() > 24) {
                logger.info("用户:" + fuegoDisplayname + "的Telephone字段太长, 截取了前25位字符;");
                //fuegoTelephone = StringUtils.left(fuegoTelephone, 25);
            }

            Participant newParticipant = new Participant();
            newParticipant.setFuegoCreator(fuegoCreator);
            newParticipant.setFuegoCrtime(fuegoCrtime);
            newParticipant.setFuegoDisplayname(fuegoDisplayname);
            newParticipant.setFuegoFax(fuegoFax);
            newParticipant.setFuegoFirstname(fuegoFirstName);
            newParticipant.setFuegoId(fuegoId);
            newParticipant.setFuegoLastname(fuegoLastName);
            newParticipant.setFuegoLocale(fuegoLocale);
            newParticipant.setFuegoMail(fuegoMail);
            newParticipant.setFuegoManager(fuegoManager);
            newParticipant.setFuegoModifier(fuegoModifier);
            newParticipant.setFuegoMotime(fuegoMotime);
            newParticipant.setFuegoOu(fuegoOu);
            String pwd = "uaes,123";
            newParticipant.setFuegoPassword(pwd.getBytes());
            newParticipant.setFuegoPermissions(fuegoPermissions);
            newParticipant.setFuegoPhoto(fuegoPhoto);
            newParticipant.setFuegoRid(fuegoRid);
            newParticipant.setFuegoStatus(fuegoStatus);
            newParticipant.setFuegoTelephone(fuegoTelephone);
            newParticipant.setFuegoTimezone(fuegoTimezone);
            //ouService.save(newParticipant.getFuegoOu()); //添加OU
            //participantMapper.insert(newParticipant);
            //System.out.print("添加participant---userid=" + fuegoId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("添加AD中participant---useridAD=" + useridAD + "异常!");
        }
    }

    public String getUcsFuegoOu(String adDNInfo) {
        //CN=Li Zhiheng(李致恒)(UAES/NE23),OU=NE23,OU=NE2,OU=NE,OU=1_Shanghai,OU=1_UserAccount,DC=uaes,DC=com
        String adDNArr[] = adDNInfo.split(",");
        StringBuffer fuegoOu = new StringBuffer("");
        for (int i = adDNArr.length - 1; i >= 0; i--) {
            try {
                if (adDNArr[i].startsWith("OU=")) {
                    String tempStrArr[] = adDNArr[i].split("=");
                    if (tempStrArr[1].equals("1_UserAccount")) {
                        fuegoOu.append("UAES");
                    } else if (tempStrArr[1].equals("1_Shanghai")) {
                        fuegoOu.append("/P");
                    } else if (tempStrArr[1].equals("Users")) {
                        fuegoOu.append("");
                    } else if (tempStrArr[1].equals("8_TempUser")) {
                        fuegoOu.append("");
                    } else if (tempStrArr[1].equals("4_Chongqing")) {
                        fuegoOu.append("/P/CQB");
                    } else {
                        fuegoOu.append("/" + tempStrArr[1].toUpperCase());
                    }
                }
                if (adDNArr[i].startsWith("CN=EXTERNAL") && adDNInfo.indexOf("OU=8_TempUser") != -1) {
                    fuegoOu.append(adDNArr[i].substring(adDNArr[i].lastIndexOf("/"), adDNArr[i].length() - 1));
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        System.out.println(adDNInfo + "\r\n-- >" + fuegoOu.toString());
        return fuegoOu.toString();
    }

}
