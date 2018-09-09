/**
 * (1) Create new appointment
 */

package dies.data;

import java.util.*;
import dies.mappers.DataMapper;
import dies.models.*;

public class UnitOfWork {
	
	private List<IDomainObject> clean;
	private List<IDomainObject> updated;
	private List<IDomainObject> created;
	private List<IDomainObject> deleted;
	
	public UnitOfWork() {
		clean = new ArrayList<IDomainObject>();
		updated = new ArrayList<IDomainObject>();
		created = new ArrayList<IDomainObject>();
		deleted = new ArrayList<IDomainObject>();	
	}

	public void registerClean(IDomainObject obj) {
		clean.add(obj);
	}
	
	public void registerUpdated(IDomainObject obj) {
		if (!updated.contains(obj) && !created.contains(obj)) {
			updated.add(obj);
		}
	}
	
	public void registerCreated(IDomainObject obj) {
		created.add(obj);
	}
	
	public void registerDeleted(IDomainObject obj) {
		if (!deleted.contains(obj)) {
			deleted.add(obj);
		}
	}
	
	public void commit() {
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
