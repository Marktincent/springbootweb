package com.uaes.esw.mapper.obpmdir;

import com.uaes.esw.entity.obpmdir.Participant;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ParticipantMapper {
    /**
     * 删除信息
     *
     * @param fuegoId
     * @return
     */
    @Delete({
            "delete from OBPMDIR.FUEGO_PARTICIPANT",
            "where FUEGO_ID = #{fuegoId,jdbcType=OTHER}"
    })
    int deleteByPrimaryKey(Object fuegoId);

    /**
     * 添加信息
     *
     * @param record
     * @return
     */
    @Insert({
            "insert into OBPMDIR.FUEGO_PARTICIPANT (FUEGO_ID, FUEGO_MODIFIER, ",
            "FUEGO_TIMEZONE, FUEGO_PERMISSIONS, ",
            "FUEGO_LASTNAME, FUEGO_IN, ",
            "FUEGO_MAIL, FUEGO_FIRSTNAME, ",
            "FUEGO_FAX, FUEGO_STATUS, ",
            "FUEGO_DISPLAYNAME, FUEGO_TELEPHONE, ",
            "FUEGO_CRTIME, FUEGO_OU, ",
            "FUEGO_MANAGER, FUEGO_RID, ",
            "FUEGO_CREATOR, FUEGO_LOCALE, ",
            "FUEGO_MOTIME, FUEGO_PHOTO, ",
            "FUEGO_PASSWORD)",
            "values (#{fuegoId,jdbcType=OTHER}, #{fuegoModifier,jdbcType=OTHER}, ",
            "#{fuegoTimezone,jdbcType=VARCHAR}, #{fuegoPermissions,jdbcType=DECIMAL}, ",
            "#{fuegoLastname,jdbcType=OTHER}, #{fuegoIn,jdbcType=DECIMAL}, ",
            "#{fuegoMail,jdbcType=OTHER}, #{fuegoFirstname,jdbcType=OTHER}, ",
            "#{fuegoFax,jdbcType=OTHER}, #{fuegoStatus,jdbcType=VARCHAR}, ",
            "#{fuegoDisplayname,jdbcType=OTHER}, #{fuegoTelephone,jdbcType=OTHER}, ",
            "#{fuegoCrtime,jdbcType=TIMESTAMP}, #{fuegoOu,jdbcType=OTHER}, ",
            "#{fuegoManager,jdbcType=OTHER}, #{fuegoRid,jdbcType=OTHER}, ",
            "#{fuegoCreator,jdbcType=OTHER}, #{fuegoLocale,jdbcType=VARCHAR}, ",
            "#{fuegoMotime,jdbcType=TIMESTAMP}, #{fuegoPhoto,jdbcType=BLOB}, ",
            "#{fuegoPassword,jdbcType=VARBINARY})"
    })
    int insert(Participant record);

    /**
     * 通过主键获得用户信息
     *
     * @param fuegoId
     * @return
     */
    @Select({
            "select",
            "FUEGO_ID, FUEGO_MODIFIER, FUEGO_TIMEZONE, FUEGO_PERMISSIONS, FUEGO_LASTNAME, ",
            "FUEGO_IN, FUEGO_MAIL, FUEGO_FIRSTNAME, FUEGO_FAX, FUEGO_STATUS, FUEGO_DISPLAYNAME, ",
            "FUEGO_TELEPHONE, FUEGO_CRTIME, FUEGO_OU, FUEGO_MANAGER, FUEGO_RID, FUEGO_CREATOR, ",
            "FUEGO_LOCALE, FUEGO_MOTIME, FUEGO_PHOTO, FUEGO_PASSWORD",
            "from OBPMDIR.FUEGO_PARTICIPANT",
            "where FUEGO_ID = #{fuegoId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "FUEGO_MODIFIER", property = "fuegoModifier", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TIMEZONE", property = "fuegoTimezone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_PERMISSIONS", property = "fuegoPermissions", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_LASTNAME", property = "fuegoLastname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_MAIL", property = "fuegoMail", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FIRSTNAME", property = "fuegoFirstname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FAX", property = "fuegoFax", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_STATUS", property = "fuegoStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_DISPLAYNAME", property = "fuegoDisplayname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TELEPHONE", property = "fuegoTelephone", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CRTIME", property = "fuegoCrtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_OU", property = "fuegoOu", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_MANAGER", property = "fuegoManager", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_RID", property = "fuegoRid", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CREATOR", property = "fuegoCreator", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_LOCALE", property = "fuegoLocale", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_MOTIME", property = "fuegoMotime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_PHOTO", property = "fuegoPhoto", jdbcType = JdbcType.BLOB),
            @Result(column = "FUEGO_PASSWORD", property = "fuegoPassword", jdbcType = JdbcType.VARBINARY)
    })
    Participant selectByPrimaryKey(String fuegoId);

    /**
     * 获得所有员工信息
     *
     * @return
     */
    @Select({
            "select",
            "FUEGO_ID, FUEGO_MODIFIER, FUEGO_TIMEZONE, FUEGO_PERMISSIONS, FUEGO_LASTNAME, ",
            "FUEGO_IN, FUEGO_MAIL, FUEGO_FIRSTNAME, FUEGO_FAX, FUEGO_STATUS, FUEGO_DISPLAYNAME, ",
            "FUEGO_TELEPHONE, FUEGO_CRTIME, FUEGO_OU, FUEGO_MANAGER, FUEGO_RID, FUEGO_CREATOR, ",
            "FUEGO_LOCALE, FUEGO_MOTIME, FUEGO_PHOTO, FUEGO_PASSWORD",
            "from OBPMDIR.FUEGO_PARTICIPANT"
    })
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.OTHER, id = true),
            @Result(column = "FUEGO_MODIFIER", property = "fuegoModifier", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TIMEZONE", property = "fuegoTimezone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_PERMISSIONS", property = "fuegoPermissions", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_LASTNAME", property = "fuegoLastname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_MAIL", property = "fuegoMail", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FIRSTNAME", property = "fuegoFirstname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FAX", property = "fuegoFax", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_STATUS", property = "fuegoStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_DISPLAYNAME", property = "fuegoDisplayname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TELEPHONE", property = "fuegoTelephone", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CRTIME", property = "fuegoCrtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_OU", property = "fuegoOu", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_MANAGER", property = "fuegoManager", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_RID", property = "fuegoRid", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CREATOR", property = "fuegoCreator", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_LOCALE", property = "fuegoLocale", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_MOTIME", property = "fuegoMotime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_PHOTO", property = "fuegoPhoto", jdbcType = JdbcType.BLOB),
            @Result(column = "FUEGO_PASSWORD", property = "fuegoPassword", jdbcType = JdbcType.VARBINARY)
    })
    List<Participant> selectAll();

    /**
     * 更新员工信息
     *
     * @param record
     * @return
     */
    @Update({
            "update OBPMDIR.FUEGO_PARTICIPANT",
            "set FUEGO_MODIFIER = #{fuegoModifier,jdbcType=OTHER},",
            "FUEGO_TIMEZONE = #{fuegoTimezone,jdbcType=VARCHAR},",
            "FUEGO_PERMISSIONS = #{fuegoPermissions,jdbcType=DECIMAL},",
            "FUEGO_LASTNAME = #{fuegoLastname,jdbcType=OTHER},",
            "FUEGO_IN = #{fuegoIn,jdbcType=DECIMAL},",
            "FUEGO_MAIL = #{fuegoMail,jdbcType=OTHER},",
            "FUEGO_FIRSTNAME = #{fuegoFirstname,jdbcType=OTHER},",
            "FUEGO_FAX = #{fuegoFax,jdbcType=OTHER},",
            "FUEGO_STATUS = #{fuegoStatus,jdbcType=VARCHAR},",
            "FUEGO_DISPLAYNAME = #{fuegoDisplayname,jdbcType=OTHER},",
            "FUEGO_TELEPHONE = #{fuegoTelephone,jdbcType=OTHER},",
            "FUEGO_CRTIME = #{fuegoCrtime,jdbcType=TIMESTAMP},",
            "FUEGO_OU = #{fuegoOu,jdbcType=OTHER},",
            "FUEGO_MANAGER = #{fuegoManager,jdbcType=OTHER},",
            "FUEGO_RID = #{fuegoRid,jdbcType=OTHER},",
            "FUEGO_CREATOR = #{fuegoCreator,jdbcType=OTHER},",
            "FUEGO_LOCALE = #{fuegoLocale,jdbcType=VARCHAR},",
            "FUEGO_MOTIME = #{fuegoMotime,jdbcType=TIMESTAMP},",
            "FUEGO_PHOTO = #{fuegoPhoto,jdbcType=BLOB},",
            "FUEGO_PASSWORD = #{fuegoPassword,jdbcType=VARBINARY}",
            "where FUEGO_ID = #{fuegoId,jdbcType=OTHER}"
    })
    int updateByPrimaryKey(Participant record);

    /**
     * 获得所有员工信息
     *
     * @return
     */
    @Select({
            "select",
            "FUEGO_ID, FUEGO_MODIFIER, FUEGO_TIMEZONE, FUEGO_PERMISSIONS, FUEGO_LASTNAME, ",
            "FUEGO_IN, FUEGO_MAIL, FUEGO_FIRSTNAME, FUEGO_FAX, FUEGO_STATUS, FUEGO_DISPLAYNAME, ",
            "FUEGO_TELEPHONE, FUEGO_CRTIME, FUEGO_OU, FUEGO_MANAGER, FUEGO_RID, FUEGO_CREATOR, ",
            "FUEGO_LOCALE, FUEGO_MOTIME, FUEGO_PHOTO, FUEGO_PASSWORD",
            "from OBPMDIR.FUEGO_PARTICIPANT",
            "where FUEGO_ID = #{fuegoId,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.OTHER, id = true),
            @Result(column = "FUEGO_MODIFIER", property = "fuegoModifier", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TIMEZONE", property = "fuegoTimezone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_PERMISSIONS", property = "fuegoPermissions", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_LASTNAME", property = "fuegoLastname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.BIGINT),
            @Result(column = "FUEGO_MAIL", property = "fuegoMail", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FIRSTNAME", property = "fuegoFirstname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FAX", property = "fuegoFax", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_STATUS", property = "fuegoStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_DISPLAYNAME", property = "fuegoDisplayname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TELEPHONE", property = "fuegoTelephone", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CRTIME", property = "fuegoCrtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_OU", property = "fuegoOu", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_MANAGER", property = "fuegoManager", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_RID", property = "fuegoRid", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CREATOR", property = "fuegoCreator", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_LOCALE", property = "fuegoLocale", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_MOTIME", property = "fuegoMotime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_PHOTO", property = "fuegoPhoto", jdbcType = JdbcType.BLOB),
            @Result(column = "FUEGO_PASSWORD", property = "fuegoPassword", jdbcType = JdbcType.VARBINARY)
    })
    List<Participant> selectIsExist(Participant record);

    @SelectProvider(type = FuegoProvider.class, method = "selectAllNotRole")
    @Results({
            @Result(column = "FUEGO_ID", property = "fuegoId", jdbcType = JdbcType.OTHER, id = true),
            @Result(column = "FUEGO_MODIFIER", property = "fuegoModifier", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TIMEZONE", property = "fuegoTimezone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_PERMISSIONS", property = "fuegoPermissions", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_LASTNAME", property = "fuegoLastname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_IN", property = "fuegoIn", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FUEGO_MAIL", property = "fuegoMail", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FIRSTNAME", property = "fuegoFirstname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_FAX", property = "fuegoFax", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_STATUS", property = "fuegoStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_DISPLAYNAME", property = "fuegoDisplayname", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_TELEPHONE", property = "fuegoTelephone", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CRTIME", property = "fuegoCrtime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_OU", property = "fuegoOu", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_MANAGER", property = "fuegoManager", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_RID", property = "fuegoRid", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_CREATOR", property = "fuegoCreator", jdbcType = JdbcType.OTHER),
            @Result(column = "FUEGO_LOCALE", property = "fuegoLocale", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FUEGO_MOTIME", property = "fuegoMotime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "FUEGO_PHOTO", property = "fuegoPhoto", jdbcType = JdbcType.BLOB),
            @Result(column = "FUEGO_PASSWORD", property = "fuegoPassword", jdbcType = JdbcType.VARBINARY)
    })
    List<Participant> selectAllNotRole(String roleName);
}