package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mon_ac_system", schema = "dtsjwb", catalog = "")
public class MonAcSystemEntity {
    private int id;
    private String doubleAc;
    private String douMagnetic;
    private String sinMagnetic;
    private String cardReader;
    private String accessCard;
    private String acButton;
    private String acPower;
    private String acControl;
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
    @Column(name = "double_ac")
    public String getDoubleAc() {
        return doubleAc;
    }

    public void setDoubleAc(String doubleAc) {
        this.doubleAc = doubleAc;
    }

    @Basic
    @Column(name = "dou_magnetic")
    public String getDouMagnetic() {
        return douMagnetic;
    }

    public void setDouMagnetic(String douMagnetic) {
        this.douMagnetic = douMagnetic;
    }

    @Basic
    @Column(name = "sin_magnetic")
    public String getSinMagnetic() {
        return sinMagnetic;
    }

    public void setSinMagnetic(String sinMagnetic) {
        this.sinMagnetic = sinMagnetic;
    }

    @Basic
    @Column(name = "card_reader")
    public String getCardReader() {
        return cardReader;
    }

    public void setCardReader(String cardReader) {
        this.cardReader = cardReader;
    }

    @Basic
    @Column(name = "access_card")
    public String getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(String accessCard) {
        this.accessCard = accessCard;
    }

    @Basic
    @Column(name = "ac_button")
    public String getAcButton() {
        return acButton;
    }

    public void setAcButton(String acButton) {
        this.acButton = acButton;
    }

    @Basic
    @Column(name = "ac_power")
    public String getAcPower() {
        return acPower;
    }

    public void setAcPower(String acPower) {
        this.acPower = acPower;
    }

    @Basic
    @Column(name = "ac_control")
    public String getAcControl() {
        return acControl;
    }

    public void setAcControl(String acControl) {
        this.acControl = acControl;
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
        MonAcSystemEntity that = (MonAcSystemEntity) o;
        return id == that.id &&
                Objects.equals(doubleAc, that.doubleAc) &&
                Objects.equals(douMagnetic, that.douMagnetic) &&
                Objects.equals(sinMagnetic, that.sinMagnetic) &&
                Objects.equals(cardReader, that.cardReader) &&
                Objects.equals(accessCard, that.accessCard) &&
                Objects.equals(acButton, that.acButton) &&
                Objects.equals(acPower, that.acPower) &&
                Objects.equals(acControl, that.acControl) &&
                Objects.equals(idcNumber, that.idcNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, doubleAc, douMagnetic, sinMagnetic, cardReader, accessCard, acButton, acPower, acControl, idcNumber);
    }
}
