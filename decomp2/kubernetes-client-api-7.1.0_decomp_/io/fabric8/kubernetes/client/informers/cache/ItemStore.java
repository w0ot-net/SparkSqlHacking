package io.fabric8.kubernetes.client.informers.cache;

import java.util.stream.Stream;

public interface ItemStore {
   String getKey(Object var1);

   Object put(String var1, Object var2);

   Object remove(String var1);

   Stream keySet();

   Stream values();

   int size();

   Object get(String var1);

   default boolean isFullState() {
      return true;
   }
}
