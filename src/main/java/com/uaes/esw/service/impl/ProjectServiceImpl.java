package com.uaes.esw.service.impl;

import com.uaes.esw.entity.ebon.ProjectUpms;
import com.uaes.esw.entity.ebon.UpmsToUcs;
import com.uaes.esw.entity.mysql.ProjectCode;
import com.uaes.esw.entity.ucs.UcsProjectnoPms;
import com.uaes.esw.mapper.ebon.ProjectUpmsMapper;
import com.uaes.esw.mapper.ebon.UpmsToUcsMapper;
import com.uaes.esw.mapper.mysql.ProjectCodeMapper;
import com.uaes.esw.mapper.ucs.UcsProjectnoPmsMapper;
import com.uaes.esw.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectUpmsMapper projectUpmsMapper;

    @Autowired
    private ProjectCodeMapper projectCodeMapper;

    @Autowired
    private UcsProjectnoPmsMapper ucsProjectnoPmsMapper;

    @Autowired
    UpmsToUcsMapper upmsToUcsMapper;

    @Override
    public void synUpmsno(String createTime) {
        String synUpmsno = createTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = sdf1.format(new Date());
        if (StringUtils.isEmpty(createTime)) {
            String currentMouth = sdf.format(new Date());
            synUpmsno = currentMouth;
        }
        System.out.println("synUpmsno:[" + synUpmsno + "]--excuteTime:[" + currentTime + "]");
        List<ProjectUpms> projectUpms = projectUpmsMapper.selectAll(synUpmsno);
        StringBuffer tmp = new StringBuffer("");
        for (ProjectUpms upms : projectUpms) {
            ProjectCode tp = new ProjectCode();
            tp.setCustomercode(upms.getCustomercode());
            tp.setProjectcode(upms.getProjectcode());
            List<ProjectCode> l = projectCodeMapper.selectProjectCode(tp);
            tmp.append(upms.getCustomercode() + "/" + upms.getProjectcode());
            if (l != null && l.size() > 0) {
                tmp.append("----exist! \r\n");
            } else {
                if (StringUtils.isEmpty(upms.getCustomercode()) || StringUtils.isEmpty(upms.getProjectcode())) {
                    tmp.append("----null! \r\n");
                } else {
                    tmp.append("----add! \r\n");
                    projectCodeMapper.insert(tp);
                }
            }
        }
        System.out.println(tmp.toString());
    }

    @Override
    public void synUpmsnoToJira(String customercode, String projectcode) {
        ProjectCode tp = new ProjectCode();
        tp.setCustomercode(customercode);
        tp.setProjectcode(projectcode);
        List<ProjectCode> l = projectCodeMapper.selectProjectCode(tp);
        StringBuffer tmp = new StringBuffer("");
        if (l != null && l.size() > 0) {
            tmp.append("----exist! \r\n");
        } else {
            if (StringUtils.isEmpty(customercode) || StringUtils.isEmpty(projectcode)) {
                tmp.append("----null! \r\n");
            } else {
                tmp.append("----add! \r\n");
                projectCodeMapper.insert(tp);
            }
        }
        System.out.println(tmp.toString());
    }


    @Override
    public List<String> getMonth(String startYearMouth, String endYearMouth) throws Exception {
        Date d1 = new SimpleDateFormat("yyyy-MM").parse(startYearMouth);
        Date d2 = new SimpleDateFormat("yyyy-MM").parse(endYearMouth);
        Calendar dd = Calendar.getInstance();
        dd.setTime(d1);
        List<String> list = new ArrayList<>();
        while (dd.getTime().before(d2)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String str = sdf.format(dd.getTime());
            System.out.println(str);
            list.add(str);
            dd.add(Calendar.MONTH, 1);
        }
        return list;
    }

    @Override
    public void synUpmsnoToUcs(String createTime) {
        String synUpmsnoMonth = createTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = sdf1.format(new Date());
        if (StringUtils.isEmpty(createTime)) {
            String currentMouth = sdf.format(new Date());
            synUpmsnoMonth = currentMouth;
        }
        System.out.println("synUpmsnoToUcs:[" + synUpmsnoMonth + "]--excuteTime:[" + currentTime + "]");
        List<UpmsToUcs> upmsList = upmsToUcsMapper.selectAllByTime(synUpmsnoMonth);
        StringBuffer tmp = new StringBuffer("");
        for (UpmsToUcs upms : upmsList) {
            UcsProjectnoPms ucsProjectnoPms = new UcsProjectnoPms();
            ucsProjectnoPms.setProjectno(upms.getProno());
            List<UcsProjectnoPms> existList = ucsProjectnoPmsMapper.selectUcsProjectnoPms(ucsProjectnoPms);
            tmp.append(upms.getProno());
            if ((existList != null && existList.size() > 0) || StringUtils.isEmpty(upms.getProno())) {
                tmp.append("----exist! \r\n");
            } else {
                ucsProjectnoPms.setProjectname(upms.getProname());
                ucsProjectnoPms.setBnumber(upms.getBcode());
                ucsProjectnoPms.setOnumber(upms.getOcode());
                ucsProjectnoPms.setProjecttype(upms.getSystype());
                tmp.append("----add! \r\n");
                ucsProjectnoPmsMapper.insert(ucsProjectnoPms);
            }
        }
        System.out.println(tmp.toString());
    }

    @Override
    public List<ProjectUpms> getProjectUpms(String synUpmsno) {
        return projectUpmsMapper.selectAll(synUpmsno);
    }

    @Override
    public List<Map> findAllListMap(Map<String, String> params) {
        List<ProjectUpms> l = projectUpmsMapper.findObjectList(params, " and CREATEYEAR = '2018-08' ");
        return projectUpmsMapper.findAllListMap(params, "EBON.V_PROJECT_T");
    }

    @Override
    public List<ProjectUpms> selectAllBySqlWhere(String sqlWhere) {
        return projectUpmsMapper.selectAllBySqlWhere(sqlWhere);
    }

    @Override
    public List<Map> getUpmsProject(String searchValue, int rowCount, Boolean precise) {
        if (precise) {
            //精确查询
            return projectCodeMapper.getUpmsProjectPrecise(searchValue, rowCount);
        } else {
            return projectCodeMapper.getUpmsProject(searchValue, rowCount);
        }
    }
}
