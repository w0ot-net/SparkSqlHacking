package shaded.parquet.it.unimi.dsi.fastutil.objects;

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
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import shaded.parquet.it.unimi.dsi.fastutil.Hash;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;
import shaded.parquet.it.unimi.dsi.fastutil.Size64;
import shaded.parquet.it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntCollection;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntIterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterator;
import shaded.parquet.it.unimi.dsi.fastutil.ints.IntSpliterators;

public class Object2IntLinkedOpenHashMap extends AbstractObject2IntSortedMap implements Serializable, Cloneable, Hash {
   private static final long serialVersionUID = 0L;
   private static final boolean ASSERTS = false;
   protected transient Object[] key;
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
   protected transient Object2IntSortedMap.FastSortedEntrySet entries;
   protected transient ObjectSortedSet keys;
   protected transient IntCollection values;

   public Object2IntLinkedOpenHashMap(int expected, float f) {
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
            this.key = new Object[this.n + 1];
            this.value = new int[this.n + 1];
            this.link = new long[this.n + 1];
         }
      } else {
         throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than 1");
      }
   }

   public Object2IntLinkedOpenHashMap(int expected) {
      this(expected, 0.75F);
   }

   public Object2IntLinkedOpenHashMap() {
      this(16, 0.75F);
   }

   public Object2IntLinkedOpenHashMap(Map m, float f) {
      this(m.size(), f);
      this.putAll(m);
   }

   public Object2IntLinkedOpenHashMap(Map m) {
      this(m, 0.75F);
   }

   public Object2IntLinkedOpenHashMap(Object2IntMap m, float f) {
      this(m.size(), f);
      this.putAll(m);
   }

   public Object2IntLinkedOpenHashMap(Object2IntMap m) {
      this(m, 0.75F);
   }

   public Object2IntLinkedOpenHashMap(Object[] k, int[] v, float f) {
      this(k.length, f);
      if (k.length != v.length) {
         throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
      } else {
         for(int i = 0; i < k.length; ++i) {
            this.put(k[i], v[i]);
         }

      }
   }

   public Object2IntLinkedOpenHashMap(Object[] k, int[] v) {
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
      this.key[this.n] = null;
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

   private int find(Object k) {
      if (k == null) {
         return this.containsNullKey ? this.n : -(this.n + 1);
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return -(pos + 1);
         } else if (k.equals(curr)) {
            return pos;
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  return pos;
               }
            }

            return -(pos + 1);
         }
      }
   }

   private void insert(int pos, Object k, int v) {
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

   public int put(Object k, int v) {
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

   public int addTo(Object k, int incr) {
      int pos;
      if (k == null) {
         if (this.containsNullKey) {
            return this.addToValue(this.n, incr);
         }

         pos = this.n;
         this.containsNullKey = true;
      } else {
         K[] key = (K[])this.key;
         K curr;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) != null) {
            if (curr.equals(k)) {
               return this.addToValue(pos, incr);
            }

            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (curr.equals(k)) {
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
      K[] key = (K[])this.key;

      while(true) {
         int last = pos;
         pos = pos + 1 & this.mask;

         K curr;
         while(true) {
            if ((curr = (K)key[pos]) == null) {
               key[last] = null;
               return;
            }

            int slot = HashCommon.mix(curr.hashCode()) & this.mask;
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

   public int removeInt(Object k) {
      if (k == null) {
         return this.containsNullKey ? this.removeNullEntry() : this.defRetValue;
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return this.defRetValue;
         } else if (k.equals(curr)) {
            return this.removeEntry(pos);
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
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
            this.key[this.n] = null;
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
            this.key[this.n] = null;
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

   public int getAndMoveToFirst(Object k) {
      if (k == null) {
         if (this.containsNullKey) {
            this.moveIndexToFirst(this.n);
            return this.value[this.n];
         } else {
            return this.defRetValue;
         }
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return this.defRetValue;
         } else if (k.equals(curr)) {
            this.moveIndexToFirst(pos);
            return this.value[pos];
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  this.moveIndexToFirst(pos);
                  return this.value[pos];
               }
            }

            return this.defRetValue;
         }
      }
   }

   public int getAndMoveToLast(Object k) {
      if (k == null) {
         if (this.containsNullKey) {
            this.moveIndexToLast(this.n);
            return this.value[this.n];
         } else {
            return this.defRetValue;
         }
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return this.defRetValue;
         } else if (k.equals(curr)) {
            this.moveIndexToLast(pos);
            return this.value[pos];
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  this.moveIndexToLast(pos);
                  return this.value[pos];
               }
            }

            return this.defRetValue;
         }
      }
   }

   public int putAndMoveToFirst(Object k, int v) {
      int pos;
      if (k == null) {
         if (this.containsNullKey) {
            this.moveIndexToFirst(this.n);
            return this.setValue(this.n, v);
         }

         this.containsNullKey = true;
         pos = this.n;
      } else {
         K[] key = (K[])this.key;
         K curr;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) != null) {
            if (curr.equals(k)) {
               this.moveIndexToFirst(pos);
               return this.setValue(pos, v);
            }

            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (curr.equals(k)) {
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

   public int putAndMoveToLast(Object k, int v) {
      int pos;
      if (k == null) {
         if (this.containsNullKey) {
            this.moveIndexToLast(this.n);
            return this.setValue(this.n, v);
         }

         this.containsNullKey = true;
         pos = this.n;
      } else {
         K[] key = (K[])this.key;
         K curr;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) != null) {
            if (curr.equals(k)) {
               this.moveIndexToLast(pos);
               return this.setValue(pos, v);
            }

            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (curr.equals(k)) {
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

   public int getInt(Object k) {
      if (k == null) {
         return this.containsNullKey ? this.value[this.n] : this.defRetValue;
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return this.defRetValue;
         } else if (k.equals(curr)) {
            return this.value[pos];
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  return this.value[pos];
               }
            }

            return this.defRetValue;
         }
      }
   }

   public boolean containsKey(Object k) {
      if (k == null) {
         return this.containsNullKey;
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return false;
         } else if (k.equals(curr)) {
            return true;
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean containsValue(int v) {
      int[] value = this.value;
      K[] key = (K[])this.key;
      if (this.containsNullKey && value[this.n] == v) {
         return true;
      } else {
         int i = this.n;

         while(i-- != 0) {
            if (key[i] != null && value[i] == v) {
               return true;
            }
         }

         return false;
      }
   }

   public int getOrDefault(Object k, int defaultValue) {
      if (k == null) {
         return this.containsNullKey ? this.value[this.n] : defaultValue;
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return defaultValue;
         } else if (k.equals(curr)) {
            return this.value[pos];
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  return this.value[pos];
               }
            }

            return defaultValue;
         }
      }
   }

   public int putIfAbsent(Object k, int v) {
      int pos = this.find(k);
      if (pos >= 0) {
         return this.value[pos];
      } else {
         this.insert(-pos - 1, k, v);
         return this.defRetValue;
      }
   }

   public boolean remove(Object k, int v) {
      if (k == null) {
         if (this.containsNullKey && v == this.value[this.n]) {
            this.removeNullEntry();
            return true;
         } else {
            return false;
         }
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return false;
         } else if (k.equals(curr) && v == this.value[pos]) {
            this.removeEntry(pos);
            return true;
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr) && v == this.value[pos]) {
                  this.removeEntry(pos);
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean replace(Object k, int oldValue, int v) {
      int pos = this.find(k);
      if (pos >= 0 && oldValue == this.value[pos]) {
         this.value[pos] = v;
         return true;
      } else {
         return false;
      }
   }

   public int replace(Object k, int v) {
      int pos = this.find(k);
      if (pos < 0) {
         return this.defRetValue;
      } else {
         int oldValue = this.value[pos];
         this.value[pos] = v;
         return oldValue;
      }
   }

   public int computeIfAbsent(Object k, ToIntFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int pos = this.find(k);
      if (pos >= 0) {
         return this.value[pos];
      } else {
         int newValue = mappingFunction.applyAsInt(k);
         this.insert(-pos - 1, k, newValue);
         return newValue;
      }
   }

   public int computeIfAbsent(Object key, Object2IntFunction mappingFunction) {
      Objects.requireNonNull(mappingFunction);
      int pos = this.find(key);
      if (pos >= 0) {
         return this.value[pos];
      } else if (!mappingFunction.containsKey(key)) {
         return this.defRetValue;
      } else {
         int newValue = mappingFunction.getInt(key);
         this.insert(-pos - 1, key, newValue);
         return newValue;
      }
   }

   public int computeIntIfPresent(Object k, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int pos = this.find(k);
      if (pos < 0) {
         return this.defRetValue;
      } else {
         Integer newValue = (Integer)remappingFunction.apply(k, this.value[pos]);
         if (newValue == null) {
            if (k == null) {
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

   public int computeInt(Object k, BiFunction remappingFunction) {
      Objects.requireNonNull(remappingFunction);
      int pos = this.find(k);
      Integer newValue = (Integer)remappingFunction.apply(k, pos >= 0 ? this.value[pos] : null);
      if (newValue == null) {
         if (pos >= 0) {
            if (k == null) {
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

   public int merge(Object k, int v, BiFunction remappingFunction) {
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
            if (k == null) {
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
         Arrays.fill(this.key, (Object)null);
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

   public Object firstKey() {
      if (this.size == 0) {
         throw new NoSuchElementException();
      } else {
         return this.key[this.first];
      }
   }

   public Object lastKey() {
      if (this.size == 0) {
         throw new NoSuchElementException();
      } else {
         return this.key[this.last];
      }
   }

   public Object2IntSortedMap tailMap(Object from) {
      throw new UnsupportedOperationException();
   }

   public Object2IntSortedMap headMap(Object to) {
      throw new UnsupportedOperationException();
   }

   public Object2IntSortedMap subMap(Object from, Object to) {
      throw new UnsupportedOperationException();
   }

   public Comparator comparator() {
      return null;
   }

   public Object2IntSortedMap.FastSortedEntrySet object2IntEntrySet() {
      if (this.entries == null) {
         this.entries = new MapEntrySet();
      }

      return this.entries;
   }

   public ObjectSortedSet keySet() {
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
               return Object2IntLinkedOpenHashMap.this.new ValueIterator();
            }

            public IntSpliterator spliterator() {
               return IntSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)Object2IntLinkedOpenHashMap.this), 336);
            }

            public void forEach(IntConsumer consumer) {
               int i = Object2IntLinkedOpenHashMap.this.size;
               int next = Object2IntLinkedOpenHashMap.this.first;

               while(i-- != 0) {
                  int curr = next;
                  next = (int)Object2IntLinkedOpenHashMap.this.link[next];
                  consumer.accept(Object2IntLinkedOpenHashMap.this.value[curr]);
               }

            }

            public int size() {
               return Object2IntLinkedOpenHashMap.this.size;
            }

            public boolean contains(int v) {
               return Object2IntLinkedOpenHashMap.this.containsValue(v);
            }

            public void clear() {
               Object2IntLinkedOpenHashMap.this.clear();
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
      K[] key = (K[])this.key;
      int[] value = this.value;
      int mask = newN - 1;
      K[] newKey = (K[])(new Object[newN + 1]);
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
         if (key[i] == null) {
            pos = newN;
         } else {
            for(pos = HashCommon.mix(key[i].hashCode()) & mask; newKey[pos] != null; pos = pos + 1 & mask) {
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

   public Object2IntLinkedOpenHashMap clone() {
      Object2IntLinkedOpenHashMap<K> c;
      try {
         c = (Object2IntLinkedOpenHashMap)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      c.keys = null;
      c.values = null;
      c.entries = null;
      c.containsNullKey = this.containsNullKey;
      c.key = this.key.clone();
      c.value = (int[])this.value.clone();
      c.link = (long[])this.link.clone();
      return c;
   }

   public int hashCode() {
      int h = 0;
      int j = this.realSize();
      int i = 0;

      for(int t = 0; j-- != 0; ++i) {
         while(this.key[i] == null) {
            ++i;
         }

         if (this != this.key[i]) {
            t = this.key[i].hashCode();
         }

         t ^= this.value[i];
         h += t;
      }

      if (this.containsNullKey) {
         h += this.value[this.n];
      }

      return h;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      K[] key = (K[])this.key;
      int[] value = this.value;
      Object2IntLinkedOpenHashMap<K>.EntryIterator i = new EntryIterator();
      s.defaultWriteObject();
      int j = this.size;

      while(j-- != 0) {
         int e = i.nextEntry();
         s.writeObject(key[e]);
         s.writeInt(value[e]);
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.n = HashCommon.arraySize(this.size, this.f);
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.mask = this.n - 1;
      K[] key = (K[])(this.key = new Object[this.n + 1]);
      int[] value = this.value = new int[this.n + 1];
      long[] link = this.link = new long[this.n + 1];
      int prev = -1;
      this.first = this.last = -1;
      int i = this.size;

      while(i-- != 0) {
         K k = (K)s.readObject();
         int v = s.readInt();
         int pos;
         if (k == null) {
            pos = this.n;
            this.containsNullKey = true;
         } else {
            for(pos = HashCommon.mix(k.hashCode()) & this.mask; key[pos] != null; pos = pos + 1 & this.mask) {
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

   final class MapEntry implements Object2IntMap.Entry, Map.Entry, ObjectIntPair {
      int index;

      MapEntry(final int index) {
         this.index = index;
      }

      MapEntry() {
      }

      public Object getKey() {
         return Object2IntLinkedOpenHashMap.this.key[this.index];
      }

      public Object left() {
         return Object2IntLinkedOpenHashMap.this.key[this.index];
      }

      public int getIntValue() {
         return Object2IntLinkedOpenHashMap.this.value[this.index];
      }

      public int rightInt() {
         return Object2IntLinkedOpenHashMap.this.value[this.index];
      }

      public int setValue(int v) {
         int oldValue = Object2IntLinkedOpenHashMap.this.value[this.index];
         Object2IntLinkedOpenHashMap.this.value[this.index] = v;
         return oldValue;
      }

      public ObjectIntPair right(int v) {
         Object2IntLinkedOpenHashMap.this.value[this.index] = v;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Integer getValue() {
         return Object2IntLinkedOpenHashMap.this.value[this.index];
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
            Map.Entry<K, Integer> e = (Map.Entry)o;
            return Objects.equals(Object2IntLinkedOpenHashMap.this.key[this.index], e.getKey()) && Object2IntLinkedOpenHashMap.this.value[this.index] == (Integer)e.getValue();
         }
      }

      public int hashCode() {
         return (Object2IntLinkedOpenHashMap.this.key[this.index] == null ? 0 : Object2IntLinkedOpenHashMap.this.key[this.index].hashCode()) ^ Object2IntLinkedOpenHashMap.this.value[this.index];
      }

      public String toString() {
         return Object2IntLinkedOpenHashMap.this.key[this.index] + "=>" + Object2IntLinkedOpenHashMap.this.value[this.index];
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
         this.next = Object2IntLinkedOpenHashMap.this.first;
         this.index = 0;
      }

      private MapIterator(final Object from) {
         this.prev = -1;
         this.next = -1;
         this.curr = -1;
         this.index = -1;
         if (from == null) {
            if (Object2IntLinkedOpenHashMap.this.containsNullKey) {
               this.next = (int)Object2IntLinkedOpenHashMap.this.link[Object2IntLinkedOpenHashMap.this.n];
               this.prev = Object2IntLinkedOpenHashMap.this.n;
            } else {
               throw new NoSuchElementException("The key " + from + " does not belong to this map.");
            }
         } else if (Objects.equals(Object2IntLinkedOpenHashMap.this.key[Object2IntLinkedOpenHashMap.this.last], from)) {
            this.prev = Object2IntLinkedOpenHashMap.this.last;
            this.index = Object2IntLinkedOpenHashMap.this.size;
         } else {
            for(int pos = HashCommon.mix(from.hashCode()) & Object2IntLinkedOpenHashMap.this.mask; Object2IntLinkedOpenHashMap.this.key[pos] != null; pos = pos + 1 & Object2IntLinkedOpenHashMap.this.mask) {
               if (Object2IntLinkedOpenHashMap.this.key[pos].equals(from)) {
                  this.next = (int)Object2IntLinkedOpenHashMap.this.link[pos];
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
               this.index = Object2IntLinkedOpenHashMap.this.size;
            } else {
               int pos = Object2IntLinkedOpenHashMap.this.first;

               for(this.index = 1; pos != this.prev; ++this.index) {
                  pos = (int)Object2IntLinkedOpenHashMap.this.link[pos];
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
            this.next = (int)Object2IntLinkedOpenHashMap.this.link[this.curr];
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
            this.prev = (int)(Object2IntLinkedOpenHashMap.this.link[this.curr] >>> 32);
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
            this.next = (int)Object2IntLinkedOpenHashMap.this.link[this.curr];
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
               this.prev = (int)(Object2IntLinkedOpenHashMap.this.link[this.curr] >>> 32);
            } else {
               this.next = (int)Object2IntLinkedOpenHashMap.this.link[this.curr];
            }

            --Object2IntLinkedOpenHashMap.this.size;
            if (this.prev == -1) {
               Object2IntLinkedOpenHashMap.this.first = this.next;
            } else {
               long[] var6 = Object2IntLinkedOpenHashMap.this.link;
               int var10001 = this.prev;
               var6[var10001] ^= (Object2IntLinkedOpenHashMap.this.link[this.prev] ^ (long)this.next & 4294967295L) & 4294967295L;
            }

            if (this.next == -1) {
               Object2IntLinkedOpenHashMap.this.last = this.prev;
            } else {
               long[] var7 = Object2IntLinkedOpenHashMap.this.link;
               int var8 = this.next;
               var7[var8] ^= (Object2IntLinkedOpenHashMap.this.link[this.next] ^ ((long)this.prev & 4294967295L) << 32) & -4294967296L;
            }

            int pos = this.curr;
            this.curr = -1;
            if (pos == Object2IntLinkedOpenHashMap.this.n) {
               Object2IntLinkedOpenHashMap.this.containsNullKey = false;
               Object2IntLinkedOpenHashMap.this.key[Object2IntLinkedOpenHashMap.this.n] = null;
            } else {
               K[] key = (K[])Object2IntLinkedOpenHashMap.this.key;

               while(true) {
                  int last = pos;
                  pos = pos + 1 & Object2IntLinkedOpenHashMap.this.mask;

                  K curr;
                  while(true) {
                     if ((curr = (K)key[pos]) == null) {
                        key[last] = null;
                        return;
                     }

                     int slot = HashCommon.mix(curr.hashCode()) & Object2IntLinkedOpenHashMap.this.mask;
                     if (last <= pos) {
                        if (last >= slot || slot > pos) {
                           break;
                        }
                     } else if (last >= slot && slot > pos) {
                        break;
                     }

                     pos = pos + 1 & Object2IntLinkedOpenHashMap.this.mask;
                  }

                  key[last] = curr;
                  Object2IntLinkedOpenHashMap.this.value[last] = Object2IntLinkedOpenHashMap.this.value[pos];
                  if (this.next == pos) {
                     this.next = last;
                  }

                  if (this.prev == pos) {
                     this.prev = last;
                  }

                  Object2IntLinkedOpenHashMap.this.fixPointers(pos, last);
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

      public void set(Object2IntMap.Entry ok) {
         throw new UnsupportedOperationException();
      }

      public void add(Object2IntMap.Entry ok) {
         throw new UnsupportedOperationException();
      }
   }

   private final class EntryIterator extends MapIterator implements ObjectListIterator {
      private MapEntry entry;

      public EntryIterator() {
      }

      public EntryIterator(Object from) {
         super(from, null);
      }

      final void acceptOnIndex(Consumer action, int index) {
         action.accept(Object2IntLinkedOpenHashMap.this.new MapEntry(index));
      }

      public MapEntry next() {
         return this.entry = Object2IntLinkedOpenHashMap.this.new MapEntry(this.nextEntry());
      }

      public MapEntry previous() {
         return this.entry = Object2IntLinkedOpenHashMap.this.new MapEntry(this.previousEntry());
      }

      public void remove() {
         super.remove();
         this.entry.index = -1;
      }
   }

   private final class FastEntryIterator extends MapIterator implements ObjectListIterator {
      final MapEntry entry;

      public FastEntryIterator() {
         this.entry = Object2IntLinkedOpenHashMap.this.new MapEntry();
      }

      public FastEntryIterator(Object from) {
         super(from, null);
         this.entry = Object2IntLinkedOpenHashMap.this.new MapEntry();
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

   private final class MapEntrySet extends AbstractObjectSortedSet implements Object2IntSortedMap.FastSortedEntrySet {
      private static final int SPLITERATOR_CHARACTERISTICS = 81;

      private MapEntrySet() {
      }

      public ObjectBidirectionalIterator iterator() {
         return Object2IntLinkedOpenHashMap.this.new EntryIterator();
      }

      public ObjectSpliterator spliterator() {
         return ObjectSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)Object2IntLinkedOpenHashMap.this), 81);
      }

      public Comparator comparator() {
         return null;
      }

      public ObjectSortedSet subSet(Object2IntMap.Entry fromElement, Object2IntMap.Entry toElement) {
         throw new UnsupportedOperationException();
      }

      public ObjectSortedSet headSet(Object2IntMap.Entry toElement) {
         throw new UnsupportedOperationException();
      }

      public ObjectSortedSet tailSet(Object2IntMap.Entry fromElement) {
         throw new UnsupportedOperationException();
      }

      public Object2IntMap.Entry first() {
         if (Object2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Object2IntLinkedOpenHashMap.this.new MapEntry(Object2IntLinkedOpenHashMap.this.first);
         }
      }

      public Object2IntMap.Entry last() {
         if (Object2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Object2IntLinkedOpenHashMap.this.new MapEntry(Object2IntLinkedOpenHashMap.this.last);
         }
      }

      public boolean contains(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)o;
            if (e.getValue() != null && e.getValue() instanceof Integer) {
               K k = (K)e.getKey();
               int v = (Integer)e.getValue();
               if (k == null) {
                  return Object2IntLinkedOpenHashMap.this.containsNullKey && Object2IntLinkedOpenHashMap.this.value[Object2IntLinkedOpenHashMap.this.n] == v;
               } else {
                  K[] key = (K[])Object2IntLinkedOpenHashMap.this.key;
                  K curr;
                  int pos;
                  if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & Object2IntLinkedOpenHashMap.this.mask]) == null) {
                     return false;
                  } else if (k.equals(curr)) {
                     return Object2IntLinkedOpenHashMap.this.value[pos] == v;
                  } else {
                     while((curr = (K)key[pos = pos + 1 & Object2IntLinkedOpenHashMap.this.mask]) != null) {
                        if (k.equals(curr)) {
                           return Object2IntLinkedOpenHashMap.this.value[pos] == v;
                        }
                     }

                     return false;
                  }
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
            if (e.getValue() != null && e.getValue() instanceof Integer) {
               K k = (K)e.getKey();
               int v = (Integer)e.getValue();
               if (k == null) {
                  if (Object2IntLinkedOpenHashMap.this.containsNullKey && Object2IntLinkedOpenHashMap.this.value[Object2IntLinkedOpenHashMap.this.n] == v) {
                     Object2IntLinkedOpenHashMap.this.removeNullEntry();
                     return true;
                  } else {
                     return false;
                  }
               } else {
                  K[] key = (K[])Object2IntLinkedOpenHashMap.this.key;
                  K curr;
                  int pos;
                  if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & Object2IntLinkedOpenHashMap.this.mask]) == null) {
                     return false;
                  } else if (curr.equals(k)) {
                     if (Object2IntLinkedOpenHashMap.this.value[pos] == v) {
                        Object2IntLinkedOpenHashMap.this.removeEntry(pos);
                        return true;
                     } else {
                        return false;
                     }
                  } else {
                     while((curr = (K)key[pos = pos + 1 & Object2IntLinkedOpenHashMap.this.mask]) != null) {
                        if (curr.equals(k) && Object2IntLinkedOpenHashMap.this.value[pos] == v) {
                           Object2IntLinkedOpenHashMap.this.removeEntry(pos);
                           return true;
                        }
                     }

                     return false;
                  }
               }
            } else {
               return false;
            }
         }
      }

      public int size() {
         return Object2IntLinkedOpenHashMap.this.size;
      }

      public void clear() {
         Object2IntLinkedOpenHashMap.this.clear();
      }

      public ObjectListIterator iterator(Object2IntMap.Entry from) {
         return Object2IntLinkedOpenHashMap.this.new EntryIterator(from.getKey());
      }

      public ObjectListIterator fastIterator() {
         return Object2IntLinkedOpenHashMap.this.new FastEntryIterator();
      }

      public ObjectListIterator fastIterator(Object2IntMap.Entry from) {
         return Object2IntLinkedOpenHashMap.this.new FastEntryIterator(from.getKey());
      }

      public void forEach(Consumer consumer) {
         int i = Object2IntLinkedOpenHashMap.this.size;
         int next = Object2IntLinkedOpenHashMap.this.first;

         while(i-- != 0) {
            int curr = next;
            next = (int)Object2IntLinkedOpenHashMap.this.link[next];
            consumer.accept(Object2IntLinkedOpenHashMap.this.new MapEntry(curr));
         }

      }

      public void fastForEach(Consumer consumer) {
         Object2IntLinkedOpenHashMap<K>.MapEntry entry = Object2IntLinkedOpenHashMap.this.new MapEntry();
         int i = Object2IntLinkedOpenHashMap.this.size;
         int next = Object2IntLinkedOpenHashMap.this.first;

         while(i-- != 0) {
            entry.index = next;
            next = (int)Object2IntLinkedOpenHashMap.this.link[next];
            consumer.accept(entry);
         }

      }
   }

   private final class KeyIterator extends MapIterator implements ObjectListIterator {
      public KeyIterator(final Object k) {
         super(k, null);
      }

      public Object previous() {
         return Object2IntLinkedOpenHashMap.this.key[this.previousEntry()];
      }

      public KeyIterator() {
      }

      final void acceptOnIndex(Consumer action, int index) {
         action.accept(Object2IntLinkedOpenHashMap.this.key[index]);
      }

      public Object next() {
         return Object2IntLinkedOpenHashMap.this.key[this.nextEntry()];
      }
   }

   private final class KeySet extends AbstractObjectSortedSet {
      private static final int SPLITERATOR_CHARACTERISTICS = 81;

      private KeySet() {
      }

      public ObjectListIterator iterator(Object from) {
         return Object2IntLinkedOpenHashMap.this.new KeyIterator(from);
      }

      public ObjectListIterator iterator() {
         return Object2IntLinkedOpenHashMap.this.new KeyIterator();
      }

      public ObjectSpliterator spliterator() {
         return ObjectSpliterators.asSpliterator(this.iterator(), Size64.sizeOf((Map)Object2IntLinkedOpenHashMap.this), 81);
      }

      public void forEach(Consumer consumer) {
         int i = Object2IntLinkedOpenHashMap.this.size;
         int next = Object2IntLinkedOpenHashMap.this.first;

         while(i-- != 0) {
            int curr = next;
            next = (int)Object2IntLinkedOpenHashMap.this.link[next];
            consumer.accept(Object2IntLinkedOpenHashMap.this.key[curr]);
         }

      }

      public int size() {
         return Object2IntLinkedOpenHashMap.this.size;
      }

      public boolean contains(Object k) {
         return Object2IntLinkedOpenHashMap.this.containsKey(k);
      }

      public boolean remove(Object k) {
         int oldSize = Object2IntLinkedOpenHashMap.this.size;
         Object2IntLinkedOpenHashMap.this.removeInt(k);
         return Object2IntLinkedOpenHashMap.this.size != oldSize;
      }

      public void clear() {
         Object2IntLinkedOpenHashMap.this.clear();
      }

      public Object first() {
         if (Object2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Object2IntLinkedOpenHashMap.this.key[Object2IntLinkedOpenHashMap.this.first];
         }
      }

      public Object last() {
         if (Object2IntLinkedOpenHashMap.this.size == 0) {
            throw new NoSuchElementException();
         } else {
            return Object2IntLinkedOpenHashMap.this.key[Object2IntLinkedOpenHashMap.this.last];
         }
      }

      public Comparator comparator() {
         return null;
      }

      public ObjectSortedSet tailSet(Object from) {
         throw new UnsupportedOperationException();
      }

      public ObjectSortedSet headSet(Object to) {
         throw new UnsupportedOperationException();
      }

      public ObjectSortedSet subSet(Object from, Object to) {
         throw new UnsupportedOperationException();
      }
   }

   private final class ValueIterator extends MapIterator implements IntListIterator {
      public int previousInt() {
         return Object2IntLinkedOpenHashMap.this.value[this.previousEntry()];
      }

      public ValueIterator() {
      }

      final void acceptOnIndex(IntConsumer action, int index) {
         action.accept(Object2IntLinkedOpenHashMap.this.value[index]);
      }

      public int nextInt() {
         return Object2IntLinkedOpenHashMap.this.value[this.nextEntry()];
      }
   }
}
