package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "agree_table", schema = "dtsjwb", catalog = "")
public class AgreeTableEntity {
    private int id;
    private Timestamp dateHistory;
    private String cusId;
    private String engId;
    private String workType;
    private String isAgree;
    private String blank;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_history")
    public Timestamp getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(Timestamp dateHistory) {
        this.dateHistory = dateHistory;
    }

    @Basic
    @Column(name = "cus_id")
    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "eng_id")
    public String getEngId() {
        return engId;
    }

    public void setEngId(String engId) {
        this.engId = engId;
    }

    @Basic
    @Column(name = "work_type")
    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    @Basic
    @Column(name = "isAgree")
    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    @Basic
    @Column(name = "blank")
    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgreeTableEntity that = (AgreeTableEntity) o;
        return id == that.id &&
                Objects.equals(dateHistory, that.dateHistory) &&
                Objects.equals(cusId, that.cusId) &&
                Objects.equals(engId, that.engId) &&
                Objects.equals(workType, that.workType) &&
                Objects.equals(isAgree, that.isAgree) &&
                Objects.equals(blank, that.blank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateHistory, cusId, engId, workType, isAgree, blank);
    }

}
