package com.ibm.icu.message2;

import com.ibm.icu.text.ConstrainedFieldPosition;
import com.ibm.icu.text.FormattedValue;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.AttributedCharacterIterator;

/** @deprecated */
@Deprecated
public class PlainStringFormattedValue implements FormattedValue {
   private final String value;

   /** @deprecated */
   @Deprecated
   public PlainStringFormattedValue(String value) {
      if (value == null) {
         throw new IllegalAccessError("Should not try to wrap a null in a formatted value");
      } else {
         this.value = value;
      }
   }

   /** @deprecated */
   @Deprecated
   public int length() {
      return this.value == null ? 0 : this.value.length();
   }

   /** @deprecated */
   @Deprecated
   public char charAt(int index) {
      return this.value.charAt(index);
   }

   /** @deprecated */
   @Deprecated
   public CharSequence subSequence(int start, int end) {
      return this.value.subSequence(start, end);
   }

   /** @deprecated */
   @Deprecated
   public Appendable appendTo(Appendable appendable) {
      try {
         appendable.append(this.value);
         return appendable;
      } catch (IOException e) {
         throw new UncheckedIOException("problem appending", e);
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean nextPosition(ConstrainedFieldPosition cfpos) {
      throw new RuntimeException("nextPosition not yet implemented");
   }

   /** @deprecated */
   @Deprecated
   public AttributedCharacterIterator toCharacterIterator() {
      throw new RuntimeException("toCharacterIterator not yet implemented");
   }

   /** @deprecated */
   @Deprecated
   public String toString() {
      return this.value;
   }
}
