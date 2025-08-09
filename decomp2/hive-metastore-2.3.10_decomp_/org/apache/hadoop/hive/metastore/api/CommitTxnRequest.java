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

public class CommitTxnRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("CommitTxnRequest");
   private static final TField TXNID_FIELD_DESC = new TField("txnid", (byte)10, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new CommitTxnRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new CommitTxnRequestTupleSchemeFactory();
   private long txnid;
   private static final int __TXNID_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public CommitTxnRequest() {
      this.__isset_bitfield = 0;
   }

   public CommitTxnRequest(long txnid) {
      this();
      this.txnid = txnid;
      this.setTxnidIsSet(true);
   }

   public CommitTxnRequest(CommitTxnRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.txnid = other.txnid;
   }

   public CommitTxnRequest deepCopy() {
      return new CommitTxnRequest(this);
   }

   public void clear() {
      this.setTxnidIsSet(false);
      this.txnid = 0L;
   }

   public long getTxnid() {
      return this.txnid;
   }

   public void setTxnid(long txnid) {
      this.txnid = txnid;
      this.setTxnidIsSet(true);
   }

   public void unsetTxnid() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetTxnid() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setTxnidIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TXNID:
            if (value == null) {
               this.unsetTxnid();
            } else {
               this.setTxnid((Long)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TXNID:
            return this.getTxnid();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TXNID:
               return this.isSetTxnid();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof CommitTxnRequest ? this.equals((CommitTxnRequest)that) : false;
   }

   public boolean equals(CommitTxnRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_txnid = true;
         boolean that_present_txnid = true;
         if (this_present_txnid || that_present_txnid) {
            if (!this_present_txnid || !that_present_txnid) {
               return false;
            }

            if (this.txnid != that.txnid) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.txnid);
      return hashCode;
   }

   public int compareTo(CommitTxnRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
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

            return 0;
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return CommitTxnRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("CommitTxnRequest(");
      boolean first = true;
      sb.append("txnid:");
      sb.append(this.txnid);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTxnid()) {
         throw new TProtocolException("Required field 'txnid' is unset! Struct:" + this.toString());
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
      tmpMap.put(CommitTxnRequest._Fields.TXNID, new FieldMetaData("txnid", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(CommitTxnRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TXNID((short)1, "txnid");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TXNID;
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

   private static class CommitTxnRequestStandardSchemeFactory implements SchemeFactory {
      private CommitTxnRequestStandardSchemeFactory() {
      }

      public CommitTxnRequestStandardScheme getScheme() {
         return new CommitTxnRequestStandardScheme();
      }
   }

   private static class CommitTxnRequestStandardScheme extends StandardScheme {
      private CommitTxnRequestStandardScheme() {
      }

      public void read(TProtocol iprot, CommitTxnRequest struct) throws TException {
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
                     struct.txnid = iprot.readI64();
                     struct.setTxnidIsSet(true);
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

      public void write(TProtocol oprot, CommitTxnRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(CommitTxnRequest.STRUCT_DESC);
         oprot.writeFieldBegin(CommitTxnRequest.TXNID_FIELD_DESC);
         oprot.writeI64(struct.txnid);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class CommitTxnRequestTupleSchemeFactory implements SchemeFactory {
      private CommitTxnRequestTupleSchemeFactory() {
      }

      public CommitTxnRequestTupleScheme getScheme() {
         return new CommitTxnRequestTupleScheme();
      }
   }

   private static class CommitTxnRequestTupleScheme extends TupleScheme {
      private CommitTxnRequestTupleScheme() {
      }

      public void write(TProtocol prot, CommitTxnRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.txnid);
      }

      public void read(TProtocol prot, CommitTxnRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.txnid = iprot.readI64();
         struct.setTxnidIsSet(true);
      }
   }
}
