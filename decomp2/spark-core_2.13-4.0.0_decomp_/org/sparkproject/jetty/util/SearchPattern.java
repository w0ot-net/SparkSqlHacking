package org.sparkproject.jetty.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SearchPattern {
   private static final int ALPHABET_SIZE = 256;
   private final int[] table = new int[256];
   private final byte[] pattern;

   public static SearchPattern compile(byte[] pattern) {
      return new SearchPattern(Arrays.copyOf(pattern, pattern.length));
   }

   public static SearchPattern compile(String pattern) {
      return new SearchPattern(pattern.getBytes(StandardCharsets.UTF_8));
   }

   private SearchPattern(byte[] pattern) {
      this.pattern = pattern;
      if (pattern.length == 0) {
         throw new IllegalArgumentException("Empty Pattern");
      } else {
         Arrays.fill(this.table, pattern.length);

         for(int i = 0; i < pattern.length - 1; ++i) {
            this.table[255 & pattern[i]] = pattern.length - 1 - i;
         }

      }
   }

   public int match(byte[] data, int offset, int length) {
      this.validateArgs(data, offset, length);

      for(int skip = offset; skip <= offset + length - this.pattern.length; skip += this.table[255 & data[skip + this.pattern.length - 1]]) {
         for(int i = this.pattern.length - 1; data[skip + i] == this.pattern[i]; --i) {
            if (i == 0) {
               return skip;
            }
         }
      }

      return -1;
   }

   public int endsWith(byte[] data, int offset, int length) {
      this.validateArgs(data, offset, length);

      for(int skip = this.pattern.length <= length ? offset + length - this.pattern.length : offset; skip < offset + length; ++skip) {
         for(int i = offset + length - 1 - skip; data[skip + i] == this.pattern[i]; --i) {
            if (i == 0) {
               return offset + length - skip;
            }
         }
      }

      return 0;
   }

   public int startsWith(byte[] data, int offset, int length, int matched) {
      this.validateArgs(data, offset, length);
      int matchedCount = 0;

      for(int i = 0; i < this.pattern.length - matched && i < length; ++i) {
         if (data[offset + i] != this.pattern[i + matched]) {
            return 0;
         }

         ++matchedCount;
      }

      return matched + matchedCount;
   }

   private void validateArgs(byte[] data, int offset, int length) {
      if (offset < 0) {
         throw new IllegalArgumentException("offset was negative");
      } else if (length < 0) {
         throw new IllegalArgumentException("length was negative");
      } else if (offset + length > data.length) {
         throw new IllegalArgumentException("(offset+length) out of bounds of data[]");
      }
   }

   public int getLength() {
      return this.pattern.length;
   }
}
