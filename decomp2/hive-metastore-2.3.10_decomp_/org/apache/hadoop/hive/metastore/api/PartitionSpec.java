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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class PartitionSpec implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PartitionSpec");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)2);
   private static final TField ROOT_PATH_FIELD_DESC = new TField("rootPath", (byte)11, (short)3);
   private static final TField SHARED_SDPARTITION_SPEC_FIELD_DESC = new TField("sharedSDPartitionSpec", (byte)12, (short)4);
   private static final TField PARTITION_LIST_FIELD_DESC = new TField("partitionList", (byte)12, (short)5);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)6);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionSpecStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionSpecTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private String tableName;
   @Nullable
   private String rootPath;
   @Nullable
   private PartitionSpecWithSharedSD sharedSDPartitionSpec;
   @Nullable
   private PartitionListComposingSpec partitionList;
   @Nullable
   private String catName;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public PartitionSpec() {
   }

   public PartitionSpec(String dbName, String tableName, String rootPath) {
      this();
      this.dbName = dbName;
      this.tableName = tableName;
      this.rootPath = rootPath;
   }

   public PartitionSpec(PartitionSpec other) {
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetRootPath()) {
         this.rootPath = other.rootPath;
      }

      if (other.isSetSharedSDPartitionSpec()) {
         this.sharedSDPartitionSpec = new PartitionSpecWithSharedSD(other.sharedSDPartitionSpec);
      }

      if (other.isSetPartitionList()) {
         this.partitionList = new PartitionListComposingSpec(other.partitionList);
      }

      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public PartitionSpec deepCopy() {
      return new PartitionSpec(this);
   }

   public void clear() {
      this.dbName = null;
      this.tableName = null;
      this.rootPath = null;
      this.sharedSDPartitionSpec = null;
      this.partitionList = null;
      this.catName = null;
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
   public String getRootPath() {
      return this.rootPath;
   }

   public void setRootPath(@Nullable String rootPath) {
      this.rootPath = rootPath;
   }

   public void unsetRootPath() {
      this.rootPath = null;
   }

   public boolean isSetRootPath() {
      return this.rootPath != null;
   }

   public void setRootPathIsSet(boolean value) {
      if (!value) {
         this.rootPath = null;
      }

   }

   @Nullable
   public PartitionSpecWithSharedSD getSharedSDPartitionSpec() {
      return this.sharedSDPartitionSpec;
   }

   public void setSharedSDPartitionSpec(@Nullable PartitionSpecWithSharedSD sharedSDPartitionSpec) {
      this.sharedSDPartitionSpec = sharedSDPartitionSpec;
   }

   public void unsetSharedSDPartitionSpec() {
      this.sharedSDPartitionSpec = null;
   }

   public boolean isSetSharedSDPartitionSpec() {
      return this.sharedSDPartitionSpec != null;
   }

   public void setSharedSDPartitionSpecIsSet(boolean value) {
      if (!value) {
         this.sharedSDPartitionSpec = null;
      }

   }

   @Nullable
   public PartitionListComposingSpec getPartitionList() {
      return this.partitionList;
   }

   public void setPartitionList(@Nullable PartitionListComposingSpec partitionList) {
      this.partitionList = partitionList;
   }

   public void unsetPartitionList() {
      this.partitionList = null;
   }

   public boolean isSetPartitionList() {
      return this.partitionList != null;
   }

   public void setPartitionListIsSet(boolean value) {
      if (!value) {
         this.partitionList = null;
      }

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
         case ROOT_PATH:
            if (value == null) {
               this.unsetRootPath();
            } else {
               this.setRootPath((String)value);
            }
            break;
         case SHARED_SDPARTITION_SPEC:
            if (value == null) {
               this.unsetSharedSDPartitionSpec();
            } else {
               this.setSharedSDPartitionSpec((PartitionSpecWithSharedSD)value);
            }
            break;
         case PARTITION_LIST:
            if (value == null) {
               this.unsetPartitionList();
            } else {
               this.setPartitionList((PartitionListComposingSpec)value);
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
         case DB_NAME:
            return this.getDbName();
         case TABLE_NAME:
            return this.getTableName();
         case ROOT_PATH:
            return this.getRootPath();
         case SHARED_SDPARTITION_SPEC:
            return this.getSharedSDPartitionSpec();
         case PARTITION_LIST:
            return this.getPartitionList();
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
            case DB_NAME:
               return this.isSetDbName();
            case TABLE_NAME:
               return this.isSetTableName();
            case ROOT_PATH:
               return this.isSetRootPath();
            case SHARED_SDPARTITION_SPEC:
               return this.isSetSharedSDPartitionSpec();
            case PARTITION_LIST:
               return this.isSetPartitionList();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PartitionSpec ? this.equals((PartitionSpec)that) : false;
   }

   public boolean equals(PartitionSpec that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_rootPath = this.isSetRootPath();
         boolean that_present_rootPath = that.isSetRootPath();
         if (this_present_rootPath || that_present_rootPath) {
            if (!this_present_rootPath || !that_present_rootPath) {
               return false;
            }

            if (!this.rootPath.equals(that.rootPath)) {
               return false;
            }
         }

         boolean this_present_sharedSDPartitionSpec = this.isSetSharedSDPartitionSpec();
         boolean that_present_sharedSDPartitionSpec = that.isSetSharedSDPartitionSpec();
         if (this_present_sharedSDPartitionSpec || that_present_sharedSDPartitionSpec) {
            if (!this_present_sharedSDPartitionSpec || !that_present_sharedSDPartitionSpec) {
               return false;
            }

            if (!this.sharedSDPartitionSpec.equals(that.sharedSDPartitionSpec)) {
               return false;
            }
         }

         boolean this_present_partitionList = this.isSetPartitionList();
         boolean that_present_partitionList = that.isSetPartitionList();
         if (this_present_partitionList || that_present_partitionList) {
            if (!this_present_partitionList || !that_present_partitionList) {
               return false;
            }

            if (!this.partitionList.equals(that.partitionList)) {
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
      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRootPath() ? 131071 : 524287);
      if (this.isSetRootPath()) {
         hashCode = hashCode * 8191 + this.rootPath.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSharedSDPartitionSpec() ? 131071 : 524287);
      if (this.isSetSharedSDPartitionSpec()) {
         hashCode = hashCode * 8191 + this.sharedSDPartitionSpec.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionList() ? 131071 : 524287);
      if (this.isSetPartitionList()) {
         hashCode = hashCode * 8191 + this.partitionList.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PartitionSpec other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

               lastComparison = Boolean.compare(this.isSetRootPath(), other.isSetRootPath());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetRootPath()) {
                     lastComparison = TBaseHelper.compareTo(this.rootPath, other.rootPath);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetSharedSDPartitionSpec(), other.isSetSharedSDPartitionSpec());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetSharedSDPartitionSpec()) {
                        lastComparison = TBaseHelper.compareTo(this.sharedSDPartitionSpec, other.sharedSDPartitionSpec);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetPartitionList(), other.isSetPartitionList());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetPartitionList()) {
                           lastComparison = TBaseHelper.compareTo(this.partitionList, other.partitionList);
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
      return PartitionSpec._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PartitionSpec(");
      boolean first = true;
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
      if (!first) {
         sb.append(", ");
      }

      sb.append("rootPath:");
      if (this.rootPath == null) {
         sb.append("null");
      } else {
         sb.append(this.rootPath);
      }

      first = false;
      if (this.isSetSharedSDPartitionSpec()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("sharedSDPartitionSpec:");
         if (this.sharedSDPartitionSpec == null) {
            sb.append("null");
         } else {
            sb.append(this.sharedSDPartitionSpec);
         }

         first = false;
      }

      if (this.isSetPartitionList()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partitionList:");
         if (this.partitionList == null) {
            sb.append("null");
         } else {
            sb.append(this.partitionList);
         }

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
      if (this.sharedSDPartitionSpec != null) {
         this.sharedSDPartitionSpec.validate();
      }

      if (this.partitionList != null) {
         this.partitionList.validate();
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
      optionals = new _Fields[]{PartitionSpec._Fields.SHARED_SDPARTITION_SPEC, PartitionSpec._Fields.PARTITION_LIST, PartitionSpec._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(PartitionSpec._Fields.DB_NAME, new FieldMetaData("dbName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionSpec._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionSpec._Fields.ROOT_PATH, new FieldMetaData("rootPath", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(PartitionSpec._Fields.SHARED_SDPARTITION_SPEC, new FieldMetaData("sharedSDPartitionSpec", (byte)2, new StructMetaData((byte)12, PartitionSpecWithSharedSD.class)));
      tmpMap.put(PartitionSpec._Fields.PARTITION_LIST, new FieldMetaData("partitionList", (byte)2, new StructMetaData((byte)12, PartitionListComposingSpec.class)));
      tmpMap.put(PartitionSpec._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PartitionSpec.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TABLE_NAME((short)2, "tableName"),
      ROOT_PATH((short)3, "rootPath"),
      SHARED_SDPARTITION_SPEC((short)4, "sharedSDPartitionSpec"),
      PARTITION_LIST((short)5, "partitionList"),
      CAT_NAME((short)6, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TABLE_NAME;
            case 3:
               return ROOT_PATH;
            case 4:
               return SHARED_SDPARTITION_SPEC;
            case 5:
               return PARTITION_LIST;
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

   private static class PartitionSpecStandardSchemeFactory implements SchemeFactory {
      private PartitionSpecStandardSchemeFactory() {
      }

      public PartitionSpecStandardScheme getScheme() {
         return new PartitionSpecStandardScheme();
      }
   }

   private static class PartitionSpecStandardScheme extends StandardScheme {
      private PartitionSpecStandardScheme() {
      }

      public void read(TProtocol iprot, PartitionSpec struct) throws TException {
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
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.rootPath = iprot.readString();
                     struct.setRootPathIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 12) {
                     struct.sharedSDPartitionSpec = new PartitionSpecWithSharedSD();
                     struct.sharedSDPartitionSpec.read(iprot);
                     struct.setSharedSDPartitionSpecIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 12) {
                     struct.partitionList = new PartitionListComposingSpec();
                     struct.partitionList.read(iprot);
                     struct.setPartitionListIsSet(true);
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

      public void write(TProtocol oprot, PartitionSpec struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PartitionSpec.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(PartitionSpec.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null) {
            oprot.writeFieldBegin(PartitionSpec.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.rootPath != null) {
            oprot.writeFieldBegin(PartitionSpec.ROOT_PATH_FIELD_DESC);
            oprot.writeString(struct.rootPath);
            oprot.writeFieldEnd();
         }

         if (struct.sharedSDPartitionSpec != null && struct.isSetSharedSDPartitionSpec()) {
            oprot.writeFieldBegin(PartitionSpec.SHARED_SDPARTITION_SPEC_FIELD_DESC);
            struct.sharedSDPartitionSpec.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.partitionList != null && struct.isSetPartitionList()) {
            oprot.writeFieldBegin(PartitionSpec.PARTITION_LIST_FIELD_DESC);
            struct.partitionList.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(PartitionSpec.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionSpecTupleSchemeFactory implements SchemeFactory {
      private PartitionSpecTupleSchemeFactory() {
      }

      public PartitionSpecTupleScheme getScheme() {
         return new PartitionSpecTupleScheme();
      }
   }

   private static class PartitionSpecTupleScheme extends TupleScheme {
      private PartitionSpecTupleScheme() {
      }

      public void write(TProtocol prot, PartitionSpec struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetDbName()) {
            optionals.set(0);
         }

         if (struct.isSetTableName()) {
            optionals.set(1);
         }

         if (struct.isSetRootPath()) {
            optionals.set(2);
         }

         if (struct.isSetSharedSDPartitionSpec()) {
            optionals.set(3);
         }

         if (struct.isSetPartitionList()) {
            optionals.set(4);
         }

         if (struct.isSetCatName()) {
            optionals.set(5);
         }

         oprot.writeBitSet(optionals, 6);
         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetRootPath()) {
            oprot.writeString(struct.rootPath);
         }

         if (struct.isSetSharedSDPartitionSpec()) {
            struct.sharedSDPartitionSpec.write(oprot);
         }

         if (struct.isSetPartitionList()) {
            struct.partitionList.write(oprot);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, PartitionSpec struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(6);
         if (incoming.get(0)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.rootPath = iprot.readString();
            struct.setRootPathIsSet(true);
         }

         if (incoming.get(3)) {
            struct.sharedSDPartitionSpec = new PartitionSpecWithSharedSD();
            struct.sharedSDPartitionSpec.read(iprot);
            struct.setSharedSDPartitionSpecIsSet(true);
         }

         if (incoming.get(4)) {
            struct.partitionList = new PartitionListComposingSpec();
            struct.partitionList.read(iprot);
            struct.setPartitionListIsSet(true);
         }

         if (incoming.get(5)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
