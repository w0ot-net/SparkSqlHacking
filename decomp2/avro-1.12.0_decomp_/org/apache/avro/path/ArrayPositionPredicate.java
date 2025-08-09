package org.apache.avro.path;

public class ArrayPositionPredicate implements PositionalPathPredicate {
   private final long index;

   public ArrayPositionPredicate(long index) {
      this.index = index;
   }

   public String toString() {
      return "[" + this.index + "]";
   }
}
