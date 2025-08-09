package org.apache.spark.util;

import java.util.concurrent.TimeUnit;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Qa\u0003\u0007\u0001\u001dQA\u0001b\b\u0001\u0003\u0002\u0004%I!\t\u0005\tK\u0001\u0011\t\u0019!C\u0005M!AA\u0006\u0001B\u0001B\u0003&!\u0005C\u0003.\u0001\u0011\u0005a\u0006C\u0003.\u0001\u0011\u0005\u0011\u0007C\u00033\u0001\u0011\u00053\u0007C\u00035\u0001\u0011\u00053\u0007C\u00036\u0001\u0011\u0005a\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003>\u0001\u0011\u0005cHA\u0006NC:,\u0018\r\\\"m_\u000e\\'BA\u0007\u000f\u0003\u0011)H/\u001b7\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c2\u0001A\u000b\u001c!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fMB\u0011A$H\u0007\u0002\u0019%\u0011a\u0004\u0004\u0002\u0006\u00072|7m[\u0001\u0005i&lWm\u0001\u0001\u0016\u0003\t\u0002\"AF\u0012\n\u0005\u0011:\"\u0001\u0002'p]\u001e\f\u0001\u0002^5nK~#S-\u001d\u000b\u0003O)\u0002\"A\u0006\u0015\n\u0005%:\"\u0001B+oSRDqa\u000b\u0002\u0002\u0002\u0003\u0007!%A\u0002yIE\nQ\u0001^5nK\u0002\na\u0001P5oSRtDCA\u00181!\ta\u0002\u0001C\u0003 \t\u0001\u0007!\u0005F\u00010\u000359W\r\u001e+j[\u0016l\u0015\u000e\u001c7jgR\t!%\u0001\u0005oC:|G+[7f\u0003\u001d\u0019X\r\u001e+j[\u0016$\"aJ\u001c\t\u000baB\u0001\u0019\u0001\u0012\u0002\u0013QLW.\u001a+p'\u0016$\u0018aB1em\u0006t7-\u001a\u000b\u0003OmBQ\u0001P\u0005A\u0002\t\n\u0011\u0002^5nKR{\u0017\t\u001a3\u0002\u0019]\f\u0017\u000e\u001e+jY2$\u0016.\\3\u0015\u0005\tz\u0004\"\u0002!\u000b\u0001\u0004\u0011\u0013A\u0003;be\u001e,G\u000fV5nK\u0002"
)
public class ManualClock implements Clock {
   private long time;

   private long time() {
      return this.time;
   }

   private void time_$eq(final long x$1) {
      this.time = x$1;
   }

   public synchronized long getTimeMillis() {
      return this.time();
   }

   public long nanoTime() {
      return TimeUnit.MILLISECONDS.toNanos(this.getTimeMillis());
   }

   public synchronized void setTime(final long timeToSet) {
      this.time_$eq(timeToSet);
      this.notifyAll();
   }

   public synchronized void advance(final long timeToAdd) {
      this.time_$eq(this.time() + timeToAdd);
      this.notifyAll();
   }

   public synchronized long waitTillTime(final long targetTime) {
      while(this.time() < targetTime) {
         this.wait(10L);
      }

      return this.getTimeMillis();
   }

   public ManualClock(final long time) {
      this.time = time;
      super();
   }

   public ManualClock() {
      this(0L);
   }
}
