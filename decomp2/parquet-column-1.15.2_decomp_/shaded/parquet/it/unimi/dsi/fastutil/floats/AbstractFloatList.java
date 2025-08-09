package shaded.parquet.it.unimi.dsi.fastutil.floats;

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

public abstract class AbstractFloatList extends AbstractFloatCollection implements FloatList, FloatStack {
   protected AbstractFloatList() {
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

   public void add(int index, float k) {
      throw new UnsupportedOperationException();
   }

   public boolean add(float k) {
      this.add(this.size(), k);
      return true;
   }

   public float removeFloat(int i) {
      throw new UnsupportedOperationException();
   }

   public float set(int index, float k) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(int index, Collection c) {
      if (c instanceof FloatCollection) {
         return this.addAll(index, (FloatCollection)c);
      } else {
         this.ensureIndex(index);
         Iterator<? extends Float> i = c.iterator();
         boolean retVal = i.hasNext();

         while(i.hasNext()) {
            this.add(index++, (Float)i.next());
         }

         return retVal;
      }
   }

   public boolean addAll(Collection c) {
      return this.addAll(this.size(), c);
   }

   public FloatListIterator iterator() {
      return this.listIterator();
   }

   public FloatListIterator listIterator() {
      return this.listIterator(0);
   }

   public FloatListIterator listIterator(int index) {
      this.ensureIndex(index);
      return new FloatIterators.AbstractIndexBasedListIterator(0, index) {
         protected final float get(int i) {
            return AbstractFloatList.this.getFloat(i);
         }

         protected final void add(int i, float k) {
            AbstractFloatList.this.add(i, k);
         }

         protected final void set(int i, float k) {
            AbstractFloatList.this.set(i, k);
         }

         protected final void remove(int i) {
            AbstractFloatList.this.removeFloat(i);
         }

         protected final int getMaxPos() {
            return AbstractFloatList.this.size();
         }
      };
   }

   public boolean contains(float k) {
      return this.indexOf(k) >= 0;
   }

   public int indexOf(float k) {
      FloatListIterator i = this.listIterator();

      while(i.hasNext()) {
         float e = i.nextFloat();
         if (Float.floatToIntBits(k) == Float.floatToIntBits(e)) {
            return i.previousIndex();
         }
      }

      return -1;
   }

   public int lastIndexOf(float k) {
      FloatListIterator i = this.listIterator(this.size());

      while(i.hasPrevious()) {
         float e = i.previousFloat();
         if (Float.floatToIntBits(k) == Float.floatToIntBits(e)) {
            return i.nextIndex();
         }
      }

      return -1;
   }

   public void size(int size) {
      int i = this.size();
      if (size > i) {
         while(i++ < size) {
            this.add(0.0F);
         }
      } else {
         while(i-- != size) {
            this.removeFloat(i);
         }
      }

   }

   public FloatList subList(int from, int to) {
      this.ensureIndex(from);
      this.ensureIndex(to);
      if (from > to) {
         throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
      } else {
         return (FloatList)(this instanceof RandomAccess ? new FloatRandomAccessSubList(this, from, to) : new FloatSubList(this, from, to));
      }
   }

   public void forEach(FloatConsumer action) {
      if (this instanceof RandomAccess) {
         int i = 0;

         for(int max = this.size(); i < max; ++i) {
            action.accept(this.getFloat(i));
         }
      } else {
         FloatList.super.forEach(action);
      }

   }

   public void removeElements(int from, int to) {
      this.ensureIndex(to);
      FloatListIterator i = this.listIterator(from);
      int n = to - from;
      if (n < 0) {
         throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
      } else {
         while(n-- != 0) {
            i.nextFloat();
            i.remove();
         }

      }
   }

   public void addElements(int index, float[] a, int offset, int length) {
      this.ensureIndex(index);
      FloatArrays.ensureOffsetLength(a, offset, length);
      if (this instanceof RandomAccess) {
         while(length-- != 0) {
            this.add(index++, a[offset++]);
         }
      } else {
         FloatListIterator iter = this.listIterator(index);

         while(length-- != 0) {
            iter.add(a[offset++]);
         }
      }

   }

   public void addElements(int index, float[] a) {
      this.addElements(index, a, 0, a.length);
   }

   public void getElements(int from, float[] a, int offset, int length) {
      this.ensureIndex(from);
      FloatArrays.ensureOffsetLength(a, offset, length);
      if (from + length > this.size()) {
         throw new IndexOutOfBoundsException("End index (" + (from + length) + ") is greater than list size (" + this.size() + ")");
      } else {
         if (this instanceof RandomAccess) {
            for(int current = from; length-- != 0; a[offset++] = this.getFloat(current++)) {
            }
         } else {
            for(FloatListIterator i = this.listIterator(from); length-- != 0; a[offset++] = i.nextFloat()) {
            }
         }

      }
   }

   public void setElements(int index, float[] a, int offset, int length) {
      this.ensureIndex(index);
      FloatArrays.ensureOffsetLength(a, offset, length);
      if (index + length > this.size()) {
         throw new IndexOutOfBoundsException("End index (" + (index + length) + ") is greater than list size (" + this.size() + ")");
      } else {
         if (this instanceof RandomAccess) {
            for(int i = 0; i < length; ++i) {
               this.set(i + index, a[i + offset]);
            }
         } else {
            FloatListIterator iter = this.listIterator(index);
            int i = 0;

            while(i < length) {
               iter.nextFloat();
               iter.set(a[offset + i++]);
            }
         }

      }
   }

   public void clear() {
      this.removeElements(0, this.size());
   }

   public int hashCode() {
      FloatIterator i = this.iterator();
      int h = 1;

      float k;
      for(int s = this.size(); s-- != 0; h = 31 * h + HashCommon.float2int(k)) {
         k = i.nextFloat();
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
         } else if (l instanceof FloatList) {
            FloatListIterator i1 = this.listIterator();
            FloatListIterator i2 = ((FloatList)l).listIterator();

            while(s-- != 0) {
               if (i1.nextFloat() != i2.nextFloat()) {
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
      } else if (l instanceof FloatList) {
         FloatListIterator i1 = this.listIterator();
         FloatListIterator i2 = ((FloatList)l).listIterator();

         while(i1.hasNext() && i2.hasNext()) {
            float e1 = i1.nextFloat();
            float e2 = i2.nextFloat();
            int r;
            if ((r = Float.compare(e1, e2)) != 0) {
               return r;
            }
         }

         return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
      } else {
         ListIterator<? extends Float> i1 = this.listIterator();
         ListIterator<? extends Float> i2 = l.listIterator();

         while(i1.hasNext() && i2.hasNext()) {
            int r;
            if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) {
               return r;
            }
         }

         return i2.hasNext() ? -1 : (i1.hasNext() ? 1 : 0);
      }
   }

   public void push(float o) {
      this.add(o);
   }

   public float popFloat() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.removeFloat(this.size() - 1);
      }
   }

