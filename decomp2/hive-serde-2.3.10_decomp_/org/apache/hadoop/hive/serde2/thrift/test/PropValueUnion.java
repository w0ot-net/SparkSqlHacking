package org.apache.hadoop.hive.serde2.thrift.test;

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
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TEnum;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TIOStreamTransport;

public class PropValueUnion extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("PropValueUnion");
   private static final TField INT_VALUE_FIELD_DESC = new TField("intValue", (byte)8, (short)1);
   private static final TField LONG_VALUE_FIELD_DESC = new TField("longValue", (byte)10, (short)2);
   private static final TField STRING_VALUE_FIELD_DESC = new TField("stringValue", (byte)11, (short)3);
   private static final TField DOUBLE_VALUE_FIELD_DESC = new TField("doubleValue", (byte)4, (short)4);
   private static final TField FLAG_FIELD_DESC = new TField("flag", (byte)2, (short)5);
   private static final TField L_STRING_FIELD_DESC = new TField("lString", (byte)15, (short)6);
   private static final TField UNION_MSTRING_STRING_FIELD_DESC = new TField("unionMStringString", (byte)13, (short)7);
   public static final Map metaDataMap;

   public PropValueUnion() {
   }

   public PropValueUnion(_Fields setField, Object value) {
      super(setField, value);
   }

   public PropValueUnion(PropValueUnion other) {
      super(other);
   }

   public PropValueUnion deepCopy() {
      return new PropValueUnion(this);
   }

   public static PropValueUnion intValue(int value) {
      PropValueUnion x = new PropValueUnion();
      x.setIntValue(value);
      return x;
   }

   public static PropValueUnion longValue(long value) {
      PropValueUnion x = new PropValueUnion();
      x.setLongValue(value);
      return x;
   }

   public static PropValueUnion stringValue(String value) {
      PropValueUnion x = new PropValueUnion();
      x.setStringValue(value);
      return x;
   }

   public static PropValueUnion doubleValue(double value) {
      PropValueUnion x = new PropValueUnion();
      x.setDoubleValue(value);
      return x;
   }

   public static PropValueUnion flag(boolean value) {
      PropValueUnion x = new PropValueUnion();
      x.setFlag(value);
      return x;
   }

   public static PropValueUnion lString(List value) {
      PropValueUnion x = new PropValueUnion();
      x.setLString(value);
      return x;
   }

   public static PropValueUnion unionMStringString(Map value) {
      PropValueUnion x = new PropValueUnion();
      x.setUnionMStringString(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case INT_VALUE:
            if (!(value instanceof Integer)) {
               throw new ClassCastException("Was expecting value of type java.lang.Integer for field 'intValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case LONG_VALUE:
            if (!(value instanceof Long)) {
               throw new ClassCastException("Was expecting value of type java.lang.Long for field 'longValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case STRING_VALUE:
            if (!(value instanceof String)) {
               throw new ClassCastException("Was expecting value of type java.lang.String for field 'stringValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case DOUBLE_VALUE:
            if (!(value instanceof Double)) {
               throw new ClassCastException("Was expecting value of type java.lang.Double for field 'doubleValue', but got " + value.getClass().getSimpleName());
            }
            break;
         case FLAG:
            if (!(value instanceof Boolean)) {
               throw new ClassCastException("Was expecting value of type java.lang.Boolean for field 'flag', but got " + value.getClass().getSimpleName());
            }
            break;
         case L_STRING:
            if (!(value instanceof List)) {
               throw new ClassCastException("Was expecting value of type java.util.List<java.lang.String> for field 'lString', but got " + value.getClass().getSimpleName());
            }
            break;
         case UNION_MSTRING_STRING:
            if (!(value instanceof Map)) {
               throw new ClassCastException("Was expecting value of type java.util.Map<java.lang.String,java.lang.String> for field 'unionMStringString', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = PropValueUnion._Fields.findByThriftId(field.id);
      if (setField == null) {
         TProtocolUtil.skip(iprot, field.type);
         return null;
      } else {
         switch (setField) {
            case INT_VALUE:
               if (field.type == INT_VALUE_FIELD_DESC.type) {
                  Integer intValue = iprot.readI32();
                  return intValue;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case LONG_VALUE:
               if (field.type == LONG_VALUE_FIELD_DESC.type) {
                  Long longValue = iprot.readI64();
                  return longValue;
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
            case DOUBLE_VALUE:
               if (field.type == DOUBLE_VALUE_FIELD_DESC.type) {
                  Double doubleValue = iprot.readDouble();
                  return doubleValue;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case FLAG:
               if (field.type == FLAG_FIELD_DESC.type) {
                  Boolean flag = iprot.readBool();
                  return flag;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case L_STRING:
               if (field.type != L_STRING_FIELD_DESC.type) {
                  TProtocolUtil.skip(iprot, field.type);
                  return null;
               }

               TList _list0 = iprot.readListBegin();
               List<String> lString = new ArrayList(_list0.size);

               for(int _i2 = 0; _i2 < _list0.size; ++_i2) {
                  String _elem1 = iprot.readString();
                  lString.add(_elem1);
               }

               iprot.readListEnd();
               return lString;
            case UNION_MSTRING_STRING:
               if (field.type != UNION_MSTRING_STRING_FIELD_DESC.type) {
                  TProtocolUtil.skip(iprot, field.type);
                  return null;
               }

               TMap _map3 = iprot.readMapBegin();
               Map<String, String> unionMStringString = new HashMap(2 * _map3.size);

               for(int _i6 = 0; _i6 < _map3.size; ++_i6) {
                  String _key4 = iprot.readString();
                  String _val5 = iprot.readString();
                  unionMStringString.put(_key4, _val5);
               }

               iprot.readMapEnd();
               return unionMStringString;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      }
   }

   protected void standardSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case INT_VALUE:
            Integer intValue = (Integer)this.value_;
            oprot.writeI32(intValue);
            return;
         case LONG_VALUE:
            Long longValue = (Long)this.value_;
            oprot.writeI64(longValue);
            return;
         case STRING_VALUE:
            String stringValue = (String)this.value_;
            oprot.writeString(stringValue);
            return;
         case DOUBLE_VALUE:
            Double doubleValue = (Double)this.value_;
            oprot.writeDouble(doubleValue);
            return;
         case FLAG:
            Boolean flag = (Boolean)this.value_;
            oprot.writeBool(flag);
            return;
         case L_STRING:
            List<String> lString = (List)this.value_;
            oprot.writeListBegin(new TList((byte)11, lString.size()));

            for(String _iter7 : lString) {
               oprot.writeString(_iter7);
            }

            oprot.writeListEnd();
            return;
         case UNION_MSTRING_STRING:
            Map<String, String> unionMStringString = (Map)this.value_;
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, unionMStringString.size()));

            for(Map.Entry _iter8 : unionMStringString.entrySet()) {
               oprot.writeString((String)_iter8.getKey());
               oprot.writeString((String)_iter8.getValue());
            }

            oprot.writeMapEnd();
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = PropValueUnion._Fields.findByThriftId(fieldID);
      if (setField == null) {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      } else {
         switch (setField) {
            case INT_VALUE:
               Integer intValue = iprot.readI32();
               return intValue;
            case LONG_VALUE:
               Long longValue = iprot.readI64();
               return longValue;
            case STRING_VALUE:
               String stringValue = iprot.readString();
               return stringValue;
            case DOUBLE_VALUE:
               Double doubleValue = iprot.readDouble();
               return doubleValue;
            case FLAG:
               Boolean flag = iprot.readBool();
               return flag;
            case L_STRING:
               TList _list9 = iprot.readListBegin();
               List<String> lString = new ArrayList(_list9.size);

               for(int _i11 = 0; _i11 < _list9.size; ++_i11) {
                  String _elem10 = iprot.readString();
                  lString.add(_elem10);
               }

               iprot.readListEnd();
               return lString;
            case UNION_MSTRING_STRING:
               TMap _map12 = iprot.readMapBegin();
               Map<String, String> unionMStringString = new HashMap(2 * _map12.size);

               for(int _i15 = 0; _i15 < _map12.size; ++_i15) {
                  String _key13 = iprot.readString();
                  String _val14 = iprot.readString();
                  unionMStringString.put(_key13, _val14);
               }

               iprot.readMapEnd();
               return unionMStringString;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case INT_VALUE:
            Integer intValue = (Integer)this.value_;
            oprot.writeI32(intValue);
            return;
         case LONG_VALUE:
            Long longValue = (Long)this.value_;
            oprot.writeI64(longValue);
            return;
         case STRING_VALUE:
            String stringValue = (String)this.value_;
            oprot.writeString(stringValue);
            return;
         case DOUBLE_VALUE:
            Double doubleValue = (Double)this.value_;
            oprot.writeDouble(doubleValue);
            return;
         case FLAG:
            Boolean flag = (Boolean)this.value_;
            oprot.writeBool(flag);
            return;
         case L_STRING:
            List<String> lString = (List)this.value_;
            oprot.writeListBegin(new TList((byte)11, lString.size()));

            for(String _iter16 : lString) {
               oprot.writeString(_iter16);
            }

            oprot.writeListEnd();
            return;
         case UNION_MSTRING_STRING:
            Map<String, String> unionMStringString = (Map)this.value_;
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, unionMStringString.size()));

            for(Map.Entry _iter17 : unionMStringString.entrySet()) {
               oprot.writeString((String)_iter17.getKey());
               oprot.writeString((String)_iter17.getValue());
            }

            oprot.writeMapEnd();
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case INT_VALUE:
            return INT_VALUE_FIELD_DESC;
         case LONG_VALUE:
            return LONG_VALUE_FIELD_DESC;
         case STRING_VALUE:
            return STRING_VALUE_FIELD_DESC;
         case DOUBLE_VALUE:
            return DOUBLE_VALUE_FIELD_DESC;
         case FLAG:
            return FLAG_FIELD_DESC;
         case L_STRING:
            return L_STRING_FIELD_DESC;
         case UNION_MSTRING_STRING:
            return UNION_MSTRING_STRING_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return PropValueUnion._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PropValueUnion._Fields.findByThriftId(fieldId);
   }

   public int getIntValue() {
      if (this.getSetField() == PropValueUnion._Fields.INT_VALUE) {
         return (Integer)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'intValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setIntValue(int value) {
      this.setField_ = PropValueUnion._Fields.INT_VALUE;
      this.value_ = value;
   }

   public long getLongValue() {
      if (this.getSetField() == PropValueUnion._Fields.LONG_VALUE) {
         return (Long)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'longValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setLongValue(long value) {
      this.setField_ = PropValueUnion._Fields.LONG_VALUE;
      this.value_ = value;
   }

   public String getStringValue() {
      if (this.getSetField() == PropValueUnion._Fields.STRING_VALUE) {
         return (String)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'stringValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setStringValue(String value) {
      this.setField_ = PropValueUnion._Fields.STRING_VALUE;
      this.value_ = Objects.requireNonNull(value, "_Fields.STRING_VALUE");
   }

   public double getDoubleValue() {
      if (this.getSetField() == PropValueUnion._Fields.DOUBLE_VALUE) {
         return (Double)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'doubleValue' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setDoubleValue(double value) {
      this.setField_ = PropValueUnion._Fields.DOUBLE_VALUE;
      this.value_ = value;
   }

   public boolean getFlag() {
      if (this.getSetField() == PropValueUnion._Fields.FLAG) {
         return (Boolean)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'flag' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setFlag(boolean value) {
      this.setField_ = PropValueUnion._Fields.FLAG;
      this.value_ = value;
   }

   public List getLString() {
      if (this.getSetField() == PropValueUnion._Fields.L_STRING) {
         return (List)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'lString' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setLString(List value) {
      this.setField_ = PropValueUnion._Fields.L_STRING;
      this.value_ = Objects.requireNonNull(value, "_Fields.L_STRING");
   }

   public Map getUnionMStringString() {
      if (this.getSetField() == PropValueUnion._Fields.UNION_MSTRING_STRING) {
         return (Map)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'unionMStringString' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setUnionMStringString(Map value) {
      this.setField_ = PropValueUnion._Fields.UNION_MSTRING_STRING;
      this.value_ = Objects.requireNonNull(value, "_Fields.UNION_MSTRING_STRING");
   }

   public boolean isSetIntValue() {
      return this.setField_ == PropValueUnion._Fields.INT_VALUE;
   }

   public boolean isSetLongValue() {
      return this.setField_ == PropValueUnion._Fields.LONG_VALUE;
   }

   public boolean isSetStringValue() {
      return this.setField_ == PropValueUnion._Fields.STRING_VALUE;
   }

   public boolean isSetDoubleValue() {
      return this.setField_ == PropValueUnion._Fields.DOUBLE_VALUE;
   }

   public boolean isSetFlag() {
      return this.setField_ == PropValueUnion._Fields.FLAG;
   }

   public boolean isSetLString() {
      return this.setField_ == PropValueUnion._Fields.L_STRING;
   }

   public boolean isSetUnionMStringString() {
      return this.setField_ == PropValueUnion._Fields.UNION_MSTRING_STRING;
   }

   public boolean equals(Object other) {
      return other instanceof PropValueUnion ? this.equals((PropValueUnion)other) : false;
   }

   public boolean equals(PropValueUnion other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(PropValueUnion other) {
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
      tmpMap.put(PropValueUnion._Fields.INT_VALUE, new FieldMetaData("intValue", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(PropValueUnion._Fields.LONG_VALUE, new FieldMetaData("longValue", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(PropValueUnion._Fields.STRING_VALUE, new FieldMetaData("stringValue", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(PropValueUnion._Fields.DOUBLE_VALUE, new FieldMetaData("doubleValue", (byte)2, new FieldValueMetaData((byte)4)));
      tmpMap.put(PropValueUnion._Fields.FLAG, new FieldMetaData("flag", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(PropValueUnion._Fields.L_STRING, new FieldMetaData("lString", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(PropValueUnion._Fields.UNION_MSTRING_STRING, new FieldMetaData("unionMStringString", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PropValueUnion.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      INT_VALUE((short)1, "intValue"),
      LONG_VALUE((short)2, "longValue"),
      STRING_VALUE((short)3, "stringValue"),
      DOUBLE_VALUE((short)4, "doubleValue"),
      FLAG((short)5, "flag"),
      L_STRING((short)6, "lString"),
      UNION_MSTRING_STRING((short)7, "unionMStringString");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return INT_VALUE;
            case 2:
               return LONG_VALUE;
            case 3:
               return STRING_VALUE;
            case 4:
               return DOUBLE_VALUE;
            case 5:
               return FLAG;
            case 6:
               return L_STRING;
            case 7:
               return UNION_MSTRING_STRING;
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
