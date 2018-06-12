package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "air_system", schema = "dtsjwb", catalog = "")
public class AirSystemEntity {
    private int id;
    private String ecAirCon;
    private String ecCuTube;
    private String acAirCon;
    private String acCuTube;
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
    @Column(name = "ec_air_con")
    public String getEcAirCon() {
        return ecAirCon;
    }

    public void setEcAirCon(String ecAirCon) {
        this.ecAirCon = ecAirCon;
    }

    @Basic
    @Column(name = "ec_cu_tube")
    public String getEcCuTube() {
        return ecCuTube;
    }

    public void setEcCuTube(String ecCuTube) {
        this.ecCuTube = ecCuTube;
    }

    @Basic
    @Column(name = "ac_air_con")
    public String getAcAirCon() {
        return acAirCon;
    }

    public void setAcAirCon(String acAirCon) {
        this.acAirCon = acAirCon;
    }

    @Basic
    @Column(name = "ac_cu_tube")
    public String getAcCuTube() {
        return acCuTube;
    }

    public void setAcCuTube(String acCuTube) {
        this.acCuTube = acCuTube;
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
        AirSystemEntity that = (AirSystemEntity) o;
        return id == that.id &&
                Objects.equals(ecAirCon, that.ecAirCon) &&
                Objects.equals(ecCuTube, that.ecCuTube) &&
                Objects.equals(acAirCon, that.acAirCon) &&
                Objects.equals(acCuTube, that.acCuTube) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, ecAirCon, ecCuTube, acAirCon, acCuTube, idcNumber);
    }
}
