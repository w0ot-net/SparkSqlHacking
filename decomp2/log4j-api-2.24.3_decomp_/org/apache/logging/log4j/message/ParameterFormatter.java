package org.apache.logging.log4j.message;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StringBuilders;

final class ParameterFormatter {
   static final String RECURSION_PREFIX = "[...";
   static final String RECURSION_SUFFIX = "...]";
   static final String ERROR_PREFIX = "[!!!";
   static final String ERROR_SEPARATOR = "=>";
   static final String ERROR_MSG_SEPARATOR = ":";
   static final String ERROR_SUFFIX = "!!!]";
   private static final char DELIM_START = '{';
   private static final char DELIM_STOP = '}';
   private static final char ESCAPE_CHAR = '\\';
   private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").withZone(ZoneId.systemDefault());
   private static final Logger STATUS_LOGGER = StatusLogger.getLogger();

   private ParameterFormatter() {
   }

   static MessagePatternAnalysis analyzePattern(final String pattern, final int argCount) {
      MessagePatternAnalysis analysis = new MessagePatternAnalysis();
      analyzePattern(pattern, argCount, analysis);
      return analysis;
   }

   static void analyzePattern(final String pattern, final int argCount, final MessagePatternAnalysis analysis) {
      int l;
      if (pattern != null && (l = pattern.length()) >= 2) {
         boolean escaped = false;
         analysis.placeholderCount = 0;
         analysis.escapedCharFound = false;

         for(int i = 0; i < l - 1; ++i) {
            char c = pattern.charAt(i);
            if (c == '\\') {
               analysis.escapedCharFound = true;
               escaped = !escaped;
            } else if (escaped) {
               escaped = false;
            } else if (c == '{' && pattern.charAt(i + 1) == '}') {
               if (argCount >= 0 && analysis.placeholderCount >= argCount) {
                  ++analysis.placeholderCount;
                  ++i;
               } else {
                  analysis.ensurePlaceholderCharIndicesCapacity(argCount);
                  analysis.placeholderCharIndices[analysis.placeholderCount++] = i++;
               }
            }
         }

      } else {
         analysis.placeholderCount = 0;
      }
   }

   static String format(final String pattern, final Object[] args, int argCount) {
      StringBuilder result = new StringBuilder();
      MessagePatternAnalysis analysis = analyzePattern(pattern, argCount);
      formatMessage(result, pattern, args, argCount, analysis);
      return result.toString();
   }

   static void formatMessage(final StringBuilder buffer, final String pattern, final Object[] args, final int argCount, final MessagePatternAnalysis analysis) {
      if (pattern != null && args != null && analysis.placeholderCount != 0) {
         if (analysis.placeholderCount != argCount) {
            int noThrowableArgCount = argCount < 1 ? 0 : argCount - (args[argCount - 1] instanceof Throwable ? 1 : 0);
            if (analysis.placeholderCount != noThrowableArgCount) {
               STATUS_LOGGER.warn((String)"found {} argument placeholders, but provided {} for pattern `{}`", (Object)analysis.placeholderCount, argCount, pattern);
            }
         }

         if (analysis.escapedCharFound) {
            formatMessageContainingEscapes(buffer, pattern, args, argCount, analysis);
         } else {
            formatMessageContainingNoEscapes(buffer, pattern, args, argCount, analysis);
         }

      } else {
         buffer.append(pattern);
      }
   }

   private static void formatMessageContainingNoEscapes(final StringBuilder buffer, final String pattern, final Object[] args, final int argCount, final MessagePatternAnalysis analysis) {
      int precedingTextStartIndex = 0;
      int argLimit = Math.min(analysis.placeholderCount, argCount);

      for(int argIndex = 0; argIndex < argLimit; ++argIndex) {
         int placeholderCharIndex = analysis.placeholderCharIndices[argIndex];
         buffer.append(pattern, precedingTextStartIndex, placeholderCharIndex);
         recursiveDeepToString(args[argIndex], buffer);
         precedingTextStartIndex = placeholderCharIndex + 2;
      }

      buffer.append(pattern, precedingTextStartIndex, pattern.length());
   }

