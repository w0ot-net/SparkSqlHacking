package org.apache.thrift;

import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProcessFunction {
   private final String methodName;
   private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFunction.class.getName());

   public ProcessFunction(String methodName) {
      this.methodName = methodName;
   }

   public final void process(int seqid, TProtocol iprot, TProtocol oprot, Object iface) throws TException {
      T args = (T)this.getEmptyArgsInstance();

      try {
         args.read(iprot);
      } catch (TProtocolException e) {
         iprot.readMessageEnd();
         TApplicationException x = new TApplicationException(7, e.getMessage());
         oprot.writeMessageBegin(new TMessage(this.getMethodName(), (byte)3, seqid));
         x.write(oprot);
         oprot.writeMessageEnd();
         oprot.getTransport().flush();
         return;
      }

      iprot.readMessageEnd();
      TSerializable result = null;
      byte msgType = 2;

      try {
         result = this.getResult(iface, args);
      } catch (TTransportException ex) {
         LOGGER.error("Transport error while processing " + this.getMethodName(), ex);
         throw ex;
      } catch (TApplicationException ex) {
         LOGGER.error("Internal application error processing " + this.getMethodName(), ex);
         result = ex;
         msgType = 3;
      } catch (Exception ex) {
         LOGGER.error("Internal error processing " + this.getMethodName(), ex);
         if (this.rethrowUnhandledExceptions()) {
            throw new RuntimeException(ex.getMessage(), ex);
         }

         if (!this.isOneway()) {
            result = new TApplicationException(6, "Internal error processing " + this.getMethodName());
            msgType = 3;
         }
      }

      if (!this.isOneway()) {
         oprot.writeMessageBegin(new TMessage(this.getMethodName(), msgType, seqid));
         result.write(oprot);
         oprot.writeMessageEnd();
         oprot.getTransport().flush();
      }

   }

   private void handleException(int seqid, TProtocol oprot) throws TException {
      if (!this.isOneway()) {
         TApplicationException x = new TApplicationException(6, "Internal error processing " + this.getMethodName());
         oprot.writeMessageBegin(new TMessage(this.getMethodName(), (byte)3, seqid));
         x.write(oprot);
         oprot.writeMessageEnd();
         oprot.getTransport().flush();
      }

   }

   protected boolean rethrowUnhandledExceptions() {
      return false;
   }

   protected abstract boolean isOneway();

   public abstract TBase getResult(Object var1, TBase var2) throws TException;

   public abstract TBase getEmptyArgsInstance();

   public String getMethodName() {
      return this.methodName;
   }
}
