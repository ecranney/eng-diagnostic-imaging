package dies.data;
import java.util.ArrayList;
import java.util.List;
import dies.mappers.DataMapper;
import dies.models.*;

public class UnitOfWork {
	
	private static List<IDomainObject> clean = new ArrayList<IDomainObject>();
	private static List<IDomainObject> updated = new ArrayList<IDomainObject>();
	private static List<IDomainObject> created = new ArrayList<IDomainObject>();
	private static List<IDomainObject> deleted = new ArrayList<IDomainObject>();

	public static void registerClean(IDomainObject obj) {
		clean.add(obj);
	}
	
	public static void registerUpdated(IDomainObject obj) {
		if (!updated.contains(obj) && !created.contains(obj)) {
			updated.add(obj);
		}
	}
	
	public static void registerCreated(IDomainObject obj) {
		created.add(obj);
	}
	
	public static void registerDeleted(IDomainObject obj) {
		if (!deleted.contains(obj)) {
			deleted.add(obj);
		}
	}
	
	public static void commit() {
		for (IDomainObject obj : created) {
			DataMapper.getMapper(obj.getClass()).insert(obj);
		}
		
		for (IDomainObject obj : updated) {
			DataMapper.getMapper(obj.getClass()).update(obj);
		}
		
		for (IDomainObject obj : deleted) {
			DataMapper.getMapper(obj.getClass()).delete(obj);
		}
	}
}
