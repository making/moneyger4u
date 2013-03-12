/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package am.ik.moneyger4u.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maki
 */
@Entity
@Table(name = "monthly_outcome_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonthlyOutcomeCategory.findAll", query = "SELECT m FROM MonthlyOutcomeCategory m"),
    @NamedQuery(name = "MonthlyOutcomeCategory.findByMonthlyOutcomeCategoryId", query = "SELECT m FROM MonthlyOutcomeCategory m WHERE m.monthlyOutcomeCategoryId = :monthlyOutcomeCategoryId"),
    @NamedQuery(name = "MonthlyOutcomeCategory.findByCategoryName", query = "SELECT m FROM MonthlyOutcomeCategory m WHERE m.categoryName = :categoryName"),
    @NamedQuery(name = "MonthlyOutcomeCategory.findByUnitName", query = "SELECT m FROM MonthlyOutcomeCategory m WHERE m.unitName = :unitName"),
    @NamedQuery(name = "MonthlyOutcomeCategory.findByCreatetedAt", query = "SELECT m FROM MonthlyOutcomeCategory m WHERE m.createtedAt = :createtedAt"),
    @NamedQuery(name = "MonthlyOutcomeCategory.findByUpdatedAt", query = "SELECT m FROM MonthlyOutcomeCategory m WHERE m.updatedAt = :updatedAt"),
    @NamedQuery(name = "MonthlyOutcomeCategory.findByVersion", query = "SELECT m FROM MonthlyOutcomeCategory m WHERE m.version = :version")})
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
    @Column(name = "CREATETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createtedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION")
    private int version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthlyOutcomeCategoryId", fetch = FetchType.LAZY)
    private List<MonthlyOutcome> monthlyOutcomeList;

    public MonthlyOutcomeCategory() {
    }

    public MonthlyOutcomeCategory(Integer monthlyOutcomeCategoryId) {
        this.monthlyOutcomeCategoryId = monthlyOutcomeCategoryId;
    }

    public MonthlyOutcomeCategory(Integer monthlyOutcomeCategoryId, String categoryName, Date createtedAt, Date updatedAt, int version) {
        this.monthlyOutcomeCategoryId = monthlyOutcomeCategoryId;
        this.categoryName = categoryName;
        this.createtedAt = createtedAt;
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

    public Date getCreatetedAt() {
        return createtedAt;
    }

    public void setCreatetedAt(Date createtedAt) {
        this.createtedAt = createtedAt;
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
