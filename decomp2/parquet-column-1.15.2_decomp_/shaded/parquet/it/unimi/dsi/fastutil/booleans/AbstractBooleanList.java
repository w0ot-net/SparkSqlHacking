package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;

public abstract class AbstractBooleanList extends AbstractBooleanCollection implements BooleanList, BooleanStack {
   protected AbstractBooleanList() {
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

   public void add(int index, boolean k) {
      throw new UnsupportedOperationException();
   }

   public boolean add(boolean k) {
      this.add(this.size(), k);
      return true;
   }

   public boolean removeBoolean(int i) {
      throw new UnsupportedOperationException();
   }

   public boolean set(int index, boolean k) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(int index, Collection c) {
      if (c instanceof BooleanCollection) {
         return this.addAll(index, (BooleanCollection)c);
      } else {
         this.ensureIndex(index);
         Iterator<? extends Boolean> i = c.iterator();
         boolean retVal = i.hasNext();

         while(i.hasNext()) {
            this.add(index++, (Boolean)i.next());
         }

         return retVal;
      }
   }

   public boolean addAll(Collection c) {
      return this.addAll(this.size(), c);
   }

   public BooleanListIterator iterator() {
      return this.listIterator();
   }

   public BooleanListIterator listIterator() {
      return this.listIterator(0);
   }

   public BooleanListIterator listIterator(int index) {
      this.ensureIndex(index);
      return new BooleanIterators.AbstractIndexBasedListIterator(0, index) {
         protected final boolean get(int i) {
            return AbstractBooleanList.this.getBoolean(i);
         }

         protected final void add(int i, boolean k) {
            AbstractBooleanList.this.add(i, k);
         }

         protected final void set(int i, boolean k) {
            AbstractBooleanList.this.set(i, k);
         }

         protected final void remove(int i) {
            AbstractBooleanList.this.removeBoolean(i);
         }

         protected final int getMaxPos() {
            return AbstractBooleanList.this.size();
         }
      };
   }

   public boolean contains(boolean k) {
      return this.indexOf(k) >= 0;
   }

   public int indexOf(boolean k) {
      BooleanListIterator i = this.listIterator();

      while(i.hasNext()) {
         boolean e = i.nextBoolean();
         if (k == e) {
            return i.previousIndex();
         }
      }

      return -1;
   }

   public int lastIndexOf(boolean k) {
      BooleanListIterator i = this.listIterator(this.size());

      while(i.hasPrevious()) {
         boolean e = i.previousBoolean();
         if (k == e) {
            return i.nextIndex();
         }
      }

      return -1;
   }

   public void size(int size) {
      int i = this.size();
      if (size > i) {
         while(i++ < size) {
            this.add(false);
         }
      } else {
         while(i-- != size) {
            this.removeBoolean(i);
         }
      }

   }

   public BooleanList subList(int from, int to) {
      this.ensureIndex(from);
      this.ensureIndex(to);
      if (from > to) {
         throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
      } else {
         return (BooleanList)(this instanceof RandomAccess ? new BooleanRandomAccessSubList(this, from, to) : new BooleanSubList(this, from, to));
      }
   }

   public void forEach(BooleanConsumer action) {
      if (this instanceof RandomAccess) {
         int i = 0;

         for(int max = this.size(); i < max; ++i) {
            action.accept(this.getBoolean(i));
         }
      } else {
         BooleanList.super.forEach(action);
      }

   }

   public void removeElements(int from, int to) {
      this.ensureIndex(to);
      BooleanListIterator i = this.listIterator(from);
      int n = to - from;
      if (n < 0) {
         throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
      } else {
         while(n-- != 0) {
            i.nextBoolean();
            i.remove();
         }

      }
   }

   public void addElements(int index, boolean[] a, int offset, int length) {
      this.ensureIndex(index);
      BooleanArrays.ensureOffsetLength(a, offset, length);
      if (this instanceof RandomAccess) {
         while(length-- != 0) {
            this.add(index++, a[offset++]);
         }
      } else {
         BooleanListIterator iter = this.listIterator(index);

         while(length-- != 0) {
            iter.add(a[offset++]);
         }
      }

   }

   public void addElements(int index, boolean[] a) {
      this.addElements(index, a, 0, a.length);
   }

   public void getElements(int from, boolean[] a, int offset, int length) {
      this.ensureIndex(from);
      BooleanArrays.ensureOffsetLength(a, offset, length);
      if (from + length > this.size()) {
         throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + this.size() + ")");
      } else {
         if (this instanceof RandomAccess) {
            for(int current = from; length-- != 0; a[offset++] = this.getBoolean(current++)) {
            }
         } else {
            for(BooleanListIterator i = this.listIterator(from); length-- != 0; a[offset++] = i.nextBoolean()) {
            }
         }

      }
   }

