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
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.EnumMetaData;
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
public class TOpenSessionResp implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TOpenSessionResp");
   private static final TField STATUS_FIELD_DESC = new TField("status", (byte)12, (short)1);
   private static final TField SERVER_PROTOCOL_VERSION_FIELD_DESC = new TField("serverProtocolVersion", (byte)8, (short)2);
   private static final TField SESSION_HANDLE_FIELD_DESC = new TField("sessionHandle", (byte)12, (short)3);
   private static final TField CONFIGURATION_FIELD_DESC = new TField("configuration", (byte)13, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TOpenSessionRespStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TOpenSessionRespTupleSchemeFactory();
   @Nullable
   private TStatus status;
   @Nullable
   private TProtocolVersion serverProtocolVersion;
   @Nullable
   private TSessionHandle sessionHandle;
   @Nullable
   private Map configuration;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TOpenSessionResp() {
      this.serverProtocolVersion = TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V10;
   }

   public TOpenSessionResp(TStatus status, TProtocolVersion serverProtocolVersion) {
      this();
      this.status = status;
      this.serverProtocolVersion = serverProtocolVersion;
   }

   public TOpenSessionResp(TOpenSessionResp other) {
      if (other.isSetStatus()) {
         this.status = new TStatus(other.status);
      }

      if (other.isSetServerProtocolVersion()) {
         this.serverProtocolVersion = other.serverProtocolVersion;
      }

      if (other.isSetSessionHandle()) {
         this.sessionHandle = new TSessionHandle(other.sessionHandle);
      }

      if (other.isSetConfiguration()) {
         Map<String, String> __this__configuration = new HashMap(other.configuration);
         this.configuration = __this__configuration;
      }

   }

   public TOpenSessionResp deepCopy() {
      return new TOpenSessionResp(this);
   }

   public void clear() {
      this.status = null;
      this.serverProtocolVersion = TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V10;
      this.sessionHandle = null;
      this.configuration = null;
   }

   @Nullable
   public TStatus getStatus() {
      return this.status;
   }

   public void setStatus(@Nullable TStatus status) {
      this.status = status;
   }

   public void unsetStatus() {
      this.status = null;
   }

   public boolean isSetStatus() {
      return this.status != null;
   }

   public void setStatusIsSet(boolean value) {
      if (!value) {
         this.status = null;
      }

   }

   @Nullable
   public TProtocolVersion getServerProtocolVersion() {
      return this.serverProtocolVersion;
   }

   public void setServerProtocolVersion(@Nullable TProtocolVersion serverProtocolVersion) {
      this.serverProtocolVersion = serverProtocolVersion;
   }

   public void unsetServerProtocolVersion() {
      this.serverProtocolVersion = null;
   }

   public boolean isSetServerProtocolVersion() {
      return this.serverProtocolVersion != null;
   }

   public void setServerProtocolVersionIsSet(boolean value) {
      if (!value) {
         this.serverProtocolVersion = null;
      }

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

   public int getConfigurationSize() {
      return this.configuration == null ? 0 : this.configuration.size();
   }

   public void putToConfiguration(String key, String val) {
      if (this.configuration == null) {
         this.configuration = new HashMap();
      }

      this.configuration.put(key, val);
   }

   @Nullable
   public Map getConfiguration() {
      return this.configuration;
   }

   public void setConfiguration(@Nullable Map configuration) {
      this.configuration = configuration;
   }

   public void unsetConfiguration() {
      this.configuration = null;
   }

   public boolean isSetConfiguration() {
      return this.configuration != null;
   }

   public void setConfigurationIsSet(boolean value) {
      if (!value) {
         this.configuration = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case STATUS:
            if (value == null) {
               this.unsetStatus();
            } else {
               this.setStatus((TStatus)value);
            }
            break;
         case SERVER_PROTOCOL_VERSION:
            if (value == null) {
               this.unsetServerProtocolVersion();
            } else {
               this.setServerProtocolVersion((TProtocolVersion)value);
            }
            break;
         case SESSION_HANDLE:
            if (value == null) {
               this.unsetSessionHandle();
            } else {
               this.setSessionHandle((TSessionHandle)value);
            }
            break;
         case CONFIGURATION:
            if (value == null) {
               this.unsetConfiguration();
            } else {
               this.setConfiguration((Map)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case STATUS:
            return this.getStatus();
         case SERVER_PROTOCOL_VERSION:
            return this.getServerProtocolVersion();
         case SESSION_HANDLE:
            return this.getSessionHandle();
         case CONFIGURATION:
            return this.getConfiguration();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case STATUS:
               return this.isSetStatus();
            case SERVER_PROTOCOL_VERSION:
               return this.isSetServerProtocolVersion();
            case SESSION_HANDLE:
               return this.isSetSessionHandle();
            case CONFIGURATION:
               return this.isSetConfiguration();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TOpenSessionResp ? this.equals((TOpenSessionResp)that) : false;
   }

   public boolean equals(TOpenSessionResp that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_status = this.isSetStatus();
         boolean that_present_status = that.isSetStatus();
         if (this_present_status || that_present_status) {
            if (!this_present_status || !that_present_status) {
               return false;
            }

            if (!this.status.equals(that.status)) {
               return false;
            }
         }

         boolean this_present_serverProtocolVersion = this.isSetServerProtocolVersion();
         boolean that_present_serverProtocolVersion = that.isSetServerProtocolVersion();
         if (this_present_serverProtocolVersion || that_present_serverProtocolVersion) {
            if (!this_present_serverProtocolVersion || !that_present_serverProtocolVersion) {
               return false;
            }

            if (!this.serverProtocolVersion.equals(that.serverProtocolVersion)) {
               return false;
            }
         }

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

         boolean this_present_configuration = this.isSetConfiguration();
         boolean that_present_configuration = that.isSetConfiguration();
         if (this_present_configuration || that_present_configuration) {
            if (!this_present_configuration || !that_present_configuration) {
               return false;
            }

            if (!this.configuration.equals(that.configuration)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetStatus() ? 131071 : 524287);
      if (this.isSetStatus()) {
         hashCode = hashCode * 8191 + this.status.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetServerProtocolVersion() ? 131071 : 524287);
      if (this.isSetServerProtocolVersion()) {
         hashCode = hashCode * 8191 + this.serverProtocolVersion.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetSessionHandle() ? 131071 : 524287);
      if (this.isSetSessionHandle()) {
         hashCode = hashCode * 8191 + this.sessionHandle.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetConfiguration() ? 131071 : 524287);
      if (this.isSetConfiguration()) {
         hashCode = hashCode * 8191 + this.configuration.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TOpenSessionResp other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetStatus(), other.isSetStatus());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetStatus()) {
               lastComparison = TBaseHelper.compareTo(this.status, other.status);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetServerProtocolVersion(), other.isSetServerProtocolVersion());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetServerProtocolVersion()) {
                  lastComparison = TBaseHelper.compareTo(this.serverProtocolVersion, other.serverProtocolVersion);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

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

                  lastComparison = Boolean.compare(this.isSetConfiguration(), other.isSetConfiguration());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetConfiguration()) {
                        lastComparison = TBaseHelper.compareTo(this.configuration, other.configuration);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TOpenSessionResp._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TOpenSessionResp(");
      boolean first = true;
      sb.append("status:");
      if (this.status == null) {
         sb.append("null");
      } else {
         sb.append(this.status);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("serverProtocolVersion:");
      if (this.serverProtocolVersion == null) {
         sb.append("null");
      } else {
         sb.append(this.serverProtocolVersion);
      }

      first = false;
      if (this.isSetSessionHandle()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("sessionHandle:");
         if (this.sessionHandle == null) {
            sb.append("null");
         } else {
            sb.append(this.sessionHandle);
         }

         first = false;
      }

      if (this.isSetConfiguration()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("configuration:");
         if (this.configuration == null) {
            sb.append("null");
         } else {
            sb.append(this.configuration);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetStatus()) {
         throw new TProtocolException("Required field 'status' is unset! Struct:" + this.toString());
      } else if (!this.isSetServerProtocolVersion()) {
         throw new TProtocolException("Required field 'serverProtocolVersion' is unset! Struct:" + this.toString());
      } else {
         if (this.status != null) {
            this.status.validate();
         }

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
         this.read(new TCompactProtocol(new TIOStreamTransport(in)));
      } catch (TException te) {
         throw new IOException(te);
      }
   }

   private static IScheme scheme(TProtocol proto) {
      return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
   }

   static {
      optionals = new _Fields[]{TOpenSessionResp._Fields.SESSION_HANDLE, TOpenSessionResp._Fields.CONFIGURATION};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TOpenSessionResp._Fields.STATUS, new FieldMetaData("status", (byte)1, new StructMetaData((byte)12, TStatus.class)));
      tmpMap.put(TOpenSessionResp._Fields.SERVER_PROTOCOL_VERSION, new FieldMetaData("serverProtocolVersion", (byte)1, new EnumMetaData((byte)16, TProtocolVersion.class)));
      tmpMap.put(TOpenSessionResp._Fields.SESSION_HANDLE, new FieldMetaData("sessionHandle", (byte)2, new StructMetaData((byte)12, TSessionHandle.class)));
      tmpMap.put(TOpenSessionResp._Fields.CONFIGURATION, new FieldMetaData("configuration", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TOpenSessionResp.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STATUS((short)1, "status"),
      SERVER_PROTOCOL_VERSION((short)2, "serverProtocolVersion"),
      SESSION_HANDLE((short)3, "sessionHandle"),
      CONFIGURATION((short)4, "configuration");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STATUS;
            case 2:
               return SERVER_PROTOCOL_VERSION;
            case 3:
               return SESSION_HANDLE;
            case 4:
               return CONFIGURATION;
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

   private static class TOpenSessionRespStandardSchemeFactory implements SchemeFactory {
      private TOpenSessionRespStandardSchemeFactory() {
      }

      public TOpenSessionRespStandardScheme getScheme() {
         return new TOpenSessionRespStandardScheme();
      }
   }

   private static class TOpenSessionRespStandardScheme extends StandardScheme {
      private TOpenSessionRespStandardScheme() {
      }

      public void read(TProtocol iprot, TOpenSessionResp struct) throws TException {
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
                     struct.status = new TStatus();
                     struct.status.read(iprot);
                     struct.setStatusIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.serverProtocolVersion = TProtocolVersion.findByValue(iprot.readI32());
                     struct.setServerProtocolVersionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 12) {
                     struct.sessionHandle = new TSessionHandle();
                     struct.sessionHandle.read(iprot);
                     struct.setSessionHandleIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map152 = iprot.readMapBegin();
                  struct.configuration = new HashMap(2 * _map152.size);

                  for(int _i155 = 0; _i155 < _map152.size; ++_i155) {
                     String _key153 = iprot.readString();
                     String _val154 = iprot.readString();
                     struct.configuration.put(_key153, _val154);
                  }

                  iprot.readMapEnd();
                  struct.setConfigurationIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TOpenSessionResp struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TOpenSessionResp.STRUCT_DESC);
         if (struct.status != null) {
            oprot.writeFieldBegin(TOpenSessionResp.STATUS_FIELD_DESC);
            struct.status.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.serverProtocolVersion != null) {
            oprot.writeFieldBegin(TOpenSessionResp.SERVER_PROTOCOL_VERSION_FIELD_DESC);
            oprot.writeI32(struct.serverProtocolVersion.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.sessionHandle != null && struct.isSetSessionHandle()) {
            oprot.writeFieldBegin(TOpenSessionResp.SESSION_HANDLE_FIELD_DESC);
            struct.sessionHandle.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.configuration != null && struct.isSetConfiguration()) {
            oprot.writeFieldBegin(TOpenSessionResp.CONFIGURATION_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.configuration.size()));

            for(Map.Entry _iter156 : struct.configuration.entrySet()) {
               oprot.writeString((String)_iter156.getKey());
               oprot.writeString((String)_iter156.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TOpenSessionRespTupleSchemeFactory implements SchemeFactory {
      private TOpenSessionRespTupleSchemeFactory() {
      }

      public TOpenSessionRespTupleScheme getScheme() {
         return new TOpenSessionRespTupleScheme();
      }
   }

   private static class TOpenSessionRespTupleScheme extends TupleScheme {
      private TOpenSessionRespTupleScheme() {
      }

      public void write(TProtocol prot, TOpenSessionResp struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.status.write(oprot);
         oprot.writeI32(struct.serverProtocolVersion.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetSessionHandle()) {
            optionals.set(0);
         }

         if (struct.isSetConfiguration()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetSessionHandle()) {
            struct.sessionHandle.write(oprot);
         }

         if (struct.isSetConfiguration()) {
            oprot.writeI32(struct.configuration.size());

            for(Map.Entry _iter157 : struct.configuration.entrySet()) {
               oprot.writeString((String)_iter157.getKey());
               oprot.writeString((String)_iter157.getValue());
            }
         }

      }

      public void read(TProtocol prot, TOpenSessionResp struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.status = new TStatus();
         struct.status.read(iprot);
         struct.setStatusIsSet(true);
         struct.serverProtocolVersion = TProtocolVersion.findByValue(iprot.readI32());
         struct.setServerProtocolVersionIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.sessionHandle = new TSessionHandle();
            struct.sessionHandle.read(iprot);
            struct.setSessionHandleIsSet(true);
         }

         if (incoming.get(1)) {
            TMap _map158 = iprot.readMapBegin((byte)11, (byte)11);
            struct.configuration = new HashMap(2 * _map158.size);

            for(int _i161 = 0; _i161 < _map158.size; ++_i161) {
               String _key159 = iprot.readString();
               String _val160 = iprot.readString();
               struct.configuration.put(_key159, _val160);
            }

            struct.setConfigurationIsSet(true);
         }

      }
   }
}
