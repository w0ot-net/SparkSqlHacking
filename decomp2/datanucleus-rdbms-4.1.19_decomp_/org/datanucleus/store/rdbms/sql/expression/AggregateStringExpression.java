package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;

public class AggregateStringExpression extends StringExpression implements AggregateExpression {
   public AggregateStringExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args);
   }
}
