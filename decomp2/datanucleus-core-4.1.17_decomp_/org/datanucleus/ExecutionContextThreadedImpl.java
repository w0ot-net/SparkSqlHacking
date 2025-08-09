package org.datanucleus;

import java.util.ArrayList;
import java.util.Map;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.Extent;

public class ExecutionContextThreadedImpl extends ExecutionContextImpl {
   public ExecutionContextThreadedImpl(PersistenceNucleusContext ctx, Object owner, Map options) {
      super(ctx, owner, options);
   }

   public boolean getMultithreaded() {
      return true;
   }

   public void processNontransactionalUpdate() {
      try {
         this.lock.lock();
         super.processNontransactionalUpdate();
      } finally {
         this.lock.unlock();
      }

   }

   public void enlistInTransaction(ObjectProvider sm) {
      try {
         this.lock.lock();
         super.enlistInTransaction(sm);
      } finally {
         this.lock.unlock();
      }

   }

   public void evictFromTransaction(ObjectProvider sm) {
      try {
         this.lock.lock();
         super.evictFromTransaction(sm);
      } finally {
         this.lock.unlock();
      }

   }

   public void addObjectProvider(ObjectProvider op) {
      try {
         this.lock.lock();
         super.addObjectProvider(op);
      } finally {
         this.lock.unlock();
      }

   }

   public void removeObjectProvider(ObjectProvider op) {
      try {
         this.lock.lock();
         super.removeObjectProvider(op);
      } finally {
         this.lock.unlock();
      }

   }

   public ObjectProvider findObjectProvider(Object pc) {
      ObjectProvider var2;
      try {
         this.lock.lock();
         var2 = super.findObjectProvider(pc);
      } finally {
         this.lock.unlock();
      }

      return var2;
   }

   public void hereIsObjectProvider(ObjectProvider sm, Object pc) {
      try {
         this.lock.lock();
         super.hereIsObjectProvider(sm, pc);
      } finally {
         this.lock.unlock();
      }

   }

   public void close() {
      try {
         this.lock.lock();
         super.close();
      } finally {
         this.lock.unlock();
      }

   }

   public void evictObject(Object obj) {
      try {
         this.lock.lock();
         super.evictObject(obj);
      } finally {
         this.lock.unlock();
      }

   }

   public void refreshObject(Object obj) {
      try {
         this.lock.lock();
         super.refreshObject(obj);
      } finally {
         this.lock.unlock();
      }

   }

   public void retrieveObject(Object obj, boolean fgOnly) {
      try {
         this.lock.lock();
         super.retrieveObject(obj, fgOnly);
      } finally {
         this.lock.unlock();
      }

   }

   public Object persistObject(Object obj, boolean merging) {
      Object var3;
      try {
         this.lock.lock();
         var3 = super.persistObject(obj, merging);
      } finally {
         this.lock.unlock();
      }

      return var3;
   }

   public Object[] persistObjects(Object[] objs) {
      Object[] var2;
      try {
         this.lock.lock();
         var2 = super.persistObjects(objs);
      } finally {
         this.lock.unlock();
      }

      return var2;
   }

   public void deleteObject(Object obj) {
      try {
         this.lock.lock();
         super.deleteObject(obj);
      } finally {
         this.lock.unlock();
      }

   }

   public void deleteObjects(Object[] objs) {
      try {
         this.lock.lock();
         super.deleteObjects(objs);
      } finally {
         this.lock.unlock();
      }

   }

   public void makeObjectTransient(Object obj, FetchPlanState state) {
      try {
         this.lock.lock();
         super.makeObjectTransient(obj, state);
      } finally {
         this.lock.unlock();
      }

   }

   public void makeObjectTransactional(Object obj) {
      try {
         this.lock.lock();
         super.makeObjectTransactional(obj);
      } finally {
         this.lock.unlock();
      }

   }

   public void attachObject(ObjectProvider ownerOP, Object pc, boolean sco) {
      try {
         this.lock.lock();
         super.attachObject(ownerOP, pc, sco);
      } finally {
         this.lock.unlock();
      }

   }

   public Object attachObjectCopy(ObjectProvider ownerOP, Object pc, boolean sco) {
      Object var4;
      try {
         this.lock.lock();
         var4 = super.attachObjectCopy(ownerOP, pc, sco);
      } finally {
         this.lock.unlock();
      }

      return var4;
   }

   public void detachObject(Object obj, FetchPlanState state) {
      try {
         this.lock.lock();
         super.detachObject(obj, state);
      } finally {
         this.lock.unlock();
      }

   }

   public Object detachObjectCopy(Object pc, FetchPlanState state) {
      Object var3;
      try {
         this.lock.lock();
         var3 = super.detachObjectCopy(pc, state);
      } finally {
         this.lock.unlock();
      }

      return var3;
   }

   public void clearDirty(ObjectProvider op) {
      try {
         this.lock.lock();
         super.clearDirty(op);
      } finally {
         this.lock.unlock();
      }

   }

   public void clearDirty() {
      try {
         this.lock.lock();
         super.clearDirty();
      } finally {
         this.lock.unlock();
      }

   }

   public void evictAllObjects() {
      this.assertIsOpen();

      try {
         this.lock.lock();
         synchronized(this.cache) {
            ArrayList<ObjectProvider> opsToEvict = new ArrayList();
            opsToEvict.addAll(this.cache.values());

            for(ObjectProvider op : opsToEvict) {
               Object pc = op.getObject();
               op.evict();
               this.removeObjectFromLevel1Cache(this.getApiAdapter().getIdForObject(pc));
            }
         }
      } finally {
         this.lock.unlock();
      }

   }

   public void markDirty(ObjectProvider op, boolean directUpdate) {
      try {
         this.lock.lock();
         super.markDirty(op, directUpdate);
      } finally {
         this.lock.unlock();
      }

   }

   public void flush() {
      try {
         this.lock.lock();
         super.flush();
      } finally {
         this.lock.unlock();
      }

   }

   public void flushInternal(boolean flushToDatastore) {
      try {
         this.lock.lock();
         super.flushInternal(flushToDatastore);
      } finally {
         this.lock.unlock();
      }

   }

   public void replaceObjectId(Object pc, Object oldID, Object newID) {
      try {
         this.lock.lock();
         super.replaceObjectId(pc, oldID, newID);
      } finally {
         this.lock.unlock();
      }

   }

   public Extent getExtent(Class pcClass, boolean subclasses) {
      Extent var3;
      try {
         this.lock.lock();
         var3 = super.getExtent(pcClass, subclasses);
      } finally {
         this.lock.unlock();
      }

      return var3;
   }
}
