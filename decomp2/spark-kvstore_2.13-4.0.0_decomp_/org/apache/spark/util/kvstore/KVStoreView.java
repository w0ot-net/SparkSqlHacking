package org.apache.spark.util.kvstore;

import org.apache.spark.annotation.Private;
import org.sparkproject.guava.base.Preconditions;

@Private
public abstract class KVStoreView implements Iterable {
   boolean ascending = true;
   String index = "__main__";
   Object first = null;
   Object last = null;
   Object parent = null;
   long skip = 0L;
   long max = Long.MAX_VALUE;

   public KVStoreView reverse() {
      this.ascending = !this.ascending;
      return this;
   }

   public KVStoreView index(String name) {
      this.index = (String)Preconditions.checkNotNull(name);
      return this;
   }

   public KVStoreView parent(Object value) {
      this.parent = value;
      return this;
   }

   public KVStoreView first(Object value) {
      this.first = value;
      return this;
   }

   public KVStoreView last(Object value) {
      this.last = value;
      return this;
   }

   public KVStoreView max(long max) {
      Preconditions.checkArgument(max > 0L, "max must be positive.");
      this.max = max;
      return this;
   }

   public KVStoreView skip(long n) {
      this.skip = n;
      return this;
   }

   public KVStoreIterator closeableIterator() throws Exception {
      return (KVStoreIterator)this.iterator();
   }
}
