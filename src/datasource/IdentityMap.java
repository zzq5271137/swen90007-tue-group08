package datasource;

import java.util.HashMap;
import java.util.Map;

class IdentityMap<E> {
	
	private Map<Long, E> map = new HashMap<Long, E>();
	
	private static Map<Class, IdentityMap> singletons =
			new HashMap<Class, IdentityMap>();
	
	public static <E> IdentityMap<E> getInstance(E e){
		IdentityMap<E> result = singletons.get(e.getClass());
		if(result == null) {
			result = new IdentityMap<E>();
			singletons.put(e.getClass(), result);
		}
		return result;
	}
	
	public void put(long id, E obj) {
		map.put(id, obj);
	}
	
	public E get(long id) {
		return map.get(id);
	}
}
