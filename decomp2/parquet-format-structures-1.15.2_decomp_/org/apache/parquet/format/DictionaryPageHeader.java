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

public class DictionaryPageHeader implements TBase, Serializable, Cloneable, Comparable {
   private static final TStruct STRUCT_DESC = new TStruct("DictionaryPageHeader");
   private static final TField NUM_VALUES_FIELD_DESC = new TField("num_values", (byte)8, (short)1);
   private static final TField ENCODING_FIELD_DESC = new TField("encoding", (byte)8, (short)2);
   private static final TField IS_SORTED_FIELD_DESC = new TField("is_sorted", (byte)2, (short)3);
   private static final SchemeFactory STANDARD_SCHEME_FACTORY = new DictionaryPageHeaderStandardSchemeFactory();
   private static final SchemeFactory TUPLE_SCHEME_FACTORY = new DictionaryPageHeaderTupleSchemeFactory();
   public int num_values;
   @Nullable
   public Encoding encoding;
   public boolean is_sorted;
   private static final int __NUM_VALUES_ISSET_ID = 0;
   private static final int __IS_SORTED_ISSET_ID = 1;
   private byte __isset_bitfield;
   private static final _Fields[] optionals;
   public static final Map metaDataMap;

   public DictionaryPageHeader() {
      this.__isset_bitfield = 0;
   }

   public DictionaryPageHeader(int num_values, Encoding encoding) {
      this();
      this.num_values = num_values;
      this.setNum_valuesIsSet(true);
      this.encoding = encoding;
   }

   public DictionaryPageHeader(DictionaryPageHeader other) {
      this.__isset_bitfield = 0;
      this.__isset_bitfield = other.__isset_bitfield;
      this.num_values = other.num_values;
      if (other.isSetEncoding()) {
         this.encoding = other.encoding;
      }

      this.is_sorted = other.is_sorted;
   }

   public DictionaryPageHeader deepCopy() {
      return new DictionaryPageHeader(this);
   }

   public void clear() {
      this.setNum_valuesIsSet(false);
      this.num_values = 0;
      this.encoding = null;
      this.setIs_sortedIsSet(false);
      this.is_sorted = false;
   }

   public int getNum_values() {
      return this.num_values;
   }

