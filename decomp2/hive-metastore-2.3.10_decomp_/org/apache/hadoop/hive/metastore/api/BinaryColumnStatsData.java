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

public class BinaryColumnStatsData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("BinaryColumnStatsData");
   private static final TField MAX_COL_LEN_FIELD_DESC = new TField("maxColLen", (byte)10, (short)1);
   private static final TField AVG_COL_LEN_FIELD_DESC = new TField("avgColLen", (byte)4, (short)2);
   private static final TField NUM_NULLS_FIELD_DESC = new TField("numNulls", (byte)10, (short)3);
   private static final TField BIT_VECTORS_FIELD_DESC = new TField("bitVectors", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new BinaryColumnStatsDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new BinaryColumnStatsDataTupleSchemeFactory();
   private long maxColLen;
   private double avgColLen;
   private long numNulls;
   @Nullable
   private String bitVectors;
   private static final int __MAXCOLLEN_ISSET_ID = 0;
   private static final int __AVGCOLLEN_ISSET_ID = 1;
   private static final int __NUMNULLS_ISSET_ID = 2;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public BinaryColumnStatsData() {
      this.__isset_bitfield = 0;
   }

   public BinaryColumnStatsData(long maxColLen, double avgColLen, long numNulls) {
      this();
      this.maxColLen = maxColLen;
      this.setMaxColLenIsSet(true);
      this.avgColLen = avgColLen;
      this.setAvgColLenIsSet(true);
      this.numNulls = numNulls;
      this.setNumNullsIsSet(true);
   }

   public BinaryColumnStatsData(BinaryColumnStatsData other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.maxColLen = other.maxColLen;
      this.avgColLen = other.avgColLen;
      this.numNulls = other.numNulls;
      if (other.isSetBitVectors()) {
         this.bitVectors = other.bitVectors;
      }

   }

   public BinaryColumnStatsData deepCopy() {
      return new BinaryColumnStatsData(this);
   }

   public void clear() {
      this.setMaxColLenIsSet(false);
      this.maxColLen = 0L;
      this.setAvgColLenIsSet(false);
      this.avgColLen = (double)0.0F;
      this.setNumNullsIsSet(false);
      this.numNulls = 0L;
      this.bitVectors = null;
   }

   public long getMaxColLen() {
      return this.maxColLen;
   }

   public void setMaxColLen(long maxColLen) {
      this.maxColLen = maxColLen;
      this.setMaxColLenIsSet(true);
   }

   public void unsetMaxColLen() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetMaxColLen() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setMaxColLenIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public double getAvgColLen() {
      return this.avgColLen;
   }

   public void setAvgColLen(double avgColLen) {
      this.avgColLen = avgColLen;
      this.setAvgColLenIsSet(true);
   }

   public void unsetAvgColLen() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetAvgColLen() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setAvgColLenIsSet(boolean value) {
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
         case MAX_COL_LEN:
            if (value == null) {
               this.unsetMaxColLen();
            } else {
               this.setMaxColLen((Long)value);
            }
            break;
         case AVG_COL_LEN:
            if (value == null) {
               this.unsetAvgColLen();
            } else {
               this.setAvgColLen((Double)value);
            }
            break;
         case NUM_NULLS:
            if (value == null) {
               this.unsetNumNulls();
            } else {
               this.setNumNulls((Long)value);
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
         case MAX_COL_LEN:
            return this.getMaxColLen();
         case AVG_COL_LEN:
            return this.getAvgColLen();
         case NUM_NULLS:
            return this.getNumNulls();
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
            case MAX_COL_LEN:
               return this.isSetMaxColLen();
            case AVG_COL_LEN:
               return this.isSetAvgColLen();
            case NUM_NULLS:
               return this.isSetNumNulls();
            case BIT_VECTORS:
               return this.isSetBitVectors();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof BinaryColumnStatsData ? this.equals((BinaryColumnStatsData)that) : false;
   }

   public boolean equals(BinaryColumnStatsData that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_maxColLen = true;
         boolean that_present_maxColLen = true;
         if (this_present_maxColLen || that_present_maxColLen) {
            if (!this_present_maxColLen || !that_present_maxColLen) {
               return false;
            }

            if (this.maxColLen != that.maxColLen) {
               return false;
            }
         }

         boolean this_present_avgColLen = true;
         boolean that_present_avgColLen = true;
         if (this_present_avgColLen || that_present_avgColLen) {
            if (!this_present_avgColLen || !that_present_avgColLen) {
               return false;
            }

            if (this.avgColLen != that.avgColLen) {
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
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.maxColLen);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.avgColLen);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numNulls);
      hashCode = hashCode * 8191 + (this.isSetBitVectors() ? 131071 : 524287);
      if (this.isSetBitVectors()) {
         hashCode = hashCode * 8191 + this.bitVectors.hashCode();
      }

      return hashCode;
   }

   public int compareTo(BinaryColumnStatsData other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetMaxColLen(), other.isSetMaxColLen());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetMaxColLen()) {
               lastComparison = TBaseHelper.compareTo(this.maxColLen, other.maxColLen);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetAvgColLen(), other.isSetAvgColLen());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetAvgColLen()) {
                  lastComparison = TBaseHelper.compareTo(this.avgColLen, other.avgColLen);
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

   @Nullable
   public _Fields fieldForId(int fieldId) {
      return BinaryColumnStatsData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("BinaryColumnStatsData(");
      boolean first = true;
      sb.append("maxColLen:");
      sb.append(this.maxColLen);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("avgColLen:");
      sb.append(this.avgColLen);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("numNulls:");
      sb.append(this.numNulls);
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
      if (!this.isSetMaxColLen()) {
         throw new TProtocolException("Required field 'maxColLen' is unset! Struct:" + this.toString());
      } else if (!this.isSetAvgColLen()) {
         throw new TProtocolException("Required field 'avgColLen' is unset! Struct:" + this.toString());
      } else if (!this.isSetNumNulls()) {
         throw new TProtocolException("Required field 'numNulls' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{BinaryColumnStatsData._Fields.BIT_VECTORS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(BinaryColumnStatsData._Fields.MAX_COL_LEN, new FieldMetaData("maxColLen", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(BinaryColumnStatsData._Fields.AVG_COL_LEN, new FieldMetaData("avgColLen", (byte)1, new FieldValueMetaData((byte)4)));
      tmpMap.put(BinaryColumnStatsData._Fields.NUM_NULLS, new FieldMetaData("numNulls", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(BinaryColumnStatsData._Fields.BIT_VECTORS, new FieldMetaData("bitVectors", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(BinaryColumnStatsData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      MAX_COL_LEN((short)1, "maxColLen"),
      AVG_COL_LEN((short)2, "avgColLen"),
      NUM_NULLS((short)3, "numNulls"),
      BIT_VECTORS((short)4, "bitVectors");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return MAX_COL_LEN;
            case 2:
               return AVG_COL_LEN;
            case 3:
               return NUM_NULLS;
            case 4:
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

   private static class BinaryColumnStatsDataStandardSchemeFactory implements SchemeFactory {
      private BinaryColumnStatsDataStandardSchemeFactory() {
      }

      public BinaryColumnStatsDataStandardScheme getScheme() {
         return new BinaryColumnStatsDataStandardScheme();
      }
   }

   private static class BinaryColumnStatsDataStandardScheme extends StandardScheme {
      private BinaryColumnStatsDataStandardScheme() {
      }

      public void read(TProtocol iprot, BinaryColumnStatsData struct) throws TException {
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
                     struct.maxColLen = iprot.readI64();
                     struct.setMaxColLenIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 4) {
                     struct.avgColLen = iprot.readDouble();
                     struct.setAvgColLenIsSet(true);
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

      public void write(TProtocol oprot, BinaryColumnStatsData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(BinaryColumnStatsData.STRUCT_DESC);
         oprot.writeFieldBegin(BinaryColumnStatsData.MAX_COL_LEN_FIELD_DESC);
         oprot.writeI64(struct.maxColLen);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(BinaryColumnStatsData.AVG_COL_LEN_FIELD_DESC);
         oprot.writeDouble(struct.avgColLen);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(BinaryColumnStatsData.NUM_NULLS_FIELD_DESC);
         oprot.writeI64(struct.numNulls);
         oprot.writeFieldEnd();
         if (struct.bitVectors != null && struct.isSetBitVectors()) {
            oprot.writeFieldBegin(BinaryColumnStatsData.BIT_VECTORS_FIELD_DESC);
            oprot.writeString(struct.bitVectors);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class BinaryColumnStatsDataTupleSchemeFactory implements SchemeFactory {
      private BinaryColumnStatsDataTupleSchemeFactory() {
      }

      public BinaryColumnStatsDataTupleScheme getScheme() {
         return new BinaryColumnStatsDataTupleScheme();
      }
   }

   private static class BinaryColumnStatsDataTupleScheme extends TupleScheme {
      private BinaryColumnStatsDataTupleScheme() {
      }

      public void write(TProtocol prot, BinaryColumnStatsData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.maxColLen);
         oprot.writeDouble(struct.avgColLen);
         oprot.writeI64(struct.numNulls);
         BitSet optionals = new BitSet();
         if (struct.isSetBitVectors()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetBitVectors()) {
            oprot.writeString(struct.bitVectors);
         }

      }

      public void read(TProtocol prot, BinaryColumnStatsData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.maxColLen = iprot.readI64();
         struct.setMaxColLenIsSet(true);
         struct.avgColLen = iprot.readDouble();
         struct.setAvgColLenIsSet(true);
         struct.numNulls = iprot.readI64();
         struct.setNumNullsIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.bitVectors = iprot.readString();
            struct.setBitVectorsIsSet(true);
         }

      }
   }
}
