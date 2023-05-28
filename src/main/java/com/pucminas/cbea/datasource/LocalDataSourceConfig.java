package com.pucminas.cbea.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static java.util.Collections.singletonMap;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "localEntityManagerFactory",
        transactionManagerRef = "localTransactionManager",
        basePackages = "com.pucminas.cbea"
)
@EnableTransactionManagement
public class LocalDataSourceConfig {

    @Primary
    @Bean(name = "localEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                            final @Qualifier("local-db") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.pucminas.cbea")
                .persistenceUnit("localDb")
                .properties(singletonMap("hibernate.hbm2ddl.auto", "update"))
                .build();
    }

    @Primary
    @Bean(name = "localTransactionManager")
    public PlatformTransactionManager localTransactionManager(@Qualifier("localEntityManagerFactory")
                                                              EntityManagerFactory localEntityManagerFactory) {
        return new JpaTransactionManager(localEntityManagerFactory);
    }
}
