package com.uaes.esw.service;

import java.util.List;
import java.util.Map;

/**
 * @author zhenghuan.wang
 */
public interface MigrateService {
    /**
     * get NotMigrateIssueId
     *
     * @return
     */
    List<Map> getNotMigrateIssueId(Long old_custom_field_id, Long new_custom_field_id, Long migrate_num);

    /**
     * migrate data
     */
    void migrateData();
}
