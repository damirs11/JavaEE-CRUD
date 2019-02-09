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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/unit?createDatabaseIfNotExist=true&useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false&createDatabaseIfNotExist=true&createTableIfNotExist=true&characterEncoding=UTF-8")
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
        session = sessionFactory.openSession();
    }

    @Test
    void Employee_Insert2persons_FindAll_Delete()
    {
        employeeDao = new EmployeeDao(session);

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
        employeeDao = new EmployeeDao(session);

        organizationDao = new OrganizationDao(session);

        EmployeeEntity emp1 = new EmployeeEntity("1", "1", "1", "1");
        EmployeeEntity emp2 = new EmployeeEntity("2", "2", "2", "2");

        employeeDao.saveOrUpdate(emp1);
        employeeDao.saveOrUpdate(emp2);


        OrganizationEntity org1 = new OrganizationEntity("name","physical", "legal", emp1);
        OrganizationEntity org2 = new OrganizationEntity("name","physical", "legal", emp2);

        organizationDao.saveOrUpdate(org1);
        organizationDao.saveOrUpdate(org2);

        List<OrganizationEntity> result = organizationDao.findAll();
        for(OrganizationEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(2, result.size());

        for(OrganizationEntity emp: result){
            employeeDao.delete(emp.getEmployee_id());
            organizationDao.delete(emp);}

        result = organizationDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @Test
    void Subdivision_Insert2persons_FindAll_Delete()
    {
        employeeDao = new EmployeeDao(session);

        subdivisionDao = new SubdivisionDao(session);

        EmployeeEntity emp1 = new EmployeeEntity("11", "1", "1", "1");
        EmployeeEntity emp2 = new EmployeeEntity("22", "2", "2", "2");

        employeeDao.saveOrUpdate(emp1);
        employeeDao.saveOrUpdate(emp2);

        SubdivisionEntity sub1 = new SubdivisionEntity("sub", "contact", emp1);
        SubdivisionEntity sub2 = new SubdivisionEntity("sub", "contact", emp2);

        subdivisionDao.saveOrUpdate(sub1);
        subdivisionDao.saveOrUpdate(sub2);

        List<SubdivisionEntity> result = subdivisionDao.findAll();
        for(SubdivisionEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(2, result.size());

        for(SubdivisionEntity sub: result){
            employeeDao.delete(sub.getEmployee_id());
            subdivisionDao.delete(sub);}

        result = subdivisionDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @Test
    void Commission_Insert2persons_FindAll_Delete()
    {
        employeeDao = new EmployeeDao(session);

        commissionDao = new CommissionDao(session);

        EmployeeEntity emp1 = new EmployeeEntity("111", "1", "1", "1");
        EmployeeEntity emp2 = new EmployeeEntity("222", "2", "2", "2");
        Set<EmployeeEntity> setemp = new HashSet<>();
        setemp.add(emp1);
        setemp.add(emp2);

        EmployeeEntity author = new EmployeeEntity("author", "2", "2", "2");

        employeeDao.saveOrUpdate(emp1);
        employeeDao.saveOrUpdate(emp2);
        employeeDao.saveOrUpdate(author);

        CommissionEntity comm1 = new CommissionEntity("sub", new Date(), "sign", "signE", "Text", setemp, author);

        commissionDao.saveOrUpdate(comm1);

        List<CommissionEntity> result = commissionDao.findAll();
        for(CommissionEntity emp: result)
            System.out.print(emp.toString() + "\n");

        Assert.assertEquals(1, result.size());

        for(CommissionEntity comm: result){
            employeeDao.delete(comm.getAuthorCommission());
            for(EmployeeEntity emp: comm.getCommissioners())
                employeeDao.delete(emp);
            commissionDao.delete(comm);}

        result = commissionDao.findAll();

        Assert.assertEquals(0, result.size());
    }

    @AfterAll
    static void clearConnection() {
        session.close();
        sessionFactory.close();
    }
}