package org.datanucleus.store.rdbms.sql.expression;

import java.util.List;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class StringSubqueryExpression extends StringExpression implements SubqueryExpressionComponent {
   SQLStatement subStatement;

   public StringSubqueryExpression(SQLStatement stmt, SQLStatement subStmt) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)null);
      this.subStatement = subStmt;
      this.st.append("(");
      this.st.append(subStmt);
      this.st.append(")");
   }

   public SQLStatement getSubqueryStatement() {
      return this.subStatement;
   }

   public void setKeyword(String keyword) {
      this.st.clearStatement();
      this.st.append(keyword).append(" (").append(this.subStatement).append(")");
   }

   public BooleanExpression eq(SQLExpression expr) {
      BooleanExpression eqExpr = super.eq(expr);
      eqExpr.encloseInParentheses();
      return eqExpr;
   }

   public BooleanExpression ne(SQLExpression expr) {
      BooleanExpression eqExpr = super.ne(expr);
      eqExpr.encloseInParentheses();
      return eqExpr;
   }

   public BooleanExpression lt(SQLExpression expr) {
      BooleanExpression eqExpr = super.lt(expr);
      eqExpr.encloseInParentheses();
      return eqExpr;
   }

   public BooleanExpression le(SQLExpression expr) {
      BooleanExpression eqExpr = super.le(expr);
      eqExpr.encloseInParentheses();
      return eqExpr;
   }

   public BooleanExpression gt(SQLExpression expr) {
      BooleanExpression eqExpr = super.gt(expr);
      eqExpr.encloseInParentheses();
      return eqExpr;
   }

   public BooleanExpression ge(SQLExpression expr) {
      BooleanExpression eqExpr = super.ge(expr);
      eqExpr.encloseInParentheses();
      return eqExpr;
   }

   public SQLExpression invoke(String methodName, List args) {
      if (methodName.equals("contains")) {
         SQLExpression sqlExpr = (SQLExpression)args.get(0);
         return new BooleanExpression(sqlExpr, Expression.OP_IN, this);
      } else {
         return super.invoke(methodName, args);
      }
   }
}
