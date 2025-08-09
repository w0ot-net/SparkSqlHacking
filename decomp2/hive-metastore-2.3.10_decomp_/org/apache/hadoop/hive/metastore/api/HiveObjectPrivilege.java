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

public class HiveObjectPrivilege implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("HiveObjectPrivilege");
   private static final TField HIVE_OBJECT_FIELD_DESC = new TField("hiveObject", (byte)12, (short)1);
   private static final TField PRINCIPAL_NAME_FIELD_DESC = new TField("principalName", (byte)11, (short)2);
   private static final TField PRINCIPAL_TYPE_FIELD_DESC = new TField("principalType", (byte)8, (short)3);
   private static final TField GRANT_INFO_FIELD_DESC = new TField("grantInfo", (byte)12, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new HiveObjectPrivilegeStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new HiveObjectPrivilegeTupleSchemeFactory();
   @Nullable
   private HiveObjectRef hiveObject;
   @Nullable
   private String principalName;
   @Nullable
   private PrincipalType principalType;
   @Nullable
   private PrivilegeGrantInfo grantInfo;
   public static final Map metaDataMap;

   public HiveObjectPrivilege() {
   }

   public HiveObjectPrivilege(HiveObjectRef hiveObject, String principalName, PrincipalType principalType, PrivilegeGrantInfo grantInfo) {
      this();
      this.hiveObject = hiveObject;
      this.principalName = principalName;
      this.principalType = principalType;
      this.grantInfo = grantInfo;
   }

   public HiveObjectPrivilege(HiveObjectPrivilege other) {
      if (other.isSetHiveObject()) {
         this.hiveObject = new HiveObjectRef(other.hiveObject);
      }

      if (other.isSetPrincipalName()) {
         this.principalName = other.principalName;
      }

      if (other.isSetPrincipalType()) {
         this.principalType = other.principalType;
      }

      if (other.isSetGrantInfo()) {
         this.grantInfo = new PrivilegeGrantInfo(other.grantInfo);
      }

   }

   public HiveObjectPrivilege deepCopy() {
      return new HiveObjectPrivilege(this);
   }

   public void clear() {
      this.hiveObject = null;
      this.principalName = null;
      this.principalType = null;
      this.grantInfo = null;
   }

   @Nullable
   public HiveObjectRef getHiveObject() {
      return this.hiveObject;
   }

   public void setHiveObject(@Nullable HiveObjectRef hiveObject) {
      this.hiveObject = hiveObject;
   }

   public void unsetHiveObject() {
      this.hiveObject = null;
   }

   public boolean isSetHiveObject() {
      return this.hiveObject != null;
   }

   public void setHiveObjectIsSet(boolean value) {
      if (!value) {
         this.hiveObject = null;
      }

   }

   @Nullable
   public String getPrincipalName() {
      return this.principalName;
   }

   public void setPrincipalName(@Nullable String principalName) {
      this.principalName = principalName;
   }

   public void unsetPrincipalName() {
      this.principalName = null;
   }

   public boolean isSetPrincipalName() {
      return this.principalName != null;
   }

   public void setPrincipalNameIsSet(boolean value) {
      if (!value) {
         this.principalName = null;
      }

   }

   @Nullable
   public PrincipalType getPrincipalType() {
      return this.principalType;
   }

   public void setPrincipalType(@Nullable PrincipalType principalType) {
      this.principalType = principalType;
   }

   public void unsetPrincipalType() {
      this.principalType = null;
   }

   public boolean isSetPrincipalType() {
      return this.principalType != null;
   }

   public void setPrincipalTypeIsSet(boolean value) {
      if (!value) {
         this.principalType = null;
      }

   }

   @Nullable
   public PrivilegeGrantInfo getGrantInfo() {
      return this.grantInfo;
   }

   public void setGrantInfo(@Nullable PrivilegeGrantInfo grantInfo) {
      this.grantInfo = grantInfo;
   }

   public void unsetGrantInfo() {
      this.grantInfo = null;
   }

   public boolean isSetGrantInfo() {
      return this.grantInfo != null;
   }

   public void setGrantInfoIsSet(boolean value) {
      if (!value) {
         this.grantInfo = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case HIVE_OBJECT:
            if (value == null) {
               this.unsetHiveObject();
            } else {
               this.setHiveObject((HiveObjectRef)value);
            }
            break;
         case PRINCIPAL_NAME:
            if (value == null) {
               this.unsetPrincipalName();
            } else {
               this.setPrincipalName((String)value);
            }
            break;
         case PRINCIPAL_TYPE:
            if (value == null) {
               this.unsetPrincipalType();
            } else {
               this.setPrincipalType((PrincipalType)value);
            }
            break;
         case GRANT_INFO:
            if (value == null) {
               this.unsetGrantInfo();
            } else {
               this.setGrantInfo((PrivilegeGrantInfo)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case HIVE_OBJECT:
            return this.getHiveObject();
         case PRINCIPAL_NAME:
            return this.getPrincipalName();
         case PRINCIPAL_TYPE:
            return this.getPrincipalType();
         case GRANT_INFO:
            return this.getGrantInfo();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case HIVE_OBJECT:
               return this.isSetHiveObject();
            case PRINCIPAL_NAME:
               return this.isSetPrincipalName();
            case PRINCIPAL_TYPE:
               return this.isSetPrincipalType();
            case GRANT_INFO:
               return this.isSetGrantInfo();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof HiveObjectPrivilege ? this.equals((HiveObjectPrivilege)that) : false;
   }

   public boolean equals(HiveObjectPrivilege that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_hiveObject = this.isSetHiveObject();
         boolean that_present_hiveObject = that.isSetHiveObject();
         if (this_present_hiveObject || that_present_hiveObject) {
            if (!this_present_hiveObject || !that_present_hiveObject) {
               return false;
            }

            if (!this.hiveObject.equals(that.hiveObject)) {
               return false;
            }
         }

         boolean this_present_principalName = this.isSetPrincipalName();
         boolean that_present_principalName = that.isSetPrincipalName();
         if (this_present_principalName || that_present_principalName) {
            if (!this_present_principalName || !that_present_principalName) {
               return false;
            }

            if (!this.principalName.equals(that.principalName)) {
               return false;
            }
         }

         boolean this_present_principalType = this.isSetPrincipalType();
         boolean that_present_principalType = that.isSetPrincipalType();
         if (this_present_principalType || that_present_principalType) {
            if (!this_present_principalType || !that_present_principalType) {
               return false;
            }

            if (!this.principalType.equals(that.principalType)) {
               return false;
            }
         }

         boolean this_present_grantInfo = this.isSetGrantInfo();
         boolean that_present_grantInfo = that.isSetGrantInfo();
         if (this_present_grantInfo || that_present_grantInfo) {
            if (!this_present_grantInfo || !that_present_grantInfo) {
               return false;
            }

            if (!this.grantInfo.equals(that.grantInfo)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetHiveObject() ? 131071 : 524287);
      if (this.isSetHiveObject()) {
         hashCode = hashCode * 8191 + this.hiveObject.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrincipalName() ? 131071 : 524287);
      if (this.isSetPrincipalName()) {
         hashCode = hashCode * 8191 + this.principalName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrincipalType() ? 131071 : 524287);
      if (this.isSetPrincipalType()) {
         hashCode = hashCode * 8191 + this.principalType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetGrantInfo() ? 131071 : 524287);
      if (this.isSetGrantInfo()) {
         hashCode = hashCode * 8191 + this.grantInfo.hashCode();
      }

      return hashCode;
   }

   public int compareTo(HiveObjectPrivilege other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetHiveObject(), other.isSetHiveObject());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetHiveObject()) {
               lastComparison = TBaseHelper.compareTo(this.hiveObject, other.hiveObject);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetPrincipalName(), other.isSetPrincipalName());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetPrincipalName()) {
                  lastComparison = TBaseHelper.compareTo(this.principalName, other.principalName);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetPrincipalType(), other.isSetPrincipalType());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetPrincipalType()) {
                     lastComparison = TBaseHelper.compareTo(this.principalType, other.principalType);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetGrantInfo(), other.isSetGrantInfo());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetGrantInfo()) {
                        lastComparison = TBaseHelper.compareTo(this.grantInfo, other.grantInfo);
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
      return HiveObjectPrivilege._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("HiveObjectPrivilege(");
      boolean first = true;
      sb.append("hiveObject:");
      if (this.hiveObject == null) {
         sb.append("null");
      } else {
         sb.append(this.hiveObject);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("principalName:");
      if (this.principalName == null) {
         sb.append("null");
      } else {
         sb.append(this.principalName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("principalType:");
      if (this.principalType == null) {
         sb.append("null");
      } else {
         sb.append(this.principalType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("grantInfo:");
      if (this.grantInfo == null) {
         sb.append("null");
      } else {
         sb.append(this.grantInfo);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.hiveObject != null) {
         this.hiveObject.validate();
      }

      if (this.grantInfo != null) {
         this.grantInfo.validate();
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
      tmpMap.put(HiveObjectPrivilege._Fields.HIVE_OBJECT, new FieldMetaData("hiveObject", (byte)3, new StructMetaData((byte)12, HiveObjectRef.class)));
      tmpMap.put(HiveObjectPrivilege._Fields.PRINCIPAL_NAME, new FieldMetaData("principalName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(HiveObjectPrivilege._Fields.PRINCIPAL_TYPE, new FieldMetaData("principalType", (byte)3, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(HiveObjectPrivilege._Fields.GRANT_INFO, new FieldMetaData("grantInfo", (byte)3, new StructMetaData((byte)12, PrivilegeGrantInfo.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(HiveObjectPrivilege.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      HIVE_OBJECT((short)1, "hiveObject"),
      PRINCIPAL_NAME((short)2, "principalName"),
      PRINCIPAL_TYPE((short)3, "principalType"),
      GRANT_INFO((short)4, "grantInfo");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return HIVE_OBJECT;
            case 2:
               return PRINCIPAL_NAME;
            case 3:
               return PRINCIPAL_TYPE;
            case 4:
               return GRANT_INFO;
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

   private static class HiveObjectPrivilegeStandardSchemeFactory implements SchemeFactory {
      private HiveObjectPrivilegeStandardSchemeFactory() {
      }

      public HiveObjectPrivilegeStandardScheme getScheme() {
         return new HiveObjectPrivilegeStandardScheme();
      }
   }

   private static class HiveObjectPrivilegeStandardScheme extends StandardScheme {
      private HiveObjectPrivilegeStandardScheme() {
      }

      public void read(TProtocol iprot, HiveObjectPrivilege struct) throws TException {
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
                     struct.hiveObject = new HiveObjectRef();
                     struct.hiveObject.read(iprot);
                     struct.setHiveObjectIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.principalName = iprot.readString();
                     struct.setPrincipalNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.principalType = PrincipalType.findByValue(iprot.readI32());
                     struct.setPrincipalTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 12) {
                     struct.grantInfo = new PrivilegeGrantInfo();
                     struct.grantInfo.read(iprot);
                     struct.setGrantInfoIsSet(true);
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

      public void write(TProtocol oprot, HiveObjectPrivilege struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(HiveObjectPrivilege.STRUCT_DESC);
         if (struct.hiveObject != null) {
            oprot.writeFieldBegin(HiveObjectPrivilege.HIVE_OBJECT_FIELD_DESC);
            struct.hiveObject.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.principalName != null) {
            oprot.writeFieldBegin(HiveObjectPrivilege.PRINCIPAL_NAME_FIELD_DESC);
            oprot.writeString(struct.principalName);
            oprot.writeFieldEnd();
         }

         if (struct.principalType != null) {
            oprot.writeFieldBegin(HiveObjectPrivilege.PRINCIPAL_TYPE_FIELD_DESC);
            oprot.writeI32(struct.principalType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.grantInfo != null) {
            oprot.writeFieldBegin(HiveObjectPrivilege.GRANT_INFO_FIELD_DESC);
            struct.grantInfo.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class HiveObjectPrivilegeTupleSchemeFactory implements SchemeFactory {
      private HiveObjectPrivilegeTupleSchemeFactory() {
      }

      public HiveObjectPrivilegeTupleScheme getScheme() {
         return new HiveObjectPrivilegeTupleScheme();
      }
   }

   private static class HiveObjectPrivilegeTupleScheme extends TupleScheme {
      private HiveObjectPrivilegeTupleScheme() {
      }

      public void write(TProtocol prot, HiveObjectPrivilege struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetHiveObject()) {
            optionals.set(0);
         }

         if (struct.isSetPrincipalName()) {
            optionals.set(1);
         }

         if (struct.isSetPrincipalType()) {
            optionals.set(2);
         }

         if (struct.isSetGrantInfo()) {
            optionals.set(3);
         }

         oprot.writeBitSet(optionals, 4);
         if (struct.isSetHiveObject()) {
            struct.hiveObject.write(oprot);
         }

         if (struct.isSetPrincipalName()) {
            oprot.writeString(struct.principalName);
         }

         if (struct.isSetPrincipalType()) {
            oprot.writeI32(struct.principalType.getValue());
         }

         if (struct.isSetGrantInfo()) {
            struct.grantInfo.write(oprot);
         }

      }

      public void read(TProtocol prot, HiveObjectPrivilege struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(4);
         if (incoming.get(0)) {
            struct.hiveObject = new HiveObjectRef();
            struct.hiveObject.read(iprot);
            struct.setHiveObjectIsSet(true);
         }

         if (incoming.get(1)) {
            struct.principalName = iprot.readString();
            struct.setPrincipalNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.principalType = PrincipalType.findByValue(iprot.readI32());
            struct.setPrincipalTypeIsSet(true);
         }

         if (incoming.get(3)) {
            struct.grantInfo = new PrivilegeGrantInfo();
            struct.grantInfo.read(iprot);
            struct.setGrantInfoIsSet(true);
         }

      }
   }
}
