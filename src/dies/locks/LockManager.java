/**
 * Lock Manager for the Read/Write locks used to control access
 * to Appointments and Reports.
 */

package dies.locks;

import java.util.*;

public class LockManager {
	
	// singleton lock manager
	private static LockManager manager;
	
	// the map from objects to their locks
	private final Map<Object, ReadWriteLock> lockMap;
	
	private LockManager() {
		lockMap = new HashMap<Object, ReadWriteLock>();
	}
	
	// static method to grab the lock manager
	public static LockManager getInstance() {
		
		// instantiate the lock manager if it hasn't been already
		if (LockManager.manager == null) {
			LockManager.manager = new LockManager();
		}
		return LockManager.manager;
	}
	
	// get the read/write lock over the given resource
	private ReadWriteLock getReadWriteLock(Object toLock) {
		ReadWriteLock lock = lockMap.get(toLock);
		
		// if the object doesn't have a lock over it, then create one
		if (lock == null) {
			lockMap.putIfAbsent(toLock, new ReadWriteLock());
			lock = lockMap.get(toLock);
		}
		
		return lock;
	}
	
	// acquire a read lock over the object
	public synchronized void acquireReadLock(Object toLock)
			throws InterruptedException {
		getReadWriteLock(toLock).lockRead();
	}
	
	// acquire a write lock over the object
	public synchronized void acquireWriteLock(Object toLock)
			throws InterruptedException {
		getReadWriteLock(toLock).lockWrite();
	}
	
	// release release a read lock
	public synchronized void releaseReadLock(Object toLock) {
		getReadWriteLock(toLock).unlockRead();
	}
	
	// release the write lock
	public synchronized void releaseWriteLock(Object toLock)
			throws InterruptedException {
		getReadWriteLock(toLock).unlockWrite();
	}
	
	// release all locks for all resources
	public synchronized void releaseAllLocks() {
		for (Map.Entry<Object, ReadWriteLock> entry : lockMap.entrySet()) {
			entry.getValue().unlock();
		}
	}
}
