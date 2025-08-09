package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.orc.DataMask;

public class DoubleIdentity implements DataMask {
   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      DoubleColumnVector target = (DoubleColumnVector)masked;
      DoubleColumnVector source = (DoubleColumnVector)original;
      target.isRepeating = source.isRepeating;
      target.noNulls = source.noNulls;
      if (source.isRepeating) {
         target.vector[0] = source.vector[0];
         target.isNull[0] = source.isNull[0];
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            target.vector[r] = source.vector[r];
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.isNull[r] = source.isNull[r];
            target.vector[r] = source.vector[r];
         }
      }

   }
}
