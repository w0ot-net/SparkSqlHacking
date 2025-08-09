package org.apache.parquet.format.event;

import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;

class TBaseStructConsumer extends TypedConsumer.StructConsumer {
   private final Class c;
   private Consumers.Consumer consumer;

   public TBaseStructConsumer(Class c, Consumers.Consumer consumer) {
      this.c = c;
      this.consumer = consumer;
   }

   public void consumeStruct(TProtocol protocol, EventBasedThriftReader reader) throws TException {
      T o = (T)this.newObject();
      o.read(protocol);
      this.consumer.consume(o);
   }

   protected TBase newObject() {
      try {
         return (TBase)this.c.newInstance();
      } catch (IllegalAccessException | InstantiationException e) {
         throw new RuntimeException(this.c.getName(), e);
      }
   }
}
