package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hot_json", schema = "dtsjwb", catalog = "")
public class HotJsonEntity {
    private int id;
    private String hotjson1;
    private String hotjson2;
    private String hotjson3;
    private String hotjson4;
    private String hotjson5;
    private String userId;
    private String userName;
    private String blank1;
    private String blank2;
    private String blank3;
    private String blank4;
    private String blank5;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hotjson1")
    public String getHotjson1() {
        return hotjson1;
    }

    public void setHotjson1(String hotjson1) {
        this.hotjson1 = hotjson1;
    }

    @Basic
    @Column(name = "hotjson2")
    public String getHotjson2() {
        return hotjson2;
    }

    public void setHotjson2(String hotjson2) {
        this.hotjson2 = hotjson2;
    }

    @Basic
    @Column(name = "hotjson3")
    public String getHotjson3() {
        return hotjson3;
    }

    public void setHotjson3(String hotjson3) {
        this.hotjson3 = hotjson3;
    }

    @Basic
    @Column(name = "hotjson4")
    public String getHotjson4() {
        return hotjson4;
    }

    public void setHotjson4(String hotjson4) {
        this.hotjson4 = hotjson4;
    }

    @Basic
    @Column(name = "hotjson5")
    public String getHotjson5() {
        return hotjson5;
    }

    public void setHotjson5(String hotjson5) {
        this.hotjson5 = hotjson5;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "blank_1")
    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1;
    }

    @Basic
    @Column(name = "blank_2")
    public String getBlank2() {
        return blank2;
    }

    public void setBlank2(String blank2) {
        this.blank2 = blank2;
    }

    @Basic
    @Column(name = "blank_3")
    public String getBlank3() {
        return blank3;
    }

    public void setBlank3(String blank3) {
        this.blank3 = blank3;
    }

    @Basic
    @Column(name = "blank_4")
    public String getBlank4() {
        return blank4;
    }

    public void setBlank4(String blank4) {
        this.blank4 = blank4;
    }

    @Basic
    @Column(name = "blank_5")
    public String getBlank5() {
        return blank5;
    }

    public void setBlank5(String blank5) {
        this.blank5 = blank5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotJsonEntity that = (HotJsonEntity) o;
        return id == that.id &&
                Objects.equals(hotjson1, that.hotjson1) &&
                Objects.equals(hotjson2, that.hotjson2) &&
                Objects.equals(hotjson3, that.hotjson3) &&
                Objects.equals(hotjson4, that.hotjson4) &&
                Objects.equals(hotjson5, that.hotjson5) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(blank1, that.blank1) &&
                Objects.equals(blank2, that.blank2) &&
                Objects.equals(blank3, that.blank3) &&
                Objects.equals(blank4, that.blank4) &&
                Objects.equals(blank5, that.blank5);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, hotjson1, hotjson2, hotjson3, hotjson4, hotjson5, userId, userName, blank1, blank2, blank3, blank4, blank5);
    }
}
