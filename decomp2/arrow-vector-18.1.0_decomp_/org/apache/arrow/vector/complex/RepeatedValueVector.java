package org.apache.arrow.vector.complex;

import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.UInt4Vector;
import org.apache.arrow.vector.ValueVector;

public interface RepeatedValueVector extends ValueVector, DensityAwareVector {
   int DEFAULT_REPEAT_PER_RECORD = 5;

   /** @deprecated */
   @Deprecated
   UInt4Vector getOffsetVector();

   ValueVector getDataVector();
}
