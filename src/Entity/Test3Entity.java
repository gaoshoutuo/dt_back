package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "test3", schema = "dtsjwb", catalog = "")
public class Test3Entity {
    private int id;
    private String data;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "data", nullable = false, length = 20)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test3Entity that = (Test3Entity) o;
        return id == that.id &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, data);
    }
}
