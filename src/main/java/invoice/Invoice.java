package invoice;

import northwindEntities.CustomersEntity;
import northwindEntities.OrderDetailsEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;


import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Invoice {
    private int orderID;
    private Date orderDate;
    private String customerID;
    private String companyName;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private String shippingVia;
    private int productID;
    private String productName;
    private int quantity;
    private double totalPrice;


    public Invoice() {
    }

    public Invoice(
            int orderID,
            Date orderDate,
            String customerID,
            String companyName,
            String address,
            String city,
            String postalCode,
            String country,
            String shippingVia,
            int productID,
            String productName,
            int quantity,
            double totalPrice
    ){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.shippingVia = shippingVia;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Invoice invoice(Timestamp startDate,Timestamp endDate, CustomersEntity customer, Session session) {

        Query query = session
                .createQuery("select new invoice.Invoice( \n" +
                        "\t\to.orderId,\n" +
                        "\t\to.orderDate,\n" +
                        "\t\tc.customerId,\n" +
                        "\t\tc.companyName,\n" +
                        "\t\tc.address,\n" +
                        "\t\tc.city,\n" +
                        "\t\tc.postalCode,\n" +
                        "\t\tc.country,\n" +
                        "\t\ts.companyName as shippingVia,\n" +
                        "\t\tp.productId,\n" +
                        "\t\tp.productName,\n" +
                        "\t\tod.quantity,\n" +
                        "\t\tod.unitPrice * od.quantity * (1 - od.discount) as totalPrice )\n" +
                        "\tfrom ShippersEntity as s\n" +
                        "\tinner join  OrdersEntity as o on o.shipVia = s.shipperId\n" +
                        "\tinner join CustomersEntity as c on c.customerId = o.customerId\n" +
                        "\tinner join OrderDetailsEntity as od on od.orderId = o.orderId\n" +
                        "\tinner join ProductsEntity  as p on p.productId = od.productId\n" +
                        "\twhere c.customerId = \'"  + customer.getCustomerId() + "\' \n" +
//                        "\t \tand o.orderDate between \' 1995-04-04 00:00:00:0 \' \n " +
//                        "\t \tand  \' 1995-04-04 00:00:00 \' \n" +
                        "\t ");
//                .setResultTransformer(Transformers.aliasToBean(Invoice.class));
        List <Invoice> invoiceResultList = new LinkedList<>();
        for(Object o : query.list()){
            invoiceResultList.add((Invoice) o);
        }
//        @SuppressWarnings("unchecked") List <Invoice> invoiceResultList = (List<Invoice> ) query.list();



//        List orderDetailsList = session.createQuery("from CustomersEntity as c \n" +
//                "inner join OrdersEntity as o on c.customerId = o.customerId \n" +
//                "inner join OrderDetailsEntity as od on od.orderId = o.orderId \n" +
//                "where c.customerId = \'" + customer.getCustomerId() + "\'").getResultList();
//        List<CustomersEntity> list2 = session.createQuery("from CustomersEntity ").getResultList();

        System.out.println("Element: " + invoiceResultList.get(5).getCity());
        return invoiceResultList.get(5);
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

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getShippingVia() {
        return shippingVia;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setShippingVia(String shippingVia) {
        this.shippingVia = shippingVia;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
