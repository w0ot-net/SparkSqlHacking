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
public class TColumn extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("TColumn");
   private static final TField BOOL_VAL_FIELD_DESC = new TField("boolVal", (byte)12, (short)1);
   private static final TField BYTE_VAL_FIELD_DESC = new TField("byteVal", (byte)12, (short)2);
   private static final TField I16_VAL_FIELD_DESC = new TField("i16Val", (byte)12, (short)3);
   private static final TField I32_VAL_FIELD_DESC = new TField("i32Val", (byte)12, (short)4);
   private static final TField I64_VAL_FIELD_DESC = new TField("i64Val", (byte)12, (short)5);
   private static final TField DOUBLE_VAL_FIELD_DESC = new TField("doubleVal", (byte)12, (short)6);
   private static final TField STRING_VAL_FIELD_DESC = new TField("stringVal", (byte)12, (short)7);
   private static final TField BINARY_VAL_FIELD_DESC = new TField("binaryVal", (byte)12, (short)8);
   public static final Map metaDataMap;

   public TColumn() {
   }

   public TColumn(_Fields setField, Object value) {
      super(setField, value);
   }

   public TColumn(TColumn other) {
      super(other);
   }

   public TColumn deepCopy() {
      return new TColumn(this);
   }

   public static TColumn boolVal(TBoolColumn value) {
      TColumn x = new TColumn();
      x.setBoolVal(value);
      return x;
   }

   public static TColumn byteVal(TByteColumn value) {
      TColumn x = new TColumn();
      x.setByteVal(value);
      return x;
   }

   public static TColumn i16Val(TI16Column value) {
      TColumn x = new TColumn();
      x.setI16Val(value);
      return x;
   }

   public static TColumn i32Val(TI32Column value) {
      TColumn x = new TColumn();
      x.setI32Val(value);
      return x;
   }

   public static TColumn i64Val(TI64Column value) {
      TColumn x = new TColumn();
      x.setI64Val(value);
      return x;
   }

   public static TColumn doubleVal(TDoubleColumn value) {
      TColumn x = new TColumn();
      x.setDoubleVal(value);
      return x;
   }

   public static TColumn stringVal(TStringColumn value) {
      TColumn x = new TColumn();
      x.setStringVal(value);
      return x;
   }

   public static TColumn binaryVal(TBinaryColumn value) {
      TColumn x = new TColumn();
      x.setBinaryVal(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case BOOL_VAL:
            if (!(value instanceof TBoolColumn)) {
               throw new ClassCastException("Was expecting value of type TBoolColumn for field 'boolVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case BYTE_VAL:
            if (!(value instanceof TByteColumn)) {
               throw new ClassCastException("Was expecting value of type TByteColumn for field 'byteVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case I16_VAL:
            if (!(value instanceof TI16Column)) {
               throw new ClassCastException("Was expecting value of type TI16Column for field 'i16Val', but got " + value.getClass().getSimpleName());
            }
            break;
         case I32_VAL:
            if (!(value instanceof TI32Column)) {
               throw new ClassCastException("Was expecting value of type TI32Column for field 'i32Val', but got " + value.getClass().getSimpleName());
            }
            break;
         case I64_VAL:
            if (!(value instanceof TI64Column)) {
               throw new ClassCastException("Was expecting value of type TI64Column for field 'i64Val', but got " + value.getClass().getSimpleName());
            }
            break;
         case DOUBLE_VAL:
            if (!(value instanceof TDoubleColumn)) {
               throw new ClassCastException("Was expecting value of type TDoubleColumn for field 'doubleVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case STRING_VAL:
            if (!(value instanceof TStringColumn)) {
               throw new ClassCastException("Was expecting value of type TStringColumn for field 'stringVal', but got " + value.getClass().getSimpleName());
            }
            break;
         case BINARY_VAL:
            if (!(value instanceof TBinaryColumn)) {
               throw new ClassCastException("Was expecting value of type TBinaryColumn for field 'binaryVal', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = TColumn._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case BOOL_VAL:
               if (field.type == BOOL_VAL_FIELD_DESC.type) {
                  TBoolColumn boolVal = new TBoolColumn();
                  boolVal.read(iprot);
                  return boolVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case BYTE_VAL:
               if (field.type == BYTE_VAL_FIELD_DESC.type) {
                  TByteColumn byteVal = new TByteColumn();
                  byteVal.read(iprot);
                  return byteVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case I16_VAL:
               if (field.type == I16_VAL_FIELD_DESC.type) {
                  TI16Column i16Val = new TI16Column();
                  i16Val.read(iprot);
                  return i16Val;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case I32_VAL:
               if (field.type == I32_VAL_FIELD_DESC.type) {
                  TI32Column i32Val = new TI32Column();
                  i32Val.read(iprot);
                  return i32Val;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case I64_VAL:
               if (field.type == I64_VAL_FIELD_DESC.type) {
                  TI64Column i64Val = new TI64Column();
                  i64Val.read(iprot);
                  return i64Val;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case DOUBLE_VAL:
               if (field.type == DOUBLE_VAL_FIELD_DESC.type) {
                  TDoubleColumn doubleVal = new TDoubleColumn();
                  doubleVal.read(iprot);
                  return doubleVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case STRING_VAL:
               if (field.type == STRING_VAL_FIELD_DESC.type) {
                  TStringColumn stringVal = new TStringColumn();
                  stringVal.read(iprot);
                  return stringVal;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case BINARY_VAL:
               if (field.type == BINARY_VAL_FIELD_DESC.type) {
                  TBinaryColumn binaryVal = new TBinaryColumn();
                  binaryVal.read(iprot);
                  return binaryVal;
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
            TBoolColumn boolVal = (TBoolColumn)this.value_;
            boolVal.write(oprot);
            return;
         case BYTE_VAL:
            TByteColumn byteVal = (TByteColumn)this.value_;
            byteVal.write(oprot);
            return;
         case I16_VAL:
            TI16Column i16Val = (TI16Column)this.value_;
            i16Val.write(oprot);
            return;
         case I32_VAL:
            TI32Column i32Val = (TI32Column)this.value_;
            i32Val.write(oprot);
            return;
         case I64_VAL:
            TI64Column i64Val = (TI64Column)this.value_;
            i64Val.write(oprot);
            return;
         case DOUBLE_VAL:
            TDoubleColumn doubleVal = (TDoubleColumn)this.value_;
            doubleVal.write(oprot);
            return;
         case STRING_VAL:
            TStringColumn stringVal = (TStringColumn)this.value_;
            stringVal.write(oprot);
            return;
         case BINARY_VAL:
            TBinaryColumn binaryVal = (TBinaryColumn)this.value_;
            binaryVal.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = TColumn._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case BOOL_VAL:
               TBoolColumn boolVal = new TBoolColumn();
               boolVal.read(iprot);
               return boolVal;
            case BYTE_VAL:
               TByteColumn byteVal = new TByteColumn();
               byteVal.read(iprot);
               return byteVal;
            case I16_VAL:
               TI16Column i16Val = new TI16Column();
               i16Val.read(iprot);
               return i16Val;
            case I32_VAL:
               TI32Column i32Val = new TI32Column();
               i32Val.read(iprot);
               return i32Val;
            case I64_VAL:
               TI64Column i64Val = new TI64Column();
               i64Val.read(iprot);
               return i64Val;
            case DOUBLE_VAL:
               TDoubleColumn doubleVal = new TDoubleColumn();
               doubleVal.read(iprot);
               return doubleVal;
            case STRING_VAL:
               TStringColumn stringVal = new TStringColumn();
               stringVal.read(iprot);
               return stringVal;
            case BINARY_VAL:
               TBinaryColumn binaryVal = new TBinaryColumn();
               binaryVal.read(iprot);
               return binaryVal;
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
            TBoolColumn boolVal = (TBoolColumn)this.value_;
            boolVal.write(oprot);
            return;
         case BYTE_VAL:
            TByteColumn byteVal = (TByteColumn)this.value_;
            byteVal.write(oprot);
            return;
         case I16_VAL:
            TI16Column i16Val = (TI16Column)this.value_;
            i16Val.write(oprot);
            return;
         case I32_VAL:
            TI32Column i32Val = (TI32Column)this.value_;
            i32Val.write(oprot);
            return;
         case I64_VAL:
            TI64Column i64Val = (TI64Column)this.value_;
            i64Val.write(oprot);
            return;
         case DOUBLE_VAL:
            TDoubleColumn doubleVal = (TDoubleColumn)this.value_;
            doubleVal.write(oprot);
            return;
         case STRING_VAL:
            TStringColumn stringVal = (TStringColumn)this.value_;
            stringVal.write(oprot);
            return;
         case BINARY_VAL:
            TBinaryColumn binaryVal = (TBinaryColumn)this.value_;
            binaryVal.write(oprot);
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
         case BINARY_VAL:
            return BINARY_VAL_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return TColumn._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TColumn._Fields.findByThriftId(fieldId);
   }

   public TBoolColumn getBoolVal() {
      if (this.getSetField() == TColumn._Fields.BOOL_VAL) {
         return (TBoolColumn)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'boolVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBoolVal(TBoolColumn value) {
      this.setField_ = TColumn._Fields.BOOL_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.BOOL_VAL");
   }

   public TByteColumn getByteVal() {
      if (this.getSetField() == TColumn._Fields.BYTE_VAL) {
         return (TByteColumn)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'byteVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setByteVal(TByteColumn value) {
      this.setField_ = TColumn._Fields.BYTE_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.BYTE_VAL");
   }

   public TI16Column getI16Val() {
      if (this.getSetField() == TColumn._Fields.I16_VAL) {
         return (TI16Column)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i16Val' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI16Val(TI16Column value) {
      this.setField_ = TColumn._Fields.I16_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.I16_VAL");
   }

   public TI32Column getI32Val() {
      if (this.getSetField() == TColumn._Fields.I32_VAL) {
         return (TI32Column)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i32Val' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI32Val(TI32Column value) {
      this.setField_ = TColumn._Fields.I32_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.I32_VAL");
   }

   public TI64Column getI64Val() {
      if (this.getSetField() == TColumn._Fields.I64_VAL) {
         return (TI64Column)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i64Val' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI64Val(TI64Column value) {
      this.setField_ = TColumn._Fields.I64_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.I64_VAL");
   }

   public TDoubleColumn getDoubleVal() {
      if (this.getSetField() == TColumn._Fields.DOUBLE_VAL) {
         return (TDoubleColumn)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'doubleVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDoubleVal(TDoubleColumn value) {
      this.setField_ = TColumn._Fields.DOUBLE_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.DOUBLE_VAL");
   }

   public TStringColumn getStringVal() {
      if (this.getSetField() == TColumn._Fields.STRING_VAL) {
         return (TStringColumn)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'stringVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStringVal(TStringColumn value) {
      this.setField_ = TColumn._Fields.STRING_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING_VAL");
   }

   public TBinaryColumn getBinaryVal() {
      if (this.getSetField() == TColumn._Fields.BINARY_VAL) {
         return (TBinaryColumn)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'binaryVal' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBinaryVal(TBinaryColumn value) {
      this.setField_ = TColumn._Fields.BINARY_VAL;
      this.value_ = Objects.requireNonNull(value, "_Fields.BINARY_VAL");
   }

   public boolean isSetBoolVal() {
      return this.setField_ == TColumn._Fields.BOOL_VAL;
   }

   public boolean isSetByteVal() {
      return this.setField_ == TColumn._Fields.BYTE_VAL;
   }

   public boolean isSetI16Val() {
      return this.setField_ == TColumn._Fields.I16_VAL;
   }

   public boolean isSetI32Val() {
      return this.setField_ == TColumn._Fields.I32_VAL;
   }

   public boolean isSetI64Val() {
      return this.setField_ == TColumn._Fields.I64_VAL;
   }

   public boolean isSetDoubleVal() {
      return this.setField_ == TColumn._Fields.DOUBLE_VAL;
   }

   public boolean isSetStringVal() {
      return this.setField_ == TColumn._Fields.STRING_VAL;
   }

   public boolean isSetBinaryVal() {
      return this.setField_ == TColumn._Fields.BINARY_VAL;
   }

   public boolean equals(Object other) {
      return other instanceof TColumn ? this.equals((TColumn)other) : false;
   }

   public boolean equals(TColumn other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(TColumn other) {
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
      tmpMap.put(TColumn._Fields.BOOL_VAL, new FieldMetaData("boolVal", (byte)2, new StructMetaData((byte)12, TBoolColumn.class)));
      tmpMap.put(TColumn._Fields.BYTE_VAL, new FieldMetaData("byteVal", (byte)2, new StructMetaData((byte)12, TByteColumn.class)));
      tmpMap.put(TColumn._Fields.I16_VAL, new FieldMetaData("i16Val", (byte)2, new StructMetaData((byte)12, TI16Column.class)));
      tmpMap.put(TColumn._Fields.I32_VAL, new FieldMetaData("i32Val", (byte)2, new StructMetaData((byte)12, TI32Column.class)));
      tmpMap.put(TColumn._Fields.I64_VAL, new FieldMetaData("i64Val", (byte)2, new StructMetaData((byte)12, TI64Column.class)));
      tmpMap.put(TColumn._Fields.DOUBLE_VAL, new FieldMetaData("doubleVal", (byte)2, new StructMetaData((byte)12, TDoubleColumn.class)));
      tmpMap.put(TColumn._Fields.STRING_VAL, new FieldMetaData("stringVal", (byte)2, new StructMetaData((byte)12, TStringColumn.class)));
      tmpMap.put(TColumn._Fields.BINARY_VAL, new FieldMetaData("binaryVal", (byte)2, new StructMetaData((byte)12, TBinaryColumn.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TColumn.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      BOOL_VAL((short)1, "boolVal"),
      BYTE_VAL((short)2, "byteVal"),
      I16_VAL((short)3, "i16Val"),
      I32_VAL((short)4, "i32Val"),
      I64_VAL((short)5, "i64Val"),
      DOUBLE_VAL((short)6, "doubleVal"),
      STRING_VAL((short)7, "stringVal"),
      BINARY_VAL((short)8, "binaryVal");

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
            case 8:
               return BINARY_VAL;
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
