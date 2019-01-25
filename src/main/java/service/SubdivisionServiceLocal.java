/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import models.SubdivisionEntity;

/**
 *
 * @author Rainbowdew
 * @param <ID>
 */
@Local
public interface SubdivisionServiceLocal<ID> {

    List<SubdivisionEntity> findAll();

    void saveOrUpdate(SubdivisionEntity entity);

    void update(SubdivisionEntity entity);

    void edit(SubdivisionEntity entity);

    void delete(SubdivisionEntity entity);

    //SubdivisionEntity findById(ID id);

    /*public List<T> findByCriteria(Criteria criteria);

    public void shutdown();*/

    SubdivisionEntity findById(Integer id);

    public List<SubdivisionEntity> findForPage(int startNumber, int pageSize);
    
    
    
}
