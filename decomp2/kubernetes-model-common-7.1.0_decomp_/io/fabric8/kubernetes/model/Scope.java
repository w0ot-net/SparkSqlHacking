package io.fabric8.kubernetes.model;

public enum Scope {
   NAMESPACED("Namespaced"),
   CLUSTER("Cluster");

   private final String value;

   private Scope(String value) {
      this.value = value;
   }

   public String value() {
      return this.value;
   }

   public String toString() {
      return this.value;
   }
}
