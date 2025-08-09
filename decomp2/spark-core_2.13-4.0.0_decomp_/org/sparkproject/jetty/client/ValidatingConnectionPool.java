package org.sparkproject.jetty.client;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Connection;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.thread.Scheduler;

public class ValidatingConnectionPool extends DuplexConnectionPool {
   private static final Logger LOG = LoggerFactory.getLogger(ValidatingConnectionPool.class);
   private final Scheduler scheduler;
   private final long timeout;
   private final Map quarantine;

   public ValidatingConnectionPool(HttpDestination destination, int maxConnections, Callback requester, Scheduler scheduler, long timeout) {
      super(destination, maxConnections, requester);
      this.scheduler = scheduler;
      this.timeout = timeout;
      this.quarantine = new ConcurrentHashMap(maxConnections);
   }

   @ManagedAttribute(
      value = "The number of validating connections",
      readonly = true
   )
   public int getValidatingConnectionCount() {
      return this.quarantine.size();
   }

   public boolean release(Connection connection) {
      Holder holder = new Holder(connection);
      holder.task = this.scheduler.schedule(holder, this.timeout, TimeUnit.MILLISECONDS);
      this.quarantine.put(connection, holder);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Validating for {}ms {}", this.timeout, connection);
      }

      this.released(connection);
      return true;
   }

   public boolean remove(Connection connection) {
      Holder holder = (Holder)this.quarantine.remove(connection);
      if (holder == null) {
         return super.remove(connection);
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Removed while validating {}", connection);
         }

         boolean cancelled = holder.cancel();
         return cancelled ? this.remove(connection, true) : super.remove(connection);
      }
   }

   public void dump(Appendable out, String indent) throws IOException {
      DumpableCollection toDump = new DumpableCollection("quarantine", this.quarantine.values());
      Dumpable.dumpObjects(out, indent, this, toDump);
   }

   public String toString() {
      int size = this.quarantine.size();
      return String.format("%s[v=%d]", super.toString(), size);
   }

   private class Holder implements Runnable {
      private final long creationNanoTime = NanoTime.now();
      private final AtomicBoolean done = new AtomicBoolean();
      private final Connection connection;
      public Scheduler.Task task;

      public Holder(Connection connection) {
         this.connection = connection;
      }

      public void run() {
         if (this.done.compareAndSet(false, true)) {
            boolean closed = ValidatingConnectionPool.this.isClosed();
            if (ValidatingConnectionPool.LOG.isDebugEnabled()) {
               ValidatingConnectionPool.LOG.debug("Validated {}", this.connection);
            }

            ValidatingConnectionPool.this.quarantine.remove(this.connection);
            if (!closed) {
               ValidatingConnectionPool.this.deactivate(this.connection);
            }

            ValidatingConnectionPool.this.idle(this.connection, closed);
            ValidatingConnectionPool.this.proceed();
         }

      }

      public boolean cancel() {
         if (this.done.compareAndSet(false, true)) {
            this.task.cancel();
            return true;
         } else {
            return false;
         }
      }

      public String toString() {
         return String.format("%s[validationLeft=%dms]", this.connection, ValidatingConnectionPool.this.timeout - NanoTime.millisSince(this.creationNanoTime));
      }
   }
}
