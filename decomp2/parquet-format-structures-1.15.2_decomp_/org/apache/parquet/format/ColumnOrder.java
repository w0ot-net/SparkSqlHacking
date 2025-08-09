package org.apache.parquet.format;

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
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.TUnion;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class ColumnOrder extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnOrder");
   private static final TField TYPE__ORDER_FIELD_DESC = new TField("TYPE_ORDER", (byte)12, (short)1);
   public static final Map metaDataMap;

   public ColumnOrder() {
   }

   public ColumnOrder(_Fields setField, Object value) {
      super(setField, value);
   }

   public ColumnOrder(ColumnOrder other) {
      super(other);
   }

   public ColumnOrder deepCopy() {
      return new ColumnOrder(this);
   }

   public static ColumnOrder TYPE_ORDER(TypeDefinedOrder value) {
      ColumnOrder x = new ColumnOrder();
      x.setTYPE_ORDER(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case TYPE__ORDER:
            if (value instanceof TypeDefinedOrder) {
               return;
            }

            throw new ClassCastException("Was expecting value of type TypeDefinedOrder for field 'TYPE_ORDER', but got " + value.getClass().getSimpleName());
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = ColumnOrder._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case TYPE__ORDER:
               if (field.type == TYPE__ORDER_FIELD_DESC.type) {
                  TypeDefinedOrder TYPE_ORDER = new TypeDefinedOrder();
                  TYPE_ORDER.read(iprot);
                  return TYPE_ORDER;
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
         case TYPE__ORDER:
            TypeDefinedOrder TYPE_ORDER = (TypeDefinedOrder)this.value_;
            TYPE_ORDER.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = ColumnOrder._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case TYPE__ORDER:
               TypeDefinedOrder TYPE_ORDER = new TypeDefinedOrder();
               TYPE_ORDER.read(iprot);
               return TYPE_ORDER;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case TYPE__ORDER:
            TypeDefinedOrder TYPE_ORDER = (TypeDefinedOrder)this.value_;
            TYPE_ORDER.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case TYPE__ORDER:
            return TYPE__ORDER_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return ColumnOrder._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnOrder._Fields.findByThriftId(fieldId);
   }

   public TypeDefinedOrder getTYPE_ORDER() {
      if (this.getSetField() == ColumnOrder._Fields.TYPE__ORDER) {
         return (TypeDefinedOrder)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'TYPE_ORDER' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setTYPE_ORDER(TypeDefinedOrder value) {
      this.setField_ = ColumnOrder._Fields.TYPE__ORDER;
      this.value_ = Objects.requireNonNull(value, "_Fields.TYPE__ORDER");
   }

   public boolean isSetTYPE_ORDER() {
      return this.setField_ == ColumnOrder._Fields.TYPE__ORDER;
   }

   public boolean equals(Object other) {
      return other instanceof ColumnOrder ? this.equals((ColumnOrder)other) : false;
   }

   public boolean equals(ColumnOrder other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(ColumnOrder other) {
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
      tmpMap.put(ColumnOrder._Fields.TYPE__ORDER, new FieldMetaData("TYPE_ORDER", (byte)2, new StructMetaData((byte)12, TypeDefinedOrder.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnOrder.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPE__ORDER((short)1, "TYPE_ORDER");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPE__ORDER;
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
