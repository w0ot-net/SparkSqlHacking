package org.sparkproject.dmg.pmml;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class VersionUtil {
   private static Map functionVersions = new LinkedHashMap();

   private VersionUtil() {
   }

   public static int compare(int[] left, int[] right) {
      int i = 0;

      for(int max = Math.max(left.length, right.length); i < max; ++i) {
         int diff = Integer.compare(left[i], right[i]);
         if (diff != 0) {
            return diff;
         }
      }

      return 0;
   }

   public static int compare(String left, String right) {
      return compare(parse(left), parse(right));
   }

   public static int[] parse(String string) {
      StringTokenizer st = new StringTokenizer(string, ".");
      int[] result = new int[st.countTokens()];

      for(int i = 0; st.hasMoreTokens(); ++i) {
         result[i] = Integer.parseInt(st.nextToken());
      }

      return result;
   }

   public static Version getVersion(String function) {
      return (Version)functionVersions.get(function);
   }

   private static void declareFunctions(Version version, String... functions) {
      for(String function : functions) {
         functionVersions.put(function, version);
      }

   }

   static {
      declareFunctions(Version.PMML_3_0, "+", "-", "*", "/", "min", "max", "sum", "avg", "log10", "ln", "sqrt", "abs", "uppercase", "substring", "trimBlanks", "formatNumber", "formatDatetime", "dateDaysSinceYear", "dateSecondsSinceYear", "dateSecondsSinceMidnight");
      declareFunctions(Version.PMML_3_1, "exp", "pow", "threshold", "floor", "ceil", "round");
      declareFunctions(Version.PMML_4_0, "isMissing", "isNotMissing", "equal", "notEqual", "lessThan", "lessOrEqual", "greaterThan", "greaterOrEqual", "and", "or", "not", "isIn", "isNotIn", "if");
      declareFunctions(Version.PMML_4_1, "median", "product", "lowercase");
      declareFunctions(Version.PMML_4_2, "concat", "replace", "matches");
      declareFunctions(Version.PMML_4_3, "normalCDF", "normalPDF", "stdNormalCDF", "stdNormalPDF", "erf", "normalIDF", "stdNormalIDF");
      declareFunctions(Version.PMML_4_4, "modulo", "isValid", "isNotValid", "expm1", "hypot", "ln1p", "rint", "stringLength", "sin", "asin", "sinh", "cos", "acos", "cosh", "tan", "atan", "tanh");
      declareFunctions(Version.XPMML, "x-atan2");
   }
}
