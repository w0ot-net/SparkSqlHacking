package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.AggregateNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class SumFunction extends SimpleNumericAggregateMethod {
   protected String getFunctionName() {
      return "SUM";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr != null) {
         throw new NucleusException(Localiser.msg("060002", new Object[]{this.getFunctionName(), expr}));
      } else if (args != null && args.size() == 1) {
         SQLExpression argExpr = (SQLExpression)args.get(0);
         JavaTypeMapping m = null;
         Class cls = argExpr.getJavaTypeMapping().getJavaType();
         if (cls != Integer.class && cls != Short.class && cls != Long.class) {
            if (!Number.class.isAssignableFrom(cls)) {
               throw new NucleusUserException("Cannot perform static SUM with arg of type " + cls.getName());
            }

            m = this.getMappingForClass(argExpr.getJavaTypeMapping().getJavaType());
         } else {
            m = this.getMappingForClass(Long.class);
         }

         return new AggregateNumericExpression(this.stmt, m, this.getFunctionName(), args);
      } else {
         throw new NucleusException(this.getFunctionName() + " is only supported with a single argument");
      }
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
