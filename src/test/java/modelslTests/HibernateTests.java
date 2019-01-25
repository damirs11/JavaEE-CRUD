package modelslTests;


import java.util.List;
import models.CommissionEntity;
import models.EmployeeEntity;
import models.OrganizationEntity;
import models.SubdivisionEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class HibernateTests {
    private SessionFactory sessionFactory;
    private Session session = null;
     
    @Before
    public void before() {
      // setup the session factory
      
      
    }
    
    @Test
    public void SubdivisionTest(){
        
        AnnotationConfiguration configuration = new AnnotationConfiguration();
        
        configuration.addAnnotatedClass(EmployeeEntity.class)
            .addAnnotatedClass(SubdivisionEntity.class)
            .addAnnotatedClass(CommissionEntity.class)
            .addAnnotatedClass(OrganizationEntity.class);
            
        configuration.setProperty("hibernate.dialect",
            "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class",
            "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", 
                "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "newuser");
        configuration.setProperty("hibernate.connection.password", "123456");
        
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        
        SubdivisionEntity entity = new SubdivisionEntity("subname");
        
        session.save(entity);
        
        Criteria criteria = session.createCriteria(SubdivisionEntity.class);       
        List<SubdivisionEntity> list = criteria.list();
        
        assertNotNull(list);
        assertEquals(1, list.size());
        
        session.close();
        sessionFactory.close();
    }
    
    @After
    public void after() {
        session.close();
        sessionFactory.close();
    }
}