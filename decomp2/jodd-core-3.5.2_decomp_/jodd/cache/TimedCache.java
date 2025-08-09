package jodd.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class TimedCache extends AbstractCacheMap {
   protected Timer pruneTimer;

   public TimedCache(long timeout) {
      this.cacheSize = 0;
      this.timeout = timeout;
      this.cacheMap = new HashMap();
   }

   protected int pruneCache() {
      int count = 0;
      Iterator<AbstractCacheMap<K, V>.CacheObject<K, V>> values = this.cacheMap.values().iterator();

      while(values.hasNext()) {
         AbstractCacheMap.CacheObject co = (AbstractCacheMap.CacheObject)values.next();
         if (co.isExpired()) {
            values.remove();
            ++count;
         }
      }

      return count;
   }

   public void schedulePrune(long delay) {
      // $FF: Couldn't be decompiled
   }

   public void cancelPruneSchedule() {
      if (this.pruneTimer != null) {
         this.pruneTimer.cancel();
         this.pruneTimer = null;
      }

   }
}
