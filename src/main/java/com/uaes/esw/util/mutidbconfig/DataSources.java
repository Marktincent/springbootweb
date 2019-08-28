package com.uaes.esw.util.mutidbconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhenghuan.wang
 */
@Configuration
public class DataSources {

    @Bean
    //默认数据源
    @Primary
    //将properties中以mysql为前缀的参数值，写入方法返回的对象中
    @ConfigurationProperties(prefix = "mysql")
    public DataSource mysqDataSource() {
        //通过DataSourceBuilder构建数据源
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    //副数据库需要配置@Qualifier
    @Qualifier("oracle")
    @ConfigurationProperties(prefix = "oracle")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier("oracleUcs")
    @ConfigurationProperties(prefix = "ucs-ds")
    public DataSource oracleUcsDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier("oracleObpmdir")
    @ConfigurationProperties(prefix = "ucs-obpmdir")
    public DataSource oracleObpmdirDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier("postgres")
    @ConfigurationProperties(prefix = "postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(DataSource mysqlDataSource) {
        return new DataSourceTransactionManager(mysqlDataSource);
    }

    @Bean
    public PlatformTransactionManager oracleTransactionManager(@Qualifier("oracle") DataSource oracleDataSource) {
        return new DataSourceTransactionManager(oracleDataSource);
    }

    @Bean
    public PlatformTransactionManager oracleUcsTransactionManager(@Qualifier("oracleUcs") DataSource oracleUcsDataSource) {
        return new DataSourceTransactionManager(oracleUcsDataSource);
    }

    @Bean
    public PlatformTransactionManager oracleObpmdirTransactionManager(@Qualifier("oracleObpmdir") DataSource oracleObpmdirDataSource) {
        return new DataSourceTransactionManager(oracleObpmdirDataSource);
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgres") DataSource postgresDataSource) {
        return new DataSourceTransactionManager(postgresDataSource);
    }
}
