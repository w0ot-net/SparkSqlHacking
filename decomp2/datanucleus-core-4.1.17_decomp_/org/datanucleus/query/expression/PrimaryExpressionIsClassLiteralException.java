package org.datanucleus.query.expression;

import org.datanucleus.exceptions.NucleusException;

public class PrimaryExpressionIsClassLiteralException extends NucleusException {
   private static final long serialVersionUID = 5464896171302876686L;
   Literal literal;

   public PrimaryExpressionIsClassLiteralException(Class cls) {
      super("PrimaryExpression should be a Literal representing class " + cls.getName());
      this.literal = new Literal(cls);
   }

   public Literal getLiteral() {
      return this.literal;
   }
}
