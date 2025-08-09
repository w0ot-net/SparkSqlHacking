package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.types.converters.TypeConverter;

public class StringLiteral extends StringExpression implements SQLLiteral {
   private final String value;

   public StringLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (value instanceof String) {
         this.value = (String)value;
      } else if (value instanceof Character) {
         this.value = ((Character)value).toString();
      } else {
         Class type = value.getClass();
         if (mapping != null) {
            type = mapping.getJavaType();
         }

         TypeConverter converter = stmt.getRDBMSManager().getNucleusContext().getTypeManager().getTypeConverterForType(type, String.class);
         if (converter == null) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (String)converter.toDatastoreType(value);
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public void generateStatementWithoutQuotes() {
      this.st.clearStatement();
      this.st.append(this.value.replace("'", "''"));
   }

   public Object getValue() {
      return this.value;
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.eq(this);
      } else if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof StringLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.equals(((StringLiteral)expr).value)) : super.eq(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.ne(this);
      } else if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof StringLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), !this.value.equals(((StringLiteral)expr).value)) : super.ne(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof StringLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((StringLiteral)expr).value) < 0) : super.lt(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof StringLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((StringLiteral)expr).value) <= 0) : super.le(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof StringLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((StringLiteral)expr).value) > 0) : super.gt(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         return (BooleanExpression)(expr instanceof StringLiteral ? new BooleanLiteral(this.stmt, this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false), this.value.compareTo(((StringLiteral)expr).value) >= 0) : super.ge(expr));
      } else {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      }
   }

   public SQLExpression add(SQLExpression expr) {
      if (!expr.isParameter() && !this.isParameter()) {
         if (expr instanceof StringLiteral) {
            return new StringLiteral(this.stmt, this.mapping, this.value.concat(((StringLiteral)expr).value), (String)null);
         } else if (expr instanceof CharacterLiteral) {
            return new StringLiteral(this.stmt, this.mapping, this.value.concat(((SQLLiteral)expr).getValue().toString()), (String)null);
         } else {
            return (SQLExpression)(!(expr instanceof IntegerLiteral) && !(expr instanceof FloatingPointLiteral) && !(expr instanceof BooleanLiteral) ? super.add(expr) : new StringLiteral(this.stmt, this.mapping, this.value.concat(((SQLLiteral)expr).getValue().toString()), (String)null));
         }
      } else {
         return super.add(expr);
      }
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
         this.setStatement();
      }
   }

   protected void setStatement() {
      if (this.value == null) {
         this.st.append('\'').append('\'');
      } else {
         this.st.append('\'').append(this.value.replace("'", "''")).append('\'');
      }

   }
}
