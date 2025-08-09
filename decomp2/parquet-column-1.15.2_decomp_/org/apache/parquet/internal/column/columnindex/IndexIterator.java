package org.apache.parquet.internal.column.columnindex;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class IndexIterator implements PrimitiveIterator.OfInt {
   public static final PrimitiveIterator.OfInt EMPTY = IntStream.empty().iterator();
   private int index;
   private final int endIndex;
   private final IntPredicate filter;
   private final IntUnaryOperator translator;

   public static PrimitiveIterator.OfInt all(int pageCount) {
      return new IndexIterator(0, pageCount, (i) -> true, (i) -> i);
   }

   static PrimitiveIterator.OfInt all(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
      int var10003 = comparator.arrayLength();
      IntPredicate var10004 = (i) -> true;
      comparator.getClass();
      return new IndexIterator(0, var10003, var10004, comparator::translate);
   }

   public static PrimitiveIterator.OfInt filter(int pageCount, IntPredicate filter) {
      return new IndexIterator(0, pageCount, filter, (i) -> i);
   }

   static PrimitiveIterator.OfInt filterTranslate(int arrayLength, IntPredicate filter, IntUnaryOperator translator) {
      return new IndexIterator(0, arrayLength, filter, translator);
   }

   static PrimitiveIterator.OfInt rangeTranslate(int from, int to, IntUnaryOperator translator) {
      return new IndexIterator(from, to + 1, (i) -> true, translator);
   }

   static PrimitiveIterator.OfInt intersection(final PrimitiveIterator.OfInt lhs, final PrimitiveIterator.OfInt rhs) {
      return new PrimitiveIterator.OfInt() {
         private int next = this.fetchNext();

         public int nextInt() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               int result = this.next;
               this.next = this.fetchNext();
               return result;
            }
         }

         public boolean hasNext() {
            return this.next != -1;
         }

         private int fetchNext() {
            if (lhs.hasNext() && rhs.hasNext()) {
               int nextL = lhs.next();
               int nextR = rhs.next();

               while(true) {
                  while(nextL < nextR && lhs.hasNext()) {
                     nextL = lhs.next();
                  }

                  while(nextR < nextL && rhs.hasNext()) {
                     nextR = rhs.next();
                  }

                  if (nextL == nextR) {
                     return nextL;
                  }

                  if (nextL >= nextR || !lhs.hasNext()) {
                     return -1;
                  }

                  nextL = lhs.next();
               }
            } else {
               return -1;
            }
         }
      };
   }

   static PrimitiveIterator.OfInt union(final PrimitiveIterator.OfInt lhs, final PrimitiveIterator.OfInt rhs) {
      return new PrimitiveIterator.OfInt() {
         private int peekL = -1;
         private int peekR = -1;
         private int next = this.fetchNext();

         public int nextInt() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               int result = this.next;
               this.next = this.fetchNext();
               return result;
            }
         }

         public boolean hasNext() {
            return this.next != -1;
         }

         private int fetchNext() {
            if (this.peekL == -1 && this.peekR == -1 && !lhs.hasNext() && !rhs.hasNext()) {
               return -1;
            } else {
               if (this.peekL == -1 && lhs.hasNext()) {
                  this.peekL = lhs.next();
               }

               if (this.peekR == -1 && rhs.hasNext()) {
                  this.peekR = rhs.next();
               }

               int result;
               if (this.peekL == -1 || this.peekL != this.peekR && this.peekR != -1) {
                  if (this.peekL == -1 && this.peekR != -1) {
                     result = this.peekR;
                     this.peekR = -1;
                  } else if (this.peekL < this.peekR) {
                     result = this.peekL;
                     this.peekL = -1;
                  } else {
                     result = this.peekR;
                     this.peekR = -1;
                  }
               } else {
                  result = this.peekL;
                  this.peekL = -1;
                  this.peekR = -1;
               }

               return result;
            }
         }
      };
   }

   private IndexIterator(int startIndex, int endIndex, IntPredicate filter, IntUnaryOperator translator) {
      this.endIndex = endIndex;
      this.filter = filter;
      this.translator = translator;
      this.index = this.nextPageIndex(startIndex);
   }

   private int nextPageIndex(int startIndex) {
      for(int i = startIndex; i < this.endIndex; ++i) {
         if (this.filter.test(i)) {
            return i;
         }
      }

      return -1;
   }

   public boolean hasNext() {
      return this.index >= 0;
   }

   public int nextInt() {
      if (this.hasNext()) {
         int ret = this.index;
         this.index = this.nextPageIndex(this.index + 1);
         return this.translator.applyAsInt(ret);
      } else {
         throw new NoSuchElementException();
      }
   }
}
