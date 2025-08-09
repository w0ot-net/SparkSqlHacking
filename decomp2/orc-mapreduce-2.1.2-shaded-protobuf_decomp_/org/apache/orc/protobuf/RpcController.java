package org.apache.orc.protobuf;

public interface RpcController {
   void reset();

   boolean failed();

   String errorText();

   void startCancel();

   void setFailed(String reason);

   boolean isCanceled();

   void notifyOnCancel(RpcCallback callback);
}
