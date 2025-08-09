package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public abstract class SimpleStringMethod extends AbstractSQLMethod {
   protected abstract String getFunctionName();

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr == null) {
         return new StringExpression(this.stmt, this.getMappingForClass(String.class), this.getFunctionName(), args);
      } else if (expr instanceof StringExpression) {
         ArrayList functionArgs = new ArrayList();
         functionArgs.add(expr);
         return new StringExpression(this.stmt, this.getMappingForClass(String.class), this.getFunctionName(), functionArgs);
      } else {
         throw new NucleusException(Localiser.msg("060002", new Object[]{this.getFunctionName(), expr}));
      }
   }
}
