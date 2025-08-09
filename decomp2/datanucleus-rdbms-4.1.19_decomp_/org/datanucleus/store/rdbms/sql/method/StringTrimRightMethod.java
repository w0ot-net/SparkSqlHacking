package org.datanucleus.store.rdbms.sql.method;

public class StringTrimRightMethod extends SimpleStringMethod {
   protected String getFunctionName() {
      return "RTRIM";
   }
}
