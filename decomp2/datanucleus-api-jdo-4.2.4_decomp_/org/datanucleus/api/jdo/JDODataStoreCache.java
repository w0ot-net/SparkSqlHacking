package org.datanucleus.api.jdo;

import java.io.Serializable;
import java.util.Collection;
import javax.jdo.datastore.DataStoreCache;
import org.datanucleus.cache.Level2Cache;

public class JDODataStoreCache implements DataStoreCache, Serializable {
   private static final long serialVersionUID = 620081773711702087L;
   Level2Cache cache = null;

   public JDODataStoreCache(Level2Cache cache) {
      this.cache = cache;
   }

   public Level2Cache getLevel2Cache() {
      return this.cache;
   }

   public void evict(Object oid) {
      this.cache.evict(oid);
   }

   public void evictAll() {
      this.cache.evictAll();
   }

   public void evictAll(Object... oids) {
      this.cache.evictAll(oids);
   }

   public void evictAll(Collection oids) {
      this.cache.evictAll(oids);
   }

   /** @deprecated */
   public void evictAll(Class pcClass, boolean subclasses) {
      this.cache.evictAll(pcClass, subclasses);
   }

   public void evictAll(boolean subclasses, Class pcClass) {
      this.cache.evictAll(pcClass, subclasses);
   }

   public void pin(Object oid) {
      this.cache.pin(oid);
   }

   public void pinAll(Collection oids) {
      this.cache.pinAll(oids);
   }

   public void pinAll(Object... oids) {
      this.cache.pinAll(oids);
   }

   /** @deprecated */
   public void pinAll(Class pcClass, boolean subclasses) {
      this.cache.pinAll(pcClass, subclasses);
   }

   public void pinAll(boolean subclasses, Class pcClass) {
      this.cache.pinAll(pcClass, subclasses);
   }

   public void unpin(Object oid) {
      this.cache.unpin(oid);
   }

   public void unpinAll(Collection oids) {
      this.cache.unpinAll(oids);
   }

   public void unpinAll(Object... oids) {
      this.cache.unpinAll(oids);
   }

   /** @deprecated */
   public void unpinAll(Class pcClass, boolean subclasses) {
      this.cache.unpinAll(pcClass, subclasses);
   }

   public void unpinAll(boolean subclasses, Class pcClass) {
      this.cache.unpinAll(pcClass, subclasses);
   }
}
