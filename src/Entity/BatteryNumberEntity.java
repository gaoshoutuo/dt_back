package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "battery_number", schema = "dtsjwb", catalog = "")
public class BatteryNumberEntity {
    private int id;
    private Timestamp dateHistory;
    private String cusId;
    private int battNumber;
    private String idcId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_history")
    public Timestamp getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(Timestamp dateHistory) {
        this.dateHistory = dateHistory;
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
    @Column(name = "batt_number")
    public int getBattNumber() {
        return battNumber;
    }

    public void setBattNumber(int battNumber) {
        this.battNumber = battNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatteryNumberEntity that = (BatteryNumberEntity) o;
        return id == that.id &&
                battNumber == that.battNumber &&
                Objects.equals(dateHistory, that.dateHistory) &&
                Objects.equals(cusId, that.cusId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateHistory, cusId, battNumber);
    }

    @Basic
    @Column(name = "idc_id")
    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }
}
