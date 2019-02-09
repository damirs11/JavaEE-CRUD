package dao;

import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

public abstract class DaoGenericImpl<T>  implements DaoBase<T, Integer> {
    
    private Session session;
    private Class<T> entityClass;
    private Criteria criteria;

    DaoGenericImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private Class<T> getEntityClass() {
        return entityClass;
    }
    
    public Session getSession(){
        return session;
    }
    
    @Override
    public List<T> findAll() {

        return getCriteria().list();
    }
    
    public List<T> findForPage(int startNumber, int pageSize) {
        resetCriteria();
        getCriteria().setFirstResult(startNumber);
        getCriteria().setMaxResults(pageSize);
        List<T> result = getCriteria().list();
        
        return result;
    }

    @Override
    public void saveOrUpdate(T entity) {
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    @Override
    public void update(T entity) { //не работает как задумывалось
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void edit(T entity) {
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
    }
    
    @Override
    public void delete(T entity) {
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

//    @Override
//    public T findById(Integer id) {
//        return (T) session.get(entityClass, id);
//    }
    
    @Override
    public T findById(Integer id){
        T o = (T) session.get(entityClass, id);
        return o;
    }
    
    @Override
    public T get(Integer id){
        T o = (T) session.get(entityClass, id);
        return o;
    }
    
    
    
    public int count() {
        Criteria c_Result = getSession().createCriteria(entityClass);
        c_Result.setProjection(Projections.rowCount());
        Long result = (Long) c_Result.uniqueResult();
        resetCriteria();
        return result.intValue();
    }
    
    
    
    
    
    
    
    public DaoGenericImpl<T> initCriteria() {
        criteria = getSession().createCriteria(getEntityClass());
        return this;
    }
    
    public Criteria getCriteria() {
        if (criteria == null) {
            criteria = getSession().createCriteria(getEntityClass());
        }
        return criteria;
    }
    
    public void resetCriteria(){
        this.criteria = null;
    }
    
    public DaoGenericImpl<T> addCriterion(Criterion criteria){
        if(criteria != null)
            getCriteria().add(criteria);
        return this;
    }
    
    public  void setSession(Session session){
        this.session = session;
    }

    public void initConnectionFromXML(){
        session = HibernateUtil.createSessionFactory().openSession();
    }
        
    
/*
    @Override
    public List<T> findByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public void shutdown() {

    }
*/
}
