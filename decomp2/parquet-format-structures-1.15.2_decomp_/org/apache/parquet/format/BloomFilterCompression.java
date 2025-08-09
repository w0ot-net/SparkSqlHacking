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

public class BloomFilterCompression extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("BloomFilterCompression");
   private static final TField UNCOMPRESSED_FIELD_DESC = new TField("UNCOMPRESSED", (byte)12, (short)1);
   public static final Map metaDataMap;

   public BloomFilterCompression() {
   }

   public BloomFilterCompression(_Fields setField, Object value) {
      super(setField, value);
   }

   public BloomFilterCompression(BloomFilterCompression other) {
      super(other);
   }

   public BloomFilterCompression deepCopy() {
      return new BloomFilterCompression(this);
   }

   public static BloomFilterCompression UNCOMPRESSED(Uncompressed value) {
      BloomFilterCompression x = new BloomFilterCompression();
      x.setUNCOMPRESSED(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case UNCOMPRESSED:
            if (value instanceof Uncompressed) {
               return;
            }

            throw new ClassCastException("Was expecting value of type Uncompressed for field 'UNCOMPRESSED', but got " + value.getClass().getSimpleName());
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = BloomFilterCompression._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case UNCOMPRESSED:
               if (field.type == UNCOMPRESSED_FIELD_DESC.type) {
                  Uncompressed UNCOMPRESSED = new Uncompressed();
                  UNCOMPRESSED.read(iprot);
                  return UNCOMPRESSED;
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
         case UNCOMPRESSED:
            Uncompressed UNCOMPRESSED = (Uncompressed)this.value_;
            UNCOMPRESSED.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = BloomFilterCompression._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case UNCOMPRESSED:
               Uncompressed UNCOMPRESSED = new Uncompressed();
               UNCOMPRESSED.read(iprot);
               return UNCOMPRESSED;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case UNCOMPRESSED:
            Uncompressed UNCOMPRESSED = (Uncompressed)this.value_;
            UNCOMPRESSED.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case UNCOMPRESSED:
            return UNCOMPRESSED_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return BloomFilterCompression._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return BloomFilterCompression._Fields.findByThriftId(fieldId);
   }

   public Uncompressed getUNCOMPRESSED() {
      if (this.getSetField() == BloomFilterCompression._Fields.UNCOMPRESSED) {
         return (Uncompressed)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'UNCOMPRESSED' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setUNCOMPRESSED(Uncompressed value) {
      this.setField_ = BloomFilterCompression._Fields.UNCOMPRESSED;
      this.value_ = Objects.requireNonNull(value, "_Fields.UNCOMPRESSED");
   }

   public boolean isSetUNCOMPRESSED() {
      return this.setField_ == BloomFilterCompression._Fields.UNCOMPRESSED;
   }

   public boolean equals(Object other) {
      return other instanceof BloomFilterCompression ? this.equals((BloomFilterCompression)other) : false;
   }

   public boolean equals(BloomFilterCompression other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(BloomFilterCompression other) {
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
      tmpMap.put(BloomFilterCompression._Fields.UNCOMPRESSED, new FieldMetaData("UNCOMPRESSED", (byte)2, new StructMetaData((byte)12, Uncompressed.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(BloomFilterCompression.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      UNCOMPRESSED((short)1, "UNCOMPRESSED");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return UNCOMPRESSED;
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
