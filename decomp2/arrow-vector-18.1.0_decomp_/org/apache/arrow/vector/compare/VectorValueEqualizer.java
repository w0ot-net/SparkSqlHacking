package org.apache.arrow.vector.compare;

import org.apache.arrow.vector.ValueVector;

public interface VectorValueEqualizer extends Cloneable {
   boolean valuesEqual(ValueVector var1, int var2, ValueVector var3, int var4);

   VectorValueEqualizer clone();
}
