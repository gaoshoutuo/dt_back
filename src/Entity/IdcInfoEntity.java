package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "idc_info", schema = "dtsjwb", catalog = "")
public class IdcInfoEntity {
    private int id;
    private String idcId;
    private String idcName;
    private String idcType;
    private String userId;
    private String userName;
    private String idcLocationDeep;
    private String idcLocationSimple;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idc_id")
    public String getIdcId() {
        return idcId;
    }

    public void setIdcId(String idcId) {
        this.idcId = idcId;
    }

    @Basic
    @Column(name = "idc_name")
    public String getIdcName() {
        return idcName;
    }

    public void setIdcName(String idcName) {
        this.idcName = idcName;
    }

    @Basic
    @Column(name = "idc_type")
    public String getIdcType() {
        return idcType;
    }

    public void setIdcType(String idcType) {
        this.idcType = idcType;
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
    @Column(name = "idc_location_deep")
    public String getIdcLocationDeep() {
        return idcLocationDeep;
    }

    public void setIdcLocationDeep(String idcLocationDeep) {
        this.idcLocationDeep = idcLocationDeep;
    }

    @Basic
    @Column(name = "idc_location_simple")
    public String getIdcLocationSimple() {
        return idcLocationSimple;
    }

    public void setIdcLocationSimple(String idcLocationSimple) {
        this.idcLocationSimple = idcLocationSimple;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdcInfoEntity that = (IdcInfoEntity) o;
        return id == that.id &&
                Objects.equals(idcId, that.idcId) &&
                Objects.equals(idcName, that.idcName) &&
                Objects.equals(idcType, that.idcType) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(idcLocationDeep, that.idcLocationDeep) &&
                Objects.equals(idcLocationSimple, that.idcLocationSimple);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idcId, idcName, idcType, userId, userName, idcLocationDeep, idcLocationSimple);
    }
}
