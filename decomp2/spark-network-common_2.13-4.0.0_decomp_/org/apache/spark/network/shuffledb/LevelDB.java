package org.apache.spark.network.shuffledb;

import java.io.IOException;

public class LevelDB implements DB {
   private final org.iq80.leveldb.DB db;

   public LevelDB(org.iq80.leveldb.DB db) {
      this.db = db;
   }

   public void put(byte[] key, byte[] value) {
      this.db.put(key, value);
   }

   public byte[] get(byte[] key) {
      return this.db.get(key);
   }

   public void delete(byte[] key) {
      this.db.delete(key);
   }

   public void close() throws IOException {
      this.db.close();
   }

   public DBIterator iterator() {
      return new LevelDBIterator(this.db.iterator());
   }
}
