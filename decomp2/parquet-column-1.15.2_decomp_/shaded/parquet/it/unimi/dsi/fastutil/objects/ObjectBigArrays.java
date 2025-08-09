package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import shaded.parquet.it.unimi.dsi.fastutil.BigArrays;
import shaded.parquet.it.unimi.dsi.fastutil.Hash;

public final class ObjectBigArrays {
   public static final Object[][] EMPTY_BIG_ARRAY = new Object[0][];
   public static final Object[][] DEFAULT_EMPTY_BIG_ARRAY = new Object[0][];
   public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy();
   private static final int QUICKSORT_NO_REC = 7;
   private static final int PARALLEL_QUICKSORT_NO_FORK = 8192;
   private static final int MEDIUM = 40;

   private ObjectBigArrays() {
   }

   /** @deprecated */
   @Deprecated
   public static Object get(Object[][] array, long index) {
      return array[BigArrays.segment(index)][BigArrays.displacement(index)];
   }

   /** @deprecated */
   @Deprecated
   public static void set(Object[][] array, long index, Object value) {
      array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
   }

   /** @deprecated */
   @Deprecated
   public static void swap(Object[][] array, long first, long second) {
      K t = (K)array[BigArrays.segment(first)][BigArrays.displacement(first)];
      array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
      array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
   }

   /** @deprecated */
   @Deprecated
   public static long length(Object[][] array) {
      int length = array.length;
      return length == 0 ? 0L : BigArrays.start(length - 1) + (long)array[length - 1].length;
   }

   /** @deprecated */
   @Deprecated
   public static void copy(Object[][] srcArray, long srcPos, Object[][] destArray, long destPos, long length) {
      BigArrays.copy(srcArray, srcPos, destArray, destPos, length);
   }

   /** @deprecated */
   @Deprecated
   public static void copyFromBig(Object[][] srcArray, long srcPos, Object[] destArray, int destPos, int length) {
      BigArrays.copyFromBig(srcArray, srcPos, destArray, destPos, length);
   }

   /** @deprecated */
   @Deprecated
   public static void copyToBig(Object[] srcArray, int srcPos, Object[][] destArray, long destPos, long length) {
      BigArrays.copyToBig(srcArray, srcPos, destArray, destPos, length);
   }

   public static Object[][] newBigArray(Object[][] prototype, long length) {
      return newBigArray(prototype.getClass().getComponentType(), length);
   }

   public static Object[][] newBigArray(Class componentType, long length) {
      if (length == 0L && componentType == Object[].class) {
         return EMPTY_BIG_ARRAY;
      } else {
         BigArrays.ensureLength(length);
         int baseLength = (int)(length + 134217727L >>> 27);
         Object[][] base = Array.newInstance(componentType, baseLength);
         int residual = (int)(length & 134217727L);
         if (residual != 0) {
            for(int i = 0; i < baseLength - 1; ++i) {
               base[i] = Array.newInstance(componentType.getComponentType(), 134217728);
            }

            base[baseLength - 1] = Array.newInstance(componentType.getComponentType(), residual);
         } else {
            for(int i = 0; i < baseLength; ++i) {
               base[i] = Array.newInstance(componentType.getComponentType(), 134217728);
            }
         }

         return base;
      }
   }

