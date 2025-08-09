package org.datanucleus.store.rdbms.sql.expression;

import java.util.Date;
import java.util.List;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class TemporalExpression extends SQLExpression {
   public TemporalExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
   }

   public TemporalExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args) {
      super(stmt, mapping, functionName, args, (List)null);
   }

   public TemporalExpression(SQLStatement stmt, JavaTypeMapping mapping, String functionName, List args, List types) {
      super(stmt, mapping, functionName, args, types);
   }

   protected TemporalExpression(SQLExpression expr1, Expression.DyadicOperator op, SQLExpression expr2) {
      super(expr1, op, expr2);
   }

   public BooleanExpression eq(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.eq(this);
      } else if (expr instanceof ColumnExpression) {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      } else {
         return expr instanceof TemporalExpression ? new BooleanExpression(this, Expression.OP_EQ, expr) : super.eq(expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      if (expr instanceof NullLiteral) {
         return expr.ne(this);
      } else if (expr instanceof ColumnExpression) {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      } else {
         return expr instanceof TemporalExpression ? new BooleanExpression(this, Expression.OP_NOTEQ, expr) : super.ne(expr);
      }
   }

   public BooleanExpression lt(SQLExpression expr) {
      if (expr instanceof TemporalExpression) {
         return new BooleanExpression(this, Expression.OP_LT, expr);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_LT, expr) : super.lt(expr);
      }
   }

   public BooleanExpression le(SQLExpression expr) {
      if (expr instanceof TemporalExpression) {
         return new BooleanExpression(this, Expression.OP_LTEQ, expr);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_LTEQ, expr) : super.le(expr);
      }
   }

   public BooleanExpression gt(SQLExpression expr) {
      if (expr instanceof TemporalExpression) {
         return new BooleanExpression(this, Expression.OP_GT, expr);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_GT, expr) : super.gt(expr);
      }
   }

   public BooleanExpression ge(SQLExpression expr) {
      if (expr instanceof TemporalExpression) {
         return new BooleanExpression(this, Expression.OP_GTEQ, expr);
      } else {
         return expr instanceof ColumnExpression ? new BooleanExpression(this, Expression.OP_GTEQ, expr) : super.ge(expr);
      }
   }

   public BooleanExpression in(SQLExpression expr, boolean not) {
      return new BooleanExpression(this, not ? Expression.OP_NOTIN : Expression.OP_IN, expr);
   }

   public SQLExpression add(SQLExpression expr) {
      if (expr instanceof TemporalExpression) {
         return (new TemporalExpression(this, Expression.OP_ADD, expr)).encloseInParentheses();
      } else {
         if (expr instanceof DelegatedExpression) {
            SQLExpression delegate = ((DelegatedExpression)expr).getDelegate();
            if (delegate instanceof TemporalExpression) {
               return (new TemporalExpression(this, Expression.OP_ADD, delegate)).encloseInParentheses();
            }
         }

         return super.add(expr);
      }
   }

   public SQLExpression sub(SQLExpression expr) {
      if (expr instanceof TemporalExpression) {
         return (new TemporalExpression(this, Expression.OP_SUB, expr)).encloseInParentheses();
      } else {
         if (expr instanceof DelegatedExpression) {
            SQLExpression delegate = ((DelegatedExpression)expr).getDelegate();
            if (delegate instanceof TemporalExpression) {
               return (new TemporalExpression(this, Expression.OP_SUB, delegate)).encloseInParentheses();
            }
         }

         return super.sub(expr);
      }
   }

   public SQLExpression invoke(String methodName, List args) {
      return this.stmt.getRDBMSManager().getSQLExpressionFactory().invokeMethod(this.stmt, Date.class.getName(), methodName, this, args);
   }
}
