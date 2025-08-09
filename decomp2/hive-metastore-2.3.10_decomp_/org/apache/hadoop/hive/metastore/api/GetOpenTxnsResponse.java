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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.transport.TIOStreamTransport;

public class GetOpenTxnsResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetOpenTxnsResponse");
   private static final TField TXN_HIGH_WATER_MARK_FIELD_DESC = new TField("txn_high_water_mark", (byte)10, (short)1);
   private static final TField OPEN_TXNS_FIELD_DESC = new TField("open_txns", (byte)14, (short)2);
   private static final TField MIN_OPEN_TXN_FIELD_DESC = new TField("min_open_txn", (byte)10, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetOpenTxnsResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetOpenTxnsResponseTupleSchemeFactory();
   private long txn_high_water_mark;
   @Nullable
   private Set open_txns;
   private long min_open_txn;
   private static final int __TXN_HIGH_WATER_MARK_ISSET_ID = 0;
   private static final int __MIN_OPEN_TXN_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public GetOpenTxnsResponse() {
      this.__isset_bitfield = 0;
   }

   public GetOpenTxnsResponse(long txn_high_water_mark, Set open_txns) {
      this();
      this.txn_high_water_mark = txn_high_water_mark;
      this.setTxn_high_water_markIsSet(true);
      this.open_txns = open_txns;
   }

   public GetOpenTxnsResponse(GetOpenTxnsResponse other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.txn_high_water_mark = other.txn_high_water_mark;
      if (other.isSetOpen_txns()) {
         Set<Long> __this__open_txns = new HashSet(other.open_txns);
         this.open_txns = __this__open_txns;
      }

      this.min_open_txn = other.min_open_txn;
   }

   public GetOpenTxnsResponse deepCopy() {
      return new GetOpenTxnsResponse(this);
   }

   public void clear() {
      this.setTxn_high_water_markIsSet(false);
      this.txn_high_water_mark = 0L;
      this.open_txns = null;
      this.setMin_open_txnIsSet(false);
      this.min_open_txn = 0L;
   }

   public long getTxn_high_water_mark() {
      return this.txn_high_water_mark;
   }

   public void setTxn_high_water_mark(long txn_high_water_mark) {
      this.txn_high_water_mark = txn_high_water_mark;
      this.setTxn_high_water_markIsSet(true);
   }

   public void unsetTxn_high_water_mark() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetTxn_high_water_mark() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setTxn_high_water_markIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public int getOpen_txnsSize() {
      return this.open_txns == null ? 0 : this.open_txns.size();
   }

   @Nullable
   public Iterator getOpen_txnsIterator() {
      return this.open_txns == null ? null : this.open_txns.iterator();
   }

   public void addToOpen_txns(long elem) {
      if (this.open_txns == null) {
         this.open_txns = new HashSet();
      }

      this.open_txns.add(elem);
   }

   @Nullable
   public Set getOpen_txns() {
      return this.open_txns;
   }

   public void setOpen_txns(@Nullable Set open_txns) {
      this.open_txns = open_txns;
   }

   public void unsetOpen_txns() {
      this.open_txns = null;
   }

   public boolean isSetOpen_txns() {
      return this.open_txns != null;
   }

   public void setOpen_txnsIsSet(boolean value) {
      if (!value) {
         this.open_txns = null;
      }

   }

   public long getMin_open_txn() {
      return this.min_open_txn;
   }

   public void setMin_open_txn(long min_open_txn) {
      this.min_open_txn = min_open_txn;
      this.setMin_open_txnIsSet(true);
   }

   public void unsetMin_open_txn() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetMin_open_txn() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setMin_open_txnIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TXN_HIGH_WATER_MARK:
            if (value == null) {
               this.unsetTxn_high_water_mark();
            } else {
               this.setTxn_high_water_mark((Long)value);
            }
            break;
         case OPEN_TXNS:
            if (value == null) {
               this.unsetOpen_txns();
            } else {
               this.setOpen_txns((Set)value);
            }
            break;
         case MIN_OPEN_TXN:
            if (value == null) {
               this.unsetMin_open_txn();
            } else {
               this.setMin_open_txn((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TXN_HIGH_WATER_MARK:
            return this.getTxn_high_water_mark();
         case OPEN_TXNS:
            return this.getOpen_txns();
         case MIN_OPEN_TXN:
            return this.getMin_open_txn();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TXN_HIGH_WATER_MARK:
               return this.isSetTxn_high_water_mark();
            case OPEN_TXNS:
               return this.isSetOpen_txns();
            case MIN_OPEN_TXN:
               return this.isSetMin_open_txn();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetOpenTxnsResponse ? this.equals((GetOpenTxnsResponse)that) : false;
   }

   public boolean equals(GetOpenTxnsResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_txn_high_water_mark = true;
         boolean that_present_txn_high_water_mark = true;
         if (this_present_txn_high_water_mark || that_present_txn_high_water_mark) {
            if (!this_present_txn_high_water_mark || !that_present_txn_high_water_mark) {
               return false;
            }

            if (this.txn_high_water_mark != that.txn_high_water_mark) {
               return false;
            }
         }

         boolean this_present_open_txns = this.isSetOpen_txns();
         boolean that_present_open_txns = that.isSetOpen_txns();
         if (this_present_open_txns || that_present_open_txns) {
            if (!this_present_open_txns || !that_present_open_txns) {
               return false;
            }

            if (!this.open_txns.equals(that.open_txns)) {
               return false;
            }
         }

         boolean this_present_min_open_txn = this.isSetMin_open_txn();
         boolean that_present_min_open_txn = that.isSetMin_open_txn();
         if (this_present_min_open_txn || that_present_min_open_txn) {
            if (!this_present_min_open_txn || !that_present_min_open_txn) {
               return false;
            }

            if (this.min_open_txn != that.min_open_txn) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.txn_high_water_mark);
      hashCode = hashCode * 8191 + (this.isSetOpen_txns() ? 131071 : 524287);
      if (this.isSetOpen_txns()) {
         hashCode = hashCode * 8191 + this.open_txns.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetMin_open_txn() ? 131071 : 524287);
      if (this.isSetMin_open_txn()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.min_open_txn);
      }

      return hashCode;
   }

   public int compareTo(GetOpenTxnsResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTxn_high_water_mark(), other.isSetTxn_high_water_mark());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTxn_high_water_mark()) {
               lastComparison = TBaseHelper.compareTo(this.txn_high_water_mark, other.txn_high_water_mark);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetOpen_txns(), other.isSetOpen_txns());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetOpen_txns()) {
                  lastComparison = TBaseHelper.compareTo(this.open_txns, other.open_txns);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetMin_open_txn(), other.isSetMin_open_txn());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetMin_open_txn()) {
                     lastComparison = TBaseHelper.compareTo(this.min_open_txn, other.min_open_txn);
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
      return GetOpenTxnsResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetOpenTxnsResponse(");
      boolean first = true;
      sb.append("txn_high_water_mark:");
      sb.append(this.txn_high_water_mark);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("open_txns:");
      if (this.open_txns == null) {
         sb.append("null");
      } else {
         sb.append(this.open_txns);
      }

      first = false;
      if (this.isSetMin_open_txn()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("min_open_txn:");
         sb.append(this.min_open_txn);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTxn_high_water_mark()) {
         throw new TProtocolException("Required field 'txn_high_water_mark' is unset! Struct:" + this.toString());
      } else if (!this.isSetOpen_txns()) {
         throw new TProtocolException("Required field 'open_txns' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{GetOpenTxnsResponse._Fields.MIN_OPEN_TXN};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetOpenTxnsResponse._Fields.TXN_HIGH_WATER_MARK, new FieldMetaData("txn_high_water_mark", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(GetOpenTxnsResponse._Fields.OPEN_TXNS, new FieldMetaData("open_txns", (byte)1, new SetMetaData((byte)14, new FieldValueMetaData((byte)10))));
      tmpMap.put(GetOpenTxnsResponse._Fields.MIN_OPEN_TXN, new FieldMetaData("min_open_txn", (byte)2, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetOpenTxnsResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TXN_HIGH_WATER_MARK((short)1, "txn_high_water_mark"),
      OPEN_TXNS((short)2, "open_txns"),
      MIN_OPEN_TXN((short)3, "min_open_txn");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TXN_HIGH_WATER_MARK;
            case 2:
               return OPEN_TXNS;
            case 3:
               return MIN_OPEN_TXN;
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

   private static class GetOpenTxnsResponseStandardSchemeFactory implements SchemeFactory {
      private GetOpenTxnsResponseStandardSchemeFactory() {
      }

      public GetOpenTxnsResponseStandardScheme getScheme() {
         return new GetOpenTxnsResponseStandardScheme();
      }
   }

   private static class GetOpenTxnsResponseStandardScheme extends StandardScheme {
      private GetOpenTxnsResponseStandardScheme() {
      }

      public void read(TProtocol iprot, GetOpenTxnsResponse struct) throws TException {
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
                     struct.txn_high_water_mark = iprot.readI64();
                     struct.setTxn_high_water_markIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 14) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TSet _set500 = iprot.readSetBegin();
                  struct.open_txns = new HashSet(2 * _set500.size);

                  for(int _i502 = 0; _i502 < _set500.size; ++_i502) {
                     long _elem501 = iprot.readI64();
                     struct.open_txns.add(_elem501);
                  }

                  iprot.readSetEnd();
                  struct.setOpen_txnsIsSet(true);
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.min_open_txn = iprot.readI64();
                     struct.setMin_open_txnIsSet(true);
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

      public void write(TProtocol oprot, GetOpenTxnsResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetOpenTxnsResponse.STRUCT_DESC);
         oprot.writeFieldBegin(GetOpenTxnsResponse.TXN_HIGH_WATER_MARK_FIELD_DESC);
         oprot.writeI64(struct.txn_high_water_mark);
         oprot.writeFieldEnd();
         if (struct.open_txns != null) {
            oprot.writeFieldBegin(GetOpenTxnsResponse.OPEN_TXNS_FIELD_DESC);
            oprot.writeSetBegin(new TSet((byte)10, struct.open_txns.size()));

            for(long _iter503 : struct.open_txns) {
               oprot.writeI64(_iter503);
            }

            oprot.writeSetEnd();
            oprot.writeFieldEnd();
         }

         if (struct.isSetMin_open_txn()) {
            oprot.writeFieldBegin(GetOpenTxnsResponse.MIN_OPEN_TXN_FIELD_DESC);
            oprot.writeI64(struct.min_open_txn);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetOpenTxnsResponseTupleSchemeFactory implements SchemeFactory {
      private GetOpenTxnsResponseTupleSchemeFactory() {
      }

      public GetOpenTxnsResponseTupleScheme getScheme() {
         return new GetOpenTxnsResponseTupleScheme();
      }
   }

   private static class GetOpenTxnsResponseTupleScheme extends TupleScheme {
      private GetOpenTxnsResponseTupleScheme() {
      }

      public void write(TProtocol prot, GetOpenTxnsResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.txn_high_water_mark);
         oprot.writeI32(struct.open_txns.size());

         for(long _iter504 : struct.open_txns) {
            oprot.writeI64(_iter504);
         }

         BitSet optionals = new BitSet();
         if (struct.isSetMin_open_txn()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetMin_open_txn()) {
            oprot.writeI64(struct.min_open_txn);
         }

      }

      public void read(TProtocol prot, GetOpenTxnsResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.txn_high_water_mark = iprot.readI64();
         struct.setTxn_high_water_markIsSet(true);
         TSet _set505 = iprot.readSetBegin((byte)10);
         struct.open_txns = new HashSet(2 * _set505.size);

         for(int _i507 = 0; _i507 < _set505.size; ++_i507) {
            long _elem506 = iprot.readI64();
            struct.open_txns.add(_elem506);
         }

         struct.setOpen_txnsIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.min_open_txn = iprot.readI64();
            struct.setMin_open_txnIsSet(true);
         }

      }
   }
}
