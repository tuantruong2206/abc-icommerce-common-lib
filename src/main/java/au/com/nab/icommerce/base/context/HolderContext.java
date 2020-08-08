package au.com.nab.icommerce.base.context;

import org.springframework.stereotype.Component;

/**
 * the class helps to hold data for transfering between components and spring bean
 * @param <T>
 */
@Component
public class HolderContext<T> {

    /**
     * requestObjects is holding request object by specified thread
     */
    private final ThreadLocal<T> requestObj = new ThreadLocal<>();

    /**
     * store object for current thread
     *
     * @param t
     */
    public void setRequestObj(T t) {
        this.requestObj.set(t);
    }

    /**
     * get object for current thread
     *
     * @return object
     */
    public T getRequestObj() throws Exception {
        return this.requestObj.get();
    }

    /**
     * cleanup Threadlocal
     */
    public void clean() {
        this.requestObj.remove();
    }
}
