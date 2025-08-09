package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import shaded.parquet.org.apache.thrift.meta_data.EnumMetaData;
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

public class ColumnMetaData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnMetaData");
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)1);
   private static final TField ENCODINGS_FIELD_DESC = new TField("encodings", (byte)15, (short)2);
   private static final TField PATH_IN_SCHEMA_FIELD_DESC = new TField("path_in_schema", (byte)15, (short)3);
   private static final TField CODEC_FIELD_DESC = new TField("codec", (byte)8, (short)4);
   private static final TField NUM_VALUES_FIELD_DESC = new TField("num_values", (byte)10, (short)5);
   private static final TField TOTAL_UNCOMPRESSED_SIZE_FIELD_DESC = new TField("total_uncompressed_size", (byte)10, (short)6);
   private static final TField TOTAL_COMPRESSED_SIZE_FIELD_DESC = new TField("total_compressed_size", (byte)10, (short)7);
   private static final TField KEY_VALUE_METADATA_FIELD_DESC = new TField("key_value_metadata", (byte)15, (short)8);
   private static final TField DATA_PAGE_OFFSET_FIELD_DESC = new TField("data_page_offset", (byte)10, (short)9);
   private static final TField INDEX_PAGE_OFFSET_FIELD_DESC = new TField("index_page_offset", (byte)10, (short)10);
   private static final TField DICTIONARY_PAGE_OFFSET_FIELD_DESC = new TField("dictionary_page_offset", (byte)10, (short)11);
   private static final TField STATISTICS_FIELD_DESC = new TField("statistics", (byte)12, (short)12);
   private static final TField ENCODING_STATS_FIELD_DESC = new TField("encoding_stats", (byte)15, (short)13);
   private static final TField BLOOM_FILTER_OFFSET_FIELD_DESC = new TField("bloom_filter_offset", (byte)10, (short)14);
   private static final TField BLOOM_FILTER_LENGTH_FIELD_DESC = new TField("bloom_filter_length", (byte)8, (short)15);
   private static final TField SIZE_STATISTICS_FIELD_DESC = new TField("size_statistics", (byte)12, (short)16);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnMetaDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnMetaDataTupleSchemeFactory();
   @Nullable
   public Type type;
   @Nullable
   public List encodings;
   @Nullable
   public List path_in_schema;
   @Nullable
   public CompressionCodec codec;
   public long num_values;
   public long total_uncompressed_size;
   public long total_compressed_size;
   @Nullable
   public List key_value_metadata;
   public long data_page_offset;
   public long index_page_offset;
   public long dictionary_page_offset;
   @Nullable
   public Statistics statistics;
   @Nullable
   public List encoding_stats;
   public long bloom_filter_offset;
   public int bloom_filter_length;
   @Nullable
   public SizeStatistics size_statistics;
   private static final int __NUM_VALUES_ISSET_ID = 0;
   private static final int __TOTAL_UNCOMPRESSED_SIZE_ISSET_ID = 1;
   private static final int __TOTAL_COMPRESSED_SIZE_ISSET_ID = 2;
   private static final int __DATA_PAGE_OFFSET_ISSET_ID = 3;
   private static final int __INDEX_PAGE_OFFSET_ISSET_ID = 4;
   private static final int __DICTIONARY_PAGE_OFFSET_ISSET_ID = 5;
   private static final int __BLOOM_FILTER_OFFSET_ISSET_ID = 6;
   private static final int __BLOOM_FILTER_LENGTH_ISSET_ID = 7;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ColumnMetaData() {
      this.__isset_bitfield = 0;
   }

   public ColumnMetaData(Type type, List encodings, List path_in_schema, CompressionCodec codec, long num_values, long total_uncompressed_size, long total_compressed_size, long data_page_offset) {
      this();
      this.type = type;
      this.encodings = encodings;
      this.path_in_schema = path_in_schema;
      this.codec = codec;
      this.num_values = num_values;
      this.setNum_valuesIsSet(true);
      this.total_uncompressed_size = total_uncompressed_size;
      this.setTotal_uncompressed_sizeIsSet(true);
      this.total_compressed_size = total_compressed_size;
      this.setTotal_compressed_sizeIsSet(true);
      this.data_page_offset = data_page_offset;
      this.setData_page_offsetIsSet(true);
   }

   public ColumnMetaData(ColumnMetaData other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetType()) {
         this.type = other.type;
      }

      if (other.isSetEncodings()) {
         List<Encoding> __this__encodings = new ArrayList(other.encodings.size());

         for(Encoding other_element : other.encodings) {
            __this__encodings.add(other_element);
         }

         this.encodings = __this__encodings;
      }

      if (other.isSetPath_in_schema()) {
         List<String> __this__path_in_schema = new ArrayList(other.path_in_schema);
         this.path_in_schema = __this__path_in_schema;
      }

      if (other.isSetCodec()) {
         this.codec = other.codec;
      }

      this.num_values = other.num_values;
      this.total_uncompressed_size = other.total_uncompressed_size;
      this.total_compressed_size = other.total_compressed_size;
      if (other.isSetKey_value_metadata()) {
         List<KeyValue> __this__key_value_metadata = new ArrayList(other.key_value_metadata.size());

         for(KeyValue other_element : other.key_value_metadata) {
            __this__key_value_metadata.add(new KeyValue(other_element));
         }

         this.key_value_metadata = __this__key_value_metadata;
      }

      this.data_page_offset = other.data_page_offset;
      this.index_page_offset = other.index_page_offset;
      this.dictionary_page_offset = other.dictionary_page_offset;
      if (other.isSetStatistics()) {
         this.statistics = new Statistics(other.statistics);
      }

      if (other.isSetEncoding_stats()) {
         List<PageEncodingStats> __this__encoding_stats = new ArrayList(other.encoding_stats.size());

         for(PageEncodingStats other_element : other.encoding_stats) {
            __this__encoding_stats.add(new PageEncodingStats(other_element));
         }

         this.encoding_stats = __this__encoding_stats;
      }

      this.bloom_filter_offset = other.bloom_filter_offset;
      this.bloom_filter_length = other.bloom_filter_length;
      if (other.isSetSize_statistics()) {
         this.size_statistics = new SizeStatistics(other.size_statistics);
      }

   }

   public ColumnMetaData deepCopy() {
      return new ColumnMetaData(this);
   }

   public void clear() {
      this.type = null;
      this.encodings = null;
      this.path_in_schema = null;
      this.codec = null;
      this.setNum_valuesIsSet(false);
      this.num_values = 0L;
      this.setTotal_uncompressed_sizeIsSet(false);
      this.total_uncompressed_size = 0L;
      this.setTotal_compressed_sizeIsSet(false);
      this.total_compressed_size = 0L;
      this.key_value_metadata = null;
      this.setData_page_offsetIsSet(false);
      this.data_page_offset = 0L;
      this.setIndex_page_offsetIsSet(false);
      this.index_page_offset = 0L;
      this.setDictionary_page_offsetIsSet(false);
      this.dictionary_page_offset = 0L;
      this.statistics = null;
      this.encoding_stats = null;
      this.setBloom_filter_offsetIsSet(false);
      this.bloom_filter_offset = 0L;
      this.setBloom_filter_lengthIsSet(false);
      this.bloom_filter_length = 0;
      this.size_statistics = null;
   }

   @Nullable
   public Type getType() {
      return this.type;
   }

   public ColumnMetaData setType(@Nullable Type type) {
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

   public int getEncodingsSize() {
      return this.encodings == null ? 0 : this.encodings.size();
   }

   @Nullable
   public Iterator getEncodingsIterator() {
      return this.encodings == null ? null : this.encodings.iterator();
   }

   public void addToEncodings(Encoding elem) {
      if (this.encodings == null) {
         this.encodings = new ArrayList();
      }

      this.encodings.add(elem);
   }

   @Nullable
   public List getEncodings() {
      return this.encodings;
   }

   public ColumnMetaData setEncodings(@Nullable List encodings) {
      this.encodings = encodings;
      return this;
   }

   public void unsetEncodings() {
      this.encodings = null;
   }

   public boolean isSetEncodings() {
      return this.encodings != null;
   }

   public void setEncodingsIsSet(boolean value) {
      if (!value) {
         this.encodings = null;
      }

   }

   public int getPath_in_schemaSize() {
      return this.path_in_schema == null ? 0 : this.path_in_schema.size();
   }

   @Nullable
   public Iterator getPath_in_schemaIterator() {
      return this.path_in_schema == null ? null : this.path_in_schema.iterator();
   }

   public void addToPath_in_schema(String elem) {
      if (this.path_in_schema == null) {
         this.path_in_schema = new ArrayList();
      }

      this.path_in_schema.add(elem);
   }

   @Nullable
   public List getPath_in_schema() {
      return this.path_in_schema;
   }

   public ColumnMetaData setPath_in_schema(@Nullable List path_in_schema) {
      this.path_in_schema = path_in_schema;
      return this;
   }

   public void unsetPath_in_schema() {
      this.path_in_schema = null;
   }

   public boolean isSetPath_in_schema() {
      return this.path_in_schema != null;
   }

   public void setPath_in_schemaIsSet(boolean value) {
      if (!value) {
         this.path_in_schema = null;
      }

   }

   @Nullable
   public CompressionCodec getCodec() {
      return this.codec;
   }

   public ColumnMetaData setCodec(@Nullable CompressionCodec codec) {
      this.codec = codec;
      return this;
   }

   public void unsetCodec() {
      this.codec = null;
   }

   public boolean isSetCodec() {
      return this.codec != null;
   }

   public void setCodecIsSet(boolean value) {
      if (!value) {
         this.codec = null;
      }

   }

   public long getNum_values() {
      return this.num_values;
   }

   public ColumnMetaData setNum_values(long num_values) {
      this.num_values = num_values;
      this.setNum_valuesIsSet(true);
      return this;
   }

   public void unsetNum_values() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetNum_values() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setNum_valuesIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public long getTotal_uncompressed_size() {
      return this.total_uncompressed_size;
   }

   public ColumnMetaData setTotal_uncompressed_size(long total_uncompressed_size) {
      this.total_uncompressed_size = total_uncompressed_size;
      this.setTotal_uncompressed_sizeIsSet(true);
      return this;
   }

   public void unsetTotal_uncompressed_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetTotal_uncompressed_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setTotal_uncompressed_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public long getTotal_compressed_size() {
      return this.total_compressed_size;
   }

   public ColumnMetaData setTotal_compressed_size(long total_compressed_size) {
      this.total_compressed_size = total_compressed_size;
      this.setTotal_compressed_sizeIsSet(true);
      return this;
   }

   public void unsetTotal_compressed_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetTotal_compressed_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setTotal_compressed_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
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

   public ColumnMetaData setKey_value_metadata(@Nullable List key_value_metadata) {
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

   public long getData_page_offset() {
      return this.data_page_offset;
   }

   public ColumnMetaData setData_page_offset(long data_page_offset) {
      this.data_page_offset = data_page_offset;
      this.setData_page_offsetIsSet(true);
      return this;
   }

   public void unsetData_page_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 3);
   }

   public boolean isSetData_page_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 3);
   }

   public void setData_page_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 3, value);
   }

   public long getIndex_page_offset() {
      return this.index_page_offset;
   }

   public ColumnMetaData setIndex_page_offset(long index_page_offset) {
      this.index_page_offset = index_page_offset;
      this.setIndex_page_offsetIsSet(true);
      return this;
   }

   public void unsetIndex_page_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 4);
   }

   public boolean isSetIndex_page_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 4);
   }

   public void setIndex_page_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 4, value);
   }

   public long getDictionary_page_offset() {
      return this.dictionary_page_offset;
   }

   public ColumnMetaData setDictionary_page_offset(long dictionary_page_offset) {
      this.dictionary_page_offset = dictionary_page_offset;
      this.setDictionary_page_offsetIsSet(true);
      return this;
   }

   public void unsetDictionary_page_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 5);
   }

   public boolean isSetDictionary_page_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 5);
   }

   public void setDictionary_page_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 5, value);
   }

   @Nullable
   public Statistics getStatistics() {
      return this.statistics;
   }

   public ColumnMetaData setStatistics(@Nullable Statistics statistics) {
      this.statistics = statistics;
      return this;
   }

   public void unsetStatistics() {
      this.statistics = null;
   }

   public boolean isSetStatistics() {
      return this.statistics != null;
   }

   public void setStatisticsIsSet(boolean value) {
      if (!value) {
         this.statistics = null;
      }

   }

   public int getEncoding_statsSize() {
      return this.encoding_stats == null ? 0 : this.encoding_stats.size();
   }

   @Nullable
   public Iterator getEncoding_statsIterator() {
      return this.encoding_stats == null ? null : this.encoding_stats.iterator();
   }

   public void addToEncoding_stats(PageEncodingStats elem) {
      if (this.encoding_stats == null) {
         this.encoding_stats = new ArrayList();
      }

      this.encoding_stats.add(elem);
   }

   @Nullable
   public List getEncoding_stats() {
      return this.encoding_stats;
   }

   public ColumnMetaData setEncoding_stats(@Nullable List encoding_stats) {
      this.encoding_stats = encoding_stats;
      return this;
   }

   public void unsetEncoding_stats() {
      this.encoding_stats = null;
   }

   public boolean isSetEncoding_stats() {
      return this.encoding_stats != null;
   }

   public void setEncoding_statsIsSet(boolean value) {
      if (!value) {
         this.encoding_stats = null;
      }

   }

   public long getBloom_filter_offset() {
      return this.bloom_filter_offset;
   }

   public ColumnMetaData setBloom_filter_offset(long bloom_filter_offset) {
      this.bloom_filter_offset = bloom_filter_offset;
      this.setBloom_filter_offsetIsSet(true);
      return this;
   }

   public void unsetBloom_filter_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 6);
   }

   public boolean isSetBloom_filter_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 6);
   }

   public void setBloom_filter_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 6, value);
   }

   public int getBloom_filter_length() {
      return this.bloom_filter_length;
   }

   public ColumnMetaData setBloom_filter_length(int bloom_filter_length) {
      this.bloom_filter_length = bloom_filter_length;
      this.setBloom_filter_lengthIsSet(true);
      return this;
   }

   public void unsetBloom_filter_length() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 7);
   }

   public boolean isSetBloom_filter_length() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 7);
   }

   public void setBloom_filter_lengthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 7, value);
   }

   @Nullable
   public SizeStatistics getSize_statistics() {
      return this.size_statistics;
   }

   public ColumnMetaData setSize_statistics(@Nullable SizeStatistics size_statistics) {
      this.size_statistics = size_statistics;
      return this;
   }

   public void unsetSize_statistics() {
      this.size_statistics = null;
   }

   public boolean isSetSize_statistics() {
      return this.size_statistics != null;
   }

   public void setSize_statisticsIsSet(boolean value) {
      if (!value) {
         this.size_statistics = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((Type)value);
            }
            break;
         case ENCODINGS:
            if (value == null) {
               this.unsetEncodings();
            } else {
               this.setEncodings((List)value);
            }
            break;
         case PATH_IN_SCHEMA:
            if (value == null) {
               this.unsetPath_in_schema();
            } else {
               this.setPath_in_schema((List)value);
            }
            break;
         case CODEC:
            if (value == null) {
               this.unsetCodec();
            } else {
               this.setCodec((CompressionCodec)value);
            }
            break;
         case NUM_VALUES:
            if (value == null) {
               this.unsetNum_values();
            } else {
               this.setNum_values((Long)value);
            }
            break;
         case TOTAL_UNCOMPRESSED_SIZE:
            if (value == null) {
               this.unsetTotal_uncompressed_size();
            } else {
               this.setTotal_uncompressed_size((Long)value);
            }
            break;
         case TOTAL_COMPRESSED_SIZE:
            if (value == null) {
               this.unsetTotal_compressed_size();
            } else {
               this.setTotal_compressed_size((Long)value);
            }
            break;
         case KEY_VALUE_METADATA:
            if (value == null) {
               this.unsetKey_value_metadata();
            } else {
               this.setKey_value_metadata((List)value);
            }
            break;
         case DATA_PAGE_OFFSET:
            if (value == null) {
               this.unsetData_page_offset();
            } else {
               this.setData_page_offset((Long)value);
            }
            break;
         case INDEX_PAGE_OFFSET:
            if (value == null) {
               this.unsetIndex_page_offset();
            } else {
               this.setIndex_page_offset((Long)value);
            }
            break;
         case DICTIONARY_PAGE_OFFSET:
            if (value == null) {
               this.unsetDictionary_page_offset();
            } else {
               this.setDictionary_page_offset((Long)value);
            }
            break;
         case STATISTICS:
            if (value == null) {
               this.unsetStatistics();
            } else {
               this.setStatistics((Statistics)value);
            }
            break;
         case ENCODING_STATS:
            if (value == null) {
               this.unsetEncoding_stats();
            } else {
               this.setEncoding_stats((List)value);
            }
            break;
         case BLOOM_FILTER_OFFSET:
            if (value == null) {
               this.unsetBloom_filter_offset();
            } else {
               this.setBloom_filter_offset((Long)value);
            }
            break;
         case BLOOM_FILTER_LENGTH:
            if (value == null) {
               this.unsetBloom_filter_length();
            } else {
               this.setBloom_filter_length((Integer)value);
            }
            break;
         case SIZE_STATISTICS:
            if (value == null) {
               this.unsetSize_statistics();
            } else {
               this.setSize_statistics((SizeStatistics)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TYPE:
            return this.getType();
         case ENCODINGS:
            return this.getEncodings();
         case PATH_IN_SCHEMA:
            return this.getPath_in_schema();
         case CODEC:
            return this.getCodec();
         case NUM_VALUES:
            return this.getNum_values();
         case TOTAL_UNCOMPRESSED_SIZE:
            return this.getTotal_uncompressed_size();
         case TOTAL_COMPRESSED_SIZE:
            return this.getTotal_compressed_size();
         case KEY_VALUE_METADATA:
            return this.getKey_value_metadata();
         case DATA_PAGE_OFFSET:
            return this.getData_page_offset();
         case INDEX_PAGE_OFFSET:
            return this.getIndex_page_offset();
         case DICTIONARY_PAGE_OFFSET:
            return this.getDictionary_page_offset();
         case STATISTICS:
            return this.getStatistics();
         case ENCODING_STATS:
            return this.getEncoding_stats();
         case BLOOM_FILTER_OFFSET:
            return this.getBloom_filter_offset();
         case BLOOM_FILTER_LENGTH:
            return this.getBloom_filter_length();
         case SIZE_STATISTICS:
            return this.getSize_statistics();
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
            case ENCODINGS:
               return this.isSetEncodings();
            case PATH_IN_SCHEMA:
               return this.isSetPath_in_schema();
            case CODEC:
               return this.isSetCodec();
            case NUM_VALUES:
               return this.isSetNum_values();
            case TOTAL_UNCOMPRESSED_SIZE:
               return this.isSetTotal_uncompressed_size();
            case TOTAL_COMPRESSED_SIZE:
               return this.isSetTotal_compressed_size();
            case KEY_VALUE_METADATA:
               return this.isSetKey_value_metadata();
            case DATA_PAGE_OFFSET:
               return this.isSetData_page_offset();
            case INDEX_PAGE_OFFSET:
               return this.isSetIndex_page_offset();
            case DICTIONARY_PAGE_OFFSET:
               return this.isSetDictionary_page_offset();
            case STATISTICS:
               return this.isSetStatistics();
            case ENCODING_STATS:
               return this.isSetEncoding_stats();
            case BLOOM_FILTER_OFFSET:
               return this.isSetBloom_filter_offset();
            case BLOOM_FILTER_LENGTH:
               return this.isSetBloom_filter_length();
            case SIZE_STATISTICS:
               return this.isSetSize_statistics();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ColumnMetaData ? this.equals((ColumnMetaData)that) : false;
   }

   public boolean equals(ColumnMetaData that) {
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

         boolean this_present_encodings = this.isSetEncodings();
         boolean that_present_encodings = that.isSetEncodings();
         if (this_present_encodings || that_present_encodings) {
            if (!this_present_encodings || !that_present_encodings) {
               return false;
            }

            if (!this.encodings.equals(that.encodings)) {
               return false;
            }
         }

         boolean this_present_path_in_schema = this.isSetPath_in_schema();
         boolean that_present_path_in_schema = that.isSetPath_in_schema();
         if (this_present_path_in_schema || that_present_path_in_schema) {
            if (!this_present_path_in_schema || !that_present_path_in_schema) {
               return false;
            }

            if (!this.path_in_schema.equals(that.path_in_schema)) {
               return false;
            }
         }

         boolean this_present_codec = this.isSetCodec();
         boolean that_present_codec = that.isSetCodec();
         if (this_present_codec || that_present_codec) {
            if (!this_present_codec || !that_present_codec) {
               return false;
            }

            if (!this.codec.equals(that.codec)) {
               return false;
            }
         }

         boolean this_present_num_values = true;
         boolean that_present_num_values = true;
         if (this_present_num_values || that_present_num_values) {
            if (!this_present_num_values || !that_present_num_values) {
               return false;
            }

            if (this.num_values != that.num_values) {
               return false;
            }
         }

         boolean this_present_total_uncompressed_size = true;
         boolean that_present_total_uncompressed_size = true;
         if (this_present_total_uncompressed_size || that_present_total_uncompressed_size) {
            if (!this_present_total_uncompressed_size || !that_present_total_uncompressed_size) {
               return false;
            }

            if (this.total_uncompressed_size != that.total_uncompressed_size) {
               return false;
            }
         }

         boolean this_present_total_compressed_size = true;
         boolean that_present_total_compressed_size = true;
         if (this_present_total_compressed_size || that_present_total_compressed_size) {
            if (!this_present_total_compressed_size || !that_present_total_compressed_size) {
               return false;
            }

            if (this.total_compressed_size != that.total_compressed_size) {
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

         boolean this_present_data_page_offset = true;
         boolean that_present_data_page_offset = true;
         if (this_present_data_page_offset || that_present_data_page_offset) {
            if (!this_present_data_page_offset || !that_present_data_page_offset) {
               return false;
            }

            if (this.data_page_offset != that.data_page_offset) {
               return false;
            }
         }

         boolean this_present_index_page_offset = this.isSetIndex_page_offset();
         boolean that_present_index_page_offset = that.isSetIndex_page_offset();
         if (this_present_index_page_offset || that_present_index_page_offset) {
            if (!this_present_index_page_offset || !that_present_index_page_offset) {
               return false;
            }

            if (this.index_page_offset != that.index_page_offset) {
               return false;
            }
         }

         boolean this_present_dictionary_page_offset = this.isSetDictionary_page_offset();
         boolean that_present_dictionary_page_offset = that.isSetDictionary_page_offset();
         if (this_present_dictionary_page_offset || that_present_dictionary_page_offset) {
            if (!this_present_dictionary_page_offset || !that_present_dictionary_page_offset) {
               return false;
            }

            if (this.dictionary_page_offset != that.dictionary_page_offset) {
               return false;
            }
         }

         boolean this_present_statistics = this.isSetStatistics();
         boolean that_present_statistics = that.isSetStatistics();
         if (this_present_statistics || that_present_statistics) {
            if (!this_present_statistics || !that_present_statistics) {
               return false;
            }

            if (!this.statistics.equals(that.statistics)) {
               return false;
            }
         }

         boolean this_present_encoding_stats = this.isSetEncoding_stats();
         boolean that_present_encoding_stats = that.isSetEncoding_stats();
         if (this_present_encoding_stats || that_present_encoding_stats) {
            if (!this_present_encoding_stats || !that_present_encoding_stats) {
               return false;
            }

            if (!this.encoding_stats.equals(that.encoding_stats)) {
               return false;
            }
         }

         boolean this_present_bloom_filter_offset = this.isSetBloom_filter_offset();
         boolean that_present_bloom_filter_offset = that.isSetBloom_filter_offset();
         if (this_present_bloom_filter_offset || that_present_bloom_filter_offset) {
            if (!this_present_bloom_filter_offset || !that_present_bloom_filter_offset) {
               return false;
            }

            if (this.bloom_filter_offset != that.bloom_filter_offset) {
               return false;
            }
         }

         boolean this_present_bloom_filter_length = this.isSetBloom_filter_length();
         boolean that_present_bloom_filter_length = that.isSetBloom_filter_length();
         if (this_present_bloom_filter_length || that_present_bloom_filter_length) {
            if (!this_present_bloom_filter_length || !that_present_bloom_filter_length) {
               return false;
            }

            if (this.bloom_filter_length != that.bloom_filter_length) {
               return false;
            }
         }

         boolean this_present_size_statistics = this.isSetSize_statistics();
         boolean that_present_size_statistics = that.isSetSize_statistics();
         if (this_present_size_statistics || that_present_size_statistics) {
            if (!this_present_size_statistics || !that_present_size_statistics) {
               return false;
            }

            if (!this.size_statistics.equals(that.size_statistics)) {
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

      hashCode = hashCode * 8191 + (this.isSetEncodings() ? 131071 : 524287);
      if (this.isSetEncodings()) {
         hashCode = hashCode * 8191 + this.encodings.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPath_in_schema() ? 131071 : 524287);
      if (this.isSetPath_in_schema()) {
         hashCode = hashCode * 8191 + this.path_in_schema.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCodec() ? 131071 : 524287);
      if (this.isSetCodec()) {
         hashCode = hashCode * 8191 + this.codec.getValue();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.num_values);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.total_uncompressed_size);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.total_compressed_size);
      hashCode = hashCode * 8191 + (this.isSetKey_value_metadata() ? 131071 : 524287);
      if (this.isSetKey_value_metadata()) {
         hashCode = hashCode * 8191 + this.key_value_metadata.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.data_page_offset);
      hashCode = hashCode * 8191 + (this.isSetIndex_page_offset() ? 131071 : 524287);
      if (this.isSetIndex_page_offset()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.index_page_offset);
      }

      hashCode = hashCode * 8191 + (this.isSetDictionary_page_offset() ? 131071 : 524287);
      if (this.isSetDictionary_page_offset()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.dictionary_page_offset);
      }

      hashCode = hashCode * 8191 + (this.isSetStatistics() ? 131071 : 524287);
      if (this.isSetStatistics()) {
         hashCode = hashCode * 8191 + this.statistics.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetEncoding_stats() ? 131071 : 524287);
      if (this.isSetEncoding_stats()) {
         hashCode = hashCode * 8191 + this.encoding_stats.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetBloom_filter_offset() ? 131071 : 524287);
      if (this.isSetBloom_filter_offset()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.bloom_filter_offset);
      }

      hashCode = hashCode * 8191 + (this.isSetBloom_filter_length() ? 131071 : 524287);
      if (this.isSetBloom_filter_length()) {
         hashCode = hashCode * 8191 + this.bloom_filter_length;
      }

      hashCode = hashCode * 8191 + (this.isSetSize_statistics() ? 131071 : 524287);
      if (this.isSetSize_statistics()) {
         hashCode = hashCode * 8191 + this.size_statistics.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ColumnMetaData other) {
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

            lastComparison = Boolean.compare(this.isSetEncodings(), other.isSetEncodings());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetEncodings()) {
                  lastComparison = TBaseHelper.compareTo(this.encodings, other.encodings);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPath_in_schema(), other.isSetPath_in_schema());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPath_in_schema()) {
                     lastComparison = TBaseHelper.compareTo(this.path_in_schema, other.path_in_schema);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetCodec(), other.isSetCodec());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetCodec()) {
                        lastComparison = TBaseHelper.compareTo((Comparable)this.codec, (Comparable)other.codec);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetNum_values(), other.isSetNum_values());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetNum_values()) {
                           lastComparison = TBaseHelper.compareTo(this.num_values, other.num_values);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetTotal_uncompressed_size(), other.isSetTotal_uncompressed_size());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetTotal_uncompressed_size()) {
                              lastComparison = TBaseHelper.compareTo(this.total_uncompressed_size, other.total_uncompressed_size);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetTotal_compressed_size(), other.isSetTotal_compressed_size());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetTotal_compressed_size()) {
                                 lastComparison = TBaseHelper.compareTo(this.total_compressed_size, other.total_compressed_size);
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

                                 lastComparison = Boolean.compare(this.isSetData_page_offset(), other.isSetData_page_offset());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetData_page_offset()) {
                                       lastComparison = TBaseHelper.compareTo(this.data_page_offset, other.data_page_offset);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetIndex_page_offset(), other.isSetIndex_page_offset());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetIndex_page_offset()) {
                                          lastComparison = TBaseHelper.compareTo(this.index_page_offset, other.index_page_offset);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetDictionary_page_offset(), other.isSetDictionary_page_offset());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetDictionary_page_offset()) {
                                             lastComparison = TBaseHelper.compareTo(this.dictionary_page_offset, other.dictionary_page_offset);
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             }
                                          }

                                          lastComparison = Boolean.compare(this.isSetStatistics(), other.isSetStatistics());
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          } else {
                                             if (this.isSetStatistics()) {
                                                lastComparison = TBaseHelper.compareTo((Comparable)this.statistics, (Comparable)other.statistics);
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                }
                                             }

                                             lastComparison = Boolean.compare(this.isSetEncoding_stats(), other.isSetEncoding_stats());
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             } else {
                                                if (this.isSetEncoding_stats()) {
                                                   lastComparison = TBaseHelper.compareTo(this.encoding_stats, other.encoding_stats);
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   }
                                                }

                                                lastComparison = Boolean.compare(this.isSetBloom_filter_offset(), other.isSetBloom_filter_offset());
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                } else {
                                                   if (this.isSetBloom_filter_offset()) {
                                                      lastComparison = TBaseHelper.compareTo(this.bloom_filter_offset, other.bloom_filter_offset);
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      }
                                                   }

                                                   lastComparison = Boolean.compare(this.isSetBloom_filter_length(), other.isSetBloom_filter_length());
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   } else {
                                                      if (this.isSetBloom_filter_length()) {
                                                         lastComparison = TBaseHelper.compareTo(this.bloom_filter_length, other.bloom_filter_length);
                                                         if (lastComparison != 0) {
                                                            return lastComparison;
                                                         }
                                                      }

                                                      lastComparison = Boolean.compare(this.isSetSize_statistics(), other.isSetSize_statistics());
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      } else {
                                                         if (this.isSetSize_statistics()) {
                                                            lastComparison = TBaseHelper.compareTo((Comparable)this.size_statistics, (Comparable)other.size_statistics);
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
                     }
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnMetaData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ColumnMetaData(");
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

      sb.append("encodings:");
      if (this.encodings == null) {
         sb.append("null");
      } else {
         sb.append(this.encodings);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("path_in_schema:");
      if (this.path_in_schema == null) {
         sb.append("null");
      } else {
         sb.append(this.path_in_schema);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("codec:");
      if (this.codec == null) {
         sb.append("null");
      } else {
         sb.append(this.codec);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("num_values:");
      sb.append(this.num_values);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("total_uncompressed_size:");
      sb.append(this.total_uncompressed_size);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("total_compressed_size:");
      sb.append(this.total_compressed_size);
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

      if (!first) {
         sb.append(", ");
      }

      sb.append("data_page_offset:");
      sb.append(this.data_page_offset);
      first = false;
      if (this.isSetIndex_page_offset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("index_page_offset:");
         sb.append(this.index_page_offset);
         first = false;
      }

      if (this.isSetDictionary_page_offset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("dictionary_page_offset:");
         sb.append(this.dictionary_page_offset);
         first = false;
      }

      if (this.isSetStatistics()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("statistics:");
         if (this.statistics == null) {
            sb.append("null");
         } else {
            sb.append(this.statistics);
         }

         first = false;
      }

      if (this.isSetEncoding_stats()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("encoding_stats:");
         if (this.encoding_stats == null) {
            sb.append("null");
         } else {
            sb.append(this.encoding_stats);
         }

         first = false;
      }

      if (this.isSetBloom_filter_offset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("bloom_filter_offset:");
         sb.append(this.bloom_filter_offset);
         first = false;
      }

      if (this.isSetBloom_filter_length()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("bloom_filter_length:");
         sb.append(this.bloom_filter_length);
         first = false;
      }

      if (this.isSetSize_statistics()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("size_statistics:");
         if (this.size_statistics == null) {
            sb.append("null");
         } else {
            sb.append(this.size_statistics);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.type == null) {
         throw new TProtocolException("Required field 'type' was not present! Struct: " + this.toString());
      } else if (this.encodings == null) {
         throw new TProtocolException("Required field 'encodings' was not present! Struct: " + this.toString());
      } else if (this.path_in_schema == null) {
         throw new TProtocolException("Required field 'path_in_schema' was not present! Struct: " + this.toString());
      } else if (this.codec == null) {
         throw new TProtocolException("Required field 'codec' was not present! Struct: " + this.toString());
      } else {
         if (this.statistics != null) {
            this.statistics.validate();
         }

         if (this.size_statistics != null) {
            this.size_statistics.validate();
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
      optionals = new _Fields[]{ColumnMetaData._Fields.KEY_VALUE_METADATA, ColumnMetaData._Fields.INDEX_PAGE_OFFSET, ColumnMetaData._Fields.DICTIONARY_PAGE_OFFSET, ColumnMetaData._Fields.STATISTICS, ColumnMetaData._Fields.ENCODING_STATS, ColumnMetaData._Fields.BLOOM_FILTER_OFFSET, ColumnMetaData._Fields.BLOOM_FILTER_LENGTH, ColumnMetaData._Fields.SIZE_STATISTICS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ColumnMetaData._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)-1, Type.class)));
      tmpMap.put(ColumnMetaData._Fields.ENCODINGS, new FieldMetaData("encodings", (byte)1, new ListMetaData((byte)15, new EnumMetaData((byte)-1, Encoding.class))));
      tmpMap.put(ColumnMetaData._Fields.PATH_IN_SCHEMA, new FieldMetaData("path_in_schema", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(ColumnMetaData._Fields.CODEC, new FieldMetaData("codec", (byte)1, new EnumMetaData((byte)-1, CompressionCodec.class)));
      tmpMap.put(ColumnMetaData._Fields.NUM_VALUES, new FieldMetaData("num_values", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.TOTAL_UNCOMPRESSED_SIZE, new FieldMetaData("total_uncompressed_size", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.TOTAL_COMPRESSED_SIZE, new FieldMetaData("total_compressed_size", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.KEY_VALUE_METADATA, new FieldMetaData("key_value_metadata", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, KeyValue.class))));
      tmpMap.put(ColumnMetaData._Fields.DATA_PAGE_OFFSET, new FieldMetaData("data_page_offset", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.INDEX_PAGE_OFFSET, new FieldMetaData("index_page_offset", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.DICTIONARY_PAGE_OFFSET, new FieldMetaData("dictionary_page_offset", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.STATISTICS, new FieldMetaData("statistics", (byte)2, new StructMetaData((byte)12, Statistics.class)));
      tmpMap.put(ColumnMetaData._Fields.ENCODING_STATS, new FieldMetaData("encoding_stats", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, PageEncodingStats.class))));
      tmpMap.put(ColumnMetaData._Fields.BLOOM_FILTER_OFFSET, new FieldMetaData("bloom_filter_offset", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnMetaData._Fields.BLOOM_FILTER_LENGTH, new FieldMetaData("bloom_filter_length", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(ColumnMetaData._Fields.SIZE_STATISTICS, new FieldMetaData("size_statistics", (byte)2, new StructMetaData((byte)12, SizeStatistics.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnMetaData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPE((short)1, "type"),
      ENCODINGS((short)2, "encodings"),
      PATH_IN_SCHEMA((short)3, "path_in_schema"),
      CODEC((short)4, "codec"),
      NUM_VALUES((short)5, "num_values"),
      TOTAL_UNCOMPRESSED_SIZE((short)6, "total_uncompressed_size"),
      TOTAL_COMPRESSED_SIZE((short)7, "total_compressed_size"),
      KEY_VALUE_METADATA((short)8, "key_value_metadata"),
      DATA_PAGE_OFFSET((short)9, "data_page_offset"),
      INDEX_PAGE_OFFSET((short)10, "index_page_offset"),
      DICTIONARY_PAGE_OFFSET((short)11, "dictionary_page_offset"),
      STATISTICS((short)12, "statistics"),
      ENCODING_STATS((short)13, "encoding_stats"),
      BLOOM_FILTER_OFFSET((short)14, "bloom_filter_offset"),
      BLOOM_FILTER_LENGTH((short)15, "bloom_filter_length"),
      SIZE_STATISTICS((short)16, "size_statistics");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPE;
            case 2:
               return ENCODINGS;
            case 3:
               return PATH_IN_SCHEMA;
            case 4:
               return CODEC;
            case 5:
               return NUM_VALUES;
            case 6:
               return TOTAL_UNCOMPRESSED_SIZE;
            case 7:
               return TOTAL_COMPRESSED_SIZE;
            case 8:
               return KEY_VALUE_METADATA;
            case 9:
               return DATA_PAGE_OFFSET;
            case 10:
               return INDEX_PAGE_OFFSET;
            case 11:
               return DICTIONARY_PAGE_OFFSET;
            case 12:
               return STATISTICS;
            case 13:
               return ENCODING_STATS;
            case 14:
               return BLOOM_FILTER_OFFSET;
            case 15:
               return BLOOM_FILTER_LENGTH;
            case 16:
               return SIZE_STATISTICS;
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

   private static class ColumnMetaDataStandardSchemeFactory implements SchemeFactory {
      private ColumnMetaDataStandardSchemeFactory() {
      }

      public ColumnMetaDataStandardScheme getScheme() {
         return new ColumnMetaDataStandardScheme();
      }
   }

   private static class ColumnMetaDataStandardScheme extends StandardScheme {
      private ColumnMetaDataStandardScheme() {
      }

      public void read(TProtocol iprot, ColumnMetaData struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetNum_values()) {
                  throw new TProtocolException("Required field 'num_values' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetTotal_uncompressed_size()) {
                  throw new TProtocolException("Required field 'total_uncompressed_size' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetTotal_compressed_size()) {
                  throw new TProtocolException("Required field 'total_compressed_size' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetData_page_offset()) {
                  throw new TProtocolException("Required field 'data_page_offset' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.type = Type.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list16 = iprot.readListBegin();
                  struct.encodings = new ArrayList(_list16.size);
                  int _i18 = 0;

                  for(; _i18 < _list16.size; ++_i18) {
                     Encoding _elem17 = Encoding.findByValue(iprot.readI32());
                     if (_elem17 != null) {
                        struct.encodings.add(_elem17);
                     }
                  }

                  iprot.readListEnd();
                  struct.setEncodingsIsSet(true);
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list19 = iprot.readListBegin();
                  struct.path_in_schema = new ArrayList(_list19.size);

                  for(int _i21 = 0; _i21 < _list19.size; ++_i21) {
                     String _elem20 = iprot.readString();
                     struct.path_in_schema.add(_elem20);
                  }

                  iprot.readListEnd();
                  struct.setPath_in_schemaIsSet(true);
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.codec = CompressionCodec.findByValue(iprot.readI32());
                     struct.setCodecIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 10) {
                     struct.num_values = iprot.readI64();
                     struct.setNum_valuesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 10) {
                     struct.total_uncompressed_size = iprot.readI64();
                     struct.setTotal_uncompressed_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 10) {
                     struct.total_compressed_size = iprot.readI64();
                     struct.setTotal_compressed_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list22 = iprot.readListBegin();
                  struct.key_value_metadata = new ArrayList(_list22.size);

                  for(int _i24 = 0; _i24 < _list22.size; ++_i24) {
                     KeyValue _elem23 = new KeyValue();
                     _elem23.read(iprot);
                     struct.key_value_metadata.add(_elem23);
                  }

                  iprot.readListEnd();
                  struct.setKey_value_metadataIsSet(true);
                  break;
               case 9:
                  if (schemeField.type == 10) {
                     struct.data_page_offset = iprot.readI64();
                     struct.setData_page_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 10) {
                     struct.index_page_offset = iprot.readI64();
                     struct.setIndex_page_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 11:
                  if (schemeField.type == 10) {
                     struct.dictionary_page_offset = iprot.readI64();
                     struct.setDictionary_page_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 12:
                  if (schemeField.type == 12) {
                     struct.statistics = new Statistics();
                     struct.statistics.read(iprot);
                     struct.setStatisticsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 13:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list25 = iprot.readListBegin();
                  struct.encoding_stats = new ArrayList(_list25.size);

                  for(int _i27 = 0; _i27 < _list25.size; ++_i27) {
                     PageEncodingStats _elem26 = new PageEncodingStats();
                     _elem26.read(iprot);
                     struct.encoding_stats.add(_elem26);
                  }

                  iprot.readListEnd();
                  struct.setEncoding_statsIsSet(true);
                  break;
               case 14:
                  if (schemeField.type == 10) {
                     struct.bloom_filter_offset = iprot.readI64();
                     struct.setBloom_filter_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 15:
                  if (schemeField.type == 8) {
                     struct.bloom_filter_length = iprot.readI32();
                     struct.setBloom_filter_lengthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 16:
                  if (schemeField.type == 12) {
                     struct.size_statistics = new SizeStatistics();
                     struct.size_statistics.read(iprot);
                     struct.setSize_statisticsIsSet(true);
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

      public void write(TProtocol oprot, ColumnMetaData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ColumnMetaData.STRUCT_DESC);
         if (struct.type != null) {
            oprot.writeFieldBegin(ColumnMetaData.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.encodings != null) {
            oprot.writeFieldBegin(ColumnMetaData.ENCODINGS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)8, struct.encodings.size()));

            for(Encoding _iter28 : struct.encodings) {
               oprot.writeI32(_iter28.getValue());
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.path_in_schema != null) {
            oprot.writeFieldBegin(ColumnMetaData.PATH_IN_SCHEMA_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.path_in_schema.size()));

            for(String _iter29 : struct.path_in_schema) {
               oprot.writeString(_iter29);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.codec != null) {
            oprot.writeFieldBegin(ColumnMetaData.CODEC_FIELD_DESC);
            oprot.writeI32(struct.codec.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(ColumnMetaData.NUM_VALUES_FIELD_DESC);
         oprot.writeI64(struct.num_values);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(ColumnMetaData.TOTAL_UNCOMPRESSED_SIZE_FIELD_DESC);
         oprot.writeI64(struct.total_uncompressed_size);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(ColumnMetaData.TOTAL_COMPRESSED_SIZE_FIELD_DESC);
         oprot.writeI64(struct.total_compressed_size);
         oprot.writeFieldEnd();
         if (struct.key_value_metadata != null && struct.isSetKey_value_metadata()) {
            oprot.writeFieldBegin(ColumnMetaData.KEY_VALUE_METADATA_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.key_value_metadata.size()));

            for(KeyValue _iter30 : struct.key_value_metadata) {
               _iter30.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(ColumnMetaData.DATA_PAGE_OFFSET_FIELD_DESC);
         oprot.writeI64(struct.data_page_offset);
         oprot.writeFieldEnd();
         if (struct.isSetIndex_page_offset()) {
            oprot.writeFieldBegin(ColumnMetaData.INDEX_PAGE_OFFSET_FIELD_DESC);
            oprot.writeI64(struct.index_page_offset);
            oprot.writeFieldEnd();
         }

         if (struct.isSetDictionary_page_offset()) {
            oprot.writeFieldBegin(ColumnMetaData.DICTIONARY_PAGE_OFFSET_FIELD_DESC);
            oprot.writeI64(struct.dictionary_page_offset);
            oprot.writeFieldEnd();
         }

         if (struct.statistics != null && struct.isSetStatistics()) {
            oprot.writeFieldBegin(ColumnMetaData.STATISTICS_FIELD_DESC);
            struct.statistics.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.encoding_stats != null && struct.isSetEncoding_stats()) {
            oprot.writeFieldBegin(ColumnMetaData.ENCODING_STATS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.encoding_stats.size()));

            for(PageEncodingStats _iter31 : struct.encoding_stats) {
               _iter31.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetBloom_filter_offset()) {
            oprot.writeFieldBegin(ColumnMetaData.BLOOM_FILTER_OFFSET_FIELD_DESC);
            oprot.writeI64(struct.bloom_filter_offset);
            oprot.writeFieldEnd();
         }

         if (struct.isSetBloom_filter_length()) {
            oprot.writeFieldBegin(ColumnMetaData.BLOOM_FILTER_LENGTH_FIELD_DESC);
            oprot.writeI32(struct.bloom_filter_length);
            oprot.writeFieldEnd();
         }

         if (struct.size_statistics != null && struct.isSetSize_statistics()) {
            oprot.writeFieldBegin(ColumnMetaData.SIZE_STATISTICS_FIELD_DESC);
            struct.size_statistics.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ColumnMetaDataTupleSchemeFactory implements SchemeFactory {
      private ColumnMetaDataTupleSchemeFactory() {
      }

      public ColumnMetaDataTupleScheme getScheme() {
         return new ColumnMetaDataTupleScheme();
      }
   }

   private static class ColumnMetaDataTupleScheme extends TupleScheme {
      private ColumnMetaDataTupleScheme() {
      }

      public void write(TProtocol prot, ColumnMetaData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.type.getValue());
         oprot.writeI32(struct.encodings.size());

         for(Encoding _iter32 : struct.encodings) {
            oprot.writeI32(_iter32.getValue());
         }

         oprot.writeI32(struct.path_in_schema.size());

         for(String _iter33 : struct.path_in_schema) {
            oprot.writeString(_iter33);
         }

         oprot.writeI32(struct.codec.getValue());
         oprot.writeI64(struct.num_values);
         oprot.writeI64(struct.total_uncompressed_size);
         oprot.writeI64(struct.total_compressed_size);
         oprot.writeI64(struct.data_page_offset);
         BitSet optionals = new BitSet();
         if (struct.isSetKey_value_metadata()) {
            optionals.set(0);
         }

         if (struct.isSetIndex_page_offset()) {
            optionals.set(1);
         }

         if (struct.isSetDictionary_page_offset()) {
            optionals.set(2);
         }

         if (struct.isSetStatistics()) {
            optionals.set(3);
         }

         if (struct.isSetEncoding_stats()) {
            optionals.set(4);
         }

         if (struct.isSetBloom_filter_offset()) {
            optionals.set(5);
         }

         if (struct.isSetBloom_filter_length()) {
            optionals.set(6);
         }

         if (struct.isSetSize_statistics()) {
            optionals.set(7);
         }

         oprot.writeBitSet(optionals, 8);
         if (struct.isSetKey_value_metadata()) {
            oprot.writeI32(struct.key_value_metadata.size());

            for(KeyValue _iter34 : struct.key_value_metadata) {
               _iter34.write(oprot);
            }
         }

         if (struct.isSetIndex_page_offset()) {
            oprot.writeI64(struct.index_page_offset);
         }

         if (struct.isSetDictionary_page_offset()) {
            oprot.writeI64(struct.dictionary_page_offset);
         }

         if (struct.isSetStatistics()) {
            struct.statistics.write(oprot);
         }

         if (struct.isSetEncoding_stats()) {
            oprot.writeI32(struct.encoding_stats.size());

            for(PageEncodingStats _iter35 : struct.encoding_stats) {
               _iter35.write(oprot);
            }
         }

         if (struct.isSetBloom_filter_offset()) {
            oprot.writeI64(struct.bloom_filter_offset);
         }

         if (struct.isSetBloom_filter_length()) {
            oprot.writeI32(struct.bloom_filter_length);
         }

         if (struct.isSetSize_statistics()) {
            struct.size_statistics.write(oprot);
         }

      }

      public void read(TProtocol prot, ColumnMetaData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.type = Type.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         TList _list36 = iprot.readListBegin((byte)8);
         struct.encodings = new ArrayList(_list36.size);

         for(int _i38 = 0; _i38 < _list36.size; ++_i38) {
            Encoding _elem37 = Encoding.findByValue(iprot.readI32());
            if (_elem37 != null) {
               struct.encodings.add(_elem37);
            }
         }

         struct.setEncodingsIsSet(true);
         _list36 = iprot.readListBegin((byte)11);
         struct.path_in_schema = new ArrayList(_list36.size);

         for(int _i41 = 0; _i41 < _list36.size; ++_i41) {
            String _elem40 = iprot.readString();
            struct.path_in_schema.add(_elem40);
         }

         struct.setPath_in_schemaIsSet(true);
         struct.codec = CompressionCodec.findByValue(iprot.readI32());
         struct.setCodecIsSet(true);
         struct.num_values = iprot.readI64();
         struct.setNum_valuesIsSet(true);
         struct.total_uncompressed_size = iprot.readI64();
         struct.setTotal_uncompressed_sizeIsSet(true);
         struct.total_compressed_size = iprot.readI64();
         struct.setTotal_compressed_sizeIsSet(true);
         struct.data_page_offset = iprot.readI64();
         struct.setData_page_offsetIsSet(true);
         BitSet incoming = iprot.readBitSet(8);
         if (incoming.get(0)) {
            TList _list42 = iprot.readListBegin((byte)12);
            struct.key_value_metadata = new ArrayList(_list42.size);

            for(int _i44 = 0; _i44 < _list42.size; ++_i44) {
               KeyValue _elem43 = new KeyValue();
               _elem43.read(iprot);
               struct.key_value_metadata.add(_elem43);
            }

            struct.setKey_value_metadataIsSet(true);
         }

         if (incoming.get(1)) {
            struct.index_page_offset = iprot.readI64();
            struct.setIndex_page_offsetIsSet(true);
         }

         if (incoming.get(2)) {
            struct.dictionary_page_offset = iprot.readI64();
            struct.setDictionary_page_offsetIsSet(true);
         }

         if (incoming.get(3)) {
            struct.statistics = new Statistics();
            struct.statistics.read(iprot);
            struct.setStatisticsIsSet(true);
         }

         if (incoming.get(4)) {
            TList _list45 = iprot.readListBegin((byte)12);
            struct.encoding_stats = new ArrayList(_list45.size);

            for(int _i47 = 0; _i47 < _list45.size; ++_i47) {
               PageEncodingStats _elem46 = new PageEncodingStats();
               _elem46.read(iprot);
               struct.encoding_stats.add(_elem46);
            }

            struct.setEncoding_statsIsSet(true);
         }

         if (incoming.get(5)) {
            struct.bloom_filter_offset = iprot.readI64();
            struct.setBloom_filter_offsetIsSet(true);
         }

         if (incoming.get(6)) {
            struct.bloom_filter_length = iprot.readI32();
            struct.setBloom_filter_lengthIsSet(true);
         }

         if (incoming.get(7)) {
            struct.size_statistics = new SizeStatistics();
            struct.size_statistics.read(iprot);
            struct.setSize_statisticsIsSet(true);
         }

      }
   }
}
