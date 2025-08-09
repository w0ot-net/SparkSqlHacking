package org.apache.arrow.vector.complex;

import org.apache.arrow.vector.ValueVector;

public class VectorWithOrdinal {
   public final ValueVector vector;
   public final int ordinal;

   public VectorWithOrdinal(ValueVector v, int ordinal) {
      this.vector = v;
      this.ordinal = ordinal;
   }
}
