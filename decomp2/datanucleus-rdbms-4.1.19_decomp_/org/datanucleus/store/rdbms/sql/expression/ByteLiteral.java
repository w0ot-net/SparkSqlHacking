package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigInteger;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class ByteLiteral extends NumericExpression implements SQLLiteral {
   private final BigInteger value;

   public ByteLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (value instanceof BigInteger) {
         this.value = (BigInteger)value;
      } else {
         if (!(value instanceof Byte)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = BigInteger.valueOf(((Byte)value).longValue());
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((ByteLiteral)expr).value) == 0);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_EQ, expr) : super.eq(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((ByteLiteral)expr).value) != 0);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : super.ne(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((ByteLiteral)expr).value) < 0);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_LT, expr) : super.lt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((ByteLiteral)expr).value) <= 0);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_LTEQ, expr) : super.le(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((ByteLiteral)expr).value) > 0);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_GT, expr) : super.gt(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((ByteLiteral)expr).value) >= 0);
         } else {
            return expr instanceof CharacterExpression ? new BooleanExpression(this, Expression.OP_GTEQ, expr) : super.ge(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public SQLExpression add(SQLExpression expr) {
      return (SQLExpression)(expr instanceof ByteLiteral ? new ByteLiteral(this.stmt, this.mapping, this.value.add(((ByteLiteral)expr).value), (String)null) : super.add(expr));
   }

   public SQLExpression sub(SQLExpression expr) {
      return (SQLExpression)(expr instanceof ByteLiteral ? new ByteLiteral(this.stmt, this.mapping, this.value.subtract(((ByteLiteral)expr).value), (String)null) : super.sub(expr));
   }

   public SQLExpression mul(SQLExpression expr) {
      return (SQLExpression)(expr instanceof ByteLiteral ? new ByteLiteral(this.stmt, this.mapping, this.value.multiply(((ByteLiteral)expr).value), (String)null) : super.mul(expr));
   }

   public SQLExpression div(SQLExpression expr) {
      return (SQLExpression)(expr instanceof ByteLiteral ? new ByteLiteral(this.stmt, this.mapping, this.value.divide(((ByteLiteral)expr).value), (String)null) : super.div(expr));
   }

   public SQLExpression mod(SQLExpression expr) {
      return (SQLExpression)(expr instanceof ByteLiteral ? new ByteLiteral(this.stmt, this.mapping, this.value.mod(((ByteLiteral)expr).value), (String)null) : super.mod(expr));
   }

   public SQLExpression neg() {
      return new ByteLiteral(this.stmt, this.mapping, this.value.negate(), (String)null);
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
      this.st.append(String.valueOf(this.value));
   }
}
