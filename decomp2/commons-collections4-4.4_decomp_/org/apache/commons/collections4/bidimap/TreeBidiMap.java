package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;

public class TreeBidiMap implements OrderedBidiMap, Serializable {
   private static final long serialVersionUID = 721969328361807L;
   private transient Node[] rootNode;
   private transient int nodeCount;
   private transient int modifications;
   private transient Set keySet;
   private transient Set valuesSet;
   private transient Set entrySet;
   private transient Inverse inverse;

   public TreeBidiMap() {
      this.nodeCount = 0;
      this.modifications = 0;
      this.inverse = null;
      this.rootNode = new Node[2];
   }

   public TreeBidiMap(Map map) {
      this();
      this.putAll(map);
   }

   public int size() {
      return this.nodeCount;
   }

   public boolean isEmpty() {
      return this.nodeCount == 0;
   }

   public boolean containsKey(Object key) {
      checkKey(key);
      return this.lookupKey(key) != null;
   }

   public boolean containsValue(Object value) {
      checkValue(value);
      return this.lookupValue(value) != null;
   }

   public Comparable get(Object key) {
      checkKey(key);
      Node<K, V> node = this.lookupKey(key);
      return node == null ? null : node.getValue();
   }

   public Comparable put(Comparable key, Comparable value) {
      V result = (V)this.get(key);
      this.doPut(key, value);
      return result;
   }

   public void putAll(Map map) {
      for(Map.Entry e : map.entrySet()) {
         this.put((Comparable)e.getKey(), (Comparable)e.getValue());
      }

   }

   public Comparable remove(Object key) {
      return this.doRemoveKey(key);
   }

   public void clear() {
      this.modify();
      this.nodeCount = 0;
      this.rootNode[TreeBidiMap.DataElement.KEY.ordinal()] = null;
      this.rootNode[TreeBidiMap.DataElement.VALUE.ordinal()] = null;
   }

   public Comparable getKey(Object value) {
      checkValue(value);
      Node<K, V> node = this.lookupValue(value);
      return node == null ? null : node.getKey();
   }

   public Comparable removeValue(Object value) {
      return this.doRemoveValue(value);
   }

   public Comparable firstKey() {
      if (this.nodeCount == 0) {
         throw new NoSuchElementException("Map is empty");
      } else {
         return this.leastNode(this.rootNode[TreeBidiMap.DataElement.KEY.ordinal()], TreeBidiMap.DataElement.KEY).getKey();
      }
   }

   public Comparable lastKey() {
      if (this.nodeCount == 0) {
         throw new NoSuchElementException("Map is empty");
      } else {
         return this.greatestNode(this.rootNode[TreeBidiMap.DataElement.KEY.ordinal()], TreeBidiMap.DataElement.KEY).getKey();
      }
   }

   public Comparable nextKey(Comparable key) {
      checkKey(key);
      Node<K, V> node = this.nextGreater(this.lookupKey(key), TreeBidiMap.DataElement.KEY);
      return node == null ? null : node.getKey();
   }

   public Comparable previousKey(Comparable key) {
      checkKey(key);
      Node<K, V> node = this.nextSmaller(this.lookupKey(key), TreeBidiMap.DataElement.KEY);
      return node == null ? null : node.getKey();
   }

   public Set keySet() {
      if (this.keySet == null) {
         this.keySet = new KeyView(TreeBidiMap.DataElement.KEY);
      }

      return this.keySet;
   }

   public Set values() {
      if (this.valuesSet == null) {
         this.valuesSet = new ValueView(TreeBidiMap.DataElement.KEY);
      }

      return this.valuesSet;
   }

   public Set entrySet() {
      if (this.entrySet == null) {
         this.entrySet = new EntryView();
      }

      return this.entrySet;
   }

   public OrderedMapIterator mapIterator() {
      return (OrderedMapIterator)(this.isEmpty() ? EmptyOrderedMapIterator.emptyOrderedMapIterator() : new ViewMapIterator(TreeBidiMap.DataElement.KEY));
   }

   public OrderedBidiMap inverseBidiMap() {
      if (this.inverse == null) {
         this.inverse = new Inverse();
      }

      return this.inverse;
   }

   public boolean equals(Object obj) {
      return this.doEquals(obj, TreeBidiMap.DataElement.KEY);
   }

   public int hashCode() {
      return this.doHashCode(TreeBidiMap.DataElement.KEY);
   }

   public String toString() {
      return this.doToString(TreeBidiMap.DataElement.KEY);
   }

