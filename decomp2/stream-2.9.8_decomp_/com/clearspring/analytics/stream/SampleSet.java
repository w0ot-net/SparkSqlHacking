package com.clearspring.analytics.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SampleSet implements ISampleSet {
   private Map sampleMap;
   private int size;
   private long count;
   private Random random;
   private Node head;
   private Node tail;

   public SampleSet() {
      this(7);
   }

   public SampleSet(int capacity) {
      this(capacity, new Random());
   }

   public SampleSet(int capacity, Random random) {
      this.sampleMap = new HashMap(capacity);
      this.random = random;
   }

   public Object peek() {
      return this.head != null ? this.head.element : null;
   }

   public List peek(int k) {
      List<T> topK = new ArrayList(k);

      for(SampleSet<T>.Node<T> itr = this.head; itr != null && topK.size() < k; itr = itr.next) {
         topK.add(itr.element);
      }

      return topK;
   }

   public long put(Object element) {
      return this.put(element, 1);
   }

   public long put(Object element, int incrementCount) {
      SampleSet<T>.Node<T> node = (Node)this.sampleMap.get(element);
      if (node != null) {
         node.count = node.count + (long)incrementCount;
         this.promote(node);
      } else {
         node = new Node();
         node.element = element;
         node.count = (long)incrementCount;
         node.prev = this.tail;
         if (this.tail != null) {
            this.tail.next = node;
         }

         this.tail = node;
         if (this.head == null) {
            this.head = node;
         }

         this.sampleMap.put(element, node);
         ++this.size;
      }

      ++this.count;
      return node.count;
   }

   public Object removeRandom() {
      double p = this.random.nextDouble();
      long weight = 0L;

      for(SampleSet<T>.Node<T> itr = this.head; itr != null; itr = itr.next) {
         weight += itr.count;
         if (p < (double)weight / (double)this.count) {
            itr.count--;
            --this.count;
            this.demote(itr);
            if (itr.count == 0L) {
               this.removeMin();
            }

            return itr.element;
         }
      }

      return null;
   }

   protected Object removeMin() {
      if (this.tail == null) {
         return null;
      } else {
         --this.size;
         this.count -= this.tail.count;
         T minElement = (T)this.tail.element;
         this.tail = this.tail.prev;
         if (this.tail != null) {
            this.tail.next = null;
         }

         this.sampleMap.remove(minElement);
         return minElement;
      }
   }

   public int size() {
      return this.size;
   }

   public long count() {
      return this.count;
   }

   protected Object peekMin() {
      return this.tail.element;
   }

   protected void promote(Node node) {
      while(node.prev != null && node.count > node.prev.count) {
         SampleSet<T>.Node<T> b = node.prev;
         SampleSet<T>.Node<T> d = node.next;
         SampleSet<T>.Node<T> a = b == null ? null : b.prev;
         if (a != null) {
            a.next = node;
         }

         node.prev = a;
         node.next = b;
         b.prev = node;
         b.next = d;
         if (d != null) {
            d.prev = b;
         }

         if (this.head == b) {
            this.head = node;
         }

         if (this.tail == node) {
            this.tail = b;
         }
      }

   }

   protected void demote(Node node) {
      while(node.next != null && node.count < node.next.count) {
         SampleSet<T>.Node<T> a = node.prev;
         SampleSet<T>.Node<T> c = node.next;
         SampleSet<T>.Node<T> d = c == null ? null : c.next;
         if (a != null) {
            a.next = c;
         }

         c.prev = a;
         c.next = node;
         node.prev = c;
         if (d != null) {
            d.prev = node;
         }

         node.next = d;
         if (this.head == node) {
            this.head = c;
         }

         if (this.tail == c) {
            this.tail = node;
         }
      }

   }

   private class Node {
      private Node next;
      private Node prev;
      private Object element;
      private long count;

      private Node() {
      }
   }
}
