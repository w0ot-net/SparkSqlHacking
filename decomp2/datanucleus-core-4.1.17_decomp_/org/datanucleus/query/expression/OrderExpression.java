package org.datanucleus.query.expression;

import org.datanucleus.query.NullOrderingType;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.StringUtils;

public class OrderExpression extends Expression {
   private static final long serialVersionUID = -2397122673416437551L;
   private String sortOrder;
   private NullOrderingType nullOrder;

   public OrderExpression(Expression expr, String sortOrder, String nullOrder) {
      this.left = expr;
      this.sortOrder = sortOrder;
      if (!StringUtils.isWhitespace(nullOrder)) {
         this.nullOrder = nullOrder.equalsIgnoreCase("nulls first") ? NullOrderingType.NULLS_FIRST : NullOrderingType.NULLS_LAST;
      }

   }

   public OrderExpression(Expression expr, String sortOrder) {
      this.left = expr;
      this.sortOrder = sortOrder;
   }

   public OrderExpression(Expression expr) {
      this.left = expr;
   }

   public String getSortOrder() {
      return this.sortOrder;
   }

   public NullOrderingType getNullOrder() {
      return this.nullOrder;
   }

   public Symbol bind(SymbolTable symtbl) {
      if (this.left instanceof VariableExpression) {
         VariableExpression ve = (VariableExpression)this.left;
         ve.bind(symtbl);
      } else if (this.left instanceof DyadicExpression) {
         DyadicExpression de = (DyadicExpression)this.left;
         de.bind(symtbl);
      } else if (this.left instanceof InvokeExpression) {
         InvokeExpression de = (InvokeExpression)this.left;
         de.bind(symtbl);
      }

      return null;
   }

   public Object evaluate(ExpressionEvaluator eval) {
      return this.left.evaluate(eval);
   }

   public String toString() {
      String nullOrderString = this.nullOrder != null ? (this.nullOrder == NullOrderingType.NULLS_FIRST ? "NULLS FIRST" : "NULLS LAST") : null;
      return "OrderExpression{" + this.left + " " + this.sortOrder + (nullOrderString != null ? " [" + nullOrderString + "]" : "") + "}";
   }
}
