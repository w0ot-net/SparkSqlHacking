package org.datanucleus.store.rdbms.sql.method;

public class DegreesFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "DEGREES";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
