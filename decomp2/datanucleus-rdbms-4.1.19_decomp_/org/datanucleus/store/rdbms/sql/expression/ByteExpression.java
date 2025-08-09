package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class ByteExpression extends NumericExpression {
   public ByteExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public ByteExpression(Expression.MonadicOperator op, SQLExpression expr1) {
      super(op, expr1);
   }

   public ByteExpression(SQLExpression expr1, Expression.DyadicOperator op, SQLExpression expr2) {
      super(expr1, op, expr2);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.eq(this);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_EQ, expr) : super.eq(expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.ne(this);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : super.ne(expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.lt(this);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_LT, expr) : super.lt(expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.le(this);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_LTEQ, expr) : super.le(expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.gt(this);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_GT, expr) : super.gt(expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.ge(this);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_GTEQ, expr) : super.ge(expr);
      }
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, Byte.class.getName(), methodName, this, args);
   }
}
