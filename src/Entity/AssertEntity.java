package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "assert", schema = "dtsjwb", catalog = "")
public class AssertEntity {
    private int id;
    private Timestamp dateHistory;
    private String json1;
    private String json2;
    private String json3;
    private String json4;
    private String cusId;
    private String location;
    private String cusName;
    private String company;

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
    @Column(name = "json_1")
    public String getJson1() {
        return json1;
    }

    public void setJson1(String json1) {
        this.json1 = json1;
    }

    @Basic
    @Column(name = "json_2")
    public String getJson2() {
        return json2;
    }

    public void setJson2(String json2) {
        this.json2 = json2;
    }

    @Basic
    @Column(name = "json_3")
    public String getJson3() {
        return json3;
    }

    public void setJson3(String json3) {
        this.json3 = json3;
    }

    @Basic
    @Column(name = "json_4")
    public String getJson4() {
        return json4;
    }

    public void setJson4(String json4) {
        this.json4 = json4;
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
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "cus_name")
    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssertEntity that = (AssertEntity) o;
        return id == that.id &&
                Objects.equals(dateHistory, that.dateHistory) &&
                Objects.equals(json1, that.json1) &&
                Objects.equals(json2, that.json2) &&
                Objects.equals(json3, that.json3) &&
                Objects.equals(json4, that.json4) &&
                Objects.equals(cusId, that.cusId) &&
                Objects.equals(location, that.location) &&
                Objects.equals(cusName, that.cusName) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateHistory, json1, json2, json3, json4, cusId, location, cusName, company);
    }
}
