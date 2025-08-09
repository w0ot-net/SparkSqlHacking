package org.apache.orc.impl.mask;

import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.orc.DataMask;

public class UnionIdentity implements DataMask {
   private final DataMask[] children;

   UnionIdentity(DataMask[] children) {
      this.children = children;
   }

   public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
      UnionColumnVector source = (UnionColumnVector)original;
      UnionColumnVector target = (UnionColumnVector)masked;
      target.isRepeating = source.isRepeating;
      target.noNulls = source.noNulls;
      if (source.isRepeating) {
         target.isNull[0] = source.isNull[0];
         if (source.noNulls || !source.isNull[0]) {
            int tag = source.tags[0];
            target.tags[0] = tag;
            this.children[tag].maskData(source.fields[tag], target.fields[tag], 0, 1);
         }
      } else if (source.noNulls) {
         for(int r = start; r < start + length; ++r) {
            int tag = source.tags[r];
            target.tags[r] = tag;
            this.children[tag].maskData(source.fields[tag], target.fields[tag], r, 1);
         }
      } else {
         for(int r = start; r < start + length; ++r) {
            target.isNull[r] = source.isNull[r];
            if (!source.isNull[r]) {
               int tag = source.tags[r];
               target.tags[r] = tag;
               this.children[tag].maskData(source.fields[tag], target.fields[tag], r, 1);
            }
         }
      }

   }
}
