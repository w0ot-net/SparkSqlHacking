package org.apache.logging.log4j.core;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.status.StatusLogger;

public class AbstractLifeCycle implements LifeCycle2 {
   public static final int DEFAULT_STOP_TIMEOUT = 0;
   public static final TimeUnit DEFAULT_STOP_TIMEUNIT;
   protected static final org.apache.logging.log4j.Logger LOGGER;
   private volatile LifeCycle.State state;

   public AbstractLifeCycle() {
      this.state = LifeCycle.State.INITIALIZED;
   }

   protected static org.apache.logging.log4j.Logger getStatusLogger() {
      return LOGGER;
   }

   protected boolean equalsImpl(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         LifeCycle other = (LifeCycle)obj;
         return this.state == other.getState();
      }
   }

   public LifeCycle.State getState() {
      return this.state;
   }

   protected int hashCodeImpl() {
      int prime = 31;
      int result = 1;
      result = 31 * result + (this.state == null ? 0 : this.state.hashCode());
      return result;
   }

   public boolean isInitialized() {
      return this.state == LifeCycle.State.INITIALIZED;
   }

   public boolean isStarted() {
      return this.state == LifeCycle.State.STARTED;
   }

   public boolean isStarting() {
      return this.state == LifeCycle.State.STARTING;
   }

   public boolean isStopped() {
      return this.state == LifeCycle.State.STOPPED;
   }

   public boolean isStopping() {
      return this.state == LifeCycle.State.STOPPING;
   }

   protected void setStarted() {
      this.setState(LifeCycle.State.STARTED);
   }

   protected void setStarting() {
      this.setState(LifeCycle.State.STARTING);
   }

   protected void setState(final LifeCycle.State newState) {
      this.state = newState;
   }

   protected void setStopped() {
      this.setState(LifeCycle.State.STOPPED);
   }

   protected void setStopping() {
      this.setState(LifeCycle.State.STOPPING);
   }

   public void initialize() {
      this.state = LifeCycle.State.INITIALIZED;
   }

   public void start() {
      this.setStarted();
   }

   public void stop() {
      this.stop(0L, DEFAULT_STOP_TIMEUNIT);
   }

   protected boolean stop(final Future future) {
      boolean stopped = true;
      if (future != null) {
         if (future.isCancelled() || future.isDone()) {
            return true;
         }

         stopped = future.cancel(true);
      }

      return stopped;
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.state = LifeCycle.State.STOPPED;
      return true;
   }

   static {
      DEFAULT_STOP_TIMEUNIT = TimeUnit.MILLISECONDS;
      LOGGER = StatusLogger.getLogger();
   }
}
