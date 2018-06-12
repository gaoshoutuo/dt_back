package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mon_soft_system", schema = "dtsjwb", catalog = "")
public class MonSoftSystemEntity {
    private int id;
    private String idcMonitor;
    private String ieRemote;
    private String messageWarn;
    private String emailWarn;
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
    @Column(name = "idc_monitor")
    public String getIdcMonitor() {
        return idcMonitor;
    }

    public void setIdcMonitor(String idcMonitor) {
        this.idcMonitor = idcMonitor;
    }

    @Basic
    @Column(name = "ie_remote")
    public String getIeRemote() {
        return ieRemote;
    }

    public void setIeRemote(String ieRemote) {
        this.ieRemote = ieRemote;
    }

    @Basic
    @Column(name = "message_warn")
    public String getMessageWarn() {
        return messageWarn;
    }

    public void setMessageWarn(String messageWarn) {
        this.messageWarn = messageWarn;
    }

    @Basic
    @Column(name = "email_warn")
    public String getEmailWarn() {
        return emailWarn;
    }

    public void setEmailWarn(String emailWarn) {
        this.emailWarn = emailWarn;
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
        MonSoftSystemEntity that = (MonSoftSystemEntity) o;
        return id == that.id &&
                Objects.equals(idcMonitor, that.idcMonitor) &&
                Objects.equals(ieRemote, that.ieRemote) &&
                Objects.equals(messageWarn, that.messageWarn) &&
                Objects.equals(emailWarn, that.emailWarn) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idcMonitor, ieRemote, messageWarn, emailWarn, idcNumber);
    }
}
