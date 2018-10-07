/**
 * Wrapper around DataMapper objects that require locks.
 * 
 */

package dies.locks;

import dies.models.*;
import dies.mappers.*;

import org.apache.shiro.*;
import org.apache.shiro.session.Session;

public class LockingMapper extends DataMapper {

	private DataMapper mapper;
	private LockManager lockManager;
	
	public LockingMapper(DataMapper mapper) {
		this.mapper = mapper;
		this.lockManager = LockManager.getInstance();
		
		// STUB - add the actual session id here
	}
	
	public IDomainObject find(int id) {
		// grab a read lock to do this
		try {
			lockManager.acquireReadLock(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapper.find(id);
		// TODO: find some way of releasing the read lock when a user
		//   just closes browser, presses back, or session expires
	}
	
	public void insert(IDomainObject object) {
		// grab a write lock to do this
		try {
			lockManager.acquireReadLock(object.getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: maybe don't block... instead throw an error and let the
		//   user continue with their work
		mapper.insert(object);
		lockManager.releaseReadLock(object.getId());
	}
	
	public void update (IDomainObject object) {
		// grab a write lock to do this
		try {
			lockManager.acquireReadLock(object.getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: maybe don't block... instead throw an error and let the
		//   user continue with their work
		mapper.insert(object);
		lockManager.releaseReadLock(object.getId());
	}
	
	public void delete(IDomainObject object) {
		// grab a write lock to do this
		try {
			lockManager.acquireWriteLock(object.getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: maybe don't block... instead throw an error and let the
		//   user continue with their work
		mapper.delete(object);
		try {
			lockManager.releaseWriteLock(object.getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
