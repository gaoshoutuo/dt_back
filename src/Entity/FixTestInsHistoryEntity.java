package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fix_test_ins_history", schema = "dtsjwb", catalog = "")
public class FixTestInsHistoryEntity {
    private int id;
    private Timestamp dateHistory;
    private String json1;
    private String json2;
    private String textHistory;
    private String business;
    private String filepath;
    private String userId;
    private String human;
    private String engId;

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
    @Column(name = "json_1")
    public String getJson1() {
        return json1;
    }

    public void setJson1(String json1) {
        this.json1 = json1;
    }

    @Basic
    @Column(name = "json_2")
    public String getJson2() {
        return json2;
    }

    public void setJson2(String json2) {
        this.json2 = json2;
    }

    @Basic
    @Column(name = "text_history")
    public String getTextHistory() {
        return textHistory;
    }

    public void setTextHistory(String textHistory) {
        this.textHistory = textHistory;
    }

    @Basic
    @Column(name = "business")
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Basic
    @Column(name = "filepath")
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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
    @Column(name = "human")
    public String getHuman() {
        return human;
    }

    public void setHuman(String human) {
        this.human = human;
    }

    @Basic
    @Column(name = "eng_id")
    public String getEngId() {
        return engId;
    }

    public void setEngId(String engId) {
        this.engId = engId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixTestInsHistoryEntity that = (FixTestInsHistoryEntity) o;
        return id == that.id &&
                Objects.equals(dateHistory, that.dateHistory) &&
                Objects.equals(json1, that.json1) &&
                Objects.equals(json2, that.json2) &&
                Objects.equals(textHistory, that.textHistory) &&
                Objects.equals(business, that.business) &&
                Objects.equals(filepath, that.filepath) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(human, that.human) &&
                Objects.equals(engId, that.engId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateHistory, json1, json2, textHistory, business, filepath, userId, human, engId);
    }

    public FixTestInsHistoryEntity() {
    }

    public FixTestInsHistoryEntity(Timestamp dateHistory, String textHistory, String business, String filepath, String human) {
        super();
        this.dateHistory = dateHistory;
        this.textHistory = textHistory;
        this.business = business;
        this.filepath = filepath;
        this.human = human;
    }
    public FixTestInsHistoryEntity( String textHistory, String business, String filepath, String human) {
        super();
        this.dateHistory = dateHistory;
        this.textHistory = textHistory;
        this.business = business;
        this.filepath = filepath;
        this.human = human;
    }
}
