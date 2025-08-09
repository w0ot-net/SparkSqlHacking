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

public class DecimalType implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DecimalType");
   private static final TField SCALE_FIELD_DESC = new TField("scale", (byte)8, (short)1);
   private static final TField PRECISION_FIELD_DESC = new TField("precision", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DecimalTypeStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DecimalTypeTupleSchemeFactory();
   public int scale;
   public int precision;
   private static final int __SCALE_ISSET_ID = 0;
   private static final int __PRECISION_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public DecimalType() {
      this.__isset_bitfield = 0;
   }

   public DecimalType(int scale, int precision) {
      this();
      this.scale = scale;
      this.setScaleIsSet(true);
      this.precision = precision;
      this.setPrecisionIsSet(true);
   }

   public DecimalType(DecimalType other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.scale = other.scale;
      this.precision = other.precision;
   }

   public DecimalType deepCopy() {
      return new DecimalType(this);
   }

   public void clear() {
      this.setScaleIsSet(false);
      this.scale = 0;
      this.setPrecisionIsSet(false);
      this.precision = 0;
   }

   public int getScale() {
      return this.scale;
   }

   public DecimalType setScale(int scale) {
      this.scale = scale;
      this.setScaleIsSet(true);
      return this;
   }

   public void unsetScale() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetScale() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setScaleIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public int getPrecision() {
      return this.precision;
   }

   public DecimalType setPrecision(int precision) {
      this.precision = precision;
      this.setPrecisionIsSet(true);
      return this;
   }

   public void unsetPrecision() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetPrecision() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setPrecisionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SCALE:
            if (value == null) {
               this.unsetScale();
            } else {
               this.setScale((Integer)value);
            }
            break;
         case PRECISION:
            if (value == null) {
               this.unsetPrecision();
            } else {
               this.setPrecision((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SCALE:
            return this.getScale();
         case PRECISION:
            return this.getPrecision();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SCALE:
               return this.isSetScale();
            case PRECISION:
               return this.isSetPrecision();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DecimalType ? this.equals((DecimalType)that) : false;
   }

   public boolean equals(DecimalType that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_scale = true;
         boolean that_present_scale = true;
         if (this_present_scale || that_present_scale) {
            if (!this_present_scale || !that_present_scale) {
               return false;
            }

            if (this.scale != that.scale) {
               return false;
            }
         }

         boolean this_present_precision = true;
         boolean that_present_precision = true;
         if (this_present_precision || that_present_precision) {
            if (!this_present_precision || !that_present_precision) {
               return false;
            }

            if (this.precision != that.precision) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.scale;
      hashCode = hashCode * 8191 + this.precision;
      return hashCode;
   }

   public int compareTo(DecimalType other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetScale(), other.isSetScale());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetScale()) {
               lastComparison = TBaseHelper.compareTo(this.scale, other.scale);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetPrecision(), other.isSetPrecision());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetPrecision()) {
                  lastComparison = TBaseHelper.compareTo(this.precision, other.precision);
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
      return DecimalType._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DecimalType(");
      boolean first = true;
      sb.append("scale:");
      sb.append(this.scale);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("precision:");
      sb.append(this.precision);
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
      tmpMap.put(DecimalType._Fields.SCALE, new FieldMetaData("scale", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DecimalType._Fields.PRECISION, new FieldMetaData("precision", (byte)1, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DecimalType.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SCALE((short)1, "scale"),
      PRECISION((short)2, "precision");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SCALE;
            case 2:
               return PRECISION;
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

   private static class DecimalTypeStandardSchemeFactory implements SchemeFactory {
      private DecimalTypeStandardSchemeFactory() {
      }

      public DecimalTypeStandardScheme getScheme() {
         return new DecimalTypeStandardScheme();
      }
   }

   private static class DecimalTypeStandardScheme extends StandardScheme {
      private DecimalTypeStandardScheme() {
      }

      public void read(TProtocol iprot, DecimalType struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetScale()) {
                  throw new TProtocolException("Required field 'scale' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetPrecision()) {
                  throw new TProtocolException("Required field 'precision' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.scale = iprot.readI32();
                     struct.setScaleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.precision = iprot.readI32();
                     struct.setPrecisionIsSet(true);
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

      public void write(TProtocol oprot, DecimalType struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DecimalType.STRUCT_DESC);
         oprot.writeFieldBegin(DecimalType.SCALE_FIELD_DESC);
         oprot.writeI32(struct.scale);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(DecimalType.PRECISION_FIELD_DESC);
         oprot.writeI32(struct.precision);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DecimalTypeTupleSchemeFactory implements SchemeFactory {
      private DecimalTypeTupleSchemeFactory() {
      }

      public DecimalTypeTupleScheme getScheme() {
         return new DecimalTypeTupleScheme();
      }
   }

   private static class DecimalTypeTupleScheme extends TupleScheme {
      private DecimalTypeTupleScheme() {
      }

      public void write(TProtocol prot, DecimalType struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.scale);
         oprot.writeI32(struct.precision);
      }

      public void read(TProtocol prot, DecimalType struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.scale = iprot.readI32();
         struct.setScaleIsSet(true);
         struct.precision = iprot.readI32();
         struct.setPrecisionIsSet(true);
      }
   }
}
