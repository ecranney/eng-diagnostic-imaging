/**
 * ReadWrite Lock, used to control access to Appointments and Reports.
 * 
 * @author ecranney
 * @date October 2018
 * 
 */

package dies.locks;

import java.util.*;

public class ReadWriteLock {

	// the list of threads with simultaneous read access
	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
	
	// the single thread with write access at any given time
	private Thread writingThread = null;
	
	private int writeAccesses = 0;
	private int writeRequests = 0;
	

	// read access to the resource
	synchronized void lockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while (!canGrantReadAccess(callingThread)) {
			wait();
		}
		
		readingThreads.put(callingThread,
				getReadAccessCount(callingThread) + 1);
	}
	
	private boolean canGrantReadAccess(Thread callingThread) {
		if (isWriter(callingThread)) return true;
		if (hasWriter()) return false;
		if (isReader(callingThread)) return true;
		return !hasWriteRequests();
	}
	
	synchronized void unlockRead() {
		Thread callingThread = Thread.currentThread();
		
		// check that the calling thread is actually a reader
		if (!isReader(callingThread)) {
			throw new IllegalMonitorStateException("Thread doesn't have read lock");
		}
		
		// 
		int accessCount = getReadAccessCount(callingThread);
		if (accessCount == 1) {
			readingThreads.remove(callingThread);
		} else {
			readingThreads.put(callingThread, (accessCount - 1));
		}
		notifyAll();
	}
	
	synchronized void lockWrite() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		
		// wait till thread is able to acquire write lock
		writeRequests++;
		while (!canGrantWriteAccess(callingThread)) {
			wait();
		}
		
		// give the write lock to the calling thread
		writeRequests--;
		writeAccesses++;
		writingThread = callingThread;
	}
	
	synchronized void unlockWrite() throws InterruptedException {
		if (!isWriter(Thread.currentThread())) {
			throw new IllegalMonitorStateException("Thread doens't have write lock");
		}
		writeAccesses--;
		if (writeAccesses == 0) {
			writingThread = null;
		}
		notifyAll();
	}
	
	synchronized void unlock() {
		Thread callingThread = Thread.currentThread();
		if (!isReader(callingThread) && !isWriter(callingThread)) {
			throw new IllegalMonitorStateException("Thread does not hold lock");
		}
		readingThreads.remove(callingThread);
		writingThread = null;
		notifyAll();
	}
	
	private boolean canGrantWriteAccess(Thread callingThread) {
		if (isOnlyReader(callingThread)) return true;
		if (hasReaders()) return false;
		if (writingThread == null) return true;
		return isWriter(callingThread);
	}
	
	private int getReadAccessCount(Thread callingThread) {
		Integer accessCount = readingThreads.get(callingThread);
		if (accessCount == null) return 0;
		return accessCount;
	}
	
	private boolean hasReaders() {
		return readingThreads.size() > 0;
	}
	
	private boolean isReader(Thread callingThread) {
		return readingThreads.get(callingThread) != null;
	}
	
	private boolean isOnlyReader(Thread callingThread) {
		return readingThreads.size() == 1 &&
				readingThreads.get(callingThread) != null;
 	}
	
	private boolean hasWriter() {
		return writingThread != null;
	}
	
	private boolean isWriter(Thread callingThread) {
		return writingThread == callingThread;
	}
	
	private boolean hasWriteRequests() {
		return this.writeRequests > 0;
	}
}