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
public class TColumnValue extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("TColumnValue");
   private static final TField BOOL_VAL_FIELD_DESC = new TField("boolVal", (byte)12, (short)1);
   private static final TField BYTE_VAL_FIELD_DESC = new TField("byteVal", (byte)12, (short)2);
   private static final TField I16_VAL_FIELD_DESC = new TField("i16Val", (byte)12, (short)3);
   private static final TField I32_VAL_FIELD_DESC = new TField("i32Val", (byte)12, (short)4);
   private static final TField I64_VAL_FIELD_DESC = new TField("i64Val", (byte)12, (short)5);
   private static final TField DOUBLE_VAL_FIELD_DESC = new TField("doubleVal", (byte)12, (short)6);
   private static final TField STRING_VAL_FIELD_DESC = new TField("stringVal", (byte)12, (short)7);
   public static final Map metaDataMap;

   public TColumnValue() {
   }

   public TColumnValue(_Fields setField, Object value) {
      super(setField, value);
   }

   public TColumnValue(TColumnValue other) {
      super(other);
   }

   public TColumnValue deepCopy() {
      return new TColumnValue(this);
   }

   public static TColumnValue boolVal(TBoolValue value) {
      TColumnValue x = new TColumnValue();
      x.setBoolVal(value);
      return x;
   }

   public static TColumnValue byteVal(TByteValue value) {
      TColumnValue x = new TColumnValue();
      x.setByteVal(value);
      return x;
   }

   public static TColumnValue i16Val(TI16Value value) {
      TColumnValue x = new TColumnValue();
      x.setI16Val(value);
      return x;
   }

   public static TColumnValue i32Val(TI32Value value) {
      TColumnValue x = new TColumnValue();
      x.setI32Val(value);
      return x;
   }

   public static TColumnValue i64Val(TI64Value value) {
      TColumnValue x = new TColumnValue();
      x.setI64Val(value);
      return x;
   }

   public static TColumnValue doubleVal(TDoubleValue value) {
      TColumnValue x = new TColumnValue();
      x.setDoubleVal(value);
      return x;
   }

   public static TColumnValue stringVal(TStringValue value) {
      TColumnValue x = new TColumnValue();
      x.setStringVal(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case BOOL_VAL:
            if (!(value instanceof TBoolValue)) {
               throw new ClassCastException("Was expecting value of type TBoolValue for field 'boolVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case BYTE_VAL:
            if (!(value instanceof TByteValue)) {
               throw new ClassCastException("Was expecting value of type TByteValue for field 'byteVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case I16_VAL:
            if (!(value instanceof TI16Value)) {
               throw new ClassCastException("Was expecting value of type TI16Value for field 'i16Val', but got " + value.getClass().getSimpleName());
            }
            break;
         case I32_VAL:
            if (!(value instanceof TI32Value)) {
               throw new ClassCastException("Was expecting value of type TI32Value for field 'i32Val', but got " + value.getClass().getSimpleName());
            }
            break;
         case I64_VAL:
            if (!(value instanceof TI64Value)) {
               throw new ClassCastException("Was expecting value of type TI64Value for field 'i64Val', but got " + value.getClass().getSimpleName());
            }
            break;
         case DOUBLE_VAL:
            if (!(value instanceof TDoubleValue)) {
               throw new ClassCastException("Was expecting value of type TDoubleValue for field 'doubleVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case STRING_VAL:
            if (!(value instanceof TStringValue)) {
               throw new ClassCastException("Was expecting value of type TStringValue for field 'stringVal', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = TColumnValue._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case BOOL_VAL:
               if (field.type == BOOL_VAL_FIELD_DESC.type) {
                  TBoolValue boolVal = new TBoolValue();
                  boolVal.read(iprot);
                  return boolVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case BYTE_VAL:
               if (field.type == BYTE_VAL_FIELD_DESC.type) {
                  TByteValue byteVal = new TByteValue();
                  byteVal.read(iprot);
                  return byteVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case I16_VAL:
               if (field.type == I16_VAL_FIELD_DESC.type) {
                  TI16Value i16Val = new TI16Value();
                  i16Val.read(iprot);
                  return i16Val;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case I32_VAL:
               if (field.type == I32_VAL_FIELD_DESC.type) {
                  TI32Value i32Val = new TI32Value();
                  i32Val.read(iprot);
                  return i32Val;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case I64_VAL:
               if (field.type == I64_VAL_FIELD_DESC.type) {
                  TI64Value i64Val = new TI64Value();
                  i64Val.read(iprot);
                  return i64Val;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DOUBLE_VAL:
               if (field.type == DOUBLE_VAL_FIELD_DESC.type) {
                  TDoubleValue doubleVal = new TDoubleValue();
                  doubleVal.read(iprot);
                  return doubleVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case STRING_VAL:
               if (field.type == STRING_VAL_FIELD_DESC.type) {
                  TStringValue stringVal = new TStringValue();
                  stringVal.read(iprot);
                  return stringVal;
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
         case BOOL_VAL:
            TBoolValue boolVal = (TBoolValue)this.value_;
            boolVal.write(oprot);
            return;
         case BYTE_VAL:
            TByteValue byteVal = (TByteValue)this.value_;
            byteVal.write(oprot);
            return;
         case I16_VAL:
            TI16Value i16Val = (TI16Value)this.value_;
            i16Val.write(oprot);
            return;
         case I32_VAL:
            TI32Value i32Val = (TI32Value)this.value_;
            i32Val.write(oprot);
            return;
         case I64_VAL:
            TI64Value i64Val = (TI64Value)this.value_;
            i64Val.write(oprot);
            return;
         case DOUBLE_VAL:
            TDoubleValue doubleVal = (TDoubleValue)this.value_;
            doubleVal.write(oprot);
            return;
         case STRING_VAL:
            TStringValue stringVal = (TStringValue)this.value_;
            stringVal.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = TColumnValue._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case BOOL_VAL:
               TBoolValue boolVal = new TBoolValue();
               boolVal.read(iprot);
               return boolVal;
            case BYTE_VAL:
               TByteValue byteVal = new TByteValue();
               byteVal.read(iprot);
               return byteVal;
            case I16_VAL:
               TI16Value i16Val = new TI16Value();
               i16Val.read(iprot);
               return i16Val;
            case I32_VAL:
               TI32Value i32Val = new TI32Value();
               i32Val.read(iprot);
               return i32Val;
            case I64_VAL:
               TI64Value i64Val = new TI64Value();
               i64Val.read(iprot);
               return i64Val;
            case DOUBLE_VAL:
               TDoubleValue doubleVal = new TDoubleValue();
               doubleVal.read(iprot);
               return doubleVal;
            case STRING_VAL:
               TStringValue stringVal = new TStringValue();
               stringVal.read(iprot);
               return stringVal;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case BOOL_VAL:
            TBoolValue boolVal = (TBoolValue)this.value_;
            boolVal.write(oprot);
            return;
         case BYTE_VAL:
            TByteValue byteVal = (TByteValue)this.value_;
            byteVal.write(oprot);
            return;
         case I16_VAL:
            TI16Value i16Val = (TI16Value)this.value_;
            i16Val.write(oprot);
            return;
         case I32_VAL:
            TI32Value i32Val = (TI32Value)this.value_;
            i32Val.write(oprot);
            return;
         case I64_VAL:
            TI64Value i64Val = (TI64Value)this.value_;
            i64Val.write(oprot);
            return;
         case DOUBLE_VAL:
            TDoubleValue doubleVal = (TDoubleValue)this.value_;
            doubleVal.write(oprot);
            return;
         case STRING_VAL:
            TStringValue stringVal = (TStringValue)this.value_;
            stringVal.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case BOOL_VAL:
            return BOOL_VAL_FIELD_DESC;
         case BYTE_VAL:
            return BYTE_VAL_FIELD_DESC;
         case I16_VAL:
            return I16_VAL_FIELD_DESC;
         case I32_VAL:
            return I32_VAL_FIELD_DESC;
         case I64_VAL:
            return I64_VAL_FIELD_DESC;
         case DOUBLE_VAL:
            return DOUBLE_VAL_FIELD_DESC;
         case STRING_VAL:
            return STRING_VAL_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return TColumnValue._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TColumnValue._Fields.findByThriftId(fieldId);
   }

   public TBoolValue getBoolVal() {
      if (this.getSetField() == TColumnValue._Fields.BOOL_VAL) {
         return (TBoolValue)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'boolVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBoolVal(TBoolValue value) {
      this.setField_ = TColumnValue._Fields.BOOL_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.BOOL_VAL");
   }

   public TByteValue getByteVal() {
      if (this.getSetField() == TColumnValue._Fields.BYTE_VAL) {
         return (TByteValue)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'byteVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setByteVal(TByteValue value) {
      this.setField_ = TColumnValue._Fields.BYTE_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.BYTE_VAL");
   }

   public TI16Value getI16Val() {
      if (this.getSetField() == TColumnValue._Fields.I16_VAL) {
         return (TI16Value)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i16Val' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI16Val(TI16Value value) {
      this.setField_ = TColumnValue._Fields.I16_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.I16_VAL");
   }

   public TI32Value getI32Val() {
      if (this.getSetField() == TColumnValue._Fields.I32_VAL) {
         return (TI32Value)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i32Val' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI32Val(TI32Value value) {
      this.setField_ = TColumnValue._Fields.I32_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.I32_VAL");
   }

   public TI64Value getI64Val() {
      if (this.getSetField() == TColumnValue._Fields.I64_VAL) {
         return (TI64Value)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i64Val' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI64Val(TI64Value value) {
      this.setField_ = TColumnValue._Fields.I64_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.I64_VAL");
   }

   public TDoubleValue getDoubleVal() {
      if (this.getSetField() == TColumnValue._Fields.DOUBLE_VAL) {
         return (TDoubleValue)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'doubleVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDoubleVal(TDoubleValue value) {
      this.setField_ = TColumnValue._Fields.DOUBLE_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.DOUBLE_VAL");
   }

   public TStringValue getStringVal() {
      if (this.getSetField() == TColumnValue._Fields.STRING_VAL) {
         return (TStringValue)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'stringVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStringVal(TStringValue value) {
      this.setField_ = TColumnValue._Fields.STRING_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING_VAL");
   }

   public boolean isSetBoolVal() {
      return this.setField_ == TColumnValue._Fields.BOOL_VAL;
   }

   public boolean isSetByteVal() {
      return this.setField_ == TColumnValue._Fields.BYTE_VAL;
   }

   public boolean isSetI16Val() {
      return this.setField_ == TColumnValue._Fields.I16_VAL;
   }

   public boolean isSetI32Val() {
      return this.setField_ == TColumnValue._Fields.I32_VAL;
   }

   public boolean isSetI64Val() {
      return this.setField_ == TColumnValue._Fields.I64_VAL;
   }

   public boolean isSetDoubleVal() {
      return this.setField_ == TColumnValue._Fields.DOUBLE_VAL;
   }

   public boolean isSetStringVal() {
      return this.setField_ == TColumnValue._Fields.STRING_VAL;
   }

   public boolean equals(Object other) {
      return other instanceof TColumnValue ? this.equals((TColumnValue)other) : false;
   }

   public boolean equals(TColumnValue other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(TColumnValue other) {
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
      tmpMap.put(TColumnValue._Fields.BOOL_VAL, new FieldMetaData("boolVal", (byte)2, new StructMetaData((byte)12, TBoolValue.class)));
      tmpMap.put(TColumnValue._Fields.BYTE_VAL, new FieldMetaData("byteVal", (byte)2, new StructMetaData((byte)12, TByteValue.class)));
      tmpMap.put(TColumnValue._Fields.I16_VAL, new FieldMetaData("i16Val", (byte)2, new StructMetaData((byte)12, TI16Value.class)));
      tmpMap.put(TColumnValue._Fields.I32_VAL, new FieldMetaData("i32Val", (byte)2, new StructMetaData((byte)12, TI32Value.class)));
      tmpMap.put(TColumnValue._Fields.I64_VAL, new FieldMetaData("i64Val", (byte)2, new StructMetaData((byte)12, TI64Value.class)));
      tmpMap.put(TColumnValue._Fields.DOUBLE_VAL, new FieldMetaData("doubleVal", (byte)2, new StructMetaData((byte)12, TDoubleValue.class)));
      tmpMap.put(TColumnValue._Fields.STRING_VAL, new FieldMetaData("stringVal", (byte)2, new StructMetaData((byte)12, TStringValue.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TColumnValue.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      BOOL_VAL((short)1, "boolVal"),
      BYTE_VAL((short)2, "byteVal"),
      I16_VAL((short)3, "i16Val"),
      I32_VAL((short)4, "i32Val"),
      I64_VAL((short)5, "i64Val"),
      DOUBLE_VAL((short)6, "doubleVal"),
      STRING_VAL((short)7, "stringVal");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return BOOL_VAL;
            case 2:
               return BYTE_VAL;
            case 3:
               return I16_VAL;
            case 4:
               return I32_VAL;
            case 5:
               return I64_VAL;
            case 6:
               return DOUBLE_VAL;
            case 7:
               return STRING_VAL;
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
