/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moneyger4u.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author maki
 */
@Entity
@Table(name = "daily_outcome")
@XmlRootElement
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
    @Column(name = "QUANTITY")
    private int quantity;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PAYMENT")
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_WASTE")
    private boolean isWaste;

    @Size(max = 256)
    @Column(name = "REMARKS")
    private String remarks;

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

    @JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User updatedBy;

    @JoinColumn(name = "DAILY_OUTCOME_CATEGORY_ID", referencedColumnName = "DAILY_OUTCOME_CATEGORY_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private DailyOutcomeCategory dailyOutcomeCategoryId;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private User userId;

    public DailyOutcome() {
    }

    public DailyOutcome(Integer dailyOutcomeId) {
        this.dailyOutcomeId = dailyOutcomeId;
    }

    public DailyOutcome(Integer dailyOutcomeId, Date outcomeDate,
                        String outcomeName, int amount, int quantity, Payment payment,
                        boolean isWaste, Date createdAt, Date updatedAt, int version) {
        this.dailyOutcomeId = dailyOutcomeId;
        this.outcomeDate = outcomeDate;
        this.outcomeName = outcomeName;
        this.amount = amount;
        this.quantity = quantity;
        this.payment = payment;
        this.isWaste = isWaste;
        this.createdAt = createdAt;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
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

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DailyOutcomeCategory getDailyOutcomeCategoryId() {
        return dailyOutcomeCategoryId;
    }

    public void setDailyOutcomeCategoryId(
            DailyOutcomeCategory dailyOutcomeCategoryId) {
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
        if ((this.dailyOutcomeId == null && other.dailyOutcomeId != null)
                || (this.dailyOutcomeId != null && !this.dailyOutcomeId
                .equals(other.dailyOutcomeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.DailyOutcome[ dailyOutcomeId="
                + dailyOutcomeId + " ]";
    }

    @Getter
    @AllArgsConstructor
    public static class Total {
        private final BigInteger all;
        private final BigInteger waste;
        private final BigInteger card;
    }

    public static Total calcTotal(List<DailyOutcome> outcomes) {
        BigInteger total = BigInteger.ZERO;
        BigInteger wasteTotal = BigInteger.ZERO;
        BigInteger cardTotal = BigInteger.ZERO;

        for (DailyOutcome outcome : outcomes) {
            BigInteger subtotal = BigInteger.valueOf(outcome.getAmount())
                    .multiply(BigInteger.valueOf(outcome.getQuantity()));
            total = total.add(subtotal);
            if (outcome.getIsWaste()) {
                wasteTotal = wasteTotal.add(subtotal);
            }
            if (Payment.CREDITCARD == outcome.getPayment()) {
                cardTotal = cardTotal.add(subtotal);
            }
        }

        return new Total(total, wasteTotal, cardTotal);
    }
}
