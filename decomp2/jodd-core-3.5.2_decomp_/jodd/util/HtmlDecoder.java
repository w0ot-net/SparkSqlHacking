package jodd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import jodd.io.StreamUtil;

public class HtmlDecoder {
   private static final Map ENTITY_MAP;

   public static String decode(String html) {
      int ndx = html.indexOf(38);
      if (ndx == -1) {
         return html;
      } else {
         StringBuilder result = new StringBuilder(html.length());
         int lastIndex = 0;

         label43:
         for(int len = html.length(); ndx != -1; ndx = html.indexOf(38, lastIndex)) {
            result.append(html.substring(lastIndex, ndx));
            lastIndex = ndx;

            while(html.charAt(lastIndex) != ';') {
               ++lastIndex;
               if (lastIndex == len) {
                  lastIndex = ndx;
                  break label43;
               }
            }

            if (html.charAt(ndx + 1) == '#') {
               char c = html.charAt(ndx + 2);
               int radix;
               if (c != 'x' && c != 'X') {
                  radix = 10;
                  ndx += 2;
               } else {
                  radix = 16;
                  ndx += 3;
               }

               String number = html.substring(ndx, lastIndex);
               int i = Integer.parseInt(number, radix);
               result.append((char)i);
               ++lastIndex;
            } else {
               String encodeToken = html.substring(ndx + 1, lastIndex);
               Character replacement = (Character)ENTITY_MAP.get(encodeToken);
               if (replacement == null) {
                  result.append('&');
                  lastIndex = ndx + 1;
               } else {
                  result.append(replacement);
                  ++lastIndex;
               }
            }
         }

         result.append(html.substring(lastIndex));
         return result.toString();
      }
   }

   static {
      Properties entityReferences = new Properties();
      String propertiesName = HtmlDecoder.class.getSimpleName() + ".properties";
      InputStream is = HtmlDecoder.class.getResourceAsStream(propertiesName);
      if (is == null) {
         throw new IllegalStateException("Missing: " + propertiesName);
      } else {
         try {
            entityReferences.load(is);
         } catch (IOException ioex) {
            throw new IllegalStateException(ioex.getMessage());
         } finally {
            StreamUtil.close(is);
         }

         ENTITY_MAP = new HashMap(entityReferences.size());
         Enumeration ioex = entityReferences.propertyNames();

         while(ioex.hasMoreElements()) {
            String name = (String)ioex.nextElement();
            String hex = entityReferences.getProperty(name);
            int value = Integer.parseInt(hex, 16);
            ENTITY_MAP.put(name, (char)value);
         }

      }
   }
}
