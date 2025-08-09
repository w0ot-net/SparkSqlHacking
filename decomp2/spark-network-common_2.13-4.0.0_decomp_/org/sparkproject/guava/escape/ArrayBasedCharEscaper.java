package org.sparkproject.guava.escape;

import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ArrayBasedCharEscaper extends CharEscaper {
   private final char[][] replacements;
   private final int replacementsLength;
   private final char safeMin;
   private final char safeMax;

   protected ArrayBasedCharEscaper(Map replacementMap, char safeMin, char safeMax) {
      this(ArrayBasedEscaperMap.create(replacementMap), safeMin, safeMax);
   }

   protected ArrayBasedCharEscaper(ArrayBasedEscaperMap escaperMap, char safeMin, char safeMax) {
      Preconditions.checkNotNull(escaperMap);
      this.replacements = escaperMap.getReplacementArray();
      this.replacementsLength = this.replacements.length;
      if (safeMax < safeMin) {
         safeMax = 0;
         safeMin = '\uffff';
      }

      this.safeMin = safeMin;
      this.safeMax = safeMax;
   }

   public final String escape(String s) {
      Preconditions.checkNotNull(s);

      for(int i = 0; i < s.length(); ++i) {
         char c = s.charAt(i);
         if (c < this.replacementsLength && this.replacements[c] != null || c > this.safeMax || c < this.safeMin) {
            return this.escapeSlow(s, i);
         }
      }

      return s;
   }

   @CheckForNull
   protected final char[] escape(char c) {
      if (c < this.replacementsLength) {
         char[] chars = this.replacements[c];
         if (chars != null) {
            return chars;
         }
      }

      return c >= this.safeMin && c <= this.safeMax ? null : this.escapeUnsafe(c);
   }

   @CheckForNull
   protected abstract char[] escapeUnsafe(char c);
}
