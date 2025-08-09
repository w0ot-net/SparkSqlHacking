package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class ParameterLiteral extends SQLExpression implements SQLLiteral {
   protected String name;
   protected Object value;

   public ParameterLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      this.value = value;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public Object getValue() {
      return this.value;
   }

   public SQLExpression add(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.add(expr) : expr.add(this);
   }

   public BooleanExpression eq(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.eq(expr) : expr.eq(this);
   }

   public BooleanExpression ge(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.ge(expr) : expr.lt(this);
   }

   public BooleanExpression gt(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.gt(expr) : expr.le(this);
   }

   public BooleanExpression le(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.le(expr) : expr.gt(this);
   }

   public BooleanExpression lt(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.lt(expr) : expr.ge(this);
   }

   public BooleanExpression ne(SQLExpression expr) {
      return expr instanceof ParameterLiteral ? super.ne(expr) : expr.ne(this);
   }

   public void setNotParameter() {
   }
}
