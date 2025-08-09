package org.datanucleus.store.rdbms.sql.method;

public class StringTrimRight3Method extends StringTrim3Method {
   protected String getTrimSpecKeyword() {
      return "TRAILING";
   }
}
