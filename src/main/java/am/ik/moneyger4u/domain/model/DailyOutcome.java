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
@Table(name = "daily_outcome")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DailyOutcome.findAll", query = "SELECT d FROM DailyOutcome d"),
    @NamedQuery(name = "DailyOutcome.findByDailyOutcomeId", query = "SELECT d FROM DailyOutcome d WHERE d.dailyOutcomeId = :dailyOutcomeId"),
    @NamedQuery(name = "DailyOutcome.findByOutcomeDate", query = "SELECT d FROM DailyOutcome d WHERE d.outcomeDate = :outcomeDate"),
    @NamedQuery(name = "DailyOutcome.findByOutcomeName", query = "SELECT d FROM DailyOutcome d WHERE d.outcomeName = :outcomeName"),
    @NamedQuery(name = "DailyOutcome.findByAmount", query = "SELECT d FROM DailyOutcome d WHERE d.amount = :amount"),
    @NamedQuery(name = "DailyOutcome.findByPayment", query = "SELECT d FROM DailyOutcome d WHERE d.payment = :payment"),
    @NamedQuery(name = "DailyOutcome.findByIsWaste", query = "SELECT d FROM DailyOutcome d WHERE d.isWaste = :isWaste"),
    @NamedQuery(name = "DailyOutcome.findByRemarks", query = "SELECT d FROM DailyOutcome d WHERE d.remarks = :remarks"),
    @NamedQuery(name = "DailyOutcome.findByCreatetedAt", query = "SELECT d FROM DailyOutcome d WHERE d.createtedAt = :createtedAt"),
    @NamedQuery(name = "DailyOutcome.findByUpdatedAt", query = "SELECT d FROM DailyOutcome d WHERE d.updatedAt = :updatedAt"),
    @NamedQuery(name = "DailyOutcome.findByVersion", query = "SELECT d FROM DailyOutcome d WHERE d.version = :version")})
public class DailyOutcome implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DAILY_OUTCOME_ID")
    private Integer dailyOutcomeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OUTCOME_DATE")
    @Temporal(TemporalType.DATE)
    private Date outcomeDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OUTCOME_NAME")
    private String outcomeName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private int amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PAYMENT")
    private String payment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_WASTE")
    private boolean isWaste;
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
    @JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User updatedBy;
    @JoinColumn(name = "DAILY_OUTCOME_CATEGORY_ID", referencedColumnName = "DAILY_OUTCOME_CATEGORY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DailyOutcomeCategory dailyOutcomeCategoryId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public DailyOutcome() {
    }

    public DailyOutcome(Integer dailyOutcomeId) {
        this.dailyOutcomeId = dailyOutcomeId;
    }

    public DailyOutcome(Integer dailyOutcomeId, Date outcomeDate, String outcomeName, int amount, String payment, boolean isWaste, Date createtedAt, Date updatedAt, int version) {
        this.dailyOutcomeId = dailyOutcomeId;
        this.outcomeDate = outcomeDate;
        this.outcomeName = outcomeName;
        this.amount = amount;
        this.payment = payment;
        this.isWaste = isWaste;
        this.createtedAt = createtedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getDailyOutcomeId() {
        return dailyOutcomeId;
    }

    public void setDailyOutcomeId(Integer dailyOutcomeId) {
        this.dailyOutcomeId = dailyOutcomeId;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public boolean getIsWaste() {
        return isWaste;
    }

    public void setIsWaste(boolean isWaste) {
        this.isWaste = isWaste;
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

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DailyOutcomeCategory getDailyOutcomeCategoryId() {
        return dailyOutcomeCategoryId;
    }

    public void setDailyOutcomeCategoryId(DailyOutcomeCategory dailyOutcomeCategoryId) {
        this.dailyOutcomeCategoryId = dailyOutcomeCategoryId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyOutcomeId != null ? dailyOutcomeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DailyOutcome)) {
            return false;
        }
        DailyOutcome other = (DailyOutcome) object;
        if ((this.dailyOutcomeId == null && other.dailyOutcomeId != null) || (this.dailyOutcomeId != null && !this.dailyOutcomeId.equals(other.dailyOutcomeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.DailyOutcome[ dailyOutcomeId=" + dailyOutcomeId + " ]";
    }
    
}
