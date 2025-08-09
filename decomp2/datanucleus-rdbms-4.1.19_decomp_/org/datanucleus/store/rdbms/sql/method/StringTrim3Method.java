package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;

public class StringTrim3Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 1) {
         throw new NucleusException("TRIM has incorrect number of args");
      } else {
         SQLExpression trimCharExpr = null;
         if (args != null && args.size() > 0) {
            trimCharExpr = (SQLExpression)args.get(0);
         }

         List trimArgs = new ArrayList();
         if (trimCharExpr == null) {
            trimArgs.add(expr);
         } else {
            StringExpression argExpr = new StringExpression(this.stmt, expr.getJavaTypeMapping(), "NULL", (List)null);
            SQLText sql = argExpr.toSQLText();
            sql.clearStatement();
            sql.append(this.getTrimSpecKeyword() + " ");
            sql.append(trimCharExpr);
            sql.append(" FROM ");
            sql.append(expr);
            trimArgs.add(argExpr);
         }

         StringExpression trimExpr = new StringExpression(this.stmt, expr.getJavaTypeMapping(), "TRIM", trimArgs);
         return trimExpr;
      }
   }

   protected String getTrimSpecKeyword() {
      return "BOTH";
   }
}
