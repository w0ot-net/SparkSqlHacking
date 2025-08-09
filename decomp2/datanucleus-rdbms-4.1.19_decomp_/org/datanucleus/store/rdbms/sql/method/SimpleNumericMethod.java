package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public abstract class SimpleNumericMethod extends AbstractSQLMethod {
   protected abstract String getFunctionName();

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr == null) {
         return new NumericExpression(this.stmt, this.getMappingForClass(this.getClassForMapping()), this.getFunctionName(), args);
      } else {
         throw new NucleusException(Localiser.msg("060002", new Object[]{this.getFunctionName(), expr}));
      }
   }

   protected abstract Class getClassForMapping();
}
