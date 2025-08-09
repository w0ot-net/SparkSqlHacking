package org.apache.spark.sql.catalyst.util;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
import com.ibm.icu.text.StringSearch;
import com.ibm.icu.util.ULocale;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.spark.unsafe.UTF8StringBuilder;
import org.apache.spark.unsafe.types.UTF8String;

public class CollationAwareUTF8String {
   private static final int MATCH_NOT_FOUND = -1;
   private static final int COMBINED_ASCII_SMALL_I_COMBINING_DOT = 6882055;
   private static final HashMap codepointOneToManyTitleCaseLookupTable = new HashMap() {
      {
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i <= 1114111; ++i) {
            sb.appendCodePoint(i);
            String titleCase = UCharacter.toTitleCase(sb.toString(), (BreakIterator)null);
            if (titleCase.codePointCount(0, titleCase.length()) > 1) {
               this.put(i, titleCase);
            }

            sb.setLength(0);
         }

      }
   };

   private static boolean lowercaseMatchFrom(UTF8String target, UTF8String lowercasePattern, int startPos) {
      return lowercaseMatchLengthFrom(target, lowercasePattern, startPos) != -1;
   }

   private static int lowercaseMatchLengthFrom(UTF8String target, UTF8String lowercasePattern, int startPos) {
      assert startPos >= 0;

      Iterator<Integer> targetIterator = target.codePointIterator();
      Iterator<Integer> patternIterator = lowercasePattern.codePointIterator();

      for(int i = 0; i < startPos; ++i) {
         if (!targetIterator.hasNext()) {
            return -1;
         }

         targetIterator.next();
      }

      int matchLength = 0;
      int codePointBuffer = -1;

      while((targetIterator.hasNext() || codePointBuffer != -1) && patternIterator.hasNext()) {
         int targetCodePoint;
         if (codePointBuffer != -1) {
            targetCodePoint = codePointBuffer;
            codePointBuffer = -1;
         } else {
            targetCodePoint = getLowercaseCodePoint((Integer)targetIterator.next());
            if (targetCodePoint == 6882055) {
               targetCodePoint = 105;
               codePointBuffer = 775;
            }

            ++matchLength;
         }

         int patternCodePoint = (Integer)patternIterator.next();
         if (targetCodePoint != patternCodePoint) {
            return -1;
         }
      }

      return !patternIterator.hasNext() && codePointBuffer == -1 ? matchLength : -1;
   }

   private static int lowercaseFind(UTF8String target, UTF8String lowercasePattern, int startPos) {
      assert startPos >= 0;

      for(int i = startPos; i <= target.numChars(); ++i) {
         if (lowercaseMatchFrom(target, lowercasePattern, i)) {
            return i;
         }
      }

      return -1;
   }

   private static boolean lowercaseMatchUntil(UTF8String target, UTF8String lowercasePattern, int endPos) {
      return lowercaseMatchLengthUntil(target, lowercasePattern, endPos) != -1;
   }

   private static int lowercaseMatchLengthUntil(UTF8String target, UTF8String lowercasePattern, int endPos) {
      assert endPos >= 0;

      Iterator<Integer> targetIterator = target.reverseCodePointIterator();
      Iterator<Integer> patternIterator = lowercasePattern.reverseCodePointIterator();

      for(int i = endPos; i < target.numChars(); ++i) {
         if (!targetIterator.hasNext()) {
            return -1;
         }

         targetIterator.next();
      }

      int matchLength = 0;
      int codePointBuffer = -1;

      while((targetIterator.hasNext() || codePointBuffer != -1) && patternIterator.hasNext()) {
         int targetCodePoint;
         if (codePointBuffer != -1) {
            targetCodePoint = codePointBuffer;
            codePointBuffer = -1;
         } else {
            targetCodePoint = getLowercaseCodePoint((Integer)targetIterator.next());
            if (targetCodePoint == 6882055) {
               targetCodePoint = 775;
               codePointBuffer = 105;
            }

            ++matchLength;
         }

         int patternCodePoint = (Integer)patternIterator.next();
         if (targetCodePoint != patternCodePoint) {
            return -1;
         }
      }

      return !patternIterator.hasNext() && codePointBuffer == -1 ? matchLength : -1;
   }

   private static int lowercaseRFind(UTF8String target, UTF8String lowercasePattern, int endPos) {
      assert endPos <= target.numChars();

      for(int i = endPos; i >= 0; --i) {
         if (lowercaseMatchUntil(target, lowercasePattern, i)) {
            return i;
         }
      }

      return -1;
   }

   public static int compareLowerCase(UTF8String left, UTF8String right) {
      return left.isFullAscii() && right.isFullAscii() ? compareLowerCaseAscii(left, right) : compareLowerCaseSlow(left, right);
   }

   private static int compareLowerCaseAscii(UTF8String left, UTF8String right) {
      int leftBytes = left.numBytes();
      int rightBytes = right.numBytes();

      for(int curr = 0; curr < leftBytes && curr < rightBytes; ++curr) {
         int lowerLeftByte = Character.toLowerCase(left.getByte(curr));
         int lowerRightByte = Character.toLowerCase(right.getByte(curr));
         if (lowerLeftByte != lowerRightByte) {
            return lowerLeftByte - lowerRightByte;
         }
      }

      return leftBytes - rightBytes;
   }

   private static int compareLowerCaseSlow(UTF8String left, UTF8String right) {
      return lowerCaseCodePoints(left).binaryCompare(lowerCaseCodePoints(right));
   }

   public static UTF8String replace(UTF8String target, UTF8String search, UTF8String replace, int collationId) {
      if (target.numBytes() != 0 && search.numBytes() != 0) {
         String targetStr = target.toValidString();
         String searchStr = search.toValidString();
         StringSearch stringSearch = CollationFactory.getStringSearch(targetStr, searchStr, collationId);
         StringBuilder sb = new StringBuilder();
         int start = 0;

         for(int matchStart = stringSearch.first(); matchStart != -1; matchStart = stringSearch.next()) {
            sb.append(targetStr, start, matchStart);
            sb.append(replace.toValidString());
            start = matchStart + stringSearch.getMatchLength();
         }

         sb.append(targetStr, start, targetStr.length());
         return UTF8String.fromString(sb.toString());
      } else {
         return target;
      }
   }

   public static UTF8String lowercaseReplace(UTF8String target, UTF8String search, UTF8String replace) {
      if (target.numBytes() != 0 && search.numBytes() != 0) {
         UTF8String lowercaseSearch = lowerCaseCodePoints(search);
         int start = 0;
         int end = lowercaseFind(target, lowercaseSearch, start);
         if (end == -1) {
            return target;
         } else {
            int increase = Math.max(0, replace.numBytes() - search.numBytes()) * 16;

            UTF8StringBuilder buf;
            for(buf = new UTF8StringBuilder(target.numBytes() + increase); end != -1; end = lowercaseFind(target, lowercaseSearch, start)) {
               buf.append(target.substring(start, end));
               buf.append(replace);
               start = end + lowercaseMatchLengthFrom(target, lowercaseSearch, end);
            }

            buf.append(target.substring(start, target.numChars()));
            return buf.build();
         }
      } else {
         return target;
      }
   }

   public static UTF8String toUpperCase(UTF8String target) {
      return target.isFullAscii() ? target.toUpperCaseAscii() : toUpperCaseSlow(target);
   }

   private static UTF8String toUpperCaseSlow(UTF8String target) {
      return UTF8String.fromString(UCharacter.toUpperCase(target.toValidString()));
   }

   public static UTF8String toUpperCase(UTF8String target, int collationId) {
      return target.isFullAscii() ? target.toUpperCaseAscii() : toUpperCaseSlow(target, collationId);
   }

   private static UTF8String toUpperCaseSlow(UTF8String target, int collationId) {
      ULocale locale = CollationFactory.fetchCollation(collationId).getCollator().getLocale(ULocale.ACTUAL_LOCALE);
      return UTF8String.fromString(UCharacter.toUpperCase(locale, target.toValidString()));
   }

   public static UTF8String toLowerCase(UTF8String target) {
      return target.isFullAscii() ? target.toLowerCaseAscii() : toLowerCaseSlow(target);
   }

   private static UTF8String toLowerCaseSlow(UTF8String target) {
      return UTF8String.fromString(UCharacter.toLowerCase(target.toValidString()));
   }

   public static UTF8String toLowerCase(UTF8String target, int collationId) {
      return target.isFullAscii() ? target.toLowerCaseAscii() : toLowerCaseSlow(target, collationId);
   }

   private static UTF8String toLowerCaseSlow(UTF8String target, int collationId) {
      ULocale locale = CollationFactory.fetchCollation(collationId).getCollator().getLocale(ULocale.ACTUAL_LOCALE);
      return UTF8String.fromString(UCharacter.toLowerCase(locale, target.toValidString()));
   }

   private static void appendLowercaseCodePoint(int codePoint, StringBuilder sb) {
      int lowercaseCodePoint = getLowercaseCodePoint(codePoint);
      if (lowercaseCodePoint == 6882055) {
         sb.appendCodePoint(105);
         sb.appendCodePoint(775);
      } else {
         sb.appendCodePoint(lowercaseCodePoint);
      }

   }

   private static int getLowercaseCodePoint(int codePoint) {
      if (codePoint == 304) {
         return 6882055;
      } else {
         return codePoint == 962 ? 963 : UCharacter.toLowerCase(codePoint);
      }
   }

   public static UTF8String lowerCaseCodePoints(UTF8String target) {
      return target.isFullAscii() ? target.toLowerCaseAscii() : lowerCaseCodePointsSlow(target);
   }

   private static UTF8String lowerCaseCodePointsSlow(UTF8String target) {
      Iterator<Integer> targetIter = target.codePointIterator(UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_MAKE_VALID);
      StringBuilder sb = new StringBuilder();

      while(targetIter.hasNext()) {
         appendLowercaseCodePoint((Integer)targetIter.next(), sb);
      }

      return UTF8String.fromString(sb.toString());
   }

   public static UTF8String toTitleCase(UTF8String target) {
      return UTF8String.fromString(UCharacter.toTitleCase(target.toValidString(), BreakIterator.getWordInstance()));
   }

   public static UTF8String toTitleCase(UTF8String target, int collationId) {
      ULocale locale = CollationFactory.fetchCollation(collationId).getCollator().getLocale(ULocale.ACTUAL_LOCALE);
      return UTF8String.fromString(UCharacter.toTitleCase(locale, target.toValidString(), BreakIterator.getWordInstance(locale)));
   }

   public static UTF8String toTitleCaseICU(UTF8String source) {
      source = source.makeValid();
      UTF8StringBuilder sb = new UTF8StringBuilder();
      boolean isNewWord = true;
      boolean precededByCasedLetter = false;
      int offset = 0;

      for(int len = source.numBytes(); offset < len; offset += UTF8String.numBytesForFirstByte(source.getByte(offset))) {
         int codepoint = source.codePointFrom(offset);
         appendTitleCasedCodepoint(sb, codepoint, isNewWord, precededByCasedLetter, source, offset);
         isNewWord = codepoint == 32;
         if (!UCharacter.hasBinaryProperty(codepoint, 50)) {
            precededByCasedLetter = UCharacter.hasBinaryProperty(codepoint, 49);
         }
      }

      return sb.build();
   }

   private static void appendTitleCasedCodepoint(UTF8StringBuilder sb, int codepoint, boolean isAfterAsciiSpace, boolean precededByCasedLetter, UTF8String source, int offset) {
      if (isAfterAsciiSpace) {
         appendCodepointToTitleCase(sb, codepoint);
      } else if (codepoint == 931) {
         appendLowerCasedGreekCapitalSigma(sb, precededByCasedLetter, source, offset);
      } else if (codepoint == 304) {
         sb.appendCodePoint(105);
         sb.appendCodePoint(775);
      } else {
         sb.appendCodePoint(UCharacter.toLowerCase(codepoint));
      }
   }

   private static void appendLowerCasedGreekCapitalSigma(UTF8StringBuilder sb, boolean precededByCasedLetter, UTF8String source, int offset) {
      int codepoint = !followedByCasedLetter(source, offset) && precededByCasedLetter ? 962 : 963;
      sb.appendCodePoint(codepoint);
   }

   private static boolean followedByCasedLetter(UTF8String source, int offset) {
      offset += UTF8String.numBytesForFirstByte(source.getByte(offset));

      for(int len = source.numBytes(); offset < len; offset += UTF8String.numBytesForFirstByte(source.getByte(offset))) {
         int codepoint = source.codePointFrom(offset);
         if (!UCharacter.hasBinaryProperty(codepoint, 50)) {
            return UCharacter.hasBinaryProperty(codepoint, 49);
         }
      }

      return false;
   }

   private static void appendCodepointToTitleCase(UTF8StringBuilder sb, int codepoint) {
      String toTitleCase = (String)codepointOneToManyTitleCaseLookupTable.get(codepoint);
      if (toTitleCase == null) {
         sb.appendCodePoint(UCharacter.toTitleCase(codepoint));
      } else {
         sb.append(toTitleCase);
      }

   }

   public static int findInSet(UTF8String match, UTF8String set, int collationId) {
      if (match.contains(UTF8String.fromString(","))) {
         return 0;
      } else {
         int byteIndex = 0;
         int charIndex = 0;
         int wordCount = 1;

         int lastComma;
         for(lastComma = -1; byteIndex < set.numBytes(); ++charIndex) {
            byte nextByte = set.getByte(byteIndex);
            if (nextByte == 44) {
               if (set.substring(lastComma + 1, charIndex).semanticEquals(match, collationId)) {
                  return wordCount;
               }

               lastComma = charIndex;
               ++wordCount;
            }

            byteIndex += UTF8String.numBytesForFirstByte(nextByte);
         }

         if (set.substring(lastComma + 1, set.numBytes()).semanticEquals(match, collationId)) {
            return wordCount;
         } else {
            return 0;
         }
      }
   }

   public static boolean lowercaseContains(UTF8String target, UTF8String pattern) {
      if (target.isFullAscii() && pattern.isFullAscii()) {
         return target.toLowerCase().contains(pattern.toLowerCase());
      } else {
         return lowercaseIndexOfSlow(target, pattern, 0) >= 0;
      }
   }

   public static boolean lowercaseStartsWith(UTF8String target, UTF8String pattern) {
      return target.isFullAscii() && pattern.isFullAscii() ? target.toLowerCase().startsWith(pattern.toLowerCase()) : lowercaseMatchFrom(target, lowerCaseCodePointsSlow(pattern), 0);
   }

   public static boolean lowercaseEndsWith(UTF8String target, UTF8String pattern) {
      return target.isFullAscii() && pattern.isFullAscii() ? target.toLowerCase().endsWith(pattern.toLowerCase()) : lowercaseMatchUntil(target, lowerCaseCodePointsSlow(pattern), target.numChars());
   }

   public static int lowercaseIndexOf(UTF8String target, UTF8String pattern, int start) {
      if (pattern.numChars() == 0) {
         return target.indexOfEmpty(start);
      } else {
         return target.isFullAscii() && pattern.isFullAscii() ? target.toLowerCase().indexOf(pattern.toLowerCase(), start) : lowercaseIndexOfSlow(target, pattern, start);
      }
   }

   private static int lowercaseIndexOfSlow(UTF8String target, UTF8String pattern, int start) {
      return lowercaseFind(target, lowerCaseCodePoints(pattern), start);
   }

   public static int indexOf(UTF8String target, UTF8String pattern, int start, int collationId) {
      if (pattern.numBytes() == 0) {
         return target.indexOfEmpty(start);
      } else if (target.numBytes() == 0) {
         return -1;
      } else {
         String targetStr = target.toValidString();
         String patternStr = pattern.toValidString();
         if (targetStr.codePointCount(0, targetStr.length()) <= start) {
            return -1;
         } else {
            StringSearch stringSearch = CollationFactory.getStringSearch(targetStr, patternStr, collationId);
            stringSearch.setOverlapping(true);
            int startIndex = targetStr.offsetByCodePoints(0, start);
            stringSearch.setIndex(startIndex);
            int searchIndex = stringSearch.next();
            if (searchIndex == -1) {
               return -1;
            } else {
               int indexOf = targetStr.codePointCount(0, searchIndex);
               return indexOf < start ? -1 : indexOf;
            }
         }
      }
   }

   private static int findIndex(StringSearch stringSearch, int count) {
      assert count >= 0;

      int index = 0;

      while(count > 0) {
         int nextIndex = stringSearch.next();
         if (nextIndex == -1) {
            return -1;
         }

         if (nextIndex == index && index != 0) {
            stringSearch.setIndex(stringSearch.getIndex() + stringSearch.getMatchLength());
         } else {
            --count;
            index = nextIndex;
         }
      }

      return index;
   }

   private static int findIndexReverse(StringSearch stringSearch, int count) {
      assert count >= 0;

      int index;
      for(index = 0; count > 0; --count) {
         index = stringSearch.previous();
         if (index == -1) {
            return -1;
         }
      }

      return index + stringSearch.getMatchLength();
   }

   public static UTF8String subStringIndex(UTF8String string, UTF8String delimiter, int count, int collationId) {
      if (delimiter.numBytes() != 0 && count != 0 && string.numBytes() != 0) {
         String str = string.toValidString();
         String delim = delimiter.toValidString();
         StringSearch stringSearch = CollationFactory.getStringSearch(str, delim, collationId);
         stringSearch.setOverlapping(true);
         if (count > 0) {
            int searchIndex = findIndex(stringSearch, count);
            if (searchIndex == -1) {
               return string;
            } else {
               return searchIndex == 0 ? UTF8String.EMPTY_UTF8 : UTF8String.fromString(str.substring(0, searchIndex));
            }
         } else {
            int searchIndex = findIndexReverse(stringSearch, -count);
            if (searchIndex == -1) {
               return string;
            } else {
               return searchIndex == str.length() ? UTF8String.EMPTY_UTF8 : UTF8String.fromString(str.substring(searchIndex));
            }
         }
      } else {
         return UTF8String.EMPTY_UTF8;
      }
   }

   public static UTF8String lowercaseSubStringIndex(UTF8String string, UTF8String delimiter, int count) {
      if (delimiter.numBytes() != 0 && count != 0) {
         UTF8String lowercaseDelimiter = lowerCaseCodePoints(delimiter);
         if (count > 0) {
            int matchLength;
            for(matchLength = -1; count > 0; --count) {
               matchLength = lowercaseFind(string, lowercaseDelimiter, matchLength + 1);
               if (matchLength <= -1) {
                  return string;
               }
            }

            return string.substring(0, matchLength);
         } else {
            int matchLength = string.numChars() + 1;

            for(int var5 = -count; var5 > 0; --var5) {
               matchLength = lowercaseRFind(string, lowercaseDelimiter, matchLength - 1);
               if (matchLength <= -1) {
                  return string;
               }
            }

            return string.substring(matchLength, string.numChars());
         }
      } else {
         return UTF8String.EMPTY_UTF8;
      }
   }

   private static Map getLowercaseDict(Map dict) {
      Map<Integer, String> lowercaseDict = new HashMap();

      for(Map.Entry entry : dict.entrySet()) {
         int codePoint = ((String)entry.getKey()).codePointAt(0);
         lowercaseDict.putIfAbsent(getLowercaseCodePoint(codePoint), (String)entry.getValue());
      }

      return lowercaseDict;
   }

   public static UTF8String lowercaseTranslate(UTF8String input, Map dict) {
      Iterator<Integer> inputIter = input.codePointIterator(UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_MAKE_VALID);
      Map<Integer, String> lowercaseDict = getLowercaseDict(dict);
      StringBuilder sb = new StringBuilder();
      int codePointBuffer = -1;

      while(inputIter.hasNext()) {
         int codePoint;
         if (codePointBuffer != -1) {
            codePoint = codePointBuffer;
            codePointBuffer = -1;
         } else {
            codePoint = (Integer)inputIter.next();
         }

         if (lowercaseDict.containsKey(6882055) && codePoint == 105 && inputIter.hasNext()) {
            int nextCodePoint = (Integer)inputIter.next();
            if (nextCodePoint == 775) {
               codePoint = 6882055;
            } else {
               codePointBuffer = nextCodePoint;
            }
         }

         String translated = (String)lowercaseDict.get(getLowercaseCodePoint(codePoint));
         if (translated == null) {
            sb.appendCodePoint(codePoint);
         } else if (!"\u0000".equals(translated)) {
            sb.append(translated);
         }
      }

      if (codePointBuffer != -1) {
         sb.appendCodePoint(codePointBuffer);
      }

      return UTF8String.fromString(sb.toString());
   }

   public static UTF8String translate(UTF8String input, Map dict, int collationId) {
      String inputString = input.toValidString();
      CharacterIterator target = new StringCharacterIterator(inputString);
      Collator collator = CollationFactory.fetchCollation(collationId).getCollator();
      StringBuilder sb = new StringBuilder();
      int charIndex = 0;

      while(charIndex < inputString.length()) {
         int longestMatchLen = 0;
         String longestMatch = "";

         for(String key : dict.keySet()) {
            StringSearch stringSearch = new StringSearch(key, target, (RuleBasedCollator)collator);
            stringSearch.setIndex(charIndex);
            int matchIndex = stringSearch.next();
            if (matchIndex == charIndex) {
               int matchLen = stringSearch.getMatchLength();
               if (matchLen > longestMatchLen) {
                  longestMatchLen = matchLen;
                  longestMatch = key;
               }
            }
         }

         if (longestMatchLen == 0) {
            sb.append(inputString.charAt(charIndex));
            ++charIndex;
         } else {
            if (!"\u0000".equals(dict.get(longestMatch))) {
               sb.append((String)dict.get(longestMatch));
            }

            charIndex += longestMatchLen;
         }
      }

      return UTF8String.fromString(sb.toString());
   }

   public static UTF8String binaryTrim(UTF8String srcString, UTF8String trimString, int collationId) {
      return binaryTrimRight(srcString.trimLeft(trimString), trimString, collationId);
   }

   public static UTF8String lowercaseTrim(UTF8String srcString, UTF8String trimString, int collationId) {
      return lowercaseTrimRight(lowercaseTrimLeft(srcString, trimString), trimString, collationId);
   }

   public static UTF8String trim(UTF8String srcString, UTF8String trimString, int collationId) {
      return trimRight(trimLeft(srcString, trimString, collationId), trimString, collationId);
   }

   public static UTF8String lowercaseTrimLeft(UTF8String srcString, UTF8String trimString) {
      if (trimString == null) {
         return null;
      } else {
         HashSet<Integer> trimChars = new HashSet();
         Iterator<Integer> trimIter = trimString.codePointIterator();

         while(trimIter.hasNext()) {
            trimChars.add(getLowercaseCodePoint((Integer)trimIter.next()));
         }

         int searchIndex = 0;
         int codePointBuffer = -1;
         Iterator<Integer> srcIter = srcString.codePointIterator();

         while(srcIter.hasNext()) {
            int codePoint;
            if (codePointBuffer != -1) {
               codePoint = codePointBuffer;
               codePointBuffer = -1;
            } else {
               codePoint = getLowercaseCodePoint((Integer)srcIter.next());
            }

            if (codePoint == 105 && srcIter.hasNext() && trimChars.contains(6882055)) {
               codePoint = getLowercaseCodePoint((Integer)srcIter.next());
               if (codePoint == 775) {
                  searchIndex += 2;
                  codePointBuffer = -1;
               } else {
                  if (!trimChars.contains(codePoint)) {
                     break;
                  }

                  ++searchIndex;
                  codePointBuffer = codePoint;
               }
            } else {
               if (!trimChars.contains(codePoint)) {
                  break;
               }

               ++searchIndex;
            }
         }

         return searchIndex == 0 ? srcString : srcString.substring(searchIndex, srcString.numChars());
      }
   }

   public static UTF8String trimLeft(UTF8String srcString, UTF8String trimString, int collationId) {
      if (trimString == null) {
         return null;
      } else if (srcString.numBytes() == 0) {
         return srcString;
      } else {
         Map<Integer, String> trimChars = new HashMap();
         Iterator<Integer> trimIter = trimString.codePointIterator(UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_MAKE_VALID);

         while(trimIter.hasNext()) {
            int codePoint = (Integer)trimIter.next();
            trimChars.putIfAbsent(codePoint, new String(Character.toChars(codePoint)));
         }

         String src = srcString.toValidString();
         CharacterIterator target = new StringCharacterIterator(src);
         Collator collator = CollationFactory.fetchCollation(collationId).getCollator();

         int charIndex;
         int longestMatchLen;
         for(charIndex = 0; charIndex < src.length(); charIndex += longestMatchLen) {
            longestMatchLen = 0;

            for(String trim : trimChars.values()) {
               StringSearch stringSearch = new StringSearch(trim, target, (RuleBasedCollator)collator);
               stringSearch.setIndex(charIndex);
               int matchIndex = stringSearch.next();
               if (matchIndex == charIndex) {
                  int matchLen = stringSearch.getMatchLength();
                  if (matchLen > longestMatchLen) {
                     longestMatchLen = matchLen;
                  }
               }
            }

            if (longestMatchLen == 0) {
               break;
            }
         }

         return UTF8String.fromString(src.substring(charIndex));
      }
   }

   public static UTF8String binaryTrimRight(UTF8String srcString, UTF8String trimString, int collationId) {
      if (trimString == null) {
         return null;
      } else {
         HashSet<Integer> trimChars = new HashSet();
         Iterator<Integer> trimIter = trimString.codePointIterator();

         while(trimIter.hasNext()) {
            trimChars.add((Integer)trimIter.next());
         }

         int searchIndex = srcString.numChars();
         int codePointBuffer = -1;
         int lastNonSpaceByteIdx = srcString.numBytes();
         int lastNonSpaceCharacterIdx = srcString.numChars();
         if (!trimChars.contains(32) && CollationFactory.ignoresSpacesInTrimFunctions(collationId, false, true)) {
            while(lastNonSpaceByteIdx > 0 && srcString.getByte(lastNonSpaceByteIdx - 1) == 32) {
               --lastNonSpaceByteIdx;
            }

            if (lastNonSpaceByteIdx == 0) {
               return srcString;
            }

            searchIndex = lastNonSpaceCharacterIdx = srcString.numChars() - (srcString.numBytes() - lastNonSpaceByteIdx);
         }

         Iterator<Integer> srcIter = srcString.reverseCodePointIterator();

         for(int i = lastNonSpaceCharacterIdx; i < srcString.numChars(); ++i) {
            srcIter.next();
         }

         while(srcIter.hasNext()) {
            int codePoint = (Integer)srcIter.next();
            if (!trimChars.contains(codePoint)) {
               break;
            }

            --searchIndex;
         }

         if (searchIndex == srcString.numChars()) {
            return srcString;
         } else {
            return lastNonSpaceCharacterIdx == srcString.numChars() ? srcString.substring(0, searchIndex) : UTF8String.concat(srcString.substring(0, searchIndex), srcString.substring(lastNonSpaceCharacterIdx, srcString.numChars()));
         }
      }
   }

   public static UTF8String lowercaseTrimRight(UTF8String srcString, UTF8String trimString, int collationId) {
      if (trimString == null) {
         return null;
      } else {
         HashSet<Integer> trimChars = new HashSet();
         Iterator<Integer> trimIter = trimString.codePointIterator();

         while(trimIter.hasNext()) {
            trimChars.add(getLowercaseCodePoint((Integer)trimIter.next()));
         }

         int searchIndex = srcString.numChars();
         int codePointBuffer = -1;
         int lastNonSpaceByteIdx = srcString.numBytes();
         int lastNonSpaceCharacterIdx = srcString.numChars();
         if (!trimChars.contains(32) && CollationFactory.ignoresSpacesInTrimFunctions(collationId, false, true)) {
            while(lastNonSpaceByteIdx > 0 && srcString.getByte(lastNonSpaceByteIdx - 1) == 32) {
               --lastNonSpaceByteIdx;
            }

            if (lastNonSpaceByteIdx == 0) {
               return srcString;
            }

            searchIndex = lastNonSpaceCharacterIdx = srcString.numChars() - (srcString.numBytes() - lastNonSpaceByteIdx);
         }

         Iterator<Integer> srcIter = srcString.reverseCodePointIterator();

         for(int i = lastNonSpaceCharacterIdx; i < srcString.numChars(); ++i) {
            srcIter.next();
         }

         while(srcIter.hasNext()) {
            int codePoint;
            if (codePointBuffer != -1) {
               codePoint = codePointBuffer;
               codePointBuffer = -1;
            } else {
               codePoint = getLowercaseCodePoint((Integer)srcIter.next());
            }

            if (codePoint == 775 && srcIter.hasNext() && trimChars.contains(6882055)) {
               codePoint = getLowercaseCodePoint((Integer)srcIter.next());
               if (codePoint == 105) {
                  searchIndex -= 2;
                  codePointBuffer = -1;
               } else {
                  if (!trimChars.contains(codePoint)) {
                     break;
                  }

                  --searchIndex;
                  codePointBuffer = codePoint;
               }
            } else {
               if (!trimChars.contains(codePoint)) {
                  break;
               }

               --searchIndex;
            }
         }

         if (searchIndex == srcString.numChars()) {
            return srcString;
         } else {
            return lastNonSpaceCharacterIdx == srcString.numChars() ? srcString.substring(0, searchIndex) : UTF8String.concat(srcString.substring(0, searchIndex), srcString.substring(lastNonSpaceCharacterIdx, srcString.numChars()));
         }
      }
   }

   public static UTF8String trimRight(UTF8String srcString, UTF8String trimString, int collationId) {
      if (trimString == null) {
         return null;
      } else if (srcString.numBytes() == 0) {
         return srcString;
      } else {
         Map<Integer, String> trimChars = new HashMap();
         Iterator<Integer> trimIter = trimString.codePointIterator(UTF8String.CodePointIteratorType.CODE_POINT_ITERATOR_MAKE_VALID);

         while(trimIter.hasNext()) {
            int codePoint = (Integer)trimIter.next();
            trimChars.putIfAbsent(codePoint, new String(Character.toChars(codePoint)));
         }

         String src = srcString.toValidString();
         CharacterIterator target = new StringCharacterIterator(src);
         Collator collator = CollationFactory.fetchCollation(collationId).getCollator();
         int charIndex = src.length();
         int lastNonSpacePosition = src.length();
         if (!trimChars.containsKey(32) && CollationFactory.ignoresSpacesInTrimFunctions(collationId, false, true)) {
            while(lastNonSpacePosition > 0 && src.charAt(lastNonSpacePosition - 1) == ' ') {
               --lastNonSpacePosition;
            }

            if (lastNonSpacePosition == 0) {
               return UTF8String.fromString(src);
            }

            charIndex = lastNonSpacePosition;
         }

         while(charIndex >= 0) {
            int longestMatchLen = 0;

            for(String trim : trimChars.values()) {
               StringSearch stringSearch = new StringSearch(trim, target, (RuleBasedCollator)collator);
               stringSearch.setIndex(Math.max(charIndex - 3, 0));
               int matchIndex = stringSearch.next();

               int matchLen;
               for(matchLen = stringSearch.getMatchLength(); matchIndex != -1 && matchIndex < charIndex - matchLen; matchLen = stringSearch.getMatchLength()) {
                  matchIndex = stringSearch.next();
               }

               if (matchIndex == charIndex - matchLen && matchLen > longestMatchLen) {
                  longestMatchLen = matchLen;
               }
            }

            if (longestMatchLen == 0) {
               break;
            }

            charIndex -= longestMatchLen;
         }

         if (charIndex == src.length()) {
            return srcString;
         } else if (lastNonSpacePosition == srcString.numChars()) {
            return UTF8String.fromString(src.substring(0, charIndex));
         } else {
            String var10000 = src.substring(0, charIndex);
            return UTF8String.fromString(var10000 + src.substring(lastNonSpacePosition));
         }
      }
   }

   public static UTF8String[] splitSQL(UTF8String input, UTF8String delim, int limit, int collationId) {
      if (CollationFactory.fetchCollation(collationId).isUtf8BinaryType) {
         return input.split(delim, limit);
      } else {
         return CollationFactory.fetchCollation(collationId).isUtf8LcaseType ? lowercaseSplitSQL(input, delim, limit) : icuSplitSQL(input, delim, limit, collationId);
      }
   }

   public static UTF8String[] lowercaseSplitSQL(UTF8String string, UTF8String delimiter, int limit) {
      if (delimiter.numBytes() == 0) {
         return new UTF8String[]{string};
      } else if (string.numBytes() == 0) {
         return new UTF8String[]{UTF8String.EMPTY_UTF8};
      } else {
         List<UTF8String> strings = new ArrayList();
         UTF8String lowercaseDelimiter = lowerCaseCodePoints(delimiter);
         int startIndex = 0;
         int nextMatch = 0;

         while(nextMatch != -1 && (limit <= 0 || strings.size() != limit - 1)) {
            nextMatch = lowercaseFind(string, lowercaseDelimiter, startIndex);
            if (nextMatch != -1) {
               int nextMatchLength = lowercaseMatchLengthFrom(string, lowercaseDelimiter, nextMatch);
               strings.add(string.substring(startIndex, nextMatch));
               startIndex = nextMatch + nextMatchLength;
            }
         }

         if (startIndex <= string.numChars()) {
            strings.add(string.substring(startIndex, string.numChars()));
         }

         if (limit == 0) {
            for(int i = strings.size() - 1; i >= 0 && ((UTF8String)strings.get(i)).numBytes() == 0; --i) {
               strings.remove(i);
            }
         }

         return (UTF8String[])strings.toArray(new UTF8String[0]);
      }
   }

   public static UTF8String[] icuSplitSQL(UTF8String string, UTF8String delimiter, int limit, int collationId) {
      if (delimiter.numBytes() == 0) {
         return new UTF8String[]{string};
      } else if (string.numBytes() == 0) {
         return new UTF8String[]{UTF8String.EMPTY_UTF8};
      } else {
         List<UTF8String> strings = new ArrayList();
         String target = string.toValidString();
         String pattern = delimiter.toValidString();
         StringSearch stringSearch = CollationFactory.getStringSearch(target, pattern, collationId);

         int start;
         int end;
         for(start = 0; (end = stringSearch.next()) != -1 && (limit <= 0 || strings.size() != limit - 1); start = end + stringSearch.getMatchLength()) {
            strings.add(UTF8String.fromString(target.substring(start, end)));
         }

         if (start <= target.length()) {
            strings.add(UTF8String.fromString(target.substring(start)));
         }

         if (limit == 0) {
            for(int i = strings.size() - 1; i >= 0 && ((UTF8String)strings.get(i)).numBytes() == 0; --i) {
               strings.remove(i);
            }
         }

         return (UTF8String[])strings.toArray(new UTF8String[0]);
      }
   }
}
