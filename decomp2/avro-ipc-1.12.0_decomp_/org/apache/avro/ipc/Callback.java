package org.apache.avro.ipc;

public interface Callback {
   void handleResult(Object result);

   void handleError(Throwable error);
}
