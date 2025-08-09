package org.apache.hadoop.hive.serde.test;

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

public class InnerStruct implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("InnerStruct");
   private static final TField FIELD0_FIELD_DESC = new TField("field0", (byte)8, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new InnerStructStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new InnerStructTupleSchemeFactory();
   private int field0;
   private static final int __FIELD0_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public InnerStruct() {
      this.__isset_bitfield = 0;
   }

   public InnerStruct(int field0) {
      this();
      this.field0 = field0;
      this.setField0IsSet(true);
   }

   public InnerStruct(InnerStruct other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.field0 = other.field0;
   }

   public InnerStruct deepCopy() {
      return new InnerStruct(this);
   }

   public void clear() {
      this.setField0IsSet(false);
      this.field0 = 0;
   }

   public int getField0() {
      return this.field0;
   }

   public void setField0(int field0) {
      this.field0 = field0;
      this.setField0IsSet(true);
   }

   public void unsetField0() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetField0() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setField0IsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FIELD0:
            if (value == null) {
               this.unsetField0();
            } else {
               this.setField0((Integer)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FIELD0:
            return this.getField0();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FIELD0:
               return this.isSetField0();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof InnerStruct ? this.equals((InnerStruct)that) : false;
   }

   public boolean equals(InnerStruct that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_field0 = true;
         boolean that_present_field0 = true;
         if (this_present_field0 || that_present_field0) {
            if (!this_present_field0 || !that_present_field0) {
               return false;
            }

            if (this.field0 != that.field0) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.field0;
      return hashCode;
   }

   public int compareTo(InnerStruct other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetField0(), other.isSetField0());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetField0()) {
               lastComparison = TBaseHelper.compareTo(this.field0, other.field0);
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
      return InnerStruct._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("InnerStruct(");
      boolean first = true;
      sb.append("field0:");
      sb.append(this.field0);
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
      tmpMap.put(InnerStruct._Fields.FIELD0, new FieldMetaData("field0", (byte)3, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(InnerStruct.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FIELD0((short)1, "field0");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FIELD0;
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

   private static class InnerStructStandardSchemeFactory implements SchemeFactory {
      private InnerStructStandardSchemeFactory() {
      }

      public InnerStructStandardScheme getScheme() {
         return new InnerStructStandardScheme();
      }
   }

   private static class InnerStructStandardScheme extends StandardScheme {
      private InnerStructStandardScheme() {
      }

      public void read(TProtocol iprot, InnerStruct struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.field0 = iprot.readI32();
                     struct.setField0IsSet(true);
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

      public void write(TProtocol oprot, InnerStruct struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(InnerStruct.STRUCT_DESC);
         oprot.writeFieldBegin(InnerStruct.FIELD0_FIELD_DESC);
         oprot.writeI32(struct.field0);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class InnerStructTupleSchemeFactory implements SchemeFactory {
      private InnerStructTupleSchemeFactory() {
      }

      public InnerStructTupleScheme getScheme() {
         return new InnerStructTupleScheme();
      }
   }

   private static class InnerStructTupleScheme extends TupleScheme {
      private InnerStructTupleScheme() {
      }

      public void write(TProtocol prot, InnerStruct struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetField0()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetField0()) {
            oprot.writeI32(struct.field0);
         }

      }

      public void read(TProtocol prot, InnerStruct struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.field0 = iprot.readI32();
            struct.setField0IsSet(true);
         }

      }
   }
}
