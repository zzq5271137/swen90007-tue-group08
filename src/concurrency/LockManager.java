package concurrency;

import java.util.HashMap;
import java.util.Map;

public class LockManager {
    private final Map<Object, ReadWriteLock> lockMap;
    private static LockManager manger;

    private LockManager() {
        lockMap = new HashMap<>();
    }

    public synchronized static LockManager getInstance() {
        if (LockManager.manger == null)
            LockManager.manger = new LockManager();
        return LockManager.manger;
    }

    private ReadWriteLock getReadWriteLock(Object toLock) {
        ReadWriteLock lock = lockMap.get(toLock);
        if (lock == null) {
            lockMap.putIfAbsent(toLock, new ReadWriteLock());
            lock = lockMap.get(toLock);
        }
        return lock;
    }

    public synchronized void acquireReadLock(Object toLock)
            throws InterruptedException {
        getReadWriteLock(toLock).lockRead();
    }

    public synchronized void acquireWriteLock(Object toLock)
            throws InterruptedException {
        getReadWriteLock(toLock).lockWrite();
    }

    public synchronized void releaseReadLock(Object toLock) {
        getReadWriteLock(toLock).unlockRead();
    }

    public synchronized void releaseWriteLock(Object toLock) {
        getReadWriteLock(toLock).unlockWrite();
    }

    public synchronized void releaseAllLocks() {
        for (Map.Entry<Object, ReadWriteLock> entry : lockMap.entrySet())
            entry.getValue().unlock();
    }
}