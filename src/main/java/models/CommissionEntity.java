/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "commission", schema = "test")
@XmlRootElement
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class CommissionEntity implements BaseEntity {

    @Id
    @Column(name = "commission_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "subjectCommission",nullable = false)
    private String subjectCommission;
    @Temporal(TemporalType.DATE)
    private Date periodExecution;
    @Column(name = "signControl")
    private String signControl;
    @Column(name = "signExecution")
    private String signExecution;
    @Column(name = "сommissionText" ,length = 65535)
    private String сommissionText;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "commission_employee",
            joinColumns = { @JoinColumn(name = "commission_id")},
            inverseJoinColumns = { @JoinColumn(name = "employee_id")})
    private Set<EmployeeEntity> commissioners;
    
    @ManyToOne
    @JoinColumn(name = "authorCommission")
    private EmployeeEntity authorCommission;

    public CommissionEntity() {
    }

    public CommissionEntity(String subjectCommission, EmployeeEntity authorCommission) {
        this.subjectCommission = subjectCommission;
        this.authorCommission = authorCommission;
    }

    public CommissionEntity(String subjectCommission, Date periodExecution, String signControl, String signExecution, String сommissionText, Set<EmployeeEntity> commissioners, EmployeeEntity authorCommission) {
        this.subjectCommission = subjectCommission;
        this.periodExecution = periodExecution;
        this.signControl = signControl;
        this.signExecution = signExecution;
        this.сommissionText = сommissionText;
        this.commissioners = commissioners;
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

    public Set<EmployeeEntity> getCommissioners() {
        return commissioners;
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

    public void setCommissioners(Set<EmployeeEntity> commissioners) {
        this.commissioners = commissioners;
    }

    public void setAuthorCommission(EmployeeEntity authorCommission) {
        this.authorCommission = authorCommission;
    }
}
