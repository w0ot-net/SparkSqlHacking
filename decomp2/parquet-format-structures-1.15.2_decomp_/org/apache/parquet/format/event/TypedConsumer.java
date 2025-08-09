package org.apache.parquet.format.event;

import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TMap;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TSet;

public abstract class TypedConsumer {
   public final byte type;

   private TypedConsumer(byte type) {
      this.type = type;
   }

   public final void read(TProtocol protocol, EventBasedThriftReader reader, byte type) throws TException {
      if (this.type != type) {
         throw new TException("Incorrect type in stream. Expected " + this.type + " but got " + type);
      } else {
         this.read(protocol, reader);
      }
   }

   abstract void read(TProtocol var1, EventBasedThriftReader var2) throws TException;

   public abstract static class DoubleConsumer extends TypedConsumer {
      protected DoubleConsumer() {
         super((byte)4, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readDouble());
      }

      public abstract void consume(double var1);
   }

   public abstract static class ByteConsumer extends TypedConsumer {
      protected ByteConsumer() {
         super((byte)3, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readByte());
      }

      public abstract void consume(byte var1);
   }

   public abstract static class BoolConsumer extends TypedConsumer {
      protected BoolConsumer() {
         super((byte)2, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readBool());
      }

      public abstract void consume(boolean var1);
   }

   public abstract static class I32Consumer extends TypedConsumer {
      protected I32Consumer() {
         super((byte)8, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readI32());
      }

      public abstract void consume(int var1);
   }

   public abstract static class I64Consumer extends TypedConsumer {
      protected I64Consumer() {
         super((byte)10, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readI64());
      }

      public abstract void consume(long var1);
   }

   public abstract static class I16Consumer extends TypedConsumer {
      protected I16Consumer() {
         super((byte)6, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readI16());
      }

      public abstract void consume(short var1);
   }

   public abstract static class StringConsumer extends TypedConsumer {
      protected StringConsumer() {
         super((byte)11, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consume(protocol.readString());
      }

      public abstract void consume(String var1);
   }

   public abstract static class StructConsumer extends TypedConsumer {
      protected StructConsumer() {
         super((byte)12, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consumeStruct(protocol, reader);
      }

      public abstract void consumeStruct(TProtocol var1, EventBasedThriftReader var2) throws TException;
   }

   public abstract static class ListConsumer extends TypedConsumer {
      protected ListConsumer() {
         super((byte)15, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consumeList(protocol, reader, protocol.readListBegin());
         protocol.readListEnd();
      }

      public void consumeList(TProtocol protocol, EventBasedThriftReader reader, TList tList) throws TException {
         reader.readListContent(this, tList);
      }

      public abstract void consumeElement(TProtocol var1, EventBasedThriftReader var2, byte var3) throws TException;
   }

   public abstract static class SetConsumer extends TypedConsumer {
      protected SetConsumer() {
         super((byte)14, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consumeSet(protocol, reader, protocol.readSetBegin());
         protocol.readSetEnd();
      }

      public void consumeSet(TProtocol protocol, EventBasedThriftReader reader, TSet tSet) throws TException {
         reader.readSetContent(this, tSet);
      }

      public abstract void consumeElement(TProtocol var1, EventBasedThriftReader var2, byte var3) throws TException;
   }

   public abstract static class MapConsumer extends TypedConsumer {
      protected MapConsumer() {
         super((byte)13, null);
      }

      final void read(TProtocol protocol, EventBasedThriftReader reader) throws TException {
         this.consumeMap(protocol, reader, protocol.readMapBegin());
         protocol.readMapEnd();
      }

      public void consumeMap(TProtocol protocol, EventBasedThriftReader reader, TMap tMap) throws TException {
         reader.readMapContent(this, tMap);
      }

      public abstract void consumeEntry(TProtocol var1, EventBasedThriftReader var2, byte var3, byte var4) throws TException;
   }
}
