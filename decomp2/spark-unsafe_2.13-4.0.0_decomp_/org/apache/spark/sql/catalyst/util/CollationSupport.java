package org.apache.spark.sql.catalyst.util;

import com.ibm.icu.text.StringSearch;
import java.util.Map;
import org.apache.spark.unsafe.types.UTF8String;

public final class CollationSupport {
   static final int lowercaseRegexFlags = 66;
   private static final UTF8String lowercaseRegexPrefix = UTF8String.fromString("(?ui)");

   public static boolean supportsLowercaseRegex(int collationId) {
      return CollationFactory.fetchCollation(collationId).isUtf8LcaseType;
   }

   public static int collationAwareRegexFlags(int collationId) {
      return supportsLowercaseRegex(collationId) ? 66 : 0;
   }

   public static UTF8String lowercaseRegex(UTF8String regex) {
      return UTF8String.concat(lowercaseRegexPrefix, regex);
   }

   public static UTF8String collationAwareRegex(UTF8String regex, int collationId) {
      return supportsLowercaseRegex(collationId) ? lowercaseRegex(regex) : regex;
   }

   public static class StringSplitSQL {
      public static UTF8String[] exec(UTF8String s, UTF8String d, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            d = CollationFactory.applyTrimmingPolicy(d, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(s, d);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(s, d) : execICU(s, d, collationId);
         }
      }

      public static String genCode(String s, String d, int collationId) {
         String expr = "CollationSupport.StringSplitSQL.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", s, d) : String.format(expr + "(%s, %s, %d)", s, d, collationId);
      }

      public static UTF8String[] execBinary(UTF8String string, UTF8String delimiter) {
         return string.splitSQL(delimiter, -1);
      }

      public static UTF8String[] execLowercase(UTF8String string, UTF8String delimiter) {
         return CollationAwareUTF8String.lowercaseSplitSQL(string, delimiter, -1);
      }

