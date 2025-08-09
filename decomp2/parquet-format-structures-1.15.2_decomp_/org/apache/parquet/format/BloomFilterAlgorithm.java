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

public class BloomFilterAlgorithm extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("BloomFilterAlgorithm");
   private static final TField BLOCK_FIELD_DESC = new TField("BLOCK", (byte)12, (short)1);
   public static final Map metaDataMap;

   public BloomFilterAlgorithm() {
   }

   public BloomFilterAlgorithm(_Fields setField, Object value) {
      super(setField, value);
   }

   public BloomFilterAlgorithm(BloomFilterAlgorithm other) {
      super(other);
   }

   public BloomFilterAlgorithm deepCopy() {
      return new BloomFilterAlgorithm(this);
   }

   public static BloomFilterAlgorithm BLOCK(SplitBlockAlgorithm value) {
      BloomFilterAlgorithm x = new BloomFilterAlgorithm();
      x.setBLOCK(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case BLOCK:
            if (value instanceof SplitBlockAlgorithm) {
               return;
            }

            throw new ClassCastException("Was expecting value of type SplitBlockAlgorithm for field 'BLOCK', but got " + value.getClass().getSimpleName());
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = BloomFilterAlgorithm._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case BLOCK:
               if (field.type == BLOCK_FIELD_DESC.type) {
                  SplitBlockAlgorithm BLOCK = new SplitBlockAlgorithm();
                  BLOCK.read(iprot);
                  return BLOCK;
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
         case BLOCK:
            SplitBlockAlgorithm BLOCK = (SplitBlockAlgorithm)this.value_;
            BLOCK.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = BloomFilterAlgorithm._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case BLOCK:
               SplitBlockAlgorithm BLOCK = new SplitBlockAlgorithm();
               BLOCK.read(iprot);
               return BLOCK;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case BLOCK:
            SplitBlockAlgorithm BLOCK = (SplitBlockAlgorithm)this.value_;
            BLOCK.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case BLOCK:
            return BLOCK_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return BloomFilterAlgorithm._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return BloomFilterAlgorithm._Fields.findByThriftId(fieldId);
   }

   public SplitBlockAlgorithm getBLOCK() {
      if (this.getSetField() == BloomFilterAlgorithm._Fields.BLOCK) {
         return (SplitBlockAlgorithm)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'BLOCK' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setBLOCK(SplitBlockAlgorithm value) {
      this.setField_ = BloomFilterAlgorithm._Fields.BLOCK;
      this.value_ = Objects.requireNonNull(value, "_Fields.BLOCK");
   }

   public boolean isSetBLOCK() {
      return this.setField_ == BloomFilterAlgorithm._Fields.BLOCK;
   }

   public boolean equals(Object other) {
      return other instanceof BloomFilterAlgorithm ? this.equals((BloomFilterAlgorithm)other) : false;
   }

   public boolean equals(BloomFilterAlgorithm other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(BloomFilterAlgorithm other) {
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
      tmpMap.put(BloomFilterAlgorithm._Fields.BLOCK, new FieldMetaData("BLOCK", (byte)2, new StructMetaData((byte)12, SplitBlockAlgorithm.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(BloomFilterAlgorithm.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      BLOCK((short)1, "BLOCK");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return BLOCK;
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
