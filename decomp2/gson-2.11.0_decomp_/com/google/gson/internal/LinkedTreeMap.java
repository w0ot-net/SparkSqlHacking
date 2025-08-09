package com.google.gson.internal;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public final class LinkedTreeMap extends AbstractMap implements Serializable {
   private static final Comparator NATURAL_ORDER = new Comparator() {
      public int compare(Comparable a, Comparable b) {
         return a.compareTo(b);
      }
   };
   private final Comparator comparator;
   private final boolean allowNullValues;
   Node root;
   int size;
   int modCount;
   final Node header;
   private EntrySet entrySet;
   private KeySet keySet;

   public LinkedTreeMap() {
      this(NATURAL_ORDER, true);
   }

   public LinkedTreeMap(boolean allowNullValues) {
      this(NATURAL_ORDER, allowNullValues);
   }

   public LinkedTreeMap(Comparator comparator, boolean allowNullValues) {
      this.size = 0;
      this.modCount = 0;
      this.comparator = comparator != null ? comparator : NATURAL_ORDER;
      this.allowNullValues = allowNullValues;
      this.header = new Node(allowNullValues);
   }

   public int size() {
      return this.size;
   }

   public Object get(Object key) {
      Node<K, V> node = this.findByObject(key);
      return node != null ? node.value : null;
   }

   public boolean containsKey(Object key) {
      return this.findByObject(key) != null;
   }

   @CanIgnoreReturnValue
   public Object put(Object key, Object value) {
      if (key == null) {
         throw new NullPointerException("key == null");
      } else if (value == null && !this.allowNullValues) {
         throw new NullPointerException("value == null");
      } else {
         Node<K, V> created = this.find(key, true);
         V result = (V)created.value;
         created.value = value;
         return result;
      }
   }

   public void clear() {
      this.root = null;
      this.size = 0;
      ++this.modCount;
      Node<K, V> header = this.header;
      header.next = header.prev = header;
   }

   public Object remove(Object key) {
      Node<K, V> node = this.removeInternalByKey(key);
      return node != null ? node.value : null;
   }

   Node find(Object key, boolean create) {
      Comparator<? super K> comparator = this.comparator;
      Node<K, V> nearest = this.root;
      int comparison = 0;
      if (nearest != null) {
         Comparable<Object> comparableKey = comparator == NATURAL_ORDER ? (Comparable)key : null;

         while(true) {
            comparison = comparableKey != null ? comparableKey.compareTo(nearest.key) : comparator.compare(key, nearest.key);
            if (comparison == 0) {
               return nearest;
            }

            Node<K, V> child = comparison < 0 ? nearest.left : nearest.right;
            if (child == null) {
               break;
            }

            nearest = child;
         }
      }

      if (!create) {
         return null;
      } else {
         Node<K, V> header = this.header;
         Node<K, V> created;
         if (nearest == null) {
            if (comparator == NATURAL_ORDER && !(key instanceof Comparable)) {
               throw new ClassCastException(key.getClass().getName() + " is not Comparable");
            }

            created = new Node(this.allowNullValues, nearest, key, header, header.prev);
            this.root = created;
         } else {
            created = new Node(this.allowNullValues, nearest, key, header, header.prev);
            if (comparison < 0) {
               nearest.left = created;
            } else {
               nearest.right = created;
            }

            this.rebalance(nearest, true);
         }

         ++this.size;
         ++this.modCount;
         return created;
      }
   }

   Node findByObject(Object key) {
      try {
         return key != null ? this.find(key, false) : null;
      } catch (ClassCastException var3) {
         return null;
      }
   }

   Node findByEntry(Map.Entry entry) {
      Node<K, V> mine = this.findByObject(entry.getKey());
      boolean valuesEqual = mine != null && equal(mine.value, entry.getValue());
      return valuesEqual ? mine : null;
   }

   private static boolean equal(Object a, Object b) {
      return Objects.equals(a, b);
   }

   void removeInternal(Node node, boolean unlink) {
      if (unlink) {
         node.prev.next = node.next;
         node.next.prev = node.prev;
      }

      Node<K, V> left = node.left;
      Node<K, V> right = node.right;
      Node<K, V> originalParent = node.parent;
      if (left != null && right != null) {
         Node<K, V> adjacent = left.height > right.height ? left.last() : right.first();
         this.removeInternal(adjacent, false);
         int leftHeight = 0;
         left = node.left;
         if (left != null) {
            leftHeight = left.height;
            adjacent.left = left;
            left.parent = adjacent;
            node.left = null;
         }

         int rightHeight = 0;
         right = node.right;
         if (right != null) {
            rightHeight = right.height;
            adjacent.right = right;
            right.parent = adjacent;
            node.right = null;
         }

         adjacent.height = Math.max(leftHeight, rightHeight) + 1;
         this.replaceInParent(node, adjacent);
      } else {
         if (left != null) {
            this.replaceInParent(node, left);
            node.left = null;
         } else if (right != null) {
            this.replaceInParent(node, right);
            node.right = null;
         } else {
            this.replaceInParent(node, (Node)null);
         }

         this.rebalance(originalParent, false);
         --this.size;
         ++this.modCount;
      }
   }

   Node removeInternalByKey(Object key) {
      Node<K, V> node = this.findByObject(key);
      if (node != null) {
         this.removeInternal(node, true);
      }

      return node;
   }

   private void replaceInParent(Node node, Node replacement) {
      Node<K, V> parent = node.parent;
      node.parent = null;
      if (replacement != null) {
         replacement.parent = parent;
      }

      if (parent != null) {
         if (parent.left == node) {
            parent.left = replacement;
         } else {
            assert parent.right == node;

            parent.right = replacement;
         }
      } else {
         this.root = replacement;
      }

   }

   private void rebalance(Node unbalanced, boolean insert) {
      for(Node<K, V> node = unbalanced; node != null; node = node.parent) {
         Node<K, V> left = node.left;
         Node<K, V> right = node.right;
         int leftHeight = left != null ? left.height : 0;
         int rightHeight = right != null ? right.height : 0;
         int delta = leftHeight - rightHeight;
         if (delta == -2) {
            Node<K, V> rightLeft = right.left;
            Node<K, V> rightRight = right.right;
            int rightRightHeight = rightRight != null ? rightRight.height : 0;
            int rightLeftHeight = rightLeft != null ? rightLeft.height : 0;
            int rightDelta = rightLeftHeight - rightRightHeight;
            if (rightDelta != -1 && (rightDelta != 0 || insert)) {
               assert rightDelta == 1;

               this.rotateRight(right);
               this.rotateLeft(node);
            } else {
               this.rotateLeft(node);
            }

            if (insert) {
               break;
            }
         } else if (delta == 2) {
            Node<K, V> leftLeft = left.left;
            Node<K, V> leftRight = left.right;
            int leftRightHeight = leftRight != null ? leftRight.height : 0;
            int leftLeftHeight = leftLeft != null ? leftLeft.height : 0;
            int leftDelta = leftLeftHeight - leftRightHeight;
            if (leftDelta != 1 && (leftDelta != 0 || insert)) {
               assert leftDelta == -1;

               this.rotateLeft(left);
               this.rotateRight(node);
            } else {
               this.rotateRight(node);
            }

            if (insert) {
               break;
            }
         } else if (delta == 0) {
            node.height = leftHeight + 1;
            if (insert) {
               break;
            }
         } else {
            assert delta == -1 || delta == 1;

            node.height = Math.max(leftHeight, rightHeight) + 1;
            if (!insert) {
               break;
            }
         }
      }

   }

   private void rotateLeft(Node root) {
      Node<K, V> left = root.left;
      Node<K, V> pivot = root.right;
      Node<K, V> pivotLeft = pivot.left;
      Node<K, V> pivotRight = pivot.right;
      root.right = pivotLeft;
      if (pivotLeft != null) {
         pivotLeft.parent = root;
      }

      this.replaceInParent(root, pivot);
      pivot.left = root;
      root.parent = pivot;
      root.height = Math.max(left != null ? left.height : 0, pivotLeft != null ? pivotLeft.height : 0) + 1;
      pivot.height = Math.max(root.height, pivotRight != null ? pivotRight.height : 0) + 1;
   }

   private void rotateRight(Node root) {
      Node<K, V> pivot = root.left;
      Node<K, V> right = root.right;
      Node<K, V> pivotLeft = pivot.left;
      Node<K, V> pivotRight = pivot.right;
      root.left = pivotRight;
      if (pivotRight != null) {
         pivotRight.parent = root;
      }

      this.replaceInParent(root, pivot);
      pivot.right = root;
      root.parent = pivot;
      root.height = Math.max(right != null ? right.height : 0, pivotRight != null ? pivotRight.height : 0) + 1;
      pivot.height = Math.max(root.height, pivotLeft != null ? pivotLeft.height : 0) + 1;
   }

   public Set entrySet() {
      LinkedTreeMap<K, V>.EntrySet result = this.entrySet;
      return result != null ? result : (this.entrySet = new EntrySet());
   }

   public Set keySet() {
      LinkedTreeMap<K, V>.KeySet result = this.keySet;
      return result != null ? result : (this.keySet = new KeySet());
   }

   private Object writeReplace() throws ObjectStreamException {
      return new LinkedHashMap(this);
   }

   private void readObject(ObjectInputStream in) throws IOException {
      throw new InvalidObjectException("Deserialization is unsupported");
   }

   static final class Node implements Map.Entry {
      Node parent;
      Node left;
      Node right;
      Node next;
      Node prev;
      final Object key;
      final boolean allowNullValue;
      Object value;
      int height;

      Node(boolean allowNullValue) {
         this.key = null;
         this.allowNullValue = allowNullValue;
         this.next = this.prev = this;
      }

      Node(boolean allowNullValue, Node parent, Object key, Node next, Node prev) {
         this.parent = parent;
         this.key = key;
         this.allowNullValue = allowNullValue;
         this.height = 1;
         this.next = next;
         this.prev = prev;
         prev.next = this;
         next.prev = this;
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         if (value == null && !this.allowNullValue) {
            throw new NullPointerException("value == null");
         } else {
            V oldValue = (V)this.value;
            this.value = value;
            return oldValue;
         }
      }

      public boolean equals(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            boolean var10000;
            label38: {
               label27: {
                  Map.Entry<?, ?> other = (Map.Entry)o;
                  if (this.key == null) {
                     if (other.getKey() != null) {
                        break label27;
                     }
                  } else if (!this.key.equals(other.getKey())) {
                     break label27;
                  }

                  if (this.value == null) {
                     if (other.getValue() == null) {
                        break label38;
                     }
                  } else if (this.value.equals(other.getValue())) {
                     break label38;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public int hashCode() {
         return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public String toString() {
         return this.key + "=" + this.value;
      }

      public Node first() {
         Node<K, V> node = this;

         for(Node<K, V> child = this.left; child != null; child = child.left) {
            node = child;
         }

         return node;
      }

      public Node last() {
         Node<K, V> node = this;

         for(Node<K, V> child = this.right; child != null; child = child.right) {
            node = child;
         }

         return node;
      }
   }

   private abstract class LinkedTreeMapIterator implements Iterator {
      Node next;
      Node lastReturned;
      int expectedModCount;

      LinkedTreeMapIterator() {
         this.next = LinkedTreeMap.this.header.next;
         this.lastReturned = null;
         this.expectedModCount = LinkedTreeMap.this.modCount;
      }

      public final boolean hasNext() {
         return this.next != LinkedTreeMap.this.header;
      }

      final Node nextNode() {
         Node<K, V> e = this.next;
         if (e == LinkedTreeMap.this.header) {
            throw new NoSuchElementException();
         } else if (LinkedTreeMap.this.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
         } else {
            this.next = e.next;
            return this.lastReturned = e;
         }
      }

      public final void remove() {
         if (this.lastReturned == null) {
            throw new IllegalStateException();
         } else {
            LinkedTreeMap.this.removeInternal(this.lastReturned, true);
            this.lastReturned = null;
            this.expectedModCount = LinkedTreeMap.this.modCount;
         }
      }
   }

   class EntrySet extends AbstractSet {
      public int size() {
         return LinkedTreeMap.this.size;
      }

      public Iterator iterator() {
         return new LinkedTreeMapIterator() {
            public Map.Entry next() {
               return this.nextNode();
            }
         };
      }

      public boolean contains(Object o) {
         return o instanceof Map.Entry && LinkedTreeMap.this.findByEntry((Map.Entry)o) != null;
      }

      public boolean remove(Object o) {
         if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            Node<K, V> node = LinkedTreeMap.this.findByEntry((Map.Entry)o);
            if (node == null) {
               return false;
            } else {
               LinkedTreeMap.this.removeInternal(node, true);
               return true;
            }
         }
      }

      public void clear() {
         LinkedTreeMap.this.clear();
      }
   }

   final class KeySet extends AbstractSet {
      public int size() {
         return LinkedTreeMap.this.size;
      }

      public Iterator iterator() {
         return new LinkedTreeMapIterator() {
            public Object next() {
               return this.nextNode().key;
            }
         };
      }

      public boolean contains(Object o) {
         return LinkedTreeMap.this.containsKey(o);
      }

      public boolean remove(Object key) {
         return LinkedTreeMap.this.removeInternalByKey(key) != null;
      }

      public void clear() {
         LinkedTreeMap.this.clear();
      }
   }
}
