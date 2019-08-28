package com.uaes.esw.service.impl;

import com.uaes.esw.entity.mysql.ProjectCode;
import com.uaes.esw.entity.postgres.FieldValue;
import com.uaes.esw.entity.postgres.WorkLog;
import com.uaes.esw.entity.ucs.GsdjFenTan;
import com.uaes.esw.entity.ucs.JiraHours;
import com.uaes.esw.mapper.mysql.ProjectCodeMapper;
import com.uaes.esw.mapper.postgres.FieldValueMapper;
import com.uaes.esw.mapper.postgres.WorkLogMapper;
import com.uaes.esw.mapper.ucs.GsdjFenTanMapper;
import com.uaes.esw.mapper.ucs.GsdjFenTanViemMapper;
import com.uaes.esw.mapper.ucs.JiraHoursMapper;
import com.uaes.esw.service.GsdjfentanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service(value = "gsdjfentanService")
public class GsdjfentanServiceImpl implements GsdjfentanService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GsdjFenTanViemMapper gsdjFenTanViemMapper;
    @Autowired
    private GsdjFenTanMapper gsdjFenTanMapper;
    @Autowired
    private JiraHoursMapper jiraHoursMapper;
    @Autowired
    private WorkLogMapper workLogMapper;
    @Autowired
    private FieldValueMapper fieldValueMapper;
    @Autowired
    private ProjectCodeMapper projectCodeMapper;

    @Override
    public void fenTanHours(String registertime) throws Exception {
        List<JiraHours> jlist = jiraHoursMapper.getJiraHoursByDate(registertime);
        System.out.println(jlist.size());
        int add_count = 0;
        for (JiraHours jh : jlist) {
            GsdjFenTan gft = new GsdjFenTan();
            String bjid = "jira" + jh.getId();
            gft.setBjid(bjid);
            //登记人
            gft.setEmployeeid(jh.getPeople());
            //项目编号
            gft.setProjectno(jh.getProjectnumber());
            //登记日期
            gft.setRecodedate(jh.getRegistertime());
            //工作包名
            gft.setWorkpackage(jh.getWpname());
            float taskHourShort = Float.parseFloat(jh.getTaskhour());
            gft.setHours(taskHourShort);
            gft.setBgDate(jh.getRegistertime());
            gft.setDpet1(jh.getDept());
            gft.setDpet2(jh.getSec());
            gft.setDpet3(jh.getGrp());
            List<GsdjFenTan> gsdj = gsdjFenTanMapper.getGsdjFenTanByDate(bjid);
            if (null == gsdj || gsdj.size() == 0) {
                gsdjFenTanMapper.insert(gft);
                System.out.println("bjid[" + bjid + "] add gsdj!!!!");
                add_count += 1;
            } else {
                System.out.println("update gsdj hours:[" + String.valueOf(gsdj.get(0).getHours()) + "] JiraHours hours:[" + jh.getTaskhour() + "] people:" + jh.getPeople());
                gsdjFenTanMapper.updateByBjid(gft);
            }
        }
        logger.info("registertime:" + registertime + "add gsdj add_count [" + add_count + "]");
    }

    /**
     * 获取两个日期间的每个日期
     */
    public static Date[] getDateArrays(Date start, Date end, int calendarType) {
        ArrayList<Date> ret = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        Date tmpDate = calendar.getTime();
        long endTime = end.getTime();
        while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
            ret.add(calendar.getTime());
            calendar.add(calendarType, 1);
            tmpDate = calendar.getTime();
        }
        Date[] dates = new Date[ret.size()];
        return ret.toArray(dates);
    }

    @Override
    public void exefenTanHours(String registertime) throws Exception {
        //registertime : yyyy-MM-dd_yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty(registertime)) {
            String createTime = sdf.format(new Date());
            System.out.println("-----------" + createTime);
            addJiraHoursToUcsHours(createTime);
            fenTanHours(sdf.format(new Date()));
        } else {
            Date[] dates = getDateArrays(sdf.parse(registertime.split("_")[0]), sdf.parse(registertime.split("_")[1]), Calendar.DAY_OF_YEAR);
            for (Date d : dates) {
                System.out.println("-----------" + sdf.format(d));
                addJiraHoursToUcsHours(sdf.format(d));
                fenTanHours(sdf.format(d));
            }
        }
    }

    public void addJiraHoursToUcsHours(String createdTime) throws Exception {
        List<WorkLog> workLogPostgres = workLogMapper.findWorkLogByCreateTime(createdTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String new_custom_field_id = fieldValueMapper.getCustomfieldId("com.atlassian.tutorial.myPlugin:get-upms-project").get(0).get("id").toString();
        for (WorkLog wl : workLogPostgres) {
            JiraHours jh = new JiraHours();
            jh.setCustom(wl.getWorklogbody());
            String startDate = sdf.format(wl.getStartdate());
            jh.setActualendtime(startDate);
            jh.setActualstarttime(startDate);
            jh.setRegistertime(startDate);
            String project_no = null;
            FieldValue fieldValue = fieldValueMapper.selectByIssueCustom(wl.getIssueid(), Long.parseLong(new_custom_field_id));
            String handleDataFlag = "old";
            if (fieldValue == null) {
                handleDataFlag = "old";

            } else if (StringUtils.isEmpty(fieldValue.getStringvalue())) {
                handleDataFlag = "old";
            } else {
                handleDataFlag = "new";
            }

            if ("old".equals(handleDataFlag)) {
                // handle old data
                fieldValue = fieldValueMapper.selectByIssueCustom(wl.getIssueid(), 15590L);
                if (fieldValue == null) {
                    continue;
                }
                String selId = fieldValue.getStringvalue();
                ProjectCode pc = projectCodeMapper.selectByPrimaryKey(Integer.parseInt(selId));
                project_no = pc.getCustomercode() + "/" + pc.getProjectcode();
            } else {
                // handle new data
                project_no = fieldValue.getStringvalue();
            }

            if ("XXX/YYYY".equals(project_no) || StringUtils.isEmpty(project_no)) {
                continue;
            }
            jh.setProjectnumber(project_no);
            jh.setPeople(wl.getAuthor());
            jh.setSystemname(wl.getGrouplevel());
            jh.setTaskhour(getHourByDateLong(wl.getTimeworked()));
            jh.setWpname("jira");
            String createTime = formatter.format(wl.getCreated());
            List<JiraHours> jhl = jiraHoursMapper.findWorkLogExist(createTime, wl.getAuthor());
            String info = "jh:id=" + jh.getId() + " createTime:" + createTime;
            if (jhl.size() > 0) {
                jh.setId(jhl.get(0).getId());
                jh.setJiracreatetime(createTime);
                logger.info("already exist [" + info + "] update people[" + jh.getPeople() + "]");
                jiraHoursMapper.updateJiraHours(jh);
            } else {
                logger.info("add successful [" + info + "]");
                jh.setJiracreatetime(createTime);
                jiraHoursMapper.insert(jh);
            }
        }
    }

    public String getHourByDateLong(Long dateLong) {
        String content = String.valueOf(dateLong / 3600f);
        String pattern = "\\d+.0+";
        boolean isMatch = Pattern.matches(pattern, content);
        if (isMatch) {
            content = content.substring(0, content.indexOf("."));
        }
        return content;
    }

    @Override
    public Map<String, String> getMonthHours(String userId, String month) throws Exception {
        Map<String, String> resMap = new HashMap<String, String>();
        List<Map> resMapList = gsdjFenTanMapper.getMonthHours(userId, month);
        if (resMapList != null && resMapList.size() == 2) {
            for (Map map : resMapList) {
                if (map.get("HOURTYPE").equals("actual")) {
                    resMap.put("actualHours", map.get("HOURSSUM").toString());
                } else if (map.get("HOURTYPE").equals("plan")) {
                    resMap.put("planHours", map.get("HOURSSUM").toString());
                }
            }
        }
        return resMap;
    }

    @Override
    public List<Map> getGroupLeader(String userId) {
        return gsdjFenTanMapper.getGroupLeader(userId);
    }
}
