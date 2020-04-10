package invoice;

import northwindEntities.CustomersEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class Invoice {
    private CustomersEntity customer;
    private List<OrderData> ordersList;
    private Date startDate;
    private Date endDate;
    private double totalFreightPrice;
    private double totalOrdersPrice;


    public Invoice() {
    }

    public Invoice(
            CustomersEntity customer,
            List<OrderData> ordersList,
            Date startDate,
            Date endDate,
            double totalFreightPrice,
            double totalOrdersPrice
    ) {
        this.customer = customer;
        this.ordersList = ordersList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalFreightPrice = totalFreightPrice;
        this.totalOrdersPrice = totalOrdersPrice;
    }


    public Invoice invoice(Timestamp startDate, Timestamp endDate, CustomersEntity customer, Session session) {

        List<OrderData> invoiceOrdersList = OrderData.getOrdersList(startDate, endDate, customer, session);
        double totalFreightPrice = invoiceOrdersList.stream().map(OrderData::getFreight).reduce(0.0, Double::sum);
        double totalOrdersPrice = invoiceOrdersList.stream().map(OrderData::getTotalPrice).reduce(0.0, Double :: sum);
        Invoice invoiceData = new Invoice(customer, invoiceOrdersList, startDate, endDate, totalFreightPrice, totalOrdersPrice);

        invoiceData.printOutSummary();
        return invoiceData;
    }

    private void printOutSummary(){

        this.ordersList.forEach(o -> {
            System.out.println("Freight: " + o.getFreight() + "\t" + "TotalOrderPrice: " + o.getTotalPrice() + "\n");
        });
        System.out.println("\t TotalOrdersPrice:\t" + this.totalOrdersPrice);
        System.out.println("\t TotalFreightPrice:\t" + this.totalFreightPrice);
        System.out.println("\t \t SummaryPrice:\t" + (this.totalFreightPrice + this.totalOrdersPrice));
    }


}
