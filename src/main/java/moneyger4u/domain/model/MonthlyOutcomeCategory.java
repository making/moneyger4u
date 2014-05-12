/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moneyger4u.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author maki
 */
@Entity
@Table(name = "monthly_outcome_category")
@XmlRootElement
public class MonthlyOutcomeCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MONTHLY_OUTCOME_CATEGORY_ID")
    private Integer monthlyOutcomeCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Size(max = 10)
    @Column(name = "UNIT_NAME")
    private String unitName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Version
    @Column(name = "VERSION")
    private int version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthlyOutcomeCategoryId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MonthlyOutcome> monthlyOutcomeList;

    public MonthlyOutcomeCategory() {
    }

    public MonthlyOutcomeCategory(Integer monthlyOutcomeCategoryId) {
        this.monthlyOutcomeCategoryId = monthlyOutcomeCategoryId;
    }

    public MonthlyOutcomeCategory(Integer monthlyOutcomeCategoryId, String categoryName, Date createdAt, Date updatedAt, int version) {
        this.monthlyOutcomeCategoryId = monthlyOutcomeCategoryId;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getMonthlyOutcomeCategoryId() {
        return monthlyOutcomeCategoryId;
    }

    public void setMonthlyOutcomeCategoryId(Integer monthlyOutcomeCategoryId) {
        this.monthlyOutcomeCategoryId = monthlyOutcomeCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @XmlTransient
    public List<MonthlyOutcome> getMonthlyOutcomeList() {
        return monthlyOutcomeList;
    }

    public void setMonthlyOutcomeList(List<MonthlyOutcome> monthlyOutcomeList) {
        this.monthlyOutcomeList = monthlyOutcomeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monthlyOutcomeCategoryId != null ? monthlyOutcomeCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonthlyOutcomeCategory)) {
            return false;
        }
        MonthlyOutcomeCategory other = (MonthlyOutcomeCategory) object;
        if ((this.monthlyOutcomeCategoryId == null && other.monthlyOutcomeCategoryId != null) || (this.monthlyOutcomeCategoryId != null && !this.monthlyOutcomeCategoryId.equals(other.monthlyOutcomeCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.MonthlyOutcomeCategory[ monthlyOutcomeCategoryId=" + monthlyOutcomeCategoryId + " ]";
    }

}
