package org.apache.spark.network.shuffledb;

import java.io.IOException;
import org.rocksdb.RocksDBException;

public class RocksDB implements DB {
   private final org.rocksdb.RocksDB db;

   public RocksDB(org.rocksdb.RocksDB db) {
      this.db = db;
   }

   public void put(byte[] key, byte[] value) {
      try {
         this.db.put(key, value);
      } catch (RocksDBException e) {
         throw new RuntimeException(e);
      }
   }

   public byte[] get(byte[] key) {
      try {
         return this.db.get(key);
      } catch (RocksDBException e) {
         throw new RuntimeException(e);
      }
   }

   public void delete(byte[] key) {
      try {
         this.db.delete(key);
      } catch (RocksDBException e) {
         throw new RuntimeException(e);
      }
   }

   public DBIterator iterator() {
      return new RocksDBIterator(this.db.newIterator());
   }

   public void close() throws IOException {
      this.db.close();
   }
}
