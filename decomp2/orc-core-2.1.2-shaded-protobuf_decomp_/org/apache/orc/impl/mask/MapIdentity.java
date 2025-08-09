package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.orc.DataMask;

public class MapIdentity implements DataMask {
   private final DataMask keyMask;
   private final DataMask valueMask;

   MapIdentity(DataMask[] children) {
      this.keyMask = children[0];
      this.valueMask = children[1];
   }

   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      MapColumnVector source = (MapColumnVector)original;
      MapColumnVector target = (MapColumnVector)masked;
      target.isRepeating = source.isRepeating;
      target.noNulls = source.noNulls;
      if (source.isRepeating) {
         target.isNull[0] = source.isNull[0];
         if (source.noNulls || !source.isNull[0]) {
            target.lengths[0] = source.lengths[0];
            this.keyMask.maskData(source.keys, target.keys, (int)source.offsets[0], (int)source.lengths[0]);
            this.valueMask.maskData(source.values, target.values, (int)source.offsets[0], (int)source.lengths[0]);
         }
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            target.offsets[r] = source.offsets[r];
            target.lengths[r] = source.lengths[r];
            this.keyMask.maskData(source.keys, target.keys, (int)target.offsets[r], (int)target.lengths[r]);
            this.valueMask.maskData(source.values, target.values, (int)target.offsets[r], (int)target.lengths[r]);
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.isNull[r] = source.isNull[r];
            if (!source.isNull[r]) {
               target.offsets[r] = source.offsets[r];
               target.lengths[r] = source.lengths[r];
               this.keyMask.maskData(source.keys, target.keys, (int)target.offsets[r], (int)target.lengths[r]);
               this.valueMask.maskData(source.values, target.values, (int)target.offsets[r], (int)target.lengths[r]);
            }
         }
      }

   }
}
