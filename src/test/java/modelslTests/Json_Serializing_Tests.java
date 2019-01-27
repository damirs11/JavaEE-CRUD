/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelslTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.CommissionEntity;
import models.EmployeeEntity;
import models.OrganizationEntity;
import models.SubdivisionEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class Json_Serializing_Tests {
    
    private SessionFactory sessionFactory;
    private Session session = null;
    
    @Test
    public void Employee_Organization_Serializing()
            throws JsonProcessingException{
        
        EmployeeEntity emp = new EmployeeEntity("first", "last", "middle", "posit");
        CommissionEntity com = new CommissionEntity("sub", emp);
        
        
        
        String result = new ObjectMapper().writeValueAsString(com);
        System.out.println(result);

    }
    
//    @Test
//    public void givenData_whenInsert_thenCreatesMtoMrelationship() {
//        
//        AnnotationConfiguration configuration = new AnnotationConfiguration();
//        
//        configuration.addAnnotatedClass(EmployeeEntity.class)
//            .addAnnotatedClass(SubdivisionEntity.class)
//            .addAnnotatedClass(CommissionEntity.class)
//            .addAnnotatedClass(OrganizationEntity.class);
//            
//        configuration.setProperty("hibernate.dialect",
//            "org.hibernate.dialect.MySQLDialect");
//        configuration.setProperty("hibernate.connection.driver_class",
//            "com.mysql.jdbc.Driver");
//        configuration.setProperty("hibernate.connection.url", 
//                "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//        configuration.setProperty("hibernate.connection.username", "newuser");
//        configuration.setProperty("hibernate.connection.password", "123456");
//        
//        sessionFactory = configuration.buildSessionFactory();
//        session = sessionFactory.openSession();
//        
//        EmployeeEntity emp1 = new EmployeeEntity("f", "l", "p");
//        CommissionEntity com = new CommissionEntity("sub", emp1);
//
//        session.beginTransaction();
//        session.saveOrUpdate(emp1);
//        session.saveOrUpdate(com);
//        session.getTransaction().commit();
//        
//        System.out.println(com.getId());
//        
//        List<CommissionEntity> list = session.createCriteria(CommissionEntity.class).list();
//        
//        System.out.println(list);
//        
//        session.close();
//        sessionFactory.close();
//    }
}
