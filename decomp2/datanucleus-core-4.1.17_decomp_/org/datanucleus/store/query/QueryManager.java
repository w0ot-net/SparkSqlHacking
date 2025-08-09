package org.datanucleus.store.query;

import java.util.List;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.query.cache.QueryCompilationCache;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.inmemory.InvocationEvaluator;
import org.datanucleus.store.query.cache.QueryDatastoreCompilationCache;
import org.datanucleus.store.query.cache.QueryResultsCache;

public interface QueryManager {
   void close();

   Query newQuery(String var1, ExecutionContext var2, Object var3);

   QueryCompilationCache getQueryCompilationCache();

   void addQueryCompilation(String var1, String var2, QueryCompilation var3);

   QueryCompilation getQueryCompilationForQuery(String var1, String var2);

   QueryDatastoreCompilationCache getQueryDatastoreCompilationCache();

   Object getDatastoreQueryCompilation(String var1, String var2, String var3);

   void addDatastoreQueryCompilation(String var1, String var2, String var3, Object var4);

   void deleteDatastoreQueryCompilation(String var1, String var2, String var3);

   QueryResultsCache getQueryResultsCache();

   List getQueryResult(Query var1, Map var2);

   void evictQueryResultsForType(Class var1);

   void addQueryResult(Query var1, Map var2, List var3);

   InvocationEvaluator getInMemoryEvaluatorForMethod(Class var1, String var2);
}
