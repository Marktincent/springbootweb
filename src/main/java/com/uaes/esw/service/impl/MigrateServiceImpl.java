package com.uaes.esw.service.impl;

import com.uaes.esw.mapper.mysql.ProjectCodeMapper;
import com.uaes.esw.mapper.postgres.FieldValueMapper;
import com.uaes.esw.service.MigrateService;
import com.uaes.esw.util.restful.HttpClientUtil;
import com.uaes.esw.util.ymlconfig.YmlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhenghuan.wang
 */
@Service(value = "migrateService")
public class MigrateServiceImpl implements MigrateService {
    @Autowired
    private FieldValueMapper fieldValueMapper;
    @Autowired
    private ProjectCodeMapper projectCodeMapper;

    @Autowired
    private YmlConfig jiraConfig;

    @Override
    public List<Map> getNotMigrateIssueId(Long old_custom_field_id, Long new_custom_field_id, Long migrate_num) {
        return fieldValueMapper.getNotMigrateIssueId(old_custom_field_id, new_custom_field_id, migrate_num);
    }

    @Override
    public void migrateData() {
        String new_custom_field_id = fieldValueMapper.getCustomfieldId("com.atlassian.tutorial.myPlugin:get-upms-project").get(0).get("id").toString();
        String old_custom_field_id = fieldValueMapper.getCustomfieldId("org.deblauwe.jira.plugin.database-values-plugin:databasevaluesselectionfield").get(0).get("id").toString();
        List<Map> ml = new ArrayList<Map>();
        ml = getNotMigrateIssueId(Long.parseLong(old_custom_field_id), Long.parseLong(new_custom_field_id), 10000L);
        int num = 0;
        for (Map m : ml) {
            System.out.println(m);
            List<Map> upList = projectCodeMapper.getUpmsProjectById(Long.parseLong(m.get("sv").toString()));
            if (upList.size() == 1) {
                System.out.println("-------------------" + upList.get(0).get("up").toString());
                String old_up = upList.get(0).get("up").toString();
                List<Map> issueKeyList = fieldValueMapper.getIssueKeyByIssueId(Long.parseLong(m.get("issue").toString()));
                String issueKey = issueKeyList.get(0).get("issuekey").toString();
//1                    fieldValueMapper.insertFieldValue(Long.parseLong(m.get("issue").toString()), Long.parseLong(new_custom_field_id), old_up);
//2                    FieldValue record = new FieldValue();
//                    record.setIssue(Long.parseLong(m.get("issue").toString()));
//                    record.setCustomfield(Long.parseLong(new_custom_field_id));
//                    record.setStringvalue(old_up);
//                    Date d = new Date();
//                    record.setUpdated(d.getTime());
//                    fieldValueMapper.insert(record);
                insertFieldValue(issueKey, new_custom_field_id, old_up);
                num++;
                System.out.println("-------migrateData-num=" + num);
            }
        }
        System.out.println("-------migrateData-finish-----------size:" + ml.size());
    }

    public void insertFieldValue(String issueKey, String new_custom_field_id, String value) {
        try {
            String createOrUpdateIssueUrl = jiraConfig.getIssueUrl();
            URI uri = new URI(createOrUpdateIssueUrl + issueKey);
            String params = "{\"update\":{\"customfield_" + new_custom_field_id + "\":[{\"set\":\"" + value + "\"}]}}";
            System.out.println("Executing request http://174.34.53.220:8080/browse/" + issueKey);
            String res = HttpClientUtil.putRequest(uri, jiraConfig.getUsername(), jiraConfig.getPassword(), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
