package org.apache.arrow.vector.complex;

import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.UInt4Vector;

public class EmptyValuePopulator {
   private final UInt4Vector offsets;

   public EmptyValuePopulator(UInt4Vector offsets) {
      this.offsets = (UInt4Vector)Preconditions.checkNotNull(offsets, "offsets cannot be null");
   }

   public void populate(int lastIndex) {
      if (lastIndex < 0) {
         throw new IndexOutOfBoundsException("index cannot be negative");
      } else {
         int lastSet = Math.max(this.offsets.getValueCount() - 1, 0);
         int previousEnd = this.offsets.get(lastSet);

         for(int i = lastSet; i < lastIndex; ++i) {
            this.offsets.setSafe(i + 1, previousEnd);
         }

         this.offsets.setValueCount(lastIndex + 1);
      }
   }
}
