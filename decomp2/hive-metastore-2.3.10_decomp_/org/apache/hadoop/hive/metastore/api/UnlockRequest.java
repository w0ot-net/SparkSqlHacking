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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class UnlockRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("UnlockRequest");
   private static final TField LOCKID_FIELD_DESC = new TField("lockid", (byte)10, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new UnlockRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new UnlockRequestTupleSchemeFactory();
   private long lockid;
   private static final int __LOCKID_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public UnlockRequest() {
      this.__isset_bitfield = 0;
   }

   public UnlockRequest(long lockid) {
      this();
      this.lockid = lockid;
      this.setLockidIsSet(true);
   }

   public UnlockRequest(UnlockRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.lockid = other.lockid;
   }

   public UnlockRequest deepCopy() {
      return new UnlockRequest(this);
   }

   public void clear() {
      this.setLockidIsSet(false);
      this.lockid = 0L;
   }

   public long getLockid() {
      return this.lockid;
   }

   public void setLockid(long lockid) {
      this.lockid = lockid;
      this.setLockidIsSet(true);
   }

   public void unsetLockid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetLockid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setLockidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LOCKID:
            if (value == null) {
               this.unsetLockid();
            } else {
               this.setLockid((Long)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LOCKID:
            return this.getLockid();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case LOCKID:
               return this.isSetLockid();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof UnlockRequest ? this.equals((UnlockRequest)that) : false;
   }

   public boolean equals(UnlockRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_lockid = true;
         boolean that_present_lockid = true;
         if (this_present_lockid || that_present_lockid) {
            if (!this_present_lockid || !that_present_lockid) {
               return false;
            }

            if (this.lockid != that.lockid) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lockid);
      return hashCode;
   }

   public int compareTo(UnlockRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetLockid(), other.isSetLockid());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetLockid()) {
               lastComparison = TBaseHelper.compareTo(this.lockid, other.lockid);
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
      return UnlockRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("UnlockRequest(");
      boolean first = true;
      sb.append("lockid:");
      sb.append(this.lockid);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetLockid()) {
         throw new TProtocolException("Required field 'lockid' is unset! Struct:" + this.toString());
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
      tmpMap.put(UnlockRequest._Fields.LOCKID, new FieldMetaData("lockid", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(UnlockRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LOCKID((short)1, "lockid");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LOCKID;
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

   private static class UnlockRequestStandardSchemeFactory implements SchemeFactory {
      private UnlockRequestStandardSchemeFactory() {
      }

      public UnlockRequestStandardScheme getScheme() {
         return new UnlockRequestStandardScheme();
      }
   }

   private static class UnlockRequestStandardScheme extends StandardScheme {
      private UnlockRequestStandardScheme() {
      }

      public void read(TProtocol iprot, UnlockRequest struct) throws TException {
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
                  if (schemeField.type == 10) {
                     struct.lockid = iprot.readI64();
                     struct.setLockidIsSet(true);
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

      public void write(TProtocol oprot, UnlockRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(UnlockRequest.STRUCT_DESC);
         oprot.writeFieldBegin(UnlockRequest.LOCKID_FIELD_DESC);
         oprot.writeI64(struct.lockid);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class UnlockRequestTupleSchemeFactory implements SchemeFactory {
      private UnlockRequestTupleSchemeFactory() {
      }

      public UnlockRequestTupleScheme getScheme() {
         return new UnlockRequestTupleScheme();
      }
   }

   private static class UnlockRequestTupleScheme extends TupleScheme {
      private UnlockRequestTupleScheme() {
      }

      public void write(TProtocol prot, UnlockRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.lockid);
      }

      public void read(TProtocol prot, UnlockRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.lockid = iprot.readI64();
         struct.setLockidIsSet(true);
      }
   }
}
