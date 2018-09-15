package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "info_ups_air", schema = "dtsjwb", catalog = "")
public class InfoUpsAirEntity {
    private int id;
    private String idcId;
    private String idcName;
    private String idcType;
    private String idcLocationSimple;
    private String jsonData;
    private String userId;
    private String userName;
    private String cusId;
    private Integer upsNumber;
    private Integer airNumber;
    private Integer emiNumber;
    private Integer msNumber;
    private Integer miNumber;
    private Integer mhNumber;
    private Integer macNumber;
    private Integer mvNumber;
    private Integer cabNumber;
    private String blank1;
    private String blank2;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "idc_type")
    public String getIdcType() {
        return idcType;
    }

    public void setIdcType(String idcType) {
        this.idcType = idcType;
    }

    @Basic
    @Column(name = "idc_location_simple")
    public String getIdcLocationSimple() {
        return idcLocationSimple;
    }

    public void setIdcLocationSimple(String idcLocationSimple) {
        this.idcLocationSimple = idcLocationSimple;
    }

    @Basic
    @Column(name = "json_data")
    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
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
    @Column(name = "cus_id")
    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "ups_number")
    public Integer getUpsNumber() {
        return upsNumber;
    }

    public void setUpsNumber(Integer upsNumber) {
        this.upsNumber = upsNumber;
    }

    @Basic
    @Column(name = "air_number")
    public Integer getAirNumber() {
        return airNumber;
    }

    public void setAirNumber(Integer airNumber) {
        this.airNumber = airNumber;
    }

    @Basic
    @Column(name = "emi_number")
    public Integer getEmiNumber() {
        return emiNumber;
    }

    public void setEmiNumber(Integer emiNumber) {
        this.emiNumber = emiNumber;
    }

    @Basic
    @Column(name = "ms_number")
    public Integer getMsNumber() {
        return msNumber;
    }

    public void setMsNumber(Integer msNumber) {
        this.msNumber = msNumber;
    }

    @Basic
    @Column(name = "mi_number")
    public Integer getMiNumber() {
        return miNumber;
    }

    public void setMiNumber(Integer miNumber) {
        this.miNumber = miNumber;
    }

    @Basic
    @Column(name = "mh_number")
    public Integer getMhNumber() {
        return mhNumber;
    }

    public void setMhNumber(Integer mhNumber) {
        this.mhNumber = mhNumber;
    }

    @Basic
    @Column(name = "mac_number")
    public Integer getMacNumber() {
        return macNumber;
    }

    public void setMacNumber(Integer macNumber) {
        this.macNumber = macNumber;
    }

    @Basic
    @Column(name = "mv_number")
    public Integer getMvNumber() {
        return mvNumber;
    }

    public void setMvNumber(Integer mvNumber) {
        this.mvNumber = mvNumber;
    }

    @Basic
    @Column(name = "cab_number")
    public Integer getCabNumber() {
        return cabNumber;
    }

    public void setCabNumber(Integer cabNumber) {
        this.cabNumber = cabNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoUpsAirEntity that = (InfoUpsAirEntity) o;
        return id == that.id &&
                Objects.equals(idcId, that.idcId) &&
                Objects.equals(idcName, that.idcName) &&
                Objects.equals(idcType, that.idcType) &&
                Objects.equals(idcLocationSimple, that.idcLocationSimple) &&
                Objects.equals(jsonData, that.jsonData) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(cusId, that.cusId) &&
                Objects.equals(upsNumber, that.upsNumber) &&
                Objects.equals(airNumber, that.airNumber) &&
                Objects.equals(emiNumber, that.emiNumber) &&
                Objects.equals(msNumber, that.msNumber) &&
                Objects.equals(miNumber, that.miNumber) &&
                Objects.equals(mhNumber, that.mhNumber) &&
                Objects.equals(macNumber, that.macNumber) &&
                Objects.equals(mvNumber, that.mvNumber) &&
                Objects.equals(cabNumber, that.cabNumber) &&
                Objects.equals(blank1, that.blank1) &&
                Objects.equals(blank2, that.blank2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idcId, idcName, idcType, idcLocationSimple, jsonData, userId, userName, cusId, upsNumber, airNumber, emiNumber, msNumber, miNumber, mhNumber, macNumber, mvNumber, cabNumber, blank1, blank2);
    }
}
