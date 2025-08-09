package jodd.cache;

import java.util.Iterator;

public interface Cache {
   int getCacheSize();

   long getCacheTimeout();

   void put(Object var1, Object var2);

   void put(Object var1, Object var2, long var3);

   Object get(Object var1);

   Iterator iterator();

   int prune();

   boolean isFull();

   void remove(Object var1);

   void clear();

   int size();

   boolean isEmpty();
}
