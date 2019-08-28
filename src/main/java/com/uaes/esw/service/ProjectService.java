package com.uaes.esw.service;

import com.uaes.esw.entity.ebon.ProjectUpms;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    void synUpmsno(String createTime);

    public void synUpmsnoToJira(String customercode, String projectcode);

    List<String> getMonth(String startYearMouth, String endYearMouth) throws Exception;

    public void synUpmsnoToUcs(String createTime) throws Exception;

    public List<ProjectUpms> getProjectUpms(String synUpmsno);

    public List<Map> findAllListMap(Map<String, String> params);

    public List<ProjectUpms> selectAllBySqlWhere(String sqlWhere);

    public List<Map> getUpmsProject(String searchValue, int rowCount, Boolean precise);
}
