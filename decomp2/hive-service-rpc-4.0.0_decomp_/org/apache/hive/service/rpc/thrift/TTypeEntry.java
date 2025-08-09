package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TEnum;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TTypeEntry extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("TTypeEntry");
   private static final TField PRIMITIVE_ENTRY_FIELD_DESC = new TField("primitiveEntry", (byte)12, (short)1);
   private static final TField ARRAY_ENTRY_FIELD_DESC = new TField("arrayEntry", (byte)12, (short)2);
   private static final TField MAP_ENTRY_FIELD_DESC = new TField("mapEntry", (byte)12, (short)3);
   private static final TField STRUCT_ENTRY_FIELD_DESC = new TField("structEntry", (byte)12, (short)4);
   private static final TField UNION_ENTRY_FIELD_DESC = new TField("unionEntry", (byte)12, (short)5);
   private static final TField USER_DEFINED_TYPE_ENTRY_FIELD_DESC = new TField("userDefinedTypeEntry", (byte)12, (short)6);
   public static final Map metaDataMap;

   public TTypeEntry() {
   }

   public TTypeEntry(_Fields setField, Object value) {
      super(setField, value);
   }

   public TTypeEntry(TTypeEntry other) {
      super(other);
   }

   public TTypeEntry deepCopy() {
      return new TTypeEntry(this);
   }

   public static TTypeEntry primitiveEntry(TPrimitiveTypeEntry value) {
      TTypeEntry x = new TTypeEntry();
      x.setPrimitiveEntry(value);
      return x;
   }

   public static TTypeEntry arrayEntry(TArrayTypeEntry value) {
      TTypeEntry x = new TTypeEntry();
      x.setArrayEntry(value);
      return x;
   }

   public static TTypeEntry mapEntry(TMapTypeEntry value) {
      TTypeEntry x = new TTypeEntry();
      x.setMapEntry(value);
      return x;
   }

   public static TTypeEntry structEntry(TStructTypeEntry value) {
      TTypeEntry x = new TTypeEntry();
      x.setStructEntry(value);
      return x;
   }

   public static TTypeEntry unionEntry(TUnionTypeEntry value) {
      TTypeEntry x = new TTypeEntry();
      x.setUnionEntry(value);
      return x;
   }

   public static TTypeEntry userDefinedTypeEntry(TUserDefinedTypeEntry value) {
      TTypeEntry x = new TTypeEntry();
      x.setUserDefinedTypeEntry(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case PRIMITIVE_ENTRY:
            if (!(value instanceof TPrimitiveTypeEntry)) {
               throw new ClassCastException("Was expecting value of type TPrimitiveTypeEntry for field 'primitiveEntry', but got " + value.getClass().getSimpleName());
            }
            break;
         case ARRAY_ENTRY:
            if (!(value instanceof TArrayTypeEntry)) {
               throw new ClassCastException("Was expecting value of type TArrayTypeEntry for field 'arrayEntry', but got " + value.getClass().getSimpleName());
            }
            break;
         case MAP_ENTRY:
            if (!(value instanceof TMapTypeEntry)) {
               throw new ClassCastException("Was expecting value of type TMapTypeEntry for field 'mapEntry', but got " + value.getClass().getSimpleName());
            }
            break;
         case STRUCT_ENTRY:
            if (!(value instanceof TStructTypeEntry)) {
               throw new ClassCastException("Was expecting value of type TStructTypeEntry for field 'structEntry', but got " + value.getClass().getSimpleName());
            }
            break;
         case UNION_ENTRY:
            if (!(value instanceof TUnionTypeEntry)) {
               throw new ClassCastException("Was expecting value of type TUnionTypeEntry for field 'unionEntry', but got " + value.getClass().getSimpleName());
            }
            break;
         case USER_DEFINED_TYPE_ENTRY:
            if (!(value instanceof TUserDefinedTypeEntry)) {
               throw new ClassCastException("Was expecting value of type TUserDefinedTypeEntry for field 'userDefinedTypeEntry', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = TTypeEntry._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case PRIMITIVE_ENTRY:
               if (field.type == PRIMITIVE_ENTRY_FIELD_DESC.type) {
                  TPrimitiveTypeEntry primitiveEntry = new TPrimitiveTypeEntry();
                  primitiveEntry.read(iprot);
                  return primitiveEntry;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case ARRAY_ENTRY:
               if (field.type == ARRAY_ENTRY_FIELD_DESC.type) {
                  TArrayTypeEntry arrayEntry = new TArrayTypeEntry();
                  arrayEntry.read(iprot);
                  return arrayEntry;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case MAP_ENTRY:
               if (field.type == MAP_ENTRY_FIELD_DESC.type) {
                  TMapTypeEntry mapEntry = new TMapTypeEntry();
                  mapEntry.read(iprot);
                  return mapEntry;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case STRUCT_ENTRY:
               if (field.type == STRUCT_ENTRY_FIELD_DESC.type) {
                  TStructTypeEntry structEntry = new TStructTypeEntry();
                  structEntry.read(iprot);
                  return structEntry;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case UNION_ENTRY:
               if (field.type == UNION_ENTRY_FIELD_DESC.type) {
                  TUnionTypeEntry unionEntry = new TUnionTypeEntry();
                  unionEntry.read(iprot);
                  return unionEntry;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case USER_DEFINED_TYPE_ENTRY:
               if (field.type == USER_DEFINED_TYPE_ENTRY_FIELD_DESC.type) {
                  TUserDefinedTypeEntry userDefinedTypeEntry = new TUserDefinedTypeEntry();
                  userDefinedTypeEntry.read(iprot);
                  return userDefinedTypeEntry;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         TProtocolUtil.skip(iprot, field.type);
         return null;
      }
   }

   protected void standardSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case PRIMITIVE_ENTRY:
            TPrimitiveTypeEntry primitiveEntry = (TPrimitiveTypeEntry)this.value_;
            primitiveEntry.write(oprot);
            return;
         case ARRAY_ENTRY:
            TArrayTypeEntry arrayEntry = (TArrayTypeEntry)this.value_;
            arrayEntry.write(oprot);
            return;
         case MAP_ENTRY:
            TMapTypeEntry mapEntry = (TMapTypeEntry)this.value_;
            mapEntry.write(oprot);
            return;
         case STRUCT_ENTRY:
            TStructTypeEntry structEntry = (TStructTypeEntry)this.value_;
            structEntry.write(oprot);
            return;
         case UNION_ENTRY:
            TUnionTypeEntry unionEntry = (TUnionTypeEntry)this.value_;
            unionEntry.write(oprot);
            return;
         case USER_DEFINED_TYPE_ENTRY:
            TUserDefinedTypeEntry userDefinedTypeEntry = (TUserDefinedTypeEntry)this.value_;
            userDefinedTypeEntry.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = TTypeEntry._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case PRIMITIVE_ENTRY:
               TPrimitiveTypeEntry primitiveEntry = new TPrimitiveTypeEntry();
               primitiveEntry.read(iprot);
               return primitiveEntry;
            case ARRAY_ENTRY:
               TArrayTypeEntry arrayEntry = new TArrayTypeEntry();
               arrayEntry.read(iprot);
               return arrayEntry;
            case MAP_ENTRY:
               TMapTypeEntry mapEntry = new TMapTypeEntry();
               mapEntry.read(iprot);
               return mapEntry;
            case STRUCT_ENTRY:
               TStructTypeEntry structEntry = new TStructTypeEntry();
               structEntry.read(iprot);
               return structEntry;
            case UNION_ENTRY:
               TUnionTypeEntry unionEntry = new TUnionTypeEntry();
               unionEntry.read(iprot);
               return unionEntry;
            case USER_DEFINED_TYPE_ENTRY:
               TUserDefinedTypeEntry userDefinedTypeEntry = new TUserDefinedTypeEntry();
               userDefinedTypeEntry.read(iprot);
               return userDefinedTypeEntry;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case PRIMITIVE_ENTRY:
            TPrimitiveTypeEntry primitiveEntry = (TPrimitiveTypeEntry)this.value_;
            primitiveEntry.write(oprot);
            return;
         case ARRAY_ENTRY:
            TArrayTypeEntry arrayEntry = (TArrayTypeEntry)this.value_;
            arrayEntry.write(oprot);
            return;
         case MAP_ENTRY:
            TMapTypeEntry mapEntry = (TMapTypeEntry)this.value_;
            mapEntry.write(oprot);
            return;
         case STRUCT_ENTRY:
            TStructTypeEntry structEntry = (TStructTypeEntry)this.value_;
            structEntry.write(oprot);
            return;
         case UNION_ENTRY:
            TUnionTypeEntry unionEntry = (TUnionTypeEntry)this.value_;
            unionEntry.write(oprot);
            return;
         case USER_DEFINED_TYPE_ENTRY:
            TUserDefinedTypeEntry userDefinedTypeEntry = (TUserDefinedTypeEntry)this.value_;
            userDefinedTypeEntry.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case PRIMITIVE_ENTRY:
            return PRIMITIVE_ENTRY_FIELD_DESC;
         case ARRAY_ENTRY:
            return ARRAY_ENTRY_FIELD_DESC;
         case MAP_ENTRY:
            return MAP_ENTRY_FIELD_DESC;
         case STRUCT_ENTRY:
            return STRUCT_ENTRY_FIELD_DESC;
         case UNION_ENTRY:
            return UNION_ENTRY_FIELD_DESC;
         case USER_DEFINED_TYPE_ENTRY:
            return USER_DEFINED_TYPE_ENTRY_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return TTypeEntry._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TTypeEntry._Fields.findByThriftId(fieldId);
   }

   public TPrimitiveTypeEntry getPrimitiveEntry() {
      if (this.getSetField() == TTypeEntry._Fields.PRIMITIVE_ENTRY) {
         return (TPrimitiveTypeEntry)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'primitiveEntry' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setPrimitiveEntry(TPrimitiveTypeEntry value) {
      this.setField_ = TTypeEntry._Fields.PRIMITIVE_ENTRY;
      this.value_ = Objects.requireNonNull(value, "_Fields.PRIMITIVE_ENTRY");
   }

   public TArrayTypeEntry getArrayEntry() {
      if (this.getSetField() == TTypeEntry._Fields.ARRAY_ENTRY) {
         return (TArrayTypeEntry)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'arrayEntry' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setArrayEntry(TArrayTypeEntry value) {
      this.setField_ = TTypeEntry._Fields.ARRAY_ENTRY;
      this.value_ = Objects.requireNonNull(value, "_Fields.ARRAY_ENTRY");
   }

   public TMapTypeEntry getMapEntry() {
      if (this.getSetField() == TTypeEntry._Fields.MAP_ENTRY) {
         return (TMapTypeEntry)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'mapEntry' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setMapEntry(TMapTypeEntry value) {
      this.setField_ = TTypeEntry._Fields.MAP_ENTRY;
      this.value_ = Objects.requireNonNull(value, "_Fields.MAP_ENTRY");
   }

   public TStructTypeEntry getStructEntry() {
      if (this.getSetField() == TTypeEntry._Fields.STRUCT_ENTRY) {
         return (TStructTypeEntry)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'structEntry' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStructEntry(TStructTypeEntry value) {
      this.setField_ = TTypeEntry._Fields.STRUCT_ENTRY;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRUCT_ENTRY");
   }

   public TUnionTypeEntry getUnionEntry() {
      if (this.getSetField() == TTypeEntry._Fields.UNION_ENTRY) {
         return (TUnionTypeEntry)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'unionEntry' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setUnionEntry(TUnionTypeEntry value) {
      this.setField_ = TTypeEntry._Fields.UNION_ENTRY;
      this.value_ = Objects.requireNonNull(value, "_Fields.UNION_ENTRY");
   }

   public TUserDefinedTypeEntry getUserDefinedTypeEntry() {
      if (this.getSetField() == TTypeEntry._Fields.USER_DEFINED_TYPE_ENTRY) {
         return (TUserDefinedTypeEntry)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'userDefinedTypeEntry' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setUserDefinedTypeEntry(TUserDefinedTypeEntry value) {
      this.setField_ = TTypeEntry._Fields.USER_DEFINED_TYPE_ENTRY;
      this.value_ = Objects.requireNonNull(value, "_Fields.USER_DEFINED_TYPE_ENTRY");
   }

   public boolean isSetPrimitiveEntry() {
      return this.setField_ == TTypeEntry._Fields.PRIMITIVE_ENTRY;
   }

   public boolean isSetArrayEntry() {
      return this.setField_ == TTypeEntry._Fields.ARRAY_ENTRY;
   }

   public boolean isSetMapEntry() {
      return this.setField_ == TTypeEntry._Fields.MAP_ENTRY;
   }

   public boolean isSetStructEntry() {
      return this.setField_ == TTypeEntry._Fields.STRUCT_ENTRY;
   }

   public boolean isSetUnionEntry() {
      return this.setField_ == TTypeEntry._Fields.UNION_ENTRY;
   }

   public boolean isSetUserDefinedTypeEntry() {
      return this.setField_ == TTypeEntry._Fields.USER_DEFINED_TYPE_ENTRY;
   }

   public boolean equals(Object other) {
      return other instanceof TTypeEntry ? this.equals((TTypeEntry)other) : false;
   }

   public boolean equals(TTypeEntry other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(TTypeEntry other) {
      int lastComparison = TBaseHelper.compareTo((Comparable)this.getSetField(), (Comparable)other.getSetField());
      return lastComparison == 0 ? TBaseHelper.compareTo(this.getFieldValue(), other.getFieldValue()) : lastComparison;
   }

   public int hashCode() {
      List<Object> list = new ArrayList();
      list.add(this.getClass().getName());
      TFieldIdEnum setField = this.getSetField();
      if (setField != null) {
         list.add(setField.getThriftFieldId());
         Object value = this.getFieldValue();
         if (value instanceof TEnum) {
            list.add(((TEnum)this.getFieldValue()).getValue());
         } else {
            list.add(value);
         }
      }

      return list.hashCode();
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

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TTypeEntry._Fields.PRIMITIVE_ENTRY, new FieldMetaData("primitiveEntry", (byte)2, new StructMetaData((byte)12, TPrimitiveTypeEntry.class)));
      tmpMap.put(TTypeEntry._Fields.ARRAY_ENTRY, new FieldMetaData("arrayEntry", (byte)2, new StructMetaData((byte)12, TArrayTypeEntry.class)));
      tmpMap.put(TTypeEntry._Fields.MAP_ENTRY, new FieldMetaData("mapEntry", (byte)2, new StructMetaData((byte)12, TMapTypeEntry.class)));
      tmpMap.put(TTypeEntry._Fields.STRUCT_ENTRY, new FieldMetaData("structEntry", (byte)2, new StructMetaData((byte)12, TStructTypeEntry.class)));
      tmpMap.put(TTypeEntry._Fields.UNION_ENTRY, new FieldMetaData("unionEntry", (byte)2, new StructMetaData((byte)12, TUnionTypeEntry.class)));
      tmpMap.put(TTypeEntry._Fields.USER_DEFINED_TYPE_ENTRY, new FieldMetaData("userDefinedTypeEntry", (byte)2, new StructMetaData((byte)12, TUserDefinedTypeEntry.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TTypeEntry.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRIMITIVE_ENTRY((short)1, "primitiveEntry"),
      ARRAY_ENTRY((short)2, "arrayEntry"),
      MAP_ENTRY((short)3, "mapEntry"),
      STRUCT_ENTRY((short)4, "structEntry"),
      UNION_ENTRY((short)5, "unionEntry"),
      USER_DEFINED_TYPE_ENTRY((short)6, "userDefinedTypeEntry");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRIMITIVE_ENTRY;
            case 2:
               return ARRAY_ENTRY;
            case 3:
               return MAP_ENTRY;
            case 4:
               return STRUCT_ENTRY;
            case 5:
               return UNION_ENTRY;
            case 6:
               return USER_DEFINED_TYPE_ENTRY;
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
}
