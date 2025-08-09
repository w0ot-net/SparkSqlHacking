package javolution.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import javolution.lang.Realtime;
import javolution.text.Text;
import javolution.xml.XMLSerializable;

public abstract class FastCollection implements Collection, XMLSerializable, Realtime {
   private static final Object NULL = new Object();

   protected FastCollection() {
   }

   public abstract int size();

   public abstract Record head();

   public abstract Record tail();

   public abstract Object valueOf(Record var1);

   public abstract void delete(Record var1);

   public Collection unmodifiable() {
      return new Unmodifiable();
   }

   public Collection shared() {
      return new Shared();
   }

   public Iterator iterator() {
      return FastIterator.valueOf(this);
   }

   public FastComparator getValueComparator() {
      return FastComparator.DEFAULT;
   }

   public boolean add(Object value) {
      throw new UnsupportedOperationException();
   }

   public boolean remove(Object value) {
      FastComparator valueComp = this.getValueComparator();
      Record r = this.head();
      Record end = this.tail();

      while((r = r.getNext()) != end) {
         if (valueComp.areEqual(value, this.valueOf(r))) {
            this.delete(r);
            return true;
         }
      }

      return false;
   }

   public void clear() {
      Record head = this.head();

      for(Record r = this.tail().getPrevious(); r != head; r = r.getPrevious()) {
         this.delete(r);
      }

   }

   public final boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean contains(Object value) {
      FastComparator valueComp = this.getValueComparator();
      Record r = this.head();
      Record end = this.tail();

      while((r = r.getNext()) != end) {
         if (valueComp.areEqual(value, this.valueOf(r))) {
            return true;
         }
      }

      return false;
   }

   public boolean addAll(Collection c) {
      boolean modified = false;
      Iterator<? extends E> itr = c.iterator();

      while(itr.hasNext()) {
         if (this.add(itr.next())) {
            modified = true;
         }
      }

      return modified;
   }

   public boolean containsAll(Collection c) {
      Iterator<?> itr = c.iterator();

      while(itr.hasNext()) {
         if (!this.contains(itr.next())) {
            return false;
         }
      }

      return true;
   }

   public boolean removeAll(Collection c) {
      boolean modified = false;
      Record head = this.head();

      Record previous;
      for(Record r = this.tail().getPrevious(); r != head; r = previous) {
         previous = r.getPrevious();
         if (contains(c, this.valueOf(r), this.getValueComparator())) {
            this.delete(r);
            modified = true;
         }
      }

      return modified;
   }

