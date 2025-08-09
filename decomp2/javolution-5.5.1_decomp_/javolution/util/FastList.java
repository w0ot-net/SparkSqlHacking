package javolution.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.context.PersistentContext;
import javolution.lang.Reusable;

public class FastList extends FastCollection implements List, Reusable {
   private static final ObjectFactory FACTORY = new ObjectFactory() {
      public Object create() {
         return new FastList();
      }
   };
   private transient Node _head;
   private transient Node _tail;
   private transient FastComparator _valueComparator;
   private transient int _size;
   private static final long serialVersionUID = 1L;

   public FastList() {
      this(4);
   }

   public FastList(String id) {
      // $FF: Couldn't be decompiled
   }

   public FastList(int capacity) {
      this._head = this.newNode();
      this._tail = this.newNode();
      this._valueComparator = FastComparator.DEFAULT;
      this._head._next = this._tail;
      this._tail._previous = this._head;
      Node<E> previous = this._tail;

      Node<E> newNode;
      for(int i = 0; i++ < capacity; previous = newNode) {
         newNode = this.newNode();
         newNode._previous = previous;
         previous._next = newNode;
      }

   }

   public FastList(Collection values) {
      this(values.size());
      this.addAll(values);
   }

   public static FastList newInstance() {
      return (FastList)FACTORY.object();
   }

   public static void recycle(FastList instance) {
      FACTORY.recycle(instance);
   }

   public final boolean add(Object value) {
      this.addLast(value);
      return true;
   }

