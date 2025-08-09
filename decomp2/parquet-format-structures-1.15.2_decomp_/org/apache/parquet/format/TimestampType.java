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
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
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

public class TimestampType implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TimestampType");
   private static final TField IS_ADJUSTED_TO_UTC_FIELD_DESC = new TField("isAdjustedToUTC", (byte)2, (short)1);
   private static final TField UNIT_FIELD_DESC = new TField("unit", (byte)12, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TimestampTypeStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TimestampTypeTupleSchemeFactory();
   public boolean isAdjustedToUTC;
   @Nullable
   public TimeUnit unit;
   private static final int __ISADJUSTEDTOUTC_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public TimestampType() {
      this.__isset_bitfield = 0;
   }

   public TimestampType(boolean isAdjustedToUTC, TimeUnit unit) {
      this();
      this.isAdjustedToUTC = isAdjustedToUTC;
      this.setIsAdjustedToUTCIsSet(true);
      this.unit = unit;
   }

   public TimestampType(TimestampType other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.isAdjustedToUTC = other.isAdjustedToUTC;
      if (other.isSetUnit()) {
         this.unit = new TimeUnit(other.unit);
      }

   }

   public TimestampType deepCopy() {
      return new TimestampType(this);
   }

   public void clear() {
      this.setIsAdjustedToUTCIsSet(false);
      this.isAdjustedToUTC = false;
      this.unit = null;
   }

   public boolean isIsAdjustedToUTC() {
      return this.isAdjustedToUTC;
   }

   public TimestampType setIsAdjustedToUTC(boolean isAdjustedToUTC) {
      this.isAdjustedToUTC = isAdjustedToUTC;
      this.setIsAdjustedToUTCIsSet(true);
      return this;
   }

   public void unsetIsAdjustedToUTC() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetIsAdjustedToUTC() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setIsAdjustedToUTCIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   @Nullable
   public TimeUnit getUnit() {
      return this.unit;
   }

   public TimestampType setUnit(@Nullable TimeUnit unit) {
      this.unit = unit;
      return this;
   }

   public void unsetUnit() {
      this.unit = null;
   }

   public boolean isSetUnit() {
      return this.unit != null;
   }

   public void setUnitIsSet(boolean value) {
      if (!value) {
         this.unit = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case IS_ADJUSTED_TO_UTC:
            if (value == null) {
               this.unsetIsAdjustedToUTC();
            } else {
               this.setIsAdjustedToUTC((Boolean)value);
            }
            break;
         case UNIT:
            if (value == null) {
               this.unsetUnit();
            } else {
               this.setUnit((TimeUnit)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case IS_ADJUSTED_TO_UTC:
            return this.isIsAdjustedToUTC();
         case UNIT:
            return this.getUnit();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case IS_ADJUSTED_TO_UTC:
               return this.isSetIsAdjustedToUTC();
            case UNIT:
               return this.isSetUnit();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TimestampType ? this.equals((TimestampType)that) : false;
   }

   public boolean equals(TimestampType that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_isAdjustedToUTC = true;
         boolean that_present_isAdjustedToUTC = true;
         if (this_present_isAdjustedToUTC || that_present_isAdjustedToUTC) {
            if (!this_present_isAdjustedToUTC || !that_present_isAdjustedToUTC) {
               return false;
            }

            if (this.isAdjustedToUTC != that.isAdjustedToUTC) {
               return false;
            }
         }

         boolean this_present_unit = this.isSetUnit();
         boolean that_present_unit = that.isSetUnit();
         if (this_present_unit || that_present_unit) {
            if (!this_present_unit || !that_present_unit) {
               return false;
            }

            if (!this.unit.equals(that.unit)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isAdjustedToUTC ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.isSetUnit() ? 131071 : 524287);
      if (this.isSetUnit()) {
         hashCode = hashCode * 8191 + this.unit.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TimestampType other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetIsAdjustedToUTC(), other.isSetIsAdjustedToUTC());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetIsAdjustedToUTC()) {
               lastComparison = TBaseHelper.compareTo(this.isAdjustedToUTC, other.isAdjustedToUTC);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetUnit(), other.isSetUnit());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetUnit()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.unit, (Comparable)other.unit);
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
      return TimestampType._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TimestampType(");
      boolean first = true;
      sb.append("isAdjustedToUTC:");
      sb.append(this.isAdjustedToUTC);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("unit:");
      if (this.unit == null) {
         sb.append("null");
      } else {
         sb.append(this.unit);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.unit == null) {
         throw new TProtocolException("Required field 'unit' was not present! Struct: " + this.toString());
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
      tmpMap.put(TimestampType._Fields.IS_ADJUSTED_TO_UTC, new FieldMetaData("isAdjustedToUTC", (byte)1, new FieldValueMetaData((byte)2)));
      tmpMap.put(TimestampType._Fields.UNIT, new FieldMetaData("unit", (byte)1, new StructMetaData((byte)12, TimeUnit.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TimestampType.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      IS_ADJUSTED_TO_UTC((short)1, "isAdjustedToUTC"),
      UNIT((short)2, "unit");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return IS_ADJUSTED_TO_UTC;
            case 2:
               return UNIT;
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

   private static class TimestampTypeStandardSchemeFactory implements SchemeFactory {
      private TimestampTypeStandardSchemeFactory() {
      }

      public TimestampTypeStandardScheme getScheme() {
         return new TimestampTypeStandardScheme();
      }
   }

   private static class TimestampTypeStandardScheme extends StandardScheme {
      private TimestampTypeStandardScheme() {
      }

      public void read(TProtocol iprot, TimestampType struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetIsAdjustedToUTC()) {
                  throw new TProtocolException("Required field 'isAdjustedToUTC' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 2) {
                     struct.isAdjustedToUTC = iprot.readBool();
                     struct.setIsAdjustedToUTCIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.unit = new TimeUnit();
                     struct.unit.read(iprot);
                     struct.setUnitIsSet(true);
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

      public void write(TProtocol oprot, TimestampType struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TimestampType.STRUCT_DESC);
         oprot.writeFieldBegin(TimestampType.IS_ADJUSTED_TO_UTC_FIELD_DESC);
         oprot.writeBool(struct.isAdjustedToUTC);
         oprot.writeFieldEnd();
         if (struct.unit != null) {
            oprot.writeFieldBegin(TimestampType.UNIT_FIELD_DESC);
            struct.unit.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TimestampTypeTupleSchemeFactory implements SchemeFactory {
      private TimestampTypeTupleSchemeFactory() {
      }

      public TimestampTypeTupleScheme getScheme() {
         return new TimestampTypeTupleScheme();
      }
   }

   private static class TimestampTypeTupleScheme extends TupleScheme {
      private TimestampTypeTupleScheme() {
      }

      public void write(TProtocol prot, TimestampType struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBool(struct.isAdjustedToUTC);
         struct.unit.write(oprot);
      }

      public void read(TProtocol prot, TimestampType struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.isAdjustedToUTC = iprot.readBool();
         struct.setIsAdjustedToUTCIsSet(true);
         struct.unit = new TimeUnit();
         struct.unit.read(iprot);
         struct.setUnitIsSet(true);
      }
   }
}
