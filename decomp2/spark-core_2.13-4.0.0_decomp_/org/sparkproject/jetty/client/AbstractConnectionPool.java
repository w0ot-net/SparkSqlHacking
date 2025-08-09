package org.sparkproject.jetty.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.util.Attachable;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.Pool;
import org.sparkproject.jetty.util.Promise;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.thread.Sweeper;

@ManagedObject
public abstract class AbstractConnectionPool extends ContainerLifeCycle implements ConnectionPool, Dumpable, Sweeper.Sweepable {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractConnectionPool.class);
   private final AtomicInteger pending;
   private final HttpDestination destination;
   private final Callback requester;
   private final Pool pool;
   private boolean maximizeConnections;
   private volatile long maxDurationNanos;

   protected AbstractConnectionPool(HttpDestination destination, int maxConnections, boolean cache, Callback requester) {
      this(destination, Pool.StrategyType.FIRST, maxConnections, cache, requester);
   }

   protected AbstractConnectionPool(HttpDestination destination, Pool.StrategyType strategy, int maxConnections, boolean cache, Callback requester) {
      this(destination, new Pool(strategy, maxConnections, cache), requester);
   }

   protected AbstractConnectionPool(HttpDestination destination, Pool pool, Callback requester) {
      this.pending = new AtomicInteger();
      this.maxDurationNanos = 0L;
      this.destination = destination;
      this.requester = requester;
      this.pool = pool;
      pool.setMaxMultiplex(1);
      this.addBean(pool);
   }

   protected void doStop() throws Exception {
      this.pool.close();
   }

   public CompletableFuture preCreateConnections(int connectionCount) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Pre-creating connections {}/{}", connectionCount, this.getMaxConnectionCount());
      }

      List<CompletableFuture<?>> futures = new ArrayList();

      for(int i = 0; i < connectionCount; ++i) {
         Pool<Connection>.Entry entry = this.pool.reserve();
         if (entry == null) {
            break;
         }

         this.pending.incrementAndGet();
         Promise.Completable<Connection> future = new FutureConnection(entry);
         futures.add(future);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Pre-creating connection {}/{} at {}", new Object[]{futures.size(), this.getMaxConnectionCount(), entry});
         }

         this.destination.newConnection(future);
      }

      return CompletableFuture.allOf((CompletableFuture[])futures.toArray(new CompletableFuture[0]));
   }

   @ManagedAttribute("The maximum duration in milliseconds a connection can be used for before it gets closed")
   public long getMaxDuration() {
      return TimeUnit.NANOSECONDS.toMillis(this.maxDurationNanos);
   }

   public void setMaxDuration(long maxDurationInMs) {
      this.maxDurationNanos = TimeUnit.MILLISECONDS.toNanos(maxDurationInMs);
   }

   protected int getMaxMultiplex() {
      return this.pool.getMaxMultiplex();
   }

   protected void setMaxMultiplex(int maxMultiplex) {
      this.pool.setMaxMultiplex(maxMultiplex);
   }

   protected int getMaxUsageCount() {
      return this.pool.getMaxUsageCount();
   }

   protected void setMaxUsageCount(int maxUsageCount) {
      this.pool.setMaxUsageCount(maxUsageCount);
   }

   @ManagedAttribute(
      value = "The number of active connections",
      readonly = true
   )
   public int getActiveConnectionCount() {
      return this.pool.getInUseCount();
   }

   @ManagedAttribute(
      value = "The number of idle connections",
      readonly = true
   )
   public int getIdleConnectionCount() {
      return this.pool.getIdleCount();
   }

   @ManagedAttribute(
      value = "The max number of connections",
      readonly = true
   )
   public int getMaxConnectionCount() {
      return this.pool.getMaxEntries();
   }

   @ManagedAttribute(
      value = "The number of connections",
      readonly = true
   )
   public int getConnectionCount() {
      return this.pool.size();
   }

   @ManagedAttribute(
      value = "The number of pending connections",
      readonly = true
   )
   public int getPendingConnectionCount() {
      return this.pending.get();
   }

   public boolean isEmpty() {
      return this.pool.size() == 0;
   }

   @ManagedAttribute("Whether this pool is closed")
   public boolean isClosed() {
      return this.pool.isClosed();
   }

   @ManagedAttribute("Whether the pool tries to maximize the number of connections used")
   public boolean isMaximizeConnections() {
      return this.maximizeConnections;
   }

   public void setMaximizeConnections(boolean maximizeConnections) {
      this.maximizeConnections = maximizeConnections;
   }

   public Connection acquire(boolean create) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Acquiring create={} on {}", create, this);
      }

      Connection connection = this.activate();
      if (connection == null) {
         this.tryCreate(create);
         connection = this.activate();
      }

      return connection;
   }

   protected void tryCreate(boolean create) {
      int connectionCount = this.getConnectionCount();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Try creating connection {}/{} with {} pending", new Object[]{connectionCount, this.getMaxConnectionCount(), this.getPendingConnectionCount()});
      }

      int multiplexed = this.getMaxMultiplex();

      int pending;
      do {
         pending = this.pending.get();
         int supply = pending * multiplexed;
         int demand = this.destination.getQueuedRequestCount() + (create ? 1 : 0);
         boolean tryCreate = this.isMaximizeConnections() || supply < demand;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Try creating({}) connection, pending/demand/supply: {}/{}/{}, result={}", new Object[]{create, pending, demand, supply, tryCreate});
         }

         if (!tryCreate) {
            return;
         }
      } while(!this.pending.compareAndSet(pending, pending + 1));

      Pool<Connection>.Entry entry = this.pool.reserve();
      if (entry == null) {
         this.pending.decrementAndGet();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Not creating connection as pool {} is full, pending: {}", this.pool, this.pending);
         }

      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Creating connection {}/{} at {}", new Object[]{connectionCount, this.getMaxConnectionCount(), entry});
         }

         Promise<Connection> future = new FutureConnection(entry);
         this.destination.newConnection(future);
      }
   }

   public boolean accept(Connection connection) {
      if (!(connection instanceof Attachable)) {
         throw new IllegalArgumentException("Invalid connection object: " + String.valueOf(connection));
      } else {
         Pool<Connection>.Entry entry = this.pool.reserve();
         if (entry == null) {
            return false;
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("onCreating {} {}", entry, connection);
            }

            Attachable attachable = (Attachable)connection;
            attachable.setAttachment(new EntryHolder(entry));
            this.onCreated(connection);
            entry.enable(connection, false);
            this.idle(connection, false);
            return true;
         }
      }
   }

   protected void proceed() {
      this.requester.succeeded();
   }

   protected Connection activate() {
      while(true) {
         Pool<Connection>.Entry entry = this.pool.acquire();
         if (entry != null) {
            Connection connection = (Connection)entry.getPooled();
            long maxDurationNanos = this.maxDurationNanos;
            if (maxDurationNanos > 0L) {
               EntryHolder holder = (EntryHolder)((Attachable)connection).getAttachment();
               if (holder.isExpired(maxDurationNanos)) {
                  boolean canClose = this.remove(connection);
                  if (canClose) {
                     IO.close((Closeable)connection);
                  }

                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Connection removed{} due to expiration {} {}", new Object[]{canClose ? " and closed" : "", entry, this.pool});
                  }
                  continue;
               }
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Activated {} {}", entry, this.pool);
            }

            this.acquired(connection);
            return connection;
         }

         return null;
      }
   }

   public boolean isActive(Connection connection) {
      if (!(connection instanceof Attachable)) {
         throw new IllegalArgumentException("Invalid connection object: " + String.valueOf(connection));
      } else {
         Attachable attachable = (Attachable)connection;
         EntryHolder holder = (EntryHolder)attachable.getAttachment();
         if (holder == null) {
            return false;
         } else {
            return !holder.entry.isIdle();
         }
      }
   }

   public boolean release(Connection connection) {
      if (!this.deactivate(connection)) {
         return false;
      } else {
         this.released(connection);
         return this.idle(connection, this.isClosed());
      }
   }

   protected boolean deactivate(Connection connection) {
      if (!(connection instanceof Attachable)) {
         throw new IllegalArgumentException("Invalid connection object: " + String.valueOf(connection));
      } else {
         Attachable attachable = (Attachable)connection;
         EntryHolder holder = (EntryHolder)attachable.getAttachment();
         if (holder == null) {
            return true;
         } else {
            long maxDurationNanos = this.maxDurationNanos;
            if (maxDurationNanos > 0L && holder.isExpired(maxDurationNanos)) {
               return !this.remove(connection);
            } else {
               boolean reusable = this.pool.release(holder.entry);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Released ({}) {} {}", new Object[]{reusable, holder.entry, this.pool});
               }

               if (reusable) {
                  return true;
               } else {
                  return !this.remove(connection);
               }
            }
         }
      }
   }

   public boolean remove(Connection connection) {
      if (!(connection instanceof Attachable)) {
         throw new IllegalArgumentException("Invalid connection object: " + String.valueOf(connection));
      } else {
         Attachable attachable = (Attachable)connection;
         EntryHolder holder = (EntryHolder)attachable.getAttachment();
         if (holder == null) {
            return false;
         } else {
            boolean removed = this.pool.remove(holder.entry);
            if (removed) {
               attachable.setAttachment((Object)null);
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Removed ({}) {} {}", new Object[]{removed, holder.entry, this.pool});
            }

            if (removed) {
               this.released(connection);
               this.removed(connection);
            }

            return removed;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   protected boolean remove(Connection connection, boolean force) {
      return this.remove(connection);
   }

   protected void onCreated(Connection connection) {
   }

   protected boolean idle(Connection connection, boolean close) {
      return !close;
   }

   protected void acquired(Connection connection) {
   }

   protected void released(Connection connection) {
   }

   protected void removed(Connection connection) {
   }

   Queue getIdleConnections() {
      return (Queue)this.pool.values().stream().filter(Pool.Entry::isIdle).filter((entry) -> !entry.isClosed()).map(Pool.Entry::getPooled).collect(Collectors.toCollection(ArrayDeque::new));
   }

   Collection getActiveConnections() {
      return (Collection)this.pool.values().stream().filter((entry) -> !entry.isIdle()).filter((entry) -> !entry.isClosed()).map(Pool.Entry::getPooled).collect(Collectors.toList());
   }

   public void close() {
      try {
         for(Pool.Entry entry : this.pool.values()) {
            while(true) {
               if (entry.isInUse()) {
                  if (!entry.release()) {
                     continue;
                  }

                  this.released((Connection)entry.getPooled());
               }

               if (entry.remove()) {
                  this.removed((Connection)entry.getPooled());
               }
               break;
            }
         }
      } catch (Throwable x) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Detected concurrent modification while forcibly releasing the pooled connections", x);
         }
      }

      this.pool.close();
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this);
   }

   public boolean sweep() {
      this.pool.values().stream().map(Pool.Entry::getPooled).filter((connection) -> connection instanceof Sweeper.Sweepable).forEach((connection) -> {
         if (((Sweeper.Sweepable)connection).sweep()) {
            boolean removed = this.remove(connection);
            LOG.warn("Connection swept: {}{}{} from active connections{}{}", new Object[]{connection, System.lineSeparator(), removed ? "Removed" : "Not removed", System.lineSeparator(), this.dump()});
         }

      });
      return false;
   }

   public String toString() {
      return String.format("%s@%x[s=%s,c=%d/%d/%d,a=%d,i=%d,q=%d,p=%s]", this.getClass().getSimpleName(), this.hashCode(), this.getState(), this.getPendingConnectionCount(), this.getConnectionCount(), this.getMaxConnectionCount(), this.getActiveConnectionCount(), this.getIdleConnectionCount(), this.destination.getQueuedRequestCount(), this.pool);
   }

   private class FutureConnection extends Promise.Completable {
      private final Pool.Entry reserved;

      public FutureConnection(Pool.Entry reserved) {
         this.reserved = reserved;
      }

      public void succeeded(Connection connection) {
         if (AbstractConnectionPool.LOG.isDebugEnabled()) {
            AbstractConnectionPool.LOG.debug("Connection creation succeeded {}: {}", this.reserved, connection);
         }

         if (connection instanceof Attachable) {
            ((Attachable)connection).setAttachment(new EntryHolder(this.reserved));
            AbstractConnectionPool.this.onCreated(connection);
            AbstractConnectionPool.this.pending.decrementAndGet();
            this.reserved.enable(connection, false);
            AbstractConnectionPool.this.idle(connection, false);
            this.complete((Object)null);
            AbstractConnectionPool.this.proceed();
         } else {
            this.failed(new IllegalArgumentException("Invalid connection object: " + String.valueOf(connection)));
         }

      }

      public void failed(Throwable x) {
         if (AbstractConnectionPool.LOG.isDebugEnabled()) {
            AbstractConnectionPool.LOG.debug("Connection creation failed {}", this.reserved, x);
         }

         AbstractConnectionPool.this.pending.decrementAndGet();
         this.reserved.remove();
         this.completeExceptionally(x);
         AbstractConnectionPool.this.requester.failed(x);
      }
   }

   private static class EntryHolder {
      private final Pool.Entry entry;
      private final long creationNanoTime = NanoTime.now();

      private EntryHolder(Pool.Entry entry) {
         this.entry = (Pool.Entry)Objects.requireNonNull(entry);
      }

      private boolean isExpired(long timeoutNanos) {
         return NanoTime.since(this.creationNanoTime) >= timeoutNanos;
      }
   }
}
