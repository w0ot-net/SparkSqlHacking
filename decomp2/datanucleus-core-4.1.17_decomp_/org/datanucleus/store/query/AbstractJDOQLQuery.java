package org.datanucleus.store.query;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.query.JDOQLSingleStringParser;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.compiler.JDOQLCompiler;
import org.datanucleus.query.compiler.JavaQueryCompiler;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractJDOQLQuery extends AbstractJavaQuery {
   private static final long serialVersionUID = 5721811300939822702L;

   public AbstractJDOQLQuery(StoreManager storeMgr, ExecutionContext ec) {
      super(storeMgr, ec);
   }

   public AbstractJDOQLQuery(StoreManager storeMgr, ExecutionContext ec, AbstractJDOQLQuery q) {
      this(storeMgr, ec);
      this.candidateClass = q != null ? q.candidateClass : null;
      this.candidateClassName = q != null ? q.candidateClassName : null;
      this.subclasses = q != null ? q.subclasses : true;
      this.filter = q != null ? q.filter : null;
      this.imports = q != null ? q.imports : null;
      this.explicitVariables = q != null ? q.explicitVariables : null;
      this.explicitParameters = q != null ? q.explicitParameters : null;
      this.grouping = q != null ? q.grouping : null;
      this.ordering = q != null ? q.ordering : null;
      this.update = q != null ? q.update : null;
      this.result = q != null ? q.result : null;
      this.resultClass = q != null ? q.resultClass : null;
      this.resultDistinct = q != null ? q.resultDistinct : false;
      this.range = q != null ? q.range : null;
      this.fromInclNo = q != null ? q.fromInclNo : 0L;
      this.toExclNo = q != null ? q.toExclNo : Long.MAX_VALUE;
      this.fromInclParam = q != null ? q.fromInclParam : null;
      this.toExclParam = q != null ? q.toExclParam : null;
      if (q != null) {
         this.ignoreCache = q.ignoreCache;
      }

      if (q != null && q.subqueries != null && !q.subqueries.isEmpty()) {
         for(Query.SubqueryDefinition subquery : q.subqueries.values()) {
            this.addSubquery(subquery.query, subquery.variableDecl, subquery.candidateExpression, subquery.parameterMap);
         }
      }

   }

   public AbstractJDOQLQuery(StoreManager storeMgr, ExecutionContext ec, String query) {
      this(storeMgr, ec);
      JDOQLSingleStringParser parser = new JDOQLSingleStringParser(this, query);
      boolean allowAllSyntax = ec.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.query.jdoql.allowAll");
      if (ec.getBooleanProperty("datanucleus.query.jdoql.allowAll") != null) {
         allowAllSyntax = ec.getBooleanProperty("datanucleus.query.jdoql.allowAll");
      }

      if (allowAllSyntax) {
         parser.setAllowDelete(true);
         parser.setAllowUpdate(true);
      }

      parser.parse();
      if (this.candidateClassName != null) {
         try {
            this.candidateClass = this.getParsedImports().resolveClassDeclaration(this.candidateClassName, this.clr, (ClassLoader)null);
            this.candidateClassName = this.candidateClass.getName();
         } catch (ClassNotResolvedException e) {
            NucleusLogger.QUERY.warn("Candidate class for JDOQL single-string query (" + this.candidateClassName + ") could not be resolved", e);
         }
      }

   }

   public void setGrouping(String grouping) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.grouping = null;
      this.setHaving((String)null);
      if (grouping != null && grouping.length() > 0) {
         if (grouping.indexOf("HAVING") >= 0) {
            this.setHaving(grouping.substring(grouping.indexOf("HAVING") + 7));
            this.grouping = grouping.substring(0, grouping.indexOf("HAVING") - 1);
         } else if (grouping.indexOf("having") >= 0) {
            this.setHaving(grouping.substring(grouping.indexOf("having") + 7));
            this.grouping = grouping.substring(0, grouping.indexOf("having") - 1);
         } else {
            this.grouping = grouping.trim();
         }
      }

   }

   public void setResult(String result) {
      this.discardCompiled();
      this.assertIsModifiable();
      if (result == null) {
         this.result = null;
         this.resultDistinct = false;
      } else {
         String str = result.trim();
         if (!str.startsWith("distinct ") && !str.startsWith("DISTINCT ")) {
            this.resultDistinct = false;
            this.result = str;
         } else {
            this.resultDistinct = true;
            this.result = str.substring(8).trim();
         }

      }
   }

   protected String getQueryCacheKey() {
      String queryCacheKey = this.toString();
      if (this.getFetchPlan() != null) {
         queryCacheKey = queryCacheKey + " " + this.getFetchPlan().toString();
      }

      return queryCacheKey;
   }

   public String getSingleStringQuery() {
      if (this.singleString != null) {
         return this.singleString;
      } else {
         StringBuilder str = new StringBuilder();
         if (this.type == 1) {
            str.append("UPDATE " + this.from + " SET " + this.update + " ");
         } else if (this.type == 2) {
            str.append("DELETE ");
         } else {
            str.append("SELECT ");
         }

         if (this.unique) {
            str.append("UNIQUE ");
         }

         if (this.result != null) {
            if (this.resultDistinct) {
               str.append("DISTINCT ");
            }

            str.append(this.result + " ");
         }

         if (this.resultClass != null) {
            str.append("INTO " + this.resultClass.getName() + " ");
         }

         if (this.from != null) {
            str.append("FROM " + this.from + " ");
         } else if (this.candidateClassName != null) {
            str.append("FROM " + this.candidateClassName + " ");
            if (!this.subclasses) {
               str.append("EXCLUDE SUBCLASSES ");
            }
         }

         if (this.filter != null) {
            str.append("WHERE " + this.dereferenceFilter(this.filter) + " ");
         }

         if (this.explicitVariables != null) {
            str.append("VARIABLES " + this.explicitVariables + " ");
         }

         if (this.explicitParameters != null) {
            str.append("PARAMETERS " + this.explicitParameters + " ");
         }

         if (this.imports != null) {
            str.append(this.imports + " ");
         }

         if (this.grouping != null) {
            str.append("GROUP BY " + this.grouping + " ");
         }

         if (this.having != null) {
            str.append("HAVING " + this.having + " ");
         }

         if (this.ordering != null) {
            str.append("ORDER BY " + this.ordering + " ");
         }

         if (this.range != null) {
            str.append("RANGE " + this.range + " ");
         } else if (this.fromInclNo > 0L || this.toExclNo != Long.MAX_VALUE) {
            str.append("RANGE " + this.fromInclNo + "," + this.toExclNo + " ");
         }

         this.singleString = str.toString().trim();
         return this.singleString;
      }
   }

   public void compileGeneric(Map parameterValues) {
      if (this.compilation == null) {
         QueryManager queryMgr = this.getQueryManager();
         String queryCacheKey = this.getQueryCacheKey();
         if (this.useCaching() && queryCacheKey != null) {
            QueryCompilation cachedCompilation = queryMgr.getQueryCompilationForQuery(this.getLanguage(), queryCacheKey);
            if (cachedCompilation != null) {
               this.compilation = cachedCompilation;
               this.checkParameterTypesAgainstCompilation(parameterValues);
               return;
            }
         }

         if (this.resultClassName != null) {
            this.resultClass = this.resolveClassDeclaration(this.resultClassName);
            this.resultClassName = null;
         }

         long startTime = 0L;
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
            NucleusLogger.QUERY.debug(Localiser.msg("021044", this.getLanguage(), this.getSingleStringQuery()));
         }

         JDOQLCompiler compiler = new JDOQLCompiler(this.ec.getMetaDataManager(), this.ec.getClassLoaderResolver(), this.from, this.candidateClass, this.candidateCollection, this.filter, this.getParsedImports(), this.ordering, this.result, this.grouping, this.having, this.explicitParameters, this.explicitVariables, this.update);
         if (this.getBooleanExtensionProperty("datanucleus.jdoql.strict", false)) {
            compiler.setOption("jdoql.strict", "true");
         }

         boolean allowAllSyntax = this.ec.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.query.jdoql.allowAll");
         if (this.ec.getBooleanProperty("datanucleus.query.jdoql.allowAll") != null) {
            allowAllSyntax = this.ec.getBooleanProperty("datanucleus.query.jdoql.allowAll");
         }

         compiler.setAllowAll(allowAllSyntax);
         this.compilation = compiler.compile(parameterValues, this.subqueries);
         if (QueryUtils.queryReturnsSingleRow(this)) {
            this.compilation.setReturnsSingleRow();
         }

         if (this.resultDistinct) {
            this.compilation.setResultDistinct();
         }

         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021045", this.getLanguage(), "" + (System.currentTimeMillis() - startTime)));
         }

         if (this.subqueries != null) {
            this.compileSubqueries(this.subqueries, this.compilation, compiler, parameterValues);
         }

         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(this.compilation.toString());
         }

         this.checkParameterTypesAgainstCompilation(parameterValues);
         if (this.useCaching() && queryCacheKey != null) {
            queryMgr.addQueryCompilation(this.getLanguage(), queryCacheKey, this.compilation);
         }

      }
   }

   protected void compileInternal(Map parameterValues) {
      this.compileGeneric(parameterValues);
   }

   protected void compileSubqueries(Map subqueryMap, QueryCompilation parentCompilation, JavaQueryCompiler parentCompiler, Map parameterValues) {
      long startTime = System.currentTimeMillis();

      for(Map.Entry entry : subqueryMap.entrySet()) {
         Query.SubqueryDefinition subqueryDefinition = (Query.SubqueryDefinition)entry.getValue();
         Query subquery = subqueryDefinition.getQuery();
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
            NucleusLogger.QUERY.debug(Localiser.msg("021044", this.getLanguage(), ((AbstractJDOQLQuery)subquery).getSingleStringQuery()));
         }

         JDOQLCompiler subCompiler = new JDOQLCompiler(this.ec.getMetaDataManager(), this.ec.getClassLoaderResolver(), subquery.from, subquery.candidateClass, (Collection)null, subquery.filter, this.getParsedImports(), subquery.ordering, subquery.result, subquery.grouping, subquery.having, subquery.explicitParameters, (String)null, (String)null);
         if (this.getBooleanExtensionProperty("datanucleus.jdoql.strict", false)) {
            subCompiler.setOption("jdoql.strict", "true");
         }

         boolean allowAllSyntax = this.ec.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.query.jdoql.allowAll");
         if (this.ec.getBooleanProperty("datanucleus.query.jdoql.allowAll") != null) {
            allowAllSyntax = this.ec.getBooleanProperty("datanucleus.query.jdoql.allowAll");
         }

         subCompiler.setAllowAll(allowAllSyntax);
         subCompiler.setLinkToParentQuery(parentCompiler, subqueryDefinition.getParameterMap());
         QueryCompilation subqueryCompilation = subCompiler.compile(parameterValues, (Map)null);
         if (QueryUtils.queryReturnsSingleRow(subquery)) {
            subqueryCompilation.setReturnsSingleRow();
         }

         parentCompilation.addSubqueryCompilation((String)entry.getKey(), subqueryCompilation);
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021045", this.getLanguage(), "" + (System.currentTimeMillis() - startTime)));
         }

         if (subquery.subqueries != null) {
            this.compileSubqueries(subquery.subqueries, subqueryCompilation, subCompiler, parameterValues);
         }
      }

   }

   public String getLanguage() {
      return "JDOQL";
   }
}
