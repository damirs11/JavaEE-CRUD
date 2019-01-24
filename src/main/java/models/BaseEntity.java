/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author rmpestano
 */
public interface BaseEntity extends Serializable{


    public <T extends Serializable> T getId();

}
