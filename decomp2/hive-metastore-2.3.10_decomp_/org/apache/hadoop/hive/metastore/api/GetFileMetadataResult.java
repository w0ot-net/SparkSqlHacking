package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class GetFileMetadataResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetFileMetadataResult");
   private static final TField METADATA_FIELD_DESC = new TField("metadata", (byte)13, (short)1);
   private static final TField IS_SUPPORTED_FIELD_DESC = new TField("isSupported", (byte)2, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetFileMetadataResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetFileMetadataResultTupleSchemeFactory();
   @Nullable
   private Map metadata;
   private boolean isSupported;
   private static final int __ISSUPPORTED_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public GetFileMetadataResult() {
      this.__isset_bitfield = 0;
   }

   public GetFileMetadataResult(Map metadata, boolean isSupported) {
      this();
      this.metadata = metadata;
      this.isSupported = isSupported;
      this.setIsSupportedIsSet(true);
   }

   public GetFileMetadataResult(GetFileMetadataResult other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetMetadata()) {
         Map<Long, ByteBuffer> __this__metadata = new HashMap(other.metadata);
         this.metadata = __this__metadata;
      }

      this.isSupported = other.isSupported;
   }

   public GetFileMetadataResult deepCopy() {
      return new GetFileMetadataResult(this);
   }

   public void clear() {
      this.metadata = null;
      this.setIsSupportedIsSet(false);
      this.isSupported = false;
   }

   public int getMetadataSize() {
      return this.metadata == null ? 0 : this.metadata.size();
   }

   public void putToMetadata(long key, ByteBuffer val) {
      if (this.metadata == null) {
         this.metadata = new HashMap();
      }

      this.metadata.put(key, val);
   }

   @Nullable
   public Map getMetadata() {
      return this.metadata;
   }

   public void setMetadata(@Nullable Map metadata) {
      this.metadata = metadata;
   }

   public void unsetMetadata() {
      this.metadata = null;
   }

   public boolean isSetMetadata() {
      return this.metadata != null;
   }

   public void setMetadataIsSet(boolean value) {
      if (!value) {
         this.metadata = null;
      }

   }

   public boolean isIsSupported() {
      return this.isSupported;
   }

   public void setIsSupported(boolean isSupported) {
      this.isSupported = isSupported;
      this.setIsSupportedIsSet(true);
   }

   public void unsetIsSupported() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIsSupported() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIsSupportedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case METADATA:
            if (value == null) {
               this.unsetMetadata();
            } else {
               this.setMetadata((Map)value);
            }
            break;
         case IS_SUPPORTED:
            if (value == null) {
               this.unsetIsSupported();
            } else {
               this.setIsSupported((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case METADATA:
            return this.getMetadata();
         case IS_SUPPORTED:
            return this.isIsSupported();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case METADATA:
               return this.isSetMetadata();
            case IS_SUPPORTED:
               return this.isSetIsSupported();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetFileMetadataResult ? this.equals((GetFileMetadataResult)that) : false;
   }

   public boolean equals(GetFileMetadataResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_metadata = this.isSetMetadata();
         boolean that_present_metadata = that.isSetMetadata();
         if (this_present_metadata || that_present_metadata) {
            if (!this_present_metadata || !that_present_metadata) {
               return false;
            }

            if (!this.metadata.equals(that.metadata)) {
               return false;
            }
         }

         boolean this_present_isSupported = true;
         boolean that_present_isSupported = true;
         if (this_present_isSupported || that_present_isSupported) {
            if (!this_present_isSupported || !that_present_isSupported) {
               return false;
            }

            if (this.isSupported != that.isSupported) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetMetadata() ? 131071 : 524287);
      if (this.isSetMetadata()) {
         hashCode = hashCode * 8191 + this.metadata.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSupported ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(GetFileMetadataResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMetadata(), other.isSetMetadata());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMetadata()) {
               lastComparison = TBaseHelper.compareTo(this.metadata, other.metadata);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetIsSupported(), other.isSetIsSupported());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetIsSupported()) {
                  lastComparison = TBaseHelper.compareTo(this.isSupported, other.isSupported);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GetFileMetadataResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetFileMetadataResult(");
      boolean first = true;
      sb.append("metadata:");
      if (this.metadata == null) {
         sb.append("null");
      } else {
         sb.append(this.metadata);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("isSupported:");
      sb.append(this.isSupported);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetMetadata()) {
         throw new TProtocolException("Required field 'metadata' is unset! Struct:" + this.toString());
      } else if (!this.isSetIsSupported()) {
         throw new TProtocolException("Required field 'isSupported' is unset! Struct:" + this.toString());
      }
   }

   private void writeObject(ObjectOutputStream out) throws IOException {
      try {
         this.write(new TCompactProtocol(new TIOStreamTransport(out)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      try {
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetFileMetadataResult._Fields.METADATA, new FieldMetaData("metadata", (byte)1, new MapMetaData((byte)13, new FieldValueMetaData((byte)10), new FieldValueMetaData((byte)11, true))));
      tmpMap.put(GetFileMetadataResult._Fields.IS_SUPPORTED, new FieldMetaData("isSupported", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetFileMetadataResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      METADATA((short)1, "metadata"),
      IS_SUPPORTED((short)2, "isSupported");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return METADATA;
            case 2:
               return IS_SUPPORTED;
            default:
               return null;
         }
      }

      public static _Fields findByThriftIdOrThrow(int fieldId) {
         _Fields fields = findByThriftId(fieldId);
         if (fields == null) {
            throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
         } else {
            return fields;
         }
      }

      @Nullable
      public static _Fields findByName(String name) {
         return (_Fields)byName.get(name);
      }

      private _Fields(short thriftId, String fieldName) {
         this._thriftId = thriftId;
         this._fieldName = fieldName;
      }

      public short getThriftFieldId() {
         return this._thriftId;
      }

      public String getFieldName() {
         return this._fieldName;
      }

      static {
         for(_Fields field : EnumSet.allOf(_Fields.class)) {
            byName.put(field.getFieldName(), field);
         }

      }
   }

   private static class GetFileMetadataResultStandardSchemeFactory implements SchemeFactory {
      private GetFileMetadataResultStandardSchemeFactory() {
      }

      public GetFileMetadataResultStandardScheme getScheme() {
         return new GetFileMetadataResultStandardScheme();
      }
   }

   private static class GetFileMetadataResultStandardScheme extends StandardScheme {
      private GetFileMetadataResultStandardScheme() {
      }

      public void read(TProtocol iprot, GetFileMetadataResult struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map632 = iprot.readMapBegin();
                  struct.metadata = new HashMap(2 * _map632.size);

                  for(int _i635 = 0; _i635 < _map632.size; ++_i635) {
                     long _key633 = iprot.readI64();
                     ByteBuffer _val634 = iprot.readBinary();
                     struct.metadata.put(_key633, _val634);
                  }

                  iprot.readMapEnd();
                  struct.setMetadataIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 2) {
                     struct.isSupported = iprot.readBool();
                     struct.setIsSupportedIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, GetFileMetadataResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetFileMetadataResult.STRUCT_DESC);
         if (struct.metadata != null) {
            oprot.writeFieldBegin(GetFileMetadataResult.METADATA_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)10, (byte)11, struct.metadata.size()));

            for(Map.Entry _iter636 : struct.metadata.entrySet()) {
               oprot.writeI64((Long)_iter636.getKey());
               oprot.writeBinary((ByteBuffer)_iter636.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(GetFileMetadataResult.IS_SUPPORTED_FIELD_DESC);
         oprot.writeBool(struct.isSupported);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetFileMetadataResultTupleSchemeFactory implements SchemeFactory {
      private GetFileMetadataResultTupleSchemeFactory() {
      }

      public GetFileMetadataResultTupleScheme getScheme() {
         return new GetFileMetadataResultTupleScheme();
      }
   }

   private static class GetFileMetadataResultTupleScheme extends TupleScheme {
      private GetFileMetadataResultTupleScheme() {
      }

      public void write(TProtocol prot, GetFileMetadataResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.metadata.size());

         for(Map.Entry _iter637 : struct.metadata.entrySet()) {
            oprot.writeI64((Long)_iter637.getKey());
            oprot.writeBinary((ByteBuffer)_iter637.getValue());
         }

         oprot.writeBool(struct.isSupported);
      }

      public void read(TProtocol prot, GetFileMetadataResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TMap _map638 = iprot.readMapBegin((byte)10, (byte)11);
         struct.metadata = new HashMap(2 * _map638.size);

         for(int _i641 = 0; _i641 < _map638.size; ++_i641) {
            long _key639 = iprot.readI64();
            ByteBuffer _val640 = iprot.readBinary();
            struct.metadata.put(_key639, _val640);
         }

         struct.setMetadataIsSet(true);
         struct.isSupported = iprot.readBool();
         struct.setIsSupportedIsSet(true);
      }
   }
}
