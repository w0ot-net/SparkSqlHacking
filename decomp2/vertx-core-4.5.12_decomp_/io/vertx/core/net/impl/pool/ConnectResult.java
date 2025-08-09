package io.vertx.core.net.impl.pool;

public class ConnectResult {
   private final Object conn;
   private final long concurrency;
   private final long capacity;

   public ConnectResult(Object connection, long concurrency, long capacity) {
      this.conn = connection;
      this.concurrency = concurrency;
      this.capacity = capacity;
   }

   public Object connection() {
      return this.conn;
   }

   public long concurrency() {
      return this.concurrency;
   }

   public long weight() {
      return this.capacity;
   }

   public long capacity() {
      return this.capacity;
   }
}
