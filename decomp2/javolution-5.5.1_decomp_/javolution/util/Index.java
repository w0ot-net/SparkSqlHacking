package javolution.util;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.List;
import javax.realtime.MemoryArea;
import javolution.lang.Configurable;
import javolution.lang.Immutable;
import javolution.lang.Realtime;
import javolution.text.Cursor;
import javolution.text.Text;
import javolution.text.TextFormat;
import javolution.text.TypeFormat;
import javolution.xml.XMLSerializable;

public final class Index extends Number implements Comparable, FastCollection.Record, Realtime, Immutable, XMLSerializable {
   public static final Index ZERO = new Index(0);
   private static Index[] _NegativeIndices = new Index[32];
   private static int _NegativeIndicesLength;
   public static final Configurable INITIAL_FIRST;
   private static Index[] _PositiveIndices;
   private static int _PositiveIndicesLength;
   public static final Configurable INITIAL_LAST;
   private static final MemoryArea IMMORTAL_MEMORY;
   private final int _value;
   private static final Runnable AUGMENT_POSITIVE;
   private static final Runnable AUGMENT_NEGATIVE;
   private static final int INCREASE_AMOUNT = 32;
   static final TextFormat TEXT_FORMAT;
   private static final long serialVersionUID = 1L;

   private Index(int i) {
      this._value = i;
   }

   public static void setMinimumRange(int first, int last) {
      if (first > last) {
         throw new IllegalArgumentException();
      } else {
         valueOf(first);
         valueOf(last);
      }
   }

   public static Index valueOf(int i) {
      return i >= 0 ? (i < _PositiveIndicesLength ? _PositiveIndices[i] : createPositive(i)) : valueOfNegative(-i);
   }

   public static List rangeOf(int start, int end) {
      FastTable<Index> list = FastTable.newInstance();

      for(int i = start; i < end; ++i) {
         list.add(valueOf(i));
      }

      return list;
   }

   public static List valuesOf(int... indices) {
      FastTable<Index> list = FastTable.newInstance();

      for(int i : indices) {
         list.add(valueOf(i));
      }

      return list;
   }

   private static Index valueOfNegative(int i) {
      return i < _NegativeIndicesLength ? _NegativeIndices[i] : createNegative(i);
   }

   private static synchronized Index createPositive(int i) {
      if (i < _PositiveIndicesLength) {
         return _PositiveIndices[i];
      } else {
         while(i >= _PositiveIndicesLength) {
            IMMORTAL_MEMORY.executeInArea(AUGMENT_POSITIVE);
         }

         return _PositiveIndices[i];
      }
   }

   private static synchronized Index createNegative(int i) {
      if (i < _NegativeIndicesLength) {
         return _NegativeIndices[i];
      } else {
         while(i >= _NegativeIndicesLength) {
            IMMORTAL_MEMORY.executeInArea(AUGMENT_NEGATIVE);
         }

         return _NegativeIndices[i];
      }
   }

   public int intValue() {
      return this._value;
   }

   public long longValue() {
      return (long)this.intValue();
   }

   public float floatValue() {
      return (float)this.intValue();
   }

   public double doubleValue() {
      return (double)this.intValue();
   }

   public String toString() {
      return TextFormat.getInstance(Index.class).formatToString(this);
   }

   public final boolean equals(Object obj) {
      return this == obj;
   }

   public final int hashCode() {
      return this._value;
   }

   protected final Object readResolve() throws ObjectStreamException {
      return valueOf(this._value);
   }

   public final int compareTo(Index that) {
      return this._value - that._value;
   }

   public final FastCollection.Record getNext() {
      return valueOf(this._value + 1);
   }

   public final FastCollection.Record getPrevious() {
      return valueOf(this._value - 1);
   }

   public Text toText() {
      return TextFormat.getInstance(Index.class).format(this);
   }

   static {
      _NegativeIndices[0] = ZERO;
      _NegativeIndices[1] = new Index(-1);
      _NegativeIndicesLength = 2;
      INITIAL_FIRST = new Configurable(new Integer(-(_NegativeIndicesLength - 1))) {
         protected void notifyChange(Object oldValue, Object newValue) {
            Index.valueOf((Integer)newValue);
         }
      };
      _PositiveIndices = new Index[32];
      _PositiveIndices[0] = ZERO;

      for(int i = 1; i < _PositiveIndices.length; ++i) {
         _PositiveIndices[i] = new Index(i);
      }

      _PositiveIndicesLength = _PositiveIndices.length;
      INITIAL_LAST = new Configurable(new Integer(_PositiveIndicesLength - 1)) {
         protected void notifyChange(Object oldValue, Object newValue) {
            Index.valueOf((Integer)newValue);
         }
      };
      IMMORTAL_MEMORY = MemoryArea.getMemoryArea(new Object());
      AUGMENT_POSITIVE = new Runnable() {
         public void run() {
            int i = Index._PositiveIndicesLength;

            for(int n = Index._PositiveIndicesLength + 32; i < n; ++i) {
               Index index = new Index(i);
               if (Index._PositiveIndices.length <= i) {
                  Index[] tmp = new Index[Index._PositiveIndices.length * 2];
                  System.arraycopy(Index._PositiveIndices, 0, tmp, 0, Index._PositiveIndices.length);
                  Index._PositiveIndices = tmp;
               }

               Index._PositiveIndices[i] = index;
            }

            Index._PositiveIndicesLength = 32;
         }
      };
      AUGMENT_NEGATIVE = new Runnable() {
         public void run() {
            int i = Index._NegativeIndicesLength;

            for(int n = Index._NegativeIndicesLength + 32; i < n; ++i) {
               Index index = new Index(-i);
               if (Index._NegativeIndices.length <= i) {
                  Index[] tmp = new Index[Index._NegativeIndices.length * 2];
                  System.arraycopy(Index._NegativeIndices, 0, tmp, 0, Index._NegativeIndices.length);
                  Index._NegativeIndices = tmp;
               }

               Index._NegativeIndices[i] = index;
            }

            Index._NegativeIndicesLength = 32;
         }
      };
      TEXT_FORMAT = new TextFormat(Index.class) {
         public Appendable format(Object obj, Appendable dest) throws IOException {
            return TypeFormat.format(((Index)obj).intValue(), dest);
         }

         public Object parse(CharSequence csq, Cursor cursor) {
            return Index.valueOf(TypeFormat.parseInt(csq, 10, cursor));
         }
      };
   }
}
