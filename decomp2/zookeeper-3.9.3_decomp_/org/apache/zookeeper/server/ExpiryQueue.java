package org.apache.zookeeper.server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.common.Time;

public class ExpiryQueue {
   private final ConcurrentHashMap elemMap = new ConcurrentHashMap();
   private final ConcurrentHashMap expiryMap = new ConcurrentHashMap();
   private final AtomicLong nextExpirationTime = new AtomicLong();
   private final int expirationInterval;

   public ExpiryQueue(int expirationInterval) {
      this.expirationInterval = expirationInterval;
      this.nextExpirationTime.set(this.roundToNextInterval(Time.currentElapsedTime()));
   }

   private long roundToNextInterval(long time) {
      return (time / (long)this.expirationInterval + 1L) * (long)this.expirationInterval;
   }

   public Long remove(Object elem) {
      Long expiryTime = (Long)this.elemMap.remove(elem);
      if (expiryTime != null) {
         Set<E> set = (Set)this.expiryMap.get(expiryTime);
         if (set != null) {
            set.remove(elem);
         }
      }

      return expiryTime;
   }

   public Long update(Object elem, int timeout) {
      Long prevExpiryTime = (Long)this.elemMap.get(elem);
      long now = Time.currentElapsedTime();
      Long newExpiryTime = this.roundToNextInterval(now + (long)timeout);
      if (newExpiryTime.equals(prevExpiryTime)) {
         return null;
      } else {
         Set<E> set = (Set)this.expiryMap.get(newExpiryTime);
         if (set == null) {
            set = Collections.newSetFromMap(new ConcurrentHashMap());
            Set<E> existingSet = (Set)this.expiryMap.putIfAbsent(newExpiryTime, set);
            if (existingSet != null) {
               set = existingSet;
            }
         }

         set.add(elem);
         prevExpiryTime = (Long)this.elemMap.put(elem, newExpiryTime);
         if (prevExpiryTime != null && !newExpiryTime.equals(prevExpiryTime)) {
            Set<E> prevSet = (Set)this.expiryMap.get(prevExpiryTime);
            if (prevSet != null) {
               prevSet.remove(elem);
            }
         }

         return newExpiryTime;
      }
   }

   public long getWaitTime() {
      long now = Time.currentElapsedTime();
      long expirationTime = this.nextExpirationTime.get();
      return now < expirationTime ? expirationTime - now : 0L;
   }

   public Set poll() {
      long now = Time.currentElapsedTime();
      long expirationTime = this.nextExpirationTime.get();
      if (now < expirationTime) {
         return Collections.emptySet();
      } else {
         Set<E> set = null;
         long newExpirationTime = expirationTime + (long)this.expirationInterval;
         if (this.nextExpirationTime.compareAndSet(expirationTime, newExpirationTime)) {
            set = (Set)this.expiryMap.remove(expirationTime);
         }

         return set == null ? Collections.emptySet() : set;
      }
   }

   public void dump(PrintWriter pwriter) {
      pwriter.print("Sets (");
      pwriter.print(this.expiryMap.size());
      pwriter.print(")/(");
      pwriter.print(this.elemMap.size());
      pwriter.println("):");
      ArrayList<Long> keys = new ArrayList(this.expiryMap.keySet());
      Collections.sort(keys);

      for(long time : keys) {
         Set<E> set = (Set)this.expiryMap.get(time);
         if (set != null) {
            pwriter.print(set.size());
            pwriter.print(" expire at ");
            pwriter.print(Time.elapsedTimeToDate(time));
            pwriter.println(":");

            for(Object elem : set) {
               pwriter.print("\t");
               pwriter.println(elem.toString());
            }
         }
      }

   }

   public Map getExpiryMap() {
      return Collections.unmodifiableMap(this.expiryMap);
   }
}
