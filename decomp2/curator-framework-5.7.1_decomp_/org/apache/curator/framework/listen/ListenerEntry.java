package org.apache.curator.framework.listen;

import java.util.concurrent.Executor;

public class ListenerEntry {
   public final Object listener;
   public final Executor executor;

   public ListenerEntry(Object listener, Executor executor) {
      this.listener = listener;
      this.executor = executor;
   }
}