   private void doPut(Comparable key, Comparable value) {
      checkKeyAndValue(key, value);
      this.doRemoveKey(key);
      this.doRemoveValue(value);
      Node<K, V> node = this.rootNode[TreeBidiMap.DataElement.KEY.ordinal()];
      if (node == null) {
         Node<K, V> root = new Node(key, value);
         this.rootNode[TreeBidiMap.DataElement.KEY.ordinal()] = root;
         this.rootNode[TreeBidiMap.DataElement.VALUE.ordinal()] = root;
         this.grow();
      } else {
         while(true) {
            int cmp = compare(key, node.getKey());
            if (cmp == 0) {
               throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map");
            }

            if (cmp < 0) {
               if (node.getLeft(TreeBidiMap.DataElement.KEY) == null) {
                  Node<K, V> newNode = new Node(key, value);
                  this.insertValue(newNode);
                  node.setLeft(newNode, TreeBidiMap.DataElement.KEY);
                  newNode.setParent(node, TreeBidiMap.DataElement.KEY);
                  this.doRedBlackInsert(newNode, TreeBidiMap.DataElement.KEY);
                  this.grow();
                  break;
               }

               node = node.getLeft(TreeBidiMap.DataElement.KEY);
            } else {
               if (node.getRight(TreeBidiMap.DataElement.KEY) == null) {
                  Node<K, V> newNode = new Node(key, value);
                  this.insertValue(newNode);
                  node.setRight(newNode, TreeBidiMap.DataElement.KEY);
                  newNode.setParent(node, TreeBidiMap.DataElement.KEY);
                  this.doRedBlackInsert(newNode, TreeBidiMap.DataElement.KEY);
                  this.grow();
                  break;
               }

               node = node.getRight(TreeBidiMap.DataElement.KEY);
            }
         }
      }

   }

   private Comparable doRemoveKey(Object key) {
      Node<K, V> node = this.lookupKey(key);
      if (node == null) {
         return null;
      } else {
         this.doRedBlackDelete(node);
         return node.getValue();
      }
   }

   private Comparable doRemoveValue(Object value) {
      Node<K, V> node = this.lookupValue(value);
      if (node == null) {
         return null;
      } else {
         this.doRedBlackDelete(node);
         return node.getKey();
      }
   }

   private Node lookup(Object data, DataElement dataElement) {
      Node<K, V> rval = null;

      int cmp;
      for(Node<K, V> node = this.rootNode[dataElement.ordinal()]; node != null; node = cmp < 0 ? node.getLeft(dataElement) : node.getRight(dataElement)) {
         cmp = compare((Comparable)data, (Comparable)node.getData(dataElement));
         if (cmp == 0) {
            rval = node;
            break;
         }
      }

      return rval;
   }

   private Node lookupKey(Object key) {
      return this.lookup(key, TreeBidiMap.DataElement.KEY);
   }

   private Node lookupValue(Object value) {
      return this.lookup(value, TreeBidiMap.DataElement.VALUE);
   }

   private Node nextGreater(Node node, DataElement dataElement) {
      Node<K, V> rval;
      if (node == null) {
         rval = null;
      } else if (node.getRight(dataElement) != null) {
         rval = this.leastNode(node.getRight(dataElement), dataElement);
      } else {
         Node<K, V> parent = node.getParent(dataElement);

         for(Node<K, V> child = node; parent != null && child == parent.getRight(dataElement); parent = parent.getParent(dataElement)) {
            child = parent;
         }

         rval = parent;
      }

      return rval;
   }

   private Node nextSmaller(Node node, DataElement dataElement) {
      Node<K, V> rval;
      if (node == null) {
         rval = null;
      } else if (node.getLeft(dataElement) != null) {
         rval = this.greatestNode(node.getLeft(dataElement), dataElement);
      } else {
         Node<K, V> parent = node.getParent(dataElement);

         for(Node<K, V> child = node; parent != null && child == parent.getLeft(dataElement); parent = parent.getParent(dataElement)) {
            child = parent;
         }

         rval = parent;
      }

      return rval;
   }

   private static int compare(Comparable o1, Comparable o2) {
      return o1.compareTo(o2);
   }

   private Node leastNode(Node node, DataElement dataElement) {
      Node<K, V> rval = node;
      if (node != null) {
         while(rval.getLeft(dataElement) != null) {
            rval = rval.getLeft(dataElement);
         }
      }

      return rval;
   }

   private Node greatestNode(Node node, DataElement dataElement) {
      Node<K, V> rval = node;
      if (node != null) {
         while(rval.getRight(dataElement) != null) {
            rval = rval.getRight(dataElement);
         }
      }

      return rval;
   }

   private void copyColor(Node from, Node to, DataElement dataElement) {
      if (to != null) {
         if (from == null) {
            to.setBlack(dataElement);
         } else {
            to.copyColor(from, dataElement);
         }
      }

   }

   private static boolean isRed(Node node, DataElement dataElement) {
      return node != null && node.isRed(dataElement);
   }

   private static boolean isBlack(Node node, DataElement dataElement) {
      return node == null || node.isBlack(dataElement);
   }

   private static void makeRed(Node node, DataElement dataElement) {
      if (node != null) {
         node.setRed(dataElement);
      }

   }

   private static void makeBlack(Node node, DataElement dataElement) {
      if (node != null) {
         node.setBlack(dataElement);
      }

   }

   private Node getGrandParent(Node node, DataElement dataElement) {
      return this.getParent(this.getParent(node, dataElement), dataElement);
   }

   private Node getParent(Node node, DataElement dataElement) {
      return node == null ? null : node.getParent(dataElement);
   }

   private Node getRightChild(Node node, DataElement dataElement) {
      return node == null ? null : node.getRight(dataElement);
   }

   private Node getLeftChild(Node node, DataElement dataElement) {
      return node == null ? null : node.getLeft(dataElement);
   }

