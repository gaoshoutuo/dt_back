package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "idc_asset", schema = "dtsjwb", catalog = "")
public class IdcAssetEntity {
    private int id;
    private String idcId;
    private String idcType;
    private String userId;
    private String idcLocationSimple;
    private String upsAsset;
    private String airAssit;
    private String emiAssit;
    private String monSoftAssit;
    private String monSoftInterface;
    private String monSoftHardware;
    private String monSoftAc;
    private String monVideo;
    private String monCabient;
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
    @Column(name = "idc_id")
    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
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
    @Column(name = "idc_location_simple")
    public String getIdcLocationSimple() {
        return idcLocationSimple;
    }

    public void setIdcLocationSimple(String idcLocationSimple) {
        this.idcLocationSimple = idcLocationSimple;
    }

    @Basic
    @Column(name = "ups_asset")
    public String getUpsAsset() {
        return upsAsset;
    }

    public void setUpsAsset(String upsAsset) {
        this.upsAsset = upsAsset;
    }

    @Basic
    @Column(name = "air_assit")
    public String getAirAssit() {
        return airAssit;
    }

    public void setAirAssit(String airAssit) {
        this.airAssit = airAssit;
    }

    @Basic
    @Column(name = "emi_assit")
    public String getEmiAssit() {
        return emiAssit;
    }

    public void setEmiAssit(String emiAssit) {
        this.emiAssit = emiAssit;
    }

    @Basic
    @Column(name = "mon_soft_assit")
    public String getMonSoftAssit() {
        return monSoftAssit;
    }

    public void setMonSoftAssit(String monSoftAssit) {
        this.monSoftAssit = monSoftAssit;
    }

    @Basic
    @Column(name = "mon_soft_interface")
    public String getMonSoftInterface() {
        return monSoftInterface;
    }

    public void setMonSoftInterface(String monSoftInterface) {
        this.monSoftInterface = monSoftInterface;
    }

    @Basic
    @Column(name = "mon_soft_hardware")
    public String getMonSoftHardware() {
        return monSoftHardware;
    }

    public void setMonSoftHardware(String monSoftHardware) {
        this.monSoftHardware = monSoftHardware;
    }

    @Basic
    @Column(name = "mon_soft_ac")
    public String getMonSoftAc() {
        return monSoftAc;
    }

    public void setMonSoftAc(String monSoftAc) {
        this.monSoftAc = monSoftAc;
    }

    @Basic
    @Column(name = "mon_video")
    public String getMonVideo() {
        return monVideo;
    }

    public void setMonVideo(String monVideo) {
        this.monVideo = monVideo;
    }

    @Basic
    @Column(name = "mon_cabient")
    public String getMonCabient() {
        return monCabient;
    }

    public void setMonCabient(String monCabient) {
        this.monCabient = monCabient;
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
        IdcAssetEntity that = (IdcAssetEntity) o;
        return id == that.id &&
                Objects.equals(idcId, that.idcId) &&
                Objects.equals(idcType, that.idcType) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(idcLocationSimple, that.idcLocationSimple) &&
                Objects.equals(upsAsset, that.upsAsset) &&
                Objects.equals(airAssit, that.airAssit) &&
                Objects.equals(emiAssit, that.emiAssit) &&
                Objects.equals(monSoftAssit, that.monSoftAssit) &&
                Objects.equals(monSoftInterface, that.monSoftInterface) &&
                Objects.equals(monSoftHardware, that.monSoftHardware) &&
                Objects.equals(monSoftAc, that.monSoftAc) &&
                Objects.equals(monVideo, that.monVideo) &&
                Objects.equals(monCabient, that.monCabient) &&
                Objects.equals(blank1, that.blank1) &&
                Objects.equals(blank2, that.blank2) &&
                Objects.equals(blank3, that.blank3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idcId, idcType, userId, idcLocationSimple, upsAsset, airAssit, emiAssit, monSoftAssit, monSoftInterface, monSoftHardware, monSoftAc, monVideo, monCabient, blank1, blank2, blank3);
    }
}
