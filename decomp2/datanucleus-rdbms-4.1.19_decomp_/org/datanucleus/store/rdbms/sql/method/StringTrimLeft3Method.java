package org.datanucleus.store.rdbms.sql.method;

public class StringTrimLeft3Method extends StringTrim3Method {
   protected String getTrimSpecKeyword() {
      return "LEADING";
   }
}
