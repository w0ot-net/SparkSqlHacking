package com.ibm.icu.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CodePointMap implements Iterable {
   protected CodePointMap() {
   }

   public abstract int get(int var1);

   public abstract boolean getRange(int var1, ValueFilter var2, Range var3);

   public boolean getRange(int start, RangeOption option, int surrogateValue, ValueFilter filter, Range range) {
      assert option != null;

      if (!this.getRange(start, filter, range)) {
         return false;
      } else if (option == CodePointMap.RangeOption.NORMAL) {
         return true;
      } else {
         int surrEnd = option == CodePointMap.RangeOption.FIXED_ALL_SURROGATES ? '\udfff' : '\udbff';
         int end = range.end;
         if (end >= 55295 && start <= surrEnd) {
            if (range.value == surrogateValue) {
               if (end >= surrEnd) {
                  return true;
               }
            } else {
               if (start <= 55295) {
                  range.end = 55295;
                  return true;
               }

               range.value = surrogateValue;
               if (end > surrEnd) {
                  range.end = surrEnd;
                  return true;
               }
            }

            if (this.getRange(surrEnd + 1, filter, range) && range.value == surrogateValue) {
               range.start = start;
               return true;
            } else {
               range.start = start;
               range.end = surrEnd;
               range.value = surrogateValue;
               return true;
            }
         } else {
            return true;
         }
      }
   }

   public Iterator iterator() {
      return new RangeIterator();
   }

   public StringIterator stringIterator(CharSequence s, int sIndex) {
      return new StringIterator(s, sIndex);
   }

   public static enum RangeOption {
      NORMAL,
      FIXED_LEAD_SURROGATES,
      FIXED_ALL_SURROGATES;
   }

   public static final class Range {
      private int start;
      private int end;
      private int value;

      public Range() {
         this.start = this.end = -1;
         this.value = 0;
      }

      public int getStart() {
         return this.start;
      }

      public int getEnd() {
         return this.end;
      }

      public int getValue() {
         return this.value;
      }

      public void set(int start, int end, int value) {
         this.start = start;
         this.end = end;
         this.value = value;
      }
   }

   private final class RangeIterator implements Iterator {
      private Range range;

      private RangeIterator() {
         this.range = new Range();
      }

      public boolean hasNext() {
         return -1 <= this.range.end && this.range.end < 1114111;
      }

      public Range next() {
         if (CodePointMap.this.getRange(this.range.end + 1, (ValueFilter)null, this.range)) {
            return this.range;
         } else {
            throw new NoSuchElementException();
         }
      }

      public final void remove() {
         throw new UnsupportedOperationException();
      }
   }

   public class StringIterator {
      /** @deprecated */
      @Deprecated
      protected CharSequence s;
      /** @deprecated */
      @Deprecated
      protected int sIndex;
      /** @deprecated */
      @Deprecated
      protected int c;
      /** @deprecated */
      @Deprecated
      protected int value;

      /** @deprecated */
      @Deprecated
      protected StringIterator(CharSequence s, int sIndex) {
         this.s = s;
         this.sIndex = sIndex;
         this.c = -1;
         this.value = 0;
      }

      public void reset(CharSequence s, int sIndex) {
         this.s = s;
         this.sIndex = sIndex;
         this.c = -1;
         this.value = 0;
      }

      public boolean next() {
         if (this.sIndex >= this.s.length()) {
            return false;
         } else {
            this.c = Character.codePointAt(this.s, this.sIndex);
            this.sIndex += Character.charCount(this.c);
            this.value = CodePointMap.this.get(this.c);
            return true;
         }
      }

      public boolean previous() {
         if (this.sIndex <= 0) {
            return false;
         } else {
            this.c = Character.codePointBefore(this.s, this.sIndex);
            this.sIndex -= Character.charCount(this.c);
            this.value = CodePointMap.this.get(this.c);
            return true;
         }
      }

      public final int getIndex() {
         return this.sIndex;
      }

      public final int getCodePoint() {
         return this.c;
      }

      public final int getValue() {
         return this.value;
      }
   }

   public interface ValueFilter {
      int apply(int var1);
   }
}
