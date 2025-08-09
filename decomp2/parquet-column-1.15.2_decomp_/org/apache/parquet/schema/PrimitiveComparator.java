package org.apache.parquet.schema;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Comparator;
import org.apache.parquet.io.api.Binary;

public abstract class PrimitiveComparator implements Comparator, Serializable {
   static final PrimitiveComparator BOOLEAN_COMPARATOR = new PrimitiveComparator() {
      int compareNotNulls(Boolean o1, Boolean o2) {
         return this.compare(o1, o2);
      }

      public int compare(boolean b1, boolean b2) {
         return Boolean.compare(b1, b2);
      }

      public String toString() {
         return "BOOLEAN_COMPARATOR";
      }
   };
   static final PrimitiveComparator SIGNED_INT32_COMPARATOR = new IntComparator() {
      public int compare(int i1, int i2) {
         return Integer.compare(i1, i2);
      }

      public String toString() {
         return "SIGNED_INT32_COMPARATOR";
      }
   };
   static final PrimitiveComparator UNSIGNED_INT32_COMPARATOR = new IntComparator() {
      public int compare(int i1, int i2) {
         return Integer.compare(i1 ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
      }

      public String toString() {
         return "UNSIGNED_INT32_COMPARATOR";
      }
   };
   static final PrimitiveComparator SIGNED_INT64_COMPARATOR = new LongComparator() {
      public int compare(long l1, long l2) {
         return Long.compare(l1, l2);
      }

      public String toString() {
         return "SIGNED_INT64_COMPARATOR";
      }
   };
   static final PrimitiveComparator UNSIGNED_INT64_COMPARATOR = new LongComparator() {
      public int compare(long l1, long l2) {
         return Long.compare(l1 ^ Long.MIN_VALUE, l2 ^ Long.MIN_VALUE);
      }

      public String toString() {
         return "UNSIGNED_INT64_COMPARATOR";
      }
   };
   static final PrimitiveComparator FLOAT_COMPARATOR = new PrimitiveComparator() {
      int compareNotNulls(Float o1, Float o2) {
         return this.compare(o1, o2);
      }

      public int compare(float f1, float f2) {
         return Float.compare(f1, f2);
      }

      public String toString() {
         return "FLOAT_COMPARATOR";
      }
   };
   static final PrimitiveComparator DOUBLE_COMPARATOR = new PrimitiveComparator() {
      int compareNotNulls(Double o1, Double o2) {
         return this.compare(o1, o2);
      }

      public int compare(double d1, double d2) {
         return Double.compare(d1, d2);
      }

      public String toString() {
         return "DOUBLE_COMPARATOR";
      }
   };
   public static final PrimitiveComparator UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR = new BinaryComparator() {
      int compareBinary(Binary b1, Binary b2) {
         return Binary.lexicographicCompare(b1, b2);
      }

      public String toString() {
         return "UNSIGNED_LEXICOGRAPHICAL_BINARY_COMPARATOR";
      }
   };
   static final PrimitiveComparator BINARY_AS_SIGNED_INTEGER_COMPARATOR = new BinaryComparator() {
      private static final int NEGATIVE_PADDING = 255;
      private static final int POSITIVE_PADDING = 0;

      int compareBinary(Binary b1, Binary b2) {
         return this.compare(b1.toByteBuffer(), b2.toByteBuffer());
      }

      private int compare(ByteBuffer b1, ByteBuffer b2) {
         int l1 = b1.remaining();
         int l2 = b2.remaining();
         int p1 = b1.position();
         int p2 = b2.position();
         boolean isNegative1 = l1 > 0 && b1.get(p1) < 0;
         boolean isNegative2 = l2 > 0 && b2.get(p2) < 0;
         if (isNegative1 != isNegative2) {
            return isNegative1 ? -1 : 1;
         } else {
            int result = 0;
            if (l1 < l2) {
               int lengthDiff = l2 - l1;
               result = -this.compareWithPadding(lengthDiff, b2, p2, isNegative1 ? 255 : 0);
               p2 += lengthDiff;
            } else if (l1 > l2) {
               int lengthDiff = l1 - l2;
               result = this.compareWithPadding(lengthDiff, b1, p1, isNegative2 ? 255 : 0);
               p1 += lengthDiff;
            }

            if (result == 0) {
               result = this.compare(Math.min(l1, l2), b1, p1, b2, p2);
            }

            return result;
         }
      }

      private int compareWithPadding(int length, ByteBuffer b, int p, int paddingByte) {
         int i = p;

         for(int n = p + length; i < n; ++i) {
            int result = this.toUnsigned(b.get(i)) - paddingByte;
            if (result != 0) {
               return result;
            }
         }

         return 0;
      }

      private int compare(int length, ByteBuffer b1, int p1, ByteBuffer b2, int p2) {
         for(int i = 0; i < length; ++i) {
            int result = this.toUnsigned(b1.get(p1 + i)) - this.toUnsigned(b2.get(p2 + i));
            if (result != 0) {
               return result;
            }
         }

         return 0;
      }

      public String toString() {
         return "BINARY_AS_SIGNED_INTEGER_COMPARATOR";
      }
   };
   static final PrimitiveComparator BINARY_AS_FLOAT16_COMPARATOR = new BinaryComparator() {
      int compareBinary(Binary b1, Binary b2) {
         return Float16.compare(b1.get2BytesLittleEndian(), b2.get2BytesLittleEndian());
      }

      public String toString() {
         return "BINARY_AS_FLOAT16_COMPARATOR";
      }
   };

   public int compare(boolean b1, boolean b2) {
      throw new UnsupportedOperationException("compare(boolean, boolean) was called on a non-boolean comparator: " + this.toString());
   }

   public int compare(int i1, int i2) {
      throw new UnsupportedOperationException("compare(int, int) was called on a non-int comparator: " + this.toString());
   }

   public int compare(long l1, long l2) {
      throw new UnsupportedOperationException("compare(long, long) was called on a non-long comparator: " + this.toString());
   }

   public int compare(float f1, float f2) {
      throw new UnsupportedOperationException("compare(float, float) was called on a non-float comparator: " + this.toString());
   }

   public int compare(double d1, double d2) {
      throw new UnsupportedOperationException("compare(double, double) was called on a non-double comparator: " + this.toString());
   }

   public final int compare(Object o1, Object o2) {
      if (o1 == null) {
         return o2 == null ? 0 : -1;
      } else {
         return o2 == null ? 1 : this.compareNotNulls(o1, o2);
      }
   }

   abstract int compareNotNulls(Object var1, Object var2);

   private abstract static class IntComparator extends PrimitiveComparator {
      private IntComparator() {
      }

      int compareNotNulls(Integer o1, Integer o2) {
         return this.compare(o1, o2);
      }
   }

   private abstract static class LongComparator extends PrimitiveComparator {
      private LongComparator() {
      }

      int compareNotNulls(Long o1, Long o2) {
         return this.compare(o1, o2);
      }
   }

   private abstract static class BinaryComparator extends PrimitiveComparator {
      private BinaryComparator() {
      }

      int compareNotNulls(Binary o1, Binary o2) {
         return this.compareBinary(o1, o2);
      }

      abstract int compareBinary(Binary var1, Binary var2);

      final int toUnsigned(byte b) {
         return b & 255;
      }
   }
}
