package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;

public class StringTrim2Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr instanceof StringLiteral) {
         String val = (String)((StringLiteral)expr).getValue();
         return new StringLiteral(this.stmt, expr.getJavaTypeMapping(), val.trim(), (String)null);
      } else {
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(expr);
         StringExpression strExpr = new StringExpression(this.stmt, this.getMappingForClass(String.class), "RTRIM", funcArgs);
         args.clear();
         args.add(strExpr);
         return new StringExpression(this.stmt, this.getMappingForClass(String.class), "LTRIM", args);
      }
   }
}
