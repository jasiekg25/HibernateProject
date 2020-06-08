import invoiceData.Invoice;
import northwindEntities.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        final Transaction transaction = session.beginTransaction();

        try {


/*            System.out.println("querying all the managed entities...");
                final Metamodel metamodel = session.getSessionFactory().getMetamodel();
                for (EntityType<?> entityType : metamodel.getEntities()) {
                    final String entityName = entityType.getName();
                    final Query query = session.createQuery("from " + entityName);
                    System.out.println("executing: " + query.getQueryString());
                    for (Object o : query.list()) {
                        System.out.println("  " + o);
                    }
                }

//            ME, MESELF & I
            CategoriesEntity newCategory = new CategoriesEntity(9, "Jasiek", "JasiekOpis", null);
//            session.save(newCategory);

            Timestamp startDate = Timestamp.valueOf("1994-04-03 00:00:00");
            Timestamp endDate = Timestamp.valueOf("2020-04-04 00:00:00");

//            Timestamp startDate = new Timestamp(1994, 4, 3, 0, 0, 0, 0);

//            Timestamp endDate = new Timestamp(2015, 4, 3, 0, 0, 0, 0);

            Invoice invoice = new Invoice();
            CustomersEntity customer = session.get(CustomersEntity.class, "ALFKI");
            invoice.invoice("Invoice1", startDate, endDate, customer, session);

*/          BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter command. (Type help to see available commands)");
            boolean isValid;
            while(true){
                System.out.print(">");
                StringTokenizer commandTok = new StringTokenizer(reader.readLine());
                String command = commandTok.nextToken();
                if(command.equals("help")){
                    System.out.println("printInvoice - Prints invoice as a PDF file. Takes 1 optional argument - path of the PDFs directory.\n\n" +
                            "getCategories - Prints all stored categories(ID, Name and Description). Takes optional arguments -id <id>, -n <name> to filter the outcome. \n\n" +
                            "getCustomers - Prints all stored customers(ID, Name, Detailed Adress, Phone and Fax). Takes optional arguments -id <id>, -n <name> to filter the outcome. \n\n" +
                            "getEmployees - Prints all stored employees(ID, Name and Surname). Takes optional arguments -id <id>, -n <name>, -sn <surname> to filter the outcome. \n\n" +
                            "getOrderDetails - Prints all stored order details(OrderID, ProductID, UnitPrice, Quantity and Discount). Takes optional arguments -oid <orderID>, -pid <productId> to filter the outcome. \n\n" +
                            "getOrders - Prints all stored orders(OrderID, CustomerID, EmployeeID and OrderDate). Takes optional arguments -oid <orderID>, -cid <customerID>, -eid <employeeID> to filter the outcome. \n\n" +
                            "getProducts - Prints all stored products(ProductID, ProductName, SupplierID and CategoryID). Takes optional arguments -pid <productID>, -pn <productName>, -sid <supplierID>, -cid <categoryID> to filter the outcome. \n\n" +
                            "getShippers - Prints all stored shippers(ShipperID, Name and Phone). Takes optional arguments -sid <shipperID>, -n <name> to filter the outcome. \n\n" +
                            "getSuppliers - Prints all stored suppliers(ShipperID, Name and Phone). Takes optional arguments -sid <supplierID>, -n <name> to filter the outcome. \n\n" +
                            "getCategories - \n\n" +
                            "addCustomers -  \n\n" +
                            "addEmployees -  \n\n" +
                            "addOrderDetails -  \n\n" +
                            "addOrders -  \n\n" +
                            "addProducts -  \n\n" +
                            "addShippers -  \n\n" +
                            "addSuppliers -  \n\n" +
                            "exit - Ends dialog and exits the program. \n");
                }else if(command.equals("printInvoice")){

                }else if(command.equals("addCategory")){

                }else if(command.equals("addCustomer")){

                }else if(command.equals("addEmployee")){

                }else if(command.equals("addOrderDetails")){

                }else if(command.equals("addOrder")){

                }else if(command.equals("addProduct")){

                }else if(command.equals("addShipper")){

                }else if(command.equals("addSupplier")){

                }else if(command.equals("getCategories")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            conditions = conditions + " CategoryID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-n")){
                            conditions = conditions + " CategoryName like " + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<CategoriesEntity> foundCategories = session.createQuery("SELECT c FROM CategoriesEntity c" + conditions, CategoriesEntity.class).getResultList();
                        foundCategories.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getCustomers")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            conditions = conditions + " CustomerID like " + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-n")){
                            conditions = conditions + " CompanyName like " + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<CustomersEntity> foundCustomers = session.createQuery("SELECT c FROM CustomersEntity c" + conditions, CustomersEntity.class).getResultList();
                        foundCustomers.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getEmployees")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            conditions = conditions + " EmployeeID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-n")){
                            conditions = conditions + " FirstName like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-sn")){
                            conditions = conditions + " LastName like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<EmployeesEntity> found = session.createQuery("SELECT c FROM EmployeesEntity c" + conditions, EmployeesEntity.class).getResultList();
                        found.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getOrderDetails")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-oid")){
                            conditions = conditions + " OrderID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-pid")){
                            conditions = conditions + " ProductID like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<OrderDetailsEntity> found = session.createQuery("SELECT c FROM OrderDetailsEntity c" + conditions, OrderDetailsEntity.class).getResultList();
                        found.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getOrders")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-oid")){
                            conditions = conditions + " OrderID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-cid")){
                            conditions = conditions + " CustomerID like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-eid")){
                            conditions = conditions + " EmployeeID like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<OrdersEntity> found = session.createQuery("SELECT c FROM OrdersEntity c" + conditions, OrdersEntity.class).getResultList();
                        found.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getProducts")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-pid")){
                            conditions = conditions + " ProductID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-pn")){
                            conditions = conditions + " ProductName like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-sid")){
                            conditions = conditions + " SupplierID like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-cid")){
                            conditions = conditions + " CategoryID like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<ProductsEntity> found = session.createQuery("SELECT c FROM ProductsEntity c" + conditions, ProductsEntity.class).getResultList();
                        found.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getShippers")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-sid")){
                            conditions = conditions + " ShipperID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-n")){
                            conditions = conditions + " CompanyName like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<ShippersEntity> found = session.createQuery("SELECT c FROM ShippersEntity c" + conditions, ShippersEntity.class).getResultList();
                        found.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("getSuppliers")){
                    isValid = true;
                    int i=0;
                    String conditions = "";
                    while(commandTok.hasMoreTokens()){
                        if(i==0){
                            conditions = conditions + " WHERE";
                        }else{
                            conditions = conditions + " AND";
                        }
                        i++;
                        command = commandTok.nextToken();
                        if(command.equals("-sid")){
                            conditions = conditions + " SupplierID like" + "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else if(command.equals("-n")){
                            conditions = conditions + " CompanyName like " +  "\'" + commandTok.nextToken().replace('_', ' ') + "\'";
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\". Optional arguments -id <id>, -n <name>.");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        List<SuppliersEntity> found = session.createQuery("SELECT c FROM SuppliersEntity c" + conditions, SuppliersEntity.class).getResultList();
                        found.forEach(entry -> System.out.println(entry.toString()));
                    }
                }else if(command.equals("exit")){
                    break;
                }else{
                    System.out.println("Unrecognized command. (Type help to see available commands)");
                }
            }


        } finally {
            transaction.commit();
            session.close();
        }
    }
}
