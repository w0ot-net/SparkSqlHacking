package org.apache.spark.util.collection.unsafe.sort;

import org.apache.spark.annotation.Private;
import org.apache.spark.unsafe.types.ByteArray;
import org.apache.spark.unsafe.types.UTF8String;
import org.sparkproject.guava.primitives.UnsignedLongs;

@Private
public class PrefixComparators {
   public static final PrefixComparator STRING = new UnsignedPrefixComparator();
   public static final PrefixComparator STRING_DESC = new UnsignedPrefixComparatorDesc();
   public static final PrefixComparator STRING_NULLS_LAST = new UnsignedPrefixComparatorNullsLast();
   public static final PrefixComparator STRING_DESC_NULLS_FIRST = new UnsignedPrefixComparatorDescNullsFirst();
   public static final PrefixComparator BINARY = new UnsignedPrefixComparator();
   public static final PrefixComparator BINARY_DESC = new UnsignedPrefixComparatorDesc();
   public static final PrefixComparator BINARY_NULLS_LAST = new UnsignedPrefixComparatorNullsLast();
   public static final PrefixComparator BINARY_DESC_NULLS_FIRST = new UnsignedPrefixComparatorDescNullsFirst();
   public static final PrefixComparator LONG = new SignedPrefixComparator();
   public static final PrefixComparator LONG_DESC = new SignedPrefixComparatorDesc();
   public static final PrefixComparator LONG_NULLS_LAST = new SignedPrefixComparatorNullsLast();
   public static final PrefixComparator LONG_DESC_NULLS_FIRST = new SignedPrefixComparatorDescNullsFirst();
   public static final PrefixComparator DOUBLE = new UnsignedPrefixComparator();
   public static final PrefixComparator DOUBLE_DESC = new UnsignedPrefixComparatorDesc();
   public static final PrefixComparator DOUBLE_NULLS_LAST = new UnsignedPrefixComparatorNullsLast();
   public static final PrefixComparator DOUBLE_DESC_NULLS_FIRST = new UnsignedPrefixComparatorDescNullsFirst();

   private PrefixComparators() {
   }

   public static final class StringPrefixComparator {
      public static long computePrefix(UTF8String value) {
         return value == null ? 0L : value.getPrefix();
      }
   }

   public static final class BinaryPrefixComparator {
      public static long computePrefix(byte[] bytes) {
         return ByteArray.getPrefix(bytes);
      }
   }

   public static final class DoublePrefixComparator {
      public static long computePrefix(double value) {
         value = value == (double)-0.0F ? (double)0.0F : value;
         long bits = Double.doubleToLongBits(value);
         long mask = -(bits >>> 63) | Long.MIN_VALUE;
         return bits ^ mask;
      }
   }

   public abstract static class RadixSortSupport extends PrefixComparator {
      public abstract boolean sortDescending();

      public abstract boolean sortSigned();

      public abstract boolean nullsFirst();
   }

   public static final class UnsignedPrefixComparator extends RadixSortSupport {
      public boolean sortDescending() {
         return false;
      }

      public boolean sortSigned() {
         return false;
      }

      public boolean nullsFirst() {
         return true;
      }

      public int compare(long aPrefix, long bPrefix) {
         return UnsignedLongs.compare(aPrefix, bPrefix);
      }
   }

   public static final class UnsignedPrefixComparatorNullsLast extends RadixSortSupport {
      public boolean sortDescending() {
         return false;
      }

      public boolean sortSigned() {
         return false;
      }

      public boolean nullsFirst() {
         return false;
      }

      public int compare(long aPrefix, long bPrefix) {
         return UnsignedLongs.compare(aPrefix, bPrefix);
      }
   }

   public static final class UnsignedPrefixComparatorDescNullsFirst extends RadixSortSupport {
      public boolean sortDescending() {
         return true;
      }

      public boolean sortSigned() {
         return false;
      }

      public boolean nullsFirst() {
         return true;
      }

      public int compare(long bPrefix, long aPrefix) {
         return UnsignedLongs.compare(aPrefix, bPrefix);
      }
   }

   public static final class UnsignedPrefixComparatorDesc extends RadixSortSupport {
      public boolean sortDescending() {
         return true;
      }

      public boolean sortSigned() {
         return false;
      }

      public boolean nullsFirst() {
         return false;
      }

      public int compare(long bPrefix, long aPrefix) {
         return UnsignedLongs.compare(aPrefix, bPrefix);
      }
   }

   public static final class SignedPrefixComparator extends RadixSortSupport {
      public boolean sortDescending() {
         return false;
      }

      public boolean sortSigned() {
         return true;
      }

      public boolean nullsFirst() {
         return true;
      }

      public int compare(long a, long b) {
         return Long.compare(a, b);
      }
   }

   public static final class SignedPrefixComparatorNullsLast extends RadixSortSupport {
      public boolean sortDescending() {
         return false;
      }

      public boolean sortSigned() {
         return true;
      }

      public boolean nullsFirst() {
         return false;
      }

      public int compare(long a, long b) {
         return Long.compare(a, b);
      }
   }

   public static final class SignedPrefixComparatorDescNullsFirst extends RadixSortSupport {
      public boolean sortDescending() {
         return true;
      }

      public boolean sortSigned() {
         return true;
      }

      public boolean nullsFirst() {
         return true;
      }

      public int compare(long b, long a) {
         return Long.compare(a, b);
      }
   }

   public static final class SignedPrefixComparatorDesc extends RadixSortSupport {
      public boolean sortDescending() {
         return true;
      }

      public boolean sortSigned() {
         return true;
      }

      public boolean nullsFirst() {
         return false;
      }

      public int compare(long b, long a) {
         return Long.compare(a, b);
      }
   }
}
