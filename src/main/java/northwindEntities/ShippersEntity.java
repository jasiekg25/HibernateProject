package northwindEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shippers", schema = "db_project", catalog = "")
public class ShippersEntity {
    private int shipperId;
    private String companyName;
    private String phone;

    @Id
    @Column(name = "ShipperID")
    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    @Basic
    @Column(name = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippersEntity that = (ShippersEntity) o;
        return shipperId == that.shipperId &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipperId, companyName, phone);
    }
}
