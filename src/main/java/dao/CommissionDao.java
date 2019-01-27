package dao;

import java.util.List;
import javax.ejb.Stateless;

import models.CommissionEntity;
import org.hibernate.Query;

@Stateless
public class CommissionDao extends DaoGenericImpl<CommissionEntity>{

    public CommissionDao() {
        super(CommissionEntity.class);
    }
    
    public List<CommissionEntity> findMyCommissions(int id){
        //from commission left outer join employee on authorCommission=employee_id
        Query query = getSession().createQuery(
        "select c from CommissionEntity c");
        List<CommissionEntity> em = query.list();
        System.out.println(em.toString());
        return query.list();
    }
}
