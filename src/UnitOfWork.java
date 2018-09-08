import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
	
	private static List<Object> clean = new ArrayList<Object>();
	private static List<Object> updated = new ArrayList<Object>();
	private static List<Object> created = new ArrayList<Object>();
	private static List<Object> deleted = new ArrayList<Object>();

	public static void registerClean(Object obj) {
		clean.add(obj);
	}
	
	public static void registerUpdated(Object obj) {
		if (!updated.contains(obj) && !created.contains(obj)) {
			updated.add(obj);
		}
	}
	
	public static void registerCreated(Object obj) {
		created.add(obj);
	}
	
	public static void registerDeleted(Object obj) {
		deleted.add(obj);
	}
	
	public static void commit() {
		for (Object obj : created) {
			DataMapper.getMapper(obj.getClass()).insert(obj);
		}
		
		for (Object obj : updated) {
			DataMapper.getMapper(obj.getClass()).update(obj);
		}
		
		for (Object obj : deleted) {
			DataMapper.getMapper(obj.getClass()).delete(obj);
		}
	}
}
