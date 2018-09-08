import java.util.ArrayList;
import java.util.List;
import dies.mappers.DataMapper;
import dies.models.*;

public class UnitOfWork {
	
	private static List<DomainObject> clean = new ArrayList<DomainObject>();
	private static List<DomainObject> updated = new ArrayList<DomainObject>();
	private static List<DomainObject> created = new ArrayList<DomainObject>();
	private static List<DomainObject> deleted = new ArrayList<DomainObject>();

	public static void registerClean(DomainObject obj) {
		clean.add(obj);
	}
	
	public static void registerUpdated(DomainObject obj) {
		if (!updated.contains(obj) && !created.contains(obj)) {
			updated.add(obj);
		}
	}
	
	public static void registerCreated(DomainObject obj) {
		created.add(obj);
	}
	
	public static void registerDeleted(DomainObject obj) {
		deleted.add(obj);
	}
	
	public static void commit() {
		for (DomainObject obj : created) {
			DataMapper.getMapper(obj.getClass()).insert(obj);
		}
		
		for (DomainObject obj : updated) {
			DataMapper.getMapper(obj.getClass()).update(obj);
		}
		
		for (DomainObject obj : deleted) {
			DataMapper.getMapper(obj.getClass()).delete(obj);
		}
	}
}
