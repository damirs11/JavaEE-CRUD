package JsonTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.EmployeeEntity;
import models.OrganizationEntity;
import org.junit.jupiter.api.Test;

import javax.json.stream.JsonParsingException;
import java.util.HashSet;
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

        System.out.print(result_org);
        System.out.print(result_emp);

    }

    @Test
    void Employee_OneToMany_Employee()
            throws JsonParsingException, JsonProcessingException {

        EmployeeEntity emp = new EmployeeEntity("name", "last", "middle", "pos");

        Set<OrganizationEntity> org_set = new HashSet<>();
        org_set.add(
                new OrganizationEntity("name1", "physical", "legal", emp));
        org_set.add(
                new OrganizationEntity("name2", "physical", "legal", emp));

        emp.setOrganizations(org_set);

        String result = new ObjectMapper().writeValueAsString(emp);

        System.out.print(result);
    }

}
