package org.datanucleus.query.expression;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class CaseExpression extends Expression {
   private static final long serialVersionUID = -7123407498309440027L;
   List actionConditions = new ArrayList();
   Expression elseExpr;

   public void addCondition(Expression whenExpr, Expression actionExpr) {
      this.actionConditions.add(new ExpressionPair(whenExpr, actionExpr));
   }

   public void setElseExpression(Expression elseExpr) {
      this.elseExpr = elseExpr;
   }

   public List getConditions() {
      return this.actionConditions;
   }

   public Expression getElseExpression() {
      return this.elseExpr;
   }

   public Symbol bind(SymbolTable symtbl) {
      for(ExpressionPair pair : this.actionConditions) {
         pair.getWhenExpression().bind(symtbl);
         pair.getActionExpression().bind(symtbl);
      }

      if (this.elseExpr != null) {
         this.elseExpr.bind(symtbl);
      }

      return null;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("CaseExpression : ");

      for(ExpressionPair pair : this.actionConditions) {
         str.append("WHEN ").append(pair.getWhenExpression()).append(" THEN ").append(pair.getActionExpression()).append(" ");
      }

      if (this.elseExpr != null) {
         str.append("ELSE ").append(this.elseExpr);
      }

      return str.toString();
   }

   public class ExpressionPair {
      Expression whenExpr;
      Expression actionExpr;

      public ExpressionPair(Expression when, Expression action) {
         this.whenExpr = when;
         this.actionExpr = action;
      }

      public Expression getWhenExpression() {
         return this.whenExpr;
      }

      public Expression getActionExpression() {
         return this.actionExpr;
      }
   }
}
