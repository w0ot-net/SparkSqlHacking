package org.datanucleus.store.query;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassConstants;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.plugin.PluginManager;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.cache.QueryCompilationCache;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.inmemory.ArrayContainsMethod;
import org.datanucleus.query.inmemory.ArraySizeMethod;
import org.datanucleus.query.inmemory.InvocationEvaluator;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.query.cache.QueryDatastoreCompilationCache;
import org.datanucleus.store.query.cache.QueryResultsCache;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class QueryManagerImpl implements QueryManager {
   protected NucleusContext nucleusCtx;
   protected StoreManager storeMgr;
   protected QueryCompilationCache queryCompilationCache = null;
   protected QueryDatastoreCompilationCache queryCompilationCacheDatastore = null;
   protected QueryResultsCache queryResultsCache = null;
   protected Map inmemoryQueryMethodEvaluatorMap = new ConcurrentHashMap();

   public QueryManagerImpl(NucleusContext nucleusContext, StoreManager storeMgr) {
      this.nucleusCtx = nucleusContext;
      this.storeMgr = storeMgr;
      Configuration conf = this.nucleusCtx.getConfiguration();
      String cacheType = conf.getStringProperty("datanucleus.cache.queryCompilation.type");
      if (cacheType != null && !cacheType.equalsIgnoreCase("none")) {
         String cacheClassName = this.nucleusCtx.getPluginManager().getAttributeValueForExtension("org.datanucleus.cache_query_compilation", "name", cacheType, "class-name");
         if (cacheClassName == null) {
            throw (new NucleusUserException(Localiser.msg("021500", cacheType))).setFatal();
         }

         try {
            this.queryCompilationCache = (QueryCompilationCache)this.nucleusCtx.getPluginManager().createExecutableExtension("org.datanucleus.cache_query_compilation", "name", cacheType, "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this.nucleusCtx});
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("021502", cacheClassName));
            }
         } catch (Exception e) {
            throw (new NucleusUserException(Localiser.msg("021501", cacheType, cacheClassName), e)).setFatal();
         }
      }

      cacheType = conf.getStringProperty("datanucleus.cache.queryCompilationDatastore.type");
      if (cacheType != null && !cacheType.equalsIgnoreCase("none")) {
         String cacheClassName = this.nucleusCtx.getPluginManager().getAttributeValueForExtension("org.datanucleus.cache_query_compilation_store", "name", cacheType, "class-name");
         if (cacheClassName == null) {
            throw (new NucleusUserException(Localiser.msg("021500", cacheType))).setFatal();
         }

         try {
            this.queryCompilationCacheDatastore = (QueryDatastoreCompilationCache)this.nucleusCtx.getPluginManager().createExecutableExtension("org.datanucleus.cache_query_compilation_store", "name", cacheType, "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this.nucleusCtx});
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("021502", cacheClassName));
            }
         } catch (Exception e) {
            throw (new NucleusUserException(Localiser.msg("021501", cacheType, cacheClassName), e)).setFatal();
         }
      }

      cacheType = conf.getStringProperty("datanucleus.cache.queryResults.type");
      if (cacheType != null && !cacheType.equalsIgnoreCase("none")) {
         String cacheClassName = this.nucleusCtx.getPluginManager().getAttributeValueForExtension("org.datanucleus.cache_query_result", "name", cacheType, "class-name");
         if (cacheClassName == null) {
            throw (new NucleusUserException(Localiser.msg("021500", cacheType))).setFatal();
         }

         try {
            this.queryResultsCache = (QueryResultsCache)this.nucleusCtx.getPluginManager().createExecutableExtension("org.datanucleus.cache_query_result", "name", cacheType, "class-name", new Class[]{ClassConstants.NUCLEUS_CONTEXT}, new Object[]{this.nucleusCtx});
            if (NucleusLogger.CACHE.isDebugEnabled()) {
               NucleusLogger.CACHE.debug(Localiser.msg("021502", cacheClassName));
            }
         } catch (Exception e) {
            throw (new NucleusUserException(Localiser.msg("021501", cacheType, cacheClassName), e)).setFatal();
         }
      }

   }

   public void close() {
      if (this.queryCompilationCache != null) {
         this.queryCompilationCache.close();
         this.queryCompilationCache = null;
      }

      if (this.queryCompilationCacheDatastore != null) {
         this.queryCompilationCacheDatastore.close();
         this.queryCompilationCacheDatastore = null;
      }

      if (this.queryResultsCache != null) {
         this.queryResultsCache.close();
         this.queryResultsCache = null;
      }

      this.inmemoryQueryMethodEvaluatorMap.clear();
      this.inmemoryQueryMethodEvaluatorMap = null;
   }

   public Query newQuery(String language, ExecutionContext ec, Object query) {
      if (language == null) {
         return null;
      } else {
         String languageImpl = language;

         try {
            if (query == null) {
               Class[] argsClass = new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.EXECUTION_CONTEXT};
               Object[] args = new Object[]{this.storeMgr, ec};
               Query q = (Query)ec.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_query_query", new String[]{"name", "datastore"}, new String[]{languageImpl, ec.getStoreManager().getStoreManagerKey()}, "class-name", argsClass, args);
               if (q == null) {
                  throw new NucleusException(Localiser.msg("021034", languageImpl, ec.getStoreManager().getStoreManagerKey()));
               } else {
                  return q;
               }
            } else {
               Query q = null;
               if (query instanceof String) {
                  Class[] argsClass = new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.EXECUTION_CONTEXT, String.class};
                  Object[] args = new Object[]{this.storeMgr, ec, query};
                  q = (Query)ec.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_query_query", new String[]{"name", "datastore"}, new String[]{languageImpl, ec.getStoreManager().getStoreManagerKey()}, "class-name", argsClass, args);
                  if (q == null) {
                     throw new NucleusException(Localiser.msg("021034", languageImpl, ec.getStoreManager().getStoreManagerKey()));
                  }
               } else if (query instanceof Query) {
                  Class[] argsClass = new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.EXECUTION_CONTEXT, query.getClass()};
                  Object[] args = new Object[]{this.storeMgr, ec, query};
                  q = (Query)ec.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_query_query", new String[]{"name", "datastore"}, new String[]{languageImpl, ec.getStoreManager().getStoreManagerKey()}, "class-name", argsClass, args);
                  if (q == null) {
                     throw new NucleusException(Localiser.msg("021034", languageImpl, ec.getStoreManager().getStoreManagerKey()));
                  }
               } else {
                  Class[] argsClass = new Class[]{ClassConstants.STORE_MANAGER, ClassConstants.EXECUTION_CONTEXT, Object.class};
                  Object[] args = new Object[]{this.storeMgr, ec, query};
                  q = (Query)ec.getNucleusContext().getPluginManager().createExecutableExtension("org.datanucleus.store_query_query", new String[]{"name", "datastore"}, new String[]{languageImpl, ec.getStoreManager().getStoreManagerKey()}, "class-name", argsClass, args);
                  if (q == null) {
                     throw new NucleusException(Localiser.msg("021034", languageImpl, ec.getStoreManager().getStoreManagerKey()));
                  }
               }

               return q;
            }
         } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t instanceof RuntimeException) {
               throw (RuntimeException)t;
            } else if (t instanceof Error) {
               throw (Error)t;
            } else {
               throw (new NucleusException(t.getMessage(), t)).setFatal();
            }
         } catch (Exception e) {
            throw (new NucleusException(e.getMessage(), e)).setFatal();
         }
      }
   }

   public synchronized QueryCompilationCache getQueryCompilationCache() {
      return this.queryCompilationCache;
   }

   public synchronized void addQueryCompilation(String language, String query, QueryCompilation compilation) {
      if (this.queryCompilationCache != null) {
         String queryKey = language + ":" + query;
         this.queryCompilationCache.put(queryKey, compilation);
      }

   }

   public synchronized QueryCompilation getQueryCompilationForQuery(String language, String query) {
      if (this.queryCompilationCache != null) {
         String queryKey = language + ":" + query;
         QueryCompilation compilation = this.queryCompilationCache.get(queryKey);
         if (compilation != null) {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021079", query, language));
            }

            return compilation;
         }
      }

      return null;
   }

   public synchronized QueryDatastoreCompilationCache getQueryDatastoreCompilationCache() {
      return this.queryCompilationCacheDatastore;
   }

   public synchronized void addDatastoreQueryCompilation(String datastore, String language, String query, Object compilation) {
      if (this.queryCompilationCacheDatastore != null) {
         String queryKey = language + ":" + query;
         this.queryCompilationCacheDatastore.put(queryKey, compilation);
      }

   }

   public synchronized void deleteDatastoreQueryCompilation(String datastore, String language, String query) {
      if (this.queryCompilationCacheDatastore != null) {
         String queryKey = language + ":" + query;
         this.queryCompilationCacheDatastore.evict(queryKey);
      }

   }

   public synchronized Object getDatastoreQueryCompilation(String datastore, String language, String query) {
      if (this.queryCompilationCacheDatastore != null) {
         String queryKey = language + ":" + query;
         Object compilation = this.queryCompilationCacheDatastore.get(queryKey);
         if (compilation != null) {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021080", query, language, datastore));
            }

            return compilation;
         }
      }

      return null;
   }

   public synchronized QueryResultsCache getQueryResultsCache() {
      return this.queryResultsCache;
   }

   public synchronized void evictQueryResultsForType(Class cls) {
      if (this.queryResultsCache != null) {
         this.queryResultsCache.evict(cls);
      }

   }

   public synchronized void addQueryResult(Query query, Map params, List results) {
      if (this.queryResultsCache != null) {
         String queryKey = QueryUtils.getKeyForQueryResultsCache(query, params);
         this.queryResultsCache.put(queryKey, results);
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021081", query, results.size()));
         }
      }

   }

   public synchronized List getQueryResult(Query query, Map params) {
      if (this.queryResultsCache != null) {
         String queryKey = QueryUtils.getKeyForQueryResultsCache(query, params);
         List<Object> results = this.queryResultsCache.get(queryKey);
         if (results != null && NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021082", query, results.size()));
         }

         return results;
      } else {
         return null;
      }
   }

   public InvocationEvaluator getInMemoryEvaluatorForMethod(Class type, String methodName) {
      if (type != null && type.isArray()) {
         if (methodName.equals("size") || methodName.equals("length")) {
            return new ArraySizeMethod();
         }

         if (methodName.equals("contains")) {
            return new ArrayContainsMethod();
         }
      }

      Map<Object, InvocationEvaluator> evaluatorsForMethod = (Map)this.inmemoryQueryMethodEvaluatorMap.get(methodName);
      if (evaluatorsForMethod != null) {
         for(Map.Entry entry : evaluatorsForMethod.entrySet()) {
            Object clsKey = entry.getKey();
            if (clsKey instanceof Class && ((Class)clsKey).isAssignableFrom(type)) {
               return (InvocationEvaluator)entry.getValue();
            }

            if (clsKey instanceof String && ((String)clsKey).equals("STATIC") && type == null) {
               return (InvocationEvaluator)entry.getValue();
            }
         }

         return null;
      } else {
         ClassLoaderResolver clr = this.nucleusCtx.getClassLoaderResolver(type != null ? type.getClassLoader() : null);
         PluginManager pluginMgr = this.nucleusCtx.getPluginManager();
         ConfigurationElement[] elems = pluginMgr.getConfigurationElementsForExtension("org.datanucleus.query_method_evaluators", "method", methodName);
         Map<Object, InvocationEvaluator> evaluators = new HashMap();
         InvocationEvaluator requiredEvaluator = null;
         if (elems == null) {
            return null;
         } else {
            for(int i = 0; i < elems.length; ++i) {
               try {
                  String evalName = elems[i].getAttribute("evaluator");
                  InvocationEvaluator eval = (InvocationEvaluator)pluginMgr.createExecutableExtension("org.datanucleus.query_method_evaluators", (String[])(new String[]{"method", "evaluator"}), (String[])(new String[]{methodName, evalName}), "evaluator", (Class[])null, (Object[])null);
                  String elemClsName = elems[i].getAttribute("class");
                  if (elemClsName != null && StringUtils.isWhitespace(elemClsName)) {
                     elemClsName = null;
                  }

                  if (elemClsName == null) {
                     if (type == null) {
                        requiredEvaluator = eval;
                     }

                     evaluators.put("STATIC", eval);
                  } else {
                     Class elemCls = clr.classForName(elemClsName);
                     if (elemCls.isAssignableFrom(type)) {
                        requiredEvaluator = eval;
                     }

                     evaluators.put(elemCls, eval);
                  }
               } catch (Exception var14) {
               }
            }

            this.inmemoryQueryMethodEvaluatorMap.put(methodName, evaluators);
            return requiredEvaluator;
         }
      }
   }
}
