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
import org.apache.thrift.meta_data.EnumMetaData;
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

public class GetRoleGrantsForPrincipalRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetRoleGrantsForPrincipalRequest");
   private static final TField PRINCIPAL_NAME_FIELD_DESC = new TField("principal_name", (byte)11, (short)1);
   private static final TField PRINCIPAL_TYPE_FIELD_DESC = new TField("principal_type", (byte)8, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetRoleGrantsForPrincipalRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetRoleGrantsForPrincipalRequestTupleSchemeFactory();
   @Nullable
   private String principal_name;
   @Nullable
   private PrincipalType principal_type;
   public static final Map metaDataMap;

   public GetRoleGrantsForPrincipalRequest() {
   }

   public GetRoleGrantsForPrincipalRequest(String principal_name, PrincipalType principal_type) {
      this();
      this.principal_name = principal_name;
      this.principal_type = principal_type;
   }

   public GetRoleGrantsForPrincipalRequest(GetRoleGrantsForPrincipalRequest other) {
      if (other.isSetPrincipal_name()) {
         this.principal_name = other.principal_name;
      }

      if (other.isSetPrincipal_type()) {
         this.principal_type = other.principal_type;
      }

   }

   public GetRoleGrantsForPrincipalRequest deepCopy() {
      return new GetRoleGrantsForPrincipalRequest(this);
   }

   public void clear() {
      this.principal_name = null;
      this.principal_type = null;
   }

   @Nullable
   public String getPrincipal_name() {
      return this.principal_name;
   }

   public void setPrincipal_name(@Nullable String principal_name) {
      this.principal_name = principal_name;
   }

   public void unsetPrincipal_name() {
      this.principal_name = null;
   }

   public boolean isSetPrincipal_name() {
      return this.principal_name != null;
   }

   public void setPrincipal_nameIsSet(boolean value) {
      if (!value) {
         this.principal_name = null;
      }

   }

   @Nullable
   public PrincipalType getPrincipal_type() {
      return this.principal_type;
   }

   public void setPrincipal_type(@Nullable PrincipalType principal_type) {
      this.principal_type = principal_type;
   }

   public void unsetPrincipal_type() {
      this.principal_type = null;
   }

   public boolean isSetPrincipal_type() {
      return this.principal_type != null;
   }

   public void setPrincipal_typeIsSet(boolean value) {
      if (!value) {
         this.principal_type = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PRINCIPAL_NAME:
            if (value == null) {
               this.unsetPrincipal_name();
            } else {
               this.setPrincipal_name((String)value);
            }
            break;
         case PRINCIPAL_TYPE:
            if (value == null) {
               this.unsetPrincipal_type();
            } else {
               this.setPrincipal_type((PrincipalType)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PRINCIPAL_NAME:
            return this.getPrincipal_name();
         case PRINCIPAL_TYPE:
            return this.getPrincipal_type();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PRINCIPAL_NAME:
               return this.isSetPrincipal_name();
            case PRINCIPAL_TYPE:
               return this.isSetPrincipal_type();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetRoleGrantsForPrincipalRequest ? this.equals((GetRoleGrantsForPrincipalRequest)that) : false;
   }

   public boolean equals(GetRoleGrantsForPrincipalRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_principal_name = this.isSetPrincipal_name();
         boolean that_present_principal_name = that.isSetPrincipal_name();
         if (this_present_principal_name || that_present_principal_name) {
            if (!this_present_principal_name || !that_present_principal_name) {
               return false;
            }

            if (!this.principal_name.equals(that.principal_name)) {
               return false;
            }
         }

         boolean this_present_principal_type = this.isSetPrincipal_type();
         boolean that_present_principal_type = that.isSetPrincipal_type();
         if (this_present_principal_type || that_present_principal_type) {
            if (!this_present_principal_type || !that_present_principal_type) {
               return false;
            }

            if (!this.principal_type.equals(that.principal_type)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPrincipal_name() ? 131071 : 524287);
      if (this.isSetPrincipal_name()) {
         hashCode = hashCode * 8191 + this.principal_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetPrincipal_type() ? 131071 : 524287);
      if (this.isSetPrincipal_type()) {
         hashCode = hashCode * 8191 + this.principal_type.getValue();
      }

      return hashCode;
   }

   public int compareTo(GetRoleGrantsForPrincipalRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPrincipal_name(), other.isSetPrincipal_name());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPrincipal_name()) {
               lastComparison = TBaseHelper.compareTo(this.principal_name, other.principal_name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetPrincipal_type(), other.isSetPrincipal_type());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetPrincipal_type()) {
                  lastComparison = TBaseHelper.compareTo(this.principal_type, other.principal_type);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GetRoleGrantsForPrincipalRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetRoleGrantsForPrincipalRequest(");
      boolean first = true;
      sb.append("principal_name:");
      if (this.principal_name == null) {
         sb.append("null");
      } else {
         sb.append(this.principal_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("principal_type:");
      if (this.principal_type == null) {
         sb.append("null");
      } else {
         sb.append(this.principal_type);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPrincipal_name()) {
         throw new TProtocolException("Required field 'principal_name' is unset! Struct:" + this.toString());
      } else if (!this.isSetPrincipal_type()) {
         throw new TProtocolException("Required field 'principal_type' is unset! Struct:" + this.toString());
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
      tmpMap.put(GetRoleGrantsForPrincipalRequest._Fields.PRINCIPAL_NAME, new FieldMetaData("principal_name", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(GetRoleGrantsForPrincipalRequest._Fields.PRINCIPAL_TYPE, new FieldMetaData("principal_type", (byte)1, new EnumMetaData((byte)16, PrincipalType.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetRoleGrantsForPrincipalRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRINCIPAL_NAME((short)1, "principal_name"),
      PRINCIPAL_TYPE((short)2, "principal_type");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRINCIPAL_NAME;
            case 2:
               return PRINCIPAL_TYPE;
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

   private static class GetRoleGrantsForPrincipalRequestStandardSchemeFactory implements SchemeFactory {
      private GetRoleGrantsForPrincipalRequestStandardSchemeFactory() {
      }

      public GetRoleGrantsForPrincipalRequestStandardScheme getScheme() {
         return new GetRoleGrantsForPrincipalRequestStandardScheme();
      }
   }

   private static class GetRoleGrantsForPrincipalRequestStandardScheme extends StandardScheme {
      private GetRoleGrantsForPrincipalRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GetRoleGrantsForPrincipalRequest struct) throws TException {
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
                     struct.principal_name = iprot.readString();
                     struct.setPrincipal_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.principal_type = PrincipalType.findByValue(iprot.readI32());
                     struct.setPrincipal_typeIsSet(true);
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

      public void write(TProtocol oprot, GetRoleGrantsForPrincipalRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetRoleGrantsForPrincipalRequest.STRUCT_DESC);
         if (struct.principal_name != null) {
            oprot.writeFieldBegin(GetRoleGrantsForPrincipalRequest.PRINCIPAL_NAME_FIELD_DESC);
            oprot.writeString(struct.principal_name);
            oprot.writeFieldEnd();
         }

         if (struct.principal_type != null) {
            oprot.writeFieldBegin(GetRoleGrantsForPrincipalRequest.PRINCIPAL_TYPE_FIELD_DESC);
            oprot.writeI32(struct.principal_type.getValue());
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetRoleGrantsForPrincipalRequestTupleSchemeFactory implements SchemeFactory {
      private GetRoleGrantsForPrincipalRequestTupleSchemeFactory() {
      }

      public GetRoleGrantsForPrincipalRequestTupleScheme getScheme() {
         return new GetRoleGrantsForPrincipalRequestTupleScheme();
      }
   }

   private static class GetRoleGrantsForPrincipalRequestTupleScheme extends TupleScheme {
      private GetRoleGrantsForPrincipalRequestTupleScheme() {
      }

      public void write(TProtocol prot, GetRoleGrantsForPrincipalRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.principal_name);
         oprot.writeI32(struct.principal_type.getValue());
      }

      public void read(TProtocol prot, GetRoleGrantsForPrincipalRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.principal_name = iprot.readString();
         struct.setPrincipal_nameIsSet(true);
         struct.principal_type = PrincipalType.findByValue(iprot.readI32());
         struct.setPrincipal_typeIsSet(true);
      }
   }
}
