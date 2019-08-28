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
@MapperScan(basePackages = "com.uaes.esw.mapper.ucs", sqlSessionFactoryRef = "oracleUcsSessionFactory" )
public class OracleUcsConfig {
    //sqlSessionTemplateRef = "oracleUcsSqlSessionTemplate"
    /*@Bean
    public SqlSessionTemplate oracleUcsSqlSessionTemplate(@Qualifier("oracleUcsSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }*/

    @Bean
    public SqlSessionFactory oracleUcsSessionFactory(@Qualifier("oracleUcs") DataSource oracleUcsDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(oracleUcsDataSource);
        return bean.getObject();
    }
}