      public static UTF8String[] execICU(UTF8String string, UTF8String delimiter, int collationId) {
         return CollationAwareUTF8String.icuSplitSQL(string, delimiter, -1, collationId);
      }
   }

   public static class Contains {
      public static boolean exec(UTF8String l, UTF8String r, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            l = CollationFactory.applyTrimmingPolicy(l, collationId);
            r = CollationFactory.applyTrimmingPolicy(r, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(l, r);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(l, r) : execICU(l, r, collationId);
         }
      }

      public static String genCode(String l, String r, int collationId) {
         String expr = "CollationSupport.Contains.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", l, r) : String.format(expr + "(%s, %s, %d)", l, r, collationId);
      }

      public static boolean execBinary(UTF8String l, UTF8String r) {
         return l.contains(r);
      }

      public static boolean execLowercase(UTF8String l, UTF8String r) {
         return CollationAwareUTF8String.lowercaseContains(l, r);
      }

      public static boolean execICU(UTF8String l, UTF8String r, int collationId) {
         if (r.numBytes() == 0) {
            return true;
         } else if (l.numBytes() == 0) {
            return false;
         } else {
            StringSearch stringSearch = CollationFactory.getStringSearch(l, r, collationId);
            return stringSearch.first() != -1;
         }
      }
   }

   public static class StartsWith {
      public static boolean exec(UTF8String l, UTF8String r, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            l = CollationFactory.applyTrimmingPolicy(l, collationId);
            r = CollationFactory.applyTrimmingPolicy(r, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(l, r);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(l, r) : execICU(l, r, collationId);
         }
      }

      public static String genCode(String l, String r, int collationId) {
         String expr = "CollationSupport.StartsWith.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", l, r) : String.format(expr + "(%s, %s, %d)", l, r, collationId);
      }

      public static boolean execBinary(UTF8String l, UTF8String r) {
         return l.startsWith(r);
      }

      public static boolean execLowercase(UTF8String l, UTF8String r) {
         return CollationAwareUTF8String.lowercaseStartsWith(l, r);
      }

      public static boolean execICU(UTF8String l, UTF8String r, int collationId) {
         if (r.numBytes() == 0) {
            return true;
         } else if (l.numBytes() == 0) {
            return false;
         } else {
            StringSearch stringSearch = CollationFactory.getStringSearch(l, r, collationId);
            return stringSearch.first() == 0;
         }
      }
   }

   public static class EndsWith {
      public static boolean exec(UTF8String l, UTF8String r, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            l = CollationFactory.applyTrimmingPolicy(l, collationId);
            r = CollationFactory.applyTrimmingPolicy(r, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(l, r);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(l, r) : execICU(l, r, collationId);
         }
      }

      public static String genCode(String l, String r, int collationId) {
         String expr = "CollationSupport.EndsWith.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", l, r) : String.format(expr + "(%s, %s, %d)", l, r, collationId);
      }

      public static boolean execBinary(UTF8String l, UTF8String r) {
         return l.endsWith(r);
      }

      public static boolean execLowercase(UTF8String l, UTF8String r) {
         return CollationAwareUTF8String.lowercaseEndsWith(l, r);
      }

      public static boolean execICU(UTF8String l, UTF8String r, int collationId) {
         if (r.numBytes() == 0) {
            return true;
         } else if (l.numBytes() == 0) {
            return false;
         } else {
            StringSearch stringSearch = CollationFactory.getStringSearch(l, r, collationId);
            int endIndex = stringSearch.getTarget().getEndIndex();
            return stringSearch.last() == endIndex - stringSearch.getMatchLength();
         }
      }
   }

   public static class Upper {
      public static UTF8String exec(UTF8String v, int collationId, boolean useICU) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType) {
            return useICU ? execBinaryICU(v) : execBinary(v);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(v) : execICU(v, collationId);
         }
      }

      public static String genCode(String v, int collationId, boolean useICU) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         String expr = "CollationSupport.Upper.exec";
         if (collation.isUtf8BinaryType) {
            String funcName = useICU ? "BinaryICU" : "Binary";
            return String.format(expr + "%s(%s)", funcName, v);
         } else {
            return collation.isUtf8LcaseType ? String.format(expr + "Lowercase(%s)", v) : String.format(expr + "ICU(%s, %d)", v, collationId);
         }
      }

      public static UTF8String execBinary(UTF8String v) {
         return v.toUpperCase();
      }

      public static UTF8String execBinaryICU(UTF8String v) {
         return CollationAwareUTF8String.toUpperCase(v);
      }

      public static UTF8String execLowercase(UTF8String v) {
         return CollationAwareUTF8String.toUpperCase(v);
      }

      public static UTF8String execICU(UTF8String v, int collationId) {
         return CollationAwareUTF8String.toUpperCase(v, collationId);
      }
   }

   public static class Lower {
      public static UTF8String exec(UTF8String v, int collationId, boolean useICU) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType) {
            return useICU ? execBinaryICU(v) : execBinary(v);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(v) : execICU(v, collationId);
         }
      }

      public static String genCode(String v, int collationId, boolean useICU) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         String expr = "CollationSupport.Lower.exec";
         if (collation.isUtf8BinaryType) {
            String funcName = useICU ? "BinaryICU" : "Binary";
            return String.format(expr + "%s(%s)", funcName, v);
         } else {
            return collation.isUtf8LcaseType ? String.format(expr + "Lowercase(%s)", v) : String.format(expr + "ICU(%s, %d)", v, collationId);
         }
      }

      public static UTF8String execBinary(UTF8String v) {
         return v.toLowerCase();
      }

      public static UTF8String execBinaryICU(UTF8String v) {
         return CollationAwareUTF8String.toLowerCase(v);
      }

      public static UTF8String execLowercase(UTF8String v) {
         return CollationAwareUTF8String.toLowerCase(v);
      }

      public static UTF8String execICU(UTF8String v, int collationId) {
         return CollationAwareUTF8String.toLowerCase(v, collationId);
      }
   }

   public static class InitCap {
      public static UTF8String exec(UTF8String v, int collationId, boolean useICU) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType) {
            return useICU ? execBinaryICU(v) : execBinary(v);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(v) : execICU(v, collationId);
         }
      }

      public static String genCode(String v, int collationId, boolean useICU) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         String expr = "CollationSupport.InitCap.exec";
         if (collation.isUtf8BinaryType) {
            String funcName = useICU ? "BinaryICU" : "Binary";
            return String.format(expr + "%s(%s)", funcName, v);
         } else {
            return collation.isUtf8LcaseType ? String.format(expr + "Lowercase(%s)", v) : String.format(expr + "ICU(%s, %d)", v, collationId);
         }
      }

      public static UTF8String execBinary(UTF8String v) {
         return v.toLowerCase().toTitleCase();
      }

      public static UTF8String execBinaryICU(UTF8String v) {
         return CollationAwareUTF8String.toTitleCaseICU(v);
      }

      public static UTF8String execLowercase(UTF8String v) {
         return CollationAwareUTF8String.toTitleCase(v);
      }

      public static UTF8String execICU(UTF8String v, int collationId) {
         return CollationAwareUTF8String.toTitleCase(v, collationId);
      }
   }

   public static class FindInSet {
      public static int exec(UTF8String word, UTF8String set, int collationId) {
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? execBinary(word, set) : execCollationAware(word, set, collationId);
      }

      public static String genCode(String word, String set, int collationId) {
         String expr = "CollationSupport.FindInSet.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", word, set) : String.format(expr + "CollationAware(%s, %s, %d)", word, set, collationId);
      }

      public static int execBinary(UTF8String word, UTF8String set) {
         return set.findInSet(word);
      }

      public static int execCollationAware(UTF8String word, UTF8String set, int collationId) {
         return CollationAwareUTF8String.findInSet(word, set, collationId);
      }
   }

   public static class StringInstr {
      public static int exec(UTF8String string, UTF8String substring, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            substring = CollationFactory.applyTrimmingPolicy(substring, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(string, substring);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(string, substring) : execICU(string, substring, collationId);
         }
      }

      public static String genCode(String string, String substring, int collationId) {
         String expr = "CollationSupport.StringInstr.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", string, substring) : String.format(expr + "(%s, %s, %d)", string, substring, collationId);
      }

      public static int execBinary(UTF8String string, UTF8String substring) {
         return string.indexOf(substring, 0);
      }

      public static int execLowercase(UTF8String string, UTF8String substring) {
         return CollationAwareUTF8String.lowercaseIndexOf(string, substring, 0);
      }

      public static int execICU(UTF8String string, UTF8String substring, int collationId) {
         return CollationAwareUTF8String.indexOf(string, substring, 0, collationId);
      }
   }

   public static class StringReplace {
      public static UTF8String exec(UTF8String src, UTF8String search, UTF8String replace, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType) {
            return execBinary(src, search, replace);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(src, search, replace) : execICU(src, search, replace, collationId);
         }
      }

      public static String genCode(String src, String search, String replace, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         String expr = "CollationSupport.StringReplace.exec";
         if (collation.isUtf8BinaryType) {
            return String.format(expr + "Binary(%s, %s, %s)", src, search, replace);
         } else {
            return collation.isUtf8LcaseType ? String.format(expr + "Lowercase(%s, %s, %s)", src, search, replace) : String.format(expr + "ICU(%s, %s, %s, %d)", src, search, replace, collationId);
         }
      }

      public static UTF8String execBinary(UTF8String src, UTF8String search, UTF8String replace) {
         return src.replace(search, replace);
      }

      public static UTF8String execLowercase(UTF8String src, UTF8String search, UTF8String replace) {
         return CollationAwareUTF8String.lowercaseReplace(src, search, replace);
      }

      public static UTF8String execICU(UTF8String src, UTF8String search, UTF8String replace, int collationId) {
         return CollationAwareUTF8String.replace(src, search, replace, collationId);
      }
   }

   public static class StringLocate {
      public static int exec(UTF8String string, UTF8String substring, int start, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            substring = CollationFactory.applyTrimmingPolicy(substring, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(string, substring, start);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(string, substring, start) : execICU(string, substring, start, collationId);
         }
      }

      public static String genCode(String string, String substring, int start, int collationId) {
         String expr = "CollationSupport.StringLocate.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s, %d)", string, substring, start) : String.format(expr + "(%s, %s, %d, %d)", string, substring, start, collationId);
      }

      public static int execBinary(UTF8String string, UTF8String substring, int start) {
         return string.indexOf(substring, start);
      }

      public static int execLowercase(UTF8String string, UTF8String substring, int start) {
         return CollationAwareUTF8String.lowercaseIndexOf(string, substring, start);
      }

      public static int execICU(UTF8String string, UTF8String substring, int start, int collationId) {
         return CollationAwareUTF8String.indexOf(string, substring, start, collationId);
      }
   }

   public static class SubstringIndex {
      public static UTF8String exec(UTF8String string, UTF8String delimiter, int count, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.supportsSpaceTrimming) {
            delimiter = CollationFactory.applyTrimmingPolicy(delimiter, collationId);
         }

         if (collation.isUtf8BinaryType) {
            return execBinary(string, delimiter, count);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(string, delimiter, count) : execICU(string, delimiter, count, collationId);
         }
      }

      public static String genCode(String string, String delimiter, String count, int collationId) {
         String expr = "CollationSupport.SubstringIndex.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s, %s)", string, delimiter, count) : String.format(expr + "(%s, %s, %s, %d)", string, delimiter, count, collationId);
      }

      public static UTF8String execBinary(UTF8String string, UTF8String delimiter, int count) {
         return string.subStringIndex(delimiter, count);
      }

      public static UTF8String execLowercase(UTF8String string, UTF8String delimiter, int count) {
         return CollationAwareUTF8String.lowercaseSubStringIndex(string, delimiter, count);
      }

      public static UTF8String execICU(UTF8String string, UTF8String delimiter, int count, int collationId) {
         return CollationAwareUTF8String.subStringIndex(string, delimiter, count, collationId);
      }
   }

   public static class StringTranslate {
      public static UTF8String exec(UTF8String source, Map dict, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType) {
            return execBinary(source, dict);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(source, dict) : execICU(source, dict, collationId);
         }
      }

      public static UTF8String execBinary(UTF8String source, Map dict) {
         return source.translate(dict);
      }

      public static UTF8String execLowercase(UTF8String source, Map dict) {
         return CollationAwareUTF8String.lowercaseTranslate(source, dict);
      }

      public static UTF8String execICU(UTF8String source, Map dict, int collationId) {
         return CollationAwareUTF8String.translate(source, dict, collationId);
      }
   }

   public static class StringTrim {
      public static UTF8String exec(UTF8String srcString) {
         return execBinary(srcString);
      }

      public static UTF8String exec(UTF8String srcString, UTF8String trimString, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType && !collation.supportsSpaceTrimming) {
            return execBinary(srcString, trimString);
         } else if (collation.isUtf8BinaryType) {
            return execBinaryTrim(srcString, trimString, collationId);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(srcString, trimString, collationId) : execICU(srcString, trimString, collationId);
         }
      }

      public static String genCode(String srcString) {
         return String.format("CollationSupport.StringTrim.execBinary(%s)", srcString);
      }

      public static String genCode(String srcString, String trimString, int collationId) {
         String expr = "CollationSupport.StringTrim.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", srcString, trimString) : String.format(expr + "(%s, %s, %d)", srcString, trimString, collationId);
      }

      public static UTF8String execBinary(UTF8String srcString) {
         return srcString.trim();
      }

      public static UTF8String execBinary(UTF8String srcString, UTF8String trimString) {
         return srcString.trim(trimString);
      }

      public static UTF8String execLowercase(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.lowercaseTrim(srcString, trimString, collationId);
      }

      public static UTF8String execICU(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.trim(srcString, trimString, collationId);
      }

      public static UTF8String execBinaryTrim(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.binaryTrim(srcString, trimString, collationId);
      }
   }

   public static class StringTrimLeft {
      public static UTF8String exec(UTF8String srcString) {
         return execBinary(srcString);
      }

      public static UTF8String exec(UTF8String srcString, UTF8String trimString, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType) {
            return execBinary(srcString, trimString);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(srcString, trimString) : execICU(srcString, trimString, collationId);
         }
      }

      public static String genCode(String srcString) {
         return String.format("CollationSupport.StringTrimLeft.execBinary(%s)", srcString);
      }

      public static String genCode(String srcString, String trimString, int collationId) {
         String expr = "CollationSupport.StringTrimLeft.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", srcString, trimString) : String.format(expr + "(%s, %s, %d)", srcString, trimString, collationId);
      }

      public static UTF8String execBinary(UTF8String srcString) {
         return srcString.trimLeft();
      }

      public static UTF8String execBinary(UTF8String srcString, UTF8String trimString) {
         return srcString.trimLeft(trimString);
      }

      public static UTF8String execLowercase(UTF8String srcString, UTF8String trimString) {
         return CollationAwareUTF8String.lowercaseTrimLeft(srcString, trimString);
      }

      public static UTF8String execICU(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.trimLeft(srcString, trimString, collationId);
      }
   }

   public static class StringTrimRight {
      public static UTF8String exec(UTF8String srcString) {
         return execBinary(srcString);
      }

      public static UTF8String exec(UTF8String srcString, UTF8String trimString, int collationId) {
         CollationFactory.Collation collation = CollationFactory.fetchCollation(collationId);
         if (collation.isUtf8BinaryType && !collation.supportsSpaceTrimming) {
            return execBinary(srcString, trimString);
         } else if (collation.isUtf8BinaryType) {
            return execBinaryTrim(srcString, trimString, collationId);
         } else {
            return collation.isUtf8LcaseType ? execLowercase(srcString, trimString, collationId) : execICU(srcString, trimString, collationId);
         }
      }

      public static String genCode(String srcString) {
         return String.format("CollationSupport.StringTrimRight.execBinary(%s)", srcString);
      }

      public static String genCode(String srcString, String trimString, int collationId) {
         String expr = "CollationSupport.StringTrimRight.exec";
         return collationId == CollationFactory.UTF8_BINARY_COLLATION_ID ? String.format(expr + "Binary(%s, %s)", srcString, trimString) : String.format(expr + "(%s, %s, %d)", srcString, trimString, collationId);
      }

      public static UTF8String execBinary(UTF8String srcString) {
         return srcString.trimRight();
      }

      public static UTF8String execBinary(UTF8String srcString, UTF8String trimString) {
         return srcString.trimRight(trimString);
      }

      public static UTF8String execLowercase(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.lowercaseTrimRight(srcString, trimString, collationId);
      }

      public static UTF8String execICU(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.trimRight(srcString, trimString, collationId);
      }

      public static UTF8String execBinaryTrim(UTF8String srcString, UTF8String trimString, int collationId) {
         return CollationAwareUTF8String.binaryTrimRight(srcString, trimString, collationId);
      }
   }
}
