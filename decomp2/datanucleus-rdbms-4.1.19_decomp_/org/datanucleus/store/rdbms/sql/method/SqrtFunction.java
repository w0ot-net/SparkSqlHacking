package org.datanucleus.store.rdbms.sql.method;

public class SqrtFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "SQRT";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
