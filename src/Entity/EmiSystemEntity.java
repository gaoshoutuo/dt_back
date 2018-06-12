package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "emi_system", schema = "dtsjwb", catalog = "")
public class EmiSystemEntity {
    private int id;
    private String fanProcessor;
    private String axisFlow;
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
    @Column(name = "fan_processor")
    public String getFanProcessor() {
        return fanProcessor;
    }

    public void setFanProcessor(String fanProcessor) {
        this.fanProcessor = fanProcessor;
    }

    @Basic
    @Column(name = "axis_flow")
    public String getAxisFlow() {
        return axisFlow;
    }

    public void setAxisFlow(String axisFlow) {
        this.axisFlow = axisFlow;
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
        EmiSystemEntity that = (EmiSystemEntity) o;
        return id == that.id &&
                Objects.equals(fanProcessor, that.fanProcessor) &&
                Objects.equals(axisFlow, that.axisFlow) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fanProcessor, axisFlow, idcNumber);
    }
}
