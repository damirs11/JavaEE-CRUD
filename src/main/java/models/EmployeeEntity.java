package models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "employee")
@XmlRootElement
public class EmployeeEntity  implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Integer id;
    
    @Column(name="firstName", nullable=false, length=50)
    private String firstName;
    @Column(name="lastName", nullable=false, length=50)
    private String lastName;
    @Column(name="middleName", nullable=true, length=50)
    private String middleName;
    @Column(name="position", nullable=false, length=50)
    private String position;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee_id")
    private Set<OrganizationEntity> organizations = new HashSet<OrganizationEntity>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee_id")
    private Set<SubdivisionEntity> subdivisions = new HashSet<SubdivisionEntity>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authorCommission")
    private Set<CommissionEntity> myCommissions = new HashSet<CommissionEntity>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "commissioners")
    private Set<CommissionEntity> commissionsForMe = new HashSet<CommissionEntity>();
    
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
    public Set<OrganizationEntity> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<OrganizationEntity> organizations) {
        this.organizations = organizations;
    }
    
    @XmlTransient
    public Set<SubdivisionEntity> getSubdivisions() {
        return subdivisions;
    }
    
    public void setSubdivisions(Set<SubdivisionEntity> subdivisions){
        this.subdivisions = subdivisions;
    }
    
    @XmlTransient
    public Set<CommissionEntity> getMyCommissions() {
        return myCommissions;
    }

    public void setMyCommissions(Set<CommissionEntity> myCommissions) {
        this.myCommissions = myCommissions;
    }
    
    @XmlTransient
    public Set<CommissionEntity> getCommissionsForMe() {
        return commissionsForMe;
    }

    public void setCommissionsForMe(Set<CommissionEntity> commissionsForMe) {
        this.commissionsForMe = commissionsForMe;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
