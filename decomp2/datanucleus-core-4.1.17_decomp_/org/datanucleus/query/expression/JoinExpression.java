package org.datanucleus.query.expression;

import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class JoinExpression extends Expression {
   private static final long serialVersionUID = -3758088504354624725L;
   JoinType type;
   Expression joinedExpr;
   Expression onExpr;

   public JoinExpression(Expression expr, String alias, JoinType type) {
      this.joinedExpr = expr;
      this.alias = alias;
      this.type = type;
   }

   public void setJoinExpression(JoinExpression expr) {
      this.right = expr;
   }

   public void setOnExpression(Expression expr) {
      this.onExpr = expr;
   }

   public Expression getJoinedExpression() {
      return this.joinedExpr;
   }

   public Expression getOnExpression() {
      return this.onExpr;
   }

   public String getAlias() {
      return this.alias;
   }

   public JoinType getType() {
      return this.type;
   }

   public Symbol bind(SymbolTable symtbl) {
      return null;
   }

   public String toString() {
      return this.right != null ? "JoinExpression{" + this.type + " " + this.joinedExpr + " alias=" + this.alias + " join=" + this.right + (this.onExpr != null ? " on=" + this.onExpr : "") + "}" : "JoinExpression{" + this.type + " " + this.joinedExpr + " alias=" + this.alias + (this.onExpr != null ? " on=" + this.onExpr : "") + "}";
   }

   public static enum JoinType {
      JOIN_INNER,
      JOIN_LEFT_OUTER,
      JOIN_RIGHT_OUTER,
      JOIN_INNER_FETCH,
      JOIN_LEFT_OUTER_FETCH,
      JOIN_RIGHT_OUTER_FETCH;
   }
}
