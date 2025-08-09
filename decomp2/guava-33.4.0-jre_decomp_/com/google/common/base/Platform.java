package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
final class Platform {
   private static final Logger logger = Logger.getLogger(Platform.class.getName());
   private static final PatternCompiler patternCompiler = loadPatternCompiler();

   private Platform() {
   }

   static CharMatcher precomputeCharMatcher(CharMatcher matcher) {
      return matcher.precomputedInternal();
   }

   static Optional getEnumIfPresent(Class enumClass, String value) {
      WeakReference<? extends Enum<?>> ref = (WeakReference)Enums.getEnumConstants(enumClass).get(value);
      return ref == null ? Optional.absent() : Optional.fromNullable((Enum)enumClass.cast(ref.get()));
   }

   static String formatCompact4Digits(double value) {
      return String.format(Locale.ROOT, "%.4g", value);
   }

   static boolean stringIsNullOrEmpty(@CheckForNull String string) {
      return string == null || string.isEmpty();
   }

   static String nullToEmpty(@CheckForNull String string) {
      return string == null ? "" : string;
   }

   @CheckForNull
   static String emptyToNull(@CheckForNull String string) {
      return stringIsNullOrEmpty(string) ? null : string;
   }

   static CommonPattern compilePattern(String pattern) {
      Preconditions.checkNotNull(pattern);
      return patternCompiler.compile(pattern);
   }

   static boolean patternCompilerIsPcreLike() {
      return patternCompiler.isPcreLike();
   }

   private static PatternCompiler loadPatternCompiler() {
      return new JdkPatternCompiler();
   }

   private static void logPatternCompilerError(ServiceConfigurationError e) {
      logger.log(Level.WARNING, "Error loading regex compiler, falling back to next option", e);
   }

   private static final class JdkPatternCompiler implements PatternCompiler {
      private JdkPatternCompiler() {
      }

      public CommonPattern compile(String pattern) {
         return new JdkPattern(Pattern.compile(pattern));
      }

      public boolean isPcreLike() {
         return true;
      }
   }
}
