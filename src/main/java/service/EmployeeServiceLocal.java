/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import models.EmployeeEntity;

/**
 *
 * @author Rainbowdew
 * @param <ID>
 */
@Local
public interface EmployeeServiceLocal<ID> {

    List<EmployeeEntity> findAll();

    void saveOrUpdate(EmployeeEntity entity);

    void update(EmployeeEntity entity);

    void edit(EmployeeEntity entity);

    void delete(EmployeeEntity entity);

    //EmployeeEntity findById(ID id);

    /*public List<T> findByCriteria(Criteria criteria);

    public void shutdown();*/

    EmployeeEntity findById(Integer id);

    public List<EmployeeEntity> findForPage(int startNumber, int pageSize);
    
    
    
}
