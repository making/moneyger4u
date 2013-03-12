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
@Table(name = "parent_outcome_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentOutcomeCategory.findAll", query = "SELECT p FROM ParentOutcomeCategory p"),
    @NamedQuery(name = "ParentOutcomeCategory.findByParentOutcomeCategoryId", query = "SELECT p FROM ParentOutcomeCategory p WHERE p.parentOutcomeCategoryId = :parentOutcomeCategoryId"),
    @NamedQuery(name = "ParentOutcomeCategory.findByCategoryName", query = "SELECT p FROM ParentOutcomeCategory p WHERE p.categoryName = :categoryName"),
    @NamedQuery(name = "ParentOutcomeCategory.findByCreatetedAt", query = "SELECT p FROM ParentOutcomeCategory p WHERE p.createtedAt = :createtedAt"),
    @NamedQuery(name = "ParentOutcomeCategory.findByUpdatedAt", query = "SELECT p FROM ParentOutcomeCategory p WHERE p.updatedAt = :updatedAt"),
    @NamedQuery(name = "ParentOutcomeCategory.findByVersion", query = "SELECT p FROM ParentOutcomeCategory p WHERE p.version = :version")})
public class ParentOutcomeCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PARENT_OUTCOME_CATEGORY_ID")
    private Integer parentOutcomeCategoryId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentOutcomeCategoryId", fetch = FetchType.LAZY)
    private List<DailyOutcomeCategory> dailyOutcomeCategoryList;

    public ParentOutcomeCategory() {
    }

    public ParentOutcomeCategory(Integer parentOutcomeCategoryId) {
        this.parentOutcomeCategoryId = parentOutcomeCategoryId;
    }

    public ParentOutcomeCategory(Integer parentOutcomeCategoryId, String categoryName, Date createtedAt, Date updatedAt, int version) {
        this.parentOutcomeCategoryId = parentOutcomeCategoryId;
        this.categoryName = categoryName;
        this.createtedAt = createtedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getParentOutcomeCategoryId() {
        return parentOutcomeCategoryId;
    }

    public void setParentOutcomeCategoryId(Integer parentOutcomeCategoryId) {
        this.parentOutcomeCategoryId = parentOutcomeCategoryId;
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
    public List<DailyOutcomeCategory> getDailyOutcomeCategoryList() {
        return dailyOutcomeCategoryList;
    }

    public void setDailyOutcomeCategoryList(List<DailyOutcomeCategory> dailyOutcomeCategoryList) {
        this.dailyOutcomeCategoryList = dailyOutcomeCategoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentOutcomeCategoryId != null ? parentOutcomeCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParentOutcomeCategory)) {
            return false;
        }
        ParentOutcomeCategory other = (ParentOutcomeCategory) object;
        if ((this.parentOutcomeCategoryId == null && other.parentOutcomeCategoryId != null) || (this.parentOutcomeCategoryId != null && !this.parentOutcomeCategoryId.equals(other.parentOutcomeCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.ParentOutcomeCategory[ parentOutcomeCategoryId=" + parentOutcomeCategoryId + " ]";
    }
    
}
