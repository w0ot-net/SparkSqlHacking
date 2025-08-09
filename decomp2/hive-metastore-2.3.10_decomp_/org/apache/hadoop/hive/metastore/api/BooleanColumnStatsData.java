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

public class BooleanColumnStatsData implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("BooleanColumnStatsData");
   private static final TField NUM_TRUES_FIELD_DESC = new TField("numTrues", (byte)10, (short)1);
   private static final TField NUM_FALSES_FIELD_DESC = new TField("numFalses", (byte)10, (short)2);
   private static final TField NUM_NULLS_FIELD_DESC = new TField("numNulls", (byte)10, (short)3);
   private static final TField BIT_VECTORS_FIELD_DESC = new TField("bitVectors", (byte)11, (short)4);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new BooleanColumnStatsDataStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new BooleanColumnStatsDataTupleSchemeFactory();
   private long numTrues;
   private long numFalses;
   private long numNulls;
   @Nullable
   private String bitVectors;
   private static final int __NUMTRUES_ISSET_ID = 0;
   private static final int __NUMFALSES_ISSET_ID = 1;
   private static final int __NUMNULLS_ISSET_ID = 2;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public BooleanColumnStatsData() {
      this.__isset_bitfield = 0;
   }

   public BooleanColumnStatsData(long numTrues, long numFalses, long numNulls) {
      this();
      this.numTrues = numTrues;
      this.setNumTruesIsSet(true);
      this.numFalses = numFalses;
      this.setNumFalsesIsSet(true);
      this.numNulls = numNulls;
      this.setNumNullsIsSet(true);
   }

   public BooleanColumnStatsData(BooleanColumnStatsData other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.numTrues = other.numTrues;
      this.numFalses = other.numFalses;
      this.numNulls = other.numNulls;
      if (other.isSetBitVectors()) {
         this.bitVectors = other.bitVectors;
      }

   }

   public BooleanColumnStatsData deepCopy() {
      return new BooleanColumnStatsData(this);
   }

   public void clear() {
      this.setNumTruesIsSet(false);
      this.numTrues = 0L;
      this.setNumFalsesIsSet(false);
      this.numFalses = 0L;
      this.setNumNullsIsSet(false);
      this.numNulls = 0L;
      this.bitVectors = null;
   }

   public long getNumTrues() {
      return this.numTrues;
   }

   public void setNumTrues(long numTrues) {
      this.numTrues = numTrues;
      this.setNumTruesIsSet(true);
   }

   public void unsetNumTrues() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 0);
   }

   public boolean isSetNumTrues() {
      return EncodingUtils.testBit(this.__isset_bitfield, 0);
   }

   public void setNumTruesIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit(this.__isset_bitfield, 0, value);
   }

   public long getNumFalses() {
      return this.numFalses;
   }

   public void setNumFalses(long numFalses) {
      this.numFalses = numFalses;
      this.setNumFalsesIsSet(true);
   }

   public void unsetNumFalses() {
      this.__isset_bitfield = EncodingUtils.clearBit(this.__isset_bitfield, 1);
   }

   public boolean isSetNumFalses() {
      return EncodingUtils.testBit(this.__isset_bitfield, 1);
   }

   public void setNumFalsesIsSet(boolean value) {
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
         case NUM_TRUES:
            if (value == null) {
               this.unsetNumTrues();
            } else {
               this.setNumTrues((Long)value);
            }
            break;
         case NUM_FALSES:
            if (value == null) {
               this.unsetNumFalses();
            } else {
               this.setNumFalses((Long)value);
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
         case NUM_TRUES:
            return this.getNumTrues();
         case NUM_FALSES:
            return this.getNumFalses();
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
            case NUM_TRUES:
               return this.isSetNumTrues();
            case NUM_FALSES:
               return this.isSetNumFalses();
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
      return that instanceof BooleanColumnStatsData ? this.equals((BooleanColumnStatsData)that) : false;
   }

   public boolean equals(BooleanColumnStatsData that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_numTrues = true;
         boolean that_present_numTrues = true;
         if (this_present_numTrues || that_present_numTrues) {
            if (!this_present_numTrues || !that_present_numTrues) {
               return false;
            }

            if (this.numTrues != that.numTrues) {
               return false;
            }
         }

         boolean this_present_numFalses = true;
         boolean that_present_numFalses = true;
         if (this_present_numFalses || that_present_numFalses) {
            if (!this_present_numFalses || !that_present_numFalses) {
               return false;
            }

            if (this.numFalses != that.numFalses) {
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
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numTrues);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numFalses);
      hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.numNulls);
      hashCode = hashCode * 8191 + (this.isSetBitVectors() ? 131071 : 524287);
      if (this.isSetBitVectors()) {
         hashCode = hashCode * 8191 + this.bitVectors.hashCode();
      }

      return hashCode;
   }

   public int compareTo(BooleanColumnStatsData other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetNumTrues(), other.isSetNumTrues());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetNumTrues()) {
               lastComparison = TBaseHelper.compareTo(this.numTrues, other.numTrues);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetNumFalses(), other.isSetNumFalses());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetNumFalses()) {
                  lastComparison = TBaseHelper.compareTo(this.numFalses, other.numFalses);
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
      return BooleanColumnStatsData._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("BooleanColumnStatsData(");
      boolean first = true;
      sb.append("numTrues:");
      sb.append(this.numTrues);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("numFalses:");
      sb.append(this.numFalses);
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
      if (!this.isSetNumTrues()) {
         throw new TProtocolException("Required field 'numTrues' is unset! Struct:" + this.toString());
      } else if (!this.isSetNumFalses()) {
         throw new TProtocolException("Required field 'numFalses' is unset! Struct:" + this.toString());
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
      optionals = new _Fields[]{BooleanColumnStatsData._Fields.BIT_VECTORS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(BooleanColumnStatsData._Fields.NUM_TRUES, new FieldMetaData("numTrues", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(BooleanColumnStatsData._Fields.NUM_FALSES, new FieldMetaData("numFalses", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(BooleanColumnStatsData._Fields.NUM_NULLS, new FieldMetaData("numNulls", (byte)1, new FieldValueMetaData((byte)10)));
      tmpMap.put(BooleanColumnStatsData._Fields.BIT_VECTORS, new FieldMetaData("bitVectors", (byte)2, new FieldValueMetaData((byte)11)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(BooleanColumnStatsData.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NUM_TRUES((short)1, "numTrues"),
      NUM_FALSES((short)2, "numFalses"),
      NUM_NULLS((short)3, "numNulls"),
      BIT_VECTORS((short)4, "bitVectors");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NUM_TRUES;
            case 2:
               return NUM_FALSES;
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

   private static class BooleanColumnStatsDataStandardSchemeFactory implements SchemeFactory {
      private BooleanColumnStatsDataStandardSchemeFactory() {
      }

      public BooleanColumnStatsDataStandardScheme getScheme() {
         return new BooleanColumnStatsDataStandardScheme();
      }
   }

   private static class BooleanColumnStatsDataStandardScheme extends StandardScheme {
      private BooleanColumnStatsDataStandardScheme() {
      }

      public void read(TProtocol iprot, BooleanColumnStatsData struct) throws TException {
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
                     struct.numTrues = iprot.readI64();
                     struct.setNumTruesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 10) {
                     struct.numFalses = iprot.readI64();
                     struct.setNumFalsesIsSet(true);
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

      public void write(TProtocol oprot, BooleanColumnStatsData struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(BooleanColumnStatsData.STRUCT_DESC);
         oprot.writeFieldBegin(BooleanColumnStatsData.NUM_TRUES_FIELD_DESC);
         oprot.writeI64(struct.numTrues);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(BooleanColumnStatsData.NUM_FALSES_FIELD_DESC);
         oprot.writeI64(struct.numFalses);
         oprot.writeFieldEnd();
         oprot.writeFieldBegin(BooleanColumnStatsData.NUM_NULLS_FIELD_DESC);
         oprot.writeI64(struct.numNulls);
         oprot.writeFieldEnd();
         if (struct.bitVectors != null && struct.isSetBitVectors()) {
            oprot.writeFieldBegin(BooleanColumnStatsData.BIT_VECTORS_FIELD_DESC);
            oprot.writeString(struct.bitVectors);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class BooleanColumnStatsDataTupleSchemeFactory implements SchemeFactory {
      private BooleanColumnStatsDataTupleSchemeFactory() {
      }

      public BooleanColumnStatsDataTupleScheme getScheme() {
         return new BooleanColumnStatsDataTupleScheme();
      }
   }

   private static class BooleanColumnStatsDataTupleScheme extends TupleScheme {
      private BooleanColumnStatsDataTupleScheme() {
      }

      public void write(TProtocol prot, BooleanColumnStatsData struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI64(struct.numTrues);
         oprot.writeI64(struct.numFalses);
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

      public void read(TProtocol prot, BooleanColumnStatsData struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.numTrues = iprot.readI64();
         struct.setNumTruesIsSet(true);
         struct.numFalses = iprot.readI64();
         struct.setNumFalsesIsSet(true);
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
