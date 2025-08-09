package org.datanucleus.query.cache;

import java.util.Map;
import org.datanucleus.query.compiler.QueryCompilation;

public class AbstractQueryCompilationCache {
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

   public QueryCompilation get(String queryKey) {
      return (QueryCompilation)this.cache.get(queryKey);
   }

   public boolean isEmpty() {
      return this.cache.isEmpty();
   }

   public QueryCompilation put(String queryKey, QueryCompilation compilation) {
      return (QueryCompilation)this.cache.put(queryKey, compilation);
   }

   public int size() {
      return this.cache.size();
   }
}
