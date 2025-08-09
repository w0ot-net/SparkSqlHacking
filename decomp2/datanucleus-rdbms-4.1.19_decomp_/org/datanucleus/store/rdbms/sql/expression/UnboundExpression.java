package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class UnboundExpression extends SQLExpression {
   protected String variableName;

   public UnboundExpression(SQLStatement stmt, String variableName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)null);
      this.variableName = variableName;
   }

   public String getVariableName() {
      return this.variableName;
   }
}
