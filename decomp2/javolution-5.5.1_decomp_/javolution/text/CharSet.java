package javolution.text;

import javolution.lang.Immutable;
import javolution.lang.MathLib;

public final class CharSet implements Immutable {
   public static final CharSet EMPTY = new CharSet(new long[0]);
   public static final CharSet WHITESPACES = valueOf('\t', '\n', '\u000b', '\f', '\r', '\u001c', '\u001d', '\u001e', '\u001f', ' ', ' ', '\u180e', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '\u200b', '\u2028', '\u2029', ' ', '　');
   public static final CharSet SPACES = valueOf(' ', ' ', ' ', '\u180e', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '\u200b', '\u2028', '\u2029', ' ', ' ', '　');
   public static final CharSet ISO_CONTROLS = valueOf('\u0000', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', '\u007f', '\u0080', '\u0081', '\u0082', '\u0083', '\u0084', '\u0085', '\u0086', '\u0087', '\u0088', '\u0089', '\u008a', '\u008b', '\u008c', '\u008d', '\u008e', '\u008f', '\u0090', '\u0091', '\u0092', '\u0093', '\u0094', '\u0095', '\u0096', '\u0097', '\u0098', '\u0099', '\u009a', '\u009b', '\u009c', '\u009d', '\u009e', '\u009f');
   private final long[] _mapping;

   private CharSet(long[] mapping) {
      this._mapping = mapping;
   }

   public static CharSet valueOf(char... chars) {
      int maxChar = 0;
      int i = chars.length;

      while(true) {
         --i;
         if (i < 0) {
            CharSet charSet = new CharSet(new long[(maxChar >> 6) + 1]);
            int i = chars.length;

            while(true) {
               --i;
               if (i < 0) {
                  return charSet;
               }

               char c = chars[i];
               long[] var10000 = charSet._mapping;
               var10000[c >> 6] |= 1L << (c & 63);
            }
         }

         if (chars[i] > maxChar) {
            maxChar = chars[i];
         }
      }
   }

   public static CharSet rangeOf(char first, char last) {
      if (first > last) {
         throw new IllegalArgumentException("first should be less or equal to last");
      } else {
         CharSet charSet = new CharSet(new long[(last >> 6) + 1]);

         for(char c = first; c <= last; ++c) {
            long[] var10000 = charSet._mapping;
            var10000[c >> 6] |= 1L << (c & 63);
         }

         return charSet;
      }
   }

   public boolean contains(char c) {
      int i = c >> 6;
      return i < this._mapping.length ? (this._mapping[i] & 1L << (c & 63)) != 0L : false;
   }

   public int indexIn(CharSequence csq) {
      return this.indexIn((CharSequence)csq, 0);
   }

   public int indexIn(CharSequence csq, int fromIndex) {
      int i = fromIndex;

      for(int n = csq.length(); i < n; ++i) {
         if (this.contains(csq.charAt(i))) {
            return i;
         }
      }

      return -1;
   }

   public int indexIn(char[] chars) {
      return this.indexIn((char[])chars, 0);
   }

   public int indexIn(char[] chars, int fromIndex) {
      int i = fromIndex;

      for(int n = chars.length; i < n; ++i) {
         if (this.contains(chars[i])) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexIn(CharSequence csq) {
      return this.lastIndexIn(csq, csq.length() - 1);
   }

   public int lastIndexIn(CharSequence csq, int fromIndex) {
      for(int i = fromIndex; i >= 0; --i) {
         if (this.contains(csq.charAt(i))) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexIn(char[] chars) {
      return this.lastIndexIn(chars, chars.length - 1);
   }

   public int lastIndexIn(char[] chars, int fromIndex) {
      for(int i = fromIndex; i >= 0; --i) {
         if (this.contains(chars[i])) {
            return i;
         }
      }

      return -1;
   }

   public CharSet plus(CharSet that) {
      if (that._mapping.length > this._mapping.length) {
         return that.plus(this);
      } else {
         CharSet result = this.copy();
         int i = that._mapping.length;

         while(true) {
            --i;
            if (i < 0) {
               return result;
            }

            long[] var10000 = result._mapping;
            var10000[i] |= that._mapping[i];
         }
      }
   }

   public CharSet minus(CharSet that) {
      CharSet result = this.copy();
      int i = MathLib.min(this._mapping.length, that._mapping.length);

      while(true) {
         --i;
         if (i < 0) {
            return result;
         }

         long[] var10000 = result._mapping;
         var10000[i] &= ~that._mapping[i];
      }
   }

   public String toString() {
      TextBuilder tb = TextBuilder.newInstance();
      tb.append('{');
      int length = this._mapping.length << 6;

      for(int i = 0; i < length; ++i) {
         if (this.contains((char)i)) {
            if (tb.length() > 1) {
               tb.append(',');
               tb.append(' ');
            }

            tb.append('\'');
            tb.append((char)i);
            tb.append('\'');
         }
      }

      tb.append('}');
      return tb.toString();
   }

   private CharSet copy() {
      CharSet charSet = new CharSet(new long[this._mapping.length]);
      int i = this._mapping.length;

      while(true) {
         --i;
         if (i < 0) {
            return charSet;
         }

         charSet._mapping[i] = this._mapping[i];
      }
   }
}
