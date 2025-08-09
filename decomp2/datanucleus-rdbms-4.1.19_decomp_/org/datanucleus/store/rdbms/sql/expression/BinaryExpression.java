package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class BinaryExpression extends SQLExpression {
   public BinaryExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public BinaryExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      super(stmt, mapping, functionName, args, types);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.eq(this);
      } else {
         return expr instanceof BinaryExpression ? new BooleanExpression(this, Expression.OP_EQ, expr) : super.eq(expr);
      }
   }

   public BooleanExpression noteq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.ne(this);
      } else {
         return expr instanceof BinaryExpression ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : super.ne(expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      return expr instanceof BinaryExpression ? new BooleanExpression(this, Expression.OP_LT, expr) : super.lt(expr);
   }

   public BooleanExpression lteq(SQLExpression expr) {
      return expr instanceof BinaryExpression ? new BooleanExpression(this, Expression.OP_LTEQ, expr) : super.le(expr);
   }

   public BooleanExpression gt(SQLExpression expr) {
      return expr instanceof BinaryExpression ? new BooleanExpression(this, Expression.OP_GT, expr) : super.gt(expr);
   }

   public BooleanExpression gteq(SQLExpression expr) {
      return expr instanceof BinaryExpression ? new BooleanExpression(this, Expression.OP_GTEQ, expr) : super.ge(expr);
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      return new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }
}
