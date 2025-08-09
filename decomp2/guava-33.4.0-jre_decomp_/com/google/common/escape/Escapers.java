package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Escapers {
   private static final Escaper NULL_ESCAPER = new CharEscaper() {
      public String escape(String string) {
         return (String)Preconditions.checkNotNull(string);
      }

      @CheckForNull
      protected char[] escape(char c) {
         return null;
      }
   };

   private Escapers() {
   }

   public static Escaper nullEscaper() {
      return NULL_ESCAPER;
   }

   public static Builder builder() {
      return new Builder();
   }

   @CheckForNull
   public static String computeReplacement(CharEscaper escaper, char c) {
      return stringOrNull(escaper.escape(c));
   }

   @CheckForNull
   public static String computeReplacement(UnicodeEscaper escaper, int cp) {
      return stringOrNull(escaper.escape(cp));
   }

   @CheckForNull
   private static String stringOrNull(@CheckForNull char[] in) {
      return in == null ? null : new String(in);
   }

   public static final class Builder {
      private final Map replacementMap;
      private char safeMin;
      private char safeMax;
      @CheckForNull
      private String unsafeReplacement;

      private Builder() {
         this.replacementMap = new HashMap();
         this.safeMin = 0;
         this.safeMax = '\uffff';
         this.unsafeReplacement = null;
      }

      @CanIgnoreReturnValue
      public Builder setSafeRange(char safeMin, char safeMax) {
         this.safeMin = safeMin;
         this.safeMax = safeMax;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder setUnsafeReplacement(@Nullable String unsafeReplacement) {
         this.unsafeReplacement = unsafeReplacement;
         return this;
      }

      @CanIgnoreReturnValue
      public Builder addEscape(char c, String replacement) {
         Preconditions.checkNotNull(replacement);
         this.replacementMap.put(c, replacement);
         return this;
      }

      public Escaper build() {
         return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) {
            @CheckForNull
            private final char[] replacementChars;

            {
               this.replacementChars = Builder.this.unsafeReplacement != null ? Builder.this.unsafeReplacement.toCharArray() : null;
            }

            @CheckForNull
            protected char[] escapeUnsafe(char c) {
               return this.replacementChars;
            }
         };
      }
   }
}
