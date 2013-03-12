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
@Table(name = "family")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Family.findAll", query = "SELECT f FROM Family f"),
    @NamedQuery(name = "Family.findByFamilyId", query = "SELECT f FROM Family f WHERE f.familyId = :familyId"),
    @NamedQuery(name = "Family.findByFamilyName", query = "SELECT f FROM Family f WHERE f.familyName = :familyName"),
    @NamedQuery(name = "Family.findByCreatetedAt", query = "SELECT f FROM Family f WHERE f.createtedAt = :createtedAt"),
    @NamedQuery(name = "Family.findByUpdatedAt", query = "SELECT f FROM Family f WHERE f.updatedAt = :updatedAt"),
    @NamedQuery(name = "Family.findByVersion", query = "SELECT f FROM Family f WHERE f.version = :version")})
public class Family implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FAMILY_ID")
    private Integer familyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "FAMILY_NAME")
    private String familyName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyId", fetch = FetchType.LAZY)
    private List<Income> incomeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyId", fetch = FetchType.LAZY)
    private List<MonthlyOutcome> monthlyOutcomeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familyId", fetch = FetchType.LAZY)
    private List<User> userList;

    public Family() {
    }

    public Family(Integer familyId) {
        this.familyId = familyId;
    }

    public Family(Integer familyId, String familyName, Date createtedAt, Date updatedAt, int version) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.createtedAt = createtedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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

    @XmlTransient
    public List<MonthlyOutcome> getMonthlyOutcomeList() {
        return monthlyOutcomeList;
    }

    public void setMonthlyOutcomeList(List<MonthlyOutcome> monthlyOutcomeList) {
        this.monthlyOutcomeList = monthlyOutcomeList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (familyId != null ? familyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Family)) {
            return false;
        }
        Family other = (Family) object;
        if ((this.familyId == null && other.familyId != null) || (this.familyId != null && !this.familyId.equals(other.familyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "am.ik.moneyger4u.domain.model.Family[ familyId=" + familyId + " ]";
    }
    
}
