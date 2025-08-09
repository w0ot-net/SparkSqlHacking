package org.apache.hadoop.hive.metastore.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
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

public class GetRoleGrantsForPrincipalResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetRoleGrantsForPrincipalResponse");
   private static final TField PRINCIPAL_GRANTS_FIELD_DESC = new TField("principalGrants", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetRoleGrantsForPrincipalResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetRoleGrantsForPrincipalResponseTupleSchemeFactory();
   @Nullable
   private List principalGrants;
   public static final Map metaDataMap;

   public GetRoleGrantsForPrincipalResponse() {
   }

   public GetRoleGrantsForPrincipalResponse(List principalGrants) {
      this();
      this.principalGrants = principalGrants;
   }

   public GetRoleGrantsForPrincipalResponse(GetRoleGrantsForPrincipalResponse other) {
      if (other.isSetPrincipalGrants()) {
         List<RolePrincipalGrant> __this__principalGrants = new ArrayList(other.principalGrants.size());

         for(RolePrincipalGrant other_element : other.principalGrants) {
            __this__principalGrants.add(new RolePrincipalGrant(other_element));
         }

         this.principalGrants = __this__principalGrants;
      }

   }

   public GetRoleGrantsForPrincipalResponse deepCopy() {
      return new GetRoleGrantsForPrincipalResponse(this);
   }

   public void clear() {
      this.principalGrants = null;
   }

   public int getPrincipalGrantsSize() {
      return this.principalGrants == null ? 0 : this.principalGrants.size();
   }

   @Nullable
   public Iterator getPrincipalGrantsIterator() {
      return this.principalGrants == null ? null : this.principalGrants.iterator();
   }

   public void addToPrincipalGrants(RolePrincipalGrant elem) {
      if (this.principalGrants == null) {
         this.principalGrants = new ArrayList();
      }

      this.principalGrants.add(elem);
   }

   @Nullable
   public List getPrincipalGrants() {
      return this.principalGrants;
   }

   public void setPrincipalGrants(@Nullable List principalGrants) {
      this.principalGrants = principalGrants;
   }

   public void unsetPrincipalGrants() {
      this.principalGrants = null;
   }

   public boolean isSetPrincipalGrants() {
      return this.principalGrants != null;
   }

   public void setPrincipalGrantsIsSet(boolean value) {
      if (!value) {
         this.principalGrants = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case PRINCIPAL_GRANTS:
            if (value == null) {
               this.unsetPrincipalGrants();
            } else {
               this.setPrincipalGrants((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case PRINCIPAL_GRANTS:
            return this.getPrincipalGrants();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case PRINCIPAL_GRANTS:
               return this.isSetPrincipalGrants();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetRoleGrantsForPrincipalResponse ? this.equals((GetRoleGrantsForPrincipalResponse)that) : false;
   }

   public boolean equals(GetRoleGrantsForPrincipalResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_principalGrants = this.isSetPrincipalGrants();
         boolean that_present_principalGrants = that.isSetPrincipalGrants();
         if (this_present_principalGrants || that_present_principalGrants) {
            if (!this_present_principalGrants || !that_present_principalGrants) {
               return false;
            }

            if (!this.principalGrants.equals(that.principalGrants)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetPrincipalGrants() ? 131071 : 524287);
      if (this.isSetPrincipalGrants()) {
         hashCode = hashCode * 8191 + this.principalGrants.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetRoleGrantsForPrincipalResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetPrincipalGrants(), other.isSetPrincipalGrants());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetPrincipalGrants()) {
               lastComparison = TBaseHelper.compareTo(this.principalGrants, other.principalGrants);
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
      return GetRoleGrantsForPrincipalResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetRoleGrantsForPrincipalResponse(");
      boolean first = true;
      sb.append("principalGrants:");
      if (this.principalGrants == null) {
         sb.append("null");
      } else {
         sb.append(this.principalGrants);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetPrincipalGrants()) {
         throw new TProtocolException("Required field 'principalGrants' is unset! Struct:" + this.toString());
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
      tmpMap.put(GetRoleGrantsForPrincipalResponse._Fields.PRINCIPAL_GRANTS, new FieldMetaData("principalGrants", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, RolePrincipalGrant.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetRoleGrantsForPrincipalResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      PRINCIPAL_GRANTS((short)1, "principalGrants");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return PRINCIPAL_GRANTS;
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

   private static class GetRoleGrantsForPrincipalResponseStandardSchemeFactory implements SchemeFactory {
      private GetRoleGrantsForPrincipalResponseStandardSchemeFactory() {
      }

      public GetRoleGrantsForPrincipalResponseStandardScheme getScheme() {
         return new GetRoleGrantsForPrincipalResponseStandardScheme();
      }
   }

   private static class GetRoleGrantsForPrincipalResponseStandardScheme extends StandardScheme {
      private GetRoleGrantsForPrincipalResponseStandardScheme() {
      }

      public void read(TProtocol iprot, GetRoleGrantsForPrincipalResponse struct) throws TException {
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

                  TList _list78 = iprot.readListBegin();
                  struct.principalGrants = new ArrayList(_list78.size);

                  for(int _i80 = 0; _i80 < _list78.size; ++_i80) {
                     RolePrincipalGrant _elem79 = new RolePrincipalGrant();
                     _elem79.read(iprot);
                     struct.principalGrants.add(_elem79);
                  }

                  iprot.readListEnd();
                  struct.setPrincipalGrantsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, GetRoleGrantsForPrincipalResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetRoleGrantsForPrincipalResponse.STRUCT_DESC);
         if (struct.principalGrants != null) {
            oprot.writeFieldBegin(GetRoleGrantsForPrincipalResponse.PRINCIPAL_GRANTS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.principalGrants.size()));

            for(RolePrincipalGrant _iter81 : struct.principalGrants) {
               _iter81.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetRoleGrantsForPrincipalResponseTupleSchemeFactory implements SchemeFactory {
      private GetRoleGrantsForPrincipalResponseTupleSchemeFactory() {
      }

      public GetRoleGrantsForPrincipalResponseTupleScheme getScheme() {
         return new GetRoleGrantsForPrincipalResponseTupleScheme();
      }
   }

   private static class GetRoleGrantsForPrincipalResponseTupleScheme extends TupleScheme {
      private GetRoleGrantsForPrincipalResponseTupleScheme() {
      }

      public void write(TProtocol prot, GetRoleGrantsForPrincipalResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.principalGrants.size());

         for(RolePrincipalGrant _iter82 : struct.principalGrants) {
            _iter82.write(oprot);
         }

      }

      public void read(TProtocol prot, GetRoleGrantsForPrincipalResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list83 = iprot.readListBegin((byte)12);
         struct.principalGrants = new ArrayList(_list83.size);

         for(int _i85 = 0; _i85 < _list83.size; ++_i85) {
            RolePrincipalGrant _elem84 = new RolePrincipalGrant();
            _elem84.read(iprot);
            struct.principalGrants.add(_elem84);
         }

         struct.setPrincipalGrantsIsSet(true);
      }
   }
}
