package javolution.text;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.io.UTF8StreamWriter;
import javolution.lang.MathLib;
import javolution.lang.Realtime;
import javolution.lang.Reusable;

public class TextBuilder implements Appendable, CharSequence, Reusable, Realtime, Serializable {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      public Object create() {
         return new TextBuilder();
      }
   };
   private static final int B0 = 5;
   private static final int C0 = 32;
   private static final int B1 = 10;
   private static final int C1 = 1024;
   private static final int M1 = 1023;
   private char[] _low;
   private char[][] _high;
   private int _length;
   private int _capacity;
   private static final char[] DIGIT_TO_CHAR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
   private static final long[] POW10_LONG = new long[]{1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
   private static final UTF8StreamWriter SYSTEM_OUT_WRITER;

   public TextBuilder() {
      this._capacity = 32;
      this._low = new char[32];
      this._high = new char[1][];
      this._high[0] = this._low;
   }

   public TextBuilder(String str) {
      this();
      this.append(str);
   }

   public TextBuilder(int capacity) {
      this();

      while(capacity > this._capacity) {
         this.increaseCapacity();
      }

   }

   public static TextBuilder newInstance() {
      TextBuilder textBuilder = (TextBuilder)FACTORY.object();
      textBuilder._length = 0;
      return textBuilder;
   }

   public static void recycle(TextBuilder instance) {
      FACTORY.recycle(instance);
   }

   public final int length() {
      return this._length;
   }

   public final char charAt(int index) {
      if (index >= this._length) {
         throw new IndexOutOfBoundsException();
      } else {
         return index < 1024 ? this._low[index] : this._high[index >> 10][index & 1023];
      }
   }

   public final void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
      if (srcBegin >= 0 && srcBegin <= srcEnd && srcEnd <= this._length) {
         int i = srcBegin;

         int length;
         for(int j = dstBegin; i < srcEnd; j += length) {
            char[] chars0 = this._high[i >> 10];
            int i0 = i & 1023;
            length = MathLib.min(1024 - i0, srcEnd - i);
            System.arraycopy(chars0, i0, dst, j, length);
            i += length;
         }

      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final void setCharAt(int index, char c) {
      if (index >= 0 && index < this._length) {
         this._high[index >> 10][index & 1023] = c;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final void setLength(int newLength) {
      this.setLength(newLength, '\u0000');
   }

   public final void setLength(int newLength, char fillChar) {
      if (newLength < 0) {
         throw new IndexOutOfBoundsException();
      } else {
         if (newLength <= this._length) {
            this._length = newLength;
         } else {
            int i = this._length;

            while(i++ < newLength) {
               this.append(fillChar);
            }
         }

      }
   }

   public final CharSequence subSequence(int start, int end) {
      if (start >= 0 && end >= 0 && start <= end && end <= this._length) {
         return Text.valueOf(this, start, end);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final TextBuilder append(char c) {
      if (this._length >= this._capacity) {
         this.increaseCapacity();
      }

      this._high[this._length >> 10][this._length & 1023] = c;
      ++this._length;
      return this;
   }

   public final TextBuilder append(Object obj) {
      if (obj instanceof String) {
         return this.append((String)obj);
      } else if (obj instanceof Realtime) {
         return this.append(((Realtime)obj).toText());
      } else {
         return obj instanceof Number ? this.appendNumber((Number)obj) : this.append(String.valueOf(obj));
      }
   }

   private TextBuilder appendNumber(Object num) {
      if (num instanceof Integer) {
         return this.append((Integer)num);
      } else if (num instanceof Long) {
         return this.append((Long)num);
      } else if (num instanceof Float) {
         return this.append((Float)num);
      } else {
         return num instanceof Double ? this.append((Double)num) : this.append(String.valueOf(num));
      }
   }

   public final TextBuilder append(CharSequence csq) {
      return csq == null ? this.append("null") : this.append((CharSequence)csq, 0, csq.length());
   }

   public final TextBuilder append(CharSequence csq, int start, int end) {
      if (csq == null) {
         return this.append("null");
      } else if (start >= 0 && end >= 0 && start <= end && end <= csq.length()) {
         int i = start;

         while(i < end) {
            this.append(csq.charAt(i++));
         }

         return this;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final TextBuilder append(String str) {
      return str == null ? this.append("null") : this.append((String)str, 0, str.length());
   }

   public final TextBuilder append(String str, int start, int end) {
      if (str == null) {
         return this.append("null");
      } else if (start >= 0 && end >= 0 && start <= end && end <= str.length()) {
         int newLength = this._length + end - start;

         while(this._capacity < newLength) {
            this.increaseCapacity();
         }

         int i = start;

         int inc;
         for(int j = this._length; i < end; j += inc) {
            char[] chars = this._high[j >> 10];
            int dstBegin = j & 1023;
            inc = MathLib.min(1024 - dstBegin, end - i);
            str.getChars(i, i += inc, chars, dstBegin);
         }

         this._length = newLength;
         return this;
      } else {
         throw new IndexOutOfBoundsException("start: " + start + ", end: " + end + ", str.length(): " + str.length());
      }
   }

   public final TextBuilder append(Text txt) {
      return txt == null ? this.append("null") : this.append((Text)txt, 0, txt.length());
   }

   public final TextBuilder append(Text txt, int start, int end) {
      if (txt == null) {
         return this.append("null");
      } else if (start >= 0 && end >= 0 && start <= end && end <= txt.length()) {
         int newLength = this._length + end - start;

         while(this._capacity < newLength) {
            this.increaseCapacity();
         }

         int i = start;

         int inc;
         for(int j = this._length; i < end; j += inc) {
            char[] chars = this._high[j >> 10];
            int dstBegin = j & 1023;
            inc = MathLib.min(1024 - dstBegin, end - i);
            txt.getChars(i, i += inc, chars, dstBegin);
         }

         this._length = newLength;
         return this;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final TextBuilder append(char[] chars) {
      this.append((char[])chars, 0, chars.length);
      return this;
   }

   public final TextBuilder append(char[] chars, int offset, int length) {
      int end = offset + length;
      if (offset >= 0 && length >= 0 && end <= chars.length) {
         int newLength = this._length + length;

         while(this._capacity < newLength) {
            this.increaseCapacity();
         }

         int i = offset;

         int inc;
         for(int j = this._length; i < end; j += inc) {
            char[] dstChars = this._high[j >> 10];
            int dstBegin = j & 1023;
            inc = MathLib.min(1024 - dstBegin, end - i);
            System.arraycopy(chars, i, dstChars, dstBegin, inc);
            i += inc;
         }

         this._length = newLength;
         return this;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final TextBuilder append(boolean b) {
      return b ? this.append("true") : this.append("false");
   }

   public final TextBuilder append(int i) {
      if (i <= 0) {
         if (i == 0) {
            return this.append("0");
         }

         if (i == Integer.MIN_VALUE) {
            return this.append("-2147483648");
         }

         this.append('-');
         i = -i;
      }

      int digits = MathLib.digitLength(i);
      if (this._capacity < this._length + digits) {
         this.increaseCapacity();
      }

      this._length += digits;
      int index = this._length - 1;

      while(true) {
         int j = i / 10;
         this._high[index >> 10][index & 1023] = (char)(48 + i - j * 10);
         if (j == 0) {
            return this;
         }

         i = j;
         --index;
      }
   }

   public final TextBuilder append(int i, int radix) {
      if (radix == 10) {
         return this.append(i);
      } else if (radix >= 2 && radix <= 36) {
         if (i < 0) {
            this.append('-');
            if (i == Integer.MIN_VALUE) {
               this.appendPositive(-(i / radix), radix);
               return this.append(DIGIT_TO_CHAR[-(i % radix)]);
            }

            i = -i;
         }

         this.appendPositive(i, radix);
         return this;
      } else {
         throw new IllegalArgumentException("radix: " + radix);
      }
   }

   private void appendPositive(int l1, int radix) {
      if (l1 >= radix) {
         int l2 = l1 / radix;
         if (l2 >= radix) {
            int l3 = l2 / radix;
            if (l3 >= radix) {
               int l4 = l3 / radix;
               this.appendPositive(l4, radix);
               this.append(DIGIT_TO_CHAR[l3 - l4 * radix]);
            } else {
               this.append(DIGIT_TO_CHAR[l3]);
            }

            this.append(DIGIT_TO_CHAR[l2 - l3 * radix]);
         } else {
            this.append(DIGIT_TO_CHAR[l2]);
         }

         this.append(DIGIT_TO_CHAR[l1 - l2 * radix]);
      } else {
         this.append(DIGIT_TO_CHAR[l1]);
      }

   }

   public final TextBuilder append(long l) {
      if (l <= 0L) {
         if (l == 0L) {
            return this.append("0");
         }

         if (l == Long.MIN_VALUE) {
            return this.append("-9223372036854775808");
         }

         this.append('-');
         l = -l;
      }

      if (l <= 2147483647L) {
         return this.append((int)l);
      } else {
         this.append(l / 1000000000L);
         int i = (int)(l % 1000000000L);
         int digits = MathLib.digitLength(i);
         this.append((String)"000000000", 0, 9 - digits);
         return this.append(i);
      }
   }

   public final TextBuilder append(long l, int radix) {
      if (radix == 10) {
         return this.append(l);
      } else if (radix >= 2 && radix <= 36) {
         if (l < 0L) {
            this.append('-');
            if (l == Long.MIN_VALUE) {
               this.appendPositive(-(l / (long)radix), radix);
               return this.append(DIGIT_TO_CHAR[(int)(-(l % (long)radix))]);
            }

            l = -l;
         }

         this.appendPositive(l, radix);
         return this;
      } else {
         throw new IllegalArgumentException("radix: " + radix);
      }
   }

   private void appendPositive(long l1, int radix) {
      if (l1 >= (long)radix) {
         long l2 = l1 / (long)radix;
         if (l2 >= (long)radix) {
            long l3 = l2 / (long)radix;
            if (l3 >= (long)radix) {
               long l4 = l3 / (long)radix;
               this.appendPositive(l4, radix);
               this.append(DIGIT_TO_CHAR[(int)(l3 - l4 * (long)radix)]);
            } else {
               this.append(DIGIT_TO_CHAR[(int)l3]);
            }

            this.append(DIGIT_TO_CHAR[(int)(l2 - l3 * (long)radix)]);
         } else {
            this.append(DIGIT_TO_CHAR[(int)l2]);
         }

         this.append(DIGIT_TO_CHAR[(int)(l1 - l2 * (long)radix)]);
      } else {
         this.append(DIGIT_TO_CHAR[(int)l1]);
      }

   }

   public final TextBuilder append(float f) {
      return this.append((double)f, 10, (double)MathLib.abs(f) >= (double)1.0E7F || (double)MathLib.abs(f) < 0.001, false);
   }

   public final TextBuilder append(double d) {
      return this.append(d, -1, MathLib.abs(d) >= (double)1.0E7F || MathLib.abs(d) < 0.001, false);
   }

   public final TextBuilder append(double d, int digits, boolean scientific, boolean showZero) {
      if (digits > 19) {
         throw new IllegalArgumentException("digits: " + digits);
      } else if (d != d) {
         return this.append("NaN");
      } else if (d == Double.POSITIVE_INFINITY) {
         return this.append("Infinity");
      } else if (d == Double.NEGATIVE_INFINITY) {
         return this.append("-Infinity");
      } else if (d == (double)0.0F) {
         if (digits < 0) {
            return this.append("0.0");
         } else {
            this.append('0');
            if (showZero) {
               this.append('.');

               for(int j = 1; j < digits; ++j) {
                  this.append('0');
               }
            }

            return this;
         }
      } else {
         if (d < (double)0.0F) {
            d = -d;
            this.append('-');
         }

         int e = MathLib.floorLog10(d);
         long m;
         if (digits < 0) {
            long m17 = MathLib.toLongPow10(d, 16 - e);
            long m16 = m17 / 10L;
            double dd = MathLib.toDoublePow10(m16, e - 16 + 1);
            if (dd == d) {
               digits = 16;
               m = m16;
            } else {
               digits = 17;
               m = m17;
            }
         } else {
            m = MathLib.toLongPow10(d, digits - 1 - e);
         }

         if (!scientific && e < digits) {
            int exp = digits - e - 1;
            if (exp < POW10_LONG.length) {
               long pow10 = POW10_LONG[exp];
               long l = m / pow10;
               this.append(l);
               m -= pow10 * l;
            } else {
               this.append('0');
            }

            this.appendFraction(m, exp, showZero);
         } else {
            long pow10 = POW10_LONG[digits - 1];
            int k = (int)(m / pow10);
            this.append((char)(48 + k));
            m -= pow10 * (long)k;
            this.appendFraction(m, digits - 1, showZero);
            this.append('E');
            this.append(e);
         }

         return this;
      }
   }

   private final void appendFraction(long l, int digits, boolean showZero) {
      this.append('.');
      if (l == 0L) {
         if (showZero) {
            for(int i = 0; i < digits; ++i) {
               this.append('0');
            }
         } else {
            this.append('0');
         }
      } else {
         int length = MathLib.digitLength(l);

         for(int j = length; j < digits; ++j) {
            this.append('0');
         }

         if (!showZero) {
            while(l % 10L == 0L) {
               l /= 10L;
            }
         }

         this.append(l);
      }

   }

   public final TextBuilder insert(int index, CharSequence csq) {
      if (index >= 0 && index <= this._length) {
         int shift = csq.length();
         this._length += shift;

         while(this._length >= this._capacity) {
            this.increaseCapacity();
         }

         int i = this._length - shift;

         while(true) {
            --i;
            if (i < index) {
               i = csq.length();

               while(true) {
                  --i;
                  if (i < 0) {
                     return this;
                  }

                  this.setCharAt(index + i, csq.charAt(i));
               }
            }

            this.setCharAt(i + shift, this.charAt(i));
         }
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final TextBuilder clear() {
      this._length = 0;
      return this;
   }

   public final TextBuilder delete(int start, int end) {
      if (start >= 0 && end >= 0 && start <= end && end <= this.length()) {
         int i = end;
         int j = start;

         while(i < this._length) {
            this.setCharAt(j++, this.charAt(i++));
         }

         this._length -= end - start;
         return this;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public final TextBuilder reverse() {
      int n = this._length - 1;
      int j = n - 1 >> 1;

      while(j >= 0) {
         char c = this.charAt(j);
         this.setCharAt(j, this.charAt(n - j));
         this.setCharAt(n - j--, c);
      }

      return this;
   }

   public final Text toText() {
      return Text.valueOf((TextBuilder)this, 0, this._length);
   }

   public final String toString() {
      char[] data = new char[this._length];
      this.getChars(0, this._length, data, 0);
      return new String(data, 0, this._length);
   }

   public final CharArray toCharArray() {
      CharArray cArray = new CharArray();
      char[] data;
      if (this._length < 1024) {
         data = this._low;
      } else {
         data = new char[this._length];
         this.getChars(0, this._length, data, 0);
      }

      cArray.setArray(data, 0, this._length);
      return cArray;
   }

   public final void reset() {
      this._length = 0;
   }

   public final int hashCode() {
      int h = 0;

      for(int i = 0; i < this._length; h = 31 * h + this.charAt(i++)) {
      }

      return h;
   }

   public final boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof TextBuilder)) {
         return false;
      } else {
         TextBuilder that = (TextBuilder)obj;
         if (this._length != that._length) {
            return false;
         } else {
            int i = 0;

            while(i < this._length) {
               if (this.charAt(i) != that.charAt(i++)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public void print() {
      try {
         synchronized(SYSTEM_OUT_WRITER) {
            this.print(SYSTEM_OUT_WRITER);
            SYSTEM_OUT_WRITER.flush();
         }
      } catch (IOException e) {
         throw new Error(e.getMessage());
      }
   }

   public void println() {
      try {
         synchronized(SYSTEM_OUT_WRITER) {
            this.println(SYSTEM_OUT_WRITER);
            SYSTEM_OUT_WRITER.flush();
         }
      } catch (IOException e) {
         throw new Error(e.getMessage());
      }
   }

   public void print(Writer writer) throws IOException {
      for(int i = 0; i < this._length; i += 1024) {
         char[] chars = this._high[i >> 10];
         writer.write(chars, 0, MathLib.min(1024, this._length - i));
      }

   }

   public void println(Writer writer) throws IOException {
      this.print(writer);
      writer.write(10);
   }

   public final boolean contentEquals(CharSequence csq) {
      if (csq.length() != this._length) {
         return false;
      } else {
         int i = 0;

         while(i < this._length) {
            char c = i < 1024 ? this._low[i] : this._high[i >> 10][i & 1023];
            if (csq.charAt(i++) != c) {
               return false;
            }
         }

         return true;
      }
   }

   public final boolean contentEquals(String csq) {
      if (csq.length() != this._length) {
         return false;
      } else {
         int i = 0;

         while(i < this._length) {
            char c = i < 1024 ? this._low[i] : this._high[i >> 10][i & 1023];
            if (csq.charAt(i++) != c) {
               return false;
            }
         }

         return true;
      }
   }

   private void increaseCapacity() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            if (TextBuilder.this._capacity < 1024) {
               TextBuilder.this._capacity = 1;
               char[] tmp = new char[TextBuilder.this._capacity];
               System.arraycopy(TextBuilder.this._low, 0, tmp, 0, TextBuilder.this._length);
               TextBuilder.this._low = tmp;
               TextBuilder.this._high[0] = tmp;
            } else {
               int j = TextBuilder.this._capacity >> 10;
               if (j >= TextBuilder.this._high.length) {
                  char[][] tmp = new char[TextBuilder.this._high.length * 2][];
                  System.arraycopy(TextBuilder.this._high, 0, tmp, 0, TextBuilder.this._high.length);
                  TextBuilder.this._high = tmp;
               }

               TextBuilder.this._high[j] = new char[1024];
               TextBuilder.this._capacity = 1024;
            }

         }
      });
   }

   static {
      SYSTEM_OUT_WRITER = (new UTF8StreamWriter()).setOutput(System.out);
   }
}
