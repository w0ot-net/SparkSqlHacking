package org.apache.parquet.format.event;

import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TMap;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TSet;

public final class EventBasedThriftReader {
   private final TProtocol protocol;

   public EventBasedThriftReader(TProtocol protocol) {
      this.protocol = protocol;
   }

   public void readStruct(FieldConsumer c) throws TException {
      this.protocol.readStructBegin();
      this.readStructContent(c);
      this.protocol.readStructEnd();
   }

   public void readStructContent(FieldConsumer c) throws TException {
      while(true) {
         TField field = this.protocol.readFieldBegin();
         if (field.type == 0) {
            return;
         }

         c.consumeField(this.protocol, this, field.id, field.type);
      }
   }

   public void readSetContent(TypedConsumer.SetConsumer eventConsumer, TSet tSet) throws TException {
      for(int i = 0; i < tSet.size; ++i) {
         eventConsumer.consumeElement(this.protocol, this, tSet.elemType);
      }

   }

   public void readMapContent(TypedConsumer.MapConsumer eventConsumer, TMap tMap) throws TException {
      for(int i = 0; i < tMap.size; ++i) {
         eventConsumer.consumeEntry(this.protocol, this, tMap.keyType, tMap.valueType);
      }

   }

   public void readMapEntry(byte keyType, TypedConsumer keyConsumer, byte valueType, TypedConsumer valueConsumer) throws TException {
      keyConsumer.read(this.protocol, this, keyType);
      valueConsumer.read(this.protocol, this, valueType);
   }

   public void readListContent(TypedConsumer.ListConsumer eventConsumer, TList tList) throws TException {
      for(int i = 0; i < tList.size; ++i) {
         eventConsumer.consumeElement(this.protocol, this, tList.elemType);
      }

   }
}
