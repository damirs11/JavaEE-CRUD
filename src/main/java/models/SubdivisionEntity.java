package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "subdivision")
@XmlRootElement
public class SubdivisionEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "subdivisionName", nullable=false)
    private String subdivisionName;
    @Column(name = "contactDetails")
    private String contactDetails;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private EmployeeEntity employee_id;

    public SubdivisionEntity() {
    }

    public SubdivisionEntity(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public SubdivisionEntity(String subdivisionName, String contactDetails, EmployeeEntity employee_id) {
        this.subdivisionName = subdivisionName;
        this.contactDetails = contactDetails;
        this.employee_id = employee_id;
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

    public EmployeeEntity getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(EmployeeEntity employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "SubdivisionEntity{" +
                "id=" + id +
                ", subdivisionName='" + subdivisionName + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", employee_id=" + employee_id +
                '}';
    }
}