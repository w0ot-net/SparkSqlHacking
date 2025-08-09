package org.apache.datasketches.tuple;

public class DeserializeResult {
   private final Object object;
   private final int size;

   public DeserializeResult(Object object, int size) {
      this.object = object;
      this.size = size;
   }

   public Object getObject() {
      return this.object;
   }

   public int getSize() {
      return this.size;
   }
}
