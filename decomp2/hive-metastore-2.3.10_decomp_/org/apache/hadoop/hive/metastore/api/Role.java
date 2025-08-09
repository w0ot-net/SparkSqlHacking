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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class Role implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Role");
   private static final TField ROLE_NAME_FIELD_DESC = new TField("roleName", (byte)11, (short)1);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)2);
   private static final TField OWNER_NAME_FIELD_DESC = new TField("ownerName", (byte)11, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new RoleStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new RoleTupleSchemeFactory();
   @Nullable
   private String roleName;
   private int createTime;
   @Nullable
   private String ownerName;
   private static final int __CREATETIME_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public Role() {
      this.__isset_bitfield = 0;
   }

   public Role(String roleName, int createTime, String ownerName) {
      this();
      this.roleName = roleName;
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.ownerName = ownerName;
   }

   public Role(Role other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetRoleName()) {
         this.roleName = other.roleName;
      }

      this.createTime = other.createTime;
      if (other.isSetOwnerName()) {
         this.ownerName = other.ownerName;
      }

   }

   public Role deepCopy() {
      return new Role(this);
   }

   public void clear() {
      this.roleName = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.ownerName = null;
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

   public int getCreateTime() {
      return this.createTime;
   }

   public void setCreateTime(int createTime) {
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
   }

   public void unsetCreateTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetCreateTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setCreateTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   @Nullable
   public String getOwnerName() {
      return this.ownerName;
   }

   public void setOwnerName(@Nullable String ownerName) {
      this.ownerName = ownerName;
   }

   public void unsetOwnerName() {
      this.ownerName = null;
   }

   public boolean isSetOwnerName() {
      return this.ownerName != null;
   }

   public void setOwnerNameIsSet(boolean value) {
      if (!value) {
         this.ownerName = null;
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
            break;
         case CREATE_TIME:
            if (value == null) {
               this.unsetCreateTime();
            } else {
               this.setCreateTime((Integer)value);
            }
            break;
         case OWNER_NAME:
            if (value == null) {
               this.unsetOwnerName();
            } else {
               this.setOwnerName((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ROLE_NAME:
            return this.getRoleName();
         case CREATE_TIME:
            return this.getCreateTime();
         case OWNER_NAME:
            return this.getOwnerName();
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
            case CREATE_TIME:
               return this.isSetCreateTime();
            case OWNER_NAME:
               return this.isSetOwnerName();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Role ? this.equals((Role)that) : false;
   }

   public boolean equals(Role that) {
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

         boolean this_present_createTime = true;
         boolean that_present_createTime = true;
         if (this_present_createTime || that_present_createTime) {
            if (!this_present_createTime || !that_present_createTime) {
               return false;
            }

            if (this.createTime != that.createTime) {
               return false;
            }
         }

         boolean this_present_ownerName = this.isSetOwnerName();
         boolean that_present_ownerName = that.isSetOwnerName();
         if (this_present_ownerName || that_present_ownerName) {
            if (!this_present_ownerName || !that_present_ownerName) {
               return false;
            }

            if (!this.ownerName.equals(that.ownerName)) {
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

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + (this.isSetOwnerName() ? 131071 : 524287);
      if (this.isSetOwnerName()) {
         hashCode = hashCode * 8191 + this.ownerName.hashCode();
      }

      return hashCode;
   }

   public int compareTo(Role other) {
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

            lastComparison = Boolean.compare(this.isSetCreateTime(), other.isSetCreateTime());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetCreateTime()) {
                  lastComparison = TBaseHelper.compareTo(this.createTime, other.createTime);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetOwnerName(), other.isSetOwnerName());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetOwnerName()) {
                     lastComparison = TBaseHelper.compareTo(this.ownerName, other.ownerName);
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
      return Role._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Role(");
      boolean first = true;
      sb.append("roleName:");
      if (this.roleName == null) {
         sb.append("null");
      } else {
         sb.append(this.roleName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("createTime:");
      sb.append(this.createTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("ownerName:");
      if (this.ownerName == null) {
         sb.append("null");
      } else {
         sb.append(this.ownerName);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(Role._Fields.ROLE_NAME, new FieldMetaData("roleName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(Role._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(Role._Fields.OWNER_NAME, new FieldMetaData("ownerName", (byte)3, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Role.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ROLE_NAME((short)1, "roleName"),
      CREATE_TIME((short)2, "createTime"),
      OWNER_NAME((short)3, "ownerName");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ROLE_NAME;
            case 2:
               return CREATE_TIME;
            case 3:
               return OWNER_NAME;
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

   private static class RoleStandardSchemeFactory implements SchemeFactory {
      private RoleStandardSchemeFactory() {
      }

      public RoleStandardScheme getScheme() {
         return new RoleStandardScheme();
      }
   }

   private static class RoleStandardScheme extends StandardScheme {
      private RoleStandardScheme() {
      }

      public void read(TProtocol iprot, Role struct) throws TException {
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
               case 2:
                  if (schemeField.type == 8) {
                     struct.createTime = iprot.readI32();
                     struct.setCreateTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.ownerName = iprot.readString();
                     struct.setOwnerNameIsSet(true);
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

      public void write(TProtocol oprot, Role struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Role.STRUCT_DESC);
         if (struct.roleName != null) {
            oprot.writeFieldBegin(Role.ROLE_NAME_FIELD_DESC);
            oprot.writeString(struct.roleName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(Role.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         if (struct.ownerName != null) {
            oprot.writeFieldBegin(Role.OWNER_NAME_FIELD_DESC);
            oprot.writeString(struct.ownerName);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class RoleTupleSchemeFactory implements SchemeFactory {
      private RoleTupleSchemeFactory() {
      }

      public RoleTupleScheme getScheme() {
         return new RoleTupleScheme();
      }
   }

   private static class RoleTupleScheme extends TupleScheme {
      private RoleTupleScheme() {
      }

      public void write(TProtocol prot, Role struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetRoleName()) {
            optionals.set(0);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(1);
         }

         if (struct.isSetOwnerName()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetRoleName()) {
            oprot.writeString(struct.roleName);
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetOwnerName()) {
            oprot.writeString(struct.ownerName);
         }

      }

      public void read(TProtocol prot, Role struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.roleName = iprot.readString();
            struct.setRoleNameIsSet(true);
         }

         if (incoming.get(1)) {
            struct.createTime = iprot.readI32();
            struct.setCreateTimeIsSet(true);
         }

         if (incoming.get(2)) {
            struct.ownerName = iprot.readString();
            struct.setOwnerNameIsSet(true);
         }

      }
   }
}
