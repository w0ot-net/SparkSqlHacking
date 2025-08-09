package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Preconditions {
   private Preconditions() {
   }

   public static void checkArgument(boolean expression) {
      if (!expression) {
         throw new IllegalArgumentException();
      }
   }

   public static void checkArgument(boolean expression, @CheckForNull Object errorMessage) {
      if (!expression) {
         throw new IllegalArgumentException(String.valueOf(errorMessage));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object... errorMessageArgs) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, char p1) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, int p1) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, long p1) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object p1) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, char p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, int p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, long p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, char p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, char p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, int p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, long p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, int p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, char p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, int p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, long p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, long p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, char p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, int p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, long p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, @CheckForNull String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2, @CheckForNull Object p3) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
      }
   }

   public static void checkArgument(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2, @CheckForNull Object p3, @CheckForNull Object p4) {
      if (!expression) {
         throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
      }
   }

   public static void checkState(boolean expression) {
      if (!expression) {
         throw new IllegalStateException();
      }
   }

   public static void checkState(boolean expression, @CheckForNull Object errorMessage) {
      if (!expression) {
         throw new IllegalStateException(String.valueOf(errorMessage));
      }
   }

   public static void checkState(boolean expression, @CheckForNull String errorMessageTemplate, @CheckForNull Object... errorMessageArgs) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, char p1) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, int p1) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, long p1) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, char p1, char p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, char p1, int p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, char p1, long p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, char p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, int p1, char p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, int p1, int p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, int p1, long p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, int p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, long p1, char p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, long p1, int p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, long p1, long p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, long p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, char p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, int p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, long p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2, @CheckForNull Object p3) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
      }
   }

   public static void checkState(boolean expression, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2, @CheckForNull Object p3, @CheckForNull Object p4) {
      if (!expression) {
         throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference) {
      if (reference == null) {
         throw new NullPointerException();
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, @CheckForNull Object errorMessage) {
      if (reference == null) {
         throw new NullPointerException(String.valueOf(errorMessage));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object... errorMessageArgs) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, char p1) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, int p1) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, long p1) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, char p1, char p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, char p1, int p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, char p1, long p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, char p1, @CheckForNull Object p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, int p1, char p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, int p1, int p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, int p1, long p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, int p1, @CheckForNull Object p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, long p1, char p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, long p1, int p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, long p1, long p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, long p1, @CheckForNull Object p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1, char p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1, int p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1, long p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2, @CheckForNull Object p3) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static Object checkNotNull(@CheckForNull Object reference, String errorMessageTemplate, @CheckForNull Object p1, @CheckForNull Object p2, @CheckForNull Object p3, @CheckForNull Object p4) {
      if (reference == null) {
         throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
      } else {
         return reference;
      }
   }

   @CanIgnoreReturnValue
   public static int checkElementIndex(int index, int size) {
      return checkElementIndex(index, size, "index");
   }

   @CanIgnoreReturnValue
   public static int checkElementIndex(int index, int size, String desc) {
      if (index >= 0 && index < size) {
         return index;
      } else {
         throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
      }
   }

   private static String badElementIndex(int index, int size, String desc) {
      if (index < 0) {
         return Strings.lenientFormat("%s (%s) must not be negative", desc, index);
      } else if (size < 0) {
         throw new IllegalArgumentException("negative size: " + size);
      } else {
         return Strings.lenientFormat("%s (%s) must be less than size (%s)", desc, index, size);
      }
   }

   @CanIgnoreReturnValue
   public static int checkPositionIndex(int index, int size) {
      return checkPositionIndex(index, size, "index");
   }

   @CanIgnoreReturnValue
   public static int checkPositionIndex(int index, int size, String desc) {
      if (index >= 0 && index <= size) {
         return index;
      } else {
         throw new IndexOutOfBoundsException(badPositionIndex(index, size, desc));
      }
   }

   private static String badPositionIndex(int index, int size, String desc) {
      if (index < 0) {
         return Strings.lenientFormat("%s (%s) must not be negative", desc, index);
      } else if (size < 0) {
         throw new IllegalArgumentException("negative size: " + size);
      } else {
         return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", desc, index, size);
      }
   }

   public static void checkPositionIndexes(int start, int end, int size) {
      if (start < 0 || end < start || end > size) {
         throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
      }
   }

   private static String badPositionIndexes(int start, int end, int size) {
      if (start >= 0 && start <= size) {
         return end >= 0 && end <= size ? Strings.lenientFormat("end index (%s) must not be less than start index (%s)", end, start) : badPositionIndex(end, size, "end index");
      } else {
         return badPositionIndex(start, size, "start index");
      }
   }
}
