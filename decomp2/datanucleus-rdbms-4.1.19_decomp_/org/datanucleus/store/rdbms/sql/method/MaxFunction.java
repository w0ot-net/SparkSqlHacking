package org.datanucleus.store.rdbms.sql.method;

public class MaxFunction extends SimpleOrderableAggregateMethod {
   protected String getFunctionName() {
      return "MAX";
   }
}
