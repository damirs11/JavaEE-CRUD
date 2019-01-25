/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelslTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.CommissionEntity;
import models.EmployeeEntity;
import models.OrganizationEntity;
import models.SubdivisionEntity;
import static org.hamcrest.CoreMatchers.containsString;
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
        SubdivisionEntity sub = new SubdivisionEntity();
        CommissionEntity com = new CommissionEntity("123", emp);
        
        emp.getOrganizations().add(org);
        emp.getSubdivisions().add(sub);
        emp.getmyCommissions().add(com);
        
        String result1 = new ObjectMapper().writeValueAsString(org);
        String result2 = new ObjectMapper().writeValueAsString(sub);
        String result3 = new ObjectMapper().writeValueAsString(com);
        
        
        
        
        
        assertThat(result1, containsString("name"));
        assertThat(result1, containsString("first"));
        
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
    
}
