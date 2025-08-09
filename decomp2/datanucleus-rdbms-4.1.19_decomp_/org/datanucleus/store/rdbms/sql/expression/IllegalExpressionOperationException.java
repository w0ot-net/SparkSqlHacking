package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.exceptions.NucleusUserException;

public class IllegalExpressionOperationException extends NucleusUserException {
   private static final long serialVersionUID = -4749845297556329228L;

   public IllegalExpressionOperationException(String operation, SQLExpression operand) {
      super("Cannot perform operation \"" + operation + "\" on " + operand);
   }

   public IllegalExpressionOperationException(SQLExpression operand1, String operation, SQLExpression operand2) {
      super("Cannot perform operation \"" + operation + "\" on " + operand1 + " and " + operand2);
   }
}
