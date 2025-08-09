package jodd.util;

import java.util.Map;

public class StringTemplateParser {
   public static final String DEFAULT_MACRO_START = "${";
   public static final String DEFAULT_MACRO_END = "}";
   protected boolean replaceMissingKey = true;
   protected String missingKeyReplacement;
   protected boolean resolveEscapes = true;
   protected String macroStart = "${";
   protected String macroEnd = "}";
   protected char escapeChar = '\\';
   protected boolean parseValues;

   public boolean isReplaceMissingKey() {
      return this.replaceMissingKey;
   }

   public void setReplaceMissingKey(boolean replaceMissingKey) {
      this.replaceMissingKey = replaceMissingKey;
   }

   public String getMissingKeyReplacement() {
      return this.missingKeyReplacement;
   }

   public void setMissingKeyReplacement(String missingKeyReplacement) {
      this.missingKeyReplacement = missingKeyReplacement;
   }

   public boolean isResolveEscapes() {
      return this.resolveEscapes;
   }

   public void setResolveEscapes(boolean resolveEscapes) {
      this.resolveEscapes = resolveEscapes;
   }

   public String getMacroStart() {
      return this.macroStart;
   }

   public void setMacroStart(String macroStart) {
      this.macroStart = macroStart;
   }

   public String getMacroEnd() {
      return this.macroEnd;
   }

   public void setMacroEnd(String macroEnd) {
      this.macroEnd = macroEnd;
   }

   public char getEscapeChar() {
      return this.escapeChar;
   }

   public void setEscapeChar(char escapeChar) {
      this.escapeChar = escapeChar;
   }

   public boolean isParseValues() {
      return this.parseValues;
   }

   public void setParseValues(boolean parseValues) {
      this.parseValues = parseValues;
   }

   public String parse(String template, MacroResolver macroResolver) {
      StringBuilder result = new StringBuilder(template.length());
      int i = 0;
      int len = template.length();
      int startLen = this.macroStart.length();
      int endLen = this.macroEnd.length();

      while(i < len) {
         int ndx = template.indexOf(this.macroStart, i);
         if (ndx == -1) {
            result.append(i == 0 ? template : template.substring(i));
            break;
         }

         int j = ndx - 1;
         boolean escape = false;

         int count;
         for(count = 0; j >= 0 && template.charAt(j) == this.escapeChar; --j) {
            escape = !escape;
            if (escape) {
               ++count;
            }
         }

         if (this.resolveEscapes) {
            result.append(template.substring(i, ndx - count));
         } else {
            result.append(template.substring(i, ndx));
         }

         if (escape) {
            result.append(this.macroStart);
            i = ndx + startLen;
         } else {
            ndx += startLen;
            int ndx2 = template.indexOf(this.macroEnd, ndx);
            if (ndx2 == -1) {
               throw new IllegalArgumentException("Invalid template, unclosed macro at: " + (ndx - startLen));
            }

            int ndx1;
            int n;
            for(ndx1 = ndx; ndx1 < ndx2; ndx1 = n + startLen) {
               n = StringUtil.indexOf(template, this.macroStart, ndx1, ndx2);
               if (n == -1) {
                  break;
               }
            }

            String name = template.substring(ndx1, ndx2);
            Object value;
            if (this.missingKeyReplacement == null && this.replaceMissingKey) {
               value = macroResolver.resolve(name);
               if (value == null) {
                  value = "";
               }
            } else {
               try {
                  value = macroResolver.resolve(name);
               } catch (Exception var17) {
                  value = null;
               }

               if (value == null) {
                  if (this.replaceMissingKey) {
                     value = this.missingKeyReplacement;
                  } else {
                     value = template.substring(ndx1 - startLen, ndx2 + 1);
                  }
               }
            }

            if (ndx == ndx1) {
               String stringValue = value.toString();
               if (this.parseValues && stringValue.contains(this.macroStart)) {
                  stringValue = this.parse(stringValue, macroResolver);
               }

               result.append(stringValue);
               i = ndx2 + endLen;
            } else {
               template = template.substring(0, ndx1 - startLen) + value.toString() + template.substring(ndx2 + endLen);
               len = template.length();
               i = ndx - startLen;
            }
         }
      }

      return result.toString();
   }

   public static MacroResolver createMapMacroResolver(final Map map) {
      return new MacroResolver() {
         public String resolve(String macroName) {
            Object value = map.get(macroName);
            return value == null ? null : value.toString();
         }
      };
   }

   public interface MacroResolver {
      String resolve(String var1);
   }
}
