package com.uaes.esw.mapper.obpmdir;

import com.uaes.esw.entity.obpmdir.FuegoRole;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FuegoRoleMapper {
    @Delete({
            "delete from OBPMDIR.FUEGO_ROLE",
            "where FUEGO_ID = #{fuegoId,jdbcType=OTHER}"
    })
    int deleteByPrimaryKey(Object fuegoId);

    @Insert({
            "insert into OBPMDIR.FUEGO_ROLE (FUEGO_ID, FUEGO_MODIFIER, ",
            "FUEGO_CRTIME, FUEGO_IN, ",
            "FUEGO_RID, FUEGO_CREATOR, ",
            "FUEGO_DESC, FUEGO_ISPARAM, ",
            "FUEGO_MOTIME, FUEGO_TYPE, ",
            "FUEGO_NAME)",
            "values (#{fuegoId,jdbcType=OTHER}, #{fuegoModifier,jdbcType=OTHER}, ",
            "#{fuegoCrtime,jdbcType=TIMESTAMP}, #{fuegoIn,jdbcType=DECIMAL}, ",
            "#{fuegoRid,jdbcType=OTHER}, #{fuegoCreator,jdbcType=OTHER}, ",
            "#{fuegoDesc,jdbcType=OTHER}, #{fuegoIsparam,jdbcType=DECIMAL}, ",
            "#{fuegoMotime,jdbcType=TIMESTAMP}, #{fuegoType,jdbcType=OTHER}, ",
            "#{fuegoName,jdbcType=OTHER})"
    })
    int insert(FuegoRole record);

    @Select({
            "select",
            "FUEGO_ID, FUEGO_MODIFIER, FUEGO_CRTIME, FUEGO_IN, FUEGO_RID, FUEGO_CREATOR, ",
            "FUEGO_DESC, FUEGO_ISPARAM, FUEGO_MOTIME, FUEGO_TYPE, FUEGO_NAME",
            "from OBPMDIR.FUEGO_ROLE",
            "where FUEGO_ID = #{fuegoId,jdbcType=OTHER}"
    })
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.OTHER, id = true),
            @Result(column = "FUEGO_MODIFIER", property = "fuegoModifier", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CRTIME", property = "fuegoCrtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_RID", property = "fuegoRid", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CREATOR", property = "fuegoCreator", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_DESC", property = "fuegoDesc", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_ISPARAM", property = "fuegoIsparam", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_MOTIME", property = "fuegoMotime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_TYPE", property = "fuegoType", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_NAME", property = "fuegoName", jdbcType = JdbcType.OTHER)
    })
    FuegoRole selectByPrimaryKey(Object fuegoId);

    @Select({
            "select",
            "FUEGO_ID, FUEGO_MODIFIER, FUEGO_CRTIME, FUEGO_IN, FUEGO_RID, FUEGO_CREATOR, ",
            "FUEGO_DESC, FUEGO_ISPARAM, FUEGO_MOTIME, FUEGO_TYPE, FUEGO_NAME",
            "from OBPMDIR.FUEGO_ROLE Order by FUEGO_ID "
    })
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "FUEGO_MODIFIER", property = "fuegoModifier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_CRTIME", property = "fuegoCrtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_RID", property = "fuegoRid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_CREATOR", property = "fuegoCreator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_DESC", property = "fuegoDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_ISPARAM", property = "fuegoIsparam", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_MOTIME", property = "fuegoMotime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_TYPE", property = "fuegoType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_NAME", property = "fuegoName", jdbcType = JdbcType.VARCHAR)
    })
    List<FuegoRole> selectAll();

    @Update({
            "update OBPMDIR.FUEGO_ROLE",
            "set FUEGO_MODIFIER = #{fuegoModifier,jdbcType=OTHER},",
            "FUEGO_CRTIME = #{fuegoCrtime,jdbcType=TIMESTAMP},",
            "FUEGO_IN = #{fuegoIn,jdbcType=DECIMAL},",
            "FUEGO_RID = #{fuegoRid,jdbcType=OTHER},",
            "FUEGO_CREATOR = #{fuegoCreator,jdbcType=OTHER},",
            "FUEGO_DESC = #{fuegoDesc,jdbcType=OTHER},",
            "FUEGO_ISPARAM = #{fuegoIsparam,jdbcType=DECIMAL},",
            "FUEGO_MOTIME = #{fuegoMotime,jdbcType=TIMESTAMP},",
            "FUEGO_TYPE = #{fuegoType,jdbcType=OTHER},",
            "FUEGO_NAME = #{fuegoName,jdbcType=OTHER}",
            "where FUEGO_ID = #{fuegoId,jdbcType=OTHER}"
    })
    int updateByPrimaryKey(FuegoRole record);
}