package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.AggregateNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ObjectExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class CountFunction extends AbstractSQLMethod {
   protected String getFunctionName() {
      return "COUNT";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr == null) {
         if (args != null && args.size() == 1) {
            SQLExpression argExpr = (SQLExpression)args.get(0);
            if (argExpr.getNumberOfSubExpressions() > 1 && argExpr instanceof ObjectExpression) {
               ((ObjectExpression)argExpr).useFirstColumnOnly();
            }

            return new AggregateNumericExpression(this.stmt, this.getMappingForClass(Long.TYPE), "COUNT", args);
         } else {
            throw new NucleusException("COUNT is only supported with a single argument");
         }
      } else {
         throw new NucleusException(Localiser.msg("060002", new Object[]{"COUNT", expr}));
      }
   }
}
