package com.uaes.esw.service;

import com.uaes.esw.entity.obpmdir.FuegoPartAssigrole;
import com.uaes.esw.entity.obpmdir.FuegoRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface WorkLogService {

    public List<Map> getWorkLogByDataAndAuth(String startDate,String endDate,String auth);

    public List<Map> getIssueByDataAndAuth(String startDate,String endDate,String auth);

    List<Map> getAllIssueType();
}
