package com.uaes.esw.mapper.obpmdir;

/**
 * @author zhenghuan.wang
 */
public class FuegoProvider {
    public String findIsHasRole(String userId, String userParam, String roleName) {
        StringBuffer sql = new StringBuffer(" select * from fuego_part_assigrole fpa where fpa.fuego_assigrole = '" + roleName + "' and fpa.fuego_id = '" + userId + "' and fpa.fuego_paramvalue = '" + userParam + "' ");
        return sql.toString();
    }

    public String selectByUserIdAndRoleName(String userId, String roleName) {
        StringBuffer sql = new StringBuffer(" select SUBSTR(fuego_parval,INSTR(fuego_parval,':',1,1)+1) as FUEGO_PARVAL ,FUEGO_ID from fuego_role_parval where fuego_id=LOWER('" + roleName + "') and SUBSTR(fuego_parval,1,INSTR(fuego_parval,':',1,1)-1)='" + userId + "'");
        return sql.toString();
    }

    public String selectAllNotRole(String roleName) {
        StringBuffer sql = new StringBuffer(" select * from FUEGO_PARTICIPANT where FUEGO_ID not in (select fuego_id from fuego_part_assigrole where fuego_assigrole = '" + roleName + "') ");
        return sql.toString();
    }

}
