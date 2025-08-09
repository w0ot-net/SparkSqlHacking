package org.datanucleus.store.rdbms.sql.method;

import java.util.Date;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class CurrentTimeFunction extends AbstractSQLMethod {
   protected String getFunctionName() {
      return "CURRENT_TIME";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr == null) {
         SQLExpression dateExpr = new TemporalExpression(this.stmt, this.getMappingForClass(this.getClassForMapping()), this.getFunctionName(), args);
         dateExpr.toSQLText().clearStatement();
         dateExpr.toSQLText().append(this.getFunctionName());
         return dateExpr;
      } else {
         throw new NucleusException(Localiser.msg("060002", new Object[]{this.getFunctionName(), expr}));
      }
   }

   protected Class getClassForMapping() {
      return Date.class;
   }
}
