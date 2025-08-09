package org.datanucleus.query.compiler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.util.NucleusLogger;

public class QueryCompilerOptimiser {
   QueryCompilation compilation;

   public QueryCompilerOptimiser(QueryCompilation compilation) {
      this.compilation = compilation;
   }

   public void optimise() {
      if (this.compilation != null) {
         if (this.compilation.getExprFilter() != null) {
            Set<String> redundantVariables = new HashSet();
            this.findRedundantFilterVariables(this.compilation.getExprFilter(), redundantVariables);
            if (!redundantVariables.isEmpty()) {
               for(String var : redundantVariables) {
                  if (NucleusLogger.QUERY.isDebugEnabled()) {
                     NucleusLogger.QUERY.debug("Query was defined with variable " + var + " yet this was redundant, so has been replaced by the candidate");
                  }

                  this.compilation.setExprFilter(this.replaceVariableWithCandidateInExpression(var, this.compilation.getExprFilter()));
                  this.compilation.setExprHaving(this.replaceVariableWithCandidateInExpression(var, this.compilation.getExprHaving()));
                  Expression[] exprResult = this.compilation.getExprResult();
                  if (exprResult != null) {
                     for(int i = 0; i < exprResult.length; ++i) {
                        exprResult[i] = this.replaceVariableWithCandidateInExpression(var, exprResult[i]);
                     }
                  }

                  Expression[] exprGrouping = this.compilation.getExprGrouping();
                  if (exprGrouping != null) {
                     for(int i = 0; i < exprGrouping.length; ++i) {
                        exprGrouping[i] = this.replaceVariableWithCandidateInExpression(var, exprGrouping[i]);
                     }
                  }

                  this.compilation.getSymbolTable().removeSymbol(this.compilation.getSymbolTable().getSymbol(var));
               }
            }
         }

      }
   }

   private Expression replaceVariableWithCandidateInExpression(String varName, Expression expr) {
      if (expr == null) {
         return null;
      } else if (expr instanceof VariableExpression && ((VariableExpression)expr).getId().equals(varName)) {
         List<String> tuples = new ArrayList();
         tuples.add(this.compilation.getCandidateAlias());
         Expression replExpr = new PrimaryExpression(tuples);
         replExpr.bind(this.compilation.getSymbolTable());
         return replExpr;
      } else {
         if (expr instanceof DyadicExpression) {
            DyadicExpression dyExpr = (DyadicExpression)expr;
            if (dyExpr.getLeft() != null) {
               dyExpr.setLeft(this.replaceVariableWithCandidateInExpression(varName, dyExpr.getLeft()));
            }

            if (dyExpr.getRight() != null) {
               dyExpr.setRight(this.replaceVariableWithCandidateInExpression(varName, dyExpr.getRight()));
            }
         } else if (expr instanceof PrimaryExpression) {
            if (expr.getLeft() != null) {
               if (expr.getLeft() instanceof VariableExpression && ((VariableExpression)expr.getLeft()).getId().equals(varName)) {
                  expr.setLeft((Expression)null);
               } else {
                  expr.setLeft(this.replaceVariableWithCandidateInExpression(varName, expr.getLeft()));
               }
            }
         } else if (expr instanceof InvokeExpression) {
            InvokeExpression invokeExpr = (InvokeExpression)expr;
            if (invokeExpr.getLeft() != null) {
               invokeExpr.setLeft(this.replaceVariableWithCandidateInExpression(varName, invokeExpr.getLeft()));
            }
         }

         return expr;
      }
   }

   private void findRedundantFilterVariables(Expression filterExpr, Set varNames) {
      if (filterExpr instanceof DyadicExpression) {
         DyadicExpression dyExpr = (DyadicExpression)filterExpr;
         if (dyExpr.getOperator() == Expression.OP_EQ) {
            if (dyExpr.getLeft() instanceof VariableExpression) {
               if (dyExpr.getRight() instanceof PrimaryExpression) {
                  PrimaryExpression rightExpr = (PrimaryExpression)dyExpr.getRight();
                  if (rightExpr.getId().equals(this.compilation.getCandidateAlias())) {
                     varNames.add(((VariableExpression)dyExpr.getLeft()).getId());
                  }
               }
            } else if (dyExpr.getRight() instanceof VariableExpression && dyExpr.getLeft() instanceof PrimaryExpression) {
               PrimaryExpression leftExpr = (PrimaryExpression)dyExpr.getLeft();
               if (leftExpr.getId().equals(this.compilation.getCandidateAlias())) {
                  varNames.add(((VariableExpression)dyExpr.getRight()).getId());
               }
            }
         } else if (dyExpr.getOperator() == Expression.OP_AND) {
            this.findRedundantFilterVariables(dyExpr.getLeft(), varNames);
            this.findRedundantFilterVariables(dyExpr.getRight(), varNames);
         } else if (dyExpr.getOperator() == Expression.OP_OR) {
            this.findRedundantFilterVariables(dyExpr.getLeft(), varNames);
            this.findRedundantFilterVariables(dyExpr.getRight(), varNames);
         }
      }

   }
}
