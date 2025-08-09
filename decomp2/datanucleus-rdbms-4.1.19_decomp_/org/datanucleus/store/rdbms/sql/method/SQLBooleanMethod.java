package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;

public class SQLBooleanMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (args != null && args.size() == 1) {
         SQLExpression expr = (SQLExpression)args.get(0);
         if (!(expr instanceof StringLiteral)) {
            throw new NucleusUserException("Cannot use SQL_boolean() without string argument");
         } else {
            String sql = (String)((StringLiteral)expr).getValue();
            JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, false);
            BooleanExpression retExpr = new BooleanExpression(this.stmt, m, sql);
            return retExpr;
         }
      } else {
         throw new NucleusUserException("Cannot invoke SQL_boolean() without a string argument");
      }
   }
}
