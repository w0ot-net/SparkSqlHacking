package shaded.parquet.it.unimi.dsi.fastutil.floats;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntConsumer;
import shaded.parquet.it.unimi.dsi.fastutil.Hash;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;
import shaded.parquet.it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterators;
import shaded.parquet.it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectSpliterators;

public class Float2IntLinkedOpenHashMap extends AbstractFloat2IntSortedMap implements Serializable, Cloneable, Hash {
   private static final long serialVersionUID = 0L;
   private static final boolean ASSERTS = false;
   protected transient float[] key;
   protected transient int[] value;
   protected transient int mask;
   protected transient boolean containsNullKey;
   protected transient int first;
   protected transient int last;
   protected transient long[] link;
   protected transient int n;
   protected transient int maxFill;
   protected final transient int minN;
   protected int size;
   protected final float f;
   protected transient Float2IntSortedMap.FastSortedEntrySet entries;
   protected transient FloatSortedSet keys;
   protected transient IntCollection values;

   public Float2IntLinkedOpenHashMap(int expected, float f) {
      this.first = -1;
      this.last = -1;
      if (!(f <= 0.0F) && !(f >= 1.0F)) {
         if (expected < 0) {
            throw new IllegalArgumentException("The expected number of elements must be nonnegative");
         } else {
            this.f = f;
            this.minN = this.n = HashCommon.arraySize(expected, f);
            this.mask = this.n - 1;
            this.maxFill = HashCommon.maxFill(this.n, f);
            this.key = new float[this.n + 1];
            this.value = new int[this.n + 1];
            this.link = new long[this.n + 1];
         }
      } else {
         throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than 1");
      }
   }

   public Float2IntLinkedOpenHashMap(int expected) {
      this(expected, 0.75F);
   }

   public Float2IntLinkedOpenHashMap() {
      this(16, 0.75F);
   }

   public Float2IntLinkedOpenHashMap(Map m, float f) {
      this(m.size(), f);
      this.putAll(m);
   }

   public Float2IntLinkedOpenHashMap(Map m) {
      this(m, 0.75F);
   }

   public Float2IntLinkedOpenHashMap(Float2IntMap m, float f) {
      this(m.size(), f);
      this.putAll(m);
   }

   public Float2IntLinkedOpenHashMap(Float2IntMap m) {
      this(m, 0.75F);
   }

   public Float2IntLinkedOpenHashMap(float[] k, int[] v, float f) {
      this(k.length, f);
      if (k.length != v.length) {
         throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
      } else {
         for(int i = 0; i < k.length; ++i) {
            this.put(k[i], v[i]);
         }

      }
   }

   public Float2IntLinkedOpenHashMap(float[] k, int[] v) {
      this(k, v, 0.75F);
   }

   private int realSize() {
      return this.containsNullKey ? this.size - 1 : this.size;
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

   private int removeEntry(int pos) {
      int oldValue = this.value[pos];
      --this.size;
      this.fixPointers(pos);
      this.shiftKeys(pos);
      if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
         this.rehash(this.n / 2);
      }

      return oldValue;
   }

   private int removeNullEntry() {
      this.containsNullKey = false;
      int oldValue = this.value[this.n];
      --this.size;
      this.fixPointers(this.n);
      if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
         this.rehash(this.n / 2);
      }

