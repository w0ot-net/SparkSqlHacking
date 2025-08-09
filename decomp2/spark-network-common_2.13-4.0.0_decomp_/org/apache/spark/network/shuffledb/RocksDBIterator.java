package org.apache.spark.network.shuffledb;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.rocksdb.RocksIterator;

public class RocksDBIterator implements DBIterator {
   private final RocksIterator it;
   private boolean checkedNext;
   private boolean closed;
   private Map.Entry next;

   public RocksDBIterator(RocksIterator it) {
      this.it = it;
   }

   public boolean hasNext() {
      if (!this.checkedNext && !this.closed) {
         this.next = this.loadNext();
         this.checkedNext = true;
      }

      if (!this.closed && this.next == null) {
         try {
            this.close();
         } catch (IOException ioe) {
            throw new RuntimeException(ioe);
         }
      }

      return this.next != null;
   }

   public Map.Entry next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         this.checkedNext = false;
         Map.Entry<byte[], byte[]> ret = this.next;
         this.next = null;
         return ret;
      }
   }

   public void close() throws IOException {
      if (!this.closed) {
         this.it.close();
         this.closed = true;
         this.next = null;
      }

   }

   public void seek(byte[] key) {
      this.it.seek(key);
   }

   private Map.Entry loadNext() {
      if (this.it.isValid()) {
         Map.Entry<byte[], byte[]> nextEntry = new AbstractMap.SimpleEntry(this.it.key(), this.it.value());
         this.it.next();
         return nextEntry;
      } else {
         return null;
      }
   }
}
