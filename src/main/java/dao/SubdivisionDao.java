package dao;

import javax.ejb.Stateless;

import models.OrganizationEntity;
import models.SubdivisionEntity;
import org.hibernate.Session;

@Stateless
public class SubdivisionDao extends DaoGenericImpl<SubdivisionEntity>{

    public SubdivisionDao() {
        super(SubdivisionEntity.class);
        super.initConnectionFromXML();
    }

    public SubdivisionDao(Session session) {
        super(SubdivisionEntity.class);
        super.setSession(session);
    }
}
