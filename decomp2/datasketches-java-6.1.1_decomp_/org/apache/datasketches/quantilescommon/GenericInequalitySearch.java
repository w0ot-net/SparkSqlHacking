package org.apache.datasketches.quantilescommon;

import java.util.Comparator;
import java.util.Objects;
import org.apache.datasketches.common.SketchesArgumentException;

public final class GenericInequalitySearch {
   public static int find(Object[] arr, int low, int high, Object v, Inequality crit, Comparator comparator) {
      Objects.requireNonNull(arr, "Input arr must not be null");
      Objects.requireNonNull(v, "Input v must not be null");
      Objects.requireNonNull(crit, "Input inequality must not be null");
      Objects.requireNonNull(comparator, "Input comparator must not be null");
      if (arr.length == 0) {
         throw new SketchesArgumentException("Input array must not be empty.");
      } else {
         int lo = low;
         int hi = high;

         while(lo <= hi) {
            if (hi - lo <= 1) {
               return resolve(arr, lo, hi, v, crit, comparator);
            }

            int mid = lo + (hi - lo) / 2;
            int ret = compare(arr, mid, mid + 1, v, crit, comparator);
            if (ret == -1) {
               hi = mid;
            } else {
               if (ret != 1) {
                  return getIndex(arr, mid, mid + 1, v, crit, comparator);
               }

               lo = mid + 1;
            }
         }

         return -1;
      }
   }

   private static int compare(Object[] arr, int a, int b, Object v, Inequality crit, Comparator comparator) {
      int result = 0;
      switch (crit) {
         case GE:
         case LT:
            result = comparator.compare(v, arr[a]) <= 0 ? -1 : (comparator.compare(arr[b], v) < 0 ? 1 : 0);
            break;
         case GT:
         case LE:
            result = comparator.compare(v, arr[a]) < 0 ? -1 : (comparator.compare(arr[b], v) <= 0 ? 1 : 0);
            break;
         case EQ:
            result = comparator.compare(v, arr[a]) < 0 ? -1 : (comparator.compare(arr[b], v) < 0 ? 1 : 0);
      }

      return result;
   }

   private static int getIndex(Object[] arr, int a, int b, Object v, Inequality crit, Comparator comparator) {
      int result = 0;
      switch (crit) {
         case GE:
         case GT:
            result = b;
            break;
         case LT:
         case LE:
            result = a;
            break;
         case EQ:
            result = comparator.compare(v, arr[a]) == 0 ? a : (comparator.compare(v, arr[b]) == 0 ? b : -1);
      }

      return result;
   }

   private static int resolve(Object[] arr, int lo, int hi, Object v, Inequality crit, Comparator comparator) {
      int result = 0;
      switch (crit) {
         case GE:
            result = lo == hi ? (comparator.compare(v, arr[lo]) <= 0 ? lo : -1) : (comparator.compare(v, arr[lo]) <= 0 ? lo : (comparator.compare(v, arr[hi]) <= 0 ? hi : -1));
            break;
         case LT:
            result = lo == hi ? (comparator.compare(v, arr[lo]) > 0 ? lo : -1) : (comparator.compare(v, arr[hi]) > 0 ? hi : (comparator.compare(v, arr[lo]) > 0 ? lo : -1));
            break;
         case GT:
            result = lo == hi ? (comparator.compare(v, arr[lo]) < 0 ? lo : -1) : (comparator.compare(v, arr[lo]) < 0 ? lo : (comparator.compare(v, arr[hi]) < 0 ? hi : -1));
            break;
         case LE:
            result = lo == hi ? (comparator.compare(v, arr[lo]) >= 0 ? lo : -1) : (comparator.compare(v, arr[hi]) >= 0 ? hi : (comparator.compare(v, arr[lo]) >= 0 ? lo : -1));
            break;
         case EQ:
            result = lo == hi ? (comparator.compare(v, arr[lo]) == 0 ? lo : -1) : (comparator.compare(v, arr[hi]) == 0 ? hi : (comparator.compare(v, arr[lo]) == 0 ? lo : -1));
      }

      return result;
   }

   public static enum Inequality {
      LT,
      LE,
      EQ,
      GE,
      GT;
   }
}
