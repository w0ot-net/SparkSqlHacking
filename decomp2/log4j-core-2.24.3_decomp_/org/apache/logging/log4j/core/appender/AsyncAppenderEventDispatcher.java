package org.apache.logging.log4j.core.appender;

import java.lang.Thread.State;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.AppenderControl;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.core.util.Log4jThread;
import org.apache.logging.log4j.status.StatusLogger;

class AsyncAppenderEventDispatcher extends Log4jThread {
   private static final LogEvent STOP_EVENT = new Log4jLogEvent();
   private static final AtomicLong THREAD_COUNTER = new AtomicLong(0L);
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final AppenderControl errorAppender;
   private final List appenders;
   private final BlockingQueue queue;
   private final AtomicBoolean stoppedRef;

   AsyncAppenderEventDispatcher(final String name, final AppenderControl errorAppender, final List appenders, final BlockingQueue queue) {
      super("AsyncAppenderEventDispatcher-" + THREAD_COUNTER.incrementAndGet() + "-" + name);
      this.setDaemon(true);
      this.errorAppender = errorAppender;
      this.appenders = appenders;
      this.queue = queue;
      this.stoppedRef = new AtomicBoolean();
   }

   List getAppenders() {
      return (List)this.appenders.stream().map(AppenderControl::getAppender).collect(Collectors.toList());
   }

   public void run() {
      LOGGER.trace("{} has started.", this.getName());
      this.dispatchAll();
      this.dispatchRemaining();
   }

   private void dispatchAll() {
      while(true) {
         if (!this.stoppedRef.get()) {
            label17: {
               LogEvent event;
               try {
                  event = (LogEvent)this.queue.take();
               } catch (InterruptedException var3) {
                  this.interrupt();
                  break label17;
               }

               if (event != STOP_EVENT) {
                  event.setEndOfBatch(this.queue.isEmpty());
                  this.dispatch(event);
                  continue;
               }
            }
         }

         LOGGER.trace("{} has stopped.", this.getName());
         return;
      }
   }

   private void dispatchRemaining() {
      int eventCount = 0;

      while(true) {
         LogEvent event = (LogEvent)this.queue.poll();
         if (event == null) {
            LOGGER.trace("{} has processed the last {} remaining event(s).", this.getName(), eventCount);
            return;
         }

         if (event != STOP_EVENT) {
            event.setEndOfBatch(this.queue.isEmpty());
            this.dispatch(event);
            ++eventCount;
         }
      }
   }

   void dispatch(final LogEvent event) {
      boolean succeeded = false;

      for(int appenderIndex = 0; appenderIndex < this.appenders.size(); ++appenderIndex) {
         AppenderControl control = (AppenderControl)this.appenders.get(appenderIndex);

         try {
            control.callAppender(event);
            succeeded = true;
         } catch (Throwable error) {
            LOGGER.trace("{} has failed to call appender {}", this.getName(), control.getAppenderName(), error);
         }
      }

      if (!succeeded && this.errorAppender != null) {
         try {
            this.errorAppender.callAppender(event);
         } catch (Throwable error) {
            LOGGER.trace("{} has failed to call the error appender {}", this.getName(), this.errorAppender.getAppenderName(), error);
         }
      }

   }

   void stop(final long timeoutMillis) throws InterruptedException {
      boolean stopped = this.stoppedRef.compareAndSet(false, true);
      if (stopped) {
         LOGGER.trace("{} is signaled to stop.", this.getName());
      }

      while(State.NEW.equals(this.getState())) {
      }

      boolean added = this.queue.offer(STOP_EVENT);
      if (!added) {
         this.interrupt();
      }

      this.join(timeoutMillis);
   }
}
