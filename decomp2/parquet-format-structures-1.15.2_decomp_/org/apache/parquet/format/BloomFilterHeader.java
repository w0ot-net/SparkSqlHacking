package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.org.apache.thrift.EncodingUtils;
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

public class BloomFilterHeader implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("BloomFilterHeader");
   private static final TField NUM_BYTES_FIELD_DESC = new TField("numBytes", (byte)8, (short)1);
   private static final TField ALGORITHM_FIELD_DESC = new TField("algorithm", (byte)12, (short)2);
   private static final TField HASH_FIELD_DESC = new TField("hash", (byte)12, (short)3);
   private static final TField COMPRESSION_FIELD_DESC = new TField("compression", (byte)12, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new BloomFilterHeaderStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new BloomFilterHeaderTupleSchemeFactory();
   public int numBytes;
   @Nullable
   public BloomFilterAlgorithm algorithm;
   @Nullable
   public BloomFilterHash hash;
   @Nullable
   public BloomFilterCompression compression;
   private static final int __NUMBYTES_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public BloomFilterHeader() {
      this.__isset_bitfield = 0;
   }

   public BloomFilterHeader(int numBytes, BloomFilterAlgorithm algorithm, BloomFilterHash hash, BloomFilterCompression compression) {
      this();
      this.numBytes = numBytes;
      this.setNumBytesIsSet(true);
      this.algorithm = algorithm;
      this.hash = hash;
      this.compression = compression;
   }

   public BloomFilterHeader(BloomFilterHeader other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.numBytes = other.numBytes;
      if (other.isSetAlgorithm()) {
         this.algorithm = new BloomFilterAlgorithm(other.algorithm);
      }

      if (other.isSetHash()) {
         this.hash = new BloomFilterHash(other.hash);
      }

      if (other.isSetCompression()) {
         this.compression = new BloomFilterCompression(other.compression);
      }

   }

   public BloomFilterHeader deepCopy() {
      return new BloomFilterHeader(this);
   }

   public void clear() {
      this.setNumBytesIsSet(false);
      this.numBytes = 0;
      this.algorithm = null;
      this.hash = null;
      this.compression = null;
   }

   public int getNumBytes() {
      return this.numBytes;
   }

   public BloomFilterHeader setNumBytes(int numBytes) {
      this.numBytes = numBytes;
      this.setNumBytesIsSet(true);
      return this;
   }

   public void unsetNumBytes() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetNumBytes() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setNumBytesIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   @Nullable
   public BloomFilterAlgorithm getAlgorithm() {
      return this.algorithm;
   }

   public BloomFilterHeader setAlgorithm(@Nullable BloomFilterAlgorithm algorithm) {
      this.algorithm = algorithm;
      return this;
   }

   public void unsetAlgorithm() {
      this.algorithm = null;
   }

   public boolean isSetAlgorithm() {
      return this.algorithm != null;
   }

   public void setAlgorithmIsSet(boolean value) {
      if (!value) {
         this.algorithm = null;
      }

   }

   @Nullable
   public BloomFilterHash getHash() {
      return this.hash;
   }

   public BloomFilterHeader setHash(@Nullable BloomFilterHash hash) {
      this.hash = hash;
      return this;
   }

   public void unsetHash() {
      this.hash = null;
   }

   public boolean isSetHash() {
      return this.hash != null;
   }

   public void setHashIsSet(boolean value) {
      if (!value) {
         this.hash = null;
      }

   }

   @Nullable
   public BloomFilterCompression getCompression() {
      return this.compression;
   }

   public BloomFilterHeader setCompression(@Nullable BloomFilterCompression compression) {
      this.compression = compression;
      return this;
   }

   public void unsetCompression() {
      this.compression = null;
   }

   public boolean isSetCompression() {
      return this.compression != null;
   }

   public void setCompressionIsSet(boolean value) {
      if (!value) {
         this.compression = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NUM_BYTES:
            if (value == null) {
               this.unsetNumBytes();
            } else {
               this.setNumBytes((Integer)value);
            }
            break;
         case ALGORITHM:
            if (value == null) {
               this.unsetAlgorithm();
            } else {
               this.setAlgorithm((BloomFilterAlgorithm)value);
            }
            break;
         case HASH:
            if (value == null) {
               this.unsetHash();
            } else {
               this.setHash((BloomFilterHash)value);
            }
            break;
         case COMPRESSION:
            if (value == null) {
               this.unsetCompression();
            } else {
               this.setCompression((BloomFilterCompression)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NUM_BYTES:
            return this.getNumBytes();
         case ALGORITHM:
            return this.getAlgorithm();
         case HASH:
            return this.getHash();
         case COMPRESSION:
            return this.getCompression();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NUM_BYTES:
               return this.isSetNumBytes();
            case ALGORITHM:
               return this.isSetAlgorithm();
            case HASH:
               return this.isSetHash();
            case COMPRESSION:
               return this.isSetCompression();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof BloomFilterHeader ? this.equals((BloomFilterHeader)that) : false;
   }

   public boolean equals(BloomFilterHeader that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_numBytes = true;
         boolean that_present_numBytes = true;
         if (this_present_numBytes || that_present_numBytes) {
            if (!this_present_numBytes || !that_present_numBytes) {
               return false;
            }

            if (this.numBytes != that.numBytes) {
               return false;
            }
         }

         boolean this_present_algorithm = this.isSetAlgorithm();
         boolean that_present_algorithm = that.isSetAlgorithm();
         if (this_present_algorithm || that_present_algorithm) {
            if (!this_present_algorithm || !that_present_algorithm) {
               return false;
            }

            if (!this.algorithm.equals(that.algorithm)) {
               return false;
            }
         }

         boolean this_present_hash = this.isSetHash();
         boolean that_present_hash = that.isSetHash();
         if (this_present_hash || that_present_hash) {
            if (!this_present_hash || !that_present_hash) {
               return false;
            }

            if (!this.hash.equals(that.hash)) {
               return false;
            }
         }

         boolean this_present_compression = this.isSetCompression();
         boolean that_present_compression = that.isSetCompression();
         if (this_present_compression || that_present_compression) {
            if (!this_present_compression || !that_present_compression) {
               return false;
            }

            if (!this.compression.equals(that.compression)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.numBytes;
      hashCode = hashCode * 8191 + (this.isSetAlgorithm() ? 131071 : 524287);
      if (this.isSetAlgorithm()) {
         hashCode = hashCode * 8191 + this.algorithm.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetHash() ? 131071 : 524287);
      if (this.isSetHash()) {
         hashCode = hashCode * 8191 + this.hash.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCompression() ? 131071 : 524287);
      if (this.isSetCompression()) {
         hashCode = hashCode * 8191 + this.compression.hashCode();
      }

      return hashCode;
   }

   public int compareTo(BloomFilterHeader other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetNumBytes(), other.isSetNumBytes());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetNumBytes()) {
               lastComparison = TBaseHelper.compareTo(this.numBytes, other.numBytes);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetAlgorithm(), other.isSetAlgorithm());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetAlgorithm()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.algorithm, (Comparable)other.algorithm);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetHash(), other.isSetHash());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetHash()) {
                     lastComparison = TBaseHelper.compareTo((Comparable)this.hash, (Comparable)other.hash);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetCompression(), other.isSetCompression());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetCompression()) {
                        lastComparison = TBaseHelper.compareTo((Comparable)this.compression, (Comparable)other.compression);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     return 0;
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return BloomFilterHeader._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("BloomFilterHeader(");
      boolean first = true;
      sb.append("numBytes:");
      sb.append(this.numBytes);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("algorithm:");
      if (this.algorithm == null) {
         sb.append("null");
      } else {
         sb.append(this.algorithm);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("hash:");
      if (this.hash == null) {
         sb.append("null");
      } else {
         sb.append(this.hash);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("compression:");
      if (this.compression == null) {
         sb.append("null");
      } else {
         sb.append(this.compression);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.algorithm == null) {
         throw new TProtocolException("Required field 'algorithm' was not present! Struct: " + this.toString());
      } else if (this.hash == null) {
         throw new TProtocolException("Required field 'hash' was not present! Struct: " + this.toString());
      } else if (this.compression == null) {
         throw new TProtocolException("Required field 'compression' was not present! Struct: " + this.toString());
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
         this.__isset_bitfield = 0;
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(BloomFilterHeader._Fields.NUM_BYTES, new FieldMetaData("numBytes", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(BloomFilterHeader._Fields.ALGORITHM, new FieldMetaData("algorithm", (byte)1, new StructMetaData((byte)12, BloomFilterAlgorithm.class)));
      tmpMap.put(BloomFilterHeader._Fields.HASH, new FieldMetaData("hash", (byte)1, new StructMetaData((byte)12, BloomFilterHash.class)));
      tmpMap.put(BloomFilterHeader._Fields.COMPRESSION, new FieldMetaData("compression", (byte)1, new StructMetaData((byte)12, BloomFilterCompression.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(BloomFilterHeader.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NUM_BYTES((short)1, "numBytes"),
      ALGORITHM((short)2, "algorithm"),
      HASH((short)3, "hash"),
      COMPRESSION((short)4, "compression");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NUM_BYTES;
            case 2:
               return ALGORITHM;
            case 3:
               return HASH;
            case 4:
               return COMPRESSION;
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

   private static class BloomFilterHeaderStandardSchemeFactory implements SchemeFactory {
      private BloomFilterHeaderStandardSchemeFactory() {
      }

      public BloomFilterHeaderStandardScheme getScheme() {
         return new BloomFilterHeaderStandardScheme();
      }
   }

   private static class BloomFilterHeaderStandardScheme extends StandardScheme {
      private BloomFilterHeaderStandardScheme() {
      }

      public void read(TProtocol iprot, BloomFilterHeader struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetNumBytes()) {
                  throw new TProtocolException("Required field 'numBytes' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.numBytes = iprot.readI32();
                     struct.setNumBytesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.algorithm = new BloomFilterAlgorithm();
                     struct.algorithm.read(iprot);
                     struct.setAlgorithmIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 12) {
                     struct.hash = new BloomFilterHash();
                     struct.hash.read(iprot);
                     struct.setHashIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 12) {
                     struct.compression = new BloomFilterCompression();
                     struct.compression.read(iprot);
                     struct.setCompressionIsSet(true);
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

      public void write(TProtocol oprot, BloomFilterHeader struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(BloomFilterHeader.STRUCT_DESC);
         oprot.writeFieldBegin(BloomFilterHeader.NUM_BYTES_FIELD_DESC);
         oprot.writeI32(struct.numBytes);
         oprot.writeFieldEnd();
         if (struct.algorithm != null) {
            oprot.writeFieldBegin(BloomFilterHeader.ALGORITHM_FIELD_DESC);
            struct.algorithm.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.hash != null) {
            oprot.writeFieldBegin(BloomFilterHeader.HASH_FIELD_DESC);
            struct.hash.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.compression != null) {
            oprot.writeFieldBegin(BloomFilterHeader.COMPRESSION_FIELD_DESC);
            struct.compression.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class BloomFilterHeaderTupleSchemeFactory implements SchemeFactory {
      private BloomFilterHeaderTupleSchemeFactory() {
      }

      public BloomFilterHeaderTupleScheme getScheme() {
         return new BloomFilterHeaderTupleScheme();
      }
   }

   private static class BloomFilterHeaderTupleScheme extends TupleScheme {
      private BloomFilterHeaderTupleScheme() {
      }

      public void write(TProtocol prot, BloomFilterHeader struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.numBytes);
         struct.algorithm.write(oprot);
         struct.hash.write(oprot);
         struct.compression.write(oprot);
      }

      public void read(TProtocol prot, BloomFilterHeader struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.numBytes = iprot.readI32();
         struct.setNumBytesIsSet(true);
         struct.algorithm = new BloomFilterAlgorithm();
         struct.algorithm.read(iprot);
         struct.setAlgorithmIsSet(true);
         struct.hash = new BloomFilterHash();
         struct.hash.read(iprot);
         struct.setHashIsSet(true);
         struct.compression = new BloomFilterCompression();
         struct.compression.read(iprot);
         struct.setCompressionIsSet(true);
      }
   }
}
