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

public class AggrStats implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("AggrStats");
   private static final TField COL_STATS_FIELD_DESC = new TField("colStats", (byte)15, (short)1);
   private static final TField PARTS_FOUND_FIELD_DESC = new TField("partsFound", (byte)10, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new AggrStatsStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new AggrStatsTupleSchemeFactory();
   @Nullable
   private List colStats;
   private long partsFound;
   private static final int __PARTSFOUND_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public AggrStats() {
      this.__isset_bitfield = 0;
   }

   public AggrStats(List colStats, long partsFound) {
      this();
      this.colStats = colStats;
      this.partsFound = partsFound;
      this.setPartsFoundIsSet(true);
   }

   public AggrStats(AggrStats other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetColStats()) {
         List<ColumnStatisticsObj> __this__colStats = new ArrayList(other.colStats.size());

         for(ColumnStatisticsObj other_element : other.colStats) {
            __this__colStats.add(new ColumnStatisticsObj(other_element));
         }

         this.colStats = __this__colStats;
      }

      this.partsFound = other.partsFound;
   }

   public AggrStats deepCopy() {
      return new AggrStats(this);
   }

   public void clear() {
      this.colStats = null;
      this.setPartsFoundIsSet(false);
      this.partsFound = 0L;
   }

   public int getColStatsSize() {
      return this.colStats == null ? 0 : this.colStats.size();
   }

   @Nullable
   public Iterator getColStatsIterator() {
      return this.colStats == null ? null : this.colStats.iterator();
   }

   public void addToColStats(ColumnStatisticsObj elem) {
      if (this.colStats == null) {
         this.colStats = new ArrayList();
      }

      this.colStats.add(elem);
   }

   @Nullable
   public List getColStats() {
      return this.colStats;
   }

   public void setColStats(@Nullable List colStats) {
      this.colStats = colStats;
   }

   public void unsetColStats() {
      this.colStats = null;
   }

   public boolean isSetColStats() {
      return this.colStats != null;
   }

   public void setColStatsIsSet(boolean value) {
      if (!value) {
         this.colStats = null;
      }

   }

   public long getPartsFound() {
      return this.partsFound;
   }

   public void setPartsFound(long partsFound) {
      this.partsFound = partsFound;
      this.setPartsFoundIsSet(true);
   }

   public void unsetPartsFound() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetPartsFound() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setPartsFoundIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COL_STATS:
            if (value == null) {
               this.unsetColStats();
            } else {
               this.setColStats((List)value);
            }
            break;
         case PARTS_FOUND:
            if (value == null) {
               this.unsetPartsFound();
            } else {
               this.setPartsFound((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COL_STATS:
            return this.getColStats();
         case PARTS_FOUND:
            return this.getPartsFound();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COL_STATS:
               return this.isSetColStats();
            case PARTS_FOUND:
               return this.isSetPartsFound();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof AggrStats ? this.equals((AggrStats)that) : false;
   }

   public boolean equals(AggrStats that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_colStats = this.isSetColStats();
         boolean that_present_colStats = that.isSetColStats();
         if (this_present_colStats || that_present_colStats) {
            if (!this_present_colStats || !that_present_colStats) {
               return false;
            }

            if (!this.colStats.equals(that.colStats)) {
               return false;
            }
         }

         boolean this_present_partsFound = true;
         boolean that_present_partsFound = true;
         if (this_present_partsFound || that_present_partsFound) {
            if (!this_present_partsFound || !that_present_partsFound) {
               return false;
            }

            if (this.partsFound != that.partsFound) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetColStats() ? 131071 : 524287);
      if (this.isSetColStats()) {
         hashCode = hashCode * 8191 + this.colStats.hashCode();
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.partsFound);
      return hashCode;
   }

   public int compareTo(AggrStats other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColStats(), other.isSetColStats());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColStats()) {
               lastComparison = TBaseHelper.compareTo(this.colStats, other.colStats);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetPartsFound(), other.isSetPartsFound());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetPartsFound()) {
                  lastComparison = TBaseHelper.compareTo(this.partsFound, other.partsFound);
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
      return AggrStats._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("AggrStats(");
      boolean first = true;
      sb.append("colStats:");
      if (this.colStats == null) {
         sb.append("null");
      } else {
         sb.append(this.colStats);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("partsFound:");
      sb.append(this.partsFound);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetColStats()) {
         throw new TProtocolException("Required field 'colStats' is unset! Struct:" + this.toString());
      } else if (!this.isSetPartsFound()) {
         throw new TProtocolException("Required field 'partsFound' is unset! Struct:" + this.toString());
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
      tmpMap.put(AggrStats._Fields.COL_STATS, new FieldMetaData("colStats", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnStatisticsObj.class))));
      tmpMap.put(AggrStats._Fields.PARTS_FOUND, new FieldMetaData("partsFound", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(AggrStats.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COL_STATS((short)1, "colStats"),
      PARTS_FOUND((short)2, "partsFound");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COL_STATS;
            case 2:
               return PARTS_FOUND;
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

   private static class AggrStatsStandardSchemeFactory implements SchemeFactory {
      private AggrStatsStandardSchemeFactory() {
      }

      public AggrStatsStandardScheme getScheme() {
         return new AggrStatsStandardScheme();
      }
   }

   private static class AggrStatsStandardScheme extends StandardScheme {
      private AggrStatsStandardScheme() {
      }

      public void read(TProtocol iprot, AggrStats struct) throws TException {
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

                  TList _list278 = iprot.readListBegin();
                  struct.colStats = new ArrayList(_list278.size);

                  for(int _i280 = 0; _i280 < _list278.size; ++_i280) {
                     ColumnStatisticsObj _elem279 = new ColumnStatisticsObj();
                     _elem279.read(iprot);
                     struct.colStats.add(_elem279);
                  }

                  iprot.readListEnd();
                  struct.setColStatsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 10) {
                     struct.partsFound = iprot.readI64();
                     struct.setPartsFoundIsSet(true);
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

      public void write(TProtocol oprot, AggrStats struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(AggrStats.STRUCT_DESC);
         if (struct.colStats != null) {
            oprot.writeFieldBegin(AggrStats.COL_STATS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.colStats.size()));

            for(ColumnStatisticsObj _iter281 : struct.colStats) {
               _iter281.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(AggrStats.PARTS_FOUND_FIELD_DESC);
         oprot.writeI64(struct.partsFound);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class AggrStatsTupleSchemeFactory implements SchemeFactory {
      private AggrStatsTupleSchemeFactory() {
      }

      public AggrStatsTupleScheme getScheme() {
         return new AggrStatsTupleScheme();
      }
   }

   private static class AggrStatsTupleScheme extends TupleScheme {
      private AggrStatsTupleScheme() {
      }

      public void write(TProtocol prot, AggrStats struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.colStats.size());

         for(ColumnStatisticsObj _iter282 : struct.colStats) {
            _iter282.write(oprot);
         }

         oprot.writeI64(struct.partsFound);
      }

      public void read(TProtocol prot, AggrStats struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list283 = iprot.readListBegin((byte)12);
         struct.colStats = new ArrayList(_list283.size);

         for(int _i285 = 0; _i285 < _list283.size; ++_i285) {
            ColumnStatisticsObj _elem284 = new ColumnStatisticsObj();
            _elem284.read(iprot);
            struct.colStats.add(_elem284);
         }

         struct.setColStatsIsSet(true);
         struct.partsFound = iprot.readI64();
         struct.setPartsFoundIsSet(true);
      }
   }
}
