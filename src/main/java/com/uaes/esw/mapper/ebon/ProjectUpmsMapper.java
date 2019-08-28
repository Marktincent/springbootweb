package com.uaes.esw.mapper.ebon;

import com.uaes.esw.entity.ebon.ProjectUpms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ProjectUpmsMapper {
    @Insert({
            "insert into EBON.V_PROJECT_T (PROJECTCODE, CUSTOMERCODE, ",
            "CREATEYEAR)",
            "values (#{projectcode,jdbcType=VARCHAR}, #{customercode,jdbcType=VARCHAR}, ",
            "#{createyear,jdbcType=VARCHAR})"
    })
    int insert(ProjectUpms record);

    @Select({
            "select",
            "PROJECTCODE, CUSTOMERCODE, CREATEYEAR",
            "from EBON.V_PROJECT_T",
            " where CREATEYEAR = #{CREATEYEAR,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "PROJECTCODE", property = "projectcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOMERCODE", property = "customercode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CREATEYEAR", property = "createyear", jdbcType = JdbcType.VARCHAR)
    })
    List<ProjectUpms> selectAll(@Param("CREATEYEAR") String CREATEYEAR);

    @Results({
            @Result(property = "PROJECTCODE", column = "projectcode")
    })
    @Select({"<script> select * from ${tableName} where 1=1 and ( <foreach item='item' index='key' collection='params' open='' separator='OR' close=''>${key} like #{item}</foreach> ) </script>"})
    public List<Map> findAllListMap(@Param("params") Map<String, String> params, @Param("tableName") String tableName);


    /**
     * 获得
     *
     * @param params
     * @return
     */
    @Select({
            "select * from EBON.V_PROJECT_T",
            "where 1=1",
            "${sqlWhere}"})
    public List<ProjectUpms> findObjectList(@Param("params") Map<String, String> params, @Param("sqlWhere") String sqlWhere);

    @Select({
            "select * from EBON.V_PROJECT_T",
            "where 1=1",
            "${sqlWhere}"})
    List<ProjectUpms> selectAllBySqlWhere(@Param("sqlWhere") String sqlWhere);
}