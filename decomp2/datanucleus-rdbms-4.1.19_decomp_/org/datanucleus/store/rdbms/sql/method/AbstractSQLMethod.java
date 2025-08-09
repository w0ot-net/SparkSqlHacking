package org.datanucleus.store.rdbms.sql.method;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;

public abstract class AbstractSQLMethod implements SQLMethod {
   protected SQLStatement stmt;
   protected SQLExpressionFactory exprFactory;
   protected ClassLoaderResolver clr;

   public void setStatement(SQLStatement stmt) {
      this.stmt = stmt;
      this.exprFactory = stmt.getSQLExpressionFactory();
      if (stmt.getQueryGenerator() == null) {
         throw new NucleusException("Attempt to use SQLMethod with an SQLStatement which doesn't have a QueryGenerator assigned");
      } else {
         this.clr = stmt.getQueryGenerator().getClassLoaderResolver();
      }
   }

   protected JavaTypeMapping getMappingForClass(Class cls) {
      return this.exprFactory.getMappingForType(cls, true);
   }
}
