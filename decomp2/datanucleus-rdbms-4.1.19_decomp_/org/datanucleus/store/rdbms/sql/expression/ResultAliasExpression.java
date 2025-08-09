package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class ResultAliasExpression extends SQLExpression {
   protected String aliasName;

   public ResultAliasExpression(SQLStatement stmt, String aliasName) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)null);
      this.aliasName = aliasName;
   }

   public String getResultAlias() {
      return this.aliasName;
   }
}
