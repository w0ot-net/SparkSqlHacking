package org.apache.commons.text;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class WordUtils {
   public static String abbreviate(String str, int lower, int upper, String appendToEnd) {
      Validate.isTrue(upper >= -1, "upper value cannot be less than -1", new Object[0]);
      Validate.isTrue(upper >= lower || upper == -1, "upper value is less than lower value", new Object[0]);
      if (StringUtils.isEmpty(str)) {
         return str;
      } else {
         if (lower > str.length()) {
            lower = str.length();
         }

         if (upper == -1 || upper > str.length()) {
            upper = str.length();
         }

         StringBuilder result = new StringBuilder();
         int index = StringUtils.indexOf(str, " ", lower);
         if (index == -1) {
            result.append(str, 0, upper);
            if (upper != str.length()) {
               result.append(StringUtils.defaultString(appendToEnd));
            }
         } else {
            result.append(str, 0, Math.min(index, upper));
            result.append(StringUtils.defaultString(appendToEnd));
         }

         return result.toString();
      }
   }

   public static String capitalize(String str) {
      return capitalize(str, (char[])null);
   }

   public static String capitalize(String str, char... delimiters) {
      if (StringUtils.isEmpty(str)) {
         return str;
      } else {
         Predicate<Integer> isDelimiter = generateIsDelimiterFunction(delimiters);
         int strLen = str.length();
         int[] newCodePoints = new int[strLen];
         int outOffset = 0;
         boolean capitalizeNext = true;
         int index = 0;

         while(index < strLen) {
            int codePoint = str.codePointAt(index);
            if (isDelimiter.test(codePoint)) {
               capitalizeNext = true;
               newCodePoints[outOffset++] = codePoint;
               index += Character.charCount(codePoint);
            } else if (capitalizeNext) {
               int titleCaseCodePoint = Character.toTitleCase(codePoint);
               newCodePoints[outOffset++] = titleCaseCodePoint;
               index += Character.charCount(titleCaseCodePoint);
               capitalizeNext = false;
            } else {
               newCodePoints[outOffset++] = codePoint;
               index += Character.charCount(codePoint);
            }
         }

         return new String(newCodePoints, 0, outOffset);
      }
   }

   public static String capitalizeFully(String str) {
      return capitalizeFully(str, (char[])null);
   }

   public static String capitalizeFully(String str, char... delimiters) {
      if (StringUtils.isEmpty(str)) {
         return str;
      } else {
         str = str.toLowerCase();
         return capitalize(str, delimiters);
      }
   }

   public static boolean containsAllWords(CharSequence word, CharSequence... words) {
      if (!StringUtils.isEmpty(word) && !ArrayUtils.isEmpty(words)) {
         for(CharSequence w : words) {
            if (StringUtils.isBlank(w)) {
               return false;
            }

            Pattern p = Pattern.compile(".*\\b" + Pattern.quote(w.toString()) + "\\b.*");
            if (!p.matcher(word).matches()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static Predicate generateIsDelimiterFunction(char[] delimiters) {
      Predicate<Integer> isDelimiter;
      if (delimiters != null && delimiters.length != 0) {
         Set<Integer> delimiterSet = new HashSet();

         for(int index = 0; index < delimiters.length; ++index) {
            delimiterSet.add(Character.codePointAt(delimiters, index));
         }

         Objects.requireNonNull(delimiterSet);
         isDelimiter = delimiterSet::contains;
      } else {
         isDelimiter = delimiters == null ? Character::isWhitespace : (c) -> false;
      }

      return isDelimiter;
   }

   public static String initials(String str) {
      return initials(str, (char[])null);
   }

   public static String initials(String str, char... delimiters) {
      if (StringUtils.isEmpty(str)) {
         return str;
      } else if (delimiters != null && delimiters.length == 0) {
         return "";
      } else {
         Predicate<Integer> isDelimiter = generateIsDelimiterFunction(delimiters);
         int strLen = str.length();
         int[] newCodePoints = new int[strLen / 2 + 1];
         int count = 0;
         boolean lastWasGap = true;

         int codePoint;
         for(int i = 0; i < strLen; i += Character.charCount(codePoint)) {
            codePoint = str.codePointAt(i);
            if (isDelimiter.test(codePoint)) {
               lastWasGap = true;
            } else if (lastWasGap) {
               newCodePoints[count++] = codePoint;
               lastWasGap = false;
            }
         }

         return new String(newCodePoints, 0, count);
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean isDelimiter(char ch, char[] delimiters) {
      if (delimiters == null) {
         return Character.isWhitespace(ch);
      } else {
         for(char delimiter : delimiters) {
            if (ch == delimiter) {
               return true;
            }
         }

         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public static boolean isDelimiter(int codePoint, char[] delimiters) {
      if (delimiters == null) {
         return Character.isWhitespace(codePoint);
      } else {
         for(int index = 0; index < delimiters.length; ++index) {
            int delimiterCodePoint = Character.codePointAt(delimiters, index);
            if (delimiterCodePoint == codePoint) {
               return true;
            }
         }

         return false;
      }
   }

   public static String swapCase(String str) {
      if (StringUtils.isEmpty(str)) {
         return str;
      } else {
         int strLen = str.length();
         int[] newCodePoints = new int[strLen];
         int outOffset = 0;
         boolean whitespace = true;

         int newCodePoint;
         for(int index = 0; index < strLen; index += Character.charCount(newCodePoint)) {
            int oldCodepoint = str.codePointAt(index);
            if (!Character.isUpperCase(oldCodepoint) && !Character.isTitleCase(oldCodepoint)) {
               if (Character.isLowerCase(oldCodepoint)) {
                  if (whitespace) {
                     newCodePoint = Character.toTitleCase(oldCodepoint);
                     whitespace = false;
                  } else {
                     newCodePoint = Character.toUpperCase(oldCodepoint);
                  }
               } else {
                  whitespace = Character.isWhitespace(oldCodepoint);
                  newCodePoint = oldCodepoint;
               }
            } else {
               newCodePoint = Character.toLowerCase(oldCodepoint);
               whitespace = false;
            }

            newCodePoints[outOffset++] = newCodePoint;
         }

         return new String(newCodePoints, 0, outOffset);
      }
   }

   public static String uncapitalize(String str) {
      return uncapitalize(str, (char[])null);
   }

   public static String uncapitalize(String str, char... delimiters) {
      if (StringUtils.isEmpty(str)) {
         return str;
      } else {
         Predicate<Integer> isDelimiter = generateIsDelimiterFunction(delimiters);
         int strLen = str.length();
         int[] newCodePoints = new int[strLen];
         int outOffset = 0;
         boolean uncapitalizeNext = true;
         int index = 0;

         while(index < strLen) {
            int codePoint = str.codePointAt(index);
            if (isDelimiter.test(codePoint)) {
               uncapitalizeNext = true;
               newCodePoints[outOffset++] = codePoint;
               index += Character.charCount(codePoint);
            } else if (uncapitalizeNext) {
               int titleCaseCodePoint = Character.toLowerCase(codePoint);
               newCodePoints[outOffset++] = titleCaseCodePoint;
               index += Character.charCount(titleCaseCodePoint);
               uncapitalizeNext = false;
            } else {
               newCodePoints[outOffset++] = codePoint;
               index += Character.charCount(codePoint);
            }
         }

         return new String(newCodePoints, 0, outOffset);
      }
   }

   public static String wrap(String str, int wrapLength) {
      return wrap(str, wrapLength, (String)null, false);
   }

   public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords) {
      return wrap(str, wrapLength, newLineStr, wrapLongWords, " ");
   }

   public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords, String wrapOn) {
      if (str == null) {
         return null;
      } else {
         if (newLineStr == null) {
            newLineStr = System.lineSeparator();
         }

         if (wrapLength < 1) {
            wrapLength = 1;
         }

         if (StringUtils.isBlank(wrapOn)) {
            wrapOn = " ";
         }

         Pattern patternToWrapOn = Pattern.compile(wrapOn);
         int inputLineLength = str.length();
         int offset = 0;
         StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
         int matcherSize = -1;

         while(offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset, Math.min((int)Math.min(2147483647L, (long)(offset + wrapLength) + 1L), inputLineLength)));
            if (matcher.find()) {
               if (matcher.start() == 0) {
                  matcherSize = matcher.end();
                  if (matcherSize != 0) {
                     offset += matcher.end();
                     continue;
                  }

                  ++offset;
               }

               spaceToWrapAt = matcher.start() + offset;
            }

            if (inputLineLength - offset <= wrapLength) {
               break;
            }

            while(matcher.find()) {
               spaceToWrapAt = matcher.start() + offset;
            }

            if (spaceToWrapAt >= offset) {
               wrappedLine.append(str, offset, spaceToWrapAt);
               wrappedLine.append(newLineStr);
               offset = spaceToWrapAt + 1;
            } else if (wrapLongWords) {
               if (matcherSize == 0) {
                  --offset;
               }

               wrappedLine.append(str, offset, wrapLength + offset);
               wrappedLine.append(newLineStr);
               offset += wrapLength;
               matcherSize = -1;
            } else {
               matcher = patternToWrapOn.matcher(str.substring(offset + wrapLength));
               if (matcher.find()) {
                  matcherSize = matcher.end() - matcher.start();
                  spaceToWrapAt = matcher.start() + offset + wrapLength;
               }

               if (spaceToWrapAt >= 0) {
                  if (matcherSize == 0 && offset != 0) {
                     --offset;
                  }

                  wrappedLine.append(str, offset, spaceToWrapAt);
                  wrappedLine.append(newLineStr);
                  offset = spaceToWrapAt + 1;
               } else {
                  if (matcherSize == 0 && offset != 0) {
                     --offset;
                  }

                  wrappedLine.append(str, offset, str.length());
                  offset = inputLineLength;
                  matcherSize = -1;
               }
            }
         }

         if (matcherSize == 0 && offset < inputLineLength) {
            --offset;
         }

         wrappedLine.append(str, offset, str.length());
         return wrappedLine.toString();
      }
   }
}