   private void rotateLeft(Node node, DataElement dataElement) {
      Node<K, V> rightChild = node.getRight(dataElement);
      node.setRight(rightChild.getLeft(dataElement), dataElement);
      if (rightChild.getLeft(dataElement) != null) {
         rightChild.getLeft(dataElement).setParent(node, dataElement);
      }

      rightChild.setParent(node.getParent(dataElement), dataElement);
      if (node.getParent(dataElement) == null) {
         this.rootNode[dataElement.ordinal()] = rightChild;
      } else if (node.getParent(dataElement).getLeft(dataElement) == node) {
         node.getParent(dataElement).setLeft(rightChild, dataElement);
      } else {
         node.getParent(dataElement).setRight(rightChild, dataElement);
      }

      rightChild.setLeft(node, dataElement);
      node.setParent(rightChild, dataElement);
   }

   private void rotateRight(Node node, DataElement dataElement) {
      Node<K, V> leftChild = node.getLeft(dataElement);
      node.setLeft(leftChild.getRight(dataElement), dataElement);
      if (leftChild.getRight(dataElement) != null) {
         leftChild.getRight(dataElement).setParent(node, dataElement);
      }

      leftChild.setParent(node.getParent(dataElement), dataElement);
      if (node.getParent(dataElement) == null) {
         this.rootNode[dataElement.ordinal()] = leftChild;
      } else if (node.getParent(dataElement).getRight(dataElement) == node) {
         node.getParent(dataElement).setRight(leftChild, dataElement);
      } else {
         node.getParent(dataElement).setLeft(leftChild, dataElement);
      }

      leftChild.setRight(node, dataElement);
      node.setParent(leftChild, dataElement);
   }

   private void doRedBlackInsert(Node insertedNode, DataElement dataElement) {
      Node<K, V> currentNode = insertedNode;
      makeRed(insertedNode, dataElement);

      while(currentNode != null && currentNode != this.rootNode[dataElement.ordinal()] && isRed(currentNode.getParent(dataElement), dataElement)) {
         if (currentNode.isLeftChild(dataElement)) {
            Node<K, V> y = this.getRightChild(this.getGrandParent(currentNode, dataElement), dataElement);
            if (isRed(y, dataElement)) {
               makeBlack(this.getParent(currentNode, dataElement), dataElement);
               makeBlack(y, dataElement);
               makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
               currentNode = this.getGrandParent(currentNode, dataElement);
            } else {
               if (currentNode.isRightChild(dataElement)) {
                  currentNode = this.getParent(currentNode, dataElement);
                  this.rotateLeft(currentNode, dataElement);
               }

               makeBlack(this.getParent(currentNode, dataElement), dataElement);
               makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
               if (this.getGrandParent(currentNode, dataElement) != null) {
                  this.rotateRight(this.getGrandParent(currentNode, dataElement), dataElement);
               }
            }
         } else {
            Node<K, V> y = this.getLeftChild(this.getGrandParent(currentNode, dataElement), dataElement);
            if (isRed(y, dataElement)) {
               makeBlack(this.getParent(currentNode, dataElement), dataElement);
               makeBlack(y, dataElement);
               makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
               currentNode = this.getGrandParent(currentNode, dataElement);
            } else {
               if (currentNode.isLeftChild(dataElement)) {
                  currentNode = this.getParent(currentNode, dataElement);
                  this.rotateRight(currentNode, dataElement);
               }

               makeBlack(this.getParent(currentNode, dataElement), dataElement);
               makeRed(this.getGrandParent(currentNode, dataElement), dataElement);
               if (this.getGrandParent(currentNode, dataElement) != null) {
                  this.rotateLeft(this.getGrandParent(currentNode, dataElement), dataElement);
               }
            }
         }
      }

      makeBlack(this.rootNode[dataElement.ordinal()], dataElement);
   }

   private void doRedBlackDelete(Node deletedNode) {
      for(DataElement dataElement : TreeBidiMap.DataElement.values()) {
         if (deletedNode.getLeft(dataElement) != null && deletedNode.getRight(dataElement) != null) {
            this.swapPosition(this.nextGreater(deletedNode, dataElement), deletedNode, dataElement);
         }

         Node<K, V> replacement = deletedNode.getLeft(dataElement) != null ? deletedNode.getLeft(dataElement) : deletedNode.getRight(dataElement);
         if (replacement != null) {
            replacement.setParent(deletedNode.getParent(dataElement), dataElement);
            if (deletedNode.getParent(dataElement) == null) {
               this.rootNode[dataElement.ordinal()] = replacement;
            } else if (deletedNode == deletedNode.getParent(dataElement).getLeft(dataElement)) {
               deletedNode.getParent(dataElement).setLeft(replacement, dataElement);
            } else {
               deletedNode.getParent(dataElement).setRight(replacement, dataElement);
            }

            deletedNode.setLeft((Node)null, dataElement);
            deletedNode.setRight((Node)null, dataElement);
            deletedNode.setParent((Node)null, dataElement);
            if (isBlack(deletedNode, dataElement)) {
               this.doRedBlackDeleteFixup(replacement, dataElement);
            }
         } else if (deletedNode.getParent(dataElement) == null) {
            this.rootNode[dataElement.ordinal()] = null;
         } else {
            if (isBlack(deletedNode, dataElement)) {
               this.doRedBlackDeleteFixup(deletedNode, dataElement);
            }

            if (deletedNode.getParent(dataElement) != null) {
               if (deletedNode == deletedNode.getParent(dataElement).getLeft(dataElement)) {
                  deletedNode.getParent(dataElement).setLeft((Node)null, dataElement);
               } else {
                  deletedNode.getParent(dataElement).setRight((Node)null, dataElement);
               }

               deletedNode.setParent((Node)null, dataElement);
            }
         }
      }

      this.shrink();
   }

