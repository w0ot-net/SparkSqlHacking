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

public class RowGroup implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("RowGroup");
   private static final TField COLUMNS_FIELD_DESC = new TField("columns", (byte)15, (short)1);
   private static final TField TOTAL_BYTE_SIZE_FIELD_DESC = new TField("total_byte_size", (byte)10, (short)2);
   private static final TField NUM_ROWS_FIELD_DESC = new TField("num_rows", (byte)10, (short)3);
   private static final TField SORTING_COLUMNS_FIELD_DESC = new TField("sorting_columns", (byte)15, (short)4);
   private static final TField FILE_OFFSET_FIELD_DESC = new TField("file_offset", (byte)10, (short)5);
   private static final TField TOTAL_COMPRESSED_SIZE_FIELD_DESC = new TField("total_compressed_size", (byte)10, (short)6);
   private static final TField ORDINAL_FIELD_DESC = new TField("ordinal", (byte)6, (short)7);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new RowGroupStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new RowGroupTupleSchemeFactory();
   @Nullable
   public List columns;
   public long total_byte_size;
   public long num_rows;
   @Nullable
   public List sorting_columns;
   public long file_offset;
   public long total_compressed_size;
   public short ordinal;
   private static final int __TOTAL_BYTE_SIZE_ISSET_ID = 0;
   private static final int __NUM_ROWS_ISSET_ID = 1;
   private static final int __FILE_OFFSET_ISSET_ID = 2;
   private static final int __TOTAL_COMPRESSED_SIZE_ISSET_ID = 3;
   private static final int __ORDINAL_ISSET_ID = 4;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public RowGroup() {
      this.__isset_bitfield = 0;
   }

   public RowGroup(List columns, long total_byte_size, long num_rows) {
      this();
      this.columns = columns;
      this.total_byte_size = total_byte_size;
      this.setTotal_byte_sizeIsSet(true);
      this.num_rows = num_rows;
      this.setNum_rowsIsSet(true);
   }

   public RowGroup(RowGroup other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetColumns()) {
         List<ColumnChunk> __this__columns = new ArrayList(other.columns.size());

         for(ColumnChunk other_element : other.columns) {
            __this__columns.add(new ColumnChunk(other_element));
         }

         this.columns = __this__columns;
      }

      this.total_byte_size = other.total_byte_size;
      this.num_rows = other.num_rows;
      if (other.isSetSorting_columns()) {
         List<SortingColumn> __this__sorting_columns = new ArrayList(other.sorting_columns.size());

         for(SortingColumn other_element : other.sorting_columns) {
            __this__sorting_columns.add(new SortingColumn(other_element));
         }

         this.sorting_columns = __this__sorting_columns;
      }

      this.file_offset = other.file_offset;
      this.total_compressed_size = other.total_compressed_size;
      this.ordinal = other.ordinal;
   }

   public RowGroup deepCopy() {
      return new RowGroup(this);
   }

   public void clear() {
      this.columns = null;
      this.setTotal_byte_sizeIsSet(false);
      this.total_byte_size = 0L;
      this.setNum_rowsIsSet(false);
      this.num_rows = 0L;
      this.sorting_columns = null;
      this.setFile_offsetIsSet(false);
      this.file_offset = 0L;
      this.setTotal_compressed_sizeIsSet(false);
      this.total_compressed_size = 0L;
      this.setOrdinalIsSet(false);
      this.ordinal = 0;
   }

   public int getColumnsSize() {
      return this.columns == null ? 0 : this.columns.size();
   }

   @Nullable
   public Iterator getColumnsIterator() {
      return this.columns == null ? null : this.columns.iterator();
   }

   public void addToColumns(ColumnChunk elem) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(elem);
   }

   @Nullable
   public List getColumns() {
      return this.columns;
   }

   public RowGroup setColumns(@Nullable List columns) {
      this.columns = columns;
      return this;
   }

   public void unsetColumns() {
      this.columns = null;
   }

   public boolean isSetColumns() {
      return this.columns != null;
   }

   public void setColumnsIsSet(boolean value) {
      if (!value) {
         this.columns = null;
      }

   }

   public long getTotal_byte_size() {
      return this.total_byte_size;
   }

   public RowGroup setTotal_byte_size(long total_byte_size) {
      this.total_byte_size = total_byte_size;
      this.setTotal_byte_sizeIsSet(true);
      return this;
   }

   public void unsetTotal_byte_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetTotal_byte_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setTotal_byte_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public long getNum_rows() {
      return this.num_rows;
   }

   public RowGroup setNum_rows(long num_rows) {
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

   public int getSorting_columnsSize() {
      return this.sorting_columns == null ? 0 : this.sorting_columns.size();
   }

   @Nullable
   public Iterator getSorting_columnsIterator() {
      return this.sorting_columns == null ? null : this.sorting_columns.iterator();
   }

   public void addToSorting_columns(SortingColumn elem) {
      if (this.sorting_columns == null) {
         this.sorting_columns = new ArrayList();
      }

      this.sorting_columns.add(elem);
   }

   @Nullable
   public List getSorting_columns() {
      return this.sorting_columns;
   }

   public RowGroup setSorting_columns(@Nullable List sorting_columns) {
      this.sorting_columns = sorting_columns;
      return this;
   }

   public void unsetSorting_columns() {
      this.sorting_columns = null;
   }

   public boolean isSetSorting_columns() {
      return this.sorting_columns != null;
   }

   public void setSorting_columnsIsSet(boolean value) {
      if (!value) {
         this.sorting_columns = null;
      }

   }

   public long getFile_offset() {
      return this.file_offset;
   }

   public RowGroup setFile_offset(long file_offset) {
      this.file_offset = file_offset;
      this.setFile_offsetIsSet(true);
      return this;
   }

   public void unsetFile_offset() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 2);
   }

   public boolean isSetFile_offset() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 2);
   }

   public void setFile_offsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 2, value);
   }

   public long getTotal_compressed_size() {
      return this.total_compressed_size;
   }

   public RowGroup setTotal_compressed_size(long total_compressed_size) {
      this.total_compressed_size = total_compressed_size;
      this.setTotal_compressed_sizeIsSet(true);
      return this;
   }

   public void unsetTotal_compressed_size() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 3);
   }

   public boolean isSetTotal_compressed_size() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 3);
   }

   public void setTotal_compressed_sizeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 3, value);
   }

   public short getOrdinal() {
      return this.ordinal;
   }

   public RowGroup setOrdinal(short ordinal) {
      this.ordinal = ordinal;
      this.setOrdinalIsSet(true);
      return this;
   }

   public void unsetOrdinal() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 4);
   }

   public boolean isSetOrdinal() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 4);
   }

   public void setOrdinalIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 4, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COLUMNS:
            if (value == null) {
               this.unsetColumns();
            } else {
               this.setColumns((List)value);
            }
            break;
         case TOTAL_BYTE_SIZE:
            if (value == null) {
               this.unsetTotal_byte_size();
            } else {
               this.setTotal_byte_size((Long)value);
            }
            break;
         case NUM_ROWS:
            if (value == null) {
               this.unsetNum_rows();
            } else {
               this.setNum_rows((Long)value);
            }
            break;
         case SORTING_COLUMNS:
            if (value == null) {
               this.unsetSorting_columns();
            } else {
               this.setSorting_columns((List)value);
            }
            break;
         case FILE_OFFSET:
            if (value == null) {
               this.unsetFile_offset();
            } else {
               this.setFile_offset((Long)value);
            }
            break;
         case TOTAL_COMPRESSED_SIZE:
            if (value == null) {
               this.unsetTotal_compressed_size();
            } else {
               this.setTotal_compressed_size((Long)value);
            }
            break;
         case ORDINAL:
            if (value == null) {
               this.unsetOrdinal();
            } else {
               this.setOrdinal((Short)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COLUMNS:
            return this.getColumns();
         case TOTAL_BYTE_SIZE:
            return this.getTotal_byte_size();
         case NUM_ROWS:
            return this.getNum_rows();
         case SORTING_COLUMNS:
            return this.getSorting_columns();
         case FILE_OFFSET:
            return this.getFile_offset();
         case TOTAL_COMPRESSED_SIZE:
            return this.getTotal_compressed_size();
         case ORDINAL:
            return this.getOrdinal();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COLUMNS:
               return this.isSetColumns();
            case TOTAL_BYTE_SIZE:
               return this.isSetTotal_byte_size();
            case NUM_ROWS:
               return this.isSetNum_rows();
            case SORTING_COLUMNS:
               return this.isSetSorting_columns();
            case FILE_OFFSET:
               return this.isSetFile_offset();
            case TOTAL_COMPRESSED_SIZE:
               return this.isSetTotal_compressed_size();
            case ORDINAL:
               return this.isSetOrdinal();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof RowGroup ? this.equals((RowGroup)that) : false;
   }

   public boolean equals(RowGroup that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_columns = this.isSetColumns();
         boolean that_present_columns = that.isSetColumns();
         if (this_present_columns || that_present_columns) {
            if (!this_present_columns || !that_present_columns) {
               return false;
            }

            if (!this.columns.equals(that.columns)) {
               return false;
            }
         }

         boolean this_present_total_byte_size = true;
         boolean that_present_total_byte_size = true;
         if (this_present_total_byte_size || that_present_total_byte_size) {
            if (!this_present_total_byte_size || !that_present_total_byte_size) {
               return false;
            }

            if (this.total_byte_size != that.total_byte_size) {
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

         boolean this_present_sorting_columns = this.isSetSorting_columns();
         boolean that_present_sorting_columns = that.isSetSorting_columns();
         if (this_present_sorting_columns || that_present_sorting_columns) {
            if (!this_present_sorting_columns || !that_present_sorting_columns) {
               return false;
            }

            if (!this.sorting_columns.equals(that.sorting_columns)) {
               return false;
            }
         }

         boolean this_present_file_offset = this.isSetFile_offset();
         boolean that_present_file_offset = that.isSetFile_offset();
         if (this_present_file_offset || that_present_file_offset) {
            if (!this_present_file_offset || !that_present_file_offset) {
               return false;
            }

            if (this.file_offset != that.file_offset) {
               return false;
            }
         }

         boolean this_present_total_compressed_size = this.isSetTotal_compressed_size();
         boolean that_present_total_compressed_size = that.isSetTotal_compressed_size();
         if (this_present_total_compressed_size || that_present_total_compressed_size) {
            if (!this_present_total_compressed_size || !that_present_total_compressed_size) {
               return false;
            }

            if (this.total_compressed_size != that.total_compressed_size) {
               return false;
            }
         }

         boolean this_present_ordinal = this.isSetOrdinal();
         boolean that_present_ordinal = that.isSetOrdinal();
         if (this_present_ordinal || that_present_ordinal) {
            if (!this_present_ordinal || !that_present_ordinal) {
               return false;
            }

            if (this.ordinal != that.ordinal) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetColumns() ? 131071 : 524287);
      if (this.isSetColumns()) {
         hashCode = hashCode * 8191 + this.columns.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.total_byte_size);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.num_rows);
      hashCode = hashCode * 8191 + (this.isSetSorting_columns() ? 131071 : 524287);
      if (this.isSetSorting_columns()) {
         hashCode = hashCode * 8191 + this.sorting_columns.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetFile_offset() ? 131071 : 524287);
      if (this.isSetFile_offset()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.file_offset);
      }

      hashCode = hashCode * 8191 + (this.isSetTotal_compressed_size() ? 131071 : 524287);
      if (this.isSetTotal_compressed_size()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.total_compressed_size);
      }

      hashCode = hashCode * 8191 + (this.isSetOrdinal() ? 131071 : 524287);
      if (this.isSetOrdinal()) {
         hashCode = hashCode * 8191 + this.ordinal;
      }

      return hashCode;
   }

   public int compareTo(RowGroup other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColumns(), other.isSetColumns());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColumns()) {
               lastComparison = TBaseHelper.compareTo(this.columns, other.columns);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTotal_byte_size(), other.isSetTotal_byte_size());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTotal_byte_size()) {
                  lastComparison = TBaseHelper.compareTo(this.total_byte_size, other.total_byte_size);
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

                  lastComparison = Boolean.compare(this.isSetSorting_columns(), other.isSetSorting_columns());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetSorting_columns()) {
                        lastComparison = TBaseHelper.compareTo(this.sorting_columns, other.sorting_columns);
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

                           lastComparison = Boolean.compare(this.isSetOrdinal(), other.isSetOrdinal());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetOrdinal()) {
                                 lastComparison = TBaseHelper.compareTo(this.ordinal, other.ordinal);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return RowGroup._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("RowGroup(");
      boolean first = true;
      sb.append("columns:");
      if (this.columns == null) {
         sb.append("null");
      } else {
         sb.append(this.columns);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("total_byte_size:");
      sb.append(this.total_byte_size);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("num_rows:");
      sb.append(this.num_rows);
      first = false;
      if (this.isSetSorting_columns()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("sorting_columns:");
         if (this.sorting_columns == null) {
            sb.append("null");
         } else {
            sb.append(this.sorting_columns);
         }

         first = false;
      }

      if (this.isSetFile_offset()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("file_offset:");
         sb.append(this.file_offset);
         first = false;
      }

      if (this.isSetTotal_compressed_size()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("total_compressed_size:");
         sb.append(this.total_compressed_size);
         first = false;
      }

      if (this.isSetOrdinal()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("ordinal:");
         sb.append(this.ordinal);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.columns == null) {
         throw new TProtocolException("Required field 'columns' was not present! Struct: " + this.toString());
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
      optionals = new _Fields[]{RowGroup._Fields.SORTING_COLUMNS, RowGroup._Fields.FILE_OFFSET, RowGroup._Fields.TOTAL_COMPRESSED_SIZE, RowGroup._Fields.ORDINAL};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(RowGroup._Fields.COLUMNS, new FieldMetaData("columns", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnChunk.class))));
      tmpMap.put(RowGroup._Fields.TOTAL_BYTE_SIZE, new FieldMetaData("total_byte_size", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(RowGroup._Fields.NUM_ROWS, new FieldMetaData("num_rows", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(RowGroup._Fields.SORTING_COLUMNS, new FieldMetaData("sorting_columns", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, SortingColumn.class))));
      tmpMap.put(RowGroup._Fields.FILE_OFFSET, new FieldMetaData("file_offset", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(RowGroup._Fields.TOTAL_COMPRESSED_SIZE, new FieldMetaData("total_compressed_size", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(RowGroup._Fields.ORDINAL, new FieldMetaData("ordinal", (byte)2, new FieldValueMetaData((byte)6)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(RowGroup.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COLUMNS((short)1, "columns"),
      TOTAL_BYTE_SIZE((short)2, "total_byte_size"),
      NUM_ROWS((short)3, "num_rows"),
      SORTING_COLUMNS((short)4, "sorting_columns"),
      FILE_OFFSET((short)5, "file_offset"),
      TOTAL_COMPRESSED_SIZE((short)6, "total_compressed_size"),
      ORDINAL((short)7, "ordinal");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COLUMNS;
            case 2:
               return TOTAL_BYTE_SIZE;
            case 3:
               return NUM_ROWS;
            case 4:
               return SORTING_COLUMNS;
            case 5:
               return FILE_OFFSET;
            case 6:
               return TOTAL_COMPRESSED_SIZE;
            case 7:
               return ORDINAL;
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

   private static class RowGroupStandardSchemeFactory implements SchemeFactory {
      private RowGroupStandardSchemeFactory() {
      }

      public RowGroupStandardScheme getScheme() {
         return new RowGroupStandardScheme();
      }
   }

   private static class RowGroupStandardScheme extends StandardScheme {
      private RowGroupStandardScheme() {
      }

      public void read(TProtocol iprot, RowGroup struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetTotal_byte_size()) {
                  throw new TProtocolException("Required field 'total_byte_size' was not found in serialized data! Struct: " + this.toString());
               }

               if (!struct.isSetNum_rows()) {
                  throw new TProtocolException("Required field 'num_rows' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list56 = iprot.readListBegin();
                  struct.columns = new ArrayList(_list56.size);

                  for(int _i58 = 0; _i58 < _list56.size; ++_i58) {
                     ColumnChunk _elem57 = new ColumnChunk();
                     _elem57.read(iprot);
                     struct.columns.add(_elem57);
                  }

                  iprot.readListEnd();
                  struct.setColumnsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 10) {
                     struct.total_byte_size = iprot.readI64();
                     struct.setTotal_byte_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
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

                  TList _list59 = iprot.readListBegin();
                  struct.sorting_columns = new ArrayList(_list59.size);

                  for(int _i61 = 0; _i61 < _list59.size; ++_i61) {
                     SortingColumn _elem60 = new SortingColumn();
                     _elem60.read(iprot);
                     struct.sorting_columns.add(_elem60);
                  }

                  iprot.readListEnd();
                  struct.setSorting_columnsIsSet(true);
                  break;
               case 5:
                  if (schemeField.type == 10) {
                     struct.file_offset = iprot.readI64();
                     struct.setFile_offsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 10) {
                     struct.total_compressed_size = iprot.readI64();
                     struct.setTotal_compressed_sizeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 6) {
                     struct.ordinal = iprot.readI16();
                     struct.setOrdinalIsSet(true);
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

      public void write(TProtocol oprot, RowGroup struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(RowGroup.STRUCT_DESC);
         if (struct.columns != null) {
            oprot.writeFieldBegin(RowGroup.COLUMNS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.columns.size()));

            for(ColumnChunk _iter62 : struct.columns) {
               _iter62.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(RowGroup.TOTAL_BYTE_SIZE_FIELD_DESC);
         oprot.writeI64(struct.total_byte_size);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(RowGroup.NUM_ROWS_FIELD_DESC);
         oprot.writeI64(struct.num_rows);
         oprot.writeFieldEnd();
         if (struct.sorting_columns != null && struct.isSetSorting_columns()) {
            oprot.writeFieldBegin(RowGroup.SORTING_COLUMNS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.sorting_columns.size()));

            for(SortingColumn _iter63 : struct.sorting_columns) {
               _iter63.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetFile_offset()) {
            oprot.writeFieldBegin(RowGroup.FILE_OFFSET_FIELD_DESC);
            oprot.writeI64(struct.file_offset);
            oprot.writeFieldEnd();
         }

         if (struct.isSetTotal_compressed_size()) {
            oprot.writeFieldBegin(RowGroup.TOTAL_COMPRESSED_SIZE_FIELD_DESC);
            oprot.writeI64(struct.total_compressed_size);
            oprot.writeFieldEnd();
         }

         if (struct.isSetOrdinal()) {
            oprot.writeFieldBegin(RowGroup.ORDINAL_FIELD_DESC);
            oprot.writeI16(struct.ordinal);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class RowGroupTupleSchemeFactory implements SchemeFactory {
      private RowGroupTupleSchemeFactory() {
      }

      public RowGroupTupleScheme getScheme() {
         return new RowGroupTupleScheme();
      }
   }

   private static class RowGroupTupleScheme extends TupleScheme {
      private RowGroupTupleScheme() {
      }

      public void write(TProtocol prot, RowGroup struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.columns.size());

         for(ColumnChunk _iter64 : struct.columns) {
            _iter64.write(oprot);
         }

         oprot.writeI64(struct.total_byte_size);
         oprot.writeI64(struct.num_rows);
         BitSet optionals = new BitSet();
         if (struct.isSetSorting_columns()) {
            optionals.set(0);
         }

         if (struct.isSetFile_offset()) {
            optionals.set(1);
         }

         if (struct.isSetTotal_compressed_size()) {
            optionals.set(2);
         }

         if (struct.isSetOrdinal()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetSorting_columns()) {
            oprot.writeI32(struct.sorting_columns.size());

            for(SortingColumn _iter65 : struct.sorting_columns) {
               _iter65.write(oprot);
            }
         }

         if (struct.isSetFile_offset()) {
            oprot.writeI64(struct.file_offset);
         }

         if (struct.isSetTotal_compressed_size()) {
            oprot.writeI64(struct.total_compressed_size);
         }

         if (struct.isSetOrdinal()) {
            oprot.writeI16(struct.ordinal);
         }

      }

      public void read(TProtocol prot, RowGroup struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list66 = iprot.readListBegin((byte)12);
         struct.columns = new ArrayList(_list66.size);

         for(int _i68 = 0; _i68 < _list66.size; ++_i68) {
            ColumnChunk _elem67 = new ColumnChunk();
            _elem67.read(iprot);
            struct.columns.add(_elem67);
         }

         struct.setColumnsIsSet(true);
         struct.total_byte_size = iprot.readI64();
         struct.setTotal_byte_sizeIsSet(true);
         struct.num_rows = iprot.readI64();
         struct.setNum_rowsIsSet(true);
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            TList _list69 = iprot.readListBegin((byte)12);
            struct.sorting_columns = new ArrayList(_list69.size);

            for(int _i71 = 0; _i71 < _list69.size; ++_i71) {
               SortingColumn _elem70 = new SortingColumn();
               _elem70.read(iprot);
               struct.sorting_columns.add(_elem70);
            }

            struct.setSorting_columnsIsSet(true);
         }

         if (incoming.get(1)) {
            struct.file_offset = iprot.readI64();
            struct.setFile_offsetIsSet(true);
         }

         if (incoming.get(2)) {
            struct.total_compressed_size = iprot.readI64();
            struct.setTotal_compressed_sizeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.ordinal = iprot.readI16();
            struct.setOrdinalIsSet(true);
         }

      }
   }
}