   private static void formatMessageContainingEscapes(final StringBuilder buffer, final String pattern, final Object[] args, final int argCount, final MessagePatternAnalysis analysis) {
      int precedingTextStartIndex = 0;
      int argLimit = Math.min(analysis.placeholderCount, argCount);

      for(int argIndex = 0; argIndex < argLimit; ++argIndex) {
         int placeholderCharIndex = analysis.placeholderCharIndices[argIndex];
         copyMessagePatternContainingEscapes(buffer, pattern, precedingTextStartIndex, placeholderCharIndex);
         recursiveDeepToString(args[argIndex], buffer);
         precedingTextStartIndex = placeholderCharIndex + 2;
      }

      copyMessagePatternContainingEscapes(buffer, pattern, precedingTextStartIndex, pattern.length());
   }

   private static void copyMessagePatternContainingEscapes(final StringBuilder buffer, final String pattern, final int startIndex, final int endIndex) {
      boolean escaped = false;

      for(int i = startIndex; i < endIndex; ++i) {
         char c = pattern.charAt(i);
         if (c == '\\') {
            if (escaped) {
               escaped = false;
            } else {
               escaped = true;
               buffer.append(c);
            }
         } else if (escaped) {
            if (c == '{' && pattern.charAt(i + 1) == '}') {
               buffer.setLength(buffer.length() - 1);
               buffer.append("{}");
               ++i;
            } else {
               buffer.append(c);
            }

            escaped = false;
         } else {
            buffer.append(c);
         }
      }

   }

