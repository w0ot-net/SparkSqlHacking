package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
public class LinkedListMultimap extends AbstractMultimap implements ListMultimap, Serializable {
   @CheckForNull
   private transient Node head;
   @CheckForNull
   private transient Node tail;
   private transient Map keyToKeyList;
   private transient int size;
   private transient int modCount;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   public static LinkedListMultimap create() {
      return new LinkedListMultimap();
   }

   public static LinkedListMultimap create(int expectedKeys) {
      return new LinkedListMultimap(expectedKeys);
   }

   public static LinkedListMultimap create(Multimap multimap) {
      return new LinkedListMultimap(multimap);
   }

   LinkedListMultimap() {
      this(12);
   }

   private LinkedListMultimap(int expectedKeys) {
      this.keyToKeyList = Platform.newHashMapWithExpectedSize(expectedKeys);
   }

   private LinkedListMultimap(Multimap multimap) {
      this(multimap.keySet().size());
      this.putAll(multimap);
   }

   @CanIgnoreReturnValue
   private Node addNode(@ParametricNullness Object key, @ParametricNullness Object value, @CheckForNull Node nextSibling) {
      Node<K, V> node = new Node(key, value);
      if (this.head == null) {
         this.head = this.tail = node;
         this.keyToKeyList.put(key, new KeyList(node));
         ++this.modCount;
      } else if (nextSibling == null) {
         ((Node)Objects.requireNonNull(this.tail)).next = node;
         node.previous = this.tail;
         this.tail = node;
         KeyList<K, V> keyList = (KeyList)this.keyToKeyList.get(key);
         if (keyList == null) {
            this.keyToKeyList.put(key, new KeyList(node));
            ++this.modCount;
         } else {
            ++keyList.count;
            Node<K, V> keyTail = keyList.tail;
            keyTail.nextSibling = node;
            node.previousSibling = keyTail;
            keyList.tail = node;
         }
      } else {
         KeyList<K, V> keyList = (KeyList)Objects.requireNonNull((KeyList)this.keyToKeyList.get(key));
         ++keyList.count;
         node.previous = nextSibling.previous;
         node.previousSibling = nextSibling.previousSibling;
         node.next = nextSibling;
         node.nextSibling = nextSibling;
         if (nextSibling.previousSibling == null) {
            keyList.head = node;
         } else {
            nextSibling.previousSibling.nextSibling = node;
         }

         if (nextSibling.previous == null) {
            this.head = node;
         } else {
            nextSibling.previous.next = node;
         }

         nextSibling.previous = node;
         nextSibling.previousSibling = node;
      }

      ++this.size;
      return node;
   }

   private void removeNode(Node node) {
      if (node.previous != null) {
         node.previous.next = node.next;
      } else {
         this.head = node.next;
      }

      if (node.next != null) {
         node.next.previous = node.previous;
      } else {
         this.tail = node.previous;
      }

      if (node.previousSibling == null && node.nextSibling == null) {
         KeyList<K, V> keyList = (KeyList)Objects.requireNonNull((KeyList)this.keyToKeyList.remove(node.key));
         keyList.count = 0;
         ++this.modCount;
      } else {
         KeyList<K, V> keyList = (KeyList)Objects.requireNonNull((KeyList)this.keyToKeyList.get(node.key));
         --keyList.count;
         if (node.previousSibling == null) {
            keyList.head = (Node)Objects.requireNonNull(node.nextSibling);
         } else {
            node.previousSibling.nextSibling = node.nextSibling;
         }

         if (node.nextSibling == null) {
            keyList.tail = (Node)Objects.requireNonNull(node.previousSibling);
         } else {
            node.nextSibling.previousSibling = node.previousSibling;
         }
      }

      --this.size;
   }

   private void removeAllNodes(@ParametricNullness Object key) {
      Iterators.clear(new ValueForKeyIterator(key));
   }

   public int size() {
      return this.size;
   }

