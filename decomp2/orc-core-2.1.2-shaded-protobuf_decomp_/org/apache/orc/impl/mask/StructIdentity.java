package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.orc.DataMask;

public class StructIdentity implements DataMask {
   private final DataMask[] children;

   StructIdentity(DataMask[] children) {
      this.children = children;
   }

   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      StructColumnVector source = (StructColumnVector)original;
      StructColumnVector target = (StructColumnVector)masked;
      target.isRepeating = source.isRepeating;
      target.noNulls = source.noNulls;
      if (source.isRepeating) {
         target.isNull[0] = source.isNull[0];
         if (source.noNulls || !source.isNull[0]) {
            for(int c = 0; c < this.children.length; ++c) {
               this.children[c].maskData(source.fields[c], target.fields[c], 0, 1);
            }
         }
      } else if (source.noNulls) {
         for(int c = 0; c < this.children.length; ++c) {
            this.children[c].maskData(source.fields[c], target.fields[c], start, length);
         }
      } else {
         int batchStart = start;

         while(batchStart < start + length) {
            int r;
            for(r = batchStart; r < start + length && !source.isNull[r]; ++r) {
            }

            if (r != batchStart) {
               for(int c = 0; c < this.children.length; ++c) {
                  this.children[c].maskData(source.fields[c], target.fields[c], batchStart, r - batchStart);
               }
            }

            for(batchStart = r; batchStart < start + length && source.isNull[batchStart]; ++batchStart) {
            }
         }
      }

   }
}
