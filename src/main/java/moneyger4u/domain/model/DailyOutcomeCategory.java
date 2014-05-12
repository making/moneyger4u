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
@Table(name = "daily_outcome_category")
@XmlRootElement
public class DailyOutcomeCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DAILY_OUTCOME_CATEGORY_ID")
    private Integer dailyOutcomeCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dailyOutcomeCategoryId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DailyOutcome> dailyOutcomeList;
    @JoinColumn(name = "PARENT_OUTCOME_CATEGORY_ID", referencedColumnName = "PARENT_OUTCOME_CATEGORY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParentOutcomeCategory parentOutcomeCategoryId;

    public DailyOutcomeCategory() {
    }

    public DailyOutcomeCategory(Integer dailyOutcomeCategoryId) {
        this.dailyOutcomeCategoryId = dailyOutcomeCategoryId;
    }

    public DailyOutcomeCategory(Integer dailyOutcomeCategoryId, String categoryName, Date createdAt, Date updatedAt, int version) {
        this.dailyOutcomeCategoryId = dailyOutcomeCategoryId;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getDailyOutcomeCategoryId() {
        return dailyOutcomeCategoryId;
    }

    public void setDailyOutcomeCategoryId(Integer dailyOutcomeCategoryId) {
        this.dailyOutcomeCategoryId = dailyOutcomeCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    public List<DailyOutcome> getDailyOutcomeList() {
        return dailyOutcomeList;
    }

    public void setDailyOutcomeList(List<DailyOutcome> dailyOutcomeList) {
        this.dailyOutcomeList = dailyOutcomeList;
    }

    public ParentOutcomeCategory getParentOutcomeCategoryId() {
        return parentOutcomeCategoryId;
    }

    public void setParentOutcomeCategoryId(ParentOutcomeCategory parentOutcomeCategoryId) {
        this.parentOutcomeCategoryId = parentOutcomeCategoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyOutcomeCategoryId != null ? dailyOutcomeCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DailyOutcomeCategory)) {
            return false;
        }
        DailyOutcomeCategory other = (DailyOutcomeCategory) object;
        if ((this.dailyOutcomeCategoryId == null && other.dailyOutcomeCategoryId != null) || (this.dailyOutcomeCategoryId != null && !this.dailyOutcomeCategoryId.equals(other.dailyOutcomeCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.DailyOutcomeCategory[ dailyOutcomeCategoryId=" + dailyOutcomeCategoryId + " ]";
    }

}
