package com.uaes.esw.mapper.postgres;

import com.uaes.esw.entity.postgres.FieldValue;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface FieldValueMapper {
    @Delete({
            "delete from public.customfieldvalue",
            "where id = #{id,jdbcType=NUMERIC}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
            "insert into public.customfieldvalue (id, issue, ",
            "customfield, parentkey, ",
            "stringvalue, numbervalue, ",
            "textvalue, datevalue, ",
            "valuetype, updated)",
            "values (#{id,jdbcType=NUMERIC}, #{issue,jdbcType=NUMERIC}, ",
            "#{customfield,jdbcType=NUMERIC}, #{parentkey,jdbcType=VARCHAR}, ",
            "#{stringvalue,jdbcType=VARCHAR}, #{numbervalue,jdbcType=DOUBLE}, ",
            "#{textvalue,jdbcType=VARCHAR}, #{datevalue,jdbcType=TIMESTAMP}, ",
            "#{valuetype,jdbcType=VARCHAR}, #{updated,jdbcType=NUMERIC})"
    })
    int insert(FieldValue record);

    @Select({
            "select",
            "id, issue, customfield, parentkey, stringvalue, numbervalue, textvalue, datevalue, ",
            "valuetype, updated",
            "from public.customfieldvalue",
            "where id = #{id,jdbcType=NUMERIC}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.NUMERIC, id = true),
            @Result(column = "issue", property = "issue", jdbcType = JdbcType.NUMERIC),
            @Result(column = "customfield", property = "customfield", jdbcType = JdbcType.NUMERIC),
            @Result(column = "parentkey", property = "parentkey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stringvalue", property = "stringvalue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "numbervalue", property = "numbervalue", jdbcType = JdbcType.DOUBLE),
            @Result(column = "textvalue", property = "textvalue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "datevalue", property = "datevalue", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "valuetype", property = "valuetype", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updated", property = "updated", jdbcType = JdbcType.NUMERIC)
    })
    FieldValue selectByPrimaryKey(Long id);

    @Select({
            "select",
            "id, issue, customfield, parentkey, stringvalue, numbervalue, textvalue, datevalue, ",
            "valuetype, updated",
            "from public.customfieldvalue"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.NUMERIC, id = true),
            @Result(column = "issue", property = "issue", jdbcType = JdbcType.NUMERIC),
            @Result(column = "customfield", property = "customfield", jdbcType = JdbcType.NUMERIC),
            @Result(column = "parentkey", property = "parentkey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stringvalue", property = "stringvalue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "numbervalue", property = "numbervalue", jdbcType = JdbcType.DOUBLE),
            @Result(column = "textvalue", property = "textvalue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "datevalue", property = "datevalue", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "valuetype", property = "valuetype", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updated", property = "updated", jdbcType = JdbcType.NUMERIC)
    })
    List<FieldValue> selectAll();

    @Update({
            "update public.customfieldvalue",
            "set issue = #{issue,jdbcType=NUMERIC},",
            "customfield = #{customfield,jdbcType=NUMERIC},",
            "parentkey = #{parentkey,jdbcType=VARCHAR},",
            "stringvalue = #{stringvalue,jdbcType=VARCHAR},",
            "numbervalue = #{numbervalue,jdbcType=DOUBLE},",
            "textvalue = #{textvalue,jdbcType=VARCHAR},",
            "datevalue = #{datevalue,jdbcType=TIMESTAMP},",
            "valuetype = #{valuetype,jdbcType=VARCHAR},",
            "updated = #{updated,jdbcType=NUMERIC}",
            "where id = #{id,jdbcType=NUMERIC}"
    })
    int updateByPrimaryKey(FieldValue record);

    @Select({
            "select",
            "id, issue, customfield, parentkey, stringvalue, numbervalue, textvalue, datevalue, ",
            "valuetype, updated",
            "from public.customfieldvalue",
            "where issue = #{issue,jdbcType=NUMERIC} and customfield = #{customfield,jdbcType=NUMERIC} limit 1"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.NUMERIC, id = true),
            @Result(column = "issue", property = "issue", jdbcType = JdbcType.NUMERIC),
            @Result(column = "customfield", property = "customfield", jdbcType = JdbcType.NUMERIC),
            @Result(column = "parentkey", property = "parentkey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stringvalue", property = "stringvalue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "numbervalue", property = "numbervalue", jdbcType = JdbcType.DOUBLE),
            @Result(column = "textvalue", property = "textvalue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "datevalue", property = "datevalue", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "valuetype", property = "valuetype", jdbcType = JdbcType.VARCHAR),
            @Result(column = "updated", property = "updated", jdbcType = JdbcType.NUMERIC)
    })
    FieldValue selectByIssueCustom(@Param("issue") Long issue, @Param("customfield") Long customfield);

    /**
     * @return getNotMigrateIssueId List
     */
    @Select({"select distinct(issue) issue,stringvalue sv from CUSTOMFIELDvalue t",
            "where customfield = #{old_custom_field_id} and issue not in (select issue from CUSTOMFIELDvalue where customfield = #{new_custom_field_id})",
            "limit #{migrate_num}"
    })
    List<Map> getNotMigrateIssueId(@Param("old_custom_field_id") Long old_custom_field_id, @Param("new_custom_field_id") Long new_custom_field_id, @Param("migrate_num") Long migrate_num);

    @Insert({"insert into CUSTOMFIELDvalue(id,issue,customfield,stringvalue,updated)",
            "values((select max(id)+1 aaa from CUSTOMFIELDvalue),#{issue},#{customid},#{old_up},1559040049691)"
    })
    int insertFieldValue(@Param("issue") Long issue, @Param("customid") Long customid, @Param("old_up") String old_up);

    @Select({"select id from CUSTOMFIELD where customfieldtypekey = #{plugin_key}"
    })
    List<Map> getCustomfieldId(@Param("plugin_key") String plugin_key);

    @Select({"select p.pkey || '-' || j.issuenum issuekey from jiraissue j left join project p on j.project = p.id where j.id = #{issue_id}"
    })
    List<Map> getIssueKeyByIssueId(@Param("issue_id") Long issue_id);
}