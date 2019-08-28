package com.uaes.esw.mapper.ucs;

import com.uaes.esw.entity.ucs.GsdjFenTan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface GsdjFenTanMapper {
    @Insert({
            "insert into UCS.UCS_GSDJ_FENTAN (ID, PROJECTNO, ",
            "WORKPACKAGE, EMPLOYEEID, ",
            "DPET1, DPET2, DPET3, ",
            "CENTERCOST, BG_DATE, ",
            "RECODEDATE, HOURS, ",
            "BJID, SYSTEM, CUSTOM)",
            "values (UCS_GSDJ_FENTAN_SEQUENCES.Nextval, #{projectno,jdbcType=VARCHAR}, ",
            "#{workpackage,jdbcType=VARCHAR}, #{employeeid,jdbcType=VARCHAR}, ",
            "#{dpet1,jdbcType=VARCHAR}, #{dpet2,jdbcType=VARCHAR}, #{dpet3,jdbcType=VARCHAR}, ",
            "#{centercost,jdbcType=VARCHAR}, #{bgDate,jdbcType=VARCHAR}, ",
            "#{recodedate,jdbcType=VARCHAR}, #{hours,jdbcType=DECIMAL}, ",
            "#{bjid,jdbcType=VARCHAR}, #{sysplat,jdbcType=VARCHAR}, #{custom,jdbcType=VARCHAR})"
    })
    int insert(GsdjFenTan record);

    @Select({
            "select",
            "ID, PROJECTNO, WORKPACKAGE, EMPLOYEEID, DPET1, DPET2, DPET3, CENTERCOST, BG_DATE, ",
            "RECODEDATE, HOURS, BJID, SYSPLAT, CUSTOM",
            "from UCS.UCS_GSDJ_FENTAN"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNO", property = "projectno", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WORKPACKAGE", property = "workpackage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMPLOYEEID", property = "employeeid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET1", property = "dpet1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET2", property = "dpet2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET3", property = "dpet3", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CENTERCOST", property = "centercost", jdbcType = JdbcType.VARCHAR),
            @Result(column = "BG_DATE", property = "bgDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "RECODEDATE", property = "recodedate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "HOURS", property = "hours", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BJID", property = "bjid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SYSPLAT", property = "sysplat", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOM", property = "custom", jdbcType = JdbcType.VARCHAR)
    })
    List<GsdjFenTan> selectAll();

    @Select({
            "select",
            "ID, PROJECTNO, WORKPACKAGE, EMPLOYEEID, DPET1, DPET2, DPET3, CENTERCOST, BG_DATE, ",
            "RECODEDATE, HOURS, BJID, SYSPLAT, CUSTOM",
            "from UCS.UCS_GSDJ_FENTAN"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNO", property = "projectno", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WORKPACKAGE", property = "workpackage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMPLOYEEID", property = "employeeid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET1", property = "dpet1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET2", property = "dpet2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET3", property = "dpet3", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CENTERCOST", property = "centercost", jdbcType = JdbcType.VARCHAR),
            @Result(column = "BG_DATE", property = "bgDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "RECODEDATE", property = "recodedate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "HOURS", property = "hours", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BJID", property = "bjid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SYSPLAT", property = "sysplat", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOM", property = "custom", jdbcType = JdbcType.VARCHAR)
    })
    List<GsdjFenTan> selectDataByDate();

    @Update({
            "update UCS_GSDJ_FENTAN",
            "set HOURS = #{hours,jdbcType=VARCHAR},",
            "PROJECTNO = #{projectno,jdbcType=VARCHAR},BG_DATE = #{bgDate,jdbcType=VARCHAR},",
            "RECODEDATE = #{recodedate,jdbcType=VARCHAR},DPET1 = #{dpet1,jdbcType=VARCHAR},",
            "DPET2 = #{dpet2,jdbcType=VARCHAR},DPET3 = #{dpet3,jdbcType=VARCHAR}",
            "where BJID = #{bjid,jdbcType=INTEGER}"
    })
    int updateByBjid(GsdjFenTan gsdjFenTan);

    @Select({
            "select",
            "ID, PROJECTNO, WORKPACKAGE, EMPLOYEEID, DPET1, DPET2, DPET3, CENTERCOST, BG_DATE, ",
            "RECODEDATE, HOURS, BJID, SYSTEM SYSPLAT, CUSTOM",
            "from UCS.UCS_GSDJ_FENTAN where BJID = #{bjid,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PROJECTNO", property = "projectno", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WORKPACKAGE", property = "workpackage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMPLOYEEID", property = "employeeid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET1", property = "dpet1", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET2", property = "dpet2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DPET3", property = "dpet3", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CENTERCOST", property = "centercost", jdbcType = JdbcType.VARCHAR),
            @Result(column = "BG_DATE", property = "bgDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "RECODEDATE", property = "recodedate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "HOURS", property = "hours", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BJID", property = "bjid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SYSTEM", property = "sysplat", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUSTOM", property = "custom", jdbcType = JdbcType.VARCHAR)
    })
    List<GsdjFenTan> getGsdjFenTanByDate(@Param("bjid") String bjid);

    @Select({"select nvl(to_char(sum(hours)),'0')  hourssum , 'actual' hourtype from UCS_GSDJ_FENTAN where bjid like 'jira%' and employeeid = #{userId} and projectno <> 'XXX/YYYY'  and substr(bg_date,0,7) = #{month} ",
            "union", "select nvl(to_char(max(Workday*8)),'176') hourssum,'plan' hourtype from UCS_RLZYWH_TABLE where (year || '-' || lpad(month,2,'0')) = #{month} "
    })
    List<Map> getMonthHours(@Param("userId") String userId, @Param("month") String month);

    @Select({"select * from fuego_part_assigrole where FUEGO_ASSIGROLE = '组长' and FUEGO_PARAMVALUE in ( ",
            "select SUBSTR (fp.fuego_ou, instr(fp.fuego_ou,'/',-1,1)+1) groupname",
            "from fuego_participant fp where fp.fuego_ou like 'UAES/P/NE/NE1/ESW%' ",
            "and SUBSTR (fp.fuego_ou, instr(fp.fuego_ou,'/',-1,1)+1) not in ('ESW','ESW1','ESW2','ESW3','ESW4') and fp.fuego_id = #{userId})"
    })
    List<Map> getGroupLeader(@Param("userId") String userId);
}