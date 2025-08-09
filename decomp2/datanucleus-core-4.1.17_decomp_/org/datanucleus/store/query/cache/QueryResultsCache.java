package org.datanucleus.store.query.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.datanucleus.store.query.Query;

public interface QueryResultsCache extends Serializable {
   void close();

   void evict(Class var1);

   void evict(Query var1);

   void evict(Query var1, Map var2);

   void evictAll();

   void pin(Query var1);

   void pin(Query var1, Map var2);

   void unpin(Query var1);

   void unpin(Query var1, Map var2);

   boolean isEmpty();

   int size();

   List get(String var1);

   List put(String var1, List var2);

   boolean contains(String var1);
}
