package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deadline {
   private static final Logger LOG = LoggerFactory.getLogger(Deadline.class.getName());
   private long timeoutNanos;
   private long startTime = Long.MIN_VALUE;
   private String method;
   private static final ThreadLocal DEADLINE_THREAD_LOCAL = new ThreadLocal() {
      protected Deadline initialValue() {
         return null;
      }
   };
   private static final long NO_DEADLINE = Long.MIN_VALUE;

   private Deadline(long timeoutMs) {
      this.timeoutNanos = timeoutMs * 1000000L;
   }

   private static void setCurrentDeadline(Deadline deadline) {
      DEADLINE_THREAD_LOCAL.set(deadline);
   }

   static Deadline getCurrentDeadline() {
      return (Deadline)DEADLINE_THREAD_LOCAL.get();
   }

   private static void removeCurrentDeadline() {
      DEADLINE_THREAD_LOCAL.remove();
   }

   public static void registerIfNot(long timeout) {
      if (getCurrentDeadline() == null) {
         setCurrentDeadline(new Deadline(timeout));
      }

   }

   public static void resetTimeout(long timeoutMs) throws MetaException {
      if (timeoutMs <= 0L) {
         throw newMetaException(new DeadlineException("The reset timeout value should be larger than 0: " + timeoutMs));
      } else {
         Deadline deadline = getCurrentDeadline();
         if (deadline != null) {
            deadline.timeoutNanos = timeoutMs * 1000000L;
         } else {
            throw newMetaException(new DeadlineException("The threadlocal Deadline is null, please register it firstly."));
         }
      }
   }

   public static boolean startTimer(String method) throws MetaException {
      Deadline deadline = getCurrentDeadline();
      if (deadline == null) {
         throw newMetaException(new DeadlineException("The threadlocal Deadline is null, please register it firstly."));
      } else if (deadline.startTime != Long.MIN_VALUE) {
         return false;
      } else {
         deadline.method = method;

         do {
            deadline.startTime = System.nanoTime();
         } while(deadline.startTime == Long.MIN_VALUE);

         return true;
      }
   }

   public static void stopTimer() throws MetaException {
      Deadline deadline = getCurrentDeadline();
      if (deadline != null) {
         deadline.startTime = Long.MIN_VALUE;
         deadline.method = null;
      } else {
         throw newMetaException(new DeadlineException("The threadlocal Deadline is null, please register it firstly."));
      }
   }

   public static void clear() {
      removeCurrentDeadline();
   }

   public static void checkTimeout() throws MetaException {
      Deadline deadline = getCurrentDeadline();
      if (deadline != null) {
         deadline.check();
      } else {
         throw newMetaException(new DeadlineException("The threadlocal Deadline is null, please register it first."));
      }
   }

   private void check() throws MetaException {
      try {
         if (this.startTime == Long.MIN_VALUE) {
            throw new DeadlineException("Should execute startTimer() method before checkTimeout. Error happens in method: " + this.method);
         } else {
            long elapsedTime = System.nanoTime() - this.startTime;
            if (elapsedTime > this.timeoutNanos) {
               throw new DeadlineException("Timeout when executing method: " + this.method + "; " + elapsedTime / 1000000L + "ms exceeds " + this.timeoutNanos / 1000000L + "ms");
            }
         }
      } catch (DeadlineException e) {
         throw newMetaException(e);
      }
   }

   private static MetaException newMetaException(DeadlineException e) {
      MetaException metaException = new MetaException(e.getMessage());
      metaException.initCause(e);
      return metaException;
   }
}
