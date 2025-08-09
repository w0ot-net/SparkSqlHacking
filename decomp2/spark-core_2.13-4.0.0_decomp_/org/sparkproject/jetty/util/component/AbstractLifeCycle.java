package org.sparkproject.jetty.util.component;

import java.util.Collection;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.Uptime;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject("Abstract Implementation of LifeCycle")
public abstract class AbstractLifeCycle implements LifeCycle {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractLifeCycle.class);
   public static final String STOPPED;
   public static final String FAILED;
   public static final String STARTING;
   public static final String STARTED;
   public static final String STOPPING;
   private final List _eventListener = new CopyOnWriteArrayList();
   private final AutoLock _lock = new AutoLock();
   private volatile State _state;

   public AbstractLifeCycle() {
      this._state = AbstractLifeCycle.State.STOPPED;
   }

   protected void doStart() throws Exception {
   }

   protected void doStop() throws Exception {
   }

   public final void start() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         try {
            switch (this._state.ordinal()) {
               case 1:
               case 3:
                  throw new IllegalStateException(this.getState());
               case 2:
                  return;
               default:
                  try {
                     this.setStarting();
                     this.doStart();
                     this.setStarted();
                  } catch (StopException e) {
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Unable to stop", e);
                     }

                     this.setStopping();
                     this.doStop();
                     this.setStopped();
                  }
            }
         } catch (Throwable e) {
            this.setFailed(e);
            throw e;
         }
      }

   }

   public final void stop() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         try {
            switch (this._state.ordinal()) {
               case 0:
                  return;
               case 1:
               case 3:
                  throw new IllegalStateException(this.getState());
               case 2:
            }

            this.setStopping();
            this.doStop();
            this.setStopped();
         } catch (Throwable e) {
            this.setFailed(e);
            throw e;
         }
      }

   }

   public boolean isRunning() {
      State state = this._state;
      switch (state.ordinal()) {
         case 1:
         case 2:
            return true;
         default:
            return false;
      }
   }

   public boolean isStarted() {
      return this._state == AbstractLifeCycle.State.STARTED;
   }

   public boolean isStarting() {
      return this._state == AbstractLifeCycle.State.STARTING;
   }

   public boolean isStopping() {
      return this._state == AbstractLifeCycle.State.STOPPING;
   }

   public boolean isStopped() {
      return this._state == AbstractLifeCycle.State.STOPPED;
   }

   public boolean isFailed() {
      return this._state == AbstractLifeCycle.State.FAILED;
   }

   public List getEventListeners() {
      return this._eventListener;
   }

   public void setEventListeners(Collection eventListeners) {
      for(EventListener l : this._eventListener) {
         if (!eventListeners.contains(l)) {
            this.removeEventListener(l);
         }
      }

      for(EventListener l : eventListeners) {
         if (!this._eventListener.contains(l)) {
            this.addEventListener(l);
         }
      }

   }

   public boolean addEventListener(EventListener listener) {
      if (this._eventListener.contains(listener)) {
         return false;
      } else {
         this._eventListener.add(listener);
         return true;
      }
   }

   public boolean removeEventListener(EventListener listener) {
      return this._eventListener.remove(listener);
   }

   @ManagedAttribute(
      value = "Lifecycle State for this instance",
      readonly = true
   )
   public String getState() {
      return this._state.toString();
   }

   public static String getState(LifeCycle lc) {
      if (lc instanceof AbstractLifeCycle) {
         return ((AbstractLifeCycle)lc)._state.toString();
      } else if (lc.isStarting()) {
         return AbstractLifeCycle.State.STARTING.toString();
      } else if (lc.isStarted()) {
         return AbstractLifeCycle.State.STARTED.toString();
      } else if (lc.isStopping()) {
         return AbstractLifeCycle.State.STOPPING.toString();
      } else {
         return lc.isStopped() ? AbstractLifeCycle.State.STOPPED.toString() : AbstractLifeCycle.State.FAILED.toString();
      }
   }

   private void setStarted() {
      if (this._state == AbstractLifeCycle.State.STARTING) {
         this._state = AbstractLifeCycle.State.STARTED;
         if (LOG.isDebugEnabled()) {
            LOG.debug("STARTED @{}ms {}", Uptime.getUptime(), this);
         }

         for(EventListener listener : this._eventListener) {
            if (listener instanceof LifeCycle.Listener) {
               ((LifeCycle.Listener)listener).lifeCycleStarted(this);
            }
         }
      }

   }

   private void setStarting() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("STARTING {}", this);
      }

      this._state = AbstractLifeCycle.State.STARTING;

      for(EventListener listener : this._eventListener) {
         if (listener instanceof LifeCycle.Listener) {
            ((LifeCycle.Listener)listener).lifeCycleStarting(this);
         }
      }

   }

   private void setStopping() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("STOPPING {}", this);
      }

      this._state = AbstractLifeCycle.State.STOPPING;

      for(EventListener listener : this._eventListener) {
         if (listener instanceof LifeCycle.Listener) {
            ((LifeCycle.Listener)listener).lifeCycleStopping(this);
         }
      }

   }

   private void setStopped() {
      if (this._state == AbstractLifeCycle.State.STOPPING) {
         this._state = AbstractLifeCycle.State.STOPPED;
         if (LOG.isDebugEnabled()) {
            LOG.debug("STOPPED {}", this);
         }

         for(EventListener listener : this._eventListener) {
            if (listener instanceof LifeCycle.Listener) {
               ((LifeCycle.Listener)listener).lifeCycleStopped(this);
            }
         }
      }

   }

   private void setFailed(Throwable th) {
      this._state = AbstractLifeCycle.State.FAILED;
      if (LOG.isDebugEnabled()) {
         LOG.warn("FAILED {}: {}", new Object[]{this, th, th});
      }

      for(EventListener listener : this._eventListener) {
         if (listener instanceof LifeCycle.Listener) {
            ((LifeCycle.Listener)listener).lifeCycleFailure(this, th);
         }
      }

   }

   public String toString() {
      String name = this.getClass().getSimpleName();
      if (StringUtil.isBlank(name) && this.getClass().getSuperclass() != null) {
         name = this.getClass().getSuperclass().getSimpleName();
      }

      return String.format("%s@%x{%s}", name, this.hashCode(), this.getState());
   }

   static {
      STOPPED = AbstractLifeCycle.State.STOPPED.toString();
      FAILED = AbstractLifeCycle.State.FAILED.toString();
      STARTING = AbstractLifeCycle.State.STARTING.toString();
      STARTED = AbstractLifeCycle.State.STARTED.toString();
      STOPPING = AbstractLifeCycle.State.STOPPING.toString();
   }

   static enum State {
      STOPPED,
      STARTING,
      STARTED,
      STOPPING,
      FAILED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{STOPPED, STARTING, STARTED, STOPPING, FAILED};
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class AbstractLifeCycleListener implements LifeCycle.Listener {
      public void lifeCycleFailure(LifeCycle event, Throwable cause) {
      }

      public void lifeCycleStarted(LifeCycle event) {
      }

      public void lifeCycleStarting(LifeCycle event) {
      }

      public void lifeCycleStopped(LifeCycle event) {
      }

      public void lifeCycleStopping(LifeCycle event) {
      }
   }

   public class StopException extends RuntimeException {
   }
}
