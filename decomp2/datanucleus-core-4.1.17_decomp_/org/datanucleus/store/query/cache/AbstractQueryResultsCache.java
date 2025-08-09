package org.datanucleus.store.query.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.NucleusContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.store.query.Query;
import org.datanucleus.util.NucleusLogger;

public class AbstractQueryResultsCache implements QueryResultsCache {
   private static final long serialVersionUID = -1071931192920096219L;
   Set keysToPin = new HashSet();
   Map pinnedCache = new HashMap();
   Map cache = null;
   private int maxSize = -1;
   private final NucleusContext nucCtx;

   public AbstractQueryResultsCache(NucleusContext nucleusCtx) {
      this.maxSize = nucleusCtx.getConfiguration().getIntProperty("datanucleus.cache.queryResults.maxSize");
      this.nucCtx = nucleusCtx;
   }

   public void close() {
      this.cache.clear();
      this.cache = null;
      this.pinnedCache.clear();
      this.pinnedCache = null;
   }

   public boolean contains(String queryKey) {
      return this.cache.containsKey(queryKey);
   }

   public synchronized void evict(Class candidate) {
      AbstractClassMetaData cmd = this.nucCtx.getMetaDataManager().getMetaDataForClass(candidate, this.nucCtx.getClassLoaderResolver(candidate.getClassLoader()));
      Iterator<String> iter = this.cache.keySet().iterator();

      while(iter.hasNext()) {
         String key = (String)iter.next();
         if (key.matches("JDOQL:.* FROM " + candidate.getName() + ".*")) {
            NucleusLogger.GENERAL.info(">> Evicting query results for key=" + key);
            iter.remove();
         } else if (key.matches("JPQL:.* FROM " + candidate.getName() + ".*")) {
            NucleusLogger.GENERAL.info(">> Evicting query results for key=" + key);
            iter.remove();
         } else if (key.matches("JPQL:.* FROM " + cmd.getEntityName() + ".*")) {
            NucleusLogger.GENERAL.info(">> Evicting query results for key=" + key);
            iter.remove();
         }
      }

   }

   public synchronized void evictAll() {
      this.cache.clear();
   }

   public synchronized void evict(Query query) {
      String baseKey = QueryUtils.getKeyForQueryResultsCache(query, (Map)null);
      Iterator<String> iter = this.cache.keySet().iterator();

      while(iter.hasNext()) {
         String key = (String)iter.next();
         if (key.startsWith(baseKey)) {
            iter.remove();
         }
      }

      iter = this.pinnedCache.keySet().iterator();

      while(iter.hasNext()) {
         String key = (String)iter.next();
         if (key.startsWith(baseKey)) {
            iter.remove();
         }
      }

   }

   public synchronized void evict(Query query, Map params) {
      String key = QueryUtils.getKeyForQueryResultsCache(query, params);
      this.cache.remove(key);
      this.pinnedCache.remove(key);
   }

   public void pin(Query query, Map params) {
      String key = QueryUtils.getKeyForQueryResultsCache(query, params);
      List<Object> results = (List)this.cache.get(key);
      if (results != null) {
         this.keysToPin.add(key);
         this.pinnedCache.put(key, results);
         this.cache.remove(key);
      }

   }

   public void pin(Query query) {
      String key = QueryUtils.getKeyForQueryResultsCache(query, (Map)null);
      List<Object> results = (List)this.cache.get(key);
      if (results != null) {
         this.keysToPin.add(key);
         this.pinnedCache.put(key, results);
         this.cache.remove(key);
      }

   }

   public void unpin(Query query, Map params) {
      String key = QueryUtils.getKeyForQueryResultsCache(query, params);
      List<Object> results = (List)this.pinnedCache.get(key);
      if (results != null) {
         this.keysToPin.remove(key);
         this.cache.put(key, results);
         this.pinnedCache.remove(key);
      }

   }

   public void unpin(Query query) {
      String key = QueryUtils.getKeyForQueryResultsCache(query, (Map)null);
      List<Object> results = (List)this.pinnedCache.get(key);
      if (results != null) {
         this.keysToPin.remove(key);
         this.cache.put(key, results);
         this.pinnedCache.remove(key);
      }

   }

   public List get(String queryKey) {
      return this.pinnedCache.containsKey(queryKey) ? (List)this.pinnedCache.get(queryKey) : (List)this.cache.get(queryKey);
   }

   public boolean isEmpty() {
      return this.cache.isEmpty();
   }

   public synchronized List put(String queryKey, List results) {
      if (this.maxSize >= 0 && this.size() == this.maxSize) {
         return null;
      } else {
         return this.keysToPin.contains(queryKey) ? (List)this.pinnedCache.put(queryKey, results) : (List)this.cache.put(queryKey, results);
      }
   }

   public int size() {
      return this.cache.size();
   }
}
