package org.apache.spark.streaming;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4Q\u0001E\t\u0001#eAQ\u0001\t\u0001\u0005\u0002\tBq!\n\u0001C\u0002\u0013%a\u0005\u0003\u00044\u0001\u0001\u0006Ia\n\u0005\bi\u0001\u0011\r\u0011\"\u00036\u0011\u0019I\u0004\u0001)A\u0005m!9!\b\u0001a\u0001\n\u0013Y\u0004b\u0002%\u0001\u0001\u0004%I!\u0013\u0005\u0007\u001f\u0002\u0001\u000b\u0015\u0002\u001f\t\u000fA\u0003\u0001\u0019!C\u0005#\"9Q\u000b\u0001a\u0001\n\u00131\u0006B\u0002-\u0001A\u0003&!\u000bC\u0003Z\u0001\u0011\u0005!\fC\u0003^\u0001\u0011\u0005a\fC\u0003`\u0001\u0011\u0005\u0001\rC\u0004g\u0001E\u0005I\u0011A4\u0003\u001b\r{g\u000e^3yi^\u000b\u0017\u000e^3s\u0015\t\u00112#A\u0005tiJ,\u0017-\\5oO*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xm\u0005\u0002\u00015A\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002GA\u0011A\u0005A\u0007\u0002#\u0005!An\\2l+\u00059\u0003C\u0001\u00152\u001b\u0005I#B\u0001\u0016,\u0003\u0015awnY6t\u0015\taS&\u0001\u0006d_:\u001cWO\u001d:f]RT!AL\u0018\u0002\tU$\u0018\u000e\u001c\u0006\u0002a\u0005!!.\u0019<b\u0013\t\u0011\u0014FA\u0007SK\u0016tGO]1oi2{7m[\u0001\u0006Y>\u001c7\u000eI\u0001\nG>tG-\u001b;j_:,\u0012A\u000e\t\u0003Q]J!\u0001O\u0015\u0003\u0013\r{g\u000eZ5uS>t\u0017AC2p]\u0012LG/[8oA\u0005)QM\u001d:peV\tA\b\u0005\u0002>\u000b:\u0011ah\u0011\b\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003\u0006\na\u0001\u0010:p_Rt\u0014\"A\u000f\n\u0005\u0011c\u0012a\u00029bG.\fw-Z\u0005\u0003\r\u001e\u0013\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005\u0011c\u0012!C3se>\u0014x\fJ3r)\tQU\n\u0005\u0002\u001c\u0017&\u0011A\n\b\u0002\u0005+:LG\u000fC\u0004O\u000f\u0005\u0005\t\u0019\u0001\u001f\u0002\u0007a$\u0013'\u0001\u0004feJ|'\u000fI\u0001\bgR|\u0007\u000f]3e+\u0005\u0011\u0006CA\u000eT\u0013\t!FDA\u0004C_>dW-\u00198\u0002\u0017M$x\u000e\u001d9fI~#S-\u001d\u000b\u0003\u0015^CqA\u0014\u0006\u0002\u0002\u0003\u0007!+\u0001\u0005ti>\u0004\b/\u001a3!\u0003-qw\u000e^5gs\u0016\u0013(o\u001c:\u0015\u0005)[\u0006\"\u0002/\r\u0001\u0004a\u0014!A3\u0002\u00159|G/\u001b4z'R|\u0007\u000fF\u0001K\u0003I9\u0018-\u001b;G_J\u001cFo\u001c9Pe\u0016\u0013(o\u001c:\u0015\u0005I\u000b\u0007b\u00022\u000f!\u0003\u0005\raY\u0001\bi&lWm\\;u!\tYB-\u0003\u0002f9\t!Aj\u001c8h\u0003q9\u0018-\u001b;G_J\u001cFo\u001c9Pe\u0016\u0013(o\u001c:%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u001b\u0016\u0003G&\\\u0013A\u001b\t\u0003WBl\u0011\u0001\u001c\u0006\u0003[:\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005=d\u0012AC1o]>$\u0018\r^5p]&\u0011\u0011\u000f\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class ContextWaiter {
   private final ReentrantLock lock = new ReentrantLock();
   private final Condition condition = this.lock().newCondition();
   private Throwable error = null;
   private boolean stopped = false;

   private ReentrantLock lock() {
      return this.lock;
   }

   private Condition condition() {
      return this.condition;
   }

   private Throwable error() {
      return this.error;
   }

   private void error_$eq(final Throwable x$1) {
      this.error = x$1;
   }

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   public void notifyError(final Throwable e) {
      this.lock().lock();

      try {
         this.error_$eq(e);
         this.condition().signalAll();
      } finally {
         this.lock().unlock();
      }

   }

   public void notifyStop() {
      this.lock().lock();

      try {
         this.stopped_$eq(true);
         this.condition().signalAll();
      } finally {
         this.lock().unlock();
      }

   }

   public boolean waitForStopOrError(final long timeout) {
      this.lock().lock();

      ReentrantLock var10000;
      try {
         if (timeout < 0L) {
            while(!this.stopped() && this.error() == null) {
               this.condition().await();
            }
         } else {
            for(long nanos = TimeUnit.MILLISECONDS.toNanos(timeout); !this.stopped() && this.error() == null && nanos > 0L; nanos = this.condition().awaitNanos(nanos)) {
            }
         }

         if (this.error() != null) {
            throw this.error();
         }

         this.stopped();
      } finally {
         var10000 = this.lock();
         var10000.unlock();
      }

      return (boolean)var10000;
   }

   public long waitForStopOrError$default$1() {
      return -1L;
   }
}
