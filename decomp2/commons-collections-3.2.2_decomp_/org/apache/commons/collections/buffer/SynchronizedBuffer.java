package org.apache.commons.collections.buffer;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.collection.SynchronizedCollection;

public class SynchronizedBuffer extends SynchronizedCollection implements Buffer {
   private static final long serialVersionUID = -6859936183953626253L;

   public static Buffer decorate(Buffer buffer) {
      return new SynchronizedBuffer(buffer);
   }

   protected SynchronizedBuffer(Buffer buffer) {
      super(buffer);
   }

   protected SynchronizedBuffer(Buffer buffer, Object lock) {
      super(buffer, lock);
   }

   protected Buffer getBuffer() {
      return (Buffer)this.collection;
   }

   public Object get() {
      synchronized(this.lock) {
         return this.getBuffer().get();
      }
   }

   public Object remove() {
      synchronized(this.lock) {
         return this.getBuffer().remove();
      }
   }
}
