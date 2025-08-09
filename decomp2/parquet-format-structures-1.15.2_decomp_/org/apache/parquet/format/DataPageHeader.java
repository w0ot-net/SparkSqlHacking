package org.apache.parquet.format;

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
import shaded.parquet.org.apache.thrift.EncodingUtils;
import shaded.parquet.org.apache.thrift.TBase;
import shaded.parquet.org.apache.thrift.TBaseHelper;
import shaded.parquet.org.apache.thrift.TException;
import shaded.parquet.org.apache.thrift.TFieldIdEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;
import shaded.parquet.org.apache.thrift.meta_data.EnumMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldMetaData;
import shaded.parquet.org.apache.thrift.meta_data.FieldValueMetaData;
import shaded.parquet.org.apache.thrift.meta_data.StructMetaData;
import shaded.parquet.org.apache.thrift.protocol.TCompactProtocol;
import shaded.parquet.org.apache.thrift.protocol.TField;
import shaded.parquet.org.apache.thrift.protocol.TProtocol;
import shaded.parquet.org.apache.thrift.protocol.TProtocolException;
import shaded.parquet.org.apache.thrift.protocol.TProtocolUtil;
import shaded.parquet.org.apache.thrift.protocol.TStruct;
import shaded.parquet.org.apache.thrift.protocol.TTupleProtocol;
import shaded.parquet.org.apache.thrift.scheme.IScheme;
import shaded.parquet.org.apache.thrift.scheme.SchemeFactory;
import shaded.parquet.org.apache.thrift.scheme.StandardScheme;
import shaded.parquet.org.apache.thrift.scheme.TupleScheme;
import shaded.parquet.org.apache.thrift.transport.TIOStreamTransport;

