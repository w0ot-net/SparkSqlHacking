package org.datanucleus.state;

public interface LockManager {
   short LOCK_MODE_NONE = 0;
   short LOCK_MODE_OPTIMISTIC_READ = 1;
   short LOCK_MODE_OPTIMISTIC_WRITE = 2;
   short LOCK_MODE_PESSIMISTIC_READ = 3;
   short LOCK_MODE_PESSIMISTIC_WRITE = 4;

   void lock(Object var1, short var2);

   short getLockMode(Object var1);

   void clear();

   void lock(ObjectProvider var1, short var2);

   void unlock(ObjectProvider var1);

   short getLockMode(ObjectProvider var1);

   void close();
}
