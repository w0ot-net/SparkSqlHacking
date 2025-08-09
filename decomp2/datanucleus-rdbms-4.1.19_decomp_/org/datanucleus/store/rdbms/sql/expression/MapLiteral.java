package org.datanucleus.store.rdbms.sql.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class MapLiteral extends MapExpression implements SQLLiteral {
   private final Map value;
   private final MapValueLiteral mapValueLiteral;
   private final MapKeyLiteral mapKeyLiteral;

   public MapLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
         this.mapKeyLiteral = null;
         this.mapValueLiteral = null;
      } else {
         if (!(value instanceof Map)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         Map mapValue = (Map)value;
         this.value = mapValue;
         if (parameterName != null) {
            this.mapKeyLiteral = null;
            this.mapValueLiteral = null;
            this.st.appendParameter(parameterName, mapping, this.value);
         } else {
            this.mapValueLiteral = new MapValueLiteral(stmt, mapping, value);
            this.mapKeyLiteral = new MapKeyLiteral(stmt, mapping, value);
         }
      }

   }

   public Object getValue() {
      return this.value;
   }

   public MapKeyLiteral getKeyLiteral() {
      return this.mapKeyLiteral;
   }

   public MapValueLiteral getValueLiteral() {
      return this.mapValueLiteral;
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
      }
   }

   public static class MapKeyLiteral extends SQLExpression implements SQLLiteral {
      private final Map value;
      private List keyExpressions;

      public MapKeyLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value) {
         super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
         if (value instanceof Map) {
            Map mapValue = (Map)value;
            this.value = mapValue;
            this.setStatement();
         } else {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + (value != null ? value.getClass().getName() : null));
         }
      }

      public List getKeyExpressions() {
         return this.keyExpressions;
      }

      public SQLExpression invoke(String methodName, List args) {
         if (methodName.equals("get") && args.size() == 1) {
            SQLExpression argExpr = (SQLExpression)args.get(0);
            if (argExpr instanceof SQLLiteral) {
               Object val = this.value.get(((SQLLiteral)argExpr).getValue());
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
         boolean isEmpty = this.value == null || this.value.size() == 0;
         if (!isEmpty) {
            RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
            this.st.append("(");
            this.keyExpressions = new ArrayList();
            boolean hadPrev = false;

            for(Object current : this.value.keySet()) {
               if (null != current) {
                  JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(current.getClass(), false);
                  SQLExpression expr = storeMgr.getSQLExpressionFactory().newLiteral(this.stmt, m, current);
                  this.st.append(hadPrev ? "," : "");
                  this.st.append(expr);
                  this.keyExpressions.add(expr);
                  hadPrev = true;
               }
            }

            this.st.append(")");
         }

      }
   }

   public static class MapValueLiteral extends SQLExpression implements SQLLiteral {
      private final Map value;
      private List valueExpressions;

      public MapValueLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value) {
         super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
         if (value instanceof Map) {
            Map mapValue = (Map)value;
            this.value = mapValue;
            this.setStatement();
         } else {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + (value != null ? value.getClass().getName() : null));
         }
      }

      public List getValueExpressions() {
         return this.valueExpressions;
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
         boolean isEmpty = this.value == null || this.value.size() == 0;
         if (!isEmpty) {
            RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
            this.valueExpressions = new ArrayList();
            this.st.append("(");
            boolean hadPrev = false;

            for(Object current : this.value.values()) {
               if (null != current) {
                  JavaTypeMapping m = storeMgr.getSQLExpressionFactory().getMappingForType(current.getClass(), false);
                  SQLExpression expr = storeMgr.getSQLExpressionFactory().newLiteral(this.stmt, m, current);
                  this.st.append(hadPrev ? "," : "");
                  this.st.append(expr);
                  this.valueExpressions.add(expr);
                  hadPrev = true;
               }
            }

            this.st.append(")");
         }

      }
   }
}
