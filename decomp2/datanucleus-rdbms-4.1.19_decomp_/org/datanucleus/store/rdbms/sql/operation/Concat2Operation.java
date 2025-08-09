package org.datanucleus.store.rdbms.sql.operation;

import java.util.ArrayList;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;

public class Concat2Operation extends AbstractSQLOperation {
   public SQLExpression getExpression(SQLExpression expr, SQLExpression expr2) {
      RDBMSStoreManager storeMgr = expr.getSQLStatement().getRDBMSManager();
      JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(String.class, false);
      ArrayList args = new ArrayList();
      args.add(expr);
      args.add(expr2);
      return new StringExpression(expr.getSQLStatement(), m, "CONCAT", args);
   }
}
