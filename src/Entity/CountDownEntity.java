package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "count_down", schema = "dtsjwb", catalog = "")
public class CountDownEntity {
    private int id;
    private String engId;
    private String userId;
    private String userName;
    private String idcId;
    private String idcName;
    private String idcLocation;
    private String idcType;
    private Integer upsTime;
    private Integer airTime;
    private Integer anotherTime1;
    private Integer anotherTime2;
    private Integer anotherTime3;
    private Integer anotherTime4;
    private Integer anotherTime5;
    private Integer anotherTime6;
    private Integer anotherTime7;
    private Integer flagSet;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    @Column(name = "ups_time")
    public Integer getUpsTime() {
        return upsTime;
    }

    public void setUpsTime(Integer upsTime) {
        this.upsTime = upsTime;
    }

    @Basic
    @Column(name = "air_time")
    public Integer getAirTime() {
        return airTime;
    }

    public void setAirTime(Integer airTime) {
        this.airTime = airTime;
    }

    @Basic
    @Column(name = "another_time_1")
    public Integer getAnotherTime1() {
        return anotherTime1;
    }

    public void setAnotherTime1(Integer anotherTime1) {
        this.anotherTime1 = anotherTime1;
    }

    @Basic
    @Column(name = "another_time_2")
    public Integer getAnotherTime2() {
        return anotherTime2;
    }

    public void setAnotherTime2(Integer anotherTime2) {
        this.anotherTime2 = anotherTime2;
    }

    @Basic
    @Column(name = "another_time_3")
    public Integer getAnotherTime3() {
        return anotherTime3;
    }

    public void setAnotherTime3(Integer anotherTime3) {
        this.anotherTime3 = anotherTime3;
    }

    @Basic
    @Column(name = "another_time_4")
    public Integer getAnotherTime4() {
        return anotherTime4;
    }

    public void setAnotherTime4(Integer anotherTime4) {
        this.anotherTime4 = anotherTime4;
    }

    @Basic
    @Column(name = "another_time_5")
    public Integer getAnotherTime5() {
        return anotherTime5;
    }

    public void setAnotherTime5(Integer anotherTime5) {
        this.anotherTime5 = anotherTime5;
    }

    @Basic
    @Column(name = "another_time_6")
    public Integer getAnotherTime6() {
        return anotherTime6;
    }

    public void setAnotherTime6(Integer anotherTime6) {
        this.anotherTime6 = anotherTime6;
    }

    @Basic
    @Column(name = "another_time_7")
    public Integer getAnotherTime7() {
        return anotherTime7;
    }

    public void setAnotherTime7(Integer anotherTime7) {
        this.anotherTime7 = anotherTime7;
    }

    @Basic
    @Column(name = "flag_set")
    public Integer getFlagSet() {
        return flagSet;
    }

    public void setFlagSet(Integer flagSet) {
        this.flagSet = flagSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountDownEntity that = (CountDownEntity) o;
        return id == that.id &&
                Objects.equals(engId, that.engId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(idcId, that.idcId) &&
                Objects.equals(idcName, that.idcName) &&
                Objects.equals(idcLocation, that.idcLocation) &&
                Objects.equals(idcType, that.idcType) &&
                Objects.equals(upsTime, that.upsTime) &&
                Objects.equals(airTime, that.airTime) &&
                Objects.equals(anotherTime1, that.anotherTime1) &&
                Objects.equals(anotherTime2, that.anotherTime2) &&
                Objects.equals(anotherTime3, that.anotherTime3) &&
                Objects.equals(anotherTime4, that.anotherTime4) &&
                Objects.equals(anotherTime5, that.anotherTime5) &&
                Objects.equals(anotherTime6, that.anotherTime6) &&
                Objects.equals(anotherTime7, that.anotherTime7) &&
                Objects.equals(flagSet, that.flagSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, engId, userId, userName, idcId, idcName, idcLocation, idcType, upsTime, airTime, anotherTime1, anotherTime2, anotherTime3, anotherTime4, anotherTime5, anotherTime6, anotherTime7, flagSet);
    }
}
