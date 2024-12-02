package interfaces;

import java.io.Closeable;

public interface IDAO <T, K> extends Closeable {
    T store(T entity);

    T findId(K entityId);

    T deleteEntity(T entityDelete);
}
