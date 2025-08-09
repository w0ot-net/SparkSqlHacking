package org.datanucleus.store.query.cache;

public interface QueryDatastoreCompilationCache {
   void close();

   void evict(String var1);

   void clear();

   boolean isEmpty();

   int size();

   Object get(String var1);

   Object put(String var1, Object var2);

   boolean contains(String var1);
}
