
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

public class HibernateTests {
    private SessionFactory sessionFactory;
    private Session session = null;
     
    @Before
    public void before() {
      // setup the session factory
      
      sessionFactory = HibernateUtil.getSessionFactory();
      session = sessionFactory.openSession();
    }
    
    @Test
    public void EmployeeReadTest(){
            
    }
    
    @After
    public void after() {
        session.close();
        sessionFactory.close();
    }
}