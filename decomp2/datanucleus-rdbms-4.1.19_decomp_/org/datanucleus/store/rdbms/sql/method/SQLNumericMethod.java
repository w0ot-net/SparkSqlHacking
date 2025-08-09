package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;

public class SQLNumericMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (args != null && args.size() == 1) {
         SQLExpression expr = (SQLExpression)args.get(0);
         if (!(expr instanceof StringLiteral)) {
            throw new NucleusUserException("Cannot use SQL_numeric() without string argument");
         } else {
            String sql = (String)((StringLiteral)expr).getValue();
            JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, false);
            NumericExpression retExpr = new NumericExpression(this.stmt, m, sql);
            return retExpr;
         }
      } else {
         throw new NucleusUserException("Cannot invoke SQL_numeric() without a string argument");
      }
   }
}
