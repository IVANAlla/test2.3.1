////package web.config;
////
////import jakarta.persistence.EntityManagerFactory;
////import web.model.User;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.ComponentScan;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.PropertySource;
////import org.springframework.core.env.Environment;
////import org.springframework.jdbc.datasource.DriverManagerDataSource;
////import org.springframework.orm.hibernate5.HibernateTransactionManager;
////import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
////import org.springframework.transaction.annotation.EnableTransactionManagement;
////
////import javax.persistence.EntityManager;
////import javax.sql.DataSource;
////import java.util.Properties;
////
/////**
//// * @EnableTransactionManagement — позволяет использовать TransactionManager для управления транзакциями.
//// * Hibernate работает с БД с помощью транзакций, они нужны чтобы какой-то набор операций выполнялся как
//// * единое целое, т.е. если в методе возникнут проблемы с какой-то одной операцией, тогда не выполнятся
//// * и все остальные, чтобы не было как в классическом примере с переводом денег, когда операция снятия
//// * денег с одного счета свершилась, а операция записи на другой не сработала, в итоге деньги исчезли.
//// */
////
////@Configuration
////@PropertySource("classpath:db.properties")
////@EnableTransactionManagement
////@ComponentScan("web")
////public class HibernateConfig {
////
////    @Autowired
////    private final Environment env; // Для того, чтобы получить свойства из property файла.
////
////    public HibernateConfig(Environment env) {
////        this.env = env;
////    }
////
////    /**
////     * Используется для создания соединения с БД. Это альтернатива DriverManager, которой мы использовали ранее,
////     * когда создавали для проверки подключения метод main. В документации сказано, что DataSource использовать
////     * предпочтительнее. Так и поступим, естественно не забыв почитать в интернете в чем разница и преимущества.
////     * В частности, одним из преимуществ является возможность создания пула соединений Database Connection Pool (DBCP).
////     */
////
////    @Bean
////    public DataSource getDataSource() {
////        DriverManagerDataSource dataSource = new DriverManagerDataSource();
////        dataSource.setDriverClassName(env.getProperty("db.driver"));
////        dataSource.setUrl(env.getProperty("db.url"));
////        dataSource.setUsername(env.getProperty("db.username"));
////        dataSource.setPassword(env.getProperty("db.password"));
////        return dataSource;
////    }
////
////    @Bean
////    public EntityManager makeEntityManager(EntityManagerFactory entityManagerFactory) {
////       return entityManagerFactory.createEntityManager();
////    }
////
////    /**
////     *  Для создания сессий, с помощью которых осуществляются операции с объектами-сущностями. Здесь мы устанавливаем
////     *  источник данных, свойства Hibernate и в каком пакете нужно искать классы-сущности.
////     */
////    @Bean
////    public LocalSessionFactoryBean getSessionFactory() {
////        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
////        factoryBean.setDataSource(getDataSource());
////
////        Properties props = new Properties();
////        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
////        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
////        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
////
////        factoryBean.setHibernateProperties(props);
////        factoryBean.setAnnotatedClasses(User.class);
////
////        return factoryBean;
////    }
////
////    /**
////     * Для настройки менеджера транзакций.
////     */
////    @Bean
////    public HibernateTransactionManager getTransactionManager() {
////        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
////        transactionManager.setSessionFactory(getSessionFactory().getObject());
////        return transactionManager;
////    }
////}
//package web.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@PropertySource("classpath:db.properties")
//@EnableTransactionManagement
//@ComponentScan(value = "web")
//public class HibernateConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("db.driver"));
//        dataSource.setUrl(env.getProperty("db.url"));
//        dataSource.setUsername(env.getProperty("db.username"));
//        dataSource.setPassword(env.getProperty("db.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public EntityManager makeEntityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(getDataSource());
//        em.setPackagesToScan("web.model");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Properties props = new Properties();
//        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//
//        em.setJpaProperties(props);
//
//        return em;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//}
package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@PropertySource("classpath:db.properties")
@Configuration
@ComponentScan(value = "web")
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Environment env;


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setDataSource(getDataSource());
        em.setPackagesToScan("web");
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }


    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}