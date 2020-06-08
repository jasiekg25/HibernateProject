package northwindEntities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "`order details`", schema = "db_project", catalog = "")
@IdClass(OrderDetailsEntityPK.class)
public class OrderDetailsEntity {
    private int orderId;
    private int productId;
    private BigDecimal unitPrice;
    private int quantity = 0;
    private double discount = 0;

    public String toString(){
        return Integer.toString(orderId) + "\t|\t" + Integer.toString(productId) + "\t|\t" + unitPrice.toString() + "\t|\t" + Integer.toString(quantity) + "\t|\t" + Double.toString(discount);
    }

    @Id
    @Column(name = "OrderID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "ProductID")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "UnitPrice")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "Quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Discount")
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsEntity that = (OrderDetailsEntity) o;
        return orderId == that.orderId &&
                productId == that.productId &&
                quantity == that.quantity &&
                Double.compare(that.discount, discount) == 0 &&
                Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, unitPrice, quantity, discount);
    }
}