   public static Object[][] newBigArray(long length) {
      if (length == 0L) {
         return EMPTY_BIG_ARRAY;
      } else {
         BigArrays.ensureLength(length);
         int baseLength = (int)(length + 134217727L >>> 27);
         Object[][] base = new Object[baseLength][];
         int residual = (int)(length & 134217727L);
         if (residual != 0) {
            for(int i = 0; i < baseLength - 1; ++i) {
               base[i] = new Object[134217728];
            }

            base[baseLength - 1] = new Object[residual];
         } else {
            for(int i = 0; i < baseLength; ++i) {
               base[i] = new Object[134217728];
            }
         }

         return base;
      }
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] wrap(Object[] array) {
      return BigArrays.wrap(array);
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] ensureCapacity(Object[][] array, long length) {
      return ensureCapacity(array, length, length(array));
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] forceCapacity(Object[][] array, long length, long preserve) {
      return BigArrays.forceCapacity(array, length, preserve);
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] ensureCapacity(Object[][] array, long length, long preserve) {
      return length > length(array) ? forceCapacity(array, length, preserve) : array;
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] grow(Object[][] array, long length) {
      long oldLength = length(array);
      return length > oldLength ? grow(array, length, oldLength) : array;
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] grow(Object[][] array, long length, long preserve) {
      long oldLength = length(array);
      return length > oldLength ? ensureCapacity(array, Math.max(oldLength + (oldLength >> 1), length), preserve) : array;
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] trim(Object[][] array, long length) {
      return BigArrays.trim(array, length);
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] setLength(Object[][] array, long length) {
      return BigArrays.setLength(array, length);
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] copy(Object[][] array, long offset, long length) {
      return BigArrays.copy(array, offset, length);
   }

   /** @deprecated */
   @Deprecated
   public static Object[][] copy(Object[][] array) {
      return BigArrays.copy(array);
   }

   /** @deprecated */
   @Deprecated
   public static void fill(Object[][] array, Object value) {
      int i = array.length;

      while(i-- != 0) {
         Arrays.fill(array[i], value);
      }

   }

   /** @deprecated */
   @Deprecated
   public static void fill(Object[][] array, long from, long to, Object value) {
      BigArrays.fill(array, from, to, value);
   }

   /** @deprecated */
   @Deprecated
   public static boolean equals(Object[][] a1, Object[][] a2) {
      return BigArrays.equals(a1, a2);
   }

   /** @deprecated */
   @Deprecated
   public static String toString(Object[][] a) {
      return BigArrays.toString(a);
   }

   /** @deprecated */
   @Deprecated
   public static void ensureFromTo(Object[][] a, long from, long to) {
      BigArrays.ensureFromTo(length(a), from, to);
   }

   /** @deprecated */
   @Deprecated
   public static void ensureOffsetLength(Object[][] a, long offset, long length) {
      BigArrays.ensureOffsetLength(length(a), offset, length);
   }

   /** @deprecated */
   @Deprecated
   public static void ensureSameLength(Object[][] a, Object[][] b) {
      if (length(a) != length(b)) {
         throw new IllegalArgumentException("Array size mismatch: " + length(a) + " != " + length(b));
      }
   }

   private static ForkJoinPool getPool() {
      ForkJoinPool current = ForkJoinTask.getPool();
      return current == null ? ForkJoinPool.commonPool() : current;
   }

   private static void swap(Object[][] x, long a, long b, long n) {
      for(int i = 0; (long)i < n; ++b) {
         BigArrays.swap(x, a, b);
         ++i;
         ++a;
      }

   }

   private static long med3(Object[][] x, long a, long b, long c, Comparator comp) {
      int ab = comp.compare(BigArrays.get(x, a), BigArrays.get(x, b));
      int ac = comp.compare(BigArrays.get(x, a), BigArrays.get(x, c));
      int bc = comp.compare(BigArrays.get(x, b), BigArrays.get(x, c));
      return ab < 0 ? (bc < 0 ? b : (ac < 0 ? c : a)) : (bc > 0 ? b : (ac > 0 ? c : a));
   }

   private static void selectionSort(Object[][] a, long from, long to, Comparator comp) {
      for(long i = from; i < to - 1L; ++i) {
         long m = i;

         for(long j = i + 1L; j < to; ++j) {
            if (comp.compare(BigArrays.get(a, j), BigArrays.get(a, m)) < 0) {
               m = j;
            }
         }

         if (m != i) {
            BigArrays.swap(a, i, m);
         }
      }

   }

