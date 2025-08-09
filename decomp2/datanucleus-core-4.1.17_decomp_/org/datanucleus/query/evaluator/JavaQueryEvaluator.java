package org.datanucleus.query.evaluator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.CreatorExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.inmemory.InMemoryExpressionEvaluator;
import org.datanucleus.query.inmemory.InMemoryFailure;
import org.datanucleus.query.inmemory.VariableNotSetException;
import org.datanucleus.store.query.Query;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class JavaQueryEvaluator {
   public static final String RESULTS_SET = "DATANUCLEUS_RESULTS_SET";
   protected final String language;
   protected String candidateAlias = "this";
   protected Collection candidates;
   protected Query query;
   protected QueryCompilation compilation;
   protected Map parameterValues;
   protected InMemoryExpressionEvaluator evaluator;
   protected Map state;
   protected ClassLoaderResolver clr;

   public JavaQueryEvaluator(String language, Query query, QueryCompilation compilation, Map parameterValues, ClassLoaderResolver clr, Collection candidates) {
      this.language = language;
      this.query = query;
      this.compilation = compilation;
      this.parameterValues = parameterValues;
      this.clr = clr;
      this.candidates = candidates;
      this.candidateAlias = compilation.getCandidateAlias() != null ? compilation.getCandidateAlias() : this.candidateAlias;
      this.state = new HashMap();
      this.state.put(this.candidateAlias, query.getCandidateClass());
      this.evaluator = new InMemoryExpressionEvaluator(query.getExecutionContext(), parameterValues, this.state, query.getParsedImports(), clr, this.candidateAlias, query.getLanguage());
   }

   protected abstract Collection evaluateSubquery(Query var1, QueryCompilation var2, Collection var3, Object var4);

   public Collection execute(boolean applyFilter, boolean applyOrdering, boolean applyResult, boolean applyResultClass, boolean applyRange) {
      if (!applyFilter && !applyOrdering && !applyResult && !applyResultClass && !applyRange) {
         return this.candidates;
      } else {
         Collection executeCandidates = new ArrayList();
         Expression[] result = this.compilation.getExprResult();
         if (this.candidates != null) {
            if (applyResult && result != null && result.length > 1) {
               for(Object candidate : this.candidates) {
                  if (!executeCandidates.contains(candidate)) {
                     executeCandidates.add(candidate);
                  }
               }
            } else {
               executeCandidates.addAll(this.candidates);
            }
         }

         String[] subqueryAliases = this.compilation.getSubqueryAliases();
         if (subqueryAliases != null) {
            for(int i = 0; i < subqueryAliases.length; ++i) {
               Query subquery = this.query.getSubqueryForVariable(subqueryAliases[i]).getQuery();
               QueryCompilation subqueryCompilation = this.compilation.getCompilationForSubquery(subqueryAliases[i]);
               if (subqueryCompilation.getExprFrom() != null) {
                  NucleusLogger.QUERY.warn("In-memory evaluation of subquery with 'from'=" + StringUtils.objectArrayToString(subqueryCompilation.getExprFrom()) + " but from clause evaluation not currently supported!");
               }

               Collection subqueryResult = this.evaluateSubquery(subquery, subqueryCompilation, executeCandidates, (Object)null);
               if (QueryUtils.queryReturnsSingleRow(subquery)) {
                  this.state.put(subqueryAliases[i], subqueryResult.iterator().next());
               } else {
                  this.state.put(subqueryAliases[i], subqueryResult);
               }
            }
         }

         List resultSet = new ArrayList(executeCandidates);
         Expression filter = this.compilation.getExprFilter();
         if (applyFilter && filter != null) {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021012", "filter", this.language, filter));
            }

            resultSet = this.handleFilter(resultSet);
         }

         Expression[] ordering = this.compilation.getExprOrdering();
         if (applyOrdering && ordering != null) {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021012", "ordering", this.language, StringUtils.objectArrayToString(ordering)));
            }

            resultSet = this.ordering(resultSet);
         }

         if (applyRange && this.query.getRange() != null) {
            long fromIncl = this.query.getRangeFromIncl();
            long toExcl = this.query.getRangeToExcl();
            if (this.query.getRangeFromInclParam() != null) {
               fromIncl = ((Number)this.parameterValues.get(this.query.getRangeFromInclParam())).longValue();
            }

            if (this.query.getRangeToExclParam() != null) {
               toExcl = ((Number)this.parameterValues.get(this.query.getRangeToExclParam())).longValue();
            }

            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021012", "range", this.language, "" + fromIncl + "," + toExcl));
            }

            resultSet = this.handleRange(resultSet, fromIncl, toExcl);
         }

         if (applyResult && result != null) {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021012", "result", this.language, StringUtils.objectArrayToString(result)));
            }

            new ArrayList();
            List s = resultSet;
            Expression[] grouping = this.compilation.getExprGrouping();
            if (grouping != null) {
               s = this.sortByGrouping(resultSet);
            }

            List var24 = s;
            if (grouping != null) {
               var24 = this.handleAggregates(s);
            }

            resultSet = this.handleResult((List)var24);
            if (this.query.getResultDistinct()) {
               List tmpList = new ArrayList();

               for(Object obj : resultSet) {
                  if (!tmpList.contains(obj)) {
                     tmpList.add(obj);
                  }
               }

               resultSet = tmpList;
            }
         }

         if (applyResultClass && this.query.getResultClass() != null) {
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021012", "resultClass", this.language, this.query.getResultClass().getName()));
            }

            if (result != null && !(result[0] instanceof CreatorExpression)) {
               return this.mapResultClass(resultSet);
            }
         }

         return resultSet;
      }
   }

   private List handleFilter(List set) {
      Expression filter = this.compilation.getExprFilter();
      if (filter == null) {
         return set;
      } else {
         this.state.put("DATANUCLEUS_RESULTS_SET", set);
         List result = new ArrayList();
         Iterator it = set.iterator();
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug("Evaluating filter for " + set.size() + " candidates");
         }

         while(it.hasNext()) {
            Object obj = it.next();
            if (!this.state.containsKey(this.candidateAlias)) {
               throw new NucleusUserException("Alias \"" + this.candidateAlias + "\" doesn't exist in the query or the candidate alias wasn't defined");
            }

            this.state.put(this.candidateAlias, obj);
            InMemoryExpressionEvaluator eval = new InMemoryExpressionEvaluator(this.query.getExecutionContext(), this.parameterValues, this.state, this.query.getParsedImports(), this.clr, this.candidateAlias, this.query.getLanguage());
            Object evalResult = this.evaluateBooleanExpression(filter, eval);
            if (Boolean.TRUE.equals(evalResult)) {
               if (NucleusLogger.QUERY.isDebugEnabled()) {
                  NucleusLogger.QUERY.debug(Localiser.msg("021023", StringUtils.toJVMIDString(obj)));
               }

               result.add(obj);
            }
         }

         return result;
      }
   }

   private Boolean evaluateBooleanExpression(Expression expr, InMemoryExpressionEvaluator eval) {
      try {
         Object result = expr.evaluate(eval);
         return result instanceof InMemoryFailure ? Boolean.FALSE : (Boolean)result;
      } catch (VariableNotSetException var5) {
         VariableNotSetException vnse = var5;
         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021024", var5.getVariableExpression().getId(), StringUtils.objectArrayToString(var5.getValues())));
         }

         if (var5.getValues() != null && var5.getValues().length != 0) {
            for(int i = 0; i < vnse.getValues().length; ++i) {
               eval.setVariableValue(vnse.getVariableExpression().getId(), vnse.getValues()[i]);
               if (NucleusLogger.QUERY.isDebugEnabled()) {
                  NucleusLogger.QUERY.debug(Localiser.msg("021025", vnse.getVariableExpression().getId(), vnse.getValues()[i]));
               }

               if (Boolean.TRUE.equals(this.evaluateBooleanExpression(expr, eval))) {
                  return Boolean.TRUE;
               }
            }
         } else {
            eval.setVariableValue(var5.getVariableExpression().getId(), (Object)null);
            if (NucleusLogger.QUERY.isDebugEnabled()) {
               NucleusLogger.QUERY.debug(Localiser.msg("021025", var5.getVariableExpression().getId(), "(null)"));
            }

            if (Boolean.TRUE.equals(this.evaluateBooleanExpression(expr, eval))) {
               return Boolean.TRUE;
            }
         }

         if (NucleusLogger.QUERY.isDebugEnabled()) {
            NucleusLogger.QUERY.debug(Localiser.msg("021026", vnse.getVariableExpression().getId()));
         }

         eval.removeVariableValue(vnse.getVariableExpression().getId());
         return Boolean.FALSE;
      }
   }

   private List handleRange(List set, long fromIncl, long toExcl) {
      if (toExcl - fromIncl <= 0L) {
         return Collections.EMPTY_LIST;
      } else {
         List resultList = new ArrayList();
         Iterator it = set.iterator();

         for(long l = 0L; l < fromIncl && it.hasNext(); ++l) {
            it.next();
         }

         for(long l = 0L; l < toExcl - fromIncl && it.hasNext(); ++l) {
            resultList.add(it.next());
         }

         return resultList;
      }
   }

   private List sortByGrouping(List set) {
      Object[] o = set.toArray();
      final Expression[] grouping = this.compilation.getExprGrouping();
      Arrays.sort(o, new Comparator() {
         public int compare(Object arg0, Object arg1) {
            for(int i = 0; i < grouping.length; ++i) {
               JavaQueryEvaluator.this.state.put(JavaQueryEvaluator.this.candidateAlias, arg0);
               Object a = grouping[i].evaluate(JavaQueryEvaluator.this.evaluator);
               JavaQueryEvaluator.this.state.put(JavaQueryEvaluator.this.candidateAlias, arg1);
               Object b = grouping[i].evaluate(JavaQueryEvaluator.this.evaluator);
               int result = 0;
               if (a == null && b == null) {
                  result = 0;
               } else if (a == null) {
                  result = -1;
               } else {
                  result = ((Comparable)a).compareTo(b);
               }

               if (result != 0) {
                  return result;
               }
            }

            return 0;
         }
      });
      return Arrays.asList(o);
   }

   private List ordering(List set) {
      Expression[] ordering = this.compilation.getExprOrdering();
      if (ordering == null) {
         return set;
      } else {
         this.state.put("DATANUCLEUS_RESULTS_SET", set);
         return QueryUtils.orderCandidates(set, ordering, this.state, this.candidateAlias, this.query.getExecutionContext(), this.clr, this.parameterValues, this.query.getParsedImports(), this.query.getLanguage());
      }
   }

   private List handleAggregates(List resultSet) {
      final Expression[] grouping = this.compilation.getExprGrouping();
      Comparator c = new Comparator() {
         public int compare(Object arg0, Object arg1) {
            for(int i = 0; i < grouping.length; ++i) {
               JavaQueryEvaluator.this.state.put(JavaQueryEvaluator.this.candidateAlias, arg0);
               Object a = grouping[i].evaluate(JavaQueryEvaluator.this.evaluator);
               JavaQueryEvaluator.this.state.put(JavaQueryEvaluator.this.candidateAlias, arg1);
               Object b = grouping[i].evaluate(JavaQueryEvaluator.this.evaluator);
               if (a == null && b == null) {
                  return 0;
               }

               if (a == null) {
                  return -1;
               }

               if (b == null) {
                  return 1;
               }

               int result = ((Comparable)a).compareTo(b);
               if (result != 0) {
                  return result;
               }
            }

            return 0;
         }
      };
      List groups = new ArrayList();
      List group = new ArrayList();
      groups.add(group);

      for(int i = 0; i < resultSet.size(); ++i) {
         if (i > 0 && c.compare(resultSet.get(i - 1), resultSet.get(i)) != 0) {
            group = new ArrayList();
            groups.add(group);
         }

         group.add(resultSet.get(i));
      }

      List result = new ArrayList();
      Expression having = this.compilation.getExprHaving();
      if (having != null) {
         for(int i = 0; i < groups.size(); ++i) {
            if (this.satisfiesHavingClause((List)groups.get(i))) {
               result.addAll((Collection)groups.get(i));
            }
         }
      } else {
         for(int i = 0; i < groups.size(); ++i) {
            result.addAll((Collection)groups.get(i));
         }
      }

      return result;
   }

   private boolean satisfiesHavingClause(List set) {
      this.state.put("DATANUCLEUS_RESULTS_SET", set);
      Expression having = this.compilation.getExprHaving();
      return having.evaluate(this.evaluator) == Boolean.TRUE;
   }

   private List handleResult(List resultSet) {
      List result = new ArrayList();
      final Expression[] grouping = this.compilation.getExprGrouping();
      if (grouping != null) {
         Comparator c = new Comparator() {
            public int compare(Object arg0, Object arg1) {
               for(int i = 0; i < grouping.length; ++i) {
                  JavaQueryEvaluator.this.state.put(JavaQueryEvaluator.this.candidateAlias, arg0);
                  Object a = grouping[i].evaluate(JavaQueryEvaluator.this.evaluator);
                  JavaQueryEvaluator.this.state.put(JavaQueryEvaluator.this.candidateAlias, arg1);
                  Object b = grouping[i].evaluate(JavaQueryEvaluator.this.evaluator);
                  if (a == null && b == null) {
                     return 0;
                  }

                  if (a == null) {
                     return -1;
                  }

                  if (b == null) {
                     return 1;
                  }

                  int result = ((Comparable)a).compareTo(b);
                  if (result != 0) {
                     return result;
                  }
               }

               return 0;
            }
         };
         List groups = new ArrayList();
         List group = new ArrayList();
         if (resultSet.size() > 0) {
            groups.add(group);
         }

         for(int i = 0; i < resultSet.size(); ++i) {
            if (i > 0 && c.compare(resultSet.get(i - 1), resultSet.get(i)) != 0) {
               group = new ArrayList();
               groups.add(group);
            }

            group.add(resultSet.get(i));
         }

         for(int i = 0; i < groups.size(); ++i) {
            group = (List)groups.get(i);
            result.add(this.result(group));
         }
      } else {
         boolean aggregates = false;
         Expression[] resultExprs = this.compilation.getExprResult();
         if (resultExprs.length > 0 && resultExprs[0] instanceof CreatorExpression) {
            Expression[] resExpr = (Expression[])((CreatorExpression)resultExprs[0]).getArguments().toArray(new Expression[((CreatorExpression)resultExprs[0]).getArguments().size()]);

            for(int i = 0; i < resExpr.length; ++i) {
               if (resExpr[i] instanceof InvokeExpression) {
                  String method = ((InvokeExpression)resExpr[i]).getOperation().toLowerCase();
                  if (method.equals("count") || method.equals("sum") || method.equals("avg") || method.equals("min") || method.equals("max")) {
                     aggregates = true;
                  }
               }
            }
         } else {
            for(int i = 0; i < resultExprs.length; ++i) {
               if (resultExprs[i] instanceof InvokeExpression) {
                  String method = ((InvokeExpression)resultExprs[i]).getOperation().toLowerCase();
                  if (method.equals("count") || method.equals("sum") || method.equals("avg") || method.equals("min") || method.equals("max")) {
                     aggregates = true;
                  }
               }
            }
         }

         if (aggregates) {
            result.add(this.result(resultSet));
         } else {
            for(int i = 0; i < resultSet.size(); ++i) {
               result.add(this.result(resultSet.get(i)));
            }
         }
      }

      if (result.size() > 0 && ((Object[])((Object[])result.get(0))).length == 1) {
         List r = result;
         result = new ArrayList();

         for(int i = 0; i < r.size(); ++i) {
            result.add(((Object[])((Object[])r.get(i)))[0]);
         }
      }

      return result;
   }

   private Object[] result(Object obj) {
      this.state.put(this.candidateAlias, obj);
      Expression[] result = this.compilation.getExprResult();
      Object[] r = new Object[result.length];

      for(int i = 0; i < result.length; ++i) {
         r[i] = result[i].evaluate(this.evaluator);
      }

      return r;
   }

   private Object[] result(List set) {
      this.state.put("DATANUCLEUS_RESULTS_SET", set);
      Expression[] result = this.compilation.getExprResult();
      Object element = set != null && set.size() > 0 ? set.get(0) : null;
      this.state.put(this.candidateAlias, element);
      Object[] r = new Object[result.length];

      for(int j = 0; j < result.length; ++j) {
         r[j] = result[j].evaluate(this.evaluator);
      }

      return r;
   }

   abstract Collection mapResultClass(Collection var1);
}