      return oldValue;
   }

   public void putAll(Map m) {
      if ((double)this.f <= (double)0.5F) {
         this.ensureCapacity(m.size());
      } else {
         this.tryCapacity((long)(this.size() + m.size()));
      }

      super.putAll(m);
   }

   private int find(float k) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNullKey ? this.n : -(this.n + 1);
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return -(pos + 1);
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            return pos;
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  return pos;
               }
            }

            return -(pos + 1);
         }
      }
   }

   private void insert(int pos, float k, int v) {
      if (pos == this.n) {
         this.containsNullKey = true;
      }

      this.key[pos] = k;
      this.value[pos] = v;
      if (this.size == 0) {
         this.first = this.last = pos;
         this.link[pos] = -1L;
      } else {
         long[] var10000 = this.link;
         int var10001 = this.last;
         var10000[var10001] ^= (this.link[this.last] ^ (long)pos & 4294967295L) & 4294967295L;
         this.link[pos] = ((long)this.last & 4294967295L) << 32 | 4294967295L;
         this.last = pos;
      }

      if (this.size++ >= this.maxFill) {
         this.rehash(HashCommon.arraySize(this.size + 1, this.f));
      }

   }

   public int put(float k, int v) {
      int pos = this.find(k);
      if (pos < 0) {
         this.insert(-pos - 1, k, v);
         return this.defRetValue;
      } else {
         int oldValue = this.value[pos];
         this.value[pos] = v;
         return oldValue;
      }
   }

   private int addToValue(int pos, int incr) {
      int oldValue = this.value[pos];
      this.value[pos] = oldValue + incr;
      return oldValue;
   }

   public int addTo(float k, int incr) {
      int pos;
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNullKey) {
            return this.addToValue(this.n, incr);
         }

         pos = this.n;
         this.containsNullKey = true;
      } else {
         float[] key = this.key;
         float curr;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) != 0) {
            if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
               return this.addToValue(pos, incr);
            }

            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
                  return this.addToValue(pos, incr);
               }
            }
         }
      }

      this.key[pos] = k;
      this.value[pos] = this.defRetValue + incr;
      if (this.size == 0) {
         this.first = this.last = pos;
         this.link[pos] = -1L;
      } else {
         long[] var10000 = this.link;
         int var10001 = this.last;
         var10000[var10001] ^= (this.link[this.last] ^ (long)pos & 4294967295L) & 4294967295L;
         this.link[pos] = ((long)this.last & 4294967295L) << 32 | 4294967295L;
         this.last = pos;
      }

      if (this.size++ >= this.maxFill) {
         this.rehash(HashCommon.arraySize(this.size + 1, this.f));
      }

      return this.defRetValue;
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
         this.value[last] = this.value[pos];
         this.fixPointers(pos, last);
      }
   }

   public int remove(float k) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNullKey ? this.removeNullEntry() : this.defRetValue;
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return this.defRetValue;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            return this.removeEntry(pos);
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  return this.removeEntry(pos);
               }
            }

            return this.defRetValue;
         }
      }
   }

   private int setValue(int pos, int v) {
      int oldValue = this.value[pos];
      this.value[pos] = v;
      return oldValue;
   }

   public int removeFirstInt() {
      if (this.size == 0) {
         throw new NoSuchElementException();
      } else {
         int pos = this.first;
         if (this.size == 1) {
            this.first = this.last = -1;
         } else {
            this.first = (int)this.link[pos];
            if (0 <= this.first) {
               long[] var10000 = this.link;
               int var10001 = this.first;
               var10000[var10001] |= -4294967296L;
            }
         }

         --this.size;
         int v = this.value[pos];
         if (pos == this.n) {
            this.containsNullKey = false;
         } else {
            this.shiftKeys(pos);
         }

         if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
            this.rehash(this.n / 2);
         }

         return v;
      }
   }

   public int removeLastInt() {
      if (this.size == 0) {
         throw new NoSuchElementException();
      } else {
         int pos = this.last;
         if (this.size == 1) {
            this.first = this.last = -1;
         } else {
            this.last = (int)(this.link[pos] >>> 32);
            if (0 <= this.last) {
               long[] var10000 = this.link;
               int var10001 = this.last;
               var10000[var10001] |= 4294967295L;
            }
         }

         --this.size;
         int v = this.value[pos];
         if (pos == this.n) {
            this.containsNullKey = false;
         } else {
            this.shiftKeys(pos);
         }

         if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
            this.rehash(this.n / 2);
         }

         return v;
      }
   }

   private void moveIndexToFirst(int i) {
      if (this.size != 1 && this.first != i) {
         if (this.last == i) {
            this.last = (int)(this.link[i] >>> 32);
            long[] var10000 = this.link;
            int var10001 = this.last;
            var10000[var10001] |= 4294967295L;
         } else {
            long linki = this.link[i];
            int prev = (int)(linki >>> 32);
            int next = (int)linki;
            long[] var6 = this.link;
            var6[prev] ^= (this.link[prev] ^ linki & 4294967295L) & 4294967295L;
            var6 = this.link;
            var6[next] ^= (this.link[next] ^ linki & -4294967296L) & -4294967296L;
         }

         long[] var8 = this.link;
         int var9 = this.first;
         var8[var9] ^= (this.link[this.first] ^ ((long)i & 4294967295L) << 32) & -4294967296L;
         this.link[i] = -4294967296L | (long)this.first & 4294967295L;
         this.first = i;
      }
   }

   private void moveIndexToLast(int i) {
      if (this.size != 1 && this.last != i) {
         if (this.first == i) {
            this.first = (int)this.link[i];
            long[] var10000 = this.link;
            int var10001 = this.first;
            var10000[var10001] |= -4294967296L;
         } else {
            long linki = this.link[i];
            int prev = (int)(linki >>> 32);
            int next = (int)linki;
            long[] var6 = this.link;
            var6[prev] ^= (this.link[prev] ^ linki & 4294967295L) & 4294967295L;
            var6 = this.link;
            var6[next] ^= (this.link[next] ^ linki & -4294967296L) & -4294967296L;
         }

         long[] var8 = this.link;
         int var9 = this.last;
         var8[var9] ^= (this.link[this.last] ^ (long)i & 4294967295L) & 4294967295L;
         this.link[i] = ((long)this.last & 4294967295L) << 32 | 4294967295L;
         this.last = i;
      }
   }

   public int getAndMoveToFirst(float k) {
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNullKey) {
            this.moveIndexToFirst(this.n);
            return this.value[this.n];
         } else {
            return this.defRetValue;
         }
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return this.defRetValue;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            this.moveIndexToFirst(pos);
            return this.value[pos];
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  this.moveIndexToFirst(pos);
                  return this.value[pos];
               }
            }

            return this.defRetValue;
         }
      }
   }

   public int getAndMoveToLast(float k) {
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNullKey) {
            this.moveIndexToLast(this.n);
            return this.value[this.n];
         } else {
            return this.defRetValue;
         }
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return this.defRetValue;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            this.moveIndexToLast(pos);
            return this.value[pos];
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  this.moveIndexToLast(pos);
                  return this.value[pos];
               }
            }

            return this.defRetValue;
         }
      }
   }

   public int putAndMoveToFirst(float k, int v) {
      int pos;
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNullKey) {
            this.moveIndexToFirst(this.n);
            return this.setValue(this.n, v);
         }

         this.containsNullKey = true;
         pos = this.n;
      } else {
         float[] key = this.key;
         float curr;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) != 0) {
            if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
               this.moveIndexToFirst(pos);
               return this.setValue(pos, v);
            }

            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
                  this.moveIndexToFirst(pos);
                  return this.setValue(pos, v);
               }
            }
         }
      }

      this.key[pos] = k;
      this.value[pos] = v;
      if (this.size == 0) {
         this.first = this.last = pos;
         this.link[pos] = -1L;
      } else {
         long[] var10000 = this.link;
         int var10001 = this.first;
         var10000[var10001] ^= (this.link[this.first] ^ ((long)pos & 4294967295L) << 32) & -4294967296L;
         this.link[pos] = -4294967296L | (long)this.first & 4294967295L;
         this.first = pos;
      }

      if (this.size++ >= this.maxFill) {
         this.rehash(HashCommon.arraySize(this.size, this.f));
      }

      return this.defRetValue;
   }

   public int putAndMoveToLast(float k, int v) {
      int pos;
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNullKey) {
            this.moveIndexToLast(this.n);
            return this.setValue(this.n, v);
         }

         this.containsNullKey = true;
         pos = this.n;
      } else {
         float[] key = this.key;
         float curr;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) != 0) {
            if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
               this.moveIndexToLast(pos);
               return this.setValue(pos, v);
            }

            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
                  this.moveIndexToLast(pos);
                  return this.setValue(pos, v);
               }
            }
         }
      }

      this.key[pos] = k;
      this.value[pos] = v;
      if (this.size == 0) {
         this.first = this.last = pos;
         this.link[pos] = -1L;
      } else {
         long[] var10000 = this.link;
         int var10001 = this.last;
         var10000[var10001] ^= (this.link[this.last] ^ (long)pos & 4294967295L) & 4294967295L;
         this.link[pos] = ((long)this.last & 4294967295L) << 32 | 4294967295L;
         this.last = pos;
      }

      if (this.size++ >= this.maxFill) {
         this.rehash(HashCommon.arraySize(this.size, this.f));
      }

      return this.defRetValue;
   }

   public int get(float k) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNullKey ? this.value[this.n] : this.defRetValue;
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return this.defRetValue;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            return this.value[pos];
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  return this.value[pos];
               }
            }

            return this.defRetValue;
         }
      }
   }

   public boolean containsKey(float k) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNullKey;
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

   public boolean containsValue(int v) {
      int[] value = this.value;
      float[] key = this.key;
      if (this.containsNullKey && value[this.n] == v) {
         return true;
      } else {
         int i = this.n;

         while(i-- != 0) {
            if (Float.floatToIntBits(key[i]) != 0 && value[i] == v) {
               return true;
            }
         }

         return false;
      }
   }

   public int getOrDefault(float k, int defaultValue) {
      if (Float.floatToIntBits(k) == 0) {
         return this.containsNullKey ? this.value[this.n] : defaultValue;
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return defaultValue;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
            return this.value[pos];
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                  return this.value[pos];
               }
            }

            return defaultValue;
         }
      }
   }

   public int putIfAbsent(float k, int v) {
      int pos = this.find(k);
      if (pos >= 0) {
         return this.value[pos];
      } else {
         this.insert(-pos - 1, k, v);
         return this.defRetValue;
      }
   }

   public boolean remove(float k, int v) {
      if (Float.floatToIntBits(k) == 0) {
         if (this.containsNullKey && v == this.value[this.n]) {
            this.removeNullEntry();
            return true;
         } else {
            return false;
         }
      } else {
         float[] key = this.key;
         float curr;
         int pos;
         if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask]) == 0) {
            return false;
         } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr) && v == this.value[pos]) {
            this.removeEntry(pos);
            return true;
         } else {
            while(Float.floatToIntBits(curr = key[pos = pos + 1 & this.mask]) != 0) {
               if (Float.floatToIntBits(k) == Float.floatToIntBits(curr) && v == this.value[pos]) {
                  this.removeEntry(pos);
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean replace(float k, int oldValue, int v) {
      int pos = this.find(k);
      if (pos >= 0 && oldValue == this.value[pos]) {
         this.value[pos] = v;
         return true;
      } else {
         return false;
      }
   }

   public int replace(float k, int v) {
      int pos = this.find(k);
      if (pos < 0) {
         return this.defRetValue;
      } else {
         int oldValue = this.value[pos];
         this.value[pos] = v;
         return oldValue;
      }
   }

   public int computeIfAbsent(float k, DoubleToIntFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int pos = this.find(k);
      if (pos >= 0) {
         return this.value[pos];
      } else {
         int newValue = mappingFunction.applyAsInt((double)k);
         this.insert(-pos - 1, k, newValue);
         return newValue;
      }
   }

   public int computeIfAbsent(float key, Float2IntFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int pos = this.find(key);
      if (pos >= 0) {
         return this.value[pos];
      } else if (!mappingFunction.containsKey(key)) {
         return this.defRetValue;
      } else {
         int newValue = mappingFunction.get(key);
         this.insert(-pos - 1, key, newValue);
         return newValue;
      }
   }

   public int computeIfAbsentNullable(float k, DoubleFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int pos = this.find(k);
      if (pos >= 0) {
         return this.value[pos];
      } else {
         Integer newValue = (Integer)mappingFunction.apply((double)k);
         if (newValue == null) {
            return this.defRetValue;
         } else {
            int v = newValue;
            this.insert(-pos - 1, k, v);
            return v;
         }
      }
   }

   public int computeIfPresent(float k, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int pos = this.find(k);
      if (pos < 0) {
         return this.defRetValue;
      } else {
         Integer newValue = (Integer)remappingFunction.apply(k, this.value[pos]);
         if (newValue == null) {
            if (Float.floatToIntBits(k) == 0) {
               this.removeNullEntry();
            } else {
               this.removeEntry(pos);
            }

            return this.defRetValue;
         } else {
            return this.value[pos] = newValue;
         }
      }
   }

   public int compute(float k, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int pos = this.find(k);
      Integer newValue = (Integer)remappingFunction.apply(k, pos >= 0 ? this.value[pos] : null);
      if (newValue == null) {
         if (pos >= 0) {
            if (Float.floatToIntBits(k) == 0) {
               this.removeNullEntry();
            } else {
               this.removeEntry(pos);
            }
         }

         return this.defRetValue;
      } else {
         int newVal = newValue;
         if (pos < 0) {
            this.insert(-pos - 1, k, newVal);
            return newVal;
         } else {
            return this.value[pos] = newVal;
         }
      }
   }

   public int merge(float k, int v, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int pos = this.find(k);
      if (pos < 0) {
         if (pos < 0) {
            this.insert(-pos - 1, k, v);
         } else {
            this.value[pos] = v;
         }

         return v;
      } else {
         Integer newValue = (Integer)remappingFunction.apply(this.value[pos], v);
         if (newValue == null) {
            if (Float.floatToIntBits(k) == 0) {
               this.removeNullEntry();
            } else {
               this.removeEntry(pos);
            }

            return this.defRetValue;
         } else {
            return this.value[pos] = newValue;
         }
      }
   }

   public void clear() {
      if (this.size != 0) {
         this.size = 0;
         this.containsNullKey = false;
         Arrays.fill(this.key, 0.0F);
         this.first = this.last = -1;
      }
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   protected void fixPointers(int i) {
      if (this.size == 0) {
         this.first = this.last = -1;
      } else if (this.first == i) {
         this.first = (int)this.link[i];
         if (0 <= this.first) {
            long[] var8 = this.link;
            int var9 = this.first;
            var8[var9] |= -4294967296L;
         }

      } else if (this.last == i) {
         this.last = (int)(this.link[i] >>> 32);
         if (0 <= this.last) {
            long[] var7 = this.link;
            int var10001 = this.last;
            var7[var10001] |= 4294967295L;
         }

      } else {
         long linki = this.link[i];
         int prev = (int)(linki >>> 32);
         int next = (int)linki;
         long[] var10000 = this.link;
         var10000[prev] ^= (this.link[prev] ^ linki & 4294967295L) & 4294967295L;
         var10000 = this.link;
         var10000[next] ^= (this.link[next] ^ linki & -4294967296L) & -4294967296L;
      }
   }

   protected void fixPointers(int s, int d) {
      if (this.size == 1) {
         this.first = this.last = d;
         this.link[d] = -1L;
      } else if (this.first == s) {
         this.first = d;
         long[] var9 = this.link;
         int var10 = (int)this.link[s];
         var9[var10] ^= (this.link[(int)this.link[s]] ^ ((long)d & 4294967295L) << 32) & -4294967296L;
         this.link[d] = this.link[s];
      } else if (this.last == s) {
         this.last = d;
         long[] var8 = this.link;
         int var10001 = (int)(this.link[s] >>> 32);
         var8[var10001] ^= (this.link[(int)(this.link[s] >>> 32)] ^ (long)d & 4294967295L) & 4294967295L;
         this.link[d] = this.link[s];
      } else {
         long links = this.link[s];
         int prev = (int)(links >>> 32);
         int next = (int)links;
         long[] var10000 = this.link;
         var10000[prev] ^= (this.link[prev] ^ (long)d & 4294967295L) & 4294967295L;
         var10000 = this.link;
         var10000[next] ^= (this.link[next] ^ ((long)d & 4294967295L) << 32) & -4294967296L;
         this.link[d] = links;
      }
   }

   public float firstFloatKey() {
      if (this.size == 0) {
         throw new NoSuchElementException();
      } else {
         return this.key[this.first];
      }
   }

   public float lastFloatKey() {
      if (this.size == 0) {
         throw new NoSuchElementException();
      } else {
         return this.key[this.last];
      }
   }

   public Float2IntSortedMap tailMap(float from) {
      throw new UnsupportedOperationException();
   }

   public Float2IntSortedMap headMap(float to) {
      throw new UnsupportedOperationException();
   }

   public Float2IntSortedMap subMap(float from, float to) {
      throw new UnsupportedOperationException();
   }

   public FloatComparator comparator() {
      return null;
   }

   public Float2IntSortedMap.FastSortedEntrySet float2IntEntrySet() {
      if (this.entries == null) {
         this.entries = new MapEntrySet();
      }

      return this.entries;
   }

   public FloatSortedSet keySet() {
      if (this.keys == null) {
         this.keys = new KeySet();
      }

      return this.keys;
   }

   public IntCollection values() {
      if (this.values == null) {
         this.values = new AbstractIntCollection() {
            private static final int SPLITERATOR_CHARACTERISTICS = 336;

            public IntIterator iterator() {
               return Float2IntLinkedOpenHashMap.this.new ValueIterator();
            }

            public IntSpliterator spliterator() {
               return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)Float2IntLinkedOpenHashMap.this), 336);
            }

            public void forEach(IntConsumer consumer) {
               int i = Float2IntLinkedOpenHashMap.this.size;
               int next = Float2IntLinkedOpenHashMap.this.first;

               while(i-- != 0) {
                  int curr = next;
                  next = (int)Float2IntLinkedOpenHashMap.this.link[next];
                  consumer.accept(Float2IntLinkedOpenHashMap.this.value[curr]);
               }

            }

            public int size() {
               return Float2IntLinkedOpenHashMap.this.size;
            }

            public boolean contains(int v) {
               return Float2IntLinkedOpenHashMap.this.containsValue(v);
            }

            public void clear() {
               Float2IntLinkedOpenHashMap.this.clear();
            }
         };
      }

      return this.values;
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
      int[] value = this.value;
      int mask = newN - 1;
      float[] newKey = new float[newN + 1];
      int[] newValue = new int[newN + 1];
      int i = this.first;
      int prev = -1;
      int newPrev = -1;
      long[] link = this.link;
      long[] newLink = new long[newN + 1];
      this.first = -1;

      int t;
      for(int j = this.size; j-- != 0; prev = t) {
         int pos;
         if (Float.floatToIntBits(key[i]) == 0) {
            pos = newN;
         } else {
            for(pos = HashCommon.mix(HashCommon.float2int(key[i])) & mask; Float.floatToIntBits(newKey[pos]) != 0; pos = pos + 1 & mask) {
            }
         }

         newKey[pos] = key[i];
         newValue[pos] = value[i];
         if (prev != -1) {
            newLink[newPrev] ^= (newLink[newPrev] ^ (long)pos & 4294967295L) & 4294967295L;
            newLink[pos] ^= (newLink[pos] ^ ((long)newPrev & 4294967295L) << 32) & -4294967296L;
            newPrev = pos;
         } else {
            newPrev = this.first = pos;
            newLink[pos] = -1L;
         }

         t = i;
         i = (int)link[i];
      }

      this.link = newLink;
      this.last = newPrev;
      if (newPrev != -1) {
         newLink[newPrev] |= 4294967295L;
      }

      this.n = newN;
      this.mask = mask;
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.key = newKey;
      this.value = newValue;
   }

   public Float2IntLinkedOpenHashMap clone() {
      Float2IntLinkedOpenHashMap c;
      try {
         c = (Float2IntLinkedOpenHashMap)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      c.keys = null;
      c.values = null;
      c.entries = null;
      c.containsNullKey = this.containsNullKey;
      c.key = (float[])this.key.clone();
      c.value = (int[])this.value.clone();
      c.link = (long[])this.link.clone();
      return c;
   }

   public int hashCode() {
      int h = 0;
      int j = this.realSize();
      int i = 0;

      for(int t = 0; j-- != 0; ++i) {
         while(Float.floatToIntBits(this.key[i]) == 0) {
            ++i;
         }

         t = HashCommon.float2int(this.key[i]);
         t ^= this.value[i];
         h += t;
      }

      if (this.containsNullKey) {
         h += this.value[this.n];
      }

      return h;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      float[] key = this.key;
      int[] value = this.value;
      EntryIterator i = new EntryIterator();
      s.defaultWriteObject();
      int j = this.size;

      while(j-- != 0) {
         int e = i.nextEntry();
         s.writeFloat(key[e]);
         s.writeInt(value[e]);
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.n = HashCommon.arraySize(this.size, this.f);
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.mask = this.n - 1;
      float[] key = this.key = new float[this.n + 1];
      int[] value = this.value = new int[this.n + 1];
      long[] link = this.link = new long[this.n + 1];
      int prev = -1;
      this.first = this.last = -1;
      int i = this.size;

      while(i-- != 0) {
         float k = s.readFloat();
         int v = s.readInt();
         int pos;
         if (Float.floatToIntBits(k) == 0) {
            pos = this.n;
            this.containsNullKey = true;
         } else {
            for(pos = HashCommon.mix(HashCommon.float2int(k)) & this.mask; Float.floatToIntBits(key[pos]) != 0; pos = pos + 1 & this.mask) {
            }
         }

         key[pos] = k;
         value[pos] = v;
         if (this.first != -1) {
            link[prev] ^= (link[prev] ^ (long)pos & 4294967295L) & 4294967295L;
            link[pos] ^= (link[pos] ^ ((long)prev & 4294967295L) << 32) & -4294967296L;
            prev = pos;
         } else {
            prev = this.first = pos;
            link[pos] |= -4294967296L;
         }
      }

      this.last = prev;
      if (prev != -1) {
         link[prev] |= 4294967295L;
      }

   }

   private void checkTable() {
   }

   final class MapEntry implements Float2IntMap.Entry, Map.Entry, FloatIntPair {
      int index;

      MapEntry(final int index) {
         this.index = index;
      }

      MapEntry() {
      }

      public float getFloatKey() {
         return Float2IntLinkedOpenHashMap.this.key[this.index];
      }

      public float leftFloat() {
         return Float2IntLinkedOpenHashMap.this.key[this.index];
      }

      public int getIntValue() {
         return Float2IntLinkedOpenHashMap.this.value[this.index];
      }

      public int rightInt() {
         return Float2IntLinkedOpenHashMap.this.value[this.index];
      }

      public int setValue(int v) {
         int oldValue = Float2IntLinkedOpenHashMap.this.value[this.index];
         Float2IntLinkedOpenHashMap.this.value[this.index] = v;
         return oldValue;
      }

      public FloatIntPair right(int v) {
         Float2IntLinkedOpenHashMap.this.value[this.index] = v;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Float getKey() {
         return Float2IntLinkedOpenHashMap.this.key[this.index];
      }

      /** @deprecated */
      @Deprecated
      public Integer getValue() {
         return Float2IntLinkedOpenHashMap.this.value[this.index];
      }

      /** @deprecated */
      @Deprecated
      public Integer setValue(Integer v) {
         return this.setValue(v);
      }

      public boolean equals(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<Float, Integer> e = (Map.Entry)o;
            return Float.floatToIntBits(Float2IntLinkedOpenHashMap.this.key[this.index]) == Float.floatToIntBits((Float)e.getKey()) && Float2IntLinkedOpenHashMap.this.value[this.index] == (Integer)e.getValue();
         }
      }

      public int hashCode() {
         return HashCommon.float2int(Float2IntLinkedOpenHashMap.this.key[this.index]) ^ Float2IntLinkedOpenHashMap.this.value[this.index];
      }

      public String toString() {
         return Float2IntLinkedOpenHashMap.this.key[this.index] + "=>" + Float2IntLinkedOpenHashMap.this.value[this.index];
      }
   }

   private abstract class MapIterator {
      int prev;
      int next;
      int curr;
      int index;

      abstract void acceptOnIndex(Object var1, int var2);

      protected MapIterator() {
         this.prev = -1;
         this.next = -1;
         this.curr = -1;
         this.index = -1;
         this.next = Float2IntLinkedOpenHashMap.this.first;
         this.index = 0;
      }

      private MapIterator(final float from) {
         this.prev = -1;
         this.next = -1;
         this.curr = -1;
         this.index = -1;
         if (Float.floatToIntBits(from) == 0) {
            if (Float2IntLinkedOpenHashMap.this.containsNullKey) {
               this.next = (int)Float2IntLinkedOpenHashMap.this.link[Float2IntLinkedOpenHashMap.this.n];
               this.prev = Float2IntLinkedOpenHashMap.this.n;
            } else {
               throw new NoSuchElementException("The key " + from + " does not belong to this map.");
            }
         } else if (Float.floatToIntBits(Float2IntLinkedOpenHashMap.this.key[Float2IntLinkedOpenHashMap.this.last]) == Float.floatToIntBits(from)) {
            this.prev = Float2IntLinkedOpenHashMap.this.last;
            this.index = Float2IntLinkedOpenHashMap.this.size;
         } else {
            for(int pos = HashCommon.mix(HashCommon.float2int(from)) & Float2IntLinkedOpenHashMap.this.mask; Float.floatToIntBits(Float2IntLinkedOpenHashMap.this.key[pos]) != 0; pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask) {
               if (Float.floatToIntBits(Float2IntLinkedOpenHashMap.this.key[pos]) == Float.floatToIntBits(from)) {
                  this.next = (int)Float2IntLinkedOpenHashMap.this.link[pos];
                  this.prev = pos;
                  return;
               }
            }

            throw new NoSuchElementException("The key " + from + " does not belong to this map.");
         }
      }

      public boolean hasNext() {
         return this.next != -1;
      }

      public boolean hasPrevious() {
         return this.prev != -1;
      }

      private final void ensureIndexKnown() {
         if (this.index < 0) {
            if (this.prev == -1) {
               this.index = 0;
            } else if (this.next == -1) {
               this.index = Float2IntLinkedOpenHashMap.this.size;
            } else {
               int pos = Float2IntLinkedOpenHashMap.this.first;

               for(this.index = 1; pos != this.prev; ++this.index) {
                  pos = (int)Float2IntLinkedOpenHashMap.this.link[pos];
               }

            }
         }
      }

      public int nextIndex() {
         this.ensureIndexKnown();
         return this.index;
      }

      public int previousIndex() {
         this.ensureIndexKnown();
         return this.index - 1;
      }

      public int nextEntry() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.curr = this.next;
            this.next = (int)Float2IntLinkedOpenHashMap.this.link[this.curr];
            this.prev = this.curr;
            if (this.index >= 0) {
               ++this.index;
            }

            return this.curr;
         }
      }

      public int previousEntry() {
         if (!this.hasPrevious()) {
            throw new NoSuchElementException();
         } else {
            this.curr = this.prev;
            this.prev = (int)(Float2IntLinkedOpenHashMap.this.link[this.curr] >>> 32);
            this.next = this.curr;
            if (this.index >= 0) {
               --this.index;
            }

            return this.curr;
         }
      }

      public void forEachRemaining(Object action) {
         for(; this.hasNext(); this.acceptOnIndex(action, this.curr)) {
            this.curr = this.next;
            this.next = (int)Float2IntLinkedOpenHashMap.this.link[this.curr];
            this.prev = this.curr;
            if (this.index >= 0) {
               ++this.index;
            }
         }

      }

      public void remove() {
         this.ensureIndexKnown();
         if (this.curr == -1) {
            throw new IllegalStateException();
         } else {
            if (this.curr == this.prev) {
               --this.index;
               this.prev = (int)(Float2IntLinkedOpenHashMap.this.link[this.curr] >>> 32);
            } else {
               this.next = (int)Float2IntLinkedOpenHashMap.this.link[this.curr];
            }

            --Float2IntLinkedOpenHashMap.this.size;
            if (this.prev == -1) {
               Float2IntLinkedOpenHashMap.this.first = this.next;
            } else {
               long[] var6 = Float2IntLinkedOpenHashMap.this.link;
               int var10001 = this.prev;
               var6[var10001] ^= (Float2IntLinkedOpenHashMap.this.link[this.prev] ^ (long)this.next & 4294967295L) & 4294967295L;
            }

            if (this.next == -1) {
               Float2IntLinkedOpenHashMap.this.last = this.prev;
            } else {
               long[] var7 = Float2IntLinkedOpenHashMap.this.link;
               int var8 = this.next;
               var7[var8] ^= (Float2IntLinkedOpenHashMap.this.link[this.next] ^ ((long)this.prev & 4294967295L) << 32) & -4294967296L;
            }

            int pos = this.curr;
            this.curr = -1;
            if (pos == Float2IntLinkedOpenHashMap.this.n) {
               Float2IntLinkedOpenHashMap.this.containsNullKey = false;
            } else {
               float[] key = Float2IntLinkedOpenHashMap.this.key;

               while(true) {
                  int last = pos;
                  pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask;

                  float curr;
                  while(true) {
                     if (Float.floatToIntBits(curr = key[pos]) == 0) {
                        key[last] = 0.0F;
                        return;
                     }

                     int slot = HashCommon.mix(HashCommon.float2int(curr)) & Float2IntLinkedOpenHashMap.this.mask;
                     if (last <= pos) {
                        if (last >= slot || slot > pos) {
                           break;
                        }
                     } else if (last >= slot && slot > pos) {
                        break;
                     }

                     pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask;
                  }

                  key[last] = curr;
                  Float2IntLinkedOpenHashMap.this.value[last] = Float2IntLinkedOpenHashMap.this.value[pos];
                  if (this.next == pos) {
                     this.next = last;
                  }

                  if (this.prev == pos) {
                     this.prev = last;
                  }

                  Float2IntLinkedOpenHashMap.this.fixPointers(pos, last);
               }
            }
         }
      }

      public int skip(int n) {
         int i = n;

         while(i-- != 0 && this.hasNext()) {
            this.nextEntry();
         }

         return n - i - 1;
      }

      public int back(int n) {
         int i = n;

         while(i-- != 0 && this.hasPrevious()) {
            this.previousEntry();
         }

         return n - i - 1;
      }

      public void set(Float2IntMap.Entry ok) {
         throw new UnsupportedOperationException();
      }

      public void add(Float2IntMap.Entry ok) {
         throw new UnsupportedOperationException();
      }
   }

   private final class EntryIterator extends MapIterator implements ObjectListIterator {
      private MapEntry entry;

      public EntryIterator() {
      }

      public EntryIterator(float from) {
         super(from, null);
      }

      final void acceptOnIndex(Consumer action, int index) {
         action.accept(Float2IntLinkedOpenHashMap.this.new MapEntry(index));
      }

      public MapEntry next() {
         return this.entry = Float2IntLinkedOpenHashMap.this.new MapEntry(this.nextEntry());
      }

      public MapEntry previous() {
         return this.entry = Float2IntLinkedOpenHashMap.this.new MapEntry(this.previousEntry());
      }

      public void remove() {
         super.remove();
         this.entry.index = -1;
      }
   }

   private final class FastEntryIterator extends MapIterator implements ObjectListIterator {
      final MapEntry entry;

      public FastEntryIterator() {
         this.entry = Float2IntLinkedOpenHashMap.this.new MapEntry();
      }

      public FastEntryIterator(float from) {
         super(from, null);
         this.entry = Float2IntLinkedOpenHashMap.this.new MapEntry();
      }

      final void acceptOnIndex(Consumer action, int index) {
         this.entry.index = index;
         action.accept(this.entry);
      }

      public MapEntry next() {
         this.entry.index = this.nextEntry();
         return this.entry;
      }

      public MapEntry previous() {
         this.entry.index = this.previousEntry();
         return this.entry;
      }
   }

   private final class MapEntrySet extends AbstractObjectSortedSet implements Float2IntSortedMap.FastSortedEntrySet {
      private static final int SPLITERATOR_CHARACTERISTICS = 81;

      private MapEntrySet() {
      }

      public ObjectBidirectionalIterator iterator() {
         return Float2IntLinkedOpenHashMap.this.new EntryIterator();
      }

      public ObjectSpliterator spliterator() {
         return ObjectSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)Float2IntLinkedOpenHashMap.this), 81);
      }

      public Comparator comparator() {
         return null;
      }

      public ObjectSortedSet subSet(Float2IntMap.Entry fromElement, Float2IntMap.Entry toElement) {
         throw new UnsupportedOperationException();
      }

      public ObjectSortedSet headSet(Float2IntMap.Entry toElement) {
         throw new UnsupportedOperationException();
      }

      public ObjectSortedSet tailSet(Float2IntMap.Entry fromElement) {
         throw new UnsupportedOperationException();
      }

      public Float2IntMap.Entry first() {
         if (Float2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Float2IntLinkedOpenHashMap.this.new MapEntry(Float2IntLinkedOpenHashMap.this.first);
         }
      }

      public Float2IntMap.Entry last() {
         if (Float2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Float2IntLinkedOpenHashMap.this.new MapEntry(Float2IntLinkedOpenHashMap.this.last);
         }
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            if (e.getKey() != null && e.getKey() instanceof Float) {
               if (e.getValue() != null && e.getValue() instanceof Integer) {
                  float k = (Float)e.getKey();
                  int v = (Integer)e.getValue();
                  if (Float.floatToIntBits(k) == 0) {
                     return Float2IntLinkedOpenHashMap.this.containsNullKey && Float2IntLinkedOpenHashMap.this.value[Float2IntLinkedOpenHashMap.this.n] == v;
                  } else {
                     float[] key = Float2IntLinkedOpenHashMap.this.key;
                     float curr;
                     int pos;
                     if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & Float2IntLinkedOpenHashMap.this.mask]) == 0) {
                        return false;
                     } else if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                        return Float2IntLinkedOpenHashMap.this.value[pos] == v;
                     } else {
                        while(Float.floatToIntBits(curr = key[pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask]) != 0) {
                           if (Float.floatToIntBits(k) == Float.floatToIntBits(curr)) {
                              return Float2IntLinkedOpenHashMap.this.value[pos] == v;
                           }
                        }

                        return false;
                     }
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public boolean remove(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            if (e.getKey() != null && e.getKey() instanceof Float) {
               if (e.getValue() != null && e.getValue() instanceof Integer) {
                  float k = (Float)e.getKey();
                  int v = (Integer)e.getValue();
                  if (Float.floatToIntBits(k) == 0) {
                     if (Float2IntLinkedOpenHashMap.this.containsNullKey && Float2IntLinkedOpenHashMap.this.value[Float2IntLinkedOpenHashMap.this.n] == v) {
                        Float2IntLinkedOpenHashMap.this.removeNullEntry();
                        return true;
                     } else {
                        return false;
                     }
                  } else {
                     float[] key = Float2IntLinkedOpenHashMap.this.key;
                     float curr;
                     int pos;
                     if (Float.floatToIntBits(curr = key[pos = HashCommon.mix(HashCommon.float2int(k)) & Float2IntLinkedOpenHashMap.this.mask]) == 0) {
                        return false;
                     } else if (Float.floatToIntBits(curr) == Float.floatToIntBits(k)) {
                        if (Float2IntLinkedOpenHashMap.this.value[pos] == v) {
                           Float2IntLinkedOpenHashMap.this.removeEntry(pos);
                           return true;
                        } else {
                           return false;
                        }
                     } else {
                        while(Float.floatToIntBits(curr = key[pos = pos + 1 & Float2IntLinkedOpenHashMap.this.mask]) != 0) {
                           if (Float.floatToIntBits(curr) == Float.floatToIntBits(k) && Float2IntLinkedOpenHashMap.this.value[pos] == v) {
                              Float2IntLinkedOpenHashMap.this.removeEntry(pos);
                              return true;
                           }
                        }

                        return false;
                     }
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }

      public int size() {
         return Float2IntLinkedOpenHashMap.this.size;
      }

      public void clear() {
         Float2IntLinkedOpenHashMap.this.clear();
      }

      public ObjectListIterator iterator(Float2IntMap.Entry from) {
         return Float2IntLinkedOpenHashMap.this.new EntryIterator(from.getFloatKey());
      }

      public ObjectListIterator fastIterator() {
         return Float2IntLinkedOpenHashMap.this.new FastEntryIterator();
      }

      public ObjectListIterator fastIterator(Float2IntMap.Entry from) {
         return Float2IntLinkedOpenHashMap.this.new FastEntryIterator(from.getFloatKey());
      }

      public void forEach(Consumer consumer) {
         int i = Float2IntLinkedOpenHashMap.this.size;
         int next = Float2IntLinkedOpenHashMap.this.first;

         while(i-- != 0) {
            int curr = next;
            next = (int)Float2IntLinkedOpenHashMap.this.link[next];
            consumer.accept(Float2IntLinkedOpenHashMap.this.new MapEntry(curr));
         }

      }

      public void fastForEach(Consumer consumer) {
         MapEntry entry = Float2IntLinkedOpenHashMap.this.new MapEntry();
         int i = Float2IntLinkedOpenHashMap.this.size;
         int next = Float2IntLinkedOpenHashMap.this.first;

         while(i-- != 0) {
            entry.index = next;
            next = (int)Float2IntLinkedOpenHashMap.this.link[next];
            consumer.accept(entry);
         }

      }
   }

   private final class KeyIterator extends MapIterator implements FloatListIterator {
      public KeyIterator(final float k) {
         super(k, null);
      }

      public float previousFloat() {
         return Float2IntLinkedOpenHashMap.this.key[this.previousEntry()];
      }

      public KeyIterator() {
      }

      final void acceptOnIndex(FloatConsumer action, int index) {
         action.accept(Float2IntLinkedOpenHashMap.this.key[index]);
      }

      public float nextFloat() {
         return Float2IntLinkedOpenHashMap.this.key[this.nextEntry()];
      }
   }

   private final class KeySet extends AbstractFloatSortedSet {
      private static final int SPLITERATOR_CHARACTERISTICS = 337;

      private KeySet() {
      }

      public FloatListIterator iterator(float from) {
         return Float2IntLinkedOpenHashMap.this.new KeyIterator(from);
      }

      public FloatListIterator iterator() {
         return Float2IntLinkedOpenHashMap.this.new KeyIterator();
      }

      public FloatSpliterator spliterator() {
         return FloatSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)Float2IntLinkedOpenHashMap.this), 337);
      }

      public void forEach(FloatConsumer consumer) {
         int i = Float2IntLinkedOpenHashMap.this.size;
         int next = Float2IntLinkedOpenHashMap.this.first;

         while(i-- != 0) {
            int curr = next;
            next = (int)Float2IntLinkedOpenHashMap.this.link[next];
            consumer.accept(Float2IntLinkedOpenHashMap.this.key[curr]);
         }

      }

      public int size() {
         return Float2IntLinkedOpenHashMap.this.size;
      }

      public boolean contains(float k) {
         return Float2IntLinkedOpenHashMap.this.containsKey(k);
      }

      public boolean remove(float k) {
         int oldSize = Float2IntLinkedOpenHashMap.this.size;
         Float2IntLinkedOpenHashMap.this.remove(k);
         return Float2IntLinkedOpenHashMap.this.size != oldSize;
      }

      public void clear() {
         Float2IntLinkedOpenHashMap.this.clear();
      }

      public float firstFloat() {
         if (Float2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Float2IntLinkedOpenHashMap.this.key[Float2IntLinkedOpenHashMap.this.first];
         }
      }

      public float lastFloat() {
         if (Float2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Float2IntLinkedOpenHashMap.this.key[Float2IntLinkedOpenHashMap.this.last];
         }
      }

      public FloatComparator comparator() {
         return null;
      }

      public FloatSortedSet tailSet(float from) {
         throw new UnsupportedOperationException();
      }

      public FloatSortedSet headSet(float to) {
         throw new UnsupportedOperationException();
      }

      public FloatSortedSet subSet(float from, float to) {
         throw new UnsupportedOperationException();
      }
   }

   private final class ValueIterator extends MapIterator implements IntListIterator {
      public int previousInt() {
         return Float2IntLinkedOpenHashMap.this.value[this.previousEntry()];
      }

      public ValueIterator() {
      }

      final void acceptOnIndex(IntConsumer action, int index) {
         action.accept(Float2IntLinkedOpenHashMap.this.value[index]);
      }

      public int nextInt() {
         return Float2IntLinkedOpenHashMap.this.value[this.nextEntry()];
      }
   }
}
