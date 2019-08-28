package com.uaes.esw.controller;

import com.uaes.esw.service.WorkLogService;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WorkLogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private WorkLogService workLogService;

    /**
     * @param auth   auth
     * @param Period Period
     */
    @ResponseBody
    @RequestMapping("/getWorkLogByDataAndAuth")
    public String getWorkLogByDataAndAuth(@RequestParam(defaultValue = "", name = "auth") String auth,
                                             @RequestParam(defaultValue = "this month", name = "Period") String Period
    ) {
        String startDate = "";
        String endDate = "";
        Long totalHours = 0l;  //总工时

        String today = format.format(new Date());
        List<String> xData = new ArrayList<>(); //用来储存echarts图X轴的时间数据
        xData.clear();
        if (Period.equals("this month")) {
            //获取当月一号到目前的数据
            Calendar cal_1 = Calendar.getInstance();//获取当前日期
            cal_1.add(Calendar.MONTH, 0);
            cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            startDate = format.format(cal_1.getTime());
            endDate = format.format(new Date());
            //获取本月几号，并将其转换成数字
            Integer num = Integer.parseInt(endDate.substring(endDate.length() - 2, endDate.length()));
            for (int i = 1; i <= num; i++) {
                if (i < 10) {
                    if ((cal_1.get(Calendar.MONTH) + 1) < 10) {
                        xData.add(cal_1.get(Calendar.YEAR) + "-0" + (cal_1.get(Calendar.MONTH) + 1) + "-0" + i);
                    } else {
                        xData.add(cal_1.get(Calendar.YEAR) + "-" + (cal_1.get(Calendar.MONTH) + 1) + "-0" + i);
                    }
                } else {
                    if ((cal_1.get(Calendar.MONTH) + 1) < 10) {
                        xData.add(cal_1.get(Calendar.YEAR) + "-0" + (cal_1.get(Calendar.MONTH) + 1) + "-" + i);
                    } else {
                        xData.add(cal_1.get(Calendar.YEAR) + "-" + (cal_1.get(Calendar.MONTH) + 1) + "-" + i);
                    }
                }
            }
        } else if (Period.equals("this year")) {
            //获取今年一号到目前的数据
            Calendar cal_1 = Calendar.getInstance();//获取当前日期
            cal_1.add(Calendar.YEAR, 0);
            cal_1.set(Calendar.DAY_OF_YEAR, 1);//设置为1号,当前日期既为本月第一天
            startDate = format.format(cal_1.getTime());
            endDate = format.format(new Date());
            Integer num = Integer.parseInt(today.substring(today.length() - 5, today.length() - 3));
            //获取本月几号，并将其转换成数字
            for (int i = 1; i <= num; i++) {
                if (i < 10) {
                    if ((cal_1.get(Calendar.MONTH) + 1) < 10) {
                        xData.add(cal_1.get(Calendar.YEAR) + "-0" + i);
                    } else {
                        xData.add(cal_1.get(Calendar.YEAR) + "-" + i);
                    }
                } else {
                    if ((cal_1.get(Calendar.MONTH) + 1) < 10) {
                        xData.add(cal_1.get(Calendar.YEAR) + "-0" + i);
                    } else {
                        xData.add(cal_1.get(Calendar.YEAR) + "-" + i);
                    }
                }
            }
        } else {
            //获取去年一号到年底数据
            Calendar cal_1 = Calendar.getInstance();//获取当前日期
            cal_1.add(Calendar.YEAR, -1);
            cal_1.set(Calendar.DAY_OF_YEAR, 1);//设置为1号,当前日期既为本月第一天
            startDate = format.format(cal_1.getTime());
            cal_1.set(Calendar.DAY_OF_YEAR, cal_1.getActualMaximum(Calendar.DAY_OF_YEAR));
            endDate = format.format(cal_1.getTime());
            String num = cal_1.getWeekYear() - 1 + ""; //获取去年的年份
            for (int i = 1; i <= 12; i++) {
                if (i < 10) {
                    xData.add(num + "-0" + i + "");
                } else {
                    xData.add(num + "-" + i + "");
                }
            }
        }
        try {
            List<Map> listWorkLog = workLogService.getWorkLogByDataAndAuth(startDate, endDate, auth);
            List<Map> listIssue = workLogService.getIssueByDataAndAuth(startDate, endDate, auth);
            List<Map> listIssueType = workLogService.getAllIssueType();
            Map<String,Integer> issueTypehoursMap = new HashMap<String, Integer>(); //issuetype总计工时

            for(Map mp:listIssueType){
                Integer totalHoursWithIssueType = 0;
                for (Map p : listWorkLog) {
                    if(p.get("issuetype") != null && mp.get("id").equals(p.get("issuetype"))){
                        totalHoursWithIssueType = Integer.parseInt(p.get("timeworked").toString()) / 3600;
                        Integer hours = issueTypehoursMap.get(mp.get("pname").toString());
                        if(hours != null){
                            issueTypehoursMap.put(mp.get("pname").toString(),totalHoursWithIssueType + hours);
                        }else{
                            issueTypehoursMap.put(mp.get("pname").toString(),totalHoursWithIssueType);
                        }
                    }
                }
            }

            Map<String, String> date_issue_hour_map = new HashMap<String, String>();
            for (Map mp : listWorkLog) {
                String startdate = null;

                if(Period.equals("this month")){
                    startdate = mp.get("startdate").toString().substring(0, 10);
                }else{
                    startdate = mp.get("startdate").toString().substring(0, 7);
                }
                String hour = String.valueOf(Integer.parseInt(mp.get("timeworked").toString()) / 3600);
                Long tempHours = Long.parseLong(hour);
                totalHours = totalHours + tempHours;    //计算总工时
                String issuekey = mp.get("issuekey").toString();
                String s_i_key = startdate + "_" + issuekey;
                String s_i_val = date_issue_hour_map.get(s_i_key);
                if (StringUtils.isEmpty(s_i_val)) {
                    date_issue_hour_map.put(s_i_key, hour);
                } else {
                    String new_hour = String.valueOf(Long.parseLong(date_issue_hour_map.get(s_i_key)) + Long.parseLong(hour));
                    date_issue_hour_map.put(s_i_key, new_hour);
                }
            }

            List<String> listIssueString = new ArrayList<>();
            //新建Map，用来储存所有echarts所用的数据
            Map<String, List<String>> allData = new HashMap<>();
            //将储存所有issue的List<Map>转换成List<String>格式
            for (int i = 0; i < listIssue.size(); i++) {
                listIssueString.add(listIssue.get(i).get("issuekey").toString());
            }
            //开始拼装echarts需要的数据,listWork里存储了所有的数据，现在开始转换数据格式
            List<String> yData = null;
            List<String> totalData = new ArrayList<>();  //用来存储每条y轴的总计数据

            for (int n = 0; n < listIssueString.size(); n++) {
                yData = new ArrayList<String>(xData.size());
                for (int j = 0; j < xData.size(); j++) {
                    String hourstr = date_issue_hour_map.get(xData.get(j) + "_" + listIssueString.get(n));
                    hourstr = StringUtils.isEmpty(hourstr) ? "0" : hourstr;
                    yData.add(hourstr);
                }
                allData.put(listIssueString.get(n),yData);
                for(int i = 0;i < yData.size();i++){
                    if(totalData.size() < yData.size()){
                        totalData.add(yData.get(i));
                    }else{
                        Integer num =  Integer.parseInt(totalData.get(i)) + Integer.parseInt(yData.get(i));
                        totalData.set(i,num.toString());
                    }
                }
            }

            allData.put("legendData", listIssueString);
            allData.put("xData", xData);
            allData.put("totalData",totalData);

            JSONObject result = new JSONObject();
            result.put("totalHours",totalHours);
            result.put("startDate",startDate);
            result.put("endDate",endDate);
            result.put("resData", allData);
            result.put("issueTypehoursMap",issueTypehoursMap);
            System.out.println(result.toString());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
