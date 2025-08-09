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

public class GrantRevokePrivilegeResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GrantRevokePrivilegeResponse");
   private static final TField SUCCESS_FIELD_DESC = new TField("success", (byte)2, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GrantRevokePrivilegeResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GrantRevokePrivilegeResponseTupleSchemeFactory();
   private boolean success;
   private static final int __SUCCESS_ISSET_ID = 0;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GrantRevokePrivilegeResponse() {
   }

   public GrantRevokePrivilegeResponse(GrantRevokePrivilegeResponse other) {
      this.__isset_bitfield = other.__isset_bitfield;
      this.success = other.success;
   }

   public GrantRevokePrivilegeResponse deepCopy() {
      return new GrantRevokePrivilegeResponse(this);
   }

   public void clear() {
      this.setSuccessIsSet(false);
      this.success = false;
   }

   public boolean isSuccess() {
      return this.success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
      this.setSuccessIsSet(true);
   }

   public void unsetSuccess() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetSuccess() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setSuccessIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case SUCCESS:
            if (value == null) {
               this.unsetSuccess();
            } else {
               this.setSuccess((Boolean)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case SUCCESS:
            return this.isSuccess();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case SUCCESS:
               return this.isSetSuccess();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GrantRevokePrivilegeResponse ? this.equals((GrantRevokePrivilegeResponse)that) : false;
   }

   public boolean equals(GrantRevokePrivilegeResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_success = this.isSetSuccess();
         boolean that_present_success = that.isSetSuccess();
         if (this_present_success || that_present_success) {
            if (!this_present_success || !that_present_success) {
               return false;
            }

            if (this.success != that.success) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetSuccess() ? 131071 : 524287);
      if (this.isSetSuccess()) {
         hashCode = hashCode * 8191 + (this.success ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(GrantRevokePrivilegeResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetSuccess(), other.isSetSuccess());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetSuccess()) {
               lastComparison = TBaseHelper.compareTo(this.success, other.success);
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
      return GrantRevokePrivilegeResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GrantRevokePrivilegeResponse(");
      boolean first = true;
      if (this.isSetSuccess()) {
         sb.append("success:");
         sb.append(this.success);
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
      optionals = new _Fields[]{GrantRevokePrivilegeResponse._Fields.SUCCESS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GrantRevokePrivilegeResponse._Fields.SUCCESS, new FieldMetaData("success", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GrantRevokePrivilegeResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      SUCCESS((short)1, "success");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return SUCCESS;
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

   private static class GrantRevokePrivilegeResponseStandardSchemeFactory implements SchemeFactory {
      private GrantRevokePrivilegeResponseStandardSchemeFactory() {
      }

      public GrantRevokePrivilegeResponseStandardScheme getScheme() {
         return new GrantRevokePrivilegeResponseStandardScheme();
      }
   }

   private static class GrantRevokePrivilegeResponseStandardScheme extends StandardScheme {
      private GrantRevokePrivilegeResponseStandardScheme() {
      }

      public void read(TProtocol iprot, GrantRevokePrivilegeResponse struct) throws TException {
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
                  if (schemeField.type == 2) {
                     struct.success = iprot.readBool();
                     struct.setSuccessIsSet(true);
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

      public void write(TProtocol oprot, GrantRevokePrivilegeResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GrantRevokePrivilegeResponse.STRUCT_DESC);
         if (struct.isSetSuccess()) {
            oprot.writeFieldBegin(GrantRevokePrivilegeResponse.SUCCESS_FIELD_DESC);
            oprot.writeBool(struct.success);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GrantRevokePrivilegeResponseTupleSchemeFactory implements SchemeFactory {
      private GrantRevokePrivilegeResponseTupleSchemeFactory() {
      }

      public GrantRevokePrivilegeResponseTupleScheme getScheme() {
         return new GrantRevokePrivilegeResponseTupleScheme();
      }
   }

   private static class GrantRevokePrivilegeResponseTupleScheme extends TupleScheme {
      private GrantRevokePrivilegeResponseTupleScheme() {
      }

      public void write(TProtocol prot, GrantRevokePrivilegeResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetSuccess()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetSuccess()) {
            oprot.writeBool(struct.success);
         }

      }

      public void read(TProtocol prot, GrantRevokePrivilegeResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.success = iprot.readBool();
            struct.setSuccessIsSet(true);
         }

      }
   }
}
