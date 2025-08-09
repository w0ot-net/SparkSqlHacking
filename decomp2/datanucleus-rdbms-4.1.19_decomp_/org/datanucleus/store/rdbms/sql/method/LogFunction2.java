package org.datanucleus.store.rdbms.sql.method;

public class LogFunction2 extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "LN";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
