package es.tendam.eco.zipcode.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages={"es.tendam.eco.zipcode.dao"}, sqlSessionFactoryRef="PSSqlSessionFactory")
@EnableTransactionManagement
public class PSDatasourceConfiguration {

	@Bean("mainDS")
	@Primary
	@ConfigurationProperties(prefix="app.datasource.ps")
	public DataSource createDataSource() {
		 return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "sqlTxManager")
    @Primary
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(createDataSource());
    }
	
	@Bean(name="PSSqlSessionFactory")
	@Primary
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(createDataSource());
        sqlSessionFactoryBean.setMapperLocations(new ClassPathResource[] {
            	new ClassPathResource("es/tendam/eco/zipcode/mapper/ZipcodeMapper.xml")
        });
              
        sqlSessionFactoryBean.setTypeAliasesPackage("es.tendam.eco.zipcode.model");
        
        return sqlSessionFactoryBean.getObject();
    }
}

