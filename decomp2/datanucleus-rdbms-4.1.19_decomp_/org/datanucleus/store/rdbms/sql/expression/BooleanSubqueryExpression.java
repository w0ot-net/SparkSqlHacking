package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;

public class BooleanSubqueryExpression extends BooleanExpression {
   public BooleanSubqueryExpression(SQLStatement stmt, String keyword, SQLStatement subStmt) {
      super((SQLStatement)stmt, (JavaTypeMapping)null);
      this.st.append(keyword).append(" (");
      this.st.append(subStmt);
      this.st.append(")");
   }
}
