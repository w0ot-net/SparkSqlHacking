package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;

public class AggregateNumericExpression extends NumericExpression implements AggregateExpression {
   public AggregateNumericExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args);
   }

   public AggregateNumericExpression(SQLStatement stmt, JavaTypeMapping mapping, String sql) {
      super(stmt, mapping, sql);
   }
}
