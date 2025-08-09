package io.vertx.core.net.impl.pool;

public interface Executor {
   void submit(Action var1);

   public interface Action {
      Task execute(Object var1);
   }
}
