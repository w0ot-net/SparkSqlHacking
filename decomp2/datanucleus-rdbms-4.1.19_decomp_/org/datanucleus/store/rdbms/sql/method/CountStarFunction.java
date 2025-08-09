package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.AggregateNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class CountStarFunction extends AbstractSQLMethod {
   protected String getFunctionName() {
      return "COUNT";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr == null) {
         if (args != null && args.size() > 0) {
            throw new NucleusException("COUNTSTAR takes no argument");
         } else {
            return new AggregateNumericExpression(this.stmt, this.getMappingForClass(Long.TYPE), "COUNT(*)");
         }
      } else {
         throw new NucleusException(Localiser.msg("060002", new Object[]{"COUNT", expr}));
      }
   }
}
