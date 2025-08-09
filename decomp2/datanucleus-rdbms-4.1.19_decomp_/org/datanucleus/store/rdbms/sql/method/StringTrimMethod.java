package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;

public class StringTrimMethod extends SimpleStringMethod {
   protected String getFunctionName() {
      return "TRIM";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!expr.isParameter() && expr instanceof StringLiteral) {
         String val = (String)((StringLiteral)expr).getValue();
         if (val != null) {
            val = val.trim();
         }

         return new StringLiteral(this.stmt, expr.getJavaTypeMapping(), val, (String)null);
      } else {
         return super.getExpression(expr, args);
      }
   }
}
