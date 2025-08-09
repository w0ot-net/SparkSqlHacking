package org.datanucleus.cache;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.NucleusContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.WeakValueMap;

public class WeakLevel2Cache implements Level2Cache {
   private static final long serialVersionUID = -4521848285620167823L;
   protected Collection pinnedClasses;
   protected Collection pinnedIds;
   protected Map pinnedCache;
   protected transient Map unpinnedCache;
   protected ApiAdapter apiAdapter;
   private int maxSize = -1;

   protected WeakLevel2Cache() {
   }

   public WeakLevel2Cache(NucleusContext nucleusCtx) {
      this.apiAdapter = nucleusCtx.getApiAdapter();
      this.pinnedCache = new HashMap();
      this.unpinnedCache = new WeakValueMap();
      this.maxSize = nucleusCtx.getConfiguration().getIntProperty("datanucleus.cache.level2.maxSize");
   }

   public void close() {
      this.evictAll();
      this.pinnedCache = null;
      this.unpinnedCache = null;
   }

   public synchronized void evict(Object oid) {
      if (oid != null) {
         this.unpinnedCache.remove(oid);
         this.pinnedCache.remove(oid);
      }
   }

   public synchronized void evictAll() {
      this.unpinnedCache.clear();
      this.pinnedCache.clear();
   }

   public synchronized void evictAll(Class pcClass, boolean subclasses) {
      if (pcClass != null) {
         Collection oidsToEvict = new HashSet();

         for(Map.Entry entry : this.pinnedCache.entrySet()) {
            CachedPC pc = (CachedPC)entry.getValue();
            if (pcClass.getName().equals(pc.getObjectClass().getName()) || subclasses && pcClass.isAssignableFrom(pc.getObjectClass())) {
               oidsToEvict.add(entry.getKey());
            }
         }

         for(Map.Entry entry : this.unpinnedCache.entrySet()) {
            CachedPC pc = (CachedPC)entry.getValue();
            if (pc != null && pcClass.getName().equals(pc.getObjectClass().getName()) || subclasses && pcClass.isAssignableFrom(pc.getObjectClass())) {
               oidsToEvict.add(entry.getKey());
            }
         }

         if (!oidsToEvict.isEmpty()) {
            this.evictAll(oidsToEvict);
         }

      }
   }

   public synchronized void evictAll(Collection oids) {
      if (oids != null) {
         Iterator iter = oids.iterator();

         while(iter.hasNext()) {
            this.evict(iter.next());
         }

      }
   }

   public synchronized void evictAll(Object[] oids) {
      if (oids != null) {
         for(int i = 0; i < oids.length; ++i) {
            this.evict(oids[i]);
         }

      }
   }

   public synchronized void pin(Object oid) {
      if (oid != null) {
         if (this.pinnedIds == null) {
            this.pinnedIds = new HashSet();
         } else if (!this.pinnedIds.contains(oid)) {
            this.pinnedIds.add(oid);
         }

         Object pc = this.unpinnedCache.get(oid);
         if (pc != null) {
            this.pinnedCache.put(oid, pc);
            this.unpinnedCache.remove(oid);
         }

      }
   }

   public synchronized void pinAll(Class cls, boolean subs) {
      if (cls != null) {
         if (this.pinnedClasses == null) {
            this.pinnedClasses = new HashSet();
         }

         Level2Cache.PinnedClass pinnedCls = new Level2Cache.PinnedClass(cls, subs);
         if (!this.pinnedClasses.contains(pinnedCls)) {
            this.pinnedClasses.add(pinnedCls);

            for(CachedPC obj : this.unpinnedCache.values()) {
               if (subs && cls.isInstance(obj.getObjectClass()) || cls.getName().equals(obj.getObjectClass().getName())) {
                  this.pin(obj);
               }
            }

         }
      }
   }

   public synchronized void pinAll(Collection oids) {
      if (oids != null) {
         Iterator iter = oids.iterator();

         while(iter.hasNext()) {
            this.pin(iter.next());
         }

      }
   }

   public synchronized void pinAll(Object[] oids) {
      if (oids != null) {
         for(int i = 0; i < oids.length; ++i) {
            this.pin(oids[i]);
         }

      }
   }

