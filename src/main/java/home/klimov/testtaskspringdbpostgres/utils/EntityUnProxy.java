package home.klimov.testtaskspringdbpostgres.utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public class EntityUnProxy {
    public static <T> T initializeAndUnProxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
