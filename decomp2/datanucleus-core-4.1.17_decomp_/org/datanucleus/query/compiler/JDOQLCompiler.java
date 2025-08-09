package org.datanucleus.query.compiler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.query.JDOQLQueryHelper;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.OrderExpression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.Imports;

public class JDOQLCompiler extends JavaQueryCompiler {
   boolean allowAll = false;

   public JDOQLCompiler(MetaDataManager metaDataManager, ClassLoaderResolver clr, String from, Class candidateClass, Collection candidates, String filter, Imports imports, String ordering, String result, String grouping, String having, String params, String variables, String update) {
      super(metaDataManager, clr, from, candidateClass, candidates, filter, imports, ordering, result, grouping, having, params, variables, update);
   }

   public void setAllowAll(boolean allow) {
      this.allowAll = allow;
   }

   public QueryCompilation compile(Map parameters, Map subqueryMap) {
      Map parseOptions = new HashMap();
      if (this.parameters != null) {
         parseOptions.put("explicitParameters", true);
      } else {
         parseOptions.put("implicitParameters", true);
      }

      if (this.options != null && this.options.containsKey("jdoql.strict")) {
         parseOptions.put("jdoql.strict", this.options.get("jdoql.strict"));
      }

      this.parser = new JDOQLParser(parseOptions);
      this.symtbl = new SymbolTable();
      this.symtbl.setSymbolResolver(this);
      if (this.parentCompiler != null) {
         this.symtbl.setParentSymbolTable(this.parentCompiler.symtbl);
      }

      if (subqueryMap != null && !subqueryMap.isEmpty()) {
         for(String subqueryName : subqueryMap.keySet()) {
            Symbol sym = new PropertySymbol(subqueryName);
            sym.setType(2);
            this.symtbl.addSymbol(sym);
         }
      }

      Expression[] exprFrom = this.compileFrom();
      this.compileCandidatesParametersVariables(parameters);
      Expression exprFilter = this.compileFilter();
      Expression[] exprOrdering = this.compileOrdering();
      Expression[] exprResult = this.compileResult();
      Expression[] exprGrouping = this.compileGrouping();
      Expression exprHaving = this.compileHaving();
      Expression[] exprUpdate = this.compileUpdate();
      if (exprGrouping != null) {
         if (exprResult != null) {
            for(int i = 0; i < exprResult.length; ++i) {
               if (!isExpressionGroupingOrAggregate(exprResult[i], exprGrouping)) {
                  throw new NucleusUserException("JDOQL query has result clause " + exprResult[i] + " but this is invalid (see JDO spec 14.6.10). When specified with grouping should be aggregate, or grouping expression");
               }
            }
         }

         if (exprOrdering != null) {
            for(int i = 0; i < exprOrdering.length; ++i) {
               if (!isExpressionGroupingOrAggregate(exprOrdering[i], exprGrouping)) {
                  throw new NucleusUserException("JDOQL query has ordering clause " + exprOrdering[i] + " but this is invalid (see JDO spec 14.6.10). When specified with grouping should be aggregate, or grouping expression");
               }
            }
         }
      }

      if (exprHaving != null && !containsOnlyGroupingOrAggregates(exprHaving, exprGrouping)) {
         throw new NucleusUserException("JDOQL query has having clause " + exprHaving + " but this is invalid (see JDO spec 14.6.10). Should contain only aggregates, or grouping expressions");
      } else {
         if (exprResult != null) {
            for(int i = 0; i < exprResult.length; ++i) {
               if (exprResult[i] instanceof InvokeExpression) {
                  InvokeExpression invokeExpr = (InvokeExpression)exprResult[i];
                  if (isMethodNameAggregate(invokeExpr.getOperation())) {
                     List<Expression> args = invokeExpr.getArguments();
                     if (args == null || args.size() != 1) {
                        throw new NucleusUserException("JDOQL query has result clause using aggregate (" + invokeExpr.getOperation() + ") but this needs 1 argument");
                     }
                  }
               }
            }
         }

         QueryCompilation compilation = new QueryCompilation(this.candidateClass, this.candidateAlias, this.symtbl, exprResult, exprFrom, exprFilter, exprGrouping, exprHaving, exprOrdering, exprUpdate);
         compilation.setQueryLanguage(this.getLanguage());
         boolean optimise = this.metaDataManager.getNucleusContext().getConfiguration().getBooleanProperty("datanucleus.query.compileOptimised");
         if (optimise) {
            QueryCompilerOptimiser optimiser = new QueryCompilerOptimiser(compilation);
            optimiser.optimise();
         }

         return compilation;
      }
   }

