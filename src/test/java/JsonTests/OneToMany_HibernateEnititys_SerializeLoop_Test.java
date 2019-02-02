package JsonTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.CommissionEntity;
import models.EmployeeEntity;
import models.OrganizationEntity;
import models.SubdivisionEntity;
import org.junit.Assert;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

import javax.json.stream.JsonParsingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class OneToMany_HibernateEnititys_SerializeLoop_Test{

    @Test
    void Employee_OneToMany_Organization()
            throws JsonParsingException, JsonProcessingException {

        EmployeeEntity emp = new EmployeeEntity("name", "last", "middle", "pos");

        Set<OrganizationEntity> org_set = new HashSet<>();
        org_set.add(
                new OrganizationEntity("name1", "physical", "legal", emp));
        org_set.add(
                new OrganizationEntity("name2", "physical", "legal", emp));

        emp.setOrganizations(org_set);

        String result_org = new ObjectMapper().writeValueAsString(org_set);
        String result_emp = new ObjectMapper().writeValueAsString(emp);

        Assert.assertNotNull(result_emp);
        Assert.assertNotNull(result_org);
    }

    @Test
    void Employee_OneToMany_Subdivision()
            throws JsonParsingException, JsonProcessingException {

        EmployeeEntity emp = new EmployeeEntity("name", "last", "middle", "pos");

        Set<SubdivisionEntity> org_set = new HashSet<>();
        org_set.add(
                new SubdivisionEntity("sub", "contact", emp));
        org_set.add(
                new SubdivisionEntity("sub", "contact", emp));

        emp.setSubdivisions(org_set);

        String result_org = new ObjectMapper().writeValueAsString(org_set);
        String result_emp = new ObjectMapper().writeValueAsString(emp);

        Assert.assertNotNull(result_emp);
        Assert.assertNotNull(result_org);
    }

    @Test
    void Employee_OneToMany_Commission_Author()
            throws JsonParsingException, JsonProcessingException {

        EmployeeEntity emp = new EmployeeEntity("name", "last", "middle", "pos");

        Set<CommissionEntity> org_set = new HashSet<>();
        org_set.add(
                new CommissionEntity("sub", emp));
        org_set.add(
                new CommissionEntity("sub", emp));

        emp.setCommissionsForMe(org_set);

        String result_org = new ObjectMapper().writeValueAsString(org_set);
        String result_emp = new ObjectMapper().writeValueAsString(emp);

        Assert.assertNotNull(result_emp);
        Assert.assertNotNull(result_org);
    }

    @Test
    void Employee_ManyToMany_Commission_Commissioners()
            throws JsonParsingException, JsonProcessingException {

        Set<EmployeeEntity> emp_set = new HashSet<>();
        emp_set.add(
                new EmployeeEntity("name", "last", "middle", "pos"));
        emp_set.add(
                new EmployeeEntity("name", "last", "middle", "pos"));
        EmployeeEntity empA =
                new EmployeeEntity("nameA", "last", "middle", "pos");

        Set<CommissionEntity> org_set = new HashSet<>();
        org_set.add(
                new CommissionEntity("sub", new Date(), "sign", "sign", "Text", emp_set, empA));
        org_set.add(
                new CommissionEntity("sub", new Date(), "sign", "sign", "Text", emp_set, empA));

        empA.setMyCommissions(org_set);

        for (Iterator<EmployeeEntity> it = emp_set.iterator(); it.hasNext();){
            EmployeeEntity emp = it.next();
            emp.setCommissionsForMe(org_set);
        }

        String result_org = new ObjectMapper().writeValueAsString(org_set);
        String result_emp = new ObjectMapper().writeValueAsString(emp_set);

        Assert.assertNotNull(result_emp);
        Assert.assertNotNull(result_org);
    }
}
