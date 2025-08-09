package org.apache.hadoop.hive.metastore.api;

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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.transport.TIOStreamTransport;

public class FireEventRequestData extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("FireEventRequestData");
   private static final TField INSERT_DATA_FIELD_DESC = new TField("insertData", (byte)12, (short)1);
   public static final Map metaDataMap;

   public FireEventRequestData() {
   }

   public FireEventRequestData(_Fields setField, Object value) {
      super(setField, value);
   }

   public FireEventRequestData(FireEventRequestData other) {
      super(other);
   }

   public FireEventRequestData deepCopy() {
      return new FireEventRequestData(this);
   }

   public static FireEventRequestData insertData(InsertEventRequestData value) {
      FireEventRequestData x = new FireEventRequestData();
      x.setInsertData(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case INSERT_DATA:
            if (value instanceof InsertEventRequestData) {
               return;
            }

            throw new ClassCastException("Was expecting value of type InsertEventRequestData for field 'insertData', but got " + value.getClass().getSimpleName());
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = FireEventRequestData._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case INSERT_DATA:
               if (field.type == INSERT_DATA_FIELD_DESC.type) {
                  InsertEventRequestData insertData = new InsertEventRequestData();
                  insertData.read(iprot);
                  return insertData;
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
         case INSERT_DATA:
            InsertEventRequestData insertData = (InsertEventRequestData)this.value_;
            insertData.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = FireEventRequestData._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case INSERT_DATA:
               InsertEventRequestData insertData = new InsertEventRequestData();
               insertData.read(iprot);
               return insertData;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case INSERT_DATA:
            InsertEventRequestData insertData = (InsertEventRequestData)this.value_;
            insertData.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case INSERT_DATA:
            return INSERT_DATA_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return FireEventRequestData._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return FireEventRequestData._Fields.findByThriftId(fieldId);
   }

   public InsertEventRequestData getInsertData() {
      if (this.getSetField() == FireEventRequestData._Fields.INSERT_DATA) {
         return (InsertEventRequestData)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'insertData' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setInsertData(InsertEventRequestData value) {
      this.setField_ = FireEventRequestData._Fields.INSERT_DATA;
      this.value_ = Objects.requireNonNull(value, "_Fields.INSERT_DATA");
   }

   public boolean isSetInsertData() {
      return this.setField_ == FireEventRequestData._Fields.INSERT_DATA;
   }

   public boolean equals(Object other) {
      return other instanceof FireEventRequestData ? this.equals((FireEventRequestData)other) : false;
   }

   public boolean equals(FireEventRequestData other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(FireEventRequestData other) {
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
      tmpMap.put(FireEventRequestData._Fields.INSERT_DATA, new FieldMetaData("insertData", (byte)2, new StructMetaData((byte)12, InsertEventRequestData.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(FireEventRequestData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      INSERT_DATA((short)1, "insertData");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return INSERT_DATA;
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
