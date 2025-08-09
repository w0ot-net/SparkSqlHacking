package com.clearspring.analytics.stream;

import com.clearspring.analytics.util.DoublyLinkedList;
import com.clearspring.analytics.util.ExternalizableUtil;
import com.clearspring.analytics.util.ListNode2;
import com.clearspring.analytics.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StreamSummary implements ITopK, Externalizable {
   protected int capacity;
   private HashMap counterMap;
   protected DoublyLinkedList bucketList;

   public StreamSummary(int capacity) {
      this.capacity = capacity;
      this.counterMap = new HashMap();
      this.bucketList = new DoublyLinkedList();
   }

   public int getCapacity() {
      return this.capacity;
   }

   public boolean offer(Object item) {
      return this.offer(item, 1);
   }

   public boolean offer(Object item, int incrementCount) {
      return (Boolean)this.offerReturnAll(item, incrementCount).left;
   }

   public Object offerReturnDropped(Object item, int incrementCount) {
      return this.offerReturnAll(item, incrementCount).right;
   }

   public Pair offerReturnAll(Object item, int incrementCount) {
      ListNode2<Counter<T>> counterNode = (ListNode2)this.counterMap.get(item);
      boolean isNewItem = counterNode == null;
      T droppedItem = (T)null;
      if (isNewItem) {
         if (this.size() < this.capacity) {
            counterNode = ((Bucket)this.bucketList.enqueue(new Bucket(0L)).getValue()).counterList.add((Object)(new Counter(this.bucketList.tail(), item)));
         } else {
            StreamSummary<T>.Bucket min = (Bucket)this.bucketList.first();
            counterNode = min.counterList.tail();
            Counter<T> counter = (Counter)counterNode.getValue();
            droppedItem = (T)counter.item;
            this.counterMap.remove(droppedItem);
            counter.item = item;
            counter.error = min.count;
         }

         this.counterMap.put(item, counterNode);
      }

      this.incrementCounter(counterNode, incrementCount);
      return new Pair(isNewItem, droppedItem);
   }

   protected void incrementCounter(ListNode2 counterNode, int incrementCount) {
      Counter<T> counter = (Counter)counterNode.getValue();
      ListNode2<StreamSummary<T>.Bucket> oldNode = counter.bucketNode;
      StreamSummary<T>.Bucket bucket = (Bucket)oldNode.getValue();
      bucket.counterList.remove(counterNode);
      counter.count += (long)incrementCount;
      ListNode2<StreamSummary<T>.Bucket> bucketNodePrev = oldNode;
      ListNode2<StreamSummary<T>.Bucket> bucketNodeNext = oldNode.getNext();

      while(bucketNodeNext != null) {
         StreamSummary<T>.Bucket bucketNext = (Bucket)bucketNodeNext.getValue();
         if (counter.count == bucketNext.count) {
            bucketNext.counterList.add(counterNode);
            break;
         }

         if (counter.count > bucketNext.count) {
            bucketNodePrev = bucketNodeNext;
            bucketNodeNext = bucketNodeNext.getNext();
         } else {
            bucketNodeNext = null;
         }
      }

      if (bucketNodeNext == null) {
         StreamSummary<T>.Bucket bucketNext = new Bucket(counter.count);
         bucketNext.counterList.add(counterNode);
         bucketNodeNext = this.bucketList.addAfter(bucketNodePrev, (Object)bucketNext);
      }

      counter.bucketNode = bucketNodeNext;
      if (bucket.counterList.isEmpty()) {
         this.bucketList.remove(oldNode);
      }

   }

   public List peek(int k) {
      List<T> topK = new ArrayList(k);

      for(ListNode2<StreamSummary<T>.Bucket> bNode = this.bucketList.head(); bNode != null; bNode = bNode.getPrev()) {
         StreamSummary<T>.Bucket b = (Bucket)bNode.getValue();

         for(Counter c : b.counterList) {
            if (topK.size() == k) {
               return topK;
            }

            topK.add(c.item);
         }
      }

      return topK;
   }

   public List topK(int k) {
      List<Counter<T>> topK = new ArrayList(k);

      for(ListNode2<StreamSummary<T>.Bucket> bNode = this.bucketList.head(); bNode != null; bNode = bNode.getPrev()) {
         StreamSummary<T>.Bucket b = (Bucket)bNode.getValue();

         for(Counter c : b.counterList) {
            if (topK.size() == k) {
               return topK;
            }

            topK.add(c);
         }
      }

      return topK;
   }

   public int size() {
      return this.counterMap.size();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append('[');

      for(ListNode2<StreamSummary<T>.Bucket> bNode = this.bucketList.head(); bNode != null; bNode = bNode.getPrev()) {
         StreamSummary<T>.Bucket b = (Bucket)bNode.getValue();
         sb.append('{');
         sb.append(b.count);
         sb.append(":[");

         for(Counter c : b.counterList) {
            sb.append('{');
            sb.append(c.item);
            sb.append(':');
            sb.append(c.error);
            sb.append("},");
         }

         if (b.counterList.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
         }

         sb.append("]},");
      }

      if (this.bucketList.size() > 0) {
         sb.deleteCharAt(sb.length() - 1);
      }

      sb.append(']');
      return sb.toString();
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.bucketList = new DoublyLinkedList();
      this.capacity = in.readInt();
      int size = in.readInt();
      this.counterMap = new HashMap(size);
      StreamSummary<T>.Bucket currentBucket = null;
      ListNode2<StreamSummary<T>.Bucket> currentBucketNode = null;

      for(int i = 0; i < size; ++i) {
         Counter<T> c = (Counter)in.readObject();
         if (currentBucket == null || c.count != currentBucket.count) {
            currentBucket = new Bucket(c.count);
            currentBucketNode = this.bucketList.add((Object)currentBucket);
         }

         c.bucketNode = currentBucketNode;
         this.counterMap.put(c.item, currentBucket.counterList.add((Object)c));
      }

   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeInt(this.capacity);
      out.writeInt(this.size());

      for(ListNode2<StreamSummary<T>.Bucket> bNode = this.bucketList.tail(); bNode != null; bNode = bNode.getNext()) {
         StreamSummary<T>.Bucket b = (Bucket)bNode.getValue();

         for(Counter c : b.counterList) {
            out.writeObject(c);
         }
      }

   }

   public StreamSummary() {
   }

   public StreamSummary(byte[] bytes) throws IOException, ClassNotFoundException {
      this.fromBytes(bytes);
   }

   public void fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
      this.readExternal(new ObjectInputStream(new ByteArrayInputStream(bytes)));
   }

   public byte[] toBytes() throws IOException {
      return ExternalizableUtil.toBytes(this);
   }

   protected class Bucket {
      protected DoublyLinkedList counterList;
      private long count;

      public Bucket(long count) {
         this.count = count;
         this.counterList = new DoublyLinkedList();
      }
   }
}
