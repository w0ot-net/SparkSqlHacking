package org.datanucleus.api.jdo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jdo.Extent;
import javax.jdo.FetchPlan;
import javax.jdo.JDODataStoreException;
import javax.jdo.JDOException;
import javax.jdo.JDOQueryInterruptedException;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.spi.JDOPermission;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.QueryMetaData;
import org.datanucleus.store.query.NoQueryResultsException;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.query.QueryInterruptedException;
import org.datanucleus.store.query.QueryTimeoutException;

public class JDOQuery implements javax.jdo.Query {
   private static final long serialVersionUID = -204134873012573162L;
   transient PersistenceManager pm;
   Query query;
   String language;
   JDOFetchPlan fetchPlan = null;
   Map parameterValueByName = null;
   Object[] parameterValues = null;

   public JDOQuery(PersistenceManager pm, Query query, String language) {
      this.pm = pm;
      this.query = query;
      this.language = language;
   }

   public void close() {
      this.closeAll();
      if (this.fetchPlan != null) {
         this.fetchPlan.clearGroups();
         this.fetchPlan = null;
      }

   }

   public void close(Object queryResult) {
      this.query.close(queryResult);
   }

   public void closeAll() {
      this.query.closeAll();
   }

   public void compile() {
      try {
         this.query.compile();
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void declareImports(String imports) {
      try {
         this.query.declareImports(imports);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void declareParameters(String parameters) {
      try {
         this.query.declareExplicitParameters(parameters);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void declareVariables(String variables) {
      try {
         this.query.declareExplicitVariables(variables);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public javax.jdo.Query imports(String imports) {
      this.declareImports(imports);
      return this;
   }

   public javax.jdo.Query parameters(String parameters) {
      this.declareParameters(parameters);
      return this;
   }

   public javax.jdo.Query variables(String variables) {
      this.declareVariables(variables);
      return this;
   }

   public javax.jdo.Query setParameters(Object... paramValues) {
      this.parameterValueByName = null;
      this.parameterValues = paramValues;
      return this;
   }

   public javax.jdo.Query setNamedParameters(Map paramMap) {
      this.parameterValueByName = paramMap;
      this.parameterValues = null;
      return this;
   }

   public Object execute() {
      this.parameterValueByName = null;
      this.parameterValues = null;
      return this.executeInternal();
   }

   public Object execute(Object p1) {
      this.parameterValueByName = null;
      this.parameterValues = new Object[]{p1};
      return this.executeInternal();
   }

   public Object execute(Object p1, Object p2) {
      this.parameterValueByName = null;
      this.parameterValues = new Object[]{p1, p2};
      return this.executeInternal();
   }

   public Object execute(Object p1, Object p2, Object p3) {
      this.parameterValueByName = null;
      this.parameterValues = new Object[]{p1, p2, p3};
      return this.executeInternal();
   }

   public Object executeWithArray(Object... parameterValues) {
      this.parameterValueByName = null;
      this.parameterValues = parameterValues;
      return this.executeInternal();
   }

   public Object executeWithMap(Map parameters) {
      this.parameterValueByName = parameters;
      this.parameterValues = null;
      return this.executeInternal();
   }

   public List executeList() {
      if (this.query.getResult() != null) {
         throw new JDOUserException("Cannot call executeXXX method when query has result set to " + this.query.getResult() + ". Use executeResultList() instead");
      } else {
         return (List)this.executeInternal();
      }
   }

   public Object executeUnique() {
      if (this.query.getResult() != null) {
         throw new JDOUserException("Cannot call executeXXX method when query has result set to " + this.query.getResult() + ". Use executeResultUnique() instead");
      } else {
         return this.executeInternal();
      }
   }

   public List executeResultList(Class resultCls) {
      if (this.query.getResult() == null) {
         throw new JDOUserException("Cannot call executeResultList method when query has result unset. Call executeList instead.");
      } else {
         this.query.setResultClass(resultCls);
         return (List)this.executeInternal();
      }
   }

   public Object executeResultUnique(Class resultCls) {
      if (this.query.getResult() == null) {
         throw new JDOUserException("Cannot call executeResultUnique method when query has result unset. Call executeUnique instead.");
      } else {
         this.query.setResultClass(resultCls);
         return this.executeInternal();
      }
   }

   public List executeResultList() {
      if (this.query.getResult() == null) {
         throw new JDOUserException("Cannot call executeResultList method when query has result unset. Call executeList instead.");
      } else {
         return (List)this.executeInternal();
      }
   }

   public Object executeResultUnique() {
      if (this.query.getResult() == null) {
         throw new JDOUserException("Cannot call executeResultUnique method when query has result unset. Call executeUnique instead.");
      } else {
         return this.executeInternal();
      }
   }

   protected Object executeInternal() {
      Object var1;
      try {
         if (this.parameterValues == null) {
            if (this.parameterValueByName != null) {
               var1 = this.query.executeWithMap(this.parameterValueByName);
               return var1;
            }

            var1 = this.query.execute();
            return var1;
         }

         var1 = this.query.executeWithArray(this.parameterValues);
      } catch (NoQueryResultsException var9) {
         Object var2 = null;
         return var2;
      } catch (QueryTimeoutException qte) {
         throw new JDODataStoreException("Query has timed out : " + qte.getMessage());
      } catch (QueryInterruptedException qie) {
         throw new JDOQueryInterruptedException("Query has been cancelled : " + qie.getMessage());
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      } finally {
         this.parameterValueByName = null;
         this.parameterValues = null;
      }

      return var1;
   }

   public long deletePersistentAll() {
      return this.deletePersistentInternal();
   }

   public long deletePersistentAll(Object... parameters) {
      this.parameterValueByName = null;
      this.parameterValues = parameters;
      return this.deletePersistentInternal();
   }

   public long deletePersistentAll(Map parameters) {
      this.parameterValueByName = parameters;
      this.parameterValues = null;
      return this.deletePersistentInternal();
   }

   protected long deletePersistentInternal() {
      long var1;
      try {
         if (this.parameterValues == null) {
            if (this.parameterValueByName != null) {
               var1 = this.query.deletePersistentAll(this.parameterValueByName);
               return var1;
            }

            var1 = this.query.deletePersistentAll();
            return var1;
         }

         var1 = this.query.deletePersistentAll(this.parameterValues);
      } catch (NoQueryResultsException var10) {
         long var2 = 0L;
         return var2;
      } catch (QueryTimeoutException qte) {
         throw new JDODataStoreException("Query has timed out : " + qte.getMessage());
      } catch (QueryInterruptedException qie) {
         throw new JDOQueryInterruptedException("Query has been cancelled : " + qie.getMessage());
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      } finally {
         this.parameterValueByName = null;
         this.parameterValues = null;
      }

      return var1;
   }

   public void cancelAll() {
      try {
         this.query.cancel();
      } catch (NucleusException ne) {
         throw new JDOException("Error in calling Query.cancelAll. See the nested exception", ne);
      } catch (UnsupportedOperationException var3) {
         throw new JDOUnsupportedOptionException();
      }
   }

   public void cancel(Thread thr) {
      try {
         this.query.cancel(thr);
      } catch (NucleusException ne) {
         throw new JDOException("Error in calling Query.cancelAll. See the nested exception", ne);
      } catch (UnsupportedOperationException var4) {
         throw new JDOUnsupportedOptionException();
      }
   }

   public void setCandidates(Extent extent) {
      try {
         if (extent == null) {
            this.query.setCandidates((org.datanucleus.store.Extent)null);
         } else {
            this.query.setCandidates(((JDOExtent)extent).getExtent());
         }

      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void setCandidates(Collection pcs) {
      try {
         this.query.setCandidates(pcs);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void setClass(Class candidateClass) {
      try {
         this.query.setCandidateClass(candidateClass);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void addExtension(String key, Object value) {
      this.query.addExtension(key, value);
   }

   public void setExtensions(Map extensions) {
      this.query.setExtensions(extensions);
   }

   public javax.jdo.Query extension(String key, Object value) {
      this.addExtension(key, value);
      return this;
   }

   public javax.jdo.Query extensions(Map values) {
      this.setExtensions(values);
      return this;
   }

   public FetchPlan getFetchPlan() {
      if (this.fetchPlan == null) {
         this.fetchPlan = new JDOFetchPlan(this.query.getFetchPlan());
      }

      return this.fetchPlan;
   }

   public javax.jdo.Query filter(String filter) {
      this.setFilter(filter);
      return this;
   }

   public void setFilter(String filter) {
      try {
         this.query.setFilter(filter);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public javax.jdo.Query groupBy(String grouping) {
      this.setGrouping(grouping);
      return this;
   }

   public void setGrouping(String grouping) {
      try {
         this.query.setGrouping(grouping);
      } catch (NucleusException ne) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(ne);
      }
   }

   public boolean getIgnoreCache() {
      return this.query.getIgnoreCache();
   }

   public void setIgnoreCache(boolean ignoreCache) {
      this.query.setIgnoreCache(ignoreCache);
   }

   public javax.jdo.Query ignoreCache(boolean flag) {
      this.setIgnoreCache(flag);
      return this;
   }

   public javax.jdo.Query orderBy(String ordering) {
      this.setOrdering(ordering);
      return this;
   }

   public void setOrdering(String ordering) {
      try {
         this.query.setOrdering(ordering);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public PersistenceManager getPersistenceManager() {
      return this.pm;
   }

   public javax.jdo.Query range(long fromIncl, long toExcl) {
      this.setRange(fromIncl, toExcl);
      return this;
   }

   public javax.jdo.Query range(String range) {
      this.setRange(range);
      return this;
   }

   public void setRange(String range) {
      try {
         this.query.setRange(range);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void setRange(long fromIncl, long toExcl) {
      try {
         this.query.setRange(fromIncl, toExcl);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public javax.jdo.Query result(String result) {
      this.setResult(result);
      return this;
   }

   public void setResult(String result) {
      try {
         this.query.setResult(result);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void setResultClass(Class result_cls) {
      try {
         this.query.setResultClass(result_cls);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public void setDatastoreReadTimeoutMillis(Integer timeout) {
      try {
         this.query.setDatastoreReadTimeoutMillis(timeout);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public Integer getDatastoreReadTimeoutMillis() {
      return this.query.getDatastoreReadTimeoutMillis();
   }

   public void setDatastoreWriteTimeoutMillis(Integer timeout) {
      try {
         this.query.setDatastoreWriteTimeoutMillis(timeout);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public Integer getDatastoreWriteTimeoutMillis() {
      return this.query.getDatastoreWriteTimeoutMillis();
   }

   public javax.jdo.Query datastoreReadTimeoutMillis(Integer interval) {
      this.setDatastoreReadTimeoutMillis(interval);
      return this;
   }

   public javax.jdo.Query datastoreWriteTimeoutMillis(Integer interval) {
      this.setDatastoreWriteTimeoutMillis(interval);
      return this;
   }

   public void setUnique(boolean unique) {
      try {
         this.query.setUnique(unique);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public boolean isUnmodifiable() {
      return this.query.isUnmodifiable();
   }

   public void setUnmodifiable() {
      this.query.setUnmodifiable();
   }

   public javax.jdo.Query unmodifiable() {
      this.setUnmodifiable();
      return this;
   }

   public void addSubquery(javax.jdo.Query sub, String variableDecl, String candidateExpr) {
      this.addSubquery(sub, variableDecl, candidateExpr, (Map)null);
   }

   public void addSubquery(javax.jdo.Query sub, String variableDecl, String candidateExpr, String parameter) {
      Map paramMap = new HashMap();
      if (parameter != null) {
         paramMap.put(0, parameter);
      }

      this.addSubquery(sub, variableDecl, candidateExpr, paramMap);
   }

   public void addSubquery(javax.jdo.Query sub, String variableDecl, String candidateExpr, String... parameters) {
      Map paramMap = new HashMap();
      if (parameters != null) {
         for(int i = 0; i < parameters.length; ++i) {
            paramMap.put(i, parameters[i]);
         }
      }

      this.addSubquery(sub, variableDecl, candidateExpr, paramMap);
   }

   public void addSubquery(javax.jdo.Query sub, String variableDecl, String candidateExpr, Map parameters) {
      try {
         Query subquery = null;
         if (sub != null) {
            subquery = ((JDOQuery)sub).query;
         }

         this.query.addSubquery(subquery, variableDecl, candidateExpr, parameters);
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public javax.jdo.Query subquery(javax.jdo.Query sub, String variableDecl, String candidateExpr) {
      this.addSubquery(sub, variableDecl, candidateExpr);
      return this;
   }

   public javax.jdo.Query subquery(javax.jdo.Query sub, String variableDecl, String candidateExpr, String parameter) {
      this.addSubquery(sub, variableDecl, candidateExpr, parameter);
      return this;
   }

   public javax.jdo.Query subquery(javax.jdo.Query sub, String variableDecl, String candidateExpr, String... parameters) {
      this.addSubquery(sub, variableDecl, candidateExpr, parameters);
      return this;
   }

   public javax.jdo.Query subquery(javax.jdo.Query sub, String variableDecl, String candidateExpr, Map parameters) {
      this.addSubquery(sub, variableDecl, candidateExpr, parameters);
      return this;
   }

   public Boolean getSerializeRead() {
      return this.query.getSerializeRead();
   }

   public void setSerializeRead(Boolean serialize) {
      this.query.setSerializeRead(serialize);
   }

   public javax.jdo.Query serializeRead(Boolean serialize) {
      this.setSerializeRead(serialize);
      return this;
   }

   public Query getInternalQuery() {
      return this.query;
   }

   public String getLanguage() {
      return this.language;
   }

   public javax.jdo.Query saveAsNamedQuery(String name) {
      JDOPersistenceManagerFactory.checkJDOPermission(JDOPermission.GET_METADATA);
      String queryName = null;
      if (this.query.getCandidateClassName() != null) {
         queryName = this.query.getCandidateClassName() + "_" + name;
      } else {
         queryName = name;
      }

      QueryMetaData qmd = new QueryMetaData(queryName);
      qmd.setLanguage(this.language);
      qmd.setQuery(this.query.toString());
      qmd.setResultClass(this.query.getResultClassName());
      qmd.setUnique(this.query.isUnique());
      Map<String, Object> queryExts = this.query.getExtensions();
      if (queryExts != null && !queryExts.isEmpty()) {
         for(Map.Entry queryExtEntry : queryExts.entrySet()) {
            qmd.addExtension((String)queryExtEntry.getKey(), "" + queryExtEntry.getValue());
         }
      }

      this.query.getExecutionContext().getMetaDataManager().registerNamedQuery(qmd);
      return this;
   }

   public String toString() {
      return this.query.toString();
   }

   public Object getNativeQuery() {
      return this.query.getNativeQuery();
   }
}
