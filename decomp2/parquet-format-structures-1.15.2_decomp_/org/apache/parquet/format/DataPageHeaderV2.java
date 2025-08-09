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

public class DataPageHeaderV2 implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DataPageHeaderV2");
   private static final TField NUM_VALUES_FIELD_DESC = new TField("num_values", (byte)8, (short)1);
   private static final TField NUM_NULLS_FIELD_DESC = new TField("num_nulls", (byte)8, (short)2);
   private static final TField NUM_ROWS_FIELD_DESC = new TField("num_rows", (byte)8, (short)3);
   private static final TField ENCODING_FIELD_DESC = new TField("encoding", (byte)8, (short)4);
   private static final TField DEFINITION_LEVELS_BYTE_LENGTH_FIELD_DESC = new TField("definition_levels_byte_length", (byte)8, (short)5);
   private static final TField REPETITION_LEVELS_BYTE_LENGTH_FIELD_DESC = new TField("repetition_levels_byte_length", (byte)8, (short)6);
   private static final TField IS_COMPRESSED_FIELD_DESC = new TField("is_compressed", (byte)2, (short)7);
   private static final TField STATISTICS_FIELD_DESC = new TField("statistics", (byte)12, (short)8);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DataPageHeaderV2StandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DataPageHeaderV2TupleSchemeFactory();
   public int num_values;
   public int num_nulls;
   public int num_rows;
   @Nullable
   public Encoding encoding;
   public int definition_levels_byte_length;
   public int repetition_levels_byte_length;
   public boolean is_compressed;
   @Nullable
   public Statistics statistics;
   private static final int __NUM_VALUES_ISSET_ID = 0;
   private static final int __NUM_NULLS_ISSET_ID = 1;
   private static final int __NUM_ROWS_ISSET_ID = 2;
   private static final int __DEFINITION_LEVELS_BYTE_LENGTH_ISSET_ID = 3;
   private static final int __REPETITION_LEVELS_BYTE_LENGTH_ISSET_ID = 4;
   private static final int __IS_COMPRESSED_ISSET_ID = 5;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DataPageHeaderV2() {
      this.__isset_bitfield = 0;
      this.is_compressed = true;
   }

   public DataPageHeaderV2(int num_values, int num_nulls, int num_rows, Encoding encoding, int definition_levels_byte_length, int repetition_levels_byte_length) {
      this();
      this.num_values = num_values;
      this.setNum_valuesIsSet(true);
      this.num_nulls = num_nulls;
      this.setNum_nullsIsSet(true);
      this.num_rows = num_rows;
      this.setNum_rowsIsSet(true);
      this.encoding = encoding;
      this.definition_levels_byte_length = definition_levels_byte_length;
      this.setDefinition_levels_byte_lengthIsSet(true);
      this.repetition_levels_byte_length = repetition_levels_byte_length;
      this.setRepetition_levels_byte_lengthIsSet(true);
   }

   public DataPageHeaderV2(DataPageHeaderV2 other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.num_values = other.num_values;
      this.num_nulls = other.num_nulls;
      this.num_rows = other.num_rows;
      if (other.isSetEncoding()) {
         this.encoding = other.encoding;
      }

      this.definition_levels_byte_length = other.definition_levels_byte_length;
      this.repetition_levels_byte_length = other.repetition_levels_byte_length;
      this.is_compressed = other.is_compressed;
      if (other.isSetStatistics()) {
         this.statistics = new Statistics(other.statistics);
      }

   }

   public DataPageHeaderV2 deepCopy() {
      return new DataPageHeaderV2(this);
   }

   public void clear() {
      this.setNum_valuesIsSet(false);
      this.num_values = 0;
      this.setNum_nullsIsSet(false);
      this.num_nulls = 0;
      this.setNum_rowsIsSet(false);
      this.num_rows = 0;
      this.encoding = null;
      this.setDefinition_levels_byte_lengthIsSet(false);
      this.definition_levels_byte_length = 0;
      this.setRepetition_levels_byte_lengthIsSet(false);
      this.repetition_levels_byte_length = 0;
      this.is_compressed = true;
      this.statistics = null;
   }

   public int getNum_values() {
      return this.num_values;
   }

   public DataPageHeaderV2 setNum_values(int num_values) {
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

   public int getNum_nulls() {
      return this.num_nulls;
   }

   public DataPageHeaderV2 setNum_nulls(int num_nulls) {
      this.num_nulls = num_nulls;
      this.setNum_nullsIsSet(true);
      return this;
   }

   public void unsetNum_nulls() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetNum_nulls() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setNum_nullsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
   }

   public int getNum_rows() {
      return this.num_rows;
   }

   public DataPageHeaderV2 setNum_rows(int num_rows) {
      this.num_rows = num_rows;
      this.setNum_rowsIsSet(true);
      return this;
   }

   public void unsetNum_rows() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetNum_rows() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setNum_rowsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   @Nullable
   public Encoding getEncoding() {
      return this.encoding;
   }

   public DataPageHeaderV2 setEncoding(@Nullable Encoding encoding) {
      this.encoding = encoding;
      return this;
   }

   public void unsetEncoding() {
      this.encoding = null;
   }

   public boolean isSetEncoding() {
      return this.encoding != null;
   }

   public void setEncodingIsSet(boolean value) {
      if (!value) {
         this.encoding = null;
      }

   }

   public int getDefinition_levels_byte_length() {
      return this.definition_levels_byte_length;
   }

   public DataPageHeaderV2 setDefinition_levels_byte_length(int definition_levels_byte_length) {
      this.definition_levels_byte_length = definition_levels_byte_length;
      this.setDefinition_levels_byte_lengthIsSet(true);
      return this;
   }

   public void unsetDefinition_levels_byte_length() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 3);
   }

   public boolean isSetDefinition_levels_byte_length() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 3);
   }

   public void setDefinition_levels_byte_lengthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 3, value);
   }

   public int getRepetition_levels_byte_length() {
      return this.repetition_levels_byte_length;
   }

   public DataPageHeaderV2 setRepetition_levels_byte_length(int repetition_levels_byte_length) {
      this.repetition_levels_byte_length = repetition_levels_byte_length;
      this.setRepetition_levels_byte_lengthIsSet(true);
      return this;
   }

   public void unsetRepetition_levels_byte_length() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 4);
   }

   public boolean isSetRepetition_levels_byte_length() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 4);
   }

   public void setRepetition_levels_byte_lengthIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 4, value);
   }

   public boolean isIs_compressed() {
      return this.is_compressed;
   }

   public DataPageHeaderV2 setIs_compressed(boolean is_compressed) {
      this.is_compressed = is_compressed;
      this.setIs_compressedIsSet(true);
      return this;
   }

   public void unsetIs_compressed() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 5);
   }

   public boolean isSetIs_compressed() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 5);
   }

   public void setIs_compressedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 5, value);
   }

   @Nullable
   public Statistics getStatistics() {
      return this.statistics;
   }

   public DataPageHeaderV2 setStatistics(@Nullable Statistics statistics) {
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

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NUM_VALUES:
            if (value == null) {
               this.unsetNum_values();
            } else {
               this.setNum_values((Integer)value);
            }
            break;
         case NUM_NULLS:
            if (value == null) {
               this.unsetNum_nulls();
            } else {
               this.setNum_nulls((Integer)value);
            }
            break;
         case NUM_ROWS:
            if (value == null) {
               this.unsetNum_rows();
            } else {
               this.setNum_rows((Integer)value);
            }
            break;
         case ENCODING:
            if (value == null) {
               this.unsetEncoding();
            } else {
               this.setEncoding((Encoding)value);
            }
            break;
         case DEFINITION_LEVELS_BYTE_LENGTH:
            if (value == null) {
               this.unsetDefinition_levels_byte_length();
            } else {
               this.setDefinition_levels_byte_length((Integer)value);
            }
            break;
         case REPETITION_LEVELS_BYTE_LENGTH:
            if (value == null) {
               this.unsetRepetition_levels_byte_length();
            } else {
               this.setRepetition_levels_byte_length((Integer)value);
            }
            break;
         case IS_COMPRESSED:
            if (value == null) {
               this.unsetIs_compressed();
            } else {
               this.setIs_compressed((Boolean)value);
            }
            break;
         case STATISTICS:
            if (value == null) {
               this.unsetStatistics();
            } else {
               this.setStatistics((Statistics)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NUM_VALUES:
            return this.getNum_values();
         case NUM_NULLS:
            return this.getNum_nulls();
         case NUM_ROWS:
            return this.getNum_rows();
         case ENCODING:
            return this.getEncoding();
         case DEFINITION_LEVELS_BYTE_LENGTH:
            return this.getDefinition_levels_byte_length();
         case REPETITION_LEVELS_BYTE_LENGTH:
            return this.getRepetition_levels_byte_length();
         case IS_COMPRESSED:
            return this.isIs_compressed();
         case STATISTICS:
            return this.getStatistics();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NUM_VALUES:
               return this.isSetNum_values();
            case NUM_NULLS:
               return this.isSetNum_nulls();
            case NUM_ROWS:
               return this.isSetNum_rows();
            case ENCODING:
               return this.isSetEncoding();
            case DEFINITION_LEVELS_BYTE_LENGTH:
               return this.isSetDefinition_levels_byte_length();
            case REPETITION_LEVELS_BYTE_LENGTH:
               return this.isSetRepetition_levels_byte_length();
            case IS_COMPRESSED:
               return this.isSetIs_compressed();
            case STATISTICS:
               return this.isSetStatistics();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DataPageHeaderV2 ? this.equals((DataPageHeaderV2)that) : false;
   }

   public boolean equals(DataPageHeaderV2 that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_num_nulls = true;
         boolean that_present_num_nulls = true;
         if (this_present_num_nulls || that_present_num_nulls) {
            if (!this_present_num_nulls || !that_present_num_nulls) {
               return false;
            }

            if (this.num_nulls != that.num_nulls) {
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

         boolean this_present_encoding = this.isSetEncoding();
         boolean that_present_encoding = that.isSetEncoding();
         if (this_present_encoding || that_present_encoding) {
            if (!this_present_encoding || !that_present_encoding) {
               return false;
            }

            if (!this.encoding.equals(that.encoding)) {
               return false;
            }
         }

         boolean this_present_definition_levels_byte_length = true;
         boolean that_present_definition_levels_byte_length = true;
         if (this_present_definition_levels_byte_length || that_present_definition_levels_byte_length) {
            if (!this_present_definition_levels_byte_length || !that_present_definition_levels_byte_length) {
               return false;
            }

            if (this.definition_levels_byte_length != that.definition_levels_byte_length) {
               return false;
            }
         }

         boolean this_present_repetition_levels_byte_length = true;
         boolean that_present_repetition_levels_byte_length = true;
         if (this_present_repetition_levels_byte_length || that_present_repetition_levels_byte_length) {
            if (!this_present_repetition_levels_byte_length || !that_present_repetition_levels_byte_length) {
               return false;
            }

            if (this.repetition_levels_byte_length != that.repetition_levels_byte_length) {
               return false;
            }
         }

         boolean this_present_is_compressed = this.isSetIs_compressed();
         boolean that_present_is_compressed = that.isSetIs_compressed();
         if (this_present_is_compressed || that_present_is_compressed) {
            if (!this_present_is_compressed || !that_present_is_compressed) {
               return false;
            }

            if (this.is_compressed != that.is_compressed) {
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

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.num_values;
      hashCode = hashCode * 8191 + this.num_nulls;
      hashCode = hashCode * 8191 + this.num_rows;
      hashCode = hashCode * 8191 + (this.isSetEncoding() ? 131071 : 524287);
      if (this.isSetEncoding()) {
         hashCode = hashCode * 8191 + this.encoding.getValue();
      }

      hashCode = hashCode * 8191 + this.definition_levels_byte_length;
      hashCode = hashCode * 8191 + this.repetition_levels_byte_length;
      hashCode = hashCode * 8191 + (this.isSetIs_compressed() ? 131071 : 524287);
      if (this.isSetIs_compressed()) {
         hashCode = hashCode * 8191 + (this.is_compressed ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetStatistics() ? 131071 : 524287);
      if (this.isSetStatistics()) {
         hashCode = hashCode * 8191 + this.statistics.hashCode();
      }

      return hashCode;
   }

   public int compareTo(DataPageHeaderV2 other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

            lastComparison = Boolean.compare(this.isSetNum_nulls(), other.isSetNum_nulls());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetNum_nulls()) {
                  lastComparison = TBaseHelper.compareTo(this.num_nulls, other.num_nulls);
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

                  lastComparison = Boolean.compare(this.isSetEncoding(), other.isSetEncoding());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetEncoding()) {
                        lastComparison = TBaseHelper.compareTo((Comparable)this.encoding, (Comparable)other.encoding);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetDefinition_levels_byte_length(), other.isSetDefinition_levels_byte_length());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetDefinition_levels_byte_length()) {
                           lastComparison = TBaseHelper.compareTo(this.definition_levels_byte_length, other.definition_levels_byte_length);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetRepetition_levels_byte_length(), other.isSetRepetition_levels_byte_length());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetRepetition_levels_byte_length()) {
                              lastComparison = TBaseHelper.compareTo(this.repetition_levels_byte_length, other.repetition_levels_byte_length);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetIs_compressed(), other.isSetIs_compressed());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetIs_compressed()) {
                                 lastComparison = TBaseHelper.compareTo(this.is_compressed, other.is_compressed);
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
      return DataPageHeaderV2._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DataPageHeaderV2(");
      boolean first = true;
      sb.append("num_values:");
      sb.append(this.num_values);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("num_nulls:");
      sb.append(this.num_nulls);
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

      sb.append("encoding:");
      if (this.encoding == null) {
         sb.append("null");
      } else {
         sb.append(this.encoding);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("definition_levels_byte_length:");
      sb.append(this.definition_levels_byte_length);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("repetition_levels_byte_length:");
      sb.append(this.repetition_levels_byte_length);
      first = false;
      if (this.isSetIs_compressed()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("is_compressed:");
         sb.append(this.is_compressed);
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

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.encoding == null) {
         throw new TProtocolException("Required field 'encoding' was not present! Struct: " + this.toString());
      } else {
         if (this.statistics != null) {
            this.statistics.validate();
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
      optionals = new _Fields[]{DataPageHeaderV2._Fields.IS_COMPRESSED, DataPageHeaderV2._Fields.STATISTICS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DataPageHeaderV2._Fields.NUM_VALUES, new FieldMetaData("num_values", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DataPageHeaderV2._Fields.NUM_NULLS, new FieldMetaData("num_nulls", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DataPageHeaderV2._Fields.NUM_ROWS, new FieldMetaData("num_rows", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DataPageHeaderV2._Fields.ENCODING, new FieldMetaData("encoding", (byte)1, new EnumMetaData((byte)-1, Encoding.class)));
      tmpMap.put(DataPageHeaderV2._Fields.DEFINITION_LEVELS_BYTE_LENGTH, new FieldMetaData("definition_levels_byte_length", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DataPageHeaderV2._Fields.REPETITION_LEVELS_BYTE_LENGTH, new FieldMetaData("repetition_levels_byte_length", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DataPageHeaderV2._Fields.IS_COMPRESSED, new FieldMetaData("is_compressed", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(DataPageHeaderV2._Fields.STATISTICS, new FieldMetaData("statistics", (byte)2, new StructMetaData((byte)12, Statistics.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DataPageHeaderV2.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NUM_VALUES((short)1, "num_values"),
      NUM_NULLS((short)2, "num_nulls"),
      NUM_ROWS((short)3, "num_rows"),
      ENCODING((short)4, "encoding"),
      DEFINITION_LEVELS_BYTE_LENGTH((short)5, "definition_levels_byte_length"),
      REPETITION_LEVELS_BYTE_LENGTH((short)6, "repetition_levels_byte_length"),
      IS_COMPRESSED((short)7, "is_compressed"),
      STATISTICS((short)8, "statistics");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NUM_VALUES;
            case 2:
               return NUM_NULLS;
            case 3:
               return NUM_ROWS;
            case 4:
               return ENCODING;
            case 5:
               return DEFINITION_LEVELS_BYTE_LENGTH;
            case 6:
               return REPETITION_LEVELS_BYTE_LENGTH;
            case 7:
               return IS_COMPRESSED;
            case 8:
               return STATISTICS;
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

   private static class DataPageHeaderV2StandardSchemeFactory implements SchemeFactory {
      private DataPageHeaderV2StandardSchemeFactory() {
      }

      public DataPageHeaderV2StandardScheme getScheme() {
         return new DataPageHeaderV2StandardScheme();
      }
   }

   private static class DataPageHeaderV2StandardScheme extends StandardScheme {
      private DataPageHeaderV2StandardScheme() {
      }

      public void read(TProtocol iprot, DataPageHeaderV2 struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetNum_values()) {
                  throw new TProtocolException("Required field 'num_values' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetNum_nulls()) {
                  throw new TProtocolException("Required field 'num_nulls' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetNum_rows()) {
                  throw new TProtocolException("Required field 'num_rows' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetDefinition_levels_byte_length()) {
                  throw new TProtocolException("Required field 'definition_levels_byte_length' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetRepetition_levels_byte_length()) {
                  throw new TProtocolException("Required field 'repetition_levels_byte_length' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.num_values = iprot.readI32();
                     struct.setNum_valuesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.num_nulls = iprot.readI32();
                     struct.setNum_nullsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.num_rows = iprot.readI32();
                     struct.setNum_rowsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.encoding = Encoding.findByValue(iprot.readI32());
                     struct.setEncodingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.definition_levels_byte_length = iprot.readI32();
                     struct.setDefinition_levels_byte_lengthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.repetition_levels_byte_length = iprot.readI32();
                     struct.setRepetition_levels_byte_lengthIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 2) {
                     struct.is_compressed = iprot.readBool();
                     struct.setIs_compressedIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.statistics = new Statistics();
                     struct.statistics.read(iprot);
                     struct.setStatisticsIsSet(true);
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

      public void write(TProtocol oprot, DataPageHeaderV2 struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DataPageHeaderV2.STRUCT_DESC);
         oprot.writeFieldBegin(DataPageHeaderV2.NUM_VALUES_FIELD_DESC);
         oprot.writeI32(struct.num_values);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(DataPageHeaderV2.NUM_NULLS_FIELD_DESC);
         oprot.writeI32(struct.num_nulls);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(DataPageHeaderV2.NUM_ROWS_FIELD_DESC);
         oprot.writeI32(struct.num_rows);
         oprot.writeFieldEnd();
         if (struct.encoding != null) {
            oprot.writeFieldBegin(DataPageHeaderV2.ENCODING_FIELD_DESC);
            oprot.writeI32(struct.encoding.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(DataPageHeaderV2.DEFINITION_LEVELS_BYTE_LENGTH_FIELD_DESC);
         oprot.writeI32(struct.definition_levels_byte_length);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(DataPageHeaderV2.REPETITION_LEVELS_BYTE_LENGTH_FIELD_DESC);
         oprot.writeI32(struct.repetition_levels_byte_length);
         oprot.writeFieldEnd();
         if (struct.isSetIs_compressed()) {
            oprot.writeFieldBegin(DataPageHeaderV2.IS_COMPRESSED_FIELD_DESC);
            oprot.writeBool(struct.is_compressed);
            oprot.writeFieldEnd();
         }

         if (struct.statistics != null && struct.isSetStatistics()) {
            oprot.writeFieldBegin(DataPageHeaderV2.STATISTICS_FIELD_DESC);
            struct.statistics.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DataPageHeaderV2TupleSchemeFactory implements SchemeFactory {
      private DataPageHeaderV2TupleSchemeFactory() {
      }

      public DataPageHeaderV2TupleScheme getScheme() {
         return new DataPageHeaderV2TupleScheme();
      }
   }

   private static class DataPageHeaderV2TupleScheme extends TupleScheme {
      private DataPageHeaderV2TupleScheme() {
      }

      public void write(TProtocol prot, DataPageHeaderV2 struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.num_values);
         oprot.writeI32(struct.num_nulls);
         oprot.writeI32(struct.num_rows);
         oprot.writeI32(struct.encoding.getValue());
         oprot.writeI32(struct.definition_levels_byte_length);
         oprot.writeI32(struct.repetition_levels_byte_length);
         BitSet optionals = new BitSet();
         if (struct.isSetIs_compressed()) {
            optionals.set(0);
         }

         if (struct.isSetStatistics()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetIs_compressed()) {
            oprot.writeBool(struct.is_compressed);
         }

         if (struct.isSetStatistics()) {
            struct.statistics.write(oprot);
         }

      }

      public void read(TProtocol prot, DataPageHeaderV2 struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.num_values = iprot.readI32();
         struct.setNum_valuesIsSet(true);
         struct.num_nulls = iprot.readI32();
         struct.setNum_nullsIsSet(true);
         struct.num_rows = iprot.readI32();
         struct.setNum_rowsIsSet(true);
         struct.encoding = Encoding.findByValue(iprot.readI32());
         struct.setEncodingIsSet(true);
         struct.definition_levels_byte_length = iprot.readI32();
         struct.setDefinition_levels_byte_lengthIsSet(true);
         struct.repetition_levels_byte_length = iprot.readI32();
         struct.setRepetition_levels_byte_lengthIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.is_compressed = iprot.readBool();
            struct.setIs_compressedIsSet(true);
         }

         if (incoming.get(1)) {
            struct.statistics = new Statistics();
            struct.statistics.read(iprot);
            struct.setStatisticsIsSet(true);
         }

      }
   }
}
