/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CommissionDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.CommissionEntity;

/**
 *
 * @author Rainbowdew
 */
@Stateless
public class CommissionService implements CommissionServiceLocal {

    @EJB
    private CommissionDao employeeDao;
    
    CommissionEntity result;
    
    @Override
    public void saveOrUpdate(CommissionEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            employeeDao.saveOrUpdate(entity);
    }

    @Override
    public List<CommissionEntity> findAll() {
        List<CommissionEntity> ent = employeeDao.findAll();
        System.out.println(ent);
        return ent;
    }

    @Override
    public void update(CommissionEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            employeeDao.update(entity);
        
    }

    @Override
    public void edit(CommissionEntity entity) {
        employeeDao.edit(entity);
    }

    @Override
    public void delete(CommissionEntity entity) {
        employeeDao.delete(entity);
    }

    @Override
    public CommissionEntity findById(Integer id) {
        return employeeDao.findById(id);
    }
    
    @Override
    public List<CommissionEntity> findForPage(int startNumber, int pageSize){
        return employeeDao.findForPage(startNumber, pageSize);
    }

}
