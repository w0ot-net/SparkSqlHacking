package org.apache.curator.framework.recipes.locks;

import java.util.concurrent.TimeUnit;

public interface InterProcessLock {
   void acquire() throws Exception;

   boolean acquire(long var1, TimeUnit var3) throws Exception;

   void release() throws Exception;

   boolean isAcquiredInThisProcess();
}
