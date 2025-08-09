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

public class Date implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("Date");
   private static final TField DAYS_SINCE_EPOCH_FIELD_DESC = new TField("daysSinceEpoch", (byte)10, (short)1);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DateStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DateTupleSchemeFactory();
   private long daysSinceEpoch;
   private static final int __DAYSSINCEEPOCH_ISSET_ID = 0;
   private byte __isset_bitfield;
   public static final Map metaDataMap;

   public Date() {
      this.__isset_bitfield = 0;
   }

   public Date(long daysSinceEpoch) {
      this();
      this.daysSinceEpoch = daysSinceEpoch;
      this.setDaysSinceEpochIsSet(true);
   }

   public Date(Date other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.daysSinceEpoch = other.daysSinceEpoch;
   }

   public Date deepCopy() {
      return new Date(this);
   }

   public void clear() {
      this.setDaysSinceEpochIsSet(false);
      this.daysSinceEpoch = 0L;
   }

   public long getDaysSinceEpoch() {
      return this.daysSinceEpoch;
   }

   public void setDaysSinceEpoch(long daysSinceEpoch) {
      this.daysSinceEpoch = daysSinceEpoch;
      this.setDaysSinceEpochIsSet(true);
   }

   public void unsetDaysSinceEpoch() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetDaysSinceEpoch() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setDaysSinceEpochIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case DAYS_SINCE_EPOCH:
            if (value == null) {
               this.unsetDaysSinceEpoch();
            } else {
               this.setDaysSinceEpoch((Long)value);
            }
         default:
      }
   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case DAYS_SINCE_EPOCH:
            return this.getDaysSinceEpoch();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case DAYS_SINCE_EPOCH:
               return this.isSetDaysSinceEpoch();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof Date ? this.equals((Date)that) : false;
   }

   public boolean equals(Date that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_daysSinceEpoch = true;
         boolean that_present_daysSinceEpoch = true;
         if (this_present_daysSinceEpoch || that_present_daysSinceEpoch) {
            if (!this_present_daysSinceEpoch || !that_present_daysSinceEpoch) {
               return false;
            }

            if (this.daysSinceEpoch != that.daysSinceEpoch) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.daysSinceEpoch);
      return hashCode;
   }

   public int compareTo(Date other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetDaysSinceEpoch(), other.isSetDaysSinceEpoch());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetDaysSinceEpoch()) {
               lastComparison = TBaseHelper.compareTo(this.daysSinceEpoch, other.daysSinceEpoch);
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
      return Date._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("Date(");
      boolean first = true;
      sb.append("daysSinceEpoch:");
      sb.append(this.daysSinceEpoch);
      first = false;
      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetDaysSinceEpoch()) {
         throw new TProtocolException("Required field 'daysSinceEpoch' is unset! Struct:" + this.toString());
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
      tmpMap.put(Date._Fields.DAYS_SINCE_EPOCH, new FieldMetaData("daysSinceEpoch", (byte)1, new FieldValueMetaData((byte)10)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(Date.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      DAYS_SINCE_EPOCH((short)1, "daysSinceEpoch");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return DAYS_SINCE_EPOCH;
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

   private static class DateStandardSchemeFactory implements SchemeFactory {
      private DateStandardSchemeFactory() {
      }

      public DateStandardScheme getScheme() {
         return new DateStandardScheme();
      }
   }

   private static class DateStandardScheme extends StandardScheme {
      private DateStandardScheme() {
      }

      public void read(TProtocol iprot, Date struct) throws TException {
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
                     struct.daysSinceEpoch = iprot.readI64();
                     struct.setDaysSinceEpochIsSet(true);
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

      public void write(TProtocol oprot, Date struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(Date.STRUCT_DESC);
         oprot.writeFieldBegin(Date.DAYS_SINCE_EPOCH_FIELD_DESC);
         oprot.writeI64(struct.daysSinceEpoch);
         oprot.writeFieldEnd();
         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DateTupleSchemeFactory implements SchemeFactory {
      private DateTupleSchemeFactory() {
      }

      public DateTupleScheme getScheme() {
         return new DateTupleScheme();
      }
   }

   private static class DateTupleScheme extends TupleScheme {
      private DateTupleScheme() {
      }

      public void write(TProtocol prot, Date struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.daysSinceEpoch);
      }

      public void read(TProtocol prot, Date struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.daysSinceEpoch = iprot.readI64();
         struct.setDaysSinceEpochIsSet(true);
      }
   }
}
