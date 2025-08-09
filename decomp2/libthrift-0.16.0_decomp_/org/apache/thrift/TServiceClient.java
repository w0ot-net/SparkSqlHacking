package org.apache.thrift;

import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;

public abstract class TServiceClient {
   protected TProtocol iprot_;
   protected TProtocol oprot_;
   protected int seqid_;

   public TServiceClient(TProtocol prot) {
      this(prot, prot);
   }

   public TServiceClient(TProtocol iprot, TProtocol oprot) {
      this.iprot_ = iprot;
      this.oprot_ = oprot;
   }

   public TProtocol getInputProtocol() {
      return this.iprot_;
   }

   public TProtocol getOutputProtocol() {
      return this.oprot_;
   }

   protected void sendBase(String methodName, TBase args) throws TException {
      this.sendBase(methodName, args, (byte)1);
   }

   protected void sendBaseOneway(String methodName, TBase args) throws TException {
      this.sendBase(methodName, args, (byte)4);
   }

   private void sendBase(String methodName, TBase args, byte type) throws TException {
      this.oprot_.writeMessageBegin(new TMessage(methodName, type, ++this.seqid_));
      args.write(this.oprot_);
      this.oprot_.writeMessageEnd();
      this.oprot_.getTransport().flush();
   }

   protected void receiveBase(TBase result, String methodName) throws TException {
      TMessage msg = this.iprot_.readMessageBegin();
      if (msg.type == 3) {
         TApplicationException x = new TApplicationException();
         x.read(this.iprot_);
         this.iprot_.readMessageEnd();
         throw x;
      } else if (msg.seqid != this.seqid_) {
         throw new TApplicationException(4, String.format("%s failed: out of sequence response: expected %d but got %d", methodName, this.seqid_, msg.seqid));
      } else {
         result.read(this.iprot_);
         this.iprot_.readMessageEnd();
      }
   }
}
