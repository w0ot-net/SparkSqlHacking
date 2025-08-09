package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.stream.DoubleStream;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public class DoubleImmutableList extends DoubleLists.ImmutableListBase implements DoubleList, RandomAccess, Cloneable, Serializable {
   private static final long serialVersionUID = 0L;
   static final DoubleImmutableList EMPTY;
   private final double[] a;

   public DoubleImmutableList(double[] a) {
      this.a = a;
   }

   public DoubleImmutableList(Collection c) {
      this(c.isEmpty() ? DoubleArrays.EMPTY_ARRAY : DoubleIterators.unwrap(DoubleIterators.asDoubleIterator(c.iterator())));
   }

   public DoubleImmutableList(DoubleCollection c) {
      this(c.isEmpty() ? DoubleArrays.EMPTY_ARRAY : DoubleIterators.unwrap(c.iterator()));
   }

   public DoubleImmutableList(DoubleList l) {
      this(l.isEmpty() ? DoubleArrays.EMPTY_ARRAY : new double[l.size()]);
      l.getElements(0, this.a, 0, l.size());
   }

   public DoubleImmutableList(double[] a, int offset, int length) {
      this(length == 0 ? DoubleArrays.EMPTY_ARRAY : new double[length]);
      System.arraycopy(a, offset, this.a, 0, length);
   }

   public DoubleImmutableList(DoubleIterator i) {
      this(i.hasNext() ? DoubleIterators.unwrap(i) : DoubleArrays.EMPTY_ARRAY);
   }

   public static DoubleImmutableList of() {
      return EMPTY;
   }

   public static DoubleImmutableList of(double... init) {
      return init.length == 0 ? of() : new DoubleImmutableList(init);
   }

   private static DoubleImmutableList convertTrustedToImmutableList(DoubleArrayList arrayList) {
      if (arrayList.isEmpty()) {
         return of();
      } else {
         double[] backingArray = arrayList.elements();
         if (arrayList.size() != backingArray.length) {
            backingArray = Arrays.copyOf(backingArray, arrayList.size());
         }

         return new DoubleImmutableList(backingArray);
      }
   }

   public static DoubleImmutableList toList(DoubleStream stream) {
      return convertTrustedToImmutableList(DoubleArrayList.toList(stream));
   }

   public static DoubleImmutableList toListWithExpectedSize(DoubleStream stream, int expectedSize) {
      return convertTrustedToImmutableList(DoubleArrayList.toListWithExpectedSize(stream, expectedSize));
   }

   public double getDouble(int index) {
      if (index >= this.a.length) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.a.length + ")");
      } else {
         return this.a[index];
      }
   }

   public int indexOf(double k) {
      int i = 0;

      for(int size = this.a.length; i < size; ++i) {
         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(this.a[i])) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(double k) {
      int i = this.a.length;

      while(i-- != 0) {
         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(this.a[i])) {
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

   public void getElements(int from, double[] a, int offset, int length) {
      DoubleArrays.ensureOffsetLength(a, offset, length);
      System.arraycopy(this.a, from, a, offset, length);
   }

   public void forEach(java.util.function.DoubleConsumer action) {
      for(int i = 0; i < this.a.length; ++i) {
         action.accept(this.a[i]);
      }

   }

   public double[] toDoubleArray() {
      return this.a.length == 0 ? DoubleArrays.EMPTY_ARRAY : (double[])this.a.clone();
   }

   public double[] toArray(double[] a) {
      if (a == null || a.length < this.size()) {
         a = new double[this.a.length];
      }

      System.arraycopy(this.a, 0, a, 0, a.length);
      return a;
   }

   public DoubleListIterator listIterator(final int index) {
      this.ensureIndex(index);
      return new DoubleListIterator() {
         int pos = index;

         public boolean hasNext() {
            return this.pos < DoubleImmutableList.this.a.length;
         }

         public boolean hasPrevious() {
            return this.pos > 0;
         }

         public double nextDouble() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return DoubleImmutableList.this.a[this.pos++];
            }
         }

         public double previousDouble() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return DoubleImmutableList.this.a[--this.pos];
            }
         }

         public int nextIndex() {
            return this.pos;
         }

         public int previousIndex() {
            return this.pos - 1;
         }

         public void forEachRemaining(java.util.function.DoubleConsumer action) {
            while(this.pos < DoubleImmutableList.this.a.length) {
               action.accept(DoubleImmutableList.this.a[this.pos++]);
            }

         }

         public void add(double k) {
            throw new UnsupportedOperationException();
         }

         public void set(double k) {
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
               int remaining = DoubleImmutableList.this.a.length - this.pos;
               if (n < remaining) {
                  this.pos += n;
               } else {
                  n = remaining;
                  this.pos = DoubleImmutableList.this.a.length;
               }

               return n;
            }
         }
      };
   }

   public DoubleSpliterator spliterator() {
      return new Spliterator();
   }

   public DoubleList subList(int from, int to) {
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

   public DoubleImmutableList clone() {
      return this;
   }

   public boolean equals(DoubleImmutableList l) {
      if (l == this) {
         return true;
      } else if (this.a == l.a) {
         return true;
      } else {
         int s = this.size();
         if (s != l.size()) {
            return false;
         } else {
            double[] a1 = this.a;
            double[] a2 = l.a;
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
      } else if (o instanceof DoubleImmutableList) {
         return this.equals((DoubleImmutableList)o);
      } else {
         return o instanceof ImmutableSubList ? ((ImmutableSubList)o).equals(this) : super.equals(o);
      }
   }

   public int compareTo(DoubleImmutableList l) {
      if (this.a == l.a) {
         return 0;
      } else {
         int s1 = this.size();
         int s2 = l.size();
         double[] a1 = this.a;
         double[] a2 = l.a;

         int i;
         for(i = 0; i < s1 && i < s2; ++i) {
            double e1 = a1[i];
            double e2 = a2[i];
            int r;
            if ((r = Double.compare(e1, e2)) != 0) {
               return r;
            }
         }

         return i < s2 ? -1 : (i < s1 ? 1 : 0);
      }
   }

   public int compareTo(List l) {
      if (l instanceof DoubleImmutableList) {
         return this.compareTo((DoubleImmutableList)l);
      } else if (l instanceof ImmutableSubList) {
         ImmutableSubList other = (ImmutableSubList)l;
         return -other.compareTo((List)this);
      } else {
         return super.compareTo(l);
      }
   }

   static {
      EMPTY = new DoubleImmutableList(DoubleArrays.EMPTY_ARRAY);
   }

   private final class Spliterator implements DoubleSpliterator {
      int pos;
      int max;

      public Spliterator() {
         this(0, DoubleImmutableList.this.a.length);
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

      public boolean tryAdvance(java.util.function.DoubleConsumer action) {
         if (this.pos >= this.max) {
            return false;
         } else {
            action.accept(DoubleImmutableList.this.a[this.pos++]);
            return true;
         }
      }

      public void forEachRemaining(java.util.function.DoubleConsumer action) {
         while(this.pos < this.max) {
            action.accept(DoubleImmutableList.this.a[this.pos]);
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

      public DoubleSpliterator trySplit() {
         int retLen = this.max - this.pos >> 1;
         if (retLen <= 1) {
            return null;
         } else {
            int myNewPos = this.pos + retLen;
            int oldPos = this.pos;
            this.pos = myNewPos;
            return DoubleImmutableList.this.new Spliterator(oldPos, myNewPos);
         }
      }
   }

   private static final class ImmutableSubList extends DoubleLists.ImmutableListBase implements RandomAccess, Serializable {
      private static final long serialVersionUID = 7054639518438982401L;
      final DoubleImmutableList innerList;
      final int from;
      final int to;
      final transient double[] a;

      ImmutableSubList(DoubleImmutableList innerList, int from, int to) {
         this.innerList = innerList;
         this.from = from;
         this.to = to;
         this.a = innerList.a;
      }

      public double getDouble(int index) {
         this.ensureRestrictedIndex(index);
         return this.a[index + this.from];
      }

      public int indexOf(double k) {
         for(int i = this.from; i < this.to; ++i) {
            if (Double.doubleToLongBits(k) == Double.doubleToLongBits(this.a[i])) {
               return i - this.from;
            }
         }

         return -1;
      }

      public int lastIndexOf(double k) {
         int i = this.to;

         while(i-- != this.from) {
            if (Double.doubleToLongBits(k) == Double.doubleToLongBits(this.a[i])) {
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

      public void getElements(int fromSublistIndex, double[] a, int offset, int length) {
         DoubleArrays.ensureOffsetLength(a, offset, length);
         this.ensureRestrictedIndex(fromSublistIndex);
         if (this.from + length > this.to) {
            throw new IndexOutOfBoundsException("Final index " + (this.from + length) + " (startingIndex: " + this.from + " + length: " + length + ") is greater then list length " + this.size());
         } else {
            System.arraycopy(this.a, fromSublistIndex + this.from, a, offset, length);
         }
      }

      public void forEach(java.util.function.DoubleConsumer action) {
         for(int i = this.from; i < this.to; ++i) {
            action.accept(this.a[i]);
         }

      }

      public double[] toDoubleArray() {
         return Arrays.copyOfRange(this.a, this.from, this.to);
      }

      public double[] toArray(double[] a) {
         if (a == null || a.length < this.size()) {
            a = new double[this.size()];
         }

         System.arraycopy(this.a, this.from, a, 0, this.size());
         return a;
      }

      public DoubleListIterator listIterator(final int index) {
         this.ensureRestrictedIndex(index + this.from);
         return new DoubleListIterator() {
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

            public double nextDouble() {
               if (!this.hasNext()) {
                  throw new NoSuchElementException();
               } else {
                  return ImmutableSubList.this.a[this.pos++];
               }
            }

            public double previousDouble() {
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

            public void forEachRemaining(java.util.function.DoubleConsumer action) {
               while(this.pos < ImmutableSubList.this.to) {
                  action.accept(ImmutableSubList.this.a[this.pos++]);
               }

            }

            public void add(double k) {
               throw new UnsupportedOperationException();
            }

            public void set(double k) {
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

      public DoubleSpliterator spliterator() {
         return new SubListSpliterator();
      }

      boolean contentsEquals(double[] otherA, int otherAFrom, int otherATo) {
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
         } else if (o instanceof DoubleImmutableList) {
            DoubleImmutableList other = (DoubleImmutableList)o;
            return this.contentsEquals(other.a, 0, other.size());
         } else if (o instanceof ImmutableSubList) {
            ImmutableSubList other = (ImmutableSubList)o;
            return this.contentsEquals(other.a, other.from, other.to);
         } else {
            return super.equals(o);
         }
      }

      int contentsCompareTo(double[] otherA, int otherAFrom, int otherATo) {
         if (this.a == otherA && this.from == otherAFrom && this.to == otherATo) {
            return 0;
         } else {
            int i = this.from;

            for(int j = otherAFrom; i < this.to && i < otherATo; ++j) {
               double e1 = this.a[i];
               double e2 = otherA[j];
               int r;
               if ((r = Double.compare(e1, e2)) != 0) {
                  return r;
               }

               ++i;
            }

            return i < otherATo ? -1 : (i < this.to ? 1 : 0);
         }
      }

      public int compareTo(List l) {
         if (l instanceof DoubleImmutableList) {
            DoubleImmutableList other = (DoubleImmutableList)l;
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

      public DoubleList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from == to) {
            return DoubleImmutableList.EMPTY;
         } else if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new ImmutableSubList(this.innerList, from + this.from, to + this.from);
         }
      }

      private final class SubListSpliterator extends DoubleSpliterators.EarlyBindingSizeIndexBasedSpliterator {
         SubListSpliterator() {
            super(ImmutableSubList.this.from, ImmutableSubList.this.to);
         }

         private SubListSpliterator(int pos, int maxPos) {
            super(pos, maxPos);
         }

         protected final double get(int i) {
            return ImmutableSubList.this.a[i];
         }

         protected final SubListSpliterator makeForSplit(int pos, int maxPos) {
            return ImmutableSubList.this.new SubListSpliterator(pos, maxPos);
         }

         public boolean tryAdvance(java.util.function.DoubleConsumer action) {
            if (this.pos >= this.maxPos) {
               return false;
            } else {
               action.accept(ImmutableSubList.this.a[this.pos++]);
               return true;
            }
         }

         public void forEachRemaining(java.util.function.DoubleConsumer action) {
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
