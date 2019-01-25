/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "commission", schema = "test")
@XmlRootElement
public class CommissionEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "subjectCommission",nullable = false)
    private String subjectCommission;
    @Temporal(TemporalType.DATE)
    @Column(name = "periodExecution")
    private Date periodExecution;
    @Column(name = "signControl", length = 255)
    private String signControl;
    @Column(name = "signExecution")
    private String signExecution;
    @Lob
    @Size(max = 65535)
    @Column(name = "сommissionText" ,length = 65535)
    private String сommissionText;
    
    @JoinTable(name = "employee_commission", joinColumns = {
        @JoinColumn(name = "commission_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<EmployeeEntity> employeeSet;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "authorCommission", nullable = false)
    @JsonManagedReference
    private EmployeeEntity authorCommission;

    public CommissionEntity() {
    }

    public CommissionEntity(String subjectCommission, EmployeeEntity authorCommission) {
        this.subjectCommission = subjectCommission;
        this.authorCommission = authorCommission;
    }
    
    

    @Override
    public Integer getId() {
        return id;
    }

    public String getSubjectCommission() {
        return subjectCommission;
    }

    public Date getPeriodExecution() {
        return periodExecution;
    }

    public String getSignControl() {
        return signControl;
    }

    public String getSignExecution() {
        return signExecution;
    }

    public String getСommissionText() {
        return сommissionText;
    }

    public Set<EmployeeEntity> getEmployeeSet() {
        return employeeSet;
    }

    public EmployeeEntity getAuthorCommission() {
        return authorCommission;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubjectCommission(String subjectCommission) {
        this.subjectCommission = subjectCommission;
    }
    
    public void setPeriodExecution(Date periodExecution) {
        this.periodExecution = periodExecution;
    }

    public void setSignControl(String signControl) {
        this.signControl = signControl;
    }

    public void setSignExecution(String signExecution) {
        this.signExecution = signExecution;
    }

    public void setСommissionText(String сommissionText) {
        this.сommissionText = сommissionText;
    }

    public void setEmployeeSet(Set<EmployeeEntity> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public void setAuthorCommission(EmployeeEntity authorCommission) {
        this.authorCommission = authorCommission;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.subjectCommission);
        hash = 29 * hash + Objects.hashCode(this.periodExecution);
        hash = 29 * hash + Objects.hashCode(this.signControl);
        hash = 29 * hash + Objects.hashCode(this.signExecution);
        hash = 29 * hash + Objects.hashCode(this.сommissionText);
        hash = 29 * hash + Objects.hashCode(this.employeeSet);
        hash = 29 * hash + Objects.hashCode(this.authorCommission);
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
        final CommissionEntity other = (CommissionEntity) obj;
        if (!Objects.equals(this.subjectCommission, other.subjectCommission)) {
            return false;
        }
        if (!Objects.equals(this.signControl, other.signControl)) {
            return false;
        }
        if (!Objects.equals(this.signExecution, other.signExecution)) {
            return false;
        }
        if (!Objects.equals(this.сommissionText, other.сommissionText)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.periodExecution, other.periodExecution)) {
            return false;
        }
        if (!Objects.equals(this.employeeSet, other.employeeSet)) {
            return false;
        }
        if (!Objects.equals(this.authorCommission, other.authorCommission)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommissionEntity{" + "id=" + id + ", subjectCommission=" + subjectCommission + ", periodExecution=" + periodExecution + ", signControl=" + signControl + ", signExecution=" + signExecution + ", \u0441ommissionText=" + сommissionText + ", employeeSet=" + employeeSet + ", authorCommission=" + authorCommission + '}';
    }
    
    
}
