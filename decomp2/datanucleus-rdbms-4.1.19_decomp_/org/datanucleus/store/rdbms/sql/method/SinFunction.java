package org.datanucleus.store.rdbms.sql.method;

public class SinFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "SIN";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
