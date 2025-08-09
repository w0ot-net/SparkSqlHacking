package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigInteger;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class IntegerLiteral extends NumericExpression implements SQLLiteral {
   private final Number value;

   public IntegerLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else {
         if (!(value instanceof Number)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (Number)value;
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof IntegerLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), (new BigInteger(this.value.toString())).compareTo(new BigInteger(((IntegerLiteral)expr).value.toString())) == 0);
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
         if (expr instanceof IntegerLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), (new BigInteger(this.value.toString())).compareTo(new BigInteger(((IntegerLiteral)expr).value.toString())) != 0);
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
         if (expr instanceof IntegerLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), (new BigInteger(this.value.toString())).compareTo(new BigInteger(((IntegerLiteral)expr).value.toString())) < 0);
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
         if (expr instanceof IntegerLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), (new BigInteger(this.value.toString())).compareTo(new BigInteger(((IntegerLiteral)expr).value.toString())) <= 0);
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
         if (expr instanceof IntegerLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), (new BigInteger(this.value.toString())).compareTo(new BigInteger(((IntegerLiteral)expr).value.toString())) > 0);
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
         if (expr instanceof IntegerLiteral) {
            return new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), (new BigInteger(this.value.toString())).compareTo(new BigInteger(((IntegerLiteral)expr).value.toString())) >= 0);
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
      if (expr instanceof StringExpression) {
         if (this.isParameter()) {
            this.stmt.getQueryGenerator().useParameterExpressionAsLiteral(this);
         }

         StringExpression strExpr = (StringExpression)this.stmt.getSQLExpressionFactory().invokeOperation("numericToString", this, (SQLExpression)null);
         return new StringExpression(strExpr, Expression.OP_CONCAT, expr);
      } else if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof IntegerLiteral) {
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).add(new BigInteger(((IntegerLiteral)expr).value.toString())), (String)null);
         } else if (expr instanceof CharacterLiteral) {
            int v = ((CharacterLiteral)expr).getValue().toString().charAt(0);
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).add(new BigInteger("" + v)), (String)null);
         } else {
            return super.add(expr);
         }
      } else {
         return new NumericExpression(this, Expression.OP_ADD, expr);
      }
   }

   public SQLExpression sub(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof IntegerLiteral) {
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).subtract(new BigInteger(((IntegerLiteral)expr).value.toString())), (String)null);
         } else if (expr instanceof CharacterLiteral) {
            int v = ((CharacterLiteral)expr).getValue().toString().charAt(0);
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).subtract(new BigInteger("" + v)), (String)null);
         } else {
            return super.sub(expr);
         }
      } else {
         return new NumericExpression(this, Expression.OP_SUB, expr);
      }
   }

   public SQLExpression mul(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof IntegerLiteral) {
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).multiply(new BigInteger(((IntegerLiteral)expr).value.toString())), (String)null);
         } else if (expr instanceof CharacterLiteral) {
            int v = ((CharacterLiteral)expr).getValue().toString().charAt(0);
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).multiply(new BigInteger("" + v)), (String)null);
         } else {
            return super.mul(expr);
         }
      } else {
         return new NumericExpression(this, Expression.OP_MUL, expr);
      }
   }

   public SQLExpression div(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof IntegerLiteral) {
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).divide(new BigInteger(((IntegerLiteral)expr).value.toString())), (String)null);
         } else if (expr instanceof CharacterLiteral) {
            int v = ((CharacterLiteral)expr).getValue().toString().charAt(0);
            return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).divide(new BigInteger("" + v)), (String)null);
         } else {
            return super.div(expr);
         }
      } else {
         return new NumericExpression(this, Expression.OP_DIV, expr);
      }
   }

   public SQLExpression mod(SQLExpression expr) {
      if (expr instanceof IntegerLiteral) {
         return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).mod(new BigInteger(((IntegerLiteral)expr).value.toString())), (String)null);
      } else if (expr instanceof CharacterLiteral) {
         int v = ((CharacterLiteral)expr).getValue().toString().charAt(0);
         return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).mod(new BigInteger("" + v)), (String)null);
      } else {
         return super.mod(expr);
      }
   }

   public SQLExpression neg() {
      return new IntegerLiteral(this.stmt, this.mapping, (new BigInteger(this.value.toString())).negate(), this.parameterName);
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
