package org.apache.arrow.util;

import org.checkerframework.dataflow.qual.AssertMethod;

public final class Preconditions {
   private Preconditions() {
   }

   @AssertMethod
   public static void checkArgument(boolean expression) {
      if (!expression) {
         throw new IllegalArgumentException();
      }
   }

   @AssertMethod
   public static void checkArgument(boolean expression, Object errorMessage) {
      if (!expression) {
         throw new IllegalArgumentException(String.valueOf(errorMessage));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
      if (!expression) {
         throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, char p1) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, int p1) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, long p1) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, char p1, char p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, char p1, int p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, char p1, long p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, char p1, Object p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, int p1, char p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, int p1, int p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, int p1, long p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, int p1, Object p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, long p1, char p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, long p1, int p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, long p1, long p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, long p1, Object p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1, char p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1, int p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1, long p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1, Object p2) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1, Object p2, Object p3) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2, p3));
      }
   }

   public static void checkArgument(boolean b, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
      if (!b) {
         throw new IllegalArgumentException(format(errorMessageTemplate, p1, p2, p3, p4));
      }
   }

   @AssertMethod
   public static void checkState(boolean expression) {
      if (!expression) {
         throw new IllegalStateException();
      }
   }

   @AssertMethod
   public static void checkState(boolean expression, Object errorMessage) {
      if (!expression) {
         throw new IllegalStateException(String.valueOf(errorMessage));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
      if (!expression) {
         throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, char p1) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, int p1) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, long p1) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, char p1, char p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, char p1, int p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, char p1, long p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, char p1, Object p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, int p1, char p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, int p1, int p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, int p1, long p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, int p1, Object p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, long p1, char p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, long p1, int p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, long p1, long p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, long p1, Object p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1, char p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1, int p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1, long p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1, Object p2) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1, Object p2, Object p3) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2, p3));
      }
   }

   public static void checkState(boolean b, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
      if (!b) {
         throw new IllegalStateException(format(errorMessageTemplate, p1, p2, p3, p4));
      }
   }

   public static Object checkNotNull(Object reference) {
      if (reference == null) {
         throw new NullPointerException();
      } else {
         return reference;
      }
   }

   public static Object checkNotNull(Object reference, Object errorMessage) {
      if (reference == null) {
         throw new NullPointerException(String.valueOf(errorMessage));
      } else {
         return reference;
      }
   }

   public static Object checkNotNull(Object reference, String errorMessageTemplate, Object... errorMessageArgs) {
      if (reference == null) {
         throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
      } else {
         return reference;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, char p1) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, int p1) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, long p1) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, char p1, char p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, char p1, int p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, char p1, long p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, char p1, Object p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, int p1, char p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, int p1, int p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, int p1, long p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, int p1, Object p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, long p1, char p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, long p1, int p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, long p1, long p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, long p1, Object p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1, char p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1, int p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1, long p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1, Object p2) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1, Object p2, Object p3) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2, p3));
      } else {
         return obj;
      }
   }

   public static Object checkNotNull(Object obj, String errorMessageTemplate, Object p1, Object p2, Object p3, Object p4) {
      if (obj == null) {
         throw new NullPointerException(format(errorMessageTemplate, p1, p2, p3, p4));
      } else {
         return obj;
      }
   }

   public static int checkElementIndex(int index, int size) {
      return checkElementIndex(index, size, "index");
   }

   public static int checkElementIndex(int index, int size, String desc) {
      if (index >= 0 && index < size) {
         return index;
      } else {
         throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
      }
   }

   private static String badElementIndex(int index, int size, String desc) {
      if (index < 0) {
         return format("%s (%s) must not be negative", desc, index);
      } else if (size < 0) {
         throw new IllegalArgumentException("negative size: " + size);
      } else {
         return format("%s (%s) must be less than size (%s)", desc, index, size);
      }
   }

   public static long checkPositionIndex(long index, long size) {
      return checkPositionIndex(index, size, "index");
   }

   public static long checkPositionIndex(long index, long size, String desc) {
      if (index >= 0L && index <= size) {
         return index;
      } else {
         throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
      }
   }

   private static String badPositionIndex(long index, long size, String desc) {
      if (index < 0L) {
         return format("%s (%s) must not be negative", desc, index);
      } else if (size < 0L) {
         throw new IllegalArgumentException("negative size: " + size);
      } else {
         return format("%s (%s) must not be greater than size (%s)", desc, index, size);
      }
   }

   public static void checkPositionIndexes(int start, int end, int size) {
      if (start < 0 || end < start || end > size) {
         throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
      }
   }

   private static String badPositionIndexes(int start, int end, int size) {
      if (start >= 0 && start <= size) {
         return end >= 0 && end <= size ? format("end index (%s) must not be less than start index (%s)", end, start) : badPositionIndex((long)end, (long)size, "end index");
      } else {
         return badPositionIndex((long)start, (long)size, "start index");
      }
   }

   static String format(String template, Object... args) {
      template = String.valueOf(template);
      StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
      int templateStart = 0;

      int i;
      int placeholderStart;
      for(i = 0; i < args.length; templateStart = placeholderStart + 2) {
         placeholderStart = template.indexOf("%s", templateStart);
         if (placeholderStart == -1) {
            break;
         }

         builder.append(template, templateStart, placeholderStart);
         builder.append(args[i++]);
      }

      builder.append(template, templateStart, template.length());
      if (i < args.length) {
         builder.append(" [");
         builder.append(args[i++]);

         while(i < args.length) {
            builder.append(", ");
            builder.append(args[i++]);
         }

         builder.append(']');
      }

      return builder.toString();
   }
}
