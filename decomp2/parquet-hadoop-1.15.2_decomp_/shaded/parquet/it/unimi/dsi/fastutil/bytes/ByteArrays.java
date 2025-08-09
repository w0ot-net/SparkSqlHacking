package shaded.parquet.it.unimi.dsi.fastutil.bytes;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import shaded.parquet.it.unimi.dsi.fastutil.Arrays;
import shaded.parquet.it.unimi.dsi.fastutil.Hash;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntArrays;

public final class ByteArrays {
   public static final byte[] EMPTY_ARRAY = new byte[0];
   public static final byte[] DEFAULT_EMPTY_ARRAY = new byte[0];
   private static final int QUICKSORT_NO_REC = 16;
   private static final int PARALLEL_QUICKSORT_NO_FORK = 8192;
   private static final int QUICKSORT_MEDIAN_OF_9 = 128;
   private static final int MERGESORT_NO_REC = 16;
   private static final int DIGIT_BITS = 8;
   private static final int DIGIT_MASK = 255;
   private static final int DIGITS_PER_ELEMENT = 1;
   private static final int RADIXSORT_NO_REC = 1024;
   private static final int RADIXSORT_NO_REC_SMALL = 64;
   private static final int PARALLEL_RADIXSORT_NO_FORK = 1024;
   static final int RADIX_SORT_MIN_THRESHOLD = 1000;
   protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
   public static final Hash.Strategy HASH_STRATEGY = new ArrayHashStrategy();

   private ByteArrays() {
   }

   public static byte[] forceCapacity(byte[] array, int length, int preserve) {
      byte[] t = new byte[length];
      System.arraycopy(array, 0, t, 0, preserve);
      return t;
   }

   public static byte[] ensureCapacity(byte[] array, int length) {
      return ensureCapacity(array, length, array.length);
   }

   public static byte[] ensureCapacity(byte[] array, int length, int preserve) {
      return length > array.length ? forceCapacity(array, length, preserve) : array;
   }

   public static byte[] grow(byte[] array, int length) {
      return grow(array, length, array.length);
   }

   public static byte[] grow(byte[] array, int length, int preserve) {
      if (length > array.length) {
         int newLength = (int)Math.max(Math.min((long)array.length + (long)(array.length >> 1), 2147483639L), (long)length);
         byte[] t = new byte[newLength];
         System.arraycopy(array, 0, t, 0, preserve);
         return t;
      } else {
         return array;
      }
   }

   public static byte[] trim(byte[] array, int length) {
      if (length >= array.length) {
         return array;
      } else {
         byte[] t = length == 0 ? EMPTY_ARRAY : new byte[length];
         System.arraycopy(array, 0, t, 0, length);
         return t;
      }
   }

   public static byte[] setLength(byte[] array, int length) {
      if (length == array.length) {
         return array;
      } else {
         return length < array.length ? trim(array, length) : ensureCapacity(array, length);
      }
   }

   public static byte[] copy(byte[] array, int offset, int length) {
      ensureOffsetLength(array, offset, length);
      byte[] a = length == 0 ? EMPTY_ARRAY : new byte[length];
      System.arraycopy(array, offset, a, 0, length);
      return a;
   }

