package com.uaes.esw.mapper.obpmdir;

import com.uaes.esw.entity.obpmdir.FuegoRoleParval;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * @author zhenghuan.wang
 */
public interface FuegoRoleParvalMapper {
    @Delete({
            "delete from OBPMDIR.FUEGO_ROLE_PARVAL",
            "where FUEGO_ID = #{fuegoId,jdbcType=OTHER}",
            "and FUEGO_PARVAL = #{fuegoParval,jdbcType=OTHER}"
    })
    int deleteByPrimaryKey(@Param("fuegoId") Object fuegoId, @Param("fuegoParval") Object fuegoParval);

    @Insert({
            "insert into OBPMDIR.FUEGO_ROLE_PARVAL (FUEGO_ID, FUEGO_PARVAL)",
            "values (#{fuegoId,jdbcType=OTHER}, #{fuegoParval,jdbcType=OTHER})"
    })
    int insert(FuegoRoleParval record);

    @Select({
            "select",
            "FUEGO_ID, FUEGO_PARVAL",
            "from OBPMDIR.FUEGO_ROLE_PARVAL"
    })
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.OTHER, id = true),
            @Result(column = "FUEGO_PARVAL", property = "fuegoParval", jdbcType = JdbcType.OTHER, id = true)
    })
    List<FuegoRoleParval> selectAll();

    @SelectProvider(type = FuegoProvider.class, method = "selectByUserIdAndRoleName")
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.OTHER, id = true),
            @Result(column = "FUEGO_PARVAL", property = "fuegoParval", jdbcType = JdbcType.OTHER, id = true)
    })
    List<FuegoRoleParval> selectByUserIdAndRoleName(String userId, String roleName);

    @Insert({
            "insert into FUEGO_ROLE_PARVAL (fuego_parval,fuego_id)",
            "values ( CONCAT(#{fuegoId,jdbcType=OTHER},concat(':', FUEGO_ROLE_FUEGO_IN.nextval )),lower(#{fuegoParval,jdbcType=OTHER}) )"
    })
    int insertFuegoRoleParval(FuegoRoleParval record);
}