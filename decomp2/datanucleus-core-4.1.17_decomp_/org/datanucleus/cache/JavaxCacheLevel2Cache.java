package org.datanucleus.cache;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import org.datanucleus.Configuration;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.util.NucleusLogger;

public class JavaxCacheLevel2Cache extends AbstractLevel2Cache {
   private static final long serialVersionUID = 3218890128547271239L;
   private final Cache cache;

   public JavaxCacheLevel2Cache(NucleusContext nucleusCtx) {
      super(nucleusCtx);

      try {
         CachingProvider cacheProvider = Caching.getCachingProvider();
         CacheManager cacheMgr = cacheProvider.getCacheManager();
         Cache tmpcache = cacheMgr.getCache(this.cacheName);
         if (tmpcache == null) {
            MutableConfiguration cacheConfig = new MutableConfiguration();
            Configuration conf = nucleusCtx.getConfiguration();
            if (conf.hasProperty("datanucleus.cache.level2.readThrough")) {
               cacheConfig.setReadThrough(conf.getBooleanProperty("datanucleus.cache.level2.readThrough"));
            }

            if (conf.hasProperty("datanucleus.cache.level2.writeThrough")) {
               cacheConfig.setWriteThrough(conf.getBooleanProperty("datanucleus.cache.level2.writeThrough"));
            }

            if (conf.hasProperty("datanucleus.cache.level2.statisticsEnabled")) {
               cacheConfig.setStatisticsEnabled(conf.getBooleanProperty("datanucleus.cache.level2.statisticsEnabled"));
            }

            if (conf.hasProperty("datanucleus.cache.level2.storeByValue")) {
               cacheConfig.setStoreByValue(conf.getBooleanProperty("datanucleus.cache.level2.storeByValue"));
            }

            if (this.timeout > 0L) {
            }

            cacheMgr.createCache(this.cacheName, cacheConfig);
            tmpcache = cacheMgr.getCache(this.cacheName);
         }

         this.cache = tmpcache;
      } catch (CacheException e) {
         throw new NucleusException("Error creating cache", e);
      }
   }

   public void close() {
      if (this.clearAtClose) {
         this.evictAll();
      }

   }

   public boolean containsOid(Object oid) {
      return this.get(oid) != null;
   }

   public CachedPC get(Object oid) {
      try {
         return (CachedPC)this.cache.get(oid);
      } catch (Exception e) {
         NucleusLogger.CACHE.info("Object with id " + oid + " not retrieved from cache due to : " + e.getMessage());
         return null;
      }
   }

   public Map getAll(Collection oids) {
      try {
         return oids instanceof Set ? this.cache.getAll((Set)oids) : this.cache.getAll(new HashSet(oids));
      } catch (Exception e) {
         NucleusLogger.CACHE.info("Objects not retrieved from cache due to : " + e.getMessage());
         return null;
      }
   }

   public int getSize() {
      return 0;
   }

   public boolean isEmpty() {
      return this.getSize() == 0;
   }

   public synchronized CachedPC put(Object oid, CachedPC pc) {
      if (oid != null && pc != null) {
         if (this.maxSize >= 0 && this.getSize() == this.maxSize) {
            return null;
         } else {
            try {
               this.cache.put(oid, pc);
            } catch (Exception e) {
               NucleusLogger.CACHE.info("Object with id " + oid + " not cached due to : " + e.getMessage());
            }

            return pc;
         }
      } else {
         return null;
      }
   }

   public void putAll(Map objs) {
      if (objs != null) {
         try {
            this.cache.putAll(objs);
         } catch (Exception e) {
            NucleusLogger.CACHE.info("Objects not cached due to : " + e.getMessage());
         }

      }
   }

   public synchronized void evict(Object oid) {
      try {
         this.cache.remove(oid);
      } catch (RuntimeException re) {
         NucleusLogger.CACHE.info("Object with id=" + oid + " not evicted from cache due to : " + re.getMessage());
      }

   }

   public synchronized void evictAll() {
      try {
         this.cache.removeAll();
      } catch (RuntimeException re) {
         NucleusLogger.CACHE.info("Objects not evicted from cache due to : " + re.getMessage());
      }

   }

   public synchronized void evictAll(Collection oids) {
      if (oids != null) {
         try {
            if (oids instanceof Set) {
               this.cache.removeAll((Set)oids);
            } else {
               this.cache.removeAll(new HashSet(oids));
            }
         } catch (RuntimeException re) {
            NucleusLogger.CACHE.info("Objects not evicted from cache due to : " + re.getMessage());
         }

      }
   }

   public synchronized void evictAll(Object[] oids) {
      if (oids != null) {
         try {
            Set oidSet = new HashSet(Arrays.asList(oids));
            this.cache.removeAll(oidSet);
         } catch (RuntimeException re) {
            NucleusLogger.CACHE.info("Objects not evicted from cache due to : " + re.getMessage());
         }

      }
   }

   public synchronized void evictAll(Class pcClass, boolean subclasses) {
      if (this.nucleusCtx.getApiAdapter().isPersistable(pcClass)) {
         this.evictAllOfClass(pcClass.getName());
         if (subclasses) {
            String[] subclassNames = this.nucleusCtx.getMetaDataManager().getSubclassesForClass(pcClass.getName(), true);
            if (subclassNames != null) {
               for(int i = 0; i < subclassNames.length; ++i) {
                  this.evictAllOfClass(subclassNames[i]);
               }
            }
         }

      }
   }

   void evictAllOfClass(String className) {
      try {
         AbstractClassMetaData cmd = this.nucleusCtx.getMetaDataManager().getMetaDataForClass(className, this.nucleusCtx.getClassLoaderResolver((ClassLoader)null));
         Iterator<Cache.Entry> entryIter = this.cache.iterator();

         while(entryIter.hasNext()) {
            Cache.Entry entry = (Cache.Entry)entryIter.next();
            Object key = entry.getKey();
            if (cmd.getIdentityType() == IdentityType.APPLICATION) {
               String targetClassName = IdentityUtils.getTargetClassNameForIdentitySimple(key);
               if (className.equals(targetClassName)) {
                  entryIter.remove();
               }
            } else if (cmd.getIdentityType() == IdentityType.DATASTORE) {
               String targetClassName = IdentityUtils.getTargetClassNameForIdentitySimple(key);
               if (className.equals(targetClassName)) {
                  entryIter.remove();
               }
            }
         }
      } catch (RuntimeException re) {
         NucleusLogger.CACHE.info("Objects not evicted from cache due to : " + re.getMessage());
      }

   }
}