   public static void quickSort(Object[][] x, long from, long to, Comparator comp) {
      long len = to - from;
      if (len < 7L) {
         selectionSort(x, from, to, comp);
      } else {
         long m = from + len / 2L;
         if (len > 7L) {
            long l = from;
            long n = to - 1L;
            if (len > 40L) {
               long s = len / 8L;
               l = med3(x, from, from + s, from + 2L * s, comp);
               m = med3(x, m - s, m, m + s, comp);
               n = med3(x, n - 2L * s, n - s, n, comp);
            }

            m = med3(x, l, m, n, comp);
         }

         K v = (K)BigArrays.get(x, m);
         long a = from;
         long b = from;
         long c = to - 1L;
         long d = c;

         while(true) {
            int comparison;
            while(b > c || (comparison = comp.compare(BigArrays.get(x, b), v)) > 0) {
               for(; c >= b && (comparison = comp.compare(BigArrays.get(x, c), v)) >= 0; --c) {
                  if (comparison == 0) {
                     BigArrays.swap(x, c, d--);
                  }
               }

               if (b > c) {
                  long s = Math.min(a - from, b - a);
                  swap(x, from, b - s, s);
                  s = Math.min(d - c, to - d - 1L);
                  swap(x, b, to - s, s);
                  if ((s = b - a) > 1L) {
                     quickSort(x, from, from + s, comp);
                  }

                  if ((s = d - c) > 1L) {
                     quickSort(x, to - s, to, comp);
                  }

                  return;
               }

               BigArrays.swap(x, b++, c--);
            }

            if (comparison == 0) {
               BigArrays.swap(x, a++, b);
            }

            ++b;
         }
      }
   }

   private static long med3(Object[][] x, long a, long b, long c) {
      int ab = ((Comparable)BigArrays.get(x, a)).compareTo(BigArrays.get(x, b));
      int ac = ((Comparable)BigArrays.get(x, a)).compareTo(BigArrays.get(x, c));
      int bc = ((Comparable)BigArrays.get(x, b)).compareTo(BigArrays.get(x, c));
      return ab < 0 ? (bc < 0 ? b : (ac < 0 ? c : a)) : (bc > 0 ? b : (ac > 0 ? c : a));
   }

   private static void selectionSort(Object[][] a, long from, long to) {
      for(long i = from; i < to - 1L; ++i) {
         long m = i;

         for(long j = i + 1L; j < to; ++j) {
            if (((Comparable)BigArrays.get(a, j)).compareTo(BigArrays.get(a, m)) < 0) {
               m = j;
            }
         }

         if (m != i) {
            BigArrays.swap(a, i, m);
         }
      }

   }

   public static void quickSort(Object[][] x, Comparator comp) {
      quickSort(x, 0L, BigArrays.length(x), comp);
   }

   public static void quickSort(Object[][] x, long from, long to) {
      long len = to - from;
      if (len < 7L) {
         selectionSort(x, from, to);
      } else {
         long m = from + len / 2L;
         if (len > 7L) {
            long l = from;
            long n = to - 1L;
            if (len > 40L) {
               long s = len / 8L;
               l = med3(x, from, from + s, from + 2L * s);
               m = med3(x, m - s, m, m + s);
               n = med3(x, n - 2L * s, n - s, n);
            }

            m = med3(x, l, m, n);
         }

         K v = (K)BigArrays.get(x, m);
         long a = from;
         long b = from;
         long c = to - 1L;
         long d = c;

         while(true) {
            int comparison;
            while(b > c || (comparison = ((Comparable)BigArrays.get(x, b)).compareTo(v)) > 0) {
               for(; c >= b && (comparison = ((Comparable)BigArrays.get(x, c)).compareTo(v)) >= 0; --c) {
                  if (comparison == 0) {
                     BigArrays.swap(x, c, d--);
                  }
               }

               if (b > c) {
                  long s = Math.min(a - from, b - a);
                  swap(x, from, b - s, s);
                  s = Math.min(d - c, to - d - 1L);
                  swap(x, b, to - s, s);
                  if ((s = b - a) > 1L) {
                     quickSort(x, from, from + s);
                  }

                  if ((s = d - c) > 1L) {
                     quickSort(x, to - s, to);
                  }

                  return;
               }

               BigArrays.swap(x, b++, c--);
            }

            if (comparison == 0) {
               BigArrays.swap(x, a++, b);
            }

            ++b;
         }
      }
   }

