package com.clearspring.analytics.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStreamSummary implements ITopK {
   private final int capacity;
   private final ConcurrentHashMap itemMap;
   private final AtomicReference minVal;
   private final AtomicLong size;
   private final AtomicBoolean reachCapacity;

   public ConcurrentStreamSummary(int capacity) {
      this.capacity = capacity;
      this.minVal = new AtomicReference();
      this.size = new AtomicLong(0L);
      this.itemMap = new ConcurrentHashMap(capacity);
      this.reachCapacity = new AtomicBoolean(false);
   }

   public boolean offer(Object element) {
      return this.offer(element, 1);
   }

   public boolean offer(Object element, int incrementCount) {
      long val = (long)incrementCount;
      ScoredItem<T> value = new ScoredItem(element, (long)incrementCount);
      ScoredItem<T> oldVal = (ScoredItem)this.itemMap.putIfAbsent(element, value);
      if (oldVal != null) {
         val = oldVal.addAndGetCount((long)incrementCount);
      } else if (this.reachCapacity.get() || this.size.incrementAndGet() > (long)this.capacity) {
         this.reachCapacity.set(true);
         ScoredItem<T> oldMinVal = (ScoredItem)this.minVal.getAndSet(value);
         this.itemMap.remove(oldMinVal.getItem());

         while(oldMinVal.isNewItem()) {
         }

         long count = oldMinVal.getCount();
         value.addAndGetCount(count);
         value.setError(count);
      }

      value.setNewItem(false);
      this.minVal.set(this.getMinValue());
      return val != (long)incrementCount;
   }

   private ScoredItem getMinValue() {
      ScoredItem<T> minVal = null;

      for(ScoredItem entry : this.itemMap.values()) {
         if (minVal == null || !entry.isNewItem() && entry.getCount() < minVal.getCount()) {
            minVal = entry;
         }
      }

      return minVal;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");

      for(ScoredItem entry : this.itemMap.values()) {
         sb.append("(" + entry.getCount() + ": " + entry.getItem() + ", e: " + entry.getError() + "),");
      }

      sb.deleteCharAt(sb.length() - 1);
      sb.append("]");
      return sb.toString();
   }

   public List peek(int k) {
      List<T> toReturn = new ArrayList(k);

      for(ScoredItem value : this.peekWithScores(k)) {
         toReturn.add(value.getItem());
      }

      return toReturn;
   }

   public List peekWithScores(int k) {
      List<ScoredItem<T>> values = new ArrayList();

      for(Map.Entry entry : this.itemMap.entrySet()) {
         ScoredItem<T> value = (ScoredItem)entry.getValue();
         values.add(new ScoredItem(value.getItem(), value.getCount(), value.getError()));
      }

      Collections.sort(values);
      values = values.size() > k ? values.subList(0, k) : values;
      return values;
   }
}
