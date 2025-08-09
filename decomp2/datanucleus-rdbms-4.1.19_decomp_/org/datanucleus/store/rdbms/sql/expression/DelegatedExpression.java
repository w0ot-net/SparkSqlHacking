package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;

public abstract class DelegatedExpression extends SQLExpression {
   protected SQLExpression delegate;

   public DelegatedExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public BooleanExpression eq(SQLExpression expr) {
      return this.delegate.eq(expr);
   }

   public BooleanExpression ne(SQLExpression expr) {
      return this.delegate.ne(expr);
   }

   public SQLExpression add(SQLExpression expr) {
      return this.delegate.add(expr);
   }

   public SQLExpression div(SQLExpression expr) {
      return this.delegate.div(expr);
   }

   public BooleanExpression ge(SQLExpression expr) {
      return this.delegate.ge(expr);
   }

   public BooleanExpression gt(SQLExpression expr) {
      return this.delegate.gt(expr);
   }

   public BooleanExpression le(SQLExpression expr) {
      return this.delegate.le(expr);
   }

   public BooleanExpression lt(SQLExpression expr) {
      return this.delegate.lt(expr);
   }

   public SQLExpression mod(SQLExpression expr) {
      return this.delegate.mod(expr);
   }

   public SQLExpression mul(SQLExpression expr) {
      return this.delegate.mul(expr);
   }

   public SQLExpression sub(SQLExpression expr) {
      return this.delegate.sub(expr);
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, this.mapping.getJavaType().getName(), methodName, this, args);
   }

   public SQLText toSQLText() {
      return this.delegate.toSQLText();
   }

   public SQLExpression getDelegate() {
      return this.delegate;
   }

   public boolean isParameter() {
      return this.delegate.isParameter();
   }
}
