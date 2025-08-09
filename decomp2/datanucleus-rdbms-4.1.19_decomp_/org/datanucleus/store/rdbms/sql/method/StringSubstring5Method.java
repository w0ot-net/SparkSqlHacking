package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.IntegerLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringSubstring5Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 2) {
         if (args.size() == 1) {
            SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
            SQLExpression startExpr = (SQLExpression)args.get(0);
            if (!(startExpr instanceof NumericExpression) && !(startExpr instanceof IntegerLiteral) && !(startExpr instanceof ParameterLiteral)) {
               throw new NucleusException(Localiser.msg("060003", new Object[]{"substring", "StringExpression", 0, "NumericExpression/IntegerLiteral/ParameterLiteral"}));
            } else {
               StringExpression strExpr = new StringExpression(this.stmt, (SQLTable)null, this.getMappingForClass(String.class));
               SQLText sql = strExpr.toSQLText();
               sql.append("SUBSTRING(").append(expr).append(" FROM CAST(").append(startExpr.add(one)).append(" AS INTEGER))");
               return strExpr;
            }
         } else {
            SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
            SQLExpression startExpr = (SQLExpression)args.get(0);
            if (!(startExpr instanceof NumericExpression)) {
               throw new NucleusException(Localiser.msg("060003", new Object[]{"substring", "StringExpression", 0, "NumericExpression"}));
            } else {
               SQLExpression endExpr = (SQLExpression)args.get(1);
               if (!(endExpr instanceof NumericExpression)) {
                  throw new NucleusException(Localiser.msg("060003", new Object[]{"substring", "StringExpression", 1, "NumericExpression"}));
               } else {
                  StringExpression strExpr = new StringExpression(this.stmt, (SQLTable)null, this.getMappingForClass(String.class));
                  SQLText sql = strExpr.toSQLText();
                  sql.append("SUBSTRING(").append(expr).append(" FROM CAST(").append(startExpr.add(one)).append(" AS INTEGER) FOR CAST(").append(endExpr.sub(startExpr)).append(" AS INTEGER))");
                  return strExpr;
               }
            }
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"substring", "StringExpression", 0, "NumericExpression/IntegerLiteral/ParameterLiteral"}));
      }
   }
}
