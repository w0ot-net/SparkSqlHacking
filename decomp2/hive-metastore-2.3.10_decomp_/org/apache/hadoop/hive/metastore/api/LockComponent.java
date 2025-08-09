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
import org.apache.thrift.meta_data.EnumMetaData;
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

public class LockComponent implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("LockComponent");
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)1);
   private static final TField LEVEL_FIELD_DESC = new TField("level", (byte)8, (short)2);
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)3);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)4);
   private static final TField PARTITIONNAME_FIELD_DESC = new TField("partitionname", (byte)11, (short)5);
   private static final TField OPERATION_TYPE_FIELD_DESC = new TField("operationType", (byte)8, (short)6);
   private static final TField IS_ACID_FIELD_DESC = new TField("isAcid", (byte)2, (short)7);
   private static final TField IS_DYNAMIC_PARTITION_WRITE_FIELD_DESC = new TField("isDynamicPartitionWrite", (byte)2, (short)8);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new LockComponentStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new LockComponentTupleSchemeFactory();
   @Nullable
   private LockType type;
   @Nullable
   private LockLevel level;
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private String partitionname;
   @Nullable
   private DataOperationType operationType;
   private boolean isAcid;
   private boolean isDynamicPartitionWrite;
   private static final int __ISACID_ISSET_ID = 0;
   private static final int __ISDYNAMICPARTITIONWRITE_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public LockComponent() {
      this.__isset_bitfield = 0;
      this.operationType = DataOperationType.UNSET;
      this.isAcid = false;
      this.isDynamicPartitionWrite = false;
   }

   public LockComponent(LockType type, LockLevel level, String dbname) {
      this();
      this.type = type;
      this.level = level;
      this.dbname = dbname;
   }

   public LockComponent(LockComponent other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetType()) {
         this.type = other.type;
      }

      if (other.isSetLevel()) {
         this.level = other.level;
      }

      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetPartitionname()) {
         this.partitionname = other.partitionname;
      }

      if (other.isSetOperationType()) {
         this.operationType = other.operationType;
      }

      this.isAcid = other.isAcid;
      this.isDynamicPartitionWrite = other.isDynamicPartitionWrite;
   }

   public LockComponent deepCopy() {
      return new LockComponent(this);
   }

   public void clear() {
      this.type = null;
      this.level = null;
      this.dbname = null;
      this.tablename = null;
      this.partitionname = null;
      this.operationType = DataOperationType.UNSET;
      this.isAcid = false;
      this.isDynamicPartitionWrite = false;
   }

   @Nullable
   public LockType getType() {
      return this.type;
   }

   public void setType(@Nullable LockType type) {
      this.type = type;
   }

   public void unsetType() {
      this.type = null;
   }

   public boolean isSetType() {
      return this.type != null;
   }

   public void setTypeIsSet(boolean value) {
      if (!value) {
         this.type = null;
      }

   }

   @Nullable
   public LockLevel getLevel() {
      return this.level;
   }

   public void setLevel(@Nullable LockLevel level) {
      this.level = level;
   }

   public void unsetLevel() {
      this.level = null;
   }

   public boolean isSetLevel() {
      return this.level != null;
   }

   public void setLevelIsSet(boolean value) {
      if (!value) {
         this.level = null;
      }

   }

   @Nullable
   public String getDbname() {
      return this.dbname;
   }

   public void setDbname(@Nullable String dbname) {
      this.dbname = dbname;
   }

   public void unsetDbname() {
      this.dbname = null;
   }

   public boolean isSetDbname() {
      return this.dbname != null;
   }

   public void setDbnameIsSet(boolean value) {
      if (!value) {
         this.dbname = null;
      }

   }

   @Nullable
   public String getTablename() {
      return this.tablename;
   }

   public void setTablename(@Nullable String tablename) {
      this.tablename = tablename;
   }

   public void unsetTablename() {
      this.tablename = null;
   }

   public boolean isSetTablename() {
      return this.tablename != null;
   }

   public void setTablenameIsSet(boolean value) {
      if (!value) {
         this.tablename = null;
      }

   }

   @Nullable
   public String getPartitionname() {
      return this.partitionname;
   }

   public void setPartitionname(@Nullable String partitionname) {
      this.partitionname = partitionname;
   }

   public void unsetPartitionname() {
      this.partitionname = null;
   }

   public boolean isSetPartitionname() {
      return this.partitionname != null;
   }

   public void setPartitionnameIsSet(boolean value) {
      if (!value) {
         this.partitionname = null;
      }

   }

   @Nullable
   public DataOperationType getOperationType() {
      return this.operationType;
   }

   public void setOperationType(@Nullable DataOperationType operationType) {
      this.operationType = operationType;
   }

   public void unsetOperationType() {
      this.operationType = null;
   }

   public boolean isSetOperationType() {
      return this.operationType != null;
   }

   public void setOperationTypeIsSet(boolean value) {
      if (!value) {
         this.operationType = null;
      }

   }

   public boolean isIsAcid() {
      return this.isAcid;
   }

   public void setIsAcid(boolean isAcid) {
      this.isAcid = isAcid;
      this.setIsAcidIsSet(true);
   }

   public void unsetIsAcid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetIsAcid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIsAcidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public boolean isIsDynamicPartitionWrite() {
      return this.isDynamicPartitionWrite;
   }

   public void setIsDynamicPartitionWrite(boolean isDynamicPartitionWrite) {
      this.isDynamicPartitionWrite = isDynamicPartitionWrite;
      this.setIsDynamicPartitionWriteIsSet(true);
   }

   public void unsetIsDynamicPartitionWrite() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetIsDynamicPartitionWrite() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setIsDynamicPartitionWriteIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((LockType)value);
            }
            break;
         case LEVEL:
            if (value == null) {
               this.unsetLevel();
            } else {
               this.setLevel((LockLevel)value);
            }
            break;
         case DBNAME:
            if (value == null) {
               this.unsetDbname();
            } else {
               this.setDbname((String)value);
            }
            break;
         case TABLENAME:
            if (value == null) {
               this.unsetTablename();
            } else {
               this.setTablename((String)value);
            }
            break;
         case PARTITIONNAME:
            if (value == null) {
               this.unsetPartitionname();
            } else {
               this.setPartitionname((String)value);
            }
            break;
         case OPERATION_TYPE:
            if (value == null) {
               this.unsetOperationType();
            } else {
               this.setOperationType((DataOperationType)value);
            }
            break;
         case IS_ACID:
            if (value == null) {
               this.unsetIsAcid();
            } else {
               this.setIsAcid((Boolean)value);
            }
            break;
         case IS_DYNAMIC_PARTITION_WRITE:
            if (value == null) {
               this.unsetIsDynamicPartitionWrite();
            } else {
               this.setIsDynamicPartitionWrite((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TYPE:
            return this.getType();
         case LEVEL:
            return this.getLevel();
         case DBNAME:
            return this.getDbname();
         case TABLENAME:
            return this.getTablename();
         case PARTITIONNAME:
            return this.getPartitionname();
         case OPERATION_TYPE:
            return this.getOperationType();
         case IS_ACID:
            return this.isIsAcid();
         case IS_DYNAMIC_PARTITION_WRITE:
            return this.isIsDynamicPartitionWrite();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TYPE:
               return this.isSetType();
            case LEVEL:
               return this.isSetLevel();
            case DBNAME:
               return this.isSetDbname();
            case TABLENAME:
               return this.isSetTablename();
            case PARTITIONNAME:
               return this.isSetPartitionname();
            case OPERATION_TYPE:
               return this.isSetOperationType();
            case IS_ACID:
               return this.isSetIsAcid();
            case IS_DYNAMIC_PARTITION_WRITE:
               return this.isSetIsDynamicPartitionWrite();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof LockComponent ? this.equals((LockComponent)that) : false;
   }

   public boolean equals(LockComponent that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_type = this.isSetType();
         boolean that_present_type = that.isSetType();
         if (this_present_type || that_present_type) {
            if (!this_present_type || !that_present_type) {
               return false;
            }

            if (!this.type.equals(that.type)) {
               return false;
            }
         }

         boolean this_present_level = this.isSetLevel();
         boolean that_present_level = that.isSetLevel();
         if (this_present_level || that_present_level) {
            if (!this_present_level || !that_present_level) {
               return false;
            }

            if (!this.level.equals(that.level)) {
               return false;
            }
         }

         boolean this_present_dbname = this.isSetDbname();
         boolean that_present_dbname = that.isSetDbname();
         if (this_present_dbname || that_present_dbname) {
            if (!this_present_dbname || !that_present_dbname) {
               return false;
            }

            if (!this.dbname.equals(that.dbname)) {
               return false;
            }
         }

         boolean this_present_tablename = this.isSetTablename();
         boolean that_present_tablename = that.isSetTablename();
         if (this_present_tablename || that_present_tablename) {
            if (!this_present_tablename || !that_present_tablename) {
               return false;
            }

            if (!this.tablename.equals(that.tablename)) {
               return false;
            }
         }

         boolean this_present_partitionname = this.isSetPartitionname();
         boolean that_present_partitionname = that.isSetPartitionname();
         if (this_present_partitionname || that_present_partitionname) {
            if (!this_present_partitionname || !that_present_partitionname) {
               return false;
            }

            if (!this.partitionname.equals(that.partitionname)) {
               return false;
            }
         }

         boolean this_present_operationType = this.isSetOperationType();
         boolean that_present_operationType = that.isSetOperationType();
         if (this_present_operationType || that_present_operationType) {
            if (!this_present_operationType || !that_present_operationType) {
               return false;
            }

            if (!this.operationType.equals(that.operationType)) {
               return false;
            }
         }

         boolean this_present_isAcid = this.isSetIsAcid();
         boolean that_present_isAcid = that.isSetIsAcid();
         if (this_present_isAcid || that_present_isAcid) {
            if (!this_present_isAcid || !that_present_isAcid) {
               return false;
            }

            if (this.isAcid != that.isAcid) {
               return false;
            }
         }

         boolean this_present_isDynamicPartitionWrite = this.isSetIsDynamicPartitionWrite();
         boolean that_present_isDynamicPartitionWrite = that.isSetIsDynamicPartitionWrite();
         if (this_present_isDynamicPartitionWrite || that_present_isDynamicPartitionWrite) {
            if (!this_present_isDynamicPartitionWrite || !that_present_isDynamicPartitionWrite) {
               return false;
            }

            if (this.isDynamicPartitionWrite != that.isDynamicPartitionWrite) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetLevel() ? 131071 : 524287);
      if (this.isSetLevel()) {
         hashCode = hashCode * 8191 + this.level.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetDbname() ? 131071 : 524287);
      if (this.isSetDbname()) {
         hashCode = hashCode * 8191 + this.dbname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTablename() ? 131071 : 524287);
      if (this.isSetTablename()) {
         hashCode = hashCode * 8191 + this.tablename.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartitionname() ? 131071 : 524287);
      if (this.isSetPartitionname()) {
         hashCode = hashCode * 8191 + this.partitionname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetOperationType() ? 131071 : 524287);
      if (this.isSetOperationType()) {
         hashCode = hashCode * 8191 + this.operationType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetIsAcid() ? 131071 : 524287);
      if (this.isSetIsAcid()) {
         hashCode = hashCode * 8191 + (this.isAcid ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetIsDynamicPartitionWrite() ? 131071 : 524287);
      if (this.isSetIsDynamicPartitionWrite()) {
         hashCode = hashCode * 8191 + (this.isDynamicPartitionWrite ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(LockComponent other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetType(), other.isSetType());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetType()) {
               lastComparison = TBaseHelper.compareTo(this.type, other.type);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetLevel(), other.isSetLevel());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetLevel()) {
                  lastComparison = TBaseHelper.compareTo(this.level, other.level);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetDbname(), other.isSetDbname());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetDbname()) {
                     lastComparison = TBaseHelper.compareTo(this.dbname, other.dbname);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetTablename(), other.isSetTablename());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetTablename()) {
                        lastComparison = TBaseHelper.compareTo(this.tablename, other.tablename);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetPartitionname(), other.isSetPartitionname());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetPartitionname()) {
                           lastComparison = TBaseHelper.compareTo(this.partitionname, other.partitionname);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetOperationType(), other.isSetOperationType());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetOperationType()) {
                              lastComparison = TBaseHelper.compareTo(this.operationType, other.operationType);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetIsAcid(), other.isSetIsAcid());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetIsAcid()) {
                                 lastComparison = TBaseHelper.compareTo(this.isAcid, other.isAcid);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetIsDynamicPartitionWrite(), other.isSetIsDynamicPartitionWrite());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetIsDynamicPartitionWrite()) {
                                    lastComparison = TBaseHelper.compareTo(this.isDynamicPartitionWrite, other.isDynamicPartitionWrite);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return LockComponent._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("LockComponent(");
      boolean first = true;
      sb.append("type:");
      if (this.type == null) {
         sb.append("null");
      } else {
         sb.append(this.type);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("level:");
      if (this.level == null) {
         sb.append("null");
      } else {
         sb.append(this.level);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("dbname:");
      if (this.dbname == null) {
         sb.append("null");
      } else {
         sb.append(this.dbname);
      }

      first = false;
      if (this.isSetTablename()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("tablename:");
         if (this.tablename == null) {
            sb.append("null");
         } else {
            sb.append(this.tablename);
         }

         first = false;
      }

      if (this.isSetPartitionname()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partitionname:");
         if (this.partitionname == null) {
            sb.append("null");
         } else {
            sb.append(this.partitionname);
         }

         first = false;
      }

      if (this.isSetOperationType()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("operationType:");
         if (this.operationType == null) {
            sb.append("null");
         } else {
            sb.append(this.operationType);
         }

         first = false;
      }

      if (this.isSetIsAcid()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("isAcid:");
         sb.append(this.isAcid);
         first = false;
      }

      if (this.isSetIsDynamicPartitionWrite()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("isDynamicPartitionWrite:");
         sb.append(this.isDynamicPartitionWrite);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetType()) {
         throw new TProtocolException("Required field 'type' is unset! Struct:" + this.toString());
      } else if (!this.isSetLevel()) {
         throw new TProtocolException("Required field 'level' is unset! Struct:" + this.toString());
      } else if (!this.isSetDbname()) {
         throw new TProtocolException("Required field 'dbname' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{LockComponent._Fields.TABLENAME, LockComponent._Fields.PARTITIONNAME, LockComponent._Fields.OPERATION_TYPE, LockComponent._Fields.IS_ACID, LockComponent._Fields.IS_DYNAMIC_PARTITION_WRITE};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(LockComponent._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)16, LockType.class)));
      tmpMap.put(LockComponent._Fields.LEVEL, new FieldMetaData("level", (byte)1, new EnumMetaData((byte)16, LockLevel.class)));
      tmpMap.put(LockComponent._Fields.DBNAME, new FieldMetaData("dbname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(LockComponent._Fields.TABLENAME, new FieldMetaData("tablename", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(LockComponent._Fields.PARTITIONNAME, new FieldMetaData("partitionname", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(LockComponent._Fields.OPERATION_TYPE, new FieldMetaData("operationType", (byte)2, new EnumMetaData((byte)16, DataOperationType.class)));
      tmpMap.put(LockComponent._Fields.IS_ACID, new FieldMetaData("isAcid", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(LockComponent._Fields.IS_DYNAMIC_PARTITION_WRITE, new FieldMetaData("isDynamicPartitionWrite", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(LockComponent.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TYPE((short)1, "type"),
      LEVEL((short)2, "level"),
      DBNAME((short)3, "dbname"),
      TABLENAME((short)4, "tablename"),
      PARTITIONNAME((short)5, "partitionname"),
      OPERATION_TYPE((short)6, "operationType"),
      IS_ACID((short)7, "isAcid"),
      IS_DYNAMIC_PARTITION_WRITE((short)8, "isDynamicPartitionWrite");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TYPE;
            case 2:
               return LEVEL;
            case 3:
               return DBNAME;
            case 4:
               return TABLENAME;
            case 5:
               return PARTITIONNAME;
            case 6:
               return OPERATION_TYPE;
            case 7:
               return IS_ACID;
            case 8:
               return IS_DYNAMIC_PARTITION_WRITE;
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

   private static class LockComponentStandardSchemeFactory implements SchemeFactory {
      private LockComponentStandardSchemeFactory() {
      }

      public LockComponentStandardScheme getScheme() {
         return new LockComponentStandardScheme();
      }
   }

   private static class LockComponentStandardScheme extends StandardScheme {
      private LockComponentStandardScheme() {
      }

      public void read(TProtocol iprot, LockComponent struct) throws TException {
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
                  if (schemeField.type == 8) {
                     struct.type = LockType.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.level = LockLevel.findByValue(iprot.readI32());
                     struct.setLevelIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.dbname = iprot.readString();
                     struct.setDbnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.tablename = iprot.readString();
                     struct.setTablenameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.partitionname = iprot.readString();
                     struct.setPartitionnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.operationType = DataOperationType.findByValue(iprot.readI32());
                     struct.setOperationTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 2) {
                     struct.isAcid = iprot.readBool();
                     struct.setIsAcidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 2) {
                     struct.isDynamicPartitionWrite = iprot.readBool();
                     struct.setIsDynamicPartitionWriteIsSet(true);
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

      public void write(TProtocol oprot, LockComponent struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(LockComponent.STRUCT_DESC);
         if (struct.type != null) {
            oprot.writeFieldBegin(LockComponent.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.level != null) {
            oprot.writeFieldBegin(LockComponent.LEVEL_FIELD_DESC);
            oprot.writeI32(struct.level.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.dbname != null) {
            oprot.writeFieldBegin(LockComponent.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null && struct.isSetTablename()) {
            oprot.writeFieldBegin(LockComponent.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.partitionname != null && struct.isSetPartitionname()) {
            oprot.writeFieldBegin(LockComponent.PARTITIONNAME_FIELD_DESC);
            oprot.writeString(struct.partitionname);
            oprot.writeFieldEnd();
         }

         if (struct.operationType != null && struct.isSetOperationType()) {
            oprot.writeFieldBegin(LockComponent.OPERATION_TYPE_FIELD_DESC);
            oprot.writeI32(struct.operationType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.isSetIsAcid()) {
            oprot.writeFieldBegin(LockComponent.IS_ACID_FIELD_DESC);
            oprot.writeBool(struct.isAcid);
            oprot.writeFieldEnd();
         }

         if (struct.isSetIsDynamicPartitionWrite()) {
            oprot.writeFieldBegin(LockComponent.IS_DYNAMIC_PARTITION_WRITE_FIELD_DESC);
            oprot.writeBool(struct.isDynamicPartitionWrite);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class LockComponentTupleSchemeFactory implements SchemeFactory {
      private LockComponentTupleSchemeFactory() {
      }

      public LockComponentTupleScheme getScheme() {
         return new LockComponentTupleScheme();
      }
   }

   private static class LockComponentTupleScheme extends TupleScheme {
      private LockComponentTupleScheme() {
      }

      public void write(TProtocol prot, LockComponent struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.type.getValue());
         oprot.writeI32(struct.level.getValue());
         oprot.writeString(struct.dbname);
         BitSet optionals = new BitSet();
         if (struct.isSetTablename()) {
            optionals.set(0);
         }

         if (struct.isSetPartitionname()) {
            optionals.set(1);
         }

         if (struct.isSetOperationType()) {
            optionals.set(2);
         }

         if (struct.isSetIsAcid()) {
            optionals.set(3);
         }

         if (struct.isSetIsDynamicPartitionWrite()) {
            optionals.set(4);
         }

         oprot.writeBitSet(optionals, 5);
         if (struct.isSetTablename()) {
            oprot.writeString(struct.tablename);
         }

         if (struct.isSetPartitionname()) {
            oprot.writeString(struct.partitionname);
         }

         if (struct.isSetOperationType()) {
            oprot.writeI32(struct.operationType.getValue());
         }

         if (struct.isSetIsAcid()) {
            oprot.writeBool(struct.isAcid);
         }

         if (struct.isSetIsDynamicPartitionWrite()) {
            oprot.writeBool(struct.isDynamicPartitionWrite);
         }

      }

      public void read(TProtocol prot, LockComponent struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.type = LockType.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         struct.level = LockLevel.findByValue(iprot.readI32());
         struct.setLevelIsSet(true);
         struct.dbname = iprot.readString();
         struct.setDbnameIsSet(true);
         BitSet incoming = iprot.readBitSet(5);
         if (incoming.get(0)) {
            struct.tablename = iprot.readString();
            struct.setTablenameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.partitionname = iprot.readString();
            struct.setPartitionnameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.operationType = DataOperationType.findByValue(iprot.readI32());
            struct.setOperationTypeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.isAcid = iprot.readBool();
            struct.setIsAcidIsSet(true);
         }

         if (incoming.get(4)) {
            struct.isDynamicPartitionWrite = iprot.readBool();
            struct.setIsDynamicPartitionWriteIsSet(true);
         }

      }
   }
}
