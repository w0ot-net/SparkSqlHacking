package org.datanucleus.query.inmemory;

import org.datanucleus.query.expression.VariableExpression;

public class VariableNotSetException extends RuntimeException {
   private static final long serialVersionUID = -8348102622967122528L;
   protected VariableExpression varExpr = null;
   protected Object[] variableValues = null;

   public VariableNotSetException(VariableExpression varExpr) {
      this.varExpr = varExpr;
   }

   public VariableNotSetException(VariableExpression varExpr, Object[] values) {
      this.varExpr = varExpr;
      this.variableValues = values;
   }

   public VariableExpression getVariableExpression() {
      return this.varExpr;
   }

   public Object[] getValues() {
      return this.variableValues;
   }
}
