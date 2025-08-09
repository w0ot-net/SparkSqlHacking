package shaded.parquet.org.apache.thrift.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.partial.TFieldData;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.transport.TTransport;

public abstract class TProtocol implements TWriteProtocol, TReadProtocol {
   protected TTransport trans_;
   static final int MAX_SKIPPED_BYTES = 256;
   protected byte[] skippedBytes = new byte[256];

   private TProtocol() {
   }

   protected TProtocol(TTransport trans) {
      this.trans_ = trans;
   }

   public TTransport getTransport() {
      return this.trans_;
   }

   protected void checkReadBytesAvailable(TMap map) throws TException {
      long elemSize = (long)(this.getMinSerializedSize(map.keyType) + this.getMinSerializedSize(map.valueType));
      this.trans_.checkReadBytesAvailable((long)map.size * elemSize);
   }

   protected void checkReadBytesAvailable(TList list) throws TException {
      long size = (long)list.getSize();
      this.trans_.checkReadBytesAvailable(size * (long)this.getMinSerializedSize(list.elemType));
   }

   protected void checkReadBytesAvailable(TSet set) throws TException {
      long size = (long)set.getSize();
      this.trans_.checkReadBytesAvailable(size * (long)this.getMinSerializedSize(set.elemType));
   }

   public abstract int getMinSerializedSize(byte var1) throws TException;

   public final void writeSet(byte elementType, Set set, WriteCallback callback) throws TException {
      this.writeSetBegin(new TSet(elementType, set.size()));

      for(Object t : set) {
         callback.call(t);
      }

      this.writeSetEnd();
   }

   public final void writeList(byte elementType, List list, WriteCallback callback) throws TException {
      this.writeListBegin(new TList(elementType, list.size()));

      for(Object t : list) {
         callback.call(t);
      }

      this.writeListEnd();
   }

   public final void writeMap(byte keyType, byte valueType, Map map, WriteCallback callback) throws TException {
      this.writeMapBegin(new TMap(keyType, valueType, map.size()));

      for(Map.Entry entry : map.entrySet()) {
         callback.call(entry);
      }

      this.writeMapEnd();
   }

   public final void writeField(TField field, WriteCallback callback) throws TException {
      this.writeFieldBegin(field);
      callback.call((Object)null);
      this.writeFieldEnd();
   }

   public final void writeStruct(TStruct struct, WriteCallback callback) throws TException {
      this.writeStructBegin(struct);
      callback.call((Object)null);
      this.writeStructEnd();
   }

   public final void writeMessage(TMessage message, WriteCallback callback) throws TException {
      this.writeMessageBegin(message);
      callback.call((Object)null);
      this.writeMessageEnd();
   }

   public final Object readMessage(ReadCallback callback) throws TException {
      TMessage tMessage = this.readMessageBegin();
      T t = (T)callback.accept(tMessage);
      this.readMessageEnd();
      return t;
   }

   public final Object readStruct(ReadCallback callback) throws TException {
      TStruct tStruct = this.readStructBegin();
      T t = (T)callback.accept(tStruct);
      this.readStructEnd();
      return t;
   }

   public final boolean readField(ReadCallback callback) throws Exception {
      TField tField = this.readFieldBegin();
      if (tField.type == 0) {
         return true;
      } else {
         callback.accept(tField);
         this.readFieldEnd();
         return false;
      }
   }

   public final Map readMap(ReadCallback callback) throws TException {
      TMap tMap = this.readMapBegin();
      T t = (T)((Map)callback.accept(tMap));
      this.readMapEnd();
      return t;
   }

   public final Map readMap(ReadMapEntryCallback callback) throws TException {
      return this.readMap(callback, HashMap::new);
   }

   public final Map readMap(ReadMapEntryCallback callback, IntFunction mapCreator) throws TException {
      return this.readMap((ReadCallback)((tMap) -> {
         Map<K, V> map = (Map)mapCreator.apply(tMap.size);

         for(int i = 0; i < tMap.size; ++i) {
            map.put(callback.getKey(), callback.getValue());
         }

         return map;
      }));
   }

   public final List readList(ReadCallback callback) throws TException {
      TList tList = this.readListBegin();
      T t = (T)((List)callback.accept(tList));
      this.readListEnd();
      return t;
   }

   public final List readList(ReadCollectionCallback callback) throws TException {
      return this.readList(callback, ArrayList::new);
   }

