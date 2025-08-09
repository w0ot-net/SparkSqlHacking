package com.zaxxer.hikari.pool;

import com.zaxxer.hikari.util.ClockSource;
import com.zaxxer.hikari.util.ConcurrentBag;
import com.zaxxer.hikari.util.FastList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class PoolEntry implements ConcurrentBag.IConcurrentBagEntry {
   private static final Logger LOGGER = LoggerFactory.getLogger(PoolEntry.class);
   private static final AtomicIntegerFieldUpdater stateUpdater = AtomicIntegerFieldUpdater.newUpdater(PoolEntry.class, "state");
   static final Comparator LASTACCESS_COMPARABLE = new Comparator() {
      public int compare(PoolEntry entryOne, PoolEntry entryTwo) {
         return Long.compare(entryOne.lastAccessed, entryTwo.lastAccessed);
      }
   };
   Connection connection;
   long lastAccessed;
   long lastBorrowed;
   private volatile int state;
   private volatile boolean evict;
   private volatile ScheduledFuture endOfLife;
   private final FastList openStatements;
   private final HikariPool hikariPool;
   private final boolean isReadOnly;
   private final boolean isAutoCommit;

   PoolEntry(Connection connection, PoolBase pool, boolean isReadOnly, boolean isAutoCommit) {
      this.connection = connection;
      this.hikariPool = (HikariPool)pool;
      this.isReadOnly = isReadOnly;
      this.isAutoCommit = isAutoCommit;
      this.lastAccessed = ClockSource.INSTANCE.currentTime();
      this.openStatements = new FastList(Statement.class, 16);
   }

   void recycle(long lastAccessed) {
      if (this.connection != null) {
         this.lastAccessed = lastAccessed;
         this.hikariPool.recycle(this);
      }

   }

   void setFutureEol(ScheduledFuture endOfLife) {
      this.endOfLife = endOfLife;
   }

   Connection createProxyConnection(ProxyLeakTask leakTask, long now) {
      return ProxyFactory.getProxyConnection(this, this.connection, this.openStatements, leakTask, now, this.isReadOnly, this.isAutoCommit);
   }

   void resetConnectionState(ProxyConnection proxyConnection, int dirtyBits) throws SQLException {
      this.hikariPool.resetConnectionState(this.connection, proxyConnection, dirtyBits);
   }

   String getPoolName() {
      return this.hikariPool.toString();
   }

   boolean isMarkedEvicted() {
      return this.evict;
   }

   void markEvicted() {
      this.evict = true;
   }

   void evict(String closureReason) {
      this.hikariPool.closeConnection(this, closureReason);
   }

   long getMillisSinceBorrowed() {
      return ClockSource.INSTANCE.elapsedMillis(this.lastBorrowed);
   }

   public String toString() {
      long now = ClockSource.INSTANCE.currentTime();
      return this.connection + ", accessed " + ClockSource.INSTANCE.elapsedDisplayString(this.lastAccessed, now) + " ago, " + this.stateToString();
   }

   public int getState() {
      return stateUpdater.get(this);
   }

   public boolean compareAndSet(int expect, int update) {
      return stateUpdater.compareAndSet(this, expect, update);
   }

   public void setState(int update) {
      stateUpdater.set(this, update);
   }

   Connection close() {
      ScheduledFuture<?> eol = this.endOfLife;
      if (eol != null && !eol.isDone() && !eol.cancel(false)) {
         LOGGER.warn("{} - maxLifeTime expiration task cancellation unexpectedly returned false for connection {}", this.getPoolName(), this.connection);
      }

      Connection con = this.connection;
      this.connection = null;
      this.endOfLife = null;
      return con;
   }

   private String stateToString() {
      switch (this.state) {
         case -2:
            return "RESERVED";
         case -1:
            return "REMOVED";
         case 0:
            return "NOT_IN_USE";
         case 1:
            return "IN_USE";
         default:
            return "Invalid";
      }
   }
}
