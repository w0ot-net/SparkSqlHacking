package org.apache.thrift;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.partial.TFieldData;
import org.apache.thrift.partial.ThriftFieldValueProcessor;
import org.apache.thrift.partial.ThriftMetadata;
import org.apache.thrift.partial.ThriftStructProcessor;
import org.apache.thrift.partial.Validate;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransportException;

public class TDeserializer {
   private final TProtocol protocol_;
   private final TMemoryInputTransport trans_;
   private ThriftMetadata.ThriftStruct metadata_;
   private ThriftFieldValueProcessor processor_;

   public TDeserializer() throws TTransportException {
      this(new TBinaryProtocol.Factory());
   }

   public TDeserializer(TProtocolFactory protocolFactory) throws TTransportException {
      this.metadata_ = null;
      this.processor_ = null;
      this.trans_ = new TMemoryInputTransport(new TConfiguration());
      this.protocol_ = protocolFactory.getProtocol(this.trans_);
   }

   public TDeserializer(Class thriftClass, Collection fieldNames, ThriftFieldValueProcessor processor, TProtocolFactory protocolFactory) throws TTransportException {
      this(protocolFactory);
      Validate.checkNotNull(thriftClass, "thriftClass");
      Validate.checkNotNull(fieldNames, "fieldNames");
      Validate.checkNotNull(processor, "processor");
      this.metadata_ = ThriftMetadata.ThriftStruct.fromFieldNames(thriftClass, fieldNames);
      this.processor_ = processor;
   }

   public TDeserializer(Class thriftClass, Collection fieldNames, TProtocolFactory protocolFactory) throws TTransportException {
      this(thriftClass, fieldNames, new ThriftStructProcessor(), protocolFactory);
   }

   public ThriftMetadata.ThriftStruct getMetadata() {
      return this.metadata_;
   }

   public void deserialize(TBase base, byte[] bytes) throws TException {
      this.deserialize(base, bytes, 0, bytes.length);
   }

   public void deserialize(TBase base, byte[] bytes, int offset, int length) throws TException {
      if (this.isPartialDeserializationMode()) {
         this.partialDeserializeThriftObject(base, bytes, offset, length);
      } else {
         try {
            this.trans_.reset(bytes, offset, length);
            base.read(this.protocol_);
         } finally {
            this.trans_.clear();
            this.protocol_.reset();
         }
      }

   }

   public void deserialize(TBase base, String data, String charset) throws TException {
      try {
         this.deserialize(base, data.getBytes(charset));
      } catch (UnsupportedEncodingException var8) {
         throw new TException("JVM DOES NOT SUPPORT ENCODING: " + charset);
      } finally {
         this.protocol_.reset();
      }

   }

   public void partialDeserialize(TBase tb, byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      try {
         if (this.locateField(bytes, fieldIdPathFirst, fieldIdPathRest) != null) {
            tb.read(this.protocol_);
         }
      } catch (Exception e) {
         throw new TException(e);
      } finally {
         this.trans_.clear();
         this.protocol_.reset();
      }

   }

