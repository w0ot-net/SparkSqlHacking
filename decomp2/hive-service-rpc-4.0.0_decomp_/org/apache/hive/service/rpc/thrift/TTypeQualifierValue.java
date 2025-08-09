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
public class TTypeQualifierValue extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("TTypeQualifierValue");
   private static final TField I32_VALUE_FIELD_DESC = new TField("i32Value", (byte)8, (short)1);
   private static final TField STRING_VALUE_FIELD_DESC = new TField("stringValue", (byte)11, (short)2);
   public static final Map metaDataMap;

   public TTypeQualifierValue() {
   }

   public TTypeQualifierValue(_Fields setField, Object value) {
      super(setField, value);
   }

   public TTypeQualifierValue(TTypeQualifierValue other) {
      super(other);
   }

   public TTypeQualifierValue deepCopy() {
      return new TTypeQualifierValue(this);
   }

   public static TTypeQualifierValue i32Value(int value) {
      TTypeQualifierValue x = new TTypeQualifierValue();
      x.setI32Value(value);
      return x;
   }

   public static TTypeQualifierValue stringValue(String value) {
      TTypeQualifierValue x = new TTypeQualifierValue();
      x.setStringValue(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case I32_VALUE:
            if (!(value instanceof Integer)) {
               throw new ClassCastException("Was expecting value of type java.lang.Integer for field 'i32Value', but got " + value.getClass().getSimpleName());
            }
            break;
         case STRING_VALUE:
            if (!(value instanceof String)) {
               throw new ClassCastException("Was expecting value of type java.lang.String for field 'stringValue', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = TTypeQualifierValue._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case I32_VALUE:
               if (field.type == I32_VALUE_FIELD_DESC.type) {
                  Integer i32Value = iprot.readI32();
                  return i32Value;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case STRING_VALUE:
               if (field.type == STRING_VALUE_FIELD_DESC.type) {
                  String stringValue = iprot.readString();
                  return stringValue;
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
         case I32_VALUE:
            Integer i32Value = (Integer)this.value_;
            oprot.writeI32(i32Value);
            return;
         case STRING_VALUE:
            String stringValue = (String)this.value_;
            oprot.writeString(stringValue);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = TTypeQualifierValue._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case I32_VALUE:
               Integer i32Value = iprot.readI32();
               return i32Value;
            case STRING_VALUE:
               String stringValue = iprot.readString();
               return stringValue;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case I32_VALUE:
            Integer i32Value = (Integer)this.value_;
            oprot.writeI32(i32Value);
            return;
         case STRING_VALUE:
            String stringValue = (String)this.value_;
            oprot.writeString(stringValue);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case I32_VALUE:
            return I32_VALUE_FIELD_DESC;
         case STRING_VALUE:
            return STRING_VALUE_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return TTypeQualifierValue._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TTypeQualifierValue._Fields.findByThriftId(fieldId);
   }

   public int getI32Value() {
      if (this.getSetField() == TTypeQualifierValue._Fields.I32_VALUE) {
         return (Integer)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'i32Value' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setI32Value(int value) {
      this.setField_ = TTypeQualifierValue._Fields.I32_VALUE;
      this.value_ = value;
   }

   public String getStringValue() {
      if (this.getSetField() == TTypeQualifierValue._Fields.STRING_VALUE) {
         return (String)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'stringValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStringValue(String value) {
      this.setField_ = TTypeQualifierValue._Fields.STRING_VALUE;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING_VALUE");
   }

   public boolean isSetI32Value() {
      return this.setField_ == TTypeQualifierValue._Fields.I32_VALUE;
   }

   public boolean isSetStringValue() {
      return this.setField_ == TTypeQualifierValue._Fields.STRING_VALUE;
   }

   public boolean equals(Object other) {
      return other instanceof TTypeQualifierValue ? this.equals((TTypeQualifierValue)other) : false;
   }

   public boolean equals(TTypeQualifierValue other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(TTypeQualifierValue other) {
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
      tmpMap.put(TTypeQualifierValue._Fields.I32_VALUE, new FieldMetaData("i32Value", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TTypeQualifierValue._Fields.STRING_VALUE, new FieldMetaData("stringValue", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TTypeQualifierValue.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      I32_VALUE((short)1, "i32Value"),
      STRING_VALUE((short)2, "stringValue");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return I32_VALUE;
            case 2:
               return STRING_VALUE;
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
