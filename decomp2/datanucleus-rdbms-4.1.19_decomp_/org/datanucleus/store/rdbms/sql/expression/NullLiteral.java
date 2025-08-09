package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.NullMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class NullLiteral extends SQLExpression implements SQLLiteral {
   public NullLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)(new NullMapping(stmt.getRDBMSManager())));
      this.st.append("NULL");
   }

   public Object getValue() {
      return null;
   }

   public SQLExpression add(SQLExpression expr) {
      return this;
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), true);
      } else if (expr instanceof ObjectExpression) {
         return expr.eq(this);
      } else {
         return this.stmt.getQueryGenerator() != null && this.stmt.getQueryGenerator().getCompilationComponent() == CompilationComponent.UPDATE ? new BooleanExpression(expr, Expression.OP_EQ, this) : new BooleanExpression(expr, Expression.OP_IS, this);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), false);
      } else {
         return expr instanceof ObjectExpression ? expr.ne(this) : new BooleanExpression(expr, Expression.OP_ISNOT, this);
      }
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
      }
   }
}