   public static byte[] copy(byte[] array) {
      return (byte[])(([B)array).clone();
   }

   /** @deprecated */
   @Deprecated
   public static void fill(byte[] array, byte value) {
      for(int i = array.length; i-- != 0; array[i] = value) {
      }

   }

   /** @deprecated */
   @Deprecated
   public static void fill(byte[] array, int from, int to, byte value) {
      ensureFromTo(array, from, to);
      if (from == 0) {
         while(to-- != 0) {
            array[to] = value;
         }
      } else {
         for(int i = from; i < to; ++i) {
            array[i] = value;
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public static boolean equals(byte[] a1, byte[] a2) {
      int i = a1.length;
      if (i != a2.length) {
         return false;
      } else {
         while(i-- != 0) {
            if (a1[i] != a2[i]) {
               return false;
            }
         }

         return true;
      }
   }

   public static void ensureFromTo(byte[] a, int from, int to) {
      Arrays.ensureFromTo(a.length, from, to);
   }

   public static void ensureOffsetLength(byte[] a, int offset, int length) {
      Arrays.ensureOffsetLength(a.length, offset, length);
   }

   public static void ensureSameLength(byte[] a, byte[] b) {
      if (a.length != b.length) {
         throw new IllegalArgumentException("Array size mismatch: " + a.length + " != " + b.length);
      }
   }

   private static ForkJoinPool getPool() {
      ForkJoinPool current = ForkJoinTask.getPool();
      return current == null ? ForkJoinPool.commonPool() : current;
   }

   public static void swap(byte[] x, int a, int b) {
      byte t = x[a];
      x[a] = x[b];
      x[b] = t;
   }

   public static void swap(byte[] x, int a, int b, int n) {
      for(int i = 0; i < n; ++b) {
         swap(x, a, b);
         ++i;
         ++a;
      }

   }

   private static int med3(byte[] x, int a, int b, int c, ByteComparator comp) {
      int ab = comp.compare(x[a], x[b]);
      int ac = comp.compare(x[a], x[c]);
      int bc = comp.compare(x[b], x[c]);
      return ab < 0 ? (bc < 0 ? b : (ac < 0 ? c : a)) : (bc > 0 ? b : (ac > 0 ? c : a));
   }

   private static void selectionSort(byte[] a, int from, int to, ByteComparator comp) {
      for(int i = from; i < to - 1; ++i) {
         int m = i;

         for(int j = i + 1; j < to; ++j) {
            if (comp.compare(a[j], a[m]) < 0) {
               m = j;
            }
         }

         if (m != i) {
            byte u = a[i];
            a[i] = a[m];
            a[m] = u;
         }
      }

   }

   private static void insertionSort(byte[] a, int from, int to, ByteComparator comp) {
      int i = from;

      while(true) {
         ++i;
         if (i >= to) {
            return;
         }

         byte t = a[i];
         int j = i;

         for(byte u = a[i - 1]; comp.compare(t, u) < 0; u = a[j - 1]) {
            a[j] = u;
            if (from == j - 1) {
               --j;
               break;
            }

            --j;
         }

         a[j] = t;
      }
   }

   public static void quickSort(byte[] x, int from, int to, ByteComparator comp) {
      int len = to - from;
      if (len < 16) {
         selectionSort(x, from, to, comp);
      } else {
         int m = from + len / 2;
         int l = from;
         int n = to - 1;
         if (len > 128) {
            int s = len / 8;
            l = med3(x, from, from + s, from + 2 * s, comp);
            m = med3(x, m - s, m, m + s, comp);
            n = med3(x, n - 2 * s, n - s, n, comp);
         }

         m = med3(x, l, m, n, comp);
         byte v = x[m];
         int a = from;
         int b = from;
         int c = to - 1;
         int d = c;

         while(true) {
            int comparison;
            while(b > c || (comparison = comp.compare(x[b], v)) > 0) {
               for(; c >= b && (comparison = comp.compare(x[c], v)) >= 0; --c) {
                  if (comparison == 0) {
                     swap(x, c, d--);
                  }
               }

               if (b > c) {
                  comparison = Math.min(a - from, b - a);
                  swap(x, from, b - comparison, comparison);
                  comparison = Math.min(d - c, to - d - 1);
                  swap(x, b, to - comparison, comparison);
                  if ((comparison = b - a) > 1) {
                     quickSort(x, from, from + comparison, comp);
                  }

                  if ((comparison = d - c) > 1) {
                     quickSort(x, to - comparison, to, comp);
                  }

                  return;
               }

               swap(x, b++, c--);
            }

            if (comparison == 0) {
               swap(x, a++, b);
            }

            ++b;
         }
      }
   }

   public static void quickSort(byte[] x, ByteComparator comp) {
      quickSort(x, 0, x.length, comp);
   }

   public static void parallelQuickSort(byte[] x, int from, int to, ByteComparator comp) {
      ForkJoinPool pool = getPool();
      if (to - from >= 8192 && pool.getParallelism() != 1) {
         pool.invoke(new ForkJoinQuickSortComp(x, from, to, comp));
      } else {
         quickSort(x, from, to, comp);
      }

   }

   public static void parallelQuickSort(byte[] x, ByteComparator comp) {
      parallelQuickSort(x, 0, x.length, comp);
   }

   private static int med3(byte[] x, int a, int b, int c) {
      int ab = Byte.compare(x[a], x[b]);
      int ac = Byte.compare(x[a], x[c]);
      int bc = Byte.compare(x[b], x[c]);
      return ab < 0 ? (bc < 0 ? b : (ac < 0 ? c : a)) : (bc > 0 ? b : (ac > 0 ? c : a));
   }

   private static void selectionSort(byte[] a, int from, int to) {
      for(int i = from; i < to - 1; ++i) {
         int m = i;

         for(int j = i + 1; j < to; ++j) {
            if (a[j] < a[m]) {
               m = j;
            }
         }

         if (m != i) {
            byte u = a[i];
            a[i] = a[m];
            a[m] = u;
         }
      }

   }

   private static void insertionSort(byte[] a, int from, int to) {
      int i = from;

      while(true) {
         ++i;
         if (i >= to) {
            return;
         }

         byte t = a[i];
         int j = i;

         for(byte u = a[i - 1]; t < u; u = a[j - 1]) {
            a[j] = u;
            if (from == j - 1) {
               --j;
               break;
            }

            --j;
         }

         a[j] = t;
      }
   }

   public static void quickSort(byte[] x, int from, int to) {
      int len = to - from;
      if (len < 16) {
         selectionSort(x, from, to);
      } else {
         int m = from + len / 2;
         int l = from;
         int n = to - 1;
         if (len > 128) {
            int s = len / 8;
            l = med3(x, from, from + s, from + 2 * s);
            m = med3(x, m - s, m, m + s);
            n = med3(x, n - 2 * s, n - s, n);
         }

         m = med3(x, l, m, n);
         byte v = x[m];
         int a = from;
         int b = from;
         int c = to - 1;
         int d = c;

         while(true) {
            int comparison;
            while(b > c || (comparison = Byte.compare(x[b], v)) > 0) {
               for(; c >= b && (comparison = Byte.compare(x[c], v)) >= 0; --c) {
                  if (comparison == 0) {
                     swap(x, c, d--);
                  }
               }

               if (b > c) {
                  comparison = Math.min(a - from, b - a);
                  swap(x, from, b - comparison, comparison);
                  comparison = Math.min(d - c, to - d - 1);
                  swap(x, b, to - comparison, comparison);
                  if ((comparison = b - a) > 1) {
                     quickSort(x, from, from + comparison);
                  }

                  if ((comparison = d - c) > 1) {
                     quickSort(x, to - comparison, to);
                  }

                  return;
               }

               swap(x, b++, c--);
            }

            if (comparison == 0) {
               swap(x, a++, b);
            }

            ++b;
         }
      }
   }

   public static void quickSort(byte[] x) {
      quickSort(x, 0, x.length);
   }

   public static void parallelQuickSort(byte[] x, int from, int to) {
      ForkJoinPool pool = getPool();
      if (to - from >= 8192 && pool.getParallelism() != 1) {
         pool.invoke(new ForkJoinQuickSort(x, from, to));
      } else {
         quickSort(x, from, to);
      }

   }

   public static void parallelQuickSort(byte[] x) {
      parallelQuickSort(x, 0, x.length);
   }

   private static int med3Indirect(int[] perm, byte[] x, int a, int b, int c) {
      byte aa = x[perm[a]];
      byte bb = x[perm[b]];
      byte cc = x[perm[c]];
      int ab = Byte.compare(aa, bb);
      int ac = Byte.compare(aa, cc);
      int bc = Byte.compare(bb, cc);
      return ab < 0 ? (bc < 0 ? b : (ac < 0 ? c : a)) : (bc > 0 ? b : (ac > 0 ? c : a));
   }

   private static void insertionSortIndirect(int[] perm, byte[] a, int from, int to) {
      int i = from;

      while(true) {
         ++i;
         if (i >= to) {
            return;
         }

         int t = perm[i];
         int j = i;

         for(int u = perm[i - 1]; a[t] < a[u]; u = perm[j - 1]) {
            perm[j] = u;
            if (from == j - 1) {
               --j;
               break;
            }

            --j;
         }

         perm[j] = t;
      }
   }

   public static void quickSortIndirect(int[] perm, byte[] x, int from, int to) {
      int len = to - from;
      if (len < 16) {
         insertionSortIndirect(perm, x, from, to);
      } else {
         int m = from + len / 2;
         int l = from;
         int n = to - 1;
         if (len > 128) {
            int s = len / 8;
            l = med3Indirect(perm, x, from, from + s, from + 2 * s);
            m = med3Indirect(perm, x, m - s, m, m + s);
            n = med3Indirect(perm, x, n - 2 * s, n - s, n);
         }

         m = med3Indirect(perm, x, l, m, n);
         byte v = x[perm[m]];
         int a = from;
         int b = from;
         int c = to - 1;
         int d = c;

         while(true) {
            int comparison;
            while(b > c || (comparison = Byte.compare(x[perm[b]], v)) > 0) {
               for(; c >= b && (comparison = Byte.compare(x[perm[c]], v)) >= 0; --c) {
                  if (comparison == 0) {
                     IntArrays.swap(perm, c, d--);
                  }
               }

               if (b > c) {
                  comparison = Math.min(a - from, b - a);
                  IntArrays.swap(perm, from, b - comparison, comparison);
                  comparison = Math.min(d - c, to - d - 1);
                  IntArrays.swap(perm, b, to - comparison, comparison);
                  if ((comparison = b - a) > 1) {
                     quickSortIndirect(perm, x, from, from + comparison);
                  }

                  if ((comparison = d - c) > 1) {
                     quickSortIndirect(perm, x, to - comparison, to);
                  }

                  return;
               }

               IntArrays.swap(perm, b++, c--);
            }

            if (comparison == 0) {
               IntArrays.swap(perm, a++, b);
            }

            ++b;
         }
      }
   }

   public static void quickSortIndirect(int[] perm, byte[] x) {
      quickSortIndirect(perm, x, 0, x.length);
   }

   public static void parallelQuickSortIndirect(int[] perm, byte[] x, int from, int to) {
      ForkJoinPool pool = getPool();
      if (to - from >= 8192 && pool.getParallelism() != 1) {
         pool.invoke(new ForkJoinQuickSortIndirect(perm, x, from, to));
      } else {
         quickSortIndirect(perm, x, from, to);
      }

   }

   public static void parallelQuickSortIndirect(int[] perm, byte[] x) {
      parallelQuickSortIndirect(perm, x, 0, x.length);
   }

   public static void stabilize(int[] perm, byte[] x, int from, int to) {
      int curr = from;

      for(int i = from + 1; i < to; ++i) {
         if (x[perm[i]] != x[perm[curr]]) {
            if (i - curr > 1) {
               IntArrays.parallelQuickSort(perm, curr, i);
            }

            curr = i;
         }
      }

      if (to - curr > 1) {
         IntArrays.parallelQuickSort(perm, curr, to);
      }

   }

   public static void stabilize(int[] perm, byte[] x) {
      stabilize(perm, x, 0, perm.length);
   }

   private static int med3(byte[] x, byte[] y, int a, int b, int c) {
      int t;
      int ab = (t = Byte.compare(x[a], x[b])) == 0 ? Byte.compare(y[a], y[b]) : t;
      int ac = (t = Byte.compare(x[a], x[c])) == 0 ? Byte.compare(y[a], y[c]) : t;
      int bc = (t = Byte.compare(x[b], x[c])) == 0 ? Byte.compare(y[b], y[c]) : t;
      return ab < 0 ? (bc < 0 ? b : (ac < 0 ? c : a)) : (bc > 0 ? b : (ac > 0 ? c : a));
   }

   private static void swap(byte[] x, byte[] y, int a, int b) {
      byte t = x[a];
      byte u = y[a];
      x[a] = x[b];
      y[a] = y[b];
      x[b] = t;
      y[b] = u;
   }

   private static void swap(byte[] x, byte[] y, int a, int b, int n) {
      for(int i = 0; i < n; ++b) {
         swap(x, y, a, b);
         ++i;
         ++a;
      }

   }

   private static void selectionSort(byte[] a, byte[] b, int from, int to) {
      for(int i = from; i < to - 1; ++i) {
         int m = i;

         for(int j = i + 1; j < to; ++j) {
            int u;
            if ((u = Byte.compare(a[j], a[m])) < 0 || u == 0 && b[j] < b[m]) {
               m = j;
            }
         }

         if (m != i) {
            byte t = a[i];
            a[i] = a[m];
            a[m] = t;
            t = b[i];
            b[i] = b[m];
            b[m] = t;
         }
      }

   }

   public static void quickSort(byte[] x, byte[] y, int from, int to) {
      int len = to - from;
      if (len < 16) {
         selectionSort(x, y, from, to);
      } else {
         int m = from + len / 2;
         int l = from;
         int n = to - 1;
         if (len > 128) {
            int s = len / 8;
            l = med3(x, y, from, from + s, from + 2 * s);
            m = med3(x, y, m - s, m, m + s);
            n = med3(x, y, n - 2 * s, n - s, n);
         }

         m = med3(x, y, l, m, n);
         byte v = x[m];
         byte w = y[m];
         int a = from;
         int b = from;
         int c = to - 1;
         int d = c;

         while(true) {
            int comparison;
            int t;
            while(b > c || (comparison = (t = Byte.compare(x[b], v)) == 0 ? Byte.compare(y[b], w) : t) > 0) {
               for(; c >= b && (comparison = (t = Byte.compare(x[c], v)) == 0 ? Byte.compare(y[c], w) : t) >= 0; --c) {
                  if (comparison == 0) {
                     swap(x, y, c, d--);
                  }
               }

               if (b > c) {
                  comparison = Math.min(a - from, b - a);
                  swap(x, y, from, b - comparison, comparison);
                  comparison = Math.min(d - c, to - d - 1);
                  swap(x, y, b, to - comparison, comparison);
                  if ((comparison = b - a) > 1) {
                     quickSort(x, y, from, from + comparison);
                  }

                  if ((comparison = d - c) > 1) {
                     quickSort(x, y, to - comparison, to);
                  }

                  return;
               }

               swap(x, y, b++, c--);
            }

            if (comparison == 0) {
               swap(x, y, a++, b);
            }

            ++b;
         }
      }
   }

   public static void quickSort(byte[] x, byte[] y) {
      ensureSameLength(x, y);
      quickSort(x, y, 0, x.length);
   }

   public static void parallelQuickSort(byte[] x, byte[] y, int from, int to) {
      ForkJoinPool pool = getPool();
      if (to - from >= 8192 && pool.getParallelism() != 1) {
         pool.invoke(new ForkJoinQuickSort2(x, y, from, to));
      } else {
         quickSort(x, y, from, to);
      }

   }

   public static void parallelQuickSort(byte[] x, byte[] y) {
      ensureSameLength(x, y);
      parallelQuickSort(x, y, 0, x.length);
   }

   public static void unstableSort(byte[] a, int from, int to) {
      java.util.Arrays.sort(a, from, to);
   }

   public static void unstableSort(byte[] a) {
      unstableSort(a, 0, a.length);
   }

   public static void unstableSort(byte[] a, int from, int to, ByteComparator comp) {
      quickSort(a, from, to, comp);
   }

   public static void unstableSort(byte[] a, ByteComparator comp) {
      unstableSort(a, 0, a.length, comp);
   }

   public static void mergeSort(byte[] a, int from, int to, byte[] supp) {
      int len = to - from;
      if (len < 16) {
         insertionSort(a, from, to);
      } else {
         if (supp == null) {
            supp = java.util.Arrays.copyOf(a, to);
         }

         int mid = from + to >>> 1;
         mergeSort(supp, from, mid, a);
         mergeSort(supp, mid, to, a);
         if (supp[mid - 1] <= supp[mid]) {
            System.arraycopy(supp, from, a, from, len);
         } else {
            int i = from;
            int p = from;

            for(int q = mid; i < to; ++i) {
               if (q < to && (p >= mid || supp[p] > supp[q])) {
                  a[i] = supp[q++];
               } else {
                  a[i] = supp[p++];
               }
            }

         }
      }
   }

   public static void mergeSort(byte[] a, int from, int to) {
      mergeSort(a, from, to, (byte[])null);
   }

   public static void mergeSort(byte[] a) {
      mergeSort(a, 0, a.length);
   }

   public static void mergeSort(byte[] a, int from, int to, ByteComparator comp, byte[] supp) {
      int len = to - from;
      if (len < 16) {
         insertionSort(a, from, to, comp);
      } else {
         if (supp == null) {
            supp = java.util.Arrays.copyOf(a, to);
         }

         int mid = from + to >>> 1;
         mergeSort(supp, from, mid, comp, a);
         mergeSort(supp, mid, to, comp, a);
         if (comp.compare(supp[mid - 1], supp[mid]) <= 0) {
            System.arraycopy(supp, from, a, from, len);
         } else {
            int i = from;
            int p = from;

            for(int q = mid; i < to; ++i) {
               if (q < to && (p >= mid || comp.compare(supp[p], supp[q]) > 0)) {
                  a[i] = supp[q++];
               } else {
                  a[i] = supp[p++];
               }
            }

         }
      }
   }

   public static void mergeSort(byte[] a, int from, int to, ByteComparator comp) {
      mergeSort(a, from, to, comp, (byte[])null);
   }

   public static void mergeSort(byte[] a, ByteComparator comp) {
      mergeSort(a, 0, a.length, (ByteComparator)comp);
   }

   public static void stableSort(byte[] a, int from, int to) {
      unstableSort(a, from, to);
   }

   public static void stableSort(byte[] a) {
      stableSort(a, 0, a.length);
   }

   public static void stableSort(byte[] a, int from, int to, ByteComparator comp) {
      mergeSort(a, from, to, comp);
   }

   public static void stableSort(byte[] a, ByteComparator comp) {
      stableSort(a, 0, a.length, comp);
   }

   public static int binarySearch(byte[] a, int from, int to, byte key) {
      --to;

      while(from <= to) {
         int mid = from + to >>> 1;
         byte midVal = a[mid];
         if (midVal < key) {
            from = mid + 1;
         } else {
            if (midVal <= key) {
               return mid;
            }

            to = mid - 1;
         }
      }

      return -(from + 1);
   }

   public static int binarySearch(byte[] a, byte key) {
      return binarySearch(a, 0, a.length, key);
   }

   public static int binarySearch(byte[] a, int from, int to, byte key, ByteComparator c) {
      --to;

      while(from <= to) {
         int mid = from + to >>> 1;
         byte midVal = a[mid];
         int cmp = c.compare(midVal, key);
         if (cmp < 0) {
            from = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            to = mid - 1;
         }
      }

      return -(from + 1);
   }

   public static int binarySearch(byte[] a, byte key, ByteComparator c) {
      return binarySearch(a, 0, a.length, key, c);
   }

   public static void radixSort(byte[] a) {
      radixSort((byte[])a, 0, a.length);
   }

   public static void radixSort(byte[] a, int from, int to) {
      if (to - from < 1024) {
         quickSort(a, from, to);
      } else {
         int maxLevel = 0;
         int stackSize = 1;
         int stackPos = 0;
         int[] offsetStack = new int[1];
         int[] lengthStack = new int[1];
         int[] levelStack = new int[1];
         offsetStack[stackPos] = from;
         lengthStack[stackPos] = to - from;
         levelStack[stackPos++] = 0;
         int[] count = new int[256];
         int[] pos = new int[256];

         while(stackPos > 0) {
            --stackPos;
            int first = offsetStack[stackPos];
            int length = lengthStack[stackPos];
            int level = levelStack[stackPos];
            int signMask = level % 1 == 0 ? 128 : 0;
            int shift = (0 - level % 1) * 8;

            for(int i = first + length; i-- != first; ++count[a[i] >>> shift & 255 ^ signMask]) {
            }

            int lastUsed = -1;
            int i = 0;

            for(int p = first; i < 256; ++i) {
               if (count[i] != 0) {
                  lastUsed = i;
               }

               pos[i] = p += count[i];
            }

            i = first + length - count[lastUsed];
            int i = first;

            int c;
            for(c = -1; i <= i; count[c] = 0) {
               byte t = a[i];
               c = t >>> shift & 255 ^ signMask;
               if (i < i) {
                  int d;
                  while((d = --pos[c]) > i) {
                     byte z = t;
                     t = a[d];
                     a[d] = z;
                     c = t >>> shift & 255 ^ signMask;
                  }

                  a[i] = t;
               }

               if (level < 0 && count[c] > 1) {
                  if (count[c] < 1024) {
                     quickSort(a, i, i + count[c]);
                  } else {
                     offsetStack[stackPos] = i;
                     lengthStack[stackPos] = count[c];
                     levelStack[stackPos++] = level + 1;
                  }
               }

               i += count[c];
            }
         }

      }
   }

   public static void parallelRadixSort(byte[] a, int from, int to) {
      ForkJoinPool pool = getPool();
      if (to - from >= 1024 && pool.getParallelism() != 1) {
         int maxLevel = 0;
         LinkedBlockingQueue<Segment> queue = new LinkedBlockingQueue();
         queue.add(new Segment(from, to - from, 0));
         AtomicInteger queueSize = new AtomicInteger(1);
         int numberOfThreads = pool.getParallelism();
         ExecutorCompletionService<Void> executorCompletionService = new ExecutorCompletionService(pool);
         int j = numberOfThreads;

         while(j-- != 0) {
            executorCompletionService.submit(() -> {
               int[] count = new int[256];
               int[] pos = new int[256];

               while(true) {
                  if (queueSize.get() == 0) {
                     int i = numberOfThreads;

                     while(i-- != 0) {
                        queue.add(POISON_PILL);
                     }
                  }

                  Segment segment = (Segment)queue.take();
                  if (segment == POISON_PILL) {
                     return null;
                  }

                  int first = segment.offset;
                  int length = segment.length;
                  int level = segment.level;
                  int signMask = level % 1 == 0 ? 128 : 0;
                  int shift = (0 - level % 1) * 8;

                  for(int i = first + length; i-- != first; ++count[a[i] >>> shift & 255 ^ signMask]) {
                  }

                  int lastUsed = -1;
                  int i = 0;

                  for(int p = first; i < 256; ++i) {
                     if (count[i] != 0) {
                        lastUsed = i;
                     }

                     pos[i] = p += count[i];
                  }

                  i = first + length - count[lastUsed];
                  int i = first;

                  int c;
                  for(c = -1; i <= i; count[c] = 0) {
                     byte t = a[i];
                     c = t >>> shift & 255 ^ signMask;
                     if (i < i) {
                        int d;
                        while((d = --pos[c]) > i) {
                           byte z = t;
                           t = a[d];
                           a[d] = z;
                           c = t >>> shift & 255 ^ signMask;
                        }

                        a[i] = t;
                     }

                     if (level < 0 && count[c] > 1) {
                        if (count[c] < 1024) {
                           quickSort(a, i, i + count[c]);
                        } else {
                           queueSize.incrementAndGet();
                           queue.add(new Segment(i, count[c], level + 1));
                        }
                     }

                     i += count[c];
                  }

                  queueSize.decrementAndGet();
               }
            });
         }

         Throwable problem = null;
         int i = numberOfThreads;

         while(i-- != 0) {
            try {
               executorCompletionService.take().get();
            } catch (Exception e) {
               problem = e.getCause();
            }
         }

         if (problem != null) {
            throw problem instanceof RuntimeException ? (RuntimeException)problem : new RuntimeException(problem);
         }
      } else {
         quickSort(a, from, to);
      }
   }

   public static void parallelRadixSort(byte[] a) {
      parallelRadixSort(a, 0, a.length);
   }

   public static void radixSortIndirect(int[] perm, byte[] a, boolean stable) {
      radixSortIndirect(perm, a, 0, perm.length, stable);
   }

   public static void radixSortIndirect(int[] perm, byte[] a, int from, int to, boolean stable) {
      if (to - from < 1024) {
         quickSortIndirect(perm, a, from, to);
         if (stable) {
            stabilize(perm, a, from, to);
         }

      } else {
         int maxLevel = 0;
         int stackSize = 1;
         int stackPos = 0;
         int[] offsetStack = new int[1];
         int[] lengthStack = new int[1];
         int[] levelStack = new int[1];
         offsetStack[stackPos] = from;
         lengthStack[stackPos] = to - from;
         levelStack[stackPos++] = 0;
         int[] count = new int[256];
         int[] pos = new int[256];
         int[] support = stable ? new int[perm.length] : null;

         while(stackPos > 0) {
            --stackPos;
            int first = offsetStack[stackPos];
            int length = lengthStack[stackPos];
            int level = levelStack[stackPos];
            int signMask = level % 1 == 0 ? 128 : 0;
            int shift = (0 - level % 1) * 8;

            for(int i = first + length; i-- != first; ++count[a[perm[i]] >>> shift & 255 ^ signMask]) {
            }

            int lastUsed = -1;
            int i = 0;

            for(int p = stable ? 0 : first; i < 256; ++i) {
               if (count[i] != 0) {
                  lastUsed = i;
               }

               pos[i] = p += count[i];
            }

            if (stable) {
               for(int i = first + length; i-- != first; support[--pos[a[perm[i]] >>> shift & 255 ^ signMask]] = perm[i]) {
               }

               System.arraycopy(support, 0, perm, first, length);
               i = 0;

               for(int p = first; i <= lastUsed; ++i) {
                  if (level < 0 && count[i] > 1) {
                     if (count[i] < 1024) {
                        quickSortIndirect(perm, a, p, p + count[i]);
                        if (stable) {
                           stabilize(perm, a, p, p + count[i]);
                        }
                     } else {
                        offsetStack[stackPos] = p;
                        lengthStack[stackPos] = count[i];
                        levelStack[stackPos++] = level + 1;
                     }
                  }

                  p += count[i];
               }

               java.util.Arrays.fill(count, 0);
            } else {
               i = first + length - count[lastUsed];
               int i = first;

               int c;
               for(c = -1; i <= i; count[c] = 0) {
                  int t = perm[i];
                  c = a[t] >>> shift & 255 ^ signMask;
                  if (i < i) {
                     int d;
                     while((d = --pos[c]) > i) {
                        int z = t;
                        t = perm[d];
                        perm[d] = z;
                        c = a[t] >>> shift & 255 ^ signMask;
                     }

                     perm[i] = t;
                  }

                  if (level < 0 && count[c] > 1) {
                     if (count[c] < 1024) {
                        quickSortIndirect(perm, a, i, i + count[c]);
                        if (stable) {
                           stabilize(perm, a, i, i + count[c]);
                        }
                     } else {
                        offsetStack[stackPos] = i;
                        lengthStack[stackPos] = count[c];
                        levelStack[stackPos++] = level + 1;
                     }
                  }

                  i += count[c];
               }
            }
         }

      }
   }

   public static void parallelRadixSortIndirect(int[] perm, byte[] a, int from, int to, boolean stable) {
      ForkJoinPool pool = getPool();
      if (to - from >= 1024 && pool.getParallelism() != 1) {
         int maxLevel = 0;
         LinkedBlockingQueue<Segment> queue = new LinkedBlockingQueue();
         queue.add(new Segment(from, to - from, 0));
         AtomicInteger queueSize = new AtomicInteger(1);
         int numberOfThreads = pool.getParallelism();
         ExecutorCompletionService<Void> executorCompletionService = new ExecutorCompletionService(pool);
         int[] support = stable ? new int[perm.length] : null;
         int j = numberOfThreads;

         while(j-- != 0) {
            executorCompletionService.submit(() -> {
               int[] count = new int[256];
               int[] pos = new int[256];

               while(true) {
                  if (queueSize.get() == 0) {
                     int i = numberOfThreads;

                     while(i-- != 0) {
                        queue.add(POISON_PILL);
                     }
                  }

                  Segment segment = (Segment)queue.take();
                  if (segment == POISON_PILL) {
                     return null;
                  }

                  int first = segment.offset;
                  int length = segment.length;
                  int level = segment.level;
                  int signMask = level % 1 == 0 ? 128 : 0;
                  int shift = (0 - level % 1) * 8;

                  for(int i = first + length; i-- != first; ++count[a[perm[i]] >>> shift & 255 ^ signMask]) {
                  }

                  int lastUsed = -1;
                  int i = 0;

                  for(int p = first; i < 256; ++i) {
                     if (count[i] != 0) {
                        lastUsed = i;
                     }

                     pos[i] = p += count[i];
                  }

                  if (stable) {
                     for(int i = first + length; i-- != first; support[--pos[a[perm[i]] >>> shift & 255 ^ signMask]] = perm[i]) {
                     }

                     System.arraycopy(support, first, perm, first, length);
                     i = 0;

                     for(int p = first; i <= lastUsed; ++i) {
                        if (level < 0 && count[i] > 1) {
                           if (count[i] < 1024) {
                              radixSortIndirect(perm, a, p, p + count[i], stable);
                           } else {
                              queueSize.incrementAndGet();
                              queue.add(new Segment(p, count[i], level + 1));
                           }
                        }

                        p += count[i];
                     }

                     java.util.Arrays.fill(count, 0);
                  } else {
                     i = first + length - count[lastUsed];
                     int i = first;

                     int c;
                     for(c = -1; i <= i; count[c] = 0) {
                        int t = perm[i];
                        c = a[t] >>> shift & 255 ^ signMask;
                        if (i < i) {
                           int d;
                           while((d = --pos[c]) > i) {
                              int z = t;
                              t = perm[d];
                              perm[d] = z;
                              c = a[t] >>> shift & 255 ^ signMask;
                           }

                           perm[i] = t;
                        }

                        if (level < 0 && count[c] > 1) {
                           if (count[c] < 1024) {
                              radixSortIndirect(perm, a, i, i + count[c], stable);
                           } else {
                              queueSize.incrementAndGet();
                              queue.add(new Segment(i, count[c], level + 1));
                           }
                        }

                        i += count[c];
                     }
                  }

                  queueSize.decrementAndGet();
               }
            });
         }

         Throwable problem = null;
         int i = numberOfThreads;

         while(i-- != 0) {
            try {
               executorCompletionService.take().get();
            } catch (Exception e) {
               problem = e.getCause();
            }
         }

         if (problem != null) {
            throw problem instanceof RuntimeException ? (RuntimeException)problem : new RuntimeException(problem);
         }
      } else {
         radixSortIndirect(perm, a, from, to, stable);
      }
   }

   public static void parallelRadixSortIndirect(int[] perm, byte[] a, boolean stable) {
      parallelRadixSortIndirect(perm, a, 0, a.length, stable);
   }

   public static void radixSort(byte[] a, byte[] b) {
      ensureSameLength(a, b);
      radixSort(a, b, 0, a.length);
   }

   public static void radixSort(byte[] a, byte[] b, int from, int to) {
      if (to - from < 1024) {
         quickSort(a, b, from, to);
      } else {
         int layers = 2;
         int maxLevel = 1;
         int stackSize = 256;
         int stackPos = 0;
         int[] offsetStack = new int[256];
         int[] lengthStack = new int[256];
         int[] levelStack = new int[256];
         offsetStack[stackPos] = from;
         lengthStack[stackPos] = to - from;
         levelStack[stackPos++] = 0;
         int[] count = new int[256];
         int[] pos = new int[256];

         while(stackPos > 0) {
            --stackPos;
            int first = offsetStack[stackPos];
            int length = lengthStack[stackPos];
            int level = levelStack[stackPos];
            int signMask = level % 1 == 0 ? 128 : 0;
            byte[] k = level < 1 ? a : b;
            int shift = (0 - level % 1) * 8;

            for(int i = first + length; i-- != first; ++count[k[i] >>> shift & 255 ^ signMask]) {
            }

            int lastUsed = -1;
            int i = 0;

            for(int p = first; i < 256; ++i) {
               if (count[i] != 0) {
                  lastUsed = i;
               }

               pos[i] = p += count[i];
            }

            i = first + length - count[lastUsed];
            int i = first;

            int c;
            for(c = -1; i <= i; count[c] = 0) {
               byte t = a[i];
               byte u = b[i];
               c = k[i] >>> shift & 255 ^ signMask;
               if (i < i) {
                  int d;
                  while((d = --pos[c]) > i) {
                     c = k[d] >>> shift & 255 ^ signMask;
                     byte z = t;
                     t = a[d];
                     a[d] = z;
                     z = u;
                     u = b[d];
                     b[d] = z;
                  }

                  a[i] = t;
                  b[i] = u;
               }

               if (level < 1 && count[c] > 1) {
                  if (count[c] < 1024) {
                     quickSort(a, b, i, i + count[c]);
                  } else {
                     offsetStack[stackPos] = i;
                     lengthStack[stackPos] = count[c];
                     levelStack[stackPos++] = level + 1;
                  }
               }

               i += count[c];
            }
         }

      }
   }

   public static void parallelRadixSort(byte[] a, byte[] b, int from, int to) {
      ForkJoinPool pool = getPool();
      if (to - from >= 1024 && pool.getParallelism() != 1) {
         int layers = 2;
         if (a.length != b.length) {
            throw new IllegalArgumentException("Array size mismatch.");
         } else {
            int maxLevel = 1;
            LinkedBlockingQueue<Segment> queue = new LinkedBlockingQueue();
            queue.add(new Segment(from, to - from, 0));
            AtomicInteger queueSize = new AtomicInteger(1);
            int numberOfThreads = pool.getParallelism();
            ExecutorCompletionService<Void> executorCompletionService = new ExecutorCompletionService(pool);
            int j = numberOfThreads;

            while(j-- != 0) {
               executorCompletionService.submit(() -> {
                  int[] count = new int[256];
                  int[] pos = new int[256];

                  while(true) {
                     if (queueSize.get() == 0) {
                        int i = numberOfThreads;

                        while(i-- != 0) {
                           queue.add(POISON_PILL);
                        }
                     }

                     Segment segment = (Segment)queue.take();
                     if (segment == POISON_PILL) {
                        return null;
                     }

                     int first = segment.offset;
                     int length = segment.length;
                     int level = segment.level;
                     int signMask = level % 1 == 0 ? 128 : 0;
                     byte[] k = level < 1 ? a : b;
                     int shift = (0 - level % 1) * 8;

                     for(int i = first + length; i-- != first; ++count[k[i] >>> shift & 255 ^ signMask]) {
                     }

                     int lastUsed = -1;
                     int i = 0;

                     for(int p = first; i < 256; ++i) {
                        if (count[i] != 0) {
                           lastUsed = i;
                        }

                        pos[i] = p += count[i];
                     }

                     i = first + length - count[lastUsed];
                     int i = first;

                     int c;
                     for(c = -1; i <= i; count[c] = 0) {
                        byte t = a[i];
                        byte u = b[i];
                        c = k[i] >>> shift & 255 ^ signMask;
                        if (i < i) {
                           int d;
                           while((d = --pos[c]) > i) {
                              c = k[d] >>> shift & 255 ^ signMask;
                              byte z = t;
                              byte w = u;
                              t = a[d];
                              u = b[d];
                              a[d] = z;
                              b[d] = w;
                           }

                           a[i] = t;
                           b[i] = u;
                        }

                        if (level < 1 && count[c] > 1) {
                           if (count[c] < 1024) {
                              quickSort(a, b, i, i + count[c]);
                           } else {
                              queueSize.incrementAndGet();
                              queue.add(new Segment(i, count[c], level + 1));
                           }
                        }

                        i += count[c];
                     }

                     queueSize.decrementAndGet();
                  }
               });
            }

            Throwable problem = null;
            int i = numberOfThreads;

            while(i-- != 0) {
               try {
                  executorCompletionService.take().get();
               } catch (Exception e) {
                  problem = e.getCause();
               }
            }

            if (problem != null) {
               throw problem instanceof RuntimeException ? (RuntimeException)problem : new RuntimeException(problem);
            }
         }
      } else {
         quickSort(a, b, from, to);
      }
   }

   public static void parallelRadixSort(byte[] a, byte[] b) {
      ensureSameLength(a, b);
      parallelRadixSort(a, b, 0, a.length);
   }

   private static void insertionSortIndirect(int[] perm, byte[] a, byte[] b, int from, int to) {
      int i = from;

      while(true) {
         ++i;
         if (i >= to) {
            return;
         }

         int t = perm[i];
         int j = i;

         for(int u = perm[i - 1]; a[t] < a[u] || a[t] == a[u] && b[t] < b[u]; u = perm[j - 1]) {
            perm[j] = u;
            if (from == j - 1) {
               --j;
               break;
            }

            --j;
         }

         perm[j] = t;
      }
   }

   public static void radixSortIndirect(int[] perm, byte[] a, byte[] b, boolean stable) {
      ensureSameLength(a, b);
      radixSortIndirect(perm, a, b, 0, a.length, stable);
   }

   public static void radixSortIndirect(int[] perm, byte[] a, byte[] b, int from, int to, boolean stable) {
      if (to - from < 64) {
         insertionSortIndirect(perm, a, b, from, to);
      } else {
         int layers = 2;
         int maxLevel = 1;
         int stackSize = 256;
         int stackPos = 0;
         int[] offsetStack = new int[256];
         int[] lengthStack = new int[256];
         int[] levelStack = new int[256];
         offsetStack[stackPos] = from;
         lengthStack[stackPos] = to - from;
         levelStack[stackPos++] = 0;
         int[] count = new int[256];
         int[] pos = new int[256];
         int[] support = stable ? new int[perm.length] : null;

         while(stackPos > 0) {
            --stackPos;
            int first = offsetStack[stackPos];
            int length = lengthStack[stackPos];
            int level = levelStack[stackPos];
            int signMask = level % 1 == 0 ? 128 : 0;
            byte[] k = level < 1 ? a : b;
            int shift = (0 - level % 1) * 8;

            for(int i = first + length; i-- != first; ++count[k[perm[i]] >>> shift & 255 ^ signMask]) {
            }

            int lastUsed = -1;
            int i = 0;

            for(int p = stable ? 0 : first; i < 256; ++i) {
               if (count[i] != 0) {
                  lastUsed = i;
               }

               pos[i] = p += count[i];
            }

            if (stable) {
               for(int i = first + length; i-- != first; support[--pos[k[perm[i]] >>> shift & 255 ^ signMask]] = perm[i]) {
               }

               System.arraycopy(support, 0, perm, first, length);
               i = 0;

               for(int p = first; i < 256; ++i) {
                  if (level < 1 && count[i] > 1) {
                     if (count[i] < 64) {
                        insertionSortIndirect(perm, a, b, p, p + count[i]);
                     } else {
                        offsetStack[stackPos] = p;
                        lengthStack[stackPos] = count[i];
                        levelStack[stackPos++] = level + 1;
                     }
                  }

                  p += count[i];
               }

               java.util.Arrays.fill(count, 0);
            } else {
               i = first + length - count[lastUsed];
               int i = first;

               int c;
               for(c = -1; i <= i; count[c] = 0) {
                  int t = perm[i];
                  c = k[t] >>> shift & 255 ^ signMask;
                  if (i < i) {
                     int d;
                     while((d = --pos[c]) > i) {
                        int z = t;
                        t = perm[d];
                        perm[d] = z;
                        c = k[t] >>> shift & 255 ^ signMask;
                     }

                     perm[i] = t;
                  }

                  if (level < 1 && count[c] > 1) {
                     if (count[c] < 64) {
                        insertionSortIndirect(perm, a, b, i, i + count[c]);
                     } else {
                        offsetStack[stackPos] = i;
                        lengthStack[stackPos] = count[c];
                        levelStack[stackPos++] = level + 1;
                     }
                  }

                  i += count[c];
               }
            }
         }

      }
   }

   private static void selectionSort(byte[][] a, int from, int to, int level) {
      int layers = a.length;
      int firstLayer = level / 1;

      for(int i = from; i < to - 1; ++i) {
         int m = i;

         for(int j = i + 1; j < to; ++j) {
            for(int p = firstLayer; p < layers; ++p) {
               if (a[p][j] < a[p][m]) {
                  m = j;
                  break;
               }

               if (a[p][j] > a[p][m]) {
                  break;
               }
            }
         }

         byte u;
         if (m != i) {
            for(int p = layers; p-- != 0; a[p][m] = u) {
               u = a[p][i];
               a[p][i] = a[p][m];
            }
         }
      }

   }

   public static void radixSort(byte[][] a) {
      radixSort((byte[][])a, 0, a[0].length);
   }

   public static void radixSort(byte[][] a, int from, int to) {
      if (to - from < 64) {
         selectionSort(a, from, to, 0);
      } else {
         int layers = a.length;
         int maxLevel = 1 * layers - 1;
         int p = layers;
         int l = a[0].length;

         while(p-- != 0) {
            if (a[p].length != l) {
               throw new IllegalArgumentException("The array of index " + p + " has not the same length of the array of index 0.");
            }
         }

         p = 255 * (layers * 1 - 1) + 1;
         l = 0;
         int[] offsetStack = new int[p];
         int[] lengthStack = new int[p];
         int[] levelStack = new int[p];
         offsetStack[l] = from;
         lengthStack[l] = to - from;
         levelStack[l++] = 0;
         int[] count = new int[256];
         int[] pos = new int[256];
         byte[] t = new byte[layers];

         while(l > 0) {
            --l;
            int first = offsetStack[l];
            int length = lengthStack[l];
            int level = levelStack[l];
            int signMask = level % 1 == 0 ? 128 : 0;
            byte[] k = a[level / 1];
            int shift = (0 - level % 1) * 8;

            for(int i = first + length; i-- != first; ++count[k[i] >>> shift & 255 ^ signMask]) {
            }

            int lastUsed = -1;
            int i = 0;

            for(int p = first; i < 256; ++i) {
               if (count[i] != 0) {
                  lastUsed = i;
               }

               pos[i] = p += count[i];
            }

            i = first + length - count[lastUsed];
            int i = first;

            int c;
            for(c = -1; i <= i; count[c] = 0) {
               for(int p = layers; p-- != 0; t[p] = a[p][i]) {
               }

               c = k[i] >>> shift & 255 ^ signMask;
               if (i < i) {
                  int d;
                  while((d = --pos[c]) > i) {
                     c = k[d] >>> shift & 255 ^ signMask;

                     byte u;
                     for(int p = layers; p-- != 0; a[p][d] = u) {
                        u = t[p];
                        t[p] = a[p][d];
                     }
                  }

                  for(int p = layers; p-- != 0; a[p][i] = t[p]) {
                  }
               }

               if (level < maxLevel && count[c] > 1) {
                  if (count[c] < 64) {
                     selectionSort(a, i, i + count[c], level + 1);
                  } else {
                     offsetStack[l] = i;
                     lengthStack[l] = count[c];
                     levelStack[l++] = level + 1;
                  }
               }

               i += count[c];
            }
         }

      }
   }

   public static byte[] shuffle(byte[] a, int from, int to, Random random) {
      int p;
      byte t;
      for(int i = to - from; i-- != 0; a[from + p] = t) {
         p = random.nextInt(i + 1);
         t = a[from + i];
         a[from + i] = a[from + p];
      }

      return a;
   }

   public static byte[] shuffle(byte[] a, Random random) {
      int p;
      byte t;
      for(int i = a.length; i-- != 0; a[p] = t) {
         p = random.nextInt(i + 1);
         t = a[i];
         a[i] = a[p];
      }

      return a;
   }

   public static byte[] reverse(byte[] a) {
      int length = a.length;

      byte t;
      for(int i = length / 2; i-- != 0; a[i] = t) {
         t = a[length - i - 1];
         a[length - i - 1] = a[i];
      }

      return a;
   }

   public static byte[] reverse(byte[] a, int from, int to) {
      int length = to - from;

      byte t;
      for(int i = length / 2; i-- != 0; a[from + i] = t) {
         t = a[from + length - i - 1];
         a[from + length - i - 1] = a[from + i];
      }

      return a;
   }

   protected static class ForkJoinQuickSortComp extends RecursiveAction {
      private static final long serialVersionUID = 1L;
      private final int from;
      private final int to;
      private final byte[] x;
      private final ByteComparator comp;

      public ForkJoinQuickSortComp(byte[] x, int from, int to, ByteComparator comp) {
         this.from = from;
         this.to = to;
         this.x = x;
         this.comp = comp;
      }

      protected void compute() {
         byte[] x = this.x;
         int len = this.to - this.from;
         if (len < 8192) {
            ByteArrays.quickSort(x, this.from, this.to, this.comp);
         } else {
            int m = this.from + len / 2;
            int l = this.from;
            int n = this.to - 1;
            int s = len / 8;
            l = ByteArrays.med3(x, l, l + s, l + 2 * s, this.comp);
            m = ByteArrays.med3(x, m - s, m, m + s, this.comp);
            n = ByteArrays.med3(x, n - 2 * s, n - s, n, this.comp);
            m = ByteArrays.med3(x, l, m, n, this.comp);
            byte v = x[m];
            int a = this.from;
            int b = a;
            int c = this.to - 1;
            int d = c;

            while(true) {
               int comparison;
               while(b > c || (comparison = this.comp.compare(x[b], v)) > 0) {
                  for(; c >= b && (comparison = this.comp.compare(x[c], v)) >= 0; --c) {
                     if (comparison == 0) {
                        ByteArrays.swap(x, c, d--);
                     }
                  }

                  if (b > c) {
                     s = Math.min(a - this.from, b - a);
                     ByteArrays.swap(x, this.from, b - s, s);
                     s = Math.min(d - c, this.to - d - 1);
                     ByteArrays.swap(x, b, this.to - s, s);
                     s = b - a;
                     comparison = d - c;
                     if (s > 1 && comparison > 1) {
                        invokeAll(new ForkJoinQuickSortComp(x, this.from, this.from + s, this.comp), new ForkJoinQuickSortComp(x, this.to - comparison, this.to, this.comp));
                     } else if (s > 1) {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSortComp(x, this.from, this.from + s, this.comp)});
                     } else {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSortComp(x, this.to - comparison, this.to, this.comp)});
                     }

                     return;
                  }

                  ByteArrays.swap(x, b++, c--);
               }

               if (comparison == 0) {
                  ByteArrays.swap(x, a++, b);
               }

               ++b;
            }
         }
      }
   }

