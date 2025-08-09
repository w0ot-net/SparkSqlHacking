package org.datanucleus.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.datanucleus.Configuration;
import org.datanucleus.NucleusContext;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractLevel2Cache implements Level2Cache {
   private static final long serialVersionUID = 7737532122953947585L;
   protected NucleusContext nucleusCtx;
   protected int maxSize = -1;
   protected boolean clearAtClose = true;
   protected long timeout = -1L;
   protected String cacheName;

   public AbstractLevel2Cache(NucleusContext nucleusCtx) {
      this.nucleusCtx = nucleusCtx;
      Configuration conf = nucleusCtx.getConfiguration();
      this.maxSize = conf.getIntProperty("datanucleus.cache.level2.maxSize");
      this.clearAtClose = conf.getBooleanProperty("datanucleus.cache.level2.clearAtClose", true);
      if (conf.hasProperty("datanucleus.cache.level2.timeout")) {
         this.timeout = (long)conf.getIntProperty("datanucleus.cache.level2.timeout");
      }

      this.cacheName = conf.getStringProperty("datanucleus.cache.level2.cacheName");
      if (this.cacheName == null) {
         NucleusLogger.CACHE.warn("No 'datanucleus.cache.level2.cacheName' specified so using name of 'dataNucleus'");
         this.cacheName = "dataNucleus";
      }

   }

   public Map getAll(Collection oids) {
      if (oids == null) {
         return null;
      } else {
         Map<Object, CachedPC> objs = new HashMap();

         for(Object id : oids) {
            CachedPC value = this.get(id);
            objs.put(id, value);
         }

         return objs;
      }
   }

   public void putAll(Map objs) {
      if (objs != null) {
         for(Map.Entry entry : objs.entrySet()) {
            this.put(entry.getKey(), (CachedPC)entry.getValue());
         }

      }
   }

   public boolean isEmpty() {
      return this.getSize() == 0;
   }

   public int getNumberOfPinnedObjects() {
      return 0;
   }

   public int getNumberOfUnpinnedObjects() {
      return 0;
   }

   public void pin(Object arg0) {
   }

   public void pinAll(Collection arg0) {
   }

   public void pinAll(Object[] arg0) {
   }

   public void pinAll(Class arg0, boolean arg1) {
   }

   public void unpin(Object arg0) {
   }

   public void unpinAll(Collection arg0) {
   }

   public void unpinAll(Object[] arg0) {
   }

   public void unpinAll(Class arg0, boolean arg1) {
   }
}
