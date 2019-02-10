package dao;

import java.util.List;
import javax.ejb.Stateless;

import models.CommissionEntity;
import models.SubdivisionEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Stateless
public class CommissionDao extends DaoGenericImpl<CommissionEntity>{

    public CommissionDao() {
        super(CommissionEntity.class);
        super.initConnectionFromXML();
    }

    public CommissionDao(Session session) {
        super(CommissionEntity.class);
        super.setSession(session);
    }
    
    public List<CommissionEntity> findMyCommissions(int _id){
        
        String hql = "select c from CommissionEntity c where c.authorCommission.id = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", _id);

        return query.list();
    }
}
