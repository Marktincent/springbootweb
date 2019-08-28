package com.uaes.esw.mapper.postgres;

public class WorkLogProvider {
    /**
     * 获得某一天登记的workLog
     *
     * @param createdTime formate(yyyy-MM--dd)
     * @return
     */
    public String findWorkLogListPostgres(String createdTime) {
        StringBuffer sql = new StringBuffer(" select distinct a.issueid as project,t.sys as sys,t.plat as plat, a.* from worklog a inner join JIRAISSUE b on a.issueid = b.id inner join project_key c on b.project = c.project_id left join (SELECT issue ,MAX(CASE fieldid WHEN (select max(id) from CUSTOMFIELD cf where cf.cfname = 'Platform/s') THEN label ELSE '' END ) sys,MAX(CASE fieldid WHEN (select max(id) from CUSTOMFIELD cf where cf.cfname = 'Project/s') THEN label ELSE '' END ) plat FROM LABEL GROUP BY issue) t on a.issueid = t.issue where Date(a.created) = '" + createdTime + "' ");
        return sql.toString();
    }
}
