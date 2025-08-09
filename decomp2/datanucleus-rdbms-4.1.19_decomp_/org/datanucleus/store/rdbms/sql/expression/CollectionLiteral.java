package org.datanucleus.store.rdbms.sql.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class CollectionLiteral extends CollectionExpression implements SQLLiteral {
   private final Collection value;
   private List elementExpressions;

   public CollectionLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (parameterName != null) {
         if (value instanceof Collection) {
            this.value = (Collection)value;
         } else {
            this.value = null;
         }

         this.st.appendParameter(parameterName, mapping, this.value);
      } else {
         if (!(value instanceof Collection)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (Collection)value;
         this.setStatement();
      }

   }

   public Object getValue() {
      return this.value;
   }

   public List getElementExpressions() {
      return this.elementExpressions;
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
         this.setStatement();
      }
   }

   protected void setStatement() {
      if (this.value != null && this.value.size() > 0) {
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         this.elementExpressions = new ArrayList();
         this.st.append("(");
         boolean hadPrev = false;

         for(Object current : this.value) {
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

   public SQLExpression invoke(String methodName, List args) {
      if (methodName.equals("get") && args.size() == 1 && this.value instanceof List) {
         SQLExpression argExpr = (SQLExpression)args.get(0);
         if (argExpr instanceof SQLLiteral) {
            Object val = ((List)this.value).get((Integer)((SQLLiteral)argExpr).getValue());
            if (val == null) {
               return new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null);
            } else {
               JavaTypeMapping m = this.stmt.getRDBMSManager().getSQLExpressionFactory().getMappingForType(val.getClass(), false);
               return new ObjectLiteral(this.stmt, m, val, (String)null);
            }
         } else {
            throw new IllegalExpressionOperationException(this, "get", argExpr);
         }
      } else {
         return super.invoke(methodName, args);
      }
   }
}
