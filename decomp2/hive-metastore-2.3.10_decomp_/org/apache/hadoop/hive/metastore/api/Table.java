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
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class Table implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Table");
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)1);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)2);
   private static final TField OWNER_FIELD_DESC = new TField("owner", (byte)11, (short)3);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)4);
   private static final TField LAST_ACCESS_TIME_FIELD_DESC = new TField("lastAccessTime", (byte)8, (short)5);
   private static final TField RETENTION_FIELD_DESC = new TField("retention", (byte)8, (short)6);
   private static final TField SD_FIELD_DESC = new TField("sd", (byte)12, (short)7);
   private static final TField PARTITION_KEYS_FIELD_DESC = new TField("partitionKeys", (byte)15, (short)8);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)9);
   private static final TField VIEW_ORIGINAL_TEXT_FIELD_DESC = new TField("viewOriginalText", (byte)11, (short)10);
   private static final TField VIEW_EXPANDED_TEXT_FIELD_DESC = new TField("viewExpandedText", (byte)11, (short)11);
   private static final TField TABLE_TYPE_FIELD_DESC = new TField("tableType", (byte)11, (short)12);
   private static final TField PRIVILEGES_FIELD_DESC = new TField("privileges", (byte)12, (short)13);
   private static final TField TEMPORARY_FIELD_DESC = new TField("temporary", (byte)2, (short)14);
   private static final TField REWRITE_ENABLED_FIELD_DESC = new TField("rewriteEnabled", (byte)2, (short)15);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)16);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TableStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TableTupleSchemeFactory();
   @Nullable
   private String tableName;
   @Nullable
   private String dbName;
   @Nullable
   private String owner;
   private int createTime;
   private int lastAccessTime;
   private int retention;
   @Nullable
   private StorageDescriptor sd;
   @Nullable
   private List partitionKeys;
   @Nullable
   private Map parameters;
   @Nullable
   private String viewOriginalText;
   @Nullable
   private String viewExpandedText;
   @Nullable
   private String tableType;
   @Nullable
   private PrincipalPrivilegeSet privileges;
   private boolean temporary;
   private boolean rewriteEnabled;
   @Nullable
   private String catName;
   private static final int __CREATETIME_ISSET_ID = 0;
   private static final int __LASTACCESSTIME_ISSET_ID = 1;
   private static final int __RETENTION_ISSET_ID = 2;
   private static final int __TEMPORARY_ISSET_ID = 3;
   private static final int __REWRITEENABLED_ISSET_ID = 4;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public Table() {
      this.__isset_bitfield = 0;
      this.temporary = false;
   }

   public Table(String tableName, String dbName, String owner, int createTime, int lastAccessTime, int retention, StorageDescriptor sd, List partitionKeys, Map parameters, String viewOriginalText, String viewExpandedText, String tableType) {
      this();
      this.tableName = tableName;
      this.dbName = dbName;
      this.owner = owner;
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.lastAccessTime = lastAccessTime;
      this.setLastAccessTimeIsSet(true);
      this.retention = retention;
      this.setRetentionIsSet(true);
      this.sd = sd;
      this.partitionKeys = partitionKeys;
      this.parameters = parameters;
      this.viewOriginalText = viewOriginalText;
      this.viewExpandedText = viewExpandedText;
      this.tableType = tableType;
   }

   public Table(Table other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetTableName()) {
         this.tableName = other.tableName;
      }

      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetOwner()) {
         this.owner = other.owner;
      }

      this.createTime = other.createTime;
      this.lastAccessTime = other.lastAccessTime;
      this.retention = other.retention;
      if (other.isSetSd()) {
         this.sd = new StorageDescriptor(other.sd);
      }

      if (other.isSetPartitionKeys()) {
         List<FieldSchema> __this__partitionKeys = new ArrayList(other.partitionKeys.size());

         for(FieldSchema other_element : other.partitionKeys) {
            __this__partitionKeys.add(new FieldSchema(other_element));
         }

         this.partitionKeys = __this__partitionKeys;
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

      if (other.isSetViewOriginalText()) {
         this.viewOriginalText = other.viewOriginalText;
      }

      if (other.isSetViewExpandedText()) {
         this.viewExpandedText = other.viewExpandedText;
      }

      if (other.isSetTableType()) {
         this.tableType = other.tableType;
      }

      if (other.isSetPrivileges()) {
         this.privileges = new PrincipalPrivilegeSet(other.privileges);
      }

      this.temporary = other.temporary;
      this.rewriteEnabled = other.rewriteEnabled;
      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public Table deepCopy() {
      return new Table(this);
   }

   public void clear() {
      this.tableName = null;
      this.dbName = null;
      this.owner = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.setLastAccessTimeIsSet(false);
      this.lastAccessTime = 0;
      this.setRetentionIsSet(false);
      this.retention = 0;
      this.sd = null;
      this.partitionKeys = null;
      this.parameters = null;
      this.viewOriginalText = null;
      this.viewExpandedText = null;
      this.tableType = null;
      this.privileges = null;
      this.temporary = false;
      this.setRewriteEnabledIsSet(false);
      this.rewriteEnabled = false;
      this.catName = null;
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
   public String getOwner() {
      return this.owner;
   }

   public void setOwner(@Nullable String owner) {
      this.owner = owner;
   }

   public void unsetOwner() {
      this.owner = null;
   }

   public boolean isSetOwner() {
      return this.owner != null;
   }

   public void setOwnerIsSet(boolean value) {
      if (!value) {
         this.owner = null;
      }

   }

   public int getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(int createTime) {
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
   }

   public void unsetCreateTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetCreateTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setCreateTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getLastAccessTime() {
      return this.lastAccessTime;
   }

   public void setLastAccessTime(int lastAccessTime) {
      this.lastAccessTime = lastAccessTime;
      this.setLastAccessTimeIsSet(true);
   }

   public void unsetLastAccessTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetLastAccessTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setLastAccessTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public int getRetention() {
      return this.retention;
   }

   public void setRetention(int retention) {
      this.retention = retention;
      this.setRetentionIsSet(true);
   }

   public void unsetRetention() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetRetention() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setRetentionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   @Nullable
   public StorageDescriptor getSd() {
      return this.sd;
   }

   public void setSd(@Nullable StorageDescriptor sd) {
      this.sd = sd;
   }

   public void unsetSd() {
      this.sd = null;
   }

   public boolean isSetSd() {
      return this.sd != null;
   }

   public void setSdIsSet(boolean value) {
      if (!value) {
         this.sd = null;
      }

   }

   public int getPartitionKeysSize() {
      return this.partitionKeys == null ? 0 : this.partitionKeys.size();
   }

   @Nullable
   public Iterator getPartitionKeysIterator() {
      return this.partitionKeys == null ? null : this.partitionKeys.iterator();
   }

   public void addToPartitionKeys(FieldSchema elem) {
      if (this.partitionKeys == null) {
         this.partitionKeys = new ArrayList();
      }

      this.partitionKeys.add(elem);
   }

   @Nullable
   public List getPartitionKeys() {
      return this.partitionKeys;
   }

   public void setPartitionKeys(@Nullable List partitionKeys) {
      this.partitionKeys = partitionKeys;
   }

   public void unsetPartitionKeys() {
      this.partitionKeys = null;
   }

   public boolean isSetPartitionKeys() {
      return this.partitionKeys != null;
   }

   public void setPartitionKeysIsSet(boolean value) {
      if (!value) {
         this.partitionKeys = null;
      }

   }

   public int getParametersSize() {
      return this.parameters == null ? 0 : this.parameters.size();
   }

   public void putToParameters(String key, String val) {
      if (this.parameters == null) {
         this.parameters = new HashMap();
      }

      this.parameters.put(key, val);
   }

   @Nullable
   public Map getParameters() {
      return this.parameters;
   }

   public void setParameters(@Nullable Map parameters) {
      this.parameters = parameters;
   }

   public void unsetParameters() {
      this.parameters = null;
   }

   public boolean isSetParameters() {
      return this.parameters != null;
   }

   public void setParametersIsSet(boolean value) {
      if (!value) {
         this.parameters = null;
      }

   }

   @Nullable
   public String getViewOriginalText() {
      return this.viewOriginalText;
   }

   public void setViewOriginalText(@Nullable String viewOriginalText) {
      this.viewOriginalText = viewOriginalText;
   }

   public void unsetViewOriginalText() {
      this.viewOriginalText = null;
   }

   public boolean isSetViewOriginalText() {
      return this.viewOriginalText != null;
   }

   public void setViewOriginalTextIsSet(boolean value) {
      if (!value) {
         this.viewOriginalText = null;
      }

   }

   @Nullable
   public String getViewExpandedText() {
      return this.viewExpandedText;
   }

   public void setViewExpandedText(@Nullable String viewExpandedText) {
      this.viewExpandedText = viewExpandedText;
   }

   public void unsetViewExpandedText() {
      this.viewExpandedText = null;
   }

   public boolean isSetViewExpandedText() {
      return this.viewExpandedText != null;
   }

   public void setViewExpandedTextIsSet(boolean value) {
      if (!value) {
         this.viewExpandedText = null;
      }

   }

   @Nullable
   public String getTableType() {
      return this.tableType;
   }

   public void setTableType(@Nullable String tableType) {
      this.tableType = tableType;
   }

   public void unsetTableType() {
      this.tableType = null;
   }

   public boolean isSetTableType() {
      return this.tableType != null;
   }

   public void setTableTypeIsSet(boolean value) {
      if (!value) {
         this.tableType = null;
      }

   }

   @Nullable
   public PrincipalPrivilegeSet getPrivileges() {
      return this.privileges;
   }

   public void setPrivileges(@Nullable PrincipalPrivilegeSet privileges) {
      this.privileges = privileges;
   }

   public void unsetPrivileges() {
      this.privileges = null;
   }

   public boolean isSetPrivileges() {
      return this.privileges != null;
   }

   public void setPrivilegesIsSet(boolean value) {
      if (!value) {
         this.privileges = null;
      }

   }

   public boolean isTemporary() {
      return this.temporary;
   }

   public void setTemporary(boolean temporary) {
      this.temporary = temporary;
      this.setTemporaryIsSet(true);
   }

   public void unsetTemporary() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetTemporary() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setTemporaryIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   public boolean isRewriteEnabled() {
      return this.rewriteEnabled;
   }

   public void setRewriteEnabled(boolean rewriteEnabled) {
      this.rewriteEnabled = rewriteEnabled;
      this.setRewriteEnabledIsSet(true);
   }

   public void unsetRewriteEnabled() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 4);
   }

   public boolean isSetRewriteEnabled() {
      return EncodingUtils.testBit(this.__isset_bitfield, 4);
   }

   public void setRewriteEnabledIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 4, value);
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
         case TABLE_NAME:
            if (value == null) {
               this.unsetTableName();
            } else {
               this.setTableName((String)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case OWNER:
            if (value == null) {
               this.unsetOwner();
            } else {
               this.setOwner((String)value);
            }
            break;
         case CREATE_TIME:
            if (value == null) {
               this.unsetCreateTime();
            } else {
               this.setCreateTime((Integer)value);
            }
            break;
         case LAST_ACCESS_TIME:
            if (value == null) {
               this.unsetLastAccessTime();
            } else {
               this.setLastAccessTime((Integer)value);
            }
            break;
         case RETENTION:
            if (value == null) {
               this.unsetRetention();
            } else {
               this.setRetention((Integer)value);
            }
            break;
         case SD:
            if (value == null) {
               this.unsetSd();
            } else {
               this.setSd((StorageDescriptor)value);
            }
            break;
         case PARTITION_KEYS:
            if (value == null) {
               this.unsetPartitionKeys();
            } else {
               this.setPartitionKeys((List)value);
            }
            break;
         case PARAMETERS:
            if (value == null) {
               this.unsetParameters();
            } else {
               this.setParameters((Map)value);
            }
            break;
         case VIEW_ORIGINAL_TEXT:
            if (value == null) {
               this.unsetViewOriginalText();
            } else {
               this.setViewOriginalText((String)value);
            }
            break;
         case VIEW_EXPANDED_TEXT:
            if (value == null) {
               this.unsetViewExpandedText();
            } else {
               this.setViewExpandedText((String)value);
            }
            break;
         case TABLE_TYPE:
            if (value == null) {
               this.unsetTableType();
            } else {
               this.setTableType((String)value);
            }
            break;
         case PRIVILEGES:
            if (value == null) {
               this.unsetPrivileges();
            } else {
               this.setPrivileges((PrincipalPrivilegeSet)value);
            }
            break;
         case TEMPORARY:
            if (value == null) {
               this.unsetTemporary();
            } else {
               this.setTemporary((Boolean)value);
            }
            break;
         case REWRITE_ENABLED:
            if (value == null) {
               this.unsetRewriteEnabled();
            } else {
               this.setRewriteEnabled((Boolean)value);
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
         case TABLE_NAME:
            return this.getTableName();
         case DB_NAME:
            return this.getDbName();
         case OWNER:
            return this.getOwner();
         case CREATE_TIME:
            return this.getCreateTime();
         case LAST_ACCESS_TIME:
            return this.getLastAccessTime();
         case RETENTION:
            return this.getRetention();
         case SD:
            return this.getSd();
         case PARTITION_KEYS:
            return this.getPartitionKeys();
         case PARAMETERS:
            return this.getParameters();
         case VIEW_ORIGINAL_TEXT:
            return this.getViewOriginalText();
         case VIEW_EXPANDED_TEXT:
            return this.getViewExpandedText();
         case TABLE_TYPE:
            return this.getTableType();
         case PRIVILEGES:
            return this.getPrivileges();
         case TEMPORARY:
            return this.isTemporary();
         case REWRITE_ENABLED:
            return this.isRewriteEnabled();
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
            case TABLE_NAME:
               return this.isSetTableName();
            case DB_NAME:
               return this.isSetDbName();
            case OWNER:
               return this.isSetOwner();
            case CREATE_TIME:
               return this.isSetCreateTime();
            case LAST_ACCESS_TIME:
               return this.isSetLastAccessTime();
            case RETENTION:
               return this.isSetRetention();
            case SD:
               return this.isSetSd();
            case PARTITION_KEYS:
               return this.isSetPartitionKeys();
            case PARAMETERS:
               return this.isSetParameters();
            case VIEW_ORIGINAL_TEXT:
               return this.isSetViewOriginalText();
            case VIEW_EXPANDED_TEXT:
               return this.isSetViewExpandedText();
            case TABLE_TYPE:
               return this.isSetTableType();
            case PRIVILEGES:
               return this.isSetPrivileges();
            case TEMPORARY:
               return this.isSetTemporary();
            case REWRITE_ENABLED:
               return this.isSetRewriteEnabled();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Table ? this.equals((Table)that) : false;
   }

   public boolean equals(Table that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_owner = this.isSetOwner();
         boolean that_present_owner = that.isSetOwner();
         if (this_present_owner || that_present_owner) {
            if (!this_present_owner || !that_present_owner) {
               return false;
            }

            if (!this.owner.equals(that.owner)) {
               return false;
            }
         }

         boolean this_present_createTime = true;
         boolean that_present_createTime = true;
         if (this_present_createTime || that_present_createTime) {
            if (!this_present_createTime || !that_present_createTime) {
               return false;
            }

            if (this.createTime != that.createTime) {
               return false;
            }
         }

         boolean this_present_lastAccessTime = true;
         boolean that_present_lastAccessTime = true;
         if (this_present_lastAccessTime || that_present_lastAccessTime) {
            if (!this_present_lastAccessTime || !that_present_lastAccessTime) {
               return false;
            }

            if (this.lastAccessTime != that.lastAccessTime) {
               return false;
            }
         }

         boolean this_present_retention = true;
         boolean that_present_retention = true;
         if (this_present_retention || that_present_retention) {
            if (!this_present_retention || !that_present_retention) {
               return false;
            }

            if (this.retention != that.retention) {
               return false;
            }
         }

         boolean this_present_sd = this.isSetSd();
         boolean that_present_sd = that.isSetSd();
         if (this_present_sd || that_present_sd) {
            if (!this_present_sd || !that_present_sd) {
               return false;
            }

            if (!this.sd.equals(that.sd)) {
               return false;
            }
         }

         boolean this_present_partitionKeys = this.isSetPartitionKeys();
         boolean that_present_partitionKeys = that.isSetPartitionKeys();
         if (this_present_partitionKeys || that_present_partitionKeys) {
            if (!this_present_partitionKeys || !that_present_partitionKeys) {
               return false;
            }

            if (!this.partitionKeys.equals(that.partitionKeys)) {
               return false;
            }
         }

         boolean this_present_parameters = this.isSetParameters();
         boolean that_present_parameters = that.isSetParameters();
         if (this_present_parameters || that_present_parameters) {
            if (!this_present_parameters || !that_present_parameters) {
               return false;
            }

            if (!this.parameters.equals(that.parameters)) {
               return false;
            }
         }

         boolean this_present_viewOriginalText = this.isSetViewOriginalText();
         boolean that_present_viewOriginalText = that.isSetViewOriginalText();
         if (this_present_viewOriginalText || that_present_viewOriginalText) {
            if (!this_present_viewOriginalText || !that_present_viewOriginalText) {
               return false;
            }

            if (!this.viewOriginalText.equals(that.viewOriginalText)) {
               return false;
            }
         }

         boolean this_present_viewExpandedText = this.isSetViewExpandedText();
         boolean that_present_viewExpandedText = that.isSetViewExpandedText();
         if (this_present_viewExpandedText || that_present_viewExpandedText) {
            if (!this_present_viewExpandedText || !that_present_viewExpandedText) {
               return false;
            }

            if (!this.viewExpandedText.equals(that.viewExpandedText)) {
               return false;
            }
         }

         boolean this_present_tableType = this.isSetTableType();
         boolean that_present_tableType = that.isSetTableType();
         if (this_present_tableType || that_present_tableType) {
            if (!this_present_tableType || !that_present_tableType) {
               return false;
            }

            if (!this.tableType.equals(that.tableType)) {
               return false;
            }
         }

         boolean this_present_privileges = this.isSetPrivileges();
         boolean that_present_privileges = that.isSetPrivileges();
         if (this_present_privileges || that_present_privileges) {
            if (!this_present_privileges || !that_present_privileges) {
               return false;
            }

            if (!this.privileges.equals(that.privileges)) {
               return false;
            }
         }

         boolean this_present_temporary = this.isSetTemporary();
         boolean that_present_temporary = that.isSetTemporary();
         if (this_present_temporary || that_present_temporary) {
            if (!this_present_temporary || !that_present_temporary) {
               return false;
            }

            if (this.temporary != that.temporary) {
               return false;
            }
         }

         boolean this_present_rewriteEnabled = this.isSetRewriteEnabled();
         boolean that_present_rewriteEnabled = that.isSetRewriteEnabled();
         if (this_present_rewriteEnabled || that_present_rewriteEnabled) {
            if (!this_present_rewriteEnabled || !that_present_rewriteEnabled) {
               return false;
            }

            if (this.rewriteEnabled != that.rewriteEnabled) {
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
      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOwner() ? 131071 : 524287);
      if (this.isSetOwner()) {
         hashCode = hashCode * 8191 + this.owner.hashCode();
      }

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + this.lastAccessTime;
      hashCode = hashCode * 8191 + this.retention;
      hashCode = hashCode * 8191 + (this.isSetSd() ? 131071 : 524287);
      if (this.isSetSd()) {
         hashCode = hashCode * 8191 + this.sd.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionKeys() ? 131071 : 524287);
      if (this.isSetPartitionKeys()) {
         hashCode = hashCode * 8191 + this.partitionKeys.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetViewOriginalText() ? 131071 : 524287);
      if (this.isSetViewOriginalText()) {
         hashCode = hashCode * 8191 + this.viewOriginalText.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetViewExpandedText() ? 131071 : 524287);
      if (this.isSetViewExpandedText()) {
         hashCode = hashCode * 8191 + this.viewExpandedText.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableType() ? 131071 : 524287);
      if (this.isSetTableType()) {
         hashCode = hashCode * 8191 + this.tableType.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrivileges() ? 131071 : 524287);
      if (this.isSetPrivileges()) {
         hashCode = hashCode * 8191 + this.privileges.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTemporary() ? 131071 : 524287);
      if (this.isSetTemporary()) {
         hashCode = hashCode * 8191 + (this.temporary ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetRewriteEnabled() ? 131071 : 524287);
      if (this.isSetRewriteEnabled()) {
         hashCode = hashCode * 8191 + (this.rewriteEnabled ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Table other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

               lastComparison = Boolean.compare(this.isSetOwner(), other.isSetOwner());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetOwner()) {
                     lastComparison = TBaseHelper.compareTo(this.owner, other.owner);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetCreateTime(), other.isSetCreateTime());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetCreateTime()) {
                        lastComparison = TBaseHelper.compareTo(this.createTime, other.createTime);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetLastAccessTime(), other.isSetLastAccessTime());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetLastAccessTime()) {
                           lastComparison = TBaseHelper.compareTo(this.lastAccessTime, other.lastAccessTime);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetRetention(), other.isSetRetention());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetRetention()) {
                              lastComparison = TBaseHelper.compareTo(this.retention, other.retention);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetSd(), other.isSetSd());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetSd()) {
                                 lastComparison = TBaseHelper.compareTo(this.sd, other.sd);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetPartitionKeys(), other.isSetPartitionKeys());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetPartitionKeys()) {
                                    lastComparison = TBaseHelper.compareTo(this.partitionKeys, other.partitionKeys);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetParameters(), other.isSetParameters());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetParameters()) {
                                       lastComparison = TBaseHelper.compareTo(this.parameters, other.parameters);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetViewOriginalText(), other.isSetViewOriginalText());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetViewOriginalText()) {
                                          lastComparison = TBaseHelper.compareTo(this.viewOriginalText, other.viewOriginalText);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetViewExpandedText(), other.isSetViewExpandedText());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetViewExpandedText()) {
                                             lastComparison = TBaseHelper.compareTo(this.viewExpandedText, other.viewExpandedText);
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             }
                                          }

                                          lastComparison = Boolean.compare(this.isSetTableType(), other.isSetTableType());
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          } else {
                                             if (this.isSetTableType()) {
                                                lastComparison = TBaseHelper.compareTo(this.tableType, other.tableType);
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                }
                                             }

                                             lastComparison = Boolean.compare(this.isSetPrivileges(), other.isSetPrivileges());
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             } else {
                                                if (this.isSetPrivileges()) {
                                                   lastComparison = TBaseHelper.compareTo(this.privileges, other.privileges);
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   }
                                                }

                                                lastComparison = Boolean.compare(this.isSetTemporary(), other.isSetTemporary());
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                } else {
                                                   if (this.isSetTemporary()) {
                                                      lastComparison = TBaseHelper.compareTo(this.temporary, other.temporary);
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      }
                                                   }

                                                   lastComparison = Boolean.compare(this.isSetRewriteEnabled(), other.isSetRewriteEnabled());
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   } else {
                                                      if (this.isSetRewriteEnabled()) {
                                                         lastComparison = TBaseHelper.compareTo(this.rewriteEnabled, other.rewriteEnabled);
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
                              }
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
      return Table._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Table(");
      boolean first = true;
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

      sb.append("owner:");
      if (this.owner == null) {
         sb.append("null");
      } else {
         sb.append(this.owner);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("createTime:");
      sb.append(this.createTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("lastAccessTime:");
      sb.append(this.lastAccessTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("retention:");
      sb.append(this.retention);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("sd:");
      if (this.sd == null) {
         sb.append("null");
      } else {
         sb.append(this.sd);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("partitionKeys:");
      if (this.partitionKeys == null) {
         sb.append("null");
      } else {
         sb.append(this.partitionKeys);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("parameters:");
      if (this.parameters == null) {
         sb.append("null");
      } else {
         sb.append(this.parameters);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("viewOriginalText:");
      if (this.viewOriginalText == null) {
         sb.append("null");
      } else {
         sb.append(this.viewOriginalText);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("viewExpandedText:");
      if (this.viewExpandedText == null) {
         sb.append("null");
      } else {
         sb.append(this.viewExpandedText);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tableType:");
      if (this.tableType == null) {
         sb.append("null");
      } else {
         sb.append(this.tableType);
      }

      first = false;
      if (this.isSetPrivileges()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("privileges:");
         if (this.privileges == null) {
            sb.append("null");
         } else {
            sb.append(this.privileges);
         }

         first = false;
      }

      if (this.isSetTemporary()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("temporary:");
         sb.append(this.temporary);
         first = false;
      }

      if (this.isSetRewriteEnabled()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("rewriteEnabled:");
         sb.append(this.rewriteEnabled);
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
      if (this.sd != null) {
         this.sd.validate();
      }

      if (this.privileges != null) {
         this.privileges.validate();
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
      optionals = new _Fields[]{Table._Fields.PRIVILEGES, Table._Fields.TEMPORARY, Table._Fields.REWRITE_ENABLED, Table._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Table._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Table._Fields.DB_NAME, new FieldMetaData("dbName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Table._Fields.OWNER, new FieldMetaData("owner", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Table._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Table._Fields.LAST_ACCESS_TIME, new FieldMetaData("lastAccessTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Table._Fields.RETENTION, new FieldMetaData("retention", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Table._Fields.SD, new FieldMetaData("sd", (byte)3, new StructMetaData((byte)12, StorageDescriptor.class)));
      tmpMap.put(Table._Fields.PARTITION_KEYS, new FieldMetaData("partitionKeys", (byte)3, new ListMetaData((byte)15, new StructMetaData((byte)12, FieldSchema.class))));
      tmpMap.put(Table._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(Table._Fields.VIEW_ORIGINAL_TEXT, new FieldMetaData("viewOriginalText", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Table._Fields.VIEW_EXPANDED_TEXT, new FieldMetaData("viewExpandedText", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Table._Fields.TABLE_TYPE, new FieldMetaData("tableType", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Table._Fields.PRIVILEGES, new FieldMetaData("privileges", (byte)2, new StructMetaData((byte)12, PrincipalPrivilegeSet.class)));
      tmpMap.put(Table._Fields.TEMPORARY, new FieldMetaData("temporary", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(Table._Fields.REWRITE_ENABLED, new FieldMetaData("rewriteEnabled", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(Table._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Table.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TABLE_NAME((short)1, "tableName"),
      DB_NAME((short)2, "dbName"),
      OWNER((short)3, "owner"),
      CREATE_TIME((short)4, "createTime"),
      LAST_ACCESS_TIME((short)5, "lastAccessTime"),
      RETENTION((short)6, "retention"),
      SD((short)7, "sd"),
      PARTITION_KEYS((short)8, "partitionKeys"),
      PARAMETERS((short)9, "parameters"),
      VIEW_ORIGINAL_TEXT((short)10, "viewOriginalText"),
      VIEW_EXPANDED_TEXT((short)11, "viewExpandedText"),
      TABLE_TYPE((short)12, "tableType"),
      PRIVILEGES((short)13, "privileges"),
      TEMPORARY((short)14, "temporary"),
      REWRITE_ENABLED((short)15, "rewriteEnabled"),
      CAT_NAME((short)16, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TABLE_NAME;
            case 2:
               return DB_NAME;
            case 3:
               return OWNER;
            case 4:
               return CREATE_TIME;
            case 5:
               return LAST_ACCESS_TIME;
            case 6:
               return RETENTION;
            case 7:
               return SD;
            case 8:
               return PARTITION_KEYS;
            case 9:
               return PARAMETERS;
            case 10:
               return VIEW_ORIGINAL_TEXT;
            case 11:
               return VIEW_EXPANDED_TEXT;
            case 12:
               return TABLE_TYPE;
            case 13:
               return PRIVILEGES;
            case 14:
               return TEMPORARY;
            case 15:
               return REWRITE_ENABLED;
            case 16:
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

   private static class TableStandardSchemeFactory implements SchemeFactory {
      private TableStandardSchemeFactory() {
      }

      public TableStandardScheme getScheme() {
         return new TableStandardScheme();
      }
   }

   private static class TableStandardScheme extends StandardScheme {
      private TableStandardScheme() {
      }

      public void read(TProtocol iprot, Table struct) throws TException {
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
                     struct.tableName = iprot.readString();
                     struct.setTableNameIsSet(true);
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
                     struct.owner = iprot.readString();
                     struct.setOwnerIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.createTime = iprot.readI32();
                     struct.setCreateTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.lastAccessTime = iprot.readI32();
                     struct.setLastAccessTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.retention = iprot.readI32();
                     struct.setRetentionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 12) {
                     struct.sd = new StorageDescriptor();
                     struct.sd.read(iprot);
                     struct.setSdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list190 = iprot.readListBegin();
                  struct.partitionKeys = new ArrayList(_list190.size);

                  for(int _i192 = 0; _i192 < _list190.size; ++_i192) {
                     FieldSchema _elem191 = new FieldSchema();
                     _elem191.read(iprot);
                     struct.partitionKeys.add(_elem191);
                  }

                  iprot.readListEnd();
                  struct.setPartitionKeysIsSet(true);
                  break;
               case 9:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map193 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map193.size);

                  for(int _i196 = 0; _i196 < _map193.size; ++_i196) {
                     String _key194 = iprot.readString();
                     String _val195 = iprot.readString();
                     struct.parameters.put(_key194, _val195);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               case 10:
                  if (schemeField.type == 11) {
                     struct.viewOriginalText = iprot.readString();
                     struct.setViewOriginalTextIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 11:
                  if (schemeField.type == 11) {
                     struct.viewExpandedText = iprot.readString();
                     struct.setViewExpandedTextIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 12:
                  if (schemeField.type == 11) {
                     struct.tableType = iprot.readString();
                     struct.setTableTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 13:
                  if (schemeField.type == 12) {
                     struct.privileges = new PrincipalPrivilegeSet();
                     struct.privileges.read(iprot);
                     struct.setPrivilegesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 14:
                  if (schemeField.type == 2) {
                     struct.temporary = iprot.readBool();
                     struct.setTemporaryIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 15:
                  if (schemeField.type == 2) {
                     struct.rewriteEnabled = iprot.readBool();
                     struct.setRewriteEnabledIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 16:
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

      public void write(TProtocol oprot, Table struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Table.STRUCT_DESC);
         if (struct.tableName != null) {
            oprot.writeFieldBegin(Table.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null) {
            oprot.writeFieldBegin(Table.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.owner != null) {
            oprot.writeFieldBegin(Table.OWNER_FIELD_DESC);
            oprot.writeString(struct.owner);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Table.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(Table.LAST_ACCESS_TIME_FIELD_DESC);
         oprot.writeI32(struct.lastAccessTime);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(Table.RETENTION_FIELD_DESC);
         oprot.writeI32(struct.retention);
         oprot.writeFieldEnd();
         if (struct.sd != null) {
            oprot.writeFieldBegin(Table.SD_FIELD_DESC);
            struct.sd.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.partitionKeys != null) {
            oprot.writeFieldBegin(Table.PARTITION_KEYS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.partitionKeys.size()));

            for(FieldSchema _iter197 : struct.partitionKeys) {
               _iter197.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(Table.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter198 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter198.getKey());
               oprot.writeString((String)_iter198.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.viewOriginalText != null) {
            oprot.writeFieldBegin(Table.VIEW_ORIGINAL_TEXT_FIELD_DESC);
            oprot.writeString(struct.viewOriginalText);
            oprot.writeFieldEnd();
         }

         if (struct.viewExpandedText != null) {
            oprot.writeFieldBegin(Table.VIEW_EXPANDED_TEXT_FIELD_DESC);
            oprot.writeString(struct.viewExpandedText);
            oprot.writeFieldEnd();
         }

         if (struct.tableType != null) {
            oprot.writeFieldBegin(Table.TABLE_TYPE_FIELD_DESC);
            oprot.writeString(struct.tableType);
            oprot.writeFieldEnd();
         }

         if (struct.privileges != null && struct.isSetPrivileges()) {
            oprot.writeFieldBegin(Table.PRIVILEGES_FIELD_DESC);
            struct.privileges.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetTemporary()) {
            oprot.writeFieldBegin(Table.TEMPORARY_FIELD_DESC);
            oprot.writeBool(struct.temporary);
            oprot.writeFieldEnd();
         }

         if (struct.isSetRewriteEnabled()) {
            oprot.writeFieldBegin(Table.REWRITE_ENABLED_FIELD_DESC);
            oprot.writeBool(struct.rewriteEnabled);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(Table.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TableTupleSchemeFactory implements SchemeFactory {
      private TableTupleSchemeFactory() {
      }

      public TableTupleScheme getScheme() {
         return new TableTupleScheme();
      }
   }

   private static class TableTupleScheme extends TupleScheme {
      private TableTupleScheme() {
      }

      public void write(TProtocol prot, Table struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetTableName()) {
            optionals.set(0);
         }

         if (struct.isSetDbName()) {
            optionals.set(1);
         }

         if (struct.isSetOwner()) {
            optionals.set(2);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(3);
         }

         if (struct.isSetLastAccessTime()) {
            optionals.set(4);
         }

         if (struct.isSetRetention()) {
            optionals.set(5);
         }

         if (struct.isSetSd()) {
            optionals.set(6);
         }

         if (struct.isSetPartitionKeys()) {
            optionals.set(7);
         }

         if (struct.isSetParameters()) {
            optionals.set(8);
         }

         if (struct.isSetViewOriginalText()) {
            optionals.set(9);
         }

         if (struct.isSetViewExpandedText()) {
            optionals.set(10);
         }

         if (struct.isSetTableType()) {
            optionals.set(11);
         }

         if (struct.isSetPrivileges()) {
            optionals.set(12);
         }

         if (struct.isSetTemporary()) {
            optionals.set(13);
         }

         if (struct.isSetRewriteEnabled()) {
            optionals.set(14);
         }

         if (struct.isSetCatName()) {
            optionals.set(15);
         }

         oprot.writeBitSet(optionals, 16);
         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetOwner()) {
            oprot.writeString(struct.owner);
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetLastAccessTime()) {
            oprot.writeI32(struct.lastAccessTime);
         }

         if (struct.isSetRetention()) {
            oprot.writeI32(struct.retention);
         }

         if (struct.isSetSd()) {
            struct.sd.write(oprot);
         }

         if (struct.isSetPartitionKeys()) {
            oprot.writeI32(struct.partitionKeys.size());

            for(FieldSchema _iter199 : struct.partitionKeys) {
               _iter199.write(oprot);
            }
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter200 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter200.getKey());
               oprot.writeString((String)_iter200.getValue());
            }
         }

         if (struct.isSetViewOriginalText()) {
            oprot.writeString(struct.viewOriginalText);
         }

         if (struct.isSetViewExpandedText()) {
            oprot.writeString(struct.viewExpandedText);
         }

         if (struct.isSetTableType()) {
            oprot.writeString(struct.tableType);
         }

         if (struct.isSetPrivileges()) {
            struct.privileges.write(oprot);
         }

         if (struct.isSetTemporary()) {
            oprot.writeBool(struct.temporary);
         }

         if (struct.isSetRewriteEnabled()) {
            oprot.writeBool(struct.rewriteEnabled);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, Table struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(16);
         if (incoming.get(0)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.owner = iprot.readString();
            struct.setOwnerIsSet(true);
         }

         if (incoming.get(3)) {
            struct.createTime = iprot.readI32();
            struct.setCreateTimeIsSet(true);
         }

         if (incoming.get(4)) {
            struct.lastAccessTime = iprot.readI32();
            struct.setLastAccessTimeIsSet(true);
         }

         if (incoming.get(5)) {
            struct.retention = iprot.readI32();
            struct.setRetentionIsSet(true);
         }

         if (incoming.get(6)) {
            struct.sd = new StorageDescriptor();
            struct.sd.read(iprot);
            struct.setSdIsSet(true);
         }

         if (incoming.get(7)) {
            TList _list201 = iprot.readListBegin((byte)12);
            struct.partitionKeys = new ArrayList(_list201.size);

            for(int _i203 = 0; _i203 < _list201.size; ++_i203) {
               FieldSchema _elem202 = new FieldSchema();
               _elem202.read(iprot);
               struct.partitionKeys.add(_elem202);
            }

            struct.setPartitionKeysIsSet(true);
         }

         if (incoming.get(8)) {
            TMap _map204 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map204.size);

            for(int _i207 = 0; _i207 < _map204.size; ++_i207) {
               String _key205 = iprot.readString();
               String _val206 = iprot.readString();
               struct.parameters.put(_key205, _val206);
            }

            struct.setParametersIsSet(true);
         }

         if (incoming.get(9)) {
            struct.viewOriginalText = iprot.readString();
            struct.setViewOriginalTextIsSet(true);
         }

         if (incoming.get(10)) {
            struct.viewExpandedText = iprot.readString();
            struct.setViewExpandedTextIsSet(true);
         }

         if (incoming.get(11)) {
            struct.tableType = iprot.readString();
            struct.setTableTypeIsSet(true);
         }

         if (incoming.get(12)) {
            struct.privileges = new PrincipalPrivilegeSet();
            struct.privileges.read(iprot);
            struct.setPrivilegesIsSet(true);
         }

         if (incoming.get(13)) {
            struct.temporary = iprot.readBool();
            struct.setTemporaryIsSet(true);
         }

         if (incoming.get(14)) {
            struct.rewriteEnabled = iprot.readBool();
            struct.setRewriteEnabledIsSet(true);
         }

         if (incoming.get(15)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
