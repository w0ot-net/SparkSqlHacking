package org.apache.hadoop.hive.serde2.thrift.test;

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
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
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

public class MiniStruct implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("MiniStruct");
   private static final TField MY_STRING_FIELD_DESC = new TField("my_string", (byte)11, (short)1);
   private static final TField MY_ENUM_FIELD_DESC = new TField("my_enum", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new MiniStructStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new MiniStructTupleSchemeFactory();
   @Nullable
   private String my_string;
   @Nullable
   private MyEnum my_enum;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public MiniStruct() {
   }

   public MiniStruct(MiniStruct other) {
      if (other.isSetMy_string()) {
         this.my_string = other.my_string;
      }

      if (other.isSetMy_enum()) {
         this.my_enum = other.my_enum;
      }

   }

   public MiniStruct deepCopy() {
      return new MiniStruct(this);
   }

   public void clear() {
      this.my_string = null;
      this.my_enum = null;
   }

   @Nullable
   public String getMy_string() {
      return this.my_string;
   }

   public void setMy_string(@Nullable String my_string) {
      this.my_string = my_string;
   }

   public void unsetMy_string() {
      this.my_string = null;
   }

   public boolean isSetMy_string() {
      return this.my_string != null;
   }

   public void setMy_stringIsSet(boolean value) {
      if (!value) {
         this.my_string = null;
      }

   }

   @Nullable
   public MyEnum getMy_enum() {
      return this.my_enum;
   }

   public void setMy_enum(@Nullable MyEnum my_enum) {
      this.my_enum = my_enum;
   }

   public void unsetMy_enum() {
      this.my_enum = null;
   }

   public boolean isSetMy_enum() {
      return this.my_enum != null;
   }

   public void setMy_enumIsSet(boolean value) {
      if (!value) {
         this.my_enum = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case MY_STRING:
            if (value == null) {
               this.unsetMy_string();
            } else {
               this.setMy_string((String)value);
            }
            break;
         case MY_ENUM:
            if (value == null) {
               this.unsetMy_enum();
            } else {
               this.setMy_enum((MyEnum)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case MY_STRING:
            return this.getMy_string();
         case MY_ENUM:
            return this.getMy_enum();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case MY_STRING:
               return this.isSetMy_string();
            case MY_ENUM:
               return this.isSetMy_enum();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof MiniStruct ? this.equals((MiniStruct)that) : false;
   }

   public boolean equals(MiniStruct that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_my_string = this.isSetMy_string();
         boolean that_present_my_string = that.isSetMy_string();
         if (this_present_my_string || that_present_my_string) {
            if (!this_present_my_string || !that_present_my_string) {
               return false;
            }

            if (!this.my_string.equals(that.my_string)) {
               return false;
            }
         }

         boolean this_present_my_enum = this.isSetMy_enum();
         boolean that_present_my_enum = that.isSetMy_enum();
         if (this_present_my_enum || that_present_my_enum) {
            if (!this_present_my_enum || !that_present_my_enum) {
               return false;
            }

            if (!this.my_enum.equals(that.my_enum)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetMy_string() ? 131071 : 524287);
      if (this.isSetMy_string()) {
         hashCode = hashCode * 8191 + this.my_string.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMy_enum() ? 131071 : 524287);
      if (this.isSetMy_enum()) {
         hashCode = hashCode * 8191 + this.my_enum.getValue();
      }

      return hashCode;
   }

   public int compareTo(MiniStruct other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMy_string(), other.isSetMy_string());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMy_string()) {
               lastComparison = TBaseHelper.compareTo(this.my_string, other.my_string);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMy_enum(), other.isSetMy_enum());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMy_enum()) {
                  lastComparison = TBaseHelper.compareTo(this.my_enum, other.my_enum);
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
      return MiniStruct._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("MiniStruct(");
      boolean first = true;
      if (this.isSetMy_string()) {
         sb.append("my_string:");
         if (this.my_string == null) {
            sb.append("null");
         } else {
            sb.append(this.my_string);
         }

         first = false;
      }

      if (this.isSetMy_enum()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("my_enum:");
         if (this.my_enum == null) {
            sb.append("null");
         } else {
            sb.append(this.my_enum);
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
      optionals = new _Fields[]{MiniStruct._Fields.MY_STRING, MiniStruct._Fields.MY_ENUM};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(MiniStruct._Fields.MY_STRING, new FieldMetaData("my_string", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(MiniStruct._Fields.MY_ENUM, new FieldMetaData("my_enum", (byte)2, new EnumMetaData((byte)16, MyEnum.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(MiniStruct.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MY_STRING((short)1, "my_string"),
      MY_ENUM((short)2, "my_enum");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MY_STRING;
            case 2:
               return MY_ENUM;
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

   private static class MiniStructStandardSchemeFactory implements SchemeFactory {
      private MiniStructStandardSchemeFactory() {
      }

      public MiniStructStandardScheme getScheme() {
         return new MiniStructStandardScheme();
      }
   }

   private static class MiniStructStandardScheme extends StandardScheme {
      private MiniStructStandardScheme() {
      }

      public void read(TProtocol iprot, MiniStruct struct) throws TException {
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
                     struct.my_string = iprot.readString();
                     struct.setMy_stringIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.my_enum = MyEnum.findByValue(iprot.readI32());
                     struct.setMy_enumIsSet(true);
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

      public void write(TProtocol oprot, MiniStruct struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(MiniStruct.STRUCT_DESC);
         if (struct.my_string != null && struct.isSetMy_string()) {
            oprot.writeFieldBegin(MiniStruct.MY_STRING_FIELD_DESC);
            oprot.writeString(struct.my_string);
            oprot.writeFieldEnd();
         }

         if (struct.my_enum != null && struct.isSetMy_enum()) {
            oprot.writeFieldBegin(MiniStruct.MY_ENUM_FIELD_DESC);
            oprot.writeI32(struct.my_enum.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class MiniStructTupleSchemeFactory implements SchemeFactory {
      private MiniStructTupleSchemeFactory() {
      }

      public MiniStructTupleScheme getScheme() {
         return new MiniStructTupleScheme();
      }
   }

   private static class MiniStructTupleScheme extends TupleScheme {
      private MiniStructTupleScheme() {
      }

      public void write(TProtocol prot, MiniStruct struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetMy_string()) {
            optionals.set(0);
         }

         if (struct.isSetMy_enum()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetMy_string()) {
            oprot.writeString(struct.my_string);
         }

         if (struct.isSetMy_enum()) {
            oprot.writeI32(struct.my_enum.getValue());
         }

      }

      public void read(TProtocol prot, MiniStruct struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.my_string = iprot.readString();
            struct.setMy_stringIsSet(true);
         }

         if (incoming.get(1)) {
            struct.my_enum = MyEnum.findByValue(iprot.readI32());
            struct.setMy_enumIsSet(true);
         }

      }
   }
}
