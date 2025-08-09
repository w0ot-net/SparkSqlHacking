package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.DataMask;

public class NullifyMask implements DataMask {
   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      masked.noNulls = false;
      masked.isRepeating = true;
      masked.isNull[0] = true;
   }
}
