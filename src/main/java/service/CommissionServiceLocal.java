/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import models.CommissionEntity;
import models.EmployeeEntity;

/**
 *
 * @author Rainbowdew
 * @param <ID>
 */
@Local
public interface CommissionServiceLocal<ID> {

    List<CommissionEntity> findAll();

    void saveOrUpdate(CommissionEntity entity);

    void update(CommissionEntity entity);

    void edit(CommissionEntity entity);

    void delete(CommissionEntity entity);

    //CommissionEntity findById(ID id);

    /*public List<T> findByCriteria(Criteria criteria);

    public void shutdown();*/

    CommissionEntity findById(Integer id);

    List<CommissionEntity> findForPage(int startNumber, int pageSize);
    
    List<CommissionEntity> myCommissions(int id);

    List<CommissionEntity> commissionsForMe(int id);
    
    
    
}
