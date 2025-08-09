package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class BooleanLiteral extends BooleanExpression implements SQLLiteral {
   private final Boolean value;

   public BooleanLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else {
         if (!(value instanceof Boolean)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (Boolean)value;
      }

      if (parameterName != null) {
         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public BooleanLiteral(SQLStatement stmt, JavaTypeMapping mapping, Boolean value) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.value = value;
      this.hasClosure = true;
      this.setStatement();
   }

   public void setJavaTypeMapping(JavaTypeMapping mapping) {
      super.setJavaTypeMapping(mapping);
      this.st.clearStatement();
      if (this.parameterName != null) {
         this.st.appendParameter(this.parameterName, mapping, this.value);
      } else {
         this.setStatement();
      }

   }

   public Object getValue() {
      return this.value;
   }

   public BooleanExpression and(SQLExpression expr) {
      if (expr instanceof BooleanExpression) {
         return (BooleanExpression)(this.value ? (BooleanExpression)expr : this);
      } else {
         return super.and(expr);
      }
   }

   public BooleanExpression eor(SQLExpression expr) {
      if (expr instanceof BooleanExpression) {
         return this.value ? expr.not() : (BooleanExpression)expr;
      } else {
         return super.eor(expr);
      }
   }

   public BooleanExpression ior(SQLExpression expr) {
      if (expr instanceof BooleanExpression) {
         return (BooleanExpression)(this.value ? this : (BooleanExpression)expr);
      } else {
         return super.ior(expr);
      }
   }

   public BooleanExpression not() {
      return this.hasClosure ? new BooleanLiteral(this.stmt, this.mapping, !this.value) : new BooleanLiteral(this.stmt, this.mapping, !this.value, (String)null);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof BooleanLiteral) {
            BooleanLiteral exprLit = (BooleanLiteral)expr;
            return new BooleanLiteral(this.stmt, this.mapping, this.value == exprLit.value);
         } else if (expr instanceof BooleanExpression) {
            DatastoreMapping datastoreMapping = expr.mapping.getDatastoreMapping(0);
            if (datastoreMapping.isStringBased()) {
               return new BooleanExpression(expr, Expression.OP_EQ, new CharacterLiteral(this.stmt, this.mapping, this.value ? "Y" : "N", (String)null));
            } else if (datastoreMapping.isIntegerBased() || datastoreMapping.isBitBased() && !this.stmt.getDatastoreAdapter().supportsOption("BitIsReallyBoolean")) {
               return new BooleanExpression(expr, Expression.OP_EQ, new IntegerLiteral(this.stmt, this.mapping, this.value ? 1 : 0, (String)null));
            } else {
               return this.stmt.getDatastoreAdapter().supportsOption("BooleanExpression") ? new BooleanExpression(this, Expression.OP_EQ, expr) : this.and(expr).ior(this.not().and(expr.not()));
            }
         } else {
            return super.eq(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (expr instanceof BooleanLiteral) {
            BooleanLiteral exprLit = (BooleanLiteral)expr;
            return new BooleanLiteral(this.stmt, this.mapping, this.value != exprLit.value);
         } else if (expr instanceof BooleanExpression) {
            DatastoreMapping datastoreMapping = expr.mapping.getDatastoreMapping(0);
            if (datastoreMapping.isStringBased()) {
               return new BooleanExpression(expr, Expression.OP_NOTEQ, new CharacterLiteral(this.stmt, this.mapping, this.value ? "Y" : "N", (String)null));
            } else if (datastoreMapping.isIntegerBased() || datastoreMapping.isBitBased() && !this.stmt.getDatastoreAdapter().supportsOption("BitIsReallyBoolean")) {
               return new BooleanExpression(expr, Expression.OP_NOTEQ, new IntegerLiteral(this.stmt, this.mapping, this.value ? 1 : 0, (String)null));
            } else {
               return this.stmt.getDatastoreAdapter().supportsOption("BooleanExpression") ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : this.and(expr.not()).ior(this.not().and(expr));
            }
         } else {
            return super.ne(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
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
      if (this.hasClosure) {
         this.st.append(this.value ? "TRUE" : "(1=0)");
      } else {
         DatastoreMapping datastoreMapping = this.mapping.getDatastoreMapping(0);
         if (datastoreMapping.isStringBased()) {
            this.st.append(this.value ? "'Y'" : "'N'");
         } else if (datastoreMapping.isIntegerBased() || datastoreMapping.isBitBased() && !this.stmt.getDatastoreAdapter().supportsOption("BitIsReallyBoolean")) {
            this.st.append(this.value ? "1" : "0");
         } else {
            this.st.append(this.value ? "TRUE" : "(1=0)");
         }
      }

   }
}