   public static void quickSort(Object[][] x) {
      quickSort(x, 0L, BigArrays.length(x));
   }

   public static void parallelQuickSort(Object[][] x, long from, long to) {
      ForkJoinPool pool = getPool();
      if (to - from >= 8192L && pool.getParallelism() != 1) {
         pool.invoke(new ForkJoinQuickSort(x, from, to));
      } else {
         quickSort(x, from, to);
      }

   }

   public static void parallelQuickSort(Object[][] x) {
      parallelQuickSort(x, 0L, BigArrays.length(x));
   }

   public static void parallelQuickSort(Object[][] x, long from, long to, Comparator comp) {
      ForkJoinPool pool = getPool();
      if (to - from >= 8192L && pool.getParallelism() != 1) {
         pool.invoke(new ForkJoinQuickSortComp(x, from, to, comp));
      } else {
         quickSort(x, from, to, comp);
      }

   }

   public static void parallelQuickSort(Object[][] x, Comparator comp) {
      parallelQuickSort(x, 0L, BigArrays.length(x), comp);
   }

   public static long binarySearch(Object[][] a, long from, long to, Object key) {
      --to;

      while(from <= to) {
         long mid = from + to >>> 1;
         K midVal = (K)BigArrays.get(a, mid);
         int cmp = ((Comparable)midVal).compareTo(key);
         if (cmp < 0) {
            from = mid + 1L;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            to = mid - 1L;
         }
      }

      return -(from + 1L);
   }

   public static long binarySearch(Object[][] a, Object key) {
      return binarySearch(a, 0L, BigArrays.length(a), key);
   }

   public static long binarySearch(Object[][] a, long from, long to, Object key, Comparator c) {
      --to;

      while(from <= to) {
         long mid = from + to >>> 1;
         K midVal = (K)BigArrays.get(a, mid);
         int cmp = c.compare(midVal, key);
         if (cmp < 0) {
            from = mid + 1L;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            to = mid - 1L;
         }
      }

      return -(from + 1L);
   }

   public static long binarySearch(Object[][] a, Object key, Comparator c) {
      return binarySearch(a, 0L, BigArrays.length(a), key, c);
   }

   public static Object[][] shuffle(Object[][] a, long from, long to, Random random) {
      return BigArrays.shuffle(a, from, to, random);
   }

   public static Object[][] shuffle(Object[][] a, Random random) {
      return BigArrays.shuffle(a, random);
   }

   private static final class BigArrayHashStrategy implements Hash.Strategy, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;

      private BigArrayHashStrategy() {
      }

      public int hashCode(Object[][] o) {
         return Arrays.deepHashCode(o);
      }

