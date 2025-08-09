package org.datanucleus.store.rdbms.sql.expression;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class ArrayLiteral extends ArrayExpression implements SQLLiteral {
   final Object value;

   public ArrayLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.parameterName = parameterName;
      this.value = value;
      if (value != null && !value.getClass().isArray()) {
         throw new NucleusUserException("Invalid argument literal : " + value);
      } else {
         if (parameterName != null) {
            this.st.appendParameter(parameterName, mapping, this.value);
         } else {
            this.setStatement();
         }

      }
   }

   public Object getValue() {
      return this.value;
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
         this.setStatement();
      }
   }

   protected void setStatement() {
      if (this.value != null && Array.getLength(this.value) > 0) {
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         this.elementExpressions = new ArrayList();
         this.st.append("(");
         boolean hadPrev = false;

         for(int i = 0; i < Array.getLength(this.value); ++i) {
            Object current = Array.get(this.value, i);
            if (current != null) {
               JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(current.getClass(), false);
               SQLExpression expr = storeMgr.getSQLExpressionFactory().newLiteral(this.stmt, m, current);
               this.st.append(hadPrev ? "," : "");
               this.st.append(expr);
               this.elementExpressions.add(expr);
               hadPrev = true;
            }
         }

         this.st.append(")");
      }

   }
}
