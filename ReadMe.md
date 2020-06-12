# InvoiceGenerator
## HibernateProject

[![](https://img.shields.io/badge/Hibernate-5.4.14-orange)]()
[![](https://img.shields.io/badge/Maven-3.6.1-green)]()
[![](https://img.shields.io/badge/Java-11-red)]()
[![](https://img.shields.io/badge/mySQL-8.0.19-blue)]()

## Project description
InvoiceGenerator is a project made for DatabaseCourse in AGH University of Science and Technology in Krakow as a task showcasing designing, programming and documenting a project in Java, Hibernate and mySQL. 


## Functionality
Project was planned as a program to manage with Northwind Database. 
User interface include:
 - **printInvoice**  - *Prints invoice as a PDF file. Takes optional arguments -sd <startDate>, -ed <endDate>, -c <customerName>.*  
 - **getCategories**  - *Prints all stored categories(ID, Name and Description). Takes optional arguments -id <id>, -n <name> to filter the outcome.* 
 - **getCustomers**  - *Prints all stored customers(ID, Name, Detailed Adress, Phone and Fax). Takes optional arguments -id <id>, -n <name> to filter the outcome.* 
 - **getEmployees**  - *Prints all stored employees(ID, Name and Surname). Takes optional arguments -id <id>, -n <name>, -sn <surname> to filter the outcome.*
 - **getOrderDetails**  - *Prints all stored order details(OrderID, ProductID, UnitPrice, Quantity and Discount). Takes optional arguments -oid <orderID>, -pid <productId> to filter the outcome.* 
 - **getOrders**  - *Prints all stored orders(OrderID, CustomerID, EmployeeID and OrderDate). Takes optional arguments -oid <orderID>, -cid <customerID>, -eid <employeeID> to filter the outcome.* 
 - **getProducts**  - *Prints all stored products(ProductID, ProductName, SupplierID and CategoryID). Takes optional arguments -pid <productID>, -pn <productName>, -sid <supplierID>, -cid <categoryID> to filter the outcome.* 
 - **getShippers**  - *Prints all stored shippers(ShipperID, Name and Phone). Takes optional arguments -sid <shipperID>, -n <name> to filter the outcome.* 
 - **getSuppliers**  - *Prints all stored suppliers(ShipperID, Name and Phone). Takes optional arguments -sid <supplierID>, -n <name> to filter the outcome.* 
 - **addCategories**  - *Adds new(or alters an existing) category entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cn <categoryName>, -d <description>. -id argument makes it so the command alters an existing entity.* 
 - **addCustomers**  - *Adds new(or alters an existing) customer entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cmn <companyName>, -ctn <contactName>, -ctt <contactTitle>, -a <address>, -c <city>, -r <region>, -pc <postalCode>, -ctr <country>, -p <phone>, -f <fax>. -id argument makes it so the command alters an existing entity.*
 - **addEmployees**  - *Adds new(or alters an existing) employee entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -ln <lastName>, -fn <firstName>, -t <title>, -toc <titleOfCourtesy>, -a <address>, -c <city>, -r <region>, -pc <postalCode>, -ctr <country>, -n <notes>, -rt <reportsTo>. -id argument makes it so the command alters an existing entity.* 
 - **addOrderDetails**  - *Adds new(or alters an existing) order details entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -up <unitPrice>, -d <discount> -q <quantity>. -id argument makes it so the command alters an existing entity.*
 - **addOrders**  - *Adds new(or alters an existing) order entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cid <customerId>, -eid <employeeId>, -od <orderDetails>, -sv <shipVia>, -f <freight>, -sn <shipName>, -sa <shipAddress>, -sc <shipCity>, -sr <shipRegion>, -spc <shipPostalCode>, -sctr <shipCountry>. -id argument makes it so the command alters an existing entity.* 
 - **addProducts**  - *Adds new(or alters an existing) product entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -pn <productName>, -sid <supplierId>, -cid <categoryId>, -qpu <quantityPerUnit>, -up <unitPrice>, -uis <unitsInStock>, -uoo <unitsOnOrder>, -rl <ReorderLevel>, -d <discontinued>. -id argument makes it so the command alters an existing entity.*
 - **addShippers**  - *Adds new(or alters an existing) shipper entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cn <companyName>, -p <phone>. -id argument makes it so the command alters an existing entity.*
 - **addSuppliers**  - *Adds new(or alters an existing) supplier entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cmn <companyName>, -ctn <contactName>, -ctt <contactTitle>, -a <address>, -c <city>, -r <region>, -pc <postalCode>, -ctr <country>, -p <phone>, -f <fax>, -hp <homePage>. -id argument makes it so the command alters an existing entity.*
 - **exit**  - *Ends dialog and exits the program.*
 
## Description of the selected functionality
#### PrintInvoice
Method take mandatory parameters and use them to take appropriate data from Northwind Database. 
Class ***OrderData*** contains all attributes necessary to gather information about one Order to generate invoice from many entities.
Class ***Invoice*** has list of ***OrderData*** objects.
The Query to extract data into OrderData object's list:
```java
public class OrderData{
//  ...
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
//  ...
}
```

Next step is to create invoice from list of orders.
```java
public class Invoice{
//  ...
    public static Invoice generateInvoice(String invoiceNumber, Timestamp startDate, Timestamp endDate, CustomersEntity customer, Session session) {

        List<OrderData> invoiceOrdersList = OrderData.getOrdersList(startDate, endDate, customer, session);
        double totalFreightPrice = invoiceOrdersList.stream().map(OrderData::getFreight).reduce(0.0, Double::sum);
        double totalOrdersPrice = invoiceOrdersList.stream().map(OrderData::getTotalPrice).reduce(0.0, Double::sum);
        Invoice invoiceData = new Invoice(invoiceNumber, customer, invoiceOrdersList, startDate, endDate, totalFreightPrice, totalOrdersPrice);

        String fileName = CamelCase.toCamelCase(customer.getCompanyName());
        File newInvoice = new File("src/main/resources/" + fileName + "_invoice.pdf");
        InvoiceGenerator.makePDF(newInvoice, invoiceData);
        return invoiceData;
    }
//  ...
}
```
 Upper method is generating an object Invoice and creating pdf version of invoice.

## Showcase
#### Inputed Data:
```
{
  "startDate": "1994-04-03 00:00:00",
  "endDate": "2020-04-04 00:00:00",
  "customerID": "ALFKI"
}
```
Command:
```
printInvoice -sd 1994-04-03_00:00:00 -ed 2020-04-04_00:00:00 -c ALFKI
```
#### Generated Invoice
[Example Of Generated Invoice in PDF](src/main/resources/Alfreds_Futterkiste_invoice.pdf)  
  
![Example Of Generated Invoice](src/main/resources/exampleInvoice.jpg)



