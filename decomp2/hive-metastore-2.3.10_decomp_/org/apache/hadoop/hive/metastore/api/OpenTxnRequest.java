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

public class OpenTxnRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("OpenTxnRequest");
   private static final TField NUM_TXNS_FIELD_DESC = new TField("num_txns", (byte)8, (short)1);
   private static final TField USER_FIELD_DESC = new TField("user", (byte)11, (short)2);
   private static final TField HOSTNAME_FIELD_DESC = new TField("hostname", (byte)11, (short)3);
   private static final TField AGENT_INFO_FIELD_DESC = new TField("agentInfo", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new OpenTxnRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new OpenTxnRequestTupleSchemeFactory();
   private int num_txns;
   @Nullable
   private String user;
   @Nullable
   private String hostname;
   @Nullable
   private String agentInfo;
   private static final int __NUM_TXNS_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public OpenTxnRequest() {
      this.__isset_bitfield = 0;
      this.agentInfo = "Unknown";
   }

   public OpenTxnRequest(int num_txns, String user, String hostname) {
      this();
      this.num_txns = num_txns;
      this.setNum_txnsIsSet(true);
      this.user = user;
      this.hostname = hostname;
   }

   public OpenTxnRequest(OpenTxnRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.num_txns = other.num_txns;
      if (other.isSetUser()) {
         this.user = other.user;
      }

      if (other.isSetHostname()) {
         this.hostname = other.hostname;
      }

      if (other.isSetAgentInfo()) {
         this.agentInfo = other.agentInfo;
      }

   }

   public OpenTxnRequest deepCopy() {
      return new OpenTxnRequest(this);
   }

   public void clear() {
      this.setNum_txnsIsSet(false);
      this.num_txns = 0;
      this.user = null;
      this.hostname = null;
      this.agentInfo = "Unknown";
   }

   public int getNum_txns() {
      return this.num_txns;
   }

   public void setNum_txns(int num_txns) {
      this.num_txns = num_txns;
      this.setNum_txnsIsSet(true);
   }

   public void unsetNum_txns() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetNum_txns() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setNum_txnsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
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

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NUM_TXNS:
            if (value == null) {
               this.unsetNum_txns();
            } else {
               this.setNum_txns((Integer)value);
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
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NUM_TXNS:
            return this.getNum_txns();
         case USER:
            return this.getUser();
         case HOSTNAME:
            return this.getHostname();
         case AGENT_INFO:
            return this.getAgentInfo();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NUM_TXNS:
               return this.isSetNum_txns();
            case USER:
               return this.isSetUser();
            case HOSTNAME:
               return this.isSetHostname();
            case AGENT_INFO:
               return this.isSetAgentInfo();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof OpenTxnRequest ? this.equals((OpenTxnRequest)that) : false;
   }

   public boolean equals(OpenTxnRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_num_txns = true;
         boolean that_present_num_txns = true;
         if (this_present_num_txns || that_present_num_txns) {
            if (!this_present_num_txns || !that_present_num_txns) {
               return false;
            }

            if (this.num_txns != that.num_txns) {
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

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.num_txns;
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

      return hashCode;
   }

   public int compareTo(OpenTxnRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetNum_txns(), other.isSetNum_txns());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetNum_txns()) {
               lastComparison = TBaseHelper.compareTo(this.num_txns, other.num_txns);
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

                     return 0;
                  }
               }
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return OpenTxnRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("OpenTxnRequest(");
      boolean first = true;
      sb.append("num_txns:");
      sb.append(this.num_txns);
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

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetNum_txns()) {
         throw new TProtocolException("Required field 'num_txns' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{OpenTxnRequest._Fields.AGENT_INFO};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(OpenTxnRequest._Fields.NUM_TXNS, new FieldMetaData("num_txns", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(OpenTxnRequest._Fields.USER, new FieldMetaData("user", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(OpenTxnRequest._Fields.HOSTNAME, new FieldMetaData("hostname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(OpenTxnRequest._Fields.AGENT_INFO, new FieldMetaData("agentInfo", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(OpenTxnRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NUM_TXNS((short)1, "num_txns"),
      USER((short)2, "user"),
      HOSTNAME((short)3, "hostname"),
      AGENT_INFO((short)4, "agentInfo");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NUM_TXNS;
            case 2:
               return USER;
            case 3:
               return HOSTNAME;
            case 4:
               return AGENT_INFO;
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

   private static class OpenTxnRequestStandardSchemeFactory implements SchemeFactory {
      private OpenTxnRequestStandardSchemeFactory() {
      }

      public OpenTxnRequestStandardScheme getScheme() {
         return new OpenTxnRequestStandardScheme();
      }
   }

   private static class OpenTxnRequestStandardScheme extends StandardScheme {
      private OpenTxnRequestStandardScheme() {
      }

      public void read(TProtocol iprot, OpenTxnRequest struct) throws TException {
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
                     struct.num_txns = iprot.readI32();
                     struct.setNum_txnsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.user = iprot.readString();
                     struct.setUserIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.hostname = iprot.readString();
                     struct.setHostnameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 11) {
                     struct.agentInfo = iprot.readString();
                     struct.setAgentInfoIsSet(true);
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

      public void write(TProtocol oprot, OpenTxnRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(OpenTxnRequest.STRUCT_DESC);
         oprot.writeFieldBegin(OpenTxnRequest.NUM_TXNS_FIELD_DESC);
         oprot.writeI32(struct.num_txns);
         oprot.writeFieldEnd();
         if (struct.user != null) {
            oprot.writeFieldBegin(OpenTxnRequest.USER_FIELD_DESC);
            oprot.writeString(struct.user);
            oprot.writeFieldEnd();
         }

         if (struct.hostname != null) {
            oprot.writeFieldBegin(OpenTxnRequest.HOSTNAME_FIELD_DESC);
            oprot.writeString(struct.hostname);
            oprot.writeFieldEnd();
         }

         if (struct.agentInfo != null && struct.isSetAgentInfo()) {
            oprot.writeFieldBegin(OpenTxnRequest.AGENT_INFO_FIELD_DESC);
            oprot.writeString(struct.agentInfo);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class OpenTxnRequestTupleSchemeFactory implements SchemeFactory {
      private OpenTxnRequestTupleSchemeFactory() {
      }

      public OpenTxnRequestTupleScheme getScheme() {
         return new OpenTxnRequestTupleScheme();
      }
   }

   private static class OpenTxnRequestTupleScheme extends TupleScheme {
      private OpenTxnRequestTupleScheme() {
      }

      public void write(TProtocol prot, OpenTxnRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.num_txns);
         oprot.writeString(struct.user);
         oprot.writeString(struct.hostname);
         BitSet optionals = new BitSet();
         if (struct.isSetAgentInfo()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetAgentInfo()) {
            oprot.writeString(struct.agentInfo);
         }

      }

      public void read(TProtocol prot, OpenTxnRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.num_txns = iprot.readI32();
         struct.setNum_txnsIsSet(true);
         struct.user = iprot.readString();
         struct.setUserIsSet(true);
         struct.hostname = iprot.readString();
         struct.setHostnameIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.agentInfo = iprot.readString();
            struct.setAgentInfoIsSet(true);
         }

      }
   }
}
