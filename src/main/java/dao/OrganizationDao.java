package dao;

import javax.ejb.Stateless;
import models.OrganizationEntity;

@Stateless
public class OrganizationDao extends DaoGenericImpl<OrganizationEntity>{

    public OrganizationDao() {
        super(OrganizationEntity.class);
    }
}