   public boolean isEmpty() {
      return this.head == null;
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.keyToKeyList.containsKey(key);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.values().contains(value);
   }

   @CanIgnoreReturnValue
   public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
      this.addNode(key, value, (Node)null);
      return true;
   }

   @CanIgnoreReturnValue
   public List replaceValues(@ParametricNullness Object key, Iterable values) {
      List<V> oldValues = this.getCopy(key);
      ListIterator<V> keyValues = new ValueForKeyIterator(key);
      Iterator<? extends V> newValues = values.iterator();

      while(keyValues.hasNext() && newValues.hasNext()) {
         keyValues.next();
         keyValues.set(newValues.next());
      }

      while(keyValues.hasNext()) {
         keyValues.next();
         keyValues.remove();
      }

      while(newValues.hasNext()) {
         keyValues.add(newValues.next());
      }

      return oldValues;
   }

   private List getCopy(@ParametricNullness Object key) {
      return Collections.unmodifiableList(Lists.newArrayList((Iterator)(new ValueForKeyIterator(key))));
   }

   @CanIgnoreReturnValue
   public List removeAll(@CheckForNull Object key) {
      List<V> oldValues = this.getCopy(key);
      this.removeAllNodes(key);
      return oldValues;
   }

   public void clear() {
      this.head = null;
      this.tail = null;
      this.keyToKeyList.clear();
      this.size = 0;
      ++this.modCount;
   }

   public List get(@ParametricNullness final Object key) {
      return new AbstractSequentialList() {
         public int size() {
            KeyList<K, V> keyList = (KeyList)LinkedListMultimap.this.keyToKeyList.get(key);
            return keyList == null ? 0 : keyList.count;
         }

         public ListIterator listIterator(int index) {
            return LinkedListMultimap.this.new ValueForKeyIterator(key, index);
         }
      };
   }

   Set createKeySet() {
      class KeySetImpl extends Sets.ImprovedAbstractSet {
         public int size() {
            return LinkedListMultimap.this.keyToKeyList.size();
         }

         public Iterator iterator() {
            return LinkedListMultimap.this.new DistinctKeyIterator();
         }

         public boolean contains(@CheckForNull Object key) {
            return LinkedListMultimap.this.containsKey(key);
         }

         public boolean remove(@CheckForNull Object o) {
            return !LinkedListMultimap.this.removeAll(o).isEmpty();
         }
      }

      return new KeySetImpl();
   }

   Multiset createKeys() {
      return new Multimaps.Keys(this);
   }

   public List values() {
      return (List)super.values();
   }

   List createValues() {
      class ValuesImpl extends AbstractSequentialList {
         public int size() {
            return LinkedListMultimap.this.size;
         }

         public ListIterator listIterator(int index) {
            final LinkedListMultimap<K, V>.NodeIterator nodeItr = LinkedListMultimap.this.new NodeIterator(index);
            return new TransformedListIterator(nodeItr) {
               @ParametricNullness
               Object transform(Map.Entry entry) {
                  return entry.getValue();
               }

               public void set(@ParametricNullness Object value) {
                  nodeItr.setValue(value);
               }
            };
         }
      }

      return new ValuesImpl();
   }

   public List entries() {
      return (List)super.entries();
   }

   List createEntries() {
      class EntriesImpl extends AbstractSequentialList {
         public int size() {
            return LinkedListMultimap.this.size;
         }

         public ListIterator listIterator(int index) {
            return LinkedListMultimap.this.new NodeIterator(index);
         }

         public void forEach(Consumer action) {
            Preconditions.checkNotNull(action);

            for(Node<K, V> node = LinkedListMultimap.this.head; node != null; node = node.next) {
               action.accept(node);
            }

         }
      }

      return new EntriesImpl();
   }

   Iterator entryIterator() {
      throw new AssertionError("should never be called");
   }

   Map createAsMap() {
      return new Multimaps.AsMap(this);
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeInt(this.size());

      for(Map.Entry entry : this.entries()) {
         stream.writeObject(entry.getKey());
         stream.writeObject(entry.getValue());
      }

   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.keyToKeyList = Maps.newLinkedHashMap();
      int size = stream.readInt();

      for(int i = 0; i < size; ++i) {
         K key = (K)stream.readObject();
         V value = (V)stream.readObject();
         this.put(key, value);
      }

   }

   static final class Node extends AbstractMapEntry {
      @ParametricNullness
      final Object key;
      @ParametricNullness
      Object value;
      @CheckForNull
      Node next;
      @CheckForNull
      Node previous;
      @CheckForNull
      Node nextSibling;
      @CheckForNull
      Node previousSibling;

      Node(@ParametricNullness Object key, @ParametricNullness Object value) {
         this.key = key;
         this.value = value;
      }

      @ParametricNullness
      public Object getKey() {
         return this.key;
      }

      @ParametricNullness
      public Object getValue() {
         return this.value;
      }

      @ParametricNullness
      public Object setValue(@ParametricNullness Object newValue) {
         V result = (V)this.value;
         this.value = newValue;
         return result;
      }
   }

   private static class KeyList {
      Node head;
      Node tail;
      int count;

      KeyList(Node firstNode) {
         this.head = firstNode;
         this.tail = firstNode;
         firstNode.previousSibling = null;
         firstNode.nextSibling = null;
         this.count = 1;
      }
   }

   private class NodeIterator implements ListIterator {
      int nextIndex;
      @CheckForNull
      Node next;
      @CheckForNull
      Node current;
      @CheckForNull
      Node previous;
      int expectedModCount;

      NodeIterator(int index) {
         this.expectedModCount = LinkedListMultimap.this.modCount;
         int size = LinkedListMultimap.this.size();
         Preconditions.checkPositionIndex(index, size);
         if (index >= size / 2) {
            this.previous = LinkedListMultimap.this.tail;
            this.nextIndex = size;

            while(index++ < size) {
               this.previous();
            }
         } else {
            this.next = LinkedListMultimap.this.head;

            while(index-- > 0) {
               this.next();
            }
         }

         this.current = null;
      }

      private void checkForConcurrentModification() {
         if (LinkedListMultimap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }

      public boolean hasNext() {
         this.checkForConcurrentModification();
         return this.next != null;
      }

      @CanIgnoreReturnValue
      public Node next() {
         this.checkForConcurrentModification();
         if (this.next == null) {
            throw new NoSuchElementException();
         } else {
            this.previous = this.current = this.next;
            this.next = this.next.next;
            ++this.nextIndex;
            return this.current;
         }
      }

      public void remove() {
         this.checkForConcurrentModification();
         Preconditions.checkState(this.current != null, "no calls to next() since the last call to remove()");
         if (this.current != this.next) {
            this.previous = this.current.previous;
            --this.nextIndex;
         } else {
            this.next = this.current.next;
         }

         LinkedListMultimap.this.removeNode(this.current);
         this.current = null;
         this.expectedModCount = LinkedListMultimap.this.modCount;
      }

      public boolean hasPrevious() {
         this.checkForConcurrentModification();
         return this.previous != null;
      }

      @CanIgnoreReturnValue
      public Node previous() {
         this.checkForConcurrentModification();
         if (this.previous == null) {
            throw new NoSuchElementException();
         } else {
            this.next = this.current = this.previous;
            this.previous = this.previous.previous;
            --this.nextIndex;
            return this.current;
         }
      }

      public int nextIndex() {
         return this.nextIndex;
      }

      public int previousIndex() {
         return this.nextIndex - 1;
      }

      public void set(Map.Entry e) {
         throw new UnsupportedOperationException();
      }

      public void add(Map.Entry e) {
         throw new UnsupportedOperationException();
      }

      void setValue(@ParametricNullness Object value) {
         Preconditions.checkState(this.current != null);
         this.current.value = value;
      }
   }

   private class DistinctKeyIterator implements Iterator {
      final Set seenKeys;
      @CheckForNull
      Node next;
      @CheckForNull
      Node current;
      int expectedModCount;

      private DistinctKeyIterator() {
         this.seenKeys = Sets.newHashSetWithExpectedSize(LinkedListMultimap.this.keySet().size());
         this.next = LinkedListMultimap.this.head;
         this.expectedModCount = LinkedListMultimap.this.modCount;
      }

      private void checkForConcurrentModification() {
         if (LinkedListMultimap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }

      public boolean hasNext() {
         this.checkForConcurrentModification();
         return this.next != null;
      }

      @ParametricNullness
      public Object next() {
         this.checkForConcurrentModification();
         if (this.next == null) {
            throw new NoSuchElementException();
         } else {
            this.current = this.next;
            this.seenKeys.add(this.current.key);

            do {
               this.next = this.next.next;
            } while(this.next != null && !this.seenKeys.add(this.next.key));

            return this.current.key;
         }
      }

      public void remove() {
         this.checkForConcurrentModification();
         Preconditions.checkState(this.current != null, "no calls to next() since the last call to remove()");
         LinkedListMultimap.this.removeAllNodes(this.current.key);
         this.current = null;
         this.expectedModCount = LinkedListMultimap.this.modCount;
      }
   }

   private class ValueForKeyIterator implements ListIterator {
      @ParametricNullness
      final Object key;
      int nextIndex;
      @CheckForNull
      Node next;
      @CheckForNull
      Node current;
      @CheckForNull
      Node previous;

      ValueForKeyIterator(@ParametricNullness Object key) {
         this.key = key;
         KeyList<K, V> keyList = (KeyList)LinkedListMultimap.this.keyToKeyList.get(key);
         this.next = keyList == null ? null : keyList.head;
      }

      public ValueForKeyIterator(@ParametricNullness Object key, int index) {
         KeyList<K, V> keyList = (KeyList)LinkedListMultimap.this.keyToKeyList.get(key);
         int size = keyList == null ? 0 : keyList.count;
         Preconditions.checkPositionIndex(index, size);
         if (index >= size / 2) {
            this.previous = keyList == null ? null : keyList.tail;
            this.nextIndex = size;

            while(index++ < size) {
               this.previous();
            }
         } else {
            this.next = keyList == null ? null : keyList.head;

            while(index-- > 0) {
               this.next();
            }
         }

         this.key = key;
         this.current = null;
      }

      public boolean hasNext() {
         return this.next != null;
      }

      @ParametricNullness
      @CanIgnoreReturnValue
      public Object next() {
         if (this.next == null) {
            throw new NoSuchElementException();
         } else {
            this.previous = this.current = this.next;
            this.next = this.next.nextSibling;
            ++this.nextIndex;
            return this.current.value;
         }
      }

      public boolean hasPrevious() {
         return this.previous != null;
      }

      @ParametricNullness
      @CanIgnoreReturnValue
      public Object previous() {
         if (this.previous == null) {
            throw new NoSuchElementException();
         } else {
            this.next = this.current = this.previous;
            this.previous = this.previous.previousSibling;
            --this.nextIndex;
            return this.current.value;
         }
      }

      public int nextIndex() {
         return this.nextIndex;
      }

      public int previousIndex() {
         return this.nextIndex - 1;
      }

      public void remove() {
         Preconditions.checkState(this.current != null, "no calls to next() since the last call to remove()");
         if (this.current != this.next) {
            this.previous = this.current.previousSibling;
            --this.nextIndex;
         } else {
            this.next = this.current.nextSibling;
         }

         LinkedListMultimap.this.removeNode(this.current);
         this.current = null;
      }

      public void set(@ParametricNullness Object value) {
         Preconditions.checkState(this.current != null);
         this.current.value = value;
      }

      public void add(@ParametricNullness Object value) {
         this.previous = LinkedListMultimap.this.addNode(this.key, value, this.next);
         ++this.nextIndex;
         this.current = null;
      }
   }
}
