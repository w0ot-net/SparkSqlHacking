package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@VertxGen
public interface SharedData {
   void getClusterWideMap(String var1, Handler var2);

   Future getClusterWideMap(String var1);

   void getAsyncMap(String var1, Handler var2);

   Future getAsyncMap(String var1);

   void getLocalAsyncMap(String var1, Handler var2);

   Future getLocalAsyncMap(String var1);

   void getLock(String var1, Handler var2);

   Future getLock(String var1);

   void getLockWithTimeout(String var1, long var2, Handler var4);

   Future getLockWithTimeout(String var1, long var2);

   void getLocalLock(String var1, Handler var2);

   Future getLocalLock(String var1);

   void getLocalLockWithTimeout(String var1, long var2, Handler var4);

   Future getLocalLockWithTimeout(String var1, long var2);

   void getCounter(String var1, Handler var2);

   Future getCounter(String var1);

   void getLocalCounter(String var1, Handler var2);

   Future getLocalCounter(String var1);

   LocalMap getLocalMap(String var1);
}
