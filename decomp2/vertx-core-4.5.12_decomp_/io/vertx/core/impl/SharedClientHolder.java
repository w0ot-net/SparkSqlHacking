package io.vertx.core.impl;

import io.vertx.core.Closeable;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.Shareable;
import java.util.function.Function;

class SharedClientHolder implements Shareable {
   final Hook hook;
   final int count;
   final Object client;

   static Object createSharedClient(Vertx vertx, String clientKey, String clientName, CloseFuture closeFuture, Function supplier) {
      LocalMap<String, SharedClientHolder<C>> localMap = vertx.sharedData().getLocalMap(clientKey);
      SharedClientHolder<C> v = (SharedClientHolder)localMap.compute(clientName, (key, value) -> {
         if (value == null) {
            Hook<C> hook = new Hook(vertx, clientKey, clientName);
            C client = (C)supplier.apply(hook.closeFuture);
            return new SharedClientHolder(hook, 1, client);
         } else {
            return new SharedClientHolder(value.hook, value.count + 1, value.client);
         }
      });
      C client = (C)v.client;
      closeFuture.add(v.hook);
      return client;
   }

   SharedClientHolder(Hook hook, int count, Object client) {
      this.hook = hook;
      this.count = count;
      this.client = client;
   }

   private static class Hook implements Closeable {
      private final Vertx vertx;
      private final CloseFuture closeFuture;
      private final String clientKey;
      private final String clientName;

      private Hook(Vertx vertx, String clientKey, String clientName) {
         this.vertx = vertx;
         this.closeFuture = new CloseFuture();
         this.clientKey = clientKey;
         this.clientName = clientName;
      }

      public void close(Promise completion) {
         LocalMap<String, SharedClientHolder<C>> localMap1 = this.vertx.sharedData().getLocalMap(this.clientKey);
         SharedClientHolder<C> res = (SharedClientHolder)localMap1.compute(this.clientName, (key, value) -> {
            if (value == null) {
               return null;
            } else {
               return value.count == 1 ? null : new SharedClientHolder(this, value.count - 1, value.client);
            }
         });
         if (res == null) {
            this.closeFuture.close(completion);
         } else {
            completion.complete();
         }

      }
   }
}
