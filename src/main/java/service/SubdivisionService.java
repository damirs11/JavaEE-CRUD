/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.SubdivisionDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.SubdivisionEntity;

/**
 *
 * @author Rainbowdew
 */
@Stateless
public class SubdivisionService implements SubdivisionServiceLocal {

    @EJB
    private SubdivisionDao employeeDao;
    
    SubdivisionEntity result;
    
    @Override
    public void saveOrUpdate(SubdivisionEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            employeeDao.saveOrUpdate(entity);
    }

    @Override
    public List<SubdivisionEntity> findAll() {
        List<SubdivisionEntity> ent = employeeDao.findAll();
        System.out.println(ent);
        return ent;
    }

    @Override
    public void update(SubdivisionEntity entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
            employeeDao.update(entity);
        
    }

    @Override
    public void edit(SubdivisionEntity entity) {
        employeeDao.edit(entity);
    }

    @Override
    public void delete(SubdivisionEntity entity) {
        employeeDao.delete(entity);
    }

    @Override
    public SubdivisionEntity findById(Integer id) {
        return employeeDao.findById(id);
    }
    
    @Override
    public List<SubdivisionEntity> findForPage(int startNumber, int pageSize){
        return employeeDao.findForPage(startNumber, pageSize);
    }

}