   private void doRedBlackDeleteFixup(Node replacementNode, DataElement dataElement) {
      Node<K, V> currentNode = replacementNode;

      while(currentNode != this.rootNode[dataElement.ordinal()] && isBlack(currentNode, dataElement)) {
         if (currentNode.isLeftChild(dataElement)) {
            Node<K, V> siblingNode = this.getRightChild(this.getParent(currentNode, dataElement), dataElement);
            if (isRed(siblingNode, dataElement)) {
               makeBlack(siblingNode, dataElement);
               makeRed(this.getParent(currentNode, dataElement), dataElement);
               this.rotateLeft(this.getParent(currentNode, dataElement), dataElement);
               siblingNode = this.getRightChild(this.getParent(currentNode, dataElement), dataElement);
            }

            if (isBlack(this.getLeftChild(siblingNode, dataElement), dataElement) && isBlack(this.getRightChild(siblingNode, dataElement), dataElement)) {
               makeRed(siblingNode, dataElement);
               currentNode = this.getParent(currentNode, dataElement);
            } else {
               if (isBlack(this.getRightChild(siblingNode, dataElement), dataElement)) {
                  makeBlack(this.getLeftChild(siblingNode, dataElement), dataElement);
                  makeRed(siblingNode, dataElement);
                  this.rotateRight(siblingNode, dataElement);
                  siblingNode = this.getRightChild(this.getParent(currentNode, dataElement), dataElement);
               }

               this.copyColor(this.getParent(currentNode, dataElement), siblingNode, dataElement);
               makeBlack(this.getParent(currentNode, dataElement), dataElement);
               makeBlack(this.getRightChild(siblingNode, dataElement), dataElement);
               this.rotateLeft(this.getParent(currentNode, dataElement), dataElement);
               currentNode = this.rootNode[dataElement.ordinal()];
            }
         } else {
            Node<K, V> siblingNode = this.getLeftChild(this.getParent(currentNode, dataElement), dataElement);
            if (isRed(siblingNode, dataElement)) {
               makeBlack(siblingNode, dataElement);
               makeRed(this.getParent(currentNode, dataElement), dataElement);
               this.rotateRight(this.getParent(currentNode, dataElement), dataElement);
               siblingNode = this.getLeftChild(this.getParent(currentNode, dataElement), dataElement);
            }

            if (isBlack(this.getRightChild(siblingNode, dataElement), dataElement) && isBlack(this.getLeftChild(siblingNode, dataElement), dataElement)) {
               makeRed(siblingNode, dataElement);
               currentNode = this.getParent(currentNode, dataElement);
            } else {
               if (isBlack(this.getLeftChild(siblingNode, dataElement), dataElement)) {
                  makeBlack(this.getRightChild(siblingNode, dataElement), dataElement);
                  makeRed(siblingNode, dataElement);
                  this.rotateLeft(siblingNode, dataElement);
                  siblingNode = this.getLeftChild(this.getParent(currentNode, dataElement), dataElement);
               }

               this.copyColor(this.getParent(currentNode, dataElement), siblingNode, dataElement);
               makeBlack(this.getParent(currentNode, dataElement), dataElement);
               makeBlack(this.getLeftChild(siblingNode, dataElement), dataElement);
               this.rotateRight(this.getParent(currentNode, dataElement), dataElement);
               currentNode = this.rootNode[dataElement.ordinal()];
            }
         }
      }

      makeBlack(currentNode, dataElement);
   }

   private void swapPosition(Node x, Node y, DataElement dataElement) {
      Node<K, V> xFormerParent = x.getParent(dataElement);
      Node<K, V> xFormerLeftChild = x.getLeft(dataElement);
      Node<K, V> xFormerRightChild = x.getRight(dataElement);
      Node<K, V> yFormerParent = y.getParent(dataElement);
      Node<K, V> yFormerLeftChild = y.getLeft(dataElement);
      Node<K, V> yFormerRightChild = y.getRight(dataElement);
      boolean xWasLeftChild = x.getParent(dataElement) != null && x == x.getParent(dataElement).getLeft(dataElement);
      boolean yWasLeftChild = y.getParent(dataElement) != null && y == y.getParent(dataElement).getLeft(dataElement);
      if (x == yFormerParent) {
         x.setParent(y, dataElement);
         if (yWasLeftChild) {
            y.setLeft(x, dataElement);
            y.setRight(xFormerRightChild, dataElement);
         } else {
            y.setRight(x, dataElement);
            y.setLeft(xFormerLeftChild, dataElement);
         }
      } else {
         x.setParent(yFormerParent, dataElement);
         if (yFormerParent != null) {
            if (yWasLeftChild) {
               yFormerParent.setLeft(x, dataElement);
            } else {
               yFormerParent.setRight(x, dataElement);
            }
         }

         y.setLeft(xFormerLeftChild, dataElement);
         y.setRight(xFormerRightChild, dataElement);
      }

      if (y == xFormerParent) {
         y.setParent(x, dataElement);
         if (xWasLeftChild) {
            x.setLeft(y, dataElement);
            x.setRight(yFormerRightChild, dataElement);
         } else {
            x.setRight(y, dataElement);
            x.setLeft(yFormerLeftChild, dataElement);
         }
      } else {
         y.setParent(xFormerParent, dataElement);
         if (xFormerParent != null) {
            if (xWasLeftChild) {
               xFormerParent.setLeft(y, dataElement);
            } else {
               xFormerParent.setRight(y, dataElement);
            }
         }

         x.setLeft(yFormerLeftChild, dataElement);
         x.setRight(yFormerRightChild, dataElement);
      }

      if (x.getLeft(dataElement) != null) {
         x.getLeft(dataElement).setParent(x, dataElement);
      }

      if (x.getRight(dataElement) != null) {
         x.getRight(dataElement).setParent(x, dataElement);
      }

      if (y.getLeft(dataElement) != null) {
         y.getLeft(dataElement).setParent(y, dataElement);
      }

      if (y.getRight(dataElement) != null) {
         y.getRight(dataElement).setParent(y, dataElement);
      }

      x.swapColors(y, dataElement);
      if (this.rootNode[dataElement.ordinal()] == x) {
         this.rootNode[dataElement.ordinal()] = y;
      } else if (this.rootNode[dataElement.ordinal()] == y) {
         this.rootNode[dataElement.ordinal()] = x;
      }

   }

