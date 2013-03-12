/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package am.ik.moneyger4u.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maki
 */
@Entity
@Table(name = "income")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Income.findAll", query = "SELECT i FROM Income i"),
    @NamedQuery(name = "Income.findByIncomeId", query = "SELECT i FROM Income i WHERE i.incomeId = :incomeId"),
    @NamedQuery(name = "Income.findByIncomeName", query = "SELECT i FROM Income i WHERE i.incomeName = :incomeName"),
    @NamedQuery(name = "Income.findByAmount", query = "SELECT i FROM Income i WHERE i.amount = :amount"),
    @NamedQuery(name = "Income.findByIncomeDate", query = "SELECT i FROM Income i WHERE i.incomeDate = :incomeDate"),
    @NamedQuery(name = "Income.findByCreatetedAt", query = "SELECT i FROM Income i WHERE i.createtedAt = :createtedAt"),
    @NamedQuery(name = "Income.findByUpdatedAt", query = "SELECT i FROM Income i WHERE i.updatedAt = :updatedAt"),
    @NamedQuery(name = "Income.findByVersion", query = "SELECT i FROM Income i WHERE i.version = :version")})
public class Income implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INCOME_ID")
    private Integer incomeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "INCOME_NAME")
    private String incomeName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INCOME_DATE")
    @Temporal(TemporalType.DATE)
    private Date incomeDate;
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
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "FAMILY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Family familyId;
    @JoinColumn(name = "INCOME_CATEGORY_ID", referencedColumnName = "INCOME_CATEGORY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private IncomeCategory incomeCategoryId;

    public Income() {
    }

    public Income(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Income(Integer incomeId, String incomeName, int amount, Date incomeDate, Date createtedAt, Date updatedAt, int version) {
        this.incomeId = incomeId;
        this.incomeName = incomeName;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.createtedAt = createtedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
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

    public Family getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Family familyId) {
        this.familyId = familyId;
    }

    public IncomeCategory getIncomeCategoryId() {
        return incomeCategoryId;
    }

    public void setIncomeCategoryId(IncomeCategory incomeCategoryId) {
        this.incomeCategoryId = incomeCategoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incomeId != null ? incomeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Income)) {
            return false;
        }
        Income other = (Income) object;
        if ((this.incomeId == null && other.incomeId != null) || (this.incomeId != null && !this.incomeId.equals(other.incomeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.Income[ incomeId=" + incomeId + " ]";
    }
    
}
