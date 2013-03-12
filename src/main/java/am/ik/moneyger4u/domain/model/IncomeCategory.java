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
@Table(name = "income_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncomeCategory.findAll", query = "SELECT i FROM IncomeCategory i"),
    @NamedQuery(name = "IncomeCategory.findByIncomeCategoryId", query = "SELECT i FROM IncomeCategory i WHERE i.incomeCategoryId = :incomeCategoryId"),
    @NamedQuery(name = "IncomeCategory.findByCategoryName", query = "SELECT i FROM IncomeCategory i WHERE i.categoryName = :categoryName"),
    @NamedQuery(name = "IncomeCategory.findByCreatetedAt", query = "SELECT i FROM IncomeCategory i WHERE i.createtedAt = :createtedAt"),
    @NamedQuery(name = "IncomeCategory.findByUpdatedAt", query = "SELECT i FROM IncomeCategory i WHERE i.updatedAt = :updatedAt"),
    @NamedQuery(name = "IncomeCategory.findByVersion", query = "SELECT i FROM IncomeCategory i WHERE i.version = :version")})
public class IncomeCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INCOME_CATEGORY_ID")
    private Integer incomeCategoryId;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incomeCategoryId", fetch = FetchType.LAZY)
    private List<Income> incomeList;

    public IncomeCategory() {
    }

    public IncomeCategory(Integer incomeCategoryId) {
        this.incomeCategoryId = incomeCategoryId;
    }

    public IncomeCategory(Integer incomeCategoryId, String categoryName, Date createtedAt, Date updatedAt, int version) {
        this.incomeCategoryId = incomeCategoryId;
        this.categoryName = categoryName;
        this.createtedAt = createtedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getIncomeCategoryId() {
        return incomeCategoryId;
    }

    public void setIncomeCategoryId(Integer incomeCategoryId) {
        this.incomeCategoryId = incomeCategoryId;
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
    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incomeCategoryId != null ? incomeCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncomeCategory)) {
            return false;
        }
        IncomeCategory other = (IncomeCategory) object;
        if ((this.incomeCategoryId == null && other.incomeCategoryId != null) || (this.incomeCategoryId != null && !this.incomeCategoryId.equals(other.incomeCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.IncomeCategory[ incomeCategoryId=" + incomeCategoryId + " ]";
    }
    
}
