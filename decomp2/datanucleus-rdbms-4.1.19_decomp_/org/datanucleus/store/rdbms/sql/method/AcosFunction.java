package org.datanucleus.store.rdbms.sql.method;

public class AcosFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "ACOS";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
