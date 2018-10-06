/**
 * Wrapper around DataMapper objects that require locks.
 * 
 */

package dies.locks;

import dies.models.*;
import dies.mappers.*;

import org.apache.shiro.*;

public class LockingMapper extends DataMapper {

	private DataMapper mapper;
	private LockManager lockManager;
	private Session session;
	
	public LockingMapper(DataMapper mapper) {
		this.mapper = mapper;
		this.lockManager = LockManager.getInstance();
		
		// STUB - add the actual session id here
		this.session = SecurityUtils.getSubject().getSession();
	}
	
	public IDomainObject find(int id) {
		// grab a read lock to do this
		lockManager.acquireReadLock(id);
		return mapper.find(id);
		// TODO: find some way of releasing the read lock when a user
		//   just closes browser, presses back, or session expires
	}
	
	public void insert(IDomainObject object) {
		// grab a write lock to do this
		lockManager.acquireReadLock(object.getId());
		// TODO: maybe don't block... instead throw an error and let the
		//   user continue with their work
		mapper.insert(object);
		lockManager.releaseReadLock(object.getId());
	}
	
	public void update (IDomainObject object) {
		// grab a write lock to do this
		lockManager.acquireReadLock(object.getId());
		// TODO: maybe don't block... instead throw an error and let the
		//   user continue with their work
		mapper.insert(object);
		lockManager.releaseReadLock(object.getId());
	}
	
	public void delete(IDomainObject object) {
		// grab a write lock to do this
		lockManager.acquireWriteLock(object.getId());
		// TODO: maybe don't block... instead throw an error and let the
		//   user continue with their work
		mapper.delete(object);
		lockManager.releaseWriteLock(object.getId());
	}
}
