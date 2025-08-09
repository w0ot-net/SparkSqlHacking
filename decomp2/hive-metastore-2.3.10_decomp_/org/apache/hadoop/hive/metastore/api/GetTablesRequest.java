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

public class GetTablesRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetTablesRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("dbName", (byte)11, (short)1);
   private static final TField TBL_NAMES_FIELD_DESC = new TField("tblNames", (byte)15, (short)2);
   private static final TField CAPABILITIES_FIELD_DESC = new TField("capabilities", (byte)12, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetTablesRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetTablesRequestTupleSchemeFactory();
   @Nullable
   private String dbName;
   @Nullable
   private List tblNames;
   @Nullable
   private ClientCapabilities capabilities;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GetTablesRequest() {
   }

   public GetTablesRequest(String dbName) {
      this();
      this.dbName = dbName;
   }

   public GetTablesRequest(GetTablesRequest other) {
      if (other.isSetDbName()) {
         this.dbName = other.dbName;
      }

      if (other.isSetTblNames()) {
         List<String> __this__tblNames = new ArrayList(other.tblNames);
         this.tblNames = __this__tblNames;
      }

      if (other.isSetCapabilities()) {
         this.capabilities = new ClientCapabilities(other.capabilities);
      }

   }

   public GetTablesRequest deepCopy() {
      return new GetTablesRequest(this);
   }

   public void clear() {
      this.dbName = null;
      this.tblNames = null;
      this.capabilities = null;
   }

   @Nullable
   public String getDbName() {
      return this.dbName;
   }

   public void setDbName(@Nullable String dbName) {
      this.dbName = dbName;
   }

   public void unsetDbName() {
      this.dbName = null;
   }

   public boolean isSetDbName() {
      return this.dbName != null;
   }

   public void setDbNameIsSet(boolean value) {
      if (!value) {
         this.dbName = null;
      }

   }

   public int getTblNamesSize() {
      return this.tblNames == null ? 0 : this.tblNames.size();
   }

   @Nullable
   public Iterator getTblNamesIterator() {
      return this.tblNames == null ? null : this.tblNames.iterator();
   }

   public void addToTblNames(String elem) {
      if (this.tblNames == null) {
         this.tblNames = new ArrayList();
      }

      this.tblNames.add(elem);
   }

   @Nullable
   public List getTblNames() {
      return this.tblNames;
   }

   public void setTblNames(@Nullable List tblNames) {
      this.tblNames = tblNames;
   }

   public void unsetTblNames() {
      this.tblNames = null;
   }

   public boolean isSetTblNames() {
      return this.tblNames != null;
   }

   public void setTblNamesIsSet(boolean value) {
      if (!value) {
         this.tblNames = null;
      }

   }

   @Nullable
   public ClientCapabilities getCapabilities() {
      return this.capabilities;
   }

   public void setCapabilities(@Nullable ClientCapabilities capabilities) {
      this.capabilities = capabilities;
   }

   public void unsetCapabilities() {
      this.capabilities = null;
   }

   public boolean isSetCapabilities() {
      return this.capabilities != null;
   }

   public void setCapabilitiesIsSet(boolean value) {
      if (!value) {
         this.capabilities = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case DB_NAME:
            if (value == null) {
               this.unsetDbName();
            } else {
               this.setDbName((String)value);
            }
            break;
         case TBL_NAMES:
            if (value == null) {
               this.unsetTblNames();
            } else {
               this.setTblNames((List)value);
            }
            break;
         case CAPABILITIES:
            if (value == null) {
               this.unsetCapabilities();
            } else {
               this.setCapabilities((ClientCapabilities)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DB_NAME:
            return this.getDbName();
         case TBL_NAMES:
            return this.getTblNames();
         case CAPABILITIES:
            return this.getCapabilities();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case DB_NAME:
               return this.isSetDbName();
            case TBL_NAMES:
               return this.isSetTblNames();
            case CAPABILITIES:
               return this.isSetCapabilities();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetTablesRequest ? this.equals((GetTablesRequest)that) : false;
   }

   public boolean equals(GetTablesRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_dbName = this.isSetDbName();
         boolean that_present_dbName = that.isSetDbName();
         if (this_present_dbName || that_present_dbName) {
            if (!this_present_dbName || !that_present_dbName) {
               return false;
            }

            if (!this.dbName.equals(that.dbName)) {
               return false;
            }
         }

         boolean this_present_tblNames = this.isSetTblNames();
         boolean that_present_tblNames = that.isSetTblNames();
         if (this_present_tblNames || that_present_tblNames) {
            if (!this_present_tblNames || !that_present_tblNames) {
               return false;
            }

            if (!this.tblNames.equals(that.tblNames)) {
               return false;
            }
         }

         boolean this_present_capabilities = this.isSetCapabilities();
         boolean that_present_capabilities = that.isSetCapabilities();
         if (this_present_capabilities || that_present_capabilities) {
            if (!this_present_capabilities || !that_present_capabilities) {
               return false;
            }

            if (!this.capabilities.equals(that.capabilities)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetDbName() ? 131071 : 524287);
      if (this.isSetDbName()) {
         hashCode = hashCode * 8191 + this.dbName.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTblNames() ? 131071 : 524287);
      if (this.isSetTblNames()) {
         hashCode = hashCode * 8191 + this.tblNames.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetCapabilities() ? 131071 : 524287);
      if (this.isSetCapabilities()) {
         hashCode = hashCode * 8191 + this.capabilities.hashCode();
      }

      return hashCode;
   }

   public int compareTo(GetTablesRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetDbName(), other.isSetDbName());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetDbName()) {
               lastComparison = TBaseHelper.compareTo(this.dbName, other.dbName);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTblNames(), other.isSetTblNames());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTblNames()) {
                  lastComparison = TBaseHelper.compareTo(this.tblNames, other.tblNames);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetCapabilities(), other.isSetCapabilities());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetCapabilities()) {
                     lastComparison = TBaseHelper.compareTo(this.capabilities, other.capabilities);
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
      return GetTablesRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetTablesRequest(");
      boolean first = true;
      sb.append("dbName:");
      if (this.dbName == null) {
         sb.append("null");
      } else {
         sb.append(this.dbName);
      }

      first = false;
      if (this.isSetTblNames()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("tblNames:");
         if (this.tblNames == null) {
            sb.append("null");
         } else {
            sb.append(this.tblNames);
         }

         first = false;
      }

      if (this.isSetCapabilities()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("capabilities:");
         if (this.capabilities == null) {
            sb.append("null");
         } else {
            sb.append(this.capabilities);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDbName()) {
         throw new TProtocolException("Required field 'dbName' is unset! Struct:" + this.toString());
      } else {
         if (this.capabilities != null) {
            this.capabilities.validate();
         }

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
      optionals = new _Fields[]{GetTablesRequest._Fields.TBL_NAMES, GetTablesRequest._Fields.CAPABILITIES};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetTablesRequest._Fields.DB_NAME, new FieldMetaData("dbName", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(GetTablesRequest._Fields.TBL_NAMES, new FieldMetaData("tblNames", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
      tmpMap.put(GetTablesRequest._Fields.CAPABILITIES, new FieldMetaData("capabilities", (byte)2, new StructMetaData((byte)12, ClientCapabilities.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetTablesRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "dbName"),
      TBL_NAMES((short)2, "tblNames"),
      CAPABILITIES((short)3, "capabilities");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TBL_NAMES;
            case 3:
               return CAPABILITIES;
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

   private static class GetTablesRequestStandardSchemeFactory implements SchemeFactory {
      private GetTablesRequestStandardSchemeFactory() {
      }

      public GetTablesRequestStandardScheme getScheme() {
         return new GetTablesRequestStandardScheme();
      }
   }

   private static class GetTablesRequestStandardScheme extends StandardScheme {
      private GetTablesRequestStandardScheme() {
      }

      public void read(TProtocol iprot, GetTablesRequest struct) throws TException {
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
                     struct.dbName = iprot.readString();
                     struct.setDbNameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list690 = iprot.readListBegin();
                  struct.tblNames = new ArrayList(_list690.size);

                  for(int _i692 = 0; _i692 < _list690.size; ++_i692) {
                     String _elem691 = iprot.readString();
                     struct.tblNames.add(_elem691);
                  }

                  iprot.readListEnd();
                  struct.setTblNamesIsSet(true);
                  break;
               case 3:
                  if (schemeField.type == 12) {
                     struct.capabilities = new ClientCapabilities();
                     struct.capabilities.read(iprot);
                     struct.setCapabilitiesIsSet(true);
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

      public void write(TProtocol oprot, GetTablesRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetTablesRequest.STRUCT_DESC);
         if (struct.dbName != null) {
            oprot.writeFieldBegin(GetTablesRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.dbName);
            oprot.writeFieldEnd();
         }

         if (struct.tblNames != null && struct.isSetTblNames()) {
            oprot.writeFieldBegin(GetTablesRequest.TBL_NAMES_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)11, struct.tblNames.size()));

            for(String _iter693 : struct.tblNames) {
               oprot.writeString(_iter693);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.capabilities != null && struct.isSetCapabilities()) {
            oprot.writeFieldBegin(GetTablesRequest.CAPABILITIES_FIELD_DESC);
            struct.capabilities.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetTablesRequestTupleSchemeFactory implements SchemeFactory {
      private GetTablesRequestTupleSchemeFactory() {
      }

      public GetTablesRequestTupleScheme getScheme() {
         return new GetTablesRequestTupleScheme();
      }
   }

   private static class GetTablesRequestTupleScheme extends TupleScheme {
      private GetTablesRequestTupleScheme() {
      }

      public void write(TProtocol prot, GetTablesRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.dbName);
         BitSet optionals = new BitSet();
         if (struct.isSetTblNames()) {
            optionals.set(0);
         }

         if (struct.isSetCapabilities()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetTblNames()) {
            oprot.writeI32(struct.tblNames.size());

            for(String _iter694 : struct.tblNames) {
               oprot.writeString(_iter694);
            }
         }

         if (struct.isSetCapabilities()) {
            struct.capabilities.write(oprot);
         }

      }

      public void read(TProtocol prot, GetTablesRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.dbName = iprot.readString();
         struct.setDbNameIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            TList _list695 = iprot.readListBegin((byte)11);
            struct.tblNames = new ArrayList(_list695.size);

            for(int _i697 = 0; _i697 < _list695.size; ++_i697) {
               String _elem696 = iprot.readString();
               struct.tblNames.add(_elem696);
            }

            struct.setTblNamesIsSet(true);
         }

         if (incoming.get(1)) {
            struct.capabilities = new ClientCapabilities();
            struct.capabilities.read(iprot);
            struct.setCapabilitiesIsSet(true);
         }

      }
   }
}
