package org.datanucleus.store.rdbms.request;

public enum RequestType {
   INSERT("insert"),
   UPDATE("update"),
   DELETE("delete"),
   FETCH("fetch"),
   LOCATE("locate");

   private String name;

   private RequestType(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }
}
