package org.datanucleus.store.rdbms.sql.method;

public class StringTrimLeftMethod extends SimpleStringMethod {
   protected String getFunctionName() {
      return "LTRIM";
   }
}
