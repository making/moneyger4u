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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "daily_outcome_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DailyOutcomeCategory.findAll", query = "SELECT d FROM DailyOutcomeCategory d"),
    @NamedQuery(name = "DailyOutcomeCategory.findByDailyOutcomeCategoryId", query = "SELECT d FROM DailyOutcomeCategory d WHERE d.dailyOutcomeCategoryId = :dailyOutcomeCategoryId"),
    @NamedQuery(name = "DailyOutcomeCategory.findByCategoryName", query = "SELECT d FROM DailyOutcomeCategory d WHERE d.categoryName = :categoryName"),
    @NamedQuery(name = "DailyOutcomeCategory.findByCreatetedAt", query = "SELECT d FROM DailyOutcomeCategory d WHERE d.createtedAt = :createtedAt"),
    @NamedQuery(name = "DailyOutcomeCategory.findByUpdatedAt", query = "SELECT d FROM DailyOutcomeCategory d WHERE d.updatedAt = :updatedAt"),
    @NamedQuery(name = "DailyOutcomeCategory.findByVersion", query = "SELECT d FROM DailyOutcomeCategory d WHERE d.version = :version")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dailyOutcomeCategoryId", fetch = FetchType.LAZY)
    private List<DailyOutcome> dailyOutcomeList;
    @JoinColumn(name = "PARENT_OUTCOME_CATEGORY_ID", referencedColumnName = "PARENT_OUTCOME_CATEGORY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ParentOutcomeCategory parentOutcomeCategoryId;

    public DailyOutcomeCategory() {
    }

    public DailyOutcomeCategory(Integer dailyOutcomeCategoryId) {
        this.dailyOutcomeCategoryId = dailyOutcomeCategoryId;
    }

    public DailyOutcomeCategory(Integer dailyOutcomeCategoryId, String categoryName, Date createtedAt, Date updatedAt, int version) {
        this.dailyOutcomeCategoryId = dailyOutcomeCategoryId;
        this.categoryName = categoryName;
        this.createtedAt = createtedAt;
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
