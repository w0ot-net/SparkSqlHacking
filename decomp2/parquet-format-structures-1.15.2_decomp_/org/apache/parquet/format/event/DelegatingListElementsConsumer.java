package org.apache.parquet.format.event;

import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;

class DelegatingListElementsConsumer extends TypedConsumer.ListConsumer {
   private TypedConsumer elementConsumer;

   protected DelegatingListElementsConsumer(TypedConsumer consumer) {
      this.elementConsumer = consumer;
   }

   public void consumeElement(TProtocol protocol, EventBasedThriftReader reader, byte elemType) throws TException {
      this.elementConsumer.read(protocol, reader, elemType);
   }
}
