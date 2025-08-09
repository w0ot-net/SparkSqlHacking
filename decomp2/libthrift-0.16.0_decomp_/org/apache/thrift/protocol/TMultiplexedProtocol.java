package org.apache.thrift.protocol;

import org.apache.thrift.TException;

public class TMultiplexedProtocol extends TProtocolDecorator {
   public static final String SEPARATOR = ":";
   private final String SERVICE_NAME;

   public TMultiplexedProtocol(TProtocol protocol, String serviceName) {
      super(protocol);
      this.SERVICE_NAME = serviceName;
   }

   public void writeMessageBegin(TMessage tMessage) throws TException {
      if (tMessage.type != 1 && tMessage.type != 4) {
         super.writeMessageBegin(tMessage);
      } else {
         super.writeMessageBegin(new TMessage(this.SERVICE_NAME + ":" + tMessage.name, tMessage.type, tMessage.seqid));
      }

   }
}
