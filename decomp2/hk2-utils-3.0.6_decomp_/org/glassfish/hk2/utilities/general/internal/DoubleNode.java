package org.glassfish.hk2.utilities.general.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class DoubleNode {
   private final WeakReference weakKey;
   private final Object value;
   private DoubleNode previous;
   private DoubleNode next;
   private Object hardenedKey;

   public DoubleNode(Object key, Object value, ReferenceQueue queue) {
      this.weakKey = new WeakReference(key, queue);
      this.value = value;
   }

   public DoubleNode getPrevious() {
      return this.previous;
   }

   public void setPrevious(DoubleNode previous) {
      this.previous = previous;
   }

   public DoubleNode getNext() {
      return this.next;
   }

   public void setNext(DoubleNode next) {
      this.next = next;
   }

   public WeakReference getWeakKey() {
      return this.weakKey;
   }

   public Object getValue() {
      return this.value;
   }

   public Object getHardenedKey() {
      return this.hardenedKey;
   }

   public void setHardenedKey(Object hardenedKey) {
      this.hardenedKey = hardenedKey;
   }
}
