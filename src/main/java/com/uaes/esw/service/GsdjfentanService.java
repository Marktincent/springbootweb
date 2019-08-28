package com.uaes.esw.service;

import java.util.List;
import java.util.Map;

public interface GsdjfentanService {
    public void fenTanHours(String registertime) throws Exception;

    public void exefenTanHours(String registertime) throws Exception;

    public Map<String, String> getMonthHours(String userId, String month) throws Exception;

    List<Map> getGroupLeader(String userId);
}
