package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.TypeConverterMultiMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;

public class TypeConverterMultiLiteral extends TypeConverterMultiExpression implements SQLLiteral {
   Object value;

   public TypeConverterMultiLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else if (mapping instanceof TypeConverterMultiMapping && mapping.getJavaType().isAssignableFrom(value.getClass())) {
         this.value = value;
      }

   }

   public Object getValue() {
      return this.value;
   }

   public void setNotParameter() {
   }
}
