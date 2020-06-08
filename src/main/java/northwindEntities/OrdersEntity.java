package northwindEntities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "db_project", catalog = "")
public class OrdersEntity {
    private int orderId;
    private String customerId;
    private Integer employeeId;
    private Timestamp orderDate;
    private Timestamp requiredDate;
    private Timestamp shippedDate;
    private Integer shipVia;
    private BigDecimal freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;


    public String toString(){
        return Integer.toString(orderId) + "\t|\t" + customerId + "\t|\t" + employeeId + "\t|\t" + orderDate;
    }

    @Id
    @Column(name = "OrderID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "EmployeeID")
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "OrderDate")
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "RequiredDate")
    public Timestamp getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
    }

    @Basic
    @Column(name = "ShippedDate")
    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    @Basic
    @Column(name = "ShipVia")
    public Integer getShipVia() {
        return shipVia;
    }

    public void setShipVia(Integer shipVia) {
        this.shipVia = shipVia;
    }

    @Basic
    @Column(name = "Freight")
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    @Basic
    @Column(name = "ShipName")
    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @Basic
    @Column(name = "ShipAddress")
    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    @Basic
    @Column(name = "ShipCity")
    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    @Basic
    @Column(name = "ShipRegion")
    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    @Basic
    @Column(name = "ShipPostalCode")
    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    @Basic
    @Column(name = "ShipCountry")
    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return orderId == that.orderId &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(requiredDate, that.requiredDate) &&
                Objects.equals(shippedDate, that.shippedDate) &&
                Objects.equals(shipVia, that.shipVia) &&
                Objects.equals(freight, that.freight) &&
                Objects.equals(shipName, that.shipName) &&
                Objects.equals(shipAddress, that.shipAddress) &&
                Objects.equals(shipCity, that.shipCity) &&
                Objects.equals(shipRegion, that.shipRegion) &&
                Objects.equals(shipPostalCode, that.shipPostalCode) &&
                Objects.equals(shipCountry, that.shipCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, employeeId, orderDate, requiredDate, shippedDate, shipVia, freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry);
    }
}
