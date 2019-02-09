package dao;

import javax.ejb.Stateless;

import models.EmployeeEntity;
import models.OrganizationEntity;
import org.hibernate.Session;

@Stateless
public class OrganizationDao extends DaoGenericImpl<OrganizationEntity>{

    public OrganizationDao() {
        super(OrganizationEntity.class);
        super.initConnectionFromXML();
    }

    public OrganizationDao(Session session) {
        super(OrganizationEntity.class);
        super.setSession(session);
    }
}
