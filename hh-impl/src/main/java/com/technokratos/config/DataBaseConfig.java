package com.technokratos.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.technokratos")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.technokratos.repositories")
@EnableMongoRepositories(basePackages = "com.technokratos.repositories")
public class DataBaseConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory);
        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter,
                                                                       DataSource dataSource,
                                                                       Properties hibernateProperties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(adapter);
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaProperties(hibernateProperties);
        entityManagerFactory.setPackagesToScan("com.technokratos.models");

        return entityManagerFactory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        return adapter;
    }


    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.enable_lazy_load_no_trans",
                environment.getProperty("hibernate.enable_lazy_load_no_trans"));
        return properties;
    }


    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.username"));
        config.setPassword(environment.getProperty("db.password"));
        config.setDriverClassName(environment.getProperty("db.driver"));
        config.setMaximumPoolSize(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("db.hikari.pool-size"))));
        return config;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog/database_hh_changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(
                Objects.requireNonNull(environment.getProperty("mongo.url")));

        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build());
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient)  {
        return new MongoTemplate(mongoClient, Objects.requireNonNull(environment.getProperty("mongo.db")));
    }
}
