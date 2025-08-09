package org.datanucleus.store.rdbms.sql.operation;

import java.util.ArrayList;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;

public class Mod2Operation extends AbstractSQLOperation {
   public SQLExpression getExpression(SQLExpression expr, SQLExpression expr2) {
      ArrayList args = new ArrayList();
      args.add(expr);
      args.add(expr2);
      return new NumericExpression(expr.getSQLStatement(), this.getMappingForClass(Integer.TYPE), "MOD", args);
   }
}
