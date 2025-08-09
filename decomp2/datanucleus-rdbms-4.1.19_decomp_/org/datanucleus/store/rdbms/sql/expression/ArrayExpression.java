package org.datanucleus.store.rdbms.sql.expression;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class ArrayExpression extends SQLExpression {
   protected List elementExpressions;

   public ArrayExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public ArrayExpression(SQLStatement stmt, JavaTypeMapping mapping, SQLExpression[] exprs) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.elementExpressions = new ArrayList();

      for(int i = 0; i < exprs.length; ++i) {
         this.elementExpressions.add(exprs[i]);
      }

   }

   public List getElementExpressions() {
      return this.elementExpressions;
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, "ARRAY", methodName, this, args);
   }

   public BooleanExpression eq(SQLExpression expr) {
      return expr instanceof NullLiteral ? expr.eq(this) : super.eq(expr);
   }

   public BooleanExpression ne(SQLExpression expr) {
      return expr instanceof NullLiteral ? expr.ne(this) : super.ne(expr);
   }
}
