package org.apache.avro.path;

public class MapKeyPredicate implements PositionalPathPredicate {
   private final String key;

   public MapKeyPredicate(String key) {
      this.key = key;
   }

   public String getKey() {
      return this.key;
   }

   public String toString() {
      return this.key == null ? "" : "[\"" + this.key + "\"]";
   }
}
