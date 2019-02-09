package dao;

import javax.ejb.Stateless;
import models.EmployeeEntity;
import org.hibernate.Session;

@Stateless
public class EmployeeDao extends DaoGenericImpl<EmployeeEntity>{

    public EmployeeDao() {
        super(EmployeeEntity.class);
        super.initConnectionFromXML();
    }

    public EmployeeDao(Session session) {
        super(EmployeeEntity.class);
        super.setSession(session);
    }


}
