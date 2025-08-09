package org.apache.hadoop.hive.metastore.api;

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

public class SetPartitionsStatsRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SetPartitionsStatsRequest");
   private static final TField COL_STATS_FIELD_DESC = new TField("colStats", (byte)15, (short)1);
   private static final TField NEED_MERGE_FIELD_DESC = new TField("needMerge", (byte)2, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SetPartitionsStatsRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SetPartitionsStatsRequestTupleSchemeFactory();
   @Nullable
   private List colStats;
   private boolean needMerge;
   private static final int __NEEDMERGE_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public SetPartitionsStatsRequest() {
      this.__isset_bitfield = 0;
   }

   public SetPartitionsStatsRequest(List colStats) {
      this();
      this.colStats = colStats;
   }

   public SetPartitionsStatsRequest(SetPartitionsStatsRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetColStats()) {
         List<ColumnStatistics> __this__colStats = new ArrayList(other.colStats.size());

         for(ColumnStatistics other_element : other.colStats) {
            __this__colStats.add(new ColumnStatistics(other_element));
         }

         this.colStats = __this__colStats;
      }

      this.needMerge = other.needMerge;
   }

   public SetPartitionsStatsRequest deepCopy() {
      return new SetPartitionsStatsRequest(this);
   }

   public void clear() {
      this.colStats = null;
      this.setNeedMergeIsSet(false);
      this.needMerge = false;
   }

   public int getColStatsSize() {
      return this.colStats == null ? 0 : this.colStats.size();
   }

   @Nullable
   public Iterator getColStatsIterator() {
      return this.colStats == null ? null : this.colStats.iterator();
   }

   public void addToColStats(ColumnStatistics elem) {
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

   public boolean isNeedMerge() {
      return this.needMerge;
   }

   public void setNeedMerge(boolean needMerge) {
      this.needMerge = needMerge;
      this.setNeedMergeIsSet(true);
   }

   public void unsetNeedMerge() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetNeedMerge() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setNeedMergeIsSet(boolean value) {
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
         case NEED_MERGE:
            if (value == null) {
               this.unsetNeedMerge();
            } else {
               this.setNeedMerge((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COL_STATS:
            return this.getColStats();
         case NEED_MERGE:
            return this.isNeedMerge();
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
            case NEED_MERGE:
               return this.isSetNeedMerge();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SetPartitionsStatsRequest ? this.equals((SetPartitionsStatsRequest)that) : false;
   }

   public boolean equals(SetPartitionsStatsRequest that) {
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

         boolean this_present_needMerge = this.isSetNeedMerge();
         boolean that_present_needMerge = that.isSetNeedMerge();
         if (this_present_needMerge || that_present_needMerge) {
            if (!this_present_needMerge || !that_present_needMerge) {
               return false;
            }

            if (this.needMerge != that.needMerge) {
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

      hashCode = hashCode * 8191 + (this.isSetNeedMerge() ? 131071 : 524287);
      if (this.isSetNeedMerge()) {
         hashCode = hashCode * 8191 + (this.needMerge ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(SetPartitionsStatsRequest other) {
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

            lastComparison = Boolean.compare(this.isSetNeedMerge(), other.isSetNeedMerge());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetNeedMerge()) {
                  lastComparison = TBaseHelper.compareTo(this.needMerge, other.needMerge);
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
      return SetPartitionsStatsRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SetPartitionsStatsRequest(");
      boolean first = true;
      sb.append("colStats:");
      if (this.colStats == null) {
         sb.append("null");
      } else {
         sb.append(this.colStats);
      }

      first = false;
      if (this.isSetNeedMerge()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("needMerge:");
         sb.append(this.needMerge);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetColStats()) {
         throw new TProtocolException("Required field 'colStats' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{SetPartitionsStatsRequest._Fields.NEED_MERGE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(SetPartitionsStatsRequest._Fields.COL_STATS, new FieldMetaData("colStats", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnStatistics.class))));
      tmpMap.put(SetPartitionsStatsRequest._Fields.NEED_MERGE, new FieldMetaData("needMerge", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SetPartitionsStatsRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COL_STATS((short)1, "colStats"),
      NEED_MERGE((short)2, "needMerge");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COL_STATS;
            case 2:
               return NEED_MERGE;
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

   private static class SetPartitionsStatsRequestStandardSchemeFactory implements SchemeFactory {
      private SetPartitionsStatsRequestStandardSchemeFactory() {
      }

      public SetPartitionsStatsRequestStandardScheme getScheme() {
         return new SetPartitionsStatsRequestStandardScheme();
      }
   }

   private static class SetPartitionsStatsRequestStandardScheme extends StandardScheme {
      private SetPartitionsStatsRequestStandardScheme() {
      }

      public void read(TProtocol iprot, SetPartitionsStatsRequest struct) throws TException {
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

                  TList _list286 = iprot.readListBegin();
                  struct.colStats = new ArrayList(_list286.size);

                  for(int _i288 = 0; _i288 < _list286.size; ++_i288) {
                     ColumnStatistics _elem287 = new ColumnStatistics();
                     _elem287.read(iprot);
                     struct.colStats.add(_elem287);
                  }

                  iprot.readListEnd();
                  struct.setColStatsIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 2) {
                     struct.needMerge = iprot.readBool();
                     struct.setNeedMergeIsSet(true);
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

      public void write(TProtocol oprot, SetPartitionsStatsRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SetPartitionsStatsRequest.STRUCT_DESC);
         if (struct.colStats != null) {
            oprot.writeFieldBegin(SetPartitionsStatsRequest.COL_STATS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.colStats.size()));

            for(ColumnStatistics _iter289 : struct.colStats) {
               _iter289.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetNeedMerge()) {
            oprot.writeFieldBegin(SetPartitionsStatsRequest.NEED_MERGE_FIELD_DESC);
            oprot.writeBool(struct.needMerge);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SetPartitionsStatsRequestTupleSchemeFactory implements SchemeFactory {
      private SetPartitionsStatsRequestTupleSchemeFactory() {
      }

      public SetPartitionsStatsRequestTupleScheme getScheme() {
         return new SetPartitionsStatsRequestTupleScheme();
      }
   }

   private static class SetPartitionsStatsRequestTupleScheme extends TupleScheme {
      private SetPartitionsStatsRequestTupleScheme() {
      }

      public void write(TProtocol prot, SetPartitionsStatsRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.colStats.size());

         for(ColumnStatistics _iter290 : struct.colStats) {
            _iter290.write(oprot);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetNeedMerge()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetNeedMerge()) {
            oprot.writeBool(struct.needMerge);
         }

      }

      public void read(TProtocol prot, SetPartitionsStatsRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list291 = iprot.readListBegin((byte)12);
         struct.colStats = new ArrayList(_list291.size);

         for(int _i293 = 0; _i293 < _list291.size; ++_i293) {
            ColumnStatistics _elem292 = new ColumnStatistics();
            _elem292.read(iprot);
            struct.colStats.add(_elem292);
         }

         struct.setColStatsIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.needMerge = iprot.readBool();
            struct.setNeedMergeIsSet(true);
         }

      }
   }
}
