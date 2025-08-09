package org.apache.thrift.async;

public interface AsyncMethodCallback {
   void onComplete(Object var1);

   void onError(Exception var1);
}
