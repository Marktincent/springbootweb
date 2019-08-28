package com.uaes.esw.service.quartz;

import com.uaes.esw.service.FuegoService;
import com.uaes.esw.service.GsdjfentanService;
import com.uaes.esw.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhenghuan.wang
 */
@SuppressWarnings("ALL")
@Component
public class QuartzService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectService projectService;
    @Autowired
    private GsdjfentanService gsdjfentanService;
    @Autowired
    private FuegoService fuegoService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 0 0 07,12,18 * * ?    在每天上午7点,中午12点,下午6点触发
     */
    @Scheduled(cron = "0 0 07,12,18 * * ?")
    public void addJirahours() {
        logger.info("tragger synUpmsnoToJira now time:" + sdf.format(new Date()));
        projectService.synUpmsno(null);
    }

    /**
     * 0 0 07,12,18 * * ?    在每天上午7点,中午12点,下午6点触发
     */
    @Scheduled(cron = "0 0 07,12,18 * * ?")
    public void synUpmsnoToUcs() throws Exception {
        logger.info("tragger synUpmsnoToUcs now time:" + sdf.format(new Date()));
        projectService.synUpmsnoToUcs(null);
    }

    /**
     * 0 0/10 * * * ?   在每天三十分钟触发
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void exefenTanHours() throws Exception {
        logger.info("tragger exefenTanHours now time:" + sdf.format(new Date()));
        gsdjfentanService.exefenTanHours(null);
    }

    /**
     * 0 0 23 * * ?    在每天晚上11点触发
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void synUcsDefautRoles() {
        logger.info("tragger synUcsDefautRoles now time:" + sdf.format(new Date()));
        fuegoService.synUcsRoles("", "");
    }
}
