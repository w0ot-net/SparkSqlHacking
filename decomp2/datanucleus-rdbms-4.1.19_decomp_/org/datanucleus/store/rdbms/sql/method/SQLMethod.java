package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;

public interface SQLMethod {
   SQLExpression getExpression(SQLExpression var1, List var2);

   void setStatement(SQLStatement var1);
}
