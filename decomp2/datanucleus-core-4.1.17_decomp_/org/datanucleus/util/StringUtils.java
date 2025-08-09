package org.datanucleus.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.JarFile;
import org.datanucleus.exceptions.NucleusException;

public class StringUtils {
   public static String getStringFromStackTrace(Throwable ex) {
      if (ex == null) {
         return "";
      } else {
         StringWriter str = new StringWriter();
         PrintWriter writer = new PrintWriter(str);

         String var3;
         try {
            ex.printStackTrace(writer);
            var3 = str.getBuffer().toString();
         } finally {
            try {
               str.close();
               writer.close();
            } catch (IOException var10) {
            }

         }

         return var3;
      }
   }

   public static File getFileForFilename(String filename) {
      return new File(getDecodedStringFromURLString(filename));
   }

   public static JarFile getJarFileForFilename(String filename) throws IOException {
      return new JarFile(getDecodedStringFromURLString(filename));
   }

   public static String getDecodedStringFromURLString(String urlString) {
      String str = urlString.replace("+", "%2B");

      try {
         return URLDecoder.decode(str, "UTF-8");
      } catch (UnsupportedEncodingException uee) {
         throw new NucleusException("Error attempting to decode string", uee);
      }
   }

   public static String getEncodedURLStringFromString(String string) {
      try {
         return URLEncoder.encode(string, "UTF-8");
      } catch (UnsupportedEncodingException uee) {
         throw new NucleusException("Error attempting to encode string", uee);
      }
   }

   public static String replaceAll(String theString, String toReplace, String replacement) {
      if (theString == null) {
         return null;
      } else if (theString.indexOf(toReplace) == -1) {
         return theString;
      } else {
         StringBuilder stringBuilder = new StringBuilder(theString);
         int index = theString.length();
         int offset = toReplace.length();

         while((index = theString.lastIndexOf(toReplace, index - 1)) > -1) {
            stringBuilder.replace(index, index + offset, replacement);
         }

         return stringBuilder.toString();
      }
   }

   public static boolean isWhitespace(String str) {
      return str == null || str.length() == 0 || str.trim().length() == 0;
   }

   public static boolean areStringsEqual(String str1, String str2) {
      if (str1 == null && str2 == null) {
         return true;
      } else if (str1 == null && str2 != null) {
         return false;
      } else {
         return str1 != null && str2 == null ? false : str1.equals(str2);
      }
   }

   public static String leftAlignedPaddedString(String input, int length) {
      if (length <= 0) {
         return null;
      } else {
         StringBuilder output = new StringBuilder();
         char space = ' ';
         if (input != null) {
            if (input.length() < length) {
               output.append(input);

               for(int i = input.length(); i < length; ++i) {
                  output.append(space);
               }
            } else {
               output.append(input.substring(0, length));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               output.append(space);
            }
         }

         return output.toString();
      }
   }

   public static String rightAlignedPaddedString(String input, int length) {
      if (length <= 0) {
         return null;
      } else {
         StringBuilder output = new StringBuilder();
         char space = ' ';
         if (input != null) {
            if (input.length() < length) {
               for(int i = input.length(); i < length; ++i) {
                  output.append(space);
               }

               output.append(input);
            } else {
               output.append(input.substring(0, length));
            }
         } else {
            for(int i = 0; i < length; ++i) {
               output.append(space);
            }
         }

         return output.toString();
      }
   }

   public static String[] split(String valuesString, String token) {
      String[] values;
      if (valuesString != null) {
         StringTokenizer tokenizer = new StringTokenizer(valuesString, token);
         values = new String[tokenizer.countTokens()];

         for(int count = 0; tokenizer.hasMoreTokens(); values[count++] = tokenizer.nextToken()) {
         }
      } else {
         values = null;
      }

      return values;
   }

   public static Set convertCommaSeparatedStringToSet(String str) {
      Set set = new HashSet();
      StringTokenizer tokens = new StringTokenizer(str, ",");

      while(tokens.hasMoreTokens()) {
         set.add(tokens.nextToken().trim().toUpperCase());
      }

      return set;
   }

