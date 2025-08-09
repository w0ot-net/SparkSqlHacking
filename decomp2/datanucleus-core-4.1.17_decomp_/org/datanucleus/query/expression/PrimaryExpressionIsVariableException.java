package org.datanucleus.query.expression;

import org.datanucleus.exceptions.NucleusException;

public class PrimaryExpressionIsVariableException extends NucleusException {
   private static final long serialVersionUID = 6811137517053964773L;
   VariableExpression varExpr;

   public PrimaryExpressionIsVariableException(String varName) {
      super("PrimaryExpression should be a VariableExpression with name " + varName);
      this.varExpr = new VariableExpression(varName);
   }

   public VariableExpression getVariableExpression() {
      return this.varExpr;
   }
}
