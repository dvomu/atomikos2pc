package com.dvomu.config;
import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@MapperScan(basePackages = "com.dvomu.mapper.logs",
        sqlSessionFactoryRef = "secondSqlSessionFactory"
)
public class MybatisDatasourceConfig2 {


    @Bean(name = "secondDataSource")
    public DataSource secondDataSource(DBConfig2 config2) throws SQLException {
        //获取mysqlXaDataSource
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        //配置数据源
        mysqlXaDataSource.setUrl(config2.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(config2.getPassword());
        mysqlXaDataSource.setUser(config2.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //将mysql数据源交由Atomikos统一管理
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("secondDataSource");
        return xaDataSource;
    }


    /**
     * 设置SqlSessionFactory，每一个数据源一个SqlSessionFactoryBean
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/logsMapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

