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

public class ColumnCryptoMetaData extends TUnion {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnCryptoMetaData");
   private static final TField ENCRYPTION__WITH__FOOTER__KEY_FIELD_DESC = new TField("ENCRYPTION_WITH_FOOTER_KEY", (byte)12, (short)1);
   private static final TField ENCRYPTION__WITH__COLUMN__KEY_FIELD_DESC = new TField("ENCRYPTION_WITH_COLUMN_KEY", (byte)12, (short)2);
   public static final Map metaDataMap;

   public ColumnCryptoMetaData() {
   }

   public ColumnCryptoMetaData(_Fields setField, Object value) {
      super(setField, value);
   }

   public ColumnCryptoMetaData(ColumnCryptoMetaData other) {
      super(other);
   }

   public ColumnCryptoMetaData deepCopy() {
      return new ColumnCryptoMetaData(this);
   }

   public static ColumnCryptoMetaData ENCRYPTION_WITH_FOOTER_KEY(EncryptionWithFooterKey value) {
      ColumnCryptoMetaData x = new ColumnCryptoMetaData();
      x.setENCRYPTION_WITH_FOOTER_KEY(value);
      return x;
   }

   public static ColumnCryptoMetaData ENCRYPTION_WITH_COLUMN_KEY(EncryptionWithColumnKey value) {
      ColumnCryptoMetaData x = new ColumnCryptoMetaData();
      x.setENCRYPTION_WITH_COLUMN_KEY(value);
      return x;
   }

   protected void checkType(_Fields setField, Object value) throws ClassCastException {
      switch (setField) {
         case ENCRYPTION__WITH__FOOTER__KEY:
            if (!(value instanceof EncryptionWithFooterKey)) {
               throw new ClassCastException("Was expecting value of type EncryptionWithFooterKey for field 'ENCRYPTION_WITH_FOOTER_KEY', but got " + value.getClass().getSimpleName());
            }
            break;
         case ENCRYPTION__WITH__COLUMN__KEY:
            if (!(value instanceof EncryptionWithColumnKey)) {
               throw new ClassCastException("Was expecting value of type EncryptionWithColumnKey for field 'ENCRYPTION_WITH_COLUMN_KEY', but got " + value.getClass().getSimpleName());
            }
            break;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }

   }

