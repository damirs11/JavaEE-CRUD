import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;

public class DAO_Hibernate_Tests {

    private static SessionFactory sessionFactory = null;
    Session session = null;

    @BeforeEach
    public void InitConnection()
        throws JDBCConnectionException {

        Configuration configuration = new Configuration();
        configuration
                .setProperty("show_sql", "true")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.autocommit", "true")
                .setProperty("hibernate.current_session_context_class", "jta")
                .setProperty("", "");

    }

}

//<?xml version="1.0" encoding="UTF-8"?>
//<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
//<hibernate-configuration>
//  <session-factory>
//    <property name="show_sql">true</property>
//    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
//    <property name="hibernate.connection.autocommit">true</property>
//    <property name="hibernate.current_session_context_class">jta</property>
//    <property name="hibernate.connection.datasource">jdbc/dbtest</property>
//    <mapping class="models.EmployeeEntity"/>
//    <mapping class="models.OrganizationEntity"/>
//    <mapping class="models.SubdivisionEntity"/>
//    <mapping class="models.CommissionEntity"/>
//  </session-factory>
//</hibernate-configuration>
