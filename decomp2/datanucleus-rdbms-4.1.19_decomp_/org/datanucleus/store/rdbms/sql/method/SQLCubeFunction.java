package org.datanucleus.store.rdbms.sql.method;

public class SQLCubeFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "CUBE";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
