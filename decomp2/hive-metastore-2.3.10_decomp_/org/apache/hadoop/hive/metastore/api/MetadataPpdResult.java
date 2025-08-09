package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class MetadataPpdResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("MetadataPpdResult");
   private static final TField METADATA_FIELD_DESC = new TField("metadata", (byte)11, (short)1);
   private static final TField INCLUDE_BITSET_FIELD_DESC = new TField("includeBitset", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new MetadataPpdResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new MetadataPpdResultTupleSchemeFactory();
   @Nullable
   private ByteBuffer metadata;
   @Nullable
   private ByteBuffer includeBitset;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public MetadataPpdResult() {
   }

   public MetadataPpdResult(MetadataPpdResult other) {
      if (other.isSetMetadata()) {
         this.metadata = TBaseHelper.copyBinary(other.metadata);
      }

      if (other.isSetIncludeBitset()) {
         this.includeBitset = TBaseHelper.copyBinary(other.includeBitset);
      }

   }

   public MetadataPpdResult deepCopy() {
      return new MetadataPpdResult(this);
   }

   public void clear() {
      this.metadata = null;
      this.includeBitset = null;
   }

   public byte[] getMetadata() {
      this.setMetadata(TBaseHelper.rightSize(this.metadata));
      return this.metadata == null ? null : this.metadata.array();
   }

   public ByteBuffer bufferForMetadata() {
      return TBaseHelper.copyBinary(this.metadata);
   }

   public void setMetadata(byte[] metadata) {
      this.metadata = metadata == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)metadata).clone());
   }

   public void setMetadata(@Nullable ByteBuffer metadata) {
      this.metadata = TBaseHelper.copyBinary(metadata);
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

   public byte[] getIncludeBitset() {
      this.setIncludeBitset(TBaseHelper.rightSize(this.includeBitset));
      return this.includeBitset == null ? null : this.includeBitset.array();
   }

   public ByteBuffer bufferForIncludeBitset() {
      return TBaseHelper.copyBinary(this.includeBitset);
   }

   public void setIncludeBitset(byte[] includeBitset) {
      this.includeBitset = includeBitset == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)includeBitset).clone());
   }

   public void setIncludeBitset(@Nullable ByteBuffer includeBitset) {
      this.includeBitset = TBaseHelper.copyBinary(includeBitset);
   }

   public void unsetIncludeBitset() {
      this.includeBitset = null;
   }

   public boolean isSetIncludeBitset() {
      return this.includeBitset != null;
   }

   public void setIncludeBitsetIsSet(boolean value) {
      if (!value) {
         this.includeBitset = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case METADATA:
            if (value == null) {
               this.unsetMetadata();
            } else if (value instanceof byte[]) {
               this.setMetadata((byte[])value);
            } else {
               this.setMetadata((ByteBuffer)value);
            }
            break;
         case INCLUDE_BITSET:
            if (value == null) {
               this.unsetIncludeBitset();
            } else if (value instanceof byte[]) {
               this.setIncludeBitset((byte[])value);
            } else {
               this.setIncludeBitset((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case METADATA:
            return this.getMetadata();
         case INCLUDE_BITSET:
            return this.getIncludeBitset();
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
            case INCLUDE_BITSET:
               return this.isSetIncludeBitset();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof MetadataPpdResult ? this.equals((MetadataPpdResult)that) : false;
   }

   public boolean equals(MetadataPpdResult that) {
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

         boolean this_present_includeBitset = this.isSetIncludeBitset();
         boolean that_present_includeBitset = that.isSetIncludeBitset();
         if (this_present_includeBitset || that_present_includeBitset) {
            if (!this_present_includeBitset || !that_present_includeBitset) {
               return false;
            }

            if (!this.includeBitset.equals(that.includeBitset)) {
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

      hashCode = hashCode * 8191 + (this.isSetIncludeBitset() ? 131071 : 524287);
      if (this.isSetIncludeBitset()) {
         hashCode = hashCode * 8191 + this.includeBitset.hashCode();
      }

      return hashCode;
   }

   public int compareTo(MetadataPpdResult other) {
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

            lastComparison = Boolean.compare(this.isSetIncludeBitset(), other.isSetIncludeBitset());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetIncludeBitset()) {
                  lastComparison = TBaseHelper.compareTo(this.includeBitset, other.includeBitset);
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
      return MetadataPpdResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("MetadataPpdResult(");
      boolean first = true;
      if (this.isSetMetadata()) {
         sb.append("metadata:");
         if (this.metadata == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.metadata, sb);
         }

         first = false;
      }

      if (this.isSetIncludeBitset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("includeBitset:");
         if (this.includeBitset == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.includeBitset, sb);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
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
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{MetadataPpdResult._Fields.METADATA, MetadataPpdResult._Fields.INCLUDE_BITSET};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(MetadataPpdResult._Fields.METADATA, new FieldMetaData("metadata", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(MetadataPpdResult._Fields.INCLUDE_BITSET, new FieldMetaData("includeBitset", (byte)2, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(MetadataPpdResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      METADATA((short)1, "metadata"),
      INCLUDE_BITSET((short)2, "includeBitset");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return METADATA;
            case 2:
               return INCLUDE_BITSET;
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

   private static class MetadataPpdResultStandardSchemeFactory implements SchemeFactory {
      private MetadataPpdResultStandardSchemeFactory() {
      }

      public MetadataPpdResultStandardScheme getScheme() {
         return new MetadataPpdResultStandardScheme();
      }
   }

   private static class MetadataPpdResultStandardScheme extends StandardScheme {
      private MetadataPpdResultStandardScheme() {
      }

      public void read(TProtocol iprot, MetadataPpdResult struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.metadata = iprot.readBinary();
                     struct.setMetadataIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.includeBitset = iprot.readBinary();
                     struct.setIncludeBitsetIsSet(true);
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

      public void write(TProtocol oprot, MetadataPpdResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(MetadataPpdResult.STRUCT_DESC);
         if (struct.metadata != null && struct.isSetMetadata()) {
            oprot.writeFieldBegin(MetadataPpdResult.METADATA_FIELD_DESC);
            oprot.writeBinary(struct.metadata);
            oprot.writeFieldEnd();
         }

         if (struct.includeBitset != null && struct.isSetIncludeBitset()) {
            oprot.writeFieldBegin(MetadataPpdResult.INCLUDE_BITSET_FIELD_DESC);
            oprot.writeBinary(struct.includeBitset);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class MetadataPpdResultTupleSchemeFactory implements SchemeFactory {
      private MetadataPpdResultTupleSchemeFactory() {
      }

      public MetadataPpdResultTupleScheme getScheme() {
         return new MetadataPpdResultTupleScheme();
      }
   }

   private static class MetadataPpdResultTupleScheme extends TupleScheme {
      private MetadataPpdResultTupleScheme() {
      }

      public void write(TProtocol prot, MetadataPpdResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetMetadata()) {
            optionals.set(0);
         }

         if (struct.isSetIncludeBitset()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetMetadata()) {
            oprot.writeBinary(struct.metadata);
         }

         if (struct.isSetIncludeBitset()) {
            oprot.writeBinary(struct.includeBitset);
         }

      }

      public void read(TProtocol prot, MetadataPpdResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.metadata = iprot.readBinary();
            struct.setMetadataIsSet(true);
         }

         if (incoming.get(1)) {
            struct.includeBitset = iprot.readBinary();
            struct.setIncludeBitsetIsSet(true);
         }

      }
   }
}
