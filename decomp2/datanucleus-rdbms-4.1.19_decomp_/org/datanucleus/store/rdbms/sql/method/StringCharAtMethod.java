package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.IntegerLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class StringCharAtMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 1) {
         SQLExpression startExpr = (SQLExpression)args.get(0);
         if (!(startExpr instanceof NumericExpression) && !(startExpr instanceof IntegerLiteral) && !(startExpr instanceof ParameterLiteral)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"charAt", "StringExpression", 0, "NumericExpression/IntegerLiteral/ParameterLiteral"}));
         } else {
            SQLExpression endExpr = startExpr.add(ExpressionUtils.getLiteralForOne(this.stmt));
            List<SQLExpression> newArgs = new ArrayList(2);
            newArgs.add(startExpr);
            newArgs.add(endExpr);
            return this.exprFactory.invokeMethod(this.stmt, String.class.getName(), "substring", expr, newArgs);
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"charAt", "StringExpression", 0, "NumericExpression/IntegerLiteral/ParameterLiteral"}));
      }
   }
}
