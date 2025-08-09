package org.datanucleus.store.rdbms.sql.method;

public class RadiansFunction extends SimpleNumericMethod {
   protected String getFunctionName() {
      return "RADIANS";
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
