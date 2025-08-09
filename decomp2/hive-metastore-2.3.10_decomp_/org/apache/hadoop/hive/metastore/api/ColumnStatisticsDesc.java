package org.apache.hadoop.hive.metastore.api;

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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
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

public class ColumnStatisticsDesc implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ColumnStatisticsDesc");
   private static final TField IS_TBL_LEVEL_FIELD_DESC = new TField("isTblLevel", (byte)2, (short)1);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)2);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)3);
   private static final TField PART_NAME_FIELD_DESC = new TField("partName", (byte)11, (short)4);
   private static final TField LAST_ANALYZED_FIELD_DESC = new TField("lastAnalyzed", (byte)10, (short)5);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ColumnStatisticsDescStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ColumnStatisticsDescTupleSchemeFactory();
   private boolean isTblLevel;
   @Nullable
   private String dbName;
   @Nullable
   private String tableName;
   @Nullable
   private String partName;
   private long lastAnalyzed;
   @Nullable
   private String catName;
   private static final int __ISTBLLEVEL_ISSET_ID = 0;
   private static final int __LASTANALYZED_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ColumnStatisticsDesc() {
      this.__isset_bitfield = 0;
   }

   public ColumnStatisticsDesc(boolean isTblLevel, String dbName, String tableName) {
      this();
      this.isTblLevel = isTblLevel;
      this.setIsTblLevelIsSet(true);
      this.dbName = dbName;
      this.tableName = tableName;
   }

   public ColumnStatisticsDesc(ColumnStatisticsDesc other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.isTblLevel = other.isTblLevel;
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetPartName()) {
         this.partName = other.partName;
      }

      this.lastAnalyzed = other.lastAnalyzed;
      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public ColumnStatisticsDesc deepCopy() {
      return new ColumnStatisticsDesc(this);
   }

   public void clear() {
      this.setIsTblLevelIsSet(false);
      this.isTblLevel = false;
      this.dbName = null;
      this.tableName = null;
      this.partName = null;
      this.setLastAnalyzedIsSet(false);
      this.lastAnalyzed = 0L;
      this.catName = null;
   }

   public boolean isIsTblLevel() {
      return this.isTblLevel;
   }

   public void setIsTblLevel(boolean isTblLevel) {
      this.isTblLevel = isTblLevel;
      this.setIsTblLevelIsSet(true);
   }

   public void unsetIsTblLevel() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIsTblLevel() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIsTblLevelIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = dbName;
   }

   public void unsetDbName() {
      this.dbName = null;
   }

   public boolean isSetDbName() {
      return this.dbName != null;
   }

   public void setDbNameIsSet(boolean value) {
      if (!value) {
         this.dbName = null;
      }

   }

   @Nullable
   public String getTableName() {
      return this.tableName;
   }

   public void setTableName(@Nullable String tableName) {
      this.tableName = tableName;
   }

   public void unsetTableName() {
      this.tableName = null;
   }

   public boolean isSetTableName() {
      return this.tableName != null;
   }

   public void setTableNameIsSet(boolean value) {
      if (!value) {
         this.tableName = null;
      }

   }

   @Nullable
   public String getPartName() {
      return this.partName;
   }

   public void setPartName(@Nullable String partName) {
      this.partName = partName;
   }

   public void unsetPartName() {
      this.partName = null;
   }

   public boolean isSetPartName() {
      return this.partName != null;
   }

   public void setPartNameIsSet(boolean value) {
      if (!value) {
         this.partName = null;
      }

   }

   public long getLastAnalyzed() {
      return this.lastAnalyzed;
   }

   public void setLastAnalyzed(long lastAnalyzed) {
      this.lastAnalyzed = lastAnalyzed;
      this.setLastAnalyzedIsSet(true);
   }

   public void unsetLastAnalyzed() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetLastAnalyzed() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setLastAnalyzedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   @Nullable
   public String getCatName() {
      return this.catName;
   }

   public void setCatName(@Nullable String catName) {
      this.catName = catName;
   }

   public void unsetCatName() {
      this.catName = null;
   }

   public boolean isSetCatName() {
      return this.catName != null;
   }

   public void setCatNameIsSet(boolean value) {
      if (!value) {
         this.catName = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case IS_TBL_LEVEL:
            if (value == null) {
               this.unsetIsTblLevel();
            } else {
               this.setIsTblLevel((Boolean)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case PART_NAME:
            if (value == null) {
               this.unsetPartName();
            } else {
               this.setPartName((String)value);
            }
            break;
         case LAST_ANALYZED:
            if (value == null) {
               this.unsetLastAnalyzed();
            } else {
               this.setLastAnalyzed((Long)value);
            }
            break;
         case CAT_NAME:
            if (value == null) {
               this.unsetCatName();
            } else {
               this.setCatName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case IS_TBL_LEVEL:
            return this.isIsTblLevel();
         case DB_NAME:
            return this.getDbName();
         case TABLE_NAME:
            return this.getTableName();
         case PART_NAME:
            return this.getPartName();
         case LAST_ANALYZED:
            return this.getLastAnalyzed();
         case CAT_NAME:
            return this.getCatName();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case IS_TBL_LEVEL:
               return this.isSetIsTblLevel();
            case DB_NAME:
               return this.isSetDbName();
            case TABLE_NAME:
               return this.isSetTableName();
            case PART_NAME:
               return this.isSetPartName();
            case LAST_ANALYZED:
               return this.isSetLastAnalyzed();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ColumnStatisticsDesc ? this.equals((ColumnStatisticsDesc)that) : false;
   }

   public boolean equals(ColumnStatisticsDesc that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_isTblLevel = true;
         boolean that_present_isTblLevel = true;
         if (this_present_isTblLevel || that_present_isTblLevel) {
            if (!this_present_isTblLevel || !that_present_isTblLevel) {
               return false;
            }

            if (this.isTblLevel != that.isTblLevel) {
               return false;
            }
         }

         boolean this_present_dbName = this.isSetDbName();
         boolean that_present_dbName = that.isSetDbName();
         if (this_present_dbName || that_present_dbName) {
            if (!this_present_dbName || !that_present_dbName) {
               return false;
            }

            if (!this.dbName.equals(that.dbName)) {
               return false;
            }
         }

         boolean this_present_tableName = this.isSetTableName();
         boolean that_present_tableName = that.isSetTableName();
         if (this_present_tableName || that_present_tableName) {
            if (!this_present_tableName || !that_present_tableName) {
               return false;
            }

            if (!this.tableName.equals(that.tableName)) {
               return false;
            }
         }

         boolean this_present_partName = this.isSetPartName();
         boolean that_present_partName = that.isSetPartName();
         if (this_present_partName || that_present_partName) {
            if (!this_present_partName || !that_present_partName) {
               return false;
            }

            if (!this.partName.equals(that.partName)) {
               return false;
            }
         }

         boolean this_present_lastAnalyzed = this.isSetLastAnalyzed();
         boolean that_present_lastAnalyzed = that.isSetLastAnalyzed();
         if (this_present_lastAnalyzed || that_present_lastAnalyzed) {
            if (!this_present_lastAnalyzed || !that_present_lastAnalyzed) {
               return false;
            }

            if (this.lastAnalyzed != that.lastAnalyzed) {
               return false;
            }
         }

         boolean this_present_catName = this.isSetCatName();
         boolean that_present_catName = that.isSetCatName();
         if (this_present_catName || that_present_catName) {
            if (!this_present_catName || !that_present_catName) {
               return false;
            }

            if (!this.catName.equals(that.catName)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isTblLevel ? 131071 : 524287);
      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartName() ? 131071 : 524287);
      if (this.isSetPartName()) {
         hashCode = hashCode * 8191 + this.partName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetLastAnalyzed() ? 131071 : 524287);
      if (this.isSetLastAnalyzed()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lastAnalyzed);
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(ColumnStatisticsDesc other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetIsTblLevel(), other.isSetIsTblLevel());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetIsTblLevel()) {
               lastComparison = TBaseHelper.compareTo(this.isTblLevel, other.isTblLevel);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetDbName(), other.isSetDbName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetDbName()) {
                  lastComparison = TBaseHelper.compareTo(this.dbName, other.dbName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetTableName(), other.isSetTableName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetTableName()) {
                     lastComparison = TBaseHelper.compareTo(this.tableName, other.tableName);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetPartName(), other.isSetPartName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetPartName()) {
                        lastComparison = TBaseHelper.compareTo(this.partName, other.partName);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetLastAnalyzed(), other.isSetLastAnalyzed());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetLastAnalyzed()) {
                           lastComparison = TBaseHelper.compareTo(this.lastAnalyzed, other.lastAnalyzed);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetCatName(), other.isSetCatName());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetCatName()) {
                              lastComparison = TBaseHelper.compareTo(this.catName, other.catName);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ColumnStatisticsDesc._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ColumnStatisticsDesc(");
      boolean first = true;
      sb.append("isTblLevel:");
      sb.append(this.isTblLevel);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("dbName:");
      if (this.dbName == null) {
         sb.append("null");
      } else {
         sb.append(this.dbName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tableName:");
      if (this.tableName == null) {
         sb.append("null");
      } else {
         sb.append(this.tableName);
      }

      first = false;
      if (this.isSetPartName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partName:");
         if (this.partName == null) {
            sb.append("null");
         } else {
            sb.append(this.partName);
         }

         first = false;
      }

      if (this.isSetLastAnalyzed()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("lastAnalyzed:");
         sb.append(this.lastAnalyzed);
         first = false;
      }

      if (this.isSetCatName()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("catName:");
         if (this.catName == null) {
            sb.append("null");
         } else {
            sb.append(this.catName);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetIsTblLevel()) {
         throw new TProtocolException("Required field 'isTblLevel' is unset! Struct:" + this.toString());
      } else if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else if (!this.isSetTableName()) {
         throw new TProtocolException("Required field 'tableName' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{ColumnStatisticsDesc._Fields.PART_NAME, ColumnStatisticsDesc._Fields.LAST_ANALYZED, ColumnStatisticsDesc._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ColumnStatisticsDesc._Fields.IS_TBL_LEVEL, new FieldMetaData("isTblLevel", (byte)1, new FieldValueMetaData((byte)2)));
      tmpMap.put(ColumnStatisticsDesc._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ColumnStatisticsDesc._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ColumnStatisticsDesc._Fields.PART_NAME, new FieldMetaData("partName", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ColumnStatisticsDesc._Fields.LAST_ANALYZED, new FieldMetaData("lastAnalyzed", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ColumnStatisticsDesc._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ColumnStatisticsDesc.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      IS_TBL_LEVEL((short)1, "isTblLevel"),
      DB_NAME((short)2, "dbName"),
      TABLE_NAME((short)3, "tableName"),
      PART_NAME((short)4, "partName"),
      LAST_ANALYZED((short)5, "lastAnalyzed"),
      CAT_NAME((short)6, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return IS_TBL_LEVEL;
            case 2:
               return DB_NAME;
            case 3:
               return TABLE_NAME;
            case 4:
               return PART_NAME;
            case 5:
               return LAST_ANALYZED;
            case 6:
               return CAT_NAME;
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

   private static class ColumnStatisticsDescStandardSchemeFactory implements SchemeFactory {
      private ColumnStatisticsDescStandardSchemeFactory() {
      }

      public ColumnStatisticsDescStandardScheme getScheme() {
         return new ColumnStatisticsDescStandardScheme();
      }
   }

   private static class ColumnStatisticsDescStandardScheme extends StandardScheme {
      private ColumnStatisticsDescStandardScheme() {
      }

      public void read(TProtocol iprot, ColumnStatisticsDesc struct) throws TException {
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
                  if (schemeField.type == 2) {
                     struct.isTblLevel = iprot.readBool();
                     struct.setIsTblLevelIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.partName = iprot.readString();
                     struct.setPartNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 10) {
                     struct.lastAnalyzed = iprot.readI64();
                     struct.setLastAnalyzedIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.catName = iprot.readString();
                     struct.setCatNameIsSet(true);
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

      public void write(TProtocol oprot, ColumnStatisticsDesc struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ColumnStatisticsDesc.STRUCT_DESC);
         oprot.writeFieldBegin(ColumnStatisticsDesc.IS_TBL_LEVEL_FIELD_DESC);
         oprot.writeBool(struct.isTblLevel);
         oprot.writeFieldEnd();
         if (struct.dbName != null) {
            oprot.writeFieldBegin(ColumnStatisticsDesc.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null) {
            oprot.writeFieldBegin(ColumnStatisticsDesc.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.partName != null && struct.isSetPartName()) {
            oprot.writeFieldBegin(ColumnStatisticsDesc.PART_NAME_FIELD_DESC);
            oprot.writeString(struct.partName);
            oprot.writeFieldEnd();
         }

         if (struct.isSetLastAnalyzed()) {
            oprot.writeFieldBegin(ColumnStatisticsDesc.LAST_ANALYZED_FIELD_DESC);
            oprot.writeI64(struct.lastAnalyzed);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(ColumnStatisticsDesc.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ColumnStatisticsDescTupleSchemeFactory implements SchemeFactory {
      private ColumnStatisticsDescTupleSchemeFactory() {
      }

      public ColumnStatisticsDescTupleScheme getScheme() {
         return new ColumnStatisticsDescTupleScheme();
      }
   }

   private static class ColumnStatisticsDescTupleScheme extends TupleScheme {
      private ColumnStatisticsDescTupleScheme() {
      }

      public void write(TProtocol prot, ColumnStatisticsDesc struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeBool(struct.isTblLevel);
         oprot.writeString(struct.dbName);
         oprot.writeString(struct.tableName);
         BitSet optionals = new BitSet();
         if (struct.isSetPartName()) {
            optionals.set(0);
         }

         if (struct.isSetLastAnalyzed()) {
            optionals.set(1);
         }

         if (struct.isSetCatName()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetPartName()) {
            oprot.writeString(struct.partName);
         }

         if (struct.isSetLastAnalyzed()) {
            oprot.writeI64(struct.lastAnalyzed);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, ColumnStatisticsDesc struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.isTblLevel = iprot.readBool();
         struct.setIsTblLevelIsSet(true);
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         struct.tableName = iprot.readString();
         struct.setTableNameIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.partName = iprot.readString();
            struct.setPartNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.lastAnalyzed = iprot.readI64();
            struct.setLastAnalyzedIsSet(true);
         }

         if (incoming.get(2)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