   public Expression[] compileUpdate() {
      if (this.allowAll && this.update != null) {
         ((JDOQLParser)this.parser).allowSingleEquals(true);
      }

      Expression[] result = super.compileUpdate();
      ((JDOQLParser)this.parser).allowSingleEquals(false);
      return result;
   }

   private static boolean containsOnlyGroupingOrAggregates(Expression expr, Expression[] exprGrouping) {
      if (expr == null) {
         return true;
      } else if (expr instanceof DyadicExpression) {
         Expression left = expr.getLeft();
         Expression right = expr.getRight();
         if (!containsOnlyGroupingOrAggregates(left, exprGrouping)) {
            return false;
         } else {
            return containsOnlyGroupingOrAggregates(right, exprGrouping);
         }
      } else if (!(expr instanceof InvokeExpression)) {
         if (expr instanceof PrimaryExpression) {
            return isExpressionGroupingOrAggregate(expr, exprGrouping);
         } else if (expr instanceof Literal) {
            return true;
         } else if (expr instanceof ParameterExpression) {
            return true;
         } else {
            return expr instanceof VariableExpression;
         }
      } else {
         InvokeExpression invExpr = (InvokeExpression)expr;
         if (isExpressionGroupingOrAggregate(invExpr, exprGrouping)) {
            return true;
         } else {
            Expression invokedExpr = invExpr.getLeft();
            if (invokedExpr != null && !containsOnlyGroupingOrAggregates(invokedExpr, exprGrouping)) {
               return false;
            } else {
               List<Expression> invArgs = invExpr.getArguments();
               if (invArgs != null) {
                  for(Expression argExpr : invArgs) {
                     if (!containsOnlyGroupingOrAggregates(argExpr, exprGrouping)) {
                        return false;
                     }
                  }
               }

               return true;
            }
         }
      }
   }

   private static boolean isMethodNameAggregate(String methodName) {
      return methodName.equals("avg") || methodName.equals("AVG") || methodName.equals("count") || methodName.equals("COUNT") || methodName.equals("sum") || methodName.equals("SUM") || methodName.equals("min") || methodName.equals("MIN") || methodName.equals("max") || methodName.equals("MAX");
   }

   private static boolean isExpressionGroupingOrAggregate(Expression expr, Expression[] exprGrouping) {
      if (expr instanceof InvokeExpression) {
         InvokeExpression invExpr = (InvokeExpression)expr;
         if (invExpr.getLeft() == null) {
            String methodName = invExpr.getOperation();
            if (isMethodNameAggregate(methodName)) {
               return true;
            }
         }

         for(int j = 0; j < exprGrouping.length; ++j) {
            if (exprGrouping[j] instanceof InvokeExpression && invExpr.toStringWithoutAlias().equalsIgnoreCase(exprGrouping[j].toString())) {
               return true;
            }
         }
      } else if (expr instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)expr;
         String id = primExpr.getId();
         if (id.equals("this")) {
            return true;
         }

         for(int j = 0; j < exprGrouping.length; ++j) {
            if (exprGrouping[j] instanceof PrimaryExpression) {
               String groupId = ((PrimaryExpression)exprGrouping[j]).getId();
               if (id.equals(groupId)) {
                  return true;
               }
            }
         }
      } else {
         if (expr instanceof OrderExpression) {
            Expression orderExpr = ((OrderExpression)expr).getLeft();
            return isExpressionGroupingOrAggregate(orderExpr, exprGrouping);
         }

         if (expr instanceof Literal) {
            return true;
         }

         if (expr instanceof ParameterExpression) {
            return true;
         }

         if (expr instanceof VariableExpression) {
            return true;
         }

         String exprStr = expr.toString();

         for(int j = 0; j < exprGrouping.length; ++j) {
            if (exprGrouping[j].toString().equals(exprStr)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean supportsImplicitVariables() {
      return this.variables == null;
   }

   public boolean caseSensitiveSymbolNames() {
      return true;
   }

   public String getLanguage() {
      return "JDOQL";
   }

   protected boolean isKeyword(String name) {
      if (name == null) {
         return false;
      } else {
         return JDOQLQueryHelper.isKeyword(name);
      }
   }
}
