package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.ObjectExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;

public class SQLFunctionMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (args != null && args.size() >= 1) {
         SQLExpression expr = (SQLExpression)args.get(0);
         if (!(expr instanceof StringLiteral)) {
            throw new NucleusUserException("Cannot use SQL_function() without first argument defining the function");
         } else {
            String sql = (String)((StringLiteral)expr).getValue();
            List<SQLExpression> funcArgs = new ArrayList();
            if (args.size() > 1) {
               funcArgs.addAll(args.subList(1, args.size()));
            }

            JavaTypeMapping m = this.exprFactory.getMappingForType(Object.class, false);
            ObjectExpression retExpr = new ObjectExpression(this.stmt, m, sql, funcArgs);
            return retExpr;
         }
      } else {
         throw new NucleusUserException("Cannot invoke SQL_function() without first argument defining the function");
      }
   }
}
