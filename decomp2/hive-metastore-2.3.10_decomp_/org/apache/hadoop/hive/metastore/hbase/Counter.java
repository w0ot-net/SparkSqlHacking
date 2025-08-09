package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;

class Counter {
   private final String name;
   private long cnt;

   Counter(String name) {
      this.name = name;
      this.cnt = 0L;
   }

   void incr() {
      ++this.cnt;
   }

   void clear() {
      this.cnt = 0L;
   }

   String dump() {
      StringBuilder bldr = new StringBuilder("Dumping metric: ");
      bldr.append(this.name).append(' ').append(this.cnt);
      return bldr.toString();
   }

   @VisibleForTesting
   long getCnt() {
      return this.cnt;
   }
}
