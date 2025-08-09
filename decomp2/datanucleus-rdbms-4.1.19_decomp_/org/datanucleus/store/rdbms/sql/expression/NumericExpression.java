package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigInteger;
import java.util.List;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class NumericExpression extends SQLExpression {
   public NumericExpression(SQLStatement stmt, JavaTypeMapping mapping, String sql) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.st.clearStatement();
      this.st.append(sql);
   }

   public NumericExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public NumericExpression(Expression.MonadicOperator op, SQLExpression expr1) {
      super(op, expr1);
   }

   public NumericExpression(SQLExpression expr1, Expression.DyadicOperator op, SQLExpression expr2) {
      super(expr1, op, expr2);
   }

   public NumericExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args, (List)null);
   }

   public NumericExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      super(stmt, mapping, functionName, args, types);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else if (expr instanceof NullLiteral) {
            return expr.eq(this);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_EQ, ExpressionUtils.getNumericExpression(expr)) : super.eq(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else if (expr instanceof NullLiteral) {
            return expr.ne(this);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_NOTEQ, ExpressionUtils.getNumericExpression(expr)) : super.ne(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_LT, expr);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_LT, expr);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_LT, ExpressionUtils.getNumericExpression(expr)) : super.lt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_LTEQ, expr);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_LTEQ, expr);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_LTEQ, ExpressionUtils.getNumericExpression(expr)) : super.le(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_GT, expr);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_GT, expr);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_GT, ExpressionUtils.getNumericExpression(expr)) : super.gt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_GTEQ, expr);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_GTEQ, expr);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_GTEQ, ExpressionUtils.getNumericExpression(expr)) : super.ge(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      return new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }

   public SQLExpression add(SQLExpression expr) {
      if (expr instanceof NumericExpression) {
         return (new NumericExpression(this, Expression.OP_ADD, expr)).encloseInParentheses();
      } else if (expr instanceof StringExpression) {
         StringExpression strExpr = (StringExpression)this.stmt.getSQLExpressionFactory().invokeOperation("numericToString", this, (SQLExpression)null);
         return new StringExpression(strExpr, Expression.OP_CONCAT, expr);
      } else if (expr instanceof CharacterExpression) {
         return (new NumericExpression(this, Expression.OP_ADD, ExpressionUtils.getNumericExpression(expr))).encloseInParentheses();
      } else {
         return expr instanceof NullLiteral ? expr : super.add(expr);
      }
   }

   public SQLExpression sub(SQLExpression expr) {
      if (expr instanceof NumericExpression) {
         return (new NumericExpression(this, Expression.OP_SUB, expr)).encloseInParentheses();
      } else {
         return expr instanceof CharacterExpression ? (new NumericExpression(this, Expression.OP_SUB, ExpressionUtils.getNumericExpression(expr))).encloseInParentheses() : super.sub(expr);
      }
   }

   public SQLExpression mul(SQLExpression expr) {
      if (expr instanceof NumericExpression) {
         return (new NumericExpression(this, Expression.OP_MUL, expr)).encloseInParentheses();
      } else {
         return expr instanceof CharacterExpression ? (new NumericExpression(this, Expression.OP_MUL, ExpressionUtils.getNumericExpression(expr))).encloseInParentheses() : super.mul(expr);
      }
   }

   public SQLExpression div(SQLExpression expr) {
      if (expr instanceof NumericExpression) {
         return (new NumericExpression(this, Expression.OP_DIV, expr)).encloseInParentheses();
      } else {
         return expr instanceof CharacterExpression ? (new NumericExpression(this, Expression.OP_DIV, ExpressionUtils.getNumericExpression(expr))).encloseInParentheses() : super.div(expr);
      }
   }

   public SQLExpression mod(SQLExpression expr) {
      try {
         if (expr instanceof NumericExpression) {
            return this.stmt.getSQLExpressionFactory().invokeOperation("mod", this, expr).encloseInParentheses();
         }

         if (expr instanceof CharacterExpression) {
            return this.stmt.getSQLExpressionFactory().invokeOperation("mod", this, ExpressionUtils.getNumericExpression(expr)).encloseInParentheses();
         }
      } catch (UnsupportedOperationException var3) {
      }

      return new NumericExpression(this, Expression.OP_MOD, expr);
   }

   public SQLExpression neg() {
      return new NumericExpression(Expression.OP_NEG, this);
   }

   public SQLExpression com() {
      return this.neg().sub(new IntegerLiteral(this.stmt, this.mapping, BigInteger.ONE, this.parameterName));
   }

   public SQLExpression bitAnd(SQLExpression expr) {
      return super.bitAnd(expr);
   }

   public SQLExpression bitOr(SQLExpression expr) {
      return super.bitOr(expr);
   }
}
