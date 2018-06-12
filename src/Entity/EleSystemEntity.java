package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ele_system", schema = "dtsjwb", catalog = "")
public class EleSystemEntity {
    private int id;
    private String frameUps;
    private String powerUnit;
    private String battery;
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
    @Column(name = "frame_ups")
    public String getFrameUps() {
        return frameUps;
    }

    public void setFrameUps(String frameUps) {
        this.frameUps = frameUps;
    }

    @Basic
    @Column(name = "power_unit")
    public String getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(String powerUnit) {
        this.powerUnit = powerUnit;
    }

    @Basic
    @Column(name = "battery")
    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
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
        EleSystemEntity that = (EleSystemEntity) o;
        return id == that.id &&
                Objects.equals(frameUps, that.frameUps) &&
                Objects.equals(powerUnit, that.powerUnit) &&
                Objects.equals(battery, that.battery) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, frameUps, powerUnit, battery, idcNumber);
    }
}
