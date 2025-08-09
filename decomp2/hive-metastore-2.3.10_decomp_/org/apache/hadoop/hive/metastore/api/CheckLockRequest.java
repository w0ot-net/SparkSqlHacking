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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class CheckLockRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CheckLockRequest");
   private static final TField LOCKID_FIELD_DESC = new TField("lockid", (byte)10, (short)1);
   private static final TField TXNID_FIELD_DESC = new TField("txnid", (byte)10, (short)2);
   private static final TField ELAPSED_MS_FIELD_DESC = new TField("elapsed_ms", (byte)10, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CheckLockRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CheckLockRequestTupleSchemeFactory();
   private long lockid;
   private long txnid;
   private long elapsed_ms;
   private static final int __LOCKID_ISSET_ID = 0;
   private static final int __TXNID_ISSET_ID = 1;
   private static final int __ELAPSED_MS_ISSET_ID = 2;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public CheckLockRequest() {
      this.__isset_bitfield = 0;
   }

   public CheckLockRequest(long lockid) {
      this();
      this.lockid = lockid;
      this.setLockidIsSet(true);
   }

   public CheckLockRequest(CheckLockRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.lockid = other.lockid;
      this.txnid = other.txnid;
      this.elapsed_ms = other.elapsed_ms;
   }

   public CheckLockRequest deepCopy() {
      return new CheckLockRequest(this);
   }

   public void clear() {
      this.setLockidIsSet(false);
      this.lockid = 0L;
      this.setTxnidIsSet(false);
      this.txnid = 0L;
      this.setElapsed_msIsSet(false);
      this.elapsed_ms = 0L;
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

   public long getTxnid() {
      return this.txnid;
   }

   public void setTxnid(long txnid) {
      this.txnid = txnid;
      this.setTxnidIsSet(true);
   }

   public void unsetTxnid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetTxnid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setTxnidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public long getElapsed_ms() {
      return this.elapsed_ms;
   }

   public void setElapsed_ms(long elapsed_ms) {
      this.elapsed_ms = elapsed_ms;
      this.setElapsed_msIsSet(true);
   }

   public void unsetElapsed_ms() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetElapsed_ms() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setElapsed_msIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LOCKID:
            if (value == null) {
               this.unsetLockid();
            } else {
               this.setLockid((Long)value);
            }
            break;
         case TXNID:
            if (value == null) {
               this.unsetTxnid();
            } else {
               this.setTxnid((Long)value);
            }
            break;
         case ELAPSED_MS:
            if (value == null) {
               this.unsetElapsed_ms();
            } else {
               this.setElapsed_ms((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LOCKID:
            return this.getLockid();
         case TXNID:
            return this.getTxnid();
         case ELAPSED_MS:
            return this.getElapsed_ms();
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
            case TXNID:
               return this.isSetTxnid();
            case ELAPSED_MS:
               return this.isSetElapsed_ms();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CheckLockRequest ? this.equals((CheckLockRequest)that) : false;
   }

   public boolean equals(CheckLockRequest that) {
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

         boolean this_present_txnid = this.isSetTxnid();
         boolean that_present_txnid = that.isSetTxnid();
         if (this_present_txnid || that_present_txnid) {
            if (!this_present_txnid || !that_present_txnid) {
               return false;
            }

            if (this.txnid != that.txnid) {
               return false;
            }
         }

         boolean this_present_elapsed_ms = this.isSetElapsed_ms();
         boolean that_present_elapsed_ms = that.isSetElapsed_ms();
         if (this_present_elapsed_ms || that_present_elapsed_ms) {
            if (!this_present_elapsed_ms || !that_present_elapsed_ms) {
               return false;
            }

            if (this.elapsed_ms != that.elapsed_ms) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lockid);
      hashCode = hashCode * 8191 + (this.isSetTxnid() ? 131071 : 524287);
      if (this.isSetTxnid()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.txnid);
      }

      hashCode = hashCode * 8191 + (this.isSetElapsed_ms() ? 131071 : 524287);
      if (this.isSetElapsed_ms()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.elapsed_ms);
      }

      return hashCode;
   }

   public int compareTo(CheckLockRequest other) {
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

            lastComparison = Boolean.compare(this.isSetTxnid(), other.isSetTxnid());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetTxnid()) {
                  lastComparison = TBaseHelper.compareTo(this.txnid, other.txnid);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetElapsed_ms(), other.isSetElapsed_ms());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetElapsed_ms()) {
                     lastComparison = TBaseHelper.compareTo(this.elapsed_ms, other.elapsed_ms);
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
      return CheckLockRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CheckLockRequest(");
      boolean first = true;
      sb.append("lockid:");
      sb.append(this.lockid);
      first = false;
      if (this.isSetTxnid()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("txnid:");
         sb.append(this.txnid);
         first = false;
      }

      if (this.isSetElapsed_ms()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("elapsed_ms:");
         sb.append(this.elapsed_ms);
         first = false;
      }

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
      optionals = new _Fields[]{CheckLockRequest._Fields.TXNID, CheckLockRequest._Fields.ELAPSED_MS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(CheckLockRequest._Fields.LOCKID, new FieldMetaData("lockid", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(CheckLockRequest._Fields.TXNID, new FieldMetaData("txnid", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(CheckLockRequest._Fields.ELAPSED_MS, new FieldMetaData("elapsed_ms", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CheckLockRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LOCKID((short)1, "lockid"),
      TXNID((short)2, "txnid"),
      ELAPSED_MS((short)3, "elapsed_ms");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LOCKID;
            case 2:
               return TXNID;
            case 3:
               return ELAPSED_MS;
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

   private static class CheckLockRequestStandardSchemeFactory implements SchemeFactory {
      private CheckLockRequestStandardSchemeFactory() {
      }

      public CheckLockRequestStandardScheme getScheme() {
         return new CheckLockRequestStandardScheme();
      }
   }

   private static class CheckLockRequestStandardScheme extends StandardScheme {
      private CheckLockRequestStandardScheme() {
      }

      public void read(TProtocol iprot, CheckLockRequest struct) throws TException {
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
               case 2:
                  if (schemeField.type == 10) {
                     struct.txnid = iprot.readI64();
                     struct.setTxnidIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.elapsed_ms = iprot.readI64();
                     struct.setElapsed_msIsSet(true);
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

      public void write(TProtocol oprot, CheckLockRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CheckLockRequest.STRUCT_DESC);
         oprot.writeFieldBegin(CheckLockRequest.LOCKID_FIELD_DESC);
         oprot.writeI64(struct.lockid);
         oprot.writeFieldEnd();
         if (struct.isSetTxnid()) {
            oprot.writeFieldBegin(CheckLockRequest.TXNID_FIELD_DESC);
            oprot.writeI64(struct.txnid);
            oprot.writeFieldEnd();
         }

         if (struct.isSetElapsed_ms()) {
            oprot.writeFieldBegin(CheckLockRequest.ELAPSED_MS_FIELD_DESC);
            oprot.writeI64(struct.elapsed_ms);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CheckLockRequestTupleSchemeFactory implements SchemeFactory {
      private CheckLockRequestTupleSchemeFactory() {
      }

      public CheckLockRequestTupleScheme getScheme() {
         return new CheckLockRequestTupleScheme();
      }
   }

   private static class CheckLockRequestTupleScheme extends TupleScheme {
      private CheckLockRequestTupleScheme() {
      }

      public void write(TProtocol prot, CheckLockRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.lockid);
         BitSet optionals = new BitSet();
         if (struct.isSetTxnid()) {
            optionals.set(0);
         }

         if (struct.isSetElapsed_ms()) {
            optionals.set(1);
         }

         oprot.writeBitSet(optionals, 2);
         if (struct.isSetTxnid()) {
            oprot.writeI64(struct.txnid);
         }

         if (struct.isSetElapsed_ms()) {
            oprot.writeI64(struct.elapsed_ms);
         }

      }

      public void read(TProtocol prot, CheckLockRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.lockid = iprot.readI64();
         struct.setLockidIsSet(true);
         BitSet incoming = iprot.readBitSet(2);
         if (incoming.get(0)) {
            struct.txnid = iprot.readI64();
            struct.setTxnidIsSet(true);
         }

         if (incoming.get(1)) {
            struct.elapsed_ms = iprot.readI64();
            struct.setElapsed_msIsSet(true);
         }

      }
   }
}