   public float topFloat() {
      if (this.isEmpty()) {
         throw new NoSuchElementException();
      } else {
         return this.getFloat(this.size() - 1);
      }
   }

   public float peekFloat(int i) {
      return this.getFloat(this.size() - 1 - i);
   }

   public boolean rem(float k) {
      int index = this.indexOf(k);
      if (index == -1) {
         return false;
      } else {
         this.removeFloat(index);
         return true;
      }
   }

   public float[] toFloatArray() {
      int size = this.size();
      if (size == 0) {
         return FloatArrays.EMPTY_ARRAY;
      } else {
         float[] ret = new float[size];
         this.getElements(0, ret, 0, size);
         return ret;
      }
   }

   public float[] toArray(float[] a) {
      int size = this.size();
      if (a.length < size) {
         a = Arrays.copyOf(a, size);
      }

      this.getElements(0, a, 0, size);
      return a;
   }

   public boolean addAll(int index, FloatCollection c) {
      this.ensureIndex(index);
      FloatIterator i = c.iterator();
      boolean retVal = i.hasNext();

      while(i.hasNext()) {
         this.add(index++, i.nextFloat());
      }

      return retVal;
   }

   public boolean addAll(FloatCollection c) {
      return this.addAll(this.size(), c);
   }

   public String toString() {
      StringBuilder s = new StringBuilder();
      FloatIterator i = this.iterator();
      int n = this.size();
      boolean first = true;
      s.append("[");

      while(n-- != 0) {
         if (first) {
            first = false;
         } else {
            s.append(", ");
         }

         float k = i.nextFloat();
         s.append(String.valueOf(k));
      }

      s.append("]");
      return s.toString();
   }

   static final class IndexBasedSpliterator extends FloatSpliterators.LateBindingSizeIndexBasedSpliterator {
      final FloatList l;

      IndexBasedSpliterator(FloatList l, int pos) {
         super(pos);
         this.l = l;
      }

      IndexBasedSpliterator(FloatList l, int pos, int maxPos) {
         super(pos, maxPos);
         this.l = l;
      }

      protected final int getMaxPosFromBackingStore() {
         return this.l.size();
      }

      protected final float get(int i) {
         return this.l.getFloat(i);
      }

      protected final IndexBasedSpliterator makeForSplit(int pos, int maxPos) {
         return new IndexBasedSpliterator(this.l, pos, maxPos);
      }
   }

   public static class FloatSubList extends AbstractFloatList implements Serializable {
      private static final long serialVersionUID = -7046029254386353129L;
      protected final FloatList l;
      protected final int from;
      protected int to;

