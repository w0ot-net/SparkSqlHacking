package org.sparkproject.jetty.server;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.SelectorManager;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.statistic.RateStatistic;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject
public class AcceptRateLimit extends AbstractLifeCycle implements SelectorManager.AcceptListener, Runnable {
   private static final Logger LOG = LoggerFactory.getLogger(AcceptRateLimit.class);
   private final AutoLock _lock;
   private final Server _server;
   private final List _connectors;
   private final Rate _rate;
   private final int _acceptRateLimit;
   private boolean _limiting;
   private Scheduler.Task _task;

   public AcceptRateLimit(@Name("acceptRateLimit") int acceptRateLimit, @Name("period") long period, @Name("units") TimeUnit units, @Name("server") Server server) {
      this._lock = new AutoLock();
      this._connectors = new ArrayList();
      this._server = server;
      this._acceptRateLimit = acceptRateLimit;
      this._rate = new Rate(period, units);
   }

   public AcceptRateLimit(@Name("limit") int limit, @Name("period") long period, @Name("units") TimeUnit units, @Name("connectors") Connector... connectors) {
      this(limit, period, units, (Server)null);

      for(Connector c : connectors) {
         if (c instanceof AbstractConnector) {
            this._connectors.add((AbstractConnector)c);
         } else {
            LOG.warn("Connector {} is not an AbstractConnector. Connections not limited", c);
         }
      }

   }

   @ManagedAttribute("The accept rate limit")
   public int getAcceptRateLimit() {
      return this._acceptRateLimit;
   }

   @ManagedAttribute("The accept rate period")
   public long getPeriod() {
      return this._rate.getPeriod();
   }

   @ManagedAttribute("The accept rate period units")
   public TimeUnit getUnits() {
      return this._rate.getUnits();
   }

   @ManagedAttribute("The current accept rate")
   public int getRate() {
      return this._rate.getRate();
   }

   @ManagedAttribute("The maximum accept rate achieved")
   public long getMaxRate() {
      return this._rate.getMax();
   }

   @ManagedOperation(
      value = "Resets the accept rate",
      impact = "ACTION"
   )
   public void reset() {
      try (AutoLock l = this._lock.lock()) {
         this._rate.reset();
         if (this._limiting) {
            this._limiting = false;
            this.unlimit();
         }
      }

   }

   protected void age(long period, TimeUnit units) {
      this._rate.age(period, units);
   }

   protected void doStart() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (this._server != null) {
            for(Connector c : this._server.getConnectors()) {
               if (c instanceof AbstractConnector) {
                  this._connectors.add((AbstractConnector)c);
               } else {
                  LOG.warn("Connector {} is not an AbstractConnector. Connections not limited", c);
               }
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("AcceptLimit accept<{} rate<{} in {}", new Object[]{this._acceptRateLimit, this._rate, this._connectors});
         }

         for(AbstractConnector c : this._connectors) {
            c.addBean(this);
         }
      }

   }

   protected void doStop() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (this._task != null) {
            this._task.cancel();
         }

         this._task = null;

         for(AbstractConnector c : this._connectors) {
            c.removeBean(this);
         }

         if (this._server != null) {
            this._connectors.clear();
         }

         this._limiting = false;
      }

   }

   protected void limit() {
      for(AbstractConnector c : this._connectors) {
         c.setAccepting(false);
      }

      this.schedule();
   }

   protected void unlimit() {
      for(AbstractConnector c : this._connectors) {
         c.setAccepting(true);
      }

   }

   public void onAccepting(SelectableChannel channel) {
      try (AutoLock l = this._lock.lock()) {
         int rate = this._rate.record();
         if (LOG.isDebugEnabled()) {
            LOG.debug("onAccepting rate {}/{} for {} {}", new Object[]{rate, this._acceptRateLimit, this._rate, channel});
         }

         if (rate > this._acceptRateLimit && !this._limiting) {
            this._limiting = true;
            LOG.warn("AcceptLimit rate exceeded {}>{} on {}", new Object[]{rate, this._acceptRateLimit, this._connectors});
            this.limit();
         }
      }

   }

   private void schedule() {
      long oldest = this._rate.getOldest(TimeUnit.MILLISECONDS);
      long period = TimeUnit.MILLISECONDS.convert(this._rate.getPeriod(), this._rate.getUnits());
      long delay = period - (oldest > 0L ? oldest : 0L);
      if (delay < 0L) {
         delay = 0L;
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("schedule {} {}", delay, TimeUnit.MILLISECONDS);
      }

      this._task = ((AbstractConnector)this._connectors.get(0)).getScheduler().schedule(this, delay, TimeUnit.MILLISECONDS);
   }

   public void run() {
      try (AutoLock l = this._lock.lock()) {
         this._task = null;
         if (!this.isRunning()) {
            return;
         }

         int rate = this._rate.getRate();
         if (rate > this._acceptRateLimit) {
            this.schedule();
            return;
         }

         if (this._limiting) {
            this._limiting = false;
            LOG.warn("AcceptLimit rate OK {}<={} on {}", new Object[]{rate, this._acceptRateLimit, this._connectors});
            this.unlimit();
         }
      }

   }

   private static final class Rate extends RateStatistic {
      private Rate(long period, TimeUnit units) {
         super(period, units);
      }

      protected void age(long period, TimeUnit units) {
         super.age(period, units);
      }
   }
}
