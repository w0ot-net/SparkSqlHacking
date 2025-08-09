package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class DateGetMonthMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!(expr instanceof TemporalExpression)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"getMonth()", expr}));
      } else {
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(expr);
         NumericExpression monthExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "MONTH", funcArgs);
         SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
         NumericExpression numExpr = new NumericExpression(monthExpr, Expression.OP_SUB, one);
         numExpr.encloseInParentheses();
         return numExpr;
      }
   }
}