      public FloatSubList(FloatList l, int from, int to) {
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

      public boolean add(float k) {
         this.l.add(this.to, k);
         ++this.to;

         assert this.assertRange();

         return true;
      }

      public void add(int index, float k) {
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

      public float getFloat(int index) {
         this.ensureRestrictedIndex(index);
         return this.l.getFloat(this.from + index);
      }

      public float removeFloat(int index) {
         this.ensureRestrictedIndex(index);
         --this.to;
         return this.l.removeFloat(this.from + index);
      }

      public float set(int index, float k) {
         this.ensureRestrictedIndex(index);
         return this.l.set(this.from + index, k);
      }

      public int size() {
         return this.to - this.from;
      }

      public void getElements(int from, float[] a, int offset, int length) {
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

      public void addElements(int index, float[] a, int offset, int length) {
         this.ensureIndex(index);
         this.l.addElements(this.from + index, a, offset, length);
         this.to += length;

         assert this.assertRange();

      }

      public void setElements(int index, float[] a, int offset, int length) {
         this.ensureIndex(index);
         this.l.setElements(this.from + index, a, offset, length);

         assert this.assertRange();

      }

      public FloatListIterator listIterator(int index) {
         this.ensureIndex(index);
         return (FloatListIterator)(this.l instanceof RandomAccess ? new RandomAccessIter(index) : new ParentWrappingIter(this.l.listIterator(index + this.from)));
      }

      public FloatSpliterator spliterator() {
         return (FloatSpliterator)(this.l instanceof RandomAccess ? new IndexBasedSpliterator(this.l, this.from, this.to) : super.spliterator());
      }

      public FloatList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new FloatSubList(this, from, to);
         }
      }

      public boolean rem(float k) {
         int index = this.indexOf(k);
         if (index == -1) {
            return false;
         } else {
            --this.to;
            this.l.removeFloat(this.from + index);

            assert this.assertRange();

            return true;
         }
      }

      public boolean addAll(int index, FloatCollection c) {
         this.ensureIndex(index);
         return super.addAll(index, c);
      }

      public boolean addAll(int index, FloatList l) {
         this.ensureIndex(index);
         return super.addAll(index, (FloatList)l);
      }

      private final class RandomAccessIter extends FloatIterators.AbstractIndexBasedListIterator {
         RandomAccessIter(int pos) {
            super(0, pos);
         }

         protected final float get(int i) {
            return FloatSubList.this.l.getFloat(FloatSubList.this.from + i);
         }

         protected final void add(int i, float k) {
            FloatSubList.this.add(i, k);
         }

         protected final void set(int i, float k) {
            FloatSubList.this.set(i, k);
         }

         protected final void remove(int i) {
            FloatSubList.this.removeFloat(i);
         }

         protected final int getMaxPos() {
            return FloatSubList.this.to - FloatSubList.this.from;
         }

         public void add(float k) {
            super.add(k);

            assert FloatSubList.this.assertRange();

         }

         public void remove() {
            super.remove();

            assert FloatSubList.this.assertRange();

         }
      }

      private class ParentWrappingIter implements FloatListIterator {
         private FloatListIterator parent;

         ParentWrappingIter(FloatListIterator parent) {
            this.parent = parent;
         }

         public int nextIndex() {
            return this.parent.nextIndex() - FloatSubList.this.from;
         }

         public int previousIndex() {
            return this.parent.previousIndex() - FloatSubList.this.from;
         }

         public boolean hasNext() {
            return this.parent.nextIndex() < FloatSubList.this.to;
         }

         public boolean hasPrevious() {
            return this.parent.previousIndex() >= FloatSubList.this.from;
         }

         public float nextFloat() {
            if (!this.hasNext()) {
               throw new NoSuchElementException();
            } else {
               return this.parent.nextFloat();
            }
         }

         public float previousFloat() {
            if (!this.hasPrevious()) {
               throw new NoSuchElementException();
            } else {
               return this.parent.previousFloat();
            }
         }

         public void add(float k) {
            this.parent.add(k);
         }

         public void set(float k) {
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
               if (parentNewPos < FloatSubList.this.from - 1) {
                  parentNewPos = FloatSubList.this.from - 1;
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
               if (parentNewPos > FloatSubList.this.to) {
                  parentNewPos = FloatSubList.this.to;
               }

               int toSkip = parentNewPos - currentPos;
               return this.parent.skip(toSkip);
            }
         }
      }
   }

   public static class FloatRandomAccessSubList extends FloatSubList implements RandomAccess {
      private static final long serialVersionUID = -107070782945191929L;

      public FloatRandomAccessSubList(FloatList l, int from, int to) {
         super(l, from, to);
      }

      public FloatList subList(int from, int to) {
         this.ensureIndex(from);
         this.ensureIndex(to);
         if (from > to) {
            throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
         } else {
            return new FloatRandomAccessSubList(this, from, to);
         }
      }
   }
}