   public final List readList(ReadCollectionCallback callback, IntFunction listCreator) throws TException {
      return this.readList((ReadCallback)((tList) -> {
         List<T> list = (List)listCreator.apply(tList.size);

         for(int i = 0; i < tList.size; ++i) {
            list.add(callback.call());
         }

         return list;
      }));
   }

   public final Set readSet(ReadCallback callback) throws TException {
      TSet tSet = this.readSetBegin();
      T t = (T)((Set)callback.accept(tSet));
      this.readSetEnd();
      return t;
   }

   public final Set readSet(ReadCollectionCallback callback) throws TException {
      return this.readSet(callback, HashSet::new);
   }

   public final Set readSet(ReadCollectionCallback callback, IntFunction setCreator) throws TException {
      return this.readSet((ReadCallback)((tSet) -> {
         Set<T> set = (Set)setCreator.apply(tSet.size);

         for(int i = 0; i < tSet.size; ++i) {
            set.add(callback.call());
         }

         return set;
      }));
   }

   public void reset() {
   }

   public Class getScheme() {
      return StandardScheme.class;
   }

   public int readFieldBeginData() throws TException {
      TField tfield = this.readFieldBegin();
      return TFieldData.encode(tfield.type, tfield.id);
   }

   public void skip(byte fieldType) throws TException {
      this.skip(fieldType, Integer.MAX_VALUE);
   }

   public void skip(byte fieldType, int maxDepth) throws TException {
      if (maxDepth <= 0) {
         throw new TException("Maximum skip depth exceeded");
      } else {
         switch (fieldType) {
            case 2:
               this.skipBool();
               break;
            case 3:
               this.skipByte();
               break;
            case 4:
               this.skipDouble();
               break;
            case 5:
            case 7:
            case 9:
            default:
               throw new TProtocolException(1, "Unrecognized type " + fieldType);
            case 6:
               this.skipI16();
               break;
            case 8:
               this.skipI32();
               break;
            case 10:
               this.skipI64();
               break;
            case 11:
               this.skipBinary();
               break;
            case 12:
               this.readStructBegin();

               while(true) {
                  int tfieldData = this.readFieldBeginData();
                  byte tfieldType = TFieldData.getType(tfieldData);
                  if (tfieldType == 0) {
                     this.readStructEnd();
                     return;
                  }

                  this.skip(tfieldType, maxDepth - 1);
                  this.readFieldEnd();
               }
            case 13:
               TMap map = this.readMapBegin();

               for(int i = 0; i < map.size; ++i) {
                  this.skip(map.keyType, maxDepth - 1);
                  this.skip(map.valueType, maxDepth - 1);
               }

               this.readMapEnd();
               break;
            case 14:
               TSet set = this.readSetBegin();

               for(int i = 0; i < set.size; ++i) {
                  this.skip(set.elemType, maxDepth - 1);
               }

               this.readSetEnd();
               break;
            case 15:
               TList list = this.readListBegin();

               for(int i = 0; i < list.size; ++i) {
                  this.skip(list.elemType, maxDepth - 1);
               }

               this.readListEnd();
         }

      }
   }

   protected void skipBool() throws TException {
      this.readBool();
   }

   protected void skipByte() throws TException {
      this.readByte();
   }

   protected void skipI16() throws TException {
      this.readI16();
   }

   protected void skipI32() throws TException {
      this.readI32();
   }

   protected void skipI64() throws TException {
      this.readI64();
   }

   protected void skipDouble() throws TException {
      this.readDouble();
   }

   protected void skipBinary() throws TException {
      this.readBinary();
   }

   protected void skipBytes(int numBytes) throws TException {
      if (numBytes <= 256) {
         if (this.getTransport().getBytesRemainingInBuffer() >= numBytes) {
            this.getTransport().consumeBuffer(numBytes);
         } else {
            this.getTransport().readAll(this.skippedBytes, 0, numBytes);
         }
      } else {
         for(int remaining = numBytes; remaining > 0; remaining -= 256) {
            this.skipBytes(Math.min(remaining, 256));
         }
      }

   }

   public interface ReadCallback {
      Object accept(Object var1) throws TException;
   }

   public interface ReadCollectionCallback {
      Object call() throws TException;
   }

   public interface ReadMapEntryCallback {
      Object getKey() throws TException;

      Object getValue() throws TException;
   }

   public interface WriteCallback {
      void call(Object var1) throws TException;
   }
}
