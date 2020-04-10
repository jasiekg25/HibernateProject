import invoice.Invoice;
import northwindEntities.CategoriesEntity;
import northwindEntities.CustomersEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;


import java.sql.Timestamp;

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


//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
////                    System.out.println("  " + o);
//                }
//            }

//            ME, MESELF & I
            CategoriesEntity newCategory = new CategoriesEntity(9, "Jasiek", "JasiekOpis", null);
//            session.save(newCategory);

            Timestamp startDate = Timestamp.valueOf("1994-04-03 00:00:00");
            Timestamp endDate = Timestamp.valueOf("2020-04-04 00:00:00");

//            Timestamp startDate = new Timestamp(1994, 4, 3, 0, 0, 0, 0);

//            Timestamp endDate = new Timestamp(2015, 4, 3, 0, 0, 0, 0);

            Invoice invoice = new Invoice();
            CustomersEntity customer = session.get(CustomersEntity.class, "VINET");
            Invoice newElement = invoice.invoice(startDate, endDate, customer, session);
            System.out.println("\n" + customer.getCustomerId());
            System.out.println(newElement.getCustomerID());


        } finally {
            transaction.commit();
            session.close();
        }
    }
}
