package org.datanucleus.store.rdbms.sql.method;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.ByteLiteral;
import org.datanucleus.store.rdbms.sql.expression.FloatingPointLiteral;
import org.datanucleus.store.rdbms.sql.expression.IllegalExpressionOperationException;
import org.datanucleus.store.rdbms.sql.expression.IntegerLiteral;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;

public class MathFloorMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (args != null && args.size() != 0) {
         SQLExpression expr = (SQLExpression)args.get(0);
         if (expr == null) {
            return new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null);
         } else if (expr instanceof SQLLiteral) {
            if (expr instanceof ByteLiteral) {
               int originalValue = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
               BigInteger absValue = new BigInteger(String.valueOf(Math.floor((double)originalValue)));
               return new ByteLiteral(this.stmt, expr.getJavaTypeMapping(), absValue, (String)null);
            } else if (expr instanceof IntegerLiteral) {
               int originalValue = ((Number)((IntegerLiteral)expr).getValue()).intValue();
               Double absValue = new Double(Math.floor((double)originalValue));
               return new FloatingPointLiteral(this.stmt, expr.getJavaTypeMapping(), absValue, (String)null);
            } else if (expr instanceof FloatingPointLiteral) {
               double originalValue = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).doubleValue();
               Double absValue = new Double(Math.floor(originalValue));
               return new FloatingPointLiteral(this.stmt, expr.getJavaTypeMapping(), absValue, (String)null);
            } else {
               throw new IllegalExpressionOperationException("Math.floor()", expr);
            }
         } else {
            return this.exprFactory.invokeMethod(this.stmt, (String)null, "floor", (SQLExpression)null, args);
         }
      } else {
         throw new NucleusUserException("Cannot invoke Math.floor without an argument");
      }
   }
}
