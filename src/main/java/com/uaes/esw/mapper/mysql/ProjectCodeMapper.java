package com.uaes.esw.mapper.mysql;

import com.uaes.esw.entity.mysql.ProjectCode;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ProjectCodeMapper {
    @Delete({
            "delete from projectcode",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into projectcode (id, projectcode, ",
            "customercode)",
            "values (#{id,jdbcType=INTEGER}, #{projectcode,jdbcType=VARCHAR}, ",
            "#{customercode,jdbcType=VARCHAR})"
    })
    int insert(ProjectCode record);

    @Select({
            "select",
            "id, projectcode, customercode",
            "from projectcode",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "projectcode", property = "projectcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "customercode", property = "customercode", jdbcType = JdbcType.VARCHAR)
    })
    ProjectCode selectByPrimaryKey(Integer id);

    @Select({
            "select",
            "id, projectcode, customercode",
            "from projectcode"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "projectcode", property = "projectcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "customercode", property = "customercode", jdbcType = JdbcType.VARCHAR)
    })
    List<ProjectCode> selectAll();

    @Update({
            "update projectcode",
            "set projectcode = #{projectcode,jdbcType=VARCHAR},",
            "customercode = #{customercode,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProjectCode record);

    @Select({
            "select",
            "id, projectcode, customercode",
            "from projectcode",
            "where projectcode = #{projectcode,jdbcType=VARCHAR} and customercode = #{customercode,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "projectcode", property = "projectcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "customercode", property = "customercode", jdbcType = JdbcType.VARCHAR)
    })
    List<ProjectCode> selectProjectCode(ProjectCode p);

    @Select({"select concat(a.customercode,'/',a.projectcode) upms_project from jira.projectcode  a where concat(a.customercode,'/',a.projectcode) like concat('%',#{searchValue},'%') order by concat(a.customercode,'/',a.projectcode) limit 0,#{rowCount} "
    })
    List<Map> getUpmsProject(@Param("searchValue") String searchValue, @Param("rowCount") int rowCount);

    @Select({"select concat(a.customercode,'/',a.projectcode) upms_project from jira.projectcode  a where concat(a.customercode,'/',a.projectcode) = #{searchValue} order by concat(a.customercode,'/',a.projectcode) limit 0,#{rowCount} "
    })
    List<Map> getUpmsProjectPrecise(@Param("searchValue") String searchValue, @Param("rowCount") int rowCount);

    @Select({"select concat(a.customercode,'/',a.projectcode) up from jira.projectcode a where id = #{upmsproid}"
    })
    List<Map> getUpmsProjectById(@Param("upmsproid") Long upmsproid);

}