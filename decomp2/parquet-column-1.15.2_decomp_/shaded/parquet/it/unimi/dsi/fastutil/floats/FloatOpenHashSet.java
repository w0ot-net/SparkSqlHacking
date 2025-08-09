package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import shaded.parquet.it.unimi.dsi.fastutil.Hash;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;

public class FloatOpenHashSet extends AbstractFloatSet implements Serializable, Cloneable, Hash {
   private static final long serialVersionUID = 0L;
   private static final boolean ASSERTS = false;
   protected transient float[] key;
   protected transient int mask;
   protected transient boolean containsNull;
   protected transient int n;
   protected transient int maxFill;
   protected final transient int minN;
   protected int size;
   protected final float f;

   public FloatOpenHashSet(int expected, float f) {
      if (!(f <= 0.0F) && !(f >= 1.0F)) {
         if (expected < 0) {
            throw new IllegalArgumentException("The expected number of elements must be nonnegative");
         } else {
            this.f = f;
            this.minN = this.n = HashCommon.arraySize(expected, f);
            this.mask = this.n - 1;
            this.maxFill = HashCommon.maxFill(this.n, f);
            this.key = new float[this.n + 1];
         }
      } else {
         throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than 1");
      }
   }

   public FloatOpenHashSet(int expected) {
      this(expected, 0.75F);
   }

   public FloatOpenHashSet() {
      this(16, 0.75F);
   }

   public FloatOpenHashSet(Collection c, float f) {
      this(c.size(), f);
      this.addAll(c);
   }

   public FloatOpenHashSet(Collection c) {
      this(c, 0.75F);
   }

   public FloatOpenHashSet(FloatCollection c, float f) {
      this(c.size(), f);
      this.addAll(c);
   }

   public FloatOpenHashSet(FloatCollection c) {
      this(c, 0.75F);
   }

   public FloatOpenHashSet(FloatIterator i, float f) {
      this(16, f);

      while(i.hasNext()) {
         this.add(i.nextFloat());
      }

   }

   public FloatOpenHashSet(FloatIterator i) {
      this(i, 0.75F);
   }

   public FloatOpenHashSet(Iterator i, float f) {
      this(FloatIterators.asFloatIterator(i), f);
   }

   public FloatOpenHashSet(Iterator i) {
      this(FloatIterators.asFloatIterator(i));
   }

   public FloatOpenHashSet(float[] a, int offset, int length, float f) {
      this(length < 0 ? 0 : length, f);
      FloatArrays.ensureOffsetLength(a, offset, length);

      for(int i = 0; i < length; ++i) {
         this.add(a[offset + i]);
      }

   }

   public FloatOpenHashSet(float[] a, int offset, int length) {
      this(a, offset, length, 0.75F);
   }

   public FloatOpenHashSet(float[] a, float f) {
      this(a, 0, a.length, f);
   }

   public FloatOpenHashSet(float[] a) {
      this(a, 0.75F);
   }

   public static FloatOpenHashSet of() {
      return new FloatOpenHashSet();
   }

   public static FloatOpenHashSet of(float e) {
      FloatOpenHashSet result = new FloatOpenHashSet(1, 0.75F);
      result.add(e);
      return result;
   }

   public static FloatOpenHashSet of(float e0, float e1) {
      FloatOpenHashSet result = new FloatOpenHashSet(2, 0.75F);
      result.add(e0);
      if (!result.add(e1)) {
         throw new IllegalArgumentException("Duplicate element: " + e1);
      } else {
         return result;
      }
   }

   public static FloatOpenHashSet of(float e0, float e1, float e2) {
      FloatOpenHashSet result = new FloatOpenHashSet(3, 0.75F);
      result.add(e0);
      if (!result.add(e1)) {
         throw new IllegalArgumentException("Duplicate element: " + e1);
      } else if (!result.add(e2)) {
         throw new IllegalArgumentException("Duplicate element: " + e2);
      } else {
         return result;
      }
   }

   public static FloatOpenHashSet of(float... a) {
      FloatOpenHashSet result = new FloatOpenHashSet(a.length, 0.75F);

      for(float element : a) {
         if (!result.add(element)) {
            throw new IllegalArgumentException("Duplicate element " + element);
         }
      }

      return result;
   }

