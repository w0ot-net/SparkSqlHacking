package org.apache.commons.codec.language;

import java.util.Arrays;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class ColognePhonetic implements StringEncoder {
   private static final char[] AEIJOUY = new char[]{'A', 'E', 'I', 'J', 'O', 'U', 'Y'};
   private static final char[] CSZ = new char[]{'C', 'S', 'Z'};
   private static final char[] FPVW = new char[]{'F', 'P', 'V', 'W'};
   private static final char[] GKQ = new char[]{'G', 'K', 'Q'};
   private static final char[] CKQ = new char[]{'C', 'K', 'Q'};
   private static final char[] AHKLOQRUX = new char[]{'A', 'H', 'K', 'L', 'O', 'Q', 'R', 'U', 'X'};
   private static final char[] SZ = new char[]{'S', 'Z'};
   private static final char[] AHKOQUX = new char[]{'A', 'H', 'K', 'O', 'Q', 'U', 'X'};
   private static final char[] DTX = new char[]{'D', 'T', 'X'};
   private static final char CHAR_IGNORE = '-';

   private static boolean arrayContains(char[] arr, char key) {
      for(char element : arr) {
         if (element == key) {
            return true;
         }
      }

      return false;
   }

   public String colognePhonetic(String text) {
      if (text == null) {
         return null;
      } else {
         CologneInputBuffer input = new CologneInputBuffer(this.preprocess(text));
         CologneOutputBuffer output = new CologneOutputBuffer(input.length() * 2);
         char lastChar = '-';

         while(!input.isEmpty()) {
            char chr = input.removeNext();
            char nextChar;
            if (!input.isEmpty()) {
               nextChar = input.getNextChar();
            } else {
               nextChar = '-';
            }

            if (chr >= 'A' && chr <= 'Z') {
               if (arrayContains(AEIJOUY, chr)) {
                  output.put('0');
               } else if (chr != 'B' && (chr != 'P' || nextChar == 'H')) {
                  if ((chr == 'D' || chr == 'T') && !arrayContains(CSZ, nextChar)) {
                     output.put('2');
                  } else if (arrayContains(FPVW, chr)) {
                     output.put('3');
                  } else if (arrayContains(GKQ, chr)) {
                     output.put('4');
                  } else if (chr == 'X' && !arrayContains(CKQ, lastChar)) {
                     output.put('4');
                     output.put('8');
                  } else if (chr != 'S' && chr != 'Z') {
                     if (chr == 'C') {
                        if (output.isEmpty()) {
                           if (arrayContains(AHKLOQRUX, nextChar)) {
                              output.put('4');
                           } else {
                              output.put('8');
                           }
                        } else if (!arrayContains(SZ, lastChar) && arrayContains(AHKOQUX, nextChar)) {
                           output.put('4');
                        } else {
                           output.put('8');
                        }
                     } else if (arrayContains(DTX, chr)) {
                        output.put('8');
                     } else {
                        switch (chr) {
                           case 'H':
                              output.put('-');
                           case 'I':
                           case 'J':
                           case 'K':
                           case 'O':
                           case 'P':
                           case 'Q':
                           default:
                              break;
                           case 'L':
                              output.put('5');
                              break;
                           case 'M':
                           case 'N':
                              output.put('6');
                              break;
                           case 'R':
                              output.put('7');
                        }
                     }
                  } else {
                     output.put('8');
                  }
               } else {
                  output.put('1');
               }

               lastChar = chr;
            }
         }

         return output.toString();
      }
   }

   public Object encode(Object object) throws EncoderException {
      if (!(object instanceof String)) {
         throw new EncoderException("This method's parameter was expected to be of the type " + String.class.getName() + ". But actually it was of the type " + object.getClass().getName() + ".");
      } else {
         return this.encode((String)object);
      }
   }

   public String encode(String text) {
      return this.colognePhonetic(text);
   }

   public boolean isEncodeEqual(String text1, String text2) {
      return this.colognePhonetic(text1).equals(this.colognePhonetic(text2));
   }

   private char[] preprocess(String text) {
      char[] chrs = text.toUpperCase(Locale.GERMAN).toCharArray();

      for(int index = 0; index < chrs.length; ++index) {
         switch (chrs[index]) {
            case 'Ä':
               chrs[index] = 'A';
               break;
            case 'Ö':
               chrs[index] = 'O';
               break;
            case 'Ü':
               chrs[index] = 'U';
         }
      }

      return chrs;
   }

   abstract static class CologneBuffer {
      protected final char[] data;
      protected int length;

      CologneBuffer(char[] data) {
         this.data = data;
         this.length = data.length;
      }

      CologneBuffer(int buffSize) {
         this.data = new char[buffSize];
         this.length = 0;
      }

      protected abstract char[] copyData(int var1, int var2);

      public boolean isEmpty() {
         return this.length() == 0;
      }

      public int length() {
         return this.length;
      }

      public String toString() {
         return new String(this.copyData(0, this.length));
      }
   }

   private final class CologneInputBuffer extends CologneBuffer {
      CologneInputBuffer(final char[] data) {
         super(data);
      }

      protected char[] copyData(int start, int length) {
         char[] newData = new char[length];
         System.arraycopy(this.data, this.data.length - this.length + start, newData, 0, length);
         return newData;
      }

      public char getNextChar() {
         return this.data[this.getNextPos()];
      }

      protected int getNextPos() {
         return this.data.length - this.length;
      }

      public char removeNext() {
         char ch = this.getNextChar();
         --this.length;
         return ch;
      }
   }

   private final class CologneOutputBuffer extends CologneBuffer {
      private char lastCode = '/';

      CologneOutputBuffer(final int buffSize) {
         super(buffSize);
      }

      protected char[] copyData(int start, int length) {
         return Arrays.copyOfRange(this.data, start, length);
      }

      public void put(char code) {
         if (code != '-' && this.lastCode != code && (code != '0' || this.length == 0)) {
            this.data[this.length] = code;
            ++this.length;
         }

         this.lastCode = code;
      }
   }
}