   public DictionaryPageHeader setNum_values(int num_values) {
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

   public DictionaryPageHeader setEncoding(@Nullable Encoding encoding) {
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

   public boolean isIs_sorted() {
      return this.is_sorted;
   }

   public DictionaryPageHeader setIs_sorted(boolean is_sorted) {
      this.is_sorted = is_sorted;
      this.setIs_sortedIsSet(true);
      return this;
   }

   public void unsetIs_sorted() {
      this.__isset_bitfield = EncodingUtils.clearBit((byte)this.__isset_bitfield, 1);
   }

   public boolean isSetIs_sorted() {
      return EncodingUtils.testBit((byte)this.__isset_bitfield, 1);
   }

   public void setIs_sortedIsSet(boolean value) {
      this.__isset_bitfield = EncodingUtils.setBit((byte)this.__isset_bitfield, 1, value);
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
         case IS_SORTED:
            if (value == null) {
               this.unsetIs_sorted();
            } else {
               this.setIs_sorted((Boolean)value);
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
         case IS_SORTED:
            return this.isIs_sorted();
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
            case IS_SORTED:
               return this.isSetIs_sorted();
            default:
               throw new IllegalStateException();
         }
      }
   }

   public boolean equals(Object that) {
      return that instanceof DictionaryPageHeader ? this.equals((DictionaryPageHeader)that) : false;
   }

   public boolean equals(DictionaryPageHeader that) {
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

         boolean this_present_is_sorted = this.isSetIs_sorted();
         boolean that_present_is_sorted = that.isSetIs_sorted();
         if (this_present_is_sorted || that_present_is_sorted) {
            if (!this_present_is_sorted || !that_present_is_sorted) {
               return false;
            }

            if (this.is_sorted != that.is_sorted) {
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

      hashCode = hashCode * 8191 + (this.isSetIs_sorted() ? 131071 : 524287);
      if (this.isSetIs_sorted()) {
         hashCode = hashCode * 8191 + (this.is_sorted ? 131071 : 524287);
      }

      return hashCode;
   }

   public int compareTo(DictionaryPageHeader other) {
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

               lastComparison = Boolean.compare(this.isSetIs_sorted(), other.isSetIs_sorted());
               if (lastComparison != 0) {
                  return lastComparison;
               } else {
                  if (this.isSetIs_sorted()) {
                     lastComparison = TBaseHelper.compareTo(this.is_sorted, other.is_sorted);
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
      return DictionaryPageHeader._Fields.findByThriftId(fieldId);
   }

   public void read(TProtocol iprot) throws TException {
      scheme(iprot).read(iprot, this);
   }

   public void write(TProtocol oprot) throws TException {
      scheme(oprot).write(oprot, this);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("DictionaryPageHeader(");
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
      if (this.isSetIs_sorted()) {
         if (!first) {
            sb.append(", ");
         }

         sb.append("is_sorted:");
         sb.append(this.is_sorted);
         first = false;
      }

      sb.append(")");
      return sb.toString();
   }

   public void validate() throws TException {
      if (this.encoding == null) {
         throw new TProtocolException("Required field 'encoding' was not present! Struct: " + this.toString());
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
      optionals = new _Fields[]{DictionaryPageHeader._Fields.IS_SORTED};
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap(_Fields.class);
      tmpMap.put(DictionaryPageHeader._Fields.NUM_VALUES, new FieldMetaData("num_values", (byte)1, new FieldValueMetaData((byte)8)));
      tmpMap.put(DictionaryPageHeader._Fields.ENCODING, new FieldMetaData("encoding", (byte)1, new EnumMetaData((byte)-1, Encoding.class)));
      tmpMap.put(DictionaryPageHeader._Fields.IS_SORTED, new FieldMetaData("is_sorted", (byte)2, new FieldValueMetaData((byte)2)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(DictionaryPageHeader.class, metaDataMap);
   }

   public static enum _Fields implements TFieldIdEnum {
      NUM_VALUES((short)1, "num_values"),
      ENCODING((short)2, "encoding"),
      IS_SORTED((short)3, "is_sorted");

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
               return IS_SORTED;
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

   private static class DictionaryPageHeaderStandardSchemeFactory implements SchemeFactory {
      private DictionaryPageHeaderStandardSchemeFactory() {
      }

      public DictionaryPageHeaderStandardScheme getScheme() {
         return new DictionaryPageHeaderStandardScheme();
      }
   }

   private static class DictionaryPageHeaderStandardScheme extends StandardScheme {
      private DictionaryPageHeaderStandardScheme() {
      }

      public void read(TProtocol iprot, DictionaryPageHeader struct) throws TException {
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
                  if (schemeField.type == 2) {
                     struct.is_sorted = iprot.readBool();
                     struct.setIs_sortedIsSet(true);
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

      public void write(TProtocol oprot, DictionaryPageHeader struct) throws TException {
         struct.validate();
         oprot.writeStructBegin(DictionaryPageHeader.STRUCT_DESC);
         oprot.writeFieldBegin(DictionaryPageHeader.NUM_VALUES_FIELD_DESC);
         oprot.writeI32(struct.num_values);
         oprot.writeFieldEnd();
         if (struct.encoding != null) {
            oprot.writeFieldBegin(DictionaryPageHeader.ENCODING_FIELD_DESC);
            oprot.writeI32(struct.encoding.getValue());
            oprot.writeFieldEnd();
         }

         if (struct.isSetIs_sorted()) {
            oprot.writeFieldBegin(DictionaryPageHeader.IS_SORTED_FIELD_DESC);
            oprot.writeBool(struct.is_sorted);
            oprot.writeFieldEnd();
         }

         oprot.writeFieldStop();
         oprot.writeStructEnd();
      }
   }

   private static class DictionaryPageHeaderTupleSchemeFactory implements SchemeFactory {
      private DictionaryPageHeaderTupleSchemeFactory() {
      }

      public DictionaryPageHeaderTupleScheme getScheme() {
         return new DictionaryPageHeaderTupleScheme();
      }
   }

   private static class DictionaryPageHeaderTupleScheme extends TupleScheme {
      private DictionaryPageHeaderTupleScheme() {
      }

      public void write(TProtocol prot, DictionaryPageHeader struct) throws TException {
         TTupleProtocol oprot = (TTupleProtocol)prot;
         oprot.writeI32(struct.num_values);
         oprot.writeI32(struct.encoding.getValue());
         BitSet optionals = new BitSet();
         if (struct.isSetIs_sorted()) {
            optionals.set(0);
         }

         oprot.writeBitSet(optionals, 1);
         if (struct.isSetIs_sorted()) {
            oprot.writeBool(struct.is_sorted);
         }

      }

      public void read(TProtocol prot, DictionaryPageHeader struct) throws TException {
         TTupleProtocol iprot = (TTupleProtocol)prot;
         struct.num_values = iprot.readI32();
         struct.setNum_valuesIsSet(true);
         struct.encoding = Encoding.findByValue(iprot.readI32());
         struct.setEncodingIsSet(true);
         BitSet incoming = iprot.readBitSet(1);
         if (incoming.get(0)) {
            struct.is_sorted = iprot.readBool();
            struct.setIs_sortedIsSet(true);
         }

      }
   }
}
