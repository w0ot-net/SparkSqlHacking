package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.orc.DataMask;

public class ListIdentity implements DataMask {
   private final DataMask child;

   ListIdentity(DataMask[] child) {
      this.child = child[0];
   }

   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      ListColumnVector source = (ListColumnVector)original;
      ListColumnVector target = (ListColumnVector)masked;
      target.noNulls = source.noNulls;
      target.isRepeating = source.isRepeating;
      if (source.isRepeating) {
         if (!source.noNulls && source.isNull[0]) {
            target.isNull[0] = true;
         } else {
            target.lengths[0] = source.lengths[0];
            this.child.maskData(source.child, target.child, (int)source.offsets[0], (int)source.lengths[0]);
         }
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            target.offsets[r] = source.offsets[r];
            target.lengths[r] = source.lengths[r];
            this.child.maskData(source.child, target.child, (int)target.offsets[r], (int)target.lengths[r]);
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.isNull[r] = source.isNull[r];
            if (!source.isNull[r]) {
               target.offsets[r] = source.offsets[r];
               target.lengths[r] = source.lengths[r];
               this.child.maskData(source.child, target.child, (int)target.offsets[r], (int)target.lengths[r]);
            }
         }
      }

   }
}
