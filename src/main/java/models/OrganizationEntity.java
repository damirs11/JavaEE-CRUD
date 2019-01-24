package models;
// Generated 06.01.2019 13:46:14 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="organization"
    ,schema = "test"
)
@XmlRootElement
public class OrganizationEntity  implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Integer id;
    
    
    @Column(name="nameOfCompany", nullable=false)
    private String nameOfCompany;
    @Column(name="physicalAdress", nullable=true)
    private String physicalAdress;
    @Column(name="legalAddress", nullable=true)
    private String legalAddress;
    @ManyToOne(targetEntity = EmployeeEntity.class)
    @JoinColumn(name = "employee_id", nullable=true)
    @JsonManagedReference
    private EmployeeEntity employee;
    
    public OrganizationEntity() {
    }

	
    public OrganizationEntity(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }
    public OrganizationEntity(String nameOfCompany, String physicalAdress, String legalAddress, EmployeeEntity EmployeeEntity) {
       this.nameOfCompany = nameOfCompany;
       this.physicalAdress = physicalAdress;
       this.legalAddress = legalAddress;
       this.employee = EmployeeEntity;
    }
   
    @Override
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNameOfCompany() {
        return this.nameOfCompany;
    }
    
    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    
    public String getPhysicalAdress() {
        return this.physicalAdress;
    }
    
    public void setPhysicalAdress(String physicalAdress) {
        this.physicalAdress = physicalAdress;
    }

    
    public String getLegalAddress() {
        return this.legalAddress;
    }
    
    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nameOfCompany);
        hash = 17 * hash + Objects.hashCode(this.physicalAdress);
        hash = 17 * hash + Objects.hashCode(this.legalAddress);
        hash = 17 * hash + Objects.hashCode(this.employee);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrganizationEntity other = (OrganizationEntity) obj;
        if (!Objects.equals(this.nameOfCompany, other.nameOfCompany)) {
            return false;
        }
        if (!Objects.equals(this.physicalAdress, other.physicalAdress)) {
            return false;
        }
        if (!Objects.equals(this.legalAddress, other.legalAddress)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.employee, other.employee);
    }

    @Override
    public String toString() {
        return "OrganizationEntity{" + "id=" + id + ", nameOfCompany=" + nameOfCompany + ", physicalAdress=" + physicalAdress + ", legalAddress=" + legalAddress + ", employee=" + employee + '}';
    }
    
    




}

