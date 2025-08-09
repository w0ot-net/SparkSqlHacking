package org.sparkproject.jpmml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.ComplexValue;

public class ArrayUtil {
   private ArrayUtil() {
   }

   public static List parse(Array.Type type, String string) {
      List<String> result = new ArrayList(Math.max(16, string.length() / 8));
      StringBuilder sb = new StringBuilder();
      boolean enableQuotes;
      switch (type) {
         case INT:
         case REAL:
            enableQuotes = false;
            break;
         case STRING:
            enableQuotes = true;
            break;
         default:
            throw new IllegalArgumentException();
      }

      boolean quoted = false;

      for(int i = 0; i < string.length(); ++i) {
         char c = string.charAt(i);
         if (quoted) {
            if (c == '\\' && i < string.length() - 1) {
               c = string.charAt(i + 1);
               if (c == '"') {
                  sb.append('"');
                  ++i;
               } else {
                  sb.append('\\');
               }
            } else {
               sb.append(c);
               if (c == '"') {
                  result.add(createToken(sb, enableQuotes));
                  quoted = false;
               }
            }
         } else if (c == '"' && enableQuotes) {
            if (sb.length() > 0) {
               result.add(createToken(sb, enableQuotes));
            }

            sb.append('"');
            quoted = true;
         } else if (Character.isWhitespace(c)) {
            if (sb.length() > 0) {
               result.add(createToken(sb, enableQuotes));
            }
         } else {
            sb.append(c);
         }
      }

      if (sb.length() > 0) {
         result.add(createToken(sb, enableQuotes));
      }

      return result;
   }

   public static String format(Array.Type type, Collection values) {
      StringBuilder sb = new StringBuilder(values.size() * 16);
      boolean enableQuotes;
      switch (type) {
         case INT:
         case REAL:
            enableQuotes = false;
            break;
         case STRING:
            enableQuotes = true;
            break;
         default:
            throw new IllegalArgumentException();
      }

      for(Object value : values) {
         if (value instanceof ComplexValue) {
            ComplexValue complexValue = (ComplexValue)value;
            value = complexValue.toSimpleValue();
         }

         String string = value.toString();
         if (sb.length() > 0) {
            sb.append(' ');
         }

         if (enableQuotes) {
            boolean quoted = "".equals(string) || string.indexOf(32) > -1;
            if (quoted) {
               sb.append('"');
            }

            if (string.indexOf(34) > -1) {
               for(int i = 0; i < string.length(); ++i) {
                  char c = string.charAt(i);
                  if (c == '"') {
                     sb.append('\\');
                  }

                  sb.append(c);
               }
            } else {
               sb.append(string);
            }

            if (quoted) {
               sb.append('"');
            }
         } else {
            sb.append(string);
         }
      }

      return sb.toString();
   }

   private static String createToken(StringBuilder sb, boolean enableQuotes) {
      String result;
      if (sb.length() > 1 && sb.charAt(0) == '"' && sb.charAt(sb.length() - 1) == '"' && enableQuotes) {
         result = sb.substring(1, sb.length() - 1);
      } else {
         result = sb.substring(0, sb.length());
      }

      sb.setLength(0);
      return result;
   }
}
