package com.uaes.esw.mapper.obpmdir;

import com.uaes.esw.entity.obpmdir.FuegoPartAssigrole;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface FuegoPartAssigroleMapper {
    @Insert({
            "insert into OBPMDIR.FUEGO_PART_ASSIGROLE (FUEGO_CATEGORY, FUEGO_PERM, ",
            "FUEGO_ROLEIN, FUEGO_ASSIGROLE, ",
            "FUEGO_IN, FUEGO_ID, ",
            "FUEGO_PARAMVALUE)",
            "values (#{fuegoCategory,jdbcType=DECIMAL}, #{fuegoPerm,jdbcType=DECIMAL}, ",
            "#{fuegoRolein,jdbcType=DECIMAL}, #{fuegoAssigrole,jdbcType=VARCHAR}, ",
            "#{fuegoIn,jdbcType=DECIMAL}, #{fuegoId,jdbcType=VARCHAR}, ",
            "#{fuegoParamvalue,jdbcType=VARCHAR})"
    })
    int insert(FuegoPartAssigrole record);

    @Select({
            "select",
            "FUEGO_CATEGORY, FUEGO_PERM, FUEGO_ROLEIN, FUEGO_ASSIGROLE, FUEGO_IN, FUEGO_ID, ",
            "FUEGO_PARAMVALUE",
            "from OBPMDIR.FUEGO_PART_ASSIGROLE"
    })
    @Results({
            @Result(column = "FUEGO_CATEGORY", property = "fuegoCategory", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_PERM", property = "fuegoPerm", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_ROLEIN", property = "fuegoRolein", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_ASSIGROLE", property = "fuegoAssigrole", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_PARAMVALUE", property = "fuegoParamvalue", jdbcType = JdbcType.VARCHAR)
    })
    List<FuegoPartAssigrole> selectAll();

    @SelectProvider(type = FuegoProvider.class, method = "findIsHasRole")
    @Results({
            @Result(column = "FUEGO_CATEGORY", property = "fuegoCategory", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_PERM", property = "fuegoPerm", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_ROLEIN", property = "fuegoRolein", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_ASSIGROLE", property = "fuegoAssigrole", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_PARAMVALUE", property = "fuegoParamvalue", jdbcType = JdbcType.VARCHAR)
    })
    List<FuegoPartAssigrole> isHasRole(String userId, String userParam, String roleName);
}