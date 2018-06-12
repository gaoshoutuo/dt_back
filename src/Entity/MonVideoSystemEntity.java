package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mon_video_system", schema = "dtsjwb", catalog = "")
public class MonVideoSystemEntity {
    private int id;
    private String digitHemR;
    private String digitDiskR;
    private String hardDisk;
    private String monPower;
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
    @Column(name = "digit_hem_r")
    public String getDigitHemR() {
        return digitHemR;
    }

    public void setDigitHemR(String digitHemR) {
        this.digitHemR = digitHemR;
    }

    @Basic
    @Column(name = "digit_disk_r")
    public String getDigitDiskR() {
        return digitDiskR;
    }

    public void setDigitDiskR(String digitDiskR) {
        this.digitDiskR = digitDiskR;
    }

    @Basic
    @Column(name = "hard_disk")
    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Basic
    @Column(name = "mon_power")
    public String getMonPower() {
        return monPower;
    }

    public void setMonPower(String monPower) {
        this.monPower = monPower;
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
        MonVideoSystemEntity that = (MonVideoSystemEntity) o;
        return id == that.id &&
                Objects.equals(digitHemR, that.digitHemR) &&
                Objects.equals(digitDiskR, that.digitDiskR) &&
                Objects.equals(hardDisk, that.hardDisk) &&
                Objects.equals(monPower, that.monPower) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, digitHemR, digitDiskR, hardDisk, monPower, idcNumber);
    }
}
