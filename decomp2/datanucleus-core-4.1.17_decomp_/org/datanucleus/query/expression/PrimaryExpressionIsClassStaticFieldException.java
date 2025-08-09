package org.datanucleus.query.expression;

import java.lang.reflect.Field;
import org.datanucleus.exceptions.NucleusException;

public class PrimaryExpressionIsClassStaticFieldException extends NucleusException {
   private static final long serialVersionUID = -5061563944018227349L;
   Field field;

   public PrimaryExpressionIsClassStaticFieldException(Field fld) {
      super("PrimaryExpression should be a Literal representing field " + fld.getName());
      this.field = fld;
   }

   public Field getLiteralField() {
      return this.field;
   }
}
