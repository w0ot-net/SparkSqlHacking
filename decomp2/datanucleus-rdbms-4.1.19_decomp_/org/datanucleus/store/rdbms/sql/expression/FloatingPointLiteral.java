package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigDecimal;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.util.StringUtils;

public class FloatingPointLiteral extends NumericExpression implements SQLLiteral {
   private final BigDecimal value;

   public FloatingPointLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (value instanceof Float) {
         this.value = new BigDecimal(((Float)value).toString());
      } else if (value instanceof Double) {
         this.value = new BigDecimal(((Double)value).toString());
      } else {
         if (!(value instanceof BigDecimal)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (BigDecimal)value;
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof FloatingPointLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((FloatingPointLiteral)expr).value) == 0);
         } else if (expr instanceof CharacterExpression) {
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)this.value.intValue()), (String)null);
            return new BooleanExpression(expr, Expression.OP_EQ, literal);
         } else {
            return super.eq(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof FloatingPointLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((FloatingPointLiteral)expr).value) != 0);
         } else if (expr instanceof CharacterExpression) {
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)this.value.intValue()), (String)null);
            return new BooleanExpression(expr, Expression.OP_NOTEQ, literal);
         } else {
            return super.ne(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof FloatingPointLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((FloatingPointLiteral)expr).value) < 0);
         } else if (expr instanceof CharacterExpression) {
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)this.value.intValue()), (String)null);
            return new BooleanExpression(literal, Expression.OP_LT, expr);
         } else {
            return super.lt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof FloatingPointLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((FloatingPointLiteral)expr).value) <= 0);
         } else if (expr instanceof CharacterExpression) {
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)this.value.intValue()), (String)null);
            return new BooleanExpression(literal, Expression.OP_LTEQ, expr);
         } else {
            return super.le(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof FloatingPointLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((FloatingPointLiteral)expr).value) > 0);
         } else if (expr instanceof CharacterExpression) {
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)this.value.intValue()), (String)null);
            return new BooleanExpression(literal, Expression.OP_GT, expr);
         } else {
            return super.gt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof FloatingPointLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((FloatingPointLiteral)expr).value) >= 0);
         } else if (expr instanceof CharacterExpression) {
            CharacterLiteral literal = new CharacterLiteral(this.stmt, this.mapping, String.valueOf((char)this.value.intValue()), (String)null);
            return new BooleanExpression(literal, Expression.OP_GTEQ, expr);
         } else {
            return super.ge(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public SQLExpression add(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (SQLExpression)(expr instanceof FloatingPointLiteral ? new FloatingPointLiteral(this.stmt, this.mapping, this.value.add(((FloatingPointLiteral)expr).value), (String)null) : super.add(expr));
      } else {
         return new NumericExpression(this, Expression.OP_ADD, expr);
      }
   }

   public SQLExpression sub(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (SQLExpression)(expr instanceof FloatingPointLiteral ? new FloatingPointLiteral(this.stmt, this.mapping, this.value.subtract(((FloatingPointLiteral)expr).value), (String)null) : super.sub(expr));
      } else {
         return new NumericExpression(this, Expression.OP_SUB, expr);
      }
   }

   public SQLExpression mul(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (SQLExpression)(expr instanceof FloatingPointLiteral ? new FloatingPointLiteral(this.stmt, this.mapping, this.value.multiply(((FloatingPointLiteral)expr).value), (String)null) : super.mul(expr));
      } else {
         return new NumericExpression(this, Expression.OP_MUL, expr);
      }
   }

   public SQLExpression div(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (SQLExpression)(expr instanceof FloatingPointLiteral ? new FloatingPointLiteral(this.stmt, this.mapping, this.value.divide(((FloatingPointLiteral)expr).value, 1), (String)null) : super.div(expr));
      } else {
         return new NumericExpression(this, Expression.OP_DIV, expr);
      }
   }

   public SQLExpression neg() {
      return new FloatingPointLiteral(this.stmt, this.mapping, this.value.negate(), (String)null);
   }

   public Object getValue() {
      return this.value;
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
         this.setStatement();
      }
   }

   protected void setStatement() {
      this.st.append(StringUtils.exponentialFormatBigDecimal(this.value));
   }
}
