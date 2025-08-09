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

public class PrimaryKeysRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("PrimaryKeysRequest");
   private static final TField DB_NAME_FIELD_DESC = new TField("db_name", (byte)11, (short)1);
   private static final TField TBL_NAME_FIELD_DESC = new TField("tbl_name", (byte)11, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new PrimaryKeysRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new PrimaryKeysRequestTupleSchemeFactory();
   @Nullable
   private String db_name;
   @Nullable
   private String tbl_name;
   public static final Map metaDataMap;

   public PrimaryKeysRequest() {
   }

   public PrimaryKeysRequest(String db_name, String tbl_name) {
      this();
      this.db_name = db_name;
      this.tbl_name = tbl_name;
   }

   public PrimaryKeysRequest(PrimaryKeysRequest other) {
      if (other.isSetDb_name()) {
         this.db_name = other.db_name;
      }

      if (other.isSetTbl_name()) {
         this.tbl_name = other.tbl_name;
      }

   }

   public PrimaryKeysRequest deepCopy() {
      return new PrimaryKeysRequest(this);
   }

   public void clear() {
      this.db_name = null;
      this.tbl_name = null;
   }

   @Nullable
   public String getDb_name() {
      return this.db_name;
   }

   public void setDb_name(@Nullable String db_name) {
      this.db_name = db_name;
   }

   public void unsetDb_name() {
      this.db_name = null;
   }

   public boolean isSetDb_name() {
      return this.db_name != null;
   }

   public void setDb_nameIsSet(boolean value) {
      if (!value) {
         this.db_name = null;
      }

   }

   @Nullable
   public String getTbl_name() {
      return this.tbl_name;
   }

   public void setTbl_name(@Nullable String tbl_name) {
      this.tbl_name = tbl_name;
   }

   public void unsetTbl_name() {
      this.tbl_name = null;
   }

   public boolean isSetTbl_name() {
      return this.tbl_name != null;
   }

   public void setTbl_nameIsSet(boolean value) {
      if (!value) {
         this.tbl_name = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case DB_NAME:
            if (value == null) {
               this.unsetDb_name();
            } else {
               this.setDb_name((String)value);
            }
            break;
         case TBL_NAME:
            if (value == null) {
               this.unsetTbl_name();
            } else {
               this.setTbl_name((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DB_NAME:
            return this.getDb_name();
         case TBL_NAME:
            return this.getTbl_name();
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
               return this.isSetDb_name();
            case TBL_NAME:
               return this.isSetTbl_name();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof PrimaryKeysRequest ? this.equals((PrimaryKeysRequest)that) : false;
   }

   public boolean equals(PrimaryKeysRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_db_name = this.isSetDb_name();
         boolean that_present_db_name = that.isSetDb_name();
         if (this_present_db_name || that_present_db_name) {
            if (!this_present_db_name || !that_present_db_name) {
               return false;
            }

            if (!this.db_name.equals(that.db_name)) {
               return false;
            }
         }

         boolean this_present_tbl_name = this.isSetTbl_name();
         boolean that_present_tbl_name = that.isSetTbl_name();
         if (this_present_tbl_name || that_present_tbl_name) {
            if (!this_present_tbl_name || !that_present_tbl_name) {
               return false;
            }

            if (!this.tbl_name.equals(that.tbl_name)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetDb_name() ? 131071 : 524287);
      if (this.isSetDb_name()) {
         hashCode = hashCode * 8191 + this.db_name.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetTbl_name() ? 131071 : 524287);
      if (this.isSetTbl_name()) {
         hashCode = hashCode * 8191 + this.tbl_name.hashCode();
      }

      return hashCode;
   }

   public int compareTo(PrimaryKeysRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetDb_name(), other.isSetDb_name());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetDb_name()) {
               lastComparison = TBaseHelper.compareTo(this.db_name, other.db_name);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetTbl_name(), other.isSetTbl_name());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTbl_name()) {
                  lastComparison = TBaseHelper.compareTo(this.tbl_name, other.tbl_name);
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
      return PrimaryKeysRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("PrimaryKeysRequest(");
      boolean first = true;
      sb.append("db_name:");
      if (this.db_name == null) {
         sb.append("null");
      } else {
         sb.append(this.db_name);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("tbl_name:");
      if (this.tbl_name == null) {
         sb.append("null");
      } else {
         sb.append(this.tbl_name);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDb_name()) {
         throw new TProtocolException("Required field 'db_name' is unset! Struct:" + this.toString());
      } else if (!this.isSetTbl_name()) {
         throw new TProtocolException("Required field 'tbl_name' is unset! Struct:" + this.toString());
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
      tmpMap.put(PrimaryKeysRequest._Fields.DB_NAME, new FieldMetaData("db_name", (byte)1, new FieldValueMetaData((byte)11)));
      tmpMap.put(PrimaryKeysRequest._Fields.TBL_NAME, new FieldMetaData("tbl_name", (byte)1, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(PrimaryKeysRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DB_NAME((short)1, "db_name"),
      TBL_NAME((short)2, "tbl_name");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DB_NAME;
            case 2:
               return TBL_NAME;
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

   private static class PrimaryKeysRequestStandardSchemeFactory implements SchemeFactory {
      private PrimaryKeysRequestStandardSchemeFactory() {
      }

      public PrimaryKeysRequestStandardScheme getScheme() {
         return new PrimaryKeysRequestStandardScheme();
      }
   }

   private static class PrimaryKeysRequestStandardScheme extends StandardScheme {
      private PrimaryKeysRequestStandardScheme() {
      }

      public void read(TProtocol iprot, PrimaryKeysRequest struct) throws TException {
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
                     struct.db_name = iprot.readString();
                     struct.setDb_nameIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 11) {
                     struct.tbl_name = iprot.readString();
                     struct.setTbl_nameIsSet(true);
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

      public void write(TProtocol oprot, PrimaryKeysRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(PrimaryKeysRequest.STRUCT_DESC);
         if (struct.db_name != null) {
            oprot.writeFieldBegin(PrimaryKeysRequest.DB_NAME_FIELD_DESC);
            oprot.writeString(struct.db_name);
            oprot.writeFieldEnd();
         }

         if (struct.tbl_name != null) {
            oprot.writeFieldBegin(PrimaryKeysRequest.TBL_NAME_FIELD_DESC);
            oprot.writeString(struct.tbl_name);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class PrimaryKeysRequestTupleSchemeFactory implements SchemeFactory {
      private PrimaryKeysRequestTupleSchemeFactory() {
      }

      public PrimaryKeysRequestTupleScheme getScheme() {
         return new PrimaryKeysRequestTupleScheme();
      }
   }

   private static class PrimaryKeysRequestTupleScheme extends TupleScheme {
      private PrimaryKeysRequestTupleScheme() {
      }

      public void write(TProtocol prot, PrimaryKeysRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeString(struct.db_name);
         oprot.writeString(struct.tbl_name);
      }

      public void read(TProtocol prot, PrimaryKeysRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.db_name = iprot.readString();
         struct.setDb_nameIsSet(true);
         struct.tbl_name = iprot.readString();
         struct.setTbl_nameIsSet(true);
      }
   }
}
