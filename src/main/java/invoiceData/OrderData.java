package invoiceData;

import northwindEntities.CustomersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderData {
    private int orderID;
    private Date orderDate;
    private String customerID;
    private int productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private double discount;
    private double freight;
    private double totalPrice;


    public OrderData() {
    }

    ;

    public OrderData(
            int orderID,
            Date orderDate,
            String customerID,
            int productID,
            String productName,
            BigDecimal unitPrice,
            int quantity,
            double discount,
            BigDecimal freight,
            double totalPrice
    ) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice.doubleValue();
        this.quantity = quantity;
        this.discount = discount;
        this.freight = freight.doubleValue();
        this.totalPrice = totalPrice;
    }



    public static List<OrderData> getOrdersList(Timestamp startDate, Timestamp endDate, CustomersEntity customer, Session session){
        @SuppressWarnings("rawtypes") Query query = session
                .createQuery("select new invoiceData.OrderData (\n" +
                        "\t\to.orderId,\n" +
                        "\t\to.orderDate,\n" +
                        "\t\tc.customerId,\n" +
                        "\t\tp.productId,\n" +
                        "\t\tp.productName,\n" +
                        "\t\tod.unitPrice,\n" +
                        "\t\tod.quantity,\n" +
                        "\t\tod.discount,\n" +
                        "\t\to.freight,\n" +
                        "\t\t(od.unitPrice * od.quantity * (1 - od.discount)) + o.freight as totalPrice )\n" +
                        "\tfrom ShippersEntity as s\n" +
                        "\tinner join  OrdersEntity as o on o.shipVia = s.shipperId\n" +
                        "\tinner join CustomersEntity as c on c.customerId = o.customerId\n" +
                        "\tinner join OrderDetailsEntity as od on od.orderId = o.orderId\n" +
                        "\tinner join ProductsEntity  as p on p.productId = od.productId\n" +
                        "\twhere c.customerId = \'" + customer.getCustomerId() + "\'\n" +
                        "\t \tand o.orderDate between  \'" + startDate + "\'\n" +
                        " \t \tand  \'" + endDate + "\'");

        List <OrderData> invoiceResultList = new LinkedList<>();
        for(Object o : query.list()){
            invoiceResultList.add((OrderData) o);
        }
        return invoiceResultList;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }


    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFreight() {
        return freight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