   private static void checkNonNullComparable(Object o, DataElement dataElement) {
      if (o == null) {
         throw new NullPointerException(dataElement + " cannot be null");
      } else if (!(o instanceof Comparable)) {
         throw new ClassCastException(dataElement + " must be Comparable");
      }
   }

   private static void checkKey(Object key) {
      checkNonNullComparable(key, TreeBidiMap.DataElement.KEY);
   }

   private static void checkValue(Object value) {
      checkNonNullComparable(value, TreeBidiMap.DataElement.VALUE);
   }

   private static void checkKeyAndValue(Object key, Object value) {
      checkKey(key);
      checkValue(value);
   }

   private void modify() {
      ++this.modifications;
   }

   private void grow() {
      this.modify();
      ++this.nodeCount;
   }

   private void shrink() {
      this.modify();
      --this.nodeCount;
   }

   private void insertValue(Node newNode) throws IllegalArgumentException {
      Node<K, V> node = this.rootNode[TreeBidiMap.DataElement.VALUE.ordinal()];

      while(true) {
         int cmp = compare(newNode.getValue(), node.getValue());
         if (cmp == 0) {
            throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(TreeBidiMap.DataElement.VALUE) + "\") in this Map");
         }

         if (cmp < 0) {
            if (node.getLeft(TreeBidiMap.DataElement.VALUE) == null) {
               node.setLeft(newNode, TreeBidiMap.DataElement.VALUE);
               newNode.setParent(node, TreeBidiMap.DataElement.VALUE);
               this.doRedBlackInsert(newNode, TreeBidiMap.DataElement.VALUE);
               break;
            }

            node = node.getLeft(TreeBidiMap.DataElement.VALUE);
         } else {
            if (node.getRight(TreeBidiMap.DataElement.VALUE) == null) {
               node.setRight(newNode, TreeBidiMap.DataElement.VALUE);
               newNode.setParent(node, TreeBidiMap.DataElement.VALUE);
               this.doRedBlackInsert(newNode, TreeBidiMap.DataElement.VALUE);
               break;
            }

            node = node.getRight(TreeBidiMap.DataElement.VALUE);
         }
      }

   }

   private boolean doEquals(Object obj, DataElement dataElement) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof Map)) {
         return false;
      } else {
         Map<?, ?> other = (Map)obj;
         if (other.size() != this.size()) {
            return false;
         } else {
            if (this.nodeCount > 0) {
               try {
                  MapIterator<?, ?> it = this.getMapIterator(dataElement);

                  while(it.hasNext()) {
                     Object key = it.next();
                     Object value = it.getValue();
                     if (!value.equals(other.get(key))) {
                        return false;
                     }
                  }
               } catch (ClassCastException var7) {
                  return false;
               } catch (NullPointerException var8) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   private int doHashCode(DataElement dataElement) {
      int total = 0;
      Object key;
      Object value;
      if (this.nodeCount > 0) {
         for(MapIterator<?, ?> it = this.getMapIterator(dataElement); it.hasNext(); total += key.hashCode() ^ value.hashCode()) {
            key = it.next();
            value = it.getValue();
         }
      }

      return total;
   }

   private String doToString(DataElement dataElement) {
      if (this.nodeCount == 0) {
         return "{}";
      } else {
         StringBuilder buf = new StringBuilder(this.nodeCount * 32);
         buf.append('{');
         MapIterator<?, ?> it = this.getMapIterator(dataElement);
         boolean hasNext = it.hasNext();

         while(hasNext) {
            Object key = it.next();
            Object value = it.getValue();
            buf.append(key == this ? "(this Map)" : key).append('=').append(value == this ? "(this Map)" : value);
            hasNext = it.hasNext();
            if (hasNext) {
               buf.append(", ");
            }
         }

         buf.append('}');
         return buf.toString();
      }
   }

   private MapIterator getMapIterator(DataElement dataElement) {
      switch (dataElement) {
         case KEY:
            return new ViewMapIterator(TreeBidiMap.DataElement.KEY);
         case VALUE:
            return new InverseViewMapIterator(TreeBidiMap.DataElement.VALUE);
         default:
            throw new IllegalArgumentException();
      }
   }

   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
      stream.defaultReadObject();
      this.rootNode = new Node[2];
      int size = stream.readInt();

      for(int i = 0; i < size; ++i) {
         K k = (K)((Comparable)stream.readObject());
         V v = (V)((Comparable)stream.readObject());
         this.put(k, v);
      }

   }

   private void writeObject(ObjectOutputStream stream) throws IOException {
      stream.defaultWriteObject();
      stream.writeInt(this.size());

      for(Map.Entry entry : this.entrySet()) {
         stream.writeObject(entry.getKey());
         stream.writeObject(entry.getValue());
      }

   }

   static enum DataElement {
      KEY("key"),
      VALUE("value");

      private final String description;

      private DataElement(String description) {
         this.description = description;
      }

      public String toString() {
         return this.description;
      }
   }

   abstract class View extends AbstractSet {
      final DataElement orderType;

      View(DataElement orderType) {
         this.orderType = orderType;
      }

      public int size() {
         return TreeBidiMap.this.size();
      }

      public void clear() {
         TreeBidiMap.this.clear();
      }
   }

   class KeyView extends View {
      public KeyView(DataElement orderType) {
         super(orderType);
      }

      public Iterator iterator() {
         return TreeBidiMap.this.new ViewMapIterator(this.orderType);
      }

      public boolean contains(Object obj) {
         TreeBidiMap.checkNonNullComparable(obj, TreeBidiMap.DataElement.KEY);
         return TreeBidiMap.this.lookupKey(obj) != null;
      }

      public boolean remove(Object o) {
         return TreeBidiMap.this.doRemoveKey(o) != null;
      }
   }

   class ValueView extends View {
      public ValueView(DataElement orderType) {
         super(orderType);
      }

      public Iterator iterator() {
         return TreeBidiMap.this.new InverseViewMapIterator(this.orderType);
      }

      public boolean contains(Object obj) {
         TreeBidiMap.checkNonNullComparable(obj, TreeBidiMap.DataElement.VALUE);
         return TreeBidiMap.this.lookupValue(obj) != null;
      }

      public boolean remove(Object o) {
         return TreeBidiMap.this.doRemoveValue(o) != null;
      }
   }

   class EntryView extends View {
      EntryView() {
         super(TreeBidiMap.DataElement.KEY);
      }

      public boolean contains(Object obj) {
         if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupKey(entry.getKey());
            return node != null && node.getValue().equals(value);
         }
      }

      public boolean remove(Object obj) {
         if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupKey(entry.getKey());
            if (node != null && node.getValue().equals(value)) {
               TreeBidiMap.this.doRedBlackDelete(node);
               return true;
            } else {
               return false;
            }
         }
      }

      public Iterator iterator() {
         return TreeBidiMap.this.new ViewMapEntryIterator();
      }
   }

   class InverseEntryView extends View {
      InverseEntryView() {
         super(TreeBidiMap.DataElement.VALUE);
      }

      public boolean contains(Object obj) {
         if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupValue(entry.getKey());
            return node != null && node.getKey().equals(value);
         }
      }

      public boolean remove(Object obj) {
         if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> entry = (Map.Entry)obj;
            Object value = entry.getValue();
            Node<K, V> node = TreeBidiMap.this.lookupValue(entry.getKey());
            if (node != null && node.getKey().equals(value)) {
               TreeBidiMap.this.doRedBlackDelete(node);
               return true;
            } else {
               return false;
            }
         }
      }

      public Iterator iterator() {
         return TreeBidiMap.this.new InverseViewMapEntryIterator();
      }
   }

   abstract class ViewIterator {
      private final DataElement orderType;
      Node lastReturnedNode;
      private Node nextNode;
      private Node previousNode;
      private int expectedModifications;

      ViewIterator(DataElement orderType) {
         this.orderType = orderType;
         this.expectedModifications = TreeBidiMap.this.modifications;
         this.nextNode = TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[orderType.ordinal()], orderType);
         this.lastReturnedNode = null;
         this.previousNode = null;
      }

      public final boolean hasNext() {
         return this.nextNode != null;
      }

      protected Node navigateNext() {
         if (this.nextNode == null) {
            throw new NoSuchElementException();
         } else if (TreeBidiMap.this.modifications != this.expectedModifications) {
            throw new ConcurrentModificationException();
         } else {
            this.lastReturnedNode = this.nextNode;
            this.previousNode = this.nextNode;
            this.nextNode = TreeBidiMap.this.nextGreater(this.nextNode, this.orderType);
            return this.lastReturnedNode;
         }
      }

      public boolean hasPrevious() {
         return this.previousNode != null;
      }

      protected Node navigatePrevious() {
         if (this.previousNode == null) {
            throw new NoSuchElementException();
         } else if (TreeBidiMap.this.modifications != this.expectedModifications) {
            throw new ConcurrentModificationException();
         } else {
            this.nextNode = this.lastReturnedNode;
            if (this.nextNode == null) {
               this.nextNode = TreeBidiMap.this.nextGreater(this.previousNode, this.orderType);
            }

            this.lastReturnedNode = this.previousNode;
            this.previousNode = TreeBidiMap.this.nextSmaller(this.previousNode, this.orderType);
            return this.lastReturnedNode;
         }
      }

      public final void remove() {
         if (this.lastReturnedNode == null) {
            throw new IllegalStateException();
         } else if (TreeBidiMap.this.modifications != this.expectedModifications) {
            throw new ConcurrentModificationException();
         } else {
            TreeBidiMap.this.doRedBlackDelete(this.lastReturnedNode);
            ++this.expectedModifications;
            this.lastReturnedNode = null;
            if (this.nextNode == null) {
               this.previousNode = TreeBidiMap.this.greatestNode(TreeBidiMap.this.rootNode[this.orderType.ordinal()], this.orderType);
            } else {
               this.previousNode = TreeBidiMap.this.nextSmaller(this.nextNode, this.orderType);
            }

         }
      }
   }

   class ViewMapIterator extends ViewIterator implements OrderedMapIterator {
      ViewMapIterator(DataElement orderType) {
         super(orderType);
      }

      public Comparable getKey() {
         if (this.lastReturnedNode == null) {
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
         } else {
            return this.lastReturnedNode.getKey();
         }
      }

      public Comparable getValue() {
         if (this.lastReturnedNode == null) {
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
         } else {
            return this.lastReturnedNode.getValue();
         }
      }

      public Comparable setValue(Comparable obj) {
         throw new UnsupportedOperationException();
      }

      public Comparable next() {
         return this.navigateNext().getKey();
      }

      public Comparable previous() {
         return this.navigatePrevious().getKey();
      }
   }

   class InverseViewMapIterator extends ViewIterator implements OrderedMapIterator {
      public InverseViewMapIterator(DataElement orderType) {
         super(orderType);
      }

      public Comparable getKey() {
         if (this.lastReturnedNode == null) {
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
         } else {
            return this.lastReturnedNode.getValue();
         }
      }

      public Comparable getValue() {
         if (this.lastReturnedNode == null) {
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
         } else {
            return this.lastReturnedNode.getKey();
         }
      }

      public Comparable setValue(Comparable obj) {
         throw new UnsupportedOperationException();
      }

      public Comparable next() {
         return this.navigateNext().getValue();
      }

      public Comparable previous() {
         return this.navigatePrevious().getValue();
      }
   }

   class ViewMapEntryIterator extends ViewIterator implements OrderedIterator {
      ViewMapEntryIterator() {
         super(TreeBidiMap.DataElement.KEY);
      }

      public Map.Entry next() {
         return this.navigateNext();
      }

      public Map.Entry previous() {
         return this.navigatePrevious();
      }
   }

   class InverseViewMapEntryIterator extends ViewIterator implements OrderedIterator {
      InverseViewMapEntryIterator() {
         super(TreeBidiMap.DataElement.VALUE);
      }

      public Map.Entry next() {
         return this.createEntry(this.navigateNext());
      }

      public Map.Entry previous() {
         return this.createEntry(this.navigatePrevious());
      }

      private Map.Entry createEntry(Node node) {
         return new UnmodifiableMapEntry(node.getValue(), node.getKey());
      }
   }

   static class Node implements Map.Entry, KeyValue {
      private final Comparable key;
      private final Comparable value;
      private final Node[] leftNode;
      private final Node[] rightNode;
      private final Node[] parentNode;
      private final boolean[] blackColor;
      private int hashcodeValue;
      private boolean calculatedHashCode;

      Node(Comparable key, Comparable value) {
         this.key = key;
         this.value = value;
         this.leftNode = new Node[2];
         this.rightNode = new Node[2];
         this.parentNode = new Node[2];
         this.blackColor = new boolean[]{true, true};
         this.calculatedHashCode = false;
      }

      private Object getData(DataElement dataElement) {
         switch (dataElement) {
            case KEY:
               return this.getKey();
            case VALUE:
               return this.getValue();
            default:
               throw new IllegalArgumentException();
         }
      }

      private void setLeft(Node node, DataElement dataElement) {
         this.leftNode[dataElement.ordinal()] = node;
      }

      private Node getLeft(DataElement dataElement) {
         return this.leftNode[dataElement.ordinal()];
      }

      private void setRight(Node node, DataElement dataElement) {
         this.rightNode[dataElement.ordinal()] = node;
      }

      private Node getRight(DataElement dataElement) {
         return this.rightNode[dataElement.ordinal()];
      }

      private void setParent(Node node, DataElement dataElement) {
         this.parentNode[dataElement.ordinal()] = node;
      }

      private Node getParent(DataElement dataElement) {
         return this.parentNode[dataElement.ordinal()];
      }

      private void swapColors(Node node, DataElement dataElement) {
         boolean[] var10000 = this.blackColor;
         int var10001 = dataElement.ordinal();
         var10000[var10001] ^= node.blackColor[dataElement.ordinal()];
         var10000 = node.blackColor;
         var10001 = dataElement.ordinal();
         var10000[var10001] ^= this.blackColor[dataElement.ordinal()];
         var10000 = this.blackColor;
         var10001 = dataElement.ordinal();
         var10000[var10001] ^= node.blackColor[dataElement.ordinal()];
      }

      private boolean isBlack(DataElement dataElement) {
         return this.blackColor[dataElement.ordinal()];
      }

      private boolean isRed(DataElement dataElement) {
         return !this.blackColor[dataElement.ordinal()];
      }

      private void setBlack(DataElement dataElement) {
         this.blackColor[dataElement.ordinal()] = true;
      }

      private void setRed(DataElement dataElement) {
         this.blackColor[dataElement.ordinal()] = false;
      }

      private void copyColor(Node node, DataElement dataElement) {
         this.blackColor[dataElement.ordinal()] = node.blackColor[dataElement.ordinal()];
      }

      private boolean isLeftChild(DataElement dataElement) {
         return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].leftNode[dataElement.ordinal()] == this;
      }

      private boolean isRightChild(DataElement dataElement) {
         return this.parentNode[dataElement.ordinal()] != null && this.parentNode[dataElement.ordinal()].rightNode[dataElement.ordinal()] == this;
      }

      public Comparable getKey() {
         return this.key;
      }

      public Comparable getValue() {
         return this.value;
      }

      public Comparable setValue(Comparable ignored) throws UnsupportedOperationException {
         throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
      }

      public boolean equals(Object obj) {
         if (obj == this) {
            return true;
         } else if (!(obj instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry<?, ?> e = (Map.Entry)obj;
            return this.getKey().equals(e.getKey()) && this.getValue().equals(e.getValue());
         }
      }

      public int hashCode() {
         if (!this.calculatedHashCode) {
            this.hashcodeValue = this.getKey().hashCode() ^ this.getValue().hashCode();
            this.calculatedHashCode = true;
         }

         return this.hashcodeValue;
      }
   }

   class Inverse implements OrderedBidiMap {
      private Set inverseKeySet;
      private Set inverseValuesSet;
      private Set inverseEntrySet;

      public int size() {
         return TreeBidiMap.this.size();
      }

      public boolean isEmpty() {
         return TreeBidiMap.this.isEmpty();
      }

      public Comparable get(Object key) {
         return TreeBidiMap.this.getKey(key);
      }

      public Comparable getKey(Object value) {
         return TreeBidiMap.this.get(value);
      }

      public boolean containsKey(Object key) {
         return TreeBidiMap.this.containsValue(key);
      }

      public boolean containsValue(Object value) {
         return TreeBidiMap.this.containsKey(value);
      }

      public Comparable firstKey() {
         if (TreeBidiMap.this.nodeCount == 0) {
            throw new NoSuchElementException("Map is empty");
         } else {
            return TreeBidiMap.this.leastNode(TreeBidiMap.this.rootNode[TreeBidiMap.DataElement.VALUE.ordinal()], TreeBidiMap.DataElement.VALUE).getValue();
         }
      }

      public Comparable lastKey() {
         if (TreeBidiMap.this.nodeCount == 0) {
            throw new NoSuchElementException("Map is empty");
         } else {
            return TreeBidiMap.this.greatestNode(TreeBidiMap.this.rootNode[TreeBidiMap.DataElement.VALUE.ordinal()], TreeBidiMap.DataElement.VALUE).getValue();
         }
      }

      public Comparable nextKey(Comparable key) {
         TreeBidiMap.checkKey(key);
         Node<K, V> node = TreeBidiMap.this.nextGreater(TreeBidiMap.this.lookup(key, TreeBidiMap.DataElement.VALUE), TreeBidiMap.DataElement.VALUE);
         return node == null ? null : node.getValue();
      }

      public Comparable previousKey(Comparable key) {
         TreeBidiMap.checkKey(key);
         Node<K, V> node = TreeBidiMap.this.nextSmaller(TreeBidiMap.this.lookup(key, TreeBidiMap.DataElement.VALUE), TreeBidiMap.DataElement.VALUE);
         return node == null ? null : node.getValue();
      }

      public Comparable put(Comparable key, Comparable value) {
         K result = (K)this.get(key);
         TreeBidiMap.this.doPut(value, key);
         return result;
      }

      public void putAll(Map map) {
         for(Map.Entry e : map.entrySet()) {
            this.put((Comparable)e.getKey(), (Comparable)e.getValue());
         }

      }

      public Comparable remove(Object key) {
         return TreeBidiMap.this.removeValue(key);
      }

      public Comparable removeValue(Object value) {
         return TreeBidiMap.this.remove(value);
      }

      public void clear() {
         TreeBidiMap.this.clear();
      }

      public Set keySet() {
         if (this.inverseKeySet == null) {
            this.inverseKeySet = TreeBidiMap.this.new ValueView(TreeBidiMap.DataElement.VALUE);
         }

         return this.inverseKeySet;
      }

      public Set values() {
         if (this.inverseValuesSet == null) {
            this.inverseValuesSet = TreeBidiMap.this.new KeyView(TreeBidiMap.DataElement.VALUE);
         }

         return this.inverseValuesSet;
      }

      public Set entrySet() {
         if (this.inverseEntrySet == null) {
            this.inverseEntrySet = TreeBidiMap.this.new InverseEntryView();
         }

         return this.inverseEntrySet;
      }

      public OrderedMapIterator mapIterator() {
         return (OrderedMapIterator)(this.isEmpty() ? EmptyOrderedMapIterator.emptyOrderedMapIterator() : TreeBidiMap.this.new InverseViewMapIterator(TreeBidiMap.DataElement.VALUE));
      }

      public OrderedBidiMap inverseBidiMap() {
         return TreeBidiMap.this;
      }

      public boolean equals(Object obj) {
         return TreeBidiMap.this.doEquals(obj, TreeBidiMap.DataElement.VALUE);
      }

      public int hashCode() {
         return TreeBidiMap.this.doHashCode(TreeBidiMap.DataElement.VALUE);
      }

      public String toString() {
         return TreeBidiMap.this.doToString(TreeBidiMap.DataElement.VALUE);
      }
   }
}
