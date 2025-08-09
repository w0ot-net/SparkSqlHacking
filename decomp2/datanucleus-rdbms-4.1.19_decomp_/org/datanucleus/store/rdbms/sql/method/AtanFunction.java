package org.datanucleus.store.rdbms.sql.method;

public class AtanFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "ATAN";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
