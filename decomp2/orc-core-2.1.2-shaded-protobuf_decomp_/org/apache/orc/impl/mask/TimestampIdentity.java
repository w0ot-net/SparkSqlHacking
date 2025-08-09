package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.orc.DataMask;

class TimestampIdentity implements DataMask {
   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      TimestampColumnVector target = (TimestampColumnVector)masked;
      TimestampColumnVector source = (TimestampColumnVector)original;
      target.noNulls = source.noNulls;
      target.isRepeating = source.isRepeating;
      if (original.isRepeating) {
         target.time[0] = source.time[0];
         target.nanos[0] = source.nanos[0];
         target.isNull[0] = source.isNull[0];
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            target.time[r] = source.time[r];
            target.nanos[r] = source.nanos[r];
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.time[r] = source.time[r];
            target.nanos[r] = source.nanos[r];
            target.isNull[r] = source.isNull[r];
         }
      }

   }
}
