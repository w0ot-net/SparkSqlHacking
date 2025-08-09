package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

public final class LinkedQueueNode {
   private static final long NEXT_OFFSET = UnsafeAccess.fieldOffset(LinkedQueueNode.class, "next");
   private Object value;
   private volatile LinkedQueueNode next;

   public LinkedQueueNode() {
      this((Object)null);
   }

   public LinkedQueueNode(Object val) {
      this.spValue(val);
   }

   public Object getAndNullValue() {
      E temp = (E)this.lpValue();
      this.spValue((Object)null);
      return temp;
   }

   public Object lpValue() {
      return this.value;
   }

   public void spValue(Object newValue) {
      this.value = newValue;
   }

   public void soNext(LinkedQueueNode n) {
      UnsafeAccess.UNSAFE.putOrderedObject(this, NEXT_OFFSET, n);
   }

   public void spNext(LinkedQueueNode n) {
      UnsafeAccess.UNSAFE.putObject(this, NEXT_OFFSET, n);
   }

   public LinkedQueueNode lvNext() {
      return this.next;
   }
}
