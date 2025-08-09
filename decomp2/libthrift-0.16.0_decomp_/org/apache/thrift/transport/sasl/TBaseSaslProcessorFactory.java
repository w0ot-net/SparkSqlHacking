package org.apache.thrift.transport.sasl;

import org.apache.thrift.TProcessor;

public class TBaseSaslProcessorFactory implements TSaslProcessorFactory {
   private final TProcessor processor;

   public TBaseSaslProcessorFactory(TProcessor processor) {
      this.processor = processor;
   }

   public TProcessor getProcessor(NonblockingSaslHandler saslHandler) {
      return this.processor;
   }
}
