package org.apache.hadoop.hive.metastore.api;

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

public class Type implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Type");
   private static final TField NAME_FIELD_DESC = new TField("name", (byte)11, (short)1);
   private static final TField TYPE1_FIELD_DESC = new TField("type1", (byte)11, (short)2);
   private static final TField TYPE2_FIELD_DESC = new TField("type2", (byte)11, (short)3);
   private static final TField FIELDS_FIELD_DESC = new TField("fields", (byte)15, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TypeStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TypeTupleSchemeFactory();
   @Nullable
   private String name;
   @Nullable
   private String type1;
   @Nullable
   private String type2;
   @Nullable
   private List fields;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public Type() {
   }

   public Type(String name) {
      this();
      this.name = name;
   }

   public Type(Type other) {
      if (other.isSetName()) {
         this.name = other.name;
      }

      if (other.isSetType1()) {
         this.type1 = other.type1;
      }

      if (other.isSetType2()) {
         this.type2 = other.type2;
      }

      if (other.isSetFields()) {
         List<FieldSchema> __this__fields = new ArrayList(other.fields.size());

         for(FieldSchema other_element : other.fields) {
            __this__fields.add(new FieldSchema(other_element));
         }

         this.fields = __this__fields;
      }

   }

   public Type deepCopy() {
      return new Type(this);
   }

   public void clear() {
      this.name = null;
      this.type1 = null;
      this.type2 = null;
      this.fields = null;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(@Nullable String name) {
      this.name = name;
   }

   public void unsetName() {
      this.name = null;
   }

   public boolean isSetName() {
      return this.name != null;
   }

   public void setNameIsSet(boolean value) {
      if (!value) {
         this.name = null;
      }

   }

   @Nullable
   public String getType1() {
      return this.type1;
   }

   public void setType1(@Nullable String type1) {
      this.type1 = type1;
   }

   public void unsetType1() {
      this.type1 = null;
   }

   public boolean isSetType1() {
      return this.type1 != null;
   }

   public void setType1IsSet(boolean value) {
      if (!value) {
         this.type1 = null;
      }

   }

   @Nullable
   public String getType2() {
      return this.type2;
   }

   public void setType2(@Nullable String type2) {
      this.type2 = type2;
   }

   public void unsetType2() {
      this.type2 = null;
   }

   public boolean isSetType2() {
      return this.type2 != null;
   }

   public void setType2IsSet(boolean value) {
      if (!value) {
         this.type2 = null;
      }

   }

   public int getFieldsSize() {
      return this.fields == null ? 0 : this.fields.size();
   }

   @Nullable
   public Iterator getFieldsIterator() {
      return this.fields == null ? null : this.fields.iterator();
   }

   public void addToFields(FieldSchema elem) {
      if (this.fields == null) {
         this.fields = new ArrayList();
      }

      this.fields.add(elem);
   }

   @Nullable
   public List getFields() {
      return this.fields;
   }

   public void setFields(@Nullable List fields) {
      this.fields = fields;
   }

   public void unsetFields() {
      this.fields = null;
   }

   public boolean isSetFields() {
      return this.fields != null;
   }

   public void setFieldsIsSet(boolean value) {
      if (!value) {
         this.fields = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NAME:
            if (value == null) {
               this.unsetName();
            } else {
               this.setName((String)value);
            }
            break;
         case TYPE1:
            if (value == null) {
               this.unsetType1();
            } else {
               this.setType1((String)value);
            }
            break;
         case TYPE2:
            if (value == null) {
               this.unsetType2();
            } else {
               this.setType2((String)value);
            }
            break;
         case FIELDS:
            if (value == null) {
               this.unsetFields();
            } else {
               this.setFields((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NAME:
            return this.getName();
         case TYPE1:
            return this.getType1();
         case TYPE2:
            return this.getType2();
         case FIELDS:
            return this.getFields();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NAME:
               return this.isSetName();
            case TYPE1:
               return this.isSetType1();
            case TYPE2:
               return this.isSetType2();
            case FIELDS:
               return this.isSetFields();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Type ? this.equals((Type)that) : false;
   }

   public boolean equals(Type that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_name = this.isSetName();
         boolean that_present_name = that.isSetName();
         if (this_present_name || that_present_name) {
            if (!this_present_name || !that_present_name) {
               return false;
            }

            if (!this.name.equals(that.name)) {
               return false;
            }
         }

         boolean this_present_type1 = this.isSetType1();
         boolean that_present_type1 = that.isSetType1();
         if (this_present_type1 || that_present_type1) {
            if (!this_present_type1 || !that_present_type1) {
               return false;
            }

            if (!this.type1.equals(that.type1)) {
               return false;
            }
         }

         boolean this_present_type2 = this.isSetType2();
         boolean that_present_type2 = that.isSetType2();
         if (this_present_type2 || that_present_type2) {
            if (!this_present_type2 || !that_present_type2) {
               return false;
            }

            if (!this.type2.equals(that.type2)) {
               return false;
            }
         }

         boolean this_present_fields = this.isSetFields();
         boolean that_present_fields = that.isSetFields();
         if (this_present_fields || that_present_fields) {
            if (!this_present_fields || !that_present_fields) {
               return false;
            }

            if (!this.fields.equals(that.fields)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetName() ? 131071 : 524287);
      if (this.isSetName()) {
         hashCode = hashCode * 8191 + this.name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetType1() ? 131071 : 524287);
      if (this.isSetType1()) {
         hashCode = hashCode * 8191 + this.type1.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetType2() ? 131071 : 524287);
      if (this.isSetType2()) {
         hashCode = hashCode * 8191 + this.type2.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFields() ? 131071 : 524287);
      if (this.isSetFields()) {
         hashCode = hashCode * 8191 + this.fields.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Type other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetName(), other.isSetName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetName()) {
               lastComparison = TBaseHelper.compareTo(this.name, other.name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetType1(), other.isSetType1());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetType1()) {
                  lastComparison = TBaseHelper.compareTo(this.type1, other.type1);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetType2(), other.isSetType2());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetType2()) {
                     lastComparison = TBaseHelper.compareTo(this.type2, other.type2);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetFields(), other.isSetFields());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetFields()) {
                        lastComparison = TBaseHelper.compareTo(this.fields, other.fields);
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
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Type._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Type(");
      boolean first = true;
      sb.append("name:");
      if (this.name == null) {
         sb.append("null");
      } else {
         sb.append(this.name);
      }

      first = false;
      if (this.isSetType1()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("type1:");
         if (this.type1 == null) {
            sb.append("null");
         } else {
            sb.append(this.type1);
         }

         first = false;
      }

      if (this.isSetType2()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("type2:");
         if (this.type2 == null) {
            sb.append("null");
         } else {
            sb.append(this.type2);
         }

         first = false;
      }

      if (this.isSetFields()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("fields:");
         if (this.fields == null) {
            sb.append("null");
         } else {
            sb.append(this.fields);
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
      optionals = new _Fields[]{Type._Fields.TYPE1, Type._Fields.TYPE2, Type._Fields.FIELDS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Type._Fields.NAME, new FieldMetaData("name", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Type._Fields.TYPE1, new FieldMetaData("type1", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(Type._Fields.TYPE2, new FieldMetaData("type2", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(Type._Fields.FIELDS, new FieldMetaData("fields", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, FieldSchema.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Type.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NAME((short)1, "name"),
      TYPE1((short)2, "type1"),
      TYPE2((short)3, "type2"),
      FIELDS((short)4, "fields");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NAME;
            case 2:
               return TYPE1;
            case 3:
               return TYPE2;
            case 4:
               return FIELDS;
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

   private static class TypeStandardSchemeFactory implements SchemeFactory {
      private TypeStandardSchemeFactory() {
      }

      public TypeStandardScheme getScheme() {
         return new TypeStandardScheme();
      }
   }

   private static class TypeStandardScheme extends StandardScheme {
      private TypeStandardScheme() {
      }

      public void read(TProtocol iprot, Type struct) throws TException {
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
                     struct.name = iprot.readString();
                     struct.setNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.type1 = iprot.readString();
                     struct.setType1IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.type2 = iprot.readString();
                     struct.setType2IsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list0 = iprot.readListBegin();
                  struct.fields = new ArrayList(_list0.size);

                  for(int _i2 = 0; _i2 < _list0.size; ++_i2) {
                     FieldSchema _elem1 = new FieldSchema();
                     _elem1.read(iprot);
                     struct.fields.add(_elem1);
                  }

                  iprot.readListEnd();
                  struct.setFieldsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, Type struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Type.STRUCT_DESC);
         if (struct.name != null) {
            oprot.writeFieldBegin(Type.NAME_FIELD_DESC);
            oprot.writeString(struct.name);
            oprot.writeFieldEnd();
         }

         if (struct.type1 != null && struct.isSetType1()) {
            oprot.writeFieldBegin(Type.TYPE1_FIELD_DESC);
            oprot.writeString(struct.type1);
            oprot.writeFieldEnd();
         }

         if (struct.type2 != null && struct.isSetType2()) {
            oprot.writeFieldBegin(Type.TYPE2_FIELD_DESC);
            oprot.writeString(struct.type2);
            oprot.writeFieldEnd();
         }

         if (struct.fields != null && struct.isSetFields()) {
            oprot.writeFieldBegin(Type.FIELDS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.fields.size()));

            for(FieldSchema _iter3 : struct.fields) {
               _iter3.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TypeTupleSchemeFactory implements SchemeFactory {
      private TypeTupleSchemeFactory() {
      }

      public TypeTupleScheme getScheme() {
         return new TypeTupleScheme();
      }
   }

   private static class TypeTupleScheme extends TupleScheme {
      private TypeTupleScheme() {
      }

      public void write(TProtocol prot, Type struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetName()) {
            optionals.set(0);
         }

         if (struct.isSetType1()) {
            optionals.set(1);
         }

         if (struct.isSetType2()) {
            optionals.set(2);
         }

         if (struct.isSetFields()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetName()) {
            oprot.writeString(struct.name);
         }

         if (struct.isSetType1()) {
            oprot.writeString(struct.type1);
         }

         if (struct.isSetType2()) {
            oprot.writeString(struct.type2);
         }

         if (struct.isSetFields()) {
            oprot.writeI32(struct.fields.size());

            for(FieldSchema _iter4 : struct.fields) {
               _iter4.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, Type struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            struct.name = iprot.readString();
            struct.setNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.type1 = iprot.readString();
            struct.setType1IsSet(true);
         }

         if (incoming.get(2)) {
            struct.type2 = iprot.readString();
            struct.setType2IsSet(true);
         }

         if (incoming.get(3)) {
            TList _list5 = iprot.readListBegin((byte)12);
            struct.fields = new ArrayList(_list5.size);

            for(int _i7 = 0; _i7 < _list5.size; ++_i7) {
               FieldSchema _elem6 = new FieldSchema();
               _elem6.read(iprot);
               struct.fields.add(_elem6);
            }

            struct.setFieldsIsSet(true);
         }

      }
   }
}
