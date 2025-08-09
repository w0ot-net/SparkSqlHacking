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

public class ColumnStatistics implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnStatistics");
   private static final TField STATS_DESC_FIELD_DESC = new TField("statsDesc", (byte)12, (short)1);
   private static final TField STATS_OBJ_FIELD_DESC = new TField("statsObj", (byte)15, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnStatisticsStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnStatisticsTupleSchemeFactory();
   @Nullable
   private ColumnStatisticsDesc statsDesc;
   @Nullable
   private List statsObj;
   public static final Map metaDataMap;

   public ColumnStatistics() {
   }

   public ColumnStatistics(ColumnStatisticsDesc statsDesc, List statsObj) {
      this();
      this.statsDesc = statsDesc;
      this.statsObj = statsObj;
   }

   public ColumnStatistics(ColumnStatistics other) {
      if (other.isSetStatsDesc()) {
         this.statsDesc = new ColumnStatisticsDesc(other.statsDesc);
      }

      if (other.isSetStatsObj()) {
         List<ColumnStatisticsObj> __this__statsObj = new ArrayList(other.statsObj.size());

         for(ColumnStatisticsObj other_element : other.statsObj) {
            __this__statsObj.add(new ColumnStatisticsObj(other_element));
         }

         this.statsObj = __this__statsObj;
      }

   }

   public ColumnStatistics deepCopy() {
      return new ColumnStatistics(this);
   }

   public void clear() {
      this.statsDesc = null;
      this.statsObj = null;
   }

   @Nullable
   public ColumnStatisticsDesc getStatsDesc() {
      return this.statsDesc;
   }

   public void setStatsDesc(@Nullable ColumnStatisticsDesc statsDesc) {
      this.statsDesc = statsDesc;
   }

   public void unsetStatsDesc() {
      this.statsDesc = null;
   }

   public boolean isSetStatsDesc() {
      return this.statsDesc != null;
   }

   public void setStatsDescIsSet(boolean value) {
      if (!value) {
         this.statsDesc = null;
      }

   }

   public int getStatsObjSize() {
      return this.statsObj == null ? 0 : this.statsObj.size();
   }

   @Nullable
   public Iterator getStatsObjIterator() {
      return this.statsObj == null ? null : this.statsObj.iterator();
   }

   public void addToStatsObj(ColumnStatisticsObj elem) {
      if (this.statsObj == null) {
         this.statsObj = new ArrayList();
      }

      this.statsObj.add(elem);
   }

   @Nullable
   public List getStatsObj() {
      return this.statsObj;
   }

   public void setStatsObj(@Nullable List statsObj) {
      this.statsObj = statsObj;
   }

   public void unsetStatsObj() {
      this.statsObj = null;
   }

   public boolean isSetStatsObj() {
      return this.statsObj != null;
   }

   public void setStatsObjIsSet(boolean value) {
      if (!value) {
         this.statsObj = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case STATS_DESC:
            if (value == null) {
               this.unsetStatsDesc();
            } else {
               this.setStatsDesc((ColumnStatisticsDesc)value);
            }
            break;
         case STATS_OBJ:
            if (value == null) {
               this.unsetStatsObj();
            } else {
               this.setStatsObj((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case STATS_DESC:
            return this.getStatsDesc();
         case STATS_OBJ:
            return this.getStatsObj();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case STATS_DESC:
               return this.isSetStatsDesc();
            case STATS_OBJ:
               return this.isSetStatsObj();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ColumnStatistics ? this.equals((ColumnStatistics)that) : false;
   }

   public boolean equals(ColumnStatistics that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_statsDesc = this.isSetStatsDesc();
         boolean that_present_statsDesc = that.isSetStatsDesc();
         if (this_present_statsDesc || that_present_statsDesc) {
            if (!this_present_statsDesc || !that_present_statsDesc) {
               return false;
            }

            if (!this.statsDesc.equals(that.statsDesc)) {
               return false;
            }
         }

         boolean this_present_statsObj = this.isSetStatsObj();
         boolean that_present_statsObj = that.isSetStatsObj();
         if (this_present_statsObj || that_present_statsObj) {
            if (!this_present_statsObj || !that_present_statsObj) {
               return false;
            }

            if (!this.statsObj.equals(that.statsObj)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetStatsDesc() ? 131071 : 524287);
      if (this.isSetStatsDesc()) {
         hashCode = hashCode * 8191 + this.statsDesc.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetStatsObj() ? 131071 : 524287);
      if (this.isSetStatsObj()) {
         hashCode = hashCode * 8191 + this.statsObj.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ColumnStatistics other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetStatsDesc(), other.isSetStatsDesc());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetStatsDesc()) {
               lastComparison = TBaseHelper.compareTo(this.statsDesc, other.statsDesc);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetStatsObj(), other.isSetStatsObj());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetStatsObj()) {
                  lastComparison = TBaseHelper.compareTo(this.statsObj, other.statsObj);
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
      return ColumnStatistics._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ColumnStatistics(");
      boolean first = true;
      sb.append("statsDesc:");
      if (this.statsDesc == null) {
         sb.append("null");
      } else {
         sb.append(this.statsDesc);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("statsObj:");
      if (this.statsObj == null) {
         sb.append("null");
      } else {
         sb.append(this.statsObj);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetStatsDesc()) {
         throw new TProtocolException("Required field 'statsDesc' is unset! Struct:" + this.toString());
      } else if (!this.isSetStatsObj()) {
         throw new TProtocolException("Required field 'statsObj' is unset! Struct:" + this.toString());
      } else {
         if (this.statsDesc != null) {
            this.statsDesc.validate();
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
      tmpMap.put(ColumnStatistics._Fields.STATS_DESC, new FieldMetaData("statsDesc", (byte)1, new StructMetaData((byte)12, ColumnStatisticsDesc.class)));
      tmpMap.put(ColumnStatistics._Fields.STATS_OBJ, new FieldMetaData("statsObj", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, ColumnStatisticsObj.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnStatistics.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STATS_DESC((short)1, "statsDesc"),
      STATS_OBJ((short)2, "statsObj");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STATS_DESC;
            case 2:
               return STATS_OBJ;
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

   private static class ColumnStatisticsStandardSchemeFactory implements SchemeFactory {
      private ColumnStatisticsStandardSchemeFactory() {
      }

      public ColumnStatisticsStandardScheme getScheme() {
         return new ColumnStatisticsStandardScheme();
      }
   }

   private static class ColumnStatisticsStandardScheme extends StandardScheme {
      private ColumnStatisticsStandardScheme() {
      }

      public void read(TProtocol iprot, ColumnStatistics struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.statsDesc = new ColumnStatisticsDesc();
                     struct.statsDesc.read(iprot);
                     struct.setStatsDescIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list270 = iprot.readListBegin();
                  struct.statsObj = new ArrayList(_list270.size);

                  for(int _i272 = 0; _i272 < _list270.size; ++_i272) {
                     ColumnStatisticsObj _elem271 = new ColumnStatisticsObj();
                     _elem271.read(iprot);
                     struct.statsObj.add(_elem271);
                  }

                  iprot.readListEnd();
                  struct.setStatsObjIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, ColumnStatistics struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ColumnStatistics.STRUCT_DESC);
         if (struct.statsDesc != null) {
            oprot.writeFieldBegin(ColumnStatistics.STATS_DESC_FIELD_DESC);
            struct.statsDesc.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.statsObj != null) {
            oprot.writeFieldBegin(ColumnStatistics.STATS_OBJ_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.statsObj.size()));

            for(ColumnStatisticsObj _iter273 : struct.statsObj) {
               _iter273.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ColumnStatisticsTupleSchemeFactory implements SchemeFactory {
      private ColumnStatisticsTupleSchemeFactory() {
      }

      public ColumnStatisticsTupleScheme getScheme() {
         return new ColumnStatisticsTupleScheme();
      }
   }

   private static class ColumnStatisticsTupleScheme extends TupleScheme {
      private ColumnStatisticsTupleScheme() {
      }

      public void write(TProtocol prot, ColumnStatistics struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.statsDesc.write(oprot);
         oprot.writeI32(struct.statsObj.size());

         for(ColumnStatisticsObj _iter274 : struct.statsObj) {
            _iter274.write(oprot);
         }

      }

      public void read(TProtocol prot, ColumnStatistics struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.statsDesc = new ColumnStatisticsDesc();
         struct.statsDesc.read(iprot);
         struct.setStatsDescIsSet(true);
         TList _list275 = iprot.readListBegin((byte)12);
         struct.statsObj = new ArrayList(_list275.size);

         for(int _i277 = 0; _i277 < _list275.size; ++_i277) {
            ColumnStatisticsObj _elem276 = new ColumnStatisticsObj();
            _elem276.read(iprot);
            struct.statsObj.add(_elem276);
         }

         struct.setStatsObjIsSet(true);
      }
   }
}
