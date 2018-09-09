/**
 * 
 * 
 * Whenever you call a "find(id)" or "findAll()" method in one of the Mappers,
 * you should check if the object already exists in the IdentityMap.
 * 
 * e.g. if findUser(id)
 * 
 * if {IdentityMap.getInstance(User.class).contains(id)} {
 * 		User user = IdentityMap.getInstance(User.class).get(id);
 * 		return user;
 * }
 * 1) move to data mappers, then
 * (2) try to access identity map every time find is called
 */

package dies.data;

import java.util.Map;

import db.DBConnection;

import java.util.HashMap;

public class IdentityMap<E> {
	
	/*
	 * IdentityMap (the class) acts as a singleton to store the map of maps
	 * from each (domain layer) class to its respective map.
	 */
	// map from each (domain layer) class to its respective identity map
	private static Map<Class<?>, IdentityMap> maps = 
			new HashMap<Class<?>, IdentityMap>();

	// retrieve the map for a particular class; create one if it doesn't exist
	public static <E> IdentityMap<E> getInstance(Class c) {
		IdentityMap<E> map = maps.get(c);
		if (map == null) {
			map = new IdentityMap<E>();
			maps.put(c, map);
		}
		return map;
	}
	
	/*
	 * When instantiated, IdentityMap maps object Ids (for a given class)
	 * to particular objects.
	 */
	private HashMap<Integer, E> map = new HashMap<Integer, E>();
	
	// add an object to the map
	public void put(Integer id, E obj) {
		map.put(id, obj);
	}
	
	// fetch an object from the map
	public E get(Integer id) {
		return map.get(id);
	}
	
	// check if map contains object
	public Boolean contains(Integer id) {
		return map.containsKey(id);
	}
}
