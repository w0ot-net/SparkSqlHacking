package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class CharacterLiteral extends CharacterExpression implements SQLLiteral {
   private final String value;

   public CharacterLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (value instanceof Character) {
         this.value = ((Character)value).toString();
      } else {
         if (!(value instanceof String)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (String)value;
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteExpression) {
            return expr.eq(this);
         } else {
            return (BooleanExpression)(expr instanceof CharacterLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.equals(((CharacterLiteral)expr).value)) : super.eq(expr));
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof ByteExpression) {
            return expr.ne(this);
         } else {
            return (BooleanExpression)(expr instanceof CharacterLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), !this.value.equals(((CharacterLiteral)expr).value)) : super.ne(expr));
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof CharacterLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((CharacterLiteral)expr).value) < 0) : super.lt(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof CharacterLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((CharacterLiteral)expr).value) <= 0) : super.le(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof CharacterLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((CharacterLiteral)expr).value) > 0) : super.gt(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof CharacterLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((CharacterLiteral)expr).value) >= 0) : super.ge(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public SQLExpression add(SQLExpression expr) {
      if (expr instanceof CharacterLiteral) {
         int v = this.value.charAt(0) + ((CharacterLiteral)expr).value.charAt(0);
         return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
      } else if (expr instanceof IntegerLiteral) {
         int v = this.value.charAt(0) + ((Number)((IntegerLiteral)expr).getValue()).intValue();
         return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
      } else {
         return super.add(expr);
      }
   }

   public SQLExpression sub(SQLExpression expr) {
      if (expr instanceof CharacterLiteral) {
         int v = this.value.charAt(0) - ((CharacterLiteral)expr).value.charAt(0);
         return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
      } else if (expr instanceof IntegerLiteral) {
         int v = this.value.charAt(0) - ((Number)((IntegerLiteral)expr).getValue()).intValue();
         return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
      } else {
         return super.sub(expr);
      }
   }

   public SQLExpression mod(SQLExpression expr) {
      if (expr instanceof CharacterLiteral) {
         int v = this.value.charAt(0) % ((CharacterLiteral)expr).value.charAt(0);
         return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
      } else if (expr instanceof IntegerLiteral) {
         int v = this.value.charAt(0) % ((Number)((IntegerLiteral)expr).getValue()).intValue();
         return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
      } else {
         return super.mod(expr);
      }
   }

   public SQLExpression neg() {
      int v = -this.value.charAt(0);
      return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
   }

   public SQLExpression com() {
      int v = ~this.value.charAt(0);
      return new IntegerLiteral(this.stmt, this.mapping, v, (String)null);
   }

   public SQLExpression invoke(String methodName, List args) {
      if (methodName.equals("toUpperCase")) {
         return new CharacterLiteral(this.stmt, this.mapping, this.value.toUpperCase(), this.parameterName);
      } else {
         return (SQLExpression)(methodName.equals("toLowerCase") ? new CharacterLiteral(this.stmt, this.mapping, this.value.toLowerCase(), this.parameterName) : super.invoke(methodName, args));
      }
   }

   public Object getValue() {
      return this.value == null ? null : this.value.charAt(0);
   }

   public void setJavaTypeMapping(JavaTypeMapping m) {
      super.setJavaTypeMapping(m);
      if (!this.isParameter()) {
         this.setStatement();
      }

   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.setStatement();
      }
   }

   protected void setStatement() {
      this.st.clearStatement();
      DatastoreMapping colMapping = this.mapping.getDatastoreMapping(0);
      if (colMapping.isIntegerBased()) {
         this.st.append("" + this.value.charAt(0));
      } else {
         this.st.append('\'').append(this.value).append('\'');
      }

   }
}
