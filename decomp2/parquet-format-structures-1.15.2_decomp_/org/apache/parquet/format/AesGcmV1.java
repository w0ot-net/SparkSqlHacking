package org.apache.parquet.format;

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
import shaded.parquet.org.apache.thrift.EncodingUtils;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class AesGcmV1 implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("AesGcmV1");
   private static final TField AAD_PREFIX_FIELD_DESC = new TField("aad_prefix", (byte)11, (short)1);
   private static final TField AAD_FILE_UNIQUE_FIELD_DESC = new TField("aad_file_unique", (byte)11, (short)2);
   private static final TField SUPPLY_AAD_PREFIX_FIELD_DESC = new TField("supply_aad_prefix", (byte)2, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new AesGcmV1StandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new AesGcmV1TupleSchemeFactory();
   @Nullable
   public ByteBuffer aad_prefix;
   @Nullable
   public ByteBuffer aad_file_unique;
   public boolean supply_aad_prefix;
   private static final int __SUPPLY_AAD_PREFIX_ISSET_ID = 0;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public AesGcmV1() {
   }

   public AesGcmV1(AesGcmV1 other) {
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetAad_prefix()) {
         this.aad_prefix = TBaseHelper.copyBinary(other.aad_prefix);
      }

      if (other.isSetAad_file_unique()) {
         this.aad_file_unique = TBaseHelper.copyBinary(other.aad_file_unique);
      }

      this.supply_aad_prefix = other.supply_aad_prefix;
   }

   public AesGcmV1 deepCopy() {
      return new AesGcmV1(this);
   }

   public void clear() {
      this.aad_prefix = null;
      this.aad_file_unique = null;
      this.setSupply_aad_prefixIsSet(false);
      this.supply_aad_prefix = false;
   }

   public byte[] getAad_prefix() {
      this.setAad_prefix(TBaseHelper.rightSize(this.aad_prefix));
      return this.aad_prefix == null ? null : this.aad_prefix.array();
   }

   public ByteBuffer bufferForAad_prefix() {
      return TBaseHelper.copyBinary(this.aad_prefix);
   }

   public AesGcmV1 setAad_prefix(byte[] aad_prefix) {
      this.aad_prefix = aad_prefix == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)aad_prefix).clone());
      return this;
   }

   public AesGcmV1 setAad_prefix(@Nullable ByteBuffer aad_prefix) {
      this.aad_prefix = TBaseHelper.copyBinary(aad_prefix);
      return this;
   }

   public void unsetAad_prefix() {
      this.aad_prefix = null;
   }

   public boolean isSetAad_prefix() {
      return this.aad_prefix != null;
   }

   public void setAad_prefixIsSet(boolean value) {
      if (!value) {
         this.aad_prefix = null;
      }

   }

   public byte[] getAad_file_unique() {
      this.setAad_file_unique(TBaseHelper.rightSize(this.aad_file_unique));
      return this.aad_file_unique == null ? null : this.aad_file_unique.array();
   }

   public ByteBuffer bufferForAad_file_unique() {
      return TBaseHelper.copyBinary(this.aad_file_unique);
   }

   public AesGcmV1 setAad_file_unique(byte[] aad_file_unique) {
      this.aad_file_unique = aad_file_unique == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)aad_file_unique).clone());
      return this;
   }

   public AesGcmV1 setAad_file_unique(@Nullable ByteBuffer aad_file_unique) {
      this.aad_file_unique = TBaseHelper.copyBinary(aad_file_unique);
      return this;
   }

   public void unsetAad_file_unique() {
      this.aad_file_unique = null;
   }

   public boolean isSetAad_file_unique() {
      return this.aad_file_unique != null;
   }

   public void setAad_file_uniqueIsSet(boolean value) {
      if (!value) {
         this.aad_file_unique = null;
      }

   }

   public boolean isSupply_aad_prefix() {
      return this.supply_aad_prefix;
   }

   public AesGcmV1 setSupply_aad_prefix(boolean supply_aad_prefix) {
      this.supply_aad_prefix = supply_aad_prefix;
      this.setSupply_aad_prefixIsSet(true);
      return this;
   }

   public void unsetSupply_aad_prefix() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetSupply_aad_prefix() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setSupply_aad_prefixIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case AAD_PREFIX:
            if (value == null) {
               this.unsetAad_prefix();
            } else if (value instanceof byte[]) {
               this.setAad_prefix((byte[])value);
            } else {
               this.setAad_prefix((ByteBuffer)value);
            }
            break;
         case AAD_FILE_UNIQUE:
            if (value == null) {
               this.unsetAad_file_unique();
            } else if (value instanceof byte[]) {
               this.setAad_file_unique((byte[])value);
            } else {
               this.setAad_file_unique((ByteBuffer)value);
            }
            break;
         case SUPPLY_AAD_PREFIX:
            if (value == null) {
               this.unsetSupply_aad_prefix();
            } else {
               this.setSupply_aad_prefix((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case AAD_PREFIX:
            return this.getAad_prefix();
         case AAD_FILE_UNIQUE:
            return this.getAad_file_unique();
         case SUPPLY_AAD_PREFIX:
            return this.isSupply_aad_prefix();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case AAD_PREFIX:
               return this.isSetAad_prefix();
            case AAD_FILE_UNIQUE:
               return this.isSetAad_file_unique();
            case SUPPLY_AAD_PREFIX:
               return this.isSetSupply_aad_prefix();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof AesGcmV1 ? this.equals((AesGcmV1)that) : false;
   }

   public boolean equals(AesGcmV1 that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_aad_prefix = this.isSetAad_prefix();
         boolean that_present_aad_prefix = that.isSetAad_prefix();
         if (this_present_aad_prefix || that_present_aad_prefix) {
            if (!this_present_aad_prefix || !that_present_aad_prefix) {
               return false;
            }

            if (!this.aad_prefix.equals(that.aad_prefix)) {
               return false;
            }
         }

         boolean this_present_aad_file_unique = this.isSetAad_file_unique();
         boolean that_present_aad_file_unique = that.isSetAad_file_unique();
         if (this_present_aad_file_unique || that_present_aad_file_unique) {
            if (!this_present_aad_file_unique || !that_present_aad_file_unique) {
               return false;
            }

            if (!this.aad_file_unique.equals(that.aad_file_unique)) {
               return false;
            }
         }

         boolean this_present_supply_aad_prefix = this.isSetSupply_aad_prefix();
         boolean that_present_supply_aad_prefix = that.isSetSupply_aad_prefix();
         if (this_present_supply_aad_prefix || that_present_supply_aad_prefix) {
            if (!this_present_supply_aad_prefix || !that_present_supply_aad_prefix) {
               return false;
            }

            if (this.supply_aad_prefix != that.supply_aad_prefix) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetAad_prefix() ? 131071 : 524287);
      if (this.isSetAad_prefix()) {
         hashCode = hashCode * 8191 + this.aad_prefix.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetAad_file_unique() ? 131071 : 524287);
      if (this.isSetAad_file_unique()) {
         hashCode = hashCode * 8191 + this.aad_file_unique.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSupply_aad_prefix() ? 131071 : 524287);
      if (this.isSetSupply_aad_prefix()) {
         hashCode = hashCode * 8191 + (this.supply_aad_prefix ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(AesGcmV1 other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetAad_prefix(), other.isSetAad_prefix());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetAad_prefix()) {
               lastComparison = TBaseHelper.compareTo((Comparable)this.aad_prefix, (Comparable)other.aad_prefix);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetAad_file_unique(), other.isSetAad_file_unique());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetAad_file_unique()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.aad_file_unique, (Comparable)other.aad_file_unique);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetSupply_aad_prefix(), other.isSetSupply_aad_prefix());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetSupply_aad_prefix()) {
                     lastComparison = TBaseHelper.compareTo(this.supply_aad_prefix, other.supply_aad_prefix);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  return 0;
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return AesGcmV1._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AesGcmV1(");
      boolean first = true;
      if (this.isSetAad_prefix()) {
         sb.append("aad_prefix:");
         if (this.aad_prefix == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.aad_prefix, sb);
         }

         first = false;
      }

      if (this.isSetAad_file_unique()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("aad_file_unique:");
         if (this.aad_file_unique == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.aad_file_unique, sb);
         }

         first = false;
      }

      if (this.isSetSupply_aad_prefix()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("supply_aad_prefix:");
         sb.append(this.supply_aad_prefix);
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
      optionals = new _Fields[]{AesGcmV1._Fields.AAD_PREFIX, AesGcmV1._Fields.AAD_FILE_UNIQUE, AesGcmV1._Fields.SUPPLY_AAD_PREFIX};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(AesGcmV1._Fields.AAD_PREFIX, new FieldMetaData("aad_prefix", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(AesGcmV1._Fields.AAD_FILE_UNIQUE, new FieldMetaData("aad_file_unique", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(AesGcmV1._Fields.SUPPLY_AAD_PREFIX, new FieldMetaData("supply_aad_prefix", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(AesGcmV1.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      AAD_PREFIX((short)1, "aad_prefix"),
      AAD_FILE_UNIQUE((short)2, "aad_file_unique"),
      SUPPLY_AAD_PREFIX((short)3, "supply_aad_prefix");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return AAD_PREFIX;
            case 2:
               return AAD_FILE_UNIQUE;
            case 3:
               return SUPPLY_AAD_PREFIX;
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

   private static class AesGcmV1StandardSchemeFactory implements SchemeFactory {
      private AesGcmV1StandardSchemeFactory() {
      }

      public AesGcmV1StandardScheme getScheme() {
         return new AesGcmV1StandardScheme();
      }
   }

   private static class AesGcmV1StandardScheme extends StandardScheme {
      private AesGcmV1StandardScheme() {
      }

      public void read(TProtocol iprot, AesGcmV1 struct) throws TException {
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
                     struct.aad_prefix = iprot.readBinary();
                     struct.setAad_prefixIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.aad_file_unique = iprot.readBinary();
                     struct.setAad_file_uniqueIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 2) {
                     struct.supply_aad_prefix = iprot.readBool();
                     struct.setSupply_aad_prefixIsSet(true);
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

      public void write(TProtocol oprot, AesGcmV1 struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(AesGcmV1.STRUCT_DESC);
         if (struct.aad_prefix != null && struct.isSetAad_prefix()) {
            oprot.writeFieldBegin(AesGcmV1.AAD_PREFIX_FIELD_DESC);
            oprot.writeBinary(struct.aad_prefix);
            oprot.writeFieldEnd();
         }

         if (struct.aad_file_unique != null && struct.isSetAad_file_unique()) {
            oprot.writeFieldBegin(AesGcmV1.AAD_FILE_UNIQUE_FIELD_DESC);
            oprot.writeBinary(struct.aad_file_unique);
            oprot.writeFieldEnd();
         }

         if (struct.isSetSupply_aad_prefix()) {
            oprot.writeFieldBegin(AesGcmV1.SUPPLY_AAD_PREFIX_FIELD_DESC);
            oprot.writeBool(struct.supply_aad_prefix);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class AesGcmV1TupleSchemeFactory implements SchemeFactory {
      private AesGcmV1TupleSchemeFactory() {
      }

      public AesGcmV1TupleScheme getScheme() {
         return new AesGcmV1TupleScheme();
      }
   }

   private static class AesGcmV1TupleScheme extends TupleScheme {
      private AesGcmV1TupleScheme() {
      }

      public void write(TProtocol prot, AesGcmV1 struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetAad_prefix()) {
            optionals.set(0);
         }

         if (struct.isSetAad_file_unique()) {
            optionals.set(1);
         }

         if (struct.isSetSupply_aad_prefix()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetAad_prefix()) {
            oprot.writeBinary(struct.aad_prefix);
         }

         if (struct.isSetAad_file_unique()) {
            oprot.writeBinary(struct.aad_file_unique);
         }

         if (struct.isSetSupply_aad_prefix()) {
            oprot.writeBool(struct.supply_aad_prefix);
         }

      }

      public void read(TProtocol prot, AesGcmV1 struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.aad_prefix = iprot.readBinary();
            struct.setAad_prefixIsSet(true);
         }

         if (incoming.get(1)) {
            struct.aad_file_unique = iprot.readBinary();
            struct.setAad_file_uniqueIsSet(true);
         }

         if (incoming.get(2)) {
            struct.supply_aad_prefix = iprot.readBool();
            struct.setSupply_aad_prefixIsSet(true);
         }

      }
   }
}
