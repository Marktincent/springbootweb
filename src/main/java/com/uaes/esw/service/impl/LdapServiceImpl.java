package com.uaes.esw.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.uaes.esw.service.LdapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author zhenghuan.wang
 */
@Service(value = "ldapService")
public class LdapServiceImpl implements LdapService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    Hashtable<String, String> HashEnv = new Hashtable<String, String>();

    @Override
    public String getManagerByUserid(String userid) {
        return "111";
    }


    public LdapServiceImpl() {
        String LDAP_URL = "ldap://174.34.50.5:389/";
        String adminName = "workflow@uaes.com";
        String adminPassword = "uaes,111";
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        HashEnv.put(Context.SECURITY_PRINCIPAL, adminName);
        HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword);
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        HashEnv.put(Context.PROVIDER_URL, LDAP_URL);
        HashEnv.put("java.naming.ldap.version", "3");
    }

    public List<List<String>> getLdapInfoList(String base_dn, String[] resultFileds) throws Exception {
        LdapContext ctx = new InitialLdapContext(HashEnv, null);
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchFilter = "(&(objectclass=user))";
        String[] base_dn_arr = base_dn.split(";");
        List<List<String>> userAdInfoList = new ArrayList<List<String>>();
        for (int i = 0; i < base_dn_arr.length; i++) {
            searchControls.setReturningAttributes(resultFileds);
            NamingEnumeration<SearchResult> answer = null;
            try {
                answer = ctx.search(base_dn_arr[i], searchFilter, searchControls);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("获取 {} 的用户失败,无法验证此OU链接，请检查是否正确！", base_dn_arr[i]);
                if (answer == null) {
                    break;
                }
            }
            List<String> resultList = null;
            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    try {
                        resultList = new ArrayList<String>();
                        for (NamingEnumeration ne = attrs.getAll(); ne.hasMore(); ) {
                            // 定义CN对象来获取用户对象属性
                            Attribute attr = (Attribute) ne.next();
                            // 读取属性值
                            Enumeration values = attr.getAll();
                            if (values != null) {
                                while (values.hasMoreElements()) {
                                    String key_value = attr.getID().toString() + "=" + values.nextElement().toString();
                                    resultList.add(key_value);
                                }
                            }
                        }
                        if (resultList.size() > 0) {
                            userAdInfoList.add(resultList);
                        }
                    } catch (NamingException e) {
                        logger.error("获取 {} 的用户失败！", base_dn_arr[i]);
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("-------------------------------------------------------");
        logger.info("------------------获取AD用户{}个--------------", userAdInfoList.size());
        ctx.close();
        return userAdInfoList;
    }

    public static void main(String[] args) throws Exception {
        LdapServiceImpl lsi = new LdapServiceImpl();
        String aaa = lsi.getManagerByUserid("aaa");
        System.out.println(aaa);
//        String returnedAtts[] = {"url", "displayname", "name", "userPrincipalName", "physicalDeliveryOfficeName",
//                "departmentNumber", "telephoneNumber", "homePhone", "mobile", "department", "sAMAccountName",
//                "mail", "Sn", "Givename", "Pager", "FacsimileTelephoneNumber", "ipPhone", "distinguishedName", "memberOf"};
        String returnedAtts[] = {"url", "displayname", "name", "departmentNumber", "department", "sAMAccountName", "mail", "Sn", "Givename", "distinguishedName", "memberOf"};
        //String ad_baseDN = "OU=NE1,OU=NE,OU=1_Shanghai,ou=1_UserAccount,DC=uaes,DC=com;OU=NE2,OU=NE,OU=1_Shanghai,ou=1_UserAccount,DC=uaes,DC=com";
        String ad_baseDN = "OU=NE1,OU=NE,OU=1_Shanghai,ou=1_UserAccount,DC=uaes,DC=com;OU=NE2,OU=NE,OU=1_Shanghai,ou=1_UserAccount,DC=uaes,DC=com";
        List<List<String>> userAdInfoList = lsi.getLdapInfoList(ad_baseDN, returnedAtts);
        //System.out.println(userAdInfoList);
        for (int i = 0; i < userAdInfoList.size(); i++) {
            System.out.println("-----------------------------------==" + i);
            for (int j = 0; j < userAdInfoList.get(i).size(); j++) {
                System.out.println(userAdInfoList.get(i).get(j));
            }
        }
    }
}
