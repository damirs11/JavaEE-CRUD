package dao;

import javax.ejb.Stateless;
import models.SubdivisionEntity;

@Stateless
public class SubdivisionDao extends DaoGenericImpl<SubdivisionEntity>{

    public SubdivisionDao() {
        super(SubdivisionEntity.class);
    }
}
