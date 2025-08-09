package org.datanucleus.query.expression;

import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class ClassExpression extends Expression {
   private static final long serialVersionUID = 4727718524363567502L;
   String candidateExpression;

   public ClassExpression(String alias) {
      this.alias = alias;
   }

   public void setCandidateExpression(String expr) {
      this.candidateExpression = expr;
   }

   public String getCandidateExpression() {
      return this.candidateExpression;
   }

   public void setJoinExpression(JoinExpression expr) {
      this.right = expr;
   }

   public String getAlias() {
      return this.alias;
   }

   public Symbol bind(SymbolTable symtbl) {
      this.symbol = symtbl.getSymbol(this.alias);
      return this.symbol;
   }

   public String toString() {
      return this.right != null ? "ClassExpression(" + (this.candidateExpression != null ? "candidate=" + this.candidateExpression + " " : "") + "alias=" + this.alias + " join=" + this.right + ")" : "ClassExpression(" + (this.candidateExpression != null ? "candidate=" + this.candidateExpression + " " : "") + "alias=" + this.alias + ")";
   }
}
