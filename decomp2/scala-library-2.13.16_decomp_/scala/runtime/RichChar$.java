package scala.runtime;

import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.package$;

public final class RichChar$ {
   public static final RichChar$ MODULE$ = new RichChar$();

   public final Numeric.CharIsIntegral$ num$extension(final char $this) {
      return Numeric.CharIsIntegral$.MODULE$;
   }

   public final Ordering.Char$ ord$extension(final char $this) {
      return Ordering.Char$.MODULE$;
   }

   public final double doubleValue$extension(final char $this) {
      return (double)$this;
   }

   public final float floatValue$extension(final char $this) {
      return (float)$this;
   }

   public final long longValue$extension(final char $this) {
      return (long)$this;
   }

   public final int intValue$extension(final char $this) {
      return $this;
   }

   public final byte byteValue$extension(final char $this) {
      return (byte)$this;
   }

   public final short shortValue$extension(final char $this) {
      return (short)$this;
   }

   public final boolean isValidChar$extension(final char $this) {
      return true;
   }

   public final char abs$extension(final char $this) {
      return $this;
   }

   public final char max$extension(final char $this, final char that) {
      package$ var10000 = package$.MODULE$;
      return (char)Math.max($this, that);
   }

   public final char min$extension(final char $this, final char that) {
      package$ var10000 = package$.MODULE$;
      return (char)Math.min($this, that);
   }

   public final int asDigit$extension(final char $this) {
      return Character.digit($this, 36);
   }

   public final boolean isControl$extension(final char $this) {
      return Character.isISOControl($this);
   }

   public final boolean isDigit$extension(final char $this) {
      return Character.isDigit($this);
   }

   public final boolean isLetter$extension(final char $this) {
      return Character.isLetter($this);
   }

   public final boolean isLetterOrDigit$extension(final char $this) {
      return Character.isLetterOrDigit($this);
   }

   public final boolean isWhitespace$extension(final char $this) {
      return Character.isWhitespace($this);
   }

   public final boolean isSpaceChar$extension(final char $this) {
      return Character.isSpaceChar($this);
   }

   public final boolean isHighSurrogate$extension(final char $this) {
      return Character.isHighSurrogate($this);
   }

   public final boolean isLowSurrogate$extension(final char $this) {
      return Character.isLowSurrogate($this);
   }

   public final boolean isSurrogate$extension(final char $this) {
      return Character.isHighSurrogate($this) || Character.isLowSurrogate($this);
   }

   public final boolean isUnicodeIdentifierStart$extension(final char $this) {
      return Character.isUnicodeIdentifierStart($this);
   }

   public final boolean isUnicodeIdentifierPart$extension(final char $this) {
      return Character.isUnicodeIdentifierPart($this);
   }

   public final boolean isIdentifierIgnorable$extension(final char $this) {
      return Character.isIdentifierIgnorable($this);
   }

   public final boolean isMirrored$extension(final char $this) {
      return Character.isMirrored($this);
   }

   public final boolean isLower$extension(final char $this) {
      return Character.isLowerCase($this);
   }

   public final boolean isUpper$extension(final char $this) {
      return Character.isUpperCase($this);
   }

   public final boolean isTitleCase$extension(final char $this) {
      return Character.isTitleCase($this);
   }

   public final char toLower$extension(final char $this) {
      return Character.toLowerCase($this);
   }

   public final char toUpper$extension(final char $this) {
      return Character.toUpperCase($this);
   }

   public final char toTitleCase$extension(final char $this) {
      return Character.toTitleCase($this);
   }

   public final int getType$extension(final char $this) {
      return Character.getType($this);
   }

   public final int getNumericValue$extension(final char $this) {
      return Character.getNumericValue($this);
   }

   public final byte getDirectionality$extension(final char $this) {
      return Character.getDirectionality($this);
   }

   public final char reverseBytes$extension(final char $this) {
      return Character.reverseBytes($this);
   }

   public final int hashCode$extension(final char $this) {
      return Character.hashCode($this);
   }

   public final boolean equals$extension(final char $this, final Object x$1) {
      if (x$1 instanceof RichChar) {
         char var3 = ((RichChar)x$1).self();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private RichChar$() {
   }
}
