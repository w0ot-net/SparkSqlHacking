package org.datanucleus.store.query;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.ExecutionContextListener;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUnsupportedOptionException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.TransactionNotActiveException;
import org.datanucleus.exceptions.TransactionNotReadableException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.QueryResultMetaData;
import org.datanucleus.query.JDOQLQueryHelper;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.store.Extent;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.Imports;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class Query implements Serializable, ExecutionContextListener {
   private static final long serialVersionUID = 7820102897590182771L;
   public static final String EXTENSION_FLUSH_BEFORE_EXECUTION = "datanucleus.query.flushBeforeExecution";
   public static final String EXTENSION_USE_FETCH_PLAN = "datanucleus.query.useFetchPlan";
   public static final String EXTENSION_RESULT_SIZE_METHOD = "datanucleus.query.resultSizeMethod";
   public static final String EXTENSION_LOAD_RESULTS_AT_COMMIT = "datanucleus.query.loadResultsAtCommit";
   public static final String EXTENSION_COMPILATION_CACHED = "datanucleus.query.compilation.cached";
   public static final String EXTENSION_RESULTS_CACHED = "datanucleus.query.results.cached";
   public static final String EXTENSION_EVALUATE_IN_MEMORY = "datanucleus.query.evaluateInMemory";
   public static final String EXTENSION_CHECK_UNUSED_PARAMETERS = "datanucleus.query.checkUnusedParameters";
   public static final String EXTENSION_EXCLUDE_SUBCLASSES = "datanucleus.query.excludeSubclasses";
   public static final String EXTENSION_MULTITHREAD = "datanucleus.query.multithread";
   public static final String EXTENSION_RESULT_CACHE_TYPE = "datanucleus.query.resultCacheType";
   public static final String EXTENSION_CLOSE_RESULTS_AT_EC_CLOSE = "datanucleus.query.closeResultsAtManagerClose";
   public static final String EXTENSION_JDOQL_STRICT = "datanucleus.jdoql.strict";
   public static final String EXTENSION_JPQL_STRICT = "datanucleus.jpql.strict";
   protected final transient StoreManager storeMgr;
   protected transient ExecutionContext ec;
   protected final transient ClassLoaderResolver clr;
   public static final short SELECT = 0;
   public static final short BULK_UPDATE = 1;
   public static final short BULK_DELETE = 2;
   public static final short OTHER = 3;
   protected short type = 0;
   protected Class candidateClass;
   protected String candidateClassName;
   protected boolean subclasses = true;
   protected transient String from = null;
   protected transient String update = null;
   protected String result = null;
   protected boolean resultDistinct = false;
   protected boolean unique = false;
   protected Class resultClass = null;
   protected String resultClassName = null;
   protected String filter;
   protected String ordering;
   protected String grouping;
   protected String having;
   protected String imports;
   protected String explicitVariables;
   protected String explicitParameters;
   protected String range;
   protected long fromInclNo = 0L;
   protected long toExclNo = Long.MAX_VALUE;
   protected String fromInclParam = null;
   protected String toExclParam = null;
   protected boolean unmodifiable = false;
   protected boolean ignoreCache = false;
   private FetchPlan fetchPlan;
   private Boolean serializeRead = null;
   private Integer readTimeoutMillis = null;
   private Integer writeTimeoutMillis = null;
   protected Map extensions = null;
   protected Map subqueries = null;
   protected transient Map implicitParameters = null;
   protected transient Imports parsedImports = null;
   protected transient String[] parameterNames = null;
   protected transient QueryCompilation compilation = null;
   protected transient Set queryResults = new HashSet(1);
   protected transient Map tasks = new ConcurrentHashMap(1);
   protected Map inputParameters;

   public Query(StoreManager storeMgr, ExecutionContext ec) {
      this.storeMgr = storeMgr;
      this.ec = ec;
      if (ec == null) {
         throw new NucleusUserException(Localiser.msg("021012"));
      } else {
         this.clr = ec.getClassLoaderResolver();
         this.ignoreCache = ec.getBooleanProperty("datanucleus.IgnoreCache");
         this.readTimeoutMillis = ec.getIntProperty("datanucleus.datastoreReadTimeout");
         this.writeTimeoutMillis = ec.getIntProperty("datanucleus.datastoreWriteTimeout");
         this.serializeRead = ec.getTransaction() != null ? ec.getTransaction().getSerializeRead() : null;
         boolean closeAtEcClose = this.getBooleanExtensionProperty("datanucleus.query.closeResultsAtManagerClose", false);
         if (closeAtEcClose) {
            ec.registerExecutionContextListener(this);
         }

      }
   }

   public void setCacheResults(boolean cache) {
      if (cache && this.queryResults == null) {
         this.queryResults = new HashSet();
      } else if (!cache) {
         this.queryResults = null;
      }

   }

   public String getLanguage() {
      throw new UnsupportedOperationException("Query Language accessor not supported in this query");
   }

   protected void discardCompiled() {
      this.parsedImports = null;
      this.parameterNames = null;
      this.compilation = null;
   }

   public void setCompilation(QueryCompilation compilation) {
      this.compilation = compilation;
      if (compilation != null && NucleusLogger.QUERY.isDebugEnabled()) {
         NucleusLogger.QUERY.debug(compilation.toString());
      }

   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Query)) {
         return false;
      } else {
         Query q = (Query)obj;
         if (this.candidateClass == null) {
            if (q.candidateClass != null) {
               return false;
            }
         } else if (!this.candidateClass.equals(q.candidateClass)) {
            return false;
         }

         if (this.filter == null) {
            if (q.filter != null) {
               return false;
            }
         } else if (!this.filter.equals(q.filter)) {
            return false;
         }

         if (this.imports == null) {
            if (q.imports != null) {
               return false;
            }
         } else if (!this.imports.equals(q.imports)) {
            return false;
         }

         if (this.explicitParameters == null) {
            if (q.explicitParameters != null) {
               return false;
            }
         } else if (!this.explicitParameters.equals(q.explicitParameters)) {
            return false;
         }

         if (this.explicitVariables == null) {
            if (q.explicitVariables != null) {
               return false;
            }
         } else if (!this.explicitVariables.equals(q.explicitVariables)) {
            return false;
         }

         if (this.unique != q.unique) {
            return false;
         } else if (this.serializeRead != q.serializeRead) {
            return false;
         } else if (this.unmodifiable != q.unmodifiable) {
            return false;
         } else if (this.resultClass != q.resultClass) {
            return false;
         } else {
            if (this.grouping == null) {
               if (q.grouping != null) {
                  return false;
               }
            } else if (!this.grouping.equals(q.grouping)) {
               return false;
            }

            if (this.ordering == null) {
               if (q.ordering != null) {
                  return false;
               }
            } else if (!this.ordering.equals(q.ordering)) {
               return false;
            }

            if (this.from == null) {
               if (q.from != null) {
                  return false;
               }
            } else if (!this.from.equals(q.from)) {
               return false;
            }

            if (this.update == null) {
               if (q.update != null) {
                  return false;
               }
            } else if (!this.update.equals(q.update)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      return (this.candidateClass == null ? 0 : this.candidateClass.hashCode()) ^ (this.result == null ? 0 : this.result.hashCode()) ^ (this.filter == null ? 0 : this.filter.hashCode()) ^ (this.imports == null ? 0 : this.imports.hashCode()) ^ (this.explicitParameters == null ? 0 : this.explicitParameters.hashCode()) ^ (this.explicitVariables == null ? 0 : this.explicitVariables.hashCode()) ^ (this.resultClass == null ? 0 : this.resultClass.hashCode()) ^ (this.grouping == null ? 0 : this.grouping.hashCode()) ^ (this.having == null ? 0 : this.having.hashCode()) ^ (this.ordering == null ? 0 : this.ordering.hashCode()) ^ (this.range == null ? 0 : this.range.hashCode()) ^ (this.from == null ? 0 : this.from.hashCode()) ^ (this.update == null ? 0 : this.update.hashCode());
   }

   public short getType() {
      return this.type;
   }

   public void setType(short type) {
      if (type != 0 && type != 1 && type != 2) {
         throw new NucleusUserException("Query only supports types of SELECT, BULK_UPDATE, BULK_DELETE : unknown value " + type);
      } else {
         this.type = type;
      }
   }

   public StoreManager getStoreManager() {
      return this.storeMgr;
   }

   public ExecutionContext getExecutionContext() {
      return this.ec;
   }

   public void executionContextClosing(ExecutionContext ec) {
      NucleusLogger.QUERY.debug("ExecutionContext is closing so closing query results for \"" + this + "\"");
      this.closeAll();
      if (this.fetchPlan != null) {
         this.fetchPlan.clearGroups();
      }

      this.ec = null;
   }

   public void addExtension(String key, Object value) {
      if (this.extensions == null) {
         this.extensions = new HashMap();
      }

      this.extensions.put(key, value);
      if (key.equals("datanucleus.query.excludeSubclasses")) {
         this.subclasses = !this.getBooleanExtensionProperty("datanucleus.query.excludeSubclasses", false);
      } else if (key.equals("datanucleus.query.closeResultsAtManagerClose")) {
         boolean closeAtEcClose = this.getBooleanExtensionProperty("datanucleus.query.closeResultsAtManagerClose", false);
         if (closeAtEcClose) {
            this.ec.registerExecutionContextListener(this);
         } else {
            this.ec.deregisterExecutionContextListener(this);
         }
      }

   }

   public void setExtensions(Map extensions) {
      this.extensions = extensions != null ? new HashMap(extensions) : null;
      if (extensions.containsKey("datanucleus.query.excludeSubclasses")) {
         this.subclasses = !this.getBooleanExtensionProperty("datanucleus.query.excludeSubclasses", false);
      }

      if (extensions.containsKey("datanucleus.query.closeResultsAtManagerClose")) {
         boolean closeAtEcClose = this.getBooleanExtensionProperty("datanucleus.query.closeResultsAtManagerClose", false);
         if (closeAtEcClose) {
            this.ec.registerExecutionContextListener(this);
         } else {
            this.ec.deregisterExecutionContextListener(this);
         }
      }

   }

   public Object getExtension(String key) {
      return this.extensions != null ? this.extensions.get(key) : null;
   }

   public Map getExtensions() {
      return this.extensions;
   }

   public boolean getBooleanExtensionProperty(String name, boolean resultIfNotSet) {
      if (this.extensions != null && this.extensions.containsKey(name)) {
         Object value = this.extensions.get(name);
         return value instanceof Boolean ? (Boolean)value : Boolean.valueOf((String)value);
      } else {
         return this.ec.getNucleusContext().getConfiguration().getBooleanProperty(name, resultIfNotSet);
      }
   }

   public String getStringExtensionProperty(String name, String resultIfNotSet) {
      if (this.extensions != null && this.extensions.containsKey(name)) {
         return (String)this.extensions.get(name);
      } else {
         String value = this.ec.getNucleusContext().getConfiguration().getStringProperty(name);
         return value != null ? value : resultIfNotSet;
      }
   }

   public Set getSupportedExtensions() {
      Set<String> extensions = new HashSet();
      extensions.add("datanucleus.query.flushBeforeExecution");
      extensions.add("datanucleus.query.useFetchPlan");
      extensions.add("datanucleus.query.resultSizeMethod");
      extensions.add("datanucleus.query.loadResultsAtCommit");
      extensions.add("datanucleus.query.resultCacheType");
      extensions.add("datanucleus.query.results.cached");
      extensions.add("datanucleus.query.compilation.cached");
      extensions.add("datanucleus.query.multithread");
      extensions.add("datanucleus.query.evaluateInMemory");
      extensions.add("datanucleus.query.closeResultsAtManagerClose");
      return extensions;
   }

   public FetchPlan getFetchPlan() {
      if (this.fetchPlan == null) {
         this.fetchPlan = this.ec.getFetchPlan().getCopy();
      }

      return this.fetchPlan;
   }

   public void setFetchPlan(FetchPlan fp) {
      this.fetchPlan = fp;
   }

   public void setUpdate(String update) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.update = update;
   }

   public String getUpdate() {
      return this.update;
   }

   public Class getCandidateClass() {
      return this.candidateClass;
   }

   public void setCandidateClass(Class candidateClass) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.candidateClassName = candidateClass != null ? candidateClass.getName() : null;
      this.candidateClass = candidateClass;
   }

   public void setCandidateClassName(String candidateClassName) {
      this.candidateClassName = candidateClassName != null ? candidateClassName.trim() : null;
   }

   public String getCandidateClassName() {
      return this.candidateClassName;
   }

   protected AbstractClassMetaData getCandidateClassMetaData() {
      AbstractClassMetaData cmd = this.ec.getMetaDataManager().getMetaDataForClass(this.candidateClass, this.clr);
      if (this.candidateClass.isInterface()) {
         String[] impls = this.ec.getMetaDataManager().getClassesImplementingInterface(this.candidateClass.getName(), this.clr);
         if (impls.length != 1 || !cmd.isImplementationOfPersistentDefinition()) {
            cmd = this.ec.getMetaDataManager().getMetaDataForInterface(this.candidateClass, this.clr);
            if (cmd == null) {
               throw new NucleusUserException("Attempting to query an interface yet it is not declared 'persistent'. Define the interface in metadata as being persistent to perform this operation, and make sure any implementations use the same identity and identity member(s)");
            }
         }
      }

      return cmd;
   }

   public void setFrom(String from) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.from = from;
   }

   public String getFrom() {
      return this.from;
   }

   public void setFilter(String filter) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.filter = StringUtils.isWhitespace(filter) ? null : StringUtils.removeSpecialTagsFromString(filter).trim();
   }

   public String getFilter() {
      return this.filter;
   }

   public void declareImports(String imports) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.imports = StringUtils.isWhitespace(imports) ? null : StringUtils.removeSpecialTagsFromString(imports).trim();
   }

   public String getImportsDeclaration() {
      return this.imports;
   }

   public void declareExplicitParameters(String parameters) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.explicitParameters = StringUtils.isWhitespace(parameters) ? null : StringUtils.removeSpecialTagsFromString(parameters).trim();
   }

   public String getExplicitParametersDeclaration() {
      return this.explicitParameters;
   }

   public void declareExplicitVariables(String variables) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.explicitVariables = StringUtils.isWhitespace(variables) ? null : StringUtils.removeSpecialTagsFromString(variables).trim();
   }

   public String getExplicitVariablesDeclaration() {
      return this.explicitVariables;
   }

   public void setImplicitParameter(String name, Object value) {
      if (this.implicitParameters == null) {
         this.implicitParameters = new HashMap();
      }

      this.implicitParameters.put(name, value);
      if (this.compilation == null) {
         this.discardCompiled();
         this.compileInternal(this.implicitParameters);
      }

      this.applyImplicitParameterValueToCompilation(name, value);
   }

   public void setImplicitParameter(int position, Object value) {
      if (this.implicitParameters == null) {
         this.implicitParameters = new HashMap();
      }

      this.implicitParameters.put(position, value);
      if (this.compilation == null) {
         this.discardCompiled();
         this.compileInternal(this.implicitParameters);
      }

      this.applyImplicitParameterValueToCompilation("" + position, value);
   }

   protected void applyImplicitParameterValueToCompilation(String name, Object value) {
      if (this.compilation != null) {
         boolean symbolFound = false;
         Symbol sym = this.compilation.getSymbolTable().getSymbol(name);
         if (sym != null) {
            symbolFound = true;
            if (sym.getValueType() == null && value != null) {
               sym.setValueType(value.getClass());
            } else if (sym.getValueType() != null && value != null && !QueryUtils.queryParameterTypesAreCompatible(sym.getValueType(), value.getClass())) {
               throw new QueryInvalidParametersException("Parameter " + name + " needs to be assignable from " + sym.getValueType().getName() + " yet the value is of type " + value.getClass().getName());
            }
         }

         boolean subSymbolFound = this.applyImplicitParameterValueToSubqueries(name, value, this.compilation);
         if (subSymbolFound) {
            symbolFound = true;
         }

         if (!symbolFound) {
            throw new QueryInvalidParametersException(Localiser.msg("021116", name));
         }
      }
   }

   protected boolean applyImplicitParameterValueToSubqueries(String name, Object value, QueryCompilation comp) {
      boolean symbolFound = false;
      Symbol sym = null;
      String[] subqueryNames = comp.getSubqueryAliases();
      if (subqueryNames != null) {
         for(int i = 0; i < subqueryNames.length; ++i) {
            QueryCompilation subCompilation = comp.getCompilationForSubquery(subqueryNames[i]);
            sym = subCompilation.getSymbolTable().getSymbol(name);
            if (sym != null) {
               symbolFound = true;
               if (sym.getValueType() == null && value != null) {
                  sym.setValueType(value.getClass());
               } else if (sym.getValueType() != null && value != null && !QueryUtils.queryParameterTypesAreCompatible(sym.getValueType(), value.getClass())) {
                  throw new QueryInvalidParametersException("Parameter " + name + " needs to be assignable from " + sym.getValueType().getName() + " yet the value is of type " + value.getClass().getName());
               }
            }

            boolean subSymbolFound = this.applyImplicitParameterValueToSubqueries(name, value, subCompilation);
            if (subSymbolFound) {
               symbolFound = true;
            }
         }
      }

      return symbolFound;
   }

   public Map getImplicitParameters() {
      return this.implicitParameters;
   }

   public void setOrdering(String ordering) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.ordering = ordering != null ? ordering.trim() : null;
   }

   public String getOrdering() {
      return this.ordering;
   }

   public void setGrouping(String grouping) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.grouping = grouping != null ? grouping.trim() : null;
   }

   public String getGrouping() {
      return this.grouping;
   }

   public void setHaving(String having) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.having = having != null ? having.trim() : null;
   }

   public String getHaving() {
      return this.having;
   }

   public abstract void setCandidates(Extent var1);

   public abstract void setCandidates(Collection var1);

   public void setUnique(boolean unique) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.unique = unique;
   }

   public boolean isUnique() {
      return this.unique;
   }

   public void setRange(long fromIncl, long toExcl) {
      this.discardCompiled();
      if (fromIncl >= 0L && fromIncl < Long.MAX_VALUE) {
         this.fromInclNo = fromIncl;
      }

      if (toExcl >= 0L) {
         this.toExclNo = toExcl;
      }

      this.fromInclParam = null;
      this.toExclParam = null;
      this.range = "" + this.fromInclNo + "," + this.toExclNo;
   }

   public void setRange(String range) {
      this.discardCompiled();
      this.range = range;
      if (range == null) {
         this.fromInclNo = 0L;
         this.fromInclParam = null;
         this.toExclNo = Long.MAX_VALUE;
         this.toExclParam = null;
      } else {
         StringTokenizer tok = new StringTokenizer(range, ",");
         if (!tok.hasMoreTokens()) {
            throw new NucleusUserException("Invalid range. Expected 'lower, upper'");
         } else {
            String first = tok.nextToken().trim();

            try {
               this.fromInclNo = Long.valueOf(first);
            } catch (NumberFormatException var7) {
               this.fromInclNo = 0L;
               this.fromInclParam = first.trim();
               if (this.fromInclParam.startsWith(":")) {
                  this.fromInclParam = this.fromInclParam.substring(1);
               }
            }

            if (!tok.hasMoreTokens()) {
               throw new NucleusUserException("Invalid range. Expected 'lower, upper'");
            } else {
               String second = tok.nextToken().trim();

               try {
                  this.toExclNo = Long.valueOf(second);
               } catch (NumberFormatException var6) {
                  this.toExclNo = Long.MAX_VALUE;
                  this.toExclParam = second.trim();
                  if (this.toExclParam.startsWith(":")) {
                     this.toExclParam = this.toExclParam.substring(1);
                  }
               }

            }
         }
      }
   }

   public String getRange() {
      return this.range;
   }

   public long getRangeFromIncl() {
      return this.fromInclNo;
   }

   public long getRangeToExcl() {
      return this.toExclNo;
   }

   public String getRangeFromInclParam() {
      return this.fromInclParam;
   }

   public String getRangeToExclParam() {
      return this.toExclParam;
   }

   public void setResult(String result) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.result = result != null ? result.trim() : null;
   }

   public String getResult() {
      return this.result;
   }

   public void setResultDistinct(boolean distinct) {
      this.resultDistinct = distinct;
   }

   public boolean getResultDistinct() {
      return this.resultDistinct;
   }

   public String getResultClassName() {
      return this.resultClassName;
   }

   public void setResultClassName(String resultClassName) {
      this.discardCompiled();

      try {
         this.resultClass = this.clr.classForName(resultClassName);
         this.resultClassName = null;
      } catch (ClassNotResolvedException var3) {
         this.resultClassName = resultClassName;
         this.resultClass = null;
      }

   }

   public void setResultClass(Class result_cls) {
      this.discardCompiled();
      this.resultClass = result_cls;
      this.resultClassName = null;
   }

   public Class getResultClass() {
      return this.resultClass;
   }

   public void setResultMetaData(QueryResultMetaData qrmd) {
      throw new NucleusException("This query doesn't support the use of setResultMetaData()");
   }

   public void setIgnoreCache(boolean ignoreCache) {
      this.discardCompiled();
      this.ignoreCache = ignoreCache;
   }

   public boolean getIgnoreCache() {
      return this.ignoreCache;
   }

   public boolean isSubclasses() {
      return this.subclasses;
   }

   public void setSubclasses(boolean subclasses) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.subclasses = subclasses;
   }

   public Boolean getSerializeRead() {
      return this.serializeRead;
   }

   public void setSerializeRead(Boolean serialize) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.serializeRead = serialize;
   }

   public boolean isUnmodifiable() {
      return this.unmodifiable;
   }

   protected void assertIsModifiable() {
      if (this.unmodifiable) {
         throw new NucleusUserException(Localiser.msg("021014"));
      }
   }

   public void setUnmodifiable() {
      this.unmodifiable = true;
   }

   public void setDatastoreReadTimeoutMillis(Integer timeout) {
      if (!this.supportsTimeout()) {
         throw new NucleusUnsupportedOptionException("Timeout not supported on this query");
      } else {
         this.readTimeoutMillis = timeout;
      }
   }

   public Integer getDatastoreReadTimeoutMillis() {
      return this.readTimeoutMillis;
   }

   public void setDatastoreWriteTimeoutMillis(Integer timeout) {
      if (!this.supportsTimeout()) {
         throw new NucleusUnsupportedOptionException("Timeout not supported on this query");
      } else {
         this.writeTimeoutMillis = timeout;
      }
   }

   public Integer getDatastoreWriteTimeoutMillis() {
      return this.writeTimeoutMillis;
   }

   public QueryManager getQueryManager() {
      return this.storeMgr != null ? this.storeMgr.getQueryManager() : null;
   }

   public void addSubquery(Query sub, String variableDecl, String candidateExpr, Map paramMap) {
      if (StringUtils.isWhitespace(variableDecl)) {
         throw new NucleusUserException(Localiser.msg("021115"));
      } else {
         if (sub == null) {
            if (this.explicitVariables == null) {
               this.explicitVariables = variableDecl;
            } else {
               this.explicitVariables = this.explicitVariables + ";" + variableDecl;
            }
         } else {
            if (this.subqueries == null) {
               this.subqueries = new HashMap();
            }

            String subqueryVariableName = variableDecl.trim();
            int sepPos = subqueryVariableName.indexOf(32);
            subqueryVariableName = subqueryVariableName.substring(sepPos + 1);
            if (!StringUtils.isWhitespace(candidateExpr)) {
               sub.setFrom(candidateExpr);
            }

            this.subqueries.put(subqueryVariableName, new SubqueryDefinition(sub, StringUtils.isWhitespace(candidateExpr) ? null : candidateExpr, variableDecl, paramMap));
         }

      }
   }

   public SubqueryDefinition getSubqueryForVariable(String variableName) {
      return this.subqueries == null ? null : (SubqueryDefinition)this.subqueries.get(variableName);
   }

   public boolean hasSubqueryForVariable(String variableName) {
      return this.subqueries == null ? false : this.subqueries.containsKey(variableName);
   }

   protected void prepareDatastore() {
      boolean flush = false;
      if (!this.ignoreCache && !this.ec.isDelayDatastoreOperationsEnabled()) {
         flush = true;
      } else {
         flush = this.getBooleanExtensionProperty("datanucleus.query.flushBeforeExecution", false);
      }

      if (flush) {
         this.ec.flushInternal(false);
      }

   }

   public QueryCompilation getCompilation() {
      return this.compilation;
   }

   protected boolean isCompiled() {
      return this.compilation != null;
   }

   public void compile() {
      try {
         if (this.candidateClass != null) {
            this.clr.setPrimary(this.candidateClass.getClassLoader());
         }

         this.compileInternal((Map)null);
      } finally {
         this.clr.setPrimary((ClassLoader)null);
      }

   }

   protected abstract void compileInternal(Map var1);

   public Imports getParsedImports() {
      if (this.parsedImports == null) {
         this.parsedImports = new Imports();
         if (this.candidateClassName != null) {
            this.parsedImports.importPackage(this.candidateClassName);
         }

         if (this.imports != null) {
            this.parsedImports.parseImports(this.imports);
         }
      }

      return this.parsedImports;
   }

   public Object execute() {
      return this.executeWithArray(new Object[0]);
   }

   public Object executeWithArray(Object[] parameterValues) {
      this.assertIsOpen();
      if (!this.ec.getTransaction().isActive() && !this.ec.getTransaction().getNontransactionalRead()) {
         throw new TransactionNotReadableException();
      } else {
         return this.executeQuery(this.getParameterMapForValues(parameterValues));
      }
   }

   public Object executeWithMap(Map parameters) {
      this.assertIsOpen();
      if (!this.ec.getTransaction().isActive() && !this.ec.getTransaction().getNontransactionalRead()) {
         throw new TransactionNotReadableException();
      } else {
         return this.executeQuery(parameters);
      }
   }

   public Map getInputParameters() {
      return this.inputParameters;
   }

   protected boolean supportsTimeout() {
      return false;
   }

   protected Object executeQuery(Map parameters) {
      try {
         if (this.candidateClass != null) {
            this.clr.setPrimary(this.candidateClass.getClassLoader());
         }

         this.inputParameters = new HashMap();
         if (this.implicitParameters != null) {
            this.inputParameters.putAll(this.implicitParameters);
         }

         if (parameters != null) {
            this.inputParameters.putAll(parameters);
         }

         try {
            this.compileInternal(this.inputParameters);
            this.checkForMissingParameters(this.inputParameters);
            if (this.compilation != null) {
               this.candidateClass = this.compilation.getCandidateClass();
            }
         } catch (RuntimeException re) {
            this.discardCompiled();
            throw re;
         }

         this.prepareDatastore();
         if (this.toExclNo - this.fromInclNo <= 0L) {
            if (this.shouldReturnSingleRow()) {
               Object var30 = null;
               return var30;
            } else {
               List var29 = Collections.EMPTY_LIST;
               return var29;
            }
         } else {
            boolean failed = true;
            long start = 0L;
            if (this.ec.getStatistics() != null) {
               start = System.currentTimeMillis();
               this.ec.getStatistics().queryBegin();
            }

            try {
               Object result = this.performExecute(this.inputParameters);
               Collection qr;
               if (this.type != 2 && this.type != 1) {
                  if (this.type != 0) {
                     qr = (Collection)result;
                     return qr;
                  } else {
                     qr = (Collection)result;
                     failed = false;
                     Iterator qrIter;
                     if (!this.shouldReturnSingleRow()) {
                        if (qr instanceof QueryResult && this.queryResults != null) {
                           this.queryResults.add((QueryResult)qr);
                        }

                        qrIter = qr;
                        return qrIter;
                     } else {
                        try {
                           if (qr != null && qr.size() != 0) {
                              if (!this.processesRangeInDatastoreQuery() && this.toExclNo - this.fromInclNo <= 0L) {
                                 throw new NoQueryResultsException("No query results were returned in the required range");
                              } else {
                                 qrIter = qr.iterator();
                                 Object firstRow = qrIter.next();
                                 if (qrIter.hasNext()) {
                                    failed = true;
                                    throw new QueryNotUniqueException();
                                 } else {
                                    Object var9 = firstRow;
                                    return var9;
                                 }
                              }
                           } else {
                              throw new NoQueryResultsException("No query results were returned");
                           }
                        } finally {
                           if (qr != null) {
                              this.close(qr);
                           }

                        }
                     }
                  }
               } else {
                  qr = (Collection)result;
                  return qr;
               }
            } finally {
               if (this.ec.getStatistics() != null) {
                  if (failed) {
                     this.ec.getStatistics().queryExecutedWithError();
                  } else {
                     this.ec.getStatistics().queryExecuted(System.currentTimeMillis() - start);
                  }
               }

            }
         }
      } finally {
         this.clr.setPrimary((ClassLoader)null);
      }
   }

   protected void assertSupportsCancel() {
      throw new UnsupportedOperationException("This query implementation doesn't support the cancel of executing queries");
   }

   public void cancel() {
      this.assertSupportsCancel();

      for(Map.Entry entry : this.tasks.entrySet()) {
         boolean success = this.cancelTaskObject(entry.getValue());
         NucleusLogger.QUERY.debug("Query cancelled for thread=" + ((Thread)entry.getKey()).getId() + " with success=" + success);
      }

      this.tasks.clear();
   }

   public void cancel(Thread thread) {
      this.assertSupportsCancel();
      synchronized(this.tasks) {
         Object threadObject = this.tasks.get(thread);
         if (threadObject != null) {
            boolean success = this.cancelTaskObject(threadObject);
            NucleusLogger.QUERY.debug("Query (in thread=" + thread.getId() + ") cancelled with success=" + success);
         }

         this.tasks.remove(thread);
      }
   }

   protected void registerTask(Object taskObject) {
      synchronized(this.tasks) {
         this.tasks.put(Thread.currentThread(), taskObject);
      }
   }

   protected void deregisterTask() {
      synchronized(this.tasks) {
         this.tasks.remove(Thread.currentThread());
      }
   }

   protected boolean cancelTaskObject(Object obj) {
      return true;
   }

   protected abstract Object performExecute(Map var1);

   public boolean processesRangeInDatastoreQuery() {
      return false;
   }

   public long deletePersistentAll() {
      return this.deletePersistentAll(new Object[0]);
   }

   public long deletePersistentAll(Object[] parameterValues) {
      return this.deletePersistentAll(this.getParameterMapForValues(parameterValues));
   }

   public long deletePersistentAll(Map parameters) {
      this.assertIsOpen();
      if (!this.ec.getTransaction().isActive() && !this.ec.getTransaction().getNontransactionalWrite()) {
         throw new TransactionNotActiveException();
      } else if (this.result != null) {
         throw new NucleusUserException(Localiser.msg("021029"));
      } else if (this.resultClass != null) {
         throw new NucleusUserException(Localiser.msg("021030"));
      } else if (this.ordering != null) {
         throw new NucleusUserException(Localiser.msg("021027"));
      } else if (this.grouping != null) {
         throw new NucleusUserException(Localiser.msg("021028"));
      } else if (this.range != null) {
         throw new NucleusUserException(Localiser.msg("021031"));
      } else if (this.fromInclNo < 0L || this.toExclNo < 0L || this.fromInclNo == 0L && this.toExclNo == Long.MAX_VALUE) {
         return this.performDeletePersistentAll(parameters);
      } else {
         throw new NucleusUserException(Localiser.msg("021031"));
      }
   }

   protected long performDeletePersistentAll(Map parameters) {
      boolean requiresUnique = this.unique;

      long var4;
      try {
         if (this.unique) {
            this.unique = false;
            this.discardCompiled();
         }

         this.compileInternal(parameters);
         Collection results = (Collection)this.performExecute(parameters);
         if (results != null) {
            int number = results.size();
            if (requiresUnique && number > 1) {
               throw new NucleusUserException(Localiser.msg("021032"));
            }

            Iterator resultsIter = results.iterator();

            while(resultsIter.hasNext()) {
               this.ec.findObjectProvider(resultsIter.next()).flush();
            }

            this.ec.deleteObjects(results.toArray());
            if (results instanceof QueryResult) {
               ((QueryResult)results).close();
            }

            long var6 = (long)number;
            return var6;
         }

         var4 = 0L;
      } finally {
         if (requiresUnique != this.unique) {
            this.unique = requiresUnique;
            this.discardCompiled();
         }

      }

      return var4;
   }

   public void close(Object queryResult) {
      if (queryResult != null && queryResult instanceof QueryResult) {
         if (this.queryResults != null) {
            this.queryResults.remove(queryResult);
         }

         ((QueryResult)queryResult).close();
      }

   }

   public void closeAll() {
      if (this.ec != null) {
         boolean closeAtEcClose = this.getBooleanExtensionProperty("datanucleus.query.closeResultsAtManagerClose", false);
         if (closeAtEcClose) {
            this.ec.deregisterExecutionContextListener(this);
         }
      }

      if (this.queryResults != null) {
         QueryResult[] qrs = (QueryResult[])this.queryResults.toArray(new QueryResult[this.queryResults.size()]);

         for(int i = 0; i < qrs.length; ++i) {
            this.close(qrs[i]);
         }
      }

      if (this.fetchPlan != null) {
         this.fetchPlan.clearGroups().addGroup("default");
      }

   }

   protected boolean shouldReturnSingleRow() {
      return QueryUtils.queryReturnsSingleRow(this);
   }

   protected Map getParameterMapForValues(Object[] parameterValues) {
      Map parameterMap = new HashMap();
      int position = 0;
      if (this.explicitParameters != null) {
         StringTokenizer t1 = new StringTokenizer(this.explicitParameters, ",");

         while(t1.hasMoreTokens()) {
            StringTokenizer t2 = new StringTokenizer(t1.nextToken(), " ");
            if (t2.countTokens() != 2) {
               throw new NucleusUserException(Localiser.msg("021101", this.explicitParameters));
            }

            t2.nextToken();
            String parameterName = t2.nextToken();
            if (!JDOQLQueryHelper.isValidJavaIdentifierForJDOQL(parameterName)) {
               throw new NucleusUserException(Localiser.msg("021102", parameterName));
            }

            if (parameterMap.containsKey(parameterName)) {
               throw new NucleusUserException(Localiser.msg("021103", parameterName));
            }

            if (parameterValues.length < position + 1) {
               throw new NucleusUserException(Localiser.msg("021108", "" + (position + 1), "" + parameterValues.length));
            }

            parameterMap.put(parameterName, parameterValues[position++]);
         }

         if (parameterMap.size() != parameterValues.length) {
            throw new NucleusUserException(Localiser.msg("021108", "" + parameterMap.size(), "" + parameterValues.length));
         }
      } else {
         for(int i = 0; i < parameterValues.length; ++i) {
            parameterMap.put(i, parameterValues[i]);
         }
      }

      return parameterMap;
   }

   protected boolean useFetchPlan() {
      boolean useFetchPlan = this.getBooleanExtensionProperty("datanucleus.query.useFetchPlan", true);
      if (this.type == 1 || this.type == 2) {
         useFetchPlan = false;
      }

      return useFetchPlan;
   }

   public boolean useCaching() {
      return this.getBooleanExtensionProperty("datanucleus.query.compilation.cached", true);
   }

   public boolean useResultsCaching() {
      return !this.useCaching() ? false : this.getBooleanExtensionProperty("datanucleus.query.results.cached", false);
   }

   public boolean checkUnusedParameters() {
      return this.getBooleanExtensionProperty("datanucleus.query.checkUnusedParameters", true);
   }

   protected void checkParameterTypesAgainstCompilation(Map parameterValues) {
      if (this.compilation != null) {
         if (parameterValues != null && !parameterValues.isEmpty()) {
            boolean checkUnusedParams = this.checkUnusedParameters();

            for(Map.Entry entry : parameterValues.entrySet()) {
               Object paramKey = entry.getKey();
               Symbol sym = null;
               sym = this.deepFindSymbolForParameterInCompilation(this.compilation, paramKey);
               if (sym != null) {
                  Class expectedValueType = sym.getValueType();
                  if (entry.getValue() != null && expectedValueType != null && !QueryUtils.queryParameterTypesAreCompatible(expectedValueType, entry.getValue().getClass())) {
                     throw new NucleusUserException("Parameter \"" + paramKey + "\" was specified as " + entry.getValue().getClass().getName() + " but should have been " + expectedValueType.getName());
                  }
               } else if (paramKey instanceof String && (this.fromInclParam == null && this.toExclParam == null || !paramKey.equals(this.fromInclParam) && !paramKey.equals(this.toExclParam)) && checkUnusedParams) {
                  throw new QueryInvalidParametersException(Localiser.msg("021116", paramKey));
               }
            }

         }
      }
   }

   protected void checkForMissingParameters(Map parameterValues) {
      if (this.compilation != null) {
         if (parameterValues == null) {
            parameterValues = new HashMap();
         }

         boolean namedParametersSupplied = true;
         if (parameterValues.size() > 0) {
            Object key = parameterValues.keySet().iterator().next();
            if (!(key instanceof String)) {
               namedParametersSupplied = false;
            }
         }

         if (namedParametersSupplied) {
            SymbolTable symtbl = this.compilation.getSymbolTable();
            Collection<String> symNames = symtbl.getSymbolNames();
            if (symNames != null && !symNames.isEmpty()) {
               for(String symName : symNames) {
                  Symbol sym = symtbl.getSymbol(symName);
                  if (sym.getType() == 1 && !parameterValues.containsKey(symName)) {
                     throw new QueryInvalidParametersException(Localiser.msg("021119", symName));
                  }
               }
            }
         }

      }
   }

   protected Symbol deepFindSymbolForParameterInCompilation(QueryCompilation compilation, Object paramKey) {
      Symbol sym = null;
      sym = this.getSymbolForParameterInCompilation(compilation, paramKey);
      if (sym == null) {
         String[] subqueryNames = compilation.getSubqueryAliases();
         if (subqueryNames != null) {
            for(int i = 0; i < subqueryNames.length; ++i) {
               sym = this.deepFindSymbolForParameterInCompilation(compilation.getCompilationForSubquery(subqueryNames[i]), paramKey);
               if (sym != null) {
                  break;
               }
            }
         }
      }

      return sym;
   }

   private Symbol getSymbolForParameterInCompilation(QueryCompilation compilation, Object paramKey) {
      Symbol sym = null;
      if (paramKey instanceof Integer) {
         ParameterExpression expr = compilation.getParameterExpressionForPosition((Integer)paramKey);
         if (expr != null) {
            sym = expr.getSymbol();
         }
      } else {
         String paramName = (String)paramKey;
         sym = compilation.getSymbolTable().getSymbol(paramName);
      }

      return sym;
   }

   public Class resolveClassDeclaration(String classDecl) {
      try {
         return this.getParsedImports().resolveClassDeclaration(classDecl, this.ec.getClassLoaderResolver(), this.candidateClass == null ? null : this.candidateClass.getClassLoader());
      } catch (ClassNotResolvedException var3) {
         throw new NucleusUserException(Localiser.msg("021015", classDecl));
      }
   }

   protected void assertIsOpen() {
      if (this.ec == null || this.ec.isClosed()) {
         throw (new NucleusUserException(Localiser.msg("021013"))).setFatal();
      }
   }

   public Object getNativeQuery() {
      return null;
   }

   public static class SubqueryDefinition {
      Query query;
      String candidateExpression;
      String variableDecl;
      Map parameterMap;

      public SubqueryDefinition(Query q, String candidates, String variables, Map params) {
         this.query = q;
         this.candidateExpression = candidates;
         this.variableDecl = variables;
         this.parameterMap = params;
      }

      public Query getQuery() {
         return this.query;
      }

      public String getCandidateExpression() {
         return this.candidateExpression;
      }

      public String getVariableDeclaration() {
         return this.variableDecl;
      }

      public Map getParameterMap() {
         return this.parameterMap;
      }
   }
}
