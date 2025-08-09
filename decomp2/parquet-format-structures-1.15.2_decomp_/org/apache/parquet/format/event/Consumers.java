package org.apache.parquet.format.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;

public class Consumers {
   public static DelegatingFieldConsumer fieldConsumer() {
      return new DelegatingFieldConsumer();
   }

   public static TypedConsumer.ListConsumer listOf(Class c, final Consumer consumer) {
      class ListConsumer implements Consumer {
         List list;

         public void consume(TBase t) {
            this.list.add(t);
         }
      }

      final ListConsumer co = new ListConsumer();
      return new DelegatingListElementsConsumer(struct(c, co)) {
         public void consumeList(TProtocol protocol, EventBasedThriftReader reader, TList tList) throws TException {
            co.list = new ArrayList();
            super.consumeList(protocol, reader, tList);
            consumer.consume(co.list);
         }
      };
   }

   public static TypedConsumer.ListConsumer listElementsOf(TypedConsumer consumer) {
      return new DelegatingListElementsConsumer(consumer);
   }

   public static TypedConsumer.StructConsumer struct(Class c, Consumer consumer) {
      return new TBaseStructConsumer(c, consumer);
   }

   public static class DelegatingFieldConsumer implements FieldConsumer {
      private final Map contexts;
      private final FieldConsumer defaultFieldEventConsumer;

      private DelegatingFieldConsumer(FieldConsumer defaultFieldEventConsumer, Map contexts) {
         this.defaultFieldEventConsumer = defaultFieldEventConsumer;
         this.contexts = Collections.unmodifiableMap(contexts);
      }

      private DelegatingFieldConsumer() {
         this((FieldConsumer)(new SkippingFieldConsumer()));
      }

      private DelegatingFieldConsumer(FieldConsumer defaultFieldEventConsumer) {
         this(defaultFieldEventConsumer, Collections.emptyMap());
      }

      public DelegatingFieldConsumer onField(TFieldIdEnum e, TypedConsumer typedConsumer) {
         Map<Short, TypedConsumer> newContexts = new HashMap(this.contexts);
         newContexts.put(e.getThriftFieldId(), typedConsumer);
         return new DelegatingFieldConsumer(this.defaultFieldEventConsumer, newContexts);
      }

      public void consumeField(TProtocol protocol, EventBasedThriftReader reader, short id, byte type) throws TException {
         TypedConsumer delegate = (TypedConsumer)this.contexts.get(id);
         if (delegate != null) {
            delegate.read(protocol, reader, type);
         } else {
            this.defaultFieldEventConsumer.consumeField(protocol, reader, id, type);
         }

      }
   }

   public interface Consumer {
      void consume(Object var1);
   }
}
