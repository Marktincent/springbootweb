package com.uaes.esw.controller;

import com.uaes.esw.entity.obpmdir.FuegoRole;
import com.uaes.esw.service.FuegoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhenghuan.wang
 */
@Controller
public class UcsRoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FuegoService fuegoService;

    @RequestMapping("/getRolesMap")
    public @ResponseBody
    Map<String, Object> getRolesMap() throws Exception {
        System.out.println("----------getRolesMap-------");
        Map<String, Object> reMap = new HashMap<String, Object>();
        List<FuegoRole> frList = fuegoService.selectAll();
        reMap.put("frList", frList);
        return reMap;
    }

    @RequestMapping("/synUcsRoles")
    public void synUcsRoles(@RequestParam(defaultValue = "", name = "people") String people, @RequestParam(defaultValue = "", name = "selRoles") String selRoles) throws Exception {
        if (!StringUtils.isEmpty(people)) {
            String peopleArr[] = people.split(",");
            for (String tempPeople : peopleArr) {
                fuegoService.synUcsRoles(people, selRoles);
            }
        }
    }
}
