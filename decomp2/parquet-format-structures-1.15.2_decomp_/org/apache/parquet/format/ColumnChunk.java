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

public class ColumnChunk implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnChunk");
   private static final TField FILE_PATH_FIELD_DESC = new TField("file_path", (byte)11, (short)1);
   private static final TField FILE_OFFSET_FIELD_DESC = new TField("file_offset", (byte)10, (short)2);
   private static final TField META_DATA_FIELD_DESC = new TField("meta_data", (byte)12, (short)3);
   private static final TField OFFSET_INDEX_OFFSET_FIELD_DESC = new TField("offset_index_offset", (byte)10, (short)4);
   private static final TField OFFSET_INDEX_LENGTH_FIELD_DESC = new TField("offset_index_length", (byte)8, (short)5);
   private static final TField COLUMN_INDEX_OFFSET_FIELD_DESC = new TField("column_index_offset", (byte)10, (short)6);
   private static final TField COLUMN_INDEX_LENGTH_FIELD_DESC = new TField("column_index_length", (byte)8, (short)7);
   private static final TField CRYPTO_METADATA_FIELD_DESC = new TField("crypto_metadata", (byte)12, (short)8);
   private static final TField ENCRYPTED_COLUMN_METADATA_FIELD_DESC = new TField("encrypted_column_metadata", (byte)11, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnChunkStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnChunkTupleSchemeFactory();
   @Nullable
   public String file_path;
   public long file_offset;
   @Nullable
   public ColumnMetaData meta_data;
   public long offset_index_offset;
   public int offset_index_length;
   public long column_index_offset;
   public int column_index_length;
   @Nullable
   public ColumnCryptoMetaData crypto_metadata;
   @Nullable
   public ByteBuffer encrypted_column_metadata;
   private static final int __FILE_OFFSET_ISSET_ID = 0;
   private static final int __OFFSET_INDEX_OFFSET_ISSET_ID = 1;
   private static final int __OFFSET_INDEX_LENGTH_ISSET_ID = 2;
   private static final int __COLUMN_INDEX_OFFSET_ISSET_ID = 3;
   private static final int __COLUMN_INDEX_LENGTH_ISSET_ID = 4;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ColumnChunk() {
      this.__isset_bitfield = 0;
   }

   public ColumnChunk(long file_offset) {
      this();
      this.file_offset = file_offset;
      this.setFile_offsetIsSet(true);
   }

   public ColumnChunk(ColumnChunk other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetFile_path()) {
         this.file_path = other.file_path;
      }

      this.file_offset = other.file_offset;
      if (other.isSetMeta_data()) {
         this.meta_data = new ColumnMetaData(other.meta_data);
      }

      this.offset_index_offset = other.offset_index_offset;
      this.offset_index_length = other.offset_index_length;
      this.column_index_offset = other.column_index_offset;
      this.column_index_length = other.column_index_length;
      if (other.isSetCrypto_metadata()) {
         this.crypto_metadata = new ColumnCryptoMetaData(other.crypto_metadata);
      }

      if (other.isSetEncrypted_column_metadata()) {
         this.encrypted_column_metadata = TBaseHelper.copyBinary(other.encrypted_column_metadata);
      }

   }

   public ColumnChunk deepCopy() {
      return new ColumnChunk(this);
   }

   public void clear() {
      this.file_path = null;
      this.setFile_offsetIsSet(false);
      this.file_offset = 0L;
      this.meta_data = null;
      this.setOffset_index_offsetIsSet(false);
      this.offset_index_offset = 0L;
      this.setOffset_index_lengthIsSet(false);
      this.offset_index_length = 0;
      this.setColumn_index_offsetIsSet(false);
      this.column_index_offset = 0L;
      this.setColumn_index_lengthIsSet(false);
      this.column_index_length = 0;
      this.crypto_metadata = null;
      this.encrypted_column_metadata = null;
   }

   @Nullable
   public String getFile_path() {
      return this.file_path;
   }

   public ColumnChunk setFile_path(@Nullable String file_path) {
      this.file_path = file_path;
      return this;
   }

   public void unsetFile_path() {
      this.file_path = null;
   }

   public boolean isSetFile_path() {
      return this.file_path != null;
   }

   public void setFile_pathIsSet(boolean value) {
      if (!value) {
         this.file_path = null;
      }

   }

   public long getFile_offset() {
      return this.file_offset;
   }

   public ColumnChunk setFile_offset(long file_offset) {
      this.file_offset = file_offset;
      this.setFile_offsetIsSet(true);
      return this;
   }

   public void unsetFile_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetFile_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setFile_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   @Nullable
   public ColumnMetaData getMeta_data() {
      return this.meta_data;
   }

   public ColumnChunk setMeta_data(@Nullable ColumnMetaData meta_data) {
      this.meta_data = meta_data;
      return this;
   }

   public void unsetMeta_data() {
      this.meta_data = null;
   }

   public boolean isSetMeta_data() {
      return this.meta_data != null;
   }

   public void setMeta_dataIsSet(boolean value) {
      if (!value) {
         this.meta_data = null;
      }

   }

   public long getOffset_index_offset() {
      return this.offset_index_offset;
   }

   public ColumnChunk setOffset_index_offset(long offset_index_offset) {
      this.offset_index_offset = offset_index_offset;
      this.setOffset_index_offsetIsSet(true);
      return this;
   }

   public void unsetOffset_index_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetOffset_index_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setOffset_index_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public int getOffset_index_length() {
      return this.offset_index_length;
   }

   public ColumnChunk setOffset_index_length(int offset_index_length) {
      this.offset_index_length = offset_index_length;
      this.setOffset_index_lengthIsSet(true);
      return this;
   }

   public void unsetOffset_index_length() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetOffset_index_length() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setOffset_index_lengthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   public long getColumn_index_offset() {
      return this.column_index_offset;
   }

   public ColumnChunk setColumn_index_offset(long column_index_offset) {
      this.column_index_offset = column_index_offset;
      this.setColumn_index_offsetIsSet(true);
      return this;
   }

   public void unsetColumn_index_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 3);
   }

   public boolean isSetColumn_index_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 3);
   }

   public void setColumn_index_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 3, value);
   }

   public int getColumn_index_length() {
      return this.column_index_length;
   }

   public ColumnChunk setColumn_index_length(int column_index_length) {
      this.column_index_length = column_index_length;
      this.setColumn_index_lengthIsSet(true);
      return this;
   }

   public void unsetColumn_index_length() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 4);
   }

   public boolean isSetColumn_index_length() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 4);
   }

   public void setColumn_index_lengthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 4, value);
   }

   @Nullable
   public ColumnCryptoMetaData getCrypto_metadata() {
      return this.crypto_metadata;
   }

   public ColumnChunk setCrypto_metadata(@Nullable ColumnCryptoMetaData crypto_metadata) {
      this.crypto_metadata = crypto_metadata;
      return this;
   }

   public void unsetCrypto_metadata() {
      this.crypto_metadata = null;
   }

   public boolean isSetCrypto_metadata() {
      return this.crypto_metadata != null;
   }

   public void setCrypto_metadataIsSet(boolean value) {
      if (!value) {
         this.crypto_metadata = null;
      }

   }

   public byte[] getEncrypted_column_metadata() {
      this.setEncrypted_column_metadata(TBaseHelper.rightSize(this.encrypted_column_metadata));
      return this.encrypted_column_metadata == null ? null : this.encrypted_column_metadata.array();
   }

   public ByteBuffer bufferForEncrypted_column_metadata() {
      return TBaseHelper.copyBinary(this.encrypted_column_metadata);
   }

   public ColumnChunk setEncrypted_column_metadata(byte[] encrypted_column_metadata) {
      this.encrypted_column_metadata = encrypted_column_metadata == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)encrypted_column_metadata).clone());
      return this;
   }

   public ColumnChunk setEncrypted_column_metadata(@Nullable ByteBuffer encrypted_column_metadata) {
      this.encrypted_column_metadata = TBaseHelper.copyBinary(encrypted_column_metadata);
      return this;
   }

   public void unsetEncrypted_column_metadata() {
      this.encrypted_column_metadata = null;
   }

   public boolean isSetEncrypted_column_metadata() {
      return this.encrypted_column_metadata != null;
   }

   public void setEncrypted_column_metadataIsSet(boolean value) {
      if (!value) {
         this.encrypted_column_metadata = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case FILE_PATH:
            if (value == null) {
               this.unsetFile_path();
            } else {
               this.setFile_path((String)value);
            }
            break;
         case FILE_OFFSET:
            if (value == null) {
               this.unsetFile_offset();
            } else {
               this.setFile_offset((Long)value);
            }
            break;
         case META_DATA:
            if (value == null) {
               this.unsetMeta_data();
            } else {
               this.setMeta_data((ColumnMetaData)value);
            }
            break;
         case OFFSET_INDEX_OFFSET:
            if (value == null) {
               this.unsetOffset_index_offset();
            } else {
               this.setOffset_index_offset((Long)value);
            }
            break;
         case OFFSET_INDEX_LENGTH:
            if (value == null) {
               this.unsetOffset_index_length();
            } else {
               this.setOffset_index_length((Integer)value);
            }
            break;
         case COLUMN_INDEX_OFFSET:
            if (value == null) {
               this.unsetColumn_index_offset();
            } else {
               this.setColumn_index_offset((Long)value);
            }
            break;
         case COLUMN_INDEX_LENGTH:
            if (value == null) {
               this.unsetColumn_index_length();
            } else {
               this.setColumn_index_length((Integer)value);
            }
            break;
         case CRYPTO_METADATA:
            if (value == null) {
               this.unsetCrypto_metadata();
            } else {
               this.setCrypto_metadata((ColumnCryptoMetaData)value);
            }
            break;
         case ENCRYPTED_COLUMN_METADATA:
            if (value == null) {
               this.unsetEncrypted_column_metadata();
            } else if (value instanceof byte[]) {
               this.setEncrypted_column_metadata((byte[])value);
            } else {
               this.setEncrypted_column_metadata((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case FILE_PATH:
            return this.getFile_path();
         case FILE_OFFSET:
            return this.getFile_offset();
         case META_DATA:
            return this.getMeta_data();
         case OFFSET_INDEX_OFFSET:
            return this.getOffset_index_offset();
         case OFFSET_INDEX_LENGTH:
            return this.getOffset_index_length();
         case COLUMN_INDEX_OFFSET:
            return this.getColumn_index_offset();
         case COLUMN_INDEX_LENGTH:
            return this.getColumn_index_length();
         case CRYPTO_METADATA:
            return this.getCrypto_metadata();
         case ENCRYPTED_COLUMN_METADATA:
            return this.getEncrypted_column_metadata();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case FILE_PATH:
               return this.isSetFile_path();
            case FILE_OFFSET:
               return this.isSetFile_offset();
            case META_DATA:
               return this.isSetMeta_data();
            case OFFSET_INDEX_OFFSET:
               return this.isSetOffset_index_offset();
            case OFFSET_INDEX_LENGTH:
               return this.isSetOffset_index_length();
            case COLUMN_INDEX_OFFSET:
               return this.isSetColumn_index_offset();
            case COLUMN_INDEX_LENGTH:
               return this.isSetColumn_index_length();
            case CRYPTO_METADATA:
               return this.isSetCrypto_metadata();
            case ENCRYPTED_COLUMN_METADATA:
               return this.isSetEncrypted_column_metadata();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ColumnChunk ? this.equals((ColumnChunk)that) : false;
   }

   public boolean equals(ColumnChunk that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_file_path = this.isSetFile_path();
         boolean that_present_file_path = that.isSetFile_path();
         if (this_present_file_path || that_present_file_path) {
            if (!this_present_file_path || !that_present_file_path) {
               return false;
            }

            if (!this.file_path.equals(that.file_path)) {
               return false;
            }
         }

         boolean this_present_file_offset = true;
         boolean that_present_file_offset = true;
         if (this_present_file_offset || that_present_file_offset) {
            if (!this_present_file_offset || !that_present_file_offset) {
               return false;
            }

            if (this.file_offset != that.file_offset) {
               return false;
            }
         }

         boolean this_present_meta_data = this.isSetMeta_data();
         boolean that_present_meta_data = that.isSetMeta_data();
         if (this_present_meta_data || that_present_meta_data) {
            if (!this_present_meta_data || !that_present_meta_data) {
               return false;
            }

            if (!this.meta_data.equals(that.meta_data)) {
               return false;
            }
         }

         boolean this_present_offset_index_offset = this.isSetOffset_index_offset();
         boolean that_present_offset_index_offset = that.isSetOffset_index_offset();
         if (this_present_offset_index_offset || that_present_offset_index_offset) {
            if (!this_present_offset_index_offset || !that_present_offset_index_offset) {
               return false;
            }

            if (this.offset_index_offset != that.offset_index_offset) {
               return false;
            }
         }

         boolean this_present_offset_index_length = this.isSetOffset_index_length();
         boolean that_present_offset_index_length = that.isSetOffset_index_length();
         if (this_present_offset_index_length || that_present_offset_index_length) {
            if (!this_present_offset_index_length || !that_present_offset_index_length) {
               return false;
            }

            if (this.offset_index_length != that.offset_index_length) {
               return false;
            }
         }

         boolean this_present_column_index_offset = this.isSetColumn_index_offset();
         boolean that_present_column_index_offset = that.isSetColumn_index_offset();
         if (this_present_column_index_offset || that_present_column_index_offset) {
            if (!this_present_column_index_offset || !that_present_column_index_offset) {
               return false;
            }

            if (this.column_index_offset != that.column_index_offset) {
               return false;
            }
         }

         boolean this_present_column_index_length = this.isSetColumn_index_length();
         boolean that_present_column_index_length = that.isSetColumn_index_length();
         if (this_present_column_index_length || that_present_column_index_length) {
            if (!this_present_column_index_length || !that_present_column_index_length) {
               return false;
            }

            if (this.column_index_length != that.column_index_length) {
               return false;
            }
         }

         boolean this_present_crypto_metadata = this.isSetCrypto_metadata();
         boolean that_present_crypto_metadata = that.isSetCrypto_metadata();
         if (this_present_crypto_metadata || that_present_crypto_metadata) {
            if (!this_present_crypto_metadata || !that_present_crypto_metadata) {
               return false;
            }

            if (!this.crypto_metadata.equals(that.crypto_metadata)) {
               return false;
            }
         }

         boolean this_present_encrypted_column_metadata = this.isSetEncrypted_column_metadata();
         boolean that_present_encrypted_column_metadata = that.isSetEncrypted_column_metadata();
         if (this_present_encrypted_column_metadata || that_present_encrypted_column_metadata) {
            if (!this_present_encrypted_column_metadata || !that_present_encrypted_column_metadata) {
               return false;
            }

            if (!this.encrypted_column_metadata.equals(that.encrypted_column_metadata)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetFile_path() ? 131071 : 524287);
      if (this.isSetFile_path()) {
         hashCode = hashCode * 8191 + this.file_path.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.file_offset);
      hashCode = hashCode * 8191 + (this.isSetMeta_data() ? 131071 : 524287);
      if (this.isSetMeta_data()) {
         hashCode = hashCode * 8191 + this.meta_data.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOffset_index_offset() ? 131071 : 524287);
      if (this.isSetOffset_index_offset()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.offset_index_offset);
      }

      hashCode = hashCode * 8191 + (this.isSetOffset_index_length() ? 131071 : 524287);
      if (this.isSetOffset_index_length()) {
         hashCode = hashCode * 8191 + this.offset_index_length;
      }

      hashCode = hashCode * 8191 + (this.isSetColumn_index_offset() ? 131071 : 524287);
      if (this.isSetColumn_index_offset()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.column_index_offset);
      }

      hashCode = hashCode * 8191 + (this.isSetColumn_index_length() ? 131071 : 524287);
      if (this.isSetColumn_index_length()) {
         hashCode = hashCode * 8191 + this.column_index_length;
      }

      hashCode = hashCode * 8191 + (this.isSetCrypto_metadata() ? 131071 : 524287);
      if (this.isSetCrypto_metadata()) {
         hashCode = hashCode * 8191 + this.crypto_metadata.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetEncrypted_column_metadata() ? 131071 : 524287);
      if (this.isSetEncrypted_column_metadata()) {
         hashCode = hashCode * 8191 + this.encrypted_column_metadata.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ColumnChunk other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetFile_path(), other.isSetFile_path());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetFile_path()) {
               lastComparison = TBaseHelper.compareTo(this.file_path, other.file_path);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetFile_offset(), other.isSetFile_offset());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetFile_offset()) {
                  lastComparison = TBaseHelper.compareTo(this.file_offset, other.file_offset);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetMeta_data(), other.isSetMeta_data());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetMeta_data()) {
                     lastComparison = TBaseHelper.compareTo((Comparable)this.meta_data, (Comparable)other.meta_data);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetOffset_index_offset(), other.isSetOffset_index_offset());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetOffset_index_offset()) {
                        lastComparison = TBaseHelper.compareTo(this.offset_index_offset, other.offset_index_offset);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetOffset_index_length(), other.isSetOffset_index_length());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetOffset_index_length()) {
                           lastComparison = TBaseHelper.compareTo(this.offset_index_length, other.offset_index_length);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetColumn_index_offset(), other.isSetColumn_index_offset());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetColumn_index_offset()) {
                              lastComparison = TBaseHelper.compareTo(this.column_index_offset, other.column_index_offset);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetColumn_index_length(), other.isSetColumn_index_length());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetColumn_index_length()) {
                                 lastComparison = TBaseHelper.compareTo(this.column_index_length, other.column_index_length);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetCrypto_metadata(), other.isSetCrypto_metadata());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetCrypto_metadata()) {
                                    lastComparison = TBaseHelper.compareTo((Comparable)this.crypto_metadata, (Comparable)other.crypto_metadata);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetEncrypted_column_metadata(), other.isSetEncrypted_column_metadata());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetEncrypted_column_metadata()) {
                                       lastComparison = TBaseHelper.compareTo((Comparable)this.encrypted_column_metadata, (Comparable)other.encrypted_column_metadata);
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
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnChunk._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ColumnChunk(");
      boolean first = true;
      if (this.isSetFile_path()) {
         sb.append("file_path:");
         if (this.file_path == null) {
            sb.append("null");
         } else {
            sb.append(this.file_path);
         }

         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("file_offset:");
      sb.append(this.file_offset);
      first = false;
      if (this.isSetMeta_data()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("meta_data:");
         if (this.meta_data == null) {
            sb.append("null");
         } else {
            sb.append(this.meta_data);
         }

         first = false;
      }

      if (this.isSetOffset_index_offset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("offset_index_offset:");
         sb.append(this.offset_index_offset);
         first = false;
      }

      if (this.isSetOffset_index_length()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("offset_index_length:");
         sb.append(this.offset_index_length);
         first = false;
      }

      if (this.isSetColumn_index_offset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("column_index_offset:");
         sb.append(this.column_index_offset);
         first = false;
      }

      if (this.isSetColumn_index_length()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("column_index_length:");
         sb.append(this.column_index_length);
         first = false;
      }

      if (this.isSetCrypto_metadata()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("crypto_metadata:");
         if (this.crypto_metadata == null) {
            sb.append("null");
         } else {
            sb.append(this.crypto_metadata);
         }

         first = false;
      }

      if (this.isSetEncrypted_column_metadata()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("encrypted_column_metadata:");
         if (this.encrypted_column_metadata == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.encrypted_column_metadata, sb);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.meta_data != null) {
         this.meta_data.validate();
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
      optionals = new _Fields[]{ColumnChunk._Fields.FILE_PATH, ColumnChunk._Fields.META_DATA, ColumnChunk._Fields.OFFSET_INDEX_OFFSET, ColumnChunk._Fields.OFFSET_INDEX_LENGTH, ColumnChunk._Fields.COLUMN_INDEX_OFFSET, ColumnChunk._Fields.COLUMN_INDEX_LENGTH, ColumnChunk._Fields.CRYPTO_METADATA, ColumnChunk._Fields.ENCRYPTED_COLUMN_METADATA};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ColumnChunk._Fields.FILE_PATH, new FieldMetaData("file_path", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ColumnChunk._Fields.FILE_OFFSET, new FieldMetaData("file_offset", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnChunk._Fields.META_DATA, new FieldMetaData("meta_data", (byte)2, new StructMetaData((byte)12, ColumnMetaData.class)));
      tmpMap.put(ColumnChunk._Fields.OFFSET_INDEX_OFFSET, new FieldMetaData("offset_index_offset", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnChunk._Fields.OFFSET_INDEX_LENGTH, new FieldMetaData("offset_index_length", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(ColumnChunk._Fields.COLUMN_INDEX_OFFSET, new FieldMetaData("column_index_offset", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnChunk._Fields.COLUMN_INDEX_LENGTH, new FieldMetaData("column_index_length", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(ColumnChunk._Fields.CRYPTO_METADATA, new FieldMetaData("crypto_metadata", (byte)2, new StructMetaData((byte)12, ColumnCryptoMetaData.class)));
      tmpMap.put(ColumnChunk._Fields.ENCRYPTED_COLUMN_METADATA, new FieldMetaData("encrypted_column_metadata", (byte)2, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnChunk.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      FILE_PATH((short)1, "file_path"),
      FILE_OFFSET((short)2, "file_offset"),
      META_DATA((short)3, "meta_data"),
      OFFSET_INDEX_OFFSET((short)4, "offset_index_offset"),
      OFFSET_INDEX_LENGTH((short)5, "offset_index_length"),
      COLUMN_INDEX_OFFSET((short)6, "column_index_offset"),
      COLUMN_INDEX_LENGTH((short)7, "column_index_length"),
      CRYPTO_METADATA((short)8, "crypto_metadata"),
      ENCRYPTED_COLUMN_METADATA((short)9, "encrypted_column_metadata");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return FILE_PATH;
            case 2:
               return FILE_OFFSET;
            case 3:
               return META_DATA;
            case 4:
               return OFFSET_INDEX_OFFSET;
            case 5:
               return OFFSET_INDEX_LENGTH;
            case 6:
               return COLUMN_INDEX_OFFSET;
            case 7:
               return COLUMN_INDEX_LENGTH;
            case 8:
               return CRYPTO_METADATA;
            case 9:
               return ENCRYPTED_COLUMN_METADATA;
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

   private static class ColumnChunkStandardSchemeFactory implements SchemeFactory {
      private ColumnChunkStandardSchemeFactory() {
      }

      public ColumnChunkStandardScheme getScheme() {
         return new ColumnChunkStandardScheme();
      }
   }

   private static class ColumnChunkStandardScheme extends StandardScheme {
      private ColumnChunkStandardScheme() {
      }

      public void read(TProtocol iprot, ColumnChunk struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetFile_offset()) {
                  throw new TProtocolException("Required field 'file_offset' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 11) {
                     struct.file_path = iprot.readString();
                     struct.setFile_pathIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 10) {
                     struct.file_offset = iprot.readI64();
                     struct.setFile_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 12) {
                     struct.meta_data = new ColumnMetaData();
                     struct.meta_data.read(iprot);
                     struct.setMeta_dataIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 10) {
                     struct.offset_index_offset = iprot.readI64();
                     struct.setOffset_index_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.offset_index_length = iprot.readI32();
                     struct.setOffset_index_lengthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 10) {
                     struct.column_index_offset = iprot.readI64();
                     struct.setColumn_index_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 8) {
                     struct.column_index_length = iprot.readI32();
                     struct.setColumn_index_lengthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.crypto_metadata = new ColumnCryptoMetaData();
                     struct.crypto_metadata.read(iprot);
                     struct.setCrypto_metadataIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 11) {
                     struct.encrypted_column_metadata = iprot.readBinary();
                     struct.setEncrypted_column_metadataIsSet(true);
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

      public void write(TProtocol oprot, ColumnChunk struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ColumnChunk.STRUCT_DESC);
         if (struct.file_path != null && struct.isSetFile_path()) {
            oprot.writeFieldBegin(ColumnChunk.FILE_PATH_FIELD_DESC);
            oprot.writeString(struct.file_path);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(ColumnChunk.FILE_OFFSET_FIELD_DESC);
         oprot.writeI64(struct.file_offset);
         oprot.writeFieldEnd();
         if (struct.meta_data != null && struct.isSetMeta_data()) {
            oprot.writeFieldBegin(ColumnChunk.META_DATA_FIELD_DESC);
            struct.meta_data.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetOffset_index_offset()) {
            oprot.writeFieldBegin(ColumnChunk.OFFSET_INDEX_OFFSET_FIELD_DESC);
            oprot.writeI64(struct.offset_index_offset);
            oprot.writeFieldEnd();
         }

         if (struct.isSetOffset_index_length()) {
            oprot.writeFieldBegin(ColumnChunk.OFFSET_INDEX_LENGTH_FIELD_DESC);
            oprot.writeI32(struct.offset_index_length);
            oprot.writeFieldEnd();
         }

         if (struct.isSetColumn_index_offset()) {
            oprot.writeFieldBegin(ColumnChunk.COLUMN_INDEX_OFFSET_FIELD_DESC);
            oprot.writeI64(struct.column_index_offset);
            oprot.writeFieldEnd();
         }

         if (struct.isSetColumn_index_length()) {
            oprot.writeFieldBegin(ColumnChunk.COLUMN_INDEX_LENGTH_FIELD_DESC);
            oprot.writeI32(struct.column_index_length);
            oprot.writeFieldEnd();
         }

         if (struct.crypto_metadata != null && struct.isSetCrypto_metadata()) {
            oprot.writeFieldBegin(ColumnChunk.CRYPTO_METADATA_FIELD_DESC);
            struct.crypto_metadata.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.encrypted_column_metadata != null && struct.isSetEncrypted_column_metadata()) {
            oprot.writeFieldBegin(ColumnChunk.ENCRYPTED_COLUMN_METADATA_FIELD_DESC);
            oprot.writeBinary(struct.encrypted_column_metadata);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ColumnChunkTupleSchemeFactory implements SchemeFactory {
      private ColumnChunkTupleSchemeFactory() {
      }

      public ColumnChunkTupleScheme getScheme() {
         return new ColumnChunkTupleScheme();
      }
   }

   private static class ColumnChunkTupleScheme extends TupleScheme {
      private ColumnChunkTupleScheme() {
      }

      public void write(TProtocol prot, ColumnChunk struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.file_offset);
         BitSet optionals = new BitSet();
         if (struct.isSetFile_path()) {
            optionals.set(0);
         }

         if (struct.isSetMeta_data()) {
            optionals.set(1);
         }

         if (struct.isSetOffset_index_offset()) {
            optionals.set(2);
         }

         if (struct.isSetOffset_index_length()) {
            optionals.set(3);
         }

         if (struct.isSetColumn_index_offset()) {
            optionals.set(4);
         }

         if (struct.isSetColumn_index_length()) {
            optionals.set(5);
         }

         if (struct.isSetCrypto_metadata()) {
            optionals.set(6);
         }

         if (struct.isSetEncrypted_column_metadata()) {
            optionals.set(7);
         }

         oprot.writeBitSet(optionals, 8);
         if (struct.isSetFile_path()) {
            oprot.writeString(struct.file_path);
         }

         if (struct.isSetMeta_data()) {
            struct.meta_data.write(oprot);
         }

         if (struct.isSetOffset_index_offset()) {
            oprot.writeI64(struct.offset_index_offset);
         }

         if (struct.isSetOffset_index_length()) {
            oprot.writeI32(struct.offset_index_length);
         }

         if (struct.isSetColumn_index_offset()) {
            oprot.writeI64(struct.column_index_offset);
         }

         if (struct.isSetColumn_index_length()) {
            oprot.writeI32(struct.column_index_length);
         }

         if (struct.isSetCrypto_metadata()) {
            struct.crypto_metadata.write(oprot);
         }

         if (struct.isSetEncrypted_column_metadata()) {
            oprot.writeBinary(struct.encrypted_column_metadata);
         }

      }

      public void read(TProtocol prot, ColumnChunk struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.file_offset = iprot.readI64();
         struct.setFile_offsetIsSet(true);
         BitSet incoming = iprot.readBitSet(8);
         if (incoming.get(0)) {
            struct.file_path = iprot.readString();
            struct.setFile_pathIsSet(true);
         }

         if (incoming.get(1)) {
            struct.meta_data = new ColumnMetaData();
            struct.meta_data.read(iprot);
            struct.setMeta_dataIsSet(true);
         }

         if (incoming.get(2)) {
            struct.offset_index_offset = iprot.readI64();
            struct.setOffset_index_offsetIsSet(true);
         }

         if (incoming.get(3)) {
            struct.offset_index_length = iprot.readI32();
            struct.setOffset_index_lengthIsSet(true);
         }

         if (incoming.get(4)) {
            struct.column_index_offset = iprot.readI64();
            struct.setColumn_index_offsetIsSet(true);
         }

         if (incoming.get(5)) {
            struct.column_index_length = iprot.readI32();
            struct.setColumn_index_lengthIsSet(true);
         }

         if (incoming.get(6)) {
            struct.crypto_metadata = new ColumnCryptoMetaData();
            struct.crypto_metadata.read(iprot);
            struct.setCrypto_metadataIsSet(true);
         }

         if (incoming.get(7)) {
            struct.encrypted_column_metadata = iprot.readBinary();
            struct.setEncrypted_column_metadataIsSet(true);
         }

      }
   }
}
