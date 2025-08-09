package org.apache.thrift;

import org.apache.thrift.transport.TTransport;

public class TProcessorFactory {
   private final TProcessor processor_;

   public TProcessorFactory(TProcessor processor) {
      this.processor_ = processor;
   }

   public TProcessor getProcessor(TTransport trans) {
      return this.processor_;
   }

   public boolean isAsyncProcessor() {
      return this.processor_ instanceof TAsyncProcessor;
   }
}
