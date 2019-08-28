package com.uaes.esw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uaes.esw.entity.ebon.ProjectUpms;
import com.uaes.esw.service.ProjectService;
import com.uaes.esw.util.PageHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhenghuan.wang
 */
@Controller
public class ProjectPageController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/allUpms")
    public String getProjectUpms(Model model,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageIndex, pageSize);
        List<ProjectUpms> upmsList = projectService.getProjectUpms("2018-06");
        StringBuilder sqlWhere = new StringBuilder("");
        Map<String, String> params = new HashMap<String, String>(16);
        PageHandle.setHeaderPageInfo(model, request, sqlWhere);
        List<Map> lm = projectService.findAllListMap(params);
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<ProjectUpms>(upmsList, pageSize);
        PageHandle.setFooterPageInfo(model, request, pageInfo);
        return "project-upms";
    }

    @RequestMapping("/allUpmsProject")
    public String getProjectUpmsAll(Model model,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageIndex, pageSize);
        StringBuilder sqlWhere = new StringBuilder("");
        PageHandle.setHeaderPageInfo(model, request, sqlWhere);
        List<ProjectUpms> upmsList = projectService.selectAllBySqlWhere(sqlWhere.toString());
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<ProjectUpms>(upmsList, pageSize);
        PageHandle.setFooterPageInfo(model, request, pageInfo);
        return "project-upms";
    }

}
