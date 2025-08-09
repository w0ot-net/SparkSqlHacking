package org.apache.hadoop.hive.serde.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class ThriftTestObj implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ThriftTestObj");
   private static final TField FIELD1_FIELD_DESC = new TField("field1", (byte)8, (short)1);
   private static final TField FIELD2_FIELD_DESC = new TField("field2", (byte)11, (short)2);
   private static final TField FIELD3_FIELD_DESC = new TField("field3", (byte)15, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ThriftTestObjStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ThriftTestObjTupleSchemeFactory();
   private int field1;
   @Nullable
   private String field2;
   @Nullable
   private List field3;
   private static final int __FIELD1_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public ThriftTestObj() {
      this.__isset_bitfield = 0;
   }

   public ThriftTestObj(int field1, String field2, List field3) {
      this();
      this.field1 = field1;
      this.setField1IsSet(true);
      this.field2 = field2;
      this.field3 = field3;
   }

   public ThriftTestObj(ThriftTestObj other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.field1 = other.field1;
      if (other.isSetField2()) {
         this.field2 = other.field2;
      }

      if (other.isSetField3()) {
         List<InnerStruct> __this__field3 = new ArrayList(other.field3.size());

         for(InnerStruct other_element : other.field3) {
            __this__field3.add(new InnerStruct(other_element));
         }

         this.field3 = __this__field3;
      }

   }

   public ThriftTestObj deepCopy() {
      return new ThriftTestObj(this);
   }

   public void clear() {
      this.setField1IsSet(false);
      this.field1 = 0;
      this.field2 = null;
      this.field3 = null;
   }

   public int getField1() {
      return this.field1;
   }

   public void setField1(int field1) {
      this.field1 = field1;
      this.setField1IsSet(true);
   }

   public void unsetField1() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetField1() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setField1IsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getField2() {
      return this.field2;
   }

   public void setField2(@Nullable String field2) {
      this.field2 = field2;
   }

   public void unsetField2() {
      this.field2 = null;
   }

   public boolean isSetField2() {
      return this.field2 != null;
   }

   public void setField2IsSet(boolean value) {
      if (!value) {
         this.field2 = null;
      }

   }

   public int getField3Size() {
      return this.field3 == null ? 0 : this.field3.size();
   }

   @Nullable
   public Iterator getField3Iterator() {
      return this.field3 == null ? null : this.field3.iterator();
   }

   public void addToField3(InnerStruct elem) {
      if (this.field3 == null) {
         this.field3 = new ArrayList();
      }

      this.field3.add(elem);
   }

   @Nullable
   public List getField3() {
      return this.field3;
   }

   public void setField3(@Nullable List field3) {
      this.field3 = field3;
   }

   public void unsetField3() {
      this.field3 = null;
   }

   public boolean isSetField3() {
      return this.field3 != null;
   }

   public void setField3IsSet(boolean value) {
      if (!value) {
         this.field3 = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FIELD1:
            if (value == null) {
               this.unsetField1();
            } else {
               this.setField1((Integer)value);
            }
            break;
         case FIELD2:
            if (value == null) {
               this.unsetField2();
            } else {
               this.setField2((String)value);
            }
            break;
         case FIELD3:
            if (value == null) {
               this.unsetField3();
            } else {
               this.setField3((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FIELD1:
            return this.getField1();
         case FIELD2:
            return this.getField2();
         case FIELD3:
            return this.getField3();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FIELD1:
               return this.isSetField1();
            case FIELD2:
               return this.isSetField2();
            case FIELD3:
               return this.isSetField3();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ThriftTestObj ? this.equals((ThriftTestObj)that) : false;
   }

   public boolean equals(ThriftTestObj that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_field1 = true;
         boolean that_present_field1 = true;
         if (this_present_field1 || that_present_field1) {
            if (!this_present_field1 || !that_present_field1) {
               return false;
            }

            if (this.field1 != that.field1) {
               return false;
            }
         }

         boolean this_present_field2 = this.isSetField2();
         boolean that_present_field2 = that.isSetField2();
         if (this_present_field2 || that_present_field2) {
            if (!this_present_field2 || !that_present_field2) {
               return false;
            }

            if (!this.field2.equals(that.field2)) {
               return false;
            }
         }

         boolean this_present_field3 = this.isSetField3();
         boolean that_present_field3 = that.isSetField3();
         if (this_present_field3 || that_present_field3) {
            if (!this_present_field3 || !that_present_field3) {
               return false;
            }

            if (!this.field3.equals(that.field3)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.field1;
      hashCode = hashCode * 8191 + (this.isSetField2() ? 131071 : 524287);
      if (this.isSetField2()) {
         hashCode = hashCode * 8191 + this.field2.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetField3() ? 131071 : 524287);
      if (this.isSetField3()) {
         hashCode = hashCode * 8191 + this.field3.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ThriftTestObj other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetField1(), other.isSetField1());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetField1()) {
               lastComparison = TBaseHelper.compareTo(this.field1, other.field1);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetField2(), other.isSetField2());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetField2()) {
                  lastComparison = TBaseHelper.compareTo(this.field2, other.field2);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetField3(), other.isSetField3());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetField3()) {
                     lastComparison = TBaseHelper.compareTo(this.field3, other.field3);
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
      return ThriftTestObj._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ThriftTestObj(");
      boolean first = true;
      sb.append("field1:");
      sb.append(this.field1);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("field2:");
      if (this.field2 == null) {
         sb.append("null");
      } else {
         sb.append(this.field2);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("field3:");
      if (this.field3 == null) {
         sb.append("null");
      } else {
         sb.append(this.field3);
      }

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
      tmpMap.put(ThriftTestObj._Fields.FIELD1, new FieldMetaData("field1", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(ThriftTestObj._Fields.FIELD2, new FieldMetaData("field2", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(ThriftTestObj._Fields.FIELD3, new FieldMetaData("field3", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, InnerStruct.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ThriftTestObj.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FIELD1((short)1, "field1"),
      FIELD2((short)2, "field2"),
      FIELD3((short)3, "field3");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FIELD1;
            case 2:
               return FIELD2;
            case 3:
               return FIELD3;
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

   private static class ThriftTestObjStandardSchemeFactory implements SchemeFactory {
      private ThriftTestObjStandardSchemeFactory() {
      }

      public ThriftTestObjStandardScheme getScheme() {
         return new ThriftTestObjStandardScheme();
      }
   }

   private static class ThriftTestObjStandardScheme extends StandardScheme {
      private ThriftTestObjStandardScheme() {
      }

      public void read(TProtocol iprot, ThriftTestObj struct) throws TException {
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
                     struct.field1 = iprot.readI32();
                     struct.setField1IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.field2 = iprot.readString();
                     struct.setField2IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list0 = iprot.readListBegin();
                  struct.field3 = new ArrayList(_list0.size);

                  for(int _i2 = 0; _i2 < _list0.size; ++_i2) {
                     InnerStruct _elem1 = new InnerStruct();
                     _elem1.read(iprot);
                     struct.field3.add(_elem1);
                  }

                  iprot.readListEnd();
                  struct.setField3IsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ThriftTestObj struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ThriftTestObj.STRUCT_DESC);
         oprot.writeFieldBegin(ThriftTestObj.FIELD1_FIELD_DESC);
         oprot.writeI32(struct.field1);
         oprot.writeFieldEnd();
         if (struct.field2 != null) {
            oprot.writeFieldBegin(ThriftTestObj.FIELD2_FIELD_DESC);
            oprot.writeString(struct.field2);
            oprot.writeFieldEnd();
         }

         if (struct.field3 != null) {
            oprot.writeFieldBegin(ThriftTestObj.FIELD3_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.field3.size()));

            for(InnerStruct _iter3 : struct.field3) {
               _iter3.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ThriftTestObjTupleSchemeFactory implements SchemeFactory {
      private ThriftTestObjTupleSchemeFactory() {
      }

      public ThriftTestObjTupleScheme getScheme() {
         return new ThriftTestObjTupleScheme();
      }
   }

   private static class ThriftTestObjTupleScheme extends TupleScheme {
      private ThriftTestObjTupleScheme() {
      }

      public void write(TProtocol prot, ThriftTestObj struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetField1()) {
            optionals.set(0);
         }

         if (struct.isSetField2()) {
            optionals.set(1);
         }

         if (struct.isSetField3()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetField1()) {
            oprot.writeI32(struct.field1);
         }

         if (struct.isSetField2()) {
            oprot.writeString(struct.field2);
         }

         if (struct.isSetField3()) {
            oprot.writeI32(struct.field3.size());

            for(InnerStruct _iter4 : struct.field3) {
               _iter4.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, ThriftTestObj struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.field1 = iprot.readI32();
            struct.setField1IsSet(true);
         }

         if (incoming.get(1)) {
            struct.field2 = iprot.readString();
            struct.setField2IsSet(true);
         }

         if (incoming.get(2)) {
            TList _list5 = iprot.readListBegin((byte)12);
            struct.field3 = new ArrayList(_list5.size);

            for(int _i7 = 0; _i7 < _list5.size; ++_i7) {
               InnerStruct _elem6 = new InnerStruct();
               _elem6.read(iprot);
               struct.field3.add(_elem6);
            }

            struct.setField3IsSet(true);
         }

      }
   }
}
