package org.apache.spark.status.api.v1;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple15;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction15;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ThreadStackTrace$ extends AbstractFunction15 implements Serializable {
   public static final ThreadStackTrace$ MODULE$ = new ThreadStackTrace$();

   public final String toString() {
      return "ThreadStackTrace";
   }

   public ThreadStackTrace apply(final long threadId, final String threadName, final Thread.State threadState, final StackTrace stackTrace, final Option blockedByThreadId, final String blockedByLock, final Seq holdingLocks, final Seq synchronizers, final Seq monitors, final Option lockName, final Option lockOwnerName, final boolean suspended, final boolean inNative, final boolean isDaemon, final int priority) {
      return new ThreadStackTrace(threadId, threadName, threadState, stackTrace, blockedByThreadId, blockedByLock, holdingLocks, synchronizers, monitors, lockName, lockOwnerName, suspended, inNative, isDaemon, priority);
   }

   public Option unapply(final ThreadStackTrace x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple15(BoxesRunTime.boxToLong(x$0.threadId()), x$0.threadName(), x$0.threadState(), x$0.stackTrace(), x$0.blockedByThreadId(), x$0.blockedByLock(), x$0.holdingLocks(), x$0.synchronizers(), x$0.monitors(), x$0.lockName(), x$0.lockOwnerName(), BoxesRunTime.boxToBoolean(x$0.suspended()), BoxesRunTime.boxToBoolean(x$0.inNative()), BoxesRunTime.boxToBoolean(x$0.isDaemon()), BoxesRunTime.boxToInteger(x$0.priority()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ThreadStackTrace$.class);
   }

   private ThreadStackTrace$() {
   }
}
