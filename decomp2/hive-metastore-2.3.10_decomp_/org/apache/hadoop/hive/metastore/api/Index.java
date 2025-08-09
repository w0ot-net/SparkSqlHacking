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
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
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

public class Index implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Index");
   private static final TField INDEX_NAME_FIELD_DESC = new TField("indexName", (byte)11, (short)1);
   private static final TField INDEX_HANDLER_CLASS_FIELD_DESC = new TField("indexHandlerClass", (byte)11, (short)2);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)3);
   private static final TField ORIG_TABLE_NAME_FIELD_DESC = new TField("origTableName", (byte)11, (short)4);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)5);
   private static final TField LAST_ACCESS_TIME_FIELD_DESC = new TField("lastAccessTime", (byte)8, (short)6);
   private static final TField INDEX_TABLE_NAME_FIELD_DESC = new TField("indexTableName", (byte)11, (short)7);
   private static final TField SD_FIELD_DESC = new TField("sd", (byte)12, (short)8);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)9);
   private static final TField DEFERRED_REBUILD_FIELD_DESC = new TField("deferredRebuild", (byte)2, (short)10);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new IndexStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new IndexTupleSchemeFactory();
   @Nullable
   private String indexName;
   @Nullable
   private String indexHandlerClass;
   @Nullable
   private String dbName;
   @Nullable
   private String origTableName;
   private int createTime;
   private int lastAccessTime;
   @Nullable
   private String indexTableName;
   @Nullable
   private StorageDescriptor sd;
   @Nullable
   private Map parameters;
   private boolean deferredRebuild;
   private static final int __CREATETIME_ISSET_ID = 0;
   private static final int __LASTACCESSTIME_ISSET_ID = 1;
   private static final int __DEFERREDREBUILD_ISSET_ID = 2;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public Index() {
      this.__isset_bitfield = 0;
   }

   public Index(String indexName, String indexHandlerClass, String dbName, String origTableName, int createTime, int lastAccessTime, String indexTableName, StorageDescriptor sd, Map parameters, boolean deferredRebuild) {
      this();
      this.indexName = indexName;
      this.indexHandlerClass = indexHandlerClass;
      this.dbName = dbName;
      this.origTableName = origTableName;
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.lastAccessTime = lastAccessTime;
      this.setLastAccessTimeIsSet(true);
      this.indexTableName = indexTableName;
      this.sd = sd;
      this.parameters = parameters;
      this.deferredRebuild = deferredRebuild;
      this.setDeferredRebuildIsSet(true);
   }

   public Index(Index other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetIndexName()) {
         this.indexName = other.indexName;
      }

      if (other.isSetIndexHandlerClass()) {
         this.indexHandlerClass = other.indexHandlerClass;
      }

      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetOrigTableName()) {
         this.origTableName = other.origTableName;
      }

      this.createTime = other.createTime;
      this.lastAccessTime = other.lastAccessTime;
      if (other.isSetIndexTableName()) {
         this.indexTableName = other.indexTableName;
      }

      if (other.isSetSd()) {
         this.sd = new StorageDescriptor(other.sd);
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

      this.deferredRebuild = other.deferredRebuild;
   }

   public Index deepCopy() {
      return new Index(this);
   }

   public void clear() {
      this.indexName = null;
      this.indexHandlerClass = null;
      this.dbName = null;
      this.origTableName = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.setLastAccessTimeIsSet(false);
      this.lastAccessTime = 0;
      this.indexTableName = null;
      this.sd = null;
      this.parameters = null;
      this.setDeferredRebuildIsSet(false);
      this.deferredRebuild = false;
   }

   @Nullable
   public String getIndexName() {
      return this.indexName;
   }

   public void setIndexName(@Nullable String indexName) {
      this.indexName = indexName;
   }

   public void unsetIndexName() {
      this.indexName = null;
   }

   public boolean isSetIndexName() {
      return this.indexName != null;
   }

   public void setIndexNameIsSet(boolean value) {
      if (!value) {
         this.indexName = null;
      }

   }

   @Nullable
   public String getIndexHandlerClass() {
      return this.indexHandlerClass;
   }

   public void setIndexHandlerClass(@Nullable String indexHandlerClass) {
      this.indexHandlerClass = indexHandlerClass;
   }

   public void unsetIndexHandlerClass() {
      this.indexHandlerClass = null;
   }

   public boolean isSetIndexHandlerClass() {
      return this.indexHandlerClass != null;
   }

   public void setIndexHandlerClassIsSet(boolean value) {
      if (!value) {
         this.indexHandlerClass = null;
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
   public String getOrigTableName() {
      return this.origTableName;
   }

   public void setOrigTableName(@Nullable String origTableName) {
      this.origTableName = origTableName;
   }

   public void unsetOrigTableName() {
      this.origTableName = null;
   }

   public boolean isSetOrigTableName() {
      return this.origTableName != null;
   }

   public void setOrigTableNameIsSet(boolean value) {
      if (!value) {
         this.origTableName = null;
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

   @Nullable
   public String getIndexTableName() {
      return this.indexTableName;
   }

   public void setIndexTableName(@Nullable String indexTableName) {
      this.indexTableName = indexTableName;
   }

   public void unsetIndexTableName() {
      this.indexTableName = null;
   }

   public boolean isSetIndexTableName() {
      return this.indexTableName != null;
   }

   public void setIndexTableNameIsSet(boolean value) {
      if (!value) {
         this.indexTableName = null;
      }

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

   public boolean isDeferredRebuild() {
      return this.deferredRebuild;
   }

   public void setDeferredRebuild(boolean deferredRebuild) {
      this.deferredRebuild = deferredRebuild;
      this.setDeferredRebuildIsSet(true);
   }

   public void unsetDeferredRebuild() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetDeferredRebuild() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setDeferredRebuildIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case INDEX_NAME:
            if (value == null) {
               this.unsetIndexName();
            } else {
               this.setIndexName((String)value);
            }
            break;
         case INDEX_HANDLER_CLASS:
            if (value == null) {
               this.unsetIndexHandlerClass();
            } else {
               this.setIndexHandlerClass((String)value);
            }
            break;
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case ORIG_TABLE_NAME:
            if (value == null) {
               this.unsetOrigTableName();
            } else {
               this.setOrigTableName((String)value);
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
         case INDEX_TABLE_NAME:
            if (value == null) {
               this.unsetIndexTableName();
            } else {
               this.setIndexTableName((String)value);
            }
            break;
         case SD:
            if (value == null) {
               this.unsetSd();
            } else {
               this.setSd((StorageDescriptor)value);
            }
            break;
         case PARAMETERS:
            if (value == null) {
               this.unsetParameters();
            } else {
               this.setParameters((Map)value);
            }
            break;
         case DEFERRED_REBUILD:
            if (value == null) {
               this.unsetDeferredRebuild();
            } else {
               this.setDeferredRebuild((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case INDEX_NAME:
            return this.getIndexName();
         case INDEX_HANDLER_CLASS:
            return this.getIndexHandlerClass();
         case DB_NAME:
            return this.getDbName();
         case ORIG_TABLE_NAME:
            return this.getOrigTableName();
         case CREATE_TIME:
            return this.getCreateTime();
         case LAST_ACCESS_TIME:
            return this.getLastAccessTime();
         case INDEX_TABLE_NAME:
            return this.getIndexTableName();
         case SD:
            return this.getSd();
         case PARAMETERS:
            return this.getParameters();
         case DEFERRED_REBUILD:
            return this.isDeferredRebuild();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case INDEX_NAME:
               return this.isSetIndexName();
            case INDEX_HANDLER_CLASS:
               return this.isSetIndexHandlerClass();
            case DB_NAME:
               return this.isSetDbName();
            case ORIG_TABLE_NAME:
               return this.isSetOrigTableName();
            case CREATE_TIME:
               return this.isSetCreateTime();
            case LAST_ACCESS_TIME:
               return this.isSetLastAccessTime();
            case INDEX_TABLE_NAME:
               return this.isSetIndexTableName();
            case SD:
               return this.isSetSd();
            case PARAMETERS:
               return this.isSetParameters();
            case DEFERRED_REBUILD:
               return this.isSetDeferredRebuild();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Index ? this.equals((Index)that) : false;
   }

   public boolean equals(Index that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_indexName = this.isSetIndexName();
         boolean that_present_indexName = that.isSetIndexName();
         if (this_present_indexName || that_present_indexName) {
            if (!this_present_indexName || !that_present_indexName) {
               return false;
            }

            if (!this.indexName.equals(that.indexName)) {
               return false;
            }
         }

         boolean this_present_indexHandlerClass = this.isSetIndexHandlerClass();
         boolean that_present_indexHandlerClass = that.isSetIndexHandlerClass();
         if (this_present_indexHandlerClass || that_present_indexHandlerClass) {
            if (!this_present_indexHandlerClass || !that_present_indexHandlerClass) {
               return false;
            }

            if (!this.indexHandlerClass.equals(that.indexHandlerClass)) {
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

         boolean this_present_origTableName = this.isSetOrigTableName();
         boolean that_present_origTableName = that.isSetOrigTableName();
         if (this_present_origTableName || that_present_origTableName) {
            if (!this_present_origTableName || !that_present_origTableName) {
               return false;
            }

            if (!this.origTableName.equals(that.origTableName)) {
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

         boolean this_present_indexTableName = this.isSetIndexTableName();
         boolean that_present_indexTableName = that.isSetIndexTableName();
         if (this_present_indexTableName || that_present_indexTableName) {
            if (!this_present_indexTableName || !that_present_indexTableName) {
               return false;
            }

            if (!this.indexTableName.equals(that.indexTableName)) {
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

         boolean this_present_deferredRebuild = true;
         boolean that_present_deferredRebuild = true;
         if (this_present_deferredRebuild || that_present_deferredRebuild) {
            if (!this_present_deferredRebuild || !that_present_deferredRebuild) {
               return false;
            }

            if (this.deferredRebuild != that.deferredRebuild) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetIndexName() ? 131071 : 524287);
      if (this.isSetIndexName()) {
         hashCode = hashCode * 8191 + this.indexName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetIndexHandlerClass() ? 131071 : 524287);
      if (this.isSetIndexHandlerClass()) {
         hashCode = hashCode * 8191 + this.indexHandlerClass.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOrigTableName() ? 131071 : 524287);
      if (this.isSetOrigTableName()) {
         hashCode = hashCode * 8191 + this.origTableName.hashCode();
      }

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + this.lastAccessTime;
      hashCode = hashCode * 8191 + (this.isSetIndexTableName() ? 131071 : 524287);
      if (this.isSetIndexTableName()) {
         hashCode = hashCode * 8191 + this.indexTableName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSd() ? 131071 : 524287);
      if (this.isSetSd()) {
         hashCode = hashCode * 8191 + this.sd.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      hashCode = hashCode * 8191 + (this.deferredRebuild ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(Index other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetIndexName(), other.isSetIndexName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetIndexName()) {
               lastComparison = TBaseHelper.compareTo(this.indexName, other.indexName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetIndexHandlerClass(), other.isSetIndexHandlerClass());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetIndexHandlerClass()) {
                  lastComparison = TBaseHelper.compareTo(this.indexHandlerClass, other.indexHandlerClass);
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

                  lastComparison = Boolean.compare(this.isSetOrigTableName(), other.isSetOrigTableName());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetOrigTableName()) {
                        lastComparison = TBaseHelper.compareTo(this.origTableName, other.origTableName);
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

                           lastComparison = Boolean.compare(this.isSetIndexTableName(), other.isSetIndexTableName());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetIndexTableName()) {
                                 lastComparison = TBaseHelper.compareTo(this.indexTableName, other.indexTableName);
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

                                    lastComparison = Boolean.compare(this.isSetDeferredRebuild(), other.isSetDeferredRebuild());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetDeferredRebuild()) {
                                          lastComparison = TBaseHelper.compareTo(this.deferredRebuild, other.deferredRebuild);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Index._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Index(");
      boolean first = true;
      sb.append("indexName:");
      if (this.indexName == null) {
         sb.append("null");
      } else {
         sb.append(this.indexName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("indexHandlerClass:");
      if (this.indexHandlerClass == null) {
         sb.append("null");
      } else {
         sb.append(this.indexHandlerClass);
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

      sb.append("origTableName:");
      if (this.origTableName == null) {
         sb.append("null");
      } else {
         sb.append(this.origTableName);
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

      sb.append("indexTableName:");
      if (this.indexTableName == null) {
         sb.append("null");
      } else {
         sb.append(this.indexTableName);
      }

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

      sb.append("deferredRebuild:");
      sb.append(this.deferredRebuild);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.sd != null) {
         this.sd.validate();
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
      tmpMap.put(Index._Fields.INDEX_NAME, new FieldMetaData("indexName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Index._Fields.INDEX_HANDLER_CLASS, new FieldMetaData("indexHandlerClass", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Index._Fields.DB_NAME, new FieldMetaData("dbName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Index._Fields.ORIG_TABLE_NAME, new FieldMetaData("origTableName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Index._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Index._Fields.LAST_ACCESS_TIME, new FieldMetaData("lastAccessTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Index._Fields.INDEX_TABLE_NAME, new FieldMetaData("indexTableName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Index._Fields.SD, new FieldMetaData("sd", (byte)3, new StructMetaData((byte)12, StorageDescriptor.class)));
      tmpMap.put(Index._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(Index._Fields.DEFERRED_REBUILD, new FieldMetaData("deferredRebuild", (byte)3, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Index.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      INDEX_NAME((short)1, "indexName"),
      INDEX_HANDLER_CLASS((short)2, "indexHandlerClass"),
      DB_NAME((short)3, "dbName"),
      ORIG_TABLE_NAME((short)4, "origTableName"),
      CREATE_TIME((short)5, "createTime"),
      LAST_ACCESS_TIME((short)6, "lastAccessTime"),
      INDEX_TABLE_NAME((short)7, "indexTableName"),
      SD((short)8, "sd"),
      PARAMETERS((short)9, "parameters"),
      DEFERRED_REBUILD((short)10, "deferredRebuild");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return INDEX_NAME;
            case 2:
               return INDEX_HANDLER_CLASS;
            case 3:
               return DB_NAME;
            case 4:
               return ORIG_TABLE_NAME;
            case 5:
               return CREATE_TIME;
            case 6:
               return LAST_ACCESS_TIME;
            case 7:
               return INDEX_TABLE_NAME;
            case 8:
               return SD;
            case 9:
               return PARAMETERS;
            case 10:
               return DEFERRED_REBUILD;
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

   private static class IndexStandardSchemeFactory implements SchemeFactory {
      private IndexStandardSchemeFactory() {
      }

      public IndexStandardScheme getScheme() {
         return new IndexStandardScheme();
      }
   }

   private static class IndexStandardScheme extends StandardScheme {
      private IndexStandardScheme() {
      }

      public void read(TProtocol iprot, Index struct) throws TException {
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
                     struct.indexName = iprot.readString();
                     struct.setIndexNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.indexHandlerClass = iprot.readString();
                     struct.setIndexHandlerClassIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.origTableName = iprot.readString();
                     struct.setOrigTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.createTime = iprot.readI32();
                     struct.setCreateTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.lastAccessTime = iprot.readI32();
                     struct.setLastAccessTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 11) {
                     struct.indexTableName = iprot.readString();
                     struct.setIndexTableNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.sd = new StorageDescriptor();
                     struct.sd.read(iprot);
                     struct.setSdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map260 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map260.size);

                  for(int _i263 = 0; _i263 < _map260.size; ++_i263) {
                     String _key261 = iprot.readString();
                     String _val262 = iprot.readString();
                     struct.parameters.put(_key261, _val262);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               case 10:
                  if (schemeField.type == 2) {
                     struct.deferredRebuild = iprot.readBool();
                     struct.setDeferredRebuildIsSet(true);
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

      public void write(TProtocol oprot, Index struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Index.STRUCT_DESC);
         if (struct.indexName != null) {
            oprot.writeFieldBegin(Index.INDEX_NAME_FIELD_DESC);
            oprot.writeString(struct.indexName);
            oprot.writeFieldEnd();
         }

         if (struct.indexHandlerClass != null) {
            oprot.writeFieldBegin(Index.INDEX_HANDLER_CLASS_FIELD_DESC);
            oprot.writeString(struct.indexHandlerClass);
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null) {
            oprot.writeFieldBegin(Index.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.origTableName != null) {
            oprot.writeFieldBegin(Index.ORIG_TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.origTableName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Index.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(Index.LAST_ACCESS_TIME_FIELD_DESC);
         oprot.writeI32(struct.lastAccessTime);
         oprot.writeFieldEnd();
         if (struct.indexTableName != null) {
            oprot.writeFieldBegin(Index.INDEX_TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.indexTableName);
            oprot.writeFieldEnd();
         }

         if (struct.sd != null) {
            oprot.writeFieldBegin(Index.SD_FIELD_DESC);
            struct.sd.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(Index.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter264 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter264.getKey());
               oprot.writeString((String)_iter264.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Index.DEFERRED_REBUILD_FIELD_DESC);
         oprot.writeBool(struct.deferredRebuild);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class IndexTupleSchemeFactory implements SchemeFactory {
      private IndexTupleSchemeFactory() {
      }

      public IndexTupleScheme getScheme() {
         return new IndexTupleScheme();
      }
   }

   private static class IndexTupleScheme extends TupleScheme {
      private IndexTupleScheme() {
      }

      public void write(TProtocol prot, Index struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetIndexName()) {
            optionals.set(0);
         }

         if (struct.isSetIndexHandlerClass()) {
            optionals.set(1);
         }

         if (struct.isSetDbName()) {
            optionals.set(2);
         }

         if (struct.isSetOrigTableName()) {
            optionals.set(3);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(4);
         }

         if (struct.isSetLastAccessTime()) {
            optionals.set(5);
         }

         if (struct.isSetIndexTableName()) {
            optionals.set(6);
         }

         if (struct.isSetSd()) {
            optionals.set(7);
         }

         if (struct.isSetParameters()) {
            optionals.set(8);
         }

         if (struct.isSetDeferredRebuild()) {
            optionals.set(9);
         }

         oprot.writeBitSet(optionals, 10);
         if (struct.isSetIndexName()) {
            oprot.writeString(struct.indexName);
         }

         if (struct.isSetIndexHandlerClass()) {
            oprot.writeString(struct.indexHandlerClass);
         }

         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetOrigTableName()) {
            oprot.writeString(struct.origTableName);
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetLastAccessTime()) {
            oprot.writeI32(struct.lastAccessTime);
         }

         if (struct.isSetIndexTableName()) {
            oprot.writeString(struct.indexTableName);
         }

         if (struct.isSetSd()) {
            struct.sd.write(oprot);
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter265 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter265.getKey());
               oprot.writeString((String)_iter265.getValue());
            }
         }

         if (struct.isSetDeferredRebuild()) {
            oprot.writeBool(struct.deferredRebuild);
         }

      }

      public void read(TProtocol prot, Index struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(10);
         if (incoming.get(0)) {
            struct.indexName = iprot.readString();
            struct.setIndexNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.indexHandlerClass = iprot.readString();
            struct.setIndexHandlerClassIsSet(true);
         }

         if (incoming.get(2)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.origTableName = iprot.readString();
            struct.setOrigTableNameIsSet(true);
         }

         if (incoming.get(4)) {
            struct.createTime = iprot.readI32();
            struct.setCreateTimeIsSet(true);
         }

         if (incoming.get(5)) {
            struct.lastAccessTime = iprot.readI32();
            struct.setLastAccessTimeIsSet(true);
         }

         if (incoming.get(6)) {
            struct.indexTableName = iprot.readString();
            struct.setIndexTableNameIsSet(true);
         }

         if (incoming.get(7)) {
            struct.sd = new StorageDescriptor();
            struct.sd.read(iprot);
            struct.setSdIsSet(true);
         }

         if (incoming.get(8)) {
            TMap _map266 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map266.size);

            for(int _i269 = 0; _i269 < _map266.size; ++_i269) {
               String _key267 = iprot.readString();
               String _val268 = iprot.readString();
               struct.parameters.put(_key267, _val268);
            }

            struct.setParametersIsSet(true);
         }

         if (incoming.get(9)) {
            struct.deferredRebuild = iprot.readBool();
            struct.setDeferredRebuildIsSet(true);
         }

      }
   }
}
