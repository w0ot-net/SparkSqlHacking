package org.datanucleus.store.query;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.query.JPQLSingleStringParser;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.compiler.JPQLCompiler;
import org.datanucleus.query.compiler.JavaQueryCompiler;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractJPQLQuery extends AbstractJavaQuery {
   private static final long serialVersionUID = 3365033406094223177L;

   public AbstractJPQLQuery(StoreManager storeMgr, ExecutionContext ec) {
      super(storeMgr, ec);
   }

   public AbstractJPQLQuery(StoreManager storeMgr, ExecutionContext ec, AbstractJPQLQuery q) {
      super(storeMgr, ec);
      this.candidateClass = q != null ? q.candidateClass : null;
      this.candidateClassName = q != null ? q.candidateClassName : null;
      this.from = q != null ? q.from : null;
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

   public AbstractJPQLQuery(StoreManager storeMgr, ExecutionContext ec, String query) {
      super(storeMgr, ec);
      (new JPQLSingleStringParser(this, query)).parse();
   }

   public void setResult(String result) {
      this.discardCompiled();
      this.assertIsModifiable();
      if (result == null) {
         this.result = null;
         this.resultDistinct = false;
      } else {
         String str = result.trim();
         if (str.toUpperCase().startsWith("DISTINCT ")) {
            this.resultDistinct = true;
            this.result = str.substring(8).trim();
         } else {
            this.resultDistinct = false;
            this.result = str;
         }

      }
   }

   protected String getQueryCacheKey() {
      String queryCacheKey = this.toString();
      if (this.range != null) {
         queryCacheKey = queryCacheKey + " RANGE " + this.range;
      } else if (this.fromInclNo > 0L || this.toExclNo != Long.MAX_VALUE) {
         queryCacheKey = queryCacheKey + " RANGE " + this.fromInclNo + "," + this.toExclNo;
      }

      if (this.getFetchPlan() != null) {
         queryCacheKey = queryCacheKey + " " + this.getFetchPlan().toString();
      }

      if (!this.subclasses) {
         queryCacheKey = queryCacheKey + " EXCLUDE SUBCLASSES";
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

         if (this.result != null) {
            if (this.resultDistinct) {
               str.append("DISTINCT ");
            }

            str.append(this.result + " ");
         } else if (this.compilation != null && this.compilation.getCandidateAlias() != null) {
            str.append(this.compilation.getCandidateAlias() + " ");
         }

         if (this.from != null && this.update == null) {
            str.append("FROM " + this.from + " ");
         }

         if (this.filter != null) {
            str.append("WHERE " + this.dereferenceFilter(this.filter) + " ");
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
               if (this.compilation.getExprResult() == null) {
                  this.result = null;
               }

               this.checkParameterTypesAgainstCompilation(parameterValues);
               return;
            }
         }

         long startTime = 0L;
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            startTime = System.currentTimeMillis();
            NucleusLogger.QUERY.debug(Localiser.msg("021044", this.getLanguage(), this.getSingleStringQuery()));
         }

         JavaQueryCompiler compiler = new JPQLCompiler(this.ec.getMetaDataManager(), this.ec.getClassLoaderResolver(), this.from, this.candidateClass, this.candidateCollection, this.filter, this.getParsedImports(), this.ordering, this.result, this.grouping, this.having, this.explicitParameters, this.update);
         if (this.getBooleanExtensionProperty("datanucleus.jpql.strict", false)) {
            compiler.setOption("jpql.strict", "true");
         }

         this.compilation = compiler.compile(parameterValues, this.subqueries);
         if (QueryUtils.queryReturnsSingleRow(this)) {
            this.compilation.setReturnsSingleRow();
         }

         if (this.resultDistinct) {
            this.compilation.setResultDistinct();
         }

         if (this.compilation.getExprResult() == null) {
            this.result = null;
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

         if (this.implicitParameters != null) {
            for(Object paramKey : this.implicitParameters.keySet()) {
               String paramName = "" + paramKey;
               this.applyImplicitParameterValueToCompilation(paramName, this.implicitParameters.get(paramName));
            }
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
            NucleusLogger.QUERY.debug(Localiser.msg("021044", this.getLanguage(), ((AbstractJPQLQuery)subquery).getSingleStringQuery()));
         }

         JavaQueryCompiler subCompiler = new JPQLCompiler(this.ec.getMetaDataManager(), this.ec.getClassLoaderResolver(), subquery.from, subquery.candidateClass, (Collection)null, subquery.filter, this.getParsedImports(), subquery.ordering, subquery.result, subquery.grouping, subquery.having, (String)null, (String)null);
         if (this.getBooleanExtensionProperty("datanucleus.jpql.strict", false)) {
            subCompiler.setOption("jpql.strict", "true");
         }

         subCompiler.setLinkToParentQuery(parentCompiler, (Map)null);
         QueryCompilation subqueryCompilation = subCompiler.compile(parameterValues, subquery.subqueries);
         parentCompilation.addSubqueryCompilation((String)entry.getKey(), subqueryCompilation);
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021045", this.getLanguage(), "" + (System.currentTimeMillis() - startTime)));
         }

         if (subquery.subqueries != null) {
            this.compileSubqueries(subquery.subqueries, subqueryCompilation, subCompiler, parameterValues);
         }
      }

   }

   public Class resolveClassDeclaration(String classDecl) {
      AbstractClassMetaData acmd = this.getStoreManager().getNucleusContext().getMetaDataManager().getMetaDataForEntityName(classDecl);
      if (acmd != null) {
         classDecl = acmd.getFullClassName();
      }

      return super.resolveClassDeclaration(classDecl);
   }

   public String getLanguage() {
      return "JPQL";
   }
}
