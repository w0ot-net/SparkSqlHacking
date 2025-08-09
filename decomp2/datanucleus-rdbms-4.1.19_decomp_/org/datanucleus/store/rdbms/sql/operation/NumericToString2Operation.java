package org.datanucleus.store.rdbms.sql.operation;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;

public class NumericToString2Operation extends AbstractSQLOperation {
   public SQLExpression getExpression(SQLExpression expr, SQLExpression expr2) {
      JavaTypeMapping m = this.exprFactory.getMappingForType(String.class, false);
      if (expr instanceof SQLLiteral) {
         return ((SQLLiteral)expr).getValue() == null ? new StringLiteral(expr.getSQLStatement(), m, (Object)null, (String)null) : new StringLiteral(expr.getSQLStatement(), m, ((SQLLiteral)expr).getValue().toString(), (String)null);
      } else {
         List args = new ArrayList();
         args.add(expr);
         List types = new ArrayList();
         types.add("CHAR(4000)");
         return new StringExpression(expr.getSQLStatement(), m, "CAST", args, types);
      }
   }
}
