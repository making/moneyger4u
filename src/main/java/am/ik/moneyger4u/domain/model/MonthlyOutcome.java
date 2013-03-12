/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package am.ik.moneyger4u.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "monthly_outcome")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonthlyOutcome.findAll", query = "SELECT m FROM MonthlyOutcome m"),
    @NamedQuery(name = "MonthlyOutcome.findByMonthlyOutcomeId", query = "SELECT m FROM MonthlyOutcome m WHERE m.monthlyOutcomeId = :monthlyOutcomeId"),
    @NamedQuery(name = "MonthlyOutcome.findByOutcomeDate", query = "SELECT m FROM MonthlyOutcome m WHERE m.outcomeDate = :outcomeDate"),
    @NamedQuery(name = "MonthlyOutcome.findByOutcomeName", query = "SELECT m FROM MonthlyOutcome m WHERE m.outcomeName = :outcomeName"),
    @NamedQuery(name = "MonthlyOutcome.findByAmount", query = "SELECT m FROM MonthlyOutcome m WHERE m.amount = :amount"),
    @NamedQuery(name = "MonthlyOutcome.findByQuantity", query = "SELECT m FROM MonthlyOutcome m WHERE m.quantity = :quantity"),
    @NamedQuery(name = "MonthlyOutcome.findByRemarks", query = "SELECT m FROM MonthlyOutcome m WHERE m.remarks = :remarks"),
    @NamedQuery(name = "MonthlyOutcome.findByCreatetedAt", query = "SELECT m FROM MonthlyOutcome m WHERE m.createtedAt = :createtedAt"),
    @NamedQuery(name = "MonthlyOutcome.findByUpdatedAt", query = "SELECT m FROM MonthlyOutcome m WHERE m.updatedAt = :updatedAt"),
    @NamedQuery(name = "MonthlyOutcome.findByVersion", query = "SELECT m FROM MonthlyOutcome m WHERE m.version = :version")})
public class MonthlyOutcome implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MONTHLY_OUTCOME_ID")
    private Integer monthlyOutcomeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OUTCOME_DATE")
    @Temporal(TemporalType.DATE)
    private Date outcomeDate;
    @Size(max = 100)
    @Column(name = "OUTCOME_NAME")
    private String outcomeName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private int amount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    @Size(max = 256)
    @Column(name = "REMARKS")
    private String remarks;
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
    @JoinColumn(name = "MONTHLY_OUTCOME_CATEGORY_ID", referencedColumnName = "MONTHLY_OUTCOME_CATEGORY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MonthlyOutcomeCategory monthlyOutcomeCategoryId;
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "FAMILY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Family familyId;
    @JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User updatedBy;

    public MonthlyOutcome() {
    }

    public MonthlyOutcome(Integer monthlyOutcomeId) {
        this.monthlyOutcomeId = monthlyOutcomeId;
    }

    public MonthlyOutcome(Integer monthlyOutcomeId, Date outcomeDate, int amount, Date createtedAt, Date updatedAt, int version) {
        this.monthlyOutcomeId = monthlyOutcomeId;
        this.outcomeDate = outcomeDate;
        this.amount = amount;
        this.createtedAt = createtedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getMonthlyOutcomeId() {
        return monthlyOutcomeId;
    }

    public void setMonthlyOutcomeId(Integer monthlyOutcomeId) {
        this.monthlyOutcomeId = monthlyOutcomeId;
    }

    public Date getOutcomeDate() {
        return outcomeDate;
    }

    public void setOutcomeDate(Date outcomeDate) {
        this.outcomeDate = outcomeDate;
    }

    public String getOutcomeName() {
        return outcomeName;
    }

    public void setOutcomeName(String outcomeName) {
        this.outcomeName = outcomeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public MonthlyOutcomeCategory getMonthlyOutcomeCategoryId() {
        return monthlyOutcomeCategoryId;
    }

    public void setMonthlyOutcomeCategoryId(MonthlyOutcomeCategory monthlyOutcomeCategoryId) {
        this.monthlyOutcomeCategoryId = monthlyOutcomeCategoryId;
    }

    public Family getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Family familyId) {
        this.familyId = familyId;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monthlyOutcomeId != null ? monthlyOutcomeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonthlyOutcome)) {
            return false;
        }
        MonthlyOutcome other = (MonthlyOutcome) object;
        if ((this.monthlyOutcomeId == null && other.monthlyOutcomeId != null) || (this.monthlyOutcomeId != null && !this.monthlyOutcomeId.equals(other.monthlyOutcomeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.MonthlyOutcome[ monthlyOutcomeId=" + monthlyOutcomeId + " ]";
    }
    
}
