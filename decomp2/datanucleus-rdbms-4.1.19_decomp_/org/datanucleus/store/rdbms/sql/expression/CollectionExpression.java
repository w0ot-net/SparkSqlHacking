package org.datanucleus.store.rdbms.sql.expression;

import java.util.Collection;
import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class CollectionExpression extends SQLExpression {
   public CollectionExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, Collection.class.getName(), methodName, this, args);
   }

   public BooleanExpression eq(SQLExpression expr) {
      return expr instanceof NullLiteral ? (BooleanExpression)this.invoke("isEmpty", (List)null) : super.eq(expr);
   }

   public BooleanExpression ne(SQLExpression expr) {
      return expr instanceof NullLiteral ? this.invoke("isEmpty", (List)null).not() : super.ne(expr);
   }
}
