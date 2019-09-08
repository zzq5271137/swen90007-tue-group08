package datasource;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap<E> {
    private Map<Integer, E> map = new HashMap<>();
    private static Map<Class, IdentityMap> singletons = new HashMap<>();

    public static <E> IdentityMap<E> getInstance(E e) {
        IdentityMap<E> result = singletons.get(e.getClass());
        if (result == null) {
            result = new IdentityMap<E>();
            singletons.put(e.getClass(), result);
        }
        return result;
    }

    public void put(int id, E element) {
        map.put(id, element);
    }

    public E get(int id) {
        return map.get(id);
    }
}
