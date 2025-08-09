package org.glassfish.hk2.utilities.general;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Hk2ThreadLocal {
   private static final Object NULL = new Object();
   private final Map locals = new ConcurrentHashMap();
   private final ReferenceQueue queue = new ReferenceQueue();

   protected Object initialValue() {
      return null;
   }

   public Object get() {
      this.removeStaleEntries();
      Key key = newLookupKey();
      Object value = this.locals.get(key);
      if (value == null) {
         value = this.initialValue();
         this.locals.put(newStorageKey(this.queue), maskNull(value));
      } else {
         value = unmaskNull(value);
      }

      return value;
   }

   public void set(Object value) {
      Key key = newStorageKey(this.queue);
      this.locals.put(key, maskNull(value));
   }

   public void remove() {
      Key key = newLookupKey();
      this.locals.remove(key);
   }

   public void removeAll() {
      this.locals.clear();
   }

   public int getSize() {
      this.removeStaleEntries();
      return this.locals.size();
   }

   private void removeStaleEntries() {
      Object queued;
      while((queued = this.queue.poll()) != null) {
         Key key = (Key)queued;
         this.locals.remove(key);
      }

   }

   private static Object maskNull(Object value) {
      return value == null ? NULL : value;
   }

   private static Object unmaskNull(Object value) {
      return value == NULL ? null : value;
   }

   private static Key newStorageKey(ReferenceQueue queue) {
      return new Key(Thread.currentThread(), queue);
   }

   private static Key newLookupKey() {
      return new Key(Thread.currentThread(), (ReferenceQueue)null);
   }

   private static class Key extends WeakReference {
      private final long threadId;
      private final int hash;

      private Key(Thread thread, ReferenceQueue queue) {
         super(thread, queue);
         this.threadId = thread.getId();
         this.hash = thread.hashCode();
      }

      public boolean equals(Object obj) {
         if (!(obj instanceof Key)) {
            return false;
         } else {
            Key other = (Key)obj;
            return other.threadId == this.threadId;
         }
      }

      public int hashCode() {
         return this.hash;
      }
   }
}
