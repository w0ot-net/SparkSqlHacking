package org.datanucleus.store.rdbms.sql.method;

public class CosFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "COS";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
