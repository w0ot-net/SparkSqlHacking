package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class ObjectLiteral extends ObjectExpression implements SQLLiteral {
   private Object value;

   public ObjectLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      this.value = value;
      this.parameterName = parameterName;
      if (parameterName != null) {
         if (value != null) {
            this.subExprs = new SQLExpression.ColumnExpressionList();
            this.addSubexpressionsForValue(this.value, mapping);
         }

         if (mapping.getNumberOfDatastoreMappings() == 1) {
            this.st.appendParameter(parameterName, mapping, this.value);
         }
      } else {
         this.subExprs = new SQLExpression.ColumnExpressionList();
         if (value != null) {
            this.addSubexpressionsForValue(this.value, mapping);
         }

         this.st.append(this.subExprs.toString());
      }

   }

   private void addSubexpressionsForValue(Object value, JavaTypeMapping mapping) {
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      ClassLoaderResolver clr = this.stmt.getClassLoaderResolver();
      String objClassName = value.getClass().getName();
      if (mapping instanceof PersistableMapping) {
         objClassName = mapping.getType();
      } else if (IdentityUtils.isDatastoreIdentity(value)) {
         objClassName = IdentityUtils.getTargetClassNameForIdentitySimple(value);
      }

      AbstractClassMetaData cmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(objClassName, clr);
      if (cmd != null) {
         int numCols = mapping.getNumberOfDatastoreMappings();

         for(int i = 0; i < numCols; ++i) {
            ColumnExpression colExpr = null;
            if (this.parameterName == null && mapping instanceof PersistableMapping) {
               Object colValue = ((PersistableMapping)mapping).getValueForDatastoreMapping(this.stmt.getRDBMSManager().getNucleusContext(), i, value);
               colExpr = new ColumnExpression(this.stmt, colValue);
            } else {
               colExpr = new ColumnExpression(this.stmt, this.parameterName, mapping, value, i);
            }

            this.subExprs.addExpression(colExpr);
         }
      } else {
         NucleusLogger.GENERAL.error(">> ObjectLiteral doesn't yet cater for values of type " + StringUtils.toJVMIDString(value));
      }

   }

   public Object getValue() {
      return this.value;
   }

   public BooleanExpression eq(SQLExpression expr) {
      this.addSubexpressionsToRelatedExpression(expr);
      if (!this.isParameter() && !expr.isParameter()) {
         if (this.value == null) {
            return (new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null)).eq(expr);
         } else {
            return expr instanceof ObjectExpression ? ExpressionUtils.getEqualityExpressionForObjectExpressions(this, (ObjectExpression)expr, true) : super.eq(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_EQ, expr);
      }
   }

   public BooleanExpression ne(SQLExpression expr) {
      this.addSubexpressionsToRelatedExpression(expr);
      if (!this.isParameter() && !expr.isParameter()) {
         if (this.value == null) {
            return (new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null)).ne(expr);
         } else {
            return expr instanceof ObjectExpression ? ExpressionUtils.getEqualityExpressionForObjectExpressions(this, (ObjectExpression)expr, false) : super.ne(expr);
         }
      } else {
         return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
      }
   }

   public String toString() {
      return this.value != null ? super.toString() + " = " + this.value.toString() : super.toString() + " = NULL";
   }

   public void setNotParameter() {
      if (this.parameterName != null) {
         this.parameterName = null;
         this.st.clearStatement();
         this.setStatement();
      }
   }

   protected void setStatement() {
      if (this.parameterName == null) {
         this.subExprs = new SQLExpression.ColumnExpressionList();
         if (this.value != null) {
            this.addSubexpressionsForValue(this.value, this.mapping);
         }

         this.st.append(this.subExprs.toString());
      } else {
         this.st.appendParameter(this.parameterName, this.mapping, this.value);
      }

   }
}
