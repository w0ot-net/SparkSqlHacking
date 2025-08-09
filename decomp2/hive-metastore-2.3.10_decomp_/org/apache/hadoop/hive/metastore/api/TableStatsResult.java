package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
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

public class TableStatsResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TableStatsResult");
   private static final TField TABLE_STATS_FIELD_DESC = new TField("tableStats", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TableStatsResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TableStatsResultTupleSchemeFactory();
   @Nullable
   private List tableStats;
   public static final Map metaDataMap;

   public TableStatsResult() {
   }

   public TableStatsResult(List tableStats) {
      this();
      this.tableStats = tableStats;
   }

   public TableStatsResult(TableStatsResult other) {
      if (other.isSetTableStats()) {
         List<ColumnStatisticsObj> __this__tableStats = new ArrayList(other.tableStats.size());

         for(ColumnStatisticsObj other_element : other.tableStats) {
            __this__tableStats.add(new ColumnStatisticsObj(other_element));
         }

         this.tableStats = __this__tableStats;
      }

   }

   public TableStatsResult deepCopy() {
      return new TableStatsResult(this);
   }

   public void clear() {
      this.tableStats = null;
   }

   public int getTableStatsSize() {
      return this.tableStats == null ? 0 : this.tableStats.size();
   }

   @Nullable
   public Iterator getTableStatsIterator() {
      return this.tableStats == null ? null : this.tableStats.iterator();
   }

   public void addToTableStats(ColumnStatisticsObj elem) {
      if (this.tableStats == null) {
         this.tableStats = new ArrayList();
      }

      this.tableStats.add(elem);
   }

   @Nullable
   public List getTableStats() {
      return this.tableStats;
   }

   public void setTableStats(@Nullable List tableStats) {
      this.tableStats = tableStats;
   }

   public void unsetTableStats() {
      this.tableStats = null;
   }

   public boolean isSetTableStats() {
      return this.tableStats != null;
   }

   public void setTableStatsIsSet(boolean value) {
      if (!value) {
         this.tableStats = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TABLE_STATS:
            if (value == null) {
               this.unsetTableStats();
            } else {
               this.setTableStats((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TABLE_STATS:
            return this.getTableStats();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TABLE_STATS:
               return this.isSetTableStats();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TableStatsResult ? this.equals((TableStatsResult)that) : false;
   }

   public boolean equals(TableStatsResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_tableStats = this.isSetTableStats();
         boolean that_present_tableStats = that.isSetTableStats();
         if (this_present_tableStats || that_present_tableStats) {
            if (!this_present_tableStats || !that_present_tableStats) {
               return false;
            }

            if (!this.tableStats.equals(that.tableStats)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetTableStats() ? 131071 : 524287);
      if (this.isSetTableStats()) {
         hashCode = hashCode * 8191 + this.tableStats.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TableStatsResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTableStats(), other.isSetTableStats());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTableStats()) {
               lastComparison = TBaseHelper.compareTo(this.tableStats, other.tableStats);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TableStatsResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TableStatsResult(");
      boolean first = true;
      sb.append("tableStats:");
      if (this.tableStats == null) {
         sb.append("null");
      } else {
         sb.append(this.tableStats);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTableStats()) {
         throw new TProtocolException("Required field 'tableStats' is unset! Struct:" + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TableStatsResult._Fields.TABLE_STATS, new FieldMetaData("tableStats", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnStatisticsObj.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TableStatsResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TABLE_STATS((short)1, "tableStats");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TABLE_STATS;
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

   private static class TableStatsResultStandardSchemeFactory implements SchemeFactory {
      private TableStatsResultStandardSchemeFactory() {
      }

      public TableStatsResultStandardScheme getScheme() {
         return new TableStatsResultStandardScheme();
      }
   }

   private static class TableStatsResultStandardScheme extends StandardScheme {
      private TableStatsResultStandardScheme() {
      }

      public void read(TProtocol iprot, TableStatsResult struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list362 = iprot.readListBegin();
                  struct.tableStats = new ArrayList(_list362.size);

                  for(int _i364 = 0; _i364 < _list362.size; ++_i364) {
                     ColumnStatisticsObj _elem363 = new ColumnStatisticsObj();
                     _elem363.read(iprot);
                     struct.tableStats.add(_elem363);
                  }

                  iprot.readListEnd();
                  struct.setTableStatsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TableStatsResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TableStatsResult.STRUCT_DESC);
         if (struct.tableStats != null) {
            oprot.writeFieldBegin(TableStatsResult.TABLE_STATS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.tableStats.size()));

            for(ColumnStatisticsObj _iter365 : struct.tableStats) {
               _iter365.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TableStatsResultTupleSchemeFactory implements SchemeFactory {
      private TableStatsResultTupleSchemeFactory() {
      }

      public TableStatsResultTupleScheme getScheme() {
         return new TableStatsResultTupleScheme();
      }
   }

   private static class TableStatsResultTupleScheme extends TupleScheme {
      private TableStatsResultTupleScheme() {
      }

      public void write(TProtocol prot, TableStatsResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.tableStats.size());

         for(ColumnStatisticsObj _iter366 : struct.tableStats) {
            _iter366.write(oprot);
         }

      }

      public void read(TProtocol prot, TableStatsResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list367 = iprot.readListBegin((byte)12);
         struct.tableStats = new ArrayList(_list367.size);

         for(int _i369 = 0; _i369 < _list367.size; ++_i369) {
            ColumnStatisticsObj _elem368 = new ColumnStatisticsObj();
            _elem368.read(iprot);
            struct.tableStats.add(_elem368);
         }

         struct.setTableStatsIsSet(true);
      }
   }
}