   static String deepToString(final Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof String) {
         return (String)o;
      } else if (o instanceof Integer) {
         return Integer.toString((Integer)o);
      } else if (o instanceof Long) {
         return Long.toString((Long)o);
      } else if (o instanceof Double) {
         return Double.toString((Double)o);
      } else if (o instanceof Boolean) {
         return Boolean.toString((Boolean)o);
      } else if (o instanceof Character) {
         return Character.toString((Character)o);
      } else if (o instanceof Short) {
         return Short.toString((Short)o);
      } else if (o instanceof Float) {
         return Float.toString((Float)o);
      } else if (o instanceof Byte) {
         return Byte.toString((Byte)o);
      } else {
         StringBuilder str = new StringBuilder();
         recursiveDeepToString(o, str);
         return str.toString();
      }
   }

   static void recursiveDeepToString(final Object o, final StringBuilder str) {
      recursiveDeepToString(o, str, (Set)null);
   }

   private static void recursiveDeepToString(final Object o, final StringBuilder str, final Set dejaVu) {
      if (!appendSpecialTypes(o, str)) {
         if (isMaybeRecursive(o)) {
            appendPotentiallyRecursiveValue(o, str, dejaVu);
         } else {
            tryObjectToString(o, str);
         }

      }
   }

   private static boolean appendSpecialTypes(final Object o, final StringBuilder str) {
      return StringBuilders.appendSpecificTypes(str, o) || appendDate(o, str);
   }

   private static boolean appendDate(final Object o, final StringBuilder str) {
      if (!(o instanceof Date)) {
         return false;
      } else {
         DATE_FORMATTER.formatTo(((Date)o).toInstant(), str);
         return true;
      }
   }

   private static boolean isMaybeRecursive(final Object o) {
      return o.getClass().isArray() || o instanceof Map || o instanceof Collection;
   }

   private static void appendPotentiallyRecursiveValue(final Object o, final StringBuilder str, final Set dejaVu) {
      Class<?> oClass = o.getClass();
      if (oClass.isArray()) {
         appendArray(o, str, dejaVu, oClass);
      } else if (o instanceof Map) {
         appendMap(o, str, dejaVu);
      } else {
         if (!(o instanceof Collection)) {
            throw new IllegalArgumentException("was expecting a container, found " + oClass);
         }

         appendCollection(o, str, dejaVu);
      }

   }

   private static void appendArray(final Object o, final StringBuilder str, final Set dejaVu, final Class oClass) {
      if (oClass == byte[].class) {
         str.append(Arrays.toString((byte[])o));
      } else if (oClass == short[].class) {
         str.append(Arrays.toString((short[])o));
      } else if (oClass == int[].class) {
         str.append(Arrays.toString((int[])o));
      } else if (oClass == long[].class) {
         str.append(Arrays.toString((long[])o));
      } else if (oClass == float[].class) {
         str.append(Arrays.toString((float[])o));
      } else if (oClass == double[].class) {
         str.append(Arrays.toString((double[])o));
      } else if (oClass == boolean[].class) {
         str.append(Arrays.toString((boolean[])o));
      } else if (oClass == char[].class) {
         str.append(Arrays.toString((char[])o));
      } else {
         Set<Object> effectiveDejaVu = getOrCreateDejaVu(dejaVu);
         boolean seen = !effectiveDejaVu.add(o);
         if (seen) {
            String id = identityToString(o);
            str.append("[...").append(id).append("...]");
         } else {
            Object[] oArray = o;
            str.append('[');
            boolean first = true;

            for(Object current : oArray) {
               if (first) {
                  first = false;
               } else {
                  str.append(", ");
               }

               recursiveDeepToString(current, str, cloneDejaVu(effectiveDejaVu));
            }

            str.append(']');
         }
      }

   }

   private static void appendMap(final Object o, final StringBuilder str, final Set dejaVu) {
      Set<Object> effectiveDejaVu = getOrCreateDejaVu(dejaVu);
      boolean seen = !effectiveDejaVu.add(o);
      if (seen) {
         String id = identityToString(o);
         str.append("[...").append(id).append("...]");
      } else {
         Map<?, ?> oMap = (Map)o;
         str.append('{');
         boolean isFirst = true;

         for(Map.Entry entry : oMap.entrySet()) {
            if (isFirst) {
               isFirst = false;
            } else {
               str.append(", ");
            }

            Object key = entry.getKey();
            Object value = entry.getValue();
            recursiveDeepToString(key, str, cloneDejaVu(effectiveDejaVu));
            str.append('=');
            recursiveDeepToString(value, str, cloneDejaVu(effectiveDejaVu));
         }

         str.append('}');
      }

   }

   private static void appendCollection(final Object o, final StringBuilder str, final Set dejaVu) {
      Set<Object> effectiveDejaVu = getOrCreateDejaVu(dejaVu);
      boolean seen = !effectiveDejaVu.add(o);
      if (seen) {
         String id = identityToString(o);
         str.append("[...").append(id).append("...]");
      } else {
         Collection<?> oCol = (Collection)o;
         str.append('[');
         boolean isFirst = true;

         for(Object anOCol : oCol) {
            if (isFirst) {
               isFirst = false;
            } else {
               str.append(", ");
            }

            recursiveDeepToString(anOCol, str, cloneDejaVu(effectiveDejaVu));
         }

         str.append(']');
      }

   }

   private static Set getOrCreateDejaVu(final Set dejaVu) {
      return dejaVu == null ? createDejaVu() : dejaVu;
   }

   private static Set createDejaVu() {
      return Collections.newSetFromMap(new IdentityHashMap());
   }

   private static Set cloneDejaVu(final Set dejaVu) {
      Set<Object> clonedDejaVu = createDejaVu();
      clonedDejaVu.addAll(dejaVu);
      return clonedDejaVu;
   }

   private static void tryObjectToString(final Object o, final StringBuilder str) {
      try {
         str.append(o.toString());
      } catch (Throwable t) {
         handleErrorInObjectToString(o, str, t);
      }

   }

   private static void handleErrorInObjectToString(final Object o, final StringBuilder str, final Throwable t) {
      str.append("[!!!");
      str.append(identityToString(o));
      str.append("=>");
      String msg = t.getMessage();
      String className = t.getClass().getName();
      str.append(className);
      if (!className.equals(msg)) {
         str.append(":");
         str.append(msg);
      }

      str.append("!!!]");
   }

   static String identityToString(final Object obj) {
      return obj == null ? null : obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
   }

   static final class MessagePatternAnalysis implements Serializable {
      private static final long serialVersionUID = -5974082575968329887L;
      private static final int PLACEHOLDER_CHAR_INDEX_BUFFER_INITIAL_SIZE = 8;
      private static final int PLACEHOLDER_CHAR_INDEX_BUFFER_SIZE_INCREMENT = 8;
      int placeholderCount;
      int[] placeholderCharIndices;
      boolean escapedCharFound;

      private void ensurePlaceholderCharIndicesCapacity(final int argCount) {
         if (this.placeholderCharIndices == null) {
            int length = Math.max(argCount, 8);
            this.placeholderCharIndices = new int[length];
         } else if (this.placeholderCount >= this.placeholderCharIndices.length) {
            int newLength = argCount > 0 ? argCount : Math.addExact(this.placeholderCharIndices.length, 8);
            int[] newPlaceholderCharIndices = new int[newLength];
            System.arraycopy(this.placeholderCharIndices, 0, newPlaceholderCharIndices, 0, this.placeholderCount);
            this.placeholderCharIndices = newPlaceholderCharIndices;
         }

      }
   }
}
