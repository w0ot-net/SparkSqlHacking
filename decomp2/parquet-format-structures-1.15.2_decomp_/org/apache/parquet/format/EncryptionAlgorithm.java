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

public class EncryptionAlgorithm extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("EncryptionAlgorithm");
   private static final TField AES__GCM__V1_FIELD_DESC = new TField("AES_GCM_V1", (byte)12, (short)1);
   private static final TField AES__GCM__CTR__V1_FIELD_DESC = new TField("AES_GCM_CTR_V1", (byte)12, (short)2);
   public static final Map metaDataMap;

   public EncryptionAlgorithm() {
   }

   public EncryptionAlgorithm(_Fields setField, Object value) {
      super(setField, value);
   }

   public EncryptionAlgorithm(EncryptionAlgorithm other) {
      super(other);
   }

   public EncryptionAlgorithm deepCopy() {
      return new EncryptionAlgorithm(this);
   }

   public static EncryptionAlgorithm AES_GCM_V1(AesGcmV1 value) {
      EncryptionAlgorithm x = new EncryptionAlgorithm();
      x.setAES_GCM_V1(value);
      return x;
   }

   public static EncryptionAlgorithm AES_GCM_CTR_V1(AesGcmCtrV1 value) {
      EncryptionAlgorithm x = new EncryptionAlgorithm();
      x.setAES_GCM_CTR_V1(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case AES__GCM__V1:
            if (!(value instanceof AesGcmV1)) {
               throw new ClassCastException("Was expecting value of type AesGcmV1 for field 'AES_GCM_V1', but got " + value.getClass().getSimpleName());
            }
            break;
         case AES__GCM__CTR__V1:
            if (!(value instanceof AesGcmCtrV1)) {
               throw new ClassCastException("Was expecting value of type AesGcmCtrV1 for field 'AES_GCM_CTR_V1', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = EncryptionAlgorithm._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case AES__GCM__V1:
               if (field.type == AES__GCM__V1_FIELD_DESC.type) {
                  AesGcmV1 AES_GCM_V1 = new AesGcmV1();
                  AES_GCM_V1.read(iprot);
                  return AES_GCM_V1;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case AES__GCM__CTR__V1:
               if (field.type == AES__GCM__CTR__V1_FIELD_DESC.type) {
                  AesGcmCtrV1 AES_GCM_CTR_V1 = new AesGcmCtrV1();
                  AES_GCM_CTR_V1.read(iprot);
                  return AES_GCM_CTR_V1;
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
         case AES__GCM__V1:
            AesGcmV1 AES_GCM_V1 = (AesGcmV1)this.value_;
            AES_GCM_V1.write(oprot);
            return;
         case AES__GCM__CTR__V1:
            AesGcmCtrV1 AES_GCM_CTR_V1 = (AesGcmCtrV1)this.value_;
            AES_GCM_CTR_V1.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = EncryptionAlgorithm._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case AES__GCM__V1:
               AesGcmV1 AES_GCM_V1 = new AesGcmV1();
               AES_GCM_V1.read(iprot);
               return AES_GCM_V1;
            case AES__GCM__CTR__V1:
               AesGcmCtrV1 AES_GCM_CTR_V1 = new AesGcmCtrV1();
               AES_GCM_CTR_V1.read(iprot);
               return AES_GCM_CTR_V1;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case AES__GCM__V1:
            AesGcmV1 AES_GCM_V1 = (AesGcmV1)this.value_;
            AES_GCM_V1.write(oprot);
            return;
         case AES__GCM__CTR__V1:
            AesGcmCtrV1 AES_GCM_CTR_V1 = (AesGcmCtrV1)this.value_;
            AES_GCM_CTR_V1.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case AES__GCM__V1:
            return AES__GCM__V1_FIELD_DESC;
         case AES__GCM__CTR__V1:
            return AES__GCM__CTR__V1_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return EncryptionAlgorithm._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return EncryptionAlgorithm._Fields.findByThriftId(fieldId);
   }

   public AesGcmV1 getAES_GCM_V1() {
      if (this.getSetField() == EncryptionAlgorithm._Fields.AES__GCM__V1) {
         return (AesGcmV1)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'AES_GCM_V1' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setAES_GCM_V1(AesGcmV1 value) {
      this.setField_ = EncryptionAlgorithm._Fields.AES__GCM__V1;
      this.value_ = Objects.requireNonNull(value, "_Fields.AES__GCM__V1");
   }

   public AesGcmCtrV1 getAES_GCM_CTR_V1() {
      if (this.getSetField() == EncryptionAlgorithm._Fields.AES__GCM__CTR__V1) {
         return (AesGcmCtrV1)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'AES_GCM_CTR_V1' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setAES_GCM_CTR_V1(AesGcmCtrV1 value) {
      this.setField_ = EncryptionAlgorithm._Fields.AES__GCM__CTR__V1;
      this.value_ = Objects.requireNonNull(value, "_Fields.AES__GCM__CTR__V1");
   }

   public boolean isSetAES_GCM_V1() {
      return this.setField_ == EncryptionAlgorithm._Fields.AES__GCM__V1;
   }

   public boolean isSetAES_GCM_CTR_V1() {
      return this.setField_ == EncryptionAlgorithm._Fields.AES__GCM__CTR__V1;
   }

   public boolean equals(Object other) {
      return other instanceof EncryptionAlgorithm ? this.equals((EncryptionAlgorithm)other) : false;
   }

   public boolean equals(EncryptionAlgorithm other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(EncryptionAlgorithm other) {
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
      tmpMap.put(EncryptionAlgorithm._Fields.AES__GCM__V1, new FieldMetaData("AES_GCM_V1", (byte)2, new StructMetaData((byte)12, AesGcmV1.class)));
      tmpMap.put(EncryptionAlgorithm._Fields.AES__GCM__CTR__V1, new FieldMetaData("AES_GCM_CTR_V1", (byte)2, new StructMetaData((byte)12, AesGcmCtrV1.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(EncryptionAlgorithm.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      AES__GCM__V1((short)1, "AES_GCM_V1"),
      AES__GCM__CTR__V1((short)2, "AES_GCM_CTR_V1");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return AES__GCM__V1;
            case 2:
               return AES__GCM__CTR__V1;
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
