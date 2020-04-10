package invoiceData;

import invoiceGenerator.InvoiceGenerator;
import northwindEntities.CustomersEntity;
import org.hibernate.Session;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class Invoice {
    private String invoiceNumber;
    private CustomersEntity customer;
    private List<OrderData> ordersList;
    private Date startDate;
    private Date endDate;
    private double totalFreightPrice;
    private double totalOrdersPrice;


    public Invoice() {
    }

    public Invoice(
            String invoiceNumber,
            CustomersEntity customer,
            List<OrderData> ordersList,
            Date startDate,
            Date endDate,
            double totalFreightPrice,
            double totalOrdersPrice
    ) {
        this.invoiceNumber = invoiceNumber;
        this.customer = customer;
        this.ordersList = ordersList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalFreightPrice = totalFreightPrice;
        this.totalOrdersPrice = totalOrdersPrice;
    }


    public Invoice invoice(String invoiceNumber, Timestamp startDate, Timestamp endDate, CustomersEntity customer, Session session) {

        List<OrderData> invoiceOrdersList = OrderData.getOrdersList(startDate, endDate, customer, session);
        double totalFreightPrice = invoiceOrdersList.stream().map(OrderData::getFreight).reduce(0.0, Double::sum);
        double totalOrdersPrice = invoiceOrdersList.stream().map(OrderData::getTotalPrice).reduce(0.0, Double::sum);
        Invoice invoiceData = new Invoice(invoiceNumber, customer, invoiceOrdersList, startDate, endDate, totalFreightPrice, totalOrdersPrice);

        invoiceData.printOutSummary();
        File jasiekInvoice = new File("src/main/resources/" + customer.getCompanyName().replaceAll(" ", "_") + "_invoice.pdf");
        InvoiceGenerator.makePDF(jasiekInvoice, invoiceData);
        return invoiceData;
    }

    private void printOutSummary() {

        this.ordersList.forEach(o -> {
            System.out.println(
                    "\tOrderID: " + o.getOrderID() +
                    "\tOrderDate: " + o.getOrderDate() +
                    "\tCustomerID: " + o.getCustomerID() +
                    "\tProductID: " + o.getProductID() +
                    "\tProductName: " + o.getProductName() +
                    "\tUnitPrice: " + o.getUnitPrice() +
                    "\tQuantity: " + o.getQuantity() +
                    "\tDiscount: " + o.getDiscount() +
                    "\tFreight: " + o.getFreight() +
                    "\tTotalOrderPrice: " + o.getTotalPrice() + "\n");
        });
        System.out.println("\t TotalOrdersPrice:\t" + this.totalOrdersPrice);
        System.out.println("\t TotalFreightPrice:\t" + this.totalFreightPrice);
        System.out.println("\t \t SummaryPrice:\t" + (this.totalFreightPrice + this.totalOrdersPrice));
    }


    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public CustomersEntity getCustomer() {
        return customer;
    }

    public List<OrderData> getOrdersList() {
        return ordersList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalFreightPrice() {
        return totalFreightPrice;
    }

    public double getTotalOrdersPrice() {
        return totalOrdersPrice;
    }
}
