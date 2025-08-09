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
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.annotation.Nullable;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
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

public class OpenTxnsResponse implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("OpenTxnsResponse");
   private static final TField TXN_IDS_FIELD_DESC = new TField("txn_ids", (byte)15, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new OpenTxnsResponseStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new OpenTxnsResponseTupleSchemeFactory();
   @Nullable
   private List txn_ids;
   public static final Map metaDataMap;

   public OpenTxnsResponse() {
   }

   public OpenTxnsResponse(List txn_ids) {
      this();
      this.txn_ids = txn_ids;
   }

   public OpenTxnsResponse(OpenTxnsResponse other) {
      if (other.isSetTxn_ids()) {
         List<Long> __this__txn_ids = new ArrayList(other.txn_ids);
         this.txn_ids = __this__txn_ids;
      }

   }

   public OpenTxnsResponse deepCopy() {
      return new OpenTxnsResponse(this);
   }

   public void clear() {
      this.txn_ids = null;
   }

   public int getTxn_idsSize() {
      return this.txn_ids == null ? 0 : this.txn_ids.size();
   }

   @Nullable
   public Iterator getTxn_idsIterator() {
      return this.txn_ids == null ? null : this.txn_ids.iterator();
   }

   public void addToTxn_ids(long elem) {
      if (this.txn_ids == null) {
         this.txn_ids = new ArrayList();
      }

      this.txn_ids.add(elem);
   }

   @Nullable
   public List getTxn_ids() {
      return this.txn_ids;
   }

   public void setTxn_ids(@Nullable List txn_ids) {
      this.txn_ids = txn_ids;
   }

   public void unsetTxn_ids() {
      this.txn_ids = null;
   }

   public boolean isSetTxn_ids() {
      return this.txn_ids != null;
   }

   public void setTxn_idsIsSet(boolean value) {
      if (!value) {
         this.txn_ids = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case TXN_IDS:
            if (value == null) {
               this.unsetTxn_ids();
            } else {
               this.setTxn_ids((List)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case TXN_IDS:
            return this.getTxn_ids();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case TXN_IDS:
               return this.isSetTxn_ids();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof OpenTxnsResponse ? this.equals((OpenTxnsResponse)that) : false;
   }

   public boolean equals(OpenTxnsResponse that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_txn_ids = this.isSetTxn_ids();
         boolean that_present_txn_ids = that.isSetTxn_ids();
         if (this_present_txn_ids || that_present_txn_ids) {
            if (!this_present_txn_ids || !that_present_txn_ids) {
               return false;
            }

            if (!this.txn_ids.equals(that.txn_ids)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetTxn_ids() ? 131071 : 524287);
      if (this.isSetTxn_ids()) {
         hashCode = hashCode * 8191 + this.txn_ids.hashCode();
      }

      return hashCode;
   }

   public int compareTo(OpenTxnsResponse other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetTxn_ids(), other.isSetTxn_ids());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetTxn_ids()) {
               lastComparison = TBaseHelper.compareTo(this.txn_ids, other.txn_ids);
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
      return OpenTxnsResponse._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("OpenTxnsResponse(");
      boolean first = true;
      sb.append("txn_ids:");
      if (this.txn_ids == null) {
         sb.append("null");
      } else {
         sb.append(this.txn_ids);
      }

      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetTxn_ids()) {
         throw new TProtocolException("Required field 'txn_ids' is unset! Struct:" + this.toString());
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
      tmpMap.put(OpenTxnsResponse._Fields.TXN_IDS, new FieldMetaData("txn_ids", (byte)1, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(OpenTxnsResponse.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      TXN_IDS((short)1, "txn_ids");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return TXN_IDS;
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

   private static class OpenTxnsResponseStandardSchemeFactory implements SchemeFactory {
      private OpenTxnsResponseStandardSchemeFactory() {
      }

      public OpenTxnsResponseStandardScheme getScheme() {
         return new OpenTxnsResponseStandardScheme();
      }
   }

   private static class OpenTxnsResponseStandardScheme extends StandardScheme {
      private OpenTxnsResponseStandardScheme() {
      }

      public void read(TProtocol iprot, OpenTxnsResponse struct) throws TException {
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
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list508 = iprot.readListBegin();
                  struct.txn_ids = new ArrayList(_list508.size);

                  for(int _i510 = 0; _i510 < _list508.size; ++_i510) {
                     long _elem509 = iprot.readI64();
                     struct.txn_ids.add(_elem509);
                  }

                  iprot.readListEnd();
                  struct.setTxn_idsIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, OpenTxnsResponse struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(OpenTxnsResponse.STRUCT_DESC);
         if (struct.txn_ids != null) {
            oprot.writeFieldBegin(OpenTxnsResponse.TXN_IDS_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.txn_ids.size()));

            for(long _iter511 : struct.txn_ids) {
               oprot.writeI64(_iter511);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class OpenTxnsResponseTupleSchemeFactory implements SchemeFactory {
      private OpenTxnsResponseTupleSchemeFactory() {
      }

      public OpenTxnsResponseTupleScheme getScheme() {
         return new OpenTxnsResponseTupleScheme();
      }
   }

   private static class OpenTxnsResponseTupleScheme extends TupleScheme {
      private OpenTxnsResponseTupleScheme() {
      }

      public void write(TProtocol prot, OpenTxnsResponse struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.txn_ids.size());

         for(long _iter512 : struct.txn_ids) {
            oprot.writeI64(_iter512);
         }

      }

      public void read(TProtocol prot, OpenTxnsResponse struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         TList _list513 = iprot.readListBegin((byte)10);
         struct.txn_ids = new ArrayList(_list513.size);

         for(int _i515 = 0; _i515 < _list513.size; ++_i515) {
            long _elem514 = iprot.readI64();
            struct.txn_ids.add(_elem514);
         }

         struct.setTxn_idsIsSet(true);
      }
   }
}
