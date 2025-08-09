package org.apache.parquet.format;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import shaded.parquet.org.apache.thrift.EncodingUtils;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.ListMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TList;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class SizeStatistics implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("SizeStatistics");
   private static final TField UNENCODED_BYTE_ARRAY_DATA_BYTES_FIELD_DESC = new TField("unencoded_byte_array_data_bytes", (byte)10, (short)1);
   private static final TField REPETITION_LEVEL_HISTOGRAM_FIELD_DESC = new TField("repetition_level_histogram", (byte)15, (short)2);
   private static final TField DEFINITION_LEVEL_HISTOGRAM_FIELD_DESC = new TField("definition_level_histogram", (byte)15, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new SizeStatisticsStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new SizeStatisticsTupleSchemeFactory();
   public long unencoded_byte_array_data_bytes;
   @Nullable
   public List repetition_level_histogram;
   @Nullable
   public List definition_level_histogram;
   private static final int __UNENCODED_BYTE_ARRAY_DATA_BYTES_ISSET_ID = 0;
   private byte __isset_bitfield = 0;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public SizeStatistics() {
   }

   public SizeStatistics(SizeStatistics other) {
      this.__isset_bitfield = other.__isset_bitfield;
      this.unencoded_byte_array_data_bytes = other.unencoded_byte_array_data_bytes;
      if (other.isSetRepetition_level_histogram()) {
         List<Long> __this__repetition_level_histogram = new ArrayList(other.repetition_level_histogram);
         this.repetition_level_histogram = __this__repetition_level_histogram;
      }

      if (other.isSetDefinition_level_histogram()) {
         List<Long> __this__definition_level_histogram = new ArrayList(other.definition_level_histogram);
         this.definition_level_histogram = __this__definition_level_histogram;
      }

   }

   public SizeStatistics deepCopy() {
      return new SizeStatistics(this);
   }

   public void clear() {
      this.setUnencoded_byte_array_data_bytesIsSet(false);
      this.unencoded_byte_array_data_bytes = 0L;
      this.repetition_level_histogram = null;
      this.definition_level_histogram = null;
   }

   public long getUnencoded_byte_array_data_bytes() {
      return this.unencoded_byte_array_data_bytes;
   }

   public SizeStatistics setUnencoded_byte_array_data_bytes(long unencoded_byte_array_data_bytes) {
      this.unencoded_byte_array_data_bytes = unencoded_byte_array_data_bytes;
      this.setUnencoded_byte_array_data_bytesIsSet(true);
      return this;
   }

   public void unsetUnencoded_byte_array_data_bytes() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetUnencoded_byte_array_data_bytes() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setUnencoded_byte_array_data_bytesIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   public int getRepetition_level_histogramSize() {
      return this.repetition_level_histogram == null ? 0 : this.repetition_level_histogram.size();
   }

   @Nullable
   public Iterator getRepetition_level_histogramIterator() {
      return this.repetition_level_histogram == null ? null : this.repetition_level_histogram.iterator();
   }

   public void addToRepetition_level_histogram(long elem) {
      if (this.repetition_level_histogram == null) {
         this.repetition_level_histogram = new ArrayList();
      }

      this.repetition_level_histogram.add(elem);
   }

   @Nullable
   public List getRepetition_level_histogram() {
      return this.repetition_level_histogram;
   }

   public SizeStatistics setRepetition_level_histogram(@Nullable List repetition_level_histogram) {
      this.repetition_level_histogram = repetition_level_histogram;
      return this;
   }

   public void unsetRepetition_level_histogram() {
      this.repetition_level_histogram = null;
   }

   public boolean isSetRepetition_level_histogram() {
      return this.repetition_level_histogram != null;
   }

   public void setRepetition_level_histogramIsSet(boolean value) {
      if (!value) {
         this.repetition_level_histogram = null;
      }

   }

   public int getDefinition_level_histogramSize() {
      return this.definition_level_histogram == null ? 0 : this.definition_level_histogram.size();
   }

   @Nullable
   public Iterator getDefinition_level_histogramIterator() {
      return this.definition_level_histogram == null ? null : this.definition_level_histogram.iterator();
   }

   public void addToDefinition_level_histogram(long elem) {
      if (this.definition_level_histogram == null) {
         this.definition_level_histogram = new ArrayList();
      }

      this.definition_level_histogram.add(elem);
   }

   @Nullable
   public List getDefinition_level_histogram() {
      return this.definition_level_histogram;
   }

   public SizeStatistics setDefinition_level_histogram(@Nullable List definition_level_histogram) {
      this.definition_level_histogram = definition_level_histogram;
      return this;
   }

   public void unsetDefinition_level_histogram() {
      this.definition_level_histogram = null;
   }

   public boolean isSetDefinition_level_histogram() {
      return this.definition_level_histogram != null;
   }

   public void setDefinition_level_histogramIsSet(boolean value) {
      if (!value) {
         this.definition_level_histogram = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case UNENCODED_BYTE_ARRAY_DATA_BYTES:
            if (value == null) {
               this.unsetUnencoded_byte_array_data_bytes();
            } else {
               this.setUnencoded_byte_array_data_bytes((Long)value);
            }
            break;
         case REPETITION_LEVEL_HISTOGRAM:
            if (value == null) {
               this.unsetRepetition_level_histogram();
            } else {
               this.setRepetition_level_histogram((List)value);
            }
            break;
         case DEFINITION_LEVEL_HISTOGRAM:
            if (value == null) {
               this.unsetDefinition_level_histogram();
            } else {
               this.setDefinition_level_histogram((List)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case UNENCODED_BYTE_ARRAY_DATA_BYTES:
            return this.getUnencoded_byte_array_data_bytes();
         case REPETITION_LEVEL_HISTOGRAM:
            return this.getRepetition_level_histogram();
         case DEFINITION_LEVEL_HISTOGRAM:
            return this.getDefinition_level_histogram();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case UNENCODED_BYTE_ARRAY_DATA_BYTES:
               return this.isSetUnencoded_byte_array_data_bytes();
            case REPETITION_LEVEL_HISTOGRAM:
               return this.isSetRepetition_level_histogram();
            case DEFINITION_LEVEL_HISTOGRAM:
               return this.isSetDefinition_level_histogram();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof SizeStatistics ? this.equals((SizeStatistics)that) : false;
   }

   public boolean equals(SizeStatistics that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_unencoded_byte_array_data_bytes = this.isSetUnencoded_byte_array_data_bytes();
         boolean that_present_unencoded_byte_array_data_bytes = that.isSetUnencoded_byte_array_data_bytes();
         if (this_present_unencoded_byte_array_data_bytes || that_present_unencoded_byte_array_data_bytes) {
            if (!this_present_unencoded_byte_array_data_bytes || !that_present_unencoded_byte_array_data_bytes) {
               return false;
            }

            if (this.unencoded_byte_array_data_bytes != that.unencoded_byte_array_data_bytes) {
               return false;
            }
         }

         boolean this_present_repetition_level_histogram = this.isSetRepetition_level_histogram();
         boolean that_present_repetition_level_histogram = that.isSetRepetition_level_histogram();
         if (this_present_repetition_level_histogram || that_present_repetition_level_histogram) {
            if (!this_present_repetition_level_histogram || !that_present_repetition_level_histogram) {
               return false;
            }

            if (!this.repetition_level_histogram.equals(that.repetition_level_histogram)) {
               return false;
            }
         }

         boolean this_present_definition_level_histogram = this.isSetDefinition_level_histogram();
         boolean that_present_definition_level_histogram = that.isSetDefinition_level_histogram();
         if (this_present_definition_level_histogram || that_present_definition_level_histogram) {
            if (!this_present_definition_level_histogram || !that_present_definition_level_histogram) {
               return false;
            }

            if (!this.definition_level_histogram.equals(that.definition_level_histogram)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + (this.isSetUnencoded_byte_array_data_bytes() ? 131071 : 524287);
      if (this.isSetUnencoded_byte_array_data_bytes()) {
         hashCode = hashCode * 8191 + TBaseHelper.hashCode(this.unencoded_byte_array_data_bytes);
      }

      hashCode = hashCode * 8191 + (this.isSetRepetition_level_histogram() ? 131071 : 524287);
      if (this.isSetRepetition_level_histogram()) {
         hashCode = hashCode * 8191 + this.repetition_level_histogram.hashCode();
      }

      hashCode = hashCode * 8191 + (this.isSetDefinition_level_histogram() ? 131071 : 524287);
      if (this.isSetDefinition_level_histogram()) {
         hashCode = hashCode * 8191 + this.definition_level_histogram.hashCode();
      }

      return hashCode;
   }

   public int compareTo(SizeStatistics other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetUnencoded_byte_array_data_bytes(), other.isSetUnencoded_byte_array_data_bytes());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetUnencoded_byte_array_data_bytes()) {
               lastComparison = TBaseHelper.compareTo(this.unencoded_byte_array_data_bytes, other.unencoded_byte_array_data_bytes);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetRepetition_level_histogram(), other.isSetRepetition_level_histogram());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetRepetition_level_histogram()) {
                  lastComparison = TBaseHelper.compareTo(this.repetition_level_histogram, other.repetition_level_histogram);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetDefinition_level_histogram(), other.isSetDefinition_level_histogram());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetDefinition_level_histogram()) {
                     lastComparison = TBaseHelper.compareTo(this.definition_level_histogram, other.definition_level_histogram);
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
      return SizeStatistics._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("SizeStatistics(");
      boolean first = true;
      if (this.isSetUnencoded_byte_array_data_bytes()) {
         sb.append("unencoded_byte_array_data_bytes:");
         sb.append(this.unencoded_byte_array_data_bytes);
         first = false;
      }

      if (this.isSetRepetition_level_histogram()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("repetition_level_histogram:");
         if (this.repetition_level_histogram == null) {
            sb.append("null");
         } else {
            sb.append(this.repetition_level_histogram);
         }

         first = false;
      }

      if (this.isSetDefinition_level_histogram()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("definition_level_histogram:");
         if (this.definition_level_histogram == null) {
            sb.append("null");
         } else {
            sb.append(this.definition_level_histogram);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
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
      optionals = new _Fields[]{SizeStatistics._Fields.UNENCODED_BYTE_ARRAY_DATA_BYTES, SizeStatistics._Fields.REPETITION_LEVEL_HISTOGRAM, SizeStatistics._Fields.DEFINITION_LEVEL_HISTOGRAM};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(SizeStatistics._Fields.UNENCODED_BYTE_ARRAY_DATA_BYTES, new FieldMetaData("unencoded_byte_array_data_bytes", (byte)2, new FieldValueMetaData((byte)10)));
      tmpMap.put(SizeStatistics._Fields.REPETITION_LEVEL_HISTOGRAM, new FieldMetaData("repetition_level_histogram", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      tmpMap.put(SizeStatistics._Fields.DEFINITION_LEVEL_HISTOGRAM, new FieldMetaData("definition_level_histogram", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)10))));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(SizeStatistics.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      UNENCODED_BYTE_ARRAY_DATA_BYTES((short)1, "unencoded_byte_array_data_bytes"),
      REPETITION_LEVEL_HISTOGRAM((short)2, "repetition_level_histogram"),
      DEFINITION_LEVEL_HISTOGRAM((short)3, "definition_level_histogram");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return UNENCODED_BYTE_ARRAY_DATA_BYTES;
            case 2:
               return REPETITION_LEVEL_HISTOGRAM;
            case 3:
               return DEFINITION_LEVEL_HISTOGRAM;
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

   private static class SizeStatisticsStandardSchemeFactory implements SchemeFactory {
      private SizeStatisticsStandardSchemeFactory() {
      }

      public SizeStatisticsStandardScheme getScheme() {
         return new SizeStatisticsStandardScheme();
      }
   }

   private static class SizeStatisticsStandardScheme extends StandardScheme {
      private SizeStatisticsStandardScheme() {
      }

      public void read(TProtocol iprot, SizeStatistics struct) throws TException {
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
                     struct.unencoded_byte_array_data_bytes = iprot.readI64();
                     struct.setUnencoded_byte_array_data_bytesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list0 = iprot.readListBegin();
                  struct.repetition_level_histogram = new ArrayList(_list0.size);

                  for(int _i2 = 0; _i2 < _list0.size; ++_i2) {
                     long _elem1 = iprot.readI64();
                     struct.repetition_level_histogram.add(_elem1);
                  }

                  iprot.readListEnd();
                  struct.setRepetition_level_histogramIsSet(true);
                  break;
               case 3:
                  if (schemeField.type != 15) {
                     TProtocolUtil.skip(iprot, schemeField.type);
                     break;
                  }

                  TList _list3 = iprot.readListBegin();
                  struct.definition_level_histogram = new ArrayList(_list3.size);

                  for(int _i5 = 0; _i5 < _list3.size; ++_i5) {
                     long _elem4 = iprot.readI64();
                     struct.definition_level_histogram.add(_elem4);
                  }

                  iprot.readListEnd();
                  struct.setDefinition_level_histogramIsSet(true);
                  break;
               default:
                  TProtocolUtil.skip(iprot, schemeField.type);
            }

            iprot.readFieldEnd();
         }
      }

      public void write(TProtocol oprot, SizeStatistics struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(SizeStatistics.STRUCT_DESC);
         if (struct.isSetUnencoded_byte_array_data_bytes()) {
            oprot.writeFieldBegin(SizeStatistics.UNENCODED_BYTE_ARRAY_DATA_BYTES_FIELD_DESC);
            oprot.writeI64(struct.unencoded_byte_array_data_bytes);
            oprot.writeFieldEnd();
         }

         if (struct.repetition_level_histogram != null && struct.isSetRepetition_level_histogram()) {
            oprot.writeFieldBegin(SizeStatistics.REPETITION_LEVEL_HISTOGRAM_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.repetition_level_histogram.size()));

            for(long _iter6 : struct.repetition_level_histogram) {
               oprot.writeI64(_iter6);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         if (struct.definition_level_histogram != null && struct.isSetDefinition_level_histogram()) {
            oprot.writeFieldBegin(SizeStatistics.DEFINITION_LEVEL_HISTOGRAM_FIELD_DESC);
            oprot.writeListBegin(new TList((byte)10, struct.definition_level_histogram.size()));

            for(long _iter7 : struct.definition_level_histogram) {
               oprot.writeI64(_iter7);
            }

            oprot.writeListEnd();
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class SizeStatisticsTupleSchemeFactory implements SchemeFactory {
      private SizeStatisticsTupleSchemeFactory() {
      }

      public SizeStatisticsTupleScheme getScheme() {
         return new SizeStatisticsTupleScheme();
      }
   }

   private static class SizeStatisticsTupleScheme extends TupleScheme {
      private SizeStatisticsTupleScheme() {
      }

      public void write(TProtocol prot, SizeStatistics struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         BitSet optionals = new BitSet();
         if (struct.isSetUnencoded_byte_array_data_bytes()) {
            optionals.set(0);
         }

         if (struct.isSetRepetition_level_histogram()) {
            optionals.set(1);
         }

         if (struct.isSetDefinition_level_histogram()) {
            optionals.set(2);
         }

         oprot.writeBitSet(optionals, 3);
         if (struct.isSetUnencoded_byte_array_data_bytes()) {
            oprot.writeI64(struct.unencoded_byte_array_data_bytes);
         }

         if (struct.isSetRepetition_level_histogram()) {
            oprot.writeI32(struct.repetition_level_histogram.size());

            for(long _iter8 : struct.repetition_level_histogram) {
               oprot.writeI64(_iter8);
            }
         }

         if (struct.isSetDefinition_level_histogram()) {
            oprot.writeI32(struct.definition_level_histogram.size());

            for(long _iter9 : struct.definition_level_histogram) {
               oprot.writeI64(_iter9);
            }
         }

      }

      public void read(TProtocol prot, SizeStatistics struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         BitSet incoming = iprot.readBitSet(3);
         if (incoming.get(0)) {
            struct.unencoded_byte_array_data_bytes = iprot.readI64();
            struct.setUnencoded_byte_array_data_bytesIsSet(true);
         }

         if (incoming.get(1)) {
            TList _list10 = iprot.readListBegin((byte)10);
            struct.repetition_level_histogram = new ArrayList(_list10.size);

            for(int _i12 = 0; _i12 < _list10.size; ++_i12) {
               long _elem11 = iprot.readI64();
               struct.repetition_level_histogram.add(_elem11);
            }

            struct.setRepetition_level_histogramIsSet(true);
         }

         if (incoming.get(2)) {
            TList _list13 = iprot.readListBegin((byte)10);
            struct.definition_level_histogram = new ArrayList(_list13.size);

            for(int _i15 = 0; _i15 < _list13.size; ++_i15) {
               long _elem14 = iprot.readI64();
               struct.definition_level_histogram.add(_elem14);
            }

            struct.setDefinition_level_histogramIsSet(true);
         }

      }
   }
}
