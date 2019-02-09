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
    
    public List<CommissionEntity> findMyCommissions(int id){
        
        resetCriteria();
        getCriteria().add(Restrictions.eq("authorCommission.id", id));
        getCriteria().setResultTransformer(getCriteria().DISTINCT_ROOT_ENTITY);
//        //from commission left outer join employee on authorCommission=employee_id
//        Query query = getSession().createQuery(
//        "select c from CommissionEntity c");
//        List<CommissionEntity> em = query.list();
//        System.out.println(em.toString());
        return getCriteria().list();
    }
}
