package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import shaded.parquet.it.unimi.dsi.fastutil.Hash;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;

public class ObjectOpenHashSet extends AbstractObjectSet implements Serializable, Cloneable, Hash {
   private static final long serialVersionUID = 0L;
   private static final boolean ASSERTS = false;
   protected transient Object[] key;
   protected transient int mask;
   protected transient boolean containsNull;
   protected transient int n;
   protected transient int maxFill;
   protected final transient int minN;
   protected int size;
   protected final float f;
   private static final Collector TO_SET_COLLECTOR;

   public ObjectOpenHashSet(int expected, float f) {
      if (!(f <= 0.0F) && !(f >= 1.0F)) {
         if (expected < 0) {
            throw new IllegalArgumentException("The expected number of elements must be nonnegative");
         } else {
            this.f = f;
            this.minN = this.n = HashCommon.arraySize(expected, f);
            this.mask = this.n - 1;
            this.maxFill = HashCommon.maxFill(this.n, f);
            this.key = new Object[this.n + 1];
         }
      } else {
         throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than 1");
      }
   }

   public ObjectOpenHashSet(int expected) {
      this(expected, 0.75F);
   }

   public ObjectOpenHashSet() {
      this(16, 0.75F);
   }

   public ObjectOpenHashSet(Collection c, float f) {
      this(c.size(), f);
      this.addAll(c);
   }

   public ObjectOpenHashSet(Collection c) {
      this(c, 0.75F);
   }

   public ObjectOpenHashSet(ObjectCollection c, float f) {
      this(c.size(), f);
      this.addAll(c);
   }

   public ObjectOpenHashSet(ObjectCollection c) {
      this(c, 0.75F);
   }

   public ObjectOpenHashSet(Iterator i, float f) {
      this(16, f);

      while(i.hasNext()) {
         this.add(i.next());
      }

   }

   public ObjectOpenHashSet(Iterator i) {
      this(i, 0.75F);
   }

   public ObjectOpenHashSet(Object[] a, int offset, int length, float f) {
      this(length < 0 ? 0 : length, f);
      ObjectArrays.ensureOffsetLength(a, offset, length);

      for(int i = 0; i < length; ++i) {
         this.add(a[offset + i]);
      }

   }

   public ObjectOpenHashSet(Object[] a, int offset, int length) {
      this(a, offset, length, 0.75F);
   }

   public ObjectOpenHashSet(Object[] a, float f) {
      this(a, 0, a.length, f);
   }

   public ObjectOpenHashSet(Object[] a) {
      this(a, 0.75F);
   }

   public static ObjectOpenHashSet of() {
      return new ObjectOpenHashSet();
   }

   public static ObjectOpenHashSet of(Object e) {
      ObjectOpenHashSet<K> result = new ObjectOpenHashSet(1, 0.75F);
      result.add(e);
      return result;
   }

   public static ObjectOpenHashSet of(Object e0, Object e1) {
      ObjectOpenHashSet<K> result = new ObjectOpenHashSet(2, 0.75F);
      result.add(e0);
      if (!result.add(e1)) {
         throw new IllegalArgumentException("Duplicate element: " + e1);
      } else {
         return result;
      }
   }

   public static ObjectOpenHashSet of(Object e0, Object e1, Object e2) {
      ObjectOpenHashSet<K> result = new ObjectOpenHashSet(3, 0.75F);
      result.add(e0);
      if (!result.add(e1)) {
         throw new IllegalArgumentException("Duplicate element: " + e1);
      } else if (!result.add(e2)) {
         throw new IllegalArgumentException("Duplicate element: " + e2);
      } else {
         return result;
      }
   }

   @SafeVarargs
   public static ObjectOpenHashSet of(Object... a) {
      ObjectOpenHashSet<K> result = new ObjectOpenHashSet(a.length, 0.75F);

      for(Object element : a) {
         if (!result.add(element)) {
            throw new IllegalArgumentException("Duplicate element " + element);
         }
      }

      return result;
   }

