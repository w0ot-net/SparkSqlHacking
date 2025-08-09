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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class GrantRevokePrivilegeRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GrantRevokePrivilegeRequest");
   private static final TField REQUEST_TYPE_FIELD_DESC = new TField("requestType", (byte)8, (short)1);
   private static final TField PRIVILEGES_FIELD_DESC = new TField("privileges", (byte)12, (short)2);
   private static final TField REVOKE_GRANT_OPTION_FIELD_DESC = new TField("revokeGrantOption", (byte)2, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GrantRevokePrivilegeRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GrantRevokePrivilegeRequestTupleSchemeFactory();
   @Nullable
   private GrantRevokeType requestType;
   @Nullable
   private PrivilegeBag privileges;
   private boolean revokeGrantOption;
   private static final int __REVOKEGRANTOPTION_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GrantRevokePrivilegeRequest() {
      this.__isset_bitfield = 0;
   }

   public GrantRevokePrivilegeRequest(GrantRevokeType requestType, PrivilegeBag privileges) {
      this();
      this.requestType = requestType;
      this.privileges = privileges;
   }

   public GrantRevokePrivilegeRequest(GrantRevokePrivilegeRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetRequestType()) {
         this.requestType = other.requestType;
      }

      if (other.isSetPrivileges()) {
         this.privileges = new PrivilegeBag(other.privileges);
      }

      this.revokeGrantOption = other.revokeGrantOption;
   }

   public GrantRevokePrivilegeRequest deepCopy() {
      return new GrantRevokePrivilegeRequest(this);
   }

   public void clear() {
      this.requestType = null;
      this.privileges = null;
      this.setRevokeGrantOptionIsSet(false);
      this.revokeGrantOption = false;
   }

   @Nullable
   public GrantRevokeType getRequestType() {
      return this.requestType;
   }

   public void setRequestType(@Nullable GrantRevokeType requestType) {
      this.requestType = requestType;
   }

   public void unsetRequestType() {
      this.requestType = null;
   }

   public boolean isSetRequestType() {
      return this.requestType != null;
   }

   public void setRequestTypeIsSet(boolean value) {
      if (!value) {
         this.requestType = null;
      }

   }

   @Nullable
   public PrivilegeBag getPrivileges() {
      return this.privileges;
   }

   public void setPrivileges(@Nullable PrivilegeBag privileges) {
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

   public boolean isRevokeGrantOption() {
      return this.revokeGrantOption;
   }

   public void setRevokeGrantOption(boolean revokeGrantOption) {
      this.revokeGrantOption = revokeGrantOption;
      this.setRevokeGrantOptionIsSet(true);
   }

   public void unsetRevokeGrantOption() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetRevokeGrantOption() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setRevokeGrantOptionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case REQUEST_TYPE:
            if (value == null) {
               this.unsetRequestType();
            } else {
               this.setRequestType((GrantRevokeType)value);
            }
            break;
         case PRIVILEGES:
            if (value == null) {
               this.unsetPrivileges();
            } else {
               this.setPrivileges((PrivilegeBag)value);
            }
            break;
         case REVOKE_GRANT_OPTION:
            if (value == null) {
               this.unsetRevokeGrantOption();
            } else {
               this.setRevokeGrantOption((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case REQUEST_TYPE:
            return this.getRequestType();
         case PRIVILEGES:
            return this.getPrivileges();
         case REVOKE_GRANT_OPTION:
            return this.isRevokeGrantOption();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case REQUEST_TYPE:
               return this.isSetRequestType();
            case PRIVILEGES:
               return this.isSetPrivileges();
            case REVOKE_GRANT_OPTION:
               return this.isSetRevokeGrantOption();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GrantRevokePrivilegeRequest ? this.equals((GrantRevokePrivilegeRequest)that) : false;
   }

   public boolean equals(GrantRevokePrivilegeRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_requestType = this.isSetRequestType();
         boolean that_present_requestType = that.isSetRequestType();
         if (this_present_requestType || that_present_requestType) {
            if (!this_present_requestType || !that_present_requestType) {
               return false;
            }

            if (!this.requestType.equals(that.requestType)) {
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

         boolean this_present_revokeGrantOption = this.isSetRevokeGrantOption();
         boolean that_present_revokeGrantOption = that.isSetRevokeGrantOption();
         if (this_present_revokeGrantOption || that_present_revokeGrantOption) {
            if (!this_present_revokeGrantOption || !that_present_revokeGrantOption) {
               return false;
            }

            if (this.revokeGrantOption != that.revokeGrantOption) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetRequestType() ? 131071 : 524287);
      if (this.isSetRequestType()) {
         hashCode = hashCode * 8191 + this.requestType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetPrivileges() ? 131071 : 524287);
      if (this.isSetPrivileges()) {
         hashCode = hashCode * 8191 + this.privileges.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetRevokeGrantOption() ? 131071 : 524287);
      if (this.isSetRevokeGrantOption()) {
         hashCode = hashCode * 8191 + (this.revokeGrantOption ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(GrantRevokePrivilegeRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetRequestType(), other.isSetRequestType());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetRequestType()) {
               lastComparison = TBaseHelper.compareTo(this.requestType, other.requestType);
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

               lastComparison = Boolean.compare(this.isSetRevokeGrantOption(), other.isSetRevokeGrantOption());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetRevokeGrantOption()) {
                     lastComparison = TBaseHelper.compareTo(this.revokeGrantOption, other.revokeGrantOption);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GrantRevokePrivilegeRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GrantRevokePrivilegeRequest(");
      boolean first = true;
      sb.append("requestType:");
      if (this.requestType == null) {
         sb.append("null");
      } else {
         sb.append(this.requestType);
      }

      first = false;
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
      if (this.isSetRevokeGrantOption()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("revokeGrantOption:");
         sb.append(this.revokeGrantOption);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
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
      optionals = new _Fields[]{GrantRevokePrivilegeRequest._Fields.REVOKE_GRANT_OPTION};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GrantRevokePrivilegeRequest._Fields.REQUEST_TYPE, new FieldMetaData("requestType", (byte)3, new EnumMetaData((byte)16, GrantRevokeType.class)));
      tmpMap.put(GrantRevokePrivilegeRequest._Fields.PRIVILEGES, new FieldMetaData("privileges", (byte)3, new StructMetaData((byte)12, PrivilegeBag.class)));
      tmpMap.put(GrantRevokePrivilegeRequest._Fields.REVOKE_GRANT_OPTION, new FieldMetaData("revokeGrantOption", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GrantRevokePrivilegeRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      REQUEST_TYPE((short)1, "requestType"),
      PRIVILEGES((short)2, "privileges"),
      REVOKE_GRANT_OPTION((short)3, "revokeGrantOption");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return REQUEST_TYPE;
            case 2:
               return PRIVILEGES;
            case 3:
               return REVOKE_GRANT_OPTION;
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

   private static class GrantRevokePrivilegeRequestStandardSchemeFactory implements SchemeFactory {
      private GrantRevokePrivilegeRequestStandardSchemeFactory() {
      }

      public GrantRevokePrivilegeRequestStandardScheme getScheme() {
         return new GrantRevokePrivilegeRequestStandardScheme();
      }
   }

   private static class GrantRevokePrivilegeRequestStandardScheme extends StandardScheme {
      private GrantRevokePrivilegeRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GrantRevokePrivilegeRequest struct) throws TException {
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
                     struct.requestType = GrantRevokeType.findByValue(iprot.readI32());
                     struct.setRequestTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.privileges = new PrivilegeBag();
                     struct.privileges.read(iprot);
                     struct.setPrivilegesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 2) {
                     struct.revokeGrantOption = iprot.readBool();
                     struct.setRevokeGrantOptionIsSet(true);
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

      public void write(TProtocol oprot, GrantRevokePrivilegeRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GrantRevokePrivilegeRequest.STRUCT_DESC);
         if (struct.requestType != null) {
            oprot.writeFieldBegin(GrantRevokePrivilegeRequest.REQUEST_TYPE_FIELD_DESC);
            oprot.writeI32(struct.requestType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.privileges != null) {
            oprot.writeFieldBegin(GrantRevokePrivilegeRequest.PRIVILEGES_FIELD_DESC);
            struct.privileges.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.isSetRevokeGrantOption()) {
            oprot.writeFieldBegin(GrantRevokePrivilegeRequest.REVOKE_GRANT_OPTION_FIELD_DESC);
            oprot.writeBool(struct.revokeGrantOption);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GrantRevokePrivilegeRequestTupleSchemeFactory implements SchemeFactory {
      private GrantRevokePrivilegeRequestTupleSchemeFactory() {
      }

      public GrantRevokePrivilegeRequestTupleScheme getScheme() {
         return new GrantRevokePrivilegeRequestTupleScheme();
      }
   }

   private static class GrantRevokePrivilegeRequestTupleScheme extends TupleScheme {
      private GrantRevokePrivilegeRequestTupleScheme() {
      }

      public void write(TProtocol prot, GrantRevokePrivilegeRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetRequestType()) {
            optionals.set(0);
         }

         if (struct.isSetPrivileges()) {
            optionals.set(1);
         }

         if (struct.isSetRevokeGrantOption()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetRequestType()) {
            oprot.writeI32(struct.requestType.getValue());
         }

         if (struct.isSetPrivileges()) {
            struct.privileges.write(oprot);
         }

         if (struct.isSetRevokeGrantOption()) {
            oprot.writeBool(struct.revokeGrantOption);
         }

      }

      public void read(TProtocol prot, GrantRevokePrivilegeRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.requestType = GrantRevokeType.findByValue(iprot.readI32());
            struct.setRequestTypeIsSet(true);
         }

         if (incoming.get(1)) {
            struct.privileges = new PrivilegeBag();
            struct.privileges.read(iprot);
            struct.setPrivilegesIsSet(true);
         }

         if (incoming.get(2)) {
            struct.revokeGrantOption = iprot.readBool();
            struct.setRevokeGrantOptionIsSet(true);
         }

      }
   }
}
