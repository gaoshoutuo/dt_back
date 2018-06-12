package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "register_user", schema = "dtsjwb", catalog = "")
public class RegisterUserEntity {
    private String name;
    private String userId;
    private String pwd;
    private String identity;
    private int id;

    public RegisterUserEntity() {
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "pwd", nullable = false, length = 20)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "identity", nullable = false, length = 20)
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterUserEntity that = (RegisterUserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(pwd, that.pwd) &&
                Objects.equals(identity, that.identity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, userId, pwd, identity, id);
    }
}
