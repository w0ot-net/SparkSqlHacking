package org.apache.thrift;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.AbstractNonblockingServer;

public abstract class AsyncProcessFunction {
   final String methodName;

   public AsyncProcessFunction(String methodName) {
      this.methodName = methodName;
   }

   protected abstract boolean isOneway();

   public abstract void start(Object var1, TBase var2, AsyncMethodCallback var3) throws TException;

   public abstract TBase getEmptyArgsInstance();

   public abstract AsyncMethodCallback getResultHandler(AbstractNonblockingServer.AsyncFrameBuffer var1, int var2);

   public String getMethodName() {
      return this.methodName;
   }

   public void sendResponse(AbstractNonblockingServer.AsyncFrameBuffer fb, TSerializable result, byte type, int seqid) throws TException {
      TProtocol oprot = fb.getOutputProtocol();
      oprot.writeMessageBegin(new TMessage(this.getMethodName(), type, seqid));
      result.write(oprot);
      oprot.writeMessageEnd();
      oprot.getTransport().flush();
      fb.responseReady();
   }
}
