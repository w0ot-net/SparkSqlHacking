package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TRowSet implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TRowSet");
   private static final TField START_ROW_OFFSET_FIELD_DESC = new TField("startRowOffset", (byte)10, (short)1);
   private static final TField ROWS_FIELD_DESC = new TField("rows", (byte)15, (short)2);
   private static final TField COLUMNS_FIELD_DESC = new TField("columns", (byte)15, (short)3);
   private static final TField BINARY_COLUMNS_FIELD_DESC = new TField("binaryColumns", (byte)11, (short)4);
   private static final TField COLUMN_COUNT_FIELD_DESC = new TField("columnCount", (byte)8, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TRowSetStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TRowSetTupleSchemeFactory();
   private long startRowOffset;
   @Nullable
   private List rows;
   @Nullable
   private List columns;
   @Nullable
   private ByteBuffer binaryColumns;
   private int columnCount;
   private static final int __STARTROWOFFSET_ISSET_ID = 0;
   private static final int __COLUMNCOUNT_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TRowSet() {
      this.__isset_bitfield = 0;
   }

   public TRowSet(long startRowOffset, List rows) {
      this();
      this.startRowOffset = startRowOffset;
      this.setStartRowOffsetIsSet(true);
      this.rows = rows;
   }

   public TRowSet(TRowSet other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.startRowOffset = other.startRowOffset;
      if (other.isSetRows()) {
         List<TRow> __this__rows = new ArrayList(other.rows.size());

         for(TRow other_element : other.rows) {
            __this__rows.add(new TRow(other_element));
         }

         this.rows = __this__rows;
      }

      if (other.isSetColumns()) {
         List<TColumn> __this__columns = new ArrayList(other.columns.size());

         for(TColumn other_element : other.columns) {
            __this__columns.add(new TColumn(other_element));
         }

         this.columns = __this__columns;
      }

      if (other.isSetBinaryColumns()) {
         this.binaryColumns = TBaseHelper.copyBinary(other.binaryColumns);
      }

      this.columnCount = other.columnCount;
   }

   public TRowSet deepCopy() {
      return new TRowSet(this);
   }

   public void clear() {
      this.setStartRowOffsetIsSet(false);
      this.startRowOffset = 0L;
      this.rows = null;
      this.columns = null;
      this.binaryColumns = null;
      this.setColumnCountIsSet(false);
      this.columnCount = 0;
   }

   public long getStartRowOffset() {
      return this.startRowOffset;
   }

   public void setStartRowOffset(long startRowOffset) {
      this.startRowOffset = startRowOffset;
      this.setStartRowOffsetIsSet(true);
   }

   public void unsetStartRowOffset() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetStartRowOffset() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setStartRowOffsetIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getRowsSize() {
      return this.rows == null ? 0 : this.rows.size();
   }

   @Nullable
   public Iterator getRowsIterator() {
      return this.rows == null ? null : this.rows.iterator();
   }

   public void addToRows(TRow elem) {
      if (this.rows == null) {
         this.rows = new ArrayList();
      }

      this.rows.add(elem);
   }

   @Nullable
   public List getRows() {
      return this.rows;
   }

   public void setRows(@Nullable List rows) {
      this.rows = rows;
   }

   public void unsetRows() {
      this.rows = null;
   }

   public boolean isSetRows() {
      return this.rows != null;
   }

   public void setRowsIsSet(boolean value) {
      if (!value) {
         this.rows = null;
      }

   }

   public int getColumnsSize() {
      return this.columns == null ? 0 : this.columns.size();
   }

   @Nullable
   public Iterator getColumnsIterator() {
      return this.columns == null ? null : this.columns.iterator();
   }

   public void addToColumns(TColumn elem) {
      if (this.columns == null) {
         this.columns = new ArrayList();
      }

      this.columns.add(elem);
   }

   @Nullable
   public List getColumns() {
      return this.columns;
   }

   public void setColumns(@Nullable List columns) {
      this.columns = columns;
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

   public byte[] getBinaryColumns() {
      this.setBinaryColumns(TBaseHelper.rightSize(this.binaryColumns));
      return this.binaryColumns == null ? null : this.binaryColumns.array();
   }

   public ByteBuffer bufferForBinaryColumns() {
      return TBaseHelper.copyBinary(this.binaryColumns);
   }

   public void setBinaryColumns(byte[] binaryColumns) {
      this.binaryColumns = binaryColumns == null ? (ByteBuffer)null : ByteBuffer.wrap((byte[])(([B)binaryColumns).clone());
   }

   public void setBinaryColumns(@Nullable ByteBuffer binaryColumns) {
      this.binaryColumns = TBaseHelper.copyBinary(binaryColumns);
   }

   public void unsetBinaryColumns() {
      this.binaryColumns = null;
   }

   public boolean isSetBinaryColumns() {
      return this.binaryColumns != null;
   }

   public void setBinaryColumnsIsSet(boolean value) {
      if (!value) {
         this.binaryColumns = null;
      }

   }

   public int getColumnCount() {
      return this.columnCount;
   }

   public void setColumnCount(int columnCount) {
      this.columnCount = columnCount;
      this.setColumnCountIsSet(true);
   }

   public void unsetColumnCount() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetColumnCount() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setColumnCountIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case START_ROW_OFFSET:
            if (value == null) {
               this.unsetStartRowOffset();
            } else {
               this.setStartRowOffset((Long)value);
            }
            break;
         case ROWS:
            if (value == null) {
               this.unsetRows();
            } else {
               this.setRows((List)value);
            }
            break;
         case COLUMNS:
            if (value == null) {
               this.unsetColumns();
            } else {
               this.setColumns((List)value);
            }
            break;
         case BINARY_COLUMNS:
            if (value == null) {
               this.unsetBinaryColumns();
            } else if (value instanceof byte[]) {
               this.setBinaryColumns((byte[])value);
            } else {
               this.setBinaryColumns((ByteBuffer)value);
            }
            break;
         case COLUMN_COUNT:
            if (value == null) {
               this.unsetColumnCount();
            } else {
               this.setColumnCount((Integer)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case START_ROW_OFFSET:
            return this.getStartRowOffset();
         case ROWS:
            return this.getRows();
         case COLUMNS:
            return this.getColumns();
         case BINARY_COLUMNS:
            return this.getBinaryColumns();
         case COLUMN_COUNT:
            return this.getColumnCount();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case START_ROW_OFFSET:
               return this.isSetStartRowOffset();
            case ROWS:
               return this.isSetRows();
            case COLUMNS:
               return this.isSetColumns();
            case BINARY_COLUMNS:
               return this.isSetBinaryColumns();
            case COLUMN_COUNT:
               return this.isSetColumnCount();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TRowSet ? this.equals((TRowSet)that) : false;
   }

   public boolean equals(TRowSet that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_startRowOffset = true;
         boolean that_present_startRowOffset = true;
         if (this_present_startRowOffset || that_present_startRowOffset) {
            if (!this_present_startRowOffset || !that_present_startRowOffset) {
               return false;
            }

            if (this.startRowOffset != that.startRowOffset) {
               return false;
            }
         }

         boolean this_present_rows = this.isSetRows();
         boolean that_present_rows = that.isSetRows();
         if (this_present_rows || that_present_rows) {
            if (!this_present_rows || !that_present_rows) {
               return false;
            }

            if (!this.rows.equals(that.rows)) {
               return false;
            }
         }

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

         boolean this_present_binaryColumns = this.isSetBinaryColumns();
         boolean that_present_binaryColumns = that.isSetBinaryColumns();
         if (this_present_binaryColumns || that_present_binaryColumns) {
            if (!this_present_binaryColumns || !that_present_binaryColumns) {
               return false;
            }

            if (!this.binaryColumns.equals(that.binaryColumns)) {
               return false;
            }
         }

         boolean this_present_columnCount = this.isSetColumnCount();
         boolean that_present_columnCount = that.isSetColumnCount();
         if (this_present_columnCount || that_present_columnCount) {
            if (!this_present_columnCount || !that_present_columnCount) {
               return false;
            }

            if (this.columnCount != that.columnCount) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.startRowOffset);
      hashCode = hashCode * 8191 + (this.isSetRows() ? 131071 : 524287);
      if (this.isSetRows()) {
         hashCode = hashCode * 8191 + this.rows.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColumns() ? 131071 : 524287);
      if (this.isSetColumns()) {
         hashCode = hashCode * 8191 + this.columns.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetBinaryColumns() ? 131071 : 524287);
      if (this.isSetBinaryColumns()) {
         hashCode = hashCode * 8191 + this.binaryColumns.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColumnCount() ? 131071 : 524287);
      if (this.isSetColumnCount()) {
         hashCode = hashCode * 8191 + this.columnCount;
      }

      return hashCode;
   }

   public int compareTo(TRowSet other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetStartRowOffset(), other.isSetStartRowOffset());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetStartRowOffset()) {
               lastComparison = TBaseHelper.compareTo(this.startRowOffset, other.startRowOffset);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetRows(), other.isSetRows());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetRows()) {
                  lastComparison = TBaseHelper.compareTo(this.rows, other.rows);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

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

                  lastComparison = Boolean.compare(this.isSetBinaryColumns(), other.isSetBinaryColumns());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetBinaryColumns()) {
                        lastComparison = TBaseHelper.compareTo(this.binaryColumns, other.binaryColumns);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetColumnCount(), other.isSetColumnCount());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetColumnCount()) {
                           lastComparison = TBaseHelper.compareTo(this.columnCount, other.columnCount);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TRowSet._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TRowSet(");
      boolean first = true;
      sb.append("startRowOffset:");
      sb.append(this.startRowOffset);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("rows:");
      if (this.rows == null) {
         sb.append("null");
      } else {
         sb.append(this.rows);
      }

      first = false;
      if (this.isSetColumns()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("columns:");
         if (this.columns == null) {
            sb.append("null");
         } else {
            sb.append(this.columns);
         }

         first = false;
      }

      if (this.isSetBinaryColumns()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("binaryColumns:");
         if (this.binaryColumns == null) {
            sb.append("null");
         } else {
            TBaseHelper.toString(this.binaryColumns, sb);
         }

         first = false;
      }

      if (this.isSetColumnCount()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("columnCount:");
         sb.append(this.columnCount);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetStartRowOffset()) {
         throw new TProtocolException("Required field 'startRowOffset' is unset! Struct:" + this.toString());
      } else if (!this.isSetRows()) {
         throw new TProtocolException("Required field 'rows' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TRowSet._Fields.COLUMNS, TRowSet._Fields.BINARY_COLUMNS, TRowSet._Fields.COLUMN_COUNT};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TRowSet._Fields.START_ROW_OFFSET, new FieldMetaData("startRowOffset", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(TRowSet._Fields.ROWS, new FieldMetaData("rows", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, TRow.class))));
      tmpMap.put(TRowSet._Fields.COLUMNS, new FieldMetaData("columns", (byte)2, new ListMetaData((byte)15, new StructMetaData((byte)12, TColumn.class))));
      tmpMap.put(TRowSet._Fields.BINARY_COLUMNS, new FieldMetaData("binaryColumns", (byte)2, new FieldValueMetaData((byte)11, true)));
      tmpMap.put(TRowSet._Fields.COLUMN_COUNT, new FieldMetaData("columnCount", (byte)2, new FieldValueMetaData((byte)8)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TRowSet.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      START_ROW_OFFSET((short)1, "startRowOffset"),
      ROWS((short)2, "rows"),
      COLUMNS((short)3, "columns"),
      BINARY_COLUMNS((short)4, "binaryColumns"),
      COLUMN_COUNT((short)5, "columnCount");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return START_ROW_OFFSET;
            case 2:
               return ROWS;
            case 3:
               return COLUMNS;
            case 4:
               return BINARY_COLUMNS;
            case 5:
               return COLUMN_COUNT;
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

   private static class TRowSetStandardSchemeFactory implements SchemeFactory {
      private TRowSetStandardSchemeFactory() {
      }

      public TRowSetStandardScheme getScheme() {
         return new TRowSetStandardScheme();
      }
   }

   private static class TRowSetStandardScheme extends StandardScheme {
      private TRowSetStandardScheme() {
      }

      public void read(TProtocol iprot, TRowSet struct) throws TException {
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
                  if (schemeField.type == 10) {
                     struct.startRowOffset = iprot.readI64();
                     struct.setStartRowOffsetIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list118 = iprot.readListBegin();
                  struct.rows = new ArrayList(_list118.size);

                  for(int _i120 = 0; _i120 < _list118.size; ++_i120) {
                     TRow _elem119 = new TRow();
                     _elem119.read(iprot);
                     struct.rows.add(_elem119);
                  }

                  iprot.readListEnd();
                  struct.setRowsIsSet(true);
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list121 = iprot.readListBegin();
                  struct.columns = new ArrayList(_list121.size);

                  for(int _i123 = 0; _i123 < _list121.size; ++_i123) {
                     TColumn _elem122 = new TColumn();
                     _elem122.read(iprot);
                     struct.columns.add(_elem122);
                  }

                  iprot.readListEnd();
                  struct.setColumnsIsSet(true);
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.binaryColumns = iprot.readBinary();
                     struct.setBinaryColumnsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.columnCount = iprot.readI32();
                     struct.setColumnCountIsSet(true);
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

      public void write(TProtocol oprot, TRowSet struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TRowSet.STRUCT_DESC);
         oprot.writeFieldBegin(TRowSet.START_ROW_OFFSET_FIELD_DESC);
         oprot.writeI64(struct.startRowOffset);
         oprot.writeFieldEnd();
         if (struct.rows != null) {
            oprot.writeFieldBegin(TRowSet.ROWS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.rows.size()));

            for(TRow _iter124 : struct.rows) {
               _iter124.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.columns != null && struct.isSetColumns()) {
            oprot.writeFieldBegin(TRowSet.COLUMNS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.columns.size()));

            for(TColumn _iter125 : struct.columns) {
               _iter125.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.binaryColumns != null && struct.isSetBinaryColumns()) {
            oprot.writeFieldBegin(TRowSet.BINARY_COLUMNS_FIELD_DESC);
            oprot.writeBinary(struct.binaryColumns);
            oprot.writeFieldEnd();
         }

         if (struct.isSetColumnCount()) {
            oprot.writeFieldBegin(TRowSet.COLUMN_COUNT_FIELD_DESC);
            oprot.writeI32(struct.columnCount);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TRowSetTupleSchemeFactory implements SchemeFactory {
      private TRowSetTupleSchemeFactory() {
      }

      public TRowSetTupleScheme getScheme() {
         return new TRowSetTupleScheme();
      }
   }

   private static class TRowSetTupleScheme extends TupleScheme {
      private TRowSetTupleScheme() {
      }

      public void write(TProtocol prot, TRowSet struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.startRowOffset);
         oprot.writeI32(struct.rows.size());

         for(TRow _iter126 : struct.rows) {
            _iter126.write(oprot);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetColumns()) {
            optionals.set(0);
         }

         if (struct.isSetBinaryColumns()) {
            optionals.set(1);
         }

         if (struct.isSetColumnCount()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetColumns()) {
            oprot.writeI32(struct.columns.size());

            for(TColumn _iter127 : struct.columns) {
               _iter127.write(oprot);
            }
         }

         if (struct.isSetBinaryColumns()) {
            oprot.writeBinary(struct.binaryColumns);
         }

         if (struct.isSetColumnCount()) {
            oprot.writeI32(struct.columnCount);
         }

      }

      public void read(TProtocol prot, TRowSet struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.startRowOffset = iprot.readI64();
         struct.setStartRowOffsetIsSet(true);
         TList _list128 = iprot.readListBegin((byte)12);
         struct.rows = new ArrayList(_list128.size);

         for(int _i130 = 0; _i130 < _list128.size; ++_i130) {
            TRow _elem129 = new TRow();
            _elem129.read(iprot);
            struct.rows.add(_elem129);
         }

         struct.setRowsIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            TList _list131 = iprot.readListBegin((byte)12);
            struct.columns = new ArrayList(_list131.size);

            for(int _i133 = 0; _i133 < _list131.size; ++_i133) {
               TColumn _elem132 = new TColumn();
               _elem132.read(iprot);
               struct.columns.add(_elem132);
            }

            struct.setColumnsIsSet(true);
         }

         if (incoming.get(1)) {
            struct.binaryColumns = iprot.readBinary();
            struct.setBinaryColumnsIsSet(true);
         }

         if (incoming.get(2)) {
            struct.columnCount = iprot.readI32();
            struct.setColumnCountIsSet(true);
         }

      }
   }
}
