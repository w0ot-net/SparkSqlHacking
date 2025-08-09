package org.sparkproject.guava.escape;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class CharEscaperBuilder {
   private final Map map = new HashMap();
   private int max = -1;

   @CanIgnoreReturnValue
   public CharEscaperBuilder addEscape(char c, String r) {
      this.map.put(c, (String)Preconditions.checkNotNull(r));
      if (c > this.max) {
         this.max = c;
      }

      return this;
   }

   @CanIgnoreReturnValue
   public CharEscaperBuilder addEscapes(char[] cs, String r) {
      Preconditions.checkNotNull(r);

      for(char c : cs) {
         this.addEscape(c, r);
      }

      return this;
   }

   public char[] @Nullable [] toArray() {
      char[][] result = new char[this.max + 1][];

      for(Map.Entry entry : this.map.entrySet()) {
         result[(Character)entry.getKey()] = ((String)entry.getValue()).toCharArray();
      }

      return result;
   }

   public Escaper toEscaper() {
      return new CharArrayDecorator(this.toArray());
   }

   private static class CharArrayDecorator extends CharEscaper {
      private final char[] @Nullable [] replacements;
      private final int replaceLength;

      CharArrayDecorator(char[] @Nullable [] replacements) {
         this.replacements = replacements;
         this.replaceLength = replacements.length;
      }

      public String escape(String s) {
         int slen = s.length();

         for(int index = 0; index < slen; ++index) {
            char c = s.charAt(index);
            if (c < this.replacements.length && this.replacements[c] != null) {
               return this.escapeSlow(s, index);
            }
         }

         return s;
      }

      @CheckForNull
      protected char[] escape(char c) {
         return c < this.replaceLength ? this.replacements[c] : null;
      }
   }
}
