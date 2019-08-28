package com.uaes.esw.controller;

import com.uaes.esw.service.GsdjfentanService;
import com.uaes.esw.service.MigrateService;
import com.uaes.esw.service.ProjectService;
import com.uaes.esw.service.SynchronizationService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class JiraProjectController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Autowired
    private ProjectService projectService;

    @Autowired
    private SynchronizationService synchronizationService;

    @Autowired
    private GsdjfentanService gsdjfentanService;

    @Autowired
    private MigrateService migrateService;

    /**
     * http://localhost:8030/synUpmsno
     * http://localhost:8030/synUpmsno?createTime=1995-01
     * http://localhost:8030/synUpmsno?createTime=1995-01_2010-01
     *
     * @param createTime createTime  null:同步当月项目号  1995-01:同步输入月项目号  1995-01_2010-01:同步输入区间月项目号
     */
    @RequestMapping("/synUpmsno")
    public void synUpmsno(@RequestParam(defaultValue = "", name = "createTime") String createTime) throws Exception {
        logger.info("Logger------synUpmsno exec Time:" + sdf.format(new Date()) + "---------createTime:" + createTime);
        if (StringUtils.isEmpty(createTime)) {
            projectService.synUpmsno(createTime);
        } else {
            if (createTime.indexOf("_") != -1) {
                List<String> l = projectService.getMonth(createTime.split("_")[0], createTime.split("_")[1]);
                for (String yearMonth : l) {
                    projectService.synUpmsno(yearMonth);
                }
            } else {
                projectService.synUpmsno(createTime);
            }
        }
    }

    /**
     * http://localhost:8030/synUpmsnoToJiraByOne?customercode=XXX&projectcode=YYYY
     *
     * @param customercode projectcode
     */
    @RequestMapping("/synUpmsnoToJiraByOne")
    public void synUpmsnoToJiraByOne(@RequestParam(defaultValue = "", name = "customercode") String customercode, @RequestParam(defaultValue = "", name = "projectcode") String projectcode) throws Exception {
        logger.info("Logger------synUpmsnoToJiraByOne exec Time:" + sdf.format(new Date()) + "---------customercode:" + customercode);
        projectService.synUpmsnoToJira(customercode, projectcode);
    }

    /**
     * http://localhost:8030/synUpmsnoToUcs
     * http://localhost:8030/synUpmsnoToUcs?createTime=1995-01
     * http://localhost:8030/synUpmsnoToUcs?createTime=1995-01_2010-01
     *
     * @param createTime createTime  null:同步当月项目号  1995-01:同步输入月项目号  1995-01_2010-01:同步输入区间月项目号
     */
    @RequestMapping("/synUpmsnoToUcs")
    public void synUpmsnoToUcs(@RequestParam(defaultValue = "", name = "createTime") String createTime) throws Exception {
        logger.info("Logger------synUpmsnoToUcs exec Time:" + sdf.format(new Date()) + "---------createTime:" + createTime);
        if (StringUtils.isEmpty(createTime)) {
            projectService.synUpmsnoToUcs(createTime);
        } else {
            if (createTime.indexOf("_") != -1) {
                List<String> l = projectService.getMonth(createTime.split("_")[0], createTime.split("_")[1]);
                for (String yearMonth : l) {
                    projectService.synUpmsnoToUcs(yearMonth);
                }
            } else {
                projectService.synUpmsnoToUcs(createTime);
            }
        }
    }

    @RequestMapping("/synAdAccountToUcs")
    public void synAdAccountToUcs(@RequestParam(defaultValue = "", name = "createTime") String createTime) throws Exception {
        logger.info("Logger------synAdAccountToUcs exec Time:" + sdf.format(new Date()) + "---------createTime:" + createTime);
        synchronizationService.synAdAccountToUcs();
    }

    @RequestMapping("/synData")
    public void synData() throws Exception {
        System.out.println("----------synData-------");
    }

    @RequestMapping("/gsftData")
    public void gsftData(@RequestParam(defaultValue = "", name = "synchronizeTime") String synchronizeTime) throws Exception {
        logger.info("Logger------gsftData exec Time:" + sdf.format(new Date()) + "---------synchronizeTime:" + synchronizeTime);
        System.out.println("----------gsft -------");
        //registertime : YYYY-MM-DD
        gsdjfentanService.exefenTanHours(synchronizeTime);
    }

    /**
     * http://localhost:8031/getMonthHours?userId=zhenghuan.wang&month=2019-01
     *
     * @param userId
     * @param month
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMonthHours", method = RequestMethod.GET)
    public String getMonthHours(@RequestParam(defaultValue = "", name = "userId") String userId, @RequestParam(defaultValue = "", name = "month") String month) {
        JSONObject result = new JSONObject();
        try {
            Map<String, String> resMap = gsdjfentanService.getMonthHours(userId, month);
            if (resMap.size() < 2) {
                result.put("planHours", "176");
                result.put("actualHours", "0");
            } else {
                result.put("planHours", resMap.get("planHours"));
                result.put("actualHours", resMap.get("actualHours"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("userId:" + userId + "---month:" + month + "---result:" + result.toString());
        return result.toString();
    }

    /**
     * http://174.34.90.112:8031/getUpmsProject?searchValue=718&rowCount=5&precise=F
     *
     * @param searchValue
     * @param rowCount
     * @param precise     是否精确查询  T/F
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUpmsProject", method = RequestMethod.GET)
    public String getUpmsProject(@RequestParam(defaultValue = "", name = "searchValue") String searchValue, @RequestParam(defaultValue = "20", name = "rowCount") int rowCount, @RequestParam(defaultValue = "F", name = "precise") String precise) {
        JSONObject result = new JSONObject();
        try {
            boolean preciseFlag = false;
            if ("T".equals(precise)) {
                preciseFlag = true;
            }
            List<Map> resMapList = projectService.getUpmsProject(searchValue, rowCount, preciseFlag);
            List<String> resList = new ArrayList<String>();
            for (int i = 0; i < resMapList.size(); i++) {
                resList.add(resMapList.get(i).get("upms_project").toString());
            }
            result.put("resData", resList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @ResponseBody
    @RequestMapping("/migrateData")
    public void migrateData() throws Exception {
        logger.info("Logger------migrateData Start Time:" + sdf.format(new Date()));
        migrateService.migrateData();
        logger.info("Logger------migrateData End Time:" + sdf.format(new Date()));
    }

    @ResponseBody
    @RequestMapping(value = "/getGroupLeader", method = RequestMethod.GET)
    public String getGroupLeader(@RequestParam(defaultValue = "", name = "userId") String userId) {
        JSONObject result = new JSONObject();
        try {
            List<Map> resMap = gsdjfentanService.getGroupLeader(userId);
            if (resMap.size() > 0) {
                result.put("groupLeader", resMap.get(0).get("FUEGO_ID"));
            } else {
                result.put("groupLeader", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("userId:" + userId + "---groupLeader:" + result.toString());
        return result.toString();
    }
}
