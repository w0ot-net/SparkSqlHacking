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

public class ShowLocksResponseElement implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("ShowLocksResponseElement");
   private static final TField LOCKID_FIELD_DESC = new TField("lockid", (byte)10, (short)1);
   private static final TField DBNAME_FIELD_DESC = new TField("dbname", (byte)11, (short)2);
   private static final TField TABLENAME_FIELD_DESC = new TField("tablename", (byte)11, (short)3);
   private static final TField PARTNAME_FIELD_DESC = new TField("partname", (byte)11, (short)4);
   private static final TField STATE_FIELD_DESC = new TField("state", (byte)8, (short)5);
   private static final TField TYPE_FIELD_DESC = new TField("type", (byte)8, (short)6);
   private static final TField TXNID_FIELD_DESC = new TField("txnid", (byte)10, (short)7);
   private static final TField LASTHEARTBEAT_FIELD_DESC = new TField("lastheartbeat", (byte)10, (short)8);
   private static final TField ACQUIREDAT_FIELD_DESC = new TField("acquiredat", (byte)10, (short)9);
   private static final TField USER_FIELD_DESC = new TField("user", (byte)11, (short)10);
   private static final TField HOSTNAME_FIELD_DESC = new TField("hostname", (byte)11, (short)11);
   private static final TField HEARTBEAT_COUNT_FIELD_DESC = new TField("heartbeatCount", (byte)8, (short)12);
   private static final TField AGENT_INFO_FIELD_DESC = new TField("agentInfo", (byte)11, (short)13);
   private static final TField BLOCKED_BY_EXT_ID_FIELD_DESC = new TField("blockedByExtId", (byte)10, (short)14);
   private static final TField BLOCKED_BY_INT_ID_FIELD_DESC = new TField("blockedByIntId", (byte)10, (short)15);
   private static final TField LOCK_ID_INTERNAL_FIELD_DESC = new TField("lockIdInternal", (byte)10, (short)16);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ShowLocksResponseElementStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ShowLocksResponseElementTupleSchemeFactory();
   private long lockid;
   @Nullable
   private String dbname;
   @Nullable
   private String tablename;
   @Nullable
   private String partname;
   @Nullable
   private LockState state;
   @Nullable
   private LockType type;
   private long txnid;
   private long lastheartbeat;
   private long acquiredat;
   @Nullable
   private String user;
   @Nullable
   private String hostname;
   private int heartbeatCount;
   @Nullable
   private String agentInfo;
   private long blockedByExtId;
   private long blockedByIntId;
   private long lockIdInternal;
   private static final int __LOCKID_ISSET_ID = 0;
   private static final int __TXNID_ISSET_ID = 1;
   private static final int __LASTHEARTBEAT_ISSET_ID = 2;
   private static final int __ACQUIREDAT_ISSET_ID = 3;
   private static final int __HEARTBEATCOUNT_ISSET_ID = 4;
   private static final int __BLOCKEDBYEXTID_ISSET_ID = 5;
   private static final int __BLOCKEDBYINTID_ISSET_ID = 6;
   private static final int __LOCKIDINTERNAL_ISSET_ID = 7;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public ShowLocksResponseElement() {
      this.__isset_bitfield = 0;
      this.heartbeatCount = 0;
   }

   public ShowLocksResponseElement(long lockid, String dbname, LockState state, LockType type, long lastheartbeat, String user, String hostname) {
      this();
      this.lockid = lockid;
      this.setLockidIsSet(true);
      this.dbname = dbname;
      this.state = state;
      this.type = type;
      this.lastheartbeat = lastheartbeat;
      this.setLastheartbeatIsSet(true);
      this.user = user;
      this.hostname = hostname;
   }

   public ShowLocksResponseElement(ShowLocksResponseElement other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.lockid = other.lockid;
      if (other.isSetDbname()) {
         this.dbname = other.dbname;
      }

      if (other.isSetTablename()) {
         this.tablename = other.tablename;
      }

      if (other.isSetPartname()) {
         this.partname = other.partname;
      }

      if (other.isSetState()) {
         this.state = other.state;
      }

      if (other.isSetType()) {
         this.type = other.type;
      }

      this.txnid = other.txnid;
      this.lastheartbeat = other.lastheartbeat;
      this.acquiredat = other.acquiredat;
      if (other.isSetUser()) {
         this.user = other.user;
      }

      if (other.isSetHostname()) {
         this.hostname = other.hostname;
      }

      this.heartbeatCount = other.heartbeatCount;
      if (other.isSetAgentInfo()) {
         this.agentInfo = other.agentInfo;
      }

      this.blockedByExtId = other.blockedByExtId;
      this.blockedByIntId = other.blockedByIntId;
      this.lockIdInternal = other.lockIdInternal;
   }

   public ShowLocksResponseElement deepCopy() {
      return new ShowLocksResponseElement(this);
   }

   public void clear() {
      this.setLockidIsSet(false);
      this.lockid = 0L;
      this.dbname = null;
      this.tablename = null;
      this.partname = null;
      this.state = null;
      this.type = null;
      this.setTxnidIsSet(false);
      this.txnid = 0L;
      this.setLastheartbeatIsSet(false);
      this.lastheartbeat = 0L;
      this.setAcquiredatIsSet(false);
      this.acquiredat = 0L;
      this.user = null;
      this.hostname = null;
      this.heartbeatCount = 0;
      this.agentInfo = null;
      this.setBlockedByExtIdIsSet(false);
      this.blockedByExtId = 0L;
      this.setBlockedByIntIdIsSet(false);
      this.blockedByIntId = 0L;
      this.setLockIdInternalIsSet(false);
      this.lockIdInternal = 0L;
   }

   public long getLockid() {
      return this.lockid;
   }

   public void setLockid(long lockid) {
      this.lockid = lockid;
      this.setLockidIsSet(true);
   }

   public void unsetLockid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetLockid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setLockidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
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
   public String getPartname() {
      return this.partname;
   }

   public void setPartname(@Nullable String partname) {
      this.partname = partname;
   }

   public void unsetPartname() {
      this.partname = null;
   }

   public boolean isSetPartname() {
      return this.partname != null;
   }

   public void setPartnameIsSet(boolean value) {
      if (!value) {
         this.partname = null;
      }

   }

   @Nullable
   public LockState getState() {
      return this.state;
   }

   public void setState(@Nullable LockState state) {
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

   public long getTxnid() {
      return this.txnid;
   }

   public void setTxnid(long txnid) {
      this.txnid = txnid;
      this.setTxnidIsSet(true);
   }

   public void unsetTxnid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetTxnid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setTxnidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public long getLastheartbeat() {
      return this.lastheartbeat;
   }

   public void setLastheartbeat(long lastheartbeat) {
      this.lastheartbeat = lastheartbeat;
      this.setLastheartbeatIsSet(true);
   }

   public void unsetLastheartbeat() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetLastheartbeat() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setLastheartbeatIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public long getAcquiredat() {
      return this.acquiredat;
   }

   public void setAcquiredat(long acquiredat) {
      this.acquiredat = acquiredat;
      this.setAcquiredatIsSet(true);
   }

   public void unsetAcquiredat() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetAcquiredat() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setAcquiredatIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   @Nullable
   public String getUser() {
      return this.user;
   }

   public void setUser(@Nullable String user) {
      this.user = user;
   }

   public void unsetUser() {
      this.user = null;
   }

   public boolean isSetUser() {
      return this.user != null;
   }

   public void setUserIsSet(boolean value) {
      if (!value) {
         this.user = null;
      }

   }

   @Nullable
   public String getHostname() {
      return this.hostname;
   }

   public void setHostname(@Nullable String hostname) {
      this.hostname = hostname;
   }

   public void unsetHostname() {
      this.hostname = null;
   }

   public boolean isSetHostname() {
      return this.hostname != null;
   }

   public void setHostnameIsSet(boolean value) {
      if (!value) {
         this.hostname = null;
      }

   }

   public int getHeartbeatCount() {
      return this.heartbeatCount;
   }

   public void setHeartbeatCount(int heartbeatCount) {
      this.heartbeatCount = heartbeatCount;
      this.setHeartbeatCountIsSet(true);
   }

   public void unsetHeartbeatCount() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 4);
   }

   public boolean isSetHeartbeatCount() {
      return EncodingUtils.testBit(this.__isset_bitfield, 4);
   }

   public void setHeartbeatCountIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 4, value);
   }

   @Nullable
   public String getAgentInfo() {
      return this.agentInfo;
   }

   public void setAgentInfo(@Nullable String agentInfo) {
      this.agentInfo = agentInfo;
   }

   public void unsetAgentInfo() {
      this.agentInfo = null;
   }

   public boolean isSetAgentInfo() {
      return this.agentInfo != null;
   }

   public void setAgentInfoIsSet(boolean value) {
      if (!value) {
         this.agentInfo = null;
      }

   }

   public long getBlockedByExtId() {
      return this.blockedByExtId;
   }

   public void setBlockedByExtId(long blockedByExtId) {
      this.blockedByExtId = blockedByExtId;
      this.setBlockedByExtIdIsSet(true);
   }

   public void unsetBlockedByExtId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 5);
   }

   public boolean isSetBlockedByExtId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 5);
   }

   public void setBlockedByExtIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 5, value);
   }

   public long getBlockedByIntId() {
      return this.blockedByIntId;
   }

   public void setBlockedByIntId(long blockedByIntId) {
      this.blockedByIntId = blockedByIntId;
      this.setBlockedByIntIdIsSet(true);
   }

   public void unsetBlockedByIntId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 6);
   }

   public boolean isSetBlockedByIntId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 6);
   }

   public void setBlockedByIntIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 6, value);
   }

   public long getLockIdInternal() {
      return this.lockIdInternal;
   }

   public void setLockIdInternal(long lockIdInternal) {
      this.lockIdInternal = lockIdInternal;
      this.setLockIdInternalIsSet(true);
   }

   public void unsetLockIdInternal() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 7);
   }

   public boolean isSetLockIdInternal() {
      return EncodingUtils.testBit(this.__isset_bitfield, 7);
   }

   public void setLockIdInternalIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 7, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LOCKID:
            if (value == null) {
               this.unsetLockid();
            } else {
               this.setLockid((Long)value);
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
         case PARTNAME:
            if (value == null) {
               this.unsetPartname();
            } else {
               this.setPartname((String)value);
            }
            break;
         case STATE:
            if (value == null) {
               this.unsetState();
            } else {
               this.setState((LockState)value);
            }
            break;
         case TYPE:
            if (value == null) {
               this.unsetType();
            } else {
               this.setType((LockType)value);
            }
            break;
         case TXNID:
            if (value == null) {
               this.unsetTxnid();
            } else {
               this.setTxnid((Long)value);
            }
            break;
         case LASTHEARTBEAT:
            if (value == null) {
               this.unsetLastheartbeat();
            } else {
               this.setLastheartbeat((Long)value);
            }
            break;
         case ACQUIREDAT:
            if (value == null) {
               this.unsetAcquiredat();
            } else {
               this.setAcquiredat((Long)value);
            }
            break;
         case USER:
            if (value == null) {
               this.unsetUser();
            } else {
               this.setUser((String)value);
            }
            break;
         case HOSTNAME:
            if (value == null) {
               this.unsetHostname();
            } else {
               this.setHostname((String)value);
            }
            break;
         case HEARTBEAT_COUNT:
            if (value == null) {
               this.unsetHeartbeatCount();
            } else {
               this.setHeartbeatCount((Integer)value);
            }
            break;
         case AGENT_INFO:
            if (value == null) {
               this.unsetAgentInfo();
            } else {
               this.setAgentInfo((String)value);
            }
            break;
         case BLOCKED_BY_EXT_ID:
            if (value == null) {
               this.unsetBlockedByExtId();
            } else {
               this.setBlockedByExtId((Long)value);
            }
            break;
         case BLOCKED_BY_INT_ID:
            if (value == null) {
               this.unsetBlockedByIntId();
            } else {
               this.setBlockedByIntId((Long)value);
            }
            break;
         case LOCK_ID_INTERNAL:
            if (value == null) {
               this.unsetLockIdInternal();
            } else {
               this.setLockIdInternal((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LOCKID:
            return this.getLockid();
         case DBNAME:
            return this.getDbname();
         case TABLENAME:
            return this.getTablename();
         case PARTNAME:
            return this.getPartname();
         case STATE:
            return this.getState();
         case TYPE:
            return this.getType();
         case TXNID:
            return this.getTxnid();
         case LASTHEARTBEAT:
            return this.getLastheartbeat();
         case ACQUIREDAT:
            return this.getAcquiredat();
         case USER:
            return this.getUser();
         case HOSTNAME:
            return this.getHostname();
         case HEARTBEAT_COUNT:
            return this.getHeartbeatCount();
         case AGENT_INFO:
            return this.getAgentInfo();
         case BLOCKED_BY_EXT_ID:
            return this.getBlockedByExtId();
         case BLOCKED_BY_INT_ID:
            return this.getBlockedByIntId();
         case LOCK_ID_INTERNAL:
            return this.getLockIdInternal();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case LOCKID:
               return this.isSetLockid();
            case DBNAME:
               return this.isSetDbname();
            case TABLENAME:
               return this.isSetTablename();
            case PARTNAME:
               return this.isSetPartname();
            case STATE:
               return this.isSetState();
            case TYPE:
               return this.isSetType();
            case TXNID:
               return this.isSetTxnid();
            case LASTHEARTBEAT:
               return this.isSetLastheartbeat();
            case ACQUIREDAT:
               return this.isSetAcquiredat();
            case USER:
               return this.isSetUser();
            case HOSTNAME:
               return this.isSetHostname();
            case HEARTBEAT_COUNT:
               return this.isSetHeartbeatCount();
            case AGENT_INFO:
               return this.isSetAgentInfo();
            case BLOCKED_BY_EXT_ID:
               return this.isSetBlockedByExtId();
            case BLOCKED_BY_INT_ID:
               return this.isSetBlockedByIntId();
            case LOCK_ID_INTERNAL:
               return this.isSetLockIdInternal();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof ShowLocksResponseElement ? this.equals((ShowLocksResponseElement)that) : false;
   }

   public boolean equals(ShowLocksResponseElement that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_lockid = true;
         boolean that_present_lockid = true;
         if (this_present_lockid || that_present_lockid) {
            if (!this_present_lockid || !that_present_lockid) {
               return false;
            }

            if (this.lockid != that.lockid) {
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

         boolean this_present_partname = this.isSetPartname();
         boolean that_present_partname = that.isSetPartname();
         if (this_present_partname || that_present_partname) {
            if (!this_present_partname || !that_present_partname) {
               return false;
            }

            if (!this.partname.equals(that.partname)) {
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

         boolean this_present_txnid = this.isSetTxnid();
         boolean that_present_txnid = that.isSetTxnid();
         if (this_present_txnid || that_present_txnid) {
            if (!this_present_txnid || !that_present_txnid) {
               return false;
            }

            if (this.txnid != that.txnid) {
               return false;
            }
         }

         boolean this_present_lastheartbeat = true;
         boolean that_present_lastheartbeat = true;
         if (this_present_lastheartbeat || that_present_lastheartbeat) {
            if (!this_present_lastheartbeat || !that_present_lastheartbeat) {
               return false;
            }

            if (this.lastheartbeat != that.lastheartbeat) {
               return false;
            }
         }

         boolean this_present_acquiredat = this.isSetAcquiredat();
         boolean that_present_acquiredat = that.isSetAcquiredat();
         if (this_present_acquiredat || that_present_acquiredat) {
            if (!this_present_acquiredat || !that_present_acquiredat) {
               return false;
            }

            if (this.acquiredat != that.acquiredat) {
               return false;
            }
         }

         boolean this_present_user = this.isSetUser();
         boolean that_present_user = that.isSetUser();
         if (this_present_user || that_present_user) {
            if (!this_present_user || !that_present_user) {
               return false;
            }

            if (!this.user.equals(that.user)) {
               return false;
            }
         }

         boolean this_present_hostname = this.isSetHostname();
         boolean that_present_hostname = that.isSetHostname();
         if (this_present_hostname || that_present_hostname) {
            if (!this_present_hostname || !that_present_hostname) {
               return false;
            }

            if (!this.hostname.equals(that.hostname)) {
               return false;
            }
         }

         boolean this_present_heartbeatCount = this.isSetHeartbeatCount();
         boolean that_present_heartbeatCount = that.isSetHeartbeatCount();
         if (this_present_heartbeatCount || that_present_heartbeatCount) {
            if (!this_present_heartbeatCount || !that_present_heartbeatCount) {
               return false;
            }

            if (this.heartbeatCount != that.heartbeatCount) {
               return false;
            }
         }

         boolean this_present_agentInfo = this.isSetAgentInfo();
         boolean that_present_agentInfo = that.isSetAgentInfo();
         if (this_present_agentInfo || that_present_agentInfo) {
            if (!this_present_agentInfo || !that_present_agentInfo) {
               return false;
            }

            if (!this.agentInfo.equals(that.agentInfo)) {
               return false;
            }
         }

         boolean this_present_blockedByExtId = this.isSetBlockedByExtId();
         boolean that_present_blockedByExtId = that.isSetBlockedByExtId();
         if (this_present_blockedByExtId || that_present_blockedByExtId) {
            if (!this_present_blockedByExtId || !that_present_blockedByExtId) {
               return false;
            }

            if (this.blockedByExtId != that.blockedByExtId) {
               return false;
            }
         }

         boolean this_present_blockedByIntId = this.isSetBlockedByIntId();
         boolean that_present_blockedByIntId = that.isSetBlockedByIntId();
         if (this_present_blockedByIntId || that_present_blockedByIntId) {
            if (!this_present_blockedByIntId || !that_present_blockedByIntId) {
               return false;
            }

            if (this.blockedByIntId != that.blockedByIntId) {
               return false;
            }
         }

         boolean this_present_lockIdInternal = this.isSetLockIdInternal();
         boolean that_present_lockIdInternal = that.isSetLockIdInternal();
         if (this_present_lockIdInternal || that_present_lockIdInternal) {
            if (!this_present_lockIdInternal || !that_present_lockIdInternal) {
               return false;
            }

            if (this.lockIdInternal != that.lockIdInternal) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lockid);
      hashCode = hashCode * 8191 + (this.isSetDbname() ? 131071 : 524287);
      if (this.isSetDbname()) {
         hashCode = hashCode * 8191 + this.dbname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTablename() ? 131071 : 524287);
      if (this.isSetTablename()) {
         hashCode = hashCode * 8191 + this.tablename.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPartname() ? 131071 : 524287);
      if (this.isSetPartname()) {
         hashCode = hashCode * 8191 + this.partname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetState() ? 131071 : 524287);
      if (this.isSetState()) {
         hashCode = hashCode * 8191 + this.state.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetType() ? 131071 : 524287);
      if (this.isSetType()) {
         hashCode = hashCode * 8191 + this.type.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetTxnid() ? 131071 : 524287);
      if (this.isSetTxnid()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.txnid);
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lastheartbeat);
      hashCode = hashCode * 8191 + (this.isSetAcquiredat() ? 131071 : 524287);
      if (this.isSetAcquiredat()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.acquiredat);
      }

      hashCode = hashCode * 8191 + (this.isSetUser() ? 131071 : 524287);
      if (this.isSetUser()) {
         hashCode = hashCode * 8191 + this.user.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetHostname() ? 131071 : 524287);
      if (this.isSetHostname()) {
         hashCode = hashCode * 8191 + this.hostname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetHeartbeatCount() ? 131071 : 524287);
      if (this.isSetHeartbeatCount()) {
         hashCode = hashCode * 8191 + this.heartbeatCount;
      }

      hashCode = hashCode * 8191 + (this.isSetAgentInfo() ? 131071 : 524287);
      if (this.isSetAgentInfo()) {
         hashCode = hashCode * 8191 + this.agentInfo.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetBlockedByExtId() ? 131071 : 524287);
      if (this.isSetBlockedByExtId()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.blockedByExtId);
      }

      hashCode = hashCode * 8191 + (this.isSetBlockedByIntId() ? 131071 : 524287);
      if (this.isSetBlockedByIntId()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.blockedByIntId);
      }

      hashCode = hashCode * 8191 + (this.isSetLockIdInternal() ? 131071 : 524287);
      if (this.isSetLockIdInternal()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lockIdInternal);
      }

      return hashCode;
   }

   public int compareTo(ShowLocksResponseElement other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetLockid(), other.isSetLockid());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetLockid()) {
               lastComparison = TBaseHelper.compareTo(this.lockid, other.lockid);
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

                  lastComparison = Boolean.compare(this.isSetPartname(), other.isSetPartname());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetPartname()) {
                        lastComparison = TBaseHelper.compareTo(this.partname, other.partname);
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

                           lastComparison = Boolean.compare(this.isSetTxnid(), other.isSetTxnid());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetTxnid()) {
                                 lastComparison = TBaseHelper.compareTo(this.txnid, other.txnid);
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 }
                              }

                              lastComparison = Boolean.compare(this.isSetLastheartbeat(), other.isSetLastheartbeat());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetLastheartbeat()) {
                                    lastComparison = TBaseHelper.compareTo(this.lastheartbeat, other.lastheartbeat);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetAcquiredat(), other.isSetAcquiredat());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetAcquiredat()) {
                                       lastComparison = TBaseHelper.compareTo(this.acquiredat, other.acquiredat);
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       }
                                    }

                                    lastComparison = Boolean.compare(this.isSetUser(), other.isSetUser());
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    } else {
                                       if (this.isSetUser()) {
                                          lastComparison = TBaseHelper.compareTo(this.user, other.user);
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          }
                                       }

                                       lastComparison = Boolean.compare(this.isSetHostname(), other.isSetHostname());
                                       if (lastComparison != 0) {
                                          return lastComparison;
                                       } else {
                                          if (this.isSetHostname()) {
                                             lastComparison = TBaseHelper.compareTo(this.hostname, other.hostname);
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             }
                                          }

                                          lastComparison = Boolean.compare(this.isSetHeartbeatCount(), other.isSetHeartbeatCount());
                                          if (lastComparison != 0) {
                                             return lastComparison;
                                          } else {
                                             if (this.isSetHeartbeatCount()) {
                                                lastComparison = TBaseHelper.compareTo(this.heartbeatCount, other.heartbeatCount);
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                }
                                             }

                                             lastComparison = Boolean.compare(this.isSetAgentInfo(), other.isSetAgentInfo());
                                             if (lastComparison != 0) {
                                                return lastComparison;
                                             } else {
                                                if (this.isSetAgentInfo()) {
                                                   lastComparison = TBaseHelper.compareTo(this.agentInfo, other.agentInfo);
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   }
                                                }

                                                lastComparison = Boolean.compare(this.isSetBlockedByExtId(), other.isSetBlockedByExtId());
                                                if (lastComparison != 0) {
                                                   return lastComparison;
                                                } else {
                                                   if (this.isSetBlockedByExtId()) {
                                                      lastComparison = TBaseHelper.compareTo(this.blockedByExtId, other.blockedByExtId);
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      }
                                                   }

                                                   lastComparison = Boolean.compare(this.isSetBlockedByIntId(), other.isSetBlockedByIntId());
                                                   if (lastComparison != 0) {
                                                      return lastComparison;
                                                   } else {
                                                      if (this.isSetBlockedByIntId()) {
                                                         lastComparison = TBaseHelper.compareTo(this.blockedByIntId, other.blockedByIntId);
                                                         if (lastComparison != 0) {
                                                            return lastComparison;
                                                         }
                                                      }

                                                      lastComparison = Boolean.compare(this.isSetLockIdInternal(), other.isSetLockIdInternal());
                                                      if (lastComparison != 0) {
                                                         return lastComparison;
                                                      } else {
                                                         if (this.isSetLockIdInternal()) {
                                                            lastComparison = TBaseHelper.compareTo(this.lockIdInternal, other.lockIdInternal);
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
      return ShowLocksResponseElement._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ShowLocksResponseElement(");
      boolean first = true;
      sb.append("lockid:");
      sb.append(this.lockid);
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

      if (this.isSetPartname()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("partname:");
         if (this.partname == null) {
            sb.append("null");
         } else {
            sb.append(this.partname);
         }

         first = false;
      }

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
      if (this.isSetTxnid()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("txnid:");
         sb.append(this.txnid);
         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("lastheartbeat:");
      sb.append(this.lastheartbeat);
      first = false;
      if (this.isSetAcquiredat()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("acquiredat:");
         sb.append(this.acquiredat);
         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("user:");
      if (this.user == null) {
         sb.append("null");
      } else {
         sb.append(this.user);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("hostname:");
      if (this.hostname == null) {
         sb.append("null");
      } else {
         sb.append(this.hostname);
      }

      first = false;
      if (this.isSetHeartbeatCount()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("heartbeatCount:");
         sb.append(this.heartbeatCount);
         first = false;
      }

      if (this.isSetAgentInfo()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("agentInfo:");
         if (this.agentInfo == null) {
            sb.append("null");
         } else {
            sb.append(this.agentInfo);
         }

         first = false;
      }

      if (this.isSetBlockedByExtId()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("blockedByExtId:");
         sb.append(this.blockedByExtId);
         first = false;
      }

      if (this.isSetBlockedByIntId()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("blockedByIntId:");
         sb.append(this.blockedByIntId);
         first = false;
      }

      if (this.isSetLockIdInternal()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("lockIdInternal:");
         sb.append(this.lockIdInternal);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetLockid()) {
         throw new TProtocolException("Required field 'lockid' is unset! Struct:" + this.toString());
      } else if (!this.isSetDbname()) {
         throw new TProtocolException("Required field 'dbname' is unset! Struct:" + this.toString());
      } else if (!this.isSetState()) {
         throw new TProtocolException("Required field 'state' is unset! Struct:" + this.toString());
      } else if (!this.isSetType()) {
         throw new TProtocolException("Required field 'type' is unset! Struct:" + this.toString());
      } else if (!this.isSetLastheartbeat()) {
         throw new TProtocolException("Required field 'lastheartbeat' is unset! Struct:" + this.toString());
      } else if (!this.isSetUser()) {
         throw new TProtocolException("Required field 'user' is unset! Struct:" + this.toString());
      } else if (!this.isSetHostname()) {
         throw new TProtocolException("Required field 'hostname' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{ShowLocksResponseElement._Fields.TABLENAME, ShowLocksResponseElement._Fields.PARTNAME, ShowLocksResponseElement._Fields.TXNID, ShowLocksResponseElement._Fields.ACQUIREDAT, ShowLocksResponseElement._Fields.HEARTBEAT_COUNT, ShowLocksResponseElement._Fields.AGENT_INFO, ShowLocksResponseElement._Fields.BLOCKED_BY_EXT_ID, ShowLocksResponseElement._Fields.BLOCKED_BY_INT_ID, ShowLocksResponseElement._Fields.LOCK_ID_INTERNAL};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(ShowLocksResponseElement._Fields.LOCKID, new FieldMetaData("lockid", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowLocksResponseElement._Fields.DBNAME, new FieldMetaData("dbname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksResponseElement._Fields.TABLENAME, new FieldMetaData("tablename", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksResponseElement._Fields.PARTNAME, new FieldMetaData("partname", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksResponseElement._Fields.STATE, new FieldMetaData("state", (byte)1, new EnumMetaData((byte)16, LockState.class)));
      tmpMap.put(ShowLocksResponseElement._Fields.TYPE, new FieldMetaData("type", (byte)1, new EnumMetaData((byte)16, LockType.class)));
      tmpMap.put(ShowLocksResponseElement._Fields.TXNID, new FieldMetaData("txnid", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowLocksResponseElement._Fields.LASTHEARTBEAT, new FieldMetaData("lastheartbeat", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowLocksResponseElement._Fields.ACQUIREDAT, new FieldMetaData("acquiredat", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowLocksResponseElement._Fields.USER, new FieldMetaData("user", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksResponseElement._Fields.HOSTNAME, new FieldMetaData("hostname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksResponseElement._Fields.HEARTBEAT_COUNT, new FieldMetaData("heartbeatCount", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(ShowLocksResponseElement._Fields.AGENT_INFO, new FieldMetaData("agentInfo", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(ShowLocksResponseElement._Fields.BLOCKED_BY_EXT_ID, new FieldMetaData("blockedByExtId", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowLocksResponseElement._Fields.BLOCKED_BY_INT_ID, new FieldMetaData("blockedByIntId", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(ShowLocksResponseElement._Fields.LOCK_ID_INTERNAL, new FieldMetaData("lockIdInternal", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(ShowLocksResponseElement.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LOCKID((short)1, "lockid"),
      DBNAME((short)2, "dbname"),
      TABLENAME((short)3, "tablename"),
      PARTNAME((short)4, "partname"),
      STATE((short)5, "state"),
      TYPE((short)6, "type"),
      TXNID((short)7, "txnid"),
      LASTHEARTBEAT((short)8, "lastheartbeat"),
      ACQUIREDAT((short)9, "acquiredat"),
      USER((short)10, "user"),
      HOSTNAME((short)11, "hostname"),
      HEARTBEAT_COUNT((short)12, "heartbeatCount"),
      AGENT_INFO((short)13, "agentInfo"),
      BLOCKED_BY_EXT_ID((short)14, "blockedByExtId"),
      BLOCKED_BY_INT_ID((short)15, "blockedByIntId"),
      LOCK_ID_INTERNAL((short)16, "lockIdInternal");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LOCKID;
            case 2:
               return DBNAME;
            case 3:
               return TABLENAME;
            case 4:
               return PARTNAME;
            case 5:
               return STATE;
            case 6:
               return TYPE;
            case 7:
               return TXNID;
            case 8:
               return LASTHEARTBEAT;
            case 9:
               return ACQUIREDAT;
            case 10:
               return USER;
            case 11:
               return HOSTNAME;
            case 12:
               return HEARTBEAT_COUNT;
            case 13:
               return AGENT_INFO;
            case 14:
               return BLOCKED_BY_EXT_ID;
            case 15:
               return BLOCKED_BY_INT_ID;
            case 16:
               return LOCK_ID_INTERNAL;
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

   private static class ShowLocksResponseElementStandardSchemeFactory implements SchemeFactory {
      private ShowLocksResponseElementStandardSchemeFactory() {
      }

      public ShowLocksResponseElementStandardScheme getScheme() {
         return new ShowLocksResponseElementStandardScheme();
      }
   }

   private static class ShowLocksResponseElementStandardScheme extends StandardScheme {
      private ShowLocksResponseElementStandardScheme() {
      }

      public void read(TProtocol iprot, ShowLocksResponseElement struct) throws TException {
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
                  if (schemeField.type == 10) {
                     struct.lockid = iprot.readI64();
                     struct.setLockidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.dbname = iprot.readString();
                     struct.setDbnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.tablename = iprot.readString();
                     struct.setTablenameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.partname = iprot.readString();
                     struct.setPartnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.state = LockState.findByValue(iprot.readI32());
                     struct.setStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.type = LockType.findByValue(iprot.readI32());
                     struct.setTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 10) {
                     struct.txnid = iprot.readI64();
                     struct.setTxnidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 10) {
                     struct.lastheartbeat = iprot.readI64();
                     struct.setLastheartbeatIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 10) {
                     struct.acquiredat = iprot.readI64();
                     struct.setAcquiredatIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 10:
                  if (schemeField.type == 11) {
                     struct.user = iprot.readString();
                     struct.setUserIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 11:
                  if (schemeField.type == 11) {
                     struct.hostname = iprot.readString();
                     struct.setHostnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 12:
                  if (schemeField.type == 8) {
                     struct.heartbeatCount = iprot.readI32();
                     struct.setHeartbeatCountIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 13:
                  if (schemeField.type == 11) {
                     struct.agentInfo = iprot.readString();
                     struct.setAgentInfoIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 14:
                  if (schemeField.type == 10) {
                     struct.blockedByExtId = iprot.readI64();
                     struct.setBlockedByExtIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 15:
                  if (schemeField.type == 10) {
                     struct.blockedByIntId = iprot.readI64();
                     struct.setBlockedByIntIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 16:
                  if (schemeField.type == 10) {
                     struct.lockIdInternal = iprot.readI64();
                     struct.setLockIdInternalIsSet(true);
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

      public void write(TProtocol oprot, ShowLocksResponseElement struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(ShowLocksResponseElement.STRUCT_DESC);
         oprot.writeFieldBegin(ShowLocksResponseElement.LOCKID_FIELD_DESC);
         oprot.writeI64(struct.lockid);
         oprot.writeFieldEnd();
         if (struct.dbname != null) {
            oprot.writeFieldBegin(ShowLocksResponseElement.DBNAME_FIELD_DESC);
            oprot.writeString(struct.dbname);
            oprot.writeFieldEnd();
         }

         if (struct.tablename != null && struct.isSetTablename()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.TABLENAME_FIELD_DESC);
            oprot.writeString(struct.tablename);
            oprot.writeFieldEnd();
         }

         if (struct.partname != null && struct.isSetPartname()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.PARTNAME_FIELD_DESC);
            oprot.writeString(struct.partname);
            oprot.writeFieldEnd();
         }

         if (struct.state != null) {
            oprot.writeFieldBegin(ShowLocksResponseElement.STATE_FIELD_DESC);
            oprot.writeI32(struct.state.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.type != null) {
            oprot.writeFieldBegin(ShowLocksResponseElement.TYPE_FIELD_DESC);
            oprot.writeI32(struct.type.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.isSetTxnid()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.TXNID_FIELD_DESC);
            oprot.writeI64(struct.txnid);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(ShowLocksResponseElement.LASTHEARTBEAT_FIELD_DESC);
         oprot.writeI64(struct.lastheartbeat);
         oprot.writeFieldEnd();
         if (struct.isSetAcquiredat()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.ACQUIREDAT_FIELD_DESC);
            oprot.writeI64(struct.acquiredat);
            oprot.writeFieldEnd();
         }

         if (struct.user != null) {
            oprot.writeFieldBegin(ShowLocksResponseElement.USER_FIELD_DESC);
            oprot.writeString(struct.user);
            oprot.writeFieldEnd();
         }

         if (struct.hostname != null) {
            oprot.writeFieldBegin(ShowLocksResponseElement.HOSTNAME_FIELD_DESC);
            oprot.writeString(struct.hostname);
            oprot.writeFieldEnd();
         }

         if (struct.isSetHeartbeatCount()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.HEARTBEAT_COUNT_FIELD_DESC);
            oprot.writeI32(struct.heartbeatCount);
            oprot.writeFieldEnd();
         }

         if (struct.agentInfo != null && struct.isSetAgentInfo()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.AGENT_INFO_FIELD_DESC);
            oprot.writeString(struct.agentInfo);
            oprot.writeFieldEnd();
         }

         if (struct.isSetBlockedByExtId()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.BLOCKED_BY_EXT_ID_FIELD_DESC);
            oprot.writeI64(struct.blockedByExtId);
            oprot.writeFieldEnd();
         }

         if (struct.isSetBlockedByIntId()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.BLOCKED_BY_INT_ID_FIELD_DESC);
            oprot.writeI64(struct.blockedByIntId);
            oprot.writeFieldEnd();
         }

         if (struct.isSetLockIdInternal()) {
            oprot.writeFieldBegin(ShowLocksResponseElement.LOCK_ID_INTERNAL_FIELD_DESC);
            oprot.writeI64(struct.lockIdInternal);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class ShowLocksResponseElementTupleSchemeFactory implements SchemeFactory {
      private ShowLocksResponseElementTupleSchemeFactory() {
      }

      public ShowLocksResponseElementTupleScheme getScheme() {
         return new ShowLocksResponseElementTupleScheme();
      }
   }

   private static class ShowLocksResponseElementTupleScheme extends TupleScheme {
      private ShowLocksResponseElementTupleScheme() {
      }

      public void write(TProtocol prot, ShowLocksResponseElement struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.lockid);
         oprot.writeString(struct.dbname);
         oprot.writeI32(struct.state.getValue());
         oprot.writeI32(struct.type.getValue());
         oprot.writeI64(struct.lastheartbeat);
         oprot.writeString(struct.user);
         oprot.writeString(struct.hostname);
         BitSet optionals = new BitSet();
         if (struct.isSetTablename()) {
            optionals.set(0);
         }

         if (struct.isSetPartname()) {
            optionals.set(1);
         }

         if (struct.isSetTxnid()) {
            optionals.set(2);
         }

         if (struct.isSetAcquiredat()) {
            optionals.set(3);
         }

         if (struct.isSetHeartbeatCount()) {
            optionals.set(4);
         }

         if (struct.isSetAgentInfo()) {
            optionals.set(5);
         }

         if (struct.isSetBlockedByExtId()) {
            optionals.set(6);
         }

         if (struct.isSetBlockedByIntId()) {
            optionals.set(7);
         }

         if (struct.isSetLockIdInternal()) {
            optionals.set(8);
         }

         oprot.writeBitSet(optionals, 9);
         if (struct.isSetTablename()) {
            oprot.writeString(struct.tablename);
         }

         if (struct.isSetPartname()) {
            oprot.writeString(struct.partname);
         }

         if (struct.isSetTxnid()) {
            oprot.writeI64(struct.txnid);
         }

         if (struct.isSetAcquiredat()) {
            oprot.writeI64(struct.acquiredat);
         }

         if (struct.isSetHeartbeatCount()) {
            oprot.writeI32(struct.heartbeatCount);
         }

         if (struct.isSetAgentInfo()) {
            oprot.writeString(struct.agentInfo);
         }

         if (struct.isSetBlockedByExtId()) {
            oprot.writeI64(struct.blockedByExtId);
         }

         if (struct.isSetBlockedByIntId()) {
            oprot.writeI64(struct.blockedByIntId);
         }

         if (struct.isSetLockIdInternal()) {
            oprot.writeI64(struct.lockIdInternal);
         }

      }

      public void read(TProtocol prot, ShowLocksResponseElement struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.lockid = iprot.readI64();
         struct.setLockidIsSet(true);
         struct.dbname = iprot.readString();
         struct.setDbnameIsSet(true);
         struct.state = LockState.findByValue(iprot.readI32());
         struct.setStateIsSet(true);
         struct.type = LockType.findByValue(iprot.readI32());
         struct.setTypeIsSet(true);
         struct.lastheartbeat = iprot.readI64();
         struct.setLastheartbeatIsSet(true);
         struct.user = iprot.readString();
         struct.setUserIsSet(true);
         struct.hostname = iprot.readString();
         struct.setHostnameIsSet(true);
         BitSet incoming = iprot.readBitSet(9);
         if (incoming.get(0)) {
            struct.tablename = iprot.readString();
            struct.setTablenameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.partname = iprot.readString();
            struct.setPartnameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.txnid = iprot.readI64();
            struct.setTxnidIsSet(true);
         }

         if (incoming.get(3)) {
            struct.acquiredat = iprot.readI64();
            struct.setAcquiredatIsSet(true);
         }

         if (incoming.get(4)) {
            struct.heartbeatCount = iprot.readI32();
            struct.setHeartbeatCountIsSet(true);
         }

         if (incoming.get(5)) {
            struct.agentInfo = iprot.readString();
            struct.setAgentInfoIsSet(true);
         }

         if (incoming.get(6)) {
            struct.blockedByExtId = iprot.readI64();
            struct.setBlockedByExtIdIsSet(true);
         }

         if (incoming.get(7)) {
            struct.blockedByIntId = iprot.readI64();
            struct.setBlockedByIntIdIsSet(true);
         }

         if (incoming.get(8)) {
            struct.lockIdInternal = iprot.readI64();
            struct.setLockIdInternalIsSet(true);
         }

      }
   }
}
