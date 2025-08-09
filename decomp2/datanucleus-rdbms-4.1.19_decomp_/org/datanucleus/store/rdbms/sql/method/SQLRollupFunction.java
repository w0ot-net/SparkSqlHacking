package org.datanucleus.store.rdbms.sql.method;

public class SQLRollupFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "ROLLUP";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
