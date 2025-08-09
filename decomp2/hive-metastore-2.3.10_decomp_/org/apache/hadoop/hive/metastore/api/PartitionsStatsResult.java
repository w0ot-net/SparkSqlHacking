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
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
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

public class PartitionsStatsResult implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionsStatsResult");
   private static final TField PART_STATS_FIELD_DESC = new TField("partStats", (byte)13, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionsStatsResultStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionsStatsResultTupleSchemeFactory();
   @Nullable
   private Map partStats;
   public static final Map metaDataMap;

   public PartitionsStatsResult() {
   }

   public PartitionsStatsResult(Map partStats) {
      this();
      this.partStats = partStats;
   }

   public PartitionsStatsResult(PartitionsStatsResult other) {
      if (other.isSetPartStats()) {
         Map<String, List<ColumnStatisticsObj>> __this__partStats = new HashMap(other.partStats.size());

         for(Map.Entry other_element : other.partStats.entrySet()) {
            String other_element_key = (String)other_element.getKey();
            List<ColumnStatisticsObj> other_element_value = (List)other_element.getValue();
            List<ColumnStatisticsObj> __this__partStats_copy_value = new ArrayList(other_element_value.size());

            for(ColumnStatisticsObj other_element_value_element : other_element_value) {
               __this__partStats_copy_value.add(new ColumnStatisticsObj(other_element_value_element));
            }

            __this__partStats.put(other_element_key, __this__partStats_copy_value);
         }

         this.partStats = __this__partStats;
      }

   }

   public PartitionsStatsResult deepCopy() {
      return new PartitionsStatsResult(this);
   }

   public void clear() {
      this.partStats = null;
   }

   public int getPartStatsSize() {
      return this.partStats == null ? 0 : this.partStats.size();
   }

   public void putToPartStats(String key, List val) {
      if (this.partStats == null) {
         this.partStats = new HashMap();
      }

      this.partStats.put(key, val);
   }

   @Nullable
   public Map getPartStats() {
      return this.partStats;
   }

   public void setPartStats(@Nullable Map partStats) {
      this.partStats = partStats;
   }

   public void unsetPartStats() {
      this.partStats = null;
   }

   public boolean isSetPartStats() {
      return this.partStats != null;
   }

   public void setPartStatsIsSet(boolean value) {
      if (!value) {
         this.partStats = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PART_STATS:
            if (value == null) {
               this.unsetPartStats();
            } else {
               this.setPartStats((Map)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PART_STATS:
            return this.getPartStats();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PART_STATS:
               return this.isSetPartStats();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionsStatsResult ? this.equals((PartitionsStatsResult)that) : false;
   }

   public boolean equals(PartitionsStatsResult that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_partStats = this.isSetPartStats();
         boolean that_present_partStats = that.isSetPartStats();
         if (this_present_partStats || that_present_partStats) {
            if (!this_present_partStats || !that_present_partStats) {
               return false;
            }

            if (!this.partStats.equals(that.partStats)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPartStats() ? 131071 : 524287);
      if (this.isSetPartStats()) {
         hashCode = hashCode * 8191 + this.partStats.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionsStatsResult other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPartStats(), other.isSetPartStats());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPartStats()) {
               lastComparison = TBaseHelper.compareTo(this.partStats, other.partStats);
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
      return PartitionsStatsResult._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionsStatsResult(");
      boolean first = true;
      sb.append("partStats:");
      if (this.partStats == null) {
         sb.append("null");
      } else {
         sb.append(this.partStats);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPartStats()) {
         throw new TProtocolException("Required field 'partStats' is unset! Struct:" + this.toString());
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
      tmpMap.put(PartitionsStatsResult._Fields.PART_STATS, new FieldMetaData("partStats", (byte)1, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnStatisticsObj.class)))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionsStatsResult.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PART_STATS((short)1, "partStats");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PART_STATS;
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

   private static class PartitionsStatsResultStandardSchemeFactory implements SchemeFactory {
      private PartitionsStatsResultStandardSchemeFactory() {
      }

      public PartitionsStatsResultStandardScheme getScheme() {
         return new PartitionsStatsResultStandardScheme();
      }
   }

   private static class PartitionsStatsResultStandardScheme extends StandardScheme {
      private PartitionsStatsResultStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionsStatsResult struct) throws TException {
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
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map370 = iprot.readMapBegin();
                  struct.partStats = new HashMap(2 * _map370.size);

                  for(int _i373 = 0; _i373 < _map370.size; ++_i373) {
                     String _key371 = iprot.readString();
                     TList _list374 = iprot.readListBegin();
                     List<ColumnStatisticsObj> _val372 = new ArrayList(_list374.size);

                     for(int _i376 = 0; _i376 < _list374.size; ++_i376) {
                        ColumnStatisticsObj _elem375 = new ColumnStatisticsObj();
                        _elem375.read(iprot);
                        _val372.add(_elem375);
                     }

                     iprot.readListEnd();
                     struct.partStats.put(_key371, _val372);
                  }

                  iprot.readMapEnd();
                  struct.setPartStatsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, PartitionsStatsResult struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionsStatsResult.STRUCT_DESC);
         if (struct.partStats != null) {
            oprot.writeFieldBegin(PartitionsStatsResult.PART_STATS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)15, struct.partStats.size()));

            for(Map.Entry _iter377 : struct.partStats.entrySet()) {
               oprot.writeString((String)_iter377.getKey());
               oprot.writeListBegin(new TList((byte)12, ((List)_iter377.getValue()).size()));

               for(ColumnStatisticsObj _iter378 : (List)_iter377.getValue()) {
                  _iter378.write(oprot);
               }

               oprot.writeListEnd();
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionsStatsResultTupleSchemeFactory implements SchemeFactory {
      private PartitionsStatsResultTupleSchemeFactory() {
      }

      public PartitionsStatsResultTupleScheme getScheme() {
         return new PartitionsStatsResultTupleScheme();
      }
   }

   private static class PartitionsStatsResultTupleScheme extends TupleScheme {
      private PartitionsStatsResultTupleScheme() {
      }

      public void write(TProtocol prot, PartitionsStatsResult struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.partStats.size());

         for(Map.Entry _iter379 : struct.partStats.entrySet()) {
            oprot.writeString((String)_iter379.getKey());
            oprot.writeI32(((List)_iter379.getValue()).size());

            for(ColumnStatisticsObj _iter380 : (List)_iter379.getValue()) {
               _iter380.write(oprot);
            }
         }

      }

      public void read(TProtocol prot, PartitionsStatsResult struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TMap _map381 = iprot.readMapBegin((byte)11, (byte)15);
         struct.partStats = new HashMap(2 * _map381.size);

         for(int _i384 = 0; _i384 < _map381.size; ++_i384) {
            String _key382 = iprot.readString();
            TList _list385 = iprot.readListBegin((byte)12);
            List<ColumnStatisticsObj> _val383 = new ArrayList(_list385.size);

            for(int _i387 = 0; _i387 < _list385.size; ++_i387) {
               ColumnStatisticsObj _elem386 = new ColumnStatisticsObj();
               _elem386.read(iprot);
               _val383.add(_elem386);
            }

            struct.partStats.put(_key382, _val383);
         }

         struct.setPartStatsIsSet(true);
      }
   }
}
