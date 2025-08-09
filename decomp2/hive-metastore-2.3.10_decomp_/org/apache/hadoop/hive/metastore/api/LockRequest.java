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
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
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

public class LockRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("LockRequest");
   private static final TField COMPONENT_FIELD_DESC = new TField("component", (byte)15, (short)1);
   private static final TField TXNID_FIELD_DESC = new TField("txnid", (byte)10, (short)2);
   private static final TField USER_FIELD_DESC = new TField("user", (byte)11, (short)3);
   private static final TField HOSTNAME_FIELD_DESC = new TField("hostname", (byte)11, (short)4);
   private static final TField AGENT_INFO_FIELD_DESC = new TField("agentInfo", (byte)11, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new LockRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new LockRequestTupleSchemeFactory();
   @Nullable
   private List component;
   private long txnid;
   @Nullable
   private String user;
   @Nullable
   private String hostname;
   @Nullable
   private String agentInfo;
   private static final int __TXNID_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public LockRequest() {
      this.__isset_bitfield = 0;
      this.agentInfo = "Unknown";
   }

   public LockRequest(List component, String user, String hostname) {
      this();
      this.component = component;
      this.user = user;
      this.hostname = hostname;
   }

   public LockRequest(LockRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetComponent()) {
         List<LockComponent> __this__component = new ArrayList(other.component.size());

         for(LockComponent other_element : other.component) {
            __this__component.add(new LockComponent(other_element));
         }

         this.component = __this__component;
      }

      this.txnid = other.txnid;
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

   public LockRequest deepCopy() {
      return new LockRequest(this);
   }

   public void clear() {
      this.component = null;
      this.setTxnidIsSet(false);
      this.txnid = 0L;
      this.user = null;
      this.hostname = null;
      this.agentInfo = "Unknown";
   }

   public int getComponentSize() {
      return this.component == null ? 0 : this.component.size();
   }

   @Nullable
   public Iterator getComponentIterator() {
      return this.component == null ? null : this.component.iterator();
   }

   public void addToComponent(LockComponent elem) {
      if (this.component == null) {
         this.component = new ArrayList();
      }

      this.component.add(elem);
   }

   @Nullable
   public List getComponent() {
      return this.component;
   }

   public void setComponent(@Nullable List component) {
      this.component = component;
   }

   public void unsetComponent() {
      this.component = null;
   }

   public boolean isSetComponent() {
      return this.component != null;
   }

   public void setComponentIsSet(boolean value) {
      if (!value) {
         this.component = null;
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
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetTxnid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setTxnidIsSet(boolean value) {
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
         case COMPONENT:
            if (value == null) {
               this.unsetComponent();
            } else {
               this.setComponent((List)value);
            }
            break;
         case TXNID:
            if (value == null) {
               this.unsetTxnid();
            } else {
               this.setTxnid((Long)value);
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
         case COMPONENT:
            return this.getComponent();
         case TXNID:
            return this.getTxnid();
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
            case COMPONENT:
               return this.isSetComponent();
            case TXNID:
               return this.isSetTxnid();
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
      return that instanceof LockRequest ? this.equals((LockRequest)that) : false;
   }

   public boolean equals(LockRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_component = this.isSetComponent();
         boolean that_present_component = that.isSetComponent();
         if (this_present_component || that_present_component) {
            if (!this_present_component || !that_present_component) {
               return false;
            }

            if (!this.component.equals(that.component)) {
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
      hashCode = hashCode * 8191 + (this.isSetComponent() ? 131071 : 524287);
      if (this.isSetComponent()) {
         hashCode = hashCode * 8191 + this.component.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTxnid() ? 131071 : 524287);
      if (this.isSetTxnid()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.txnid);
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

      return hashCode;
   }

   public int compareTo(LockRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetComponent(), other.isSetComponent());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetComponent()) {
               lastComparison = TBaseHelper.compareTo(this.component, other.component);
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
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return LockRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("LockRequest(");
      boolean first = true;
      sb.append("component:");
      if (this.component == null) {
         sb.append("null");
      } else {
         sb.append(this.component);
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
      if (!this.isSetComponent()) {
         throw new TProtocolException("Required field 'component' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{LockRequest._Fields.TXNID, LockRequest._Fields.AGENT_INFO};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(LockRequest._Fields.COMPONENT, new FieldMetaData("component", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, LockComponent.class))));
      tmpMap.put(LockRequest._Fields.TXNID, new FieldMetaData("txnid", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(LockRequest._Fields.USER, new FieldMetaData("user", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(LockRequest._Fields.HOSTNAME, new FieldMetaData("hostname", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(LockRequest._Fields.AGENT_INFO, new FieldMetaData("agentInfo", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(LockRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      COMPONENT((short)1, "component"),
      TXNID((short)2, "txnid"),
      USER((short)3, "user"),
      HOSTNAME((short)4, "hostname"),
      AGENT_INFO((short)5, "agentInfo");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return COMPONENT;
            case 2:
               return TXNID;
            case 3:
               return USER;
            case 4:
               return HOSTNAME;
            case 5:
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

   private static class LockRequestStandardSchemeFactory implements SchemeFactory {
      private LockRequestStandardSchemeFactory() {
      }

      public LockRequestStandardScheme getScheme() {
         return new LockRequestStandardScheme();
      }
   }

   private static class LockRequestStandardScheme extends StandardScheme {
      private LockRequestStandardScheme() {
      }

      public void read(TProtocol iprot, LockRequest struct) throws TException {
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

                  TList _list524 = iprot.readListBegin();
                  struct.component = new ArrayList(_list524.size);

                  for(int _i526 = 0; _i526 < _list524.size; ++_i526) {
                     LockComponent _elem525 = new LockComponent();
                     _elem525.read(iprot);
                     struct.component.add(_elem525);
                  }

                  iprot.readListEnd();
                  struct.setComponentIsSet(true);
                  break;
               case 2:
                  if (schemeField.type == 10) {
                     struct.txnid = iprot.readI64();
                     struct.setTxnidIsSet(true);
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
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, LockRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(LockRequest.STRUCT_DESC);
         if (struct.component != null) {
            oprot.writeFieldBegin(LockRequest.COMPONENT_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.component.size()));

            for(LockComponent _iter527 : struct.component) {
               _iter527.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetTxnid()) {
            oprot.writeFieldBegin(LockRequest.TXNID_FIELD_DESC);
            oprot.writeI64(struct.txnid);
            oprot.writeFieldEnd();
         }

         if (struct.user != null) {
            oprot.writeFieldBegin(LockRequest.USER_FIELD_DESC);
            oprot.writeString(struct.user);
            oprot.writeFieldEnd();
         }

         if (struct.hostname != null) {
            oprot.writeFieldBegin(LockRequest.HOSTNAME_FIELD_DESC);
            oprot.writeString(struct.hostname);
            oprot.writeFieldEnd();
         }

         if (struct.agentInfo != null && struct.isSetAgentInfo()) {
            oprot.writeFieldBegin(LockRequest.AGENT_INFO_FIELD_DESC);
            oprot.writeString(struct.agentInfo);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class LockRequestTupleSchemeFactory implements SchemeFactory {
      private LockRequestTupleSchemeFactory() {
      }

      public LockRequestTupleScheme getScheme() {
         return new LockRequestTupleScheme();
      }
   }

   private static class LockRequestTupleScheme extends TupleScheme {
      private LockRequestTupleScheme() {
      }

      public void write(TProtocol prot, LockRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.component.size());

         for(LockComponent _iter528 : struct.component) {
            _iter528.write(oprot);
         }

         oprot.writeString(struct.user);
         oprot.writeString(struct.hostname);
         BitSet optionals = new BitSet();
         if (struct.isSetTxnid()) {
            optionals.set(0);
         }

         if (struct.isSetAgentInfo()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetTxnid()) {
            oprot.writeI64(struct.txnid);
         }

         if (struct.isSetAgentInfo()) {
            oprot.writeString(struct.agentInfo);
         }

      }

      public void read(TProtocol prot, LockRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list529 = iprot.readListBegin((byte)12);
         struct.component = new ArrayList(_list529.size);

         for(int _i531 = 0; _i531 < _list529.size; ++_i531) {
            LockComponent _elem530 = new LockComponent();
            _elem530.read(iprot);
            struct.component.add(_elem530);
         }

         struct.setComponentIsSet(true);
         struct.user = iprot.readString();
         struct.setUserIsSet(true);
         struct.hostname = iprot.readString();
         struct.setHostnameIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.txnid = iprot.readI64();
            struct.setTxnidIsSet(true);
         }

         if (incoming.get(1)) {
            struct.agentInfo = iprot.readString();
            struct.setAgentInfoIsSet(true);
         }

      }
   }
}
