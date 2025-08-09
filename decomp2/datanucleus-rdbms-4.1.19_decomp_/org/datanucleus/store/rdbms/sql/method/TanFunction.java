package org.datanucleus.store.rdbms.sql.method;

public class TanFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "TAN";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
