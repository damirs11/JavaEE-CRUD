/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.OrganizationDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.OrganizationEntity;

/**
 *
 * @author Rainbowdew
 */
@Stateless
public class OrganizationService implements OrganizationServiceLocal {

    @EJB
    private OrganizationDao OrganizationDao;
    
    OrganizationEntity result;
    
    @Override
    public void saveOrUpdate(OrganizationEntity entity) {
        OrganizationDao.saveOrUpdate(entity);
    }

    @Override
    public List<OrganizationEntity> findAll() {
        List<OrganizationEntity> ent = OrganizationDao.findAll();
        System.out.println(ent);
        return ent;
    }

    @Override
    public void update(OrganizationEntity entity) {
        OrganizationDao.update(entity);
        
    }

    @Override
    public void edit(OrganizationEntity entity) {
        OrganizationDao.edit(entity);
    }

    @Override
    public void delete(OrganizationEntity entity) {
        OrganizationDao.delete(entity);
    }

    @Override
    public OrganizationEntity findById(Integer id) {
        return OrganizationDao.findById(id);
    }
    
    @Override
    public List<OrganizationEntity> findForPage(int startNumber, int pageSize){
        return OrganizationDao.findForPage(startNumber, pageSize);
    }

}
