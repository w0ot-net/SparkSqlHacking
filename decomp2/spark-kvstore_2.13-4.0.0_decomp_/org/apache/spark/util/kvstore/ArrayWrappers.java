package org.apache.spark.util.kvstore;

import java.util.Arrays;
import org.sparkproject.guava.base.Preconditions;

class ArrayWrappers {
   public static Comparable forArray(Object a) {
      Preconditions.checkArgument(a.getClass().isArray());
      Comparable<?> ret;
      if (a instanceof int[] ia) {
         ret = new ComparableIntArray(ia);
      } else if (a instanceof long[] la) {
         ret = new ComparableLongArray(la);
      } else if (a instanceof byte[] ba) {
         ret = new ComparableByteArray(ba);
      } else {
         Preconditions.checkArgument(!a.getClass().getComponentType().isPrimitive());
         ret = new ComparableObjectArray(a);
      }

      return ret;
   }

   private static class ComparableIntArray implements Comparable {
      private final int[] array;

      ComparableIntArray(int[] array) {
         this.array = array;
      }

      public boolean equals(Object other) {
         if (other instanceof ComparableIntArray comparableIntArray) {
            return Arrays.equals(this.array, comparableIntArray.array);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int code = 0;

         for(int j : this.array) {
            code = code * 31 + j;
         }

         return code;
      }

      public int compareTo(ComparableIntArray other) {
         int len = Math.min(this.array.length, other.array.length);

         for(int i = 0; i < len; ++i) {
            int diff = this.array[i] - other.array[i];
            if (diff != 0) {
               return diff;
            }
         }

         return this.array.length - other.array.length;
      }
   }

   private static class ComparableLongArray implements Comparable {
      private final long[] array;

      ComparableLongArray(long[] array) {
         this.array = array;
      }

      public boolean equals(Object other) {
         if (other instanceof ComparableLongArray comparableLongArray) {
            return Arrays.equals(this.array, comparableLongArray.array);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int code = 0;

         for(long l : this.array) {
            code = code * 31 + (int)l;
         }

         return code;
      }

      public int compareTo(ComparableLongArray other) {
         int len = Math.min(this.array.length, other.array.length);

         for(int i = 0; i < len; ++i) {
            long diff = this.array[i] - other.array[i];
            if (diff != 0L) {
               return diff > 0L ? 1 : -1;
            }
         }

         return this.array.length - other.array.length;
      }
   }

   private static class ComparableByteArray implements Comparable {
      private final byte[] array;

      ComparableByteArray(byte[] array) {
         this.array = array;
      }

      public boolean equals(Object other) {
         if (other instanceof ComparableByteArray comparableByteArray) {
            return Arrays.equals(this.array, comparableByteArray.array);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int code = 0;

         for(byte b : this.array) {
            code = code * 31 + b;
         }

         return code;
      }

      public int compareTo(ComparableByteArray other) {
         int len = Math.min(this.array.length, other.array.length);

         for(int i = 0; i < len; ++i) {
            int diff = this.array[i] - other.array[i];
            if (diff != 0) {
               return diff;
            }
         }

         return this.array.length - other.array.length;
      }
   }

   private static class ComparableObjectArray implements Comparable {
      private final Object[] array;

      ComparableObjectArray(Object[] array) {
         this.array = array;
      }

      public boolean equals(Object other) {
         if (other instanceof ComparableObjectArray comparableObjectArray) {
            return Arrays.equals(this.array, comparableObjectArray.array);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int code = 0;

         for(Object o : this.array) {
            code = code * 31 + o.hashCode();
         }

         return code;
      }

      public int compareTo(ComparableObjectArray other) {
         int len = Math.min(this.array.length, other.array.length);

         for(int i = 0; i < len; ++i) {
            int diff = ((Comparable)this.array[i]).compareTo(other.array[i]);
            if (diff != 0) {
               return diff;
            }
         }

         return this.array.length - other.array.length;
      }
   }
}
