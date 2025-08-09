package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class SubqueryExpression extends SQLExpression {
   SQLStatement subStatement;

   public SubqueryExpression(SQLStatement stmt, SQLStatement subStmt) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)null);
      this.subStatement = subStmt;
      this.st.append("(");
      this.st.append(subStmt);
      this.st.append(")");
   }

   public SQLStatement getSubqueryStatement() {
      return this.subStatement;
   }
}
