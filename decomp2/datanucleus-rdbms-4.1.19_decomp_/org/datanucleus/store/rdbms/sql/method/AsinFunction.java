package org.datanucleus.store.rdbms.sql.method;

public class AsinFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "ASIN";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
