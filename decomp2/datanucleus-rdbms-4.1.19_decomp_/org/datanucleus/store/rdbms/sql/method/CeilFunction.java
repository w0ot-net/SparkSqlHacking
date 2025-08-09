package org.datanucleus.store.rdbms.sql.method;

public class CeilFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "CEIL";
   }

   protected Class getClassForMapping() {
      return Integer.TYPE;
   }
}
