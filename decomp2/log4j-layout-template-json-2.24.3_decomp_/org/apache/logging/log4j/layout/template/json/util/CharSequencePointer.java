package org.apache.logging.log4j.layout.template.json.util;

import java.util.Objects;
import java.util.stream.IntStream;

public final class CharSequencePointer implements CharSequence {
   private CharSequence delegate;
   private int startIndex;
   private int length = -1;

   public void reset(final CharSequence delegate, final int startIndex, final int endIndex) {
      Objects.requireNonNull(delegate, "delegate");
      this.delegate = delegate;
      if (startIndex < 0) {
         throw new IndexOutOfBoundsException("invalid start: " + startIndex);
      } else if (endIndex > delegate.length()) {
         throw new IndexOutOfBoundsException("invalid end: " + endIndex);
      } else {
         this.length = Math.subtractExact(endIndex, startIndex);
         if (this.length < 0) {
            throw new IndexOutOfBoundsException("invalid length: " + this.length);
         } else {
            this.delegate = delegate;
            this.startIndex = startIndex;
         }
      }
   }

   public int length() {
      this.requireReset();
      return this.length;
   }

   public char charAt(final int startIndex) {
      this.requireReset();
      int delegateStartIndex = Math.addExact(this.startIndex, startIndex);
      return this.delegate.charAt(delegateStartIndex);
   }

   public CharSequence subSequence(final int startIndex, final int endIndex) {
      throw new UnsupportedOperationException("operation requires allocation, contradicting with the purpose of the class");
   }

   public IntStream chars() {
      throw new UnsupportedOperationException("operation requires allocation, contradicting with the purpose of the class");
   }

   public IntStream codePoints() {
      throw new UnsupportedOperationException("operation requires allocation, contradicting with the purpose of the class");
   }

   public String toString() {
      this.requireReset();
      int endIndex = Math.addExact(this.startIndex, this.length);
      return this.delegate.toString().substring(this.startIndex, endIndex);
   }

   private void requireReset() {
      if (this.length < 0) {
         throw new IllegalStateException("pointer must be reset first");
      }
   }
}
