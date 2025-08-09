package org.datanucleus.state;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.NucleusLogger;

public class ObjectProviderPool {
   private long maxIdle = 100L;
   private long expirationTime;
   private Map recyclableOps;
   private CleanUpThread cleaner;
   private Class opClass;

   public ObjectProviderPool(int maxIdle, boolean reaperThread, Class opClass) {
      this.maxIdle = (long)maxIdle;
      this.expirationTime = 30000L;
      this.recyclableOps = new ConcurrentHashMap();
      this.opClass = opClass;
      if (reaperThread) {
         this.cleaner = new CleanUpThread(this, this.expirationTime * 2L);
         this.cleaner.start();
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug("Started pool of ObjectProviders (maxPool=" + maxIdle + ", reaperThread=" + reaperThread + ")");
      }

   }

   public void close() {
      if (this.cleaner != null) {
         this.cleaner.interrupt();
      }

   }

   protected ObjectProvider create(ExecutionContext ec, AbstractClassMetaData cmd) {
      return (ObjectProvider)ClassUtils.newInstance(this.opClass, ObjectProviderFactoryImpl.OBJECT_PROVIDER_CTR_ARG_CLASSES, new Object[]{ec, cmd});
   }

   public boolean validate(ObjectProvider op) {
      return true;
   }

   public void expire(ObjectProvider op) {
   }

   public synchronized ObjectProvider checkOut(ExecutionContext ec, AbstractClassMetaData cmd) {
      long now = System.currentTimeMillis();
      if (this.recyclableOps.size() > 0) {
         for(ObjectProvider op : this.recyclableOps.keySet()) {
            if (now - (Long)this.recyclableOps.get(op) > this.expirationTime) {
               this.recyclableOps.remove(op);
               this.expire(op);
               ObjectProvider op = null;
            } else {
               if (this.validate(op)) {
                  this.recyclableOps.remove(op);
                  op.connect(ec, cmd);
                  return op;
               }

               this.recyclableOps.remove(op);
               this.expire(op);
               ObjectProvider var9 = null;
            }
         }
      }

      ObjectProvider op = this.create(ec, cmd);
      return op;
   }

   public synchronized void cleanUp() {
      long now = System.currentTimeMillis();

      for(ObjectProvider op : this.recyclableOps.keySet()) {
         if (now - (Long)this.recyclableOps.get(op) > this.expirationTime) {
            this.recyclableOps.remove(op);
            this.expire(op);
            op = null;
         }
      }

      System.gc();
   }

   public synchronized void checkIn(ObjectProvider op) {
      if ((long)this.recyclableOps.size() < this.maxIdle) {
         this.recyclableOps.put(op, System.currentTimeMillis());
      }

   }

   class CleanUpThread extends Thread {
      private ObjectProviderPool pool;
      private long sleepTime;

      CleanUpThread(ObjectProviderPool pool, long sleepTime) {
         this.pool = pool;
         this.sleepTime = sleepTime;
      }

      public void run() {
         for(boolean needsStopping = false; !needsStopping; this.pool.cleanUp()) {
            try {
               sleep(this.sleepTime);
            } catch (InterruptedException var3) {
               needsStopping = true;
            }
         }

      }
   }
}
