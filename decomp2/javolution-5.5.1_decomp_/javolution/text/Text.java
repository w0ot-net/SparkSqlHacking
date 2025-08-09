package javolution.text;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.io.UTF8StreamWriter;
import javolution.lang.MathLib;
import javolution.lang.Realtime;
import javolution.lang.ValueType;
import javolution.util.FastComparator;
import javolution.util.FastMap;
import javolution.xml.XMLSerializable;

public final class Text implements CharSequence, Comparable, XMLSerializable, ValueType, Realtime {
   private static final int BLOCK_SIZE = 32;
   private static final int BLOCK_MASK = -32;
   private static final FastMap INTERN_INSTANCES;
   public static final Text EMPTY;
   private final char[] _data;
   private int _count;
   private Text _head;
   private Text _tail;
   private static final Text TRUE;
   private static final Text FALSE;
   private static final UTF8StreamWriter SYSTEM_OUT_WRITER;
   private static final ObjectFactory PRIMITIVE_FACTORY;
   private static final ObjectFactory COMPOSITE_FACTORY;

   private Text(boolean isPrimitive) {
      this._data = isPrimitive ? new char[32] : null;
   }

   public Text(String str) {
      this(str.length() <= 32);
      this._count = str.length();
      if (this._data != null) {
         str.getChars(0, this._count, this._data, 0);
      } else {
         int half = this._count + 32 >> 1 & -32;
         this._head = new Text(str.substring(0, half));
         this._tail = new Text(str.substring(half, this._count));
      }

   }

   public static Text valueOf(Object obj) {
      if (obj instanceof Realtime) {
         return ((Realtime)obj).toText();
      } else {
         return obj instanceof Number ? valueOfNumber(obj) : valueOf(String.valueOf(obj));
      }
   }

   private static Text valueOfNumber(Object num) {
      if (num instanceof Integer) {
         return valueOf((Integer)num);
      } else if (num instanceof Long) {
         return valueOf((Long)num);
      } else if (num instanceof Float) {
         return valueOf((Float)num);
      } else {
         return num instanceof Double ? valueOf((Double)num) : valueOf(String.valueOf(num));
      }
   }

   private static Text valueOf(String str) {
      return valueOf((String)str, 0, str.length());
   }

   private static Text valueOf(String str, int start, int end) {
      int length = end - start;
      if (length <= 32) {
         Text text = newPrimitive(length);
         str.getChars(start, end, text._data, 0);
         return text;
      } else {
         int half = length + 32 >> 1 & -32;
         return newComposite(valueOf(str, start, start + half), valueOf(str, start + half, end));
      }
   }

   public static Text valueOf(char[] chars) {
      return valueOf((char[])chars, 0, chars.length);
   }

