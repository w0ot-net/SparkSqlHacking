package org.apache.hive.service.rpc.thrift;

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
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.StructMetaData;
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

@Public
@Stable
public class TGetResultSetMetadataResp implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TGetResultSetMetadataResp");
   private static final TField STATUS_FIELD_DESC = new TField("status", (byte)12, (short)1);
   private static final TField SCHEMA_FIELD_DESC = new TField("schema", (byte)12, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TGetResultSetMetadataRespStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TGetResultSetMetadataRespTupleSchemeFactory();
   @Nullable
   private TStatus status;
   @Nullable
   private TTableSchema schema;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public TGetResultSetMetadataResp() {
   }

   public TGetResultSetMetadataResp(TStatus status) {
      this();
      this.status = status;
   }

   public TGetResultSetMetadataResp(TGetResultSetMetadataResp other) {
      if (other.isSetStatus()) {
         this.status = new TStatus(other.status);
      }

      if (other.isSetSchema()) {
         this.schema = new TTableSchema(other.schema);
      }

   }

   public TGetResultSetMetadataResp deepCopy() {
      return new TGetResultSetMetadataResp(this);
   }

   public void clear() {
      this.status = null;
      this.schema = null;
   }

   @Nullable
   public TStatus getStatus() {
      return this.status;
   }

   public void setStatus(@Nullable TStatus status) {
      this.status = status;
   }

   public void unsetStatus() {
      this.status = null;
   }

   public boolean isSetStatus() {
      return this.status != null;
   }

   public void setStatusIsSet(boolean value) {
      if (!value) {
         this.status = null;
      }

   }

   @Nullable
   public TTableSchema getSchema() {
      return this.schema;
   }

   public void setSchema(@Nullable TTableSchema schema) {
      this.schema = schema;
   }

   public void unsetSchema() {
      this.schema = null;
   }

   public boolean isSetSchema() {
      return this.schema != null;
   }

   public void setSchemaIsSet(boolean value) {
      if (!value) {
         this.schema = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case STATUS:
            if (value == null) {
               this.unsetStatus();
            } else {
               this.setStatus((TStatus)value);
            }
            break;
         case SCHEMA:
            if (value == null) {
               this.unsetSchema();
            } else {
               this.setSchema((TTableSchema)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case STATUS:
            return this.getStatus();
         case SCHEMA:
            return this.getSchema();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case STATUS:
               return this.isSetStatus();
            case SCHEMA:
               return this.isSetSchema();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TGetResultSetMetadataResp ? this.equals((TGetResultSetMetadataResp)that) : false;
   }

   public boolean equals(TGetResultSetMetadataResp that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_status = this.isSetStatus();
         boolean that_present_status = that.isSetStatus();
         if (this_present_status || that_present_status) {
            if (!this_present_status || !that_present_status) {
               return false;
            }

            if (!this.status.equals(that.status)) {
               return false;
            }
         }

         boolean this_present_schema = this.isSetSchema();
         boolean that_present_schema = that.isSetSchema();
         if (this_present_schema || that_present_schema) {
            if (!this_present_schema || !that_present_schema) {
               return false;
            }

            if (!this.schema.equals(that.schema)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetStatus() ? 131071 : 524287);
      if (this.isSetStatus()) {
         hashCode = hashCode * 8191 + this.status.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetSchema() ? 131071 : 524287);
      if (this.isSetSchema()) {
         hashCode = hashCode * 8191 + this.schema.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TGetResultSetMetadataResp other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetStatus(), other.isSetStatus());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetStatus()) {
               lastComparison = TBaseHelper.compareTo(this.status, other.status);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetSchema(), other.isSetSchema());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetSchema()) {
                  lastComparison = TBaseHelper.compareTo(this.schema, other.schema);
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
      return TGetResultSetMetadataResp._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TGetResultSetMetadataResp(");
      boolean first = true;
      sb.append("status:");
      if (this.status == null) {
         sb.append("null");
      } else {
         sb.append(this.status);
      }

      first = false;
      if (this.isSetSchema()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("schema:");
         if (this.schema == null) {
            sb.append("null");
         } else {
            sb.append(this.schema);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetStatus()) {
         throw new TProtocolException("Required field 'status' is unset! Struct:" + this.toString());
      } else {
         if (this.status != null) {
            this.status.validate();
         }

         if (this.schema != null) {
            this.schema.validate();
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
      optionals = new _Fields[]{TGetResultSetMetadataResp._Fields.SCHEMA};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(TGetResultSetMetadataResp._Fields.STATUS, new FieldMetaData("status", (byte)1, new StructMetaData((byte)12, TStatus.class)));
      tmpMap.put(TGetResultSetMetadataResp._Fields.SCHEMA, new FieldMetaData("schema", (byte)2, new StructMetaData((byte)12, TTableSchema.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TGetResultSetMetadataResp.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STATUS((short)1, "status"),
      SCHEMA((short)2, "schema");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STATUS;
            case 2:
               return SCHEMA;
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

   private static class TGetResultSetMetadataRespStandardSchemeFactory implements SchemeFactory {
      private TGetResultSetMetadataRespStandardSchemeFactory() {
      }

      public TGetResultSetMetadataRespStandardScheme getScheme() {
         return new TGetResultSetMetadataRespStandardScheme();
      }
   }

   private static class TGetResultSetMetadataRespStandardScheme extends StandardScheme {
      private TGetResultSetMetadataRespStandardScheme() {
      }

      public void read(TProtocol iprot, TGetResultSetMetadataResp struct) throws TException {
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
                     struct.status = new TStatus();
                     struct.status.read(iprot);
                     struct.setStatusIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 12) {
                     struct.schema = new TTableSchema();
                     struct.schema.read(iprot);
                     struct.setSchemaIsSet(true);
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

      public void write(TProtocol oprot, TGetResultSetMetadataResp struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TGetResultSetMetadataResp.STRUCT_DESC);
         if (struct.status != null) {
            oprot.writeFieldBegin(TGetResultSetMetadataResp.STATUS_FIELD_DESC);
            struct.status.write(oprot);
            oprot.writeFieldEnd();
         }

         if (struct.schema != null && struct.isSetSchema()) {
            oprot.writeFieldBegin(TGetResultSetMetadataResp.SCHEMA_FIELD_DESC);
            struct.schema.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TGetResultSetMetadataRespTupleSchemeFactory implements SchemeFactory {
      private TGetResultSetMetadataRespTupleSchemeFactory() {
      }

      public TGetResultSetMetadataRespTupleScheme getScheme() {
         return new TGetResultSetMetadataRespTupleScheme();
      }
   }

   private static class TGetResultSetMetadataRespTupleScheme extends TupleScheme {
      private TGetResultSetMetadataRespTupleScheme() {
      }

      public void write(TProtocol prot, TGetResultSetMetadataResp struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.status.write(oprot);
         BitSet optionals = new BitSet();
         if (struct.isSetSchema()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetSchema()) {
            struct.schema.write(oprot);
         }

      }

      public void read(TProtocol prot, TGetResultSetMetadataResp struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.status = new TStatus();
         struct.status.read(iprot);
         struct.setStatusIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.schema = new TTableSchema();
            struct.schema.read(iprot);
            struct.setSchemaIsSet(true);
         }

      }
   }
}
