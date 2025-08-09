package org.apache.thrift;

import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolDecorator;
import org.apache.thrift.protocol.TProtocolException;

public class TMultiplexedProcessor implements TProcessor {
   private final Map SERVICE_PROCESSOR_MAP = new HashMap();
   private TProcessor defaultProcessor;

   public void registerProcessor(String serviceName, TProcessor processor) {
      this.SERVICE_PROCESSOR_MAP.put(serviceName, processor);
   }

   public void registerDefault(TProcessor processor) {
      this.defaultProcessor = processor;
   }

   public void process(TProtocol iprot, TProtocol oprot) throws TException {
      TMessage message = iprot.readMessageBegin();
      if (message.type != 1 && message.type != 4) {
         throw new TProtocolException(5, "This should not have happened!?");
      } else {
         int index = message.name.indexOf(":");
         if (index < 0) {
            if (this.defaultProcessor != null) {
               this.defaultProcessor.process(new StoredMessageProtocol(iprot, message), oprot);
            } else {
               throw new TProtocolException(5, "Service name not found in message name: " + message.name + ".  Did you forget to use a TMultiplexProtocol in your client?");
            }
         } else {
            String serviceName = message.name.substring(0, index);
            TProcessor actualProcessor = (TProcessor)this.SERVICE_PROCESSOR_MAP.get(serviceName);
            if (actualProcessor == null) {
               throw new TProtocolException(5, "Service name not found: " + serviceName + ".  Did you forget to call registerProcessor()?");
            } else {
               TMessage standardMessage = new TMessage(message.name.substring(serviceName.length() + ":".length()), message.type, message.seqid);
               actualProcessor.process(new StoredMessageProtocol(iprot, standardMessage), oprot);
            }
         }
      }
   }

   private static class StoredMessageProtocol extends TProtocolDecorator {
      TMessage messageBegin;

      public StoredMessageProtocol(TProtocol protocol, TMessage messageBegin) {
         super(protocol);
         this.messageBegin = messageBegin;
      }

      public TMessage readMessageBegin() throws TException {
         return this.messageBegin;
      }
   }
}
