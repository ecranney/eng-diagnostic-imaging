/**
 * Class implementing the Unit Of Work pattern. This is used by the service
 * layer to encapsulate database accesses into transactions.
 */

package dies.data;

import dies.mappers.DataMapper;
import dies.models.IDomainObject;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
	
	// domain object registries
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

	// add an object to the clean registry
	public void registerClean(IDomainObject obj) {
		clean.add(obj);
	}
	
	// add an object to the update registry
	public void registerUpdated(IDomainObject obj) {
		// check that object is not already in another registry
		if (!updated.contains(obj) && !created.contains(obj)
				&& !created.contains(obj)) {
			updated.add(obj);
		}
	}
	
	// add an object to the created registry
	public void registerCreated(IDomainObject obj) {
		// check that the object isn't already in any registry
		if (!clean.contains(obj) && !updated.contains(obj)
				&& !deleted.contains(obj) && !created.contains(obj)) {
			created.add(obj);
		}
	}
	
	// add an object to the deleted registry
	public void registerDeleted(IDomainObject obj) {
		
		// remove from created registry if necessary
		if (created.contains(obj)) {
			created.remove(obj);
		}
		
		// check object is not already in registry
		if (!deleted.contains(obj)) {
			deleted.add(obj);
		}
	}
	
	// send all changes to the database
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
