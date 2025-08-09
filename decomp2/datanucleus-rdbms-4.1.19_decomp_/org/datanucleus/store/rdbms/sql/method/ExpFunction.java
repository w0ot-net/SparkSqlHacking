package org.datanucleus.store.rdbms.sql.method;

public class ExpFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "EXP";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
