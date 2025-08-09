package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import shaded.parquet.org.apache.thrift.meta_data.EnumMetaData;
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

public class PageHeader implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PageHeader");
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)1);
   private static final TField UNCOMPRESSED_PAGE_SIZE_FIELD_DESC = new TField("uncompressed_page_size", (byte)8, (short)2);
   private static final TField COMPRESSED_PAGE_SIZE_FIELD_DESC = new TField("compressed_page_size", (byte)8, (short)3);
   private static final TField CRC_FIELD_DESC = new TField("crc", (byte)8, (short)4);
   private static final TField DATA_PAGE_HEADER_FIELD_DESC = new TField("data_page_header", (byte)12, (short)5);
   private static final TField INDEX_PAGE_HEADER_FIELD_DESC = new TField("index_page_header", (byte)12, (short)6);
   private static final TField DICTIONARY_PAGE_HEADER_FIELD_DESC = new TField("dictionary_page_header", (byte)12, (short)7);
   private static final TField DATA_PAGE_HEADER_V2_FIELD_DESC = new TField("data_page_header_v2", (byte)12, (short)8);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PageHeaderStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PageHeaderTupleSchemeFactory();
   @Nullable
   public PageType type;
   public int uncompressed_page_size;
   public int compressed_page_size;
   public int crc;
   @Nullable
   public DataPageHeader data_page_header;
   @Nullable
   public IndexPageHeader index_page_header;
   @Nullable
   public DictionaryPageHeader dictionary_page_header;
   @Nullable
   public DataPageHeaderV2 data_page_header_v2;
   private static final int __UNCOMPRESSED_PAGE_SIZE_ISSET_ID = 0;
   private static final int __COMPRESSED_PAGE_SIZE_ISSET_ID = 1;
   private static final int __CRC_ISSET_ID = 2;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PageHeader() {
      this.__isset_bitfield = 0;
   }

   public PageHeader(PageType type, int uncompressed_page_size, int compressed_page_size) {
      this();
      this.type = type;
      this.uncompressed_page_size = uncompressed_page_size;
      this.setUncompressed_page_sizeIsSet(true);
      this.compressed_page_size = compressed_page_size;
      this.setCompressed_page_sizeIsSet(true);
   }

   public PageHeader(PageHeader other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetType()) {
         this.type = other.type;
      }

      this.uncompressed_page_size = other.uncompressed_page_size;
      this.compressed_page_size = other.compressed_page_size;
      this.crc = other.crc;
      if (other.isSetData_page_header()) {
         this.data_page_header = new DataPageHeader(other.data_page_header);
      }

      if (other.isSetIndex_page_header()) {
         this.index_page_header = new IndexPageHeader(other.index_page_header);
      }

      if (other.isSetDictionary_page_header()) {
         this.dictionary_page_header = new DictionaryPageHeader(other.dictionary_page_header);
      }

      if (other.isSetData_page_header_v2()) {
         this.data_page_header_v2 = new DataPageHeaderV2(other.data_page_header_v2);
      }

   }

   public PageHeader deepCopy() {
      return new PageHeader(this);
   }

   public void clear() {
      this.type = null;
      this.setUncompressed_page_sizeIsSet(false);
      this.uncompressed_page_size = 0;
      this.setCompressed_page_sizeIsSet(false);
      this.compressed_page_size = 0;
      this.setCrcIsSet(false);
      this.crc = 0;
      this.data_page_header = null;
      this.index_page_header = null;
      this.dictionary_page_header = null;
      this.data_page_header_v2 = null;
   }

   @Nullable
   public PageType getType() {
      return this.type;
   }

   public PageHeader setType(@Nullable PageType type) {
      this.type = type;
      return this;
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   public int getUncompressed_page_size() {
      return this.uncompressed_page_size;
   }

   public PageHeader setUncompressed_page_size(int uncompressed_page_size) {
      this.uncompressed_page_size = uncompressed_page_size;
      this.setUncompressed_page_sizeIsSet(true);
      return this;
   }

   public void unsetUncompressed_page_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetUncompressed_page_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setUncompressed_page_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public int getCompressed_page_size() {
      return this.compressed_page_size;
   }

   public PageHeader setCompressed_page_size(int compressed_page_size) {
      this.compressed_page_size = compressed_page_size;
      this.setCompressed_page_sizeIsSet(true);
      return this;
   }

   public void unsetCompressed_page_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetCompressed_page_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setCompressed_page_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public int getCrc() {
      return this.crc;
   }

   public PageHeader setCrc(int crc) {
      this.crc = crc;
      this.setCrcIsSet(true);
      return this;
   }

   public void unsetCrc() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetCrc() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setCrcIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   @Nullable
   public DataPageHeader getData_page_header() {
      return this.data_page_header;
   }

   public PageHeader setData_page_header(@Nullable DataPageHeader data_page_header) {
      this.data_page_header = data_page_header;
      return this;
   }

   public void unsetData_page_header() {
      this.data_page_header = null;
   }

   public boolean isSetData_page_header() {
      return this.data_page_header != null;
   }

   public void setData_page_headerIsSet(boolean value) {
      if (!value) {
         this.data_page_header = null;
      }

   }

   @Nullable
   public IndexPageHeader getIndex_page_header() {
      return this.index_page_header;
   }

   public PageHeader setIndex_page_header(@Nullable IndexPageHeader index_page_header) {
      this.index_page_header = index_page_header;
      return this;
   }

   public void unsetIndex_page_header() {
      this.index_page_header = null;
   }

   public boolean isSetIndex_page_header() {
      return this.index_page_header != null;
   }

   public void setIndex_page_headerIsSet(boolean value) {
      if (!value) {
         this.index_page_header = null;
      }

   }

   @Nullable
   public DictionaryPageHeader getDictionary_page_header() {
      return this.dictionary_page_header;
   }

   public PageHeader setDictionary_page_header(@Nullable DictionaryPageHeader dictionary_page_header) {
      this.dictionary_page_header = dictionary_page_header;
      return this;
   }

   public void unsetDictionary_page_header() {
      this.dictionary_page_header = null;
   }

   public boolean isSetDictionary_page_header() {
      return this.dictionary_page_header != null;
   }

   public void setDictionary_page_headerIsSet(boolean value) {
      if (!value) {
         this.dictionary_page_header = null;
      }

   }

   @Nullable
   public DataPageHeaderV2 getData_page_header_v2() {
      return this.data_page_header_v2;
   }

   public PageHeader setData_page_header_v2(@Nullable DataPageHeaderV2 data_page_header_v2) {
      this.data_page_header_v2 = data_page_header_v2;
      return this;
   }

   public void unsetData_page_header_v2() {
      this.data_page_header_v2 = null;
   }

   public boolean isSetData_page_header_v2() {
      return this.data_page_header_v2 != null;
   }

   public void setData_page_header_v2IsSet(boolean value) {
      if (!value) {
         this.data_page_header_v2 = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((PageType)value);
            }
            break;
         case UNCOMPRESSED_PAGE_SIZE:
            if (value == null) {
               this.unsetUncompressed_page_size();
            } else {
               this.setUncompressed_page_size((Integer)value);
            }
            break;
         case COMPRESSED_PAGE_SIZE:
            if (value == null) {
               this.unsetCompressed_page_size();
            } else {
               this.setCompressed_page_size((Integer)value);
            }
            break;
         case CRC:
            if (value == null) {
               this.unsetCrc();
            } else {
               this.setCrc((Integer)value);
            }
            break;
         case DATA_PAGE_HEADER:
            if (value == null) {
               this.unsetData_page_header();
            } else {
               this.setData_page_header((DataPageHeader)value);
            }
            break;
         case INDEX_PAGE_HEADER:
            if (value == null) {
               this.unsetIndex_page_header();
            } else {
               this.setIndex_page_header((IndexPageHeader)value);
            }
            break;
         case DICTIONARY_PAGE_HEADER:
            if (value == null) {
               this.unsetDictionary_page_header();
            } else {
               this.setDictionary_page_header((DictionaryPageHeader)value);
            }
            break;
         case DATA_PAGE_HEADER_V2:
            if (value == null) {
               this.unsetData_page_header_v2();
            } else {
               this.setData_page_header_v2((DataPageHeaderV2)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TYPE:
            return this.getType();
         case UNCOMPRESSED_PAGE_SIZE:
            return this.getUncompressed_page_size();
         case COMPRESSED_PAGE_SIZE:
            return this.getCompressed_page_size();
         case CRC:
            return this.getCrc();
         case DATA_PAGE_HEADER:
            return this.getData_page_header();
         case INDEX_PAGE_HEADER:
            return this.getIndex_page_header();
         case DICTIONARY_PAGE_HEADER:
            return this.getDictionary_page_header();
         case DATA_PAGE_HEADER_V2:
            return this.getData_page_header_v2();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TYPE:
               return this.isSetType();
            case UNCOMPRESSED_PAGE_SIZE:
               return this.isSetUncompressed_page_size();
            case COMPRESSED_PAGE_SIZE:
               return this.isSetCompressed_page_size();
            case CRC:
               return this.isSetCrc();
            case DATA_PAGE_HEADER:
               return this.isSetData_page_header();
            case INDEX_PAGE_HEADER:
               return this.isSetIndex_page_header();
            case DICTIONARY_PAGE_HEADER:
               return this.isSetDictionary_page_header();
            case DATA_PAGE_HEADER_V2:
               return this.isSetData_page_header_v2();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PageHeader ? this.equals((PageHeader)that) : false;
   }

   public boolean equals(PageHeader that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         boolean this_present_uncompressed_page_size = true;
         boolean that_present_uncompressed_page_size = true;
         if (this_present_uncompressed_page_size || that_present_uncompressed_page_size) {
            if (!this_present_uncompressed_page_size || !that_present_uncompressed_page_size) {
               return false;
            }

            if (this.uncompressed_page_size != that.uncompressed_page_size) {
               return false;
            }
         }

         boolean this_present_compressed_page_size = true;
         boolean that_present_compressed_page_size = true;
         if (this_present_compressed_page_size || that_present_compressed_page_size) {
            if (!this_present_compressed_page_size || !that_present_compressed_page_size) {
               return false;
            }

            if (this.compressed_page_size != that.compressed_page_size) {
               return false;
            }
         }

         boolean this_present_crc = this.isSetCrc();
         boolean that_present_crc = that.isSetCrc();
         if (this_present_crc || that_present_crc) {
            if (!this_present_crc || !that_present_crc) {
               return false;
            }

            if (this.crc != that.crc) {
               return false;
            }
         }

         boolean this_present_data_page_header = this.isSetData_page_header();
         boolean that_present_data_page_header = that.isSetData_page_header();
         if (this_present_data_page_header || that_present_data_page_header) {
            if (!this_present_data_page_header || !that_present_data_page_header) {
               return false;
            }

            if (!this.data_page_header.equals(that.data_page_header)) {
               return false;
            }
         }

         boolean this_present_index_page_header = this.isSetIndex_page_header();
         boolean that_present_index_page_header = that.isSetIndex_page_header();
         if (this_present_index_page_header || that_present_index_page_header) {
            if (!this_present_index_page_header || !that_present_index_page_header) {
               return false;
            }

            if (!this.index_page_header.equals(that.index_page_header)) {
               return false;
            }
         }

         boolean this_present_dictionary_page_header = this.isSetDictionary_page_header();
         boolean that_present_dictionary_page_header = that.isSetDictionary_page_header();
         if (this_present_dictionary_page_header || that_present_dictionary_page_header) {
            if (!this_present_dictionary_page_header || !that_present_dictionary_page_header) {
               return false;
            }

            if (!this.dictionary_page_header.equals(that.dictionary_page_header)) {
               return false;
            }
         }

         boolean this_present_data_page_header_v2 = this.isSetData_page_header_v2();
         boolean that_present_data_page_header_v2 = that.isSetData_page_header_v2();
         if (this_present_data_page_header_v2 || that_present_data_page_header_v2) {
            if (!this_present_data_page_header_v2 || !that_present_data_page_header_v2) {
               return false;
            }

            if (!this.data_page_header_v2.equals(that.data_page_header_v2)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + this.uncompressed_page_size;
      hashCode = hashCode * 8191 + this.compressed_page_size;
      hashCode = hashCode * 8191 + (this.isSetCrc() ? 131071 : 524287);
      if (this.isSetCrc()) {
         hashCode = hashCode * 8191 + this.crc;
      }

      hashCode = hashCode * 8191 + (this.isSetData_page_header() ? 131071 : 524287);
      if (this.isSetData_page_header()) {
         hashCode = hashCode * 8191 + this.data_page_header.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetIndex_page_header() ? 131071 : 524287);
      if (this.isSetIndex_page_header()) {
         hashCode = hashCode * 8191 + this.index_page_header.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDictionary_page_header() ? 131071 : 524287);
      if (this.isSetDictionary_page_header()) {
         hashCode = hashCode * 8191 + this.dictionary_page_header.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetData_page_header_v2() ? 131071 : 524287);
      if (this.isSetData_page_header_v2()) {
         hashCode = hashCode * 8191 + this.data_page_header_v2.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PageHeader other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetType()) {
               lastComparison = TBaseHelper.compareTo((Comparable)this.type, (Comparable)other.type);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetUncompressed_page_size(), other.isSetUncompressed_page_size());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetUncompressed_page_size()) {
                  lastComparison = TBaseHelper.compareTo(this.uncompressed_page_size, other.uncompressed_page_size);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetCompressed_page_size(), other.isSetCompressed_page_size());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetCompressed_page_size()) {
                     lastComparison = TBaseHelper.compareTo(this.compressed_page_size, other.compressed_page_size);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetCrc(), other.isSetCrc());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetCrc()) {
                        lastComparison = TBaseHelper.compareTo(this.crc, other.crc);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetData_page_header(), other.isSetData_page_header());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetData_page_header()) {
                           lastComparison = TBaseHelper.compareTo((Comparable)this.data_page_header, (Comparable)other.data_page_header);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetIndex_page_header(), other.isSetIndex_page_header());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetIndex_page_header()) {
                              lastComparison = TBaseHelper.compareTo((Comparable)this.index_page_header, (Comparable)other.index_page_header);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetDictionary_page_header(), other.isSetDictionary_page_header());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetDictionary_page_header()) {
                                 lastComparison = TBaseHelper.compareTo((Comparable)this.dictionary_page_header, (Comparable)other.dictionary_page_header);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetData_page_header_v2(), other.isSetData_page_header_v2());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetData_page_header_v2()) {
                                    lastComparison = TBaseHelper.compareTo((Comparable)this.data_page_header_v2, (Comparable)other.data_page_header_v2);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return PageHeader._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PageHeader(");
      boolean first = true;
      sb.append("type:");
      if (this.type == null) {
         sb.append("null");
      } else {
         sb.append(this.type);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("uncompressed_page_size:");
      sb.append(this.uncompressed_page_size);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("compressed_page_size:");
      sb.append(this.compressed_page_size);
      first = false;
      if (this.isSetCrc()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("crc:");
         sb.append(this.crc);
         first = false;
      }

      if (this.isSetData_page_header()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("data_page_header:");
         if (this.data_page_header == null) {
            sb.append("null");
         } else {
            sb.append(this.data_page_header);
         }

         first = false;
      }

      if (this.isSetIndex_page_header()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("index_page_header:");
         if (this.index_page_header == null) {
            sb.append("null");
         } else {
            sb.append(this.index_page_header);
         }

         first = false;
      }

      if (this.isSetDictionary_page_header()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("dictionary_page_header:");
         if (this.dictionary_page_header == null) {
            sb.append("null");
         } else {
            sb.append(this.dictionary_page_header);
         }

         first = false;
      }

      if (this.isSetData_page_header_v2()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("data_page_header_v2:");
         if (this.data_page_header_v2 == null) {
            sb.append("null");
         } else {
            sb.append(this.data_page_header_v2);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.type == null) {
         throw new TProtocolException("Required field 'type' was not present! Struct: " + this.toString());
      } else {
         if (this.data_page_header != null) {
            this.data_page_header.validate();
         }

         if (this.index_page_header != null) {
            this.index_page_header.validate();
         }

         if (this.dictionary_page_header != null) {
            this.dictionary_page_header.validate();
         }

         if (this.data_page_header_v2 != null) {
            this.data_page_header_v2.validate();
         }

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
      optionals = new _Fields[]{PageHeader._Fields.CRC, PageHeader._Fields.DATA_PAGE_HEADER, PageHeader._Fields.INDEX_PAGE_HEADER, PageHeader._Fields.DICTIONARY_PAGE_HEADER, PageHeader._Fields.DATA_PAGE_HEADER_V2};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PageHeader._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)-1, PageType.class)));
      tmpMap.put(PageHeader._Fields.UNCOMPRESSED_PAGE_SIZE, new FieldMetaData("uncompressed_page_size", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(PageHeader._Fields.COMPRESSED_PAGE_SIZE, new FieldMetaData("compressed_page_size", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(PageHeader._Fields.CRC, new FieldMetaData("crc", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(PageHeader._Fields.DATA_PAGE_HEADER, new FieldMetaData("data_page_header", (byte)2, new StructMetaData((byte)12, DataPageHeader.class)));
      tmpMap.put(PageHeader._Fields.INDEX_PAGE_HEADER, new FieldMetaData("index_page_header", (byte)2, new StructMetaData((byte)12, IndexPageHeader.class)));
      tmpMap.put(PageHeader._Fields.DICTIONARY_PAGE_HEADER, new FieldMetaData("dictionary_page_header", (byte)2, new StructMetaData((byte)12, DictionaryPageHeader.class)));
      tmpMap.put(PageHeader._Fields.DATA_PAGE_HEADER_V2, new FieldMetaData("data_page_header_v2", (byte)2, new StructMetaData((byte)12, DataPageHeaderV2.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PageHeader.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPE((short)1, "type"),
      UNCOMPRESSED_PAGE_SIZE((short)2, "uncompressed_page_size"),
      COMPRESSED_PAGE_SIZE((short)3, "compressed_page_size"),
      CRC((short)4, "crc"),
      DATA_PAGE_HEADER((short)5, "data_page_header"),
      INDEX_PAGE_HEADER((short)6, "index_page_header"),
      DICTIONARY_PAGE_HEADER((short)7, "dictionary_page_header"),
      DATA_PAGE_HEADER_V2((short)8, "data_page_header_v2");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPE;
            case 2:
               return UNCOMPRESSED_PAGE_SIZE;
            case 3:
               return COMPRESSED_PAGE_SIZE;
            case 4:
               return CRC;
            case 5:
               return DATA_PAGE_HEADER;
            case 6:
               return INDEX_PAGE_HEADER;
            case 7:
               return DICTIONARY_PAGE_HEADER;
            case 8:
               return DATA_PAGE_HEADER_V2;
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

   private static class PageHeaderStandardSchemeFactory implements SchemeFactory {
      private PageHeaderStandardSchemeFactory() {
      }

      public PageHeaderStandardScheme getScheme() {
         return new PageHeaderStandardScheme();
      }
   }

   private static class PageHeaderStandardScheme extends StandardScheme {
      private PageHeaderStandardScheme() {
      }

      public void read(TProtocol iprot, PageHeader struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetUncompressed_page_size()) {
                  throw new TProtocolException("Required field 'uncompressed_page_size' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetCompressed_page_size()) {
                  throw new TProtocolException("Required field 'compressed_page_size' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.type = PageType.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.uncompressed_page_size = iprot.readI32();
                     struct.setUncompressed_page_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.compressed_page_size = iprot.readI32();
                     struct.setCompressed_page_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.crc = iprot.readI32();
                     struct.setCrcIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 12) {
                     struct.data_page_header = new DataPageHeader();
                     struct.data_page_header.read(iprot);
                     struct.setData_page_headerIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 12) {
                     struct.index_page_header = new IndexPageHeader();
                     struct.index_page_header.read(iprot);
                     struct.setIndex_page_headerIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 12) {
                     struct.dictionary_page_header = new DictionaryPageHeader();
                     struct.dictionary_page_header.read(iprot);
                     struct.setDictionary_page_headerIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.data_page_header_v2 = new DataPageHeaderV2();
                     struct.data_page_header_v2.read(iprot);
                     struct.setData_page_header_v2IsSet(true);
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

      public void write(TProtocol oprot, PageHeader struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PageHeader.STRUCT_DESC);
         if (struct.type != null) {
            oprot.writeFieldBegin(PageHeader.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(PageHeader.UNCOMPRESSED_PAGE_SIZE_FIELD_DESC);
         oprot.writeI32(struct.uncompressed_page_size);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(PageHeader.COMPRESSED_PAGE_SIZE_FIELD_DESC);
         oprot.writeI32(struct.compressed_page_size);
         oprot.writeFieldEnd();
         if (struct.isSetCrc()) {
            oprot.writeFieldBegin(PageHeader.CRC_FIELD_DESC);
            oprot.writeI32(struct.crc);
            oprot.writeFieldEnd();
         }

         if (struct.data_page_header != null && struct.isSetData_page_header()) {
            oprot.writeFieldBegin(PageHeader.DATA_PAGE_HEADER_FIELD_DESC);
            struct.data_page_header.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.index_page_header != null && struct.isSetIndex_page_header()) {
            oprot.writeFieldBegin(PageHeader.INDEX_PAGE_HEADER_FIELD_DESC);
            struct.index_page_header.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.dictionary_page_header != null && struct.isSetDictionary_page_header()) {
            oprot.writeFieldBegin(PageHeader.DICTIONARY_PAGE_HEADER_FIELD_DESC);
            struct.dictionary_page_header.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.data_page_header_v2 != null && struct.isSetData_page_header_v2()) {
            oprot.writeFieldBegin(PageHeader.DATA_PAGE_HEADER_V2_FIELD_DESC);
            struct.data_page_header_v2.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PageHeaderTupleSchemeFactory implements SchemeFactory {
      private PageHeaderTupleSchemeFactory() {
      }

      public PageHeaderTupleScheme getScheme() {
         return new PageHeaderTupleScheme();
      }
   }

   private static class PageHeaderTupleScheme extends TupleScheme {
      private PageHeaderTupleScheme() {
      }

      public void write(TProtocol prot, PageHeader struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.type.getValue());
         oprot.writeI32(struct.uncompressed_page_size);
         oprot.writeI32(struct.compressed_page_size);
         BitSet optionals = new BitSet();
         if (struct.isSetCrc()) {
            optionals.set(0);
         }

         if (struct.isSetData_page_header()) {
            optionals.set(1);
         }

         if (struct.isSetIndex_page_header()) {
            optionals.set(2);
         }

         if (struct.isSetDictionary_page_header()) {
            optionals.set(3);
         }

         if (struct.isSetData_page_header_v2()) {
            optionals.set(4);
         }

         oprot.writeBitSet(optionals, 5);
         if (struct.isSetCrc()) {
            oprot.writeI32(struct.crc);
         }

         if (struct.isSetData_page_header()) {
            struct.data_page_header.write(oprot);
         }

         if (struct.isSetIndex_page_header()) {
            struct.index_page_header.write(oprot);
         }

         if (struct.isSetDictionary_page_header()) {
            struct.dictionary_page_header.write(oprot);
         }

         if (struct.isSetData_page_header_v2()) {
            struct.data_page_header_v2.write(oprot);
         }

      }

      public void read(TProtocol prot, PageHeader struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.type = PageType.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         struct.uncompressed_page_size = iprot.readI32();
         struct.setUncompressed_page_sizeIsSet(true);
         struct.compressed_page_size = iprot.readI32();
         struct.setCompressed_page_sizeIsSet(true);
         BitSet incoming = iprot.readBitSet(5);
         if (incoming.get(0)) {
            struct.crc = iprot.readI32();
            struct.setCrcIsSet(true);
         }

         if (incoming.get(1)) {
            struct.data_page_header = new DataPageHeader();
            struct.data_page_header.read(iprot);
            struct.setData_page_headerIsSet(true);
         }

         if (incoming.get(2)) {
            struct.index_page_header = new IndexPageHeader();
            struct.index_page_header.read(iprot);
            struct.setIndex_page_headerIsSet(true);
         }

         if (incoming.get(3)) {
            struct.dictionary_page_header = new DictionaryPageHeader();
            struct.dictionary_page_header.read(iprot);
            struct.setDictionary_page_headerIsSet(true);
         }

         if (incoming.get(4)) {
            struct.data_page_header_v2 = new DataPageHeaderV2();
            struct.data_page_header_v2.read(iprot);
            struct.setData_page_header_v2IsSet(true);
         }

      }
   }
}
