package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class BooleanExpression extends SQLExpression {
   boolean hasClosure = false;

   public BooleanExpression(SQLStatement stmt, JavaTypeMapping mapping, String sql) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.st.clearStatement();
      this.st.append(sql);
   }

   public BooleanExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public BooleanExpression(SQLStatement stmt, JavaTypeMapping mapping) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.hasClosure = true;
   }

   public BooleanExpression(Expression.MonadicOperator op, SQLExpression expr1) {
      super(op, expr1);
      this.hasClosure = true;
   }

   public BooleanExpression(SQLExpression expr1, Expression.DyadicOperator op, SQLExpression expr2) {
      super(expr1, op, expr2);
      if (op != Expression.OP_EQ && op != Expression.OP_GT && op != Expression.OP_GTEQ && op != Expression.OP_NOTEQ && op != Expression.OP_LT && op != Expression.OP_LTEQ) {
         if ((op == Expression.OP_IS || op == Expression.OP_ISNOT) && (expr1 instanceof NullLiteral || expr2 instanceof NullLiteral)) {
            this.mapping = this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false);
         }
      } else {
         this.mapping = this.stmt.getSQLExpressionFactory().getMappingForType(Boolean.TYPE, false);
      }

      this.hasClosure = true;
   }

   public boolean hasClosure() {
      return this.hasClosure;
   }

   public BooleanExpression and(SQLExpression expr) {
      if (expr instanceof BooleanLiteral) {
         return expr.and(this);
      } else if (expr instanceof BooleanExpression) {
         BooleanExpression left = this;
         BooleanExpression right = (BooleanExpression)expr;
         if (!this.hasClosure()) {
            left = this.eq(new BooleanLiteral(this.stmt, this.mapping, Boolean.TRUE));
         }

         if (!right.hasClosure()) {
            right = right.eq(new BooleanLiteral(this.stmt, this.mapping, Boolean.TRUE));
         }

         if (this.stmt.getQueryGenerator() != null && this.stmt.getQueryGenerator().getCompilationComponent() == CompilationComponent.UPDATE) {
            BooleanExpression boolExpr = new BooleanExpression(this.stmt, (SQLTable)null, this.mapping);
            boolExpr.st.append((SQLExpression)left);
            boolExpr.st.append(',');
            boolExpr.st.append((SQLExpression)right);
            return boolExpr;
         } else {
            return new BooleanExpression(left, Expression.OP_AND, right);
         }
      } else {
         return super.and(expr);
      }
   }

   public BooleanExpression eor(SQLExpression expr) {
      if (expr instanceof BooleanLiteral) {
         return expr.eor(this);
      } else if (expr instanceof BooleanExpression) {
         return this.stmt.getDatastoreAdapter().supportsOption("BooleanExpression") ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : this.and(expr.not()).ior(this.not().and(expr));
      } else {
         return super.eor(expr);
      }
   }

   public BooleanExpression ior(SQLExpression expr) {
      if (expr instanceof BooleanLiteral) {
         return expr.ior(this);
      } else if (expr instanceof BooleanExpression) {
         BooleanExpression left = this;
         BooleanExpression right = (BooleanExpression)expr;
         if (!this.hasClosure()) {
            left = this.eq(new BooleanLiteral(this.stmt, this.mapping, Boolean.TRUE));
         }

         if (!right.hasClosure()) {
            right = right.eq(new BooleanLiteral(this.stmt, this.mapping, Boolean.TRUE));
         }

         return new BooleanExpression(left, Expression.OP_OR, right);
      } else {
         return super.ior(expr);
      }
   }

   public BooleanExpression not() {
      return !this.hasClosure ? new BooleanExpression(this, Expression.OP_EQ, new BooleanLiteral(this.stmt, this.mapping, Boolean.FALSE, (String)null)) : new BooleanExpression(Expression.OP_NOT, this);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (!(expr instanceof BooleanLiteral) && !(expr instanceof NullLiteral)) {
            if (expr instanceof BooleanExpression) {
               DatastoreMapping datastoreMapping = this.mapping.getDatastoreMapping(0);
               if (datastoreMapping.isStringBased()) {
                  return new BooleanExpression(new CharacterExpression(this.stmt, this.table, this.mapping), Expression.OP_EQ, new CharacterExpression(this.stmt, expr.table, expr.mapping));
               } else if (!datastoreMapping.isIntegerBased() && (!datastoreMapping.isBitBased() || this.stmt.getDatastoreAdapter().supportsOption("BitIsReallyBoolean"))) {
                  return this.stmt.getDatastoreAdapter().supportsOption("BooleanExpression") ? new BooleanExpression(this, Expression.OP_EQ, expr) : this.and(expr).ior(this.not().and(expr.not()));
               } else {
                  return new BooleanExpression(new NumericExpression(this.stmt, this.table, this.mapping), Expression.OP_EQ, new NumericExpression(this.stmt, expr.table, expr.mapping));
               }
            } else {
               return super.eq(expr);
            }
         } else {
            return expr.eq(this);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (!this.isParameter() && !expr.isParameter()) {
         if (!(expr instanceof BooleanLiteral) && !(expr instanceof NullLiteral)) {
            if (expr instanceof BooleanExpression) {
               DatastoreMapping datastoreMapping = this.mapping.getDatastoreMapping(0);
               if (datastoreMapping.isStringBased()) {
                  return new BooleanExpression(new CharacterExpression(this.stmt, this.table, this.mapping), Expression.OP_NOTEQ, new CharacterExpression(this.stmt, expr.table, expr.mapping));
               } else if (!datastoreMapping.isIntegerBased() && (!datastoreMapping.isBitBased() || this.stmt.getDatastoreAdapter().supportsOption("BitIsReallyBoolean"))) {
                  return this.stmt.getDatastoreAdapter().supportsOption("BooleanExpression") ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : this.and(expr.not()).ior(this.not().and(expr));
               } else {
                  return new BooleanExpression(new NumericExpression(this.stmt, this.table, this.mapping), Expression.OP_NOTEQ, new NumericExpression(this.stmt, expr.table, expr.mapping));
               }
            } else {
               return super.ne(expr);
            }
         } else {
            return expr.ne(this);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      DatastoreMapping datastoreMapping = this.mapping.getDatastoreMapping(0);
      return datastoreMapping.isStringBased() ? new BooleanExpression(new CharacterExpression(this.stmt, this.table, this.mapping), not ? Expression.OP_NOTIN : Expression.OP_IN, expr) : new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, Boolean.class.getName(), methodName, this, args);
   }

   public BooleanExpression neg() {
      return new BooleanExpression(Expression.OP_NEG, this);
   }
}
