package io.vertx.core.impl.future;

public interface Listener {
   void onSuccess(Object var1);

   void onFailure(Throwable var1);
}
