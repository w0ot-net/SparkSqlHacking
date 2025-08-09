package org.apache.parquet.internal.column.columnindex;

import java.util.PrimitiveIterator;
import java.util.function.IntPredicate;

public enum BoundaryOrder {
   UNORDERED {
      PrimitiveIterator.OfInt eq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int var10000 = comparator.arrayLength();
         IntPredicate var10001 = (arrayIndex) -> comparator.compareValueToMin(arrayIndex) >= 0 && comparator.compareValueToMax(arrayIndex) <= 0;
         comparator.getClass();
         return IndexIterator.filterTranslate(var10000, var10001, comparator::translate);
      }

      PrimitiveIterator.OfInt gt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int var10000 = comparator.arrayLength();
         IntPredicate var10001 = (arrayIndex) -> comparator.compareValueToMax(arrayIndex) < 0;
         comparator.getClass();
         return IndexIterator.filterTranslate(var10000, var10001, comparator::translate);
      }

      PrimitiveIterator.OfInt gtEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int var10000 = comparator.arrayLength();
         IntPredicate var10001 = (arrayIndex) -> comparator.compareValueToMax(arrayIndex) <= 0;
         comparator.getClass();
         return IndexIterator.filterTranslate(var10000, var10001, comparator::translate);
      }

      PrimitiveIterator.OfInt lt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int var10000 = comparator.arrayLength();
         IntPredicate var10001 = (arrayIndex) -> comparator.compareValueToMin(arrayIndex) > 0;
         comparator.getClass();
         return IndexIterator.filterTranslate(var10000, var10001, comparator::translate);
      }

      PrimitiveIterator.OfInt ltEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int var10000 = comparator.arrayLength();
         IntPredicate var10001 = (arrayIndex) -> comparator.compareValueToMin(arrayIndex) >= 0;
         comparator.getClass();
         return IndexIterator.filterTranslate(var10000, var10001, comparator::translate);
      }

      PrimitiveIterator.OfInt notEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int var10000 = comparator.arrayLength();
         IntPredicate var10001 = (arrayIndex) -> comparator.compareValueToMin(arrayIndex) != 0 || comparator.compareValueToMax(arrayIndex) != 0;
         comparator.getClass();
         return IndexIterator.filterTranslate(var10000, var10001, comparator::translate);
      }
   },
   ASCENDING {
      PrimitiveIterator.OfInt eq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         Bounds bounds = this.findBounds(comparator);
         if (bounds == null) {
            return IndexIterator.EMPTY;
         } else {
            int var10000 = bounds.lower;
            int var10001 = bounds.upper;
            comparator.getClass();
            return IndexIterator.rangeTranslate(var10000, var10001, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt gt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = 0;
            int right = length;

            do {
               int i = BoundaryOrder.floorMid(left, right);
               if (comparator.compareValueToMax(i) >= 0) {
                  left = i + 1;
               } else {
                  right = i;
               }
            } while(left < right);

            int var10001 = length - 1;
            comparator.getClass();
            return IndexIterator.rangeTranslate(right, var10001, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt gtEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = 0;
            int right = length;

            do {
               int i = BoundaryOrder.floorMid(left, right);
               if (comparator.compareValueToMax(i) > 0) {
                  left = i + 1;
               } else {
                  right = i;
               }
            } while(left < right);

            int var10001 = length - 1;
            comparator.getClass();
            return IndexIterator.rangeTranslate(right, var10001, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt lt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = -1;
            int right = length - 1;

            do {
               int i = BoundaryOrder.ceilingMid(left, right);
               if (comparator.compareValueToMin(i) <= 0) {
                  right = i - 1;
               } else {
                  left = i;
               }
            } while(left < right);

            comparator.getClass();
            return IndexIterator.rangeTranslate(0, left, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt ltEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = -1;
            int right = length - 1;

            do {
               int i = BoundaryOrder.ceilingMid(left, right);
               if (comparator.compareValueToMin(i) < 0) {
                  right = i - 1;
               } else {
                  left = i;
               }
            } while(left < right);

            comparator.getClass();
            return IndexIterator.rangeTranslate(0, left, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt notEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         Bounds bounds = this.findBounds(comparator);
         int length = comparator.arrayLength();
         if (bounds == null) {
            return IndexIterator.all(comparator);
         } else {
            IntPredicate var10001 = (i) -> i < bounds.lower || i > bounds.upper || comparator.compareValueToMin(i) != 0 || comparator.compareValueToMax(i) != 0;
            comparator.getClass();
            return IndexIterator.filterTranslate(length, var10001, comparator::translate);
         }
      }

      private Bounds findBounds(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         int lowerLeft = 0;
         int upperLeft = 0;
         int lowerRight = length - 1;
         int upperRight = length - 1;

         while(lowerLeft <= lowerRight) {
            int i = BoundaryOrder.floorMid(lowerLeft, lowerRight);
            if (comparator.compareValueToMin(i) < 0) {
               lowerRight = upperRight = i - 1;
            } else if (comparator.compareValueToMax(i) > 0) {
               lowerLeft = upperLeft = i + 1;
            } else {
               upperLeft = i;
               lowerRight = i;
            }

            if (lowerLeft == lowerRight) {
               while(upperLeft <= upperRight) {
                  i = BoundaryOrder.ceilingMid(upperLeft, upperRight);
                  if (comparator.compareValueToMin(i) < 0) {
                     upperRight = i - 1;
                  } else if (comparator.compareValueToMax(i) > 0) {
                     upperLeft = i + 1;
                  } else {
                     upperLeft = i;
                  }

                  if (upperLeft == upperRight) {
                     return new Bounds(lowerLeft, upperRight);
                  }
               }

               return null;
            }
         }

         return null;
      }
   },
   DESCENDING {
      PrimitiveIterator.OfInt eq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         Bounds bounds = this.findBounds(comparator);
         if (bounds == null) {
            return IndexIterator.EMPTY;
         } else {
            int var10000 = bounds.lower;
            int var10001 = bounds.upper;
            comparator.getClass();
            return IndexIterator.rangeTranslate(var10000, var10001, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt gt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = -1;
            int right = length - 1;

            do {
               int i = BoundaryOrder.ceilingMid(left, right);
               if (comparator.compareValueToMax(i) >= 0) {
                  right = i - 1;
               } else {
                  left = i;
               }
            } while(left < right);

            comparator.getClass();
            return IndexIterator.rangeTranslate(0, left, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt gtEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = -1;
            int right = length - 1;

            do {
               int i = BoundaryOrder.ceilingMid(left, right);
               if (comparator.compareValueToMax(i) > 0) {
                  right = i - 1;
               } else {
                  left = i;
               }
            } while(left < right);

            comparator.getClass();
            return IndexIterator.rangeTranslate(0, left, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt lt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = 0;
            int right = length;

            do {
               int i = BoundaryOrder.floorMid(left, right);
               if (comparator.compareValueToMin(i) <= 0) {
                  left = i + 1;
               } else {
                  right = i;
               }
            } while(left < right);

            int var10001 = length - 1;
            comparator.getClass();
            return IndexIterator.rangeTranslate(right, var10001, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt ltEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         if (length == 0) {
            return IndexIterator.EMPTY;
         } else {
            int left = 0;
            int right = length;

            do {
               int i = BoundaryOrder.floorMid(left, right);
               if (comparator.compareValueToMin(i) < 0) {
                  left = i + 1;
               } else {
                  right = i;
               }
            } while(left < right);

            int var10001 = length - 1;
            comparator.getClass();
            return IndexIterator.rangeTranslate(right, var10001, comparator::translate);
         }
      }

      PrimitiveIterator.OfInt notEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         Bounds bounds = this.findBounds(comparator);
         int length = comparator.arrayLength();
         if (bounds == null) {
            return IndexIterator.all(comparator);
         } else {
            IntPredicate var10001 = (i) -> i < bounds.lower || i > bounds.upper || comparator.compareValueToMin(i) != 0 || comparator.compareValueToMax(i) != 0;
            comparator.getClass();
            return IndexIterator.filterTranslate(length, var10001, comparator::translate);
         }
      }

      private Bounds findBounds(ColumnIndexBuilder.ColumnIndexBase.ValueComparator comparator) {
         int length = comparator.arrayLength();
         int lowerLeft = 0;
         int upperLeft = 0;
         int lowerRight = length - 1;
         int upperRight = length - 1;

         while(lowerLeft <= lowerRight) {
            int i = BoundaryOrder.floorMid(lowerLeft, lowerRight);
            if (comparator.compareValueToMax(i) > 0) {
               lowerRight = upperRight = i - 1;
            } else if (comparator.compareValueToMin(i) < 0) {
               lowerLeft = upperLeft = i + 1;
            } else {
               upperLeft = i;
               lowerRight = i;
            }

            if (lowerLeft == lowerRight) {
               while(upperLeft <= upperRight) {
                  i = BoundaryOrder.ceilingMid(upperLeft, upperRight);
                  if (comparator.compareValueToMax(i) > 0) {
                     upperRight = i - 1;
                  } else if (comparator.compareValueToMin(i) < 0) {
                     upperLeft = i + 1;
                  } else {
                     upperLeft = i;
                  }

                  if (upperLeft == upperRight) {
                     return new Bounds(lowerLeft, upperRight);
                  }
               }

               return null;
            }
         }

         return null;
      }
   };

   private BoundaryOrder() {
   }

   private static int floorMid(int left, int right) {
      return left + (right - left) / 2;
   }

   private static int ceilingMid(int left, int right) {
      return left + (right - left + 1) / 2;
   }

   abstract PrimitiveIterator.OfInt eq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator var1);

   abstract PrimitiveIterator.OfInt gt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator var1);

   abstract PrimitiveIterator.OfInt gtEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator var1);

   abstract PrimitiveIterator.OfInt lt(ColumnIndexBuilder.ColumnIndexBase.ValueComparator var1);

   abstract PrimitiveIterator.OfInt ltEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator var1);

   abstract PrimitiveIterator.OfInt notEq(ColumnIndexBuilder.ColumnIndexBase.ValueComparator var1);

   private static class Bounds {
      final int lower;
      final int upper;

      Bounds(int lower, int upper) {
         assert lower <= upper;

         this.lower = lower;
         this.upper = upper;
      }
   }
}
