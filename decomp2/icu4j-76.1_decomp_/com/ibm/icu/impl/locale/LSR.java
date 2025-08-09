package com.ibm.icu.impl.locale;

import com.ibm.icu.lang.UScript;
import java.util.List;
import java.util.Objects;

public final class LSR {
   public static final int REGION_INDEX_LIMIT = 1677;
   public static final int EXPLICIT_LSR = 7;
   public static final int EXPLICIT_LANGUAGE = 4;
   public static final int EXPLICIT_SCRIPT = 2;
   public static final int EXPLICIT_REGION = 1;
   public static final int IMPLICIT_LSR = 0;
   public static final int DONT_CARE_FLAGS = 0;
   public static final boolean DEBUG_OUTPUT = false;
   public final String language;
   public final String script;
   public final String region;
   final int regionIndex;
   public final int flags;

   public LSR(String language, String script, String region, int flags) {
      this.language = language;
      this.script = script;
      this.region = region;
      this.regionIndex = indexForRegion(region);
      this.flags = flags;
   }

   public static final int indexForRegion(String region) {
      if (region.length() == 2) {
         int a = region.charAt(0) - 65;
         if (a >= 0 && 25 >= a) {
            int b = region.charAt(1) - 65;
            return b >= 0 && 25 >= b ? 26 * a + b + 1001 : 0;
         } else {
            return 0;
         }
      } else if (region.length() == 3) {
         int a = region.charAt(0) - 48;
         if (a >= 0 && 9 >= a) {
            int b = region.charAt(1) - 48;
            if (b >= 0 && 9 >= b) {
               int c = region.charAt(2) - 48;
               return c >= 0 && 9 >= c ? (10 * a + b) * 10 + c + 1 : 0;
            } else {
               return 0;
            }
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   public String toString() {
      StringBuilder result = new StringBuilder(this.language);
      if (!this.script.isEmpty()) {
         result.append('-').append(this.script);
      }

      if (!this.region.isEmpty()) {
         result.append('-').append(this.region);
      }

      return result.toString();
   }

   public boolean isEquivalentTo(LSR other) {
      return this.language.equals(other.language) && this.script.equals(other.script) && this.region.equals(other.region);
   }

   public boolean equals(Object obj) {
      LSR other;
      return this == obj || obj != null && obj.getClass() == this.getClass() && this.language.equals((other = (LSR)obj).language) && this.script.equals(other.script) && this.region.equals(other.region) && this.flags == other.flags;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.language, this.script, this.region, this.flags});
   }

   private int encodeLanguageToInt() {
      assert this.language.length() >= 2;

      assert this.language.length() <= 3;

      assert this.language.charAt(0) >= 'a';

      assert this.language.charAt(0) <= 'z';

      assert this.language.charAt(1) >= 'a';

      assert this.language.charAt(1) <= 'z';

      assert this.language.length() == 2 || this.language.charAt(2) >= 'a';

      assert this.language.length() == 2 || this.language.charAt(2) <= 'z';

      return this.language.charAt(0) - 97 + 1 + 27 * (this.language.charAt(1) - 97 + 1) + (this.language.length() == 2 ? 0 : 729 * (this.language.charAt(2) - 97 + 1));
   }

   private int encodeScriptToInt() {
      int ret = UScript.getCodeFromName(this.script);

      assert ret != -1;

      return ret;
   }

   private int encodeRegionToInt(List m49) {
      assert this.region.length() >= 2;

      assert this.region.length() <= 3;

      if (this.region.length() == 3) {
         int index = m49.indexOf(this.region);

         assert index >= 0;

         if (index < 0) {
            throw new IllegalStateException("Please add '" + this.region + "' to M49 in LocaleDistanceMapper.java");
         } else {
            return index;
         }
      } else {
         assert this.region.charAt(0) >= 'A';

         assert this.region.charAt(0) <= 'Z';

         assert this.region.charAt(1) >= 'A';

         assert this.region.charAt(1) <= 'Z';

         return this.region.charAt(0) - 65 + 1 + 27 * (this.region.charAt(1) - 65 + 1);
      }
   }

   public int encodeToIntForResource(List m49) {
      return this.encodeLanguageToInt() + 19683 * this.encodeRegionToInt(m49) | this.encodeScriptToInt() << 24;
   }

   private static String toLanguage(int encoded) {
      if (encoded == 0) {
         return "";
      } else if (encoded == 1) {
         return "skip";
      } else {
         encoded &= 16777215;
         encoded %= 19683;
         StringBuilder res = new StringBuilder(3);
         res.append((char)(97 + (encoded % 27 - 1)));
         res.append((char)(97 + (encoded / 27 % 27 - 1)));
         if (encoded / 729 != 0) {
            res.append((char)(97 + (encoded / 729 - 1)));
         }

         return res.toString();
      }
   }

   private static String toScript(int encoded) {
      if (encoded == 0) {
         return "";
      } else if (encoded == 1) {
         return "script";
      } else {
         encoded = encoded >> 24 & 255;
         return UScript.getShortName(encoded);
      }
   }

   private static String toRegion(int encoded, String[] m49) {
      if (encoded != 0 && encoded != 1) {
         encoded &= 16777215;
         encoded /= 19683;
         encoded %= 729;
         if (encoded < 27) {
            return m49[encoded];
         } else {
            StringBuilder res = new StringBuilder(3);
            res.append((char)(65 + (encoded % 27 - 1)));
            res.append((char)(65 + (encoded / 27 % 27 - 1)));
            return res.toString();
         }
      } else {
         return "";
      }
   }

   public static LSR[] decodeInts(int[] nums, String[] m49) {
      LSR[] lsrs = new LSR[nums.length];

      for(int i = 0; i < nums.length; ++i) {
         int n = nums[i];
         lsrs[i] = new LSR(toLanguage(n), toScript(n), toRegion(n, m49), 0);
      }

      return lsrs;
   }
}
