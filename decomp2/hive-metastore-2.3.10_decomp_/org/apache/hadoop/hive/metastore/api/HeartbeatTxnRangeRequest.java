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

public class HeartbeatTxnRangeRequest implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("HeartbeatTxnRangeRequest");
   private static final TField MIN_FIELD_DESC = new TField("min", (byte)10, (short)1);
   private static final TField MAX_FIELD_DESC = new TField("max", (byte)10, (short)2);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new HeartbeatTxnRangeRequestStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new HeartbeatTxnRangeRequestTupleSchemeFactory();
   private long min;
   private long max;
   private static final int __MIN_ISSET_ID = 0;
   private static final int __MAX_ISSET_ID = 1;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public HeartbeatTxnRangeRequest() {
      this.__isset_bitfield = 0;
   }

   public HeartbeatTxnRangeRequest(long min, long max) {
      this();
      this.min = min;
      this.setMinIsSet(true);
      this.max = max;
      this.setMaxIsSet(true);
   }

   public HeartbeatTxnRangeRequest(HeartbeatTxnRangeRequest other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.min = other.min;
      this.max = other.max;
   }

   public HeartbeatTxnRangeRequest deepCopy() {
      return new HeartbeatTxnRangeRequest(this);
   }

   public void clear() {
      this.setMinIsSet(false);
      this.min = 0L;
      this.setMaxIsSet(false);
      this.max = 0L;
   }

   public long getMin() {
      return this.min;
   }

   public void setMin(long min) {
      this.min = min;
      this.setMinIsSet(true);
   }

   public void unsetMin() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetMin() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setMinIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public long getMax() {
      return this.max;
   }

   public void setMax(long max) {
      this.max = max;
      this.setMaxIsSet(true);
   }

   public void unsetMax() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetMax() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setMaxIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case MIN:
            if (value == null) {
               this.unsetMin();
            } else {
               this.setMin((Long)value);
            }
            break;
         case MAX:
            if (value == null) {
               this.unsetMax();
            } else {
               this.setMax((Long)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case MIN:
            return this.getMin();
         case MAX:
            return this.getMax();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case MIN:
               return this.isSetMin();
            case MAX:
               return this.isSetMax();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof HeartbeatTxnRangeRequest ? this.equals((HeartbeatTxnRangeRequest)that) : false;
   }

   public boolean equals(HeartbeatTxnRangeRequest that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_min = true;
         boolean that_present_min = true;
         if (this_present_min || that_present_min) {
            if (!this_present_min || !that_present_min) {
               return false;
            }

            if (this.min != that.min) {
               return false;
            }
         }

         boolean this_present_max = true;
         boolean that_present_max = true;
         if (this_present_max || that_present_max) {
            if (!this_present_max || !that_present_max) {
               return false;
            }

            if (this.max != that.max) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.min);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.max);
      return hashCode;
   }

   public int compareTo(HeartbeatTxnRangeRequest other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMin(), other.isSetMin());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMin()) {
               lastComparison = TBaseHelper.compareTo(this.min, other.min);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetMax(), other.isSetMax());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetMax()) {
                  lastComparison = TBaseHelper.compareTo(this.max, other.max);
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
      return HeartbeatTxnRangeRequest._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("HeartbeatTxnRangeRequest(");
      boolean first = true;
      sb.append("min:");
      sb.append(this.min);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("max:");
      sb.append(this.max);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetMin()) {
         throw new TProtocolException("Required field 'min' is unset! Struct:" + this.toString());
      } else if (!this.isSetMax()) {
         throw new TProtocolException("Required field 'max' is unset! Struct:" + this.toString());
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
      tmpMap.put(HeartbeatTxnRangeRequest._Fields.MIN, new FieldMetaData("min", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(HeartbeatTxnRangeRequest._Fields.MAX, new FieldMetaData("max", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(HeartbeatTxnRangeRequest.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MIN((short)1, "min"),
      MAX((short)2, "max");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MIN;
            case 2:
               return MAX;
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

   private static class HeartbeatTxnRangeRequestStandardSchemeFactory implements SchemeFactory {
      private HeartbeatTxnRangeRequestStandardSchemeFactory() {
      }

      public HeartbeatTxnRangeRequestStandardScheme getScheme() {
         return new HeartbeatTxnRangeRequestStandardScheme();
      }
   }

   private static class HeartbeatTxnRangeRequestStandardScheme extends StandardScheme {
      private HeartbeatTxnRangeRequestStandardScheme() {
      }

      public void read(TProtocol iprot, HeartbeatTxnRangeRequest struct) throws TException {
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
                     struct.min = iprot.readI64();
                     struct.setMinIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 10) {
                     struct.max = iprot.readI64();
                     struct.setMaxIsSet(true);
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

      public void write(TProtocol oprot, HeartbeatTxnRangeRequest struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(HeartbeatTxnRangeRequest.STRUCT_DESC);
         oprot.writeFieldBegin(HeartbeatTxnRangeRequest.MIN_FIELD_DESC);
         oprot.writeI64(struct.min);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(HeartbeatTxnRangeRequest.MAX_FIELD_DESC);
         oprot.writeI64(struct.max);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class HeartbeatTxnRangeRequestTupleSchemeFactory implements SchemeFactory {
      private HeartbeatTxnRangeRequestTupleSchemeFactory() {
      }

      public HeartbeatTxnRangeRequestTupleScheme getScheme() {
         return new HeartbeatTxnRangeRequestTupleScheme();
      }
   }

   private static class HeartbeatTxnRangeRequestTupleScheme extends TupleScheme {
      private HeartbeatTxnRangeRequestTupleScheme() {
      }

      public void write(TProtocol prot, HeartbeatTxnRangeRequest struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.min);
         oprot.writeI64(struct.max);
      }

      public void read(TProtocol prot, HeartbeatTxnRangeRequest struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.min = iprot.readI64();
         struct.setMinIsSet(true);
         struct.max = iprot.readI64();
         struct.setMaxIsSet(true);
      }
   }
}
