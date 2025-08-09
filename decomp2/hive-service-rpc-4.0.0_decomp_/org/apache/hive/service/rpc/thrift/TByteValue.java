package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TByteValue implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TByteValue");
   private static final TField VALUE_FIELD_DESC = new TField("value", (byte)3, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TByteValueStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TByteValueTupleSchemeFactory();
   private byte value;
   private static final int __VALUE_ISSET_ID = 0;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TByteValue() {
   }

   public TByteValue(TByteValue other) {
      this.__isset_bitfield = other.__isset_bitfield;
      this.value = other.value;
   }

   public TByteValue deepCopy() {
      return new TByteValue(this);
   }

   public void clear() {
      this.setValueIsSet(false);
      this.value = 0;
   }

   public byte getValue() {
      return this.value;
   }

   public void setValue(byte value) {
      this.value = value;
      this.setValueIsSet(true);
   }

   public void unsetValue() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetValue() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setValueIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case VALUE:
            if (value == null) {
               this.unsetValue();
            } else {
               this.setValue((Byte)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case VALUE:
            return this.getValue();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case VALUE:
               return this.isSetValue();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TByteValue ? this.equals((TByteValue)that) : false;
   }

   public boolean equals(TByteValue that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_value = this.isSetValue();
         boolean that_present_value = that.isSetValue();
         if (this_present_value || that_present_value) {
            if (!this_present_value || !that_present_value) {
               return false;
            }

            if (this.value != that.value) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetValue() ? 131071 : 524287);
      if (this.isSetValue()) {
         hashCode = hashCode * 8191 + this.value;
      }

      return hashCode;
   }

   public int compareTo(TByteValue other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetValue(), other.isSetValue());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetValue()) {
               lastComparison = TBaseHelper.compareTo(this.value, other.value);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TByteValue._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TByteValue(");
      boolean first = true;
      if (this.isSetValue()) {
         sb.append("value:");
         sb.append(this.value);
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
      optionals = new _Fields[]{TByteValue._Fields.VALUE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TByteValue._Fields.VALUE, new FieldMetaData("value", (byte)2, new FieldValueMetaData((byte)3)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TByteValue.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VALUE((short)1, "value");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VALUE;
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

   private static class TByteValueStandardSchemeFactory implements SchemeFactory {
      private TByteValueStandardSchemeFactory() {
      }

      public TByteValueStandardScheme getScheme() {
         return new TByteValueStandardScheme();
      }
   }

   private static class TByteValueStandardScheme extends StandardScheme {
      private TByteValueStandardScheme() {
      }

      public void read(TProtocol iprot, TByteValue struct) throws TException {
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
                  if (schemeField.type == 3) {
                     struct.value = iprot.readByte();
                     struct.setValueIsSet(true);
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

      public void write(TProtocol oprot, TByteValue struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TByteValue.STRUCT_DESC);
         if (struct.isSetValue()) {
            oprot.writeFieldBegin(TByteValue.VALUE_FIELD_DESC);
            oprot.writeByte(struct.value);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TByteValueTupleSchemeFactory implements SchemeFactory {
      private TByteValueTupleSchemeFactory() {
      }

      public TByteValueTupleScheme getScheme() {
         return new TByteValueTupleScheme();
      }
   }

   private static class TByteValueTupleScheme extends TupleScheme {
      private TByteValueTupleScheme() {
      }

      public void write(TProtocol prot, TByteValue struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetValue()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetValue()) {
            oprot.writeByte(struct.value);
         }

      }

      public void read(TProtocol prot, TByteValue struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.value = iprot.readByte();
            struct.setValueIsSet(true);
         }

      }
   }
}