   public synchronized void unpin(Object oid) {
      if (oid != null) {
         Object pc = this.pinnedCache.get(oid);
         if (pc != null) {
            this.unpinnedCache.put(oid, pc);
            this.pinnedCache.remove(oid);
         }

         if (this.pinnedIds != null && this.pinnedIds.contains(oid)) {
            this.pinnedIds.remove(oid);
         }

      }
   }

   public synchronized void unpinAll(Class cls, boolean subs) {
      if (cls != null) {
         if (this.pinnedClasses != null) {
            Level2Cache.PinnedClass pinnedCls = new Level2Cache.PinnedClass(cls, subs);
            this.pinnedClasses.remove(pinnedCls);
         }

         for(CachedPC obj : this.pinnedCache.values()) {
            if (subs && cls.isInstance(obj.getObjectClass()) || cls.getName().equals(obj.getObjectClass().getName())) {
               this.unpin(obj);
            }
         }

      }
   }

   public synchronized void unpinAll(Collection oids) {
      if (oids != null) {
         Iterator iter = oids.iterator();

         while(iter.hasNext()) {
            this.unpin(iter.next());
         }

      }
   }

   public synchronized void unpinAll(Object[] oids) {
      if (oids != null) {
         for(int i = 0; i < oids.length; ++i) {
            this.unpin(oids[i]);
         }

      }
   }

   public synchronized CachedPC get(Object oid) {
      if (oid == null) {
         return null;
      } else {
         CachedPC pc = (CachedPC)this.pinnedCache.get(oid);
         if (pc != null) {
            return pc;
         } else {
            pc = (CachedPC)this.unpinnedCache.get(oid);
            return pc;
         }
      }
   }

   public Map getAll(Collection oids) {
      if (oids == null) {
         return null;
      } else {
         Map<Object, CachedPC> objs = new HashMap();

         for(Object oid : oids) {
            CachedPC obj = this.get(oid);
            if (obj != null) {
               objs.put(oid, obj);
            }
         }

         return objs;
      }
   }

   public int getNumberOfPinnedObjects() {
      return this.pinnedCache.size();
   }

   public int getNumberOfUnpinnedObjects() {
      return this.unpinnedCache.size();
   }

   public int getSize() {
      return this.getNumberOfPinnedObjects() + this.getNumberOfUnpinnedObjects();
   }

   public void putAll(Map objs) {
      if (objs != null) {
         for(Map.Entry entry : objs.entrySet()) {
            this.put(entry.getKey(), (CachedPC)entry.getValue());
         }

      }
   }

   public synchronized CachedPC put(Object oid, CachedPC pc) {
      if (oid != null && pc != null) {
         if (this.maxSize >= 0 && this.getSize() == this.maxSize) {
            return null;
         } else {
            boolean toBePinned = false;
            if (this.pinnedClasses != null) {
               for(Level2Cache.PinnedClass pinCls : this.pinnedClasses) {
                  if (pinCls.cls.getName().equals(pc.getObjectClass().getName()) || pinCls.subclasses && pinCls.cls.isAssignableFrom(pc.getObjectClass())) {
                     toBePinned = true;
                     break;
                  }
               }
            }

            if (this.pinnedIds != null && this.pinnedIds.contains(oid)) {
               toBePinned = true;
            }

            Object obj = null;
            if (this.pinnedCache.get(oid) != null) {
               obj = this.pinnedCache.put(oid, pc);
               if (obj != null) {
                  return (CachedPC)obj;
               }
            } else if (toBePinned) {
               this.pinnedCache.put(oid, pc);
               this.unpinnedCache.remove(oid);
            } else {
               obj = this.unpinnedCache.put(oid, pc);
               if (obj != null) {
                  return (CachedPC)obj;
               }
            }

            return null;
         }
      } else {
         NucleusLogger.CACHE.warn(Localiser.msg("004011"));
         return null;
      }
   }

   public boolean containsOid(Object oid) {
      return this.pinnedCache.containsKey(oid) || this.unpinnedCache.containsKey(oid);
   }

   public boolean isEmpty() {
      return this.pinnedCache.isEmpty() && this.unpinnedCache.isEmpty();
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      out.defaultWriteObject();
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.unpinnedCache = new WeakValueMap();
   }
}
