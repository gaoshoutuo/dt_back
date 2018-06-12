package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mon_hard_system", schema = "dtsjwb", catalog = "")
public class MonHardSystemEntity {
    private int id;
    private String professionProcess;
    private String embeddedProcess;
    private String leakControl;
    private String temHumSensor;
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
    @Column(name = "profession_process")
    public String getProfessionProcess() {
        return professionProcess;
    }

    public void setProfessionProcess(String professionProcess) {
        this.professionProcess = professionProcess;
    }

    @Basic
    @Column(name = "embedded_process")
    public String getEmbeddedProcess() {
        return embeddedProcess;
    }

    public void setEmbeddedProcess(String embeddedProcess) {
        this.embeddedProcess = embeddedProcess;
    }

    @Basic
    @Column(name = "leak_control")
    public String getLeakControl() {
        return leakControl;
    }

    public void setLeakControl(String leakControl) {
        this.leakControl = leakControl;
    }

    @Basic
    @Column(name = "tem_hum_sensor")
    public String getTemHumSensor() {
        return temHumSensor;
    }

    public void setTemHumSensor(String temHumSensor) {
        this.temHumSensor = temHumSensor;
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
        MonHardSystemEntity that = (MonHardSystemEntity) o;
        return id == that.id &&
                Objects.equals(professionProcess, that.professionProcess) &&
                Objects.equals(embeddedProcess, that.embeddedProcess) &&
                Objects.equals(leakControl, that.leakControl) &&
                Objects.equals(temHumSensor, that.temHumSensor) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, professionProcess, embeddedProcess, leakControl, temHumSensor, idcNumber);
    }
}
