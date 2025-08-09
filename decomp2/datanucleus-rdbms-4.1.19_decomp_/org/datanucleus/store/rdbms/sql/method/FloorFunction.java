package org.datanucleus.store.rdbms.sql.method;

public class FloorFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "FLOOR";
   }

   protected Class getClassForMapping() {
      return Integer.TYPE;
   }
}
