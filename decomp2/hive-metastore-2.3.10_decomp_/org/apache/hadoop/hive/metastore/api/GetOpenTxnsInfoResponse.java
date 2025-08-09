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
import org.apache.thrift.EncodingUtils;
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

public class GetOpenTxnsInfoResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("GetOpenTxnsInfoResponse");
   private static final TField TXN_HIGH_WATER_MARK_FIELD_DESC = new TField("txn_high_water_mark", (byte)10, (short)1);
   private static final TField OPEN_TXNS_FIELD_DESC = new TField("open_txns", (byte)15, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new GetOpenTxnsInfoResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new GetOpenTxnsInfoResponseTupleSchemeFactory();
   private long txn_high_water_mark;
   @Nullable
   private List open_txns;
   private static final int __TXN_HIGH_WATER_MARK_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public GetOpenTxnsInfoResponse() {
      this.__isset_bitfield = 0;
   }

   public GetOpenTxnsInfoResponse(long txn_high_water_mark, List open_txns) {
      this();
      this.txn_high_water_mark = txn_high_water_mark;
      this.setTxn_high_water_markIsSet(true);
      this.open_txns = open_txns;
   }

   public GetOpenTxnsInfoResponse(GetOpenTxnsInfoResponse other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.txn_high_water_mark = other.txn_high_water_mark;
      if (other.isSetOpen_txns()) {
         List<TxnInfo> __this__open_txns = new ArrayList(other.open_txns.size());

         for(TxnInfo other_element : other.open_txns) {
            __this__open_txns.add(new TxnInfo(other_element));
         }

         this.open_txns = __this__open_txns;
      }

   }

   public GetOpenTxnsInfoResponse deepCopy() {
      return new GetOpenTxnsInfoResponse(this);
   }

   public void clear() {
      this.setTxn_high_water_markIsSet(false);
      this.txn_high_water_mark = 0L;
      this.open_txns = null;
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

   public void addToOpen_txns(TxnInfo elem) {
      if (this.open_txns == null) {
         this.open_txns = new ArrayList();
      }

      this.open_txns.add(elem);
   }

   @Nullable
   public List getOpen_txns() {
      return this.open_txns;
   }

   public void setOpen_txns(@Nullable List open_txns) {
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
               this.setOpen_txns((List)value);
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
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof GetOpenTxnsInfoResponse ? this.equals((GetOpenTxnsInfoResponse)that) : false;
   }

   public boolean equals(GetOpenTxnsInfoResponse that) {
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

      return hashCode;
   }

   public int compareTo(GetOpenTxnsInfoResponse other) {
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

               return 0;
            }
         }
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return GetOpenTxnsInfoResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("GetOpenTxnsInfoResponse(");
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
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(GetOpenTxnsInfoResponse._Fields.TXN_HIGH_WATER_MARK, new FieldMetaData("txn_high_water_mark", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(GetOpenTxnsInfoResponse._Fields.OPEN_TXNS, new FieldMetaData("open_txns", (byte)1, new ListMetaData((byte)15, new StructMetaData((byte)12, TxnInfo.class))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(GetOpenTxnsInfoResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TXN_HIGH_WATER_MARK((short)1, "txn_high_water_mark"),
      OPEN_TXNS((short)2, "open_txns");

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

   private static class GetOpenTxnsInfoResponseStandardSchemeFactory implements SchemeFactory {
      private GetOpenTxnsInfoResponseStandardSchemeFactory() {
      }

      public GetOpenTxnsInfoResponseStandardScheme getScheme() {
         return new GetOpenTxnsInfoResponseStandardScheme();
      }
   }

   private static class GetOpenTxnsInfoResponseStandardScheme extends StandardScheme {
      private GetOpenTxnsInfoResponseStandardScheme() {
      }

      public void read(TProtocol iprot, GetOpenTxnsInfoResponse struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list492 = iprot.readListBegin();
                  struct.open_txns = new ArrayList(_list492.size);

                  for(int _i494 = 0; _i494 < _list492.size; ++_i494) {
                     TxnInfo _elem493 = new TxnInfo();
                     _elem493.read(iprot);
                     struct.open_txns.add(_elem493);
                  }

                  iprot.readListEnd();
                  struct.setOpen_txnsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, GetOpenTxnsInfoResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(GetOpenTxnsInfoResponse.STRUCT_DESC);
         oprot.writeFieldBegin(GetOpenTxnsInfoResponse.TXN_HIGH_WATER_MARK_FIELD_DESC);
         oprot.writeI64(struct.txn_high_water_mark);
         oprot.writeFieldEnd();
         if (struct.open_txns != null) {
            oprot.writeFieldBegin(GetOpenTxnsInfoResponse.OPEN_TXNS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)12, struct.open_txns.size()));

            for(TxnInfo _iter495 : struct.open_txns) {
               _iter495.write(oprot);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class GetOpenTxnsInfoResponseTupleSchemeFactory implements SchemeFactory {
      private GetOpenTxnsInfoResponseTupleSchemeFactory() {
      }

      public GetOpenTxnsInfoResponseTupleScheme getScheme() {
         return new GetOpenTxnsInfoResponseTupleScheme();
      }
   }

   private static class GetOpenTxnsInfoResponseTupleScheme extends TupleScheme {
      private GetOpenTxnsInfoResponseTupleScheme() {
      }

      public void write(TProtocol prot, GetOpenTxnsInfoResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.txn_high_water_mark);
         oprot.writeI32(struct.open_txns.size());

         for(TxnInfo _iter496 : struct.open_txns) {
            _iter496.write(oprot);
         }

      }

      public void read(TProtocol prot, GetOpenTxnsInfoResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.txn_high_water_mark = iprot.readI64();
         struct.setTxn_high_water_markIsSet(true);
         TList _list497 = iprot.readListBegin((byte)12);
         struct.open_txns = new ArrayList(_list497.size);

         for(int _i499 = 0; _i499 < _list497.size; ++_i499) {
            TxnInfo _elem498 = new TxnInfo();
            _elem498.read(iprot);
            struct.open_txns.add(_elem498);
         }

         struct.setOpen_txnsIsSet(true);
      }
   }
}
