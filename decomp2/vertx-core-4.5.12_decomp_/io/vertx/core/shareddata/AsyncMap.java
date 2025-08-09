package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VertxGen
public interface AsyncMap {
   default void get(Object k, Handler resultHandler) {
      this.get(k).onComplete(resultHandler);
   }

   Future get(Object var1);

   default void put(Object k, Object v, Handler completionHandler) {
      this.put(k, v).onComplete(completionHandler);
   }

   Future put(Object var1, Object var2);

   default void put(Object k, Object v, long ttl, Handler completionHandler) {
      this.put(k, v, ttl).onComplete(completionHandler);
   }

   Future put(Object var1, Object var2, long var3);

   default void putIfAbsent(Object k, Object v, Handler completionHandler) {
      this.putIfAbsent(k, v).onComplete(completionHandler);
   }

   Future putIfAbsent(Object var1, Object var2);

   default void putIfAbsent(Object k, Object v, long ttl, Handler completionHandler) {
      this.putIfAbsent(k, v, ttl).onComplete(completionHandler);
   }

   Future putIfAbsent(Object var1, Object var2, long var3);

   default void remove(Object k, Handler resultHandler) {
      this.remove(k).onComplete(resultHandler);
   }

   Future remove(Object var1);

   default void removeIfPresent(Object k, Object v, Handler resultHandler) {
      this.removeIfPresent(k, v).onComplete(resultHandler);
   }

   Future removeIfPresent(Object var1, Object var2);

   default void replace(Object k, Object v, Handler resultHandler) {
      this.replace(k, v).onComplete(resultHandler);
   }

   Future replace(Object var1, Object var2);

   default void replace(Object k, Object v, long ttl, Handler resultHandler) {
      this.replace(k, v, ttl).onComplete(resultHandler);
   }

   default Future replace(Object k, Object v, long ttl) {
      return Future.failedFuture((Throwable)(new UnsupportedOperationException()));
   }

   default void replaceIfPresent(Object k, Object oldValue, Object newValue, Handler resultHandler) {
      this.replaceIfPresent(k, oldValue, newValue).onComplete(resultHandler);
   }

   Future replaceIfPresent(Object var1, Object var2, Object var3);

   default void replaceIfPresent(Object k, Object oldValue, Object newValue, long ttl, Handler resultHandler) {
      this.replaceIfPresent(k, oldValue, newValue, ttl).onComplete(resultHandler);
   }

   default Future replaceIfPresent(Object k, Object oldValue, Object newValue, long ttl) {
      return Future.failedFuture((Throwable)(new UnsupportedOperationException()));
   }

   default void clear(Handler resultHandler) {
      this.clear().onComplete(resultHandler);
   }

   Future clear();

   default void size(Handler resultHandler) {
      this.size().onComplete(resultHandler);
   }

   Future size();

   @GenIgnore({"permitted-type"})
   default void keys(Handler resultHandler) {
      this.keys().onComplete(resultHandler);
   }

   @GenIgnore({"permitted-type"})
   Future keys();

   @GenIgnore({"permitted-type"})
   default void values(Handler resultHandler) {
      this.values().onComplete(resultHandler);
   }

   @GenIgnore({"permitted-type"})
   Future values();

   @GenIgnore
   default void entries(Handler resultHandler) {
      this.entries().onComplete(resultHandler);
   }

   @GenIgnore
   Future entries();
}
