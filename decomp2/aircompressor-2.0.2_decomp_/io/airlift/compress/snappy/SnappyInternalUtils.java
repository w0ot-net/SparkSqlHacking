package io.airlift.compress.snappy;

import java.io.IOException;
import java.io.InputStream;

final class SnappyInternalUtils {
   private SnappyInternalUtils() {
   }

   static Object checkNotNull(Object reference, String errorMessageTemplate, Object... errorMessageArgs) {
      if (reference == null) {
         throw new NullPointerException(String.format(errorMessageTemplate, errorMessageArgs));
      } else {
         return reference;
      }
   }

   static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
      if (!expression) {
         throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
      }
   }

   static void checkPositionIndexes(int start, int end, int size) {
      if (start < 0 || end < start || end > size) {
         throw new IndexOutOfBoundsException(badPositionIndexes(start, end, size));
      }
   }

   static String badPositionIndexes(int start, int end, int size) {
      if (start >= 0 && start <= size) {
         return end >= 0 && end <= size ? String.format("end index (%s) must not be less than start index (%s)", end, start) : badPositionIndex(end, size, "end index");
      } else {
         return badPositionIndex(start, size, "start index");
      }
   }

   static String badPositionIndex(int index, int size, String desc) {
      if (index < 0) {
         return String.format("%s (%s) must not be negative", desc, index);
      } else if (size < 0) {
         throw new IllegalArgumentException("negative size: " + size);
      } else {
         return String.format("%s (%s) must not be greater than size (%s)", desc, index, size);
      }
   }

   static int readBytes(InputStream source, byte[] dest, int offset, int length) throws IOException {
      checkNotNull(source, "source is null");
      checkNotNull(dest, "dest is null");
      int lastRead = source.read(dest, offset, length);
      int totalRead = lastRead;
      if (lastRead < length) {
         while(totalRead < length && lastRead != -1) {
            lastRead = source.read(dest, offset + totalRead, length - totalRead);
            if (lastRead != -1) {
               totalRead += lastRead;
            }
         }
      }

      return totalRead;
   }

   static int skip(InputStream source, int skip) throws IOException {
      if (skip <= 0) {
         return 0;
      } else {
         int toSkip = skip - (int)source.skip((long)skip);
         boolean more = true;

         while(toSkip > 0 && more) {
            int read = source.read();
            if (read == -1) {
               more = false;
            } else {
               --toSkip;
               toSkip = (int)((long)toSkip - source.skip((long)toSkip));
            }
         }

         int skipped = skip - toSkip;
         return skipped;
      }
   }
}
