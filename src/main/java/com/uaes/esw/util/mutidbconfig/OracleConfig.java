package com.uaes.esw.util.mutidbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author zhenghuan.wang
 */
@SpringBootConfiguration
@MapperScan(basePackages = "com.uaes.esw.mapper.ebon", sqlSessionFactoryRef = "oracleSessionFactory")
public class OracleConfig {

    @Bean
    public SqlSessionFactory oracleSessionFactory(@Qualifier("oracle") DataSource oracleDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(oracleDataSource);
        return bean.getObject();
    }
}
