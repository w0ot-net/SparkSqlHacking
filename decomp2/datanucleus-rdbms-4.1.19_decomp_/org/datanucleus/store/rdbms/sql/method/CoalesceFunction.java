package org.datanucleus.store.rdbms.sql.method;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ObjectExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class CoalesceFunction extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr != null) {
         throw new NucleusException(Localiser.msg("060002", new Object[]{"COALESCE", expr}));
      } else {
         Class exprType = null;
         Class cls = null;
         int clsLevel = 0;

         for(int i = 0; i < args.size(); ++i) {
            SQLExpression argExpr = (SQLExpression)args.get(i);
            if (exprType == null) {
               if (argExpr instanceof NumericExpression) {
                  exprType = NumericExpression.class;
                  cls = Integer.class;
               } else if (argExpr instanceof StringExpression) {
                  exprType = StringExpression.class;
                  cls = String.class;
               } else if (argExpr instanceof TemporalExpression) {
                  exprType = TemporalExpression.class;
                  cls = argExpr.getJavaTypeMapping().getJavaType();
               } else {
                  exprType = argExpr.getClass();
                  cls = argExpr.getJavaTypeMapping().getJavaType();
               }
            } else if (!exprType.isAssignableFrom(argExpr.getClass())) {
               throw new NucleusUserException("COALESCE invocation first argument of type " + exprType.getName() + " yet subsequent argument of type " + argExpr.getClass().getName());
            }

            if (exprType == NumericExpression.class) {
               Class argType = argExpr.getJavaTypeMapping().getJavaType();
               if (clsLevel >= 5 || argType != Double.TYPE && argType != Double.class) {
                  if (clsLevel >= 4 || argType != Float.TYPE && argType != Float.class) {
                     if (clsLevel < 3 && argType == BigDecimal.class) {
                        cls = BigDecimal.class;
                        clsLevel = 3;
                     } else if (clsLevel < 2 && argType == BigInteger.class) {
                        cls = BigInteger.class;
                        clsLevel = 2;
                     } else if (clsLevel < 1 && (argType == Long.TYPE || argType == Long.class)) {
                        cls = Long.class;
                        clsLevel = 1;
                     }
                  } else {
                     cls = Float.class;
                     clsLevel = 4;
                  }
               } else {
                  cls = Double.class;
                  clsLevel = 5;
               }
            }
         }

         if (exprType == NumericExpression.class) {
            return new NumericExpression(this.stmt, this.getMappingForClass(cls), "COALESCE", args);
         } else if (exprType == StringExpression.class) {
            return new StringExpression(this.stmt, this.getMappingForClass(cls), "COALESCE", args);
         } else if (exprType == TemporalExpression.class) {
            return new TemporalExpression(this.stmt, this.getMappingForClass(cls), "COALESCE", args);
         } else {
            return new ObjectExpression(this.stmt, this.getMappingForClass(cls), "COALESCE", args);
         }
      }
   }
}
