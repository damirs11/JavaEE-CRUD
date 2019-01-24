package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ДНС
 */
@Entity
@Table(catalog = "subdivision"
    ,schema = "test"
)
@XmlRootElement
public class SubdivisionEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "subdivisionName", nullable=false)
    private String subdivisionName;
    @Column(name = "contactDetails", nullable=true)
    private String contactDetails;
    @ManyToOne(targetEntity = EmployeeEntity.class)
    @JoinColumn(name = "employee_id", nullable=true)
    @JsonManagedReference
    private EmployeeEntity employee;

    public SubdivisionEntity() {
    }

    public SubdivisionEntity(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public SubdivisionEntity(String subdivisionName, String contactDetails, EmployeeEntity employee_id) {
        this.subdivisionName = subdivisionName;
        this.contactDetails = contactDetails;
        this.employee = employee_id;
    }
    
    

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

}