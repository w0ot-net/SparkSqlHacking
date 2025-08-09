package org.apache.datasketches.fdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.tuple.TupleSketchIterator;
import org.apache.datasketches.tuple.strings.ArrayOfStringsSummary;

public class PostProcessor {
   private final FdtSketch sketch;
   private final char sep;
   private int groupCount;
   private Group group;
   private boolean mapValid;
   private final int mapArrSize;
   private final long[] hashArr;
   private final String[] priKeyArr;
   private final int[] counterArr;

   public PostProcessor(FdtSketch sketch, Group group, char sep) {
      Objects.requireNonNull(sketch, "sketch must be non-null");
      Objects.requireNonNull(group, "group must be non-null");
      this.sketch = sketch.copy();
      this.sep = sep;
      int numEntries = sketch.getRetainedEntries();
      this.mapArrSize = Util.ceilingPowerOf2((int)((double)numEntries / (double)0.75F));
      this.hashArr = new long[this.mapArrSize];
      this.priKeyArr = new String[this.mapArrSize];
      this.counterArr = new int[this.mapArrSize];
      this.mapValid = false;
      this.group = group;
   }

   public int getGroupCount() {
      return this.groupCount;
   }

   public List getGroupList(int[] priKeyIndices, int numStdDev, int limit) {
      if (!this.mapValid) {
         this.populateMap(priKeyIndices);
      }

      return this.populateList(numStdDev, limit);
   }

   private void populateMap(int[] priKeyIndices) {
      TupleSketchIterator<ArrayOfStringsSummary> it = this.sketch.iterator();
      Arrays.fill(this.hashArr, 0L);
      Arrays.fill(this.priKeyArr, (Object)null);
      Arrays.fill(this.counterArr, 0);
      this.groupCount = 0;
      int lgMapArrSize = Integer.numberOfTrailingZeros(this.mapArrSize);

      while(it.next()) {
         String[] arr = ((ArrayOfStringsSummary)it.getSummary()).getValue();
         String priKey = getPrimaryKey(arr, priKeyIndices, this.sep);
         long hash = org.apache.datasketches.tuple.Util.stringHash(priKey);
         int index = HashOperations.hashSearchOrInsert(this.hashArr, lgMapArrSize, hash);
         if (index < 0) {
            int idx = -(index + 1);
            this.counterArr[idx] = 1;
            ++this.groupCount;
            this.priKeyArr[idx] = priKey;
         } else {
            int var10002 = this.counterArr[index]++;
         }
      }

      this.mapValid = true;
   }

   private List populateList(int numStdDev, int limit) {
      List<Group> list = new ArrayList();

      for(int i = 0; i < this.mapArrSize; ++i) {
         if (this.hashArr[i] != 0L) {
            String priKey = this.priKeyArr[i];
            int count = this.counterArr[i];
            double est = this.sketch.getEstimate(count);
            double ub = this.sketch.getUpperBound(numStdDev, count);
            double lb = this.sketch.getLowerBound(numStdDev, count);
            double thresh = (double)count / (double)this.sketch.getRetainedEntries();
            double rse = this.sketch.getUpperBound(1, count) / est - (double)1.0F;
            Group gp = new Group();
            gp.init(priKey, count, est, ub, lb, thresh, rse);
            list.add(gp);
         }
      }

      list.sort((Comparator)null);
      int totLen = list.size();
      List<Group> returnList;
      if (limit > 0 && limit < totLen) {
         returnList = list.subList(0, limit);
      } else {
         returnList = list;
      }

      return returnList;
   }

   private static String getPrimaryKey(String[] tuple, int[] priKeyIndices, char sep) {
      assert priKeyIndices.length < tuple.length;

      StringBuilder sb = new StringBuilder();
      int keys = priKeyIndices.length;

      for(int i = 0; i < keys; ++i) {
         int idx = priKeyIndices[i];
         sb.append(tuple[idx]);
         if (i + 1 < keys) {
            sb.append(sep);
         }
      }

      return sb.toString();
   }
}
