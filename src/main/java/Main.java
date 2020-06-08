import invoiceData.Invoice;
import invoiceGenerator.CamelCase;
import northwindEntities.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import CLI.MainCLI;


import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            MainCLI.startDialog(session, transaction);
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
