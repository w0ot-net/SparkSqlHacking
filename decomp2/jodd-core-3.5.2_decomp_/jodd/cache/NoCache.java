package jodd.cache;

import java.util.Iterator;

public class NoCache implements Cache {
   public int getCacheSize() {
      return 0;
   }

   public long getCacheTimeout() {
      return 0L;
   }

   public void put(Object key, Object object) {
   }

   public void put(Object key, Object object, long timeout) {
   }

   public Object get(Object key) {
      return null;
   }

   public Iterator iterator() {
      return null;
   }

   public int prune() {
      return 0;
   }

   public boolean isFull() {
      return true;
   }

   public void remove(Object key) {
   }

   public void clear() {
   }

   public int size() {
      return 0;
   }

   public boolean isEmpty() {
      return true;
   }
}
