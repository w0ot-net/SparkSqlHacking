package org.datanucleus.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public interface Level2Cache extends Serializable {
   void close();

   void evict(Object var1);

   void evictAll();

   void evictAll(Object[] var1);

   void evictAll(Collection var1);

   void evictAll(Class var1, boolean var2);

   void pin(Object var1);

   void pinAll(Collection var1);

   void pinAll(Object[] var1);

   void pinAll(Class var1, boolean var2);

   void unpin(Object var1);

   void unpinAll(Collection var1);

   void unpinAll(Object[] var1);

   void unpinAll(Class var1, boolean var2);

   int getNumberOfPinnedObjects();

   int getNumberOfUnpinnedObjects();

   int getSize();

   CachedPC get(Object var1);

   Map getAll(Collection var1);

   CachedPC put(Object var1, CachedPC var2);

   void putAll(Map var1);

   boolean isEmpty();

   boolean containsOid(Object var1);

   public static class PinnedClass {
      Class cls;
      boolean subclasses;

      public PinnedClass(Class cls, boolean subclasses) {
         this.cls = cls;
         this.subclasses = subclasses;
      }

      public int hashCode() {
         return this.cls.hashCode() ^ (this.subclasses ? 0 : 1);
      }

      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         } else if (!(obj instanceof PinnedClass)) {
            return false;
         } else {
            PinnedClass other = (PinnedClass)obj;
            return other.cls.getName().equals(this.cls.getName()) && other.subclasses == this.subclasses;
         }
      }
   }
}
