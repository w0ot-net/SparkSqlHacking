package org.apache.spark.util;

import java.util.concurrent.ConcurrentHashMap;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3Qa\u0002\u0005\u0001\u0015AAQ\u0001\u0007\u0001\u0005\u0002iAq\u0001\u000b\u0001C\u0002\u0013%\u0011\u0006\u0003\u00044\u0001\u0001\u0006IA\u000b\u0005\u0006i\u0001!I!\u000e\u0005\u0006w\u0001!I\u0001\u0010\u0005\u0006}\u0001!\ta\u0010\u0002\b\u0017\u0016LHj\\2l\u0015\tI!\"\u0001\u0003vi&d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0016\u0005Ey2C\u0001\u0001\u0013!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u001c!\ra\u0002!H\u0007\u0002\u0011A\u0011ad\b\u0007\u0001\t\u0015\u0001\u0003A1\u0001\"\u0005\u0005Y\u0015C\u0001\u0012&!\t\u00192%\u0003\u0002%)\t9aj\u001c;iS:<\u0007CA\n'\u0013\t9CCA\u0002B]f\fq\u0001\\8dW6\u000b\u0007/F\u0001+!\u0011Y\u0013'\b\n\u000e\u00031R!!\f\u0018\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002\n_)\t\u0001'\u0001\u0003kCZ\f\u0017B\u0001\u001a-\u0005E\u0019uN\\2veJ,g\u000e\u001e%bg\"l\u0015\r]\u0001\tY>\u001c7.T1qA\u0005Y\u0011mY9vSJ,Gj\\2l)\t1\u0014\b\u0005\u0002\u0014o%\u0011\u0001\b\u0006\u0002\u0005+:LG\u000fC\u0003;\t\u0001\u0007Q$A\u0002lKf\f1B]3mK\u0006\u001cX\rT8dWR\u0011a'\u0010\u0005\u0006u\u0015\u0001\r!H\u0001\to&$\b\u000eT8dWV\u0011\u0001i\u0011\u000b\u0003\u0003*#\"AQ#\u0011\u0005y\u0019E!\u0002#\u0007\u0005\u0004\t#!\u0001+\t\r\u00193A\u00111\u0001H\u0003\u00111WO\\2\u0011\u0007MA%)\u0003\u0002J)\tAAHY=oC6,g\bC\u0003;\r\u0001\u0007Q\u0004"
)
public class KeyLock {
   private final ConcurrentHashMap lockMap = new ConcurrentHashMap();

   private ConcurrentHashMap lockMap() {
      return this.lockMap;
   }

   private void acquireLock(final Object key) {
      while(true) {
         Object lock = this.lockMap().putIfAbsent(key, new Object());
         if (lock == null) {
            return;
         }

         synchronized(lock){}

         try {
            while(this.lockMap().get(key) == lock) {
               lock.wait();
            }
         } catch (Throwable var5) {
            throw var5;
         }
      }
   }

   private void releaseLock(final Object key) {
      Object lock = this.lockMap().remove(key);
      synchronized(lock){}

      try {
         lock.notifyAll();
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public Object withLock(final Object key, final Function0 func) {
      if (key == null) {
         throw new NullPointerException("key must not be null");
      } else {
         this.acquireLock(key);

         Object var10000;
         try {
            var10000 = func.apply();
         } finally {
            this.releaseLock(key);
         }

         return var10000;
      }
   }
}
