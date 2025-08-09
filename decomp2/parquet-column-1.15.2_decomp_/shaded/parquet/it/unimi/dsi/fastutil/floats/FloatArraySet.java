package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public class FloatArraySet extends AbstractFloatSet implements Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   protected transient float[] a;
   protected int size;

   public FloatArraySet(float[] a) {
      this.a = a;
      this.size = a.length;
   }

   public FloatArraySet() {
      this.a = FloatArrays.EMPTY_ARRAY;
   }

   public FloatArraySet(int capacity) {
      this.a = new float[capacity];
   }

   public FloatArraySet(FloatCollection c) {
      this(c.size());
      this.addAll(c);
   }

   public FloatArraySet(Collection c) {
      this(c.size());
      this.addAll(c);
   }

   public FloatArraySet(FloatSet c) {
      this(c.size());
      int i = 0;

      for(FloatIterator var3 = c.iterator(); var3.hasNext(); ++i) {
         float x = (Float)var3.next();
         this.a[i] = x;
      }

      this.size = i;
   }

   public FloatArraySet(Set c) {
      this(c.size());
      int i = 0;

      for(Float x : c) {
         this.a[i] = x;
         ++i;
      }

      this.size = i;
   }

   public FloatArraySet(float[] a, int size) {
      this.a = a;
      this.size = size;
      if (size > a.length) {
         throw new IllegalArgumentException("The provided size (" + size + ") is larger than or equal to the array size (" + a.length + ")");
      }
   }

   public static FloatArraySet of() {
      return ofUnchecked();
   }

   public static FloatArraySet of(float e) {
      return ofUnchecked(e);
   }

   public static FloatArraySet of(float... a) {
      if (a.length == 2) {
         if (Float.floatToIntBits(a[0]) == Float.floatToIntBits(a[1])) {
            throw new IllegalArgumentException("Duplicate element: " + a[1]);
         }
      } else if (a.length > 2) {
         FloatOpenHashSet.of(a);
      }

      return ofUnchecked(a);
   }

   public static FloatArraySet ofUnchecked() {
      return new FloatArraySet();
   }

   public static FloatArraySet ofUnchecked(float... a) {
      return new FloatArraySet(a);
   }

   private int findKey(float o) {
      int i = this.size;

      while(i-- != 0) {
         if (Float.floatToIntBits(this.a[i]) == Float.floatToIntBits(o)) {
            return i;
         }
      }

      return -1;
   }

   public FloatIterator iterator() {
      return new FloatIterator() {
         int next = 0;

         public boolean hasNext() {
            return this.next < FloatArraySet.this.size;
         }

         public float nextFloat() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return FloatArraySet.this.a[this.next++];
            }
         }

         public void remove() {
            int tail = FloatArraySet.this.size-- - this.next--;
            System.arraycopy(FloatArraySet.this.a, this.next + 1, FloatArraySet.this.a, this.next, tail);
         }

         public int skip(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int remaining = FloatArraySet.this.size - this.next;
               if (n < remaining) {
                  this.next += n;
                  return n;
               } else {
                  this.next = FloatArraySet.this.size;
                  return remaining;
               }
            }
         }
      };
   }

   public FloatSpliterator spliterator() {
      return new Spliterator();
   }

   public boolean contains(float k) {
      return this.findKey(k) != -1;
   }

   public int size() {
      return this.size;
   }

   public boolean remove(float k) {
      int pos = this.findKey(k);
      if (pos == -1) {
         return false;
      } else {
         int tail = this.size - pos - 1;

         for(int i = 0; i < tail; ++i) {
            this.a[pos + i] = this.a[pos + i + 1];
         }

         --this.size;
         return true;
      }
   }

   public boolean add(float k) {
      int pos = this.findKey(k);
      if (pos != -1) {
         return false;
      } else {
         if (this.size == this.a.length) {
            float[] b = new float[this.size == 0 ? 2 : this.size * 2];

            for(int i = this.size; i-- != 0; b[i] = this.a[i]) {
            }

            this.a = b;
         }

         this.a[this.size++] = k;
         return true;
      }
   }

   public void clear() {
      this.size = 0;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public float[] toFloatArray() {
      return this.size == 0 ? FloatArrays.EMPTY_ARRAY : Arrays.copyOf(this.a, this.size);
   }

   public float[] toArray(float[] a) {
      if (a == null || a.length < this.size) {
         a = new float[this.size];
      }

      System.arraycopy(this.a, 0, a, 0, this.size);
      return a;
   }

   public FloatArraySet clone() {
      FloatArraySet c;
      try {
         c = (FloatArraySet)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      c.a = (float[])this.a.clone();
      return c;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      s.defaultWriteObject();

      for(int i = 0; i < this.size; ++i) {
         s.writeFloat(this.a[i]);
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.a = new float[this.size];

      for(int i = 0; i < this.size; ++i) {
         this.a[i] = s.readFloat();
      }

   }

   private final class Spliterator implements FloatSpliterator {
      boolean hasSplit;
      int pos;
      int max;

      public Spliterator() {
         this(0, FloatArraySet.this.size, false);
      }

      private Spliterator(int pos, int max, boolean hasSplit) {
         this.hasSplit = false;

         assert pos <= max : "pos " + pos + " must be <= max " + max;

         this.pos = pos;
         this.max = max;
         this.hasSplit = hasSplit;
      }

      private int getWorkingMax() {
         return this.hasSplit ? this.max : FloatArraySet.this.size;
      }

      public int characteristics() {
         return 16721;
      }

      public long estimateSize() {
         return (long)(this.getWorkingMax() - this.pos);
      }

      public boolean tryAdvance(FloatConsumer action) {
         if (this.pos >= this.getWorkingMax()) {
            return false;
         } else {
            action.accept(FloatArraySet.this.a[this.pos++]);
            return true;
         }
      }

      public void forEachRemaining(FloatConsumer action) {
         for(int max = this.getWorkingMax(); this.pos < max; ++this.pos) {
            action.accept(FloatArraySet.this.a[this.pos]);
         }

      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else {
            int max = this.getWorkingMax();
            if (this.pos >= max) {
               return 0L;
            } else {
               int remaining = max - this.pos;
               if (n < (long)remaining) {
                  this.pos = SafeMath.safeLongToInt((long)this.pos + n);
                  return n;
               } else {
                  n = (long)remaining;
                  this.pos = max;
                  return n;
               }
            }
         }
      }

      public FloatSpliterator trySplit() {
         int max = this.getWorkingMax();
         int retLen = max - this.pos >> 1;
         if (retLen <= 1) {
            return null;
         } else {
            this.max = max;
            int myNewPos = this.pos + retLen;
            int oldPos = this.pos;
            this.pos = myNewPos;
            this.hasSplit = true;
            return FloatArraySet.this.new Spliterator(oldPos, myNewPos, true);
         }
      }
   }
}
