package dao;

import java.io.Serializable;
import javax.ejb.Local;
import java.util.List;

@Local
public interface DaoBase<T,N extends Serializable> {

    List<T> findAll();

    void saveOrUpdate(T entity);

    void update(T entity);

    void edit(T entity);

    void delete(T entity);

    T findById(N id);
    
    T get(N id);
    
    

    /*public List<T> findByCriteria(Criteria criteria);

    public void shutdown();*/
}
