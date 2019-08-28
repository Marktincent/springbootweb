package com.uaes.esw.mapper.ebon;

import com.uaes.esw.entity.ebon.UpmsToUcs;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface UpmsToUcsMapper {
    @Insert({
        "insert into EBON.V_PROJECT_TO_UCS (PRONO, PRONAME, ",
        "SYSTYPE, BCODE, ",
        "OCODE, CREATETIME)",
        "values (#{prono,jdbcType=VARCHAR}, #{proname,jdbcType=VARCHAR}, ",
        "#{systype,jdbcType=VARCHAR}, #{bcode,jdbcType=VARCHAR}, ",
        "#{ocode,jdbcType=VARCHAR}, #{createtime,jdbcType=VARCHAR})"
    })
    int insert(UpmsToUcs record);

    @Select({
            "select",
            "PRONO, PRONAME, SYSTYPE, BCODE, OCODE, CREATETIME",
            "from EBON.V_PROJECT_TO_UCS",
            " where CREATETIME = #{year,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="PRONO", property="prono", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRONAME", property="proname", jdbcType=JdbcType.VARCHAR),
            @Result(column="SYSTYPE", property="systype", jdbcType=JdbcType.VARCHAR),
            @Result(column="BCODE", property="bcode", jdbcType=JdbcType.VARCHAR),
            @Result(column="OCODE", property="ocode", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATETIME", property="createtime", jdbcType=JdbcType.VARCHAR)
    })
    List<UpmsToUcs> selectAllByTime(@Param("year") String year);
}