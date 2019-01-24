/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelslTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.EmployeeEntity;
import models.OrganizationEntity;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class Json_Serializing_Tests {
    
    @Test
    public void Employee_Organization_Serializing()
            throws JsonProcessingException{
        
        EmployeeEntity emp = new EmployeeEntity("first", "last", "middle", "posit");
        OrganizationEntity org = new OrganizationEntity("name", "pith", "legal", emp);
        emp.getOrganization().add(org);
        
        String result = new ObjectMapper().writeValueAsString(org);
        
        assertThat(result, containsString("name"));
        assertThat(result, containsString("first"));
        
        System.out.println(result);
    }
    
}
