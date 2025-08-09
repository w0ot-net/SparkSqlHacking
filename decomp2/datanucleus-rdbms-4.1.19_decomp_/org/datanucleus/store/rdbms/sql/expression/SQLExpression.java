package org.datanucleus.store.rdbms.sql.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.SQLText;

public abstract class SQLExpression {
   protected SQLStatement stmt;
   protected SQLTable table;
   protected JavaTypeMapping mapping;
   protected final SQLText st = new SQLText();
   protected Expression.Operator lowestOperator = null;
   protected ColumnExpressionList subExprs;
   protected String parameterName = null;

   protected SQLExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      this.stmt = stmt;
      this.table = table;
      this.mapping = mapping;
      if (table != null) {
         this.subExprs = new ColumnExpressionList();
         if (mapping != null) {
            for(int i = 0; i < mapping.getNumberOfDatastoreMappings(); ++i) {
               ColumnExpression colExpr = new ColumnExpression(stmt, table, mapping.getDatastoreMapping(i).getColumn());
               this.subExprs.addExpression(colExpr);
            }
         }

         this.st.append(this.subExprs);
      }

   }

   protected SQLExpression(Expression.MonadicOperator op, SQLExpression expr1) {
      this.st.append(op.toString());
      if (op.isHigherThan(expr1.lowestOperator)) {
         this.st.append('(').append(expr1).append(')');
      } else {
         this.st.append(expr1);
      }

      this.stmt = expr1.stmt;
      this.mapping = expr1.mapping;
      this.lowestOperator = op;
   }

   protected SQLExpression(SQLExpression expr1, Expression.DyadicOperator op, SQLExpression expr2) {
      this.stmt = expr1.stmt;
      this.mapping = expr1.mapping != null ? expr1.mapping : expr2.mapping;
      this.lowestOperator = op;
      if (op == Expression.OP_CONCAT) {
         try {
            SQLExpression concatExpr = this.stmt.getSQLExpressionFactory().invokeOperation("concat", expr1, expr2);
            this.st.append(concatExpr.encloseInParentheses());
            return;
         } catch (UnsupportedOperationException var6) {
         }
      } else if (op == Expression.OP_MOD) {
         try {
            SQLExpression modExpr = this.stmt.getSQLExpressionFactory().invokeOperation("mod", expr1, expr2);
            this.st.append(modExpr.encloseInParentheses());
            return;
         } catch (UnsupportedOperationException var5) {
         }
      }

      if (op.isHigherThanLeftSide(expr1.lowestOperator)) {
         this.st.append('(').append(expr1).append(')');
      } else {
         this.st.append(expr1);
      }

      this.st.append(op.toString());
      if (op.isHigherThanRightSide(expr2.lowestOperator)) {
         this.st.append('(').append(expr2).append(')');
      } else {
         this.st.append(expr2);
      }

      if (op == Expression.OP_LIKE && this.stmt.getRDBMSManager().getDatastoreAdapter().supportsOption("EscapeExpressionInLikePredicate") && expr2 instanceof SQLLiteral) {
         DatastoreAdapter dba = this.stmt.getRDBMSManager().getDatastoreAdapter();
         this.st.append(' ');
         this.st.append(dba.getEscapePatternExpression());
         this.st.append(' ');
      }

   }

   protected SQLExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      if (types != null && args != null && args.size() != types.size()) {
         throw new NucleusException("Number of arguments (" + args.size() + ") and their types (" + types.size() + ") are inconsistent");
      } else {
         this.stmt = stmt;
         if (stmt == null && args != null && args.size() > 0) {
            this.stmt = ((SQLExpression)args.get(0)).stmt;
         }

         this.mapping = mapping;
         this.st.append(functionName).append('(');
         if (args != null) {
            Iterator<SQLExpression> argIter = args.listIterator();
            Iterator typesIter = types != null ? types.listIterator() : null;

            while(argIter.hasNext()) {
               SQLExpression argExpr = (SQLExpression)argIter.next();
               this.st.append(argExpr);
               if (typesIter != null) {
                  Object argType = typesIter.next();
                  this.st.append(" AS ");
                  if (argType instanceof SQLExpression) {
                     this.st.append((SQLExpression)argType);
                  } else {
                     this.st.append(argType.toString());
                  }
               }

               if (argIter.hasNext()) {
                  this.st.append(",");
               }
            }
         }

         this.st.append(')');
      }
   }

   public Expression.Operator getLowestOperator() {
      return this.lowestOperator;
   }

   public int getNumberOfSubExpressions() {
      return this.subExprs != null ? this.subExprs.size() : 1;
   }

   public ColumnExpression getSubExpression(int index) {
      if (this.subExprs == null) {
         return null;
      } else {
         return index >= 0 && index < this.subExprs.size() ? this.subExprs.getExpression(index) : null;
      }
   }

   public SQLStatement getSQLStatement() {
      return this.stmt;
   }

   public boolean isParameter() {
      return this.parameterName != null;
   }

   public String getParameterName() {
      return this.parameterName;
   }

   public JavaTypeMapping getJavaTypeMapping() {
      return this.mapping;
   }

   public void setJavaTypeMapping(JavaTypeMapping mapping) {
      this.mapping = mapping;
      if (this.parameterName != null) {
         this.st.changeMappingForParameter(this.parameterName, mapping);
      }

   }

   public SQLTable getSQLTable() {
      return this.table;
   }

   public SQLText toSQLText() {
      return this.st;
   }

   public SQLExpression encloseInParentheses() {
      this.st.encloseInParentheses();
      return this;
   }

   public BooleanExpression and(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "&&", expr);
   }

   public BooleanExpression eor(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "^", expr);
   }

   public BooleanExpression ior(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "||", expr);
   }

   public BooleanExpression not() {
      throw new IllegalExpressionOperationException("!", this);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof DelegatedExpression) {
         return this.eq(((DelegatedExpression)expr).getDelegate());
      } else {
         throw new IllegalExpressionOperationException(this, "==", expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (expr instanceof DelegatedExpression) {
         return this.ne(((DelegatedExpression)expr).getDelegate());
      } else {
         throw new IllegalExpressionOperationException(this, "!=", expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (expr instanceof DelegatedExpression) {
         return this.lt(((DelegatedExpression)expr).getDelegate());
      } else {
         throw new IllegalExpressionOperationException(this, "<", expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (expr instanceof DelegatedExpression) {
         return this.le(((DelegatedExpression)expr).getDelegate());
      } else {
         throw new IllegalExpressionOperationException(this, "<=", expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (expr instanceof DelegatedExpression) {
         return this.gt(((DelegatedExpression)expr).getDelegate());
      } else {
         throw new IllegalExpressionOperationException(this, ">", expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (expr instanceof DelegatedExpression) {
         return this.ge(((DelegatedExpression)expr).getDelegate());
      } else {
         throw new IllegalExpressionOperationException(this, ">=", expr);
      }
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      throw new IllegalExpressionOperationException(this, "in", expr);
   }

   public SQLExpression add(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "+", expr);
   }

   public SQLExpression sub(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "-", expr);
   }

   public SQLExpression mul(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "*", expr);
   }

   public SQLExpression div(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "/", expr);
   }

   public SQLExpression mod(SQLExpression expr) {
      throw new IllegalExpressionOperationException(this, "%", expr);
   }

   public SQLExpression neg() {
      throw new IllegalExpressionOperationException("-", this);
   }

   public SQLExpression com() {
      throw new IllegalExpressionOperationException("~", this);
   }

   public SQLExpression distinct() {
      this.st.prepend("DISTINCT (");
      this.st.append(")");
      return this;
   }

   public SQLExpression bitAnd(SQLExpression expr) {
      throw new IllegalExpressionOperationException("Bitwise AND on " + expr, this);
   }

   public SQLExpression bitOr(SQLExpression expr) {
      throw new IllegalExpressionOperationException("Bitwise OR on " + expr, this);
   }

   public SQLExpression cast(SQLExpression expr) {
      throw new IllegalExpressionOperationException("cast to " + expr, this);
   }

   public BooleanExpression is(SQLExpression expr, boolean not) {
      throw new IllegalExpressionOperationException("instanceof " + expr, this);
   }

   public SQLExpression invoke(String methodName, List args) {
      throw new IllegalExpressionOperationException("." + methodName, this);
   }

   public static class ColumnExpressionList {
      private List exprs = new ArrayList();

      public void addExpression(ColumnExpression expression) {
         this.exprs.add(expression);
      }

      public ColumnExpression getExpression(int index) {
         return (ColumnExpression)this.exprs.get(index);
      }

      public int size() {
         return this.exprs.size();
      }

      public String toString() {
         StringBuilder expr = new StringBuilder();
         int size = this.exprs.size();

         for(int i = 0; i < size; ++i) {
            expr.append(this.getExpression(i).toString());
            if (i < size - 1) {
               expr.append(',');
            }
         }

         return expr.toString();
      }

      public ColumnExpression[] toArray() {
         return (ColumnExpression[])this.exprs.toArray(new ColumnExpression[this.exprs.size()]);
      }
   }
}
