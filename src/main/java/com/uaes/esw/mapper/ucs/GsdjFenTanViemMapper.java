package com.uaes.esw.mapper.ucs;

import com.uaes.esw.entity.ucs.GsdjFenTanViem;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface GsdjFenTanViemMapper {

    @Select({
            "select",
            "BJID, TASKHOUR, WPNAME, PROJECTNUMBER, REGISTERTIME, PEOPLE, ACTUALSTARTTIME, ",
            "ACTUALENDTIME, SYSTEMNAME, CUSTOM,DEPT,SEC,GRP",
            "from UCS.GSDJ_FENTAN_OU_VW t where BJID like 'JIRA%' "
    })
    @Results({
            @Result(column = "BJID", property = "bjid", jdbcType = JdbcType.VARCHAR),
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
    List<GsdjFenTanViem> getTwoMonthDataList();

    /**
     * 删除近两个月的分摊时间表里面的数据
     */
    @Delete({
            "delete from ucs_gsdj_fentan s where to_char(to_date(s.bg_date, 'yyyy-mm-dd'),'yyyymm') in (select to_char(add_months(trunc(sysdate), -1), 'yyyymm') up_month from dual) or to_char(to_date(s.bg_date, 'yyyy-mm-dd'),'yyyymm') in (select to_char(sysdate, 'yyyymm') cu_month from dual)"
    })
    int deleteTwoMonthData();
}