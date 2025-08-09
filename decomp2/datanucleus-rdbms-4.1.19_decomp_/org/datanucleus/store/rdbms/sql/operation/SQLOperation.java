package org.datanucleus.store.rdbms.sql.operation;

import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;

public interface SQLOperation {
   SQLExpression getExpression(SQLExpression var1, SQLExpression var2);

   void setExpressionFactory(SQLExpressionFactory var1);
}
