package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

@Public
@Stable
public class TExecuteStatementReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TExecuteStatementReq");
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)1);
   private static final TField STATEMENT_FIELD_DESC = new TField("statement", (byte)11, (short)2);
   private static final TField CONF_OVERLAY_FIELD_DESC = new TField("confOverlay", (byte)13, (short)3);
   private static final TField RUN_ASYNC_FIELD_DESC = new TField("runAsync", (byte)2, (short)4);
   private static final TField QUERY_TIMEOUT_FIELD_DESC = new TField("queryTimeout", (byte)10, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TExecuteStatementReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TExecuteStatementReqTupleSchemeFactory();
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private String statement;
   @Nullable
   private Map confOverlay;
   private boolean runAsync;
   private long queryTimeout;
   private static final int __RUNASYNC_ISSET_ID = 0;
   private static final int __QUERYTIMEOUT_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TExecuteStatementReq() {
      this.__isset_bitfield = 0;
      this.runAsync = false;
      this.queryTimeout = 0L;
   }

   public TExecuteStatementReq(TSessionHandle sessionHandle, String statement) {
      this();
      this.sessionHandle = sessionHandle;
      this.statement = statement;
   }

   public TExecuteStatementReq(TExecuteStatementReq other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetStatement()) {
         this.statement = other.statement;
      }

      if (other.isSetConfOverlay()) {
         Map<String, String> __this__confOverlay = new HashMap(other.confOverlay);
         this.confOverlay = __this__confOverlay;
      }

      this.runAsync = other.runAsync;
      this.queryTimeout = other.queryTimeout;
   }

   public TExecuteStatementReq deepCopy() {
      return new TExecuteStatementReq(this);
   }

   public void clear() {
      this.sessionHandle = null;
      this.statement = null;
      this.confOverlay = null;
      this.runAsync = false;
      this.queryTimeout = 0L;
   }

   @Nullable
   public TSessionHandle getSessionHandle() {
      return this.sessionHandle;
   }

   public void setSessionHandle(@Nullable TSessionHandle sessionHandle) {
      this.sessionHandle = sessionHandle;
   }

   public void unsetSessionHandle() {
      this.sessionHandle = null;
   }

   public boolean isSetSessionHandle() {
      return this.sessionHandle != null;
   }

   public void setSessionHandleIsSet(boolean value) {
      if (!value) {
         this.sessionHandle = null;
      }

   }

   @Nullable
   public String getStatement() {
      return this.statement;
   }

   public void setStatement(@Nullable String statement) {
      this.statement = statement;
   }

   public void unsetStatement() {
      this.statement = null;
   }

   public boolean isSetStatement() {
      return this.statement != null;
   }

   public void setStatementIsSet(boolean value) {
      if (!value) {
         this.statement = null;
      }

   }

   public int getConfOverlaySize() {
      return this.confOverlay == null ? 0 : this.confOverlay.size();
   }

   public void putToConfOverlay(String key, String val) {
      if (this.confOverlay == null) {
         this.confOverlay = new HashMap();
      }

      this.confOverlay.put(key, val);
   }

   @Nullable
   public Map getConfOverlay() {
      return this.confOverlay;
   }

   public void setConfOverlay(@Nullable Map confOverlay) {
      this.confOverlay = confOverlay;
   }

   public void unsetConfOverlay() {
      this.confOverlay = null;
   }

   public boolean isSetConfOverlay() {
      return this.confOverlay != null;
   }

   public void setConfOverlayIsSet(boolean value) {
      if (!value) {
         this.confOverlay = null;
      }

   }

   public boolean isRunAsync() {
      return this.runAsync;
   }

   public void setRunAsync(boolean runAsync) {
      this.runAsync = runAsync;
      this.setRunAsyncIsSet(true);
   }

   public void unsetRunAsync() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetRunAsync() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setRunAsyncIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public long getQueryTimeout() {
      return this.queryTimeout;
   }

   public void setQueryTimeout(long queryTimeout) {
      this.queryTimeout = queryTimeout;
      this.setQueryTimeoutIsSet(true);
   }

   public void unsetQueryTimeout() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetQueryTimeout() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setQueryTimeoutIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SESSION_HANDLE:
            if (value == null) {
               this.unsetSessionHandle();
            } else {
               this.setSessionHandle((TSessionHandle)value);
            }
            break;
         case STATEMENT:
            if (value == null) {
               this.unsetStatement();
            } else {
               this.setStatement((String)value);
            }
            break;
         case CONF_OVERLAY:
            if (value == null) {
               this.unsetConfOverlay();
            } else {
               this.setConfOverlay((Map)value);
            }
            break;
         case RUN_ASYNC:
            if (value == null) {
               this.unsetRunAsync();
            } else {
               this.setRunAsync((Boolean)value);
            }
            break;
         case QUERY_TIMEOUT:
            if (value == null) {
               this.unsetQueryTimeout();
            } else {
               this.setQueryTimeout((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case STATEMENT:
            return this.getStatement();
         case CONF_OVERLAY:
            return this.getConfOverlay();
         case RUN_ASYNC:
            return this.isRunAsync();
         case QUERY_TIMEOUT:
            return this.getQueryTimeout();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SESSION_HANDLE:
               return this.isSetSessionHandle();
            case STATEMENT:
               return this.isSetStatement();
            case CONF_OVERLAY:
               return this.isSetConfOverlay();
            case RUN_ASYNC:
               return this.isSetRunAsync();
            case QUERY_TIMEOUT:
               return this.isSetQueryTimeout();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TExecuteStatementReq ? this.equals((TExecuteStatementReq)that) : false;
   }

   public boolean equals(TExecuteStatementReq that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_sessionHandle = this.isSetSessionHandle();
         boolean that_present_sessionHandle = that.isSetSessionHandle();
         if (this_present_sessionHandle || that_present_sessionHandle) {
            if (!this_present_sessionHandle || !that_present_sessionHandle) {
               return false;
            }

            if (!this.sessionHandle.equals(that.sessionHandle)) {
               return false;
            }
         }

         boolean this_present_statement = this.isSetStatement();
         boolean that_present_statement = that.isSetStatement();
         if (this_present_statement || that_present_statement) {
            if (!this_present_statement || !that_present_statement) {
               return false;
            }

            if (!this.statement.equals(that.statement)) {
               return false;
            }
         }

         boolean this_present_confOverlay = this.isSetConfOverlay();
         boolean that_present_confOverlay = that.isSetConfOverlay();
         if (this_present_confOverlay || that_present_confOverlay) {
            if (!this_present_confOverlay || !that_present_confOverlay) {
               return false;
            }

            if (!this.confOverlay.equals(that.confOverlay)) {
               return false;
            }
         }

         boolean this_present_runAsync = this.isSetRunAsync();
         boolean that_present_runAsync = that.isSetRunAsync();
         if (this_present_runAsync || that_present_runAsync) {
            if (!this_present_runAsync || !that_present_runAsync) {
               return false;
            }

            if (this.runAsync != that.runAsync) {
               return false;
            }
         }

         boolean this_present_queryTimeout = this.isSetQueryTimeout();
         boolean that_present_queryTimeout = that.isSetQueryTimeout();
         if (this_present_queryTimeout || that_present_queryTimeout) {
            if (!this_present_queryTimeout || !that_present_queryTimeout) {
               return false;
            }

            if (this.queryTimeout != that.queryTimeout) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetSessionHandle() ? 131071 : 524287);
      if (this.isSetSessionHandle()) {
         hashCode = hashCode * 8191 + this.sessionHandle.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetStatement() ? 131071 : 524287);
      if (this.isSetStatement()) {
         hashCode = hashCode * 8191 + this.statement.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetConfOverlay() ? 131071 : 524287);
      if (this.isSetConfOverlay()) {
         hashCode = hashCode * 8191 + this.confOverlay.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRunAsync() ? 131071 : 524287);
      if (this.isSetRunAsync()) {
         hashCode = hashCode * 8191 + (this.runAsync ? 131071 : 524287);
      }

      hashCode = hashCode * 8191 + (this.isSetQueryTimeout() ? 131071 : 524287);
      if (this.isSetQueryTimeout()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.queryTimeout);
      }

      return hashCode;
   }

   public int compareTo(TExecuteStatementReq other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSessionHandle(), other.isSetSessionHandle());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSessionHandle()) {
               lastComparison = TBaseHelper.compareTo(this.sessionHandle, other.sessionHandle);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetStatement(), other.isSetStatement());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetStatement()) {
                  lastComparison = TBaseHelper.compareTo(this.statement, other.statement);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetConfOverlay(), other.isSetConfOverlay());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetConfOverlay()) {
                     lastComparison = TBaseHelper.compareTo(this.confOverlay, other.confOverlay);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetRunAsync(), other.isSetRunAsync());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetRunAsync()) {
                        lastComparison = TBaseHelper.compareTo(this.runAsync, other.runAsync);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetQueryTimeout(), other.isSetQueryTimeout());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetQueryTimeout()) {
                           lastComparison = TBaseHelper.compareTo(this.queryTimeout, other.queryTimeout);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TExecuteStatementReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TExecuteStatementReq(");
      boolean first = true;
      sb.append("sessionHandle:");
      if (this.sessionHandle == null) {
         sb.append("null");
      } else {
         sb.append(this.sessionHandle);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("statement:");
      if (this.statement == null) {
         sb.append("null");
      } else {
         sb.append(this.statement);
      }

      first = false;
      if (this.isSetConfOverlay()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("confOverlay:");
         if (this.confOverlay == null) {
            sb.append("null");
         } else {
            sb.append(this.confOverlay);
         }

         first = false;
      }

      if (this.isSetRunAsync()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("runAsync:");
         sb.append(this.runAsync);
         first = false;
      }

      if (this.isSetQueryTimeout()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("queryTimeout:");
         sb.append(this.queryTimeout);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetSessionHandle()) {
         throw new TProtocolException("Required field 'sessionHandle' is unset! Struct:" + this.toString());
      } else if (!this.isSetStatement()) {
         throw new TProtocolException("Required field 'statement' is unset! Struct:" + this.toString());
      } else {
         if (this.sessionHandle != null) {
            this.sessionHandle.validate();
         }

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
      optionals = new _Fields[]{TExecuteStatementReq._Fields.CONF_OVERLAY, TExecuteStatementReq._Fields.RUN_ASYNC, TExecuteStatementReq._Fields.QUERY_TIMEOUT};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TExecuteStatementReq._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)1, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TExecuteStatementReq._Fields.STATEMENT, new FieldMetaData("statement", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(TExecuteStatementReq._Fields.CONF_OVERLAY, new FieldMetaData("confOverlay", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      tmpMap.put(TExecuteStatementReq._Fields.RUN_ASYNC, new FieldMetaData("runAsync", (byte)2, new FieldValueMetaData((byte)2)));
      tmpMap.put(TExecuteStatementReq._Fields.QUERY_TIMEOUT, new FieldMetaData("queryTimeout", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TExecuteStatementReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SESSION_HANDLE((short)1, "sessionHandle"),
      STATEMENT((short)2, "statement"),
      CONF_OVERLAY((short)3, "confOverlay"),
      RUN_ASYNC((short)4, "runAsync"),
      QUERY_TIMEOUT((short)5, "queryTimeout");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SESSION_HANDLE;
            case 2:
               return STATEMENT;
            case 3:
               return CONF_OVERLAY;
            case 4:
               return RUN_ASYNC;
            case 5:
               return QUERY_TIMEOUT;
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

   private static class TExecuteStatementReqStandardSchemeFactory implements SchemeFactory {
      private TExecuteStatementReqStandardSchemeFactory() {
      }

      public TExecuteStatementReqStandardScheme getScheme() {
         return new TExecuteStatementReqStandardScheme();
      }
   }

   private static class TExecuteStatementReqStandardScheme extends StandardScheme {
      private TExecuteStatementReqStandardScheme() {
      }

      public void read(TProtocol iprot, TExecuteStatementReq struct) throws TException {
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
                  if (schemeField.type == 12) {
                     struct.sessionHandle = new TSessionHandle();
                     struct.sessionHandle.read(iprot);
                     struct.setSessionHandleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.statement = iprot.readString();
                     struct.setStatementIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map172 = iprot.readMapBegin();
                  struct.confOverlay = new HashMap(2 * _map172.size);

                  for(int _i175 = 0; _i175 < _map172.size; ++_i175) {
                     String _key173 = iprot.readString();
                     String _val174 = iprot.readString();
                     struct.confOverlay.put(_key173, _val174);
                  }

                  iprot.readMapEnd();
                  struct.setConfOverlayIsSet(true);
                  break;
               case 4:
                  if (schemeField.type == 2) {
                     struct.runAsync = iprot.readBool();
                     struct.setRunAsyncIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 10) {
                     struct.queryTimeout = iprot.readI64();
                     struct.setQueryTimeoutIsSet(true);
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

      public void write(TProtocol oprot, TExecuteStatementReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TExecuteStatementReq.STRUCT_DESC);
         if (struct.sessionHandle != null) {
            oprot.writeFieldBegin(TExecuteStatementReq.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.statement != null) {
            oprot.writeFieldBegin(TExecuteStatementReq.STATEMENT_FIELD_DESC);
            oprot.writeString(struct.statement);
            oprot.writeFieldEnd();
         }

         if (struct.confOverlay != null && struct.isSetConfOverlay()) {
            oprot.writeFieldBegin(TExecuteStatementReq.CONF_OVERLAY_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.confOverlay.size()));

            for(Map.Entry _iter176 : struct.confOverlay.entrySet()) {
               oprot.writeString((String)_iter176.getKey());
               oprot.writeString((String)_iter176.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetRunAsync()) {
            oprot.writeFieldBegin(TExecuteStatementReq.RUN_ASYNC_FIELD_DESC);
            oprot.writeBool(struct.runAsync);
            oprot.writeFieldEnd();
         }

         if (struct.isSetQueryTimeout()) {
            oprot.writeFieldBegin(TExecuteStatementReq.QUERY_TIMEOUT_FIELD_DESC);
            oprot.writeI64(struct.queryTimeout);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TExecuteStatementReqTupleSchemeFactory implements SchemeFactory {
      private TExecuteStatementReqTupleSchemeFactory() {
      }

      public TExecuteStatementReqTupleScheme getScheme() {
         return new TExecuteStatementReqTupleScheme();
      }
   }

   private static class TExecuteStatementReqTupleScheme extends TupleScheme {
      private TExecuteStatementReqTupleScheme() {
      }

      public void write(TProtocol prot, TExecuteStatementReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.sessionHandle.write(oprot);
         oprot.writeString(struct.statement);
         BitSet optionals = new BitSet();
         if (struct.isSetConfOverlay()) {
            optionals.set(0);
         }

         if (struct.isSetRunAsync()) {
            optionals.set(1);
         }

         if (struct.isSetQueryTimeout()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetConfOverlay()) {
            oprot.writeI32(struct.confOverlay.size());

            for(Map.Entry _iter177 : struct.confOverlay.entrySet()) {
               oprot.writeString((String)_iter177.getKey());
               oprot.writeString((String)_iter177.getValue());
            }
         }

         if (struct.isSetRunAsync()) {
            oprot.writeBool(struct.runAsync);
         }

         if (struct.isSetQueryTimeout()) {
            oprot.writeI64(struct.queryTimeout);
         }

      }

      public void read(TProtocol prot, TExecuteStatementReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.sessionHandle = new TSessionHandle();
         struct.sessionHandle.read(iprot);
         struct.setSessionHandleIsSet(true);
         struct.statement = iprot.readString();
         struct.setStatementIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            TMap _map178 = iprot.readMapBegin((byte)11, (byte)11);
            struct.confOverlay = new HashMap(2 * _map178.size);

            for(int _i181 = 0; _i181 < _map178.size; ++_i181) {
               String _key179 = iprot.readString();
               String _val180 = iprot.readString();
               struct.confOverlay.put(_key179, _val180);
            }

            struct.setConfOverlayIsSet(true);
         }

         if (incoming.get(1)) {
            struct.runAsync = iprot.readBool();
            struct.setRunAsyncIsSet(true);
         }

         if (incoming.get(2)) {
            struct.queryTimeout = iprot.readI64();
            struct.setQueryTimeoutIsSet(true);
         }

      }
   }
}