   private int realSize() {
      return this.containsNull ? this.size - 1 : this.size;
   }

   public void ensureCapacity(int capacity) {
      int needed = HashCommon.arraySize(capacity, this.f);
      if (needed > this.n) {
         this.rehash(needed);
      }

   }

   private void tryCapacity(long capacity) {
      int needed = (int)Math.min(1073741824L, Math.max(2L, HashCommon.nextPowerOfTwo((long)Math.ceil((double)((float)capacity / this.f)))));
      if (needed > this.n) {
         this.rehash(needed);
      }

   }

   public boolean addAll(FloatCollection c) {
      if ((double)this.f <= (double)0.5F) {
         this.ensureCapacity(c.size());
      } else {
         this.tryCapacity((long)(this.size() + c.size()));
      }

      return super.addAll(c);
   }

   public boolean addAll(Collection c) {
      if ((double)this.f <= (double)0.5F) {
         this.ensureCapacity(c.size());
      } else {
         this.tryCapacity((long)(this.size() + c.size()));
      }

      return super.addAll(c);
   }

   public boolean add(float k) {
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNull) {
            return false;
         }

         this.containsNull = true;
      } else {
         float[] key = this.key;
         int pos;
         float curr;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) != 0) {
            if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
               return false;
            }

            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
                  return false;
               }
            }
         }

         key[pos] = k;
      }

      if (this.size++ >= this.maxFill) {
         this.rehash(HashCommon.arraySize(this.size + 1, this.f));
      }

      return true;
   }

   protected final void shiftKeys(int pos) {
      float[] key = this.key;

      while(true) {
         int last = pos;
         pos = pos + 1 & this.mask;

         float curr;
         while(true) {
            if (Float.floatToIntBits(curr = key[pos]) == 0) {
               key[last] = 0.0F;
               return;
            }

            int slot = HashCommon.mix(HashCommon.float2int(curr)) & this.mask;
            if (last <= pos) {
               if (last >= slot || slot > pos) {
                  break;
               }
            } else if (last >= slot && slot > pos) {
               break;
            }

            pos = pos + 1 & this.mask;
         }

         key[last] = curr;
      }
   }

   private boolean removeEntry(int pos) {
      --this.size;
      this.shiftKeys(pos);
      if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
         this.rehash(this.n / 2);
      }

      return true;
   }

   private boolean removeNullEntry() {
      this.containsNull = false;
      this.key[this.n] = 0.0F;
      --this.size;
      if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
         this.rehash(this.n / 2);
      }

      return true;
   }

   public boolean remove(float k) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNull ? this.removeNullEntry() : false;
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return false;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            return this.removeEntry(pos);
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  return this.removeEntry(pos);
               }
            }

            return false;
         }
      }
   }

   public boolean contains(float k) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNull;
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return false;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            return true;
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public void clear() {
      if (this.size != 0) {
         this.size = 0;
         this.containsNull = false;
         Arrays.fill(this.key, 0.0F);
      }
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public FloatIterator iterator() {
      return new SetIterator();
   }

   public FloatSpliterator spliterator() {
      return new SetSpliterator();
   }

   public void forEach(FloatConsumer action) {
      if (this.containsNull) {
         action.accept(this.key[this.n]);
      }

      float[] key = this.key;
      int pos = this.n;

      while(pos-- != 0) {
         if (Float.floatToIntBits(key[pos]) != 0) {
            action.accept(key[pos]);
         }
      }

   }

   public boolean trim() {
      return this.trim(this.size);
   }

   public boolean trim(int n) {
      int l = HashCommon.nextPowerOfTwo((int)Math.ceil((double)((float)n / this.f)));
      if (l < this.n && this.size <= HashCommon.maxFill(l, this.f)) {
         try {
            this.rehash(l);
            return true;
         } catch (OutOfMemoryError var4) {
            return false;
         }
      } else {
         return true;
      }
   }

   protected void rehash(int newN) {
      float[] key = this.key;
      int mask = newN - 1;
      float[] newKey = new float[newN + 1];
      int i = this.n;

      int pos;
      for(int j = this.realSize(); j-- != 0; newKey[pos] = key[i]) {
         --i;
         if (Float.floatToIntBits(key[i]) != 0 && Float.floatToIntBits(newKey[pos = HashCommon.mix(HashCommon.float2int(key[i])) & mask]) != 0) {
            while(Float.floatToIntBits(newKey[pos = pos + 1 & mask]) != 0) {
            }
         }
      }

      this.n = newN;
      this.mask = mask;
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.key = newKey;
   }

   public FloatOpenHashSet clone() {
      FloatOpenHashSet c;
      try {
         c = (FloatOpenHashSet)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      c.key = (float[])this.key.clone();
      c.containsNull = this.containsNull;
      return c;
   }

   public int hashCode() {
      int h = 0;
      int j = this.realSize();

      for(int i = 0; j-- != 0; ++i) {
         while(Float.floatToIntBits(this.key[i]) == 0) {
            ++i;
         }

         h += HashCommon.float2int(this.key[i]);
      }

      return h;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      FloatIterator i = this.iterator();
      s.defaultWriteObject();
      int j = this.size;

      while(j-- != 0) {
         s.writeFloat(i.nextFloat());
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.n = HashCommon.arraySize(this.size, this.f);
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.mask = this.n - 1;
      float[] key = this.key = new float[this.n + 1];

      float k;
      int pos;
      for(int i = this.size; i-- != 0; key[pos] = k) {
         k = s.readFloat();
         if (Float.floatToIntBits(k) == 0) {
            pos = this.n;
            this.containsNull = true;
         } else if (Float.floatToIntBits(key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) != 0) {
            while(Float.floatToIntBits(key[pos = pos + 1 & this.mask]) != 0) {
            }
         }
      }

   }

   private void checkTable() {
   }

   private final class SetIterator implements FloatIterator {
      int pos;
      int last;
      int c;
      boolean mustReturnNull;
      FloatArrayList wrapped;

      private SetIterator() {
         this.pos = FloatOpenHashSet.this.n;
         this.last = -1;
         this.c = FloatOpenHashSet.this.size;
         this.mustReturnNull = FloatOpenHashSet.this.containsNull;
      }

      public boolean hasNext() {
         return this.c != 0;
      }

      public float nextFloat() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            --this.c;
            if (this.mustReturnNull) {
               this.mustReturnNull = false;
               this.last = FloatOpenHashSet.this.n;
               return FloatOpenHashSet.this.key[FloatOpenHashSet.this.n];
            } else {
               float[] key = FloatOpenHashSet.this.key;

               while(--this.pos >= 0) {
                  if (Float.floatToIntBits(key[this.pos]) != 0) {
                     return key[this.last = this.pos];
                  }
               }

               this.last = Integer.MIN_VALUE;
               return this.wrapped.getFloat(-this.pos - 1);
            }
         }
      }

      private final void shiftKeys(int pos) {
         float[] key = FloatOpenHashSet.this.key;

         while(true) {
            int last = pos;
            pos = pos + 1 & FloatOpenHashSet.this.mask;

            float curr;
            while(true) {
               if (Float.floatToIntBits(curr = key[pos]) == 0) {
                  key[last] = 0.0F;
                  return;
               }

               int slot = HashCommon.mix(HashCommon.float2int(curr)) & FloatOpenHashSet.this.mask;
               if (last <= pos) {
                  if (last >= slot || slot > pos) {
                     break;
                  }
               } else if (last >= slot && slot > pos) {
                  break;
               }

               pos = pos + 1 & FloatOpenHashSet.this.mask;
            }

            if (pos < last) {
               if (this.wrapped == null) {
                  this.wrapped = new FloatArrayList(2);
               }

               this.wrapped.add(key[pos]);
            }

            key[last] = curr;
         }
      }

      public void remove() {
         if (this.last == -1) {
            throw new IllegalStateException();
         } else {
            if (this.last == FloatOpenHashSet.this.n) {
               FloatOpenHashSet.this.containsNull = false;
               FloatOpenHashSet.this.key[FloatOpenHashSet.this.n] = 0.0F;
            } else {
               if (this.pos < 0) {
                  FloatOpenHashSet.this.remove(this.wrapped.getFloat(-this.pos - 1));
                  this.last = -1;
                  return;
               }

               this.shiftKeys(this.last);
            }

            --FloatOpenHashSet.this.size;
            this.last = -1;
         }
      }

      public void forEachRemaining(FloatConsumer action) {
         float[] key = FloatOpenHashSet.this.key;
         if (this.mustReturnNull) {
            this.mustReturnNull = false;
            this.last = FloatOpenHashSet.this.n;
            action.accept(key[FloatOpenHashSet.this.n]);
            --this.c;
         }

         while(this.c != 0) {
            if (--this.pos < 0) {
               this.last = Integer.MIN_VALUE;
               action.accept(this.wrapped.getFloat(-this.pos - 1));
               --this.c;
            } else if (Float.floatToIntBits(key[this.pos]) != 0) {
               action.accept(key[this.last = this.pos]);
               --this.c;
            }
         }

      }
   }

   private final class SetSpliterator implements FloatSpliterator {
      private static final int POST_SPLIT_CHARACTERISTICS = 257;
      int pos = 0;
      int max;
      int c;
      boolean mustReturnNull;
      boolean hasSplit;

      SetSpliterator() {
         this.max = FloatOpenHashSet.this.n;
         this.c = 0;
         this.mustReturnNull = FloatOpenHashSet.this.containsNull;
         this.hasSplit = false;
      }

      SetSpliterator(int pos, int max, boolean mustReturnNull, boolean hasSplit) {
         this.max = FloatOpenHashSet.this.n;
         this.c = 0;
         this.mustReturnNull = FloatOpenHashSet.this.containsNull;
         this.hasSplit = false;
         this.pos = pos;
         this.max = max;
         this.mustReturnNull = mustReturnNull;
         this.hasSplit = hasSplit;
      }

      public boolean tryAdvance(FloatConsumer action) {
         if (this.mustReturnNull) {
            this.mustReturnNull = false;
            ++this.c;
            action.accept(FloatOpenHashSet.this.key[FloatOpenHashSet.this.n]);
            return true;
         } else {
            for(float[] key = FloatOpenHashSet.this.key; this.pos < this.max; ++this.pos) {
               if (Float.floatToIntBits(key[this.pos]) != 0) {
                  ++this.c;
                  action.accept(key[this.pos++]);
                  return true;
               }
            }

            return false;
         }
      }

      public void forEachRemaining(FloatConsumer action) {
         float[] key = FloatOpenHashSet.this.key;
         if (this.mustReturnNull) {
            this.mustReturnNull = false;
            action.accept(key[FloatOpenHashSet.this.n]);
            ++this.c;
         }

         for(; this.pos < this.max; ++this.pos) {
            if (Float.floatToIntBits(key[this.pos]) != 0) {
               action.accept(key[this.pos]);
               ++this.c;
            }
         }

      }

      public int characteristics() {
         return this.hasSplit ? 257 : 321;
      }

      public long estimateSize() {
         return !this.hasSplit ? (long)(FloatOpenHashSet.this.size - this.c) : Math.min((long)(FloatOpenHashSet.this.size - this.c), (long)((double)FloatOpenHashSet.this.realSize() / (double)FloatOpenHashSet.this.n * (double)(this.max - this.pos)) + (long)(this.mustReturnNull ? 1 : 0));
      }

      public SetSpliterator trySplit() {
         if (this.pos >= this.max - 1) {
            return null;
         } else {
            int retLen = this.max - this.pos >> 1;
            if (retLen <= 1) {
               return null;
            } else {
               int myNewPos = this.pos + retLen;
               int retPos = this.pos;
               SetSpliterator split = FloatOpenHashSet.this.new SetSpliterator(retPos, myNewPos, this.mustReturnNull, true);
               this.pos = myNewPos;
               this.mustReturnNull = false;
               this.hasSplit = true;
               return split;
            }
         }
      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else if (n == 0L) {
            return 0L;
         } else {
            long skipped = 0L;
            if (this.mustReturnNull) {
               this.mustReturnNull = false;
               ++skipped;
               --n;
            }

            float[] key = FloatOpenHashSet.this.key;

            while(this.pos < this.max && n > 0L) {
               if (Float.floatToIntBits(key[this.pos++]) != 0) {
                  ++skipped;
                  --n;
               }
            }

            return skipped;
         }
      }
   }
}
