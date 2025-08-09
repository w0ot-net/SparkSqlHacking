package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.ClassNameConstants;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class StringTemporalExpression extends DelegatedExpression {
   public StringTemporalExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
      if (mapping.getJavaTypeForDatastoreMapping(0).equals(ClassNameConstants.JAVA_LANG_STRING)) {
         this.delegate = new StringExpression(stmt, table, mapping);
      } else {
         this.delegate = new TemporalExpression(stmt, table, mapping);
      }

   }
}
