package org.datanucleus.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import org.datanucleus.Configuration;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.query.cache.QueryResultsCache;
import org.datanucleus.util.NucleusLogger;

public class JavaxCacheQueryResultCache implements QueryResultsCache {
   private static final long serialVersionUID = -3967431477335678467L;
   private Cache cache;

   public JavaxCacheQueryResultCache(NucleusContext nucleusCtx) {
      Configuration conf = nucleusCtx.getConfiguration();
      String cacheName = conf.getStringProperty("datanucleus.cache.queryResults.cacheName");
      if (cacheName == null) {
         NucleusLogger.CACHE.warn("No 'datanucleus.cache.queryResults.cacheName' specified so using name of 'DataNucleus-Query'");
         cacheName = "datanucleus-query";
      }

      try {
         CachingProvider cacheProvider = Caching.getCachingProvider();
         CacheManager cacheMgr = cacheProvider.getCacheManager();
         Cache tmpcache = cacheMgr.getCache(cacheName);
         if (tmpcache == null) {
            javax.cache.configuration.Configuration cacheConfig = new MutableConfiguration();
            cacheMgr.createCache(cacheName, cacheConfig);
            tmpcache = cacheMgr.getCache(cacheName);
         }

         this.cache = tmpcache;
      } catch (CacheException e) {
         throw new NucleusException("Error creating cache", e);
      }
   }

   public void close() {
      this.evictAll();
      this.cache = null;
   }

   public boolean contains(String queryKey) {
      return this.get(queryKey) != null;
   }

   public void evict(Class candidate) {
   }

   public synchronized void evict(Query query) {
      String baseKey = QueryUtils.getKeyForQueryResultsCache(query, (Map)null);
      Iterator<Cache.Entry> entryIter = this.cache.iterator();

      while(entryIter.hasNext()) {
         Cache.Entry entry = (Cache.Entry)entryIter.next();
         String key = (String)entry.getKey();
         if (key.startsWith(baseKey)) {
            entryIter.remove();
         }
      }

   }

   public synchronized void evict(Query query, Map params) {
      String key = QueryUtils.getKeyForQueryResultsCache(query, params);
      this.cache.remove(key);
   }

   public synchronized void evictAll() {
      this.cache.removeAll();
   }

   public void pin(Query query, Map params) {
      throw new UnsupportedOperationException("This cache doesn't support pinning/unpinning");
   }

   public void pin(Query query) {
      throw new UnsupportedOperationException("This cache doesn't support pinning/unpinning");
   }

   public void unpin(Query query, Map params) {
      throw new UnsupportedOperationException("This cache doesn't support pinning/unpinning");
   }

   public void unpin(Query query) {
      throw new UnsupportedOperationException("This cache doesn't support pinning/unpinning");
   }

   public List get(String queryKey) {
      return (List)this.cache.get(queryKey);
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public synchronized List put(String queryKey, List results) {
      if (queryKey != null && results != null) {
         try {
            this.cache.put(queryKey, results);
         } catch (RuntimeException re) {
            NucleusLogger.CACHE.info("Query results with key '" + queryKey + "' not cached. " + re.getMessage());
         }

         return results;
      } else {
         return null;
      }
   }

   public int size() {
      throw new UnsupportedOperationException("size() method not supported by this plugin");
   }
}
