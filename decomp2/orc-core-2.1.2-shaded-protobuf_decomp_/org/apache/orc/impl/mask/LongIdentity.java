package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.orc.DataMask;

public class LongIdentity implements DataMask {
   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      LongColumnVector target = (LongColumnVector)masked;
      LongColumnVector source = (LongColumnVector)original;
      target.isRepeating = source.isRepeating;
      target.noNulls = source.noNulls;
      if (original.isRepeating) {
         target.vector[0] = source.vector[0];
         target.isNull[0] = source.isNull[0];
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            target.vector[r] = source.vector[r];
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.vector[r] = source.vector[r];
            target.isNull[r] = source.isNull[r];
         }
      }

   }
}
