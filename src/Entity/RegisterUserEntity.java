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
    private String location;
    private String company;
    private String industry;
    private String email;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "identity")
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
                Objects.equals(identity, that.identity) &&
                Objects.equals(location, that.location) &&
                Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, userId, pwd, identity, id, location, company);
    }

    @Basic
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