   public final Object get(int index) {
      if (index >= 0 && index < this._size) {
         return this.nodeAt(index)._value;
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final Object set(int index, Object value) {
      if (index >= 0 && index < this._size) {
         Node<E> node = this.nodeAt(index);
         E previousValue = (E)node._value;
         node._value = value;
         return previousValue;
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final void add(int index, Object value) {
      if (index >= 0 && index <= this._size) {
         this.addBefore(this.nodeAt(index), value);
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final boolean addAll(int index, Collection values) {
      if (index >= 0 && index <= this._size) {
         Node indexNode = this.nodeAt(index);
         Iterator<? extends E> i = values.iterator();

         while(i.hasNext()) {
            this.addBefore(indexNode, i.next());
         }

         return values.size() != 0;
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final Object remove(int index) {
      if (index >= 0 && index < this._size) {
         Node<E> node = this.nodeAt(index);
         E previousValue = (E)node._value;
         this.delete(node);
         return previousValue;
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final int indexOf(Object value) {
      FastComparator comp = this.getValueComparator();
      int index = 0;
      Node n = this._head;
      Node end = this._tail;

      while(true) {
         if ((n = n._next) == end) {
            return -1;
         }

         if (comp == FastComparator.DEFAULT) {
            if (defaultEquals(value, n._value)) {
               break;
            }
         } else if (comp.areEqual(value, n._value)) {
            break;
         }

         ++index;
      }

      return index;
   }

   public final int lastIndexOf(Object value) {
      FastComparator comp = this.getValueComparator();
      int index = this.size() - 1;
      Node n = this._tail;
      Node end = this._head;

      while(true) {
         if ((n = n._previous) == end) {
            return -1;
         }

         if (comp == FastComparator.DEFAULT) {
            if (defaultEquals(value, n._value)) {
               break;
            }
         } else if (comp.areEqual(value, n._value)) {
            break;
         }

         --index;
      }

      return index;
   }

   public Iterator iterator() {
      return this.listIterator();
   }

   public ListIterator listIterator() {
      return FastList.FastListIterator.valueOf(this, this._head._next, 0, this._size);
   }

   public ListIterator listIterator(int index) {
      if (index >= 0 && index <= this._size) {
         return FastList.FastListIterator.valueOf(this, this.nodeAt(index), index, this._size);
      } else {
         throw new IndexOutOfBoundsException("index: " + index);
      }
   }

   public final List subList(int fromIndex, int toIndex) {
      if (fromIndex >= 0 && toIndex <= this._size && fromIndex <= toIndex) {
         return FastList.SubList.valueOf(this, this.nodeAt(fromIndex)._previous, this.nodeAt(toIndex), toIndex - fromIndex);
      } else {
         throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + " for list of size: " + this._size);
      }
   }

   public final Object getFirst() {
      Node<E> node = this._head._next;
      if (node == this._tail) {
         throw new NoSuchElementException();
      } else {
         return node._value;
      }
   }

   public final Object getLast() {
      Node<E> node = this._tail._previous;
      if (node == this._head) {
         throw new NoSuchElementException();
      } else {
         return node._value;
      }
   }

   public final void addFirst(Object value) {
      this.addBefore(this._head._next, value);
   }

   public void addLast(Object value) {
      if (this._tail._next == null) {
         this.increaseCapacity();
      }

      this._tail._value = value;
      this._tail = this._tail._next;
      ++this._size;
   }

   public final Object removeFirst() {
      Node<E> first = this._head._next;
      if (first == this._tail) {
         throw new NoSuchElementException();
      } else {
         E previousValue = (E)first._value;
         this.delete(first);
         return previousValue;
      }
   }

   public final Object removeLast() {
      if (this._size == 0) {
         throw new NoSuchElementException();
      } else {
         --this._size;
         Node<E> last = this._tail._previous;
         E previousValue = (E)last._value;
         this._tail = last;
         last._value = null;
         return previousValue;
      }
   }

   public final void addBefore(Node next, Object value) {
      if (this._tail._next == null) {
         this.increaseCapacity();
      }

      Node newNode = this._tail._next;
      Node tailNext = this._tail._next = newNode._next;
      if (tailNext != null) {
         tailNext._previous = this._tail;
      }

      Node previous = next._previous;
      previous._next = newNode;
      next._previous = newNode;
      newNode._next = next;
      newNode._previous = previous;
      newNode._value = value;
      ++this._size;
   }

   private final Node nodeAt(int index) {
      Node<E> node = this._head;

      for(int i = index; i-- >= 0; node = node._next) {
      }

      return node;
   }

   public final Node head() {
      return this._head;
   }

   public final Node tail() {
      return this._tail;
   }

   public final Object valueOf(FastCollection.Record record) {
      return ((Node)record)._value;
   }

   public final void delete(FastCollection.Record record) {
      Node<E> node = (Node)record;
      --this._size;
      node._value = null;
      node._previous._next = node._next;
      node._next._previous = node._previous;
      Node<E> next = this._tail._next;
      node._previous = this._tail;
      node._next = next;
      this._tail._next = node;
      if (next != null) {
         next._previous = node;
      }

   }

   public final boolean contains(Object value) {
      return this.indexOf(value) >= 0;
   }

   public final int size() {
      return this._size;
   }

   public final void clear() {
      this._size = 0;
      Node<E> n = this._head;
      Node<E> end = this._tail;

      while((n = n._next) != end) {
         n._value = null;
      }

      this._tail = this._head._next;
   }

   public FastList setValueComparator(FastComparator comparator) {
      this._valueComparator = comparator;
      return this;
   }

   public FastComparator getValueComparator() {
      return this._valueComparator;
   }

   public List unmodifiable() {
      return (List)super.unmodifiable();
   }

   protected Node newNode() {
      return new Node();
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      this._head = new Node();
      this._tail = new Node();
      this._head._next = this._tail;
      this._tail._previous = this._head;
      this.setValueComparator((FastComparator)stream.readObject());
      int size = stream.readInt();
      int i = size;

      while(i-- != 0) {
         this.addLast(stream.readObject());
      }

   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.writeObject(this.getValueComparator());
      stream.writeInt(this._size);
      Node node = this._head;
      int i = this._size;

      while(i-- != 0) {
         node = node._next;
         stream.writeObject(node._value);
      }

   }

   private void increaseCapacity() {
      // $FF: Couldn't be decompiled
   }

   public void reset() {
      this.clear();
      this.setValueComparator(FastComparator.DEFAULT);
   }

   private static boolean defaultEquals(Object o1, Object o2) {
      return o1 == null ? o2 == null : o1 == o2 || o1.equals(o2);
   }

   // $FF: synthetic method
   static Node access$300(FastList x0) {
      return x0._tail;
   }

   public static class Node implements FastCollection.Record, Serializable {
      private Node _next;
      private Node _previous;
      private Object _value;

      protected Node() {
      }

      public final Object getValue() {
         return this._value;
      }

      public final Node getNext() {
         return this._next;
      }

      public final Node getPrevious() {
         return this._previous;
      }
   }

   private static final class SubList extends FastCollection implements List, Serializable {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new SubList();
         }

         protected void cleanup(Object obj) {
            SubList sl = (SubList)obj;
            sl._list = null;
            sl._head = null;
            sl._tail = null;
         }
      };
      private FastList _list;
      private Node _head;
      private Node _tail;
      private int _size;

      private SubList() {
      }

      public static SubList valueOf(FastList list, Node head, Node tail, int size) {
         SubList subList = (SubList)FACTORY.object();
         subList._list = list;
         subList._head = head;
         subList._tail = tail;
         subList._size = size;
         return subList;
      }

      public int size() {
         return this._size;
      }

      public FastCollection.Record head() {
         return this._head;
      }

      public FastCollection.Record tail() {
         return this._tail;
      }

      public Object valueOf(FastCollection.Record record) {
         return this._list.valueOf(record);
      }

      public void delete(FastCollection.Record record) {
         this._list.delete(record);
      }

      public boolean addAll(int index, Collection values) {
         if (index >= 0 && index <= this._size) {
            Node indexNode = this.nodeAt(index);
            Iterator i = values.iterator();

            while(i.hasNext()) {
               this._list.addBefore(indexNode, i.next());
            }

            return values.size() != 0;
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public Object get(int index) {
         if (index >= 0 && index < this._size) {
            return this.nodeAt(index)._value;
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public Object set(int index, Object value) {
         if (index >= 0 && index < this._size) {
            Node node = this.nodeAt(index);
            Object previousValue = node._value;
            node._value = value;
            return previousValue;
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public void add(int index, Object element) {
         if (index >= 0 && index <= this._size) {
            this._list.addBefore(this.nodeAt(index), element);
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public Object remove(int index) {
         if (index >= 0 && index < this._size) {
            Node node = this.nodeAt(index);
            Object previousValue = node._value;
            this._list.delete(node);
            return previousValue;
         } else {
            throw new IndexOutOfBoundsException("index: " + index);
         }
      }

      public int indexOf(Object value) {
         FastComparator comp = this._list.getValueComparator();
         int index = 0;
         Node n = this._head;

         for(Node end = this._tail; (n = n._next) != end; ++index) {
            if (comp.areEqual(value, n._value)) {
               return index;
            }
         }

         return -1;
      }

      public int lastIndexOf(Object value) {
         FastComparator comp = this.getValueComparator();
         int index = this.size() - 1;
         Node n = this._tail;

         for(Node end = this._head; (n = n._previous) != end; --index) {
            if (comp.areEqual(value, n._value)) {
               return index;
            }
         }

         return -1;
      }

      public ListIterator listIterator() {
         return this.listIterator(0);
      }

      public ListIterator listIterator(int index) {
         if (index >= 0 && index <= this._size) {
            return FastList.FastListIterator.valueOf(this._list, this.nodeAt(index), index, this._size);
         } else {
            throw new IndexOutOfBoundsException("index: " + index + " for list of size: " + this._size);
         }
      }

      public List subList(int fromIndex, int toIndex) {
         if (fromIndex >= 0 && toIndex <= this._size && fromIndex <= toIndex) {
            SubList subList = valueOf(this._list, this.nodeAt(fromIndex)._previous, this.nodeAt(toIndex), toIndex - fromIndex);
            return subList;
         } else {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + " for list of size: " + this._size);
         }
      }

      private final Node nodeAt(int index) {
         if (index <= this._size >> 1) {
            Node node = this._head;

            for(int i = index; i-- >= 0; node = node._next) {
            }

            return node;
         } else {
            Node node = this._tail;

            for(int i = this._size - index; i-- > 0; node = node._previous) {
            }

            return node;
         }
      }
   }

   private static final class FastListIterator implements ListIterator {
      private static final ObjectFactory FACTORY = new ObjectFactory() {
         protected Object create() {
            return new FastListIterator();
         }

         protected void cleanup(Object obj) {
            FastListIterator i = (FastListIterator)obj;
            i._list = null;
            i._currentNode = null;
            i._nextNode = null;
         }
      };
      private FastList _list;
      private Node _nextNode;
      private Node _currentNode;
      private int _length;
      private int _nextIndex;

      private FastListIterator() {
      }

      public static FastListIterator valueOf(FastList list, Node nextNode, int nextIndex, int size) {
         FastListIterator itr = (FastListIterator)FACTORY.object();
         itr._list = list;
         itr._nextNode = nextNode;
         itr._nextIndex = nextIndex;
         itr._length = size;
         return itr;
      }

      public boolean hasNext() {
         return this._nextIndex != this._length;
      }

      public Object next() {
         if (this._nextIndex == this._length) {
            throw new NoSuchElementException();
         } else {
            ++this._nextIndex;
            this._currentNode = this._nextNode;
            this._nextNode = this._nextNode._next;
            return this._currentNode._value;
         }
      }

      public int nextIndex() {
         return this._nextIndex;
      }

      public boolean hasPrevious() {
         return this._nextIndex != 0;
      }

      public Object previous() {
         if (this._nextIndex == 0) {
            throw new NoSuchElementException();
         } else {
            --this._nextIndex;
            this._currentNode = this._nextNode = this._nextNode._previous;
            return this._currentNode._value;
         }
      }

      public int previousIndex() {
         return this._nextIndex - 1;
      }

      public void add(Object o) {
         this._list.addBefore(this._nextNode, o);
         this._currentNode = null;
         ++this._length;
         ++this._nextIndex;
      }

      public void set(Object o) {
         if (this._currentNode == null) {
            throw new IllegalStateException();
         } else {
            this._currentNode._value = o;
         }
      }

      public void remove() {
         if (this._currentNode == null) {
            throw new IllegalStateException();
         } else {
            if (this._nextNode == this._currentNode) {
               this._nextNode = this._nextNode._next;
            } else {
               --this._nextIndex;
            }

            this._list.delete(this._currentNode);
            this._currentNode = null;
            --this._length;
         }
      }
   }
}
