package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.table.Column;

public class ColumnExpression extends SQLExpression {
   Column column;
   Object value;
   boolean omitTableFromString = false;

   protected ColumnExpression(SQLStatement stmt, String parameterName, JavaTypeMapping mapping, Object value, int colNumber) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)mapping);
      this.st.appendParameter(parameterName, mapping, value, colNumber);
   }

   protected ColumnExpression(SQLStatement stmt, SQLTable table, Column col) {
      super((SQLStatement)stmt, (SQLTable)table, (JavaTypeMapping)null);
      this.column = col;
      this.st.append(this.toString());
   }

   protected ColumnExpression(SQLStatement stmt, Object value) {
      super((SQLStatement)stmt, (SQLTable)null, (JavaTypeMapping)null);
      this.value = value;
      this.st.append(this.toString());
   }

   public BooleanExpression eq(SQLExpression expr) {
      return new BooleanExpression(this, Expression.OP_EQ, expr);
   }

   public BooleanExpression noteq(SQLExpression expr) {
      return new BooleanExpression(this, Expression.OP_NOTEQ, expr);
   }

   public void setOmitTableFromString(boolean omitTable) {
      this.omitTableFromString = omitTable;
      this.st.clearStatement();
      this.st.append(this.toString());
   }

   public String toString() {
      if (this.value != null) {
         return !(this.value instanceof String) && !(this.value instanceof Character) ? "" + this.value : "'" + this.value + "'";
      } else if (this.table == null) {
         return "?";
      } else if (this.omitTableFromString) {
         return this.column.getIdentifier().toString();
      } else {
         return this.table.getAlias() != null ? this.table.getAlias() + "." + this.column.getIdentifier().toString() : this.table.getTable() + "." + this.column.getIdentifier().toString();
      }
   }
}
