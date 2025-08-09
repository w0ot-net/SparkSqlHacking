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

public class TxnInfo implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TxnInfo");
   private static final TField ID_FIELD_DESC = new TField("id", (byte)10, (short)1);
   private static final TField STATE_FIELD_DESC = new TField("state", (byte)8, (short)2);
   private static final TField USER_FIELD_DESC = new TField("user", (byte)11, (short)3);
   private static final TField HOSTNAME_FIELD_DESC = new TField("hostname", (byte)11, (short)4);
   private static final TField AGENT_INFO_FIELD_DESC = new TField("agentInfo", (byte)11, (short)5);
   private static final TField HEARTBEAT_COUNT_FIELD_DESC = new TField("heartbeatCount", (byte)8, (short)6);
   private static final TField META_INFO_FIELD_DESC = new TField("metaInfo", (byte)11, (short)7);
   private static final TField STARTED_TIME_FIELD_DESC = new TField("startedTime", (byte)10, (short)8);
   private static final TField LAST_HEARTBEAT_TIME_FIELD_DESC = new TField("lastHeartbeatTime", (byte)10, (short)9);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TxnInfoStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TxnInfoTupleSchemeFactory();
   private long id;
   @Nullable
   private TxnState state;
   @Nullable
   private String user;
   @Nullable
   private String hostname;
   @Nullable
   private String agentInfo;
   private int heartbeatCount;
   @Nullable
   private String metaInfo;
   private long startedTime;
   private long lastHeartbeatTime;
   private static final int __ID_ISSET_ID = 0;
   private static final int __HEARTBEATCOUNT_ISSET_ID = 1;
   private static final int __STARTEDTIME_ISSET_ID = 2;
   private static final int __LASTHEARTBEATTIME_ISSET_ID = 3;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TxnInfo() {
      this.__isset_bitfield = 0;
      this.agentInfo = "Unknown";
      this.heartbeatCount = 0;
   }

   public TxnInfo(long id, TxnState state, String user, String hostname) {
      this();
      this.id = id;
      this.setIdIsSet(true);
      this.state = state;
      this.user = user;
      this.hostname = hostname;
   }

   public TxnInfo(TxnInfo other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.id = other.id;
      if (other.isSetState()) {
         this.state = other.state;
      }

      if (other.isSetUser()) {
         this.user = other.user;
      }

      if (other.isSetHostname()) {
         this.hostname = other.hostname;
      }

      if (other.isSetAgentInfo()) {
         this.agentInfo = other.agentInfo;
      }

      this.heartbeatCount = other.heartbeatCount;
      if (other.isSetMetaInfo()) {
         this.metaInfo = other.metaInfo;
      }

      this.startedTime = other.startedTime;
      this.lastHeartbeatTime = other.lastHeartbeatTime;
   }

   public TxnInfo deepCopy() {
      return new TxnInfo(this);
   }

   public void clear() {
      this.setIdIsSet(false);
      this.id = 0L;
      this.state = null;
      this.user = null;
      this.hostname = null;
      this.agentInfo = "Unknown";
      this.heartbeatCount = 0;
      this.metaInfo = null;
      this.setStartedTimeIsSet(false);
      this.startedTime = 0L;
      this.setLastHeartbeatTimeIsSet(false);
      this.lastHeartbeatTime = 0L;
   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
      this.setIdIsSet(true);
   }

   public void unsetId() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetId() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setIdIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public TxnState getState() {
      return this.state;
   }

   public void setState(@Nullable TxnState state) {
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

   public int getHeartbeatCount() {
      return this.heartbeatCount;
   }

   public void setHeartbeatCount(int heartbeatCount) {
      this.heartbeatCount = heartbeatCount;
      this.setHeartbeatCountIsSet(true);
   }

   public void unsetHeartbeatCount() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetHeartbeatCount() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setHeartbeatCountIsSet(boolean value) {
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

   public long getStartedTime() {
      return this.startedTime;
   }

   public void setStartedTime(long startedTime) {
      this.startedTime = startedTime;
      this.setStartedTimeIsSet(true);
   }

   public void unsetStartedTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetStartedTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setStartedTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public long getLastHeartbeatTime() {
      return this.lastHeartbeatTime;
   }

   public void setLastHeartbeatTime(long lastHeartbeatTime) {
      this.lastHeartbeatTime = lastHeartbeatTime;
      this.setLastHeartbeatTimeIsSet(true);
   }

   public void unsetLastHeartbeatTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetLastHeartbeatTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setLastHeartbeatTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case ID:
            if (value == null) {
               this.unsetId();
            } else {
               this.setId((Long)value);
            }
            break;
         case STATE:
            if (value == null) {
               this.unsetState();
            } else {
               this.setState((TxnState)value);
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
         case AGENT_INFO:
            if (value == null) {
               this.unsetAgentInfo();
            } else {
               this.setAgentInfo((String)value);
            }
            break;
         case HEARTBEAT_COUNT:
            if (value == null) {
               this.unsetHeartbeatCount();
            } else {
               this.setHeartbeatCount((Integer)value);
            }
            break;
         case META_INFO:
            if (value == null) {
               this.unsetMetaInfo();
            } else {
               this.setMetaInfo((String)value);
            }
            break;
         case STARTED_TIME:
            if (value == null) {
               this.unsetStartedTime();
            } else {
               this.setStartedTime((Long)value);
            }
            break;
         case LAST_HEARTBEAT_TIME:
            if (value == null) {
               this.unsetLastHeartbeatTime();
            } else {
               this.setLastHeartbeatTime((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ID:
            return this.getId();
         case STATE:
            return this.getState();
         case USER:
            return this.getUser();
         case HOSTNAME:
            return this.getHostname();
         case AGENT_INFO:
            return this.getAgentInfo();
         case HEARTBEAT_COUNT:
            return this.getHeartbeatCount();
         case META_INFO:
            return this.getMetaInfo();
         case STARTED_TIME:
            return this.getStartedTime();
         case LAST_HEARTBEAT_TIME:
            return this.getLastHeartbeatTime();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case ID:
               return this.isSetId();
            case STATE:
               return this.isSetState();
            case USER:
               return this.isSetUser();
            case HOSTNAME:
               return this.isSetHostname();
            case AGENT_INFO:
               return this.isSetAgentInfo();
            case HEARTBEAT_COUNT:
               return this.isSetHeartbeatCount();
            case META_INFO:
               return this.isSetMetaInfo();
            case STARTED_TIME:
               return this.isSetStartedTime();
            case LAST_HEARTBEAT_TIME:
               return this.isSetLastHeartbeatTime();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TxnInfo ? this.equals((TxnInfo)that) : false;
   }

   public boolean equals(TxnInfo that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_id = true;
         boolean that_present_id = true;
         if (this_present_id || that_present_id) {
            if (!this_present_id || !that_present_id) {
               return false;
            }

            if (this.id != that.id) {
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

         boolean this_present_startedTime = this.isSetStartedTime();
         boolean that_present_startedTime = that.isSetStartedTime();
         if (this_present_startedTime || that_present_startedTime) {
            if (!this_present_startedTime || !that_present_startedTime) {
               return false;
            }

            if (this.startedTime != that.startedTime) {
               return false;
            }
         }

         boolean this_present_lastHeartbeatTime = this.isSetLastHeartbeatTime();
         boolean that_present_lastHeartbeatTime = that.isSetLastHeartbeatTime();
         if (this_present_lastHeartbeatTime || that_present_lastHeartbeatTime) {
            if (!this_present_lastHeartbeatTime || !that_present_lastHeartbeatTime) {
               return false;
            }

            if (this.lastHeartbeatTime != that.lastHeartbeatTime) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.id);
      hashCode = hashCode * 8191 + (this.isSetState() ? 131071 : 524287);
      if (this.isSetState()) {
         hashCode = hashCode * 8191 + this.state.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetUser() ? 131071 : 524287);
      if (this.isSetUser()) {
         hashCode = hashCode * 8191 + this.user.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetHostname() ? 131071 : 524287);
      if (this.isSetHostname()) {
         hashCode = hashCode * 8191 + this.hostname.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetAgentInfo() ? 131071 : 524287);
      if (this.isSetAgentInfo()) {
         hashCode = hashCode * 8191 + this.agentInfo.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetHeartbeatCount() ? 131071 : 524287);
      if (this.isSetHeartbeatCount()) {
         hashCode = hashCode * 8191 + this.heartbeatCount;
      }

      hashCode = hashCode * 8191 + (this.isSetMetaInfo() ? 131071 : 524287);
      if (this.isSetMetaInfo()) {
         hashCode = hashCode * 8191 + this.metaInfo.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetStartedTime() ? 131071 : 524287);
      if (this.isSetStartedTime()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.startedTime);
      }

      hashCode = hashCode * 8191 + (this.isSetLastHeartbeatTime() ? 131071 : 524287);
      if (this.isSetLastHeartbeatTime()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lastHeartbeatTime);
      }

      return hashCode;
   }

   public int compareTo(TxnInfo other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

                              lastComparison = Boolean.compare(this.isSetStartedTime(), other.isSetStartedTime());
                              if (lastComparison != 0) {
                                 return lastComparison;
                              } else {
                                 if (this.isSetStartedTime()) {
                                    lastComparison = TBaseHelper.compareTo(this.startedTime, other.startedTime);
                                    if (lastComparison != 0) {
                                       return lastComparison;
                                    }
                                 }

                                 lastComparison = Boolean.compare(this.isSetLastHeartbeatTime(), other.isSetLastHeartbeatTime());
                                 if (lastComparison != 0) {
                                    return lastComparison;
                                 } else {
                                    if (this.isSetLastHeartbeatTime()) {
                                       lastComparison = TBaseHelper.compareTo(this.lastHeartbeatTime, other.lastHeartbeatTime);
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
      return TxnInfo._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TxnInfo(");
      boolean first = true;
      sb.append("id:");
      sb.append(this.id);
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

      if (this.isSetHeartbeatCount()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("heartbeatCount:");
         sb.append(this.heartbeatCount);
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

      if (this.isSetStartedTime()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("startedTime:");
         sb.append(this.startedTime);
         first = false;
      }

      if (this.isSetLastHeartbeatTime()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("lastHeartbeatTime:");
         sb.append(this.lastHeartbeatTime);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetId()) {
         throw new TProtocolException("Required field 'id' is unset! Struct:" + this.toString());
      } else if (!this.isSetState()) {
         throw new TProtocolException("Required field 'state' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TxnInfo._Fields.AGENT_INFO, TxnInfo._Fields.HEARTBEAT_COUNT, TxnInfo._Fields.META_INFO, TxnInfo._Fields.STARTED_TIME, TxnInfo._Fields.LAST_HEARTBEAT_TIME};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TxnInfo._Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(TxnInfo._Fields.STATE, new FieldMetaData("state", (byte)1, new EnumMetaData((byte)16, TxnState.class)));
      tmpMap.put(TxnInfo._Fields.USER, new FieldMetaData("user", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TxnInfo._Fields.HOSTNAME, new FieldMetaData("hostname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TxnInfo._Fields.AGENT_INFO, new FieldMetaData("agentInfo", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TxnInfo._Fields.HEARTBEAT_COUNT, new FieldMetaData("heartbeatCount", (byte)2, new FieldValueMetaData((byte)8)));
      tmpMap.put(TxnInfo._Fields.META_INFO, new FieldMetaData("metaInfo", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TxnInfo._Fields.STARTED_TIME, new FieldMetaData("startedTime", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(TxnInfo._Fields.LAST_HEARTBEAT_TIME, new FieldMetaData("lastHeartbeatTime", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TxnInfo.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ID((short)1, "id"),
      STATE((short)2, "state"),
      USER((short)3, "user"),
      HOSTNAME((short)4, "hostname"),
      AGENT_INFO((short)5, "agentInfo"),
      HEARTBEAT_COUNT((short)6, "heartbeatCount"),
      META_INFO((short)7, "metaInfo"),
      STARTED_TIME((short)8, "startedTime"),
      LAST_HEARTBEAT_TIME((short)9, "lastHeartbeatTime");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ID;
            case 2:
               return STATE;
            case 3:
               return USER;
            case 4:
               return HOSTNAME;
            case 5:
               return AGENT_INFO;
            case 6:
               return HEARTBEAT_COUNT;
            case 7:
               return META_INFO;
            case 8:
               return STARTED_TIME;
            case 9:
               return LAST_HEARTBEAT_TIME;
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

   private static class TxnInfoStandardSchemeFactory implements SchemeFactory {
      private TxnInfoStandardSchemeFactory() {
      }

      public TxnInfoStandardScheme getScheme() {
         return new TxnInfoStandardScheme();
      }
   }

   private static class TxnInfoStandardScheme extends StandardScheme {
      private TxnInfoStandardScheme() {
      }

      public void read(TProtocol iprot, TxnInfo struct) throws TException {
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
                     struct.id = iprot.readI64();
                     struct.setIdIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.state = TxnState.findByValue(iprot.readI32());
                     struct.setStateIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.user = iprot.readString();
                     struct.setUserIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.hostname = iprot.readString();
                     struct.setHostnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.agentInfo = iprot.readString();
                     struct.setAgentInfoIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.heartbeatCount = iprot.readI32();
                     struct.setHeartbeatCountIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 11) {
                     struct.metaInfo = iprot.readString();
                     struct.setMetaInfoIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 8:
                  if (schemeField.type == 10) {
                     struct.startedTime = iprot.readI64();
                     struct.setStartedTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 9:
                  if (schemeField.type == 10) {
                     struct.lastHeartbeatTime = iprot.readI64();
                     struct.setLastHeartbeatTimeIsSet(true);
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

      public void write(TProtocol oprot, TxnInfo struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TxnInfo.STRUCT_DESC);
         oprot.writeFieldBegin(TxnInfo.ID_FIELD_DESC);
         oprot.writeI64(struct.id);
         oprot.writeFieldEnd();
         if (struct.state != null) {
            oprot.writeFieldBegin(TxnInfo.STATE_FIELD_DESC);
            oprot.writeI32(struct.state.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.user != null) {
            oprot.writeFieldBegin(TxnInfo.USER_FIELD_DESC);
            oprot.writeString(struct.user);
            oprot.writeFieldEnd();
         }

         if (struct.hostname != null) {
            oprot.writeFieldBegin(TxnInfo.HOSTNAME_FIELD_DESC);
            oprot.writeString(struct.hostname);
            oprot.writeFieldEnd();
         }

         if (struct.agentInfo != null && struct.isSetAgentInfo()) {
            oprot.writeFieldBegin(TxnInfo.AGENT_INFO_FIELD_DESC);
            oprot.writeString(struct.agentInfo);
            oprot.writeFieldEnd();
         }

         if (struct.isSetHeartbeatCount()) {
            oprot.writeFieldBegin(TxnInfo.HEARTBEAT_COUNT_FIELD_DESC);
            oprot.writeI32(struct.heartbeatCount);
            oprot.writeFieldEnd();
         }

         if (struct.metaInfo != null && struct.isSetMetaInfo()) {
            oprot.writeFieldBegin(TxnInfo.META_INFO_FIELD_DESC);
            oprot.writeString(struct.metaInfo);
            oprot.writeFieldEnd();
         }

         if (struct.isSetStartedTime()) {
            oprot.writeFieldBegin(TxnInfo.STARTED_TIME_FIELD_DESC);
            oprot.writeI64(struct.startedTime);
            oprot.writeFieldEnd();
         }

         if (struct.isSetLastHeartbeatTime()) {
            oprot.writeFieldBegin(TxnInfo.LAST_HEARTBEAT_TIME_FIELD_DESC);
            oprot.writeI64(struct.lastHeartbeatTime);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TxnInfoTupleSchemeFactory implements SchemeFactory {
      private TxnInfoTupleSchemeFactory() {
      }

      public TxnInfoTupleScheme getScheme() {
         return new TxnInfoTupleScheme();
      }
   }

   private static class TxnInfoTupleScheme extends TupleScheme {
      private TxnInfoTupleScheme() {
      }

      public void write(TProtocol prot, TxnInfo struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.id);
         oprot.writeI32(struct.state.getValue());
         oprot.writeString(struct.user);
         oprot.writeString(struct.hostname);
         BitSet optionals = new BitSet();
         if (struct.isSetAgentInfo()) {
            optionals.set(0);
         }

         if (struct.isSetHeartbeatCount()) {
            optionals.set(1);
         }

         if (struct.isSetMetaInfo()) {
            optionals.set(2);
         }

         if (struct.isSetStartedTime()) {
            optionals.set(3);
         }

         if (struct.isSetLastHeartbeatTime()) {
            optionals.set(4);
         }

         oprot.writeBitSet(optionals, 5);
         if (struct.isSetAgentInfo()) {
            oprot.writeString(struct.agentInfo);
         }

         if (struct.isSetHeartbeatCount()) {
            oprot.writeI32(struct.heartbeatCount);
         }

         if (struct.isSetMetaInfo()) {
            oprot.writeString(struct.metaInfo);
         }

         if (struct.isSetStartedTime()) {
            oprot.writeI64(struct.startedTime);
         }

         if (struct.isSetLastHeartbeatTime()) {
            oprot.writeI64(struct.lastHeartbeatTime);
         }

      }

      public void read(TProtocol prot, TxnInfo struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.id = iprot.readI64();
         struct.setIdIsSet(true);
         struct.state = TxnState.findByValue(iprot.readI32());
         struct.setStateIsSet(true);
         struct.user = iprot.readString();
         struct.setUserIsSet(true);
         struct.hostname = iprot.readString();
         struct.setHostnameIsSet(true);
         BitSet incoming = iprot.readBitSet(5);
         if (incoming.get(0)) {
            struct.agentInfo = iprot.readString();
            struct.setAgentInfoIsSet(true);
         }

         if (incoming.get(1)) {
            struct.heartbeatCount = iprot.readI32();
            struct.setHeartbeatCountIsSet(true);
         }

         if (incoming.get(2)) {
            struct.metaInfo = iprot.readString();
            struct.setMetaInfoIsSet(true);
         }

         if (incoming.get(3)) {
            struct.startedTime = iprot.readI64();
            struct.setStartedTimeIsSet(true);
         }

         if (incoming.get(4)) {
            struct.lastHeartbeatTime = iprot.readI64();
            struct.setLastHeartbeatTimeIsSet(true);
         }

      }
   }
}
