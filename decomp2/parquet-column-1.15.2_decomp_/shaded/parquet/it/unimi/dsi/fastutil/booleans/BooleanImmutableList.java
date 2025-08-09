package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public class BooleanImmutableList extends BooleanLists.ImmutableListBase implements BooleanList, RandomAccess, Cloneable, Serializable {
   private static final long serialVersionUID = 0L;
   static final BooleanImmutableList EMPTY;
   private final boolean[] a;

   public BooleanImmutableList(boolean[] a) {
      this.a = a;
   }

   public BooleanImmutableList(Collection c) {
      this(c.isEmpty() ? BooleanArrays.EMPTY_ARRAY : BooleanIterators.unwrap(BooleanIterators.asBooleanIterator(c.iterator())));
   }

   public BooleanImmutableList(BooleanCollection c) {
      this(c.isEmpty() ? BooleanArrays.EMPTY_ARRAY : BooleanIterators.unwrap(c.iterator()));
   }

   public BooleanImmutableList(BooleanList l) {
      this(l.isEmpty() ? BooleanArrays.EMPTY_ARRAY : new boolean[l.size()]);
      l.getElements(0, this.a, 0, l.size());
   }

   public BooleanImmutableList(boolean[] a, int offset, int length) {
      this(length == 0 ? BooleanArrays.EMPTY_ARRAY : new boolean[length]);
      System.arraycopy(a, offset, this.a, 0, length);
   }

   public BooleanImmutableList(BooleanIterator i) {
      this(i.hasNext() ? BooleanIterators.unwrap(i) : BooleanArrays.EMPTY_ARRAY);
   }

   public static BooleanImmutableList of() {
      return EMPTY;
   }

   public static BooleanImmutableList of(boolean... init) {
      return init.length == 0 ? of() : new BooleanImmutableList(init);
   }

   public boolean getBoolean(int index) {
      if (index >= this.a.length) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.a.length + ")");
      } else {
         return this.a[index];
      }
   }

   public int indexOf(boolean k) {
      int i = 0;

      for(int size = this.a.length; i < size; ++i) {
         if (k == this.a[i]) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(boolean k) {
      int i = this.a.length;

      while(i-- != 0) {
         if (k == this.a[i]) {
            return i;
         }
      }

      return -1;
   }

   public int size() {
      return this.a.length;
   }

   public boolean isEmpty() {
      return this.a.length == 0;
   }

   public void getElements(int from, boolean[] a, int offset, int length) {
      BooleanArrays.ensureOffsetLength(a, offset, length);
      System.arraycopy(this.a, from, a, offset, length);
   }

   public void forEach(BooleanConsumer action) {
      for(int i = 0; i < this.a.length; ++i) {
         action.accept(this.a[i]);
      }

   }

   public boolean[] toBooleanArray() {
      return this.a.length == 0 ? BooleanArrays.EMPTY_ARRAY : (boolean[])this.a.clone();
   }

   public boolean[] toArray(boolean[] a) {
      if (a == null || a.length < this.size()) {
         a = new boolean[this.a.length];
      }

      System.arraycopy(this.a, 0, a, 0, a.length);
      return a;
   }

   public BooleanListIterator listIterator(final int index) {
      this.ensureIndex(index);
      return new BooleanListIterator() {
         int pos = index;

         public boolean hasNext() {
            return this.pos < BooleanImmutableList.this.a.length;
         }

         public boolean hasPrevious() {
            return this.pos > 0;
         }

         public boolean nextBoolean() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return BooleanImmutableList.this.a[this.pos++];
            }
         }

         public boolean previousBoolean() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return BooleanImmutableList.this.a[--this.pos];
            }
         }

         public int nextIndex() {
            return this.pos;
         }

         public int previousIndex() {
            return this.pos - 1;
         }

         public void forEachRemaining(BooleanConsumer action) {
            while(this.pos < BooleanImmutableList.this.a.length) {
               action.accept(BooleanImmutableList.this.a[this.pos++]);
            }

         }

         public void add(boolean k) {
            throw new UnsupportedOperationException();
         }

         public void set(boolean k) {
            throw new UnsupportedOperationException();
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }

         public int back(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int remaining = this.pos;
               if (n < remaining) {
                  this.pos -= n;
               } else {
                  n = remaining;
                  this.pos = 0;
               }

               return n;
            }
         }

         public int skip(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int remaining = BooleanImmutableList.this.a.length - this.pos;
               if (n < remaining) {
                  this.pos += n;
               } else {
                  n = remaining;
                  this.pos = BooleanImmutableList.this.a.length;
               }

               return n;
            }
         }
      };
   }

   public BooleanSpliterator spliterator() {
      return new Spliterator();
   }

   public BooleanList subList(int from, int to) {
      if (from == 0 && to == this.size()) {
         return this;
      } else {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from == to) {
            return EMPTY;
         } else if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new ImmutableSubList(this, from, to);
         }
      }
   }

   public BooleanImmutableList clone() {
      return this;
   }

   public boolean equals(BooleanImmutableList l) {
      if (l == this) {
         return true;
      } else if (this.a == l.a) {
         return true;
      } else {
         int s = this.size();
         if (s != l.size()) {
            return false;
         } else {
            boolean[] a1 = this.a;
            boolean[] a2 = l.a;
            return Arrays.equals(a1, a2);
         }
      }
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (o == null) {
         return false;
      } else if (!(o instanceof List)) {
         return false;
      } else if (o instanceof BooleanImmutableList) {
         return this.equals((BooleanImmutableList)o);
      } else {
         return o instanceof ImmutableSubList ? ((ImmutableSubList)o).equals(this) : super.equals(o);
      }
   }

   public int compareTo(BooleanImmutableList l) {
      if (this.a == l.a) {
         return 0;
      } else {
         int s1 = this.size();
         int s2 = l.size();
         boolean[] a1 = this.a;
         boolean[] a2 = l.a;

         int i;
         for(i = 0; i < s1 && i < s2; ++i) {
            boolean e1 = a1[i];
            boolean e2 = a2[i];
            int r;
            if ((r = Boolean.compare(e1, e2)) != 0) {
               return r;
            }
         }

         return i < s2 ? -1 : (i < s1 ? 1 : 0);
      }
   }

   public int compareTo(List l) {
      if (l instanceof BooleanImmutableList) {
         return this.compareTo((BooleanImmutableList)l);
      } else if (l instanceof ImmutableSubList) {
         ImmutableSubList other = (ImmutableSubList)l;
         return -other.compareTo((List)this);
      } else {
         return super.compareTo(l);
      }
   }

   static {
      EMPTY = new BooleanImmutableList(BooleanArrays.EMPTY_ARRAY);
   }

   private final class Spliterator implements BooleanSpliterator {
      int pos;
      int max;

      public Spliterator() {
         this(0, BooleanImmutableList.this.a.length);
      }

      private Spliterator(int pos, int max) {
         assert pos <= max : "pos " + pos + " must be <= max " + max;

         this.pos = pos;
         this.max = max;
      }

      public int characteristics() {
         return 17744;
      }

      public long estimateSize() {
         return (long)(this.max - this.pos);
      }

      public boolean tryAdvance(BooleanConsumer action) {
         if (this.pos >= this.max) {
            return false;
         } else {
            action.accept(BooleanImmutableList.this.a[this.pos++]);
            return true;
         }
      }

      public void forEachRemaining(BooleanConsumer action) {
         while(this.pos < this.max) {
            action.accept(BooleanImmutableList.this.a[this.pos]);
            ++this.pos;
         }

      }

      public long skip(long n) {
         if (n < 0L) {
            throw new IllegalArgumentException("Argument must be nonnegative: " + n);
         } else if (this.pos >= this.max) {
            return 0L;
         } else {
            int remaining = this.max - this.pos;
            if (n < (long)remaining) {
               this.pos = SafeMath.safeLongToInt((long)this.pos + n);
               return n;
            } else {
               n = (long)remaining;
               this.pos = this.max;
               return n;
            }
         }
      }

      public BooleanSpliterator trySplit() {
         int retLen = this.max - this.pos >> 1;
         if (retLen <= 1) {
            return null;
         } else {
            int myNewPos = this.pos + retLen;
            int oldPos = this.pos;
            this.pos = myNewPos;
            return BooleanImmutableList.this.new Spliterator(oldPos, myNewPos);
         }
      }
   }

   private static final class ImmutableSubList extends BooleanLists.ImmutableListBase implements RandomAccess, Serializable {
      private static final long serialVersionUID = 7054639518438982401L;
      final BooleanImmutableList innerList;
      final int from;
      final int to;
      final transient boolean[] a;

      ImmutableSubList(BooleanImmutableList innerList, int from, int to) {
         this.innerList = innerList;
         this.from = from;
         this.to = to;
         this.a = innerList.a;
      }

      public boolean getBoolean(int index) {
         this.ensureRestrictedIndex(index);
         return this.a[index + this.from];
      }

      public int indexOf(boolean k) {
         for(int i = this.from; i < this.to; ++i) {
            if (k == this.a[i]) {
               return i - this.from;
            }
         }

         return -1;
      }

      public int lastIndexOf(boolean k) {
         int i = this.to;

         while(i-- != this.from) {
            if (k == this.a[i]) {
               return i - this.from;
            }
         }

         return -1;
      }

      public int size() {
         return this.to - this.from;
      }

      public boolean isEmpty() {
         return this.to <= this.from;
      }

      public void getElements(int fromSublistIndex, boolean[] a, int offset, int length) {
         BooleanArrays.ensureOffsetLength(a, offset, length);
         this.ensureRestrictedIndex(fromSublistIndex);
         if (this.from + length > this.to) {
            throw new IndexOutOfBoundsException("Final index " + (this.from + length) + " (startingIndex: " + this.from + " + length: " + length + ") is greater then list length " + this.size());
         } else {
            System.arraycopy(this.a, fromSublistIndex + this.from, a, offset, length);
         }
      }

      public void forEach(BooleanConsumer action) {
         for(int i = this.from; i < this.to; ++i) {
            action.accept(this.a[i]);
         }

      }

      public boolean[] toBooleanArray() {
         return Arrays.copyOfRange(this.a, this.from, this.to);
      }

      public boolean[] toArray(boolean[] a) {
         if (a == null || a.length < this.size()) {
            a = new boolean[this.size()];
         }

         System.arraycopy(this.a, this.from, a, 0, this.size());
         return a;
      }

      public BooleanListIterator listIterator(final int index) {
         this.ensureRestrictedIndex(index + this.from);
         return new BooleanListIterator() {
            int pos;

            {
               this.pos = index + ImmutableSubList.this.from;
            }

            public boolean hasNext() {
               return this.pos < ImmutableSubList.this.to;
            }

            public boolean hasPrevious() {
               return this.pos > ImmutableSubList.this.from;
            }

            public boolean nextBoolean() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  return ImmutableSubList.this.a[this.pos++];
               }
            }

            public boolean previousBoolean() {
               if (!this.hasPrevious()) {
                  throw new NoSuchElementException();
               } else {
                  return ImmutableSubList.this.a[--this.pos];
               }
            }

            public int nextIndex() {
               return this.pos - ImmutableSubList.this.from;
            }

            public int previousIndex() {
               return this.pos - ImmutableSubList.this.from - 1;
            }

            public void forEachRemaining(BooleanConsumer action) {
               while(this.pos < ImmutableSubList.this.to) {
                  action.accept(ImmutableSubList.this.a[this.pos++]);
               }

            }

            public void add(boolean k) {
               throw new UnsupportedOperationException();
            }

            public void set(boolean k) {
               throw new UnsupportedOperationException();
            }

            public void remove() {
               throw new UnsupportedOperationException();
            }

            public int back(int n) {
               if (n < 0) {
                  throw new IllegalArgumentException("Argument must be nonnegative: " + n);
               } else {
                  int remaining = this.pos - ImmutableSubList.this.from;
                  if (n < remaining) {
                     this.pos -= n;
                  } else {
                     n = remaining;
                     this.pos = ImmutableSubList.this.from;
                  }

                  return n;
               }
            }

            public int skip(int n) {
               if (n < 0) {
                  throw new IllegalArgumentException("Argument must be nonnegative: " + n);
               } else {
                  int remaining = ImmutableSubList.this.to - this.pos;
                  if (n < remaining) {
                     this.pos += n;
                  } else {
                     n = remaining;
                     this.pos = ImmutableSubList.this.to;
                  }

                  return n;
               }
            }
         };
      }

      public BooleanSpliterator spliterator() {
         return new SubListSpliterator();
      }

      boolean contentsEquals(boolean[] otherA, int otherAFrom, int otherATo) {
         if (this.a == otherA && this.from == otherAFrom && this.to == otherATo) {
            return true;
         } else if (otherATo - otherAFrom != this.size()) {
            return false;
         } else {
            int pos = this.from;
            int otherPos = otherAFrom;

            while(pos < this.to) {
               if (this.a[pos++] != otherA[otherPos++]) {
                  return false;
               }
            }

            return true;
         }
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (o == null) {
            return false;
         } else if (!(o instanceof List)) {
            return false;
         } else if (o instanceof BooleanImmutableList) {
            BooleanImmutableList other = (BooleanImmutableList)o;
            return this.contentsEquals(other.a, 0, other.size());
         } else if (o instanceof ImmutableSubList) {
            ImmutableSubList other = (ImmutableSubList)o;
            return this.contentsEquals(other.a, other.from, other.to);
         } else {
            return super.equals(o);
         }
      }

      int contentsCompareTo(boolean[] otherA, int otherAFrom, int otherATo) {
         if (this.a == otherA && this.from == otherAFrom && this.to == otherATo) {
            return 0;
         } else {
            int i = this.from;

            for(int j = otherAFrom; i < this.to && i < otherATo; ++j) {
               boolean e1 = this.a[i];
               boolean e2 = otherA[j];
               int r;
               if ((r = Boolean.compare(e1, e2)) != 0) {
                  return r;
               }

               ++i;
            }

            return i < otherATo ? -1 : (i < this.to ? 1 : 0);
         }
      }

      public int compareTo(List l) {
         if (l instanceof BooleanImmutableList) {
            BooleanImmutableList other = (BooleanImmutableList)l;
            return this.contentsCompareTo(other.a, 0, other.size());
         } else if (l instanceof ImmutableSubList) {
            ImmutableSubList other = (ImmutableSubList)l;
            return this.contentsCompareTo(other.a, other.from, other.to);
         } else {
            return super.compareTo(l);
         }
      }

      private Object readResolve() throws ObjectStreamException {
         try {
            return this.innerList.subList(this.from, this.to);
         } catch (IndexOutOfBoundsException | IllegalArgumentException ex) {
            throw (InvalidObjectException)(new InvalidObjectException(((RuntimeException)ex).getMessage())).initCause(ex);
         }
      }

      public BooleanList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from == to) {
            return BooleanImmutableList.EMPTY;
         } else if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new ImmutableSubList(this.innerList, from + this.from, to + this.from);
         }
      }

      private final class SubListSpliterator extends BooleanSpliterators.EarlyBindingSizeIndexBasedSpliterator {
         SubListSpliterator() {
            super(ImmutableSubList.this.from, ImmutableSubList.this.to);
         }

         private SubListSpliterator(int pos, int maxPos) {
            super(pos, maxPos);
         }

         protected final boolean get(int i) {
            return ImmutableSubList.this.a[i];
         }

         protected final SubListSpliterator makeForSplit(int pos, int maxPos) {
            return ImmutableSubList.this.new SubListSpliterator(pos, maxPos);
         }

         public boolean tryAdvance(BooleanConsumer action) {
            if (this.pos >= this.maxPos) {
               return false;
            } else {
               action.accept(ImmutableSubList.this.a[this.pos++]);
               return true;
            }
         }

         public void forEachRemaining(BooleanConsumer action) {
            int max = this.maxPos;

            while(this.pos < max) {
               action.accept(ImmutableSubList.this.a[this.pos++]);
            }

         }

         public int characteristics() {
            return 17744;
         }
      }
   }
}
