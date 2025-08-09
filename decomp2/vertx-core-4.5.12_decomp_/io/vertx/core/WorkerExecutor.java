package io.vertx.core;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.metrics.Measured;
import java.util.concurrent.Callable;

@VertxGen
public interface WorkerExecutor extends Measured {
   /** @deprecated */
   @Deprecated
   void executeBlocking(Handler var1, boolean var2, Handler var3);

   /** @deprecated */
   @Deprecated
   default void executeBlocking(Handler blockingCodeHandler, Handler resultHandler) {
      this.executeBlocking(blockingCodeHandler, true, resultHandler);
   }

   /** @deprecated */
   @Deprecated
   Future executeBlocking(Handler var1, boolean var2);

   @GenIgnore({"permitted-type"})
   Future executeBlocking(Callable var1, boolean var2);

   /** @deprecated */
   @Deprecated
   default Future executeBlocking(Handler blockingCodeHandler) {
      return this.executeBlocking(blockingCodeHandler, true);
   }

   @GenIgnore({"permitted-type"})
   default Future executeBlocking(Callable blockingCodeHandler) {
      return this.executeBlocking(blockingCodeHandler, true);
   }

   @GenIgnore({"permitted-type"})
   default void executeBlocking(Callable blockingCodeHandler, Handler resultHandler) {
      Future<T> future = this.executeBlocking(blockingCodeHandler, true);
      if (resultHandler != null) {
         future.onComplete(resultHandler);
      }

   }

   @GenIgnore({"permitted-type"})
   default void executeBlocking(Callable blockingCodeHandler, boolean ordered, Handler resultHandler) {
      Future<T> future = this.executeBlocking(blockingCodeHandler, ordered);
      if (resultHandler != null) {
         future.onComplete(resultHandler);
      }

   }

   void close(Handler var1);

   Future close();
}
