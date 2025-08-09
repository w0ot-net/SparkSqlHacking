package io.vertx.core;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.VertxThread;
import io.vertx.core.impl.launcher.VertxCommandLauncher;
import io.vertx.core.json.JsonObject;
import java.util.List;
import java.util.concurrent.Callable;

@VertxGen
public interface Context {
   static boolean isOnWorkerThread() {
      Thread t = Thread.currentThread();
      return t instanceof VertxThread && ((VertxThread)t).isWorker();
   }

   static boolean isOnEventLoopThread() {
      Thread t = Thread.currentThread();
      return t instanceof VertxThread && !((VertxThread)t).isWorker();
   }

   static boolean isOnVertxThread() {
      return Thread.currentThread() instanceof VertxThread;
   }

   void runOnContext(Handler var1);

   /** @deprecated */
   @Deprecated
   void executeBlocking(Handler var1, boolean var2, Handler var3);

   @GenIgnore({"permitted-type"})
   Future executeBlocking(Callable var1, boolean var2);

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

   /** @deprecated */
   @Deprecated
   default void executeBlocking(Handler blockingCodeHandler, Handler resultHandler) {
      this.executeBlocking(blockingCodeHandler, true, resultHandler);
   }

   /** @deprecated */
   @Deprecated
   Future executeBlocking(Handler var1, boolean var2);

   /** @deprecated */
   @Deprecated
   default Future executeBlocking(Handler blockingCodeHandler) {
      return this.executeBlocking(blockingCodeHandler, true);
   }

   @GenIgnore({"permitted-type"})
   default Future executeBlocking(Callable blockingCodeHandler) {
      return this.executeBlocking(blockingCodeHandler, true);
   }

   String deploymentID();

   @Nullable JsonObject config();

   default List processArgs() {
      return VertxCommandLauncher.getProcessArguments();
   }

   boolean isEventLoopContext();

   boolean isWorkerContext();

   ThreadingModel threadingModel();

   Object get(Object var1);

   void put(Object var1, Object var2);

   boolean remove(Object var1);

   Object getLocal(Object var1);

   void putLocal(Object var1, Object var2);

   boolean removeLocal(Object var1);

   Vertx owner();

   int getInstanceCount();

   @Fluent
   Context exceptionHandler(@Nullable Handler var1);

   @GenIgnore
   @Nullable Handler exceptionHandler();
}