   protected Object standardSchemeReadValue(TProtocol iprot, TField field) throws TException {
      _Fields setField = ColumnCryptoMetaData._Fields.findByThriftId(field.id);
      if (setField != null) {
         switch (setField) {
            case ENCRYPTION__WITH__FOOTER__KEY:
               if (field.type == ENCRYPTION__WITH__FOOTER__KEY_FIELD_DESC.type) {
                  EncryptionWithFooterKey ENCRYPTION_WITH_FOOTER_KEY = new EncryptionWithFooterKey();
                  ENCRYPTION_WITH_FOOTER_KEY.read(iprot);
                  return ENCRYPTION_WITH_FOOTER_KEY;
               }

               TProtocolUtil.skip(iprot, field.type);
               return null;
            case ENCRYPTION__WITH__COLUMN__KEY:
               if (field.type == ENCRYPTION__WITH__COLUMN__KEY_FIELD_DESC.type) {
                  EncryptionWithColumnKey ENCRYPTION_WITH_COLUMN_KEY = new EncryptionWithColumnKey();
                  ENCRYPTION_WITH_COLUMN_KEY.read(iprot);
                  return ENCRYPTION_WITH_COLUMN_KEY;
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
         case ENCRYPTION__WITH__FOOTER__KEY:
            EncryptionWithFooterKey ENCRYPTION_WITH_FOOTER_KEY = (EncryptionWithFooterKey)this.value_;
            ENCRYPTION_WITH_FOOTER_KEY.write(oprot);
            return;
         case ENCRYPTION__WITH__COLUMN__KEY:
            EncryptionWithColumnKey ENCRYPTION_WITH_COLUMN_KEY = (EncryptionWithColumnKey)this.value_;
            ENCRYPTION_WITH_COLUMN_KEY.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected Object tupleSchemeReadValue(TProtocol iprot, short fieldID) throws TException {
      _Fields setField = ColumnCryptoMetaData._Fields.findByThriftId(fieldID);
      if (setField != null) {
         switch (setField) {
            case ENCRYPTION__WITH__FOOTER__KEY:
               EncryptionWithFooterKey ENCRYPTION_WITH_FOOTER_KEY = new EncryptionWithFooterKey();
               ENCRYPTION_WITH_FOOTER_KEY.read(iprot);
               return ENCRYPTION_WITH_FOOTER_KEY;
            case ENCRYPTION__WITH__COLUMN__KEY:
               EncryptionWithColumnKey ENCRYPTION_WITH_COLUMN_KEY = new EncryptionWithColumnKey();
               ENCRYPTION_WITH_COLUMN_KEY.read(iprot);
               return ENCRYPTION_WITH_COLUMN_KEY;
            default:
               throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
         }
      } else {
         throw new TProtocolException("Couldn't find a field with field id " + fieldID);
      }
   }

   protected void tupleSchemeWriteValue(TProtocol oprot) throws TException {
      switch ((_Fields)this.setField_) {
         case ENCRYPTION__WITH__FOOTER__KEY:
            EncryptionWithFooterKey ENCRYPTION_WITH_FOOTER_KEY = (EncryptionWithFooterKey)this.value_;
            ENCRYPTION_WITH_FOOTER_KEY.write(oprot);
            return;
         case ENCRYPTION__WITH__COLUMN__KEY:
            EncryptionWithColumnKey ENCRYPTION_WITH_COLUMN_KEY = (EncryptionWithColumnKey)this.value_;
            ENCRYPTION_WITH_COLUMN_KEY.write(oprot);
            return;
         default:
            throw new IllegalStateException("Cannot write union with unknown field " + this.setField_);
      }
   }

   protected TField getFieldDesc(_Fields setField) {
      switch (setField) {
         case ENCRYPTION__WITH__FOOTER__KEY:
            return ENCRYPTION__WITH__FOOTER__KEY_FIELD_DESC;
         case ENCRYPTION__WITH__COLUMN__KEY:
            return ENCRYPTION__WITH__COLUMN__KEY_FIELD_DESC;
         default:
            throw new IllegalArgumentException("Unknown field id " + setField);
      }
   }

   protected TStruct getStructDesc() {
      return STRUCT_DESC;
   }

   protected _Fields enumForId(short id) {
      return ColumnCryptoMetaData._Fields.findByThriftIdOrThrow(id);
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnCryptoMetaData._Fields.findByThriftId(fieldId);
   }

   public EncryptionWithFooterKey getENCRYPTION_WITH_FOOTER_KEY() {
      if (this.getSetField() == ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__FOOTER__KEY) {
         return (EncryptionWithFooterKey)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'ENCRYPTION_WITH_FOOTER_KEY' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setENCRYPTION_WITH_FOOTER_KEY(EncryptionWithFooterKey value) {
      this.setField_ = ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__FOOTER__KEY;
      this.value_ = Objects.requireNonNull(value, "_Fields.ENCRYPTION__WITH__FOOTER__KEY");
   }

   public EncryptionWithColumnKey getENCRYPTION_WITH_COLUMN_KEY() {
      if (this.getSetField() == ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__COLUMN__KEY) {
         return (EncryptionWithColumnKey)this.getFieldValue();
      } else {
         throw new RuntimeException("Cannot get field 'ENCRYPTION_WITH_COLUMN_KEY' because union is currently set to " + this.getFieldDesc((_Fields)this.getSetField()).name);
      }
   }

   public void setENCRYPTION_WITH_COLUMN_KEY(EncryptionWithColumnKey value) {
      this.setField_ = ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__COLUMN__KEY;
      this.value_ = Objects.requireNonNull(value, "_Fields.ENCRYPTION__WITH__COLUMN__KEY");
   }

   public boolean isSetENCRYPTION_WITH_FOOTER_KEY() {
      return this.setField_ == ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__FOOTER__KEY;
   }

   public boolean isSetENCRYPTION_WITH_COLUMN_KEY() {
      return this.setField_ == ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__COLUMN__KEY;
   }

   public boolean equals(Object other) {
      return other instanceof ColumnCryptoMetaData ? this.equals((ColumnCryptoMetaData)other) : false;
   }

   public boolean equals(ColumnCryptoMetaData other) {
      return other != null && this.getSetField() == other.getSetField() && this.getFieldValue().equals(other.getFieldValue());
   }

   public int compareTo(ColumnCryptoMetaData other) {
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
      tmpMap.put(ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__FOOTER__KEY, new FieldMetaData("ENCRYPTION_WITH_FOOTER_KEY", (byte)2, new StructMetaData((byte)12, EncryptionWithFooterKey.class)));
      tmpMap.put(ColumnCryptoMetaData._Fields.ENCRYPTION__WITH__COLUMN__KEY, new FieldMetaData("ENCRYPTION_WITH_COLUMN_KEY", (byte)2, new StructMetaData((byte)12, EncryptionWithColumnKey.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnCryptoMetaData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ENCRYPTION__WITH__FOOTER__KEY((short)1, "ENCRYPTION_WITH_FOOTER_KEY"),
      ENCRYPTION__WITH__COLUMN__KEY((short)2, "ENCRYPTION_WITH_COLUMN_KEY");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ENCRYPTION__WITH__FOOTER__KEY;
            case 2:
               return ENCRYPTION__WITH__COLUMN__KEY;
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
