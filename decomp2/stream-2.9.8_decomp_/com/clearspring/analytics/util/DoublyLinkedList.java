package com.clearspring.analytics.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DoublyLinkedList implements Iterable {
   protected int size;
   protected ListNode2 tail;
   protected ListNode2 head;

   public ListNode2 add(Object value) {
      ListNode2<T> node = new ListNode2(value);
      if (this.size++ == 0) {
         this.tail = node;
      } else {
         node.prev = this.head;
         this.head.next = node;
      }

      this.head = node;
      return node;
   }

   public ListNode2 enqueue(Object value) {
      ListNode2<T> node = new ListNode2(value);
      if (this.size++ == 0) {
         this.head = node;
      } else {
         node.next = this.tail;
         this.tail.prev = node;
      }

      this.tail = node;
      return node;
   }

   public void add(ListNode2 node) {
      node.prev = this.head;
      node.next = null;
      if (this.size++ == 0) {
         this.tail = node;
      } else {
         this.head.next = node;
      }

      this.head = node;
   }

   public ListNode2 addAfter(ListNode2 node, Object value) {
      ListNode2<T> newNode = new ListNode2(value);
      this.addAfter(node, newNode);
      return newNode;
   }

   public void addAfter(ListNode2 node, ListNode2 newNode) {
      newNode.next = node.next;
      newNode.prev = node;
      node.next = newNode;
      if (newNode.next == null) {
         this.head = newNode;
      } else {
         newNode.next.prev = newNode;
      }

      ++this.size;
   }

   public void remove(ListNode2 node) {
      if (node == this.tail) {
         this.tail = node.next;
      } else {
         node.prev.next = node.next;
      }

      if (node == this.head) {
         this.head = node.prev;
      } else {
         node.next.prev = node.prev;
      }

      --this.size;
   }

   public int size() {
      return this.size;
   }

   public Iterator iterator() {
      return new DoublyLinkedListIterator(this);
   }

   public Object first() {
      return this.tail == null ? null : this.tail.getValue();
   }

   public Object last() {
      return this.head == null ? null : this.head.getValue();
   }

   public ListNode2 head() {
      return this.head;
   }

   public ListNode2 tail() {
      return this.tail;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public Object[] toArray() {
      T[] a = (T[])((Object[])(new Object[this.size]));
      int i = 0;

      for(Object v : this) {
         a[i++] = v;
      }

      return a;
   }

   protected class DoublyLinkedListIterator implements Iterator {
      protected DoublyLinkedList list;
      protected ListNode2 itr;
      protected int length;

      public DoublyLinkedListIterator(DoublyLinkedList list) {
         this.length = list.size;
         this.list = list;
         this.itr = list.tail;
      }

      public boolean hasNext() {
         return this.itr != null;
      }

      public Object next() {
         if (this.length != this.list.size) {
            throw new ConcurrentModificationException();
         } else {
            T next = (T)this.itr.value;
            this.itr = this.itr.next;
            return next;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
