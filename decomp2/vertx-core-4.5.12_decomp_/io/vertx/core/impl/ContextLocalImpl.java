package io.vertx.core.impl;

import io.vertx.core.spi.context.storage.ContextLocal;
import java.util.function.Function;

public class ContextLocalImpl implements ContextLocal {
   final int index;
   final Function duplicator;

   public static ContextLocal create(Class type, Function duplicator) {
      synchronized(LocalSeq.class) {
         int idx = LocalSeq.locals.size();
         ContextLocal<T> local = new ContextLocalImpl(idx, duplicator);
         LocalSeq.locals.add(local);
         return local;
      }
   }

   public ContextLocalImpl(int index, Function duplicator) {
      this.index = index;
      this.duplicator = duplicator;
   }
}
