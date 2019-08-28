package com.uaes.esw.service;

import com.uaes.esw.entity.obpmdir.FuegoPartAssigrole;
import com.uaes.esw.entity.obpmdir.FuegoRole;

import java.util.List;

public interface FuegoService {
    List<FuegoRole> selectAll();

    public List<FuegoPartAssigrole> isHasRole(String userId, String userParam, String roleName);

    public void updateUserRole(String userId, String roleName);

    public void updateAllUserRole(String roleName);

    public void synUcsRoles(String people, String selRoles);
}
