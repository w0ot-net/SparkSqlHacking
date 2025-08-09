package org.datanucleus.api.jdo;

import java.io.Serializable;
import java.util.Map;
import javax.jdo.Query;
import org.datanucleus.store.query.cache.QueryResultsCache;

public class JDOQueryCache implements Serializable {
   private static final long serialVersionUID = -6836991171780739390L;
   QueryResultsCache resultsCache;

   public JDOQueryCache(QueryResultsCache cache) {
      this.resultsCache = cache;
   }

   public QueryResultsCache getQueryCache() {
      return this.resultsCache;
   }

   public void evict(Query query) {
      this.resultsCache.evict(((JDOQuery)query).getInternalQuery());
   }

   public void evict(Query query, Map params) {
      this.resultsCache.evict(((JDOQuery)query).getInternalQuery(), params);
   }

   public void evictAll() {
      this.resultsCache.evictAll();
   }

   public void pin(Query query) {
      this.resultsCache.pin(((JDOQuery)query).getInternalQuery());
   }

   public void pin(Query query, Map params) {
      this.resultsCache.pin(((JDOQuery)query).getInternalQuery(), params);
   }

   public void unpin(Query query) {
      this.resultsCache.unpin(((JDOQuery)query).getInternalQuery());
   }

   public void unpin(Query query, Map params) {
      this.resultsCache.unpin(((JDOQuery)query).getInternalQuery(), params);
   }
}
