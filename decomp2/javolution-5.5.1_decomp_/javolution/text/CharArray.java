package javolution.text;

import javolution.util.FastComparator;

public final class CharArray implements CharSequence, Comparable {
   private char[] _array;
   private int _offset;
   private int _length;
   private static final char[] NO_CHAR = new char[0];

   public CharArray() {
      this._array = NO_CHAR;
   }

   public CharArray(int capacity) {
      this._array = new char[capacity];
   }

   public CharArray(String string) {
      this._array = string.toCharArray();
      this._length = string.length();
   }

   public char[] array() {
      return this._array;
   }

   public int length() {
      return this._length;
   }

   public int offset() {
      return this._offset;
   }

   public CharArray setArray(char[] array, int offset, int length) {
      this._array = array;
      this._offset = offset;
      this._length = length;
      return this;
   }

   public final int indexOf(CharSequence csq) {
      char c = csq.charAt(0);
      int csqLength = csq.length();
      int i = this._offset;

      for(int end = this._offset + this._length - csqLength + 1; i < end; ++i) {
         if (this._array[i] == c) {
            boolean match = true;

            for(int j = 1; j < csqLength; ++j) {
               if (this._array[i + j] != csq.charAt(j)) {
                  match = false;
                  break;
               }
            }

            if (match) {
               return i - this._offset;
            }
         }
      }

      return -1;
   }

   public final int indexOf(char c) {
      int i = this._offset;

      for(int end = this._offset + this._length; i < end; ++i) {
         if (this._array[i] == c) {
            return i - this._offset;
         }
      }

      return -1;
   }

   public String toString() {
      return new String(this._array, this._offset, this._length);
   }

   public int hashCode() {
      int h = 0;
      int i = 0;

      for(int j = this._offset; i < this._length; ++i) {
         h = 31 * h + this._array[j++];
      }

      return h;
   }

   public boolean equals(Object that) {
      if (that instanceof String) {
         return this.equals((String)that);
      } else if (that instanceof CharArray) {
         return this.equals((CharArray)that);
      } else {
         return that instanceof CharSequence ? this.equals((CharSequence)that) : false;
      }
   }

   private boolean equals(CharSequence chars) {
      if (chars == null) {
         return false;
      } else if (this._length != chars.length()) {
         return false;
      } else {
         int i = this._length;
         int j = this._offset + this._length;

         do {
            --i;
            if (i < 0) {
               return true;
            }

            --j;
         } while(this._array[j] == chars.charAt(i));

         return false;
      }
   }

   public boolean equals(CharArray that) {
      if (this == that) {
         return true;
      } else if (that == null) {
         return false;
      } else if (this._length != that._length) {
         return false;
      } else {
         char[] thatArray = that._array;
         int i = that._offset + this._length;
         int j = this._offset + this._length;

         char var10000;
         do {
            --j;
            if (j < this._offset) {
               return true;
            }

            var10000 = this._array[j];
            --i;
         } while(var10000 == thatArray[i]);

         return false;
      }
   }

   public boolean equals(String str) {
      if (str == null) {
         return false;
      } else if (this._length != str.length()) {
         return false;
      } else {
         int i = this._length;
         int j = this._offset + this._length;

         do {
            --i;
            if (i < 0) {
               return true;
            }

            --j;
         } while(this._array[j] == str.charAt(i));

         return false;
      }
   }

   public int compareTo(Object seq) {
      return FastComparator.LEXICAL.compare(this, seq);
   }

   public boolean toBoolean() {
      return TypeFormat.parseBoolean((CharSequence)this);
   }

   public int toInt() {
      return TypeFormat.parseInt((CharSequence)this);
   }

   public int toInt(int radix) {
      return TypeFormat.parseInt((CharSequence)this, radix);
   }

   public long toLong() {
      return TypeFormat.parseLong((CharSequence)this);
   }

   public long toLong(int radix) {
      return TypeFormat.parseLong((CharSequence)this, radix);
   }

   public float toFloat() {
      return TypeFormat.parseFloat((CharSequence)this);
   }

   public double toDouble() {
      return TypeFormat.parseDouble((CharSequence)this);
   }

   public char charAt(int index) {
      if (index >= 0 && index < this._length) {
         return this._array[this._offset + index];
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public CharSequence subSequence(int start, int end) {
      if (start >= 0 && end >= 0 && start <= end && end <= this.length()) {
         CharArray chars = new CharArray();
         chars._array = this._array;
         chars._offset = this._offset + start;
         chars._length = end - start;
         return chars;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void getChars(int start, int end, char[] dest, int destPos) {
      if (start >= 0 && end >= 0 && start <= end && end <= this._length) {
         System.arraycopy(this._array, start + this._offset, dest, destPos, end - start);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }
}
