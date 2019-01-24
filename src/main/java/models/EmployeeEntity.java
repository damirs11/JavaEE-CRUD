package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "employee", schema = "test")
@XmlRootElement
public class EmployeeEntity  implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="firstName", nullable=false, length=50)
    private String firstName;
    @Column(name="lastName", nullable=false, length=50)
    private String lastName;
    @Column(name="middleName", nullable=true, length=50)
    private String middleName;
    @Column(name="position", nullable=false, length=50)
    private String position;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrganizationEntity> organization = new HashSet<OrganizationEntity>();
    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<SubdivisionEntity> subdivision = new HashSet<SubdivisionEntity>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorCommission")
    private Set<CommissionEntity> commissionAuthor;
    
    public EmployeeEntity(){
    }

    public EmployeeEntity(String firstName, String lastName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }
    
    public EmployeeEntity(String firstName, String lastName, String middleName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
  
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }
    

    @XmlTransient
    public Set<OrganizationEntity> getOrganization() {
        return organization;
    }

    public void setOrganization(Set<OrganizationEntity> organization) {
        this.organization = organization;
    }
    
    @XmlTransient
    public Set<SubdivisionEntity> getSubdivision() {
        return subdivision;
    }
    
    public void setSubdivision(Set<SubdivisionEntity> subdivision){
        this.subdivision = subdivision;
    }

    public Set<Commission> getCommissionAuthor() {
        return commissionAuthor;
    }

    public void setCommissionAuthor(Set<Commission> commissionAuthor) {
        this.commissionAuthor = commissionAuthor;
    }
    
    @Override
    public String toString() {
        return "EmployeeEntity{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", position=" + position + ", organization=" + organization + ", subdivision=" + subdivision + ", commissionAuthor=" + commissionAuthor + '}';
    }

    
}
