package io.fabric8.kubernetes.api.model;

public enum DeletionPropagation {
   ORPHAN("Orphan"),
   BACKGROUND("Background"),
   FOREGROUND("Foreground");

   private final String value;

   private DeletionPropagation(String value) {
      this.value = value;
   }

   public String toString() {
      return this.value;
   }
}
