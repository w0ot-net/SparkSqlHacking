package org.datanucleus.store.rdbms.sql.method;

public class LogFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "LOG";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
