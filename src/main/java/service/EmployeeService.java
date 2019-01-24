/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.EmployeeDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.EmployeeEntity;

/**
 *
 * @author Rainbowdew
 */
@Stateless
public class EmployeeService implements EmployeeServiceLocal {

    @EJB
    private EmployeeDao employeeDao;
    
    EmployeeEntity result;
    
    @Override
    public void saveOrUpdate(EmployeeEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            employeeDao.saveOrUpdate(entity);
    }

    @Override
    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity> ent = employeeDao.findAll();
        System.out.println(ent);
        return ent;
    }

    @Override
    public void update(EmployeeEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            employeeDao.update(entity);
        
    }

    @Override
    public void edit(EmployeeEntity entity) {
        employeeDao.edit(entity);
    }

    @Override
    public void delete(EmployeeEntity entity) {
        employeeDao.delete(entity);
    }

    @Override
    public EmployeeEntity findById(Integer id) {
        return employeeDao.findById(id);
    }
    
    @Override
    public List<EmployeeEntity> findForPage(int startNumber, int pageSize){
        return employeeDao.findForPage(startNumber, pageSize);
    }

}
