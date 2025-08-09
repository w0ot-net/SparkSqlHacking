package org.datanucleus.store.rdbms.sql;

import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.util.StringUtils;

public class SQLStatementParameter {
   final String name;
   JavaTypeMapping mapping;
   final int columnNumber;
   final Object value;

   public SQLStatementParameter(String name, JavaTypeMapping mapping, Object value, int columnNumber) {
      this.mapping = mapping;
      this.value = value;
      this.name = name;
      this.columnNumber = columnNumber;
   }

   public String getName() {
      return this.name;
   }

   public JavaTypeMapping getMapping() {
      return this.mapping;
   }

   public void setMapping(JavaTypeMapping mapping) {
      this.mapping = mapping;
   }

   public int getColumnNumber() {
      return this.columnNumber;
   }

   public Object getValue() {
      return this.value;
   }

   public String toString() {
      return "SQLStatementParameter name=" + this.name + " mapping=" + this.mapping + " value=" + StringUtils.toJVMIDString(this.value) + (this.columnNumber >= 0 ? " column=" + this.columnNumber : "");
   }
}
