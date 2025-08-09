package io.vertx.core.impl;

import io.vertx.core.spi.context.storage.ContextLocal;
import java.util.ArrayList;
import java.util.List;

class LocalSeq {
   static final List locals = new ArrayList();

   static synchronized void reset() {
      locals.clear();
      locals.add(ContextInternal.LOCAL_MAP);
   }

   static synchronized ContextLocal[] get() {
      return (ContextLocal[])locals.toArray(new ContextLocal[0]);
   }

   static {
      reset();
   }
}
