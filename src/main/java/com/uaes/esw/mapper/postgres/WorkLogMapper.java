package com.uaes.esw.mapper.postgres;

import com.uaes.esw.entity.postgres.WorkLog;

import java.util.List;
import java.util.Map;

import com.uaes.esw.entity.ucs.JiraHours;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WorkLogMapper {
    @Delete({
            "delete from public.worklog",
            "where id = #{id,jdbcType=NUMERIC}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into public.worklog (id, issueid, ",
            "author, grouplevel, ",
            "rolelevel, worklogbody, ",
            "created, updateauthor, ",
            "updated, startdate, ",
            "timeworked)",
            "values (#{id,jdbcType=NUMERIC}, #{issueid,jdbcType=NUMERIC}, ",
            "#{author,jdbcType=VARCHAR}, #{grouplevel,jdbcType=VARCHAR}, ",
            "#{rolelevel,jdbcType=NUMERIC}, #{worklogbody,jdbcType=VARCHAR}, ",
            "#{created,jdbcType=TIMESTAMP}, #{updateauthor,jdbcType=VARCHAR}, ",
            "#{updated,jdbcType=TIMESTAMP}, #{startdate,jdbcType=TIMESTAMP}, ",
            "#{timeworked,jdbcType=NUMERIC})"
    })
    int insert(WorkLog record);

    @Select({
            "select",
            "id, issueid, author, grouplevel, rolelevel, worklogbody, created, updateauthor, ",
            "updated, startdate, timeworked",
            "from public.worklog",
            "where id = #{id,jdbcType=NUMERIC}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.NUMERIC, id = true),
            @Result(column = "issueid", property = "issueid", jdbcType = JdbcType.NUMERIC),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "grouplevel", property = "grouplevel", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rolelevel", property = "rolelevel", jdbcType = JdbcType.NUMERIC),
            @Result(column = "worklogbody", property = "worklogbody", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created", property = "created", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updateauthor", property = "updateauthor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updated", property = "updated", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "startdate", property = "startdate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "timeworked", property = "timeworked", jdbcType = JdbcType.NUMERIC)
    })
    WorkLog selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, issueid, author, grouplevel, rolelevel, worklogbody, created, updateauthor, ",
            "updated, startdate, timeworked",
            "from public.worklog"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.NUMERIC, id = true),
            @Result(column = "issueid", property = "issueid", jdbcType = JdbcType.NUMERIC),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "grouplevel", property = "grouplevel", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rolelevel", property = "rolelevel", jdbcType = JdbcType.NUMERIC),
            @Result(column = "worklogbody", property = "worklogbody", jdbcType = JdbcType.VARCHAR),
            @Result(column = "created", property = "created", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "updateauthor", property = "updateauthor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updated", property = "updated", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "startdate", property = "startdate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "timeworked", property = "timeworked", jdbcType = JdbcType.NUMERIC)
    })
    List<WorkLog> selectAll();

    @Update({
            "update public.worklog",
            "set issueid = #{issueid,jdbcType=NUMERIC},",
            "author = #{author,jdbcType=VARCHAR},",
            "grouplevel = #{grouplevel,jdbcType=VARCHAR},",
            "rolelevel = #{rolelevel,jdbcType=NUMERIC},",
            "worklogbody = #{worklogbody,jdbcType=VARCHAR},",
            "created = #{created,jdbcType=TIMESTAMP},",
            "updateauthor = #{updateauthor,jdbcType=VARCHAR},",
            "updated = #{updated,jdbcType=TIMESTAMP},",
            "startdate = #{startdate,jdbcType=TIMESTAMP},",
            "timeworked = #{timeworked,jdbcType=NUMERIC}",
            "where id = #{id,jdbcType=NUMERIC}"
    })
    int updateByPrimaryKey(WorkLog record);

    /**
     * 多条件关键注释
     *
     * @param createdTime
     * @return
     */
    @SelectProvider(type = WorkLogProvider.class, method = "findWorkLogListPostgres")
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.DECIMAL, id = true),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "AUTHOR", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sys", property = "grouplevel", jdbcType = JdbcType.VARCHAR),
            @Result(column = "rolelevel", property = "rolelevel", jdbcType = JdbcType.DECIMAL),
            @Result(column = "CREATED", property = "created", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UPDATEAUTHOR", property = "updateauthor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UPDATED", property = "updated", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "STARTDATE", property = "startdate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "timeworked", property = "timeworked", jdbcType = JdbcType.DECIMAL),
            @Result(column = "plat", property = "worklogbody", jdbcType = JdbcType.VARCHAR)
    })
    List<WorkLog> findWorkLogByCreateTime(String createdTime);

    /**
     * 根据参数获取工时，当条件选择是查询月为单位的工时时，执行此查询
     * @param startdate
     * @param enddate
     * @param author
     * @return
     */
    @Select({"select p.pkey || '-' || b.issuenum issuekey,b.issuetype,a.* " +
            "from worklog a left join jiraissue b on a.issueid = b.id inner join project p " +
            "on  b.project = p.id  \n" +
            "where Date(a.startdate) >= Date(#{startdate}) and Date(a.startdate) <= Date(#{enddate}) and a.author = #{author}"
    })
    List<Map> getWorkLogByDataAndAuth(@Param("startdate") String startdate,@Param("enddate") String enddate,@Param("author") String author);

    /**
     * 按照条件查询出个人某个时间段登记的所有issue
     * @param startdate
     * @param enddate
     * @param author
     * @return
     */
    @Select({"select distinct p.pkey || '-' || b.issuenum issuekey " +
            "from worklog a left join jiraissue b on a.issueid = b.id inner join project p on  b.project = p.id  \n" +
            "where Date(a.startdate) >= Date(#{startdate}) and Date(a.startdate) <= Date(#{enddate}) and a.author = #{author}"
    })
    List<Map> getIssueByDataAndAuth(@Param("startdate") String startdate,@Param("enddate") String enddate,@Param("author") String author);

    /**
     * 查找所有的issuetype
     */
    @Select({"select i.id,i.pname from  issuetype i"})
    List<Map> getAllIssueType();
}