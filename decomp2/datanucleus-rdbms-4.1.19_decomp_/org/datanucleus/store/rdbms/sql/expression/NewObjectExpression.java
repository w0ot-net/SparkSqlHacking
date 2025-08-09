package org.datanucleus.store.rdbms.sql.expression;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class NewObjectExpression extends SQLExpression {
   Class newClass = null;
   List ctrArgExprs = null;

   public NewObjectExpression(SQLStatement stmt, Class cls, List args) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)null);
      this.newClass = cls;
      if (args != null) {
         this.ctrArgExprs = new ArrayList();
         this.ctrArgExprs.addAll(args);
      }

   }

   public Class getNewClass() {
      return this.newClass;
   }

   public List getConstructorArgExpressions() {
      return this.ctrArgExprs;
   }
}
