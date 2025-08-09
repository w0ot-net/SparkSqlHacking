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

public class DoubleColumnStatsData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DoubleColumnStatsData");
   private static final TField LOW_VALUE_FIELD_DESC = new TField("lowValue", (byte)4, (short)1);
   private static final TField HIGH_VALUE_FIELD_DESC = new TField("highValue", (byte)4, (short)2);
   private static final TField NUM_NULLS_FIELD_DESC = new TField("numNulls", (byte)10, (short)3);
   private static final TField NUM_DVS_FIELD_DESC = new TField("numDVs", (byte)10, (short)4);
   private static final TField BIT_VECTORS_FIELD_DESC = new TField("bitVectors", (byte)11, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DoubleColumnStatsDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DoubleColumnStatsDataTupleSchemeFactory();
   private double lowValue;
   private double highValue;
   private long numNulls;
   private long numDVs;
   @Nullable
   private String bitVectors;
   private static final int __LOWVALUE_ISSET_ID = 0;
   private static final int __HIGHVALUE_ISSET_ID = 1;
   private static final int __NUMNULLS_ISSET_ID = 2;
   private static final int __NUMDVS_ISSET_ID = 3;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DoubleColumnStatsData() {
      this.__isset_bitfield = 0;
   }

   public DoubleColumnStatsData(long numNulls, long numDVs) {
      this();
      this.numNulls = numNulls;
      this.setNumNullsIsSet(true);
      this.numDVs = numDVs;
      this.setNumDVsIsSet(true);
   }

   public DoubleColumnStatsData(DoubleColumnStatsData other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.lowValue = other.lowValue;
      this.highValue = other.highValue;
      this.numNulls = other.numNulls;
      this.numDVs = other.numDVs;
      if (other.isSetBitVectors()) {
         this.bitVectors = other.bitVectors;
      }

   }

   public DoubleColumnStatsData deepCopy() {
      return new DoubleColumnStatsData(this);
   }

   public void clear() {
      this.setLowValueIsSet(false);
      this.lowValue = (double)0.0F;
      this.setHighValueIsSet(false);
      this.highValue = (double)0.0F;
      this.setNumNullsIsSet(false);
      this.numNulls = 0L;
      this.setNumDVsIsSet(false);
      this.numDVs = 0L;
      this.bitVectors = null;
   }

   public double getLowValue() {
      return this.lowValue;
   }

   public void setLowValue(double lowValue) {
      this.lowValue = lowValue;
      this.setLowValueIsSet(true);
   }

   public void unsetLowValue() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetLowValue() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setLowValueIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public double getHighValue() {
      return this.highValue;
   }

   public void setHighValue(double highValue) {
      this.highValue = highValue;
      this.setHighValueIsSet(true);
   }

   public void unsetHighValue() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetHighValue() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setHighValueIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 1, value);
   }

   public long getNumNulls() {
      return this.numNulls;
   }

   public void setNumNulls(long numNulls) {
      this.numNulls = numNulls;
      this.setNumNullsIsSet(true);
   }

   public void unsetNumNulls() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 2);
   }

   public boolean isSetNumNulls() {
      return EncodingUtils.testBit(this.__isset_bitfield, 2);
   }

   public void setNumNullsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 2, value);
   }

   public long getNumDVs() {
      return this.numDVs;
   }

   public void setNumDVs(long numDVs) {
      this.numDVs = numDVs;
      this.setNumDVsIsSet(true);
   }

   public void unsetNumDVs() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 3);
   }

   public boolean isSetNumDVs() {
      return EncodingUtils.testBit(this.__isset_bitfield, 3);
   }

   public void setNumDVsIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 3, value);
   }

   @Nullable
   public String getBitVectors() {
      return this.bitVectors;
   }

   public void setBitVectors(@Nullable String bitVectors) {
      this.bitVectors = bitVectors;
   }

   public void unsetBitVectors() {
      this.bitVectors = null;
   }

   public boolean isSetBitVectors() {
      return this.bitVectors != null;
   }

   public void setBitVectorsIsSet(boolean value) {
      if (!value) {
         this.bitVectors = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case LOW_VALUE:
            if (value == null) {
               this.unsetLowValue();
            } else {
               this.setLowValue((Double)value);
            }
            break;
         case HIGH_VALUE:
            if (value == null) {
               this.unsetHighValue();
            } else {
               this.setHighValue((Double)value);
            }
            break;
         case NUM_NULLS:
            if (value == null) {
               this.unsetNumNulls();
            } else {
               this.setNumNulls((Long)value);
            }
            break;
         case NUM_DVS:
            if (value == null) {
               this.unsetNumDVs();
            } else {
               this.setNumDVs((Long)value);
            }
            break;
         case BIT_VECTORS:
            if (value == null) {
               this.unsetBitVectors();
            } else {
               this.setBitVectors((String)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case LOW_VALUE:
            return this.getLowValue();
         case HIGH_VALUE:
            return this.getHighValue();
         case NUM_NULLS:
            return this.getNumNulls();
         case NUM_DVS:
            return this.getNumDVs();
         case BIT_VECTORS:
            return this.getBitVectors();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case LOW_VALUE:
               return this.isSetLowValue();
            case HIGH_VALUE:
               return this.isSetHighValue();
            case NUM_NULLS:
               return this.isSetNumNulls();
            case NUM_DVS:
               return this.isSetNumDVs();
            case BIT_VECTORS:
               return this.isSetBitVectors();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DoubleColumnStatsData ? this.equals((DoubleColumnStatsData)that) : false;
   }

   public boolean equals(DoubleColumnStatsData that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_lowValue = this.isSetLowValue();
         boolean that_present_lowValue = that.isSetLowValue();
         if (this_present_lowValue || that_present_lowValue) {
            if (!this_present_lowValue || !that_present_lowValue) {
               return false;
            }

            if (this.lowValue != that.lowValue) {
               return false;
            }
         }

         boolean this_present_highValue = this.isSetHighValue();
         boolean that_present_highValue = that.isSetHighValue();
         if (this_present_highValue || that_present_highValue) {
            if (!this_present_highValue || !that_present_highValue) {
               return false;
            }

            if (this.highValue != that.highValue) {
               return false;
            }
         }

         boolean this_present_numNulls = true;
         boolean that_present_numNulls = true;
         if (this_present_numNulls || that_present_numNulls) {
            if (!this_present_numNulls || !that_present_numNulls) {
               return false;
            }

            if (this.numNulls != that.numNulls) {
               return false;
            }
         }

         boolean this_present_numDVs = true;
         boolean that_present_numDVs = true;
         if (this_present_numDVs || that_present_numDVs) {
            if (!this_present_numDVs || !that_present_numDVs) {
               return false;
            }

            if (this.numDVs != that.numDVs) {
               return false;
            }
         }

         boolean this_present_bitVectors = this.isSetBitVectors();
         boolean that_present_bitVectors = that.isSetBitVectors();
         if (this_present_bitVectors || that_present_bitVectors) {
            if (!this_present_bitVectors || !that_present_bitVectors) {
               return false;
            }

            if (!this.bitVectors.equals(that.bitVectors)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetLowValue() ? 131071 : 524287);
      if (this.isSetLowValue()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.lowValue);
      }

      hashCode = hashCode * 8191 + (this.isSetHighValue() ? 131071 : 524287);
      if (this.isSetHighValue()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.highValue);
      }

      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numNulls);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numDVs);
      hashCode = hashCode * 8191 + (this.isSetBitVectors() ? 131071 : 524287);
      if (this.isSetBitVectors()) {
         hashCode = hashCode * 8191 + this.bitVectors.hashCode();
      }

      return hashCode;
   }

   public int compareTo(DoubleColumnStatsData other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetLowValue(), other.isSetLowValue());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetLowValue()) {
               lastComparison = TBaseHelper.compareTo(this.lowValue, other.lowValue);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetHighValue(), other.isSetHighValue());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetHighValue()) {
                  lastComparison = TBaseHelper.compareTo(this.highValue, other.highValue);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetNumNulls(), other.isSetNumNulls());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetNumNulls()) {
                     lastComparison = TBaseHelper.compareTo(this.numNulls, other.numNulls);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetNumDVs(), other.isSetNumDVs());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetNumDVs()) {
                        lastComparison = TBaseHelper.compareTo(this.numDVs, other.numDVs);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetBitVectors(), other.isSetBitVectors());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetBitVectors()) {
                           lastComparison = TBaseHelper.compareTo(this.bitVectors, other.bitVectors);
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
      }
   }

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return DoubleColumnStatsData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DoubleColumnStatsData(");
      boolean first = true;
      if (this.isSetLowValue()) {
         sb.append("lowValue:");
         sb.append(this.lowValue);
         first = false;
      }

      if (this.isSetHighValue()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("highValue:");
         sb.append(this.highValue);
         first = false;
      }

      if (!first) {
         sb.append(", ");
      }

      sb.append("numNulls:");
      sb.append(this.numNulls);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("numDVs:");
      sb.append(this.numDVs);
      first = false;
      if (this.isSetBitVectors()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("bitVectors:");
         if (this.bitVectors == null) {
            sb.append("null");
         } else {
            sb.append(this.bitVectors);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (!this.isSetNumNulls()) {
         throw new TProtocolException("Required field 'numNulls' is unset! Struct:" + this.toString());
      } else if (!this.isSetNumDVs()) {
         throw new TProtocolException("Required field 'numDVs' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{DoubleColumnStatsData._Fields.LOW_VALUE, DoubleColumnStatsData._Fields.HIGH_VALUE, DoubleColumnStatsData._Fields.BIT_VECTORS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DoubleColumnStatsData._Fields.LOW_VALUE, new FieldMetaData("lowValue", (byte)2, new FieldValueMetaData((byte)4)));
      tmpMap.put(DoubleColumnStatsData._Fields.HIGH_VALUE, new FieldMetaData("highValue", (byte)2, new FieldValueMetaData((byte)4)));
      tmpMap.put(DoubleColumnStatsData._Fields.NUM_NULLS, new FieldMetaData("numNulls", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(DoubleColumnStatsData._Fields.NUM_DVS, new FieldMetaData("numDVs", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(DoubleColumnStatsData._Fields.BIT_VECTORS, new FieldMetaData("bitVectors", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DoubleColumnStatsData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      LOW_VALUE((short)1, "lowValue"),
      HIGH_VALUE((short)2, "highValue"),
      NUM_NULLS((short)3, "numNulls"),
      NUM_DVS((short)4, "numDVs"),
      BIT_VECTORS((short)5, "bitVectors");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return LOW_VALUE;
            case 2:
               return HIGH_VALUE;
            case 3:
               return NUM_NULLS;
            case 4:
               return NUM_DVS;
            case 5:
               return BIT_VECTORS;
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

   private static class DoubleColumnStatsDataStandardSchemeFactory implements SchemeFactory {
      private DoubleColumnStatsDataStandardSchemeFactory() {
      }

      public DoubleColumnStatsDataStandardScheme getScheme() {
         return new DoubleColumnStatsDataStandardScheme();
      }
   }

   private static class DoubleColumnStatsDataStandardScheme extends StandardScheme {
      private DoubleColumnStatsDataStandardScheme() {
      }

      public void read(TProtocol iprot, DoubleColumnStatsData struct) throws TException {
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
                  if (schemeField.type == 4) {
                     struct.lowValue = iprot.readDouble();
                     struct.setLowValueIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 4) {
                     struct.highValue = iprot.readDouble();
                     struct.setHighValueIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 10) {
                     struct.numNulls = iprot.readI64();
                     struct.setNumNullsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 10) {
                     struct.numDVs = iprot.readI64();
                     struct.setNumDVsIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 11) {
                     struct.bitVectors = iprot.readString();
                     struct.setBitVectorsIsSet(true);
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

      public void write(TProtocol oprot, DoubleColumnStatsData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DoubleColumnStatsData.STRUCT_DESC);
         if (struct.isSetLowValue()) {
            oprot.writeFieldBegin(DoubleColumnStatsData.LOW_VALUE_FIELD_DESC);
            oprot.writeDouble(struct.lowValue);
            oprot.writeFieldEnd();
         }

         if (struct.isSetHighValue()) {
            oprot.writeFieldBegin(DoubleColumnStatsData.HIGH_VALUE_FIELD_DESC);
            oprot.writeDouble(struct.highValue);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldBegin(DoubleColumnStatsData.NUM_NULLS_FIELD_DESC);
         oprot.writeI64(struct.numNulls);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(DoubleColumnStatsData.NUM_DVS_FIELD_DESC);
         oprot.writeI64(struct.numDVs);
         oprot.writeFieldEnd();
         if (struct.bitVectors != null && struct.isSetBitVectors()) {
            oprot.writeFieldBegin(DoubleColumnStatsData.BIT_VECTORS_FIELD_DESC);
            oprot.writeString(struct.bitVectors);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DoubleColumnStatsDataTupleSchemeFactory implements SchemeFactory {
      private DoubleColumnStatsDataTupleSchemeFactory() {
      }

      public DoubleColumnStatsDataTupleScheme getScheme() {
         return new DoubleColumnStatsDataTupleScheme();
      }
   }

   private static class DoubleColumnStatsDataTupleScheme extends TupleScheme {
      private DoubleColumnStatsDataTupleScheme() {
      }

      public void write(TProtocol prot, DoubleColumnStatsData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.numNulls);
         oprot.writeI64(struct.numDVs);
         BitSet optionals = new BitSet();
         if (struct.isSetLowValue()) {
            optionals.set(0);
         }

         if (struct.isSetHighValue()) {
            optionals.set(1);
         }

         if (struct.isSetBitVectors()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetLowValue()) {
            oprot.writeDouble(struct.lowValue);
         }

         if (struct.isSetHighValue()) {
            oprot.writeDouble(struct.highValue);
         }

         if (struct.isSetBitVectors()) {
            oprot.writeString(struct.bitVectors);
         }

      }

      public void read(TProtocol prot, DoubleColumnStatsData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.numNulls = iprot.readI64();
         struct.setNumNullsIsSet(true);
         struct.numDVs = iprot.readI64();
         struct.setNumDVsIsSet(true);
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.lowValue = iprot.readDouble();
            struct.setLowValueIsSet(true);
         }

         if (incoming.get(1)) {
            struct.highValue = iprot.readDouble();
            struct.setHighValueIsSet(true);
         }

         if (incoming.get(2)) {
            struct.bitVectors = iprot.readString();
            struct.setBitVectorsIsSet(true);
         }

      }
   }
}
