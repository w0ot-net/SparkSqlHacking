package org.datanucleus.store.query.cache;

import java.util.Map;

public class AbstractQueryDatastoreCompilationCache implements QueryDatastoreCompilationCache {
   Map cache;

   public void clear() {
      this.cache.clear();
   }

   public void close() {
      this.cache.clear();
      this.cache = null;
   }

   public boolean contains(String queryKey) {
      return this.cache.containsKey(queryKey);
   }

   public void evict(String queryKey) {
      this.cache.remove(queryKey);
   }

   public Object get(String queryKey) {
      return this.cache.get(queryKey);
   }

   public boolean isEmpty() {
      return this.cache.isEmpty();
   }

   public Object put(String queryKey, Object compilation) {
      return this.cache.put(queryKey, compilation);
   }

   public int size() {
      return this.cache.size();
   }
}
