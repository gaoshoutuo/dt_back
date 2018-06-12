package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "device_para", schema = "dtsjwb", catalog = "")
public class DeviceParaEntity {
    private int id;
    private String deviceName;
    private String deviceP;
    private String deviceT;
    private String deviceB;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "device_name")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "device_p")
    public String getDeviceP() {
        return deviceP;
    }

    public void setDeviceP(String deviceP) {
        this.deviceP = deviceP;
    }

    @Basic
    @Column(name = "device_t")
    public String getDeviceT() {
        return deviceT;
    }

    public void setDeviceT(String deviceT) {
        this.deviceT = deviceT;
    }

    @Basic
    @Column(name = "device_b")
    public String getDeviceB() {
        return deviceB;
    }

    public void setDeviceB(String deviceB) {
        this.deviceB = deviceB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceParaEntity that = (DeviceParaEntity) o;
        return id == that.id &&
                Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(deviceP, that.deviceP) &&
                Objects.equals(deviceT, that.deviceT) &&
                Objects.equals(deviceB, that.deviceB);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deviceName, deviceP, deviceT, deviceB);
    }
}
