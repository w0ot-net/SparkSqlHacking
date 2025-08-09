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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class SetIntString implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SetIntString");
   private static final TField S_INT_STRING_FIELD_DESC = new TField("sIntString", (byte)14, (short)1);
   private static final TField A_STRING_FIELD_DESC = new TField("aString", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SetIntStringStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SetIntStringTupleSchemeFactory();
   @Nullable
   private Set sIntString;
   @Nullable
   private String aString;
   public static final Map metaDataMap;

   public SetIntString() {
   }

   public SetIntString(Set sIntString, String aString) {
      this();
      this.sIntString = sIntString;
      this.aString = aString;
   }

   public SetIntString(SetIntString other) {
      if (other.isSetSIntString()) {
         Set<IntString> __this__sIntString = new HashSet(other.sIntString.size());

         for(IntString other_element : other.sIntString) {
            __this__sIntString.add(new IntString(other_element));
         }

         this.sIntString = __this__sIntString;
      }

      if (other.isSetAString()) {
         this.aString = other.aString;
      }

   }

   public SetIntString deepCopy() {
      return new SetIntString(this);
   }

   public void clear() {
      this.sIntString = null;
      this.aString = null;
   }

   public int getSIntStringSize() {
      return this.sIntString == null ? 0 : this.sIntString.size();
   }

   @Nullable
   public Iterator getSIntStringIterator() {
      return this.sIntString == null ? null : this.sIntString.iterator();
   }

   public void addToSIntString(IntString elem) {
      if (this.sIntString == null) {
         this.sIntString = new HashSet();
      }

      this.sIntString.add(elem);
   }

   @Nullable
   public Set getSIntString() {
      return this.sIntString;
   }

   public void setSIntString(@Nullable Set sIntString) {
      this.sIntString = sIntString;
   }

   public void unsetSIntString() {
      this.sIntString = null;
   }

   public boolean isSetSIntString() {
      return this.sIntString != null;
   }

   public void setSIntStringIsSet(boolean value) {
      if (!value) {
         this.sIntString = null;
      }

   }

   @Nullable
   public String getAString() {
      return this.aString;
   }

   public void setAString(@Nullable String aString) {
      this.aString = aString;
   }

   public void unsetAString() {
      this.aString = null;
   }

   public boolean isSetAString() {
      return this.aString != null;
   }

   public void setAStringIsSet(boolean value) {
      if (!value) {
         this.aString = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case S_INT_STRING:
            if (value == null) {
               this.unsetSIntString();
            } else {
               this.setSIntString((Set)value);
            }
            break;
         case A_STRING:
            if (value == null) {
               this.unsetAString();
            } else {
               this.setAString((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case S_INT_STRING:
            return this.getSIntString();
         case A_STRING:
            return this.getAString();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case S_INT_STRING:
               return this.isSetSIntString();
            case A_STRING:
               return this.isSetAString();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SetIntString ? this.equals((SetIntString)that) : false;
   }

   public boolean equals(SetIntString that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_sIntString = this.isSetSIntString();
         boolean that_present_sIntString = that.isSetSIntString();
         if (this_present_sIntString || that_present_sIntString) {
            if (!this_present_sIntString || !that_present_sIntString) {
               return false;
            }

            if (!this.sIntString.equals(that.sIntString)) {
               return false;
            }
         }

         boolean this_present_aString = this.isSetAString();
         boolean that_present_aString = that.isSetAString();
         if (this_present_aString || that_present_aString) {
            if (!this_present_aString || !that_present_aString) {
               return false;
            }

            if (!this.aString.equals(that.aString)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetSIntString() ? 131071 : 524287);
      if (this.isSetSIntString()) {
         hashCode = hashCode * 8191 + this.sIntString.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetAString() ? 131071 : 524287);
      if (this.isSetAString()) {
         hashCode = hashCode * 8191 + this.aString.hashCode();
      }

      return hashCode;
   }

   public int compareTo(SetIntString other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSIntString(), other.isSetSIntString());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSIntString()) {
               lastComparison = TBaseHelper.compareTo(this.sIntString, other.sIntString);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetAString(), other.isSetAString());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetAString()) {
                  lastComparison = TBaseHelper.compareTo(this.aString, other.aString);
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
      return SetIntString._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SetIntString(");
      boolean first = true;
      sb.append("sIntString:");
      if (this.sIntString == null) {
         sb.append("null");
      } else {
         sb.append(this.sIntString);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("aString:");
      if (this.aString == null) {
         sb.append("null");
      } else {
         sb.append(this.aString);
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
      tmpMap.put(SetIntString._Fields.S_INT_STRING, new FieldMetaData("sIntString", (byte)3, new SetMetaData((byte)14, new StructMetaData((byte)12, IntString.class))));
      tmpMap.put(SetIntString._Fields.A_STRING, new FieldMetaData("aString", (byte)3, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SetIntString.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      S_INT_STRING((short)1, "sIntString"),
      A_STRING((short)2, "aString");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return S_INT_STRING;
            case 2:
               return A_STRING;
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

   private static class SetIntStringStandardSchemeFactory implements SchemeFactory {
      private SetIntStringStandardSchemeFactory() {
      }

      public SetIntStringStandardScheme getScheme() {
         return new SetIntStringStandardScheme();
      }
   }

   private static class SetIntStringStandardScheme extends StandardScheme {
      private SetIntStringStandardScheme() {
      }

      public void read(TProtocol iprot, SetIntString struct) throws TException {
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
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set82 = iprot.readSetBegin();
                  struct.sIntString = new HashSet(2 * _set82.size);

                  for(int _i84 = 0; _i84 < _set82.size; ++_i84) {
                     IntString _elem83 = new IntString();
                     _elem83.read(iprot);
                     struct.sIntString.add(_elem83);
                  }

                  iprot.readSetEnd();
                  struct.setSIntStringIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.aString = iprot.readString();
                     struct.setAStringIsSet(true);
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

      public void write(TProtocol oprot, SetIntString struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SetIntString.STRUCT_DESC);
         if (struct.sIntString != null) {
            oprot.writeFieldBegin(SetIntString.S_INT_STRING_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)12, struct.sIntString.size()));

            for(IntString _iter85 : struct.sIntString) {
               _iter85.write(oprot);
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         if (struct.aString != null) {
            oprot.writeFieldBegin(SetIntString.A_STRING_FIELD_DESC);
            oprot.writeString(struct.aString);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SetIntStringTupleSchemeFactory implements SchemeFactory {
      private SetIntStringTupleSchemeFactory() {
      }

      public SetIntStringTupleScheme getScheme() {
         return new SetIntStringTupleScheme();
      }
   }

   private static class SetIntStringTupleScheme extends TupleScheme {
      private SetIntStringTupleScheme() {
      }

      public void write(TProtocol prot, SetIntString struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetSIntString()) {
            optionals.set(0);
         }

         if (struct.isSetAString()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetSIntString()) {
            oprot.writeI32(struct.sIntString.size());

            for(IntString _iter86 : struct.sIntString) {
               _iter86.write(oprot);
            }
         }

         if (struct.isSetAString()) {
            oprot.writeString(struct.aString);
         }

      }

      public void read(TProtocol prot, SetIntString struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            TSet _set87 = iprot.readSetBegin((byte)12);
            struct.sIntString = new HashSet(2 * _set87.size);

            for(int _i89 = 0; _i89 < _set87.size; ++_i89) {
               IntString _elem88 = new IntString();
               _elem88.read(iprot);
               struct.sIntString.add(_elem88);
            }

            struct.setSIntStringIsSet(true);
         }

         if (incoming.get(1)) {
            struct.aString = iprot.readString();
            struct.setAStringIsSet(true);
         }

      }
   }
}
