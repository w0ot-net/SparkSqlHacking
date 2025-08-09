package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.AggregateNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;

public class AvgWithCastFunction extends AvgFunction {
   protected SQLExpression getAggregateExpression(List args, JavaTypeMapping m) {
      Class argType = ((SQLExpression)args.get(0)).getJavaTypeMapping().getJavaType();
      List<SQLExpression> checkedArgs = null;
      if (!argType.equals(Double.class) && !argType.equals(Float.class)) {
         checkedArgs = new ArrayList();
         checkedArgs.add(new StringExpression(this.stmt, m, "CAST", args, Arrays.asList("double")));
      } else {
         checkedArgs = args;
      }

      return new AggregateNumericExpression(this.stmt, m, this.getFunctionName(), checkedArgs);
   }
}
