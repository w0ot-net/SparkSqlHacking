package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import org.apache.thrift.meta_data.StructMetaData;
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

public class GetFileMetadataByExprResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetFileMetadataByExprResult");
   private static final TField METADATA_FIELD_DESC = new TField("metadata", (byte)13, (short)1);
   private static final TField IS_SUPPORTED_FIELD_DESC = new TField("isSupported", (byte)2, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetFileMetadataByExprResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetFileMetadataByExprResultTupleSchemeFactory();
   @Nullable
   private Map metadata;
   private boolean isSupported;
   private static final int __ISSUPPORTED_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public GetFileMetadataByExprResult() {
      this.__isset_bitfield = 0;
   }

   public GetFileMetadataByExprResult(Map metadata, boolean isSupported) {
      this();
      this.metadata = metadata;
      this.isSupported = isSupported;
      this.setIsSupportedIsSet(true);
   }

   public GetFileMetadataByExprResult(GetFileMetadataByExprResult other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetMetadata()) {
         Map<Long, MetadataPpdResult> __this__metadata = new HashMap(other.metadata.size());

         for(Map.Entry other_element : other.metadata.entrySet()) {
            Long other_element_key = (Long)other_element.getKey();
            MetadataPpdResult other_element_value = (MetadataPpdResult)other_element.getValue();
            MetadataPpdResult __this__metadata_copy_value = new MetadataPpdResult(other_element_value);
            __this__metadata.put(other_element_key, __this__metadata_copy_value);
         }

         this.metadata = __this__metadata;
      }

      this.isSupported = other.isSupported;
   }

   public GetFileMetadataByExprResult deepCopy() {
      return new GetFileMetadataByExprResult(this);
   }

   public void clear() {
      this.metadata = null;
      this.setIsSupportedIsSet(false);
      this.isSupported = false;
   }

   public int getMetadataSize() {
      return this.metadata == null ? 0 : this.metadata.size();
   }

   public void putToMetadata(long key, MetadataPpdResult val) {
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
      return that instanceof GetFileMetadataByExprResult ? this.equals((GetFileMetadataByExprResult)that) : false;
   }

   public boolean equals(GetFileMetadataByExprResult that) {
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

   public int compareTo(GetFileMetadataByExprResult other) {
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
      return GetFileMetadataByExprResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetFileMetadataByExprResult(");
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
      tmpMap.put(GetFileMetadataByExprResult._Fields.METADATA, new FieldMetaData("metadata", (byte)1, new MapMetaData((byte)13, new FieldValueMetaData((byte)10), new StructMetaData((byte)12, MetadataPpdResult.class))));
      tmpMap.put(GetFileMetadataByExprResult._Fields.IS_SUPPORTED, new FieldMetaData("isSupported", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetFileMetadataByExprResult.class, metaDataMap);
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

   private static class GetFileMetadataByExprResultStandardSchemeFactory implements SchemeFactory {
      private GetFileMetadataByExprResultStandardSchemeFactory() {
      }

      public GetFileMetadataByExprResultStandardScheme getScheme() {
         return new GetFileMetadataByExprResultStandardScheme();
      }
   }

   private static class GetFileMetadataByExprResultStandardScheme extends StandardScheme {
      private GetFileMetadataByExprResultStandardScheme() {
      }

      public void read(TProtocol iprot, GetFileMetadataByExprResult struct) throws TException {
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

                  TMap _map614 = iprot.readMapBegin();
                  struct.metadata = new HashMap(2 * _map614.size);

                  for(int _i617 = 0; _i617 < _map614.size; ++_i617) {
                     long _key615 = iprot.readI64();
                     MetadataPpdResult _val616 = new MetadataPpdResult();
                     _val616.read(iprot);
                     struct.metadata.put(_key615, _val616);
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

      public void write(TProtocol oprot, GetFileMetadataByExprResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetFileMetadataByExprResult.STRUCT_DESC);
         if (struct.metadata != null) {
            oprot.writeFieldBegin(GetFileMetadataByExprResult.METADATA_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)10, (byte)12, struct.metadata.size()));

            for(Map.Entry _iter618 : struct.metadata.entrySet()) {
               oprot.writeI64((Long)_iter618.getKey());
               ((MetadataPpdResult)_iter618.getValue()).write(oprot);
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(GetFileMetadataByExprResult.IS_SUPPORTED_FIELD_DESC);
         oprot.writeBool(struct.isSupported);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetFileMetadataByExprResultTupleSchemeFactory implements SchemeFactory {
      private GetFileMetadataByExprResultTupleSchemeFactory() {
      }

      public GetFileMetadataByExprResultTupleScheme getScheme() {
         return new GetFileMetadataByExprResultTupleScheme();
      }
   }

   private static class GetFileMetadataByExprResultTupleScheme extends TupleScheme {
      private GetFileMetadataByExprResultTupleScheme() {
      }

      public void write(TProtocol prot, GetFileMetadataByExprResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.metadata.size());

         for(Map.Entry _iter619 : struct.metadata.entrySet()) {
            oprot.writeI64((Long)_iter619.getKey());
            ((MetadataPpdResult)_iter619.getValue()).write(oprot);
         }

         oprot.writeBool(struct.isSupported);
      }

      public void read(TProtocol prot, GetFileMetadataByExprResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TMap _map620 = iprot.readMapBegin((byte)10, (byte)12);
         struct.metadata = new HashMap(2 * _map620.size);

         for(int _i623 = 0; _i623 < _map620.size; ++_i623) {
            long _key621 = iprot.readI64();
            MetadataPpdResult _val622 = new MetadataPpdResult();
            _val622.read(iprot);
            struct.metadata.put(_key621, _val622);
         }

         struct.setMetadataIsSet(true);
         struct.isSupported = iprot.readBool();
         struct.setIsSupportedIsSet(true);
      }
   }
}
