package org.datanucleus.query.evaluator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.query.Query;

public class JDOQLEvaluator extends JavaQueryEvaluator {
   public JDOQLEvaluator(Query query, Collection candidates, QueryCompilation compilation, Map parameterValues, ClassLoaderResolver clr) {
      super("JDOQL", query, compilation, parameterValues, clr, candidates);
   }

   protected Collection evaluateSubquery(Query query, QueryCompilation compilation, Collection candidates, Object outerCandidate) {
      JDOQLEvaluator eval = new JDOQLEvaluator(query, candidates, compilation, this.parameterValues, this.clr);
      return eval.execute(true, true, true, true, true);
   }

   public Collection execute(boolean applyFilter, boolean applyOrdering, boolean applyResult, boolean applyResultClass, boolean applyRange) {
      Collection results = super.execute(applyFilter, applyOrdering, applyResult, applyResultClass, applyRange);
      return (Collection)(results instanceof List ? new InMemoryQueryResult((List)results, this.query.getExecutionContext().getApiAdapter()) : results);
   }

   Collection mapResultClass(Collection resultSet) {
      Expression[] result = this.compilation.getExprResult();
      return (new JDOQLResultClassMapper(this.query.getResultClass())).map(resultSet, result);
   }
}
