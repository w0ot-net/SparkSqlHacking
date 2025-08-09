package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class IntType implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("IntType");
   private static final TField BIT_WIDTH_FIELD_DESC = new TField("bitWidth", (byte)3, (short)1);
   private static final TField IS_SIGNED_FIELD_DESC = new TField("isSigned", (byte)2, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new IntTypeStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new IntTypeTupleSchemeFactory();
   public byte bitWidth;
   public boolean isSigned;
   private static final int __BITWIDTH_ISSET_ID = 0;
   private static final int __ISSIGNED_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public IntType() {
      this.__isset_bitfield = 0;
   }

   public IntType(byte bitWidth, boolean isSigned) {
      this();
      this.bitWidth = bitWidth;
      this.setBitWidthIsSet(true);
      this.isSigned = isSigned;
      this.setIsSignedIsSet(true);
   }

   public IntType(IntType other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.bitWidth = other.bitWidth;
      this.isSigned = other.isSigned;
   }

   public IntType deepCopy() {
      return new IntType(this);
   }

   public void clear() {
      this.setBitWidthIsSet(false);
      this.bitWidth = 0;
      this.setIsSignedIsSet(false);
      this.isSigned = false;
   }

   public byte getBitWidth() {
      return this.bitWidth;
   }

   public IntType setBitWidth(byte bitWidth) {
      this.bitWidth = bitWidth;
      this.setBitWidthIsSet(true);
      return this;
   }

   public void unsetBitWidth() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetBitWidth() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setBitWidthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public boolean isIsSigned() {
      return this.isSigned;
   }

   public IntType setIsSigned(boolean isSigned) {
      this.isSigned = isSigned;
      this.setIsSignedIsSet(true);
      return this;
   }

   public void unsetIsSigned() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetIsSigned() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setIsSignedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case BIT_WIDTH:
            if (value == null) {
               this.unsetBitWidth();
            } else {
               this.setBitWidth((Byte)value);
            }
            break;
         case IS_SIGNED:
            if (value == null) {
               this.unsetIsSigned();
            } else {
               this.setIsSigned((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case BIT_WIDTH:
            return this.getBitWidth();
         case IS_SIGNED:
            return this.isIsSigned();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case BIT_WIDTH:
               return this.isSetBitWidth();
            case IS_SIGNED:
               return this.isSetIsSigned();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof IntType ? this.equals((IntType)that) : false;
   }

   public boolean equals(IntType that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_bitWidth = true;
         boolean that_present_bitWidth = true;
         if (this_present_bitWidth || that_present_bitWidth) {
            if (!this_present_bitWidth || !that_present_bitWidth) {
               return false;
            }

            if (this.bitWidth != that.bitWidth) {
               return false;
            }
         }

         boolean this_present_isSigned = true;
         boolean that_present_isSigned = true;
         if (this_present_isSigned || that_present_isSigned) {
            if (!this_present_isSigned || !that_present_isSigned) {
               return false;
            }

            if (this.isSigned != that.isSigned) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.bitWidth;
      hashCode = hashCode * 8191 + (this.isSigned ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(IntType other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetBitWidth(), other.isSetBitWidth());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetBitWidth()) {
               lastComparison = TBaseHelper.compareTo(this.bitWidth, other.bitWidth);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetIsSigned(), other.isSetIsSigned());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetIsSigned()) {
                  lastComparison = TBaseHelper.compareTo(this.isSigned, other.isSigned);
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
      return IntType._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("IntType(");
      boolean first = true;
      sb.append("bitWidth:");
      sb.append(this.bitWidth);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("isSigned:");
      sb.append(this.isSigned);
      first = false;
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(IntType._Fields.BIT_WIDTH, new FieldMetaData("bitWidth", (byte)1, new FieldValueMetaData((byte)3)));
      tmpMap.put(IntType._Fields.IS_SIGNED, new FieldMetaData("isSigned", (byte)1, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(IntType.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      BIT_WIDTH((short)1, "bitWidth"),
      IS_SIGNED((short)2, "isSigned");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return BIT_WIDTH;
            case 2:
               return IS_SIGNED;
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

   private static class IntTypeStandardSchemeFactory implements SchemeFactory {
      private IntTypeStandardSchemeFactory() {
      }

      public IntTypeStandardScheme getScheme() {
         return new IntTypeStandardScheme();
      }
   }

   private static class IntTypeStandardScheme extends StandardScheme {
      private IntTypeStandardScheme() {
      }

      public void read(TProtocol iprot, IntType struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetBitWidth()) {
                  throw new TProtocolException("Required field 'bitWidth' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetIsSigned()) {
                  throw new TProtocolException("Required field 'isSigned' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 3) {
                     struct.bitWidth = iprot.readByte();
                     struct.setBitWidthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 2) {
                     struct.isSigned = iprot.readBool();
                     struct.setIsSignedIsSet(true);
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

      public void write(TProtocol oprot, IntType struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(IntType.STRUCT_DESC);
         oprot.writeFieldBegin(IntType.BIT_WIDTH_FIELD_DESC);
         oprot.writeByte(struct.bitWidth);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(IntType.IS_SIGNED_FIELD_DESC);
         oprot.writeBool(struct.isSigned);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class IntTypeTupleSchemeFactory implements SchemeFactory {
      private IntTypeTupleSchemeFactory() {
      }

      public IntTypeTupleScheme getScheme() {
         return new IntTypeTupleScheme();
      }
   }

   private static class IntTypeTupleScheme extends TupleScheme {
      private IntTypeTupleScheme() {
      }

      public void write(TProtocol prot, IntType struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeByte(struct.bitWidth);
         oprot.writeBool(struct.isSigned);
      }

      public void read(TProtocol prot, IntType struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.bitWidth = iprot.readByte();
         struct.setBitWidthIsSet(true);
         struct.isSigned = iprot.readBool();
         struct.setIsSignedIsSet(true);
      }
   }
}
