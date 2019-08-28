package com.uaes.esw.mapper.ucs;

import com.uaes.esw.entity.ucs.JiraHours;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface JiraHoursMapper {
    @Insert({
            "insert into UCS.JIRA_HOURS (ID, TASKHOUR, ",
            "WPNAME, PROJECTNUMBER, ",
            "REGISTERTIME, PEOPLE, ",
            "ACTUALSTARTTIME, ACTUALENDTIME, ",
            "SYSTEMNAME, CUSTOM, ",
            "JIRACREATETIME)",
            "values (#{id,jdbcType=LONGVARBINARY}, #{taskhour,jdbcType=VARCHAR}, ",
            "#{wpname,jdbcType=VARCHAR}, #{projectnumber,jdbcType=VARCHAR}, ",
            "#{registertime,jdbcType=VARCHAR}, #{people,jdbcType=VARCHAR}, ",
            "#{actualstarttime,jdbcType=VARCHAR}, #{actualendtime,jdbcType=VARCHAR}, ",
            "#{systemname,jdbcType=VARCHAR}, #{custom,jdbcType=VARCHAR}, ",
            "#{jiracreatetime,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "select jira_hours_SEQ.Nextval from dual", keyProperty = "id", before = true, resultType = Long.class)
    int insert(JiraHours record);

    @Select({
            "select",
            "ID, TASKHOUR, WPNAME, PROJECTNUMBER, REGISTERTIME, PEOPLE, ACTUALSTARTTIME, ",
            "ACTUALENDTIME, SYSTEMNAME, CUSTOM, JIRACREATETIME",
            "from UCS.JIRA_HOURS"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.LONGVARBINARY),
            @Result(column = "TASKHOUR", property = "taskhour", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WPNAME", property = "wpname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNUMBER", property = "projectnumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REGISTERTIME", property = "registertime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PEOPLE", property = "people", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACTUALSTARTTIME", property = "actualstarttime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACTUALENDTIME", property = "actualendtime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SYSTEMNAME", property = "systemname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOM", property = "custom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "JIRACREATETIME", property = "jiracreatetime", jdbcType = JdbcType.VARCHAR)
    })
    List<JiraHours> selectAll();

    @Select({
            "select",
            "to_char(w.id) ID, w.taskhour TASKHOUR, w.wpname WPNAME, w.projectnumber PROJECTNUMBER, w.registertime REGISTERTIME, w.people PEOPLE, w.actualstarttime ACTUALSTARTTIME, ",
            "w.actualendtime ACTUALENDTIME, w.systemname SYSTEMNAME,w.custom CUSTOM ,get_min_dept(fp.fuego_ou) DEPT,get_min_sec(fp.fuego_ou) SEC,get_min_group(fp.fuego_ou) GRP",
            "from UCS.JIRA_HOURS w left join fuego_participant fp on w.people = fp.fuego_id where w.jiracreatetime like #{registertime} || '%' "
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.LONGVARBINARY),
            @Result(column = "TASKHOUR", property = "taskhour", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WPNAME", property = "wpname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNUMBER", property = "projectnumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REGISTERTIME", property = "registertime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PEOPLE", property = "people", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACTUALSTARTTIME", property = "actualstarttime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACTUALENDTIME", property = "actualendtime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SYSTEMNAME", property = "systemname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOM", property = "custom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEPT", property = "dept", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SEC", property = "sec", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GRP", property = "grp", jdbcType = JdbcType.VARCHAR)
    })
    List<JiraHours> getJiraHoursByDate(@Param("registertime") String registertime);


    @Select({
            "select",
            "ID, TASKHOUR, WPNAME, PROJECTNUMBER, REGISTERTIME, PEOPLE, ACTUALSTARTTIME, ",
            "ACTUALENDTIME, SYSTEMNAME, CUSTOM, JIRACREATETIME",
            "from UCS.JIRA_HOURS",
            "where JIRACREATETIME = #{createdTime,jdbcType=VARCHAR} and PEOPLE = #{people,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.LONGVARBINARY),
            @Result(column = "TASKHOUR", property = "taskhour", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WPNAME", property = "wpname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNUMBER", property = "projectnumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REGISTERTIME", property = "registertime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PEOPLE", property = "people", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACTUALSTARTTIME", property = "actualstarttime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACTUALENDTIME", property = "actualendtime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SYSTEMNAME", property = "systemname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOM", property = "custom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "JIRACREATETIME", property = "jiracreatetime", jdbcType = JdbcType.VARCHAR)
    })
    List<JiraHours> findWorkLogExist(@Param("createdTime") String createdTime, @Param("people") String people);

    @Update({
            "update UCS.JIRA_HOURS",
            "set PROJECTNUMBER = #{projectnumber,jdbcType=NUMERIC},",
            "TASKHOUR = #{taskhour,jdbcType=VARCHAR},",
            "SYSTEMNAME = #{systemname,jdbcType=VARCHAR},",
            "CUSTOM = #{custom,jdbcType=VARCHAR},ACTUALSTARTTIME=#{actualstarttime,jdbcType=VARCHAR},",
            "ACTUALENDTIME = #{actualendtime,jdbcType=VARCHAR},REGISTERTIME=#{registertime,jdbcType=VARCHAR}",
            "where JIRACREATETIME = #{jiracreatetime,jdbcType=VARCHAR} and PEOPLE = #{people,jdbcType=VARCHAR}"
    })
    int updateJiraHours(JiraHours jh);
}