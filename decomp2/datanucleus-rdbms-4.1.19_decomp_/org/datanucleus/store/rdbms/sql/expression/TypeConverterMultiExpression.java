package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class TypeConverterMultiExpression extends ObjectExpression {
   public TypeConverterMultiExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, this.mapping.getJavaType().getName(), methodName, this, args);
   }
}
