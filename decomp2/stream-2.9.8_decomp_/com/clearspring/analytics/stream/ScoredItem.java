package com.clearspring.analytics.stream;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ScoredItem implements Comparable {
   private final AtomicLong error;
   private final AtomicLong count;
   private final AtomicBoolean newItem;
   private final Object item;

   public ScoredItem(Object item, long count, long error) {
      this.item = item;
      this.error = new AtomicLong(error);
      this.count = new AtomicLong(count);
      this.newItem = new AtomicBoolean(true);
   }

   public ScoredItem(Object item, long count) {
      this(item, count, 0L);
   }

   public long addAndGetCount(long delta) {
      return this.count.addAndGet(delta);
   }

   public void setError(long newError) {
      this.error.set(newError);
   }

   public long getError() {
      return this.error.get();
   }

   public Object getItem() {
      return this.item;
   }

   public boolean isNewItem() {
      return this.newItem.get();
   }

   public long getCount() {
      return this.count.get();
   }

   public int compareTo(ScoredItem o) {
      long x = o.count.get();
      long y = this.count.get();
      return x < y ? -1 : (x == y ? 0 : 1);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Value: ");
      sb.append(this.item);
      sb.append(", Count: ");
      sb.append(this.count);
      sb.append(", Error: ");
      sb.append(this.error);
      sb.append(", object: ");
      sb.append(super.toString());
      return sb.toString();
   }

   public void setNewItem(boolean newItem) {
      this.newItem.set(newItem);
   }
}
