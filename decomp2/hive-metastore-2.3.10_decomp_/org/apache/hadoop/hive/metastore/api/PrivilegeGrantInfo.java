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
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class PrivilegeGrantInfo implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PrivilegeGrantInfo");
   private static final TField PRIVILEGE_FIELD_DESC = new TField("privilege", (byte)11, (short)1);
   private static final TField CREATE_TIME_FIELD_DESC = new TField("createTime", (byte)8, (short)2);
   private static final TField GRANTOR_FIELD_DESC = new TField("grantor", (byte)11, (short)3);
   private static final TField GRANTOR_TYPE_FIELD_DESC = new TField("grantorType", (byte)8, (short)4);
   private static final TField GRANT_OPTION_FIELD_DESC = new TField("grantOption", (byte)2, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PrivilegeGrantInfoStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PrivilegeGrantInfoTupleSchemeFactory();
   @Nullable
   private String privilege;
   private int createTime;
   @Nullable
   private String grantor;
   @Nullable
   private PrincipalType grantorType;
   private boolean grantOption;
   private static final int __CREATETIME_ISSET_ID = 0;
   private static final int __GRANTOPTION_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public PrivilegeGrantInfo() {
      this.__isset_bitfield = 0;
   }

   public PrivilegeGrantInfo(String privilege, int createTime, String grantor, PrincipalType grantorType, boolean grantOption) {
      this();
      this.privilege = privilege;
      this.createTime = createTime;
      this.setCreateTimeIsSet(true);
      this.grantor = grantor;
      this.grantorType = grantorType;
      this.grantOption = grantOption;
      this.setGrantOptionIsSet(true);
   }

   public PrivilegeGrantInfo(PrivilegeGrantInfo other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetPrivilege()) {
         this.privilege = other.privilege;
      }

      this.createTime = other.createTime;
      if (other.isSetGrantor()) {
         this.grantor = other.grantor;
      }

      if (other.isSetGrantorType()) {
         this.grantorType = other.grantorType;
      }

      this.grantOption = other.grantOption;
   }

   public PrivilegeGrantInfo deepCopy() {
      return new PrivilegeGrantInfo(this);
   }

   public void clear() {
      this.privilege = null;
      this.setCreateTimeIsSet(false);
      this.createTime = 0;
      this.grantor = null;
      this.grantorType = null;
      this.setGrantOptionIsSet(false);
      this.grantOption = false;
   }

   @Nullable
   public String getPrivilege() {
      return this.privilege;
   }

   public void setPrivilege(@Nullable String privilege) {
      this.privilege = privilege;
   }

   public void unsetPrivilege() {
      this.privilege = null;
   }

   public boolean isSetPrivilege() {
      return this.privilege != null;
   }

   public void setPrivilegeIsSet(boolean value) {
      if (!value) {
         this.privilege = null;
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
   public String getGrantor() {
      return this.grantor;
   }

   public void setGrantor(@Nullable String grantor) {
      this.grantor = grantor;
   }

   public void unsetGrantor() {
      this.grantor = null;
   }

   public boolean isSetGrantor() {
      return this.grantor != null;
   }

   public void setGrantorIsSet(boolean value) {
      if (!value) {
         this.grantor = null;
      }

   }

   @Nullable
   public PrincipalType getGrantorType() {
      return this.grantorType;
   }

   public void setGrantorType(@Nullable PrincipalType grantorType) {
      this.grantorType = grantorType;
   }

   public void unsetGrantorType() {
      this.grantorType = null;
   }

   public boolean isSetGrantorType() {
      return this.grantorType != null;
   }

   public void setGrantorTypeIsSet(boolean value) {
      if (!value) {
         this.grantorType = null;
      }

   }

   public boolean isGrantOption() {
      return this.grantOption;
   }

   public void setGrantOption(boolean grantOption) {
      this.grantOption = grantOption;
      this.setGrantOptionIsSet(true);
   }

   public void unsetGrantOption() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetGrantOption() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setGrantOptionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PRIVILEGE:
            if (value == null) {
               this.unsetPrivilege();
            } else {
               this.setPrivilege((String)value);
            }
            break;
         case CREATE_TIME:
            if (value == null) {
               this.unsetCreateTime();
            } else {
               this.setCreateTime((Integer)value);
            }
            break;
         case GRANTOR:
            if (value == null) {
               this.unsetGrantor();
            } else {
               this.setGrantor((String)value);
            }
            break;
         case GRANTOR_TYPE:
            if (value == null) {
               this.unsetGrantorType();
            } else {
               this.setGrantorType((PrincipalType)value);
            }
            break;
         case GRANT_OPTION:
            if (value == null) {
               this.unsetGrantOption();
            } else {
               this.setGrantOption((Boolean)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PRIVILEGE:
            return this.getPrivilege();
         case CREATE_TIME:
            return this.getCreateTime();
         case GRANTOR:
            return this.getGrantor();
         case GRANTOR_TYPE:
            return this.getGrantorType();
         case GRANT_OPTION:
            return this.isGrantOption();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PRIVILEGE:
               return this.isSetPrivilege();
            case CREATE_TIME:
               return this.isSetCreateTime();
            case GRANTOR:
               return this.isSetGrantor();
            case GRANTOR_TYPE:
               return this.isSetGrantorType();
            case GRANT_OPTION:
               return this.isSetGrantOption();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PrivilegeGrantInfo ? this.equals((PrivilegeGrantInfo)that) : false;
   }

   public boolean equals(PrivilegeGrantInfo that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_privilege = this.isSetPrivilege();
         boolean that_present_privilege = that.isSetPrivilege();
         if (this_present_privilege || that_present_privilege) {
            if (!this_present_privilege || !that_present_privilege) {
               return false;
            }

            if (!this.privilege.equals(that.privilege)) {
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

         boolean this_present_grantor = this.isSetGrantor();
         boolean that_present_grantor = that.isSetGrantor();
         if (this_present_grantor || that_present_grantor) {
            if (!this_present_grantor || !that_present_grantor) {
               return false;
            }

            if (!this.grantor.equals(that.grantor)) {
               return false;
            }
         }

         boolean this_present_grantorType = this.isSetGrantorType();
         boolean that_present_grantorType = that.isSetGrantorType();
         if (this_present_grantorType || that_present_grantorType) {
            if (!this_present_grantorType || !that_present_grantorType) {
               return false;
            }

            if (!this.grantorType.equals(that.grantorType)) {
               return false;
            }
         }

         boolean this_present_grantOption = true;
         boolean that_present_grantOption = true;
         if (this_present_grantOption || that_present_grantOption) {
            if (!this_present_grantOption || !that_present_grantOption) {
               return false;
            }

            if (this.grantOption != that.grantOption) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPrivilege() ? 131071 : 524287);
      if (this.isSetPrivilege()) {
         hashCode = hashCode * 8191 + this.privilege.hashCode();
      }

      hashCode = hashCode * 8191 + this.createTime;
      hashCode = hashCode * 8191 + (this.isSetGrantor() ? 131071 : 524287);
      if (this.isSetGrantor()) {
         hashCode = hashCode * 8191 + this.grantor.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetGrantorType() ? 131071 : 524287);
      if (this.isSetGrantorType()) {
         hashCode = hashCode * 8191 + this.grantorType.getValue();
      }

      hashCode = hashCode * 8191 + (this.grantOption ? 131071 : 524287);
      return hashCode;
   }

   public int compareTo(PrivilegeGrantInfo other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPrivilege(), other.isSetPrivilege());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPrivilege()) {
               lastComparison = TBaseHelper.compareTo(this.privilege, other.privilege);
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

               lastComparison = Boolean.compare(this.isSetGrantor(), other.isSetGrantor());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetGrantor()) {
                     lastComparison = TBaseHelper.compareTo(this.grantor, other.grantor);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetGrantorType(), other.isSetGrantorType());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetGrantorType()) {
                        lastComparison = TBaseHelper.compareTo(this.grantorType, other.grantorType);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetGrantOption(), other.isSetGrantOption());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetGrantOption()) {
                           lastComparison = TBaseHelper.compareTo(this.grantOption, other.grantOption);
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
      return PrivilegeGrantInfo._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PrivilegeGrantInfo(");
      boolean first = true;
      sb.append("privilege:");
      if (this.privilege == null) {
         sb.append("null");
      } else {
         sb.append(this.privilege);
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

      sb.append("grantor:");
      if (this.grantor == null) {
         sb.append("null");
      } else {
         sb.append(this.grantor);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("grantorType:");
      if (this.grantorType == null) {
         sb.append("null");
      } else {
         sb.append(this.grantorType);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("grantOption:");
      sb.append(this.grantOption);
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
      tmpMap.put(PrivilegeGrantInfo._Fields.PRIVILEGE, new FieldMetaData("privilege", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(PrivilegeGrantInfo._Fields.CREATE_TIME, new FieldMetaData("createTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(PrivilegeGrantInfo._Fields.GRANTOR, new FieldMetaData("grantor", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(PrivilegeGrantInfo._Fields.GRANTOR_TYPE, new FieldMetaData("grantorType", (byte)3, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(PrivilegeGrantInfo._Fields.GRANT_OPTION, new FieldMetaData("grantOption", (byte)3, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PrivilegeGrantInfo.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRIVILEGE((short)1, "privilege"),
      CREATE_TIME((short)2, "createTime"),
      GRANTOR((short)3, "grantor"),
      GRANTOR_TYPE((short)4, "grantorType"),
      GRANT_OPTION((short)5, "grantOption");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRIVILEGE;
            case 2:
               return CREATE_TIME;
            case 3:
               return GRANTOR;
            case 4:
               return GRANTOR_TYPE;
            case 5:
               return GRANT_OPTION;
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

   private static class PrivilegeGrantInfoStandardSchemeFactory implements SchemeFactory {
      private PrivilegeGrantInfoStandardSchemeFactory() {
      }

      public PrivilegeGrantInfoStandardScheme getScheme() {
         return new PrivilegeGrantInfoStandardScheme();
      }
   }

   private static class PrivilegeGrantInfoStandardScheme extends StandardScheme {
      private PrivilegeGrantInfoStandardScheme() {
      }

      public void read(TProtocol iprot, PrivilegeGrantInfo struct) throws TException {
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
                     struct.privilege = iprot.readString();
                     struct.setPrivilegeIsSet(true);
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
                     struct.grantor = iprot.readString();
                     struct.setGrantorIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.grantorType = PrincipalType.findByValue(iprot.readI32());
                     struct.setGrantorTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 2) {
                     struct.grantOption = iprot.readBool();
                     struct.setGrantOptionIsSet(true);
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

      public void write(TProtocol oprot, PrivilegeGrantInfo struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PrivilegeGrantInfo.STRUCT_DESC);
         if (struct.privilege != null) {
            oprot.writeFieldBegin(PrivilegeGrantInfo.PRIVILEGE_FIELD_DESC);
            oprot.writeString(struct.privilege);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(PrivilegeGrantInfo.CREATE_TIME_FIELD_DESC);
         oprot.writeI32(struct.createTime);
         oprot.writeFieldEnd();
         if (struct.grantor != null) {
            oprot.writeFieldBegin(PrivilegeGrantInfo.GRANTOR_FIELD_DESC);
            oprot.writeString(struct.grantor);
            oprot.writeFieldEnd();
         }

         if (struct.grantorType != null) {
            oprot.writeFieldBegin(PrivilegeGrantInfo.GRANTOR_TYPE_FIELD_DESC);
            oprot.writeI32(struct.grantorType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(PrivilegeGrantInfo.GRANT_OPTION_FIELD_DESC);
         oprot.writeBool(struct.grantOption);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PrivilegeGrantInfoTupleSchemeFactory implements SchemeFactory {
      private PrivilegeGrantInfoTupleSchemeFactory() {
      }

      public PrivilegeGrantInfoTupleScheme getScheme() {
         return new PrivilegeGrantInfoTupleScheme();
      }
   }

   private static class PrivilegeGrantInfoTupleScheme extends TupleScheme {
      private PrivilegeGrantInfoTupleScheme() {
      }

      public void write(TProtocol prot, PrivilegeGrantInfo struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetPrivilege()) {
            optionals.set(0);
         }

         if (struct.isSetCreateTime()) {
            optionals.set(1);
         }

         if (struct.isSetGrantor()) {
            optionals.set(2);
         }

         if (struct.isSetGrantorType()) {
            optionals.set(3);
         }

         if (struct.isSetGrantOption()) {
            optionals.set(4);
         }

         oprot.writeBitSet(optionals, 5);
         if (struct.isSetPrivilege()) {
            oprot.writeString(struct.privilege);
         }

         if (struct.isSetCreateTime()) {
            oprot.writeI32(struct.createTime);
         }

         if (struct.isSetGrantor()) {
            oprot.writeString(struct.grantor);
         }

         if (struct.isSetGrantorType()) {
            oprot.writeI32(struct.grantorType.getValue());
         }

         if (struct.isSetGrantOption()) {
            oprot.writeBool(struct.grantOption);
         }

      }

      public void read(TProtocol prot, PrivilegeGrantInfo struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(5);
         if (incoming.get(0)) {
            struct.privilege = iprot.readString();
            struct.setPrivilegeIsSet(true);
         }

         if (incoming.get(1)) {
            struct.createTime = iprot.readI32();
            struct.setCreateTimeIsSet(true);
         }

         if (incoming.get(2)) {
            struct.grantor = iprot.readString();
            struct.setGrantorIsSet(true);
         }

         if (incoming.get(3)) {
            struct.grantorType = PrincipalType.findByValue(iprot.readI32());
            struct.setGrantorTypeIsSet(true);
         }

         if (incoming.get(4)) {
            struct.grantOption = iprot.readBool();
            struct.setGrantOptionIsSet(true);
         }

      }
   }
}
