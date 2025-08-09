package org.apache.commons.lang3.concurrent;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class CallableBackgroundInitializer extends BackgroundInitializer {
   private final Callable callable;

   public CallableBackgroundInitializer(Callable call) {
      this.checkCallable(call);
      this.callable = call;
   }

   public CallableBackgroundInitializer(Callable call, ExecutorService exec) {
      super(exec);
      this.checkCallable(call);
      this.callable = call;
   }

   private void checkCallable(Callable callable) {
      Objects.requireNonNull(callable, "callable");
   }

   protected Exception getTypedException(Exception e) {
      return new Exception(e);
   }

   protected Object initialize() throws Exception {
      return this.callable.call();
   }
}
