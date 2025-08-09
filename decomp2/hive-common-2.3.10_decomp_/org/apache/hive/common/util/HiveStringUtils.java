package org.apache.hive.common.util;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.apache.hadoop.hive.common.classification.InterfaceStability;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;

@InterfaceAudience.Private
@InterfaceStability.Unstable
public class HiveStringUtils {
   public static final int SHUTDOWN_HOOK_PRIORITY = 0;
   private static final DecimalFormat decimalFormat;
   private static final CharSequenceTranslator ESCAPE_JAVA = (new LookupTranslator(new String[][]{{"\"", "\\\""}, {"\\", "\\\\"}})).with(new CharSequenceTranslator[]{new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())});
   private static final CharSequenceTranslator ESCAPE_HIVE_COMMAND = (new LookupTranslator(new String[][]{{"'", "\\'"}, {";", "\\;"}, {"\\", "\\\\"}})).with(new CharSequenceTranslator[]{new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())});
   private static final Interner STRING_INTERNER;
   private static DecimalFormat oneDecimal;
   public static final String[] emptyStringArray;
   public static final char COMMA = ',';
   public static final char EQUALS = '=';
   public static final String COMMA_STR = ",";
   public static final char ESCAPE_CHAR = '\\';

   public static String intern(String str) {
      return str == null ? null : (String)STRING_INTERNER.intern(str);
   }

   public static List intern(List list) {
      if (list == null) {
         return null;
      } else {
         List<String> newList = new ArrayList(list.size());

         for(String str : list) {
            newList.add(intern(str));
         }

         return newList;
      }
   }

   public static Map intern(Map map) {
      if (map == null) {
         return null;
      } else if (map.isEmpty()) {
         return map;
      } else {
         Map<String, String> newMap = new HashMap(map.size());

         for(Map.Entry entry : map.entrySet()) {
            newMap.put(intern((String)entry.getKey()), intern((String)entry.getValue()));
         }

         return newMap;
      }
   }

   public static String stringifyException(Throwable e) {
      StringWriter stm = new StringWriter();
      PrintWriter wrt = new PrintWriter(stm);
      e.printStackTrace(wrt);
      wrt.close();
      return stm.toString();
   }

   public static String simpleHostname(String fullHostname) {
      int offset = fullHostname.indexOf(46);
      return offset != -1 ? fullHostname.substring(0, offset) : fullHostname;
   }

   public static String humanReadableInt(long number) {
      long absNumber = Math.abs(number);
      double result = (double)number;
      String suffix = "";
      if (absNumber < 1024L) {
         return String.valueOf(number);
      } else {
         if (absNumber < 1048576L) {
            result = (double)number / (double)1024.0F;
            suffix = "k";
         } else if (absNumber < 1073741824L) {
            result = (double)number / (double)1048576.0F;
            suffix = "m";
         } else {
            result = (double)number / 1.073741824E9;
            suffix = "g";
         }

         return oneDecimal.format(result) + suffix;
      }
   }

   public static String formatPercent(double done, int digits) {
      DecimalFormat percentFormat = new DecimalFormat("0.00%");
      double scale = Math.pow((double)10.0F, (double)(digits + 2));
      double rounded = Math.floor(done * scale);
      percentFormat.setDecimalSeparatorAlwaysShown(false);
      percentFormat.setMinimumFractionDigits(digits);
      percentFormat.setMaximumFractionDigits(digits);
      return percentFormat.format(rounded / scale);
   }

   public static String arrayToString(String[] strs) {
      if (strs.length == 0) {
         return "";
      } else {
         StringBuilder sbuf = new StringBuilder();
         sbuf.append(strs[0]);

         for(int idx = 1; idx < strs.length; ++idx) {
            sbuf.append(",");
            sbuf.append(strs[idx]);
         }

         return sbuf.toString();
      }
   }

   public static String byteToHexString(byte[] bytes, int start, int end) {
      if (bytes == null) {
         throw new IllegalArgumentException("bytes == null");
      } else {
         StringBuilder s = new StringBuilder();

         for(int i = start; i < end; ++i) {
            s.append(String.format("%02x", bytes[i]));
         }

         return s.toString();
      }
   }

   public static String byteToHexString(byte[] bytes) {
      return byteToHexString(bytes, 0, bytes.length);
   }

   public static byte[] hexStringToByte(String hex) {
      byte[] bts = new byte[hex.length() / 2];

      for(int i = 0; i < bts.length; ++i) {
         bts[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
      }

      return bts;
   }

   public static String uriToString(URI[] uris) {
      if (uris == null) {
         return null;
      } else {
         StringBuilder ret = new StringBuilder(uris[0].toString());

         for(int i = 1; i < uris.length; ++i) {
            ret.append(",");
            ret.append(uris[i].toString());
         }

         return ret.toString();
      }
   }

   public static URI[] stringToURI(String[] str) {
      if (str == null) {
         return null;
      } else {
         URI[] uris = new URI[str.length];

         for(int i = 0; i < str.length; ++i) {
            try {
               uris[i] = new URI(str[i]);
            } catch (URISyntaxException ur) {
               throw new IllegalArgumentException("Failed to create uri for " + str[i], ur);
            }
         }

         return uris;
      }
   }

   public static Path[] stringToPath(String[] str) {
      if (str == null) {
         return null;
      } else {
         Path[] p = new Path[str.length];

         for(int i = 0; i < str.length; ++i) {
            p[i] = new Path(str[i]);
         }

         return p;
      }
   }

   public static String formatTimeDiff(long finishTime, long startTime) {
      long timeDiff = finishTime - startTime;
      return formatTime(timeDiff);
   }

   public static String formatTime(long timeDiff) {
      StringBuilder buf = new StringBuilder();
      long hours = timeDiff / 3600000L;
      long rem = timeDiff % 3600000L;
      long minutes = rem / 60000L;
      rem %= 60000L;
      long seconds = rem / 1000L;
      if (hours != 0L) {
         buf.append(hours);
         buf.append("hrs, ");
      }

      if (minutes != 0L) {
         buf.append(minutes);
         buf.append("mins, ");
      }

      buf.append(seconds);
      buf.append("sec");
      return buf.toString();
   }

   public static String getFormattedTimeWithDiff(DateFormat dateFormat, long finishTime, long startTime) {
      StringBuilder buf = new StringBuilder();
      if (0L != finishTime) {
         buf.append(dateFormat.format(new Date(finishTime)));
         if (0L != startTime) {
            buf.append(" (" + formatTimeDiff(finishTime, startTime) + ")");
         }
      }

      return buf.toString();
   }

   public static String[] getStrings(String str) {
      Collection<String> values = getStringCollection(str);
      return values.size() == 0 ? null : (String[])values.toArray(new String[values.size()]);
   }

   public static Collection getStringCollection(String str) {
      List<String> values = new ArrayList();
      if (str == null) {
         return values;
      } else {
         StringTokenizer tokenizer = new StringTokenizer(str, ",");
         values = new ArrayList();

         while(tokenizer.hasMoreTokens()) {
            values.add(tokenizer.nextToken());
         }

         return values;
      }
   }

   public static Collection getTrimmedStringCollection(String str) {
      return new ArrayList(Arrays.asList(getTrimmedStrings(str)));
   }

   public static String[] getTrimmedStrings(String str) {
      return null != str && !"".equals(str.trim()) ? str.trim().split("\\s*,\\s*") : emptyStringArray;
   }

   public static String[] split(String str) {
      return split(str, '\\', ',');
   }

   public static String[] split(String str, char escapeChar, char separator) {
      if (str == null) {
         return null;
      } else {
         ArrayList<String> strList = new ArrayList();
         StringBuilder split = new StringBuilder();
         int index = 0;

         while((index = findNext(str, separator, escapeChar, index, split)) >= 0) {
            ++index;
            strList.add(split.toString());
            split.setLength(0);
         }

         strList.add(split.toString());
         int last = strList.size();

         while(true) {
            --last;
            if (last < 0 || !"".equals(strList.get(last))) {
               return (String[])strList.toArray(new String[strList.size()]);
            }

            strList.remove(last);
         }
      }
   }

   public static String[] split(String str, char separator) {
      if ("".equals(str)) {
         return new String[]{""};
      } else {
         ArrayList<String> strList = new ArrayList();
         int startIndex = 0;

         int nextIndex;
         for(nextIndex = 0; (nextIndex = str.indexOf(separator, startIndex)) != -1; startIndex = nextIndex + 1) {
            strList.add(str.substring(startIndex, nextIndex));
         }

         strList.add(str.substring(startIndex));
         int last = strList.size();

         while(true) {
            --last;
            if (last < 0 || !"".equals(strList.get(last))) {
               return (String[])strList.toArray(new String[strList.size()]);
            }

            strList.remove(last);
         }
      }
   }

   public static String[] splitAndUnEscape(String str) {
      return splitAndUnEscape(str, '\\', ',');
   }

   public static String[] splitAndUnEscape(String str, char escapeChar, char separator) {
      String[] result = split(str, escapeChar, separator);
      if (result != null) {
         for(int idx = 0; idx < result.length; ++idx) {
            result[idx] = unEscapeString(result[idx], escapeChar, separator);
         }
      }

      return result;
   }

   public static String insertValue(String key, String newValue, String strKvPairs) {
      String[] keyValuePairs = split(strKvPairs);
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < keyValuePairs.length; ++i) {
         String[] pair = split(keyValuePairs[i], '\\', '=');
         if (pair.length != 2) {
            throw new RuntimeException("Error parsing the keyvalue pair " + keyValuePairs[i]);
         }

         sb.append(pair[0]);
         sb.append('=');
         if (pair[0].equals(key)) {
            sb.append(newValue);
         } else {
            sb.append(pair[1]);
         }

         if (i < keyValuePairs.length - 1) {
            sb.append(',');
         }
      }

      return sb.toString();
   }

   public static int findNext(String str, char separator, char escapeChar, int start, StringBuilder split) {
      int numPreEscapes = 0;

      for(int i = start; i < str.length(); ++i) {
         char curChar = str.charAt(i);
         if (numPreEscapes == 0 && curChar == separator) {
            return i;
         }

         split.append(curChar);
         int var10000;
         if (curChar == escapeChar) {
            ++numPreEscapes;
            var10000 = numPreEscapes % 2;
         } else {
            var10000 = 0;
         }

         numPreEscapes = var10000;
      }

      return -1;
   }

   public static String escapeString(String str) {
      return escapeString(str, '\\', ',');
   }

   public static String escapeString(String str, char escapeChar, char charToEscape) {
      return escapeString(str, escapeChar, new char[]{charToEscape});
   }

   private static boolean hasChar(char[] chars, char character) {
      for(char target : chars) {
         if (character == target) {
            return true;
         }
      }

      return false;
   }

   public static String escapeString(String str, char escapeChar, char[] charsToEscape) {
      if (str == null) {
         return null;
      } else {
         StringBuilder result = new StringBuilder();

         for(int i = 0; i < str.length(); ++i) {
            char curChar = str.charAt(i);
            if (curChar == escapeChar || hasChar(charsToEscape, curChar)) {
               result.append(escapeChar);
            }

            result.append(curChar);
         }

         return result.toString();
      }
   }

   public static String escapeJava(String str) {
      return ESCAPE_JAVA.translate(str);
   }

   public static String escapeHiveCommand(String str) {
      return ESCAPE_HIVE_COMMAND.translate(str);
   }

   public static String unEscapeString(String str) {
      return unEscapeString(str, '\\', ',');
   }

   public static String unEscapeString(String str, char escapeChar, char charToEscape) {
      return unEscapeString(str, escapeChar, new char[]{charToEscape});
   }

   public static String unEscapeString(String str, char escapeChar, char[] charsToEscape) {
      if (str == null) {
         return null;
      } else {
         StringBuilder result = new StringBuilder(str.length());
         boolean hasPreEscape = false;

         for(int i = 0; i < str.length(); ++i) {
            char curChar = str.charAt(i);
            if (hasPreEscape) {
               if (curChar != escapeChar && !hasChar(charsToEscape, curChar)) {
                  throw new IllegalArgumentException("Illegal escaped string " + str + " unescaped " + escapeChar + " at " + (i - 1));
               }

               result.append(curChar);
               hasPreEscape = false;
            } else {
               if (hasChar(charsToEscape, curChar)) {
                  throw new IllegalArgumentException("Illegal escaped string " + str + " unescaped " + curChar + " at " + i);
               }

               if (curChar == escapeChar) {
                  hasPreEscape = true;
               } else {
                  result.append(curChar);
               }
            }
         }

         if (hasPreEscape) {
            throw new IllegalArgumentException("Illegal escaped string " + str + ", not expecting " + escapeChar + " in the end.");
         } else {
            return result.toString();
         }
      }
   }

   private static String toStartupShutdownString(String prefix, String[] msg) {
      StringBuilder b = new StringBuilder(prefix);
      b.append("\n/************************************************************");

      for(String s : msg) {
         b.append("\n" + prefix + s);
      }

      b.append("\n************************************************************/");
      return b.toString();
   }

   public static void startupShutdownMessage(Class clazz, String[] args, final Logger LOG) {
      final String hostname = getHostname();
      final String classname = clazz.getSimpleName();
      LOG.info(toStartupShutdownString("STARTUP_MSG: ", new String[]{"Starting " + classname, "  host = " + hostname, "  args = " + Arrays.asList(args), "  version = " + HiveVersionInfo.getVersion(), "  classpath = " + System.getProperty("java.class.path"), "  build = " + HiveVersionInfo.getUrl() + " -r " + HiveVersionInfo.getRevision() + "; compiled by '" + HiveVersionInfo.getUser() + "' on " + HiveVersionInfo.getDate()}));
      ShutdownHookManager.addShutdownHook(new Runnable() {
         public void run() {
            LOG.info(HiveStringUtils.toStartupShutdownString("SHUTDOWN_MSG: ", new String[]{"Shutting down " + classname + " at " + hostname}));
         }
      }, 0);
   }

   public static String getHostname() {
      try {
         return "" + InetAddress.getLocalHost();
      } catch (UnknownHostException uhe) {
         return "" + uhe;
      }
   }

   public static String escapeHTML(String string) {
      if (string == null) {
         return null;
      } else {
         StringBuilder sb = new StringBuilder();
         boolean lastCharacterWasSpace = false;
         char[] chars = string.toCharArray();

         for(char c : chars) {
            if (c == ' ') {
               if (lastCharacterWasSpace) {
                  lastCharacterWasSpace = false;
                  sb.append("&nbsp;");
               } else {
                  lastCharacterWasSpace = true;
                  sb.append(" ");
               }
            } else {
               lastCharacterWasSpace = false;
               switch (c) {
                  case '"':
                     sb.append("&quot;");
                     break;
                  case '&':
                     sb.append("&amp;");
                     break;
                  case '<':
                     sb.append("&lt;");
                     break;
                  case '>':
                     sb.append("&gt;");
                     break;
                  default:
                     sb.append(c);
               }
            }
         }

         return sb.toString();
      }
   }

   public static String byteDesc(long len) {
      double val = (double)0.0F;
      String ending = "";
      if (len < 1048576L) {
         val = (double)1.0F * (double)len / (double)1024.0F;
         ending = " KB";
      } else if (len < 1073741824L) {
         val = (double)1.0F * (double)len / (double)1048576.0F;
         ending = " MB";
      } else if (len < 1099511627776L) {
         val = (double)1.0F * (double)len / 1.073741824E9;
         ending = " GB";
      } else if (len < 1125899906842624L) {
         val = (double)1.0F * (double)len / (double)1.09951163E12F;
         ending = " TB";
      } else {
         val = (double)1.0F * (double)len / (double)1.12589991E15F;
         ending = " PB";
      }

      return limitDecimalTo2(val) + ending;
   }

   public static synchronized String limitDecimalTo2(double d) {
      return decimalFormat.format(d);
   }

   public static String join(CharSequence separator, Iterable strings) {
      Iterator<?> i = strings.iterator();
      if (!i.hasNext()) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder(i.next().toString());

         while(i.hasNext()) {
            sb.append(separator);
            sb.append(i.next().toString());
         }

         return sb.toString();
      }
   }

   public static String joinIgnoringEmpty(String[] strings, char separator) {
      ArrayList<String> list = new ArrayList();

      for(String str : strings) {
         if (StringUtils.isNotBlank(str)) {
            list.add(str);
         }
      }

      return StringUtils.join(list, separator);
   }

   public static String camelize(String s) {
      StringBuilder sb = new StringBuilder();
      String[] words = split(s.toLowerCase(Locale.US), '\\', '_');

      for(String word : words) {
         sb.append(StringUtils.capitalize(word));
      }

      return sb.toString();
   }

   public static boolean isUtfStartByte(byte b) {
      return (b & 192) != 128;
   }

   public static int getTextUtfLength(Text t) {
      byte[] data = t.getBytes();
      int len = 0;

      for(int i = 0; i < t.getLength(); ++i) {
         if (isUtfStartByte(data[i])) {
            ++len;
         }
      }

      return len;
   }

   public static String normalizeIdentifier(String identifier) {
      return identifier.trim().toLowerCase();
   }

   public static Map getPropertiesExplain(Properties properties) {
      if (properties != null) {
         String value = properties.getProperty("columns.comments");
         if (value != null) {
            Map clone = new HashMap(properties);
            clone.put("columns.comments", quoteComments(value));
            return clone;
         }
      }

      return properties;
   }

   public static String quoteComments(String value) {
      char[] chars = value.toCharArray();
      if (!commentProvided(chars)) {
         return null;
      } else {
         StringBuilder builder = new StringBuilder();
         int prev = 0;

         for(int i = 0; i < chars.length; ++i) {
            if (chars[i] == 0) {
               if (builder.length() > 0) {
                  builder.append(',');
               }

               builder.append('\'').append(chars, prev, i - prev).append('\'');
               prev = i + 1;
            }
         }

         builder.append(",'").append(chars, prev, chars.length - prev).append('\'');
         return builder.toString();
      }
   }

   public static boolean commentProvided(char[] chars) {
      for(char achar : chars) {
         if (achar != 0) {
            return true;
         }
      }

      return false;
   }

   public static String getPartitionValWithInvalidCharacter(List partVals, Pattern partitionValidationPattern) {
      if (partitionValidationPattern == null) {
         return null;
      } else {
         for(String partVal : partVals) {
            if (!partitionValidationPattern.matcher(partVal).matches()) {
               return partVal;
            }
         }

         return null;
      }
   }

   static {
      NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
      decimalFormat = (DecimalFormat)numberFormat;
      decimalFormat.applyPattern("#.##");
      STRING_INTERNER = Interners.newWeakInterner();
      oneDecimal = new DecimalFormat("0.0");
      emptyStringArray = new String[0];
   }

   public static enum TraditionalBinaryPrefix {
      KILO(1024L),
      MEGA(KILO.value << 10),
      GIGA(MEGA.value << 10),
      TERA(GIGA.value << 10),
      PETA(TERA.value << 10),
      EXA(PETA.value << 10);

      public final long value;
      public final char symbol;

      private TraditionalBinaryPrefix(long value) {
         this.value = value;
         this.symbol = this.toString().charAt(0);
      }

      public static TraditionalBinaryPrefix valueOf(char symbol) {
         symbol = Character.toUpperCase(symbol);

         for(TraditionalBinaryPrefix prefix : values()) {
            if (symbol == prefix.symbol) {
               return prefix;
            }
         }

         throw new IllegalArgumentException("Unknown symbol '" + symbol + "'");
      }

      public static long string2long(String s) {
         s = s.trim();
         int lastpos = s.length() - 1;
         char lastchar = s.charAt(lastpos);
         if (Character.isDigit(lastchar)) {
            return Long.parseLong(s);
         } else {
            long prefix;
            try {
               prefix = valueOf(lastchar).value;
            } catch (IllegalArgumentException var7) {
               throw new IllegalArgumentException("Invalid size prefix '" + lastchar + "' in '" + s + "'. Allowed prefixes are k, m, g, t, p, e(case insensitive)");
            }

            long num = Long.parseLong(s.substring(0, lastpos));
            if (num <= Long.MAX_VALUE / prefix && num >= Long.MIN_VALUE / prefix) {
               return num * prefix;
            } else {
               throw new IllegalArgumentException(s + " does not fit in a Long");
            }
         }
      }
   }
}
