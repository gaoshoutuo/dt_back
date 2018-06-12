package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "custom_info", schema = "dtsjwb", catalog = "")
public class CustomInfoEntity {
    private String customId;
    private String comName;
    private String location;
    private String password;
    private String identity;

    @Id
    @Column(name = "custom_id")
    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    @Basic
    @Column(name = "com_name")
    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "identity")
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomInfoEntity that = (CustomInfoEntity) o;
        return Objects.equals(customId, that.customId) &&
                Objects.equals(comName, that.comName) &&
                Objects.equals(location, that.location) &&
                Objects.equals(password, that.password) &&
                Objects.equals(identity, that.identity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customId, comName, location, password, identity);
    }
}
