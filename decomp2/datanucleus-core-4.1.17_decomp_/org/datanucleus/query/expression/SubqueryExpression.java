package org.datanucleus.query.expression;

import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class SubqueryExpression extends Expression {
   private static final long serialVersionUID = 833340286814838655L;
   String keyword;

   public SubqueryExpression(String keyword, VariableExpression operand) {
      this.keyword = keyword;
      this.right = operand;
   }

   public Symbol bind(SymbolTable symtbl) {
      this.right.bind(symtbl);
      return null;
   }

   public String getKeyword() {
      return this.keyword;
   }

   public String toString() {
      return "SubqueryExpression{" + this.keyword + "(" + this.right + ")}";
   }
}
