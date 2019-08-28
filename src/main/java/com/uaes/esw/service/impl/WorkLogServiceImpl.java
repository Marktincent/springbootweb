package com.uaes.esw.service.impl;

import com.uaes.esw.mapper.postgres.WorkLogMapper;
import com.uaes.esw.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkLogServiceImpl implements WorkLogService {

    @Autowired
    private WorkLogMapper workLogMapper;

    @Override
    public List<Map> getWorkLogByDataAndAuth(String startDate, String endDate, String auth) {
        return workLogMapper.getWorkLogByDataAndAuth(startDate,endDate,auth);
    }

    @Override
    public List<Map> getIssueByDataAndAuth(String startDate, String endDate, String auth) {
        return workLogMapper.getIssueByDataAndAuth(startDate,endDate,auth);
    }

    @Override
    public List<Map> getAllIssueType() {
        return workLogMapper.getAllIssueType();
    }
}
