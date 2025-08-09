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
import org.apache.hive.common.util.HiveStringUtils;
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

public class Partition implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Partition");
   private static final TField VALUES_FIELD_DESC = new TField("values", (byte)15, (short)1);
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)2);
   private static final TField TABLE_NAME_FIELD_DESC = new TField("tableName", (byte)11, (short)3);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)4);
   private static final TField LAST_ACCESS_TIME_FIELD_DESC = new TField("lastAccessTime", (byte)8, (short)5);
   private static final TField SD_FIELD_DESC = new TField("sd", (byte)12, (short)6);
   private static final TField PARAMETERS_FIELD_DESC = new TField("parameters", (byte)13, (short)7);
   private static final TField PRIVILEGES_FIELD_DESC = new TField("privileges", (byte)12, (short)8);
   private static final TField CAT_NAME_FIELD_DESC = new TField("catName", (byte)11, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PartitionStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PartitionTupleSchemeFactory();
   @Nullable
   private List values;
   @Nullable
   private String dbName;
   @Nullable
   private String tableName;
   private int createTime;
   private int lastAccessTime;
   @Nullable
   private StorageDescriptor sd;
   @Nullable
   private Map parameters;
   @Nullable
   private PrincipalPrivilegeSet privileges;
   @Nullable
   private String catName;
   private static final int __CREATETIME_ISSET_ID = 0;
   private static final int __LASTACCESSTIME_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public Partition() {
      this.__isset_bitfield = 0;
   }

   public Partition(List values, String dbName, String tableName, int createTime, int lastAccessTime, StorageDescriptor sd, Map parameters) {
      this();
      this.values = values;
      this.dbName = HiveStringUtils.intern(dbName);
      this.tableName = HiveStringUtils.intern(tableName);
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.lastAccessTime = lastAccessTime;
      this.setLastAccessTimeIsSet(true);
      this.sd = sd;
      this.parameters = HiveStringUtils.intern(parameters);
   }

   public Partition(Partition other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetValues()) {
         List<String> __this__values = new ArrayList(other.values);
         this.values = __this__values;
      }

      if (other.isSetDbName()) {
         this.dbName = HiveStringUtils.intern(other.dbName);
      }

      if (other.isSetTableName()) {
         this.tableName = HiveStringUtils.intern(other.tableName);
      }

      this.createTime = other.createTime;
      this.lastAccessTime = other.lastAccessTime;
      if (other.isSetSd()) {
         this.sd = new StorageDescriptor(other.sd);
      }

      if (other.isSetParameters()) {
         Map<String, String> __this__parameters = new HashMap(other.parameters);
         this.parameters = __this__parameters;
      }

      if (other.isSetPrivileges()) {
         this.privileges = new PrincipalPrivilegeSet(other.privileges);
      }

      if (other.isSetCatName()) {
         this.catName = other.catName;
      }

   }

   public Partition deepCopy() {
      return new Partition(this);
   }

   public void clear() {
      this.values = null;
      this.dbName = null;
      this.tableName = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.setLastAccessTimeIsSet(false);
      this.lastAccessTime = 0;
      this.sd = null;
      this.parameters = null;
      this.privileges = null;
      this.catName = null;
   }

   public int getValuesSize() {
      return this.values == null ? 0 : this.values.size();
   }

   @Nullable
   public Iterator getValuesIterator() {
      return this.values == null ? null : this.values.iterator();
   }

   public void addToValues(String elem) {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      this.values.add(elem);
   }

   @Nullable
   public List getValues() {
      return this.values;
   }

   public void setValues(@Nullable List values) {
      this.values = values;
   }

   public void unsetValues() {
      this.values = null;
   }

   public boolean isSetValues() {
      return this.values != null;
   }

   public void setValuesIsSet(boolean value) {
      if (!value) {
         this.values = null;
      }

   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = HiveStringUtils.intern(dbName);
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
      this.tableName = HiveStringUtils.intern(tableName);
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
      this.parameters = HiveStringUtils.intern(parameters);
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
         case VALUES:
            if (value == null) {
               this.unsetValues();
            } else {
               this.setValues((List)value);
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
         case PRIVILEGES:
            if (value == null) {
               this.unsetPrivileges();
            } else {
               this.setPrivileges((PrincipalPrivilegeSet)value);
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
         case VALUES:
            return this.getValues();
         case DB_NAME:
            return this.getDbName();
         case TABLE_NAME:
            return this.getTableName();
         case CREATE_TIME:
            return this.getCreateTime();
         case LAST_ACCESS_TIME:
            return this.getLastAccessTime();
         case SD:
            return this.getSd();
         case PARAMETERS:
            return this.getParameters();
         case PRIVILEGES:
            return this.getPrivileges();
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
            case VALUES:
               return this.isSetValues();
            case DB_NAME:
               return this.isSetDbName();
            case TABLE_NAME:
               return this.isSetTableName();
            case CREATE_TIME:
               return this.isSetCreateTime();
            case LAST_ACCESS_TIME:
               return this.isSetLastAccessTime();
            case SD:
               return this.isSetSd();
            case PARAMETERS:
               return this.isSetParameters();
            case PRIVILEGES:
               return this.isSetPrivileges();
            case CAT_NAME:
               return this.isSetCatName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Partition ? this.equals((Partition)that) : false;
   }

   public boolean equals(Partition that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_values = this.isSetValues();
         boolean that_present_values = that.isSetValues();
         if (this_present_values || that_present_values) {
            if (!this_present_values || !that_present_values) {
               return false;
            }

            if (!this.values.equals(that.values)) {
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
      hashCode = hashCode * 8191 + (this.isSetValues() ? 131071 : 524287);
      if (this.isSetValues()) {
         hashCode = hashCode * 8191 + this.values.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTableName() ? 131071 : 524287);
      if (this.isSetTableName()) {
         hashCode = hashCode * 8191 + this.tableName.hashCode();
      }

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + this.lastAccessTime;
      hashCode = hashCode * 8191 + (this.isSetSd() ? 131071 : 524287);
      if (this.isSetSd()) {
         hashCode = hashCode * 8191 + this.sd.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetParameters() ? 131071 : 524287);
      if (this.isSetParameters()) {
         hashCode = hashCode * 8191 + this.parameters.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrivileges() ? 131071 : 524287);
      if (this.isSetPrivileges()) {
         hashCode = hashCode * 8191 + this.privileges.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCatName() ? 131071 : 524287);
      if (this.isSetCatName()) {
         hashCode = hashCode * 8191 + this.catName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Partition other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetValues(), other.isSetValues());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetValues()) {
               lastComparison = TBaseHelper.compareTo(this.values, other.values);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return Partition._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Partition(");
      boolean first = true;
      sb.append("values:");
      if (this.values == null) {
         sb.append("null");
      } else {
         sb.append(this.values);
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
      optionals = new _Fields[]{Partition._Fields.PRIVILEGES, Partition._Fields.CAT_NAME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Partition._Fields.VALUES, new FieldMetaData("values", (byte)3, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(Partition._Fields.DB_NAME, new FieldMetaData("dbName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Partition._Fields.TABLE_NAME, new FieldMetaData("tableName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Partition._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Partition._Fields.LAST_ACCESS_TIME, new FieldMetaData("lastAccessTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Partition._Fields.SD, new FieldMetaData("sd", (byte)3, new StructMetaData((byte)12, StorageDescriptor.class)));
      tmpMap.put(Partition._Fields.PARAMETERS, new FieldMetaData("parameters", (byte)3, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(Partition._Fields.PRIVILEGES, new FieldMetaData("privileges", (byte)2, new StructMetaData((byte)12, PrincipalPrivilegeSet.class)));
      tmpMap.put(Partition._Fields.CAT_NAME, new FieldMetaData("catName", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Partition.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      VALUES((short)1, "values"),
      DB_NAME((short)2, "dbName"),
      TABLE_NAME((short)3, "tableName"),
      CREATE_TIME((short)4, "createTime"),
      LAST_ACCESS_TIME((short)5, "lastAccessTime"),
      SD((short)6, "sd"),
      PARAMETERS((short)7, "parameters"),
      PRIVILEGES((short)8, "privileges"),
      CAT_NAME((short)9, "catName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return VALUES;
            case 2:
               return DB_NAME;
            case 3:
               return TABLE_NAME;
            case 4:
               return CREATE_TIME;
            case 5:
               return LAST_ACCESS_TIME;
            case 6:
               return SD;
            case 7:
               return PARAMETERS;
            case 8:
               return PRIVILEGES;
            case 9:
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

   private static class PartitionStandardSchemeFactory implements SchemeFactory {
      private PartitionStandardSchemeFactory() {
      }

      public PartitionStandardScheme getScheme() {
         return new PartitionStandardScheme();
      }
   }

   private static class PartitionStandardScheme extends StandardScheme {
      private PartitionStandardScheme() {
      }

      public void read(TProtocol iprot, Partition struct) throws TException {
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

                  TList _list208 = iprot.readListBegin();
                  struct.values = new ArrayList(_list208.size);

                  for(int _i210 = 0; _i210 < _list208.size; ++_i210) {
                     String _elem209 = iprot.readString();
                     struct.values.add(_elem209);
                  }

                  iprot.readListEnd();
                  struct.setValuesIsSet(true);
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
                  if (schemeField.type == 12) {
                     struct.sd = new StorageDescriptor();
                     struct.sd.read(iprot);
                     struct.setSdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map211 = iprot.readMapBegin();
                  struct.parameters = new HashMap(2 * _map211.size);

                  for(int _i214 = 0; _i214 < _map211.size; ++_i214) {
                     String _key212 = iprot.readString();
                     String _val213 = iprot.readString();
                     struct.parameters.put(_key212, _val213);
                  }

                  iprot.readMapEnd();
                  struct.setParametersIsSet(true);
                  break;
               case 8:
                  if (schemeField.type == 12) {
                     struct.privileges = new PrincipalPrivilegeSet();
                     struct.privileges.read(iprot);
                     struct.setPrivilegesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
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

      public void write(TProtocol oprot, Partition struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Partition.STRUCT_DESC);
         if (struct.values != null) {
            oprot.writeFieldBegin(Partition.VALUES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.values.size()));

            for(String _iter215 : struct.values) {
               oprot.writeString(_iter215);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.dbName != null) {
            oprot.writeFieldBegin(Partition.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tableName != null) {
            oprot.writeFieldBegin(Partition.TABLE_NAME_FIELD_DESC);
            oprot.writeString(struct.tableName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Partition.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(Partition.LAST_ACCESS_TIME_FIELD_DESC);
         oprot.writeI32(struct.lastAccessTime);
         oprot.writeFieldEnd();
         if (struct.sd != null) {
            oprot.writeFieldBegin(Partition.SD_FIELD_DESC);
            struct.sd.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.parameters != null) {
            oprot.writeFieldBegin(Partition.PARAMETERS_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.parameters.size()));

            for(Map.Entry _iter216 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter216.getKey());
               oprot.writeString((String)_iter216.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.privileges != null && struct.isSetPrivileges()) {
            oprot.writeFieldBegin(Partition.PRIVILEGES_FIELD_DESC);
            struct.privileges.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.catName != null && struct.isSetCatName()) {
            oprot.writeFieldBegin(Partition.CAT_NAME_FIELD_DESC);
            oprot.writeString(struct.catName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PartitionTupleSchemeFactory implements SchemeFactory {
      private PartitionTupleSchemeFactory() {
      }

      public PartitionTupleScheme getScheme() {
         return new PartitionTupleScheme();
      }
   }

   private static class PartitionTupleScheme extends TupleScheme {
      private PartitionTupleScheme() {
      }

      public void write(TProtocol prot, Partition struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetValues()) {
            optionals.set(0);
         }

         if (struct.isSetDbName()) {
            optionals.set(1);
         }

         if (struct.isSetTableName()) {
            optionals.set(2);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(3);
         }

         if (struct.isSetLastAccessTime()) {
            optionals.set(4);
         }

         if (struct.isSetSd()) {
            optionals.set(5);
         }

         if (struct.isSetParameters()) {
            optionals.set(6);
         }

         if (struct.isSetPrivileges()) {
            optionals.set(7);
         }

         if (struct.isSetCatName()) {
            optionals.set(8);
         }

         oprot.writeBitSet(optionals, 9);
         if (struct.isSetValues()) {
            oprot.writeI32(struct.values.size());

            for(String _iter217 : struct.values) {
               oprot.writeString(_iter217);
            }
         }

         if (struct.isSetDbName()) {
            oprot.writeString(struct.dbName);
         }

         if (struct.isSetTableName()) {
            oprot.writeString(struct.tableName);
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetLastAccessTime()) {
            oprot.writeI32(struct.lastAccessTime);
         }

         if (struct.isSetSd()) {
            struct.sd.write(oprot);
         }

         if (struct.isSetParameters()) {
            oprot.writeI32(struct.parameters.size());

            for(Map.Entry _iter218 : struct.parameters.entrySet()) {
               oprot.writeString((String)_iter218.getKey());
               oprot.writeString((String)_iter218.getValue());
            }
         }

         if (struct.isSetPrivileges()) {
            struct.privileges.write(oprot);
         }

         if (struct.isSetCatName()) {
            oprot.writeString(struct.catName);
         }

      }

      public void read(TProtocol prot, Partition struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(9);
         if (incoming.get(0)) {
            TList _list219 = iprot.readListBegin((byte)11);
            struct.values = new ArrayList(_list219.size);

            for(int _i221 = 0; _i221 < _list219.size; ++_i221) {
               String _elem220 = iprot.readString();
               struct.values.add(_elem220);
            }

            struct.setValuesIsSet(true);
         }

         if (incoming.get(1)) {
            struct.dbName = iprot.readString();
            struct.setDbNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.tableName = iprot.readString();
            struct.setTableNameIsSet(true);
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
            struct.sd = new StorageDescriptor();
            struct.sd.read(iprot);
            struct.setSdIsSet(true);
         }

         if (incoming.get(6)) {
            TMap _map222 = iprot.readMapBegin((byte)11, (byte)11);
            struct.parameters = new HashMap(2 * _map222.size);

            for(int _i225 = 0; _i225 < _map222.size; ++_i225) {
               String _key223 = iprot.readString();
               String _val224 = iprot.readString();
               struct.parameters.put(_key223, _val224);
            }

            struct.setParametersIsSet(true);
         }

         if (incoming.get(7)) {
            struct.privileges = new PrincipalPrivilegeSet();
            struct.privileges.read(iprot);
            struct.setPrivilegesIsSet(true);
         }

         if (incoming.get(8)) {
            struct.catName = iprot.readString();
            struct.setCatNameIsSet(true);
         }

      }
   }
}
