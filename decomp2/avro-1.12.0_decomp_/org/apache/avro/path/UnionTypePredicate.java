package org.apache.avro.path;

public class UnionTypePredicate implements PositionalPathPredicate {
   private final String type;

   public UnionTypePredicate(String type) {
      this.type = type;
   }

   public String toString() {
      return "[" + this.type + "]";
   }
}
