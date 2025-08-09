package org.datanucleus.query.evaluator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.query.Query;
import org.datanucleus.util.NucleusLogger;

public class JPQLEvaluator extends JavaQueryEvaluator {
   public JPQLEvaluator(Query query, Collection candidates, QueryCompilation compilation, Map parameterValues, ClassLoaderResolver clr) {
      super("JPQL", query, compilation, parameterValues, clr, candidates);
      if (this.parameterValues != null && this.parameterValues.size() > 0) {
         Set keys = this.parameterValues.keySet();
         boolean numericKeys = false;
         int origin = Integer.MAX_VALUE;

         for(Object key : keys) {
            if (numericKeys || key instanceof Integer) {
               numericKeys = true;
               if ((Integer)key < origin) {
                  origin = (Integer)key;
               }
            }
         }

         if (numericKeys && origin != 0) {
            Map paramValues = new HashMap();

            for(Map.Entry entry : this.parameterValues.entrySet()) {
               if (entry.getKey() instanceof Integer) {
                  int pos = (Integer)entry.getKey();
                  paramValues.put(pos - 1, entry.getValue());
               } else {
                  paramValues.put(entry.getKey(), entry.getValue());
               }
            }

            this.parameterValues = paramValues;
         }
      }

      if (compilation.getExprFrom() != null && compilation.getExprFrom().length > 0) {
         Expression[] fromExprs = compilation.getExprFrom();
         if (fromExprs.length > 1 || fromExprs[0].getRight() != null) {
            NucleusLogger.DATASTORE_RETRIEVE.warn("In-memory evaluation of query does not currently support JPQL FROM joins with aliases. This will be ignored so if depending on aliases defined in FROM then the query will fail");
         }
      }

   }

   protected Collection evaluateSubquery(Query query, QueryCompilation compilation, Collection candidates, Object outerCandidate) {
      JPQLEvaluator eval = new JPQLEvaluator(query, candidates, compilation, this.parameterValues, this.clr);
      return eval.execute(true, true, true, true, true);
   }

   public Collection execute(boolean applyFilter, boolean applyOrdering, boolean applyResult, boolean applyResultClass, boolean applyRange) {
      Collection results = super.execute(applyFilter, applyOrdering, applyResult, applyResultClass, applyRange);
      return (Collection)(results instanceof List ? new InMemoryQueryResult((List)results, this.query.getExecutionContext().getApiAdapter()) : results);
   }

   Collection mapResultClass(Collection resultSet) {
      Expression[] result = this.compilation.getExprResult();
      return (new JPQLResultClassMapper(this.query.getResultClass())).map(resultSet, result);
   }
}
