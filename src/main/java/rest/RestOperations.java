/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Rainbowdew
 */
interface RestOperations<T> {
    
    Response findAll();
    
    Response findById(@PathParam("id") Integer id);
    
    Response saveOrUpdate(T entity);
    
    Response edit(T entity);
    
    Response delete(@PathParam("id") Integer id);
}
