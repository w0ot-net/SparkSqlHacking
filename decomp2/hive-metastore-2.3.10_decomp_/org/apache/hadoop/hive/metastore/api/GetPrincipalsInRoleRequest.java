package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
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

public class GetPrincipalsInRoleRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetPrincipalsInRoleRequest");
   private static final TField ROLE_NAME_FIELD_DESC = new TField("roleName", (byte)11, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetPrincipalsInRoleRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetPrincipalsInRoleRequestTupleSchemeFactory();
   @Nullable
   private String roleName;
   public static final Map metaDataMap;

   public GetPrincipalsInRoleRequest() {
   }

   public GetPrincipalsInRoleRequest(String roleName) {
      this();
      this.roleName = roleName;
   }

   public GetPrincipalsInRoleRequest(GetPrincipalsInRoleRequest other) {
      if (other.isSetRoleName()) {
         this.roleName = other.roleName;
      }

   }

   public GetPrincipalsInRoleRequest deepCopy() {
      return new GetPrincipalsInRoleRequest(this);
   }

   public void clear() {
      this.roleName = null;
   }

   @Nullable
   public String getRoleName() {
      return this.roleName;
   }

   public void setRoleName(@Nullable String roleName) {
      this.roleName = roleName;
   }

   public void unsetRoleName() {
      this.roleName = null;
   }

   public boolean isSetRoleName() {
      return this.roleName != null;
   }

   public void setRoleNameIsSet(boolean value) {
      if (!value) {
         this.roleName = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case ROLE_NAME:
            if (value == null) {
               this.unsetRoleName();
            } else {
               this.setRoleName((String)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ROLE_NAME:
            return this.getRoleName();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case ROLE_NAME:
               return this.isSetRoleName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetPrincipalsInRoleRequest ? this.equals((GetPrincipalsInRoleRequest)that) : false;
   }

   public boolean equals(GetPrincipalsInRoleRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_roleName = this.isSetRoleName();
         boolean that_present_roleName = that.isSetRoleName();
         if (this_present_roleName || that_present_roleName) {
            if (!this_present_roleName || !that_present_roleName) {
               return false;
            }

            if (!this.roleName.equals(that.roleName)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetRoleName() ? 131071 : 524287);
      if (this.isSetRoleName()) {
         hashCode = hashCode * 8191 + this.roleName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetPrincipalsInRoleRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetRoleName(), other.isSetRoleName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetRoleName()) {
               lastComparison = TBaseHelper.compareTo(this.roleName, other.roleName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GetPrincipalsInRoleRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetPrincipalsInRoleRequest(");
      boolean first = true;
      sb.append("roleName:");
      if (this.roleName == null) {
         sb.append("null");
      } else {
         sb.append(this.roleName);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetRoleName()) {
         throw new TProtocolException("Required field 'roleName' is unset! Struct:" + this.toString());
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetPrincipalsInRoleRequest._Fields.ROLE_NAME, new FieldMetaData("roleName", (byte)1, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetPrincipalsInRoleRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ROLE_NAME((short)1, "roleName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ROLE_NAME;
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

   private static class GetPrincipalsInRoleRequestStandardSchemeFactory implements SchemeFactory {
      private GetPrincipalsInRoleRequestStandardSchemeFactory() {
      }

      public GetPrincipalsInRoleRequestStandardScheme getScheme() {
         return new GetPrincipalsInRoleRequestStandardScheme();
      }
   }

   private static class GetPrincipalsInRoleRequestStandardScheme extends StandardScheme {
      private GetPrincipalsInRoleRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GetPrincipalsInRoleRequest struct) throws TException {
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
                     struct.roleName = iprot.readString();
                     struct.setRoleNameIsSet(true);
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

      public void write(TProtocol oprot, GetPrincipalsInRoleRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetPrincipalsInRoleRequest.STRUCT_DESC);
         if (struct.roleName != null) {
            oprot.writeFieldBegin(GetPrincipalsInRoleRequest.ROLE_NAME_FIELD_DESC);
            oprot.writeString(struct.roleName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetPrincipalsInRoleRequestTupleSchemeFactory implements SchemeFactory {
      private GetPrincipalsInRoleRequestTupleSchemeFactory() {
      }

      public GetPrincipalsInRoleRequestTupleScheme getScheme() {
         return new GetPrincipalsInRoleRequestTupleScheme();
      }
   }

   private static class GetPrincipalsInRoleRequestTupleScheme extends TupleScheme {
      private GetPrincipalsInRoleRequestTupleScheme() {
      }

      public void write(TProtocol prot, GetPrincipalsInRoleRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.roleName);
      }

      public void read(TProtocol prot, GetPrincipalsInRoleRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.roleName = iprot.readString();
         struct.setRoleNameIsSet(true);
      }
   }
}
