package org.apache.arrow.vector;

import org.apache.arrow.util.Preconditions;

public class AddOrGetResult {
   private final ValueVector vector;
   private final boolean created;

   public AddOrGetResult(ValueVector vector, boolean created) {
      this.vector = (ValueVector)Preconditions.checkNotNull(vector);
      this.created = created;
   }

   public ValueVector getVector() {
      return this.vector;
   }

   public boolean isCreated() {
      return this.created;
   }
}
