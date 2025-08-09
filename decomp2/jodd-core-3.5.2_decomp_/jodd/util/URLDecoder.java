package jodd.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import jodd.JoddCore;

public class URLDecoder {
   public static String decode(String url) {
      return decode(url, JoddCore.encoding, false);
   }

   public static String decode(String source, String encoding) {
      return decode(source, encoding, false);
   }

   public static String decodeQuery(String source) {
      return decode(source, JoddCore.encoding, true);
   }

   public static String decodeQuery(String source, String encoding) {
      return decode(source, encoding, true);
   }

   private static String decode(String source, String encoding, boolean decodePlus) {
      int length = source.length();
      ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
      boolean changed = false;

      for(int i = 0; i < length; ++i) {
         int ch = source.charAt(i);
         switch (ch) {
            case 37:
               if (i + 2 >= length) {
                  throw new IllegalArgumentException("Invalid sequence: " + source.substring(i));
               }

               char hex1 = source.charAt(i + 1);
               char hex2 = source.charAt(i + 2);
               int u = Character.digit(hex1, 16);
               int l = Character.digit(hex2, 16);
               if (u == -1 || l == -1) {
                  throw new IllegalArgumentException("Invalid sequence: " + source.substring(i));
               }

               bos.write((char)((u << 4) + l));
               i += 2;
               changed = true;
               break;
            case 43:
               if (decodePlus) {
                  ch = 32;
                  changed = true;
               }
            default:
               bos.write(ch);
         }
      }

      try {
         return changed ? new String(bos.toByteArray(), encoding) : source;
      } catch (UnsupportedEncodingException var12) {
         return null;
      }
   }
}
