package org.apache.spark.util;

import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512Qa\u0002\u0005\u0001\u0015AAQa\u0007\u0001\u0005\u0002uAqa\b\u0001C\u0002\u0013\u0005\u0001\u0005\u0003\u0004%\u0001\u0001\u0006I!\t\u0005\u0006K\u0001!\tE\n\u0005\u0006O\u0001!\tE\n\u0005\u0006Q\u0001!\t%\u000b\u0002\f'f\u001cH/Z7DY>\u001c7N\u0003\u0002\n\u0015\u0005!Q\u000f^5m\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7c\u0001\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\r\u000e\u0003!I!A\u0007\u0005\u0003\u000b\rcwnY6\u0002\rqJg.\u001b;?\u0007\u0001!\u0012A\b\t\u00031\u0001\t1\"\\5o!>dG\u000eV5nKV\t\u0011\u0005\u0005\u0002\u0013E%\u00111e\u0005\u0002\u0005\u0019>tw-\u0001\u0007nS:\u0004v\u000e\u001c7US6,\u0007%A\u0007hKR$\u0016.\\3NS2d\u0017n\u001d\u000b\u0002C\u0005Aa.\u00198p)&lW-\u0001\u0007xC&$H+\u001b7m)&lW\r\u0006\u0002\"U!)1F\u0002a\u0001C\u0005QA/\u0019:hKR$\u0016.\\3"
)
public class SystemClock implements Clock {
   private final long minPollTime = 25L;

   public long minPollTime() {
      return this.minPollTime;
   }

   public long getTimeMillis() {
      return System.currentTimeMillis();
   }

   public long nanoTime() {
      return System.nanoTime();
   }

   public long waitTillTime(final long targetTime) {
      long currentTime = System.currentTimeMillis();
      long waitTime = targetTime - currentTime;
      if (waitTime <= 0L) {
         return currentTime;
      } else {
         long pollTime = (long).MODULE$.max((double)waitTime / (double)10.0F, (double)this.minPollTime());

         while(true) {
            currentTime = System.currentTimeMillis();
            waitTime = targetTime - currentTime;
            if (waitTime <= 0L) {
               return currentTime;
            }

            long sleepTime = .MODULE$.min(waitTime, pollTime);
            Thread.sleep(sleepTime);
         }
      }
   }
}
