package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigInteger;
import java.util.List;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class CharacterExpression extends SQLExpression {
   public CharacterExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public CharacterExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args, (List)null);
   }

   public CharacterExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      super(stmt, mapping, functionName, args, types);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof NullLiteral) {
            return expr.eq(this);
         } else if (!(expr instanceof ColumnExpression) && !(expr instanceof CharacterExpression)) {
            if (expr instanceof StringLiteral) {
               Object value = ((StringLiteral)expr).getValue();
               if (value instanceof String && ((String)value).length() > 1) {
                  throw new NucleusUserException("Can't perform equality comparison between a character and a String of more than 1 character (" + value + ") !");
               } else {
                  return new BooleanExpression(this, Expression.OP_EQ, expr);
               }
            } else if (expr instanceof StringExpression) {
               return new BooleanExpression(this, Expression.OP_EQ, expr);
            } else {
               return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).eq(expr) : super.eq(expr);
            }
         } else {
            return new BooleanExpression(this, Expression.OP_EQ, expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof NullLiteral) {
            return expr.ne(this);
         } else if (!(expr instanceof ColumnExpression) && !(expr instanceof CharacterExpression) && !(expr instanceof StringExpression)) {
            return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).ne(expr) : super.ne(expr);
         } else {
            return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof NullLiteral) {
            return expr.lt(this);
         } else if (!(expr instanceof ColumnExpression) && !(expr instanceof CharacterExpression) && !(expr instanceof StringExpression)) {
            return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).lt(expr) : super.lt(expr);
         } else {
            return new BooleanExpression(this, Expression.OP_LT, expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof NullLiteral) {
            return expr.le(this);
         } else if (!(expr instanceof ColumnExpression) && !(expr instanceof CharacterExpression) && !(expr instanceof StringExpression)) {
            return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).le(expr) : super.le(expr);
         } else {
            return new BooleanExpression(this, Expression.OP_LTEQ, expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof NullLiteral) {
            return expr.gt(this);
         } else if (!(expr instanceof ColumnExpression) && !(expr instanceof CharacterExpression) && !(expr instanceof StringExpression)) {
            return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).gt(expr) : super.gt(expr);
         } else {
            return new BooleanExpression(this, Expression.OP_GT, expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof NullLiteral) {
            return expr.ge(this);
         } else if (!(expr instanceof ColumnExpression) && !(expr instanceof CharacterExpression) && !(expr instanceof StringExpression)) {
            return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).ge(expr) : super.ge(expr);
         } else {
            return new BooleanExpression(this, Expression.OP_GTEQ, expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public SQLExpression add(SQLExpression expr) {
      if (expr instanceof CharacterExpression) {
         return new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_ADD, ExpressionUtils.getNumericExpression(expr));
      } else {
         return (SQLExpression)(expr instanceof NumericExpression ? new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_ADD, expr) : super.add(expr));
      }
   }

   public SQLExpression sub(SQLExpression expr) {
      if (expr instanceof CharacterExpression) {
         return new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_SUB, ExpressionUtils.getNumericExpression(expr));
      } else {
         return (SQLExpression)(expr instanceof NumericExpression ? new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_SUB, expr) : super.sub(expr));
      }
   }

   public SQLExpression mul(SQLExpression expr) {
      if (expr instanceof NumericExpression) {
         return new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_MUL, expr);
      } else if (expr instanceof CharacterExpression) {
         return new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_MUL, ExpressionUtils.getNumericExpression(expr));
      } else {
         return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).mul(expr) : super.mul(expr);
      }
   }

   public SQLExpression div(SQLExpression expr) {
      if (expr instanceof NumericExpression) {
         return new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_DIV, expr);
      } else if (expr instanceof CharacterExpression) {
         return new NumericExpression(ExpressionUtils.getNumericExpression(this), Expression.OP_DIV, ExpressionUtils.getNumericExpression(expr));
      } else {
         return expr instanceof NumericExpression ? ExpressionUtils.getNumericExpression(this).div(expr) : super.div(expr);
      }
   }

   public SQLExpression mod(SQLExpression expr) {
      try {
         if (expr instanceof CharacterExpression) {
            return this.stmt.getSQLExpressionFactory().invokeOperation("mod", ExpressionUtils.getNumericExpression(this), ExpressionUtils.getNumericExpression(expr)).encloseInParentheses();
         }

         if (expr instanceof NumericExpression) {
            return this.stmt.getSQLExpressionFactory().invokeOperation("mod", ExpressionUtils.getNumericExpression(this), expr);
         }
      } catch (UnsupportedOperationException var3) {
      }

      return new NumericExpression(this, Expression.OP_MOD, expr);
   }

   public SQLExpression neg() {
      return new NumericExpression(Expression.OP_NEG, ExpressionUtils.getNumericExpression(this));
   }

   public SQLExpression com() {
      return ExpressionUtils.getNumericExpression(this).neg().sub(new IntegerLiteral(this.stmt, this.mapping, BigInteger.ONE, (String)null));
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      return new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, Character.class.getName(), methodName, this, args);
   }
}
