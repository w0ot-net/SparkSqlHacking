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
public class TOpenSessionReq implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TOpenSessionReq");
   private static final TField CLIENT_PROTOCOL_FIELD_DESC = new TField("client_protocol", (byte)8, (short)1);
   private static final TField USERNAME_FIELD_DESC = new TField("username", (byte)11, (short)2);
   private static final TField PASSWORD_FIELD_DESC = new TField("password", (byte)11, (short)3);
   private static final TField CONFIGURATION_FIELD_DESC = new TField("configuration", (byte)13, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TOpenSessionReqStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TOpenSessionReqTupleSchemeFactory();
   @Nullable
   private TProtocolVersion client_protocol;
   @Nullable
   private String username;
   @Nullable
   private String password;
   @Nullable
   private Map configuration;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TOpenSessionReq() {
      this.client_protocol = TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V10;
   }

   public TOpenSessionReq(TProtocolVersion client_protocol) {
      this();
      this.client_protocol = client_protocol;
   }

   public TOpenSessionReq(TOpenSessionReq other) {
      if (other.isSetClient_protocol()) {
         this.client_protocol = other.client_protocol;
      }

      if (other.isSetUsername()) {
         this.username = other.username;
      }

      if (other.isSetPassword()) {
         this.password = other.password;
      }

      if (other.isSetConfiguration()) {
         Map<String, String> __this__configuration = new HashMap(other.configuration);
         this.configuration = __this__configuration;
      }

   }

   public TOpenSessionReq deepCopy() {
      return new TOpenSessionReq(this);
   }

   public void clear() {
      this.client_protocol = TProtocolVersion.HIVE_CLI_SERVICE_PROTOCOL_V10;
      this.username = null;
      this.password = null;
      this.configuration = null;
   }

   @Nullable
   public TProtocolVersion getClient_protocol() {
      return this.client_protocol;
   }

   public void setClient_protocol(@Nullable TProtocolVersion client_protocol) {
      this.client_protocol = client_protocol;
   }

   public void unsetClient_protocol() {
      this.client_protocol = null;
   }

   public boolean isSetClient_protocol() {
      return this.client_protocol != null;
   }

   public void setClient_protocolIsSet(boolean value) {
      if (!value) {
         this.client_protocol = null;
      }

   }

   @Nullable
   public String getUsername() {
      return this.username;
   }

   public void setUsername(@Nullable String username) {
      this.username = username;
   }

   public void unsetUsername() {
      this.username = null;
   }

   public boolean isSetUsername() {
      return this.username != null;
   }

   public void setUsernameIsSet(boolean value) {
      if (!value) {
         this.username = null;
      }

   }

   @Nullable
   public String getPassword() {
      return this.password;
   }

   public void setPassword(@Nullable String password) {
      this.password = password;
   }

   public void unsetPassword() {
      this.password = null;
   }

   public boolean isSetPassword() {
      return this.password != null;
   }

   public void setPasswordIsSet(boolean value) {
      if (!value) {
         this.password = null;
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
         case CLIENT_PROTOCOL:
            if (value == null) {
               this.unsetClient_protocol();
            } else {
               this.setClient_protocol((TProtocolVersion)value);
            }
            break;
         case USERNAME:
            if (value == null) {
               this.unsetUsername();
            } else {
               this.setUsername((String)value);
            }
            break;
         case PASSWORD:
            if (value == null) {
               this.unsetPassword();
            } else {
               this.setPassword((String)value);
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
         case CLIENT_PROTOCOL:
            return this.getClient_protocol();
         case USERNAME:
            return this.getUsername();
         case PASSWORD:
            return this.getPassword();
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
            case CLIENT_PROTOCOL:
               return this.isSetClient_protocol();
            case USERNAME:
               return this.isSetUsername();
            case PASSWORD:
               return this.isSetPassword();
            case CONFIGURATION:
               return this.isSetConfiguration();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TOpenSessionReq ? this.equals((TOpenSessionReq)that) : false;
   }

   public boolean equals(TOpenSessionReq that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_client_protocol = this.isSetClient_protocol();
         boolean that_present_client_protocol = that.isSetClient_protocol();
         if (this_present_client_protocol || that_present_client_protocol) {
            if (!this_present_client_protocol || !that_present_client_protocol) {
               return false;
            }

            if (!this.client_protocol.equals(that.client_protocol)) {
               return false;
            }
         }

         boolean this_present_username = this.isSetUsername();
         boolean that_present_username = that.isSetUsername();
         if (this_present_username || that_present_username) {
            if (!this_present_username || !that_present_username) {
               return false;
            }

            if (!this.username.equals(that.username)) {
               return false;
            }
         }

         boolean this_present_password = this.isSetPassword();
         boolean that_present_password = that.isSetPassword();
         if (this_present_password || that_present_password) {
            if (!this_present_password || !that_present_password) {
               return false;
            }

            if (!this.password.equals(that.password)) {
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
      hashCode = hashCode * 8191 + (this.isSetClient_protocol() ? 131071 : 524287);
      if (this.isSetClient_protocol()) {
         hashCode = hashCode * 8191 + this.client_protocol.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetUsername() ? 131071 : 524287);
      if (this.isSetUsername()) {
         hashCode = hashCode * 8191 + this.username.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPassword() ? 131071 : 524287);
      if (this.isSetPassword()) {
         hashCode = hashCode * 8191 + this.password.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetConfiguration() ? 131071 : 524287);
      if (this.isSetConfiguration()) {
         hashCode = hashCode * 8191 + this.configuration.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TOpenSessionReq other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetClient_protocol(), other.isSetClient_protocol());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetClient_protocol()) {
               lastComparison = TBaseHelper.compareTo(this.client_protocol, other.client_protocol);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetUsername(), other.isSetUsername());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetUsername()) {
                  lastComparison = TBaseHelper.compareTo(this.username, other.username);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPassword(), other.isSetPassword());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPassword()) {
                     lastComparison = TBaseHelper.compareTo(this.password, other.password);
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
      return TOpenSessionReq._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TOpenSessionReq(");
      boolean first = true;
      sb.append("client_protocol:");
      if (this.client_protocol == null) {
         sb.append("null");
      } else {
         sb.append(this.client_protocol);
      }

      first = false;
      if (this.isSetUsername()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("username:");
         if (this.username == null) {
            sb.append("null");
         } else {
            sb.append(this.username);
         }

         first = false;
      }

      if (this.isSetPassword()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("password:");
         sb.append("-");
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
      if (!this.isSetClient_protocol()) {
         throw new TProtocolException("Required field 'client_protocol' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{TOpenSessionReq._Fields.USERNAME, TOpenSessionReq._Fields.PASSWORD, TOpenSessionReq._Fields.CONFIGURATION};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TOpenSessionReq._Fields.CLIENT_PROTOCOL, new FieldMetaData("client_protocol", (byte)1, new EnumMetaData((byte)16, TProtocolVersion.class)));
      tmpMap.put(TOpenSessionReq._Fields.USERNAME, new FieldMetaData("username", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TOpenSessionReq._Fields.PASSWORD, new FieldMetaData("password", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(TOpenSessionReq._Fields.CONFIGURATION, new FieldMetaData("configuration", (byte)2, new MapMetaData((byte)13, new FieldValueMetaData((byte)11), new FieldValueMetaData((byte)11))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TOpenSessionReq.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      CLIENT_PROTOCOL((short)1, "client_protocol"),
      USERNAME((short)2, "username"),
      PASSWORD((short)3, "password"),
      CONFIGURATION((short)4, "configuration");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return CLIENT_PROTOCOL;
            case 2:
               return USERNAME;
            case 3:
               return PASSWORD;
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

   private static class TOpenSessionReqStandardSchemeFactory implements SchemeFactory {
      private TOpenSessionReqStandardSchemeFactory() {
      }

      public TOpenSessionReqStandardScheme getScheme() {
         return new TOpenSessionReqStandardScheme();
      }
   }

   private static class TOpenSessionReqStandardScheme extends StandardScheme {
      private TOpenSessionReqStandardScheme() {
      }

      public void read(TProtocol iprot, TOpenSessionReq struct) throws TException {
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
                     struct.client_protocol = TProtocolVersion.findByValue(iprot.readI32());
                     struct.setClient_protocolIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.username = iprot.readString();
                     struct.setUsernameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.password = iprot.readString();
                     struct.setPasswordIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type != 13) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TMap _map142 = iprot.readMapBegin();
                  struct.configuration = new HashMap(2 * _map142.size);

                  for(int _i145 = 0; _i145 < _map142.size; ++_i145) {
                     String _key143 = iprot.readString();
                     String _val144 = iprot.readString();
                     struct.configuration.put(_key143, _val144);
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

      public void write(TProtocol oprot, TOpenSessionReq struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TOpenSessionReq.STRUCT_DESC);
         if (struct.client_protocol != null) {
            oprot.writeFieldBegin(TOpenSessionReq.CLIENT_PROTOCOL_FIELD_DESC);
            oprot.writeI32(struct.client_protocol.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.username != null && struct.isSetUsername()) {
            oprot.writeFieldBegin(TOpenSessionReq.USERNAME_FIELD_DESC);
            oprot.writeString(struct.username);
            oprot.writeFieldEnd();
         }

         if (struct.password != null && struct.isSetPassword()) {
            oprot.writeFieldBegin(TOpenSessionReq.PASSWORD_FIELD_DESC);
            oprot.writeString(struct.password);
            oprot.writeFieldEnd();
         }

         if (struct.configuration != null && struct.isSetConfiguration()) {
            oprot.writeFieldBegin(TOpenSessionReq.CONFIGURATION_FIELD_DESC);
            oprot.writeMapBegin(new TMap((byte)11, (byte)11, struct.configuration.size()));

            for(Map.Entry _iter146 : struct.configuration.entrySet()) {
               oprot.writeString((String)_iter146.getKey());
               oprot.writeString((String)_iter146.getValue());
            }

            oprot.writeMapEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TOpenSessionReqTupleSchemeFactory implements SchemeFactory {
      private TOpenSessionReqTupleSchemeFactory() {
      }

      public TOpenSessionReqTupleScheme getScheme() {
         return new TOpenSessionReqTupleScheme();
      }
   }

   private static class TOpenSessionReqTupleScheme extends TupleScheme {
      private TOpenSessionReqTupleScheme() {
      }

      public void write(TProtocol prot, TOpenSessionReq struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.client_protocol.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetUsername()) {
            optionals.set(0);
         }

         if (struct.isSetPassword()) {
            optionals.set(1);
         }

         if (struct.isSetConfiguration()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetUsername()) {
            oprot.writeString(struct.username);
         }

         if (struct.isSetPassword()) {
            oprot.writeString(struct.password);
         }

         if (struct.isSetConfiguration()) {
            oprot.writeI32(struct.configuration.size());

            for(Map.Entry _iter147 : struct.configuration.entrySet()) {
               oprot.writeString((String)_iter147.getKey());
               oprot.writeString((String)_iter147.getValue());
            }
         }

      }

      public void read(TProtocol prot, TOpenSessionReq struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.client_protocol = TProtocolVersion.findByValue(iprot.readI32());
         struct.setClient_protocolIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.username = iprot.readString();
            struct.setUsernameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.password = iprot.readString();
            struct.setPasswordIsSet(true);
         }

         if (incoming.get(2)) {
            TMap _map148 = iprot.readMapBegin((byte)11, (byte)11);
            struct.configuration = new HashMap(2 * _map148.size);

            for(int _i151 = 0; _i151 < _map148.size; ++_i151) {
               String _key149 = iprot.readString();
               String _val150 = iprot.readString();
               struct.configuration.put(_key149, _val150);
            }

            struct.setConfigurationIsSet(true);
         }

      }
   }
}
