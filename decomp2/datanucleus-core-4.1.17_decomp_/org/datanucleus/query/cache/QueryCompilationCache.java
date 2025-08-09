package org.datanucleus.query.cache;

import org.datanucleus.query.compiler.QueryCompilation;

public interface QueryCompilationCache {
   void close();

   void evict(String var1);

   void clear();

   boolean isEmpty();

   int size();

   QueryCompilation get(String var1);

   QueryCompilation put(String var1, QueryCompilation var2);

   boolean contains(String var1);
}
