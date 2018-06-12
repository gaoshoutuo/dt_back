package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cabient_system", schema = "dtsjwb", catalog = "")
public class CabientSystemEntity {
    private int id;
    private String coldItCab;
    private String cabLine;
    private String cabVerticalLine;
    private String cabBottomLine;
    private String coldBackDoor;
    private String cabBlind;
    private String coldSunroof;
    private String sunroofEle;
    private String coldClose;
    private String coldCompont;
    private String coldLight;
    private String cabLight;
    private String eleCollection;
    private String cabHead;
    private String coldBase;
    private String idcNumber;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cold_it_cab")
    public String getColdItCab() {
        return coldItCab;
    }

    public void setColdItCab(String coldItCab) {
        this.coldItCab = coldItCab;
    }

    @Basic
    @Column(name = "cab_line")
    public String getCabLine() {
        return cabLine;
    }

    public void setCabLine(String cabLine) {
        this.cabLine = cabLine;
    }

    @Basic
    @Column(name = "cab_vertical_line")
    public String getCabVerticalLine() {
        return cabVerticalLine;
    }

    public void setCabVerticalLine(String cabVerticalLine) {
        this.cabVerticalLine = cabVerticalLine;
    }

    @Basic
    @Column(name = "cab_bottom_line")
    public String getCabBottomLine() {
        return cabBottomLine;
    }

    public void setCabBottomLine(String cabBottomLine) {
        this.cabBottomLine = cabBottomLine;
    }

    @Basic
    @Column(name = "cold_back_door")
    public String getColdBackDoor() {
        return coldBackDoor;
    }

    public void setColdBackDoor(String coldBackDoor) {
        this.coldBackDoor = coldBackDoor;
    }

    @Basic
    @Column(name = "cab_blind")
    public String getCabBlind() {
        return cabBlind;
    }

    public void setCabBlind(String cabBlind) {
        this.cabBlind = cabBlind;
    }

    @Basic
    @Column(name = "cold_sunroof")
    public String getColdSunroof() {
        return coldSunroof;
    }

    public void setColdSunroof(String coldSunroof) {
        this.coldSunroof = coldSunroof;
    }

    @Basic
    @Column(name = "sunroof_ele")
    public String getSunroofEle() {
        return sunroofEle;
    }

    public void setSunroofEle(String sunroofEle) {
        this.sunroofEle = sunroofEle;
    }

    @Basic
    @Column(name = "cold_close")
    public String getColdClose() {
        return coldClose;
    }

    public void setColdClose(String coldClose) {
        this.coldClose = coldClose;
    }

    @Basic
    @Column(name = "cold_compont")
    public String getColdCompont() {
        return coldCompont;
    }

    public void setColdCompont(String coldCompont) {
        this.coldCompont = coldCompont;
    }

    @Basic
    @Column(name = "cold_light")
    public String getColdLight() {
        return coldLight;
    }

    public void setColdLight(String coldLight) {
        this.coldLight = coldLight;
    }

    @Basic
    @Column(name = "cab_light")
    public String getCabLight() {
        return cabLight;
    }

    public void setCabLight(String cabLight) {
        this.cabLight = cabLight;
    }

    @Basic
    @Column(name = "ele_collection")
    public String getEleCollection() {
        return eleCollection;
    }

    public void setEleCollection(String eleCollection) {
        this.eleCollection = eleCollection;
    }

    @Basic
    @Column(name = "cab_head")
    public String getCabHead() {
        return cabHead;
    }

    public void setCabHead(String cabHead) {
        this.cabHead = cabHead;
    }

    @Basic
    @Column(name = "cold_base")
    public String getColdBase() {
        return coldBase;
    }

    public void setColdBase(String coldBase) {
        this.coldBase = coldBase;
    }

    @Basic
    @Column(name = "idc_number")
    public String getIdcNumber() {
        return idcNumber;
    }

    public void setIdcNumber(String idcNumber) {
        this.idcNumber = idcNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CabientSystemEntity that = (CabientSystemEntity) o;
        return id == that.id &&
                Objects.equals(coldItCab, that.coldItCab) &&
                Objects.equals(cabLine, that.cabLine) &&
                Objects.equals(cabVerticalLine, that.cabVerticalLine) &&
                Objects.equals(cabBottomLine, that.cabBottomLine) &&
                Objects.equals(coldBackDoor, that.coldBackDoor) &&
                Objects.equals(cabBlind, that.cabBlind) &&
                Objects.equals(coldSunroof, that.coldSunroof) &&
                Objects.equals(sunroofEle, that.sunroofEle) &&
                Objects.equals(coldClose, that.coldClose) &&
                Objects.equals(coldCompont, that.coldCompont) &&
                Objects.equals(coldLight, that.coldLight) &&
                Objects.equals(cabLight, that.cabLight) &&
                Objects.equals(eleCollection, that.eleCollection) &&
                Objects.equals(cabHead, that.cabHead) &&
                Objects.equals(coldBase, that.coldBase) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, coldItCab, cabLine, cabVerticalLine, cabBottomLine, coldBackDoor, cabBlind, coldSunroof, sunroofEle, coldClose, coldCompont, coldLight, cabLight, eleCollection, cabHead, coldBase, idcNumber);
    }
}
