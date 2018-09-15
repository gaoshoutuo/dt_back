package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "out_length_eight_row_table", schema = "dtsjwb", catalog = "")
public class OutLengthEightRowTableEntity {
    private int id;
    private String json88888888;
    private Timestamp timeH;
    private Integer rank;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "json_88888888")
    public String getJson88888888() {
        return json88888888;
    }

    public void setJson88888888(String json88888888) {
        this.json88888888 = json88888888;
    }

    @Basic
    @Column(name = "time_h")
    public Timestamp getTimeH() {
        return timeH;
    }

    public void setTimeH(Timestamp timeH) {
        this.timeH = timeH;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutLengthEightRowTableEntity that = (OutLengthEightRowTableEntity) o;
        return id == that.id &&
                Objects.equals(json88888888, that.json88888888) &&
                Objects.equals(timeH, that.timeH) &&
                Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, json88888888, timeH, rank);
    }
}
