package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class ColumnStatisticsObj implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnStatisticsObj");
   private static final TField COL_NAME_FIELD_DESC = new TField("colName", (byte)11, (short)1);
   private static final TField COL_TYPE_FIELD_DESC = new TField("colType", (byte)11, (short)2);
   private static final TField STATS_DATA_FIELD_DESC = new TField("statsData", (byte)12, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnStatisticsObjStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnStatisticsObjTupleSchemeFactory();
   @Nullable
   private String colName;
   @Nullable
   private String colType;
   @Nullable
   private ColumnStatisticsData statsData;
   public static final Map metaDataMap;

   public ColumnStatisticsObj() {
   }

   public ColumnStatisticsObj(String colName, String colType, ColumnStatisticsData statsData) {
      this();
      this.colName = colName;
      this.colType = colType;
      this.statsData = statsData;
   }

   public ColumnStatisticsObj(ColumnStatisticsObj other) {
      if (other.isSetColName()) {
         this.colName = other.colName;
      }

      if (other.isSetColType()) {
         this.colType = other.colType;
      }

      if (other.isSetStatsData()) {
         this.statsData = new ColumnStatisticsData(other.statsData);
      }

   }

   public ColumnStatisticsObj deepCopy() {
      return new ColumnStatisticsObj(this);
   }

   public void clear() {
      this.colName = null;
      this.colType = null;
      this.statsData = null;
   }

   @Nullable
   public String getColName() {
      return this.colName;
   }

   public void setColName(@Nullable String colName) {
      this.colName = colName;
   }

   public void unsetColName() {
      this.colName = null;
   }

   public boolean isSetColName() {
      return this.colName != null;
   }

   public void setColNameIsSet(boolean value) {
      if (!value) {
         this.colName = null;
      }

   }

   @Nullable
   public String getColType() {
      return this.colType;
   }

   public void setColType(@Nullable String colType) {
      this.colType = colType;
   }

   public void unsetColType() {
      this.colType = null;
   }

   public boolean isSetColType() {
      return this.colType != null;
   }

   public void setColTypeIsSet(boolean value) {
      if (!value) {
         this.colType = null;
      }

   }

   @Nullable
   public ColumnStatisticsData getStatsData() {
      return this.statsData;
   }

   public void setStatsData(@Nullable ColumnStatisticsData statsData) {
      this.statsData = statsData;
   }

   public void unsetStatsData() {
      this.statsData = null;
   }

   public boolean isSetStatsData() {
      return this.statsData != null;
   }

   public void setStatsDataIsSet(boolean value) {
      if (!value) {
         this.statsData = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case COL_NAME:
            if (value == null) {
               this.unsetColName();
            } else {
               this.setColName((String)value);
            }
            break;
         case COL_TYPE:
            if (value == null) {
               this.unsetColType();
            } else {
               this.setColType((String)value);
            }
            break;
         case STATS_DATA:
            if (value == null) {
               this.unsetStatsData();
            } else {
               this.setStatsData((ColumnStatisticsData)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case COL_NAME:
            return this.getColName();
         case COL_TYPE:
            return this.getColType();
         case STATS_DATA:
            return this.getStatsData();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case COL_NAME:
               return this.isSetColName();
            case COL_TYPE:
               return this.isSetColType();
            case STATS_DATA:
               return this.isSetStatsData();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ColumnStatisticsObj ? this.equals((ColumnStatisticsObj)that) : false;
   }

   public boolean equals(ColumnStatisticsObj that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_colName = this.isSetColName();
         boolean that_present_colName = that.isSetColName();
         if (this_present_colName || that_present_colName) {
            if (!this_present_colName || !that_present_colName) {
               return false;
            }

            if (!this.colName.equals(that.colName)) {
               return false;
            }
         }

         boolean this_present_colType = this.isSetColType();
         boolean that_present_colType = that.isSetColType();
         if (this_present_colType || that_present_colType) {
            if (!this_present_colType || !that_present_colType) {
               return false;
            }

            if (!this.colType.equals(that.colType)) {
               return false;
            }
         }

         boolean this_present_statsData = this.isSetStatsData();
         boolean that_present_statsData = that.isSetStatsData();
         if (this_present_statsData || that_present_statsData) {
            if (!this_present_statsData || !that_present_statsData) {
               return false;
            }

            if (!this.statsData.equals(that.statsData)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetColName() ? 131071 : 524287);
      if (this.isSetColName()) {
         hashCode = hashCode * 8191 + this.colName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetColType() ? 131071 : 524287);
      if (this.isSetColType()) {
         hashCode = hashCode * 8191 + this.colType.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetStatsData() ? 131071 : 524287);
      if (this.isSetStatsData()) {
         hashCode = hashCode * 8191 + this.statsData.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ColumnStatisticsObj other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetColName(), other.isSetColName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetColName()) {
               lastComparison = TBaseHelper.compareTo(this.colName, other.colName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetColType(), other.isSetColType());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetColType()) {
                  lastComparison = TBaseHelper.compareTo(this.colType, other.colType);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetStatsData(), other.isSetStatsData());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetStatsData()) {
                     lastComparison = TBaseHelper.compareTo(this.statsData, other.statsData);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnStatisticsObj._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ColumnStatisticsObj(");
      boolean first = true;
      sb.append("colName:");
      if (this.colName == null) {
         sb.append("null");
      } else {
         sb.append(this.colName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("colType:");
      if (this.colType == null) {
         sb.append("null");
      } else {
         sb.append(this.colType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("statsData:");
      if (this.statsData == null) {
         sb.append("null");
      } else {
         sb.append(this.statsData);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetColName()) {
         throw new TProtocolException("Required field 'colName' is unset! Struct:" + this.toString());
      } else if (!this.isSetColType()) {
         throw new TProtocolException("Required field 'colType' is unset! Struct:" + this.toString());
      } else if (!this.isSetStatsData()) {
         throw new TProtocolException("Required field 'statsData' is unset! Struct:" + this.toString());
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
      tmpMap.put(ColumnStatisticsObj._Fields.COL_NAME, new FieldMetaData("colName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ColumnStatisticsObj._Fields.COL_TYPE, new FieldMetaData("colType", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ColumnStatisticsObj._Fields.STATS_DATA, new FieldMetaData("statsData", (byte)1, new StructMetaData((byte)12, ColumnStatisticsData.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnStatisticsObj.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COL_NAME((short)1, "colName"),
      COL_TYPE((short)2, "colType"),
      STATS_DATA((short)3, "statsData");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COL_NAME;
            case 2:
               return COL_TYPE;
            case 3:
               return STATS_DATA;
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

   private static class ColumnStatisticsObjStandardSchemeFactory implements SchemeFactory {
      private ColumnStatisticsObjStandardSchemeFactory() {
      }

      public ColumnStatisticsObjStandardScheme getScheme() {
         return new ColumnStatisticsObjStandardScheme();
      }
   }

   private static class ColumnStatisticsObjStandardScheme extends StandardScheme {
      private ColumnStatisticsObjStandardScheme() {
      }

      public void read(TProtocol iprot, ColumnStatisticsObj struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.colName = iprot.readString();
                     struct.setColNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.colType = iprot.readString();
                     struct.setColTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 12) {
                     struct.statsData = new ColumnStatisticsData();
                     struct.statsData.read(iprot);
                     struct.setStatsDataIsSet(true);
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

      public void write(TProtocol oprot, ColumnStatisticsObj struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ColumnStatisticsObj.STRUCT_DESC);
         if (struct.colName != null) {
            oprot.writeFieldBegin(ColumnStatisticsObj.COL_NAME_FIELD_DESC);
            oprot.writeString(struct.colName);
            oprot.writeFieldEnd();
         }

         if (struct.colType != null) {
            oprot.writeFieldBegin(ColumnStatisticsObj.COL_TYPE_FIELD_DESC);
            oprot.writeString(struct.colType);
            oprot.writeFieldEnd();
         }

         if (struct.statsData != null) {
            oprot.writeFieldBegin(ColumnStatisticsObj.STATS_DATA_FIELD_DESC);
            struct.statsData.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ColumnStatisticsObjTupleSchemeFactory implements SchemeFactory {
      private ColumnStatisticsObjTupleSchemeFactory() {
      }

      public ColumnStatisticsObjTupleScheme getScheme() {
         return new ColumnStatisticsObjTupleScheme();
      }
   }

   private static class ColumnStatisticsObjTupleScheme extends TupleScheme {
      private ColumnStatisticsObjTupleScheme() {
      }

      public void write(TProtocol prot, ColumnStatisticsObj struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.colName);
         oprot.writeString(struct.colType);
         struct.statsData.write(oprot);
      }

      public void read(TProtocol prot, ColumnStatisticsObj struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.colName = iprot.readString();
         struct.setColNameIsSet(true);
         struct.colType = iprot.readString();
         struct.setColTypeIsSet(true);
         struct.statsData = new ColumnStatisticsData();
         struct.statsData.read(iprot);
         struct.setStatsDataIsSet(true);
      }
   }
}
