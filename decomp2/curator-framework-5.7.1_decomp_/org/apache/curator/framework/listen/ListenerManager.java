package org.apache.curator.framework.listen;

import java.util.function.Consumer;

public interface ListenerManager extends Listenable {
   void clear();

   int size();

   void forEach(Consumer var1);

   default boolean isEmpty() {
      return this.size() == 0;
   }
}
