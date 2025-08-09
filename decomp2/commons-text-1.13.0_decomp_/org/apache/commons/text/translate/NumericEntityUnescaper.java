package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import org.apache.commons.lang3.ArrayUtils;

public class NumericEntityUnescaper extends CharSequenceTranslator {
   private static final EnumSet DEFAULT_OPTIONS;
   private final EnumSet options;

   public NumericEntityUnescaper(OPTION... options) {
      this.options = ArrayUtils.isEmpty(options) ? DEFAULT_OPTIONS : EnumSet.copyOf(Arrays.asList(options));
   }

   public boolean isSet(OPTION option) {
      return this.options.contains(option);
   }

   public int translate(CharSequence input, int index, Writer writer) throws IOException {
      int seqEnd = input.length();
      if (input.charAt(index) == '&' && index < seqEnd - 2 && input.charAt(index + 1) == '#') {
         int start = index + 2;
         boolean isHex = false;
         char firstChar = input.charAt(start);
         if (firstChar == 'x' || firstChar == 'X') {
            ++start;
            isHex = true;
            if (start == seqEnd) {
               return 0;
            }
         }

         int end;
         for(end = start; end < seqEnd && (input.charAt(end) >= '0' && input.charAt(end) <= '9' || input.charAt(end) >= 'a' && input.charAt(end) <= 'f' || input.charAt(end) >= 'A' && input.charAt(end) <= 'F'); ++end) {
         }

         boolean semiNext = end != seqEnd && input.charAt(end) == ';';
         if (!semiNext) {
            if (this.isSet(NumericEntityUnescaper.OPTION.semiColonRequired)) {
               return 0;
            }

            if (this.isSet(NumericEntityUnescaper.OPTION.errorIfNoSemiColon)) {
               throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
            }
         }

         int entityValue;
         try {
            if (isHex) {
               entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 16);
            } else {
               entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 10);
            }
         } catch (NumberFormatException var12) {
            return 0;
         }

         if (entityValue > 65535) {
            char[] chrs = Character.toChars(entityValue);
            writer.write(chrs[0]);
            writer.write(chrs[1]);
         } else {
            writer.write(entityValue);
         }

         return 2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
      } else {
         return 0;
      }
   }

   static {
      DEFAULT_OPTIONS = EnumSet.copyOf(Collections.singletonList(NumericEntityUnescaper.OPTION.semiColonRequired));
   }

   public static enum OPTION {
      semiColonRequired,
      semiColonOptional,
      errorIfNoSemiColon;

      // $FF: synthetic method
      private static OPTION[] $values() {
         return new OPTION[]{semiColonRequired, semiColonOptional, errorIfNoSemiColon};
      }
   }
}
