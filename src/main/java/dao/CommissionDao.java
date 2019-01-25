package dao;

import javax.ejb.Stateless;
import models.CommissionEntity;

@Stateless
public class CommissionDao extends DaoGenericImpl<CommissionEntity>{

    public CommissionDao() {
        super(CommissionEntity.class);
    }
}
