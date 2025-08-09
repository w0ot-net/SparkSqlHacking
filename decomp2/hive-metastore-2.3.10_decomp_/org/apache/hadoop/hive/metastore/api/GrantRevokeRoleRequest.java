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

public class GrantRevokeRoleRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GrantRevokeRoleRequest");
   private static final TField REQUEST_TYPE_FIELD_DESC = new TField("requestType", (byte)8, (short)1);
   private static final TField ROLE_NAME_FIELD_DESC = new TField("roleName", (byte)11, (short)2);
   private static final TField PRINCIPAL_NAME_FIELD_DESC = new TField("principalName", (byte)11, (short)3);
   private static final TField PRINCIPAL_TYPE_FIELD_DESC = new TField("principalType", (byte)8, (short)4);
   private static final TField GRANTOR_FIELD_DESC = new TField("grantor", (byte)11, (short)5);
   private static final TField GRANTOR_TYPE_FIELD_DESC = new TField("grantorType", (byte)8, (short)6);
   private static final TField GRANT_OPTION_FIELD_DESC = new TField("grantOption", (byte)2, (short)7);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GrantRevokeRoleRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GrantRevokeRoleRequestTupleSchemeFactory();
   @Nullable
   private GrantRevokeType requestType;
   @Nullable
   private String roleName;
   @Nullable
   private String principalName;
   @Nullable
   private PrincipalType principalType;
   @Nullable
   private String grantor;
   @Nullable
   private PrincipalType grantorType;
   private boolean grantOption;
   private static final int __GRANTOPTION_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GrantRevokeRoleRequest() {
      this.__isset_bitfield = 0;
   }

   public GrantRevokeRoleRequest(GrantRevokeType requestType, String roleName, String principalName, PrincipalType principalType) {
      this();
      this.requestType = requestType;
      this.roleName = roleName;
      this.principalName = principalName;
      this.principalType = principalType;
   }

   public GrantRevokeRoleRequest(GrantRevokeRoleRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      if (other.isSetRequestType()) {
         this.requestType = other.requestType;
      }

      if (other.isSetRoleName()) {
         this.roleName = other.roleName;
      }

      if (other.isSetPrincipalName()) {
         this.principalName = other.principalName;
      }

      if (other.isSetPrincipalType()) {
         this.principalType = other.principalType;
      }

      if (other.isSetGrantor()) {
         this.grantor = other.grantor;
      }

      if (other.isSetGrantorType()) {
         this.grantorType = other.grantorType;
      }

      this.grantOption = other.grantOption;
   }

   public GrantRevokeRoleRequest deepCopy() {
      return new GrantRevokeRoleRequest(this);
   }

   public void clear() {
      this.requestType = null;
      this.roleName = null;
      this.principalName = null;
      this.principalType = null;
      this.grantor = null;
      this.grantorType = null;
      this.setGrantOptionIsSet(false);
      this.grantOption = false;
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
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetGrantOption() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setGrantOptionIsSet(boolean value) {
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
         case REQUEST_TYPE:
            return this.getRequestType();
         case ROLE_NAME:
            return this.getRoleName();
         case PRINCIPAL_NAME:
            return this.getPrincipalName();
         case PRINCIPAL_TYPE:
            return this.getPrincipalType();
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
            case REQUEST_TYPE:
               return this.isSetRequestType();
            case ROLE_NAME:
               return this.isSetRoleName();
            case PRINCIPAL_NAME:
               return this.isSetPrincipalName();
            case PRINCIPAL_TYPE:
               return this.isSetPrincipalType();
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
      return that instanceof GrantRevokeRoleRequest ? this.equals((GrantRevokeRoleRequest)that) : false;
   }

   public boolean equals(GrantRevokeRoleRequest that) {
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

         boolean this_present_grantOption = this.isSetGrantOption();
         boolean that_present_grantOption = that.isSetGrantOption();
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
      hashCode = hashCode * 8191 + (this.isSetRequestType() ? 131071 : 524287);
      if (this.isSetRequestType()) {
         hashCode = hashCode * 8191 + this.requestType.getValue();
      }

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

      hashCode = hashCode * 8191 + (this.isSetGrantor() ? 131071 : 524287);
      if (this.isSetGrantor()) {
         hashCode = hashCode * 8191 + this.grantor.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetGrantorType() ? 131071 : 524287);
      if (this.isSetGrantorType()) {
         hashCode = hashCode * 8191 + this.grantorType.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetGrantOption() ? 131071 : 524287);
      if (this.isSetGrantOption()) {
         hashCode = hashCode * 8191 + (this.grantOption ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(GrantRevokeRoleRequest other) {
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
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GrantRevokeRoleRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GrantRevokeRoleRequest(");
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
      if (this.isSetGrantor()) {
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
      }

      if (this.isSetGrantorType()) {
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
      }

      if (this.isSetGrantOption()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("grantOption:");
         sb.append(this.grantOption);
         first = false;
      }

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
      optionals = new _Fields[]{GrantRevokeRoleRequest._Fields.GRANTOR, GrantRevokeRoleRequest._Fields.GRANTOR_TYPE, GrantRevokeRoleRequest._Fields.GRANT_OPTION};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GrantRevokeRoleRequest._Fields.REQUEST_TYPE, new FieldMetaData("requestType", (byte)3, new EnumMetaData((byte)16, GrantRevokeType.class)));
      tmpMap.put(GrantRevokeRoleRequest._Fields.ROLE_NAME, new FieldMetaData("roleName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(GrantRevokeRoleRequest._Fields.PRINCIPAL_NAME, new FieldMetaData("principalName", (byte)3, new FieldValueMetaData((byte)11)));
      tmpMap.put(GrantRevokeRoleRequest._Fields.PRINCIPAL_TYPE, new FieldMetaData("principalType", (byte)3, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(GrantRevokeRoleRequest._Fields.GRANTOR, new FieldMetaData("grantor", (byte)2, new FieldValueMetaData((byte)11)));
      tmpMap.put(GrantRevokeRoleRequest._Fields.GRANTOR_TYPE, new FieldMetaData("grantorType", (byte)2, new EnumMetaData((byte)16, PrincipalType.class)));
      tmpMap.put(GrantRevokeRoleRequest._Fields.GRANT_OPTION, new FieldMetaData("grantOption", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GrantRevokeRoleRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      REQUEST_TYPE((short)1, "requestType"),
      ROLE_NAME((short)2, "roleName"),
      PRINCIPAL_NAME((short)3, "principalName"),
      PRINCIPAL_TYPE((short)4, "principalType"),
      GRANTOR((short)5, "grantor"),
      GRANTOR_TYPE((short)6, "grantorType"),
      GRANT_OPTION((short)7, "grantOption");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return REQUEST_TYPE;
            case 2:
               return ROLE_NAME;
            case 3:
               return PRINCIPAL_NAME;
            case 4:
               return PRINCIPAL_TYPE;
            case 5:
               return GRANTOR;
            case 6:
               return GRANTOR_TYPE;
            case 7:
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

   private static class GrantRevokeRoleRequestStandardSchemeFactory implements SchemeFactory {
      private GrantRevokeRoleRequestStandardSchemeFactory() {
      }

      public GrantRevokeRoleRequestStandardScheme getScheme() {
         return new GrantRevokeRoleRequestStandardScheme();
      }
   }

   private static class GrantRevokeRoleRequestStandardScheme extends StandardScheme {
      private GrantRevokeRoleRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GrantRevokeRoleRequest struct) throws TException {
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
                  if (schemeField.type == 11) {
                     struct.roleName = iprot.readString();
                     struct.setRoleNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 11) {
                     struct.principalName = iprot.readString();
                     struct.setPrincipalNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.principalType = PrincipalType.findByValue(iprot.readI32());
                     struct.setPrincipalTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.grantor = iprot.readString();
                     struct.setGrantorIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 6:
                  if (schemeField.type == 8) {
                     struct.grantorType = PrincipalType.findByValue(iprot.readI32());
                     struct.setGrantorTypeIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 7:
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

      public void write(TProtocol oprot, GrantRevokeRoleRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GrantRevokeRoleRequest.STRUCT_DESC);
         if (struct.requestType != null) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.REQUEST_TYPE_FIELD_DESC);
            oprot.writeI32(struct.requestType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.roleName != null) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.ROLE_NAME_FIELD_DESC);
            oprot.writeString(struct.roleName);
            oprot.writeFieldEnd();
         }

         if (struct.principalName != null) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.PRINCIPAL_NAME_FIELD_DESC);
            oprot.writeString(struct.principalName);
            oprot.writeFieldEnd();
         }

         if (struct.principalType != null) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.PRINCIPAL_TYPE_FIELD_DESC);
            oprot.writeI32(struct.principalType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.grantor != null && struct.isSetGrantor()) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.GRANTOR_FIELD_DESC);
            oprot.writeString(struct.grantor);
            oprot.writeFieldEnd();
         }

         if (struct.grantorType != null && struct.isSetGrantorType()) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.GRANTOR_TYPE_FIELD_DESC);
            oprot.writeI32(struct.grantorType.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.isSetGrantOption()) {
            oprot.writeFieldBegin(GrantRevokeRoleRequest.GRANT_OPTION_FIELD_DESC);
            oprot.writeBool(struct.grantOption);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GrantRevokeRoleRequestTupleSchemeFactory implements SchemeFactory {
      private GrantRevokeRoleRequestTupleSchemeFactory() {
      }

      public GrantRevokeRoleRequestTupleScheme getScheme() {
         return new GrantRevokeRoleRequestTupleScheme();
      }
   }

   private static class GrantRevokeRoleRequestTupleScheme extends TupleScheme {
      private GrantRevokeRoleRequestTupleScheme() {
      }

      public void write(TProtocol prot, GrantRevokeRoleRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetRequestType()) {
            optionals.set(0);
         }

         if (struct.isSetRoleName()) {
            optionals.set(1);
         }

         if (struct.isSetPrincipalName()) {
            optionals.set(2);
         }

         if (struct.isSetPrincipalType()) {
            optionals.set(3);
         }

         if (struct.isSetGrantor()) {
            optionals.set(4);
         }

         if (struct.isSetGrantorType()) {
            optionals.set(5);
         }

         if (struct.isSetGrantOption()) {
            optionals.set(6);
         }

         oprot.writeBitSet(optionals, 7);
         if (struct.isSetRequestType()) {
            oprot.writeI32(struct.requestType.getValue());
         }

         if (struct.isSetRoleName()) {
            oprot.writeString(struct.roleName);
         }

         if (struct.isSetPrincipalName()) {
            oprot.writeString(struct.principalName);
         }

         if (struct.isSetPrincipalType()) {
            oprot.writeI32(struct.principalType.getValue());
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

      public void read(TProtocol prot, GrantRevokeRoleRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(7);
         if (incoming.get(0)) {
            struct.requestType = GrantRevokeType.findByValue(iprot.readI32());
            struct.setRequestTypeIsSet(true);
         }

         if (incoming.get(1)) {
            struct.roleName = iprot.readString();
            struct.setRoleNameIsSet(true);
         }

         if (incoming.get(2)) {
            struct.principalName = iprot.readString();
            struct.setPrincipalNameIsSet(true);
         }

         if (incoming.get(3)) {
            struct.principalType = PrincipalType.findByValue(iprot.readI32());
            struct.setPrincipalTypeIsSet(true);
         }

         if (incoming.get(4)) {
            struct.grantor = iprot.readString();
            struct.setGrantorIsSet(true);
         }

         if (incoming.get(5)) {
            struct.grantorType = PrincipalType.findByValue(iprot.readI32());
            struct.setGrantorTypeIsSet(true);
         }

         if (incoming.get(6)) {
            struct.grantOption = iprot.readBool();
            struct.setGrantOptionIsSet(true);
         }

      }
   }
}
