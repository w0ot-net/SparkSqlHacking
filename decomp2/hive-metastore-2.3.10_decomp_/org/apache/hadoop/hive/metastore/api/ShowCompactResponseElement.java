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

public class ShowCompactResponseElement implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ShowCompactResponseElement");
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)1);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)2);
   private static final TField PARTITIONNAME_FIELD_DESC = new TField("partitionname", (byte)11, (short)3);
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)4);
   private static final TField STATE_FIELD_DESC = new TField("state", (byte)11, (short)5);
   private static final TField WORKERID_FIELD_DESC = new TField("workerid", (byte)11, (short)6);
   private static final TField START_FIELD_DESC = new TField("start", (byte)10, (short)7);
   private static final TField RUN_AS_FIELD_DESC = new TField("runAs", (byte)11, (short)8);
   private static final TField HIGHTEST_TXN_ID_FIELD_DESC = new TField("hightestTxnId", (byte)10, (short)9);
   private static final TField META_INFO_FIELD_DESC = new TField("metaInfo", (byte)11, (short)10);
   private static final TField END_TIME_FIELD_DESC = new TField("endTime", (byte)10, (short)11);
   private static final TField HADOOP_JOB_ID_FIELD_DESC = new TField("hadoopJobId", (byte)11, (short)12);
   private static final TField ID_FIELD_DESC = new TField("id", (byte)10, (short)13);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ShowCompactResponseElementStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ShowCompactResponseElementTupleSchemeFactory();
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private String partitionname;
   @Nullable
   private CompactionType type;
   @Nullable
   private String state;
   @Nullable
   private String workerid;
   private long start;
   @Nullable
   private String runAs;
   private long hightestTxnId;
   @Nullable
   private String metaInfo;
   private long endTime;
   @Nullable
   private String hadoopJobId;
   private long id;
   private static final int __START_ISSET_ID = 0;
   private static final int __HIGHTESTTXNID_ISSET_ID = 1;
   private static final int __ENDTIME_ISSET_ID = 2;
   private static final int __ID_ISSET_ID = 3;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ShowCompactResponseElement() {
      this.__isset_bitfield = 0;
      this.hadoopJobId = "None";
   }

   public ShowCompactResponseElement(String dbname, String tablename, CompactionType type, String state) {
      this();
      this.dbname = dbname;
      this.tablename = tablename;
      this.type = type;
      this.state = state;
   }

   public ShowCompactResponseElement(ShowCompactResponseElement other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetPartitionname()) {
         this.partitionname = other.partitionname;
      }

      if (other.isSetType()) {
         this.type = other.type;
      }

      if (other.isSetState()) {
         this.state = other.state;
      }

      if (other.isSetWorkerid()) {
         this.workerid = other.workerid;
      }

      this.start = other.start;
      if (other.isSetRunAs()) {
         this.runAs = other.runAs;
      }

      this.hightestTxnId = other.hightestTxnId;
      if (other.isSetMetaInfo()) {
         this.metaInfo = other.metaInfo;
      }

      this.endTime = other.endTime;
      if (other.isSetHadoopJobId()) {
         this.hadoopJobId = other.hadoopJobId;
      }

      this.id = other.id;
   }

   public ShowCompactResponseElement deepCopy() {
      return new ShowCompactResponseElement(this);
   }

   public void clear() {
      this.dbname = null;
      this.tablename = null;
      this.partitionname = null;
      this.type = null;
      this.state = null;
      this.workerid = null;
      this.setStartIsSet(false);
      this.start = 0L;
      this.runAs = null;
      this.setHightestTxnIdIsSet(false);
      this.hightestTxnId = 0L;
      this.metaInfo = null;
      this.setEndTimeIsSet(false);
      this.endTime = 0L;
      this.hadoopJobId = "None";
      this.setIdIsSet(false);
      this.id = 0L;
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
   public CompactionType getType() {
      return this.type;
   }

   public void setType(@Nullable CompactionType type) {
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
   public String getState() {
      return this.state;
   }

   public void setState(@Nullable String state) {
      this.state = state;
   }

   public void unsetState() {
      this.state = null;
   }

   public boolean isSetState() {
      return this.state != null;
   }

   public void setStateIsSet(boolean value) {
      if (!value) {
         this.state = null;
      }

   }

   @Nullable
   public String getWorkerid() {
      return this.workerid;
   }

   public void setWorkerid(@Nullable String workerid) {
      this.workerid = workerid;
   }

   public void unsetWorkerid() {
      this.workerid = null;
   }

   public boolean isSetWorkerid() {
      return this.workerid != null;
   }

   public void setWorkeridIsSet(boolean value) {
      if (!value) {
         this.workerid = null;
      }

   }

   public long getStart() {
      return this.start;
   }

   public void setStart(long start) {
      this.start = start;
      this.setStartIsSet(true);
   }

   public void unsetStart() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetStart() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setStartIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getRunAs() {
      return this.runAs;
   }

   public void setRunAs(@Nullable String runAs) {
      this.runAs = runAs;
   }

   public void unsetRunAs() {
      this.runAs = null;
   }

   public boolean isSetRunAs() {
      return this.runAs != null;
   }

   public void setRunAsIsSet(boolean value) {
      if (!value) {
         this.runAs = null;
      }

   }

   public long getHightestTxnId() {
      return this.hightestTxnId;
   }

   public void setHightestTxnId(long hightestTxnId) {
      this.hightestTxnId = hightestTxnId;
      this.setHightestTxnIdIsSet(true);
   }

   public void unsetHightestTxnId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetHightestTxnId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setHightestTxnIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   @Nullable
   public String getMetaInfo() {
      return this.metaInfo;
   }

   public void setMetaInfo(@Nullable String metaInfo) {
      this.metaInfo = metaInfo;
   }

   public void unsetMetaInfo() {
      this.metaInfo = null;
   }

   public boolean isSetMetaInfo() {
      return this.metaInfo != null;
   }

   public void setMetaInfoIsSet(boolean value) {
      if (!value) {
         this.metaInfo = null;
      }

   }

   public long getEndTime() {
      return this.endTime;
   }

   public void setEndTime(long endTime) {
      this.endTime = endTime;
      this.setEndTimeIsSet(true);
   }

   public void unsetEndTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetEndTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setEndTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   @Nullable
   public String getHadoopJobId() {
      return this.hadoopJobId;
   }

   public void setHadoopJobId(@Nullable String hadoopJobId) {
      this.hadoopJobId = hadoopJobId;
   }

   public void unsetHadoopJobId() {
      this.hadoopJobId = null;
   }

   public boolean isSetHadoopJobId() {
      return this.hadoopJobId != null;
   }

   public void setHadoopJobIdIsSet(boolean value) {
      if (!value) {
         this.hadoopJobId = null;
      }

   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
      this.setIdIsSet(true);
   }

   public void unsetId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
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
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((CompactionType)value);
            }
            break;
         case STATE:
            if (value == null) {
               this.unsetState();
            } else {
               this.setState((String)value);
            }
            break;
         case WORKERID:
            if (value == null) {
               this.unsetWorkerid();
            } else {
               this.setWorkerid((String)value);
            }
            break;
         case START:
            if (value == null) {
               this.unsetStart();
            } else {
               this.setStart((Long)value);
            }
            break;
         case RUN_AS:
            if (value == null) {
               this.unsetRunAs();
            } else {
               this.setRunAs((String)value);
            }
            break;
         case HIGHTEST_TXN_ID:
            if (value == null) {
               this.unsetHightestTxnId();
            } else {
               this.setHightestTxnId((Long)value);
            }
            break;
         case META_INFO:
            if (value == null) {
               this.unsetMetaInfo();
            } else {
               this.setMetaInfo((String)value);
            }
            break;
         case END_TIME:
            if (value == null) {
               this.unsetEndTime();
            } else {
               this.setEndTime((Long)value);
            }
            break;
         case HADOOP_JOB_ID:
            if (value == null) {
               this.unsetHadoopJobId();
            } else {
               this.setHadoopJobId((String)value);
            }
            break;
         case ID:
            if (value == null) {
               this.unsetId();
            } else {
               this.setId((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DBNAME:
            return this.getDbname();
         case TABLENAME:
            return this.getTablename();
         case PARTITIONNAME:
            return this.getPartitionname();
         case TYPE:
            return this.getType();
         case STATE:
            return this.getState();
         case WORKERID:
            return this.getWorkerid();
         case START:
            return this.getStart();
         case RUN_AS:
            return this.getRunAs();
         case HIGHTEST_TXN_ID:
            return this.getHightestTxnId();
         case META_INFO:
            return this.getMetaInfo();
         case END_TIME:
            return this.getEndTime();
         case HADOOP_JOB_ID:
            return this.getHadoopJobId();
         case ID:
            return this.getId();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case DBNAME:
               return this.isSetDbname();
            case TABLENAME:
               return this.isSetTablename();
            case PARTITIONNAME:
               return this.isSetPartitionname();
            case TYPE:
               return this.isSetType();
            case STATE:
               return this.isSetState();
            case WORKERID:
               return this.isSetWorkerid();
            case START:
               return this.isSetStart();
            case RUN_AS:
               return this.isSetRunAs();
            case HIGHTEST_TXN_ID:
               return this.isSetHightestTxnId();
            case META_INFO:
               return this.isSetMetaInfo();
            case END_TIME:
               return this.isSetEndTime();
            case HADOOP_JOB_ID:
               return this.isSetHadoopJobId();
            case ID:
               return this.isSetId();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ShowCompactResponseElement ? this.equals((ShowCompactResponseElement)that) : false;
   }

   public boolean equals(ShowCompactResponseElement that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
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

         boolean this_present_state = this.isSetState();
         boolean that_present_state = that.isSetState();
         if (this_present_state || that_present_state) {
            if (!this_present_state || !that_present_state) {
               return false;
            }

            if (!this.state.equals(that.state)) {
               return false;
            }
         }

         boolean this_present_workerid = this.isSetWorkerid();
         boolean that_present_workerid = that.isSetWorkerid();
         if (this_present_workerid || that_present_workerid) {
            if (!this_present_workerid || !that_present_workerid) {
               return false;
            }

            if (!this.workerid.equals(that.workerid)) {
               return false;
            }
         }

         boolean this_present_start = this.isSetStart();
         boolean that_present_start = that.isSetStart();
         if (this_present_start || that_present_start) {
            if (!this_present_start || !that_present_start) {
               return false;
            }

            if (this.start != that.start) {
               return false;
            }
         }

         boolean this_present_runAs = this.isSetRunAs();
         boolean that_present_runAs = that.isSetRunAs();
         if (this_present_runAs || that_present_runAs) {
            if (!this_present_runAs || !that_present_runAs) {
               return false;
            }

            if (!this.runAs.equals(that.runAs)) {
               return false;
            }
         }

         boolean this_present_hightestTxnId = this.isSetHightestTxnId();
         boolean that_present_hightestTxnId = that.isSetHightestTxnId();
         if (this_present_hightestTxnId || that_present_hightestTxnId) {
            if (!this_present_hightestTxnId || !that_present_hightestTxnId) {
               return false;
            }

            if (this.hightestTxnId != that.hightestTxnId) {
               return false;
            }
         }

         boolean this_present_metaInfo = this.isSetMetaInfo();
         boolean that_present_metaInfo = that.isSetMetaInfo();
         if (this_present_metaInfo || that_present_metaInfo) {
            if (!this_present_metaInfo || !that_present_metaInfo) {
               return false;
            }

            if (!this.metaInfo.equals(that.metaInfo)) {
               return false;
            }
         }

         boolean this_present_endTime = this.isSetEndTime();
         boolean that_present_endTime = that.isSetEndTime();
         if (this_present_endTime || that_present_endTime) {
            if (!this_present_endTime || !that_present_endTime) {
               return false;
            }

            if (this.endTime != that.endTime) {
               return false;
            }
         }

         boolean this_present_hadoopJobId = this.isSetHadoopJobId();
         boolean that_present_hadoopJobId = that.isSetHadoopJobId();
         if (this_present_hadoopJobId || that_present_hadoopJobId) {
            if (!this_present_hadoopJobId || !that_present_hadoopJobId) {
               return false;
            }

            if (!this.hadoopJobId.equals(that.hadoopJobId)) {
               return false;
            }
         }

         boolean this_present_id = this.isSetId();
         boolean that_present_id = that.isSetId();
         if (this_present_id || that_present_id) {
            if (!this_present_id || !that_present_id) {
               return false;
            }

            if (this.id != that.id) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
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

      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetState() ? 131071 : 524287);
      if (this.isSetState()) {
         hashCode = hashCode * 8191 + this.state.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetWorkerid() ? 131071 : 524287);
      if (this.isSetWorkerid()) {
         hashCode = hashCode * 8191 + this.workerid.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetStart() ? 131071 : 524287);
      if (this.isSetStart()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.start);
      }

      hashCode = hashCode * 8191 + (this.isSetRunAs() ? 131071 : 524287);
      if (this.isSetRunAs()) {
         hashCode = hashCode * 8191 + this.runAs.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetHightestTxnId() ? 131071 : 524287);
      if (this.isSetHightestTxnId()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.hightestTxnId);
      }

      hashCode = hashCode * 8191 + (this.isSetMetaInfo() ? 131071 : 524287);
      if (this.isSetMetaInfo()) {
         hashCode = hashCode * 8191 + this.metaInfo.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetEndTime() ? 131071 : 524287);
      if (this.isSetEndTime()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.endTime);
      }

      hashCode = hashCode * 8191 + (this.isSetHadoopJobId() ? 131071 : 524287);
      if (this.isSetHadoopJobId()) {
         hashCode = hashCode * 8191 + this.hadoopJobId.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetId() ? 131071 : 524287);
      if (this.isSetId()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.id);
      }

      return hashCode;
   }

   public int compareTo(ShowCompactResponseElement other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

                     lastComparison = Boolean.compare(this.isSetState(), other.isSetState());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetState()) {
                           lastComparison = TBaseHelper.compareTo(this.state, other.state);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetWorkerid(), other.isSetWorkerid());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetWorkerid()) {
                              lastComparison = TBaseHelper.compareTo(this.workerid, other.workerid);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetStart(), other.isSetStart());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetStart()) {
                                 lastComparison = TBaseHelper.compareTo(this.start, other.start);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetRunAs(), other.isSetRunAs());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetRunAs()) {
                                    lastComparison = TBaseHelper.compareTo(this.runAs, other.runAs);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetHightestTxnId(), other.isSetHightestTxnId());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetHightestTxnId()) {
                                       lastComparison = TBaseHelper.compareTo(this.hightestTxnId, other.hightestTxnId);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetMetaInfo(), other.isSetMetaInfo());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetMetaInfo()) {
                                          lastComparison = TBaseHelper.compareTo(this.metaInfo, other.metaInfo);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetEndTime(), other.isSetEndTime());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetEndTime()) {
                                             lastComparison = TBaseHelper.compareTo(this.endTime, other.endTime);
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             }
                                          }

                                          lastComparison = Boolean.compare(this.isSetHadoopJobId(), other.isSetHadoopJobId());
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          } else {
                                             if (this.isSetHadoopJobId()) {
                                                lastComparison = TBaseHelper.compareTo(this.hadoopJobId, other.hadoopJobId);
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                }
                                             }

                                             lastComparison = Boolean.compare(this.isSetId(), other.isSetId());
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             } else {
                                                if (this.isSetId()) {
                                                   lastComparison = TBaseHelper.compareTo(this.id, other.id);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return ShowCompactResponseElement._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ShowCompactResponseElement(");
      boolean first = true;
      sb.append("dbname:");
      if (this.dbname == null) {
         sb.append("null");
      } else {
         sb.append(this.dbname);
      }

      first = false;
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

      if (!first) {
         sb.append(", ");
      }

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

      sb.append("state:");
      if (this.state == null) {
         sb.append("null");
      } else {
         sb.append(this.state);
      }

      first = false;
      if (this.isSetWorkerid()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("workerid:");
         if (this.workerid == null) {
            sb.append("null");
         } else {
            sb.append(this.workerid);
         }

         first = false;
      }

      if (this.isSetStart()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("start:");
         sb.append(this.start);
         first = false;
      }

      if (this.isSetRunAs()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("runAs:");
         if (this.runAs == null) {
            sb.append("null");
         } else {
            sb.append(this.runAs);
         }

         first = false;
      }

      if (this.isSetHightestTxnId()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("hightestTxnId:");
         sb.append(this.hightestTxnId);
         first = false;
      }

      if (this.isSetMetaInfo()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("metaInfo:");
         if (this.metaInfo == null) {
            sb.append("null");
         } else {
            sb.append(this.metaInfo);
         }

         first = false;
      }

      if (this.isSetEndTime()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("endTime:");
         sb.append(this.endTime);
         first = false;
      }

      if (this.isSetHadoopJobId()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("hadoopJobId:");
         if (this.hadoopJobId == null) {
            sb.append("null");
         } else {
            sb.append(this.hadoopJobId);
         }

         first = false;
      }

      if (this.isSetId()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("id:");
         sb.append(this.id);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbname()) {
         throw new TProtocolException("Required field 'dbname' is unset! Struct:" + this.toString());
      } else if (!this.isSetTablename()) {
         throw new TProtocolException("Required field 'tablename' is unset! Struct:" + this.toString());
      } else if (!this.isSetType()) {
         throw new TProtocolException("Required field 'type' is unset! Struct:" + this.toString());
      } else if (!this.isSetState()) {
         throw new TProtocolException("Required field 'state' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{ShowCompactResponseElement._Fields.PARTITIONNAME, ShowCompactResponseElement._Fields.WORKERID, ShowCompactResponseElement._Fields.START, ShowCompactResponseElement._Fields.RUN_AS, ShowCompactResponseElement._Fields.HIGHTEST_TXN_ID, ShowCompactResponseElement._Fields.META_INFO, ShowCompactResponseElement._Fields.END_TIME, ShowCompactResponseElement._Fields.HADOOP_JOB_ID, ShowCompactResponseElement._Fields.ID};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ShowCompactResponseElement._Fields.DBNAME, new FieldMetaData("dbname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.TABLENAME, new FieldMetaData("tablename", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.PARTITIONNAME, new FieldMetaData("partitionname", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)16, CompactionType.class)));
      tmpMap.put(ShowCompactResponseElement._Fields.STATE, new FieldMetaData("state", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.WORKERID, new FieldMetaData("workerid", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.START, new FieldMetaData("start", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowCompactResponseElement._Fields.RUN_AS, new FieldMetaData("runAs", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.HIGHTEST_TXN_ID, new FieldMetaData("hightestTxnId", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowCompactResponseElement._Fields.META_INFO, new FieldMetaData("metaInfo", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.END_TIME, new FieldMetaData("endTime", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowCompactResponseElement._Fields.HADOOP_JOB_ID, new FieldMetaData("hadoopJobId", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowCompactResponseElement._Fields.ID, new FieldMetaData("id", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ShowCompactResponseElement.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DBNAME((short)1, "dbname"),
      TABLENAME((short)2, "tablename"),
      PARTITIONNAME((short)3, "partitionname"),
      TYPE((short)4, "type"),
      STATE((short)5, "state"),
      WORKERID((short)6, "workerid"),
      START((short)7, "start"),
      RUN_AS((short)8, "runAs"),
      HIGHTEST_TXN_ID((short)9, "hightestTxnId"),
      META_INFO((short)10, "metaInfo"),
      END_TIME((short)11, "endTime"),
      HADOOP_JOB_ID((short)12, "hadoopJobId"),
      ID((short)13, "id");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DBNAME;
            case 2:
               return TABLENAME;
            case 3:
               return PARTITIONNAME;
            case 4:
               return TYPE;
            case 5:
               return STATE;
            case 6:
               return WORKERID;
            case 7:
               return START;
            case 8:
               return RUN_AS;
            case 9:
               return HIGHTEST_TXN_ID;
            case 10:
               return META_INFO;
            case 11:
               return END_TIME;
            case 12:
               return HADOOP_JOB_ID;
            case 13:
               return ID;
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

   private static class ShowCompactResponseElementStandardSchemeFactory implements SchemeFactory {
      private ShowCompactResponseElementStandardSchemeFactory() {
      }

      public ShowCompactResponseElementStandardScheme getScheme() {
         return new ShowCompactResponseElementStandardScheme();
      }
   }

   private static class ShowCompactResponseElementStandardScheme extends StandardScheme {
      private ShowCompactResponseElementStandardScheme() {
      }

      public void read(TProtocol iprot, ShowCompactResponseElement struct) throws TException {
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
                     struct.dbname = iprot.readString();
                     struct.setDbnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tablename = iprot.readString();
                     struct.setTablenameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.partitionname = iprot.readString();
                     struct.setPartitionnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.type = CompactionType.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.state = iprot.readString();
                     struct.setStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.workerid = iprot.readString();
                     struct.setWorkeridIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 10) {
                     struct.start = iprot.readI64();
                     struct.setStartIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 11) {
                     struct.runAs = iprot.readString();
                     struct.setRunAsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 10) {
                     struct.hightestTxnId = iprot.readI64();
                     struct.setHightestTxnIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 11) {
                     struct.metaInfo = iprot.readString();
                     struct.setMetaInfoIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 11:
                  if (schemeField.type == 10) {
                     struct.endTime = iprot.readI64();
                     struct.setEndTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 12:
                  if (schemeField.type == 11) {
                     struct.hadoopJobId = iprot.readString();
                     struct.setHadoopJobIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 13:
                  if (schemeField.type == 10) {
                     struct.id = iprot.readI64();
                     struct.setIdIsSet(true);
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

      public void write(TProtocol oprot, ShowCompactResponseElement struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ShowCompactResponseElement.STRUCT_DESC);
         if (struct.dbname != null) {
            oprot.writeFieldBegin(ShowCompactResponseElement.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null) {
            oprot.writeFieldBegin(ShowCompactResponseElement.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.partitionname != null && struct.isSetPartitionname()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.PARTITIONNAME_FIELD_DESC);
            oprot.writeString(struct.partitionname);
            oprot.writeFieldEnd();
         }

         if (struct.type != null) {
            oprot.writeFieldBegin(ShowCompactResponseElement.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.state != null) {
            oprot.writeFieldBegin(ShowCompactResponseElement.STATE_FIELD_DESC);
            oprot.writeString(struct.state);
            oprot.writeFieldEnd();
         }

         if (struct.workerid != null && struct.isSetWorkerid()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.WORKERID_FIELD_DESC);
            oprot.writeString(struct.workerid);
            oprot.writeFieldEnd();
         }

         if (struct.isSetStart()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.START_FIELD_DESC);
            oprot.writeI64(struct.start);
            oprot.writeFieldEnd();
         }

         if (struct.runAs != null && struct.isSetRunAs()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.RUN_AS_FIELD_DESC);
            oprot.writeString(struct.runAs);
            oprot.writeFieldEnd();
         }

         if (struct.isSetHightestTxnId()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.HIGHTEST_TXN_ID_FIELD_DESC);
            oprot.writeI64(struct.hightestTxnId);
            oprot.writeFieldEnd();
         }

         if (struct.metaInfo != null && struct.isSetMetaInfo()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.META_INFO_FIELD_DESC);
            oprot.writeString(struct.metaInfo);
            oprot.writeFieldEnd();
         }

         if (struct.isSetEndTime()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.END_TIME_FIELD_DESC);
            oprot.writeI64(struct.endTime);
            oprot.writeFieldEnd();
         }

         if (struct.hadoopJobId != null && struct.isSetHadoopJobId()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.HADOOP_JOB_ID_FIELD_DESC);
            oprot.writeString(struct.hadoopJobId);
            oprot.writeFieldEnd();
         }

         if (struct.isSetId()) {
            oprot.writeFieldBegin(ShowCompactResponseElement.ID_FIELD_DESC);
            oprot.writeI64(struct.id);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ShowCompactResponseElementTupleSchemeFactory implements SchemeFactory {
      private ShowCompactResponseElementTupleSchemeFactory() {
      }

      public ShowCompactResponseElementTupleScheme getScheme() {
         return new ShowCompactResponseElementTupleScheme();
      }
   }

   private static class ShowCompactResponseElementTupleScheme extends TupleScheme {
      private ShowCompactResponseElementTupleScheme() {
      }

      public void write(TProtocol prot, ShowCompactResponseElement struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbname);
         oprot.writeString(struct.tablename);
         oprot.writeI32(struct.type.getValue());
         oprot.writeString(struct.state);
         BitSet optionals = new BitSet();
         if (struct.isSetPartitionname()) {
            optionals.set(0);
         }

         if (struct.isSetWorkerid()) {
            optionals.set(1);
         }

         if (struct.isSetStart()) {
            optionals.set(2);
         }

         if (struct.isSetRunAs()) {
            optionals.set(3);
         }

         if (struct.isSetHightestTxnId()) {
            optionals.set(4);
         }

         if (struct.isSetMetaInfo()) {
            optionals.set(5);
         }

         if (struct.isSetEndTime()) {
            optionals.set(6);
         }

         if (struct.isSetHadoopJobId()) {
            optionals.set(7);
         }

         if (struct.isSetId()) {
            optionals.set(8);
         }

         oprot.writeBitSet(optionals, 9);
         if (struct.isSetPartitionname()) {
            oprot.writeString(struct.partitionname);
         }

         if (struct.isSetWorkerid()) {
            oprot.writeString(struct.workerid);
         }

         if (struct.isSetStart()) {
            oprot.writeI64(struct.start);
         }

         if (struct.isSetRunAs()) {
            oprot.writeString(struct.runAs);
         }

         if (struct.isSetHightestTxnId()) {
            oprot.writeI64(struct.hightestTxnId);
         }

         if (struct.isSetMetaInfo()) {
            oprot.writeString(struct.metaInfo);
         }

         if (struct.isSetEndTime()) {
            oprot.writeI64(struct.endTime);
         }

         if (struct.isSetHadoopJobId()) {
            oprot.writeString(struct.hadoopJobId);
         }

         if (struct.isSetId()) {
            oprot.writeI64(struct.id);
         }

      }

      public void read(TProtocol prot, ShowCompactResponseElement struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbname = iprot.readString();
         struct.setDbnameIsSet(true);
         struct.tablename = iprot.readString();
         struct.setTablenameIsSet(true);
         struct.type = CompactionType.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         struct.state = iprot.readString();
         struct.setStateIsSet(true);
         BitSet incoming = iprot.readBitSet(9);
         if (incoming.get(0)) {
            struct.partitionname = iprot.readString();
            struct.setPartitionnameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.workerid = iprot.readString();
            struct.setWorkeridIsSet(true);
         }

         if (incoming.get(2)) {
            struct.start = iprot.readI64();
            struct.setStartIsSet(true);
         }

         if (incoming.get(3)) {
            struct.runAs = iprot.readString();
            struct.setRunAsIsSet(true);
         }

         if (incoming.get(4)) {
            struct.hightestTxnId = iprot.readI64();
            struct.setHightestTxnIdIsSet(true);
         }

         if (incoming.get(5)) {
            struct.metaInfo = iprot.readString();
            struct.setMetaInfoIsSet(true);
         }

         if (incoming.get(6)) {
            struct.endTime = iprot.readI64();
            struct.setEndTimeIsSet(true);
         }

         if (incoming.get(7)) {
            struct.hadoopJobId = iprot.readString();
            struct.setHadoopJobIdIsSet(true);
         }

         if (incoming.get(8)) {
            struct.id = iprot.readI64();
            struct.setIdIsSet(true);
         }

      }
   }
}
