package dao;

import javax.ejb.Stateless;
import models.EmployeeEntity;
import org.hibernate.Session;

@Stateless
public class EmployeeDao extends DaoGenericImpl<EmployeeEntity>{

    public EmployeeDao() {
        super(EmployeeEntity.class);
    }



}
