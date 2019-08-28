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
@MapperScan(basePackages = "com.uaes.esw.mapper.postgres", sqlSessionFactoryRef = "postgresSessionFactory")
public class PostgresConfig {

    @Bean
    public SqlSessionFactory postgresSessionFactory(@Qualifier("postgres") DataSource postgresDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(postgresDataSource);
        return bean.getObject();
    }
}
