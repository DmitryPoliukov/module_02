package com.epam.esm.controller.conf;

import com.epam.esm.CertificateDaoImpl;
import com.epam.esm.TagDaoImpl;
import com.epam.esm.controller.TagController;
import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {TagController.class}) //
//basePackages = "com\\epam\\esm\\controller"
public class SpringConfig {
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/db02r");
        dataSource.setUsername("root");
        dataSource.setPassword("112121");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
        /*
         FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            String host = property.getProperty("db.host");
            String login = property.getProperty("db.login");
            String password = property.getProperty("db.password");

         */
    }

    @Bean
    public TagDao getTagDao() {
        return new TagDaoImpl(getJdbcTemplate());
    }

    @Bean
    public CertificateDao getCertificateDao() {
        return new CertificateDaoImpl(getJdbcTemplate());
    }

    @Bean
    public TagActionService getAddTagActionService() {
        return new AddTagActionServiceImpl(getTagDao(), getCertificateDao());
    }

    @Bean TagActionService getRemoveActionService() {
        return new RemoveTagActionServiceImpl(getTagDao(), getCertificateDao());
    }

    /*
    @Bean
    public TagService getTagService() {
        return new TagServiceImpl(getTagDao(), getCertificateDao(), )
    }

 */
    @Bean
    public CertificateService getCertificateService() {
        return  new CertificateServiceImpl(getCertificateDao(), getTagDao());
    }
    /*
    @Bean
    public TagController getTagController() {
        return new TagController()
    }

     */

}
