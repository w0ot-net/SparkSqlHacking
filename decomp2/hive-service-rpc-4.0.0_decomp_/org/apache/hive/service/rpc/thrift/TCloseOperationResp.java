package org.apache.hive.service.rpc.thrift;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class TCloseOperationResp implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("TCloseOperationResp");
   private static final TField STATUS_FIELD_DESC = new TField("status", (byte)12, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new TCloseOperationRespStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new TCloseOperationRespTupleSchemeFactory();
   @Nullable
   private TStatus status;
   public static final Map metaDataMap;

   public TCloseOperationResp() {
   }

   public TCloseOperationResp(TStatus status) {
      this();
      this.status = status;
   }

   public TCloseOperationResp(TCloseOperationResp other) {
      if (other.isSetStatus()) {
         this.status = new TStatus(other.status);
      }

   }

   public TCloseOperationResp deepCopy() {
      return new TCloseOperationResp(this);
   }

   public void clear() {
      this.status = null;
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

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case STATUS:
            if (value == null) {
               this.unsetStatus();
            } else {
               this.setStatus((TStatus)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case STATUS:
            return this.getStatus();
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
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof TCloseOperationResp ? this.equals((TCloseOperationResp)that) : false;
   }

   public boolean equals(TCloseOperationResp that) {
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

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetStatus() ? 131071 : 524287);
      if (this.isSetStatus()) {
         hashCode = hashCode * 8191 + this.status.hashCode();
      }

      return hashCode;
   }

   public int compareTo(TCloseOperationResp other) {
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

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return TCloseOperationResp._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("TCloseOperationResp(");
      boolean first = true;
      sb.append("status:");
      if (this.status == null) {
         sb.append("null");
      } else {
         sb.append(this.status);
      }

      first = false;
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
      tmpMap.put(TCloseOperationResp._Fields.STATUS, new FieldMetaData("status", (byte)1, new StructMetaData((byte)12, TStatus.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(TCloseOperationResp.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      STATUS((short)1, "status");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return STATUS;
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

   private static class TCloseOperationRespStandardSchemeFactory implements SchemeFactory {
      private TCloseOperationRespStandardSchemeFactory() {
      }

      public TCloseOperationRespStandardScheme getScheme() {
         return new TCloseOperationRespStandardScheme();
      }
   }

   private static class TCloseOperationRespStandardScheme extends StandardScheme {
      private TCloseOperationRespStandardScheme() {
      }

      public void read(TProtocol iprot, TCloseOperationResp struct) throws TException {
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
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, TCloseOperationResp struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(TCloseOperationResp.STRUCT_DESC);
         if (struct.status != null) {
            oprot.writeFieldBegin(TCloseOperationResp.STATUS_FIELD_DESC);
            struct.status.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class TCloseOperationRespTupleSchemeFactory implements SchemeFactory {
      private TCloseOperationRespTupleSchemeFactory() {
      }

      public TCloseOperationRespTupleScheme getScheme() {
         return new TCloseOperationRespTupleScheme();
      }
   }

   private static class TCloseOperationRespTupleScheme extends TupleScheme {
      private TCloseOperationRespTupleScheme() {
      }

      public void write(TProtocol prot, TCloseOperationResp struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         struct.status.write(oprot);
      }

      public void read(TProtocol prot, TCloseOperationResp struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.status = new TStatus();
         struct.status.read(iprot);
         struct.setStatusIsSet(true);
      }
   }
}