   public static String toJVMIDString(Object obj) {
      return obj == null ? "null" : obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
   }

   public static String booleanArrayToString(boolean[] ba) {
      if (ba == null) {
         return "null";
      } else {
         StringBuilder sb = new StringBuilder("[");

         for(int i = 0; i < ba.length; ++i) {
            sb.append((char)(ba[i] ? 'Y' : 'N'));
         }

         sb.append(']');
         return sb.toString();
      }
   }

   public static String intArrayToString(int[] ia) {
      if (ia == null) {
         return "null";
      } else {
         StringBuilder sb = new StringBuilder("[");

         for(int i = 0; i < ia.length; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(ia[i]);
         }

         sb.append(']');
         return sb.toString();
      }
   }

   public static String objectArrayToString(Object[] arr) {
      if (arr == null) {
         return "null";
      } else {
         StringBuilder sb = new StringBuilder("[");

         for(int i = 0; i < arr.length; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(arr[i]);
         }

         sb.append(']');
         return sb.toString();
      }
   }

   public static String collectionToString(Collection coll) {
      if (coll == null) {
         return "<null>";
      } else if (coll.isEmpty()) {
         return "<none>";
      } else {
         StringBuilder s = new StringBuilder();

         for(Object obj : coll) {
            if (s.length() > 0) {
               s.append(", ");
            }

            s.append(obj);
         }

         return s.toString();
      }
   }

   public static String getNameOfClass(Class cls) {
      if (cls.isPrimitive()) {
         if (cls == Boolean.TYPE) {
            return "boolean";
         }

         if (cls == Byte.TYPE) {
            return "byte";
         }

         if (cls == Character.TYPE) {
            return "char";
         }

         if (cls == Double.TYPE) {
            return "double";
         }

         if (cls == Float.TYPE) {
            return "float";
         }

         if (cls == Integer.TYPE) {
            return "int";
         }

         if (cls == Long.TYPE) {
            return "long";
         }

         if (cls == Short.TYPE) {
            return "short";
         }
      } else if (cls.isArray() && cls.getComponentType().isPrimitive()) {
         return getNameOfClass(cls.getComponentType()) + "[]";
      }

      return cls.getName();
   }

   public static String mapToString(Map map) {
      if (map == null) {
         return "<null>";
      } else if (map.isEmpty()) {
         return "<none>";
      } else {
         StringBuilder s = new StringBuilder();

         for(Map.Entry entry : map.entrySet()) {
            if (s.length() > 0) {
               s.append(", ");
            }

            s.append("<" + entry.getKey() + "," + entry.getValue() + ">");
         }

         return s.toString();
      }
   }

   public static int getIntValueForProperty(Properties props, String propName, int defaultValue) {
      int value = defaultValue;
      if (props != null && props.containsKey(propName)) {
         try {
            value = Integer.valueOf(props.getProperty(propName));
         } catch (NumberFormatException var5) {
         }
      }

      return value;
   }

   public static boolean isEmpty(String s) {
      return s == null || s.length() == 0;
   }

   public static boolean notEmpty(String s) {
      return s != null && s.length() > 0;
   }

   public static String exponentialFormatBigDecimal(BigDecimal bd) {
      String digits = bd.unscaledValue().abs().toString();
      int scale = bd.scale();

      int len;
      for(len = digits.length(); len > 1 && digits.charAt(len - 1) == '0'; --len) {
         --scale;
      }

      if (len < digits.length()) {
         digits = digits.substring(0, len);
      }

      StringBuilder sb = new StringBuilder();
      if (bd.signum() < 0) {
         sb.append('-');
      }

      int exponent = len - scale;
      if (exponent >= 0 && exponent <= len) {
         if (exponent == len) {
            sb.append(digits);
         } else {
            sb.append(digits.substring(0, exponent)).append('.').append(digits.substring(exponent));
         }
      } else {
         sb.append('.').append(digits).append('E').append(exponent);
      }

      return sb.toString();
   }

   public static String removeSpecialTagsFromString(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.replace('\n', ' ');
         str = str.replace('\t', ' ');
         str = str.replace('\r', ' ');
         return str;
      }
   }
}