   protected static class ForkJoinQuickSort extends RecursiveAction {
      private static final long serialVersionUID = 1L;
      private final int from;
      private final int to;
      private final byte[] x;

      public ForkJoinQuickSort(byte[] x, int from, int to) {
         this.from = from;
         this.to = to;
         this.x = x;
      }

      protected void compute() {
         byte[] x = this.x;
         int len = this.to - this.from;
         if (len < 8192) {
            ByteArrays.quickSort(x, this.from, this.to);
         } else {
            int m = this.from + len / 2;
            int l = this.from;
            int n = this.to - 1;
            int s = len / 8;
            l = ByteArrays.med3(x, l, l + s, l + 2 * s);
            m = ByteArrays.med3(x, m - s, m, m + s);
            n = ByteArrays.med3(x, n - 2 * s, n - s, n);
            m = ByteArrays.med3(x, l, m, n);
            byte v = x[m];
            int a = this.from;
            int b = a;
            int c = this.to - 1;
            int d = c;

            while(true) {
               int comparison;
               while(b > c || (comparison = Byte.compare(x[b], v)) > 0) {
                  for(; c >= b && (comparison = Byte.compare(x[c], v)) >= 0; --c) {
                     if (comparison == 0) {
                        ByteArrays.swap(x, c, d--);
                     }
                  }

                  if (b > c) {
                     s = Math.min(a - this.from, b - a);
                     ByteArrays.swap(x, this.from, b - s, s);
                     s = Math.min(d - c, this.to - d - 1);
                     ByteArrays.swap(x, b, this.to - s, s);
                     s = b - a;
                     comparison = d - c;
                     if (s > 1 && comparison > 1) {
                        invokeAll(new ForkJoinQuickSort(x, this.from, this.from + s), new ForkJoinQuickSort(x, this.to - comparison, this.to));
                     } else if (s > 1) {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSort(x, this.from, this.from + s)});
                     } else {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSort(x, this.to - comparison, this.to)});
                     }

                     return;
                  }

