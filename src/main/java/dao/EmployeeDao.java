package dao;

import javax.ejb.Stateless;
import models.EmployeeEntity;

@Stateless
public class EmployeeDao extends DaoGenericImpl<EmployeeEntity>{

    public EmployeeDao() {
        super(EmployeeEntity.class);
    }
    


}
