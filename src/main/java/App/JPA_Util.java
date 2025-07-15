package App;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA_Util {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            // "myPersistenceUnit" should match the name in persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}