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

public class RolePrincipalGrant implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("RolePrincipalGrant");
   private static final TField ROLE_NAME_FIELD_DESC = new TField("roleName", (byte)11, (short)1);
   private static final TField PRINCIPAL_NAME_FIELD_DESC = new TField("principalName", (byte)11, (short)2);
   private static final TField PRINCIPAL_TYPE_FIELD_DESC = new TField("principalType", (byte)8, (short)3);
   private static final TField GRANT_OPTION_FIELD_DESC = new TField("grantOption", (byte)2, (short)4);
   private static final TField GRANT_TIME_FIELD_DESC = new TField("grantTime", (byte)8, (short)5);
   private static final TField GRANTOR_NAME_FIELD_DESC = new TField("grantorName", (byte)11, (short)6);
   private static final TField GRANTOR_PRINCIPAL_TYPE_FIELD_DESC = new TField("grantorPrincipalType", (byte)8, (short)7);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new RolePrincipalGrantStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new RolePrincipalGrantTupleSchemeFactory();
   @Nullable
   private String roleName;
   @Nullable
   private String principalName;
   @Nullable
   private PrincipalType principalType;
   private boolean grantOption;
   private int grantTime;
   @Nullable
   private String grantorName;
   @Nullable
   private PrincipalType grantorPrincipalType;
   private static final int __GRANTOPTION_ISSET_ID = 0;
   private static final int __GRANTTIME_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public RolePrincipalGrant() {
      this.__isset_bitfield = 0;
   }

   public RolePrincipalGrant(String roleName, String principalName, PrincipalType principalType, boolean grantOption, int grantTime, String grantorName, PrincipalType grantorPrincipalType) {
      this();
      this.roleName = roleName;
      this.principalName = principalName;
      this.principalType = principalType;
      this.grantOption = grantOption;
      this.setGrantOptionIsSet(true);
      this.grantTime = grantTime;
      this.setGrantTimeIsSet(true);
      this.grantorName = grantorName;
      this.grantorPrincipalType = grantorPrincipalType;
   }

   public RolePrincipalGrant(RolePrincipalGrant other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetRoleName()) {
         this.roleName = other.roleName;
      }

      if (other.isSetPrincipalName()) {
         this.principalName = other.principalName;
      }

      if (other.isSetPrincipalType()) {
         this.principalType = other.principalType;
      }

      this.grantOption = other.grantOption;
      this.grantTime = other.grantTime;
      if (other.isSetGrantorName()) {
         this.grantorName = other.grantorName;
      }

      if (other.isSetGrantorPrincipalType()) {
         this.grantorPrincipalType = other.grantorPrincipalType;
      }

   }

   public RolePrincipalGrant deepCopy() {
      return new RolePrincipalGrant(this);
   }

   public void clear() {
      this.roleName = null;
      this.principalName = null;
      this.principalType = null;
      this.setGrantOptionIsSet(false);
      this.grantOption = false;
      this.setGrantTimeIsSet(false);
      this.grantTime = 0;
      this.grantorName = null;
      this.grantorPrincipalType = null;
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

   public boolean isGrantOption() {
      return this.grantOption;
   }

   public void setGrantOption(boolean grantOption) {
      this.grantOption = grantOption;
      this.setGrantOptionIsSet(true);
   }

   public void unsetGrantOption() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetGrantOption() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setGrantOptionIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getGrantTime() {
      return this.grantTime;
   }

   public void setGrantTime(int grantTime) {
      this.grantTime = grantTime;
      this.setGrantTimeIsSet(true);
   }

   public void unsetGrantTime() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetGrantTime() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setGrantTimeIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   @Nullable
   public String getGrantorName() {
      return this.grantorName;
   }

   public void setGrantorName(@Nullable String grantorName) {
      this.grantorName = grantorName;
   }

   public void unsetGrantorName() {
      this.grantorName = null;
   }

   public boolean isSetGrantorName() {
      return this.grantorName != null;
   }

   public void setGrantorNameIsSet(boolean value) {
      if (!value) {
         this.grantorName = null;
      }

   }

   @Nullable
   public PrincipalType getGrantorPrincipalType() {
      return this.grantorPrincipalType;
   }

   public void setGrantorPrincipalType(@Nullable PrincipalType grantorPrincipalType) {
      this.grantorPrincipalType = grantorPrincipalType;
   }

   public void unsetGrantorPrincipalType() {
      this.grantorPrincipalType = null;
   }

   public boolean isSetGrantorPrincipalType() {
      return this.grantorPrincipalType != null;
   }

   public void setGrantorPrincipalTypeIsSet(boolean value) {
      if (!value) {
         this.grantorPrincipalType = null;
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
         case GRANT_OPTION:
            if (value == null) {
               this.unsetGrantOption();
            } else {
               this.setGrantOption((Boolean)value);
            }
            break;
         case GRANT_TIME:
            if (value == null) {
               this.unsetGrantTime();
            } else {
               this.setGrantTime((Integer)value);
            }
            break;
         case GRANTOR_NAME:
            if (value == null) {
               this.unsetGrantorName();
            } else {
               this.setGrantorName((String)value);
            }
            break;
         case GRANTOR_PRINCIPAL_TYPE:
            if (value == null) {
               this.unsetGrantorPrincipalType();
            } else {
               this.setGrantorPrincipalType((PrincipalType)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case ROLE_NAME:
            return this.getRoleName();
         case PRINCIPAL_NAME:
            return this.getPrincipalName();
         case PRINCIPAL_TYPE:
            return this.getPrincipalType();
         case GRANT_OPTION:
            return this.isGrantOption();
         case GRANT_TIME:
            return this.getGrantTime();
         case GRANTOR_NAME:
            return this.getGrantorName();
         case GRANTOR_PRINCIPAL_TYPE:
            return this.getGrantorPrincipalType();
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
            case PRINCIPAL_NAME:
               return this.isSetPrincipalName();
            case PRINCIPAL_TYPE:
               return this.isSetPrincipalType();
            case GRANT_OPTION:
               return this.isSetGrantOption();
            case GRANT_TIME:
               return this.isSetGrantTime();
            case GRANTOR_NAME:
               return this.isSetGrantorName();
            case GRANTOR_PRINCIPAL_TYPE:
               return this.isSetGrantorPrincipalType();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof RolePrincipalGrant ? this.equals((RolePrincipalGrant)that) : false;
   }

   public boolean equals(RolePrincipalGrant that) {
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

         boolean this_present_grantTime = true;
         boolean that_present_grantTime = true;
         if (this_present_grantTime || that_present_grantTime) {
            if (!this_present_grantTime || !that_present_grantTime) {
               return false;
            }

            if (this.grantTime != that.grantTime) {
               return false;
            }
         }

         boolean this_present_grantorName = this.isSetGrantorName();
         boolean that_present_grantorName = that.isSetGrantorName();
         if (this_present_grantorName || that_present_grantorName) {
            if (!this_present_grantorName || !that_present_grantorName) {
               return false;
            }

            if (!this.grantorName.equals(that.grantorName)) {
               return false;
            }
         }

         boolean this_present_grantorPrincipalType = this.isSetGrantorPrincipalType();
         boolean that_present_grantorPrincipalType = that.isSetGrantorPrincipalType();
         if (this_present_grantorPrincipalType || that_present_grantorPrincipalType) {
            if (!this_present_grantorPrincipalType || !that_present_grantorPrincipalType) {
               return false;
            }

            if (!this.grantorPrincipalType.equals(that.grantorPrincipalType)) {
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

      hashCode = hashCode * 8191 + (this.isSetPrincipalName() ? 131071 : 524287);
      if (this.isSetPrincipalName()) {
         hashCode = hashCode * 8191 + this.principalName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrincipalType() ? 131071 : 524287);
      if (this.isSetPrincipalType()) {
         hashCode = hashCode * 8191 + this.principalType.getValue();
      }

      hashCode = hashCode * 8191 + (this.grantOption ? 131071 : 524287);
      hashCode = hashCode * 8191 + this.grantTime;
      hashCode = hashCode * 8191 + (this.isSetGrantorName() ? 131071 : 524287);
      if (this.isSetGrantorName()) {
         hashCode = hashCode * 8191 + this.grantorName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetGrantorPrincipalType() ? 131071 : 524287);
      if (this.isSetGrantorPrincipalType()) {
         hashCode = hashCode * 8191 + this.grantorPrincipalType.getValue();
      }

      return hashCode;
   }

   public int compareTo(RolePrincipalGrant other) {
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

                     lastComparison = Boolean.compare(this.isSetGrantTime(), other.isSetGrantTime());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetGrantTime()) {
                           lastComparison = TBaseHelper.compareTo(this.grantTime, other.grantTime);
                           if (lastComparison != 0) {
                              return lastComparison;
                           }
                        }

                        lastComparison = Boolean.compare(this.isSetGrantorName(), other.isSetGrantorName());
                        if (lastComparison != 0) {
                           return lastComparison;
                        } else {
                           if (this.isSetGrantorName()) {
                              lastComparison = TBaseHelper.compareTo(this.grantorName, other.grantorName);
                              if (lastComparison != 0) {
                                 return lastComparison;
                              }
                           }

                           lastComparison = Boolean.compare(this.isSetGrantorPrincipalType(), other.isSetGrantorPrincipalType());
                           if (lastComparison != 0) {
                              return lastComparison;
                           } else {
                              if (this.isSetGrantorPrincipalType()) {
                                 lastComparison = TBaseHelper.compareTo(this.grantorPrincipalType, other.grantorPrincipalType);
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
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return RolePrincipalGrant._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("RolePrincipalGrant(");
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

      sb.append("grantOption:");
      sb.append(this.grantOption);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("grantTime:");
      sb.append(this.grantTime);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("grantorName:");
      if (this.grantorName == null) {
         sb.append("null");
      } else {
         sb.append(this.grantorName);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("grantorPrincipalType:");
      if (this.grantorPrincipalType == null) {
         sb.append("null");
      } else {
         sb.append(this.grantorPrincipalType);
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
      tmpMap.put(RolePrincipalGrant._Fields.ROLE_NAME, new FieldMetaData("roleName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(RolePrincipalGrant._Fields.PRINCIPAL_NAME, new FieldMetaData("principalName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(RolePrincipalGrant._Fields.PRINCIPAL_TYPE, new FieldMetaData("principalType", (byte)3, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(RolePrincipalGrant._Fields.GRANT_OPTION, new FieldMetaData("grantOption", (byte)3, new FieldValueMetaData((byte)2)));
      tmpMap.put(RolePrincipalGrant._Fields.GRANT_TIME, new FieldMetaData("grantTime", (byte)3, new FieldValueMetaData((byte)8)));
      tmpMap.put(RolePrincipalGrant._Fields.GRANTOR_NAME, new FieldMetaData("grantorName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(RolePrincipalGrant._Fields.GRANTOR_PRINCIPAL_TYPE, new FieldMetaData("grantorPrincipalType", (byte)3, new EnumMetaData((byte)16, PrincipalType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(RolePrincipalGrant.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      ROLE_NAME((short)1, "roleName"),
      PRINCIPAL_NAME((short)2, "principalName"),
      PRINCIPAL_TYPE((short)3, "principalType"),
      GRANT_OPTION((short)4, "grantOption"),
      GRANT_TIME((short)5, "grantTime"),
      GRANTOR_NAME((short)6, "grantorName"),
      GRANTOR_PRINCIPAL_TYPE((short)7, "grantorPrincipalType");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return ROLE_NAME;
            case 2:
               return PRINCIPAL_NAME;
            case 3:
               return PRINCIPAL_TYPE;
            case 4:
               return GRANT_OPTION;
            case 5:
               return GRANT_TIME;
            case 6:
               return GRANTOR_NAME;
            case 7:
               return GRANTOR_PRINCIPAL_TYPE;
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

   private static class RolePrincipalGrantStandardSchemeFactory implements SchemeFactory {
      private RolePrincipalGrantStandardSchemeFactory() {
      }

      public RolePrincipalGrantStandardScheme getScheme() {
         return new RolePrincipalGrantStandardScheme();
      }
   }

   private static class RolePrincipalGrantStandardScheme extends StandardScheme {
      private RolePrincipalGrantStandardScheme() {
      }

      public void read(TProtocol iprot, RolePrincipalGrant struct) throws TException {
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
                  if (schemeField.type == 2) {
                     struct.grantOption = iprot.readBool();
                     struct.setGrantOptionIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 8) {
                     struct.grantTime = iprot.readI32();
                     struct.setGrantTimeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 11) {
                     struct.grantorName = iprot.readString();
                     struct.setGrantorNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
                  if (schemeField.type == 8) {
                     struct.grantorPrincipalType = PrincipalType.findByValue(iprot.readI32());
                     struct.setGrantorPrincipalTypeIsSet(true);
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

      public void write(TProtocol oprot, RolePrincipalGrant struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(RolePrincipalGrant.STRUCT_DESC);
         if (struct.roleName != null) {
            oprot.writeFieldBegin(RolePrincipalGrant.ROLE_NAME_FIELD_DESC);
            oprot.writeString(struct.roleName);
            oprot.writeFieldEnd();
         }

         if (struct.principalName != null) {
            oprot.writeFieldBegin(RolePrincipalGrant.PRINCIPAL_NAME_FIELD_DESC);
            oprot.writeString(struct.principalName);
            oprot.writeFieldEnd();
         }

         if (struct.principalType != null) {
            oprot.writeFieldBegin(RolePrincipalGrant.PRINCIPAL_TYPE_FIELD_DESC);
            oprot.writeI32(struct.principalType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(RolePrincipalGrant.GRANT_OPTION_FIELD_DESC);
         oprot.writeBool(struct.grantOption);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(RolePrincipalGrant.GRANT_TIME_FIELD_DESC);
         oprot.writeI32(struct.grantTime);
         oprot.writeFieldEnd();
         if (struct.grantorName != null) {
            oprot.writeFieldBegin(RolePrincipalGrant.GRANTOR_NAME_FIELD_DESC);
            oprot.writeString(struct.grantorName);
            oprot.writeFieldEnd();
         }

         if (struct.grantorPrincipalType != null) {
            oprot.writeFieldBegin(RolePrincipalGrant.GRANTOR_PRINCIPAL_TYPE_FIELD_DESC);
            oprot.writeI32(struct.grantorPrincipalType.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class RolePrincipalGrantTupleSchemeFactory implements SchemeFactory {
      private RolePrincipalGrantTupleSchemeFactory() {
      }

      public RolePrincipalGrantTupleScheme getScheme() {
         return new RolePrincipalGrantTupleScheme();
      }
   }

   private static class RolePrincipalGrantTupleScheme extends TupleScheme {
      private RolePrincipalGrantTupleScheme() {
      }

      public void write(TProtocol prot, RolePrincipalGrant struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetRoleName()) {
            optionals.set(0);
         }

         if (struct.isSetPrincipalName()) {
            optionals.set(1);
         }

         if (struct.isSetPrincipalType()) {
            optionals.set(2);
         }

         if (struct.isSetGrantOption()) {
            optionals.set(3);
         }

         if (struct.isSetGrantTime()) {
            optionals.set(4);
         }

         if (struct.isSetGrantorName()) {
            optionals.set(5);
         }

         if (struct.isSetGrantorPrincipalType()) {
            optionals.set(6);
         }

         oprot.writeBitSet(optionals, 7);
         if (struct.isSetRoleName()) {
            oprot.writeString(struct.roleName);
         }

         if (struct.isSetPrincipalName()) {
            oprot.writeString(struct.principalName);
         }

         if (struct.isSetPrincipalType()) {
            oprot.writeI32(struct.principalType.getValue());
         }

         if (struct.isSetGrantOption()) {
            oprot.writeBool(struct.grantOption);
         }

         if (struct.isSetGrantTime()) {
            oprot.writeI32(struct.grantTime);
         }

         if (struct.isSetGrantorName()) {
            oprot.writeString(struct.grantorName);
         }

         if (struct.isSetGrantorPrincipalType()) {
            oprot.writeI32(struct.grantorPrincipalType.getValue());
         }

      }

      public void read(TProtocol prot, RolePrincipalGrant struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(7);
         if (incoming.get(0)) {
            struct.roleName = iprot.readString();
            struct.setRoleNameIsSet(true);
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
            struct.grantOption = iprot.readBool();
            struct.setGrantOptionIsSet(true);
         }

         if (incoming.get(4)) {
            struct.grantTime = iprot.readI32();
            struct.setGrantTimeIsSet(true);
         }

         if (incoming.get(5)) {
            struct.grantorName = iprot.readString();
            struct.setGrantorNameIsSet(true);
         }

         if (incoming.get(6)) {
            struct.grantorPrincipalType = PrincipalType.findByValue(iprot.readI32());
            struct.setGrantorPrincipalTypeIsSet(true);
         }

      }
   }
}
