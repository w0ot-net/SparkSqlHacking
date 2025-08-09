package org.snakeyaml.engine.v2.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class CharConstants {
   private static final String ALPHA_S = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
   private static final String LINEBR_S = "\n";
   private static final String FULL_LINEBR_S = "\r\n";
   private static final String NULL_OR_LINEBR_S = "\u0000\r\n";
   private static final String NULL_BL_LINEBR_S = " \u0000\r\n";
   private static final String NULL_BL_T_LINEBR_S = "\t \u0000\r\n";
   private static final String NULL_BL_T_S = "\u0000 \t";
   private static final String URI_CHARS_SUFFIX_S = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_-;/?:@&=+$_.!~*'()%";
   public static final CharConstants LINEBR = new CharConstants("\n");
   public static final CharConstants NULL_OR_LINEBR = new CharConstants("\u0000\r\n");
   public static final CharConstants NULL_BL_LINEBR = new CharConstants(" \u0000\r\n");
   public static final CharConstants NULL_BL_T_LINEBR = new CharConstants("\t \u0000\r\n");
   public static final CharConstants NULL_BL_T = new CharConstants("\u0000 \t");
   public static final CharConstants URI_CHARS_FOR_TAG_PREFIX = new CharConstants("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_-;/?:@&=+$_.!~*'()%,[]");
   public static final CharConstants URI_CHARS_FOR_TAG_SUFFIX = new CharConstants("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_-;/?:@&=+$_.!~*'()%");
   public static final CharConstants ALPHA = new CharConstants("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_");
   private static final int ASCII_SIZE = 128;
   boolean[] contains = new boolean[128];
   public static final Map ESCAPE_REPLACEMENTS;
   public static final Map ESCAPE_CODES;

   private CharConstants(String content) {
      Arrays.fill(this.contains, false);

      for(int i = 0; i < content.length(); ++i) {
         int c = content.codePointAt(i);
         this.contains[c] = true;
      }

   }

   public boolean has(int c) {
      return c < 128 && this.contains[c];
   }

   public boolean hasNo(int c) {
      return !this.has(c);
   }

   public boolean has(int c, String additional) {
      return this.has(c) || additional.indexOf(c) != -1;
   }

   public boolean hasNo(int c, String additional) {
      return !this.has(c, additional);
   }

   public static String escapeChar(String chRepresentation) {
      for(Character s : ESCAPE_REPLACEMENTS.keySet()) {
         String v = (String)ESCAPE_REPLACEMENTS.get(s);
         if (!" ".equals(v) && !"/".equals(v) && !"\"".equals(v) && v.equals(chRepresentation)) {
            return "\\" + s;
         }
      }

      return chRepresentation;
   }

   static {
      Map<Character, String> escapes = new HashMap();
      escapes.put('0', "\u0000");
      escapes.put('a', "\u0007");
      escapes.put('b', "\b");
      escapes.put('t', "\t");
      escapes.put('n', "\n");
      escapes.put('v', "\u000b");
      escapes.put('f', "\f");
      escapes.put('r', "\r");
      escapes.put('e', "\u001b");
      escapes.put(' ', " ");
      escapes.put('"', "\"");
      escapes.put('/', "/");
      escapes.put('\\', "\\");
      escapes.put('N', "\u0085");
      escapes.put('_', " ");
      ESCAPE_REPLACEMENTS = Collections.unmodifiableMap(escapes);
      Map<Character, Integer> escapeCodes = new HashMap();
      escapeCodes.put('x', 2);
      escapeCodes.put('u', 4);
      escapeCodes.put('U', 8);
      ESCAPE_CODES = Collections.unmodifiableMap(escapeCodes);
   }
}
