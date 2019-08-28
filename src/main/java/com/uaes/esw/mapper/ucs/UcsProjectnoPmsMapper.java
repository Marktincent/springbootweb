package com.uaes.esw.mapper.ucs;

import com.uaes.esw.entity.ucs.UcsProjectnoPms;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface UcsProjectnoPmsMapper {
    @Insert({
            "insert into UCS.UCS_PROJECTNO_PMS (PROJECTNO, PROJECTNAME, ",
            "BNUMBER, ONUMBER, ",
            "PROJMANAGER, SOD, ",
            "SOP, SALES, UNIQUEID, ",
            "PROJECTTYPE, MATCH25, ",
            "DATAFIX)",
            "values (#{projectno,jdbcType=VARCHAR}, #{projectname,jdbcType=VARCHAR}, ",
            "#{bnumber,jdbcType=VARCHAR}, #{onumber,jdbcType=VARCHAR}, ",
            "#{projmanager,jdbcType=VARCHAR}, #{sod,jdbcType=VARCHAR}, ",
            "#{sop,jdbcType=VARCHAR}, #{sales,jdbcType=VARCHAR}, #{uniqueid,jdbcType=VARCHAR}, ",
            "#{projecttype,jdbcType=VARCHAR}, #{match25,jdbcType=VARCHAR}, ",
            "#{datafix,jdbcType=VARCHAR})"
    })
    int insert(UcsProjectnoPms record);

    @Select({
            "select",
            "PROJECTNO, PROJECTNAME, BNUMBER, ONUMBER, PROJMANAGER, SOD, SOP, SALES, UNIQUEID, ",
            "PROJECTTYPE, MATCH25, DATAFIX",
            "from UCS.UCS_PROJECTNO_PMS"
    })
    @Results({
            @Result(column = "PROJECTNO", property = "projectno", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNAME", property = "projectname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "BNUMBER", property = "bnumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ONUMBER", property = "onumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJMANAGER", property = "projmanager", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SOD", property = "sod", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SOP", property = "sop", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SALES", property = "sales", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UNIQUEID", property = "uniqueid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTTYPE", property = "projecttype", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MATCH25", property = "match25", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DATAFIX", property = "datafix", jdbcType = JdbcType.VARCHAR)
    })
    List<UcsProjectnoPms> selectAll();

    @Select({
            "select",
            "PROJECTNO, PROJECTNAME, BNUMBER, ONUMBER, PROJMANAGER, SOD, SOP, SALES, UNIQUEID, ",
            "PROJECTTYPE, MATCH25, DATAFIX",
            "from UCS.UCS_PROJECTNO_PMS",
            "where PROJECTNO = #{projectno,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "PROJECTNO", property = "projectno", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNAME", property = "projectname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "BNUMBER", property = "bnumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ONUMBER", property = "onumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJMANAGER", property = "projmanager", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SOD", property = "sod", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SOP", property = "sop", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SALES", property = "sales", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UNIQUEID", property = "uniqueid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTTYPE", property = "projecttype", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MATCH25", property = "match25", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DATAFIX", property = "datafix", jdbcType = JdbcType.VARCHAR)
    })
    List<UcsProjectnoPms> selectUcsProjectnoPms(UcsProjectnoPms ucsProjectnoPms);
}