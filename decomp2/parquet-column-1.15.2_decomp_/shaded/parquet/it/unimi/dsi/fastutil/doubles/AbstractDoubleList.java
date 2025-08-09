package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import shaded.parquet.it.unimi.dsi.fastutil.HashCommon;

public abstract class AbstractDoubleList extends AbstractDoubleCollection implements DoubleList, DoubleStack {
   protected AbstractDoubleList() {
   }

   protected void ensureIndex(int index) {
      if (index < 0) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
      } else if (index > this.size()) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than list size (" + this.size() + ")");
      }
   }

   protected void ensureRestrictedIndex(int index) {
      if (index < 0) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
      } else if (index >= this.size()) {
         throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size() + ")");
      }
   }

   public void add(int index, double k) {
      throw new UnsupportedOperationException();
   }

   public boolean add(double k) {
      this.add(this.size(), k);
      return true;
   }

   public double removeDouble(int i) {
      throw new UnsupportedOperationException();
   }

   public double set(int index, double k) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(int index, Collection c) {
      if (c instanceof DoubleCollection) {
         return this.addAll(index, (DoubleCollection)c);
      } else {
         this.ensureIndex(index);
         Iterator<? extends Double> i = c.iterator();
         boolean retVal = i.hasNext();

         while(i.hasNext()) {
            this.add(index++, (Double)i.next());
         }

         return retVal;
      }
   }

   public boolean addAll(Collection c) {
      return this.addAll(this.size(), c);
   }

   public DoubleListIterator iterator() {
      return this.listIterator();
   }

   public DoubleListIterator listIterator() {
      return this.listIterator(0);
   }

   public DoubleListIterator listIterator(int index) {
      this.ensureIndex(index);
      return new DoubleIterators.AbstractIndexBasedListIterator(0, index) {
         protected final double get(int i) {
            return AbstractDoubleList.this.getDouble(i);
         }

         protected final void add(int i, double k) {
            AbstractDoubleList.this.add(i, k);
         }

         protected final void set(int i, double k) {
            AbstractDoubleList.this.set(i, k);
         }

         protected final void remove(int i) {
            AbstractDoubleList.this.removeDouble(i);
         }

         protected final int getMaxPos() {
            return AbstractDoubleList.this.size();
         }
      };
   }

   public boolean contains(double k) {
      return this.indexOf(k) >= 0;
   }

   public int indexOf(double k) {
      DoubleListIterator i = this.listIterator();

      while(i.hasNext()) {
         double e = i.nextDouble();
         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(e)) {
            return i.previousIndex();
         }
      }

      return -1;
   }

   public int lastIndexOf(double k) {
      DoubleListIterator i = this.listIterator(this.size());

      while(i.hasPrevious()) {
         double e = i.previousDouble();
         if (Double.doubleToLongBits(k) == Double.doubleToLongBits(e)) {
            return i.nextIndex();
         }
      }

      return -1;
   }

   public void size(int size) {
      int i = this.size();
      if (size > i) {
         while(i++ < size) {
            this.add((double)0.0F);
         }
      } else {
         while(i-- != size) {
            this.removeDouble(i);
         }
      }

   }

   public DoubleList subList(int from, int to) {
      this.ensureIndex(from);
      this.ensureIndex(to);
      if (from > to) {
         throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
      } else {
         return (DoubleList)(this instanceof RandomAccess ? new DoubleRandomAccessSubList(this, from, to) : new DoubleSubList(this, from, to));
      }
   }

   public void forEach(java.util.function.DoubleConsumer action) {
      if (this instanceof RandomAccess) {
         int i = 0;

         for(int max = this.size(); i < max; ++i) {
            action.accept(this.getDouble(i));
         }
      } else {
         DoubleList.super.forEach(action);
      }

   }

   public void removeElements(int from, int to) {
      this.ensureIndex(to);
      DoubleListIterator i = this.listIterator(from);
      int n = to - from;
      if (n < 0) {
         throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
      } else {
         while(n-- != 0) {
            i.nextDouble();
            i.remove();
         }

      }
   }

   public void addElements(int index, double[] a, int offset, int length) {
      this.ensureIndex(index);
      DoubleArrays.ensureOffsetLength(a, offset, length);
      if (this instanceof RandomAccess) {
         while(length-- != 0) {
            this.add(index++, a[offset++]);
         }
      } else {
         DoubleListIterator iter = this.listIterator(index);

         while(length-- != 0) {
            iter.add(a[offset++]);
         }
      }

   }

   public void addElements(int index, double[] a) {
      this.addElements(index, a, 0, a.length);
   }

   public void getElements(int from, double[] a, int offset, int length) {
      this.ensureIndex(from);
      DoubleArrays.ensureOffsetLength(a, offset, length);
      if (from + length > this.size()) {
         throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + this.size() + ")");
      } else {
         if (this instanceof RandomAccess) {
            for(int current = from; length-- != 0; a[offset++] = this.getDouble(current++)) {
            }
         } else {
            for(DoubleListIterator i = this.listIterator(from); length-- != 0; a[offset++] = i.nextDouble()) {
            }
         }

      }
   }

   public void setElements(int index, double[] a, int offset, int length) {
      this.ensureIndex(index);
      DoubleArrays.ensureOffsetLength(a, offset, length);
      if (index + length > this.size()) {
         throw new IndexOutOfBoundsException("End index (" + (index + length) + ") is greater than list size (" + this.size() + ")");
      } else {
         if (this instanceof RandomAccess) {
            for(int i = 0; i < length; ++i) {
               this.set(i + index, a[i + offset]);
            }
         } else {
            DoubleListIterator iter = this.listIterator(index);
            int i = 0;

            while(i < length) {
               iter.nextDouble();
               iter.set(a[offset + i++]);
            }
         }

      }
   }

   public void clear() {
      this.removeElements(0, this.size());
   }

   public int hashCode() {
      DoubleIterator i = this.iterator();
      int h = 1;

      double k;
      for(int s = this.size(); s-- != 0; h = 31 * h + HashCommon.double2int(k)) {
         k = i.nextDouble();
      }

      return h;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof List)) {
         return false;
      } else {
         List<?> l = (List)o;
         int s = this.size();
         if (s != l.size()) {
            return false;
         } else if (l instanceof DoubleList) {
            DoubleListIterator i1 = this.listIterator();
            DoubleListIterator i2 = ((DoubleList)l).listIterator();

            while(s-- != 0) {
               if (i1.nextDouble() != i2.nextDouble()) {
                  return false;
               }
            }

            return true;
         } else {
            ListIterator<?> i1 = this.listIterator();
            ListIterator<?> i2 = l.listIterator();

            while(s-- != 0) {
               if (!Objects.equals(i1.next(), i2.next())) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int compareTo(List l) {
      if (l == this) {
         return 0;
      } else if (l instanceof DoubleList) {
         DoubleListIterator i1 = this.listIterator();
         DoubleListIterator i2 = ((DoubleList)l).listIterator();

         while(i1.hasNext() && i2.hasNext()) {
            double e1 = i1.nextDouble();
            double e2 = i2.nextDouble();
            int r;
            if ((r = Double.compare(e1, e2)) != 0) {
               return r;
            }
         }

         return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
      } else {
         ListIterator<? extends Double> i1 = this.listIterator();
         ListIterator<? extends Double> i2 = l.listIterator();

         while(i1.hasNext() && i2.hasNext()) {
            int r;
            if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) {
               return r;
            }
         }

         return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
      }
   }

   public void push(double o) {
      this.add(o);
   }

   public double popDouble() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.removeDouble(this.size() - 1);
      }
   }

   public double topDouble() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.getDouble(this.size() - 1);
      }
   }

   public double peekDouble(int i) {
      return this.getDouble(this.size() - 1 - i);
   }

   public boolean rem(double k) {
      int index = this.indexOf(k);
      if (index == -1) {
         return false;
      } else {
         this.removeDouble(index);
         return true;
      }
   }

   public double[] toDoubleArray() {
      int size = this.size();
      if (size == 0) {
         return DoubleArrays.EMPTY_ARRAY;
      } else {
         double[] ret = new double[size];
         this.getElements(0, ret, 0, size);
         return ret;
      }
   }

   public double[] toArray(double[] a) {
      int size = this.size();
      if (a.length < size) {
         a = Arrays.copyOf(a, size);
      }

      this.getElements(0, a, 0, size);
      return a;
   }

   public boolean addAll(int index, DoubleCollection c) {
      this.ensureIndex(index);
      DoubleIterator i = c.iterator();
      boolean retVal = i.hasNext();

      while(i.hasNext()) {
         this.add(index++, i.nextDouble());
      }

      return retVal;
   }

   public boolean addAll(DoubleCollection c) {
      return this.addAll(this.size(), c);
   }

   public final void replaceAll(DoubleUnaryOperator operator) {
      this.replaceAll(operator);
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      DoubleIterator i = this.iterator();
      int n = this.size();
      boolean first = true;
      s.append("[");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         double k = i.nextDouble();
         s.append(String.valueOf(k));
      }

      s.append("]");
      return s.toString();
   }

   static final class IndexBasedSpliterator extends DoubleSpliterators.LateBindingSizeIndexBasedSpliterator {
      final DoubleList l;

      IndexBasedSpliterator(DoubleList l, int pos) {
         super(pos);
         this.l = l;
      }

      IndexBasedSpliterator(DoubleList l, int pos, int maxPos) {
         super(pos, maxPos);
         this.l = l;
      }

      protected final int getMaxPosFromBackingStore() {
         return this.l.size();
      }

      protected final double get(int i) {
         return this.l.getDouble(i);
      }

      protected final IndexBasedSpliterator makeForSplit(int pos, int maxPos) {
         return new IndexBasedSpliterator(this.l, pos, maxPos);
      }
   }

   public static class DoubleSubList extends AbstractDoubleList implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final DoubleList l;
      protected final int from;
      protected int to;

      public DoubleSubList(DoubleList l, int from, int to) {
         this.l = l;
         this.from = from;
         this.to = to;
      }

      private boolean assertRange() {
         assert this.from <= this.l.size();

         assert this.to <= this.l.size();

         assert this.to >= this.from;

         return true;
      }

      public boolean add(double k) {
         this.l.add(this.to, k);
         ++this.to;

         assert this.assertRange();

         return true;
      }

      public void add(int index, double k) {
         this.ensureIndex(index);
         this.l.add(this.from + index, k);
         ++this.to;

         assert this.assertRange();

      }

      public boolean addAll(int index, Collection c) {
         this.ensureIndex(index);
         this.to += c.size();
         return this.l.addAll(this.from + index, (Collection)c);
      }

      public double getDouble(int index) {
         this.ensureRestrictedIndex(index);
         return this.l.getDouble(this.from + index);
      }

      public double removeDouble(int index) {
         this.ensureRestrictedIndex(index);
         --this.to;
         return this.l.removeDouble(this.from + index);
      }

      public double set(int index, double k) {
         this.ensureRestrictedIndex(index);
         return this.l.set(this.from + index, k);
      }

      public int size() {
         return this.to - this.from;
      }

      public void getElements(int from, double[] a, int offset, int length) {
         this.ensureIndex(from);
         if (from + length > this.size()) {
            throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + this.size() + ")");
         } else {
            this.l.getElements(this.from + from, a, offset, length);
         }
      }

      public void removeElements(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         this.l.removeElements(this.from + from, this.from + to);
         this.to -= to - from;

         assert this.assertRange();

      }

      public void addElements(int index, double[] a, int offset, int length) {
         this.ensureIndex(index);
         this.l.addElements(this.from + index, a, offset, length);
         this.to += length;

         assert this.assertRange();

      }

      public void setElements(int index, double[] a, int offset, int length) {
         this.ensureIndex(index);
         this.l.setElements(this.from + index, a, offset, length);

         assert this.assertRange();

      }

      public DoubleListIterator listIterator(int index) {
         this.ensureIndex(index);
         return (DoubleListIterator)(this.l instanceof RandomAccess ? new RandomAccessIter(index) : new ParentWrappingIter(this.l.listIterator(index + this.from)));
      }

      public DoubleSpliterator spliterator() {
         return (DoubleSpliterator)(this.l instanceof RandomAccess ? new IndexBasedSpliterator(this.l, this.from, this.to) : super.spliterator());
      }

      public DoubleList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new DoubleSubList(this, from, to);
         }
      }

      public boolean rem(double k) {
         int index = this.indexOf(k);
         if (index == -1) {
            return false;
         } else {
            --this.to;
            this.l.removeDouble(this.from + index);

            assert this.assertRange();

            return true;
         }
      }

      public boolean addAll(int index, DoubleCollection c) {
         this.ensureIndex(index);
         return super.addAll(index, c);
      }

      public boolean addAll(int index, DoubleList l) {
         this.ensureIndex(index);
         return super.addAll(index, (DoubleList)l);
      }

      private final class RandomAccessIter extends DoubleIterators.AbstractIndexBasedListIterator {
         RandomAccessIter(int pos) {
            super(0, pos);
         }

         protected final double get(int i) {
            return DoubleSubList.this.l.getDouble(DoubleSubList.this.from + i);
         }

         protected final void add(int i, double k) {
            DoubleSubList.this.add(i, k);
         }

         protected final void set(int i, double k) {
            DoubleSubList.this.set(i, k);
         }

         protected final void remove(int i) {
            DoubleSubList.this.removeDouble(i);
         }

         protected final int getMaxPos() {
            return DoubleSubList.this.to - DoubleSubList.this.from;
         }

         public void add(double k) {
            super.add(k);

            assert DoubleSubList.this.assertRange();

         }

         public void remove() {
            super.remove();

            assert DoubleSubList.this.assertRange();

         }
      }

      private class ParentWrappingIter implements DoubleListIterator {
         private DoubleListIterator parent;

         ParentWrappingIter(DoubleListIterator parent) {
            this.parent = parent;
         }

         public int nextIndex() {
            return this.parent.nextIndex() - DoubleSubList.this.from;
         }

         public int previousIndex() {
            return this.parent.previousIndex() - DoubleSubList.this.from;
         }

         public boolean hasNext() {
            return this.parent.nextIndex() < DoubleSubList.this.to;
         }

         public boolean hasPrevious() {
            return this.parent.previousIndex() >= DoubleSubList.this.from;
         }

         public double nextDouble() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return this.parent.nextDouble();
            }
         }

         public double previousDouble() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return this.parent.previousDouble();
            }
         }

         public void add(double k) {
            this.parent.add(k);
         }

         public void set(double k) {
            this.parent.set(k);
         }

         public void remove() {
            this.parent.remove();
         }

         public int back(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int currentPos = this.parent.previousIndex();
               int parentNewPos = currentPos - n;
               if (parentNewPos < DoubleSubList.this.from - 1) {
                  parentNewPos = DoubleSubList.this.from - 1;
               }

               int toSkip = parentNewPos - currentPos;
               return this.parent.back(toSkip);
            }
         }

         public int skip(int n) {
            if (n < 0) {
               throw new IllegalArgumentException("Argument must be nonnegative: " + n);
            } else {
               int currentPos = this.parent.nextIndex();
               int parentNewPos = currentPos + n;
               if (parentNewPos > DoubleSubList.this.to) {
                  parentNewPos = DoubleSubList.this.to;
               }

               int toSkip = parentNewPos - currentPos;
               return this.parent.skip(toSkip);
            }
         }
      }
   }

   public static class DoubleRandomAccessSubList extends DoubleSubList implements RandomAccess {
      private static final long serialVersionUID = -107070782945191929L;

      public DoubleRandomAccessSubList(DoubleList l, int from, int to) {
         super(l, from, to);
      }

      public DoubleList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new DoubleRandomAccessSubList(this, from, to);
         }
      }
   }
}