   private static boolean contains(Collection c, Object obj, FastComparator cmp) {
      if (c instanceof FastCollection && ((FastCollection)c).getValueComparator().equals(cmp)) {
         return c.contains(obj);
      } else {
         Iterator<?> itr = c.iterator();

         while(itr.hasNext()) {
            if (cmp.areEqual(obj, itr.next())) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean retainAll(Collection c) {
      boolean modified = false;
      Record head = this.head();

      Record previous;
      for(Record r = this.tail().getPrevious(); r != head; r = previous) {
         previous = r.getPrevious();
         if (!contains(c, this.valueOf(r), this.getValueComparator())) {
            this.delete(r);
            modified = true;
         }
      }

      return modified;
   }

   public Object[] toArray() {
      return this.toArray(new Object[this.size()]);
   }

   public Object[] toArray(Object[] array) {
      int size = this.size();
      if (array.length < size) {
         throw new UnsupportedOperationException("Destination array too small");
      } else {
         if (array.length > size) {
            array[size] = null;
         }

         int i = 0;
         Object[] arrayView = array;
         Record r = this.head();

         for(Record end = this.tail(); (r = r.getNext()) != end; arrayView[i++] = this.valueOf(r)) {
         }

         return array;
      }
   }

   public Text toText() {
      Text text = Text.valueOf((Object)"{");
      Record r = this.head();
      Record end = this.tail();

      while((r = r.getNext()) != end) {
         text = text.plus(this.valueOf(r));
         if (r.getNext() != end) {
            text = text.plus(", ");
         }
      }

      return text.plus("}");
   }

   public final String toString() {
      return this.toText().toString();
   }

   public boolean equals(Object obj) {
      if (this instanceof List) {
         return obj instanceof List ? this.equalsOrder((List)obj) : false;
      } else if (obj instanceof List) {
         return false;
      } else if (!(obj instanceof Collection)) {
         return false;
      } else {
         Collection that = (Collection)obj;
         return this == that || this.size() == that.size() && this.containsAll(that);
      }
   }

   private boolean equalsOrder(List that) {
      if (that == this) {
         return true;
      } else if (this.size() != that.size()) {
         return false;
      } else {
         Iterator thatIterator = that.iterator();
         FastComparator comp = this.getValueComparator();
         Record r = this.head();
         Record end = this.tail();

         while((r = r.getNext()) != end) {
            Object o1 = this.valueOf(r);
            Object o2 = thatIterator.next();
            if (!comp.areEqual(o1, o2)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      if (this instanceof List) {
         return this.hashCodeList();
      } else {
         FastComparator valueComp = this.getValueComparator();
         int hash = 0;
         Record r = this.head();

         for(Record end = this.tail(); (r = r.getNext()) != end; hash += valueComp.hashCodeOf(this.valueOf(r))) {
         }

         return hash;
      }
   }

   private int hashCodeList() {
      FastComparator comp = this.getValueComparator();
      int h = 1;
      Record r = this.head();

      for(Record end = this.tail(); (r = r.getNext()) != end; h = 31 * h + comp.hashCodeOf(this.valueOf(r))) {
      }

      return h;
   }

   class Unmodifiable extends FastCollection implements List, Set {
      public int size() {
         return FastCollection.this.size();
      }

      public Record head() {
         return FastCollection.this.head();
      }

      public Record tail() {
         return FastCollection.this.tail();
      }

      public Object valueOf(Record record) {
         return FastCollection.this.valueOf(record);
      }

      public boolean contains(Object value) {
         return FastCollection.this.contains(value);
      }

      public boolean containsAll(Collection c) {
         return FastCollection.this.containsAll(c);
      }

      public FastComparator getValueComparator() {
         return FastCollection.this.getValueComparator();
      }

      public boolean add(Object obj) {
         throw new UnsupportedOperationException("Unmodifiable");
      }

      public void delete(Record node) {
         throw new UnsupportedOperationException("Unmodifiable");
      }

      public boolean addAll(int index, Collection c) {
         throw new UnsupportedOperationException("Unmodifiable");
      }

      public Object get(int index) {
         return ((List)FastCollection.this).get(index);
      }

      public Object set(int index, Object element) {
         throw new UnsupportedOperationException("Unmodifiable");
      }

      public void add(int index, Object element) {
         throw new UnsupportedOperationException("Unmodifiable");
      }

      public Object remove(int index) {
         throw new UnsupportedOperationException("Unmodifiable");
      }

      public int indexOf(Object o) {
         return ((List)FastCollection.this).indexOf(o);
      }

      public int lastIndexOf(Object o) {
         return ((List)FastCollection.this).lastIndexOf(o);
      }

      public ListIterator listIterator() {
         throw new UnsupportedOperationException("List iterator not supported for unmodifiable collection");
      }

      public ListIterator listIterator(int index) {
         throw new UnsupportedOperationException("List iterator not supported for unmodifiable collection");
      }

      public List subList(int fromIndex, int toIndex) {
         throw new UnsupportedOperationException("Sub-List not supported for unmodifiable collection");
      }
   }

   private class Shared implements Collection, Serializable {
      private Shared() {
      }

      public synchronized int size() {
         return FastCollection.this.size();
      }

      public synchronized boolean isEmpty() {
         return FastCollection.this.isEmpty();
      }

      public synchronized boolean contains(Object o) {
         return FastCollection.this.contains(o);
      }

      public synchronized Object[] toArray() {
         return FastCollection.this.toArray();
      }

      public synchronized Object[] toArray(Object[] a) {
         return FastCollection.this.toArray(a);
      }

      public synchronized Iterator iterator() {
         return (Iterator)(FastCollection.this instanceof List ? new ListArrayIterator(FastCollection.this.toArray()) : new CollectionArrayIterator(FastCollection.this.toArray()));
      }

      public synchronized boolean add(Object e) {
         return FastCollection.this.add(e);
      }

      public synchronized boolean remove(Object o) {
         return FastCollection.this.remove(o);
      }

      public synchronized boolean containsAll(Collection c) {
         return FastCollection.this.containsAll(c);
      }

      public synchronized boolean addAll(Collection c) {
         return FastCollection.this.addAll(c);
      }

      public synchronized boolean removeAll(Collection c) {
         return FastCollection.this.removeAll(c);
      }

      public synchronized boolean retainAll(Collection c) {
         return FastCollection.this.retainAll(c);
      }

      public synchronized void clear() {
         FastCollection.this.clear();
      }

      public synchronized String toString() {
         return FastCollection.this.toString();
      }

      private synchronized void writeObject(ObjectOutputStream s) throws IOException {
         s.defaultWriteObject();
      }

      private class ListArrayIterator implements Iterator {
         private final Object[] _elements;
         private int _index;
         private int _removed;

         public ListArrayIterator(Object[] elements) {
            this._elements = elements;
         }

         public boolean hasNext() {
            return this._index < this._elements.length;
         }

         public Object next() {
            return this._elements[this._index++];
         }

         public void remove() {
            if (this._index == 0) {
               throw new IllegalStateException();
            } else {
               Object removed = this._elements[this._index - 1];
               if (removed == FastCollection.NULL) {
                  throw new IllegalStateException();
               } else {
                  this._elements[this._index - 1] = FastCollection.NULL;
                  ++this._removed;
                  synchronized(Shared.this) {
                     ((List)FastCollection.this).remove(this._index - this._removed);
                  }
               }
            }
         }
      }

      private class CollectionArrayIterator implements Iterator {
         private final Object[] _elements;
         private int _index;
         private Object _next;

         public CollectionArrayIterator(Object[] elements) {
            this._elements = elements;
         }

         public boolean hasNext() {
            return this._index < this._elements.length;
         }

         public Object next() {
            return this._next = this._elements[this._index++];
         }

         public void remove() {
            if (this._next == null) {
               throw new IllegalStateException();
            } else {
               Shared.this.remove(this._next);
               this._next = null;
            }
         }
      }
   }

   public interface Record {
      Record getPrevious();

      Record getNext();
   }
}
