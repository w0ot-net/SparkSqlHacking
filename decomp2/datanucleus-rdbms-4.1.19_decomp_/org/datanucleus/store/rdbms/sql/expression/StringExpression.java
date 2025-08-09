package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class StringExpression extends SQLExpression {
   public StringExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public StringExpression(Expression.MonadicOperator op, SQLExpression expr1) {
      super(op, expr1);
   }

   public StringExpression(SQLExpression expr1, Expression.DyadicOperator op, SQLExpression expr2) {
      super(expr1, op, expr2);
   }

   public StringExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args, (List)null);
   }

   public StringExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      super(stmt, mapping, functionName, args, types);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.eq(this);
      } else if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else if (expr instanceof EnumExpression) {
            return expr.eq(this);
         } else if (expr instanceof CharacterLiteral) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else if (expr instanceof StringExpression) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else if (expr instanceof ByteLiteral) {
            int value = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_EQ, literal);
         } else if (expr instanceof IntegerLiteral) {
            int value = ((Number)((IntegerLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_EQ, literal);
         } else if (expr instanceof FloatingPointLiteral) {
            int value = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_EQ, literal);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else if (expr instanceof TemporalExpression) {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         } else {
            return expr instanceof EnumLiteral ? new BooleanExpression(this, Expression.OP_EQ, expr) : super.eq(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.ne(this);
      } else if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else if (expr instanceof EnumExpression) {
            return expr.ne(this);
         } else if (expr instanceof CharacterLiteral) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else if (expr instanceof StringExpression) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else if (expr instanceof ByteLiteral) {
            int value = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_NOTEQ, literal);
         } else if (expr instanceof IntegerLiteral) {
            int value = ((Number)((IntegerLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_NOTEQ, literal);
         } else if (expr instanceof FloatingPointLiteral) {
            int value = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_NOTEQ, literal);
         } else if (expr instanceof NumericExpression) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else if (expr instanceof TemporalExpression) {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         } else {
            return expr instanceof EnumLiteral ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : super.ne(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_LT, expr);
         } else if (expr instanceof NullLiteral) {
            return expr.lt(this);
         } else if (expr instanceof EnumExpression) {
            return expr.ge(this);
         } else if (expr instanceof CharacterLiteral) {
            return new BooleanExpression(this, Expression.OP_LT, expr);
         } else if (expr instanceof StringExpression) {
            return new BooleanExpression(this, Expression.OP_LT, expr);
         } else if (expr instanceof ByteLiteral) {
            int value = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_LT, literal);
         } else if (expr instanceof IntegerLiteral) {
            int value = ((Number)((IntegerLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_LT, literal);
         } else if (expr instanceof FloatingPointLiteral) {
            int value = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_LT, literal);
         } else {
            return expr instanceof NumericExpression ? new BooleanExpression(this, Expression.OP_LT, expr) : super.lt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_LTEQ, expr);
         } else if (expr instanceof NullLiteral) {
            return expr.le(this);
         } else if (expr instanceof EnumExpression) {
            return expr.gt(this);
         } else if (expr instanceof CharacterLiteral) {
            return new BooleanExpression(this, Expression.OP_LTEQ, expr);
         } else if (expr instanceof StringExpression) {
            return new BooleanExpression(this, Expression.OP_LTEQ, expr);
         } else if (expr instanceof ByteLiteral) {
            int value = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_LTEQ, literal);
         } else if (expr instanceof IntegerLiteral) {
            int value = ((Number)((IntegerLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_LTEQ, literal);
         } else if (expr instanceof FloatingPointLiteral) {
            int value = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_LTEQ, literal);
         } else {
            return expr instanceof NumericExpression ? new BooleanExpression(this, Expression.OP_LTEQ, expr) : super.le(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_GT, expr);
         } else if (expr instanceof NullLiteral) {
            return expr.gt(this);
         } else if (expr instanceof EnumExpression) {
            return expr.le(this);
         } else if (expr instanceof CharacterLiteral) {
            return new BooleanExpression(this, Expression.OP_GT, expr);
         } else if (expr instanceof StringExpression) {
            return new BooleanExpression(this, Expression.OP_GT, expr);
         } else if (expr instanceof ByteLiteral) {
            int value = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_GT, literal);
         } else if (expr instanceof IntegerLiteral) {
            int value = ((Number)((IntegerLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_GT, literal);
         } else if (expr instanceof FloatingPointLiteral) {
            int value = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_GT, literal);
         } else {
            return expr instanceof NumericExpression ? new BooleanExpression(this, Expression.OP_GT, expr) : super.gt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ColumnExpression) {
            return new BooleanExpression(this, Expression.OP_GTEQ, expr);
         } else if (expr instanceof NullLiteral) {
            return expr.ge(this);
         } else if (expr instanceof EnumExpression) {
            return expr.lt(this);
         } else if (expr instanceof CharacterLiteral) {
            return new BooleanExpression(this, Expression.OP_GTEQ, expr);
         } else if (expr instanceof StringExpression) {
            return new BooleanExpression(this, Expression.OP_GTEQ, expr);
         } else if (expr instanceof ByteLiteral) {
            int value = ((BigInteger)((ByteLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_GTEQ, literal);
         } else if (expr instanceof IntegerLiteral) {
            int value = ((Number)((IntegerLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_GTEQ, literal);
         } else if (expr instanceof FloatingPointLiteral) {
            int value = ((BigDecimal)((FloatingPointLiteral)expr).getValue()).intValue();
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)value), (String)null);
            return new BooleanExpression(this, Expression.OP_GTEQ, literal);
         } else {
            return expr instanceof NumericExpression ? new BooleanExpression(this, Expression.OP_GTEQ, expr) : super.ge(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public SQLExpression add(SQLExpression expr) {
      if (this instanceof SQLLiteral && this.isParameter() && expr instanceof SQLLiteral && expr.isParameter()) {
         this.stmt.getQueryGenerator().useParameterExpressionAsLiteral((SQLLiteral)this);
         this.stmt.getQueryGenerator().useParameterExpressionAsLiteral((SQLLiteral)expr);
         return (new StringExpression(this, Expression.OP_CONCAT, expr)).encloseInParentheses();
      } else if (expr.isParameter()) {
         return (new StringExpression(this, Expression.OP_CONCAT, expr)).encloseInParentheses();
      } else if (expr instanceof StringLiteral) {
         return (new StringExpression(this, Expression.OP_CONCAT, new StringLiteral(this.stmt, expr.mapping, ((StringLiteral)expr).getValue(), (String)null))).encloseInParentheses();
      } else if (expr instanceof StringExpression) {
         return (new StringExpression(this, Expression.OP_CONCAT, expr)).encloseInParentheses();
      } else if (expr instanceof CharacterExpression) {
         return (new StringExpression(this, Expression.OP_CONCAT, expr)).encloseInParentheses();
      } else if (expr instanceof NumericExpression) {
         StringExpression strExpr = (StringExpression)this.stmt.getSQLExpressionFactory().invokeOperation("numericToString", expr, (SQLExpression)null).encloseInParentheses();
         return (new StringExpression(this, Expression.OP_CONCAT, strExpr)).encloseInParentheses();
      } else {
         return expr instanceof NullLiteral ? expr : (new StringExpression(this, Expression.OP_CONCAT, expr)).encloseInParentheses();
      }
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      return new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, String.class.getName(), methodName, this, args);
   }
}