   public Boolean partialDeserializeBool(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (Boolean)this.partialDeserializeField((byte)2, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public Byte partialDeserializeByte(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (Byte)this.partialDeserializeField((byte)3, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public Double partialDeserializeDouble(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (Double)this.partialDeserializeField((byte)4, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public Short partialDeserializeI16(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (Short)this.partialDeserializeField((byte)6, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public Integer partialDeserializeI32(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (Integer)this.partialDeserializeField((byte)8, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public Long partialDeserializeI64(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (Long)this.partialDeserializeField((byte)10, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public String partialDeserializeString(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (String)this.partialDeserializeField((byte)11, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public ByteBuffer partialDeserializeByteArray(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      return (ByteBuffer)this.partialDeserializeField((byte)100, bytes, fieldIdPathFirst, fieldIdPathRest);
   }

   public Short partialDeserializeSetFieldIdInUnion(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      Short var5;
      try {
         TField field = this.locateField(bytes, fieldIdPathFirst, fieldIdPathRest);
         if (field == null) {
            var5 = null;
            return var5;
         }

         this.protocol_.readStructBegin();
         var5 = this.protocol_.readFieldBegin().id;
      } catch (Exception e) {
         throw new TException(e);
      } finally {
         this.trans_.clear();
         this.protocol_.reset();
      }

      return var5;
   }

   private Object partialDeserializeField(byte ttype, byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      try {
         TField field = this.locateField(bytes, fieldIdPathFirst, fieldIdPathRest);
         if (field != null) {
            if (ttype == field.type) {
               switch (ttype) {
                  case 2:
                     Boolean var19 = this.protocol_.readBool();
                     return var19;
                  case 3:
                     Byte var18 = this.protocol_.readByte();
                     return var18;
                  case 4:
                     Double var17 = this.protocol_.readDouble();
                     return var17;
                  case 5:
                  case 7:
                  case 9:
                  default:
                     Object var20 = null;
                     return var20;
                  case 6:
                     Short var16 = this.protocol_.readI16();
                     return var16;
                  case 8:
                     Integer var15 = this.protocol_.readI32();
                     return var15;
                  case 10:
                     Long var14 = this.protocol_.readI64();
                     return var14;
                  case 11:
                     String var13 = this.protocol_.readString();
                     return var13;
               }
            }

            if (ttype == 100 && field.type == 11) {
               ByteBuffer var12 = this.protocol_.readBinary();
               return var12;
            }
         }

         Object var6 = null;
         return var6;
      } catch (Exception e) {
         throw new TException(e);
      } finally {
         this.trans_.clear();
         this.protocol_.reset();
      }
   }

   private TField locateField(byte[] bytes, TFieldIdEnum fieldIdPathFirst, TFieldIdEnum... fieldIdPathRest) throws TException {
      this.trans_.reset(bytes);
      TFieldIdEnum[] fieldIdPath = new TFieldIdEnum[fieldIdPathRest.length + 1];
      fieldIdPath[0] = fieldIdPathFirst;
      System.arraycopy(fieldIdPathRest, 0, fieldIdPath, 1, fieldIdPathRest.length);
      int curPathIndex = 0;
      TField field = null;
      this.protocol_.readStructBegin();

      while(curPathIndex < fieldIdPath.length) {
         field = this.protocol_.readFieldBegin();
         if (field.type == 0 || field.id > fieldIdPath[curPathIndex].getThriftFieldId()) {
            return null;
         }

         if (field.id != fieldIdPath[curPathIndex].getThriftFieldId()) {
            TProtocolUtil.skip(this.protocol_, field.type);
            this.protocol_.readFieldEnd();
         } else {
            ++curPathIndex;
            if (curPathIndex < fieldIdPath.length) {
               this.protocol_.readStructBegin();
            }
         }
      }

      return field;
   }

   public void fromString(TBase base, String data) throws TException {
      this.deserialize(base, data.getBytes());
   }

   public Object partialDeserializeObject(byte[] bytes) throws TException {
      return this.partialDeserializeObject(bytes, 0, bytes.length);
   }

   public Object partialDeserializeThriftObject(TBase base, byte[] bytes, int offset, int length) throws TException {
      this.ensurePartialThriftDeserializationMode();
      return this.partialDeserializeObject(base, bytes, offset, length);
   }

   public Object partialDeserializeObject(byte[] bytes, int offset, int length) throws TException {
      this.ensurePartialDeserializationMode();
      return this.partialDeserializeObject((Object)null, bytes, offset, length);
   }

   private Object partialDeserializeObject(Object instance, byte[] bytes, int offset, int length) throws TException {
      this.ensurePartialDeserializationMode();
      this.trans_.reset(bytes, offset, length);
      this.protocol_.reset();
      return this.deserializeStruct(instance, this.metadata_);
   }

   private Object deserialize(ThriftMetadata.ThriftObject data) throws TException {
      byte fieldType = data.data.valueMetaData.type;
      switch (fieldType) {
         case 2:
            return this.protocol_.readBool();
         case 3:
            return this.protocol_.readByte();
         case 4:
            return this.protocol_.readDouble();
         case 5:
         case 7:
         case 9:
         default:
            throw unsupportedFieldTypeException(fieldType);
         case 6:
            return this.protocol_.readI16();
         case 8:
            return this.protocol_.readI32();
         case 10:
            return this.protocol_.readI64();
         case 11:
            if (((ThriftMetadata.ThriftPrimitive)data).isBinary()) {
               return this.processor_.prepareBinary(this.protocol_.readBinary());
            }

            return this.processor_.prepareString(this.protocol_.readBinary());
         case 12:
            return this.deserializeStruct((Object)null, (ThriftMetadata.ThriftStruct)data);
         case 13:
            return this.deserializeMap((ThriftMetadata.ThriftMap)data);
         case 14:
            return this.deserializeSet((ThriftMetadata.ThriftSet)data);
         case 15:
            return this.deserializeList((ThriftMetadata.ThriftList)data);
         case 16:
            return this.deserializeEnum((ThriftMetadata.ThriftEnum)data);
      }
   }

   private Object deserializeStruct(Object instance, ThriftMetadata.ThriftStruct data) throws TException {
      if (instance == null) {
         instance = this.processor_.createNewStruct(data);
      }

      this.protocol_.readStructBegin();

      while(true) {
         int tfieldData = this.protocol_.readFieldBeginData();
         byte tfieldType = TFieldData.getType(tfieldData);
         if (tfieldType == 0) {
            this.protocol_.readStructEnd();
            return this.processor_.prepareStruct(instance);
         }

         Integer id = Integer.valueOf(TFieldData.getId(tfieldData));
         ThriftMetadata.ThriftObject field = (ThriftMetadata.ThriftObject)data.fields.get(id);
         if (field != null) {
            this.deserializeStructField(instance, field.fieldId, field);
         } else {
            this.protocol_.skip(tfieldType);
         }

         this.protocol_.readFieldEnd();
      }
   }

   private void deserializeStructField(Object instance, TFieldIdEnum fieldId, ThriftMetadata.ThriftObject data) throws TException {
      byte fieldType = data.data.valueMetaData.type;
      switch (fieldType) {
         case 2:
            this.processor_.setBool(instance, fieldId, this.protocol_.readBool());
            break;
         case 3:
            this.processor_.setByte(instance, fieldId, this.protocol_.readByte());
            break;
         case 4:
            this.processor_.setDouble(instance, fieldId, this.protocol_.readDouble());
            break;
         case 5:
         case 7:
         case 9:
         default:
            throw new RuntimeException("Unsupported field type: " + fieldId.toString());
         case 6:
            this.processor_.setInt16(instance, fieldId, this.protocol_.readI16());
            break;
         case 8:
            this.processor_.setInt32(instance, fieldId, this.protocol_.readI32());
            break;
         case 10:
            this.processor_.setInt64(instance, fieldId, this.protocol_.readI64());
            break;
         case 11:
            if (((ThriftMetadata.ThriftPrimitive)data).isBinary()) {
               this.processor_.setBinary(instance, fieldId, this.protocol_.readBinary());
            } else {
               this.processor_.setString(instance, fieldId, this.protocol_.readBinary());
            }
            break;
         case 12:
            Object value = this.deserializeStruct((Object)null, (ThriftMetadata.ThriftStruct)data);
            this.processor_.setStructField(instance, fieldId, value);
            break;
         case 13:
            Object value = this.deserializeMap((ThriftMetadata.ThriftMap)data);
            this.processor_.setMapField(instance, fieldId, value);
            break;
         case 14:
            Object value = this.deserializeSet((ThriftMetadata.ThriftSet)data);
            this.processor_.setSetField(instance, fieldId, value);
            break;
         case 15:
            Object value = this.deserializeList((ThriftMetadata.ThriftList)data);
            this.processor_.setListField(instance, fieldId, value);
            break;
         case 16:
            Object value = this.deserializeEnum((ThriftMetadata.ThriftEnum)data);
            this.processor_.setEnumField(instance, fieldId, value);
      }

   }

   private Object deserializeList(ThriftMetadata.ThriftList data) throws TException {
      TList tlist = this.protocol_.readListBegin();
      Object instance = this.processor_.createNewList(tlist.size);

      for(int i = 0; i < tlist.size; ++i) {
         Object value = this.deserialize(data.elementData);
         this.processor_.setListElement(instance, i, value);
      }

      this.protocol_.readListEnd();
      return this.processor_.prepareList(instance);
   }

   private Object deserializeMap(ThriftMetadata.ThriftMap data) throws TException {
      TMap tmap = this.protocol_.readMapBegin();
      Object instance = this.processor_.createNewMap(tmap.size);

      for(int i = 0; i < tmap.size; ++i) {
         Object key = this.deserialize(data.keyData);
         Object val = this.deserialize(data.valueData);
         this.processor_.setMapElement(instance, i, key, val);
      }

      this.protocol_.readMapEnd();
      return this.processor_.prepareMap(instance);
   }

   private Object deserializeSet(ThriftMetadata.ThriftSet data) throws TException {
      TSet tset = this.protocol_.readSetBegin();
      Object instance = this.processor_.createNewSet(tset.size);

      for(int i = 0; i < tset.size; ++i) {
         Object eltValue = this.deserialize(data.elementData);
         this.processor_.setSetElement(instance, i, eltValue);
      }

      this.protocol_.readSetEnd();
      return this.processor_.prepareSet(instance);
   }

   private Object deserializeEnum(ThriftMetadata.ThriftEnum data) throws TException {
      int ordinal = this.protocol_.readI32();
      Class<? extends TEnum> enumClass = ((EnumMetaData)data.data.valueMetaData).enumClass;
      return this.processor_.prepareEnum(enumClass, ordinal);
   }

   private Class getStructClass(ThriftMetadata.ThriftStruct data) {
      return ((StructMetaData)data.data.valueMetaData).structClass;
   }

   private static UnsupportedOperationException unsupportedFieldTypeException(byte fieldType) {
      return new UnsupportedOperationException("field type not supported: " + fieldType);
   }

   private boolean isPartialDeserializationMode() {
      return this.metadata_ != null && this.processor_ != null;
   }

   private void ensurePartialDeserializationMode() throws IllegalStateException {
      if (!this.isPartialDeserializationMode()) {
         throw new IllegalStateException("Members metadata and processor must be correctly initialized in order to use this method");
      }
   }

   private void ensurePartialThriftDeserializationMode() throws IllegalStateException {
      this.ensurePartialDeserializationMode();
      if (!(this.processor_ instanceof ThriftStructProcessor)) {
         throw new IllegalStateException("processor must be an instance of ThriftStructProcessor to use this method");
      }
   }
}
