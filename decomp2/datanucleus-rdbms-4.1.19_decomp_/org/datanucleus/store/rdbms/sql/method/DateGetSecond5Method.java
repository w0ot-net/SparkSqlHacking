package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class DateGetSecond5Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!(expr instanceof TemporalExpression)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"getSecond()", expr}));
      } else {
         List secondArgs = new ArrayList();
         secondArgs.add(expr);
         NumericExpression secondExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "SECOND", secondArgs);
         List castArgs = new ArrayList();
         castArgs.add(secondExpr);
         return new NumericExpression(this.stmt, this.getMappingForClass(Integer.class), "CAST", castArgs, Arrays.asList("INTEGER"));
      }
   }
}
