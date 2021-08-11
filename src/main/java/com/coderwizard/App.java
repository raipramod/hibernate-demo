package com.coderwizard;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Configuration configuration = new Configuration();

        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(props);
            configuration.addAnnotatedClass(User.class);
           // configuration.addAnnotatedClass(User.class);
           // ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                //    .applySettings(configuration.getProperties()).build();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
           // Session session = sessionFactory.getCurrentSession();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = new User();
            user.setUsername("test");
            user.setPassword("PP");
            user.setDob(LocalDate.now());
            user.setAddress("Kathmandu");
            session.save(user);
            session.getTransaction().commit();
            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