   private ObjectOpenHashSet combine(ObjectOpenHashSet toAddFrom) {
      this.addAll(toAddFrom);
      return this;
   }

   public static Collector toSet() {
      return TO_SET_COLLECTOR;
   }

   public static Collector toSetWithExpectedSize(int expectedSize) {
      return expectedSize <= 16 ? toSet() : Collector.of(new ObjectCollections.SizeDecreasingSupplier(expectedSize, (size) -> size <= 16 ? new ObjectOpenHashSet() : new ObjectOpenHashSet(size)), ObjectOpenHashSet::add, ObjectOpenHashSet::combine, Characteristics.UNORDERED);
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

   public boolean addAll(Collection c) {
      if ((double)this.f <= (double)0.5F) {
         this.ensureCapacity(c.size());
      } else {
         this.tryCapacity((long)(this.size() + c.size()));
      }

      return super.addAll(c);
   }

   public boolean add(Object k) {
      if (k == null) {
         if (this.containsNull) {
            return false;
         }

         this.containsNull = true;
      } else {
         K[] key = (K[])this.key;
         int pos;
         K curr;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) != null) {
            if (curr.equals(k)) {
               return false;
            }

            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (curr.equals(k)) {
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

   public Object addOrGet(Object k) {
      if (k == null) {
         if (this.containsNull) {
            return this.key[this.n];
         }

         this.containsNull = true;
      } else {
         K[] key = (K[])this.key;
         int pos;
         K curr;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) != null) {
            if (curr.equals(k)) {
               return curr;
            }

            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (curr.equals(k)) {
                  return curr;
               }
            }
         }

         key[pos] = k;
      }

      if (this.size++ >= this.maxFill) {
         this.rehash(HashCommon.arraySize(this.size + 1, this.f));
      }

      return k;
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
      this.key[this.n] = null;
      --this.size;
      if (this.n > this.minN && this.size < this.maxFill / 4 && this.n > 16) {
         this.rehash(this.n / 2);
      }

      return true;
   }

   public boolean remove(Object k) {
      if (k == null) {
         return this.containsNull ? this.removeNullEntry() : false;
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return false;
         } else if (k.equals(curr)) {
            return this.removeEntry(pos);
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  return this.removeEntry(pos);
               }
            }

