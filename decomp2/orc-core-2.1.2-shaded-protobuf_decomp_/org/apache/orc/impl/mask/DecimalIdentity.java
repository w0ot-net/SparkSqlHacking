package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.orc.DataMask;

public class DecimalIdentity implements DataMask {
   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      DecimalColumnVector target = (DecimalColumnVector)masked;
      DecimalColumnVector source = (DecimalColumnVector)original;
      target.scale = source.scale;
      target.precision = source.precision;
      target.isRepeating = source.isRepeating;
      target.noNulls = source.noNulls;
      if (source.isRepeating) {
         target.vector[0].set(source.vector[0]);
         target.isNull[0] = source.isNull[0];
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            target.vector[r].set(source.vector[r]);
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.isNull[r] = source.isNull[r];
            if (!target.isNull[r]) {
               target.vector[r].set(source.vector[r]);
            }
         }
      }

   }
}
