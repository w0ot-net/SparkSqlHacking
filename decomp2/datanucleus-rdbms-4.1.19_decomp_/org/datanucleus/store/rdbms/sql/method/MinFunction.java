package org.datanucleus.store.rdbms.sql.method;

public class MinFunction extends SimpleOrderableAggregateMethod {
   protected String getFunctionName() {
      return "MIN";
   }
}
