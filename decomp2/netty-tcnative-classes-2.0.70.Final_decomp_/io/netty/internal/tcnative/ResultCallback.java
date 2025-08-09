package io.netty.internal.tcnative;

public interface ResultCallback {
   void onSuccess(long var1, Object var3);

   void onError(long var1, Throwable var3);
}
