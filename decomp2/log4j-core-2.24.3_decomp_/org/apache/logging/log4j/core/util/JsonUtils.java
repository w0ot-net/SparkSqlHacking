package org.apache.logging.log4j.core.util;

public final class JsonUtils {
   private static final char[] HC = "0123456789ABCDEF".toCharArray();
   private static final int[] ESC_CODES;
   private static final ThreadLocal _qbufLocal;

   private static char[] getQBuf() {
      char[] _qbuf = (char[])_qbufLocal.get();
      if (_qbuf == null) {
         _qbuf = new char[]{'\\', '\u0000', '0', '0', '\u0000', '\u0000'};
         _qbufLocal.set(_qbuf);
      }

      return _qbuf;
   }

   public static void quoteAsString(final CharSequence input, final StringBuilder output) {
      char[] qbuf = getQBuf();
      int escCodeCount = ESC_CODES.length;
      int inPtr = 0;
      int inputLen = input.length();

      while(inPtr < inputLen) {
         char c = input.charAt(inPtr);
         if (c < escCodeCount && ESC_CODES[c] != 0) {
            c = input.charAt(inPtr++);
            int escCode = ESC_CODES[c];
            int length = escCode < 0 ? _appendNumeric(c, qbuf) : _appendNamed(escCode, qbuf);
            output.append(qbuf, 0, length);
         } else {
            output.append(c);
            ++inPtr;
            if (inPtr >= inputLen) {
               return;
            }
         }
      }

   }

   private static int _appendNumeric(final int value, final char[] qbuf) {
      qbuf[1] = 'u';
      qbuf[4] = HC[value >> 4];
      qbuf[5] = HC[value & 15];
      return 6;
   }

   private static int _appendNamed(final int esc, final char[] qbuf) {
      qbuf[1] = (char)esc;
      return 2;
   }

   static {
      int[] table = new int[128];

      for(int i = 0; i < 32; ++i) {
         table[i] = -1;
      }

      table[34] = 34;
      table[92] = 92;
      table[8] = 98;
      table[9] = 116;
      table[12] = 102;
      table[10] = 110;
      table[13] = 114;
      ESC_CODES = table;
      _qbufLocal = new ThreadLocal();
   }
}
