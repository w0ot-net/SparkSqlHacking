package com.clearspring.analytics.stream;

import com.clearspring.analytics.util.ListNode2;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Counter implements Externalizable {
   protected ListNode2 bucketNode;
   protected Object item;
   protected long count;
   protected long error;

   public Counter() {
   }

   public Counter(ListNode2 bucket, Object item) {
      this.bucketNode = bucket;
      this.count = 0L;
      this.error = 0L;
      this.item = item;
   }

   public Object getItem() {
      return this.item;
   }

   public long getCount() {
      return this.count;
   }

   public long getError() {
      return this.error;
   }

   public String toString() {
      return this.item + ":" + this.count + ':' + this.error;
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.item = in.readObject();
      this.count = in.readLong();
      this.error = in.readLong();
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeObject(this.item);
      out.writeLong(this.count);
      out.writeLong(this.error);
   }
}
