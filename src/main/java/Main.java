import invoiceData.Invoice;
import northwindEntities.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Byte.parseByte;
import static java.lang.Double.parseDouble;
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
                            "addCategories - Adds new(or alters an existing) category entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cn <categoryName>, -d <description>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addCustomers - Adds new(or alters an existing) customer entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cmn <companyName>, -ctn <contactName>, -ctt <contactTitle>, -a <address>, -c <city>, -r <region>, -pc <postalCode>, -ctr <country>, -p <phone>, -f <fax>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addEmployees - Adds new(or alters an existing) employee entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -ln <lastName>, -fn <firstName>, -t <title>, -toc <titleOfCourtesy>, -a <address>, -c <city>, -r <region>, -pc <postalCode>, -ctr <country>, -n <notes>, -rt <reportsTo>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addOrderDetails - Adds new(or alters an existing) order details entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -up <unitPrice>, -d <discount> -q <quantity>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addOrders - Adds new(or alters an existing) order entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cid <customerId>, -eid <employeeId>, -od <orderDetails>, -sv <shipVia>, -f <freight>, -sn <shipName>, -sa <shipAddress>, -sc <shipCity>, -sr <shipRegion>, -spc <shipPostalCode>, -sctr <shipCountry>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addProducts - Adds new(or alters an existing) product entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -pn <productName>, -sid <supplierId>, -cid <categoryId>, -qpu <quantityPerUnit>, -up <unitPrice>, -uis <unitsInStock>, -uoo <unitsOnOrder>, -rl <ReorderLevel>, -d <discontinued>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addShippers - Adds new(or alters an existing) shipper entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cn <companyName>, -p <phone>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "addSuppliers - Adds new(or alters an existing) supplier entity and returns its id. Takes optional arguments: -id <alteredEntityId>, -cmn <companyName>, -ctn <contactName>, -ctt <contactTitle>, -a <address>, -c <city>, -r <region>, -pc <postalCode>, -ctr <country>, -p <phone>, -f <fax>, -hp <homePage>. -id argument makes it so the command alters an existing entity. \n\n" +
                            "exit - Ends dialog and exits the program. \n");
                }else if(command.equals("printInvoice")){

                }else if(command.equals("addCategory")){
                    isValid = true;
                    CategoriesEntity tmp = new CategoriesEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<CategoriesEntity> tmp2 = session.createQuery("SELECT c FROM CategoriesEntity c WHERE CategoryID like " + "\'" + command + "\'", CategoriesEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getCategoryName()!=null)
                                tmp2.get(0).setCategoryName(tmp.getCategoryName());
                            if(tmp.getDescription()!=null)
                                tmp2.get(0).setDescription(tmp.getDescription());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-cn")){
                            tmp.setCategoryName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-d")){
                            tmp.setDescription(commandTok.nextToken().replace('_', ' '));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addCustomer")){
                    isValid = true;
                    CustomersEntity tmp = new CustomersEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<CustomersEntity> tmp2 = session.createQuery("SELECT c FROM CustomersEntity c WHERE CustomerID like " + "\'" + command + "\'", CustomersEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getCompanyName()!=null)
                                tmp2.get(0).setCompanyName(tmp.getCompanyName());
                            if(tmp.getContactName()!=null)
                                tmp2.get(0).setContactName(tmp.getContactName());
                            if(tmp.getContactTitle()!=null)
                                tmp2.get(0).setContactTitle(tmp.getContactTitle());
                            if(tmp.getAddress()!=null)
                                tmp2.get(0).setAddress(tmp.getAddress());
                            if(tmp.getCity()!=null)
                                tmp2.get(0).setCity(tmp.getCity());
                            if(tmp.getRegion()!=null)
                                tmp2.get(0).setRegion(tmp.getRegion());
                            if(tmp.getPostalCode()!=null)
                                tmp2.get(0).setPostalCode(tmp.getPostalCode());
                            if(tmp.getCountry()!=null)
                                tmp2.get(0).setCountry(tmp.getCountry());
                            if(tmp.getPhone()!=null)
                                tmp2.get(0).setPhone(tmp.getPhone());
                            if(tmp.getFax()!=null)
                                tmp2.get(0).setFax(tmp.getFax());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-cmn")){
                            tmp.setCompanyName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ctn")){
                            tmp.setContactName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ctt")){
                            tmp.setContactTitle(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-a")){
                            tmp.setAddress(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-c")){
                            tmp.setCity(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-r")){
                            tmp.setRegion(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-pc")){
                            tmp.setPostalCode(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ctr")){
                            tmp.setCountry(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-p")){
                            tmp.setPhone(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-f")){
                            tmp.setFax(commandTok.nextToken().replace('_', ' '));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addEmployee")){
                    isValid = true;
                    EmployeesEntity tmp = new EmployeesEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<EmployeesEntity> tmp2 = session.createQuery("SELECT c FROM EmployeesEntity c WHERE EmployeeID like " + "\'" + command + "\'", EmployeesEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getLastName()!=null)
                                tmp2.get(0).setLastName(tmp.getLastName());
                            if(tmp.getFirstName()!=null)
                                tmp2.get(0).setFirstName(tmp.getFirstName());
                            if(tmp.getTitle()!=null)
                                tmp2.get(0).setTitle(tmp.getTitle());
                            if(tmp.getTitleOfCourtesy()!=null)
                                tmp2.get(0).setTitleOfCourtesy(tmp.getTitleOfCourtesy());
                            if(tmp.getBirthDate()!=null)
                                tmp2.get(0).setBirthDate(tmp.getBirthDate());
                            if(tmp.getHireDate()!=null)
                                tmp2.get(0).setHireDate(tmp.getHireDate());
                            if(tmp.getAddress()!=null)
                                tmp2.get(0).setAddress(tmp.getAddress());
                            if(tmp.getCity()!=null)
                                tmp2.get(0).setCity(tmp.getCity());
                            if(tmp.getRegion()!=null)
                                tmp2.get(0).setRegion(tmp.getRegion());
                            if(tmp.getPostalCode()!=null)
                                tmp2.get(0).setPostalCode(tmp.getPostalCode());
                            if(tmp.getCountry()!=null)
                                tmp2.get(0).setCountry(tmp.getCountry());
                            if(tmp.getReportsTo()!=null)
                                tmp2.get(0).setReportsTo(tmp.getReportsTo());
                            if(tmp.getNotes()!=null)
                                tmp2.get(0).setNotes(tmp.getNotes());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-ln")){
                            tmp.setLastName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-fn")){
                            tmp.setFirstName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-t")){
                            tmp.setTitle(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-toc")){
                            tmp.setTitleOfCourtesy(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-a")){
                            tmp.setAddress(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-c")){
                            tmp.setCity(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-r")){
                            tmp.setRegion(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-pc")){
                            tmp.setPostalCode(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ctr")){
                            tmp.setCountry(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-n")){
                            tmp.setNotes(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-rt")){
                            tmp.setReportsTo(parseInt(commandTok.nextToken()));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addOrderDetails")){
                    isValid = true;
                    OrderDetailsEntity tmp = new OrderDetailsEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<OrderDetailsEntity> tmp2 = session.createQuery("SELECT c FROM OrderDetailsEntity c WHERE OrderID like " + "\'" + command + "\' AND ProductID like " + "\'" + commandTok.nextToken().replace('_', ' ') + "\'", OrderDetailsEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getUnitPrice()!=null)
                                tmp2.get(0).setUnitPrice(tmp.getUnitPrice());
                            if(tmp.getQuantity() != 0)
                                tmp2.get(0).setQuantity(tmp.getQuantity());
                            if(tmp.getDiscount()!=0)
                                tmp2.get(0).setQuantity(tmp.getQuantity());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-up")){
                            tmp.setUnitPrice(BigDecimal.valueOf(parseDouble(commandTok.nextToken())));
                        }else if(command.equals("-d")){
                            tmp.setDiscount(parseDouble(commandTok.nextToken()));
                        }else if(command.equals("-q")){
                            tmp.setQuantity(parseInt(commandTok.nextToken()));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addOrder")){
                    isValid = true;
                    OrdersEntity tmp = new OrdersEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<OrdersEntity> tmp2 = session.createQuery("SELECT c FROM OrdersEntity c WHERE OrderID like " + "\'" + command + "\'", OrdersEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getCustomerId()!=null)
                                tmp2.get(0).setCustomerId(tmp.getCustomerId());
                            if(tmp.getEmployeeId()!=null)
                                tmp2.get(0).setEmployeeId(tmp.getEmployeeId());
                            if(tmp.getOrderDate()!=null)
                                tmp2.get(0).setOrderDate(tmp.getOrderDate());
                            if(tmp.getRequiredDate()!=null)
                                tmp2.get(0).setRequiredDate(tmp.getRequiredDate());
                            if(tmp.getShippedDate()!=null)
                                tmp2.get(0).setShippedDate(tmp.getShippedDate());
                            if(tmp.getShipVia()!=null)
                                tmp2.get(0).setShipVia(tmp.getShipVia());
                            if(tmp.getFreight()!=null)
                                tmp2.get(0).setFreight(tmp.getFreight());
                            if(tmp.getShipName()!=null)
                                tmp2.get(0).setShipName(tmp.getShipName());
                            if(tmp.getShipAddress()!=null)
                                tmp2.get(0).setShipAddress(tmp.getShipAddress());
                            if(tmp.getShipCity()!=null)
                                tmp2.get(0).setShipCity(tmp.getShipCity());
                            if(tmp.getShipRegion()!=null)
                                tmp2.get(0).setShipRegion(tmp.getShipRegion());
                            if(tmp.getShipPostalCode()!=null)
                                tmp2.get(0).setShipPostalCode(tmp.getShipPostalCode());
                            if(tmp.getShipCountry()!=null)
                                tmp2.get(0).setShipCountry(tmp.getShipCountry());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-cid")){
                            tmp.setCustomerId(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-eid")){
                            tmp.setEmployeeId(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-od")){
                            tmp.setOrderDate(Timestamp.valueOf(commandTok.nextToken()));
                        }else if(command.equals("-rd")){
                            tmp.setRequiredDate(Timestamp.valueOf(commandTok.nextToken()));
                        }else if(command.equals("-sd")){
                            tmp.setShippedDate(Timestamp.valueOf(commandTok.nextToken()));
                        }else if(command.equals("-sv")){
                            tmp.setShipVia(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-f")){
                            tmp.setFreight(BigDecimal.valueOf(parseDouble(commandTok.nextToken())));
                        }else if(command.equals("-sn")){
                            tmp.setShipName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-sa")){
                            tmp.setShipAddress(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-sc")){
                            tmp.setShipCity(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-sr")){
                            tmp.setShipRegion(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-spc")){
                            tmp.setShipPostalCode(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-sctr")){
                            tmp.setShipCountry(commandTok.nextToken().replace('_', ' '));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addProduct")){
                    isValid = true;
                    ProductsEntity tmp = new ProductsEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<ProductsEntity> tmp2 = session.createQuery("SELECT c FROM ProductsEntity c WHERE ProductID like " + "\'" + command + "\'", ProductsEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getProductName()!=null)
                                tmp2.get(0).setProductName(tmp.getProductName());
                            if(tmp.getSupplierId()!=null)
                                tmp2.get(0).setSupplierId(tmp.getSupplierId());
                            if(tmp.getCategoryId()!=null)
                                tmp2.get(0).setCategoryId(tmp.getCategoryId());
                            if(tmp.getQuantityPerUnit()!=null)
                                tmp2.get(0).setQuantityPerUnit(tmp.getQuantityPerUnit());
                            if(tmp.getUnitPrice()!=null)
                                tmp2.get(0).setUnitPrice(tmp.getUnitPrice());
                            if(tmp.getUnitsInStock()!=null)
                                tmp2.get(0).setUnitsInStock(tmp.getUnitsInStock());
                            if(tmp.getUnitsOnOrder()!=null)
                                tmp2.get(0).setUnitsOnOrder(tmp.getUnitsOnOrder());
                            if(tmp.getReorderLevel()!=null)
                                tmp2.get(0).setReorderLevel(tmp.getReorderLevel());
                            if(tmp.getDiscontinued()!=null)
                                tmp2.get(0).setDiscontinued(tmp.getDiscontinued());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-pn")){
                            tmp.setProductName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-sid")){
                            tmp.setSupplierId(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-cid")){
                            tmp.setCategoryId(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-qpu")){
                            tmp.setQuantityPerUnit(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-up")){
                            tmp.setUnitPrice(BigDecimal.valueOf(parseDouble(commandTok.nextToken())));
                        }else if(command.equals("-uis")){
                            tmp.setUnitsInStock(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-uoo")){
                            tmp.setUnitsOnOrder(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-rl")){
                            tmp.setReorderLevel(parseInt(commandTok.nextToken()));
                        }else if(command.equals("-d")){
                            tmp.setDiscontinued(parseByte(commandTok.nextToken()));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addShipper")){
                    isValid = true;
                    ShippersEntity tmp = new ShippersEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<ShippersEntity> tmp2 = session.createQuery("SELECT c FROM ShippersEntity c WHERE CategoryID like " + "\'" + command + "\'", ShippersEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getCompanyName()!=null)
                                tmp2.get(0).setCompanyName(tmp.getCompanyName());
                            if(tmp.getPhone()!=null)
                                tmp2.get(0).setPhone(tmp.getPhone());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-cn")){
                            tmp.setCompanyName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-p")){
                            tmp.setPhone(commandTok.nextToken().replace('_', ' '));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
                }else if(command.equals("addSupplier")){
                    isValid = true;
                    SuppliersEntity tmp = new SuppliersEntity();
                    while(commandTok.hasMoreTokens()){
                        command = commandTok.nextToken();
                        if(command.equals("-id")){
                            command = commandTok.nextToken().replace('_', ' ');
                            List<SuppliersEntity> tmp2 = session.createQuery("SELECT c FROM SuppliersEntity c WHERE SupplierID like " + "\'" + command + "\'", SuppliersEntity.class).getResultList();
                            if(tmp2.isEmpty()){
                                System.out.println("Invalid ID.");
                                isValid = false;
                                break;
                            }
                            if(tmp.getCompanyName()!=null)
                                tmp2.get(0).setCompanyName(tmp.getCompanyName());
                            if(tmp.getContactName()!=null)
                                tmp2.get(0).setContactName(tmp.getContactName());
                            if(tmp.getContactTitle()!=null)
                                tmp2.get(0).setContactTitle(tmp.getContactTitle());
                            if(tmp.getAddress()!=null)
                                tmp2.get(0).setAddress(tmp.getAddress());
                            if(tmp.getCity()!=null)
                                tmp2.get(0).setCity(tmp.getCity());
                            if(tmp.getRegion()!=null)
                                tmp2.get(0).setRegion(tmp.getRegion());
                            if(tmp.getPostalCode()!=null)
                                tmp2.get(0).setPostalCode(tmp.getPostalCode());
                            if(tmp.getCountry()!=null)
                                tmp2.get(0).setCountry(tmp.getCountry());
                            if(tmp.getPhone()!=null)
                                tmp2.get(0).setPhone(tmp.getPhone());
                            if(tmp.getFax()!=null)
                                tmp2.get(0).setFax(tmp.getFax());
                            if(tmp.getHomePage()!=null)
                                tmp2.get(0).setHomePage(tmp.getHomePage());
                            tmp = tmp2.get(0);
                        }else if(command.equals("-cmn")){
                            tmp.setCompanyName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ctn")){
                            tmp.setContactName(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ctt")){
                            tmp.setContactTitle(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-a")){
                            tmp.setAddress(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-c")){
                            tmp.setCity(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-r")){
                            tmp.setRegion(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-pc")){
                            tmp.setPostalCode(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-ct")){
                            tmp.setCountry(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-p")){
                            tmp.setPhone(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-f")){
                            tmp.setFax(commandTok.nextToken().replace('_', ' '));
                        }else if(command.equals("-hp")){
                            tmp.setHomePage(commandTok.nextToken().replace('_', ' '));
                        }else{
                            System.out.println("Invalid argument: \"" + command + "\".");
                            isValid = false;
                            break;
                        }
                    }
                    if(isValid){
                        try{
                            session.persist(tmp); transaction.commit();
                        }catch(javax.persistence.PersistenceException ex){
                            System.out.println("Invalid insertion data.");
                        }
                    }
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
                            System.out.println("Invalid argument: \"" + command + "\".");
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
