/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CommissionDao;
import dao.EmployeeDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.CommissionEntity;
import models.EmployeeEntity;

/**
 *
 * @author Rainbowdew
 */
@Stateless
public class CommissionService implements CommissionServiceLocal {

    @EJB
    private CommissionDao commissionDao;
    @EJB
    private EmployeeDao employeeDao;
    
    
    CommissionEntity result;
    
    @Override
    public void saveOrUpdate(CommissionEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            commissionDao.saveOrUpdate(entity);
    }

    @Override
    public List<CommissionEntity> findAll() {
        List<CommissionEntity> ent = commissionDao.findAll();
        System.out.println(ent);
        return ent;
    }

    @Override
    public void update(CommissionEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            commissionDao.update(entity);
        
    }

    @Override
    public void edit(CommissionEntity entity) {
        commissionDao.edit(entity);
    }

    @Override
    public void delete(CommissionEntity entity) {
        commissionDao.delete(entity);
    }

    @Override
    public CommissionEntity findById(Integer id) {
        return commissionDao.findById(id);
    }
    
    @Override
    public List<CommissionEntity> findForPage(int startNumber, int pageSize){
        return commissionDao.findForPage(startNumber, pageSize);
    }
    
    public  List<CommissionEntity> commissionsForMe(int id){
        
        return commissionDao.findMyCommissions(id);
    }

}
