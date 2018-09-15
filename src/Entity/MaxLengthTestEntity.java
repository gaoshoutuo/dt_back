package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "max_length_test", schema = "dtsjwb", catalog = "")
public class MaxLengthTestEntity {
    private int id;
    private String json1;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "json_1")
    public String getJson1() {
        return json1;
    }

    public void setJson1(String json1) {
        this.json1 = json1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaxLengthTestEntity that = (MaxLengthTestEntity) o;
        return id == that.id &&
                Objects.equals(json1, that.json1);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, json1);
    }
}
