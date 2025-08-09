package org.datanucleus.store.rdbms.sql.operation;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;

public abstract class AbstractSQLOperation implements SQLOperation {
   protected SQLExpressionFactory exprFactory;

   public void setExpressionFactory(SQLExpressionFactory exprFactory) {
      this.exprFactory = exprFactory;
   }

   protected JavaTypeMapping getMappingForClass(Class cls) {
      return this.exprFactory.getMappingForType(cls, true);
   }
}
