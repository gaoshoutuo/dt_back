package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ups_air_any", schema = "dtsjwb", catalog = "")
public class UpsAirAnyEntity {
    private int id;
    private String idcId;
    private String idcName;
    private String idcType;
    private String idcLocationSimple;
    private String jsonData;
    private String cusId;
    private String deviceType;
    private String blank1;
    private String blank2;
    private String blank3;
    private String blank4;

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
    @Column(name = "cus_id")
    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Basic
    @Column(name = "device_type")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    @Basic
    @Column(name = "blank_4")
    public String getBlank4() {
        return blank4;
    }

    public void setBlank4(String blank4) {
        this.blank4 = blank4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpsAirAnyEntity that = (UpsAirAnyEntity) o;
        return id == that.id &&
                Objects.equals(idcId, that.idcId) &&
                Objects.equals(idcName, that.idcName) &&
                Objects.equals(idcType, that.idcType) &&
                Objects.equals(idcLocationSimple, that.idcLocationSimple) &&
                Objects.equals(jsonData, that.jsonData) &&
                Objects.equals(cusId, that.cusId) &&
                Objects.equals(deviceType, that.deviceType) &&
                Objects.equals(blank1, that.blank1) &&
                Objects.equals(blank2, that.blank2) &&
                Objects.equals(blank3, that.blank3) &&
                Objects.equals(blank4, that.blank4);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idcId, idcName, idcType, idcLocationSimple, jsonData, cusId, deviceType, blank1, blank2, blank3, blank4);
    }
}
