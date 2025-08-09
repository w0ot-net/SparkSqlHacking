package org.datanucleus.store.rdbms.sql.operation;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;

public class Mod3Operation extends AbstractSQLOperation {
   public SQLExpression getExpression(SQLExpression expr, SQLExpression expr2) {
      List args = new ArrayList();
      List types = new ArrayList();
      types.add("BIGINT");
      List argsOp1 = new ArrayList();
      argsOp1.add(expr);
      args.add(new NumericExpression(expr.getSQLStatement(), this.getMappingForClass(Integer.TYPE), "CAST", argsOp1, types));
      List argsOp2 = new ArrayList();
      argsOp2.add(expr2);
      args.add(new NumericExpression(expr.getSQLStatement(), this.getMappingForClass(Integer.TYPE), "CAST", argsOp2, types));
      return new NumericExpression(expr.getSQLStatement(), this.getMappingForClass(Integer.TYPE), "MOD", args);
   }
}
