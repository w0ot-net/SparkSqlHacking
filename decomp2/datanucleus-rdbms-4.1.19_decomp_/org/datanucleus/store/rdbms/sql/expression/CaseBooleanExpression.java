package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class CaseBooleanExpression extends BooleanExpression {
   public CaseBooleanExpression(SQLExpression[] whenExprs, SQLExpression[] actionExprs, SQLExpression elseExpr) {
      super(whenExprs[0].getSQLStatement(), (SQLTable)null, (JavaTypeMapping)null);
      this.hasClosure = true;
      this.st.clearStatement();
      this.st.append("CASE");
      if (actionExprs != null && whenExprs.length == actionExprs.length && whenExprs.length != 0) {
         this.mapping = actionExprs[0].getJavaTypeMapping();

         for(int i = 0; i < whenExprs.length; ++i) {
            this.st.append(" WHEN ").append(whenExprs[i]).append(" THEN ").append(actionExprs[i]);
         }

         if (elseExpr != null) {
            this.st.append(" ELSE ").append(elseExpr);
         }

         this.st.append(" END");
         this.st.encloseInParentheses();
      } else {
         throw new IllegalArgumentException("CaseExpression must have equal number of WHEN and THEN expressions");
      }
   }
}
