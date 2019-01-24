/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import models.OrganizationEntity;

/**
 *
 * @author Rainbowdew
 * @param <ID>
 */
@Local
public interface OrganizationServiceLocal<ID> {

    List<OrganizationEntity> findAll();

    void saveOrUpdate(OrganizationEntity entity);

    void update(OrganizationEntity entity);

    void edit(OrganizationEntity entity);

    void delete(OrganizationEntity entity);

    //OrganizationEntity findById(ID id);

    /*public List<T> findByCriteria(Criteria criteria);

    public void shutdown();*/

    OrganizationEntity findById(Integer id);

    public List<OrganizationEntity> findForPage(int startNumber, int pageSize);
    
    
    
}