                  ByteArrays.swap(x, b++, c--);
               }

               if (comparison == 0) {
                  ByteArrays.swap(x, a++, b);
               }

               ++b;
            }
         }
      }
   }

   protected static class ForkJoinQuickSortIndirect extends RecursiveAction {
      private static final long serialVersionUID = 1L;
      private final int from;
      private final int to;
      private final int[] perm;
      private final byte[] x;

      public ForkJoinQuickSortIndirect(int[] perm, byte[] x, int from, int to) {
         this.from = from;
         this.to = to;
         this.x = x;
         this.perm = perm;
      }

      protected void compute() {
         byte[] x = this.x;
         int len = this.to - this.from;
         if (len < 8192) {
            ByteArrays.quickSortIndirect(this.perm, x, this.from, this.to);
         } else {
            int m = this.from + len / 2;
            int l = this.from;
            int n = this.to - 1;
            int s = len / 8;
            l = ByteArrays.med3Indirect(this.perm, x, l, l + s, l + 2 * s);
            m = ByteArrays.med3Indirect(this.perm, x, m - s, m, m + s);
            n = ByteArrays.med3Indirect(this.perm, x, n - 2 * s, n - s, n);
            m = ByteArrays.med3Indirect(this.perm, x, l, m, n);
            byte v = x[this.perm[m]];
            int a = this.from;
            int b = a;
            int c = this.to - 1;
            int d = c;

            while(true) {
               int comparison;
               while(b > c || (comparison = Byte.compare(x[this.perm[b]], v)) > 0) {
                  for(; c >= b && (comparison = Byte.compare(x[this.perm[c]], v)) >= 0; --c) {
                     if (comparison == 0) {
                        IntArrays.swap(this.perm, c, d--);
                     }
                  }

                  if (b > c) {
                     s = Math.min(a - this.from, b - a);
                     IntArrays.swap(this.perm, this.from, b - s, s);
                     s = Math.min(d - c, this.to - d - 1);
                     IntArrays.swap(this.perm, b, this.to - s, s);
                     s = b - a;
                     comparison = d - c;
                     if (s > 1 && comparison > 1) {
                        invokeAll(new ForkJoinQuickSortIndirect(this.perm, x, this.from, this.from + s), new ForkJoinQuickSortIndirect(this.perm, x, this.to - comparison, this.to));
                     } else if (s > 1) {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSortIndirect(this.perm, x, this.from, this.from + s)});
                     } else {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSortIndirect(this.perm, x, this.to - comparison, this.to)});
                     }

                     return;
                  }

                  IntArrays.swap(this.perm, b++, c--);
               }

               if (comparison == 0) {
                  IntArrays.swap(this.perm, a++, b);
               }

               ++b;
            }
         }
      }
   }

   protected static class ForkJoinQuickSort2 extends RecursiveAction {
      private static final long serialVersionUID = 1L;
      private final int from;
      private final int to;
      private final byte[] x;
      private final byte[] y;

      public ForkJoinQuickSort2(byte[] x, byte[] y, int from, int to) {
         this.from = from;
         this.to = to;
         this.x = x;
         this.y = y;
      }

      protected void compute() {
         byte[] x = this.x;
         byte[] y = this.y;
         int len = this.to - this.from;
         if (len < 8192) {
            ByteArrays.quickSort(x, y, this.from, this.to);
         } else {
            int m = this.from + len / 2;
            int l = this.from;
            int n = this.to - 1;
            int s = len / 8;
            l = ByteArrays.med3(x, y, l, l + s, l + 2 * s);
            m = ByteArrays.med3(x, y, m - s, m, m + s);
            n = ByteArrays.med3(x, y, n - 2 * s, n - s, n);
            m = ByteArrays.med3(x, y, l, m, n);
            byte v = x[m];
            byte w = y[m];
            int a = this.from;
            int b = a;
            int c = this.to - 1;
            int d = c;

            while(true) {
               int comparison;
               int t;
               while(b > c || (comparison = (t = Byte.compare(x[b], v)) == 0 ? Byte.compare(y[b], w) : t) > 0) {
                  for(; c >= b && (comparison = (t = Byte.compare(x[c], v)) == 0 ? Byte.compare(y[c], w) : t) >= 0; --c) {
                     if (comparison == 0) {
                        ByteArrays.swap(x, y, c, d--);
                     }
                  }

                  if (b > c) {
                     s = Math.min(a - this.from, b - a);
                     ByteArrays.swap(x, y, this.from, b - s, s);
                     s = Math.min(d - c, this.to - d - 1);
                     ByteArrays.swap(x, y, b, this.to - s, s);
                     s = b - a;
                     comparison = d - c;
                     if (s > 1 && comparison > 1) {
                        invokeAll(new ForkJoinQuickSort2(x, y, this.from, this.from + s), new ForkJoinQuickSort2(x, y, this.to - comparison, this.to));
                     } else if (s > 1) {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSort2(x, y, this.from, this.from + s)});
                     } else {
                        invokeAll(new ForkJoinTask[]{new ForkJoinQuickSort2(x, y, this.to - comparison, this.to)});
                     }

                     return;
                  }

                  ByteArrays.swap(x, y, b++, c--);
               }

               if (comparison == 0) {
                  ByteArrays.swap(x, y, a++, b);
               }

               ++b;
            }
         }
      }
   }

   protected static final class Segment {
      protected final int offset;
      protected final int length;
      protected final int level;

      protected Segment(int offset, int length, int level) {
         this.offset = offset;
         this.length = length;
         this.level = level;
      }

      public String toString() {
         return "Segment [offset=" + this.offset + ", length=" + this.length + ", level=" + this.level + "]";
      }
   }

   private static final class ArrayHashStrategy implements Hash.Strategy, Serializable {
      private static final long serialVersionUID = -7046029254386353129L;

      private ArrayHashStrategy() {
      }

      public int hashCode(byte[] o) {
         return java.util.Arrays.hashCode(o);
      }

      public boolean equals(byte[] a, byte[] b) {
         return java.util.Arrays.equals(a, b);
      }
   }
}