   public void setElements(int index, boolean[] a, int offset, int length) {
      this.ensureIndex(index);
      BooleanArrays.ensureOffsetLength(a, offset, length);
      if (index + length > this.size()) {
         throw new IndexOutOfBoundsException("End index (" + (index + length) + ") is greater than list size (" + this.size() + ")");
      } else {
         if (this instanceof RandomAccess) {
            for(int i = 0; i < length; ++i) {
               this.set(i + index, a[i + offset]);
            }
         } else {
            BooleanListIterator iter = this.listIterator(index);
            int i = 0;

            while(i < length) {
               iter.nextBoolean();
               iter.set(a[offset + i++]);
            }
         }

      }
   }

   public void clear() {
      this.removeElements(0, this.size());
   }

   public int hashCode() {
      BooleanIterator i = this.iterator();
      int h = 1;

      boolean k;
      for(int s = this.size(); s-- != 0; h = 31 * h + (k ? 1231 : 1237)) {
         k = i.nextBoolean();
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
         } else if (l instanceof BooleanList) {
            BooleanListIterator i1 = this.listIterator();
            BooleanListIterator i2 = ((BooleanList)l).listIterator();

            while(s-- != 0) {
               if (i1.nextBoolean() != i2.nextBoolean()) {
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
      } else if (l instanceof BooleanList) {
         BooleanListIterator i1 = this.listIterator();
         BooleanListIterator i2 = ((BooleanList)l).listIterator();

         while(i1.hasNext() && i2.hasNext()) {
            boolean e1 = i1.nextBoolean();
            boolean e2 = i2.nextBoolean();
            int r;
            if ((r = Boolean.compare(e1, e2)) != 0) {
               return r;
            }
         }

         return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
      } else {
         ListIterator<? extends Boolean> i1 = this.listIterator();
         ListIterator<? extends Boolean> i2 = l.listIterator();

         while(i1.hasNext() && i2.hasNext()) {
            int r;
            if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) {
               return r;
            }
         }

         return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
      }
   }

   public void push(boolean o) {
      this.add(o);
   }

   public boolean popBoolean() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.removeBoolean(this.size() - 1);
      }
   }

   public boolean topBoolean() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.getBoolean(this.size() - 1);
      }
   }

   public boolean peekBoolean(int i) {
      return this.getBoolean(this.size() - 1 - i);
   }

   public boolean rem(boolean k) {
      int index = this.indexOf(k);
      if (index == -1) {
         return false;
      } else {
         this.removeBoolean(index);
         return true;
      }
   }

   public boolean[] toBooleanArray() {
      int size = this.size();
      if (size == 0) {
         return BooleanArrays.EMPTY_ARRAY;
      } else {
         boolean[] ret = new boolean[size];
         this.getElements(0, ret, 0, size);
         return ret;
      }
   }

   public boolean[] toArray(boolean[] a) {
      int size = this.size();
      if (a.length < size) {
         a = Arrays.copyOf(a, size);
      }

      this.getElements(0, a, 0, size);
      return a;
   }

   public boolean addAll(int index, BooleanCollection c) {
      this.ensureIndex(index);
      BooleanIterator i = c.iterator();
      boolean retVal = i.hasNext();

      while(i.hasNext()) {
         this.add(index++, i.nextBoolean());
      }

      return retVal;
   }

   public boolean addAll(BooleanCollection c) {
      return this.addAll(this.size(), c);
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      BooleanIterator i = this.iterator();
      int n = this.size();
      boolean first = true;
      s.append("[");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         boolean k = i.nextBoolean();
         s.append(String.valueOf(k));
      }

      s.append("]");
      return s.toString();
   }

   static final class IndexBasedSpliterator extends BooleanSpliterators.LateBindingSizeIndexBasedSpliterator {
      final BooleanList l;

      IndexBasedSpliterator(BooleanList l, int pos) {
         super(pos);
         this.l = l;
      }

      IndexBasedSpliterator(BooleanList l, int pos, int maxPos) {
         super(pos, maxPos);
         this.l = l;
      }

      protected final int getMaxPosFromBackingStore() {
         return this.l.size();
      }

      protected final boolean get(int i) {
         return this.l.getBoolean(i);
      }

      protected final IndexBasedSpliterator makeForSplit(int pos, int maxPos) {
         return new IndexBasedSpliterator(this.l, pos, maxPos);
      }
   }

   public static class BooleanSubList extends AbstractBooleanList implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final BooleanList l;
      protected final int from;
      protected int to;

      public BooleanSubList(BooleanList l, int from, int to) {
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

      public boolean add(boolean k) {
         this.l.add(this.to, k);
         ++this.to;

         assert this.assertRange();

         return true;
      }

      public void add(int index, boolean k) {
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

      public boolean getBoolean(int index) {
         this.ensureRestrictedIndex(index);
         return this.l.getBoolean(this.from + index);
      }

      public boolean removeBoolean(int index) {
         this.ensureRestrictedIndex(index);
         --this.to;
         return this.l.removeBoolean(this.from + index);
      }

      public boolean set(int index, boolean k) {
         this.ensureRestrictedIndex(index);
         return this.l.set(this.from + index, k);
      }

      public int size() {
         return this.to - this.from;
      }

      public void getElements(int from, boolean[] a, int offset, int length) {
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

      public void addElements(int index, boolean[] a, int offset, int length) {
         this.ensureIndex(index);
         this.l.addElements(this.from + index, a, offset, length);
         this.to += length;

         assert this.assertRange();

      }

      public void setElements(int index, boolean[] a, int offset, int length) {
         this.ensureIndex(index);
         this.l.setElements(this.from + index, a, offset, length);

         assert this.assertRange();

      }

      public BooleanListIterator listIterator(int index) {
         this.ensureIndex(index);
         return (BooleanListIterator)(this.l instanceof RandomAccess ? new RandomAccessIter(index) : new ParentWrappingIter(this.l.listIterator(index + this.from)));
      }

      public BooleanSpliterator spliterator() {
         return (BooleanSpliterator)(this.l instanceof RandomAccess ? new IndexBasedSpliterator(this.l, this.from, this.to) : super.spliterator());
      }

      public BooleanList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new BooleanSubList(this, from, to);
         }
      }

      public boolean rem(boolean k) {
         int index = this.indexOf(k);
         if (index == -1) {
            return false;
         } else {
            --this.to;
            this.l.removeBoolean(this.from + index);

            assert this.assertRange();

            return true;
         }
      }

      public boolean addAll(int index, BooleanCollection c) {
         this.ensureIndex(index);
         return super.addAll(index, c);
      }

      public boolean addAll(int index, BooleanList l) {
         this.ensureIndex(index);
         return super.addAll(index, (BooleanList)l);
      }

      private final class RandomAccessIter extends BooleanIterators.AbstractIndexBasedListIterator {
         RandomAccessIter(int pos) {
            super(0, pos);
         }

         protected final boolean get(int i) {
            return BooleanSubList.this.l.getBoolean(BooleanSubList.this.from + i);
         }

         protected final void add(int i, boolean k) {
            BooleanSubList.this.add(i, k);
         }

         protected final void set(int i, boolean k) {
            BooleanSubList.this.set(i, k);
         }

         protected final void remove(int i) {
            BooleanSubList.this.removeBoolean(i);
         }

         protected final int getMaxPos() {
            return BooleanSubList.this.to - BooleanSubList.this.from;
         }

         public void add(boolean k) {
            super.add(k);

            assert BooleanSubList.this.assertRange();

         }

         public void remove() {
            super.remove();

            assert BooleanSubList.this.assertRange();

         }
      }

      private class ParentWrappingIter implements BooleanListIterator {
         private BooleanListIterator parent;

         ParentWrappingIter(BooleanListIterator parent) {
            this.parent = parent;
         }

         public int nextIndex() {
            return this.parent.nextIndex() - BooleanSubList.this.from;
         }

         public int previousIndex() {
            return this.parent.previousIndex() - BooleanSubList.this.from;
         }

         public boolean hasNext() {
            return this.parent.nextIndex() < BooleanSubList.this.to;
         }

         public boolean hasPrevious() {
            return this.parent.previousIndex() >= BooleanSubList.this.from;
         }

         public boolean nextBoolean() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return this.parent.nextBoolean();
            }
         }

         public boolean previousBoolean() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return this.parent.previousBoolean();
            }
         }

         public void add(boolean k) {
            this.parent.add(k);
         }

         public void set(boolean k) {
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
               if (parentNewPos < BooleanSubList.this.from - 1) {
                  parentNewPos = BooleanSubList.this.from - 1;
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
               if (parentNewPos > BooleanSubList.this.to) {
                  parentNewPos = BooleanSubList.this.to;
               }

               int toSkip = parentNewPos - currentPos;
               return this.parent.skip(toSkip);
            }
         }
      }
   }

   public static class BooleanRandomAccessSubList extends BooleanSubList implements RandomAccess {
      private static final long serialVersionUID = -107070782945191929L;

      public BooleanRandomAccessSubList(BooleanList l, int from, int to) {
         super(l, from, to);
      }

      public BooleanList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new BooleanRandomAccessSubList(this, from, to);
         }
      }
   }
}