public class DataPageHeader implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DataPageHeader");
   private static final TField NUM_VALUES_FIELD_DESC = new TField("num_values", (byte)8, (short)1);
   private static final TField ENCODING_FIELD_DESC = new TField("encoding", (byte)8, (short)2);
   private static final TField DEFINITION_LEVEL_ENCODING_FIELD_DESC = new TField("definition_level_encoding", (byte)8, (short)3);
   private static final TField REPETITION_LEVEL_ENCODING_FIELD_DESC = new TField("repetition_level_encoding", (byte)8, (short)4);
   private static final TField STATISTICS_FIELD_DESC = new TField("statistics", (byte)12, (short)5);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DataPageHeaderStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DataPageHeaderTupleSchemeFactory();
   public int num_values;
   @Nullable
   public Encoding encoding;
   @Nullable
   public Encoding definition_level_encoding;
   @Nullable
   public Encoding repetition_level_encoding;
   @Nullable
   public Statistics statistics;
   private static final int __NUM_VALUES_ISSET_ID = 0;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DataPageHeader() {
      this.__isset_bitfield = 0;
   }

   public DataPageHeader(int num_values, Encoding encoding, Encoding definition_level_encoding, Encoding repetition_level_encoding) {
      this();
      this.num_values = num_values;
      this.setNum_valuesIsSet(true);
      this.encoding = encoding;
      this.definition_level_encoding = definition_level_encoding;
      this.repetition_level_encoding = repetition_level_encoding;
   }

   public DataPageHeader(DataPageHeader other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.num_values = other.num_values;
      if (other.isSetEncoding()) {
         this.encoding = other.encoding;
      }

      if (other.isSetDefinition_level_encoding()) {
         this.definition_level_encoding = other.definition_level_encoding;
      }

      if (other.isSetRepetition_level_encoding()) {
         this.repetition_level_encoding = other.repetition_level_encoding;
      }

      if (other.isSetStatistics()) {
         this.statistics = new Statistics(other.statistics);
      }

   }

   public DataPageHeader deepCopy() {
      return new DataPageHeader(this);
   }

   public void clear() {
      this.setNum_valuesIsSet(false);
      this.num_values = 0;
      this.encoding = null;
      this.definition_level_encoding = null;
      this.repetition_level_encoding = null;
      this.statistics = null;
   }

   public int getNum_values() {
      return this.num_values;
   }

   public DataPageHeader setNum_values(int num_values) {
      this.num_values = num_values;
      this.setNum_valuesIsSet(true);
      return this;
   }

   public void unsetNum_values() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 0);
   }

   public boolean isSetNum_values() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 0);
   }

   public void setNum_valuesIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 0, value);
   }

   @Nullable
   public Encoding getEncoding() {
      return this.encoding;
   }

   public DataPageHeader setEncoding(@Nullable Encoding encoding) {
      this.encoding = encoding;
      return this;
   }

   public void unsetEncoding() {
      this.encoding = null;
   }

   public boolean isSetEncoding() {
      return this.encoding != null;
   }

   public void setEncodingIsSet(boolean value) {
      if (!value) {
         this.encoding = null;
      }

   }

   @Nullable
   public Encoding getDefinition_level_encoding() {
      return this.definition_level_encoding;
   }

   public DataPageHeader setDefinition_level_encoding(@Nullable Encoding definition_level_encoding) {
      this.definition_level_encoding = definition_level_encoding;
      return this;
   }

   public void unsetDefinition_level_encoding() {
      this.definition_level_encoding = null;
   }

   public boolean isSetDefinition_level_encoding() {
      return this.definition_level_encoding != null;
   }

   public void setDefinition_level_encodingIsSet(boolean value) {
      if (!value) {
         this.definition_level_encoding = null;
      }

   }

   @Nullable
   public Encoding getRepetition_level_encoding() {
      return this.repetition_level_encoding;
   }

   public DataPageHeader setRepetition_level_encoding(@Nullable Encoding repetition_level_encoding) {
      this.repetition_level_encoding = repetition_level_encoding;
      return this;
   }

   public void unsetRepetition_level_encoding() {
      this.repetition_level_encoding = null;
   }

   public boolean isSetRepetition_level_encoding() {
      return this.repetition_level_encoding != null;
   }

   public void setRepetition_level_encodingIsSet(boolean value) {
      if (!value) {
         this.repetition_level_encoding = null;
      }

   }

   @Nullable
   public Statistics getStatistics() {
      return this.statistics;
   }

   public DataPageHeader setStatistics(@Nullable Statistics statistics) {
      this.statistics = statistics;
      return this;
   }

   public void unsetStatistics() {
      this.statistics = null;
   }

   public boolean isSetStatistics() {
      return this.statistics != null;
   }

   public void setStatisticsIsSet(boolean value) {
      if (!value) {
         this.statistics = null;
      }

   }

   public void setFieldValue(_Fields field, @Nullable Object value) {
      switch (field) {
         case NUM_VALUES:
            if (value == null) {
               this.unsetNum_values();
            } else {
               this.setNum_values((Integer)value);
            }
            break;
         case ENCODING:
            if (value == null) {
               this.unsetEncoding();
            } else {
               this.setEncoding((Encoding)value);
            }
            break;
         case DEFINITION_LEVEL_ENCODING:
            if (value == null) {
               this.unsetDefinition_level_encoding();
            } else {
               this.setDefinition_level_encoding((Encoding)value);
            }
            break;
         case REPETITION_LEVEL_ENCODING:
            if (value == null) {
               this.unsetRepetition_level_encoding();
            } else {
               this.setRepetition_level_encoding((Encoding)value);
            }
            break;
         case STATISTICS:
            if (value == null) {
               this.unsetStatistics();
            } else {
               this.setStatistics((Statistics)value);
            }
      }

   }

   @Nullable
   public Object getFieldValue(_Fields field) {
      switch (field) {
         case NUM_VALUES:
            return this.getNum_values();
         case ENCODING:
            return this.getEncoding();
         case DEFINITION_LEVEL_ENCODING:
            return this.getDefinition_level_encoding();
         case REPETITION_LEVEL_ENCODING:
            return this.getRepetition_level_encoding();
         case STATISTICS:
            return this.getStatistics();
         default:
            throw new IllegalStateException();
      }
   }

   public boolean isSet(_Fields field) {
      if (field == null) {
         throw new IllegalArgumentException();
      } else {
         switch (field) {
            case NUM_VALUES:
               return this.isSetNum_values();
            case ENCODING:
               return this.isSetEncoding();
            case DEFINITION_LEVEL_ENCODING:
               return this.isSetDefinition_level_encoding();
            case REPETITION_LEVEL_ENCODING:
               return this.isSetRepetition_level_encoding();
            case STATISTICS:
               return this.isSetStatistics();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DataPageHeader ? this.equals((DataPageHeader)that) : false;
   }

   public boolean equals(DataPageHeader that) {
      if (that == null) {
         return false;
      } else if (this == that) {
         return true;
      } else {
         boolean this_present_num_values = true;
         boolean that_present_num_values = true;
         if (this_present_num_values || that_present_num_values) {
            if (!this_present_num_values || !that_present_num_values) {
               return false;
            }

            if (this.num_values != that.num_values) {
               return false;
            }
         }

         boolean this_present_encoding = this.isSetEncoding();
         boolean that_present_encoding = that.isSetEncoding();
         if (this_present_encoding || that_present_encoding) {
            if (!this_present_encoding || !that_present_encoding) {
               return false;
            }

            if (!this.encoding.equals(that.encoding)) {
               return false;
            }
         }

         boolean this_present_definition_level_encoding = this.isSetDefinition_level_encoding();
         boolean that_present_definition_level_encoding = that.isSetDefinition_level_encoding();
         if (this_present_definition_level_encoding || that_present_definition_level_encoding) {
            if (!this_present_definition_level_encoding || !that_present_definition_level_encoding) {
               return false;
            }

            if (!this.definition_level_encoding.equals(that.definition_level_encoding)) {
               return false;
            }
         }

         boolean this_present_repetition_level_encoding = this.isSetRepetition_level_encoding();
         boolean that_present_repetition_level_encoding = that.isSetRepetition_level_encoding();
         if (this_present_repetition_level_encoding || that_present_repetition_level_encoding) {
            if (!this_present_repetition_level_encoding || !that_present_repetition_level_encoding) {
               return false;
            }

            if (!this.repetition_level_encoding.equals(that.repetition_level_encoding)) {
               return false;
            }
         }

         boolean this_present_statistics = this.isSetStatistics();
         boolean that_present_statistics = that.isSetStatistics();
         if (this_present_statistics || that_present_statistics) {
            if (!this_present_statistics || !that_present_statistics) {
               return false;
            }

            if (!this.statistics.equals(that.statistics)) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      int hashCode = 1;
      hashCode = hashCode * 8191 + this.num_values;
      hashCode = hashCode * 8191 + (this.isSetEncoding() ? 131071 : 524287);
      if (this.isSetEncoding()) {
         hashCode = hashCode * 8191 + this.encoding.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetDefinition_level_encoding() ? 131071 : 524287);
      if (this.isSetDefinition_level_encoding()) {
         hashCode = hashCode * 8191 + this.definition_level_encoding.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetRepetition_level_encoding() ? 131071 : 524287);
      if (this.isSetRepetition_level_encoding()) {
         hashCode = hashCode * 8191 + this.repetition_level_encoding.getValue();
      }

      hashCode = hashCode * 8191 + (this.isSetStatistics() ? 131071 : 524287);
      if (this.isSetStatistics()) {
         hashCode = hashCode * 8191 + this.statistics.hashCode();
      }

      return hashCode;
   }

   public int compareTo(DataPageHeader other) {
      if (!this.getClass().equals(other.getClass())) {
         return this.getClass().getName().compareTo(other.getClass().getName());
      } else {
         int lastComparison = 0;
         lastComparison = Boolean.compare(this.isSetNum_values(), other.isSetNum_values());
         if (lastComparison != 0) {
            return lastComparison;
         } else {
            if (this.isSetNum_values()) {
               lastComparison = TBaseHelper.compareTo(this.num_values, other.num_values);
               if (lastComparison != 0) {
                  return lastComparison;
               }
            }

            lastComparison = Boolean.compare(this.isSetEncoding(), other.isSetEncoding());
            if (lastComparison != 0) {
               return lastComparison;
            } else {
               if (this.isSetEncoding()) {
                  lastComparison = TBaseHelper.compareTo((Comparable)this.encoding, (Comparable)other.encoding);
                  if (lastComparison != 0) {
                     return lastComparison;
                  }
               }

               lastComparison = Boolean.compare(this.isSetDefinition_level_encoding(), other.isSetDefinition_level_encoding());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetDefinition_level_encoding()) {
                     lastComparison = TBaseHelper.compareTo((Comparable)this.definition_level_encoding, (Comparable)other.definition_level_encoding);
                     if (lastComparison != 0) {
                        return lastComparison;
                     }
                  }

                  lastComparison = Boolean.compare(this.isSetRepetition_level_encoding(), other.isSetRepetition_level_encoding());
                  if (lastComparison != 0) {
                     return lastComparison;
                  } else {
                     if (this.isSetRepetition_level_encoding()) {
                        lastComparison = TBaseHelper.compareTo((Comparable)this.repetition_level_encoding, (Comparable)other.repetition_level_encoding);
                        if (lastComparison != 0) {
                           return lastComparison;
                        }
                     }

                     lastComparison = Boolean.compare(this.isSetStatistics(), other.isSetStatistics());
                     if (lastComparison != 0) {
                        return lastComparison;
                     } else {
                        if (this.isSetStatistics()) {
                           lastComparison = TBaseHelper.compareTo((Comparable)this.statistics, (Comparable)other.statistics);
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
      return DataPageHeader._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DataPageHeader(");
      boolean first = true;
      sb.append("num_values:");
      sb.append(this.num_values);
      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("encoding:");
      if (this.encoding == null) {
         sb.append("null");
      } else {
         sb.append(this.encoding);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("definition_level_encoding:");
      if (this.definition_level_encoding == null) {
         sb.append("null");
      } else {
         sb.append(this.definition_level_encoding);
      }

      first = false;
      if (!first) {
         sb.append(", ");
      }

      sb.append("repetition_level_encoding:");
      if (this.repetition_level_encoding == null) {
         sb.append("null");
      } else {
         sb.append(this.repetition_level_encoding);
      }

      first = false;
      if (this.isSetStatistics()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("statistics:");
         if (this.statistics == null) {
            sb.append("null");
         } else {
            sb.append(this.statistics);
         }

         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.encoding == null) {
         throw new TProtocolException("Required field 'encoding' was not present! Struct: " + this.toString());
      } else if (this.definition_level_encoding == null) {
         throw new TProtocolException("Required field 'definition_level_encoding' was not present! Struct: " + this.toString());
      } else if (this.repetition_level_encoding == null) {
         throw new TProtocolException("Required field 'repetition_level_encoding' was not present! Struct: " + this.toString());
      } else {
         if (this.statistics != null) {
            this.statistics.validate();
         }

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
      optionals = new _Fields[]{DataPageHeader._Fields.STATISTICS};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DataPageHeader._Fields.NUM_VALUES, new FieldMetaData("num_values", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DataPageHeader._Fields.ENCODING, new FieldMetaData("encoding", (byte)1, new EnumMetaData((byte)-1, Encoding.class)));
      tmpMap.put(DataPageHeader._Fields.DEFINITION_LEVEL_ENCODING, new FieldMetaData("definition_level_encoding", (byte)1, new EnumMetaData((byte)-1, Encoding.class)));
      tmpMap.put(DataPageHeader._Fields.REPETITION_LEVEL_ENCODING, new FieldMetaData("repetition_level_encoding", (byte)1, new EnumMetaData((byte)-1, Encoding.class)));
      tmpMap.put(DataPageHeader._Fields.STATISTICS, new FieldMetaData("statistics", (byte)2, new StructMetaData((byte)12, Statistics.class)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DataPageHeader.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NUM_VALUES((short)1, "num_values"),
      ENCODING((short)2, "encoding"),
      DEFINITION_LEVEL_ENCODING((short)3, "definition_level_encoding"),
      REPETITION_LEVEL_ENCODING((short)4, "repetition_level_encoding"),
      STATISTICS((short)5, "statistics");

      private static final Map byName = new HashMap();
      private final short _thriftId;
      private final String _fieldName;

      @Nullable
      public static _Fields findByThriftId(int fieldId) {
         switch (fieldId) {
            case 1:
               return NUM_VALUES;
            case 2:
               return ENCODING;
            case 3:
               return DEFINITION_LEVEL_ENCODING;
            case 4:
               return REPETITION_LEVEL_ENCODING;
            case 5:
               return STATISTICS;
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

   private static class DataPageHeaderStandardSchemeFactory implements SchemeFactory {
      private DataPageHeaderStandardSchemeFactory() {
      }

      public DataPageHeaderStandardScheme getScheme() {
         return new DataPageHeaderStandardScheme();
      }
   }

   private static class DataPageHeaderStandardScheme extends StandardScheme {
      private DataPageHeaderStandardScheme() {
      }

      public void read(TProtocol iprot, DataPageHeader struct) throws TException {
         iprot.readStructBegin();

         while(true) {
            TField schemeField = iprot.readFieldBegin();
            if (schemeField.type == 0) {
               iprot.readStructEnd();
               if (!struct.isSetNum_values()) {
                  throw new TProtocolException("Required field 'num_values' was not found in serialized data! Struct: " + this.toString());
               }

               struct.validate();
               return;
            }

            switch (schemeField.id) {
               case 1:
                  if (schemeField.type == 8) {
                     struct.num_values = iprot.readI32();
                     struct.setNum_valuesIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 2:
                  if (schemeField.type == 8) {
                     struct.encoding = Encoding.findByValue(iprot.readI32());
                     struct.setEncodingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 3:
                  if (schemeField.type == 8) {
                     struct.definition_level_encoding = Encoding.findByValue(iprot.readI32());
                     struct.setDefinition_level_encodingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 4:
                  if (schemeField.type == 8) {
                     struct.repetition_level_encoding = Encoding.findByValue(iprot.readI32());
                     struct.setRepetition_level_encodingIsSet(true);
                  } else {
                     TProtocolUtil.skip(iprot, schemeField.type);
                  }
                  break;
               case 5:
                  if (schemeField.type == 12) {
                     struct.statistics = new Statistics();
                     struct.statistics.read(iprot);
                     struct.setStatisticsIsSet(true);
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

      public void write(TProtocol oprot, DataPageHeader struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DataPageHeader.STRUCT_DESC);
         oprot.writeFieldBegin(DataPageHeader.NUM_VALUES_FIELD_DESC);
         oprot.writeI32(struct.num_values);
         oprot.writeFieldEnd();
         if (struct.encoding != null) {
            oprot.writeFieldBegin(DataPageHeader.ENCODING_FIELD_DESC);
            oprot.writeI32(struct.encoding.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.definition_level_encoding != null) {
            oprot.writeFieldBegin(DataPageHeader.DEFINITION_LEVEL_ENCODING_FIELD_DESC);
            oprot.writeI32(struct.definition_level_encoding.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.repetition_level_encoding != null) {
            oprot.writeFieldBegin(DataPageHeader.REPETITION_LEVEL_ENCODING_FIELD_DESC);
            oprot.writeI32(struct.repetition_level_encoding.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.statistics != null && struct.isSetStatistics()) {
            oprot.writeFieldBegin(DataPageHeader.STATISTICS_FIELD_DESC);
            struct.statistics.write(oprot);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DataPageHeaderTupleSchemeFactory implements SchemeFactory {
      private DataPageHeaderTupleSchemeFactory() {
      }

      public DataPageHeaderTupleScheme getScheme() {
         return new DataPageHeaderTupleScheme();
      }
   }

   private static class DataPageHeaderTupleScheme extends TupleScheme {
      private DataPageHeaderTupleScheme() {
      }

      public void write(TProtocol prot, DataPageHeader struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.num_values);
         oprot.writeI32(struct.encoding.getValue());
         oprot.writeI32(struct.definition_level_encoding.getValue());
         oprot.writeI32(struct.repetition_level_encoding.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetStatistics()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetStatistics()) {
            struct.statistics.write(oprot);
         }

      }

      public void read(TProtocol prot, DataPageHeader struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.num_values = iprot.readI32();
         struct.setNum_valuesIsSet(true);
         struct.encoding = Encoding.findByValue(iprot.readI32());
         struct.setEncodingIsSet(true);
         struct.definition_level_encoding = Encoding.findByValue(iprot.readI32());
         struct.setDefinition_level_encodingIsSet(true);
         struct.repetition_level_encoding = Encoding.findByValue(iprot.readI32());
         struct.setRepetition_level_encodingIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.statistics = new Statistics();
            struct.statistics.read(iprot);
            struct.setStatisticsIsSet(true);
         }

      }
   }
}