   public static Text valueOf(char[] chars, int offset, int length) {
      if (offset >= 0 && length >= 0 && offset + length <= chars.length) {
         if (length <= 32) {
            Text text = newPrimitive(length);
            System.arraycopy(chars, offset, text._data, 0, length);
            return text;
         } else {
            int half = length + 32 >> 1 & -32;
            return newComposite(valueOf(chars, offset, half), valueOf(chars, offset + half, length - half));
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   static Text valueOf(TextBuilder tb, int start, int end) {
      int length = end - start;
      if (length <= 32) {
         Text text = newPrimitive(length);
         tb.getChars(start, end, text._data, 0);
         return text;
      } else {
         int half = length + 32 >> 1 & -32;
         return newComposite(valueOf(tb, start, start + half), valueOf(tb, start + half, end));
      }
   }

   public static Text valueOf(boolean b) {
      return b ? TRUE : FALSE;
   }

   public static Text valueOf(char c) {
      Text text = newPrimitive(1);
      text._data[0] = c;
      return text;
   }

   public static Text valueOf(int i) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var2;
      try {
         var2 = tb.append(i).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var2;
   }

   public static Text valueOf(int i, int radix) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var3;
      try {
         var3 = tb.append(i, radix).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var3;
   }

   public static Text valueOf(long l) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var3;
      try {
         var3 = tb.append(l).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var3;
   }

   public static Text valueOf(long l, int radix) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var4;
      try {
         var4 = tb.append(l, radix).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var4;
   }

   public static Text valueOf(float f) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var2;
      try {
         var2 = tb.append(f).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var2;
   }

   public static Text valueOf(double d) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var3;
      try {
         var3 = tb.append(d).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var3;
   }

   public static Text valueOf(double d, int digits, boolean scientific, boolean showZero) {
      TextBuilder tb = TextBuilder.newInstance();

      Text var6;
      try {
         var6 = tb.append(d, digits, scientific, showZero).toText();
      } finally {
         TextBuilder.recycle(tb);
      }

      return var6;
   }

   public int length() {
      return this._count;
   }

   public Text plus(Object obj) {
      return this.concat(valueOf(obj));
   }

   public Text plus(String str) {
      Text merge = this.append(str);
      return merge != null ? merge : this.concat(valueOf(str));
   }

   private Text append(String str) {
      int length = str.length();
      if (this._data == null) {
         Text merge = this._tail.append(str);
         return merge != null ? newComposite(this._head, merge) : null;
      } else if (this._count + length > 32) {
         return null;
      } else {
         Text text = newPrimitive(this._count + length);
         System.arraycopy(this._data, 0, text._data, 0, this._count);
         str.getChars(0, length, text._data, this._count);
         return text;
      }
   }

   public Text concat(Text that) {
      int length = this._count + that._count;
      if (length <= 32) {
         Text text = newPrimitive(length);
         this.getChars(0, this._count, text._data, 0);
         that.getChars(0, that._count, text._data, this._count);
         return text;
      } else {
         Text head = this;
         Text tail = that;
         if (this._count << 1 < that._count && that._data == null) {
            if (that._head._count > that._tail._count) {
               tail = that.rightRotation();
            }

            head = this.concat(tail._head);
            tail = tail._tail;
         } else if (that._count << 1 < this._count && this._data == null) {
            if (this._tail._count > this._head._count) {
               head = this.leftRotation();
            }

            tail = head._tail.concat(that);
            head = head._head;
         }

         return newComposite(head, tail);
      }
   }

   private Text rightRotation() {
      Text P = this._head;
      if (P._data != null) {
         return this;
      } else {
         Text A = P._head;
         Text B = P._tail;
         Text C = this._tail;
         return newComposite(A, newComposite(B, C));
      }
   }

   private Text leftRotation() {
      Text Q = this._tail;
      if (Q._data != null) {
         return this;
      } else {
         Text B = Q._head;
         Text C = Q._tail;
         Text A = this._head;
         return newComposite(newComposite(A, B), C);
      }
   }

   public Text subtext(int start) {
      return this.subtext(start, this.length());
   }

   public Text insert(int index, Text txt) {
      return this.subtext(0, index).concat(txt).concat(this.subtext(index));
   }

   public Text delete(int start, int end) {
      if (start > end) {
         throw new IndexOutOfBoundsException();
      } else {
         return this.subtext(0, start).concat(this.subtext(end));
      }
   }

   public Text replace(CharSequence target, CharSequence replacement) {
      int i = this.indexOf(target);
      return i < 0 ? this : this.subtext(0, i).concat(valueOf((Object)replacement)).concat(this.subtext(i + target.length()).replace(target, replacement));
   }

   public Text replace(CharSet charSet, CharSequence replacement) {
      int i = this.indexOfAny(charSet);
      return i < 0 ? this : this.subtext(0, i).concat(valueOf((Object)replacement)).concat(this.subtext(i + 1).replace(charSet, replacement));
   }

   public CharSequence subSequence(int start, int end) {
      return this.subtext(start, end);
   }

   public int indexOf(CharSequence csq) {
      return this.indexOf(csq, 0);
   }

   public int indexOf(CharSequence csq, int fromIndex) {
      int csqLength = csq.length();
      int min = Math.max(0, fromIndex);
      int max = this._count - csqLength;
      if (csqLength == 0) {
         return min > max ? -1 : min;
      } else {
         char c = csq.charAt(0);

         for(int i = this.indexOf(c, min); i >= 0 && i <= max; i = this.indexOf(c, i)) {
            boolean match = true;

            for(int j = 1; j < csqLength; ++j) {
               if (this.charAt(i + j) != csq.charAt(j)) {
                  match = false;
                  break;
               }
            }

            if (match) {
               return i;
            }

            ++i;
         }

         return -1;
      }
   }

   public int lastIndexOf(CharSequence csq) {
      return this.lastIndexOf(csq, this._count);
   }

   public int lastIndexOf(CharSequence csq, int fromIndex) {
      int csqLength = csq.length();
      int min = 0;
      int max = Math.min(fromIndex, this._count - csqLength);
      if (csqLength == 0) {
         return 0 > max ? -1 : max;
      } else {
         char c = csq.charAt(0);

         for(int i = this.lastIndexOf(c, max); i >= 0; i = this.lastIndexOf(c, i)) {
            boolean match = true;

            for(int j = 1; j < csqLength; ++j) {
               if (this.charAt(i + j) != csq.charAt(j)) {
                  match = false;
                  break;
               }
            }

            if (match) {
               return i;
            }

            --i;
         }

         return -1;
      }
   }

   public boolean startsWith(CharSequence prefix) {
      return this.startsWith(prefix, 0);
   }

   public boolean endsWith(CharSequence suffix) {
      return this.startsWith(suffix, this.length() - suffix.length());
   }

   public boolean startsWith(CharSequence prefix, int index) {
      int prefixLength = prefix.length();
      if (index >= 0 && index <= this.length() - prefixLength) {
         int i = 0;
         int j = index;

         while(i < prefixLength) {
            if (prefix.charAt(i++) != this.charAt(j++)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public Text trim() {
      int first = 0;

      int last;
      for(last = this.length() - 1; first <= last && this.charAt(first) <= ' '; ++first) {
      }

      while(last >= first && this.charAt(last) <= ' ') {
         --last;
      }

      return this.subtext(first, last + 1);
   }

   public static Text intern(CharSequence csq) {
      Text text = (Text)INTERN_INSTANCES.get(csq);
      return text != null ? text : internImpl(csq.toString());
   }

   public static Text intern(String str) {
      Text text = (Text)INTERN_INSTANCES.get(str);
      return text != null ? text : internImpl(str);
   }

   private static synchronized Text internImpl(final String str) {
      if (!INTERN_INSTANCES.containsKey(str)) {
         MemoryArea.getMemoryArea(INTERN_INSTANCES).executeInArea(new Runnable() {
            public void run() {
               Text txt = new Text(str);
               Text.INTERN_INSTANCES.put(txt, txt);
            }
         });
      }

      return (Text)INTERN_INSTANCES.get(str);
   }

   public boolean contentEquals(CharSequence csq) {
      if (csq.length() != this._count) {
         return false;
      } else {
         int i = 0;

         while(i < this._count) {
            if (this.charAt(i) != csq.charAt(i++)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean contentEqualsIgnoreCase(CharSequence csq) {
      if (this._count != csq.length()) {
         return false;
      } else {
         int i = 0;

         while(i < this._count) {
            char u1 = this.charAt(i);
            char u2 = csq.charAt(i++);
            if (u1 != u2) {
               u1 = Character.toUpperCase(u1);
               u2 = Character.toUpperCase(u2);
               if (u1 != u2 && Character.toLowerCase(u1) != Character.toLowerCase(u2)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof Text)) {
         return false;
      } else {
         Text that = (Text)obj;
         if (this._count != that._count) {
            return false;
         } else {
            int i = 0;

            while(i < this._count) {
               if (this.charAt(i) != that.charAt(i++)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int h = 0;
      int length = this.length();

      for(int i = 0; i < length; h = 31 * h + this.charAt(i++)) {
      }

      return h;
   }

   public int compareTo(Object csq) {
      return FastComparator.LEXICAL.compare(this, csq);
   }

   public Text toText() {
      return this;
   }

   public void printStatistics(PrintStream out) {
      int length = this.length();
      int leaves = this.getNbrOfLeaves();
      synchronized(out) {
         out.print("LENGTH: " + this.length());
         out.print(", MAX DEPTH: " + this.getDepth());
         out.print(", NBR OF BRANCHES: " + this.getNbrOfBranches());
         out.print(", NBR OF LEAVES: " + leaves);
         out.print(", AVG LEAVE LENGTH: " + (length + (leaves >> 1)) / leaves);
         out.println();
      }
   }

   private int getDepth() {
      return this._data != null ? 0 : MathLib.max(this._head.getDepth(), this._tail.getDepth()) + 1;
   }

   private int getNbrOfBranches() {
      return this._data == null ? this._head.getNbrOfBranches() + this._tail.getNbrOfBranches() + 1 : 0;
   }

   private int getNbrOfLeaves() {
      return this._data == null ? this._head.getNbrOfLeaves() + this._tail.getNbrOfLeaves() : 1;
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
      if (this._data != null) {
         writer.write(this._data, 0, this._count);
      } else {
         this._head.print(writer);
         this._tail.print(writer);
      }

   }

   public void println(Writer writer) throws IOException {
      this.print(writer);
      writer.write(10);
   }

   public Text toLowerCase() {
      if (this._data == null) {
         return newComposite(this._head.toLowerCase(), this._tail.toLowerCase());
      } else {
         Text text = newPrimitive(this._count);

         for(int i = 0; i < this._count; text._data[i] = Character.toLowerCase(this._data[i++])) {
         }

         return text;
      }
   }

   public Text toUpperCase() {
      if (this._data == null) {
         return newComposite(this._head.toUpperCase(), this._tail.toUpperCase());
      } else {
         Text text = newPrimitive(this._count);

         for(int i = 0; i < this._count; text._data[i] = Character.toUpperCase(this._data[i++])) {
         }

         return text;
      }
   }

   public char charAt(int index) {
      if (index >= this._count) {
         throw new IndexOutOfBoundsException();
      } else {
         return this._data != null ? this._data[index] : (index < this._head._count ? this._head.charAt(index) : this._tail.charAt(index - this._head._count));
      }
   }

   public int indexOf(char c) {
      return this.indexOf(c, 0);
   }

   public int indexOf(char c, int fromIndex) {
      if (this._data != null) {
         for(int i = MathLib.max(fromIndex, 0); i < this._count; ++i) {
            if (this._data[i] == c) {
               return i;
            }
         }

         return -1;
      } else {
         int cesure = this._head._count;
         if (fromIndex < cesure) {
            int headIndex = this._head.indexOf(c, fromIndex);
            if (headIndex >= 0) {
               return headIndex;
            }
         }

         int tailIndex = this._tail.indexOf(c, fromIndex - cesure);
         return tailIndex >= 0 ? tailIndex + cesure : -1;
      }
   }

   public int lastIndexOf(char c, int fromIndex) {
      if (this._data != null) {
         for(int i = MathLib.min(fromIndex, this._count - 1); i >= 0; --i) {
            if (this._data[i] == c) {
               return i;
            }
         }

         return -1;
      } else {
         int cesure = this._head._count;
         if (fromIndex >= cesure) {
            int tailIndex = this._tail.lastIndexOf(c, fromIndex - cesure);
            if (tailIndex >= 0) {
               return tailIndex + cesure;
            }
         }

         return this._head.lastIndexOf(c, fromIndex);
      }
   }

   public Text subtext(int start, int end) {
      if (this._data != null) {
         if (start >= 0 && start <= end && end <= this._count) {
            if (start == 0 && end == this._count) {
               return this;
            } else if (start == end) {
               return EMPTY;
            } else {
               int length = end - start;
               Text text = newPrimitive(length);
               System.arraycopy(this._data, start, text._data, 0, length);
               return text;
            }
         } else {
            throw new IndexOutOfBoundsException();
         }
      } else {
         int cesure = this._head._count;
         if (end <= cesure) {
            return this._head.subtext(start, end);
         } else if (start >= cesure) {
            return this._tail.subtext(start - cesure, end - cesure);
         } else {
            return start == 0 && end == this._count ? this : this._head.subtext(start, cesure).concat(this._tail.subtext(0, end - cesure));
         }
      }
   }

   public void getChars(int start, int end, char[] dest, int destPos) {
      if (this._data != null) {
         if (start < 0 || end > this._count || start > end) {
            throw new IndexOutOfBoundsException();
         }

         System.arraycopy(this._data, start, dest, destPos, end - start);
      } else {
         int cesure = this._head._count;
         if (end <= cesure) {
            this._head.getChars(start, end, dest, destPos);
         } else if (start >= cesure) {
            this._tail.getChars(start - cesure, end - cesure, dest, destPos);
         } else {
            this._head.getChars(start, cesure, dest, destPos);
            this._tail.getChars(0, end - cesure, dest, destPos + cesure - start);
         }
      }

   }

   public String toString() {
      if (this._data != null) {
         return new String(this._data, 0, this._count);
      } else {
         char[] data = new char[this._count];
         this.getChars(0, this._count, data, 0);
         return new String(data, 0, this._count);
      }
   }

   public Text copy() {
      if (this._data != null) {
         Text text = newPrimitive(this._count);
         System.arraycopy(this._data, 0, text._data, 0, this._count);
         return text;
      } else {
         return newComposite(this._head.copy(), this._tail.copy());
      }
   }

   public static Text valueOf(char c, int length) {
      if (length < 0) {
         throw new IndexOutOfBoundsException();
      } else if (length > 32) {
         int middle = length >> 1;
         return newComposite(valueOf(c, middle), valueOf(c, length - middle));
      } else {
         Text text = newPrimitive(length);

         for(int i = 0; i < length; text._data[i++] = c) {
         }

         return text;
      }
   }

   public boolean isBlank() {
      return this.isBlank(0, this.length());
   }

   public boolean isBlank(int start, int length) {
      while(start < length) {
         if (this.charAt(start) > ' ') {
            return false;
         }

         ++start;
      }

      return true;
   }

   public Text trimStart() {
      int first = 0;

      int last;
      for(last = this.length() - 1; first <= last && this.charAt(first) <= ' '; ++first) {
      }

      return this.subtext(first, last + 1);
   }

   public Text trimEnd() {
      int first = 0;

      int last;
      for(last = this.length() - 1; last >= first && this.charAt(last) <= ' '; --last) {
      }

      return this.subtext(first, last + 1);
   }

   public Text padLeft(int len) {
      return this.padLeft(len, ' ');
   }

   public Text padLeft(int len, char c) {
      int padSize = len <= this.length() ? 0 : len - this.length();
      return this.insert(0, valueOf(c, padSize));
   }

   public Text padRight(int len) {
      return this.padRight(len, ' ');
   }

   public Text padRight(int len, char c) {
      int padSize = len <= this.length() ? 0 : len - this.length();
      return this.concat(valueOf(c, padSize));
   }

   public int indexOfAny(CharSet charSet) {
      return this.indexOfAny(charSet, 0, this.length());
   }

   public int indexOfAny(CharSet charSet, int start) {
      return this.indexOfAny(charSet, start, this.length() - start);
   }

   public int indexOfAny(CharSet charSet, int start, int length) {
      int stop = start + length;

      for(int i = start; i < stop; ++i) {
         if (charSet.contains(this.charAt(i))) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOfAny(CharSet charSet) {
      return this.lastIndexOfAny(charSet, 0, this.length());
   }

   public int lastIndexOfAny(CharSet charSet, int start) {
      return this.lastIndexOfAny(charSet, start, this.length() - start);
   }

   public int lastIndexOfAny(CharSet charSet, int start, int length) {
      int i = start + length;

      do {
         --i;
         if (i < start) {
            return -1;
         }
      } while(!charSet.contains(this.charAt(i)));

      return i;
   }

   private static Text newPrimitive(int length) {
      Text text = (Text)PRIMITIVE_FACTORY.object();
      text._count = length;
      return text;
   }

   private static Text newComposite(Text head, Text tail) {
      Text text = (Text)COMPOSITE_FACTORY.object();
      text._count = head._count + tail._count;
      text._head = head;
      text._tail = tail;
      return text;
   }

   static {
      INTERN_INSTANCES = (new FastMap()).setKeyComparator(FastComparator.LEXICAL);
      EMPTY = intern("");
      TRUE = intern("true");
      FALSE = intern("false");
      SYSTEM_OUT_WRITER = (new UTF8StreamWriter()).setOutput(System.out);
      PRIMITIVE_FACTORY = new ObjectFactory() {
         public Object create() {
            return new Text(true);
         }
      };
      COMPOSITE_FACTORY = new ObjectFactory() {
         public Object create() {
            return new Text(false);
         }
      };
   }
}
