package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "testtable", schema = "dtsjwb", catalog = "")
public class TesttableEntity {
    private int id;
    private String age;
    private String name;

    public TesttableEntity() {//居然是默认构造器的原因 坑...
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "age", nullable = false, length = 10)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesttableEntity that = (TesttableEntity) o;
        return id == that.id &&
                Objects.equals(age, that.age) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, age, name);
    }

    public TesttableEntity( String age, String name) {
        this.age = age;
        this.name = name;
    }

    public TesttableEntity(int id, String age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
