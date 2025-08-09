package io.vertx.core.impl.btc;

import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class BlockedThreadChecker {
   public static final String LOGGER_NAME = "io.vertx.core.impl.BlockedThreadChecker";
   private static final Logger log = LoggerFactory.getLogger("io.vertx.core.impl.BlockedThreadChecker");
   private final Map threads = new WeakHashMap();
   private final Timer timer = new Timer("vertx-blocked-thread-checker", true);
   private Handler blockedThreadHandler = BlockedThreadChecker::defaultBlockedThreadHandler;

   public BlockedThreadChecker(long interval, TimeUnit intervalUnit, final long warningExceptionTime, final TimeUnit warningExceptionTimeUnit) {
      this.timer.schedule(new TimerTask() {
         public void run() {
            List<BlockedThreadEvent> events = new ArrayList();
            Handler<BlockedThreadEvent> handler;
            synchronized(BlockedThreadChecker.this) {
               handler = BlockedThreadChecker.this.blockedThreadHandler;
               long now = System.nanoTime();

               for(Map.Entry entry : BlockedThreadChecker.this.threads.entrySet()) {
                  ThreadInfo task = (ThreadInfo)entry.getValue();
                  long execStart = task.startTime;
                  long dur = now - execStart;
                  long timeLimit = task.maxExecTime;
                  TimeUnit maxExecTimeUnit = task.maxExecTimeUnit;
                  long maxExecTimeInNanos = TimeUnit.NANOSECONDS.convert(timeLimit, maxExecTimeUnit);
                  long warningExceptionTimeInNanos = TimeUnit.NANOSECONDS.convert(warningExceptionTime, warningExceptionTimeUnit);
                  if (execStart != 0L && dur >= maxExecTimeInNanos) {
                     events.add(new BlockedThreadEvent((Thread)entry.getKey(), dur, maxExecTimeInNanos, warningExceptionTimeInNanos));
                  }
               }
            }

            events.forEach(handler::handle);
         }
      }, intervalUnit.toMillis(interval), intervalUnit.toMillis(interval));
   }

   public synchronized void setThreadBlockedHandler(Handler handler) {
      this.blockedThreadHandler = handler == null ? BlockedThreadChecker::defaultBlockedThreadHandler : handler;
   }

   public synchronized void registerThread(Thread thread, ThreadInfo checked) {
      this.threads.put(thread, checked);
   }

   public void close() {
      this.timer.cancel();
      synchronized(this) {
         this.threads.clear();
      }
   }

   private static void defaultBlockedThreadHandler(BlockedThreadEvent bte) {
      Thread thread = bte.thread();
      String message = "Thread Thread[" + thread.getName() + "," + thread.getPriority() + "," + thread.getThreadGroup().getName() + "] has been blocked for " + bte.duration() / 1000000L + " ms, time limit is " + bte.maxExecTime() / 1000000L + " ms";
      if (bte.duration() <= bte.warningExceptionTime()) {
         log.warn(message);
      } else {
         VertxException stackTrace = new VertxException("Thread blocked");
         stackTrace.setStackTrace(thread.getStackTrace());
         log.warn(message, stackTrace);
      }

   }
}
