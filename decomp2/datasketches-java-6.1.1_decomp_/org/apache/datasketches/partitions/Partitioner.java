package org.apache.datasketches.partitions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.quantilescommon.GenericPartitionBoundaries;
import org.apache.datasketches.quantilescommon.QuantileSearchCriteria;
import org.apache.datasketches.quantilescommon.QuantilesGenericAPI;

public class Partitioner {
   private static final QuantileSearchCriteria defaultCriteria;
   private final long tgtPartitionSize;
   private final int maxPartsPerSk;
   private final SketchFillRequest fillReq;
   private final QuantileSearchCriteria criteria;
   private final ArrayDeque stack;
   private int numLevels;
   private int partitionsPerSk;
   private final List finalPartitionList;

   public Partitioner(long tgtPartitionSize, int maxPartsPerPass, SketchFillRequest fillReq) {
      this(tgtPartitionSize, maxPartsPerPass, fillReq, defaultCriteria);
   }

   public Partitioner(long tgtPartitionSize, int maxPartsPerSk, SketchFillRequest fillReq, QuantileSearchCriteria criteria) {
      this.stack = new ArrayDeque();
      this.finalPartitionList = new ArrayList();
      this.tgtPartitionSize = tgtPartitionSize;
      this.maxPartsPerSk = maxPartsPerSk;
      this.fillReq = fillReq;
      this.criteria = criteria;
   }

   public List partition(QuantilesGenericAPI sk) {
      if (sk.isEmpty()) {
         throw new SketchesArgumentException("The sketch must not be empty for this operation. ");
      } else {
         long inputN = sk.getN();
         double guessNumParts = Math.max((double)1.0F, Math.ceil((double)inputN / (double)this.tgtPartitionSize));
         this.numLevels = (int)Math.max((double)1.0F, Math.ceil(Math.log(guessNumParts) / Math.log((double)this.maxPartsPerSk)));
         int partsPerSk = (int)Math.round(Math.pow(guessNumParts, (double)1.0F / (double)this.numLevels));
         this.partitionsPerSk = Math.min(partsPerSk, this.maxPartsPerSk);
         GenericPartitionBoundaries<T> gpb = sk.getPartitionBoundariesFromNumParts(this.partitionsPerSk, this.criteria);
         StackElement<T> se = new StackElement(gpb, 0, "1");
         this.stack.push(se);
         this.partitionSearch(this.stack);
         return Collections.unmodifiableList(this.finalPartitionList);
      }
   }

   private void partitionSearch(ArrayDeque stack) {
      if (!stack.isEmpty()) {
         StackElement<T> se = (StackElement)stack.peek();
         GenericPartitionBoundaries<T> gpb = se.gpb;
         int numParts = gpb.getNumPartitions();
         if (stack.size() != this.numLevels) {
            if (++se.part <= numParts) {
               PartitionBoundsRow<T> row = new PartitionBoundsRow(se);
               S sk = (S)this.fillReq.getRange(row.lowerBound, row.upperBound, row.rule);
               GenericPartitionBoundaries<T> gpb2 = sk.getPartitionBoundariesFromNumParts(this.partitionsPerSk, this.criteria);
               int level = stack.size() + 1;
               String partId = se.levelPartId + "." + se.part + "," + level;
               StackElement<T> se2 = new StackElement(gpb2, 0, partId);
               stack.push(se2);
               this.partitionSearch(stack);
            }

            if (stack.isEmpty()) {
               return;
            }

            stack.pop();
            this.partitionSearch(stack);
         } else {
            while(++se.part <= numParts) {
               PartitionBoundsRow<T> row = new PartitionBoundsRow(se);
               this.finalPartitionList.add(row);
            }

            stack.pop();
            this.partitionSearch(stack);
         }

      }
   }

   static {
      defaultCriteria = QuantileSearchCriteria.INCLUSIVE;
   }

   public static class StackElement {
      public final GenericPartitionBoundaries gpb;
      public int part;
      public String levelPartId;

      public StackElement(GenericPartitionBoundaries gpb, int part, String levelPartId) {
         this.gpb = gpb;
         this.part = part;
         this.levelPartId = levelPartId;
      }
   }

   public static class PartitionBoundsRow {
      public int part;
      public String levelPartId;
      public long approxNumDeltaItems;
      public BoundsRule rule;
      public Object lowerBound;
      public Object upperBound;

      public PartitionBoundsRow(StackElement se) {
         GenericPartitionBoundaries<T> gpb = se.gpb;
         QuantileSearchCriteria searchCrit = gpb.getSearchCriteria();
         T[] boundaries = (T[])gpb.getBoundaries();
         int numParts = gpb.getNumPartitions();
         this.part = se.part;
         this.levelPartId = se.levelPartId + "." + this.part;
         long num;
         this.approxNumDeltaItems = num = gpb.getNumDeltaItems()[this.part];
         if (searchCrit == QuantileSearchCriteria.INCLUSIVE) {
            if (this.part == 1) {
               this.lowerBound = gpb.getMinItem();
               this.upperBound = boundaries[this.part];
               this.rule = num == 0L ? BoundsRule.INCLUDE_NEITHER : (this.lowerBound == this.upperBound ? BoundsRule.INCLUDE_UPPER : BoundsRule.INCLUDE_BOTH);
            } else {
               this.lowerBound = boundaries[this.part - 1];
               this.upperBound = boundaries[this.part];
               this.rule = num == 0L ? BoundsRule.INCLUDE_NEITHER : BoundsRule.INCLUDE_UPPER;
            }
         } else if (this.part == numParts) {
            this.lowerBound = boundaries[this.part - 1];
            this.upperBound = gpb.getMaxItem();
            this.rule = num == 0L ? BoundsRule.INCLUDE_NEITHER : (this.lowerBound == this.upperBound ? BoundsRule.INCLUDE_LOWER : BoundsRule.INCLUDE_BOTH);
         } else {
            this.lowerBound = boundaries[this.part - 1];
            this.upperBound = boundaries[this.part];
            this.rule = num == 0L ? BoundsRule.INCLUDE_NEITHER : BoundsRule.INCLUDE_LOWER;
         }

      }
   }
}
