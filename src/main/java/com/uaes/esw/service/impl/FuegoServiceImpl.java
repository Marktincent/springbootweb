package com.uaes.esw.service.impl;

import com.uaes.esw.entity.obpmdir.FuegoPartAssigrole;
import com.uaes.esw.entity.obpmdir.FuegoRole;
import com.uaes.esw.entity.obpmdir.FuegoRoleParval;
import com.uaes.esw.entity.obpmdir.Participant;
import com.uaes.esw.mapper.obpmdir.FuegoPartAssigroleMapper;
import com.uaes.esw.mapper.obpmdir.FuegoRoleMapper;
import com.uaes.esw.mapper.obpmdir.FuegoRoleParvalMapper;
import com.uaes.esw.mapper.obpmdir.ParticipantMapper;
import com.uaes.esw.service.FuegoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service(value = "fuegoService")
public class FuegoServiceImpl implements FuegoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FuegoRoleMapper fuegoRoleMapper;
    @Autowired
    private FuegoPartAssigroleMapper fuegoPartAssigroleMapper;
    @Autowired
    private FuegoRoleParvalMapper fuegoRoleParvalMapper;
    @Autowired
    private ParticipantMapper participantMapper;

    @Override
    public List<FuegoRole> selectAll() {
        return fuegoRoleMapper.selectAll();
    }

    @Override
    public List<FuegoPartAssigrole> isHasRole(String userId, String userParam, String roleName) {
        return fuegoPartAssigroleMapper.isHasRole(userId, userParam, roleName);
    }

    @Override
    public void updateUserRole(String userId, String roleName) {
        List<FuegoPartAssigrole> fpa = isHasRole(userId, userId, roleName);
        logger.info("userId:[" + userId + "] roleName:[" + roleName + "] count=" + fpa.size());
        Participant p = participantMapper.selectByPrimaryKey(userId);
        if (fpa.size() == 0 && p != null) {
            List<FuegoRoleParval> frpList = fuegoRoleParvalMapper.selectByUserIdAndRoleName(userId, roleName);
            logger.info("FuegoRoleParval List Size:[" + frpList.size() + "] ");
            if (frpList.size() == 0) {
                FuegoRoleParval record = new FuegoRoleParval();
                record.setFuegoId(userId);
                record.setFuegoParval(roleName);
                fuegoRoleParvalMapper.insertFuegoRoleParval(record);
                logger.info("new add FuegoRoleParval FuegoParval:[" + record.getFuegoParval() + "] ");
            }
            if (frpList.size() == 0) {
                frpList = fuegoRoleParvalMapper.selectByUserIdAndRoleName(userId, roleName);
            }
            if (frpList.size() > 0) {
                logger.info("FuegoRoleParval Fuego_rolein:[" + frpList.get(0).getFuegoParval() + "] Userid:[" + frpList.get(0).getFuegoId() + "]");
                FuegoPartAssigrole fpaNew = new FuegoPartAssigrole();
                fpaNew.setFuegoCategory(5L);
                fpaNew.setFuegoPerm(95L);
                fpaNew.setFuegoRolein(Long.parseLong(frpList.get(0).getFuegoParval().toString()));
                fpaNew.setFuegoAssigrole(roleName);
                fpaNew.setFuegoId(userId);
                fpaNew.setFuegoIn(p.getFuegoIn());
                if (!roleName.equals("DC")) {
                    fpaNew.setFuegoParamvalue(userId);
                }
                fuegoPartAssigroleMapper.insert(fpaNew);
            }
        }
    }

    @Override
    public void updateAllUserRole(String roleName) {
        List<Participant> listNotRoleName = participantMapper.selectAllNotRole(roleName);
        for (Participant p : listNotRoleName) {

            updateUserRole(p.getFuegoId().toString(), roleName);
        }
    }

    @Override
    public void synUcsRoles(String people, String selRoles) {
        logger.info("synUcsRoles--------people:[" + people + "]------selRoles[" + selRoles + "]");
        if (StringUtils.isEmpty(selRoles)) {
            selRoles = "DC_动态,DC,UAES员工,PA_动态,系统工程师,软件工程师,项目安全经理";
        }
        if (StringUtils.isEmpty(people)) {
            people = "ALL";
        }
        String rolesArr[] = selRoles.split(",");
        for (String role : rolesArr) {
            logger.info("synUcsRoles---people:[" + people + "]------role[" + role + "]");
            if (people.equals("ALL")) {
                updateAllUserRole(role);
            } else {
                updateUserRole(people, role);
            }
        }
    }
}
