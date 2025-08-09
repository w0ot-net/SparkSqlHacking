package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class FileCryptoMetaData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("FileCryptoMetaData");
   private static final TField ENCRYPTION_ALGORITHM_FIELD_DESC = new TField("encryption_algorithm", (byte)12, (short)1);
   private static final TField KEY_METADATA_FIELD_DESC = new TField("key_metadata", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FileCryptoMetaDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FileCryptoMetaDataTupleSchemeFactory();
   @Nullable
   public EncryptionAlgorithm encryption_algorithm;
   @Nullable
   public ByteBuffer key_metadata;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public FileCryptoMetaData() {
   }

   public FileCryptoMetaData(EncryptionAlgorithm encryption_algorithm) {
      this();
      this.encryption_algorithm = encryption_algorithm;
   }

   public FileCryptoMetaData(FileCryptoMetaData other) {
      if (other.isSetEncryption_algorithm()) {
         this.encryption_algorithm = new EncryptionAlgorithm(other.encryption_algorithm);
      }

      if (other.isSetKey_metadata()) {
         this.key_metadata = TBaseHelper.copyBinary(other.key_metadata);
      }

   }

   public FileCryptoMetaData deepCopy() {
      return new FileCryptoMetaData(this);
   }

   public void clear() {
      this.encryption_algorithm = null;
      this.key_metadata = null;
   }

   @Nullable
   public EncryptionAlgorithm getEncryption_algorithm() {
      return this.encryption_algorithm;
   }

   public FileCryptoMetaData setEncryption_algorithm(@Nullable EncryptionAlgorithm encryption_algorithm) {
      this.encryption_algorithm = encryption_algorithm;
      return this;
   }

   public void unsetEncryption_algorithm() {
      this.encryption_algorithm = null;
   }

   public boolean isSetEncryption_algorithm() {
      return this.encryption_algorithm != null;
   }

   public void setEncryption_algorithmIsSet(boolean value) {
      if (!value) {
         this.encryption_algorithm = null;
      }

   }

   public byte[] getKey_metadata() {
      this.setKey_metadata(TBaseHelper.rightSize(this.key_metadata));
      return this.key_metadata == null ? null : this.key_metadata.array();
   }

   public ByteBuffer bufferForKey_metadata() {
      return TBaseHelper.copyBinary(this.key_metadata);
   }

   public FileCryptoMetaData setKey_metadata(byte[] key_metadata) {
      this.key_metadata = key_metadata == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)key_metadata).clone());
      return this;
   }

   public FileCryptoMetaData setKey_metadata(@Nullable ByteBuffer key_metadata) {
      this.key_metadata = TBaseHelper.copyBinary(key_metadata);
      return this;
   }

   public void unsetKey_metadata() {
      this.key_metadata = null;
   }

   public boolean isSetKey_metadata() {
      return this.key_metadata != null;
   }

   public void setKey_metadataIsSet(boolean value) {
      if (!value) {
         this.key_metadata = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case ENCRYPTION_ALGORITHM:
            if (value == null) {
               this.unsetEncryption_algorithm();
            } else {
               this.setEncryption_algorithm((EncryptionAlgorithm)value);
            }
            break;
         case KEY_METADATA:
            if (value == null) {
               this.unsetKey_metadata();
            } else if (value instanceof byte[]) {
               this.setKey_metadata((byte[])value);
            } else {
               this.setKey_metadata((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ENCRYPTION_ALGORITHM:
            return this.getEncryption_algorithm();
         case KEY_METADATA:
            return this.getKey_metadata();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case ENCRYPTION_ALGORITHM:
               return this.isSetEncryption_algorithm();
            case KEY_METADATA:
               return this.isSetKey_metadata();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof FileCryptoMetaData ? this.equals((FileCryptoMetaData)that) : false;
   }

   public boolean equals(FileCryptoMetaData that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_encryption_algorithm = this.isSetEncryption_algorithm();
         boolean that_present_encryption_algorithm = that.isSetEncryption_algorithm();
         if (this_present_encryption_algorithm || that_present_encryption_algorithm) {
            if (!this_present_encryption_algorithm || !that_present_encryption_algorithm) {
               return false;
            }

            if (!this.encryption_algorithm.equals(that.encryption_algorithm)) {
               return false;
            }
         }

         boolean this_present_key_metadata = this.isSetKey_metadata();
         boolean that_present_key_metadata = that.isSetKey_metadata();
         if (this_present_key_metadata || that_present_key_metadata) {
            if (!this_present_key_metadata || !that_present_key_metadata) {
               return false;
            }

            if (!this.key_metadata.equals(that.key_metadata)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetEncryption_algorithm() ? 131071 : 524287);
      if (this.isSetEncryption_algorithm()) {
         hashCode = hashCode * 8191 + this.encryption_algorithm.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetKey_metadata() ? 131071 : 524287);
      if (this.isSetKey_metadata()) {
         hashCode = hashCode * 8191 + this.key_metadata.hashCode();
      }

      return hashCode;
   }

   public int compareTo(FileCryptoMetaData other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetEncryption_algorithm(), other.isSetEncryption_algorithm());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetEncryption_algorithm()) {
               lastComparison = TBaseHelper.compareTo((Comparable)this.encryption_algorithm, (Comparable)other.encryption_algorithm);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetKey_metadata(), other.isSetKey_metadata());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetKey_metadata()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.key_metadata, (Comparable)other.key_metadata);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return FileCryptoMetaData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("FileCryptoMetaData(");
      boolean first = true;
      sb.append("encryption_algorithm:");
      if (this.encryption_algorithm == null) {
         sb.append("null");
      } else {
         sb.append(this.encryption_algorithm);
      }

      first = false;
      if (this.isSetKey_metadata()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("key_metadata:");
         if (this.key_metadata == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.key_metadata, sb);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.encryption_algorithm == null) {
         throw new TProtocolException("Required field 'encryption_algorithm' was not present! Struct: " + this.toString());
      }
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

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{FileCryptoMetaData._Fields.KEY_METADATA};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(FileCryptoMetaData._Fields.ENCRYPTION_ALGORITHM, new FieldMetaData("encryption_algorithm", (byte)1, new StructMetaData((byte)12, EncryptionAlgorithm.class)));
      tmpMap.put(FileCryptoMetaData._Fields.KEY_METADATA, new FieldMetaData("key_metadata", (byte)2, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(FileCryptoMetaData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ENCRYPTION_ALGORITHM((short)1, "encryption_algorithm"),
      KEY_METADATA((short)2, "key_metadata");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ENCRYPTION_ALGORITHM;
            case 2:
               return KEY_METADATA;
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

   private static class FileCryptoMetaDataStandardSchemeFactory implements SchemeFactory {
      private FileCryptoMetaDataStandardSchemeFactory() {
      }

      public FileCryptoMetaDataStandardScheme getScheme() {
         return new FileCryptoMetaDataStandardScheme();
      }
   }

   private static class FileCryptoMetaDataStandardScheme extends StandardScheme {
      private FileCryptoMetaDataStandardScheme() {
      }

      public void read(TProtocol iprot, FileCryptoMetaData struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 12) {
                     struct.encryption_algorithm = new EncryptionAlgorithm();
                     struct.encryption_algorithm.read(iprot);
                     struct.setEncryption_algorithmIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.key_metadata = iprot.readBinary();
                     struct.setKey_metadataIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, FileCryptoMetaData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(FileCryptoMetaData.STRUCT_DESC);
         if (struct.encryption_algorithm != null) {
            oprot.writeFieldBegin(FileCryptoMetaData.ENCRYPTION_ALGORITHM_FIELD_DESC);
            struct.encryption_algorithm.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.key_metadata != null && struct.isSetKey_metadata()) {
            oprot.writeFieldBegin(FileCryptoMetaData.KEY_METADATA_FIELD_DESC);
            oprot.writeBinary(struct.key_metadata);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class FileCryptoMetaDataTupleSchemeFactory implements SchemeFactory {
      private FileCryptoMetaDataTupleSchemeFactory() {
      }

      public FileCryptoMetaDataTupleScheme getScheme() {
         return new FileCryptoMetaDataTupleScheme();
      }
   }

   private static class FileCryptoMetaDataTupleScheme extends TupleScheme {
      private FileCryptoMetaDataTupleScheme() {
      }

      public void write(TProtocol prot, FileCryptoMetaData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.encryption_algorithm.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetKey_metadata()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetKey_metadata()) {
            oprot.writeBinary(struct.key_metadata);
         }

      }

      public void read(TProtocol prot, FileCryptoMetaData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.encryption_algorithm = new EncryptionAlgorithm();
         struct.encryption_algorithm.read(iprot);
         struct.setEncryption_algorithmIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.key_metadata = iprot.readBinary();
            struct.setKey_metadataIsSet(true);
         }

      }
   }
}
