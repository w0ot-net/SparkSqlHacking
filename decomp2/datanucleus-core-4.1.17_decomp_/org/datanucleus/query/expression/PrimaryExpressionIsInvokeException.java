package org.datanucleus.query.expression;

import org.datanucleus.exceptions.NucleusException;

public class PrimaryExpressionIsInvokeException extends NucleusException {
   private static final long serialVersionUID = 7343816751966773821L;
   InvokeExpression invokeExpr;

   public PrimaryExpressionIsInvokeException(InvokeExpression expr) {
      super("PrimaryExpression should be a InvokeExpression " + expr);
      this.invokeExpr = expr;
   }

   public InvokeExpression getInvokeExpression() {
      return this.invokeExpr;
   }
}
