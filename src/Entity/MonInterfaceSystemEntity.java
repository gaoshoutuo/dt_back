package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mon_interface_system", schema = "dtsjwb", catalog = "")
public class MonInterfaceSystemEntity {
    private int id;
    private String eleSysSoft;
    private String leakSoft;
    private String temHumSoft;
    private String videoSoft;
    private String upsInterface;
    private String airconInterface;
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
    @Column(name = "ele_sys_soft")
    public String getEleSysSoft() {
        return eleSysSoft;
    }

    public void setEleSysSoft(String eleSysSoft) {
        this.eleSysSoft = eleSysSoft;
    }

    @Basic
    @Column(name = "leak_soft")
    public String getLeakSoft() {
        return leakSoft;
    }

    public void setLeakSoft(String leakSoft) {
        this.leakSoft = leakSoft;
    }

    @Basic
    @Column(name = "tem_hum_soft")
    public String getTemHumSoft() {
        return temHumSoft;
    }

    public void setTemHumSoft(String temHumSoft) {
        this.temHumSoft = temHumSoft;
    }

    @Basic
    @Column(name = "video_soft")
    public String getVideoSoft() {
        return videoSoft;
    }

    public void setVideoSoft(String videoSoft) {
        this.videoSoft = videoSoft;
    }

    @Basic
    @Column(name = "ups_interface")
    public String getUpsInterface() {
        return upsInterface;
    }

    public void setUpsInterface(String upsInterface) {
        this.upsInterface = upsInterface;
    }

    @Basic
    @Column(name = "aircon_interface")
    public String getAirconInterface() {
        return airconInterface;
    }

    public void setAirconInterface(String airconInterface) {
        this.airconInterface = airconInterface;
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
        MonInterfaceSystemEntity that = (MonInterfaceSystemEntity) o;
        return id == that.id &&
                Objects.equals(eleSysSoft, that.eleSysSoft) &&
                Objects.equals(leakSoft, that.leakSoft) &&
                Objects.equals(temHumSoft, that.temHumSoft) &&
                Objects.equals(videoSoft, that.videoSoft) &&
                Objects.equals(upsInterface, that.upsInterface) &&
                Objects.equals(airconInterface, that.airconInterface) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eleSysSoft, leakSoft, temHumSoft, videoSoft, upsInterface, airconInterface, idcNumber);
    }
}