            return false;
         }
      }
   }

   public boolean contains(Object k) {
      if (k == null) {
         return this.containsNull;
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

   public Object get(Object k) {
      if (k == null) {
         return this.key[this.n];
      } else {
         K[] key = (K[])this.key;
         K curr;
         int pos;
         if ((curr = (K)key[pos = HashCommon.mix(k.hashCode()) & this.mask]) == null) {
            return null;
         } else if (k.equals(curr)) {
            return curr;
         } else {
            while((curr = (K)key[pos = pos + 1 & this.mask]) != null) {
               if (k.equals(curr)) {
                  return curr;
               }
            }

            return null;
         }
      }
   }

   public void clear() {
      if (this.size != 0) {
         this.size = 0;
         this.containsNull = false;
         Arrays.fill(this.key, (Object)null);
      }
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public ObjectIterator iterator() {
      return new SetIterator();
   }

   public ObjectSpliterator spliterator() {
      return new SetSpliterator();
   }

   public void forEach(Consumer action) {
      if (this.containsNull) {
         action.accept(this.key[this.n]);
      }

      K[] key = (K[])this.key;
      int pos = this.n;

      while(pos-- != 0) {
         if (key[pos] != null) {
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
      K[] key = (K[])this.key;
      int mask = newN - 1;
      K[] newKey = (K[])(new Object[newN + 1]);
      int i = this.n;

      int pos;
      for(int j = this.realSize(); j-- != 0; newKey[pos] = key[i]) {
         --i;
         if (key[i] != null && newKey[pos = HashCommon.mix(key[i].hashCode()) & mask] != null) {
            while(newKey[pos = pos + 1 & mask] != null) {
            }
         }
      }

      this.n = newN;
      this.mask = mask;
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.key = newKey;
   }

   public ObjectOpenHashSet clone() {
      ObjectOpenHashSet<K> c;
      try {
         c = (ObjectOpenHashSet)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }

      c.key = this.key.clone();
      c.containsNull = this.containsNull;
      return c;
   }

   public int hashCode() {
      int h = 0;
      int j = this.realSize();

      for(int i = 0; j-- != 0; ++i) {
         while(this.key[i] == null) {
            ++i;
         }

         if (this != this.key[i]) {
            h += this.key[i].hashCode();
         }
      }

      return h;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      ObjectIterator<K> i = this.iterator();
      s.defaultWriteObject();
      int j = this.size;

      while(j-- != 0) {
         s.writeObject(i.next());
      }

   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.n = HashCommon.arraySize(this.size, this.f);
      this.maxFill = HashCommon.maxFill(this.n, this.f);
      this.mask = this.n - 1;
      K[] key = (K[])(this.key = new Object[this.n + 1]);

      K k;
      int pos;
      for(int i = this.size; i-- != 0; key[pos] = k) {
         k = (K)s.readObject();
         if (k == null) {
            pos = this.n;
            this.containsNull = true;
         } else if (key[pos = HashCommon.mix(k.hashCode()) & this.mask] != null) {
            while(key[pos = pos + 1 & this.mask] != null) {
            }
         }
      }

   }

   private void checkTable() {
   }

   static {
      TO_SET_COLLECTOR = Collector.of(ObjectOpenHashSet::new, ObjectOpenHashSet::add, ObjectOpenHashSet::combine, Characteristics.UNORDERED);
   }

   private final class SetIterator implements ObjectIterator {
      int pos;
      int last;
      int c;
      boolean mustReturnNull;
      ObjectArrayList wrapped;

      private SetIterator() {
         this.pos = ObjectOpenHashSet.this.n;
         this.last = -1;
         this.c = ObjectOpenHashSet.this.size;
         this.mustReturnNull = ObjectOpenHashSet.this.containsNull;
      }

      public boolean hasNext() {
         return this.c != 0;
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            --this.c;
            if (this.mustReturnNull) {
               this.mustReturnNull = false;
               this.last = ObjectOpenHashSet.this.n;
               return ObjectOpenHashSet.this.key[ObjectOpenHashSet.this.n];
            } else {
               K[] key = (K[])ObjectOpenHashSet.this.key;

               while(--this.pos >= 0) {
                  if (key[this.pos] != null) {
                     return key[this.last = this.pos];
                  }
               }

               this.last = Integer.MIN_VALUE;
               return this.wrapped.get(-this.pos - 1);
            }
         }
      }

      private final void shiftKeys(int pos) {
         K[] key = (K[])ObjectOpenHashSet.this.key;

         while(true) {
            int last = pos;
            pos = pos + 1 & ObjectOpenHashSet.this.mask;

            K curr;
            while(true) {
               if ((curr = (K)key[pos]) == null) {
                  key[last] = null;
                  return;
               }

               int slot = HashCommon.mix(curr.hashCode()) & ObjectOpenHashSet.this.mask;
               if (last <= pos) {
                  if (last >= slot || slot > pos) {
                     break;
                  }
               } else if (last >= slot && slot > pos) {
                  break;
               }

               pos = pos + 1 & ObjectOpenHashSet.this.mask;
            }

            if (pos < last) {
               if (this.wrapped == null) {
                  this.wrapped = new ObjectArrayList(2);
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
            if (this.last == ObjectOpenHashSet.this.n) {
               ObjectOpenHashSet.this.containsNull = false;
               ObjectOpenHashSet.this.key[ObjectOpenHashSet.this.n] = null;
            } else {
               if (this.pos < 0) {
                  ObjectOpenHashSet.this.remove(this.wrapped.set(-this.pos - 1, (Object)null));
                  this.last = -1;
                  return;
               }

               this.shiftKeys(this.last);
            }

            --ObjectOpenHashSet.this.size;
            this.last = -1;
         }
      }

      public void forEachRemaining(Consumer action) {
         K[] key = (K[])ObjectOpenHashSet.this.key;
         if (this.mustReturnNull) {
            this.mustReturnNull = false;
            this.last = ObjectOpenHashSet.this.n;
            action.accept(key[ObjectOpenHashSet.this.n]);
            --this.c;
         }

         while(this.c != 0) {
            if (--this.pos < 0) {
               this.last = Integer.MIN_VALUE;
               action.accept(this.wrapped.get(-this.pos - 1));
               --this.c;
            } else if (key[this.pos] != null) {
               action.accept(key[this.last = this.pos]);
               --this.c;
            }
         }

      }
   }

   private final class SetSpliterator implements ObjectSpliterator {
      private static final int POST_SPLIT_CHARACTERISTICS = 1;
      int pos = 0;
      int max;
      int c;
      boolean mustReturnNull;
      boolean hasSplit;

      SetSpliterator() {
         this.max = ObjectOpenHashSet.this.n;
         this.c = 0;
         this.mustReturnNull = ObjectOpenHashSet.this.containsNull;
         this.hasSplit = false;
      }

      SetSpliterator(int pos, int max, boolean mustReturnNull, boolean hasSplit) {
         this.max = ObjectOpenHashSet.this.n;
         this.c = 0;
         this.mustReturnNull = ObjectOpenHashSet.this.containsNull;
         this.hasSplit = false;
         this.pos = pos;
         this.max = max;
         this.mustReturnNull = mustReturnNull;
         this.hasSplit = hasSplit;
      }

      public boolean tryAdvance(Consumer action) {
         if (this.mustReturnNull) {
            this.mustReturnNull = false;
            ++this.c;
            action.accept(ObjectOpenHashSet.this.key[ObjectOpenHashSet.this.n]);
            return true;
         } else {
            for(K[] key = (K[])ObjectOpenHashSet.this.key; this.pos < this.max; ++this.pos) {
               if (key[this.pos] != null) {
                  ++this.c;
                  action.accept(key[this.pos++]);
                  return true;
               }
            }

            return false;
         }
      }

      public void forEachRemaining(Consumer action) {
         K[] key = (K[])ObjectOpenHashSet.this.key;
         if (this.mustReturnNull) {
            this.mustReturnNull = false;
            action.accept(key[ObjectOpenHashSet.this.n]);
            ++this.c;
         }

         for(; this.pos < this.max; ++this.pos) {
            if (key[this.pos] != null) {
               action.accept(key[this.pos]);
               ++this.c;
            }
         }

      }

      public int characteristics() {
         return this.hasSplit ? 1 : 65;
      }

      public long estimateSize() {
         return !this.hasSplit ? (long)(ObjectOpenHashSet.this.size - this.c) : Math.min((long)(ObjectOpenHashSet.this.size - this.c), (long)((double)ObjectOpenHashSet.this.realSize() / (double)ObjectOpenHashSet.this.n * (double)(this.max - this.pos)) + (long)(this.mustReturnNull ? 1 : 0));
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
               ObjectOpenHashSet<K>.SetSpliterator split = ObjectOpenHashSet.this.new SetSpliterator(retPos, myNewPos, this.mustReturnNull, true);
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

            K[] key = (K[])ObjectOpenHashSet.this.key;

            while(this.pos < this.max && n > 0L) {
               if (key[this.pos++] != null) {
                  ++skipped;
                  --n;
               }
            }

            return skipped;
         }
      }
   }
}
