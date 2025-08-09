package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shaded.parquet.org.apache.thrift.EncodingUtils;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.ListMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
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

public class FileMetaData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("FileMetaData");
   private static final TField VERSION_FIELD_DESC = new TField("version", (byte)8, (short)1);
   private static final TField SCHEMA_FIELD_DESC = new TField("schema", (byte)15, (short)2);
   private static final TField NUM_ROWS_FIELD_DESC = new TField("num_rows", (byte)10, (short)3);
   private static final TField ROW_GROUPS_FIELD_DESC = new TField("row_groups", (byte)15, (short)4);
   private static final TField KEY_VALUE_METADATA_FIELD_DESC = new TField("key_value_metadata", (byte)15, (short)5);
   private static final TField CREATED_BY_FIELD_DESC = new TField("created_by", (byte)11, (short)6);
   private static final TField COLUMN_ORDERS_FIELD_DESC = new TField("column_orders", (byte)15, (short)7);
   private static final TField ENCRYPTION_ALGORITHM_FIELD_DESC = new TField("encryption_algorithm", (byte)12, (short)8);
   private static final TField FOOTER_SIGNING_KEY_METADATA_FIELD_DESC = new TField("footer_signing_key_metadata", (byte)11, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new FileMetaDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new FileMetaDataTupleSchemeFactory();
   public int version;
   @Nullable
   public List schema;
   public long num_rows;
   @Nullable
   public List row_groups;
   @Nullable
   public List key_value_metadata;
   @Nullable
   public String created_by;
   @Nullable
   public List column_orders;
   @Nullable
   public EncryptionAlgorithm encryption_algorithm;
   @Nullable
   public ByteBuffer footer_signing_key_metadata;
   private static final int __VERSION_ISSET_ID = 0;
   private static final int __NUM_ROWS_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public FileMetaData() {
      this.__isset_bitfield = 0;
   }

   public FileMetaData(int version, List schema, long num_rows, List row_groups) {
      this();
      this.version = version;
      this.setVersionIsSet(true);
      this.schema = schema;
      this.num_rows = num_rows;
      this.setNum_rowsIsSet(true);
      this.row_groups = row_groups;
   }

   public FileMetaData(FileMetaData other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.version = other.version;
      if (other.isSetSchema()) {
         List<SchemaElement> __this__schema = new ArrayList(other.schema.size());

         for(SchemaElement other_element : other.schema) {
            __this__schema.add(new SchemaElement(other_element));
         }

         this.schema = __this__schema;
      }

      this.num_rows = other.num_rows;
      if (other.isSetRow_groups()) {
         List<RowGroup> __this__row_groups = new ArrayList(other.row_groups.size());

         for(RowGroup other_element : other.row_groups) {
            __this__row_groups.add(new RowGroup(other_element));
         }

         this.row_groups = __this__row_groups;
      }

      if (other.isSetKey_value_metadata()) {
         List<KeyValue> __this__key_value_metadata = new ArrayList(other.key_value_metadata.size());

         for(KeyValue other_element : other.key_value_metadata) {
            __this__key_value_metadata.add(new KeyValue(other_element));
         }

         this.key_value_metadata = __this__key_value_metadata;
      }

      if (other.isSetCreated_by()) {
         this.created_by = other.created_by;
      }

      if (other.isSetColumn_orders()) {
         List<ColumnOrder> __this__column_orders = new ArrayList(other.column_orders.size());

         for(ColumnOrder other_element : other.column_orders) {
            __this__column_orders.add(new ColumnOrder(other_element));
         }

         this.column_orders = __this__column_orders;
      }

      if (other.isSetEncryption_algorithm()) {
         this.encryption_algorithm = new EncryptionAlgorithm(other.encryption_algorithm);
      }

      if (other.isSetFooter_signing_key_metadata()) {
         this.footer_signing_key_metadata = TBaseHelper.copyBinary(other.footer_signing_key_metadata);
      }

   }

   public FileMetaData deepCopy() {
      return new FileMetaData(this);
   }

   public void clear() {
      this.setVersionIsSet(false);
      this.version = 0;
      this.schema = null;
      this.setNum_rowsIsSet(false);
      this.num_rows = 0L;
      this.row_groups = null;
      this.key_value_metadata = null;
      this.created_by = null;
      this.column_orders = null;
      this.encryption_algorithm = null;
      this.footer_signing_key_metadata = null;
   }

   public int getVersion() {
      return this.version;
   }

   public FileMetaData setVersion(int version) {
      this.version = version;
      this.setVersionIsSet(true);
      return this;
   }

   public void unsetVersion() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetVersion() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setVersionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public int getSchemaSize() {
      return this.schema == null ? 0 : this.schema.size();
   }

   @Nullable
   public Iterator getSchemaIterator() {
      return this.schema == null ? null : this.schema.iterator();
   }

   public void addToSchema(SchemaElement elem) {
      if (this.schema == null) {
         this.schema = new ArrayList();
      }

      this.schema.add(elem);
   }

   @Nullable
   public List getSchema() {
      return this.schema;
   }

   public FileMetaData setSchema(@Nullable List schema) {
      this.schema = schema;
      return this;
   }

   public void unsetSchema() {
      this.schema = null;
   }

   public boolean isSetSchema() {
      return this.schema != null;
   }

   public void setSchemaIsSet(boolean value) {
      if (!value) {
         this.schema = null;
      }

   }

   public long getNum_rows() {
      return this.num_rows;
   }

   public FileMetaData setNum_rows(long num_rows) {
      this.num_rows = num_rows;
      this.setNum_rowsIsSet(true);
      return this;
   }

   public void unsetNum_rows() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetNum_rows() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setNum_rowsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public int getRow_groupsSize() {
      return this.row_groups == null ? 0 : this.row_groups.size();
   }

   @Nullable
   public Iterator getRow_groupsIterator() {
      return this.row_groups == null ? null : this.row_groups.iterator();
   }

   public void addToRow_groups(RowGroup elem) {
      if (this.row_groups == null) {
         this.row_groups = new ArrayList();
      }

      this.row_groups.add(elem);
   }

   @Nullable
   public List getRow_groups() {
      return this.row_groups;
   }

   public FileMetaData setRow_groups(@Nullable List row_groups) {
      this.row_groups = row_groups;
      return this;
   }

   public void unsetRow_groups() {
      this.row_groups = null;
   }

   public boolean isSetRow_groups() {
      return this.row_groups != null;
   }

   public void setRow_groupsIsSet(boolean value) {
      if (!value) {
         this.row_groups = null;
      }

   }

   public int getKey_value_metadataSize() {
      return this.key_value_metadata == null ? 0 : this.key_value_metadata.size();
   }

   @Nullable
   public Iterator getKey_value_metadataIterator() {
      return this.key_value_metadata == null ? null : this.key_value_metadata.iterator();
   }

   public void addToKey_value_metadata(KeyValue elem) {
      if (this.key_value_metadata == null) {
         this.key_value_metadata = new ArrayList();
      }

      this.key_value_metadata.add(elem);
   }

   @Nullable
   public List getKey_value_metadata() {
      return this.key_value_metadata;
   }

   public FileMetaData setKey_value_metadata(@Nullable List key_value_metadata) {
      this.key_value_metadata = key_value_metadata;
      return this;
   }

   public void unsetKey_value_metadata() {
      this.key_value_metadata = null;
   }

   public boolean isSetKey_value_metadata() {
      return this.key_value_metadata != null;
   }

   public void setKey_value_metadataIsSet(boolean value) {
      if (!value) {
         this.key_value_metadata = null;
      }

   }

   @Nullable
   public String getCreated_by() {
      return this.created_by;
   }

   public FileMetaData setCreated_by(@Nullable String created_by) {
      this.created_by = created_by;
      return this;
   }

   public void unsetCreated_by() {
      this.created_by = null;
   }

   public boolean isSetCreated_by() {
      return this.created_by != null;
   }

   public void setCreated_byIsSet(boolean value) {
      if (!value) {
         this.created_by = null;
      }

   }

   public int getColumn_ordersSize() {
      return this.column_orders == null ? 0 : this.column_orders.size();
   }

   @Nullable
   public Iterator getColumn_ordersIterator() {
      return this.column_orders == null ? null : this.column_orders.iterator();
   }

   public void addToColumn_orders(ColumnOrder elem) {
      if (this.column_orders == null) {
         this.column_orders = new ArrayList();
      }

      this.column_orders.add(elem);
   }

   @Nullable
   public List getColumn_orders() {
      return this.column_orders;
   }

   public FileMetaData setColumn_orders(@Nullable List column_orders) {
      this.column_orders = column_orders;
      return this;
   }

   public void unsetColumn_orders() {
      this.column_orders = null;
   }

   public boolean isSetColumn_orders() {
      return this.column_orders != null;
   }

   public void setColumn_ordersIsSet(boolean value) {
      if (!value) {
         this.column_orders = null;
      }

   }

   @Nullable
   public EncryptionAlgorithm getEncryption_algorithm() {
      return this.encryption_algorithm;
   }

   public FileMetaData setEncryption_algorithm(@Nullable EncryptionAlgorithm encryption_algorithm) {
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

   public byte[] getFooter_signing_key_metadata() {
      this.setFooter_signing_key_metadata(TBaseHelper.rightSize(this.footer_signing_key_metadata));
      return this.footer_signing_key_metadata == null ? null : this.footer_signing_key_metadata.array();
   }

   public ByteBuffer bufferForFooter_signing_key_metadata() {
      return TBaseHelper.copyBinary(this.footer_signing_key_metadata);
   }

   public FileMetaData setFooter_signing_key_metadata(byte[] footer_signing_key_metadata) {
      this.footer_signing_key_metadata = footer_signing_key_metadata == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)footer_signing_key_metadata).clone());
      return this;
   }

   public FileMetaData setFooter_signing_key_metadata(@Nullable ByteBuffer footer_signing_key_metadata) {
      this.footer_signing_key_metadata = TBaseHelper.copyBinary(footer_signing_key_metadata);
      return this;
   }

   public void unsetFooter_signing_key_metadata() {
      this.footer_signing_key_metadata = null;
   }

   public boolean isSetFooter_signing_key_metadata() {
      return this.footer_signing_key_metadata != null;
   }

   public void setFooter_signing_key_metadataIsSet(boolean value) {
      if (!value) {
         this.footer_signing_key_metadata = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case VERSION:
            if (value == null) {
               this.unsetVersion();
            } else {
               this.setVersion((Integer)value);
            }
            break;
         case SCHEMA:
            if (value == null) {
               this.unsetSchema();
            } else {
               this.setSchema((List)value);
            }
            break;
         case NUM_ROWS:
            if (value == null) {
               this.unsetNum_rows();
            } else {
               this.setNum_rows((Long)value);
            }
            break;
         case ROW_GROUPS:
            if (value == null) {
               this.unsetRow_groups();
            } else {
               this.setRow_groups((List)value);
            }
            break;
         case KEY_VALUE_METADATA:
            if (value == null) {
               this.unsetKey_value_metadata();
            } else {
               this.setKey_value_metadata((List)value);
            }
            break;
         case CREATED_BY:
            if (value == null) {
               this.unsetCreated_by();
            } else {
               this.setCreated_by((String)value);
            }
            break;
         case COLUMN_ORDERS:
            if (value == null) {
               this.unsetColumn_orders();
            } else {
               this.setColumn_orders((List)value);
            }
            break;
         case ENCRYPTION_ALGORITHM:
            if (value == null) {
               this.unsetEncryption_algorithm();
            } else {
               this.setEncryption_algorithm((EncryptionAlgorithm)value);
            }
            break;
         case FOOTER_SIGNING_KEY_METADATA:
            if (value == null) {
               this.unsetFooter_signing_key_metadata();
            } else if (value instanceof byte[]) {
               this.setFooter_signing_key_metadata((byte[])value);
            } else {
               this.setFooter_signing_key_metadata((ByteBuffer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case VERSION:
            return this.getVersion();
         case SCHEMA:
            return this.getSchema();
         case NUM_ROWS:
            return this.getNum_rows();
         case ROW_GROUPS:
            return this.getRow_groups();
         case KEY_VALUE_METADATA:
            return this.getKey_value_metadata();
         case CREATED_BY:
            return this.getCreated_by();
         case COLUMN_ORDERS:
            return this.getColumn_orders();
         case ENCRYPTION_ALGORITHM:
            return this.getEncryption_algorithm();
         case FOOTER_SIGNING_KEY_METADATA:
            return this.getFooter_signing_key_metadata();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case VERSION:
               return this.isSetVersion();
            case SCHEMA:
               return this.isSetSchema();
            case NUM_ROWS:
               return this.isSetNum_rows();
            case ROW_GROUPS:
               return this.isSetRow_groups();
            case KEY_VALUE_METADATA:
               return this.isSetKey_value_metadata();
            case CREATED_BY:
               return this.isSetCreated_by();
            case COLUMN_ORDERS:
               return this.isSetColumn_orders();
            case ENCRYPTION_ALGORITHM:
               return this.isSetEncryption_algorithm();
            case FOOTER_SIGNING_KEY_METADATA:
               return this.isSetFooter_signing_key_metadata();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof FileMetaData ? this.equals((FileMetaData)that) : false;
   }

   public boolean equals(FileMetaData that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_version = true;
         boolean that_present_version = true;
         if (this_present_version || that_present_version) {
            if (!this_present_version || !that_present_version) {
               return false;
            }

            if (this.version != that.version) {
               return false;
            }
         }

         boolean this_present_schema = this.isSetSchema();
         boolean that_present_schema = that.isSetSchema();
         if (this_present_schema || that_present_schema) {
            if (!this_present_schema || !that_present_schema) {
               return false;
            }

            if (!this.schema.equals(that.schema)) {
               return false;
            }
         }

         boolean this_present_num_rows = true;
         boolean that_present_num_rows = true;
         if (this_present_num_rows || that_present_num_rows) {
            if (!this_present_num_rows || !that_present_num_rows) {
               return false;
            }

            if (this.num_rows != that.num_rows) {
               return false;
            }
         }

         boolean this_present_row_groups = this.isSetRow_groups();
         boolean that_present_row_groups = that.isSetRow_groups();
         if (this_present_row_groups || that_present_row_groups) {
            if (!this_present_row_groups || !that_present_row_groups) {
               return false;
            }

            if (!this.row_groups.equals(that.row_groups)) {
               return false;
            }
         }

         boolean this_present_key_value_metadata = this.isSetKey_value_metadata();
         boolean that_present_key_value_metadata = that.isSetKey_value_metadata();
         if (this_present_key_value_metadata || that_present_key_value_metadata) {
            if (!this_present_key_value_metadata || !that_present_key_value_metadata) {
               return false;
            }

            if (!this.key_value_metadata.equals(that.key_value_metadata)) {
               return false;
            }
         }

         boolean this_present_created_by = this.isSetCreated_by();
         boolean that_present_created_by = that.isSetCreated_by();
         if (this_present_created_by || that_present_created_by) {
            if (!this_present_created_by || !that_present_created_by) {
               return false;
            }

            if (!this.created_by.equals(that.created_by)) {
               return false;
            }
         }

         boolean this_present_column_orders = this.isSetColumn_orders();
         boolean that_present_column_orders = that.isSetColumn_orders();
         if (this_present_column_orders || that_present_column_orders) {
            if (!this_present_column_orders || !that_present_column_orders) {
               return false;
            }

            if (!this.column_orders.equals(that.column_orders)) {
               return false;
            }
         }

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

         boolean this_present_footer_signing_key_metadata = this.isSetFooter_signing_key_metadata();
         boolean that_present_footer_signing_key_metadata = that.isSetFooter_signing_key_metadata();
         if (this_present_footer_signing_key_metadata || that_present_footer_signing_key_metadata) {
            if (!this_present_footer_signing_key_metadata || !that_present_footer_signing_key_metadata) {
               return false;
            }

            if (!this.footer_signing_key_metadata.equals(that.footer_signing_key_metadata)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.version;
      hashCode = hashCode * 8191 + (this.isSetSchema() ? 131071 : 524287);
      if (this.isSetSchema()) {
         hashCode = hashCode * 8191 + this.schema.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.num_rows);
      hashCode = hashCode * 8191 + (this.isSetRow_groups() ? 131071 : 524287);
      if (this.isSetRow_groups()) {
         hashCode = hashCode * 8191 + this.row_groups.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetKey_value_metadata() ? 131071 : 524287);
      if (this.isSetKey_value_metadata()) {
         hashCode = hashCode * 8191 + this.key_value_metadata.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCreated_by() ? 131071 : 524287);
      if (this.isSetCreated_by()) {
         hashCode = hashCode * 8191 + this.created_by.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColumn_orders() ? 131071 : 524287);
      if (this.isSetColumn_orders()) {
         hashCode = hashCode * 8191 + this.column_orders.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetEncryption_algorithm() ? 131071 : 524287);
      if (this.isSetEncryption_algorithm()) {
         hashCode = hashCode * 8191 + this.encryption_algorithm.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFooter_signing_key_metadata() ? 131071 : 524287);
      if (this.isSetFooter_signing_key_metadata()) {
         hashCode = hashCode * 8191 + this.footer_signing_key_metadata.hashCode();
      }

      return hashCode;
   }

   public int compareTo(FileMetaData other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetVersion(), other.isSetVersion());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetVersion()) {
               lastComparison = TBaseHelper.compareTo(this.version, other.version);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetSchema(), other.isSetSchema());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSchema()) {
                  lastComparison = TBaseHelper.compareTo(this.schema, other.schema);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetNum_rows(), other.isSetNum_rows());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetNum_rows()) {
                     lastComparison = TBaseHelper.compareTo(this.num_rows, other.num_rows);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetRow_groups(), other.isSetRow_groups());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetRow_groups()) {
                        lastComparison = TBaseHelper.compareTo(this.row_groups, other.row_groups);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetKey_value_metadata(), other.isSetKey_value_metadata());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetKey_value_metadata()) {
                           lastComparison = TBaseHelper.compareTo(this.key_value_metadata, other.key_value_metadata);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetCreated_by(), other.isSetCreated_by());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetCreated_by()) {
                              lastComparison = TBaseHelper.compareTo(this.created_by, other.created_by);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetColumn_orders(), other.isSetColumn_orders());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetColumn_orders()) {
                                 lastComparison = TBaseHelper.compareTo(this.column_orders, other.column_orders);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

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

                                 lastComparison = Boolean.compare(this.isSetFooter_signing_key_metadata(), other.isSetFooter_signing_key_metadata());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetFooter_signing_key_metadata()) {
                                       lastComparison = TBaseHelper.compareTo((Comparable)this.footer_signing_key_metadata, (Comparable)other.footer_signing_key_metadata);
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
      return FileMetaData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("FileMetaData(");
      boolean first = true;
      sb.append("version:");
      sb.append(this.version);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("schema:");
      if (this.schema == null) {
         sb.append("null");
      } else {
         sb.append(this.schema);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("num_rows:");
      sb.append(this.num_rows);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("row_groups:");
      if (this.row_groups == null) {
         sb.append("null");
      } else {
         sb.append(this.row_groups);
      }

      first = false;
      if (this.isSetKey_value_metadata()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("key_value_metadata:");
         if (this.key_value_metadata == null) {
            sb.append("null");
         } else {
            sb.append(this.key_value_metadata);
         }

         first = false;
      }

      if (this.isSetCreated_by()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("created_by:");
         if (this.created_by == null) {
            sb.append("null");
         } else {
            sb.append(this.created_by);
         }

         first = false;
      }

      if (this.isSetColumn_orders()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("column_orders:");
         if (this.column_orders == null) {
            sb.append("null");
         } else {
            sb.append(this.column_orders);
         }

         first = false;
      }

      if (this.isSetEncryption_algorithm()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("encryption_algorithm:");
         if (this.encryption_algorithm == null) {
            sb.append("null");
         } else {
            sb.append(this.encryption_algorithm);
         }

         first = false;
      }

      if (this.isSetFooter_signing_key_metadata()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("footer_signing_key_metadata:");
         if (this.footer_signing_key_metadata == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.footer_signing_key_metadata, sb);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.schema == null) {
         throw new TProtocolException("Required field 'schema' was not present! Struct: " + this.toString());
      } else if (this.row_groups == null) {
         throw new TProtocolException("Required field 'row_groups' was not present! Struct: " + this.toString());
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
      optionals = new _Fields[]{FileMetaData._Fields.KEY_VALUE_METADATA, FileMetaData._Fields.CREATED_BY, FileMetaData._Fields.COLUMN_ORDERS, FileMetaData._Fields.ENCRYPTION_ALGORITHM, FileMetaData._Fields.FOOTER_SIGNING_KEY_METADATA};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(FileMetaData._Fields.VERSION, new FieldMetaData("version", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(FileMetaData._Fields.SCHEMA, new FieldMetaData("schema", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, SchemaElement.class))));
      tmpMap.put(FileMetaData._Fields.NUM_ROWS, new FieldMetaData("num_rows", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(FileMetaData._Fields.ROW_GROUPS, new FieldMetaData("row_groups", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, RowGroup.class))));
      tmpMap.put(FileMetaData._Fields.KEY_VALUE_METADATA, new FieldMetaData("key_value_metadata", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, KeyValue.class))));
      tmpMap.put(FileMetaData._Fields.CREATED_BY, new FieldMetaData("created_by", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(FileMetaData._Fields.COLUMN_ORDERS, new FieldMetaData("column_orders", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnOrder.class))));
      tmpMap.put(FileMetaData._Fields.ENCRYPTION_ALGORITHM, new FieldMetaData("encryption_algorithm", (byte)2, new StructMetaData((byte)12, EncryptionAlgorithm.class)));
      tmpMap.put(FileMetaData._Fields.FOOTER_SIGNING_KEY_METADATA, new FieldMetaData("footer_signing_key_metadata", (byte)2, new FieldValueMetaData((byte)11, true)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(FileMetaData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VERSION((short)1, "version"),
      SCHEMA((short)2, "schema"),
      NUM_ROWS((short)3, "num_rows"),
      ROW_GROUPS((short)4, "row_groups"),
      KEY_VALUE_METADATA((short)5, "key_value_metadata"),
      CREATED_BY((short)6, "created_by"),
      COLUMN_ORDERS((short)7, "column_orders"),
      ENCRYPTION_ALGORITHM((short)8, "encryption_algorithm"),
      FOOTER_SIGNING_KEY_METADATA((short)9, "footer_signing_key_metadata");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VERSION;
            case 2:
               return SCHEMA;
            case 3:
               return NUM_ROWS;
            case 4:
               return ROW_GROUPS;
            case 5:
               return KEY_VALUE_METADATA;
            case 6:
               return CREATED_BY;
            case 7:
               return COLUMN_ORDERS;
            case 8:
               return ENCRYPTION_ALGORITHM;
            case 9:
               return FOOTER_SIGNING_KEY_METADATA;
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

   private static class FileMetaDataStandardSchemeFactory implements SchemeFactory {
      private FileMetaDataStandardSchemeFactory() {
      }

      public FileMetaDataStandardScheme getScheme() {
         return new FileMetaDataStandardScheme();
      }
   }

   private static class FileMetaDataStandardScheme extends StandardScheme {
      private FileMetaDataStandardScheme() {
      }

      public void read(TProtocol iprot, FileMetaData struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetVersion()) {
                  throw new TProtocolException("Required field 'version' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetNum_rows()) {
                  throw new TProtocolException("Required field 'num_rows' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.version = iprot.readI32();
                     struct.setVersionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list136 = iprot.readListBegin();
                  struct.schema = new ArrayList(_list136.size);

                  for(int _i138 = 0; _i138 < _list136.size; ++_i138) {
                     SchemaElement _elem137 = new SchemaElement();
                     _elem137.read(iprot);
                     struct.schema.add(_elem137);
                  }

                  iprot.readListEnd();
                  struct.setSchemaIsSet(true);
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.num_rows = iprot.readI64();
                     struct.setNum_rowsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list139 = iprot.readListBegin();
                  struct.row_groups = new ArrayList(_list139.size);

                  for(int _i141 = 0; _i141 < _list139.size; ++_i141) {
                     RowGroup _elem140 = new RowGroup();
                     _elem140.read(iprot);
                     struct.row_groups.add(_elem140);
                  }

                  iprot.readListEnd();
                  struct.setRow_groupsIsSet(true);
                  break;
               case 5:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list142 = iprot.readListBegin();
                  struct.key_value_metadata = new ArrayList(_list142.size);

                  for(int _i144 = 0; _i144 < _list142.size; ++_i144) {
                     KeyValue _elem143 = new KeyValue();
                     _elem143.read(iprot);
                     struct.key_value_metadata.add(_elem143);
                  }

                  iprot.readListEnd();
                  struct.setKey_value_metadataIsSet(true);
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.created_by = iprot.readString();
                     struct.setCreated_byIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list145 = iprot.readListBegin();
                  struct.column_orders = new ArrayList(_list145.size);

                  for(int _i147 = 0; _i147 < _list145.size; ++_i147) {
                     ColumnOrder _elem146 = new ColumnOrder();
                     _elem146.read(iprot);
                     struct.column_orders.add(_elem146);
                  }

                  iprot.readListEnd();
                  struct.setColumn_ordersIsSet(true);
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.encryption_algorithm = new EncryptionAlgorithm();
                     struct.encryption_algorithm.read(iprot);
                     struct.setEncryption_algorithmIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 11) {
                     struct.footer_signing_key_metadata = iprot.readBinary();
                     struct.setFooter_signing_key_metadataIsSet(true);
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

      public void write(TProtocol oprot, FileMetaData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(FileMetaData.STRUCT_DESC);
         oprot.writeFieldBegin(FileMetaData.VERSION_FIELD_DESC);
         oprot.writeI32(struct.version);
         oprot.writeFieldEnd();
         if (struct.schema != null) {
            oprot.writeFieldBegin(FileMetaData.SCHEMA_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.schema.size()));

            for(SchemaElement _iter148 : struct.schema) {
               _iter148.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(FileMetaData.NUM_ROWS_FIELD_DESC);
         oprot.writeI64(struct.num_rows);
         oprot.writeFieldEnd();
         if (struct.row_groups != null) {
            oprot.writeFieldBegin(FileMetaData.ROW_GROUPS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.row_groups.size()));

            for(RowGroup _iter149 : struct.row_groups) {
               _iter149.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.key_value_metadata != null && struct.isSetKey_value_metadata()) {
            oprot.writeFieldBegin(FileMetaData.KEY_VALUE_METADATA_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.key_value_metadata.size()));

            for(KeyValue _iter150 : struct.key_value_metadata) {
               _iter150.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.created_by != null && struct.isSetCreated_by()) {
            oprot.writeFieldBegin(FileMetaData.CREATED_BY_FIELD_DESC);
            oprot.writeString(struct.created_by);
            oprot.writeFieldEnd();
         }

         if (struct.column_orders != null && struct.isSetColumn_orders()) {
            oprot.writeFieldBegin(FileMetaData.COLUMN_ORDERS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.column_orders.size()));

            for(ColumnOrder _iter151 : struct.column_orders) {
               _iter151.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.encryption_algorithm != null && struct.isSetEncryption_algorithm()) {
            oprot.writeFieldBegin(FileMetaData.ENCRYPTION_ALGORITHM_FIELD_DESC);
            struct.encryption_algorithm.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.footer_signing_key_metadata != null && struct.isSetFooter_signing_key_metadata()) {
            oprot.writeFieldBegin(FileMetaData.FOOTER_SIGNING_KEY_METADATA_FIELD_DESC);
            oprot.writeBinary(struct.footer_signing_key_metadata);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class FileMetaDataTupleSchemeFactory implements SchemeFactory {
      private FileMetaDataTupleSchemeFactory() {
      }

      public FileMetaDataTupleScheme getScheme() {
         return new FileMetaDataTupleScheme();
      }
   }

   private static class FileMetaDataTupleScheme extends TupleScheme {
      private FileMetaDataTupleScheme() {
      }

      public void write(TProtocol prot, FileMetaData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.version);
         oprot.writeI32(struct.schema.size());

         for(SchemaElement _iter152 : struct.schema) {
            _iter152.write(oprot);
         }

         oprot.writeI64(struct.num_rows);
         oprot.writeI32(struct.row_groups.size());

         for(RowGroup _iter153 : struct.row_groups) {
            _iter153.write(oprot);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetKey_value_metadata()) {
            optionals.set(0);
         }

         if (struct.isSetCreated_by()) {
            optionals.set(1);
         }

         if (struct.isSetColumn_orders()) {
            optionals.set(2);
         }

         if (struct.isSetEncryption_algorithm()) {
            optionals.set(3);
         }

         if (struct.isSetFooter_signing_key_metadata()) {
            optionals.set(4);
         }

         oprot.writeBitSet(optionals, 5);
         if (struct.isSetKey_value_metadata()) {
            oprot.writeI32(struct.key_value_metadata.size());

            for(KeyValue _iter154 : struct.key_value_metadata) {
               _iter154.write(oprot);
            }
         }

         if (struct.isSetCreated_by()) {
            oprot.writeString(struct.created_by);
         }

         if (struct.isSetColumn_orders()) {
            oprot.writeI32(struct.column_orders.size());

            for(ColumnOrder _iter155 : struct.column_orders) {
               _iter155.write(oprot);
            }
         }

         if (struct.isSetEncryption_algorithm()) {
            struct.encryption_algorithm.write(oprot);
         }

         if (struct.isSetFooter_signing_key_metadata()) {
            oprot.writeBinary(struct.footer_signing_key_metadata);
         }

      }

      public void read(TProtocol prot, FileMetaData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.version = iprot.readI32();
         struct.setVersionIsSet(true);
         TList _list156 = iprot.readListBegin((byte)12);
         struct.schema = new ArrayList(_list156.size);

         for(int _i158 = 0; _i158 < _list156.size; ++_i158) {
            SchemaElement _elem157 = new SchemaElement();
            _elem157.read(iprot);
            struct.schema.add(_elem157);
         }

         struct.setSchemaIsSet(true);
         struct.num_rows = iprot.readI64();
         struct.setNum_rowsIsSet(true);
         _list156 = iprot.readListBegin((byte)12);
         struct.row_groups = new ArrayList(_list156.size);

         for(int _i161 = 0; _i161 < _list156.size; ++_i161) {
            RowGroup _elem160 = new RowGroup();
            _elem160.read(iprot);
            struct.row_groups.add(_elem160);
         }

         struct.setRow_groupsIsSet(true);
         BitSet incoming = iprot.readBitSet(5);
         if (incoming.get(0)) {
            TList _list162 = iprot.readListBegin((byte)12);
            struct.key_value_metadata = new ArrayList(_list162.size);

            for(int _i164 = 0; _i164 < _list162.size; ++_i164) {
               KeyValue _elem163 = new KeyValue();
               _elem163.read(iprot);
               struct.key_value_metadata.add(_elem163);
            }

            struct.setKey_value_metadataIsSet(true);
         }

         if (incoming.get(1)) {
            struct.created_by = iprot.readString();
            struct.setCreated_byIsSet(true);
         }

         if (incoming.get(2)) {
            TList _list165 = iprot.readListBegin((byte)12);
            struct.column_orders = new ArrayList(_list165.size);

            for(int _i167 = 0; _i167 < _list165.size; ++_i167) {
               ColumnOrder _elem166 = new ColumnOrder();
               _elem166.read(iprot);
               struct.column_orders.add(_elem166);
            }

            struct.setColumn_ordersIsSet(true);
         }

         if (incoming.get(3)) {
            struct.encryption_algorithm = new EncryptionAlgorithm();
            struct.encryption_algorithm.read(iprot);
            struct.setEncryption_algorithmIsSet(true);
         }

         if (incoming.get(4)) {
            struct.footer_signing_key_metadata = iprot.readBinary();
            struct.setFooter_signing_key_metadataIsSet(true);
         }

      }
   }
}
