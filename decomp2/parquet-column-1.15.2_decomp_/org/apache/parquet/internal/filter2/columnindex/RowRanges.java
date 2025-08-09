package org.apache.parquet.internal.filter2.columnindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;

public class RowRanges {
   public static final RowRanges EMPTY = new RowRanges(Collections.emptyList());
   private final List ranges;

   private RowRanges() {
      this((List)(new ArrayList()));
   }

   private RowRanges(Range range) {
      this(Collections.singletonList(range));
   }

   private RowRanges(List ranges) {
      this.ranges = ranges;
   }

   public static RowRanges createSingle(long rowCount) {
      return new RowRanges(new Range(0L, rowCount - 1L));
   }

   public static RowRanges create(long rowCount, PrimitiveIterator.OfInt pageIndexes, OffsetIndex offsetIndex) {
      RowRanges ranges = new RowRanges();

      while(pageIndexes.hasNext()) {
         int pageIndex = pageIndexes.nextInt();
         ranges.add(new Range(offsetIndex.getFirstRowIndex(pageIndex), offsetIndex.getLastRowIndex(pageIndex, rowCount)));
      }

      return ranges;
   }

   public static RowRanges union(RowRanges left, RowRanges right) {
      RowRanges result = new RowRanges();
      Iterator<Range> it1 = left.ranges.iterator();
      Iterator<Range> it2 = right.ranges.iterator();
      if (it2.hasNext()) {
         Range range2 = (Range)it2.next();

         while(it1.hasNext()) {
            Range range1 = (Range)it1.next();
            if (range1.isAfter(range2)) {
               result.add(range2);
               range2 = range1;
               Iterator<Range> tmp = it1;
               it1 = it2;
               it2 = tmp;
            } else {
               result.add(range1);
            }
         }

         result.add(range2);
      } else {
         it2 = it1;
      }

      while(it2.hasNext()) {
         result.add((Range)it2.next());
      }

      return result;
   }

   public static RowRanges intersection(RowRanges left, RowRanges right) {
      RowRanges result = new RowRanges();
      int rightIndex = 0;

      for(Range l : left.ranges) {
         int i = rightIndex;

         for(int n = right.ranges.size(); i < n; ++i) {
            Range r = (Range)right.ranges.get(i);
            if (l.isBefore(r)) {
               break;
            }

            if (l.isAfter(r)) {
               rightIndex = i + 1;
            } else {
               result.add(RowRanges.Range.intersection(l, r));
            }
         }
      }

      return result;
   }

   private void add(Range range) {
      Range rangeToAdd = range;

      for(int i = this.ranges.size() - 1; i >= 0; --i) {
         Range last = (Range)this.ranges.get(i);

         assert !last.isAfter(range);

         Range u = RowRanges.Range.union(last, rangeToAdd);
         if (u == null) {
            break;
         }

         rangeToAdd = u;
         this.ranges.remove(i);
      }

      this.ranges.add(rangeToAdd);
   }

   public long rowCount() {
      long cnt = 0L;

      for(Range range : this.ranges) {
         cnt += range.count();
      }

      return cnt;
   }

   public PrimitiveIterator.OfLong iterator() {
      return new PrimitiveIterator.OfLong() {
         private int currentRangeIndex = -1;
         private Range currentRange;
         private long next = this.findNext();

         private long findNext() {
            if (this.currentRange != null && this.next + 1L <= this.currentRange.to) {
               ++this.next;
            } else {
               if (this.currentRangeIndex + 1 >= RowRanges.this.ranges.size()) {
                  return -1L;
               }

               this.currentRange = (Range)RowRanges.this.ranges.get(++this.currentRangeIndex);
               this.next = this.currentRange.from;
            }

            return this.next;
         }

         public boolean hasNext() {
            return this.next >= 0L;
         }

         public long nextLong() {
            long ret = this.next;
            if (ret < 0L) {
               throw new NoSuchElementException();
            } else {
               this.next = this.findNext();
               return ret;
            }
         }
      };
   }

   public boolean isOverlapping(long from, long to) {
      return Collections.binarySearch(this.ranges, new Range(from, to), (r1, r2) -> r1.isBefore(r2) ? -1 : (r1.isAfter(r2) ? 1 : 0)) >= 0;
   }

   public List getRanges() {
      return this.ranges;
   }

   public String toString() {
      return this.ranges.toString();
   }

   public static class Range {
      public final long from;
      public final long to;

      private static Range union(Range left, Range right) {
         if (left.from <= right.from) {
            if (left.to + 1L >= right.from) {
               return new Range(left.from, Math.max(left.to, right.to));
            }
         } else if (right.to + 1L >= left.from) {
            return new Range(right.from, Math.max(left.to, right.to));
         }

         return null;
      }

      private static Range intersection(Range left, Range right) {
         if (left.from <= right.from) {
            if (left.to >= right.from) {
               return new Range(right.from, Math.min(left.to, right.to));
            }
         } else if (right.to >= left.from) {
            return new Range(left.from, Math.min(left.to, right.to));
         }

         return null;
      }

      Range(long from, long to) {
         assert from <= to;

         this.from = from;
         this.to = to;
      }

      long count() {
         return this.to - this.from + 1L;
      }

      boolean isBefore(Range other) {
         return this.to < other.from;
      }

      boolean isAfter(Range other) {
         return this.from > other.to;
      }

      public String toString() {
         return "[" + this.from + ", " + this.to + ']';
      }
   }
}
