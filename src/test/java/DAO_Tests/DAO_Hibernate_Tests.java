package DAO_Tests;

import dao.CommissionDao;
import dao.EmployeeDao;
import dao.OrganizationDao;
import dao.SubdivisionDao;
import models.CommissionEntity;
import models.EmployeeEntity;
import models.OrganizationEntity;
import models.SubdivisionEntity;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.registry.infomodel.Organization;
import java.util.List;

class Employee_CRUD_DAO_Hibernate_Tests {

    private static SessionFactory sessionFactory;
    private static Session session;

    private EmployeeDao employeeDao;
    private OrganizationDao organizationDao;
    private SubdivisionDao subdivisionDao;
    private CommissionDao commissionDao;

    @BeforeAll
    static void initDBConnection()
            throws HibernateError {

        Configuration configuration = new Configuration();
        configuration
                .setProperty("show_sql", "true")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.autocommit", "true")
                .setProperty("hibernate.current_session_context_class", "jta")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/unit?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false&createDatabaseIfNotExist=true&createTableIfNotExist=true&characterEncoding=UTF-8")
                .setProperty("hibernate.connection.username", "newuser")
                .setProperty("hibernate.connection.password", "123456")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(OrganizationEntity.class)
                .addAnnotatedClass(SubdivisionEntity.class)
                .addAnnotatedClass(CommissionEntity.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        System.out.println("Hibernate serviceRegistry created");

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    void Employee_Insert2persons_FindAll_Delete()
    {
        session = sessionFactory.openSession();
        employeeDao = new EmployeeDao();
        employeeDao.setSession(session);

        EmployeeEntity emp1 = new EmployeeEntity("first","last", "middle", "pos");
        EmployeeEntity emp2 = new EmployeeEntity("first","last", "middle", "pos");

        employeeDao.saveOrUpdate(emp1);
        employeeDao.saveOrUpdate(emp2);

        List<EmployeeEntity> result = employeeDao.findAll();
        for(EmployeeEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(2, result.size());

        for(EmployeeEntity emp: result)
            employeeDao.delete(emp);

        result = employeeDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @Test
    void Organization_Insert2persons_FindAll_Delete()
    {
        session = sessionFactory.openSession();
        organizationDao = new OrganizationDao();
        organizationDao.setSession(session);

        EmployeeEntity emp1 = new EmployeeEntity("1", "1", "1", "1");
        OrganizationEntity org1 = new OrganizationEntity("name","physical", "legal", emp1);
        EmployeeEntity emp2 = new EmployeeEntity("2", "2", "2", "2");
        OrganizationEntity org2 = new OrganizationEntity("name","physical", "legal", emp2);

        organizationDao.saveOrUpdate(org1);
        organizationDao.saveOrUpdate(org2);

        List<OrganizationEntity> result = organizationDao.findAll();
        for(OrganizationEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(2, result.size());

        for(OrganizationEntity emp: result)
            organizationDao.delete(emp);

        result = organizationDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @Test
    void Subdivision_Insert2persons_FindAll_Delete()
    {
        EmployeeEntity emp1 = new EmployeeEntity("first","last", "middle", "pos");
        EmployeeEntity emp2 = new EmployeeEntity("first","last", "middle", "pos");

        employeeDao.saveOrUpdate(emp1);
        employeeDao.saveOrUpdate(emp2);

        List<EmployeeEntity> result = employeeDao.findAll();
        for(EmployeeEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(2, result.size());

        for(EmployeeEntity emp: result)
            employeeDao.delete(emp);

        result = employeeDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @Test
    void Commission_Insert2persons_FindAll_Delete()
    {
        OrganizationEntity emp1 = new OrganizationEntity("first","last", "middle", "pos");
        OrganizationEntity emp2 = new OrganizationEntity("first","last", "middle", "pos");

        organizationDao.saveOrUpdate();
        organizationDao.saveOrUpdate();

        List<OrganizationEntity> result = organizationDao.findAll();
        for(OrganizationEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(2, result.size());

        for(OrganizationEntity org: result)
            organizationDao.delete(org);

        result = organizationDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @AfterEach
    void clearConnection() {
        session.close();
        sessionFactory.close();
    }
}