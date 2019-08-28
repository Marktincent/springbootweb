package com.uaes.esw.util;

import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhenghuan.wang
 */
public class PageHandle {
    public static void setFooterPageInfo(Model model, HttpServletRequest request, PageInfo pageInfo) {
        request.setAttribute("pageInfo", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        String requestUrl = request.getRequestURI();
        model.addAttribute("requestUrl", requestUrl);
    }

    public static void setHeaderPageInfo(Model model, HttpServletRequest request, StringBuilder sqlWhere) {
        Map<String, String> params = new HashMap<String, String>(16);
        Map<String, String[]> paramMap = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            String key = entry.getKey().toLowerCase().trim();
            String[] value = entry.getValue();
            if (key.startsWith("like_") && !StringUtils.isEmpty(value[0])) {
                params.put(key.substring(key.indexOf("_") + 1), "like '%" + value[0] + "%'");
                model.addAttribute(key, value[0]);
            }
        }
        Iterator<Map.Entry<String, String>> paramsIterator = params.entrySet().iterator();
        if (params.size() > 0) {
            while (paramsIterator.hasNext()) {
                Map.Entry<String, String> entry = paramsIterator.next();
                sqlWhere.append(" and " + entry.getKey() + " " + entry.getValue() + " ");
            }
        }
    }
}
