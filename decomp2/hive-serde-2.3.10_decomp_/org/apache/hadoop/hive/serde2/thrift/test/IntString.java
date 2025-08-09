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

public class IntString implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("IntString");
   private static final TField MYINT_FIELD_DESC = new TField("myint", (byte)8, (short)1);
   private static final TField MY_STRING_FIELD_DESC = new TField("myString", (byte)11, (short)2);
   private static final TField UNDERSCORE_INT_FIELD_DESC = new TField("underscore_int", (byte)8, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new IntStringStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new IntStringTupleSchemeFactory();
   private int myint;
   @Nullable
   private String myString;
   private int underscore_int;
   private static final int __MYINT_ISSET_ID = 0;
   private static final int __UNDERSCORE_INT_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public IntString() {
      this.__isset_bitfield = 0;
   }

   public IntString(int myint, String myString, int underscore_int) {
      this();
      this.myint = myint;
      this.setMyintIsSet(true);
      this.myString = myString;
      this.underscore_int = underscore_int;
      this.setUnderscore_intIsSet(true);
   }

   public IntString(IntString other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.myint = other.myint;
      if (other.isSetMyString()) {
         this.myString = other.myString;
      }

      this.underscore_int = other.underscore_int;
   }

   public IntString deepCopy() {
      return new IntString(this);
   }

   public void clear() {
      this.setMyintIsSet(false);
      this.myint = 0;
      this.myString = null;
      this.setUnderscore_intIsSet(false);
      this.underscore_int = 0;
   }

   public int getMyint() {
      return this.myint;
   }

   public void setMyint(int myint) {
      this.myint = myint;
      this.setMyintIsSet(true);
   }

   public void unsetMyint() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetMyint() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setMyintIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getMyString() {
      return this.myString;
   }

   public void setMyString(@Nullable String myString) {
      this.myString = myString;
   }

   public void unsetMyString() {
      this.myString = null;
   }

   public boolean isSetMyString() {
      return this.myString != null;
   }

   public void setMyStringIsSet(boolean value) {
      if (!value) {
         this.myString = null;
      }

   }

   public int getUnderscore_int() {
      return this.underscore_int;
   }

   public void setUnderscore_int(int underscore_int) {
      this.underscore_int = underscore_int;
      this.setUnderscore_intIsSet(true);
   }

   public void unsetUnderscore_int() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetUnderscore_int() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setUnderscore_intIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case MYINT:
            if (value == null) {
               this.unsetMyint();
            } else {
               this.setMyint((Integer)value);
            }
            break;
         case MY_STRING:
            if (value == null) {
               this.unsetMyString();
            } else {
               this.setMyString((String)value);
            }
            break;
         case UNDERSCORE_INT:
            if (value == null) {
               this.unsetUnderscore_int();
            } else {
               this.setUnderscore_int((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case MYINT:
            return this.getMyint();
         case MY_STRING:
            return this.getMyString();
         case UNDERSCORE_INT:
            return this.getUnderscore_int();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case MYINT:
               return this.isSetMyint();
            case MY_STRING:
               return this.isSetMyString();
            case UNDERSCORE_INT:
               return this.isSetUnderscore_int();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof IntString ? this.equals((IntString)that) : false;
   }

   public boolean equals(IntString that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_myint = true;
         boolean that_present_myint = true;
         if (this_present_myint || that_present_myint) {
            if (!this_present_myint || !that_present_myint) {
               return false;
            }

            if (this.myint != that.myint) {
               return false;
            }
         }

         boolean this_present_myString = this.isSetMyString();
         boolean that_present_myString = that.isSetMyString();
         if (this_present_myString || that_present_myString) {
            if (!this_present_myString || !that_present_myString) {
               return false;
            }

            if (!this.myString.equals(that.myString)) {
               return false;
            }
         }

         boolean this_present_underscore_int = true;
         boolean that_present_underscore_int = true;
         if (this_present_underscore_int || that_present_underscore_int) {
            if (!this_present_underscore_int || !that_present_underscore_int) {
               return false;
            }

            if (this.underscore_int != that.underscore_int) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.myint;
      hashCode = hashCode * 8191 + (this.isSetMyString() ? 131071 : 524287);
      if (this.isSetMyString()) {
         hashCode = hashCode * 8191 + this.myString.hashCode();
      }

      hashCode = hashCode * 8191 + this.underscore_int;
      return hashCode;
   }

   public int compareTo(IntString other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMyint(), other.isSetMyint());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMyint()) {
               lastComparison = TBaseHelper.compareTo(this.myint, other.myint);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMyString(), other.isSetMyString());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMyString()) {
                  lastComparison = TBaseHelper.compareTo(this.myString, other.myString);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetUnderscore_int(), other.isSetUnderscore_int());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetUnderscore_int()) {
                     lastComparison = TBaseHelper.compareTo(this.underscore_int, other.underscore_int);
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
      return IntString._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("IntString(");
      boolean first = true;
      sb.append("myint:");
      sb.append(this.myint);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("myString:");
      if (this.myString == null) {
         sb.append("null");
      } else {
         sb.append(this.myString);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("underscore_int:");
      sb.append(this.underscore_int);
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
      tmpMap.put(IntString._Fields.MYINT, new FieldMetaData("myint", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(IntString._Fields.MY_STRING, new FieldMetaData("myString", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(IntString._Fields.UNDERSCORE_INT, new FieldMetaData("underscore_int", (byte)3, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(IntString.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MYINT((short)1, "myint"),
      MY_STRING((short)2, "myString"),
      UNDERSCORE_INT((short)3, "underscore_int");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MYINT;
            case 2:
               return MY_STRING;
            case 3:
               return UNDERSCORE_INT;
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

   private static class IntStringStandardSchemeFactory implements SchemeFactory {
      private IntStringStandardSchemeFactory() {
      }

      public IntStringStandardScheme getScheme() {
         return new IntStringStandardScheme();
      }
   }

   private static class IntStringStandardScheme extends StandardScheme {
      private IntStringStandardScheme() {
      }

      public void read(TProtocol iprot, IntString struct) throws TException {
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
                     struct.myint = iprot.readI32();
                     struct.setMyintIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.myString = iprot.readString();
                     struct.setMyStringIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.underscore_int = iprot.readI32();
                     struct.setUnderscore_intIsSet(true);
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

      public void write(TProtocol oprot, IntString struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(IntString.STRUCT_DESC);
         oprot.writeFieldBegin(IntString.MYINT_FIELD_DESC);
         oprot.writeI32(struct.myint);
         oprot.writeFieldEnd();
         if (struct.myString != null) {
            oprot.writeFieldBegin(IntString.MY_STRING_FIELD_DESC);
            oprot.writeString(struct.myString);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(IntString.UNDERSCORE_INT_FIELD_DESC);
         oprot.writeI32(struct.underscore_int);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class IntStringTupleSchemeFactory implements SchemeFactory {
      private IntStringTupleSchemeFactory() {
      }

      public IntStringTupleScheme getScheme() {
         return new IntStringTupleScheme();
      }
   }

   private static class IntStringTupleScheme extends TupleScheme {
      private IntStringTupleScheme() {
      }

      public void write(TProtocol prot, IntString struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetMyint()) {
            optionals.set(0);
         }

         if (struct.isSetMyString()) {
            optionals.set(1);
         }

         if (struct.isSetUnderscore_int()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetMyint()) {
            oprot.writeI32(struct.myint);
         }

         if (struct.isSetMyString()) {
            oprot.writeString(struct.myString);
         }

         if (struct.isSetUnderscore_int()) {
            oprot.writeI32(struct.underscore_int);
         }

      }

      public void read(TProtocol prot, IntString struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.myint = iprot.readI32();
            struct.setMyintIsSet(true);
         }

         if (incoming.get(1)) {
            struct.myString = iprot.readString();
            struct.setMyStringIsSet(true);
         }

         if (incoming.get(2)) {
            struct.underscore_int = iprot.readI32();
            struct.setUnderscore_intIsSet(true);
         }

      }
   }
}
