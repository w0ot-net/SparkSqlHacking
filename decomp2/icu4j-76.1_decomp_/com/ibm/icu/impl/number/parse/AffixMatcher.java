package com.ibm.icu.impl.number.parse;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.impl.StringSegment;
import com.ibm.icu.impl.number.AffixPatternProvider;
import com.ibm.icu.impl.number.AffixUtils;
import com.ibm.icu.impl.number.PatternStringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class AffixMatcher implements NumberParseMatcher {
   private final AffixPatternMatcher prefix;
   private final AffixPatternMatcher suffix;
   private final int flags;
   public static final Comparator COMPARATOR = new Comparator() {
      public int compare(AffixMatcher lhs, AffixMatcher rhs) {
         if (AffixMatcher.length(lhs.prefix) != AffixMatcher.length(rhs.prefix)) {
            return AffixMatcher.length(lhs.prefix) > AffixMatcher.length(rhs.prefix) ? -1 : 1;
         } else if (AffixMatcher.length(lhs.suffix) != AffixMatcher.length(rhs.suffix)) {
            return AffixMatcher.length(lhs.suffix) > AffixMatcher.length(rhs.suffix) ? -1 : 1;
         } else if (!lhs.equals(rhs)) {
            return lhs.hashCode() > rhs.hashCode() ? -1 : 1;
         } else {
            return 0;
         }
      }
   };

   private static boolean isInteresting(AffixPatternProvider patternInfo, IgnorablesMatcher ignorables, int parseFlags) {
      String posPrefixString = patternInfo.getString(256);
      String posSuffixString = patternInfo.getString(0);
      String negPrefixString = null;
      String negSuffixString = null;
      if (patternInfo.hasNegativeSubpattern()) {
         negPrefixString = patternInfo.getString(768);
         negSuffixString = patternInfo.getString(512);
      }

      return 0 != (parseFlags & 256) || !AffixUtils.containsOnlySymbolsAndIgnorables(posPrefixString, ignorables.getSet()) || !AffixUtils.containsOnlySymbolsAndIgnorables(posSuffixString, ignorables.getSet()) || !AffixUtils.containsOnlySymbolsAndIgnorables(negPrefixString, ignorables.getSet()) || !AffixUtils.containsOnlySymbolsAndIgnorables(negSuffixString, ignorables.getSet()) || AffixUtils.containsType(posSuffixString, -2) || AffixUtils.containsType(posSuffixString, -1) || AffixUtils.containsType(negSuffixString, -2) || AffixUtils.containsType(negSuffixString, -1);
   }

   public static void createMatchers(AffixPatternProvider patternInfo, NumberParserImpl output, AffixTokenMatcherFactory factory, IgnorablesMatcher ignorables, int parseFlags) {
      if (isInteresting(patternInfo, ignorables, parseFlags)) {
         StringBuilder sb = new StringBuilder();
         ArrayList<AffixMatcher> matchers = new ArrayList(6);
         boolean includeUnpaired = 0 != (parseFlags & 128);
         AffixPatternMatcher posPrefix = null;
         AffixPatternMatcher posSuffix = null;

         for(PatternStringUtils.PatternSignType type : PatternStringUtils.PatternSignType.VALUES) {
            if ((type != PatternStringUtils.PatternSignType.POS || 0 == (parseFlags & 1024)) && (type != PatternStringUtils.PatternSignType.POS_SIGN || 0 != (parseFlags & 1024))) {
               PatternStringUtils.patternInfoToStringBuilder(patternInfo, true, type, false, StandardPlural.OTHER, false, sb);
               AffixPatternMatcher prefix = AffixPatternMatcher.fromAffixPattern(sb.toString(), factory, parseFlags);
               PatternStringUtils.patternInfoToStringBuilder(patternInfo, false, type, false, StandardPlural.OTHER, false, sb);
               AffixPatternMatcher suffix = AffixPatternMatcher.fromAffixPattern(sb.toString(), factory, parseFlags);
               if (type == PatternStringUtils.PatternSignType.POS) {
                  posPrefix = prefix;
                  posSuffix = suffix;
               } else if (Objects.equals(prefix, posPrefix) && Objects.equals(suffix, posSuffix)) {
                  continue;
               }

               int flags = type == PatternStringUtils.PatternSignType.NEG ? 1 : 0;
               matchers.add(getInstance(prefix, suffix, flags));
               if (includeUnpaired && prefix != null && suffix != null) {
                  if (type == PatternStringUtils.PatternSignType.POS || !Objects.equals(prefix, posPrefix)) {
                     matchers.add(getInstance(prefix, (AffixPatternMatcher)null, flags));
                  }

                  if (type == PatternStringUtils.PatternSignType.POS || !Objects.equals(suffix, posSuffix)) {
                     matchers.add(getInstance((AffixPatternMatcher)null, suffix, flags));
                  }
               }
            }
         }

         Collections.sort(matchers, COMPARATOR);
         output.addMatchers(matchers);
      }
   }

   private static final AffixMatcher getInstance(AffixPatternMatcher prefix, AffixPatternMatcher suffix, int flags) {
      return new AffixMatcher(prefix, suffix, flags);
   }

   private AffixMatcher(AffixPatternMatcher prefix, AffixPatternMatcher suffix, int flags) {
      this.prefix = prefix;
      this.suffix = suffix;
      this.flags = flags;
   }

   public boolean match(StringSegment segment, ParsedNumber result) {
      if (!result.seenNumber()) {
         if (result.prefix == null && this.prefix != null) {
            int initialOffset = segment.getOffset();
            boolean maybeMore = this.prefix.match(segment, result);
            if (initialOffset != segment.getOffset()) {
               result.prefix = this.prefix.getPattern();
            }

            return maybeMore;
         } else {
            return false;
         }
      } else if (result.suffix == null && this.suffix != null && matched(this.prefix, result.prefix)) {
         int initialOffset = segment.getOffset();
         boolean maybeMore = this.suffix.match(segment, result);
         if (initialOffset != segment.getOffset()) {
            result.suffix = this.suffix.getPattern();
         }

         return maybeMore;
      } else {
         return false;
      }
   }

   public boolean smokeTest(StringSegment segment) {
      return this.prefix != null && this.prefix.smokeTest(segment) || this.suffix != null && this.suffix.smokeTest(segment);
   }

   public void postProcess(ParsedNumber result) {
      if (matched(this.prefix, result.prefix) && matched(this.suffix, result.suffix)) {
         if (result.prefix == null) {
            result.prefix = "";
         }

         if (result.suffix == null) {
            result.suffix = "";
         }

         result.flags |= this.flags;
         if (this.prefix != null) {
            this.prefix.postProcess(result);
         }

         if (this.suffix != null) {
            this.suffix.postProcess(result);
         }
      }

   }

   static boolean matched(AffixPatternMatcher affix, String patternString) {
      return affix == null && patternString == null || affix != null && affix.getPattern().equals(patternString);
   }

   private static int length(AffixPatternMatcher matcher) {
      return matcher == null ? 0 : matcher.getPattern().length();
   }

   public boolean equals(Object _other) {
      if (!(_other instanceof AffixMatcher)) {
         return false;
      } else {
         AffixMatcher other = (AffixMatcher)_other;
         return Objects.equals(this.prefix, other.prefix) && Objects.equals(this.suffix, other.suffix) && this.flags == other.flags;
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.prefix) ^ Objects.hashCode(this.suffix) ^ this.flags;
   }

   public String toString() {
      boolean isNegative = 0 != (this.flags & 1);
      return "<AffixMatcher" + (isNegative ? ":negative " : " ") + this.prefix + "#" + this.suffix + ">";
   }
}
