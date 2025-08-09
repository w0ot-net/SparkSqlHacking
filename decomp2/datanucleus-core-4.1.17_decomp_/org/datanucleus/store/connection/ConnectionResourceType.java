package org.datanucleus.store.connection;

public enum ConnectionResourceType {
   JTA("JTA"),
   RESOURCE_LOCAL("RESOURCE_LOCAL");

   String name;

   private ConnectionResourceType(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }
}
