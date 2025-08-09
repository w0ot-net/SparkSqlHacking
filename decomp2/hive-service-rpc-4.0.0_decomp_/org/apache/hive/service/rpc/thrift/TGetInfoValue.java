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
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TGetInfoValue extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("TGetInfoValue");
   private static final TField STRING_VALUE_FIELD_DESC = new TField("stringValue", (byte)11, (short)1);
   private static final TField SMALL_INT_VALUE_FIELD_DESC = new TField("smallIntValue", (byte)6, (short)2);
   private static final TField INTEGER_BITMASK_FIELD_DESC = new TField("integerBitmask", (byte)8, (short)3);
   private static final TField INTEGER_FLAG_FIELD_DESC = new TField("integerFlag", (byte)8, (short)4);
   private static final TField BINARY_VALUE_FIELD_DESC = new TField("binaryValue", (byte)8, (short)5);
   private static final TField LEN_VALUE_FIELD_DESC = new TField("lenValue", (byte)10, (short)6);
   public static final Map metaDataMap;

   public TGetInfoValue() {
   }

   public TGetInfoValue(_Fields setField, Object value) {
      super(setField, value);
   }

   public TGetInfoValue(TGetInfoValue other) {
      super(other);
   }

   public TGetInfoValue deepCopy() {
      return new TGetInfoValue(this);
   }

   public static TGetInfoValue stringValue(String value) {
      TGetInfoValue x = new TGetInfoValue();
      x.setStringValue(value);
      return x;
   }

   public static TGetInfoValue smallIntValue(short value) {
      TGetInfoValue x = new TGetInfoValue();
      x.setSmallIntValue(value);
      return x;
   }

   public static TGetInfoValue integerBitmask(int value) {
      TGetInfoValue x = new TGetInfoValue();
      x.setIntegerBitmask(value);
      return x;
   }

   public static TGetInfoValue integerFlag(int value) {
      TGetInfoValue x = new TGetInfoValue();
      x.setIntegerFlag(value);
      return x;
   }

   public static TGetInfoValue binaryValue(int value) {
      TGetInfoValue x = new TGetInfoValue();
      x.setBinaryValue(value);
      return x;
   }

   public static TGetInfoValue lenValue(long value) {
      TGetInfoValue x = new TGetInfoValue();
      x.setLenValue(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case STRING_VALUE:
            if (!(value instanceof String)) {
               throw new ClassCastException("Was expecting value of type java.lang.String for field 'stringValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case SMALL_INT_VALUE:
            if (!(value instanceof Short)) {
               throw new ClassCastException("Was expecting value of type java.lang.Short for field 'smallIntValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case INTEGER_BITMASK:
            if (!(value instanceof Integer)) {
               throw new ClassCastException("Was expecting value of type java.lang.Integer for field 'integerBitmask', but got " + value.getClass().getSimpleName());
            }
            break;
         case INTEGER_FLAG:
            if (!(value instanceof Integer)) {
               throw new ClassCastException("Was expecting value of type java.lang.Integer for field 'integerFlag', but got " + value.getClass().getSimpleName());
            }
            break;
         case BINARY_VALUE:
            if (!(value instanceof Integer)) {
               throw new ClassCastException("Was expecting value of type java.lang.Integer for field 'binaryValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case LEN_VALUE:
            if (!(value instanceof Long)) {
               throw new ClassCastException("Was expecting value of type java.lang.Long for field 'lenValue', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = TGetInfoValue._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case STRING_VALUE:
               if (field.type == STRING_VALUE_FIELD_DESC.type) {
                  String stringValue = iprot.readString();
                  return stringValue;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case SMALL_INT_VALUE:
               if (field.type == SMALL_INT_VALUE_FIELD_DESC.type) {
                  Short smallIntValue = iprot.readI16();
                  return smallIntValue;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case INTEGER_BITMASK:
               if (field.type == INTEGER_BITMASK_FIELD_DESC.type) {
                  Integer integerBitmask = iprot.readI32();
                  return integerBitmask;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case INTEGER_FLAG:
               if (field.type == INTEGER_FLAG_FIELD_DESC.type) {
                  Integer integerFlag = iprot.readI32();
                  return integerFlag;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case BINARY_VALUE:
               if (field.type == BINARY_VALUE_FIELD_DESC.type) {
                  Integer binaryValue = iprot.readI32();
                  return binaryValue;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case LEN_VALUE:
               if (field.type == LEN_VALUE_FIELD_DESC.type) {
                  Long lenValue = iprot.readI64();
                  return lenValue;
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
         case STRING_VALUE:
            String stringValue = (String)this.value_;
            oprot.writeString(stringValue);
            return;
         case SMALL_INT_VALUE:
            Short smallIntValue = (Short)this.value_;
            oprot.writeI16(smallIntValue);
            return;
         case INTEGER_BITMASK:
            Integer integerBitmask = (Integer)this.value_;
            oprot.writeI32(integerBitmask);
            return;
         case INTEGER_FLAG:
            Integer integerFlag = (Integer)this.value_;
            oprot.writeI32(integerFlag);
            return;
         case BINARY_VALUE:
            Integer binaryValue = (Integer)this.value_;
            oprot.writeI32(binaryValue);
            return;
         case LEN_VALUE:
            Long lenValue = (Long)this.value_;
            oprot.writeI64(lenValue);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = TGetInfoValue._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case STRING_VALUE:
               String stringValue = iprot.readString();
               return stringValue;
            case SMALL_INT_VALUE:
               Short smallIntValue = iprot.readI16();
               return smallIntValue;
            case INTEGER_BITMASK:
               Integer integerBitmask = iprot.readI32();
               return integerBitmask;
            case INTEGER_FLAG:
               Integer integerFlag = iprot.readI32();
               return integerFlag;
            case BINARY_VALUE:
               Integer binaryValue = iprot.readI32();
               return binaryValue;
            case LEN_VALUE:
               Long lenValue = iprot.readI64();
               return lenValue;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case STRING_VALUE:
            String stringValue = (String)this.value_;
            oprot.writeString(stringValue);
            return;
         case SMALL_INT_VALUE:
            Short smallIntValue = (Short)this.value_;
            oprot.writeI16(smallIntValue);
            return;
         case INTEGER_BITMASK:
            Integer integerBitmask = (Integer)this.value_;
            oprot.writeI32(integerBitmask);
            return;
         case INTEGER_FLAG:
            Integer integerFlag = (Integer)this.value_;
            oprot.writeI32(integerFlag);
            return;
         case BINARY_VALUE:
            Integer binaryValue = (Integer)this.value_;
            oprot.writeI32(binaryValue);
            return;
         case LEN_VALUE:
            Long lenValue = (Long)this.value_;
            oprot.writeI64(lenValue);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case STRING_VALUE:
            return STRING_VALUE_FIELD_DESC;
         case SMALL_INT_VALUE:
            return SMALL_INT_VALUE_FIELD_DESC;
         case INTEGER_BITMASK:
            return INTEGER_BITMASK_FIELD_DESC;
         case INTEGER_FLAG:
            return INTEGER_FLAG_FIELD_DESC;
         case BINARY_VALUE:
            return BINARY_VALUE_FIELD_DESC;
         case LEN_VALUE:
            return LEN_VALUE_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return TGetInfoValue._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TGetInfoValue._Fields.findByThriftId(fieldId);
   }

   public String getStringValue() {
      if (this.getSetField() == TGetInfoValue._Fields.STRING_VALUE) {
         return (String)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'stringValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStringValue(String value) {
      this.setField_ = TGetInfoValue._Fields.STRING_VALUE;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING_VALUE");
   }

   public short getSmallIntValue() {
      if (this.getSetField() == TGetInfoValue._Fields.SMALL_INT_VALUE) {
         return (Short)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'smallIntValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setSmallIntValue(short value) {
      this.setField_ = TGetInfoValue._Fields.SMALL_INT_VALUE;
      this.value_ = value;
   }

   public int getIntegerBitmask() {
      if (this.getSetField() == TGetInfoValue._Fields.INTEGER_BITMASK) {
         return (Integer)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'integerBitmask' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setIntegerBitmask(int value) {
      this.setField_ = TGetInfoValue._Fields.INTEGER_BITMASK;
      this.value_ = value;
   }

   public int getIntegerFlag() {
      if (this.getSetField() == TGetInfoValue._Fields.INTEGER_FLAG) {
         return (Integer)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'integerFlag' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setIntegerFlag(int value) {
      this.setField_ = TGetInfoValue._Fields.INTEGER_FLAG;
      this.value_ = value;
   }

   public int getBinaryValue() {
      if (this.getSetField() == TGetInfoValue._Fields.BINARY_VALUE) {
         return (Integer)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'binaryValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBinaryValue(int value) {
      this.setField_ = TGetInfoValue._Fields.BINARY_VALUE;
      this.value_ = value;
   }

   public long getLenValue() {
      if (this.getSetField() == TGetInfoValue._Fields.LEN_VALUE) {
         return (Long)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'lenValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setLenValue(long value) {
      this.setField_ = TGetInfoValue._Fields.LEN_VALUE;
      this.value_ = value;
   }

   public boolean isSetStringValue() {
      return this.setField_ == TGetInfoValue._Fields.STRING_VALUE;
   }

   public boolean isSetSmallIntValue() {
      return this.setField_ == TGetInfoValue._Fields.SMALL_INT_VALUE;
   }

   public boolean isSetIntegerBitmask() {
      return this.setField_ == TGetInfoValue._Fields.INTEGER_BITMASK;
   }

   public boolean isSetIntegerFlag() {
      return this.setField_ == TGetInfoValue._Fields.INTEGER_FLAG;
   }

   public boolean isSetBinaryValue() {
      return this.setField_ == TGetInfoValue._Fields.BINARY_VALUE;
   }

   public boolean isSetLenValue() {
      return this.setField_ == TGetInfoValue._Fields.LEN_VALUE;
   }

   public boolean equals(Object other) {
      return other instanceof TGetInfoValue ? this.equals((TGetInfoValue)other) : false;
   }

   public boolean equals(TGetInfoValue other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(TGetInfoValue other) {
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
      tmpMap.put(TGetInfoValue._Fields.STRING_VALUE, new FieldMetaData("stringValue", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TGetInfoValue._Fields.SMALL_INT_VALUE, new FieldMetaData("smallIntValue", (byte)2, new FieldValueMetaData((byte)6)));
      tmpMap.put(TGetInfoValue._Fields.INTEGER_BITMASK, new FieldMetaData("integerBitmask", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TGetInfoValue._Fields.INTEGER_FLAG, new FieldMetaData("integerFlag", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TGetInfoValue._Fields.BINARY_VALUE, new FieldMetaData("binaryValue", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TGetInfoValue._Fields.LEN_VALUE, new FieldMetaData("lenValue", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetInfoValue.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STRING_VALUE((short)1, "stringValue"),
      SMALL_INT_VALUE((short)2, "smallIntValue"),
      INTEGER_BITMASK((short)3, "integerBitmask"),
      INTEGER_FLAG((short)4, "integerFlag"),
      BINARY_VALUE((short)5, "binaryValue"),
      LEN_VALUE((short)6, "lenValue");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STRING_VALUE;
            case 2:
               return SMALL_INT_VALUE;
            case 3:
               return INTEGER_BITMASK;
            case 4:
               return INTEGER_FLAG;
            case 5:
               return BINARY_VALUE;
            case 6:
               return LEN_VALUE;
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
