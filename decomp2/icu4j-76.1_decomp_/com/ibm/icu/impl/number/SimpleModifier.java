package com.ibm.icu.impl.number;

import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.number.range.PrefixInfixSuffixLengthHelper;
import com.ibm.icu.util.ICUException;
import java.text.Format;

public class SimpleModifier implements Modifier {
   private final String compiledPattern;
   private final Format.Field field;
   private final boolean strong;
   private final Modifier.Parameters parameters;
   private static final int ARG_NUM_LIMIT = 256;

   public SimpleModifier(String compiledPattern, Format.Field field, boolean strong) {
      this(compiledPattern, field, strong, (Modifier.Parameters)null);
   }

   public SimpleModifier(String compiledPattern, Format.Field field, boolean strong, Modifier.Parameters parameters) {
      assert compiledPattern != null;

      this.compiledPattern = compiledPattern;
      this.field = field;
      this.strong = strong;
      this.parameters = parameters;
   }

   public int apply(FormattedStringBuilder output, int leftIndex, int rightIndex) {
      return SimpleFormatterImpl.formatPrefixSuffix(this.compiledPattern, this.field, leftIndex, rightIndex, output);
   }

   public int getPrefixLength() {
      return SimpleFormatterImpl.getPrefixLength(this.compiledPattern);
   }

   public int getCodePointCount() {
      return SimpleFormatterImpl.getLength(this.compiledPattern, true);
   }

   public boolean isStrong() {
      return this.strong;
   }

   public boolean containsField(Format.Field field) {
      assert false;

      return false;
   }

   public Modifier.Parameters getParameters() {
      return this.parameters;
   }

   public boolean strictEquals(Modifier other) {
      if (!(other instanceof SimpleModifier)) {
         return false;
      } else {
         SimpleModifier _other = (SimpleModifier)other;
         return this.compiledPattern.equals(_other.compiledPattern) && this.field == _other.field && this.strong == _other.strong;
      }
   }

   public static void formatTwoArgPattern(String compiledPattern, FormattedStringBuilder result, int index, PrefixInfixSuffixLengthHelper h, Format.Field field) {
      int argLimit = SimpleFormatterImpl.getArgumentLimit(compiledPattern);
      if (argLimit != 2) {
         throw new ICUException();
      } else {
         int offset = 1;
         int length = 0;
         int prefixLength = compiledPattern.charAt(offset);
         ++offset;
         if (prefixLength < 256) {
            prefixLength = 0;
         } else {
            prefixLength -= 256;
            result.insert(index + length, compiledPattern, offset, offset + prefixLength, field);
            offset += prefixLength;
            length += prefixLength;
            ++offset;
         }

         int infixLength = compiledPattern.charAt(offset);
         ++offset;
         if (infixLength < 256) {
            infixLength = 0;
         } else {
            infixLength -= 256;
            result.insert(index + length, compiledPattern, offset, offset + infixLength, field);
            offset += infixLength;
            length += infixLength;
            ++offset;
         }

         int suffixLength;
         if (offset == compiledPattern.length()) {
            suffixLength = 0;
         } else {
            suffixLength = compiledPattern.charAt(offset) - 256;
            ++offset;
            result.insert(index + length, compiledPattern, offset, offset + suffixLength, field);
            int var10000 = length + suffixLength;
         }

         h.lengthPrefix = prefixLength;
         h.lengthInfix = infixLength;
         h.lengthSuffix = suffixLength;
      }
   }
}
