package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fix_message", schema = "dtsjwb", catalog = "")
public class FixMessageEntity {
    private int id;
    private String timeRecord;
    private String json1;
    private String json2;
    private String idcId;
    private String idcName;
    private String idcLocation;
    private String idcType;
    private String userId;
    private String engId;
    private String businessType;
    private String isWatch;
    private String engName;
    private String blank1;
    private String blank2;
    private String blank3;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_record")
    public String getTimeRecord() {
        return timeRecord;
    }

    public void setTimeRecord(String timeRecord) {
        this.timeRecord = timeRecord;
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
    @Column(name = "idc_id")
    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }

    @Basic
    @Column(name = "idc_name")
    public String getIdcName() {
        return idcName;
    }

    public void setIdcName(String idcName) {
        this.idcName = idcName;
    }

    @Basic
    @Column(name = "idc_location")
    public String getIdcLocation() {
        return idcLocation;
    }

    public void setIdcLocation(String idcLocation) {
        this.idcLocation = idcLocation;
    }

    @Basic
    @Column(name = "idc_type")
    public String getIdcType() {
        return idcType;
    }

    public void setIdcType(String idcType) {
        this.idcType = idcType;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    @Column(name = "business_type")
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "is_watch")
    public String getIsWatch() {
        return isWatch;
    }

    public void setIsWatch(String isWatch) {
        this.isWatch = isWatch;
    }

    @Basic
    @Column(name = "eng_name")
    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    @Basic
    @Column(name = "blank_1")
    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1;
    }

    @Basic
    @Column(name = "blank_2")
    public String getBlank2() {
        return blank2;
    }

    public void setBlank2(String blank2) {
        this.blank2 = blank2;
    }

    @Basic
    @Column(name = "blank_3")
    public String getBlank3() {
        return blank3;
    }

    public void setBlank3(String blank3) {
        this.blank3 = blank3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixMessageEntity that = (FixMessageEntity) o;
        return id == that.id &&
                Objects.equals(timeRecord, that.timeRecord) &&
                Objects.equals(json1, that.json1) &&
                Objects.equals(json2, that.json2) &&
                Objects.equals(idcId, that.idcId) &&
                Objects.equals(idcName, that.idcName) &&
                Objects.equals(idcLocation, that.idcLocation) &&
                Objects.equals(idcType, that.idcType) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(engId, that.engId) &&
                Objects.equals(businessType, that.businessType) &&
                Objects.equals(isWatch, that.isWatch) &&
                Objects.equals(engName, that.engName) &&
                Objects.equals(blank1, that.blank1) &&
                Objects.equals(blank2, that.blank2) &&
                Objects.equals(blank3, that.blank3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, timeRecord, json1, json2, idcId, idcName, idcLocation, idcType, userId, engId, businessType, isWatch, engName, blank1, blank2, blank3);
    }
}
