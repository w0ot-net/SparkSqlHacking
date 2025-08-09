package org.datanucleus.store.rdbms.sql.method;

public class AbsFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "ABS";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
