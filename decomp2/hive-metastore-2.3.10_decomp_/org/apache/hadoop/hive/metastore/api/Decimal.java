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
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class Decimal implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Decimal");
   private static final TField UNSCALED_FIELD_DESC = new TField("unscaled", (byte)11, (short)1);
   private static final TField SCALE_FIELD_DESC = new TField("scale", (byte)6, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DecimalStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DecimalTupleSchemeFactory();
   @Nullable
   private ByteBuffer unscaled;
   private short scale;
   private static final int __SCALE_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public Decimal() {
      this.__isset_bitfield = 0;
   }

   public Decimal(ByteBuffer unscaled, short scale) {
      this();
      this.unscaled = TBaseHelper.copyBinary(unscaled);
      this.scale = scale;
      this.setScaleIsSet(true);
   }

   public Decimal(Decimal other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetUnscaled()) {
         this.unscaled = TBaseHelper.copyBinary(other.unscaled);
      }

      this.scale = other.scale;
   }

   public Decimal deepCopy() {
      return new Decimal(this);
   }

   public void clear() {
      this.unscaled = null;
      this.setScaleIsSet(false);
      this.scale = 0;
   }

   public byte[] getUnscaled() {
      this.setUnscaled(TBaseHelper.rightSize(this.unscaled));
      return this.unscaled == null ? null : this.unscaled.array();
   }

   public ByteBuffer bufferForUnscaled() {
      return TBaseHelper.copyBinary(this.unscaled);
   }

   public void setUnscaled(byte[] unscaled) {
      this.unscaled = unscaled == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)unscaled).clone());
   }

   public void setUnscaled(@Nullable ByteBuffer unscaled) {
      this.unscaled = TBaseHelper.copyBinary(unscaled);
   }

   public void unsetUnscaled() {
      this.unscaled = null;
   }

   public boolean isSetUnscaled() {
      return this.unscaled != null;
   }

   public void setUnscaledIsSet(boolean value) {
      if (!value) {
         this.unscaled = null;
      }

   }

   public short getScale() {
      return this.scale;
   }

   public void setScale(short scale) {
      this.scale = scale;
      this.setScaleIsSet(true);
   }

   public void unsetScale() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetScale() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setScaleIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case UNSCALED:
            if (value == null) {
               this.unsetUnscaled();
            } else if (value instanceof byte[]) {
               this.setUnscaled((byte[])value);
            } else {
               this.setUnscaled((ByteBuffer)value);
            }
            break;
         case SCALE:
            if (value == null) {
               this.unsetScale();
            } else {
               this.setScale((Short)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case UNSCALED:
            return this.getUnscaled();
         case SCALE:
            return this.getScale();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case UNSCALED:
               return this.isSetUnscaled();
            case SCALE:
               return this.isSetScale();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Decimal ? this.equals((Decimal)that) : false;
   }

   public boolean equals(Decimal that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_unscaled = this.isSetUnscaled();
         boolean that_present_unscaled = that.isSetUnscaled();
         if (this_present_unscaled || that_present_unscaled) {
            if (!this_present_unscaled || !that_present_unscaled) {
               return false;
            }

            if (!this.unscaled.equals(that.unscaled)) {
               return false;
            }
         }

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

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetUnscaled() ? 131071 : 524287);
      if (this.isSetUnscaled()) {
         hashCode = hashCode * 8191 + this.unscaled.hashCode();
      }

      hashCode = hashCode * 8191 + this.scale;
      return hashCode;
   }

   public int compareTo(Decimal other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetUnscaled(), other.isSetUnscaled());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetUnscaled()) {
               lastComparison = TBaseHelper.compareTo(this.unscaled, other.unscaled);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

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

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Decimal._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Decimal(");
      boolean first = true;
      sb.append("unscaled:");
      if (this.unscaled == null) {
         sb.append("null");
      } else {
         TBaseHelper.toString(this.unscaled, sb);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("scale:");
      sb.append(this.scale);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetUnscaled()) {
         throw new TProtocolException("Required field 'unscaled' is unset! Struct:" + this.toString());
      } else if (!this.isSetScale()) {
         throw new TProtocolException("Required field 'scale' is unset! Struct:" + this.toString());
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
      tmpMap.put(Decimal._Fields.UNSCALED, new FieldMetaData("unscaled", (byte)1, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(Decimal._Fields.SCALE, new FieldMetaData("scale", (byte)1, new FieldValueMetaData((byte)6)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Decimal.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      UNSCALED((short)1, "unscaled"),
      SCALE((short)3, "scale");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return UNSCALED;
            case 3:
               return SCALE;
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

   private static class DecimalStandardSchemeFactory implements SchemeFactory {
      private DecimalStandardSchemeFactory() {
      }

      public DecimalStandardScheme getScheme() {
         return new DecimalStandardScheme();
      }
   }

   private static class DecimalStandardScheme extends StandardScheme {
      private DecimalStandardScheme() {
      }

      public void read(TProtocol iprot, Decimal struct) throws TException {
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
                     struct.unscaled = iprot.readBinary();
                     struct.setUnscaledIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 6) {
                     struct.scale = iprot.readI16();
                     struct.setScaleIsSet(true);
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

      public void write(TProtocol oprot, Decimal struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Decimal.STRUCT_DESC);
         if (struct.unscaled != null) {
            oprot.writeFieldBegin(Decimal.UNSCALED_FIELD_DESC);
            oprot.writeBinary(struct.unscaled);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Decimal.SCALE_FIELD_DESC);
         oprot.writeI16(struct.scale);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DecimalTupleSchemeFactory implements SchemeFactory {
      private DecimalTupleSchemeFactory() {
      }

      public DecimalTupleScheme getScheme() {
         return new DecimalTupleScheme();
      }
   }

   private static class DecimalTupleScheme extends TupleScheme {
      private DecimalTupleScheme() {
      }

      public void write(TProtocol prot, Decimal struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBinary(struct.unscaled);
         oprot.writeI16(struct.scale);
      }

      public void read(TProtocol prot, Decimal struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.unscaled = iprot.readBinary();
         struct.setUnscaledIsSet(true);
         struct.scale = iprot.readI16();
         struct.setScaleIsSet(true);
      }
   }
}