      public boolean equals(Object[][] a, Object[][] b) {
         return ObjectBigArrays.equals(a, b);
      }
   }

   protected static class ForkJoinQuickSort extends RecursiveAction {
      private static final long serialVersionUID = 1L;
      private final long from;
      private final long to;
      private final Object[][] x;

      public ForkJoinQuickSort(Object[][] x, long from, long to) {
         this.from = from;
         this.to = to;
         this.x = x;
      }

      protected void compute() {
         K[][] x = (K[][])this.x;
         long len = this.to - this.from;
         if (len < 8192L) {
            ObjectBigArrays.quickSort(x, this.from, this.to);
         } else {
            long m = this.from + len / 2L;
            long l = this.from;
            long n = this.to - 1L;
            long s = len / 8L;
            l = ObjectBigArrays.med3(x, l, l + s, l + 2L * s);
            m = ObjectBigArrays.med3(x, m - s, m, m + s);
            n = ObjectBigArrays.med3(x, n - 2L * s, n - s, n);
            m = ObjectBigArrays.med3(x, l, m, n);
            K v = (K)BigArrays.get(x, m);
            long a = this.from;
            long b = a;
            long c = this.to - 1L;
            long d = c;

            while(true) {
               int comparison;
               while(b > c || (comparison = ((Comparable)BigArrays.get(x, b)).compareTo(v)) > 0) {
                  for(; c >= b && (comparison = ((Comparable)BigArrays.get(x, c)).compareTo(v)) >= 0; --c) {
                     if (comparison == 0) {
                        BigArrays.swap(x, c, d--);
                     }
                  }

                  if (b > c) {
                     s = Math.min(a - this.from, b - a);
                     ObjectBigArrays.swap(x, this.from, b - s, s);
                     s = Math.min(d - c, this.to - d - 1L);
                     ObjectBigArrays.swap(x, b, this.to - s, s);
                     s = b - a;
                     long t = d - c;
                     if (s > 1L && t > 1L) {
                        invokeAll(new ForkJoinQuickSort(x, this.from, this.from + s), new ForkJoinQuickSort(x, this.to - t, this.to));
                     } else if (s > 1L) {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSort(x, this.from, this.from + s)});
                     } else {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSort(x, this.to - t, this.to)});
                     }

                     return;
                  }

                  BigArrays.swap(x, b++, c--);
               }

               if (comparison == 0) {
                  BigArrays.swap(x, a++, b);
               }

               ++b;
            }
         }
      }
   }

   protected static class ForkJoinQuickSortComp extends RecursiveAction {
      private static final long serialVersionUID = 1L;
      private final long from;
      private final long to;
      private final Object[][] x;
      private final Comparator comp;

      public ForkJoinQuickSortComp(Object[][] x, long from, long to, Comparator comp) {
         this.from = from;
         this.to = to;
         this.x = x;
         this.comp = comp;
      }

      protected void compute() {
         K[][] x = (K[][])this.x;
         long len = this.to - this.from;
         if (len < 8192L) {
            ObjectBigArrays.quickSort(x, this.from, this.to, this.comp);
         } else {
            long m = this.from + len / 2L;
            long l = this.from;
            long n = this.to - 1L;
            long s = len / 8L;
            l = ObjectBigArrays.med3(x, l, l + s, l + 2L * s, this.comp);
            m = ObjectBigArrays.med3(x, m - s, m, m + s, this.comp);
            n = ObjectBigArrays.med3(x, n - 2L * s, n - s, n, this.comp);
            m = ObjectBigArrays.med3(x, l, m, n, this.comp);
            K v = (K)BigArrays.get(x, m);
            long a = this.from;
            long b = a;
            long c = this.to - 1L;
            long d = c;

            while(true) {
               int comparison;
               while(b > c || (comparison = this.comp.compare(BigArrays.get(x, b), v)) > 0) {
                  for(; c >= b && (comparison = this.comp.compare(BigArrays.get(x, c), v)) >= 0; --c) {
                     if (comparison == 0) {
                        BigArrays.swap(x, c, d--);
                     }
                  }

                  if (b > c) {
                     s = Math.min(a - this.from, b - a);
                     ObjectBigArrays.swap(x, this.from, b - s, s);
                     s = Math.min(d - c, this.to - d - 1L);
                     ObjectBigArrays.swap(x, b, this.to - s, s);
                     s = b - a;
                     long t = d - c;
                     if (s > 1L && t > 1L) {
                        invokeAll(new ForkJoinQuickSortComp(x, this.from, this.from + s, this.comp), new ForkJoinQuickSortComp(x, this.to - t, this.to, this.comp));
                     } else if (s > 1L) {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSortComp(x, this.from, this.from + s, this.comp)});
                     } else {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSortComp(x, this.to - t, this.to, this.comp)});
                     }

                     return;
                  }

                  BigArrays.swap(x, b++, c--);
               }

               if (comparison == 0) {
                  BigArrays.swap(x, a++, b);
               }

               ++b;
            }
         }
      }
   }
}
