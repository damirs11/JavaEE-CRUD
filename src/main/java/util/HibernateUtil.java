package util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    
    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        try{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            return sessionFactory;
        }
        catch(HibernateException e)
        {
            throw new HibernateException("Fail in 'createSessionFactory' function, please check your hibernate config file!" + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = createSessionFactory();
        return sessionFactory;
    }

}
